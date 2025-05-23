
package it.csi.siac.integ.data._1;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * &lt;p&gt;Classe Java per capitolo complex type.
 * 
 * &lt;p&gt;Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * &lt;pre&gt;
 * &amp;lt;complexType name="capitolo"&amp;gt;
 *   &amp;lt;complexContent&amp;gt;
 *     &amp;lt;extension base="{http://siac.csi.it/integ/data/1.0}entitaBase"&amp;gt;
 *       &amp;lt;sequence&amp;gt;
 *         &amp;lt;element name="annoEsercizio" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="classificatoriGenerici" type="{http://siac.csi.it/integ/data/1.0}classificatoreGenerico" maxOccurs="unbounded" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="descrizione" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="descrizioneArticolo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="numeroArticolo" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="numeroCapitolo" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="numeroUEB" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="pianoDeiContiFinanziario" type="{http://siac.csi.it/integ/data/1.0}pianoDeiContiFinanziario" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="rilevanteIva" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="sac" type="{http://siac.csi.it/integ/data/1.0}strutturaAmministrativa" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="tipoFinanziamento" type="{http://siac.csi.it/integ/data/1.0}tipoFinanziamento" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="tipoFondo" type="{http://siac.csi.it/integ/data/1.0}tipoFondo" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="titolo" type="{http://siac.csi.it/integ/data/1.0}titolo" minOccurs="0"/&amp;gt;
 *       &amp;lt;/sequence&amp;gt;
 *     &amp;lt;/extension&amp;gt;
 *   &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &lt;/pre&gt;
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "capitolo", propOrder = {
    "annoEsercizio",
    "classificatoriGenerici",
    "descrizione",
    "descrizioneArticolo",
    "numeroArticolo",
    "numeroCapitolo",
    "numeroUEB",
    "pianoDeiContiFinanziario",
    "rilevanteIva",
    "sac",
    "tipoFinanziamento",
    "tipoFondo",
    "titolo"
})
@XmlSeeAlso({
    CapitoloEntrataGestione.class,
    CapitoloUscitaGestione.class
})
public class Capitolo
    extends EntitaBase
{

    protected Integer annoEsercizio;
    @XmlElement(nillable = true)
    protected List<ClassificatoreGenerico> classificatoriGenerici;
    protected String descrizione;
    protected String descrizioneArticolo;
    protected Integer numeroArticolo;
    protected Integer numeroCapitolo;
    protected Integer numeroUEB;
    protected PianoDeiContiFinanziario pianoDeiContiFinanziario;
    protected Boolean rilevanteIva;
    protected StrutturaAmministrativa sac;
    protected TipoFinanziamento tipoFinanziamento;
    protected TipoFondo tipoFondo;
    protected Titolo titolo;

    /**
     * Recupera il valore della proprietà annoEsercizio.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getAnnoEsercizio() {
        return annoEsercizio;
    }

    /**
     * Imposta il valore della proprietà annoEsercizio.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setAnnoEsercizio(Integer value) {
        this.annoEsercizio = value;
    }

    /**
     * Gets the value of the classificatoriGenerici property.
     * 
     * &lt;p&gt;
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a &lt;CODE&gt;set&lt;/CODE&gt; method for the classificatoriGenerici property.
     * 
     * &lt;p&gt;
     * For example, to add a new item, do as follows:
     * &lt;pre&gt;
     *    getClassificatoriGenerici().add(newItem);
     * &lt;/pre&gt;
     * 
     * 
     * &lt;p&gt;
     * Objects of the following type(s) are allowed in the list
     * {@link ClassificatoreGenerico }
     * 
     * 
     */
    public List<ClassificatoreGenerico> getClassificatoriGenerici() {
        if (classificatoriGenerici == null) {
            classificatoriGenerici = new ArrayList<ClassificatoreGenerico>();
        }
        return this.classificatoriGenerici;
    }

    /**
     * Recupera il valore della proprietà descrizione.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescrizione() {
        return descrizione;
    }

    /**
     * Imposta il valore della proprietà descrizione.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescrizione(String value) {
        this.descrizione = value;
    }

    /**
     * Recupera il valore della proprietà descrizioneArticolo.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescrizioneArticolo() {
        return descrizioneArticolo;
    }

    /**
     * Imposta il valore della proprietà descrizioneArticolo.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescrizioneArticolo(String value) {
        this.descrizioneArticolo = value;
    }

    /**
     * Recupera il valore della proprietà numeroArticolo.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getNumeroArticolo() {
        return numeroArticolo;
    }

    /**
     * Imposta il valore della proprietà numeroArticolo.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setNumeroArticolo(Integer value) {
        this.numeroArticolo = value;
    }

    /**
     * Recupera il valore della proprietà numeroCapitolo.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getNumeroCapitolo() {
        return numeroCapitolo;
    }

    /**
     * Imposta il valore della proprietà numeroCapitolo.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setNumeroCapitolo(Integer value) {
        this.numeroCapitolo = value;
    }

    /**
     * Recupera il valore della proprietà numeroUEB.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getNumeroUEB() {
        return numeroUEB;
    }

    /**
     * Imposta il valore della proprietà numeroUEB.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setNumeroUEB(Integer value) {
        this.numeroUEB = value;
    }

    /**
     * Recupera il valore della proprietà pianoDeiContiFinanziario.
     * 
     * @return
     *     possible object is
     *     {@link PianoDeiContiFinanziario }
     *     
     */
    public PianoDeiContiFinanziario getPianoDeiContiFinanziario() {
        return pianoDeiContiFinanziario;
    }

    /**
     * Imposta il valore della proprietà pianoDeiContiFinanziario.
     * 
     * @param value
     *     allowed object is
     *     {@link PianoDeiContiFinanziario }
     *     
     */
    public void setPianoDeiContiFinanziario(PianoDeiContiFinanziario value) {
        this.pianoDeiContiFinanziario = value;
    }

    /**
     * Recupera il valore della proprietà rilevanteIva.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isRilevanteIva() {
        return rilevanteIva;
    }

    /**
     * Imposta il valore della proprietà rilevanteIva.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setRilevanteIva(Boolean value) {
        this.rilevanteIva = value;
    }

    /**
     * Recupera il valore della proprietà sac.
     * 
     * @return
     *     possible object is
     *     {@link StrutturaAmministrativa }
     *     
     */
    public StrutturaAmministrativa getSac() {
        return sac;
    }

    /**
     * Imposta il valore della proprietà sac.
     * 
     * @param value
     *     allowed object is
     *     {@link StrutturaAmministrativa }
     *     
     */
    public void setSac(StrutturaAmministrativa value) {
        this.sac = value;
    }

    /**
     * Recupera il valore della proprietà tipoFinanziamento.
     * 
     * @return
     *     possible object is
     *     {@link TipoFinanziamento }
     *     
     */
    public TipoFinanziamento getTipoFinanziamento() {
        return tipoFinanziamento;
    }

    /**
     * Imposta il valore della proprietà tipoFinanziamento.
     * 
     * @param value
     *     allowed object is
     *     {@link TipoFinanziamento }
     *     
     */
    public void setTipoFinanziamento(TipoFinanziamento value) {
        this.tipoFinanziamento = value;
    }

    /**
     * Recupera il valore della proprietà tipoFondo.
     * 
     * @return
     *     possible object is
     *     {@link TipoFondo }
     *     
     */
    public TipoFondo getTipoFondo() {
        return tipoFondo;
    }

    /**
     * Imposta il valore della proprietà tipoFondo.
     * 
     * @param value
     *     allowed object is
     *     {@link TipoFondo }
     *     
     */
    public void setTipoFondo(TipoFondo value) {
        this.tipoFondo = value;
    }

    /**
     * Recupera il valore della proprietà titolo.
     * 
     * @return
     *     possible object is
     *     {@link Titolo }
     *     
     */
    public Titolo getTitolo() {
        return titolo;
    }

    /**
     * Imposta il valore della proprietà titolo.
     * 
     * @param value
     *     allowed object is
     *     {@link Titolo }
     *     
     */
    public void setTitolo(Titolo value) {
        this.titolo = value;
    }

}
