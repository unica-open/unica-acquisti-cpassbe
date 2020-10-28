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
 * Rappresenta un soggetto interno all'AOO che può essere: una UO, un utente, una scrivania virtuale (che rappresenta l'utente non come persona, ma nella funzione che svolge presso una certa UO), un gruppo (di utenti o UO o scrivanie virtuali) o un ruolo amministrativo (es: Dirigente, Direttore Generale ecc). Quando si assegna ad un gruppo o un ruolo il sistema assegna a tutti gli utenti, UO e scrivanie del gruppo o aventi il ruolo
 * 
 * <p>Classe Java per SoggettoInternoType complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="SoggettoInternoType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;choice&gt;
 *         &lt;element name="UO" type="{}UOType"/&gt;
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
@XmlType(name = "SoggettoInternoType", propOrder = {
    "uo",
    "utente",
    "scrivaniaVirtuale",
    "gruppo",
    "ruoloAmmContestualizzato"
})
@XmlSeeAlso({
    AssegnazioneInternaType.class,
    SoggettoInternoEstesoType.class
})
public class SoggettoInternoType {

    @XmlElement(name = "UO")
    protected UOType uo;
    @XmlElement(name = "Utente")
    protected UserType utente;
    @XmlElement(name = "ScrivaniaVirtuale")
    protected ScrivaniaVirtualeType scrivaniaVirtuale;
    @XmlElement(name = "Gruppo")
    protected OggDiTabDiSistemaType gruppo;
    @XmlElement(name = "RuoloAmmContestualizzato")
    protected RuoloAmmContestualizzatoType ruoloAmmContestualizzato;

    /**
     * Recupera il valore della proprietà uo.
     * 
     * @return
     *     possible object is
     *     {@link UOType }
     *     
     */
    public UOType getUO() {
        return uo;
    }

    /**
     * Imposta il valore della proprietà uo.
     * 
     * @param value
     *     allowed object is
     *     {@link UOType }
     *     
     */
    public void setUO(UOType value) {
        this.uo = value;
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
