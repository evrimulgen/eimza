
package esignerclientjava.ws;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.6-1b01 
 * Generated source version: 2.2
 * 
 */
@WebService(name = "WSFileManagerSoap", targetNamespace = "http://tempuri.org/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface WSFileManagerSoap {


    /**
     * 
     * @return
     *     returns java.lang.String
     */
    @WebMethod(action = "http://tempuri.org/helloWorld")
    @WebResult(name = "helloWorldResult", targetNamespace = "http://tempuri.org/")
    @RequestWrapper(localName = "helloWorld", targetNamespace = "http://tempuri.org/", className = "esignerclientjava.ws.HelloWorld")
    @ResponseWrapper(localName = "helloWorldResponse", targetNamespace = "http://tempuri.org/", className = "esignerclientjava.ws.HelloWorldResponse")
    public String helloWorld();

    /**
     * 
     * @param documentID
     * @param sessionID
     * @return
     *     returns byte[]
     */
    @WebMethod(action = "http://tempuri.org/getFileBytes")
    @WebResult(name = "getFileBytesResult", targetNamespace = "http://tempuri.org/")
    @RequestWrapper(localName = "getFileBytes", targetNamespace = "http://tempuri.org/", className = "esignerclientjava.ws.GetFileBytes")
    @ResponseWrapper(localName = "getFileBytesResponse", targetNamespace = "http://tempuri.org/", className = "esignerclientjava.ws.GetFileBytesResponse")
    public byte[] getFileBytes(
        @WebParam(name = "documentID", targetNamespace = "http://tempuri.org/")
        String documentID,
        @WebParam(name = "sessionID", targetNamespace = "http://tempuri.org/")
        String sessionID);

    /**
     * 
     * @param signedFileBytes
     * @param documentID
     * @return
     *     returns boolean
     */
    @WebMethod(action = "http://tempuri.org/uploadSignedFile")
    @WebResult(name = "uploadSignedFileResult", targetNamespace = "http://tempuri.org/")
    @RequestWrapper(localName = "uploadSignedFile", targetNamespace = "http://tempuri.org/", className = "esignerclientjava.ws.UploadSignedFile")
    @ResponseWrapper(localName = "uploadSignedFileResponse", targetNamespace = "http://tempuri.org/", className = "esignerclientjava.ws.UploadSignedFileResponse")
    public boolean uploadSignedFile(
        @WebParam(name = "documentID", targetNamespace = "http://tempuri.org/")
        String documentID,
        @WebParam(name = "signedFileBytes", targetNamespace = "http://tempuri.org/")
        byte[] signedFileBytes);

}
