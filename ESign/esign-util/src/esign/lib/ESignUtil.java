package esign.lib;

import esign.interfaces.IESignUtil;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.PrivateKey;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.smartcardio.CardTerminal;
import javax.smartcardio.TerminalFactory;
import sun.security.pkcs11.wrapper.PKCS11Exception;
import tr.gov.tubitak.uekae.esya.api.asn.x509.ECertificate;
import tr.gov.tubitak.uekae.esya.api.certificate.validation.policy.PolicyReader;
import tr.gov.tubitak.uekae.esya.api.certificate.validation.policy.ValidationPolicy;
import tr.gov.tubitak.uekae.esya.api.cmssignature.attribute.EParameters;
import tr.gov.tubitak.uekae.esya.api.cmssignature.signature.BaseSignedData;
import tr.gov.tubitak.uekae.esya.api.cmssignature.signature.ESignatureType;
import tr.gov.tubitak.uekae.esya.api.cmssignature.signature.Signer;
import tr.gov.tubitak.uekae.esya.api.cmssignature.validation.SignedDataValidation;
import tr.gov.tubitak.uekae.esya.api.cmssignature.validation.SignedDataValidationResult;
import tr.gov.tubitak.uekae.esya.api.common.ESYAException;
import tr.gov.tubitak.uekae.esya.api.common.crypto.Algorithms;
import tr.gov.tubitak.uekae.esya.api.common.crypto.BaseSigner;
import tr.gov.tubitak.uekae.esya.api.common.util.LicenseUtil;
import tr.gov.tubitak.uekae.esya.api.common.util.bag.Pair;
import tr.gov.tubitak.uekae.esya.api.crypto.alg.SignatureAlg;
import tr.gov.tubitak.uekae.esya.api.crypto.exceptions.CryptoException;
import tr.gov.tubitak.uekae.esya.api.crypto.util.PfxParser;
import tr.gov.tubitak.uekae.esya.api.infra.tsclient.TSSettings;
import tr.gov.tubitak.uekae.esya.api.pades.PAdESContext;
import tr.gov.tubitak.uekae.esya.api.signature.ContainerValidationResult;
import tr.gov.tubitak.uekae.esya.api.signature.Context;
import tr.gov.tubitak.uekae.esya.api.signature.Signature;
import tr.gov.tubitak.uekae.esya.api.signature.SignatureContainer;
import tr.gov.tubitak.uekae.esya.api.signature.SignatureException;
import tr.gov.tubitak.uekae.esya.api.signature.SignatureFactory;
import tr.gov.tubitak.uekae.esya.api.signature.SignatureFormat;
import tr.gov.tubitak.uekae.esya.api.signature.SignatureType;
import tr.gov.tubitak.uekae.esya.api.signature.config.Config;
import tr.gov.tubitak.uekae.esya.api.signature.config.TimestampConfig;
import tr.gov.tubitak.uekae.esya.api.signature.impl.BaseSignable;
import tr.gov.tubitak.uekae.esya.api.signature.impl.SignableBytes;
import tr.gov.tubitak.uekae.esya.api.signature.util.PfxSigner;
import tr.gov.tubitak.uekae.esya.api.smartcard.apdu.APDUSmartCard;
import tr.gov.tubitak.uekae.esya.api.smartcard.pkcs11.BaseSmartCard;
import tr.gov.tubitak.uekae.esya.api.smartcard.pkcs11.CardType;
import tr.gov.tubitak.uekae.esya.api.smartcard.pkcs11.LoginException;
import tr.gov.tubitak.uekae.esya.api.smartcard.pkcs11.P11SmartCard;
import tr.gov.tubitak.uekae.esya.api.smartcard.pkcs11.SmartCardException;
import tr.gov.tubitak.uekae.esya.api.smartcard.pkcs11.SmartOp;

/**
 *
 * @author cihan
 */
public class ESignUtil implements IESignUtil {

    public String configFile;
    public String dataTextFile;
    public String dataFileContentType;
    public String policyFile;

    public ESignUtil() {
        this.policyFile = "certval-policy-test.xml";
        this.dataFileContentType = "text/plain";
        this.dataTextFile = "data.txt";
        this.configFile = "esya-signature-config.xml";

    }

