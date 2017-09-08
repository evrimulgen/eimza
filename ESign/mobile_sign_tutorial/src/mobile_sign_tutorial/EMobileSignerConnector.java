/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mobile_sign_tutorial;

import java.security.MessageDigest;
import java.security.spec.AlgorithmParameterSpec;
import tr.gov.tubitak.uekae.esya.api.asn.cms.ESignerIdentifier;
import tr.gov.tubitak.uekae.esya.api.asn.x509.ECertificate;
import tr.gov.tubitak.uekae.esya.api.common.ESYAException;
import tr.gov.tubitak.uekae.esya.api.common.crypto.Algorithms;
import tr.gov.tubitak.uekae.esya.api.crypto.alg.DigestAlg;
import tr.gov.tubitak.uekae.esya.api.infra.mobile.MSSPClientConnector;
import tr.gov.tubitak.uekae.esya.api.infra.mobile.PhoneNumberAndOperator;
import tr.gov.tubitak.uekae.esya.api.infra.mobile.SigningMode;
import tr.gov.tubitak.uekae.esya.api.infra.mobile.UserIdentifier;
import tr.gov.tubitak.uekae.esya.api.webservice.mssclient.wrapper.EMSSPRequestHandler;
import tr.gov.tubitak.uekae.esya.api.webservice.mssclient.wrapper.MSSParams;
import tr.gov.tubitak.uekae.esya.api.webservice.mssclient.wrapper.StringUtil;
import tr.gov.tubitak.uekae.esya.asn.cms.SigningCertificate;
import tr.gov.tubitak.uekae.esya.asn.cms.SigningCertificateV2;

/**
 *
 * @author cihan
 */
public class EMobileSignerConnector implements MSSPClientConnector {

    EMSSPRequestHandler msspRequestHandler;

    public EMobileSignerConnector(MSSParams msspParams) {
        msspRequestHandler = new EMSSPRequestHandler(msspParams);
    }

    @Override
    public void setCertificateInitials(UserIdentifier uı) throws ESYAException {
        try {
            msspRequestHandler.setCertificateInitials((PhoneNumberAndOperator) uı);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public String calculateFingerPrintValue(byte[] dataToBeSigned) {
        String retFingerPrintStr = "";
        byte[] digestForSign = null;
        try {
            MessageDigest digester = MessageDigest.getInstance(Algorithms.DIGEST_SHA1);
            digester.update(dataToBeSigned);
            digestForSign = digester.digest();
        } catch (Exception exc) {
            System.out.println(exc);
            return null;
        }
        String digest4SignHex = StringUtil.toString(digestForSign);
        retFingerPrintStr = digest4SignHex.substring(digest4SignHex.length() - 32);
        return retFingerPrintStr;
    }

    @Override
    public byte[] sign(byte[] bytes, SigningMode sm, UserIdentifier uı, ECertificate ec, String informativeText, String aSigningAlg, AlgorithmParameterSpec aParams) throws ESYAException {
        String fingerPrintStr = calculateFingerPrintValue(bytes);
        if (fingerPrintStr == null) {
            throw new ESYAException("Parmak izi değeri hesaplanamadı.");
        }
        byte[] retSignature = null;
        try {

            retSignature = msspRequestHandler.sign(bytes, sm, (PhoneNumberAndOperator) uı, informativeText, aSigningAlg, aParams);
        } catch (Exception e) {
            throw new ESYAException(e);
        }
        return retSignature;
    }

    @Override
    public ECertificate getSigningCert() {
        return msspRequestHandler.getSigningCert();
    }

    @Override
    public SigningCertificate getSigningCertAttr() {
        return msspRequestHandler.getSigningCertAttr();
    }

    @Override
    public SigningCertificateV2 getSigningCertAttrv2() {
        return msspRequestHandler.getSigningCertAttrv2();
    }

    @Override
    public ESignerIdentifier getSignerIdentifier() {
        return msspRequestHandler.getSignerIdentifier();
    }

    @Override
    public DigestAlg getDigestAlg() {
        return msspRequestHandler.getDigestAlg();
    }

}
