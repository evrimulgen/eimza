using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using tr.gov.tubitak.uekae.esya.api.common.util;
using tr.gov.tubitak.uekae.esya.api.smartcard.pkcs11;
using tr.gov.tubitak.uekae.esya.api.asn.x509;
using tr.gov.tubitak.uekae.esya.api.signature;
using tr.gov.tubitak.uekae.esya.api.cmssignature.validation;
using tr.gov.tubitak.uekae.esya.api.common.util.bag;
using tr.gov.tubitak.uekae.esya.api.cmssignature.signature;
using tr.gov.tubitak.uekae.esya.api.webservice.mssclient.wrapper.signer;
using tr.gov.tubitak.uekae.esya.api.cmssignature;
using tr.gov.tubitak.uekae.esya.api.cmssignature.attribute;
using tr.gov.tubitak.uekae.esya.api.certificate.validation.policy;
using System.IO;
using tr.gov.tubitak.uekae.esya.api.common.crypto;
using tr.gov.tubitak.uekae.esya.api.signature.util;
using tr.gov.tubitak.uekae.esya.api.crypto.alg;
using tr.gov.tubitak.uekae.esya.api.signature.config;
using iTextSharp.text.pdf;
using iTextSharp.text.pdf.security;
using Org.BouncyCastle.Crypto.Parameters;
using Org.BouncyCastle.Pkcs;
using Org.BouncyCastle.X509;
using System.Security.Cryptography.X509Certificates;
using Org.BouncyCastle.Security;

namespace ESign_Util
{
    public class ESignUtil : IESignUtil
    {
        public String configFile;
        public String dataTextFile;
        public String dataFileContentType;
        public String policyFile;

        private ValidationPolicy policy;
        private BaseSigner mSigner;

        ValidationPolicy getPolicy()
        {
            if (policy == null)
            {
                try
                {
                    policy = PolicyReader.readValidationPolicy(new FileStream(policyFile, FileMode.Open));
                    //For UEKAE Test Environment, we add our test roots.
                    //Dictionary<String, Object> parameters = new Dictionary<String, Object>();
                    //parameters["dizin"] = DIRECTORY + @"\sertifika deposu\test kok sertifika\";
                    //POLICY.bulmaPolitikasiAl().addTrustedCertificateFinder("tr.gov.tubitak.uekae.esya.api.certificate.validation.find.certificate.trusted.TrustedCertificateFinderFromFileSystem",
                    //        parameters);
                }
                catch (FileNotFoundException e)
                {
                    throw new SystemException("Policy file could not be found", e);
                }
            }
            return policy;
        }

        BaseSigner getSigner(IBaseSmartCard bsc, String aCardPIN, ECertificate aCert)
        {

            if (mSigner == null)
            {
                bsc.login(aCardPIN);
                mSigner = bsc.getSigner(aCert, Algorithms.SIGNATURE_RSA_SHA256);
            }
            return mSigner;

        }

        public static byte[] ReadFile(string filePath)
        {
            byte[] buffer;
            FileStream fileStream = new FileStream(filePath, FileMode.Open, FileAccess.Read);
            try
            {
                int length = (int)fileStream.Length;  // get file length
                buffer = new byte[length];            // create buffer
                int count;                            // actual number of bytes read
                int sum = 0;                          // total number of bytes read

                while ((count = fileStream.Read(buffer, sum, length - sum)) > 0)
                    sum += count;
            }
            finally
            {
                fileStream.Close();
            }
            return buffer;
        }

        public ESignUtil()
        {
            this.policyFile = "certval-policy-test.xml";
            this.dataFileContentType = "text/plain";
            this.dataTextFile = "data.txt";
            this.configFile = "esya-signature-config.xml";
        }

        public bool setLicenseXml(System.IO.FileStream FileStream)
        {
            return LicenseUtil.setLicenseXml(FileStream);
        }

