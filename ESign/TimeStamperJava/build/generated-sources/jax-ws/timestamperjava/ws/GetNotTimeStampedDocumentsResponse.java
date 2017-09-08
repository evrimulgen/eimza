
package timestamperjava.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="getNotTimeStampedDocumentsResult" type="{http://tempuri.org/}ArrayOfDocumentClass" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "getNotTimeStampedDocumentsResult"
})
@XmlRootElement(name = "getNotTimeStampedDocumentsResponse")
public class GetNotTimeStampedDocumentsResponse {

    protected ArrayOfDocumentClass getNotTimeStampedDocumentsResult;

    /**
     * Gets the value of the getNotTimeStampedDocumentsResult property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfDocumentClass }
     *     
     */
    public ArrayOfDocumentClass getGetNotTimeStampedDocumentsResult() {
        return getNotTimeStampedDocumentsResult;
    }

    /**
     * Sets the value of the getNotTimeStampedDocumentsResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfDocumentClass }
     *     
     */
    public void setGetNotTimeStampedDocumentsResult(ArrayOfDocumentClass value) {
        this.getNotTimeStampedDocumentsResult = value;
    }

}
