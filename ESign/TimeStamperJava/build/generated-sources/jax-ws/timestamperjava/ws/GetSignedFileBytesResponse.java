
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
 *         &lt;element name="getSignedFileBytesResult" type="{http://www.w3.org/2001/XMLSchema}base64Binary" minOccurs="0"/>
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
    "getSignedFileBytesResult"
})
@XmlRootElement(name = "getSignedFileBytesResponse")
public class GetSignedFileBytesResponse {

    protected byte[] getSignedFileBytesResult;

    /**
     * Gets the value of the getSignedFileBytesResult property.
     * 
     * @return
     *     possible object is
     *     byte[]
     */
    public byte[] getGetSignedFileBytesResult() {
        return getSignedFileBytesResult;
    }

    /**
     * Sets the value of the getSignedFileBytesResult property.
     * 
     * @param value
     *     allowed object is
     *     byte[]
     */
    public void setGetSignedFileBytesResult(byte[] value) {
        this.getSignedFileBytesResult = value;
    }

}