        public void setLicenseXmlWithPassword(System.IO.FileStream FileStream, string password)
        {
            LicenseUtil.setLicenseXml(FileStream, password);
        }

        public string[] getTerminals()
        {
            return SmartOp.getCardTerminals();
        }

        public List<ECertificate> getSignatureCertificates(string terminal)
        {
            List<ECertificate> certs = new List<ECertificate>();
            Pair<long, CardType> slotAndCardType = SmartOp.getSlotAndCardType(terminal);
            IBaseSmartCard smartCard = getSmartCard(terminal);
            smartCard.openSession(slotAndCardType.getmObj1());
            List<byte[]> byteOfCerts = smartCard.getSignatureCertificates();
            foreach (byte[] bs in byteOfCerts)
            {
                ECertificate cert = new ECertificate(bs);
                certs.Add(cert);
            }
            return certs;
        }

        public byte[] signWithSmartCard(string terminal, ECertificate signatureCertificate, string pinCode, byte[] tobeSignBytes)
        {
            IBaseSmartCard smartCard = getSmartCard(terminal);
            BaseSignedData bs = new BaseSignedData();


            tr.gov.tubitak.uekae.esya.api.cmssignature.ISignable content = new SignableByteArray(tobeSignBytes);
            bs.addContent(content);

            //Since SigningTime attribute is optional,add it to optional attributes list
            List<IAttribute> optionalAttributes = new List<IAttribute>();
            optionalAttributes.Add(new SigningTimeAttr(DateTime.UtcNow));

            Dictionary<string, object> params_ = new Dictionary<string, object>();
            ValidationPolicy policy = getPolicy();

            //necessary for certificate validation.By default,certificate validation is done 
            params_[EParameters.P_CERT_VALIDATION_POLICY] = policy;

            //if the user does not want certificate validation,he can add 
            //P_VALIDATE_CERTIFICATE_BEFORE_SIGNING parameter with its value set to false
            params_[EParameters.P_VALIDATE_CERTIFICATE_BEFORE_SIGNING] = false;
            BaseSigner signer = getSigner(smartCard, pinCode, signatureCertificate);
            bs.addSigner(ESignatureType.TYPE_BES, signatureCertificate, signer, optionalAttributes, params_);
            smartCard.logout();
            smartCard.closeSession();
            return bs.getEncoded();
        }

        private class SmartCardPrivateKeySignature : IExternalSignature
        {
            string terminal = "", pin = "";
            ECertificate cert = null;

            public SmartCardPrivateKeySignature(string terminal, string pin, ECertificate cert)
            {
                this.terminal = terminal;
                this.pin = pin;
                this.cert = cert;
            }

            public String GetHashAlgorithm()
            {
                return "SHA256";
            }

            public String GetEncryptionAlgorithm()
            {
                return "RSA";
            }

            public byte[] Sign(byte[] message)
            {
                Pair<long, CardType> pair = SmartOp.getSlotAndCardType(terminal);
                P11SmartCard smartCard = new P11SmartCard(pair.getmObj2());
                smartCard.openSession(pair.getmObj1());
                smartCard.login(pin);
                BaseSigner signer = smartCard.getSigner(cert, Algorithms.SIGNATURE_RSA_SHA256);
                byte[] buffer = signer.sign(message);
                smartCard.logout();
                smartCard.closeSession();
                return buffer;
            }
        }

