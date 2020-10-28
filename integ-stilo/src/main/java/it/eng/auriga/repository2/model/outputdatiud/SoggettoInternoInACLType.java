//
// Questo file è stato generato dall'architettura JavaTM per XML Binding (JAXB) Reference Implementation, v2.3.2 
// Vedere <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Qualsiasi modifica a questo file andrà persa durante la ricompilazione dello schema di origine. 
// Generato il: 2020.05.26 alle 03:23:29 PM CEST 
//


package it.eng.auriga.repository2.model.outputdatiud;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per SoggettoInternoInACLType complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="SoggettoInternoInACLType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;choice&gt;
 *         &lt;element name="InteroEnte" type="{http://www.w3.org/2001/XMLSchema}anyType"/&gt;
 *         &lt;element name="InteraAOO" type="{http://www.w3.org/2001/XMLSchema}anyType"/&gt;
 *         &lt;element name="ScrivanieDiUO" type="{}UOEstesaType"/&gt;
 *         &lt;element name="Utente" type="{}UserType"/&gt;
 *         &lt;element name="ScrivaniaVirtuale" type="{}ScrivaniaVirtualeType"/&gt;
 *         &lt;element name="Gruppo" type="{}OggDiTabDiSistemaType"/&gt;
 *         &lt;element name="RuoloAmmContestualizzato" type="{}RuoloAmmContestualizzatoType"/&gt;
 *       &lt;/choice&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SoggettoInternoInACLType", propOrder = {
    "interoEnte",
    "interaAOO",
    "scrivanieDiUO",
    "utente",
    "scrivaniaVirtuale",
    "gruppo",
    "ruoloAmmContestualizzato"
})
@XmlSeeAlso({
    ACLRecordType.class
})
public class SoggettoInternoInACLType {

    @XmlElement(name = "InteroEnte")
    protected Object interoEnte;
    @XmlElement(name = "InteraAOO")
    protected Object interaAOO;
    @XmlElement(name = "ScrivanieDiUO")
    protected UOEstesaType scrivanieDiUO;
    @XmlElement(name = "Utente")
    protected UserType utente;
    @XmlElement(name = "ScrivaniaVirtuale")
    protected ScrivaniaVirtualeType scrivaniaVirtuale;
    @XmlElement(name = "Gruppo")
    protected OggDiTabDiSistemaType gruppo;
    @XmlElement(name = "RuoloAmmContestualizzato")
    protected RuoloAmmContestualizzatoType ruoloAmmContestualizzato;

    /**
     * Recupera il valore della proprietà interoEnte.
     * 
     * @return
     *     possible object is
     *     {@link Object }
     *     
     */
    public Object getInteroEnte() {
        return interoEnte;
    }

    /**
     * Imposta il valore della proprietà interoEnte.
     * 
     * @param value
     *     allowed object is
     *     {@link Object }
     *     
     */
    public void setInteroEnte(Object value) {
        this.interoEnte = value;
    }

    /**
     * Recupera il valore della proprietà interaAOO.
     * 
     * @return
     *     possible object is
     *     {@link Object }
     *     
     */
    public Object getInteraAOO() {
        return interaAOO;
    }

    /**
     * Imposta il valore della proprietà interaAOO.
     * 
     * @param value
     *     allowed object is
     *     {@link Object }
     *     
     */
    public void setInteraAOO(Object value) {
        this.interaAOO = value;
    }

    /**
     * Recupera il valore della proprietà scrivanieDiUO.
     * 
     * @return
     *     possible object is
     *     {@link UOEstesaType }
     *     
     */
    public UOEstesaType getScrivanieDiUO() {
        return scrivanieDiUO;
    }

    /**
     * Imposta il valore della proprietà scrivanieDiUO.
     * 
     * @param value
     *     allowed object is
     *     {@link UOEstesaType }
     *     
     */
    public void setScrivanieDiUO(UOEstesaType value) {
        this.scrivanieDiUO = value;
    }

    /**
     * Recupera il valore della proprietà utente.
     * 
     * @return
     *     possible object is
     *     {@link UserType }
     *     
     */
    public UserType getUtente() {
        return utente;
    }

    /**
     * Imposta il valore della proprietà utente.
     * 
     * @param value
     *     allowed object is
     *     {@link UserType }
     *     
     */
    public void setUtente(UserType value) {
        this.utente = value;
    }

    /**
     * Recupera il valore della proprietà scrivaniaVirtuale.
     * 
     * @return
     *     possible object is
     *     {@link ScrivaniaVirtualeType }
     *     
     */
    public ScrivaniaVirtualeType getScrivaniaVirtuale() {
        return scrivaniaVirtuale;
    }

    /**
     * Imposta il valore della proprietà scrivaniaVirtuale.
     * 
     * @param value
     *     allowed object is
     *     {@link ScrivaniaVirtualeType }
     *     
     */
    public void setScrivaniaVirtuale(ScrivaniaVirtualeType value) {
        this.scrivaniaVirtuale = value;
    }

    /**
     * Recupera il valore della proprietà gruppo.
     * 
     * @return
     *     possible object is
     *     {@link OggDiTabDiSistemaType }
     *     
     */
    public OggDiTabDiSistemaType getGruppo() {
        return gruppo;
    }

    /**
     * Imposta il valore della proprietà gruppo.
     * 
     * @param value
     *     allowed object is
     *     {@link OggDiTabDiSistemaType }
     *     
     */
    public void setGruppo(OggDiTabDiSistemaType value) {
        this.gruppo = value;
    }

    /**
     * Recupera il valore della proprietà ruoloAmmContestualizzato.
     * 
     * @return
     *     possible object is
     *     {@link RuoloAmmContestualizzatoType }
     *     
     */
    public RuoloAmmContestualizzatoType getRuoloAmmContestualizzato() {
        return ruoloAmmContestualizzato;
    }

    /**
     * Imposta il valore della proprietà ruoloAmmContestualizzato.
     * 
     * @param value
     *     allowed object is
     *     {@link RuoloAmmContestualizzatoType }
     *     
     */
    public void setRuoloAmmContestualizzato(RuoloAmmContestualizzatoType value) {
        this.ruoloAmmContestualizzato = value;
    }

}
