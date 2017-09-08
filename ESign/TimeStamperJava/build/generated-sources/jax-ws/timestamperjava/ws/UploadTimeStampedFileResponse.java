
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
 *         &lt;element name="uploadTimeStampedFileResult" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
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
    "uploadTimeStampedFileResult"
})
@XmlRootElement(name = "uploadTimeStampedFileResponse")
public class UploadTimeStampedFileResponse {

    protected boolean uploadTimeStampedFileResult;

    /**
     * Gets the value of the uploadTimeStampedFileResult property.
     * 
     */
    public boolean isUploadTimeStampedFileResult() {
        return uploadTimeStampedFileResult;
    }

    /**
     * Sets the value of the uploadTimeStampedFileResult property.
     * 
     */
    public void setUploadTimeStampedFileResult(boolean value) {
        this.uploadTimeStampedFileResult = value;
    }

}