        public byte[] signPdfWithSmartCard(string terminal, ECertificate signatureCertificate, string pinCode, string pdfFileName)
        {
            byte[] buffer = null;
            PdfReader reader = null;
            PdfStamper stamper = null;
            FileStream os = null;
            try
            {
                string dest = AppDomain.CurrentDomain.BaseDirectory + "\\tmp.pdf";
                if (File.Exists(dest))
                {
                    File.Delete(dest);
                }
                IExternalSignature pks = new SmartCardPrivateKeySignature(terminal, pinCode, signatureCertificate);
                reader = new PdfReader(pdfFileName);
                os = new FileStream(dest, FileMode.Create);
                stamper = PdfStamper.CreateSignature(reader, os, '\0');

                Org.BouncyCastle.Asn1.Esf.SignaturePolicyIdentifier policy = new Org.BouncyCastle.Asn1.Esf.SignaturePolicyIdentifier();
                DateTime dtNow = DateTime.Now;
                PdfSignatureAppearance appearance = stamper.SignatureAppearance;
                appearance.Reason = "";
                appearance.Location = "";
                appearance.SignDate = dtNow;
                PdfSignature dic = new PdfSignature(PdfName.ADOBE_PPKMS, PdfName.ADBE_PKCS7_SHA1);
                dic.Date = new PdfDate(dtNow);
                appearance.CryptoDictionary = dic;
                ICollection<Org.BouncyCastle.X509.X509Certificate> chain = new List<Org.BouncyCastle.X509.X509Certificate>();


                X509Certificate2 cert = signatureCertificate.asX509Certificate2();

                X509Chain x509chain = new X509Chain();
                x509chain.Build(cert);
                foreach (X509ChainElement x509ChainElement in x509chain.ChainElements)
                {
                    chain.Add(DotNetUtilities.FromX509Certificate(x509ChainElement.Certificate));
                }


                MakeSignature.SignDetached(appearance, pks, chain, null, null, null, 0, CryptoStandard.CADES, policy);
                buffer = File.ReadAllBytes(dest);
                File.Delete(dest);
            }
            finally
            {
                if (reader != null)
                    reader.Close();
                if (stamper != null)
                    stamper.Close();
                if (os != null)
                    os.Close();

            }
            return buffer;
        }

        public byte[] signWithPfxFile(string pfxFile, string pinCode, byte[] tobeSignBytes)
        {
            BaseSignedData bs = new BaseSignedData();
            tr.gov.tubitak.uekae.esya.api.cmssignature.ISignable content = new SignableByteArray(tobeSignBytes);
            bs.addContent(content);

            //Since SigningTime attribute is optional,add it to optional attributes list
            List<IAttribute> optionalAttributes = new List<IAttribute>();
            optionalAttributes.Add(new SigningTimeAttr(DateTime.UtcNow));

            Dictionary<string, object> params_ = new Dictionary<string, object>();
            ValidationPolicy policy = getPolicy();

            //necessary for certificate validation.By default,certificate validation is done 
            params_[EParameters.P_CERT_VALIDATION_POLICY] = policy;

            //if the user does not want certificate validation,he can add 
            //P_VALIDATE_CERTIFICATE_BEFORE_SIGNING parameter with its value set to false
            params_[EParameters.P_VALIDATE_CERTIFICATE_BEFORE_SIGNING] = false;
            PfxSigner signer = new PfxSigner(SignatureAlg.RSA_SHA256.getName(), pfxFile, pinCode);
            ECertificate signatureCertificate = signer.getSignersCertificate();
            bs.addSigner(ESignatureType.TYPE_BES, signatureCertificate, signer, optionalAttributes, params_);
            return bs.getEncoded();
        }

