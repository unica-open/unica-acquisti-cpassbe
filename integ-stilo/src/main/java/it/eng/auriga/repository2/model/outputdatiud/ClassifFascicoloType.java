
package it.eng.auriga.repository2.model.outputdatiud;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per ClassifFascicoloType complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="ClassifFascicoloType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;choice&gt;
 *         &lt;element name="ClassifUA" type="{}ClassifUAType"/&gt;
 *         &lt;element name="FolderCustom" type="{}FolderCustomType"/&gt;
 *       &lt;/choice&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ClassifFascicoloType", propOrder = {
    "classifUA",
    "folderCustom"
})
@XmlSeeAlso({
    ClassifFascicoloEstesoType.class
})
public class ClassifFascicoloType {

    @XmlElement(name = "ClassifUA")
    protected ClassifUAType classifUA;
    @XmlElement(name = "FolderCustom")
    protected FolderCustomType folderCustom;

    /**
     * Recupera il valore della proprietà classifUA.
     * 
     * @return
     *     possible object is
     *     {@link ClassifUAType }
     *     
     */
    public ClassifUAType getClassifUA() {
        return classifUA;
    }

    /**
     * Imposta il valore della proprietà classifUA.
     * 
     * @param value
     *     allowed object is
     *     {@link ClassifUAType }
     *     
     */
    public void setClassifUA(ClassifUAType value) {
        this.classifUA = value;
    }

    /**
     * Recupera il valore della proprietà folderCustom.
     * 
     * @return
     *     possible object is
     *     {@link FolderCustomType }
     *     
     */
    public FolderCustomType getFolderCustom() {
        return folderCustom;
    }

    /**
     * Imposta il valore della proprietà folderCustom.
     * 
     * @param value
     *     allowed object is
     *     {@link FolderCustomType }
     *     
     */
    public void setFolderCustom(FolderCustomType value) {
        this.folderCustom = value;
    }

}
