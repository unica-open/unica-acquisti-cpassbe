
package it.eng.auriga.repository2.model.outputdatiud;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per PrivilegioSuFileType complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="PrivilegioSuFileType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="Files" type="{}FileUDxPrivilegioType"/&gt;
 *       &lt;/sequence&gt;
 *       &lt;attribute name="ConsentiNega" use="required" type="{}FlagConsentiNegaType" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PrivilegioSuFileType", propOrder = {
    "files"
})
public class PrivilegioSuFileType {

    @XmlElement(name = "Files", required = true)
    protected FileUDxPrivilegioType files;
    @XmlAttribute(name = "ConsentiNega", required = true)
    protected String consentiNega;

    /**
     * Recupera il valore della proprietà files.
     * 
     * @return
     *     possible object is
     *     {@link FileUDxPrivilegioType }
     *     
     */
    public FileUDxPrivilegioType getFiles() {
        return files;
    }

    /**
     * Imposta il valore della proprietà files.
     * 
     * @param value
     *     allowed object is
     *     {@link FileUDxPrivilegioType }
     *     
     */
    public void setFiles(FileUDxPrivilegioType value) {
        this.files = value;
    }

    /**
     * Recupera il valore della proprietà consentiNega.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getConsentiNega() {
        return consentiNega;
    }

    /**
     * Imposta il valore della proprietà consentiNega.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setConsentiNega(String value) {
        this.consentiNega = value;
    }

}
