package esign.interfaces;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import sun.security.pkcs11.wrapper.PKCS11Exception;
import tr.gov.tubitak.uekae.esya.api.asn.x509.ECertificate;
import tr.gov.tubitak.uekae.esya.api.cmssignature.validation.SignedDataValidationResult;
import tr.gov.tubitak.uekae.esya.api.common.ESYAException;
import tr.gov.tubitak.uekae.esya.api.crypto.exceptions.CryptoException;
import tr.gov.tubitak.uekae.esya.api.signature.ContainerValidationResult;
import tr.gov.tubitak.uekae.esya.api.signature.SignatureException;
import tr.gov.tubitak.uekae.esya.api.smartcard.pkcs11.BaseSmartCard;
import tr.gov.tubitak.uekae.esya.api.smartcard.pkcs11.LoginException;
import tr.gov.tubitak.uekae.esya.api.smartcard.pkcs11.SmartCardException;

/**
 *
 * @author cihan
 */
public interface IESignUtil {

    public Boolean setLicenseXml(InputStream inputStream) throws ESYAException;

    public Boolean setLicenseXmlWithPassword(InputStream inputStream, String password) throws ESYAException;

    public String[] getTerminals() throws SmartCardException;

    public List<ECertificate> getSignatureCertificates(String terminal) throws SmartCardException, ESYAException, PKCS11Exception, IOException;

    public byte[] signWithSmartCard(String terminal, ECertificate signatureCertificate, String pinCode, byte[] tobeSignBytes) throws SmartCardException, LoginException, SignatureException, PKCS11Exception, IOException, ESYAException;

    public byte[] signWithPfxFile(String pfxFile, String pinCode, byte[] tobeSignBytes) throws CryptoException, SignatureException, FileNotFoundException;

    public byte[] signPdfWithSmartCard(String terminal, ECertificate signatureCertificate, String pinCode, String pdfFile) throws SmartCardException, LoginException, SignatureException, FileNotFoundException, PKCS11Exception, IOException, ESYAException;

    public byte[] signPdfWithPfxFile(String pfxFile, String pinCode, String pdfFile) throws CryptoException, SignatureException, FileNotFoundException;

    public SignedDataValidationResult validateSign(String signedFile) throws IOException, SignatureException, ESYAException;

    public ContainerValidationResult validateSignedPdf(String signedFile) throws FileNotFoundException, SignatureException;

    public void extractSignedFile(String signedFile, OutputStream output) throws SignatureException, IOException;

    public BaseSmartCard getSmartCard(String terminal) throws PKCS11Exception, IOException, ESYAException;

    public byte[] convertSignedPdfToTimeStampedPdf(String pdfFile) throws CryptoException, SignatureException, FileNotFoundException, IOException, ESYAException;

}
