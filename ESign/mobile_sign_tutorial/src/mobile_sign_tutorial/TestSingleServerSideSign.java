/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mobile_sign_tutorial;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import tr.gov.tubitak.uekae.esya.api.asn.x509.ECertificate;
import tr.gov.tubitak.uekae.esya.api.certificate.validation.policy.PolicyReader;
import tr.gov.tubitak.uekae.esya.api.certificate.validation.policy.ValidationPolicy;
import tr.gov.tubitak.uekae.esya.api.cmssignature.SignableFile;
import tr.gov.tubitak.uekae.esya.api.cmssignature.attribute.EParameters;
import tr.gov.tubitak.uekae.esya.api.cmssignature.attribute.IAttribute;
import tr.gov.tubitak.uekae.esya.api.cmssignature.attribute.SigningTimeAttr;
import tr.gov.tubitak.uekae.esya.api.cmssignature.signature.BaseSignedData;
import tr.gov.tubitak.uekae.esya.api.cmssignature.signature.ESignatureType;
import tr.gov.tubitak.uekae.esya.api.common.util.LicenseUtil;
import tr.gov.tubitak.uekae.esya.api.crypto.alg.SignatureAlg;
import tr.gov.tubitak.uekae.esya.api.infra.mobile.MobileSigner;
import tr.gov.tubitak.uekae.esya.api.infra.mobile.Operator;
import tr.gov.tubitak.uekae.esya.api.infra.mobile.PhoneNumberAndOperator;
import tr.gov.tubitak.uekae.esya.api.webservice.mssclient.wrapper.MSSParams;
import tr.gov.tubitak.uekae.esya.asn.util.AsnIO;

/**
 *
 * @author cihan
 */
public class TestSingleServerSideSign {

    static void loadLicense() {

        FileInputStream fis;
        try {
            //write license path below
            //fis = new FileInputStream("C:\\lisans\\lisans.xml");
            fis = new FileInputStream("C:\\lisans\\test_lisans.xml");
            LicenseUtil.setLicenseXml(fis);
            fis.close();
        } catch (Exception exc) {
            // TODO Auto-generated catch block
            exc.printStackTrace();
        }
    }

    private static final Map<Integer, Operator> intToTypeMap = new HashMap<Integer, Operator>();

    static {
        for (Operator type : Operator.values()) {
            intToTypeMap.put(type.ordinal(), type);
        }
    }

    static Operator fromInt(int i) {
        Operator type = intToTypeMap.get(Integer.valueOf(i));
        if (type == null) {
            return Operator.TURKCELL;
        }
        return type;
    }

    public static void main(String[] args) {
        loadLicense();
        String filePath = "C:\\testdata\\sample.txt"; //document which will be signed
        //byte[] contentData;
        try {
            String phoneNumber = "05322619573";
            //String phoneNumber = "05380892868";
            int mobileOperator = 0;//0-TURKCELL 1-AVEA 2-VODAFONE Operator enum'u içindeki sıra

            BaseSignedData bs = new BaseSignedData();
            bs.addContent(new SignableFile(new File(filePath)), true);
            //write policy path below
            ValidationPolicy validationPolicy = PolicyReader.readValidationPolicy(new FileInputStream("C:\\config\\certval-policy-test.xml"));
            HashMap<String, Object> params = new HashMap<String, Object>();

            //In real system, validate certificate by giving parameter "true" instead of "false"
            params.put(EParameters.P_VALIDATE_CERTIFICATE_BEFORE_SIGNING, false);
            params.put(EParameters.P_CERT_VALIDATION_POLICY, validationPolicy);

            //Since SigningTime attribute is optional,add it to optional attributes list
            java.util.List<IAttribute> optionalAttributes = new ArrayList<IAttribute>();
            optionalAttributes.add(new SigningTimeAttr(Calendar.getInstance()));

            //TURKCELL	         
            //mobileOperator = 0;
            //phoneNumber = "05380892868";
            MSSParams mobilParams = new MSSParams("http://MImzaTubitakBilgem", "*******", "www.turkcelltech.com");

            boolean test = Boolean.TRUE;

            if (test) {
                mobilParams.setMsspSignatureQueryUrl("http://85.235.93.165/MSSP2/services/MSS_Signature");
                mobilParams.setMsspProfileQueryUrl("http://85.235.93.165/MSSP2/services/MSS_ProfileQueryPort");
            } else {
                mobilParams.setMsspSignatureQueryUrl("http://mobilimza.corbuss.com.tr/MSSP2/services/MSS_Signature");
                //Test ortamı için "http://85.235.93.165/MSSP2/services/MSS_Signature"
                mobilParams.setMsspProfileQueryUrl("http://mobilimza.corbuss.com.tr/MSSP2/services/MSS_ProfileQueryPort");
                //Test ortamı için "http://85.235.93.165/MSSP2/services/MSS_ProfileQueryPort "    
            }

            //AVEA
            /*
	         phoneNumber = "05319321172";
	         mobileOperator = 1;
	         MSSParams mobilParams =new MSSParams("http://www.kamusm.gov.tr/prod", "*****", "");
	         mobilParams.setMsspSignatureQueryUrl("http://www.aveamobilimza.com/EGAMsspWSAP/MSS_SignatureService");
	         mobilParams.setMsspProfileQueryUrl("http://www.aveamobilimza.com/EGAMsspWSAP/MSS_ProfileQueryService");
             */
 /*
	         mobilParams.setMsspRequestTimeout(2500);
	         mobilParams.setConnectionTimeoutMs(5000);
             */
            PhoneNumberAndOperator phoneNumberAndOperator = new PhoneNumberAndOperator(phoneNumber, fromInt(mobileOperator));
            EMobileSignerConnector emsspClientConnector = new EMobileSignerConnector(mobilParams);

            //get signer certificate necessary field for signing from operator
            emsspClientConnector.setCertificateInitials(phoneNumberAndOperator);
            ECertificate signerCert = null;

            //byte[] certFileData = AsnIO.dosyadanOKU("C:/Users/int/Desktop/05380892868.cer");
            //ECertificate signerCert = new ECertificate(certFileData);
            MobileSigner mobileSigner = new MobileSigner(emsspClientConnector, phoneNumberAndOperator, signerCert, "Dosya imzalanacak", SignatureAlg.RSA_SHA1.getName(), null);
            bs.addSigner(ESignatureType.TYPE_BES, signerCert, mobileSigner, optionalAttributes, params);

            AsnIO.dosyayaz(bs.getEncoded(), filePath + ".p7s");

        } catch (Exception exc) {
            // TODO Auto-generated catch block
            exc.printStackTrace();
        }
    }
}
