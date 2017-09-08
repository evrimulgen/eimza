
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
 *         &lt;element name="justNotTimeStamped" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
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
    "justNotTimeStamped"
})
@XmlRootElement(name = "getNotTimeStampedDocuments")
public class GetNotTimeStampedDocuments {

    protected boolean justNotTimeStamped;

    /**
     * Gets the value of the justNotTimeStamped property.
     * 
     */
    public boolean isJustNotTimeStamped() {
        return justNotTimeStamped;
    }

    /**
     * Sets the value of the justNotTimeStamped property.
     * 
     */
    public void setJustNotTimeStamped(boolean value) {
        this.justNotTimeStamped = value;
    }

}
