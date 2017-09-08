
package timestamperjava.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for DocumentClass complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DocumentClass">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="DocumentID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DocumentName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="FileName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="FileHash" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="IsSigned" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="SignedFileName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="HashSigned" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="UploadDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TimeStamped" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="TimeStampingDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Comment" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Uploader" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DocumentClass", propOrder = {
    "documentID",
    "documentName",
    "fileName",
    "fileHash",
    "isSigned",
    "signedFileName",
    "hashSigned",
    "uploadDate",
    "timeStamped",
    "timeStampingDate",
    "comment",
    "uploader"
})
public class DocumentClass {

    @XmlElement(name = "DocumentID")
    protected String documentID;
    @XmlElement(name = "DocumentName")
    protected String documentName;
    @XmlElement(name = "FileName")
    protected String fileName;
    @XmlElement(name = "FileHash")
    protected String fileHash;
    @XmlElement(name = "IsSigned")
    protected boolean isSigned;
    @XmlElement(name = "SignedFileName")
    protected String signedFileName;
    @XmlElement(name = "HashSigned")
    protected boolean hashSigned;
    @XmlElement(name = "UploadDate")
    protected String uploadDate;
    @XmlElement(name = "TimeStamped")
    protected boolean timeStamped;
    @XmlElement(name = "TimeStampingDate")
    protected String timeStampingDate;
    @XmlElement(name = "Comment")
    protected String comment;
    @XmlElement(name = "Uploader")
    protected String uploader;

    /**
     * Gets the value of the documentID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDocumentID() {
        return documentID;
    }

    /**
     * Sets the value of the documentID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDocumentID(String value) {
        this.documentID = value;
    }

    /**
     * Gets the value of the documentName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDocumentName() {
        return documentName;
    }

    /**
     * Sets the value of the documentName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDocumentName(String value) {
        this.documentName = value;
    }

    /**
     * Gets the value of the fileName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * Sets the value of the fileName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFileName(String value) {
        this.fileName = value;
    }

    /**
     * Gets the value of the fileHash property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFileHash() {
        return fileHash;
    }

    /**
     * Sets the value of the fileHash property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFileHash(String value) {
        this.fileHash = value;
    }

    /**
     * Gets the value of the isSigned property.
     * 
     */
    public boolean isIsSigned() {
        return isSigned;
    }

    /**
     * Sets the value of the isSigned property.
     * 
     */
    public void setIsSigned(boolean value) {
        this.isSigned = value;
    }

    /**
     * Gets the value of the signedFileName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSignedFileName() {
        return signedFileName;
    }

    /**
     * Sets the value of the signedFileName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSignedFileName(String value) {
        this.signedFileName = value;
    }

    /**
     * Gets the value of the hashSigned property.
     * 
     */
    public boolean isHashSigned() {
        return hashSigned;
    }

    /**
     * Sets the value of the hashSigned property.
     * 
     */
    public void setHashSigned(boolean value) {
        this.hashSigned = value;
    }

    /**
     * Gets the value of the uploadDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUploadDate() {
        return uploadDate;
    }

    /**
     * Sets the value of the uploadDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUploadDate(String value) {
        this.uploadDate = value;
    }

    /**
     * Gets the value of the timeStamped property.
     * 
     */
    public boolean isTimeStamped() {
        return timeStamped;
    }

    /**
     * Sets the value of the timeStamped property.
     * 
     */
    public void setTimeStamped(boolean value) {
        this.timeStamped = value;
    }

    /**
     * Gets the value of the timeStampingDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTimeStampingDate() {
        return timeStampingDate;
    }

    /**
     * Sets the value of the timeStampingDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTimeStampingDate(String value) {
        this.timeStampingDate = value;
    }

    /**
     * Gets the value of the comment property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getComment() {
        return comment;
    }

    /**
     * Sets the value of the comment property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setComment(String value) {
        this.comment = value;
    }

    /**
     * Gets the value of the uploader property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUploader() {
        return uploader;
    }

    /**
     * Sets the value of the uploader property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUploader(String value) {
        this.uploader = value;
    }

}
