//
// Questo file è stato generato dall'architettura JavaTM per XML Binding (JAXB) Reference Implementation, v2.3.2 
// Vedere <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Qualsiasi modifica a questo file andrà persa durante la ricompilazione dello schema di origine. 
// Generato il: 2020.05.26 alle 03:23:29 PM CEST 
//


package it.eng.auriga.repository2.model.outputdatiud;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * Rappresenta un folder che non sia un'unità archivistica
 * 
 * <p>Classe Java per FolderCustomType complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="FolderCustomType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="IdFolder" type="{http://www.w3.org/2001/XMLSchema}integer"/&gt;
 *         &lt;element name="Path_Nome" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FolderCustomType", propOrder = {
    "idFolder",
    "pathNome"
})
public class FolderCustomType {

    @XmlElement(name = "IdFolder", required = true)
    protected BigInteger idFolder;
    @XmlElement(name = "Path_Nome", required = true)
    protected String pathNome;

    /**
     * Recupera il valore della proprietà idFolder.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getIdFolder() {
        return idFolder;
    }

    /**
     * Imposta il valore della proprietà idFolder.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setIdFolder(BigInteger value) {
        this.idFolder = value;
    }

    /**
     * Recupera il valore della proprietà pathNome.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPathNome() {
        return pathNome;
    }

    /**
     * Imposta il valore della proprietà pathNome.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPathNome(String value) {
        this.pathNome = value;
    }

}
