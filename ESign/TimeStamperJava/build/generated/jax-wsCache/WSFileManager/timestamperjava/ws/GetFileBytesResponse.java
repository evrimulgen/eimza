
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
 *         &lt;element name="getFileBytesResult" type="{http://www.w3.org/2001/XMLSchema}base64Binary" minOccurs="0"/>
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
    "getFileBytesResult"
})
@XmlRootElement(name = "getFileBytesResponse")
public class GetFileBytesResponse {

    protected byte[] getFileBytesResult;

    /**
     * Gets the value of the getFileBytesResult property.
     * 
     * @return
     *     possible object is
     *     byte[]
     */
    public byte[] getGetFileBytesResult() {
        return getFileBytesResult;
    }

    /**
     * Sets the value of the getFileBytesResult property.
     * 
     * @param value
     *     allowed object is
     *     byte[]
     */
    public void setGetFileBytesResult(byte[] value) {
        this.getFileBytesResult = value;
    }

}
