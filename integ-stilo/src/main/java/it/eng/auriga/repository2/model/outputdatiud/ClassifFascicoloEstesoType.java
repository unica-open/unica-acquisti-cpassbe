//
// Questo file è stato generato dall'architettura JavaTM per XML Binding (JAXB) Reference Implementation, v2.3.2 
// Vedere <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Qualsiasi modifica a questo file andrà persa durante la ricompilazione dello schema di origine. 
// Generato il: 2020.05.26 alle 03:23:29 PM CEST 
//


package it.eng.auriga.repository2.model.outputdatiud;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per ClassifFascicoloEstesoType complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="ClassifFascicoloEstesoType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{}ClassifFascicoloType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="OggettoFasc" type="{}OggettoFascType" minOccurs="0"/&gt;
 *         &lt;element name="TipoFasc" type="{}OggDiTabDiSistemaType" minOccurs="0"/&gt;
 *         &lt;element name="InCaricoA" type="{}AssegnatarioEffType" minOccurs="0"/&gt;
 *         &lt;element name="ApertoDa" type="{}UOUserType" minOccurs="0"/&gt;
 *         &lt;element name="AttributoAddFasc" type="{}AttributoAddizionaleType" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ClassifFascicoloEstesoType", propOrder = {
    "oggettoFasc",
    "tipoFasc",
    "inCaricoA",
    "apertoDa",
    "attributoAddFasc"
})
public class ClassifFascicoloEstesoType
    extends ClassifFascicoloType
{

    @XmlElement(name = "OggettoFasc")
    protected String oggettoFasc;
    @XmlElement(name = "TipoFasc")
    protected OggDiTabDiSistemaType tipoFasc;
    @XmlElement(name = "InCaricoA")
    protected AssegnatarioEffType inCaricoA;
    @XmlElement(name = "ApertoDa")
    protected UOUserType apertoDa;
    @XmlElement(name = "AttributoAddFasc")
    protected List<AttributoAddizionaleType> attributoAddFasc;

    /**
     * Recupera il valore della proprietà oggettoFasc.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOggettoFasc() {
        return oggettoFasc;
    }

    /**
     * Imposta il valore della proprietà oggettoFasc.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOggettoFasc(String value) {
        this.oggettoFasc = value;
    }

    /**
     * Recupera il valore della proprietà tipoFasc.
     * 
     * @return
     *     possible object is
     *     {@link OggDiTabDiSistemaType }
     *     
     */
    public OggDiTabDiSistemaType getTipoFasc() {
        return tipoFasc;
    }

    /**
     * Imposta il valore della proprietà tipoFasc.
     * 
     * @param value
     *     allowed object is
     *     {@link OggDiTabDiSistemaType }
     *     
     */
    public void setTipoFasc(OggDiTabDiSistemaType value) {
        this.tipoFasc = value;
    }

    /**
     * Recupera il valore della proprietà inCaricoA.
     * 
     * @return
     *     possible object is
     *     {@link AssegnatarioEffType }
     *     
     */
    public AssegnatarioEffType getInCaricoA() {
        return inCaricoA;
    }

    /**
     * Imposta il valore della proprietà inCaricoA.
     * 
     * @param value
     *     allowed object is
     *     {@link AssegnatarioEffType }
     *     
     */
    public void setInCaricoA(AssegnatarioEffType value) {
        this.inCaricoA = value;
    }

    /**
     * Recupera il valore della proprietà apertoDa.
     * 
     * @return
     *     possible object is
     *     {@link UOUserType }
     *     
     */
    public UOUserType getApertoDa() {
        return apertoDa;
    }

    /**
     * Imposta il valore della proprietà apertoDa.
     * 
     * @param value
     *     allowed object is
     *     {@link UOUserType }
     *     
     */
    public void setApertoDa(UOUserType value) {
        this.apertoDa = value;
    }

    /**
     * Gets the value of the attributoAddFasc property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the attributoAddFasc property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAttributoAddFasc().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link AttributoAddizionaleType }
     * 
     * 
     */
    public List<AttributoAddizionaleType> getAttributoAddFasc() {
        if (attributoAddFasc == null) {
            attributoAddFasc = new ArrayList<AttributoAddizionaleType>();
        }
        return this.attributoAddFasc;
    }

}