        public byte[] signPdfWithPfxFile(string pfxFile, string pinCode, string pdfFileName)
        {
            PfxSigner signer = new PfxSigner(SignatureAlg.RSA_SHA256.getName(), pfxFile, pinCode);
            ECertificate signatureCertificate = signer.getSignersCertificate();
            Pkcs12Store store = new Pkcs12Store(new FileStream(pfxFile, FileMode.Open), pinCode.ToCharArray());
            String alias = "";
            string dest = AppDomain.CurrentDomain.BaseDirectory + "\\tmp.pdf";
            if (File.Exists(dest))
            {
                File.Delete(dest);
            }
            ICollection<Org.BouncyCastle.X509.X509Certificate> chain = new List<Org.BouncyCastle.X509.X509Certificate>();
            // searching for private key
            foreach (string al in store.Aliases)
                if (store.IsKeyEntry(al) && store.GetKey(al).Key.IsPrivate)
                {
                    alias = al;
                    break;
                }

            AsymmetricKeyEntry pk = store.GetKey(alias);
            foreach (X509CertificateEntry c in store.GetCertificateChain(alias))
                chain.Add(c.Certificate);

            RsaPrivateCrtKeyParameters parameters = pk.Key as RsaPrivateCrtKeyParameters;

            // Creating the reader and the stamper
            PdfReader reader = new PdfReader(pdfFileName);
            FileStream os = new FileStream(dest, FileMode.Create);
            PdfStamper stamper = PdfStamper.CreateSignature(reader, os, '\0');
            // Creating the appearance
            PdfSignatureAppearance appearance = stamper.SignatureAppearance;
            appearance.Reason = "";
            appearance.Location = "";
            //appearance.SetVisibleSignature(new Rectangle(36, 748, 144, 780), 1, "sig");//don't show rectangle on pdf
            // Creating the signature
            IExternalSignature pks = new PrivateKeySignature(parameters, DigestAlgorithms.SHA256);
            MakeSignature.SignDetached(appearance, pks, chain, null, null, null, 0, CryptoStandard.CADES);
            byte[] buffer = File.ReadAllBytes(dest);
            File.Delete(dest);
            return buffer;
        }

        Context createPadesContext(string pdfFileName)
        {
            Context c = new Context(new Uri(pdfFileName));
            c.setConfig(new Config(configFile));
            return c;
        }


        public SignedDataValidationResult validateSign(string signedFile)
        {
            Dictionary<string, object> params_ = new Dictionary<string, object>();
            params_[EParameters.P_CERT_VALIDATION_POLICY] = getPolicy();
            //if (externalContent != null)
            //    params_[EParameters.P_EXTERNAL_CONTENT] = externalContent;

            //Use only reference and their corresponding value to validate signature
            params_[EParameters.P_FORCE_STRICT_REFERENCE_USE] = true;
            //Ignore grace period which means allow usage of CRL published before signature time 
            //params_[EParameters.P_IGNORE_GRACE] = true;

            //Use multiple policies if you want to use different policies to validate different types of certificate
            // CertValidationPolicies certificateValidationPolicies = new CertValidationPolicies();
            // certificateValidationPolicies.register(CertValidationPolicies.CertificateType.DEFAULT.ToString(), TestConstants.getPolicy());
            // ValidationPolicy maliMuhurPolicy = PolicyReader.readValidationPolicy(new FileStream(TestConstants.getDirectory()+"/config/certval-policy-malimuhur.xml", FileMode.Open, FileAccess.Read));
            // certificateValidationPolicies.register(CertValidationPolicies.CertificateType.MaliMuhurCertificate.ToString(), maliMuhurPolicy);
            // params_[EParameters.P_CERT_VALIDATION_POLICIES]= certificateValidationPolicies;

            SignedDataValidation sdv = new SignedDataValidation();
            byte[] signedFileByes = ReadFile(signedFile);
            SignedDataValidationResult sdvr = sdv.verify(signedFileByes, params_);
            return sdvr;

        }

        public void extractSignedFile(string signedFile, System.IO.FileStream output)
        {
            byte[] signedFileByes = ReadFile(signedFile);
            BaseSignedData bs = new BaseSignedData(signedFileByes);
            byte[] contentBytes = bs.getSignedData().getBytes();
            output.Write(contentBytes, 0, contentBytes.Length);
            output.Flush();
            output.Close();
        }

        public IBaseSmartCard getSmartCard(string terminal)
        {
            Pair<long, CardType> slotAndCardType = SmartOp.getSlotAndCardType(terminal);
            return new P11SmartCard(slotAndCardType.getmObj2());
        }
    }
}
