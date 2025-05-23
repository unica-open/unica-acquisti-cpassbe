
package it.eng.auriga.repository2.model.outputdatiud;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per LivelloGerarchiaType complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="LivelloGerarchiaType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;attribute name="Nro" use="required" type="{}NroLivelloGerarchiaType" /&gt;
 *       &lt;attribute name="Codice" use="required" type="{}CodLivelloGerarchiaType" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "LivelloGerarchiaType")
public class LivelloGerarchiaType {

    @XmlAttribute(name = "Nro", required = true)
    protected int nro;
    @XmlAttribute(name = "Codice", required = true)
    protected String codice;

    /**
     * Recupera il valore della proprietà nro.
     * 
     */
    public int getNro() {
        return nro;
    }

    /**
     * Imposta il valore della proprietà nro.
     * 
     */
    public void setNro(int value) {
        this.nro = value;
    }

    /**
     * Recupera il valore della proprietà codice.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodice() {
        return codice;
    }

    /**
     * Imposta il valore della proprietà codice.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodice(String value) {
        this.codice = value;
    }

}
