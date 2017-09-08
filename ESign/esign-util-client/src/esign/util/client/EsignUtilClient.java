package esign.util.client;

import esign.lib.ESignUtil;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import sun.security.pkcs11.wrapper.PKCS11Exception;
import tr.gov.tubitak.uekae.esya.api.asn.x509.ECertificate;
import tr.gov.tubitak.uekae.esya.api.cmssignature.validation.SignedDataValidationResult;
import tr.gov.tubitak.uekae.esya.api.cmssignature.validation.SignedData_Status;
import tr.gov.tubitak.uekae.esya.api.common.ESYAException;
import tr.gov.tubitak.uekae.esya.api.crypto.exceptions.CryptoException;
import tr.gov.tubitak.uekae.esya.api.signature.ContainerValidationResult;
import tr.gov.tubitak.uekae.esya.api.signature.ContainerValidationResultType;
import tr.gov.tubitak.uekae.esya.api.signature.SignatureException;
import tr.gov.tubitak.uekae.esya.api.smartcard.pkcs11.LoginException;
import tr.gov.tubitak.uekae.esya.api.smartcard.pkcs11.SmartCardException;

/**
 *
 * @author cihan
 */
public class EsignUtilClient {

    static ESignUtil eSignUtil = new ESignUtil();

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     * @throws tr.gov.tubitak.uekae.esya.api.common.ESYAException
     */
    public static void main(String[] args) throws IOException, ESYAException {
        try {
            try {
                eSignUtil.setLicenseXml(new FileInputStream("C:\\lisans\\lisans.xml"));
            } catch (FileNotFoundException ex) {
                Logger.getLogger(EsignUtilClient.class.getName()).log(Level.SEVERE, null, ex);
            }
            eSignUtil.configFile = "c:\\config\\esya-signature-config.xml";
            eSignUtil.policyFile = "c:\\config\\certval-policy-test.xml";
//        signWithPfx();
//signWithSmartCard();
//        signPdfWithPfx();
            signPdfWithSmartCard();

        } catch (SmartCardException | PKCS11Exception | LoginException | SignatureException ex) {
            Logger.getLogger(EsignUtilClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    static void signWithPfx() throws CryptoException, SignatureException, ESYAException {
        FileInputStream pfxFileInputstream = null;
        try {
            String pfxFilePath = "C:\\sertifika deposu\\522277_test1@kamusm.gov.tr.pfx";
            String signedFile = "C:\\imzalidosya.p2";
            String extractFile = "C:\\imzalidosya_acik.txt";
//            String pfxFilePath = "C:\\cihan.pfx";
            pfxFileInputstream = new FileInputStream(pfxFilePath);
            byte[] tobeSignBytes = "deneme data".getBytes();

            byte[] signedBytes = eSignUtil.signWithPfxFile(pfxFilePath, "522277", tobeSignBytes);

            try (FileOutputStream fileOutputStream = new FileOutputStream(signedFile)) {
                fileOutputStream.write(signedBytes);
            }

            SignedDataValidationResult signedDataValidationResult = eSignUtil.validateSign(signedFile);

            if (SignedData_Status.ALL_VALID.equals(signedDataValidationResult.getSDStatus())) {
                eSignUtil.extractSignedFile(signedFile, new FileOutputStream(extractFile));
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(EsignUtilClient.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(EsignUtilClient.class.getName()).log(Level.SEVERE, null, ex);
        } finally {

        }
    }

    static void signPdfWithPfx() throws CryptoException, SignatureException {
        try {
            String pfxFilePath = "C:\\sertifika deposu\\522277_test1@kamusm.gov.tr.pfx";
            String signedFile = "C:\\imzalipdfdosya.pdf";
            String extractFile = "C:\\imzalipdfdosya_acik.pdf";
            String pdfFile = "C:\\Documents and Settings\\cihan\\Desktop\\kamusm_eimza\\MA3-JAVA-API-2.1.2\\testdata\\sample.pdf";

            byte[] signedBytes = eSignUtil.signPdfWithPfxFile(pfxFilePath, "522277", pdfFile);

            try (FileOutputStream fileOutputStream = new FileOutputStream(signedFile)) {
                fileOutputStream.write(signedBytes);
            }

            ContainerValidationResult signedDataValidationResult = eSignUtil.validateSignedPdf(signedFile);

            if (ContainerValidationResultType.ALL_VALID.equals(signedDataValidationResult.getResultType())) {
                System.out.println("eimza imzalandı");
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(EsignUtilClient.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(EsignUtilClient.class.getName()).log(Level.SEVERE, null, ex);
        } finally {

        }
    }

    static void signWithSmartCard() throws IOException, SmartCardException, ESYAException, PKCS11Exception, LoginException, SignatureException {
        String signedFile = "C:\\imzalidosya_smart.p2";
        String extractFile = "C:\\imzalidosya_acik_smart.txt";
        String terminal = eSignUtil.getTerminals()[0];
        ECertificate certificate = eSignUtil.getSignatureCertificates(terminal).get(0);
        byte[] signedBytes = eSignUtil.signWithSmartCard(terminal, certificate, "telman1234+-", "deneme cihan - telman e imza".getBytes());

        try (FileOutputStream fileOutputStream = new FileOutputStream(signedFile)) {
            fileOutputStream.write(signedBytes);
        }

        SignedDataValidationResult signedDataValidationResult = eSignUtil.validateSign(signedFile);

        if (SignedData_Status.ALL_VALID.equals(signedDataValidationResult.getSDStatus())) {
            eSignUtil.extractSignedFile(signedFile, new FileOutputStream(extractFile));
        }

    }

    static void signPdfWithSmartCard() throws IOException, SmartCardException, ESYAException, PKCS11Exception, LoginException, SignatureException {
        String extractFile = "C:\\imzalidosya_acik_smart.pdf";
        String extractFile_b = "C:\\imzalidosya_acik_smart_ext_long_timestamped.pdf";
        String pdfFile = "C:\\Documents and Settings\\cihan\\Desktop\\kamusm_eimza\\MA3-JAVA-API-2.1.2\\testdata\\sample.pdf";
        String terminal = eSignUtil.getTerminals()[0];
        ECertificate certificate = eSignUtil.getSignatureCertificates(terminal).get(0);
        byte[] signedBytes = eSignUtil.signPdfWithSmartCard(terminal, certificate, "1234", pdfFile);

        try (FileOutputStream fileOutputStream = new FileOutputStream(extractFile)) {
            fileOutputStream.write(signedBytes);
        }

        ContainerValidationResult signedDataValidationResult = eSignUtil.validateSignedPdf(extractFile);

        if (ContainerValidationResultType.ALL_VALID.equals(signedDataValidationResult.getResultType())) {
            System.out.println("eimza imzalandı");

            byte[] signedByteB = eSignUtil.convertSignedPdfToTimeStampedPdf(extractFile);
            
            try (FileOutputStream fileOutputStream = new FileOutputStream(extractFile_b)) {
                fileOutputStream.write(signedByteB);
            }
        }

    }
}