    private ValidationPolicy getPolicy() throws ESYAException {
        try {
            return PolicyReader.readValidationPolicy(new FileInputStream(policyFile));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ESignUtil.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    private Context createContext(byte[] tobeSignBytes) {
        Context c = new Context();
        c.setConfig(new Config(configFile));
        c.setData(new SignableBytes(tobeSignBytes, dataTextFile, dataFileContentType));
        return c;
    }

    private PAdESContext createPadesContext(String fileName) {
        PAdESContext c = new PAdESContext(new File(fileName).toURI());
        c.setConfig(new Config(configFile));
        return c;
    }

    private ECertificate getPfxCertificate(InputStream pfxFileInputstream, String pinCode) throws CryptoException {
        PfxParser p = new PfxParser(pfxFileInputstream, pinCode.toCharArray());
        List<Pair<ECertificate, PrivateKey>> ls = p.getCertificatesAndKeys();
        return (ECertificate) ((Pair) ls.get(0)).getObject1();
    }

    private BaseSignable createContent(byte[] tobeSignBytes) {
        return new SignableBytes(tobeSignBytes, dataTextFile, dataFileContentType);
    }

    /**
     *
     * @return @throws SmartCardException
     */
    @Override
    public String[] getTerminals() throws SmartCardException {
        return SmartOp.getCardTerminals();
    }

    /**
     *
     * @param terminal
     * @return
     * @throws SmartCardException
     * @throws sun.security.pkcs11.wrapper.PKCS11Exception
     * @throws java.io.IOException
     */
    @Override
    public List<ECertificate> getSignatureCertificates(String terminal) throws SmartCardException, ESYAException, PKCS11Exception, IOException {
        List<ECertificate> result = new ArrayList<>();
        if (terminal != null && !terminal.isEmpty()) {
            BaseSmartCard smartCard = getSmartCard(terminal);
            if (smartCard != null) {
                if (APDUSmartCard.isSupported(terminal)) {

                    CardTerminal ct = TerminalFactory.getDefault().terminals().getTerminal(terminal);
                    ((APDUSmartCard) smartCard).openSession(ct);

                } else {
                    Pair<Long, CardType> slotAndCardType = SmartOp.getSlotAndCardType(terminal);
                    smartCard.openSession((slotAndCardType.getObject1()));
                }
                if (smartCard.isSessionActive()) {
                    List<byte[]> certificatesBytes = smartCard.getSignatureCertificates();
                    if (certificatesBytes != null) {
                        for (byte[] bs : certificatesBytes) {
                            ECertificate cert = new ECertificate(bs);
                            result.add(cert);
                        }
                    }
                    smartCard.closeSession();
                }
            }
        }
        return result;
    }

    @Override
    public byte[] signWithSmartCard(String terminal, ECertificate signatureCertificate, String pinCode, byte[] tobeSignBytes) throws SmartCardException, LoginException, SignatureException, PKCS11Exception, IOException, ESYAException {
        byte[] result = null;
        if (terminal != null && !terminal.isEmpty() && signatureCertificate != null && pinCode != null && !pinCode.isEmpty() && tobeSignBytes != null) {
            BaseSmartCard smartCard = getSmartCard(terminal);
            if (smartCard != null) {
                if (APDUSmartCard.isSupported(terminal)) {
                    CardTerminal ct = TerminalFactory.getDefault().terminals().getTerminal(terminal);
                    ((APDUSmartCard) smartCard).openSession(ct);
                } else {
                    Pair<Long, CardType> slotAndCardType = SmartOp.getSlotAndCardType(terminal);
                    smartCard.openSession((slotAndCardType.getObject1()));
                }

                if (smartCard.isSessionActive()) {

                    BaseSigner signer = smartCard.getSigner(signatureCertificate.asX509Certificate(), Algorithms.SIGNATURE_RSA_SHA256);
                    smartCard.login(pinCode);
                    SignatureContainer container = SignatureFactory.createContainer(SignatureFormat.CAdES, createContext(tobeSignBytes));
                    Signature signature = container.createSignature(signatureCertificate);
                    signature.addContent(createContent(tobeSignBytes), true);
                    signature.sign(signer);

                    ByteArrayOutputStream out = new ByteArrayOutputStream();
                    container.write(out);
                    result = out.toByteArray();

                    smartCard.logout();
                    smartCard.closeSession();

                }
            }
        }
        return result;
    }

    @Override
    public byte[] signWithPfxFile(String pfxFile, String pinCode, byte[] tobeSignBytes) throws CryptoException, SignatureException, FileNotFoundException {
        byte[] result;
        ECertificate certificate = getPfxCertificate(new FileInputStream(pfxFile), pinCode);
        PfxSigner signer = new PfxSigner(SignatureAlg.RSA_SHA256, new FileInputStream(pfxFile), pinCode.toCharArray());
        SignatureContainer container = SignatureFactory.createContainer(SignatureFormat.CAdES, createContext(tobeSignBytes));
        Signature signature = container.createSignature(certificate);
        signature.addContent(createContent(tobeSignBytes), true);
        signature.sign(signer);

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        container.write(out);
        result = out.toByteArray();
        return result;
    }

    @Override
    public SignedDataValidationResult validateSign(String signedFile) throws IOException, SignatureException, ESYAException {

        FileInputStream fileInputStream = new FileInputStream(signedFile);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int length;
        while ((length = fileInputStream.read(buffer)) != -1) {
            baos.write(buffer, 0, length);
        }

        //BaseSignedData baseSignedData = new BaseSignedData(baos.toByteArray());
        Map<String, Object> params = new Hashtable<>();
        params.put(EParameters.P_CERT_VALIDATION_POLICY, getPolicy());
        //Use only reference and their corresponding value to validate signature
        params.put(EParameters.P_FORCE_STRICT_REFERENCE_USE, true);

        //Ignore grace period which means allow usage of CRL published before signature time 
        //params.put(EParameters.P_IGNORE_GRACE, true);
        //Use multiple policies if you want to use different policies to validate different types of certificate
        //CertValidationPolicies certificateValidationPolicies = new CertValidationPolicies();
        //certificateValidationPolicies.register(CertificateType.DEFAULT.toString(), policy);
        //ValidationPolicy maliMuhurPolicy=PolicyReader.readValidationPolicy(new FileInputStream("./config/certval-policy-malimuhur.xml"));
        //certificateValidationPolicies.register(CertificateType.MaliMuhurCertificate.toString(), maliMuhurPolicy);
        //params.put(EParameters.P_CERT_VALIDATION_POLICIES, certificateValidationPolicies);
//            if (externalContent != null) {
//                params.put(EParameters.P_EXTERNAL_CONTENT, externalContent);
//            }
        SignedDataValidation sdv = new SignedDataValidation();
        SignedDataValidationResult sdvr = sdv.verify(baos.toByteArray(), params);

        return sdvr;

    }

    @Override
    public void extractSignedFile(String signedFile, OutputStream output) throws SignatureException, IOException {

        FileInputStream fileInputStream = new FileInputStream(signedFile);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int length;
        while ((length = fileInputStream.read(buffer)) != -1) {
            baos.write(buffer, 0, length);
        }
        BaseSignedData baseSignedData = new BaseSignedData(baos.toByteArray());
//            baseSignedData.getAllSigners().forEach(new Consumer<Signer>() {
//                @Override
//                public void accept(Signer t) {
//                    System.out.println(t.getSignerCertificate().getIssuer().getCommonNameAttribute());
//                }
//            });
        output.write(baseSignedData.getContent());
        output.close();

    }

    @Override
    public BaseSmartCard getSmartCard(String terminal) throws PKCS11Exception, IOException, ESYAException {
        boolean APDUSupport;
        try {
            APDUSupport = APDUSmartCard.isSupported(terminal);
        } catch (NoClassDefFoundError ex) {
            Logger.getLogger(ESignUtil.class.getName()).log(Level.SEVERE, null, "AkisCIF.jar is missing");
            APDUSupport = false;
        }
        P11SmartCard p11SmartCard;
        if (APDUSupport) {
            APDUSmartCard asc = new APDUSmartCard();
            return asc;

        } else {
            Pair<Long, CardType> slotAndCardType;
            slotAndCardType = SmartOp.getSlotAndCardType(terminal);
            if ((terminal.contains("ACS")) && (((CardType) slotAndCardType.getObject2()).toString().compareTo(CardType.UNKNOWN.toString()) == 0)) {
                if (terminal.contains("ACR38U")) {
                    long slotNum = SmartOp.findSlotNumber(CardType.TKART);
                    slotAndCardType.setObject1(slotNum);
                    slotAndCardType.setObject2(CardType.TKART);
                } else {
                    long slotNum = SmartOp.findSlotNumber(CardType.SAFESIGN);
                    slotAndCardType.setObject1(slotNum);
                    slotAndCardType.setObject2(CardType.SAFESIGN);
                }
            } else if ((terminal.contains("OMNIKEY CardMan 3x21")) && (((CardType) slotAndCardType.getObject2()).toString().compareTo(CardType.UNKNOWN.toString()) == 0)) {
                long slotNum = SmartOp.findSlotNumber(CardType.TKART);
                slotAndCardType.setObject1(slotNum);
                slotAndCardType.setObject2(CardType.TKART);
            } else if ((terminal.contains("OMNIKEY")) && (((CardType) slotAndCardType.getObject2()).toString().compareTo(CardType.UNKNOWN.toString()) == 0)) {
                long slotNum = SmartOp.findSlotNumber(CardType.TKART);
                slotAndCardType.setObject1(slotNum);
                slotAndCardType.setObject2(CardType.TKART);
            } else if ((terminal.contains("Gemplus")) && (((CardType) slotAndCardType.getObject2()).toString().compareTo(CardType.UNKNOWN.toString()) == 0)) {
                long slotNum = SmartOp.findSlotNumber(CardType.TKART);
                slotAndCardType.setObject1(slotNum);
                slotAndCardType.setObject2(CardType.TKART);
            }
            p11SmartCard = new P11SmartCard((CardType) slotAndCardType.getObject2());
            return p11SmartCard;
        }
    }

    @Override
    public Boolean setLicenseXml(InputStream inputStream) throws ESYAException {
        return LicenseUtil.setLicenseXml(inputStream);
    }

    @Override
    public Boolean setLicenseXmlWithPassword(InputStream inputStream, String password) throws ESYAException {
        return LicenseUtil.setLicenseXml(inputStream, password);
    }

    @Override
    public byte[] signPdfWithSmartCard(String terminal, ECertificate signatureCertificate, String pinCode, String pdfFile) throws SmartCardException, LoginException, SignatureException, FileNotFoundException, PKCS11Exception, IOException, ESYAException {
        byte[] result = null;
        if (terminal != null && !terminal.isEmpty() && signatureCertificate != null && pinCode != null && !pinCode.isEmpty() && pdfFile != null) {
            BaseSmartCard smartCard = getSmartCard(terminal);
            if (smartCard != null) {
                if (APDUSmartCard.isSupported(terminal)) {
                    CardTerminal ct = TerminalFactory.getDefault().terminals().getTerminal(terminal);
                    ((APDUSmartCard) smartCard).openSession(ct);
                } else {
                    Pair<Long, CardType> slotAndCardType = SmartOp.getSlotAndCardType(terminal);
                    smartCard.openSession((slotAndCardType.getObject1()));
                }

                if (smartCard.isSessionActive()) {

                    BaseSigner signer = smartCard.getSigner(signatureCertificate.asX509Certificate(), Algorithms.SIGNATURE_RSA_SHA256);
                    smartCard.login(pinCode);
                    SignatureContainer container = SignatureFactory.readContainer(SignatureFormat.PAdES, new FileInputStream(pdfFile), createPadesContext(pdfFile));
                    Signature signature = container.createSignature(signatureCertificate);
                    signature.setSigningTime(Calendar.getInstance());
                    signature.sign(signer);

                    ByteArrayOutputStream out = new ByteArrayOutputStream();
                    container.write(out);
                    result = out.toByteArray();

                    smartCard.logout();
                    smartCard.closeSession();

                }
            }
        }
        return result;
    }

    @Override
    public byte[] signPdfWithPfxFile(String pfxFile, String pinCode, String pdfFile) throws CryptoException, SignatureException, FileNotFoundException {
        byte[] result;

        ECertificate certificate = getPfxCertificate(new FileInputStream(pfxFile), pinCode);
        PfxSigner signer = new PfxSigner(SignatureAlg.RSA_SHA256, new FileInputStream(pfxFile), pinCode.toCharArray());
        SignatureContainer container = SignatureFactory.readContainer(SignatureFormat.PAdES, new FileInputStream(pdfFile), createPadesContext(pdfFile));
        Signature signature = container.createSignature(certificate);
        signature.setSigningTime(Calendar.getInstance());
        signature.sign(signer);

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        container.write(out);
        result = out.toByteArray();

        return result;

    }

    @Override
    public ContainerValidationResult validateSignedPdf(String signedFile) throws FileNotFoundException, SignatureException {
        SignatureContainer sc = SignatureFactory.readContainer(new FileInputStream(signedFile), createPadesContext(signedFile));
        return sc.verifyAll();
    }

    @Override
    public byte[] convertSignedPdfToTimeStampedPdf(String pdfFile) throws CryptoException, SignatureException, FileNotFoundException, IOException, ESYAException {
        FileInputStream fileInputStream = new FileInputStream(pdfFile);
        PAdESContext context = new PAdESContext();
        context.setConfig(new Config(configFile));
        context.setSignWithTimestamp(true);

        SignatureContainer pc = SignatureFactory.readContainer(
                SignatureFormat.PAdES,
                fileInputStream, context);

        int count = pc.getSignatures().size();
        
        for (int i = 0; i < count; i++) {
            Signature signature = pc.getSignatures().get(i);
            signature.upgrade(SignatureType.ES_XL);
        }
        

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        pc.write(baos);
        return baos.toByteArray();
    }

}
