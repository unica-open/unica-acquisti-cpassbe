//
// Questo file è stato generato dall'architettura JavaTM per XML Binding (JAXB) Reference Implementation, v2.3.2 
// Vedere <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Qualsiasi modifica a questo file andrà persa durante la ricompilazione dello schema di origine. 
// Generato il: 2020.05.26 alle 03:23:29 PM CEST 
//


package it.eng.auriga.repository2.model.outputdatiud;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per FileUDxPrivilegioType complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="FileUDxPrivilegioType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;choice&gt;
 *         &lt;element name="AllFiles" type="{http://www.w3.org/2001/XMLSchema}anyType"/&gt;
 *         &lt;element name="SuFile" type="{http://www.w3.org/2001/XMLSchema}positiveInteger" maxOccurs="unbounded"/&gt;
 *       &lt;/choice&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FileUDxPrivilegioType", propOrder = {
    "allFiles",
    "suFile"
})
public class FileUDxPrivilegioType {

    @XmlElement(name = "AllFiles")
    protected Object allFiles;
    @XmlElement(name = "SuFile")
    @XmlSchemaType(name = "positiveInteger")
    protected List<BigInteger> suFile;

    /**
     * Recupera il valore della proprietà allFiles.
     * 
     * @return
     *     possible object is
     *     {@link Object }
     *     
     */
    public Object getAllFiles() {
        return allFiles;
    }

    /**
     * Imposta il valore della proprietà allFiles.
     * 
     * @param value
     *     allowed object is
     *     {@link Object }
     *     
     */
    public void setAllFiles(Object value) {
        this.allFiles = value;
    }

    /**
     * Gets the value of the suFile property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the suFile property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSuFile().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link BigInteger }
     * 
     * 
     */
    public List<BigInteger> getSuFile() {
        if (suFile == null) {
            suFile = new ArrayList<BigInteger>();
        }
        return this.suFile;
    }

}
