
package it.eng.auriga.repository2.model.outputdatiud;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per ACLRecordType complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="ACLRecordType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{}SoggettoInternoInACLType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="VisualizzazioneDati" type="{}FlagConsentiNegaType"/&gt;
 *         &lt;element name="VisualizzazioneFile" type="{}PrivilegioSuFileType"/&gt;
 *         &lt;element name="ModificaDati" type="{}FlagConsentiNegaType"/&gt;
 *         &lt;element name="ModificaFile" type="{}PrivilegioSuFileType"/&gt;
 *         &lt;element name="ModificaPermessi" type="{}FlagConsentiNegaType"/&gt;
 *         &lt;element name="Copia" type="{}FlagConsentiNegaType"/&gt;
 *         &lt;element name="Eliminazione" type="{}FlagConsentiNegaType"/&gt;
 *         &lt;element name="Recupero" type="{}FlagConsentiNegaType"/&gt;
 *         &lt;element name="AccessoLimitatoVerFilePubblicate" type="{}FileUDxPrivilegioType" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ACLRecordType", propOrder = {
    "visualizzazioneDati",
    "visualizzazioneFile",
    "modificaDati",
    "modificaFile",
    "modificaPermessi",
    "copia",
    "eliminazione",
    "recupero",
    "accessoLimitatoVerFilePubblicate"
})
public class ACLRecordType
    extends SoggettoInternoInACLType
{

    @XmlElement(name = "VisualizzazioneDati", required = true)
    protected String visualizzazioneDati;
    @XmlElement(name = "VisualizzazioneFile", required = true)
    protected PrivilegioSuFileType visualizzazioneFile;
    @XmlElement(name = "ModificaDati", required = true)
    protected String modificaDati;
    @XmlElement(name = "ModificaFile", required = true)
    protected PrivilegioSuFileType modificaFile;
    @XmlElement(name = "ModificaPermessi", required = true)
    protected String modificaPermessi;
    @XmlElement(name = "Copia", required = true)
    protected String copia;
    @XmlElement(name = "Eliminazione", required = true)
    protected String eliminazione;
    @XmlElement(name = "Recupero", required = true)
    protected String recupero;
    @XmlElement(name = "AccessoLimitatoVerFilePubblicate")
    protected FileUDxPrivilegioType accessoLimitatoVerFilePubblicate;

    /**
     * Recupera il valore della proprietà visualizzazioneDati.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVisualizzazioneDati() {
        return visualizzazioneDati;
    }

    /**
     * Imposta il valore della proprietà visualizzazioneDati.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVisualizzazioneDati(String value) {
        this.visualizzazioneDati = value;
    }

    /**
     * Recupera il valore della proprietà visualizzazioneFile.
     * 
     * @return
     *     possible object is
     *     {@link PrivilegioSuFileType }
     *     
     */
    public PrivilegioSuFileType getVisualizzazioneFile() {
        return visualizzazioneFile;
    }

    /**
     * Imposta il valore della proprietà visualizzazioneFile.
     * 
     * @param value
     *     allowed object is
     *     {@link PrivilegioSuFileType }
     *     
     */
    public void setVisualizzazioneFile(PrivilegioSuFileType value) {
        this.visualizzazioneFile = value;
    }

    /**
     * Recupera il valore della proprietà modificaDati.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getModificaDati() {
        return modificaDati;
    }

    /**
     * Imposta il valore della proprietà modificaDati.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setModificaDati(String value) {
        this.modificaDati = value;
    }

    /**
     * Recupera il valore della proprietà modificaFile.
     * 
     * @return
     *     possible object is
     *     {@link PrivilegioSuFileType }
     *     
     */
    public PrivilegioSuFileType getModificaFile() {
        return modificaFile;
    }

    /**
     * Imposta il valore della proprietà modificaFile.
     * 
     * @param value
     *     allowed object is
     *     {@link PrivilegioSuFileType }
     *     
     */
    public void setModificaFile(PrivilegioSuFileType value) {
        this.modificaFile = value;
    }

    /**
     * Recupera il valore della proprietà modificaPermessi.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getModificaPermessi() {
        return modificaPermessi;
    }

    /**
     * Imposta il valore della proprietà modificaPermessi.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setModificaPermessi(String value) {
        this.modificaPermessi = value;
    }

    /**
     * Recupera il valore della proprietà copia.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCopia() {
        return copia;
    }

    /**
     * Imposta il valore della proprietà copia.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCopia(String value) {
        this.copia = value;
    }

    /**
     * Recupera il valore della proprietà eliminazione.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEliminazione() {
        return eliminazione;
    }

    /**
     * Imposta il valore della proprietà eliminazione.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEliminazione(String value) {
        this.eliminazione = value;
    }

    /**
     * Recupera il valore della proprietà recupero.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRecupero() {
        return recupero;
    }

    /**
     * Imposta il valore della proprietà recupero.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRecupero(String value) {
        this.recupero = value;
    }

    /**
     * Recupera il valore della proprietà accessoLimitatoVerFilePubblicate.
     * 
     * @return
     *     possible object is
     *     {@link FileUDxPrivilegioType }
     *     
     */
    public FileUDxPrivilegioType getAccessoLimitatoVerFilePubblicate() {
        return accessoLimitatoVerFilePubblicate;
    }

    /**
     * Imposta il valore della proprietà accessoLimitatoVerFilePubblicate.
     * 
     * @param value
     *     allowed object is
     *     {@link FileUDxPrivilegioType }
     *     
     */
    public void setAccessoLimitatoVerFilePubblicate(FileUDxPrivilegioType value) {
        this.accessoLimitatoVerFilePubblicate = value;
    }

}
