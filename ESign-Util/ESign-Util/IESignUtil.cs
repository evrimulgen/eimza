using System;
using System.Collections.Generic;
using System.Text;
using System.IO;
using tr.gov.tubitak.uekae.esya.api.asn.x509;
using tr.gov.tubitak.uekae.esya.api.cmssignature.validation;
using tr.gov.tubitak.uekae.esya.api.signature;
using tr.gov.tubitak.uekae.esya.api.smartcard.pkcs11;

namespace ESign_Util
{
    interface IESignUtil
    {
        Boolean setLicenseXml(FileStream FileStream) ;

        void setLicenseXmlWithPassword(FileStream FileStream, String password);

        String[] getTerminals();

        List<ECertificate> getSignatureCertificates(String terminal) ;

        byte[] signWithSmartCard(String terminal, ECertificate signatureCertificate, String pinCode, byte[] tobeSignBytes) ;

        byte[] signWithPfxFile(String pfxFile, String pinCode, byte[] tobeSignBytes) ;
         
        SignedDataValidationResult validateSign(String signedFile);

        byte[] signPdfWithSmartCard(String terminal, ECertificate signatureCertificate, String pinCode, string pdfFileName);

        byte[] signPdfWithPfxFile(String pfxFile, String pinCode, string pdfFileName);

        void extractSignedFile(String signedFile, FileStream output);

        IBaseSmartCard getSmartCard(String terminal);
    }
}
