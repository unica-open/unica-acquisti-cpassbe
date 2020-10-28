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
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Classe Java per anonymous complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="IdUD" type="{http://www.w3.org/2001/XMLSchema}integer"/&gt;
 *         &lt;element name="IdDocPrimario" type="{http://www.w3.org/2001/XMLSchema}integer"/&gt;
 *         &lt;element name="NomeUD"&gt;
 *           &lt;complexType&gt;
 *             &lt;simpleContent&gt;
 *               &lt;extension base="&lt;&gt;NomeUDType"&gt;
 *                 &lt;attribute name="DifferenteDaAttuale" type="{}FlagSiNoType" /&gt;
 *                 &lt;attribute name="ModificatoAlTsRich" type="{}FlagSiNoType" /&gt;
 *               &lt;/extension&gt;
 *             &lt;/simpleContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="RegistrazioneData" type="{}RegistrazioneNumerazioneType" maxOccurs="unbounded"/&gt;
 *         &lt;element name="OggettoUD"&gt;
 *           &lt;complexType&gt;
 *             &lt;simpleContent&gt;
 *               &lt;extension base="&lt;&gt;OggettoUDType"&gt;
 *                 &lt;attribute name="DifferenteDaAttuale" type="{}FlagSiNoType" /&gt;
 *                 &lt;attribute name="ModificatoAlTsRich" type="{}FlagSiNoType" /&gt;
 *               &lt;/extension&gt;
 *             &lt;/simpleContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="TipoDoc" type="{}OggDiTabDiSistemaType"/&gt;
 *         &lt;element name="OriginaleCartaceo" type="{}FlagSiNoType" minOccurs="0"/&gt;
 *         &lt;element name="TipoCartaceo" type="{}TipoCartaceoType" minOccurs="0"/&gt;
 *         &lt;element name="VersioneElettronica" type="{}VersioneElettronicaType"/&gt;
 *         &lt;element name="TipoProvenienza" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;enumeration value="E"/&gt;
 *               &lt;enumeration value="U"/&gt;
 *               &lt;enumeration value="I"/&gt;
 *               &lt;enumeration value=""/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="NroAllegati" type="{http://www.w3.org/2001/XMLSchema}integer"/&gt;
 *         &lt;element name="DatiProduzione" minOccurs="0"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="DataStesura" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/&gt;
 *                   &lt;element name="LuogoStesura" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                   &lt;element name="Estensore" type="{}UserType" maxOccurs="unbounded" minOccurs="0"/&gt;
 *                   &lt;element name="UffProduttore" type="{}UOType" maxOccurs="unbounded" minOccurs="0"/&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="DatiEntrata" minOccurs="0"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="DataOraArrivo" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *                   &lt;element name="DataDocRicevuto" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/&gt;
 *                   &lt;element name="EstremiRegistrazioneDocRicevuto" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                   &lt;element name="RiferimentiDocRicevuto" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                   &lt;element name="MezzoTrasmissione" type="{}OggDiTabDiSistemaType" minOccurs="0"/&gt;
 *                   &lt;element name="DataRaccomandata" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/&gt;
 *                   &lt;element name="NroRaccomandata" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                   &lt;element name="MittenteEsterno" type="{}SoggettoEsternoType" maxOccurs="unbounded" minOccurs="0"/&gt;
 *                   &lt;element name="FirmatarioEsterno" type="{}SoggettoEsternoType" maxOccurs="unbounded" minOccurs="0"/&gt;
 *                   &lt;element name="IndirizzoProv" type="{}IndirizzoType" minOccurs="0"/&gt;
 *                   &lt;element name="IndirizzoEmailProv" type="{}EmailType" minOccurs="0"/&gt;
 *                   &lt;element name="UtenteRicezione" type="{}UserType" minOccurs="0"/&gt;
 *                   &lt;element name="UffRicezione" type="{}UOType" minOccurs="0"/&gt;
 *                   &lt;element name="UtenteCtrlAmmissib" type="{}UserType" minOccurs="0"/&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="DatiUscita" minOccurs="0"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="DataOraSped" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *                   &lt;element name="MezzoTrasmissione" type="{}OggDiTabDiSistemaType" minOccurs="0"/&gt;
 *                   &lt;element name="DataRaccomandata" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/&gt;
 *                   &lt;element name="NroRaccomandata" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                   &lt;element name="DestinatarioEsterno" type="{}DestinatarioEsternoType" maxOccurs="unbounded" minOccurs="0"/&gt;
 *                   &lt;element name="UtenteSpedizione" type="{}UserType" minOccurs="0"/&gt;
 *                   &lt;element name="UffSpedizione" type="{}UOType" minOccurs="0"/&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="AltroSoggEsterno" type="{}SoggettoEstEstesoType" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="AssegnazioneInterna" type="{}AssegnazioneInternaType" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="CopiaDaTenereA" type="{}SoggettoInternoType" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="AltroSoggettoInterno" type="{}SoggettoInternoEstesoType" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="CollocazioneClassificazioneUD"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="LibreriaUD" type="{}LibreriaType" minOccurs="0"/&gt;
 *                   &lt;element name="ClassifFascicolo" type="{}ClassifFascicoloEstesoType" maxOccurs="unbounded" minOccurs="0"/&gt;
 *                   &lt;element name="Workspace" type="{}OggDiTabDiSistemaType" maxOccurs="unbounded" minOccurs="0"/&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="AllegatoUD" type="{}AllegatoUDType" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="InRispostaAUD" type="{}EstremiRegNumType" minOccurs="0"/&gt;
 *         &lt;element name="LivelloRiservatezzaInterno" type="{http://www.w3.org/2001/XMLSchema}positiveInteger" minOccurs="0"/&gt;
 *         &lt;element name="FlagVietatoAccessoEsterno" type="{}FlagSiNoType" minOccurs="0"/&gt;
 *         &lt;element name="VietatoAccessoEsternoFinoAl" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/&gt;
 *         &lt;element name="PermessoVsSoggInterno" type="{}ACLRecordType" maxOccurs="unbounded"/&gt;
 *         &lt;element name="LivelloEvidenza" type="{http://www.w3.org/2001/XMLSchema}positiveInteger" minOccurs="0"/&gt;
 *         &lt;element name="Stato" minOccurs="0"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;extension base="{}OggDiTabDiSistemaType"&gt;
 *                 &lt;attribute name="DifferenteDaAttuale" type="{}FlagSiNoType" /&gt;
 *                 &lt;attribute name="ModificatoAlTsRich" type="{}FlagSiNoType" /&gt;
 *               &lt;/extension&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="StatoDettaglio" minOccurs="0"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;extension base="{}OggDiTabDiSistemaType"&gt;
 *                 &lt;attribute name="DifferenteDaAttuale" type="{}FlagSiNoType" /&gt;
 *                 &lt;attribute name="ModificatoAlTsRich" type="{}FlagSiNoType" /&gt;
 *               &lt;/extension&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="NoteUD" minOccurs="0"&gt;
 *           &lt;complexType&gt;
 *             &lt;simpleContent&gt;
 *               &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema&gt;string"&gt;
 *                 &lt;attribute name="DifferenteDaAttuale" type="{}FlagSiNoType" /&gt;
 *                 &lt;attribute name="ModificatoAlTsRich" type="{}FlagSiNoType" /&gt;
 *               &lt;/extension&gt;
 *             &lt;/simpleContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="AttributoAddUD" type="{}AttributoAddizionaleType" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="DataOraCreazione" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="UtenteDiCreazione" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="DataOraUltimaModifica" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="UtenteUltimaModifica" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="DataOraUltimoAccesso" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="LockInfo" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="BitmapAbilitazioniAzioniUD" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="BitmapAbilitazioniAzioniFilePrimario" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="RuoloUtenteRispettoUD" type="{}OggDiTabDiSistemaType" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "idUD",
    "idDocPrimario",
    "nomeUD",
    "registrazioneData",
    "oggettoUD",
    "tipoDoc",
    "originaleCartaceo",
    "tipoCartaceo",
    "versioneElettronica",
    "tipoProvenienza",
    "nroAllegati",
    "datiProduzione",
    "datiEntrata",
    "datiUscita",
    "altroSoggEsterno",
    "assegnazioneInterna",
    "copiaDaTenereA",
    "altroSoggettoInterno",
    "collocazioneClassificazioneUD",
    "allegatoUD",
    "inRispostaAUD",
    "livelloRiservatezzaInterno",
    "flagVietatoAccessoEsterno",
    "vietatoAccessoEsternoFinoAl",
    "permessoVsSoggInterno",
    "livelloEvidenza",
    "stato",
    "statoDettaglio",
    "noteUD",
    "attributoAddUD",
    "dataOraCreazione",
    "utenteDiCreazione",
    "dataOraUltimaModifica",
    "utenteUltimaModifica",
    "dataOraUltimoAccesso",
    "lockInfo",
    "bitmapAbilitazioniAzioniUD",
    "bitmapAbilitazioniAzioniFilePrimario",
    "ruoloUtenteRispettoUD"
})
@XmlRootElement(name = "DatiUD")
public class DatiUD {

    @XmlElement(name = "IdUD", required = true)
    protected BigInteger idUD;
    @XmlElement(name = "IdDocPrimario", required = true)
    protected BigInteger idDocPrimario;
    @XmlElement(name = "NomeUD", required = true)
    protected DatiUD.NomeUD nomeUD;
    @XmlElement(name = "RegistrazioneData", required = true)
    protected List<RegistrazioneNumerazioneType> registrazioneData;
    @XmlElement(name = "OggettoUD", required = true)
    protected DatiUD.OggettoUD oggettoUD;
    @XmlElement(name = "TipoDoc", required = true)
    protected OggDiTabDiSistemaType tipoDoc;
    @XmlElement(name = "OriginaleCartaceo")
    protected String originaleCartaceo;
    @XmlElement(name = "TipoCartaceo")
    protected String tipoCartaceo;
    @XmlElement(name = "VersioneElettronica", required = true)
    protected VersioneElettronicaType versioneElettronica;
    @XmlElement(name = "TipoProvenienza", defaultValue = "I")
    protected String tipoProvenienza;
    @XmlElement(name = "NroAllegati", required = true)
    protected BigInteger nroAllegati;
    @XmlElement(name = "DatiProduzione")
    protected DatiUD.DatiProduzione datiProduzione;
    @XmlElement(name = "DatiEntrata")
    protected DatiUD.DatiEntrata datiEntrata;
    @XmlElement(name = "DatiUscita")
    protected DatiUD.DatiUscita datiUscita;
    @XmlElement(name = "AltroSoggEsterno")
    protected List<SoggettoEstEstesoType> altroSoggEsterno;
    @XmlElement(name = "AssegnazioneInterna")
    protected List<AssegnazioneInternaType> assegnazioneInterna;
    @XmlElement(name = "CopiaDaTenereA")
    protected List<SoggettoInternoType> copiaDaTenereA;
    @XmlElement(name = "AltroSoggettoInterno")
    protected List<SoggettoInternoEstesoType> altroSoggettoInterno;
    @XmlElement(name = "CollocazioneClassificazioneUD", required = true)
    protected DatiUD.CollocazioneClassificazioneUD collocazioneClassificazioneUD;
    @XmlElement(name = "AllegatoUD")
    protected List<AllegatoUDType> allegatoUD;
    @XmlElement(name = "InRispostaAUD")
    protected EstremiRegNumType inRispostaAUD;
    @XmlElement(name = "LivelloRiservatezzaInterno")
    @XmlSchemaType(name = "positiveInteger")
    protected BigInteger livelloRiservatezzaInterno;
    @XmlElement(name = "FlagVietatoAccessoEsterno")
    protected String flagVietatoAccessoEsterno;
    @XmlElement(name = "VietatoAccessoEsternoFinoAl")
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar vietatoAccessoEsternoFinoAl;
    @XmlElement(name = "PermessoVsSoggInterno", required = true)
    protected List<ACLRecordType> permessoVsSoggInterno;
    @XmlElement(name = "LivelloEvidenza")
    @XmlSchemaType(name = "positiveInteger")
    protected BigInteger livelloEvidenza;
    @XmlElement(name = "Stato")
    protected DatiUD.Stato stato;
    @XmlElement(name = "StatoDettaglio")
    protected DatiUD.StatoDettaglio statoDettaglio;
    @XmlElement(name = "NoteUD")
    protected DatiUD.NoteUD noteUD;
    @XmlElement(name = "AttributoAddUD")
    protected List<AttributoAddizionaleType> attributoAddUD;
    @XmlElement(name = "DataOraCreazione", required = true)
    protected String dataOraCreazione;
    @XmlElement(name = "UtenteDiCreazione", required = true)
    protected String utenteDiCreazione;
    @XmlElement(name = "DataOraUltimaModifica")
    protected String dataOraUltimaModifica;
    @XmlElement(name = "UtenteUltimaModifica")
    protected String utenteUltimaModifica;
    @XmlElement(name = "DataOraUltimoAccesso")
    protected String dataOraUltimoAccesso;
    @XmlElement(name = "LockInfo", required = true)
    protected String lockInfo;
    @XmlElement(name = "BitmapAbilitazioniAzioniUD", required = true)
    protected String bitmapAbilitazioniAzioniUD;
    @XmlElement(name = "BitmapAbilitazioniAzioniFilePrimario", required = true)
    protected String bitmapAbilitazioniAzioniFilePrimario;
    @XmlElement(name = "RuoloUtenteRispettoUD")
    protected List<OggDiTabDiSistemaType> ruoloUtenteRispettoUD;

    /**
     * Recupera il valore della proprietà idUD.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getIdUD() {
        return idUD;
    }

    /**
     * Imposta il valore della proprietà idUD.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setIdUD(BigInteger value) {
        this.idUD = value;
    }

    /**
     * Recupera il valore della proprietà idDocPrimario.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getIdDocPrimario() {
        return idDocPrimario;
    }

    /**
     * Imposta il valore della proprietà idDocPrimario.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setIdDocPrimario(BigInteger value) {
        this.idDocPrimario = value;
    }

    /**
     * Recupera il valore della proprietà nomeUD.
     * 
     * @return
     *     possible object is
     *     {@link DatiUD.NomeUD }
     *     
     */
    public DatiUD.NomeUD getNomeUD() {
        return nomeUD;
    }

    /**
     * Imposta il valore della proprietà nomeUD.
     * 
     * @param value
     *     allowed object is
     *     {@link DatiUD.NomeUD }
     *     
     */
    public void setNomeUD(DatiUD.NomeUD value) {
        this.nomeUD = value;
    }

    /**
     * Gets the value of the registrazioneData property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the registrazioneData property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRegistrazioneData().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link RegistrazioneNumerazioneType }
     * 
     * 
     */
    public List<RegistrazioneNumerazioneType> getRegistrazioneData() {
        if (registrazioneData == null) {
            registrazioneData = new ArrayList<RegistrazioneNumerazioneType>();
        }
        return this.registrazioneData;
    }

    /**
     * Recupera il valore della proprietà oggettoUD.
     * 
     * @return
     *     possible object is
     *     {@link DatiUD.OggettoUD }
     *     
     */
    public DatiUD.OggettoUD getOggettoUD() {
        return oggettoUD;
    }

    /**
     * Imposta il valore della proprietà oggettoUD.
     * 
     * @param value
     *     allowed object is
     *     {@link DatiUD.OggettoUD }
     *     
     */
    public void setOggettoUD(DatiUD.OggettoUD value) {
        this.oggettoUD = value;
    }

    /**
     * Recupera il valore della proprietà tipoDoc.
     * 
     * @return
     *     possible object is
     *     {@link OggDiTabDiSistemaType }
     *     
     */
    public OggDiTabDiSistemaType getTipoDoc() {
        return tipoDoc;
    }

    /**
     * Imposta il valore della proprietà tipoDoc.
     * 
     * @param value
     *     allowed object is
     *     {@link OggDiTabDiSistemaType }
     *     
     */
    public void setTipoDoc(OggDiTabDiSistemaType value) {
        this.tipoDoc = value;
    }

    /**
     * Recupera il valore della proprietà originaleCartaceo.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOriginaleCartaceo() {
        return originaleCartaceo;
    }

    /**
     * Imposta il valore della proprietà originaleCartaceo.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOriginaleCartaceo(String value) {
        this.originaleCartaceo = value;
    }

    /**
     * Recupera il valore della proprietà tipoCartaceo.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipoCartaceo() {
        return tipoCartaceo;
    }

    /**
     * Imposta il valore della proprietà tipoCartaceo.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipoCartaceo(String value) {
        this.tipoCartaceo = value;
    }

    /**
     * Recupera il valore della proprietà versioneElettronica.
     * 
     * @return
     *     possible object is
     *     {@link VersioneElettronicaType }
     *     
     */
    public VersioneElettronicaType getVersioneElettronica() {
        return versioneElettronica;
    }

    /**
     * Imposta il valore della proprietà versioneElettronica.
     * 
     * @param value
     *     allowed object is
     *     {@link VersioneElettronicaType }
     *     
     */
    public void setVersioneElettronica(VersioneElettronicaType value) {
        this.versioneElettronica = value;
    }

    /**
     * Recupera il valore della proprietà tipoProvenienza.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipoProvenienza() {
        return tipoProvenienza;
    }

    /**
     * Imposta il valore della proprietà tipoProvenienza.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipoProvenienza(String value) {
        this.tipoProvenienza = value;
    }

    /**
     * Recupera il valore della proprietà nroAllegati.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getNroAllegati() {
        return nroAllegati;
    }

    /**
     * Imposta il valore della proprietà nroAllegati.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setNroAllegati(BigInteger value) {
        this.nroAllegati = value;
    }

    /**
     * Recupera il valore della proprietà datiProduzione.
     * 
     * @return
     *     possible object is
     *     {@link DatiUD.DatiProduzione }
     *     
     */
    public DatiUD.DatiProduzione getDatiProduzione() {
        return datiProduzione;
    }

    /**
     * Imposta il valore della proprietà datiProduzione.
     * 
     * @param value
     *     allowed object is
     *     {@link DatiUD.DatiProduzione }
     *     
     */
    public void setDatiProduzione(DatiUD.DatiProduzione value) {
        this.datiProduzione = value;
    }

    /**
     * Recupera il valore della proprietà datiEntrata.
     * 
     * @return
     *     possible object is
     *     {@link DatiUD.DatiEntrata }
     *     
     */
    public DatiUD.DatiEntrata getDatiEntrata() {
        return datiEntrata;
    }

    /**
     * Imposta il valore della proprietà datiEntrata.
     * 
     * @param value
     *     allowed object is
     *     {@link DatiUD.DatiEntrata }
     *     
     */
    public void setDatiEntrata(DatiUD.DatiEntrata value) {
        this.datiEntrata = value;
    }

    /**
     * Recupera il valore della proprietà datiUscita.
     * 
     * @return
     *     possible object is
     *     {@link DatiUD.DatiUscita }
     *     
     */
    public DatiUD.DatiUscita getDatiUscita() {
        return datiUscita;
    }

    /**
     * Imposta il valore della proprietà datiUscita.
     * 
     * @param value
     *     allowed object is
     *     {@link DatiUD.DatiUscita }
     *     
     */
    public void setDatiUscita(DatiUD.DatiUscita value) {
        this.datiUscita = value;
    }

    /**
     * Gets the value of the altroSoggEsterno property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the altroSoggEsterno property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAltroSoggEsterno().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SoggettoEstEstesoType }
     * 
     * 
     */
    public List<SoggettoEstEstesoType> getAltroSoggEsterno() {
        if (altroSoggEsterno == null) {
            altroSoggEsterno = new ArrayList<SoggettoEstEstesoType>();
        }
        return this.altroSoggEsterno;
    }

    /**
     * Gets the value of the assegnazioneInterna property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the assegnazioneInterna property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAssegnazioneInterna().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link AssegnazioneInternaType }
     * 
     * 
     */
    public List<AssegnazioneInternaType> getAssegnazioneInterna() {
        if (assegnazioneInterna == null) {
            assegnazioneInterna = new ArrayList<AssegnazioneInternaType>();
        }
        return this.assegnazioneInterna;
    }

    /**
     * Gets the value of the copiaDaTenereA property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the copiaDaTenereA property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCopiaDaTenereA().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SoggettoInternoType }
     * 
     * 
     */
    public List<SoggettoInternoType> getCopiaDaTenereA() {
        if (copiaDaTenereA == null) {
            copiaDaTenereA = new ArrayList<SoggettoInternoType>();
        }
        return this.copiaDaTenereA;
    }

    /**
     * Gets the value of the altroSoggettoInterno property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the altroSoggettoInterno property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAltroSoggettoInterno().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SoggettoInternoEstesoType }
     * 
     * 
     */
    public List<SoggettoInternoEstesoType> getAltroSoggettoInterno() {
        if (altroSoggettoInterno == null) {
            altroSoggettoInterno = new ArrayList<SoggettoInternoEstesoType>();
        }
        return this.altroSoggettoInterno;
    }

    /**
     * Recupera il valore della proprietà collocazioneClassificazioneUD.
     * 
     * @return
     *     possible object is
     *     {@link DatiUD.CollocazioneClassificazioneUD }
     *     
     */
    public DatiUD.CollocazioneClassificazioneUD getCollocazioneClassificazioneUD() {
        return collocazioneClassificazioneUD;
    }

    /**
     * Imposta il valore della proprietà collocazioneClassificazioneUD.
     * 
     * @param value
     *     allowed object is
     *     {@link DatiUD.CollocazioneClassificazioneUD }
     *     
     */
    public void setCollocazioneClassificazioneUD(DatiUD.CollocazioneClassificazioneUD value) {
        this.collocazioneClassificazioneUD = value;
    }

    /**
     * Gets the value of the allegatoUD property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the allegatoUD property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAllegatoUD().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link AllegatoUDType }
     * 
     * 
     */
    public List<AllegatoUDType> getAllegatoUD() {
        if (allegatoUD == null) {
            allegatoUD = new ArrayList<AllegatoUDType>();
        }
        return this.allegatoUD;
    }

    /**
     * Recupera il valore della proprietà inRispostaAUD.
     * 
     * @return
     *     possible object is
     *     {@link EstremiRegNumType }
     *     
     */
    public EstremiRegNumType getInRispostaAUD() {
        return inRispostaAUD;
    }

    /**
     * Imposta il valore della proprietà inRispostaAUD.
     * 
     * @param value
     *     allowed object is
     *     {@link EstremiRegNumType }
     *     
     */
    public void setInRispostaAUD(EstremiRegNumType value) {
        this.inRispostaAUD = value;
    }

    /**
     * Recupera il valore della proprietà livelloRiservatezzaInterno.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getLivelloRiservatezzaInterno() {
        return livelloRiservatezzaInterno;
    }

    /**
     * Imposta il valore della proprietà livelloRiservatezzaInterno.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setLivelloRiservatezzaInterno(BigInteger value) {
        this.livelloRiservatezzaInterno = value;
    }

    /**
     * Recupera il valore della proprietà flagVietatoAccessoEsterno.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFlagVietatoAccessoEsterno() {
        return flagVietatoAccessoEsterno;
    }

    /**
     * Imposta il valore della proprietà flagVietatoAccessoEsterno.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFlagVietatoAccessoEsterno(String value) {
        this.flagVietatoAccessoEsterno = value;
    }

    /**
     * Recupera il valore della proprietà vietatoAccessoEsternoFinoAl.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getVietatoAccessoEsternoFinoAl() {
        return vietatoAccessoEsternoFinoAl;
    }

    /**
     * Imposta il valore della proprietà vietatoAccessoEsternoFinoAl.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setVietatoAccessoEsternoFinoAl(XMLGregorianCalendar value) {
        this.vietatoAccessoEsternoFinoAl = value;
    }

    /**
     * Gets the value of the permessoVsSoggInterno property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the permessoVsSoggInterno property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPermessoVsSoggInterno().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ACLRecordType }
     * 
     * 
     */
    public List<ACLRecordType> getPermessoVsSoggInterno() {
        if (permessoVsSoggInterno == null) {
            permessoVsSoggInterno = new ArrayList<ACLRecordType>();
        }
        return this.permessoVsSoggInterno;
    }

    /**
     * Recupera il valore della proprietà livelloEvidenza.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getLivelloEvidenza() {
        return livelloEvidenza;
    }

    /**
     * Imposta il valore della proprietà livelloEvidenza.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setLivelloEvidenza(BigInteger value) {
        this.livelloEvidenza = value;
    }

    /**
     * Recupera il valore della proprietà stato.
     * 
     * @return
     *     possible object is
     *     {@link DatiUD.Stato }
     *     
     */
    public DatiUD.Stato getStato() {
        return stato;
    }

    /**
     * Imposta il valore della proprietà stato.
     * 
     * @param value
     *     allowed object is
     *     {@link DatiUD.Stato }
     *     
     */
    public void setStato(DatiUD.Stato value) {
        this.stato = value;
    }

    /**
     * Recupera il valore della proprietà statoDettaglio.
     * 
     * @return
     *     possible object is
     *     {@link DatiUD.StatoDettaglio }
     *     
     */
    public DatiUD.StatoDettaglio getStatoDettaglio() {
        return statoDettaglio;
    }

    /**
     * Imposta il valore della proprietà statoDettaglio.
     * 
     * @param value
     *     allowed object is
     *     {@link DatiUD.StatoDettaglio }
     *     
     */
    public void setStatoDettaglio(DatiUD.StatoDettaglio value) {
        this.statoDettaglio = value;
    }

    /**
     * Recupera il valore della proprietà noteUD.
     * 
     * @return
     *     possible object is
     *     {@link DatiUD.NoteUD }
     *     
     */
    public DatiUD.NoteUD getNoteUD() {
        return noteUD;
    }

    /**
     * Imposta il valore della proprietà noteUD.
     * 
     * @param value
     *     allowed object is
     *     {@link DatiUD.NoteUD }
     *     
     */
    public void setNoteUD(DatiUD.NoteUD value) {
        this.noteUD = value;
    }

    /**
     * Gets the value of the attributoAddUD property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the attributoAddUD property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAttributoAddUD().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link AttributoAddizionaleType }
     * 
     * 
     */
    public List<AttributoAddizionaleType> getAttributoAddUD() {
        if (attributoAddUD == null) {
            attributoAddUD = new ArrayList<AttributoAddizionaleType>();
        }
        return this.attributoAddUD;
    }

    /**
     * Recupera il valore della proprietà dataOraCreazione.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDataOraCreazione() {
        return dataOraCreazione;
    }

    /**
     * Imposta il valore della proprietà dataOraCreazione.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDataOraCreazione(String value) {
        this.dataOraCreazione = value;
    }

    /**
     * Recupera il valore della proprietà utenteDiCreazione.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUtenteDiCreazione() {
        return utenteDiCreazione;
    }

    /**
     * Imposta il valore della proprietà utenteDiCreazione.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUtenteDiCreazione(String value) {
        this.utenteDiCreazione = value;
    }

    /**
     * Recupera il valore della proprietà dataOraUltimaModifica.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDataOraUltimaModifica() {
        return dataOraUltimaModifica;
    }

    /**
     * Imposta il valore della proprietà dataOraUltimaModifica.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDataOraUltimaModifica(String value) {
        this.dataOraUltimaModifica = value;
    }

    /**
     * Recupera il valore della proprietà utenteUltimaModifica.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUtenteUltimaModifica() {
        return utenteUltimaModifica;
    }

    /**
     * Imposta il valore della proprietà utenteUltimaModifica.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUtenteUltimaModifica(String value) {
        this.utenteUltimaModifica = value;
    }

    /**
     * Recupera il valore della proprietà dataOraUltimoAccesso.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDataOraUltimoAccesso() {
        return dataOraUltimoAccesso;
    }

    /**
     * Imposta il valore della proprietà dataOraUltimoAccesso.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDataOraUltimoAccesso(String value) {
        this.dataOraUltimoAccesso = value;
    }

    /**
     * Recupera il valore della proprietà lockInfo.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLockInfo() {
        return lockInfo;
    }

    /**
     * Imposta il valore della proprietà lockInfo.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLockInfo(String value) {
        this.lockInfo = value;
    }

    /**
     * Recupera il valore della proprietà bitmapAbilitazioniAzioniUD.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBitmapAbilitazioniAzioniUD() {
        return bitmapAbilitazioniAzioniUD;
    }

    /**
     * Imposta il valore della proprietà bitmapAbilitazioniAzioniUD.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBitmapAbilitazioniAzioniUD(String value) {
        this.bitmapAbilitazioniAzioniUD = value;
    }

    /**
     * Recupera il valore della proprietà bitmapAbilitazioniAzioniFilePrimario.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBitmapAbilitazioniAzioniFilePrimario() {
        return bitmapAbilitazioniAzioniFilePrimario;
    }

    /**
     * Imposta il valore della proprietà bitmapAbilitazioniAzioniFilePrimario.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBitmapAbilitazioniAzioniFilePrimario(String value) {
        this.bitmapAbilitazioniAzioniFilePrimario = value;
    }

    /**
     * Gets the value of the ruoloUtenteRispettoUD property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the ruoloUtenteRispettoUD property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRuoloUtenteRispettoUD().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link OggDiTabDiSistemaType }
     * 
     * 
     */
    public List<OggDiTabDiSistemaType> getRuoloUtenteRispettoUD() {
        if (ruoloUtenteRispettoUD == null) {
            ruoloUtenteRispettoUD = new ArrayList<OggDiTabDiSistemaType>();
        }
        return this.ruoloUtenteRispettoUD;
    }


    /**
     * <p>Classe Java per anonymous complex type.
     * 
     * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
     * 
     * <pre>
     * &lt;complexType&gt;
     *   &lt;complexContent&gt;
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *       &lt;sequence&gt;
     *         &lt;element name="LibreriaUD" type="{}LibreriaType" minOccurs="0"/&gt;
     *         &lt;element name="ClassifFascicolo" type="{}ClassifFascicoloEstesoType" maxOccurs="unbounded" minOccurs="0"/&gt;
     *         &lt;element name="Workspace" type="{}OggDiTabDiSistemaType" maxOccurs="unbounded" minOccurs="0"/&gt;
     *       &lt;/sequence&gt;
     *     &lt;/restriction&gt;
     *   &lt;/complexContent&gt;
     * &lt;/complexType&gt;
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "libreriaUD",
        "classifFascicolo",
        "workspace"
    })
    public static class CollocazioneClassificazioneUD {

        @XmlElement(name = "LibreriaUD")
        protected String libreriaUD;
        @XmlElement(name = "ClassifFascicolo")
        protected List<ClassifFascicoloEstesoType> classifFascicolo;
        @XmlElement(name = "Workspace")
        protected List<OggDiTabDiSistemaType> workspace;

        /**
         * Recupera il valore della proprietà libreriaUD.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getLibreriaUD() {
            return libreriaUD;
        }

        /**
         * Imposta il valore della proprietà libreriaUD.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setLibreriaUD(String value) {
            this.libreriaUD = value;
        }

        /**
         * Gets the value of the classifFascicolo property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the classifFascicolo property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getClassifFascicolo().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link ClassifFascicoloEstesoType }
         * 
         * 
         */
        public List<ClassifFascicoloEstesoType> getClassifFascicolo() {
            if (classifFascicolo == null) {
                classifFascicolo = new ArrayList<ClassifFascicoloEstesoType>();
            }
            return this.classifFascicolo;
        }

        /**
         * Gets the value of the workspace property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the workspace property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getWorkspace().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link OggDiTabDiSistemaType }
         * 
         * 
         */
        public List<OggDiTabDiSistemaType> getWorkspace() {
            if (workspace == null) {
                workspace = new ArrayList<OggDiTabDiSistemaType>();
            }
            return this.workspace;
        }

    }


    /**
     * <p>Classe Java per anonymous complex type.
     * 
     * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
     * 
     * <pre>
     * &lt;complexType&gt;
     *   &lt;complexContent&gt;
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *       &lt;sequence&gt;
     *         &lt;element name="DataOraArrivo" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
     *         &lt;element name="DataDocRicevuto" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/&gt;
     *         &lt;element name="EstremiRegistrazioneDocRicevuto" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
     *         &lt;element name="RiferimentiDocRicevuto" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
     *         &lt;element name="MezzoTrasmissione" type="{}OggDiTabDiSistemaType" minOccurs="0"/&gt;
     *         &lt;element name="DataRaccomandata" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/&gt;
     *         &lt;element name="NroRaccomandata" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
     *         &lt;element name="MittenteEsterno" type="{}SoggettoEsternoType" maxOccurs="unbounded" minOccurs="0"/&gt;
     *         &lt;element name="FirmatarioEsterno" type="{}SoggettoEsternoType" maxOccurs="unbounded" minOccurs="0"/&gt;
     *         &lt;element name="IndirizzoProv" type="{}IndirizzoType" minOccurs="0"/&gt;
     *         &lt;element name="IndirizzoEmailProv" type="{}EmailType" minOccurs="0"/&gt;
     *         &lt;element name="UtenteRicezione" type="{}UserType" minOccurs="0"/&gt;
     *         &lt;element name="UffRicezione" type="{}UOType" minOccurs="0"/&gt;
     *         &lt;element name="UtenteCtrlAmmissib" type="{}UserType" minOccurs="0"/&gt;
     *       &lt;/sequence&gt;
     *     &lt;/restriction&gt;
     *   &lt;/complexContent&gt;
     * &lt;/complexType&gt;
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "dataOraArrivo",
        "dataDocRicevuto",
        "estremiRegistrazioneDocRicevuto",
        "riferimentiDocRicevuto",
        "mezzoTrasmissione",
        "dataRaccomandata",
        "nroRaccomandata",
        "mittenteEsterno",
        "firmatarioEsterno",
        "indirizzoProv",
        "indirizzoEmailProv",
        "utenteRicezione",
        "uffRicezione",
        "utenteCtrlAmmissib"
    })
    public static class DatiEntrata {

        @XmlElement(name = "DataOraArrivo")
        @XmlSchemaType(name = "dateTime")
        protected XMLGregorianCalendar dataOraArrivo;
        @XmlElement(name = "DataDocRicevuto")
        @XmlSchemaType(name = "date")
        protected XMLGregorianCalendar dataDocRicevuto;
        @XmlElement(name = "EstremiRegistrazioneDocRicevuto")
        protected String estremiRegistrazioneDocRicevuto;
        @XmlElement(name = "RiferimentiDocRicevuto")
        protected String riferimentiDocRicevuto;
        @XmlElement(name = "MezzoTrasmissione")
        protected OggDiTabDiSistemaType mezzoTrasmissione;
        @XmlElement(name = "DataRaccomandata")
        @XmlSchemaType(name = "date")
        protected XMLGregorianCalendar dataRaccomandata;
        @XmlElement(name = "NroRaccomandata")
        protected String nroRaccomandata;
        @XmlElement(name = "MittenteEsterno")
        protected List<SoggettoEsternoType> mittenteEsterno;
        @XmlElement(name = "FirmatarioEsterno")
        protected List<SoggettoEsternoType> firmatarioEsterno;
        @XmlElement(name = "IndirizzoProv")
        protected IndirizzoType indirizzoProv;
        @XmlElement(name = "IndirizzoEmailProv")
        protected EmailType indirizzoEmailProv;
        @XmlElement(name = "UtenteRicezione")
        protected UserType utenteRicezione;
        @XmlElement(name = "UffRicezione")
        protected UOType uffRicezione;
        @XmlElement(name = "UtenteCtrlAmmissib")
        protected UserType utenteCtrlAmmissib;

        /**
         * Recupera il valore della proprietà dataOraArrivo.
         * 
         * @return
         *     possible object is
         *     {@link XMLGregorianCalendar }
         *     
         */
        public XMLGregorianCalendar getDataOraArrivo() {
            return dataOraArrivo;
        }

        /**
         * Imposta il valore della proprietà dataOraArrivo.
         * 
         * @param value
         *     allowed object is
         *     {@link XMLGregorianCalendar }
         *     
         */
        public void setDataOraArrivo(XMLGregorianCalendar value) {
            this.dataOraArrivo = value;
        }

        /**
         * Recupera il valore della proprietà dataDocRicevuto.
         * 
         * @return
         *     possible object is
         *     {@link XMLGregorianCalendar }
         *     
         */
        public XMLGregorianCalendar getDataDocRicevuto() {
            return dataDocRicevuto;
        }

        /**
         * Imposta il valore della proprietà dataDocRicevuto.
         * 
         * @param value
         *     allowed object is
         *     {@link XMLGregorianCalendar }
         *     
         */
        public void setDataDocRicevuto(XMLGregorianCalendar value) {
            this.dataDocRicevuto = value;
        }

        /**
         * Recupera il valore della proprietà estremiRegistrazioneDocRicevuto.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getEstremiRegistrazioneDocRicevuto() {
            return estremiRegistrazioneDocRicevuto;
        }

        /**
         * Imposta il valore della proprietà estremiRegistrazioneDocRicevuto.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setEstremiRegistrazioneDocRicevuto(String value) {
            this.estremiRegistrazioneDocRicevuto = value;
        }

        /**
         * Recupera il valore della proprietà riferimentiDocRicevuto.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getRiferimentiDocRicevuto() {
            return riferimentiDocRicevuto;
        }

        /**
         * Imposta il valore della proprietà riferimentiDocRicevuto.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setRiferimentiDocRicevuto(String value) {
            this.riferimentiDocRicevuto = value;
        }

        /**
         * Recupera il valore della proprietà mezzoTrasmissione.
         * 
         * @return
         *     possible object is
         *     {@link OggDiTabDiSistemaType }
         *     
         */
        public OggDiTabDiSistemaType getMezzoTrasmissione() {
            return mezzoTrasmissione;
        }

        /**
         * Imposta il valore della proprietà mezzoTrasmissione.
         * 
         * @param value
         *     allowed object is
         *     {@link OggDiTabDiSistemaType }
         *     
         */
        public void setMezzoTrasmissione(OggDiTabDiSistemaType value) {
            this.mezzoTrasmissione = value;
        }

        /**
         * Recupera il valore della proprietà dataRaccomandata.
         * 
         * @return
         *     possible object is
         *     {@link XMLGregorianCalendar }
         *     
         */
        public XMLGregorianCalendar getDataRaccomandata() {
            return dataRaccomandata;
        }

        /**
         * Imposta il valore della proprietà dataRaccomandata.
         * 
         * @param value
         *     allowed object is
         *     {@link XMLGregorianCalendar }
         *     
         */
        public void setDataRaccomandata(XMLGregorianCalendar value) {
            this.dataRaccomandata = value;
        }

        /**
         * Recupera il valore della proprietà nroRaccomandata.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getNroRaccomandata() {
            return nroRaccomandata;
        }

        /**
         * Imposta il valore della proprietà nroRaccomandata.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setNroRaccomandata(String value) {
            this.nroRaccomandata = value;
        }

        /**
         * Gets the value of the mittenteEsterno property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the mittenteEsterno property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getMittenteEsterno().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link SoggettoEsternoType }
         * 
         * 
         */
        public List<SoggettoEsternoType> getMittenteEsterno() {
            if (mittenteEsterno == null) {
                mittenteEsterno = new ArrayList<SoggettoEsternoType>();
            }
            return this.mittenteEsterno;
        }

        /**
         * Gets the value of the firmatarioEsterno property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the firmatarioEsterno property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getFirmatarioEsterno().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link SoggettoEsternoType }
         * 
         * 
         */
        public List<SoggettoEsternoType> getFirmatarioEsterno() {
            if (firmatarioEsterno == null) {
                firmatarioEsterno = new ArrayList<SoggettoEsternoType>();
            }
            return this.firmatarioEsterno;
        }

        /**
         * Recupera il valore della proprietà indirizzoProv.
         * 
         * @return
         *     possible object is
         *     {@link IndirizzoType }
         *     
         */
        public IndirizzoType getIndirizzoProv() {
            return indirizzoProv;
        }

        /**
         * Imposta il valore della proprietà indirizzoProv.
         * 
         * @param value
         *     allowed object is
         *     {@link IndirizzoType }
         *     
         */
        public void setIndirizzoProv(IndirizzoType value) {
            this.indirizzoProv = value;
        }

        /**
         * Recupera il valore della proprietà indirizzoEmailProv.
         * 
         * @return
         *     possible object is
         *     {@link EmailType }
         *     
         */
        public EmailType getIndirizzoEmailProv() {
            return indirizzoEmailProv;
        }

        /**
         * Imposta il valore della proprietà indirizzoEmailProv.
         * 
         * @param value
         *     allowed object is
         *     {@link EmailType }
         *     
         */
        public void setIndirizzoEmailProv(EmailType value) {
            this.indirizzoEmailProv = value;
        }

        /**
         * Recupera il valore della proprietà utenteRicezione.
         * 
         * @return
         *     possible object is
         *     {@link UserType }
         *     
         */
        public UserType getUtenteRicezione() {
            return utenteRicezione;
        }

        /**
         * Imposta il valore della proprietà utenteRicezione.
         * 
         * @param value
         *     allowed object is
         *     {@link UserType }
         *     
         */
        public void setUtenteRicezione(UserType value) {
            this.utenteRicezione = value;
        }

        /**
         * Recupera il valore della proprietà uffRicezione.
         * 
         * @return
         *     possible object is
         *     {@link UOType }
         *     
         */
        public UOType getUffRicezione() {
            return uffRicezione;
        }

        /**
         * Imposta il valore della proprietà uffRicezione.
         * 
         * @param value
         *     allowed object is
         *     {@link UOType }
         *     
         */
        public void setUffRicezione(UOType value) {
            this.uffRicezione = value;
        }

        /**
         * Recupera il valore della proprietà utenteCtrlAmmissib.
         * 
         * @return
         *     possible object is
         *     {@link UserType }
         *     
         */
        public UserType getUtenteCtrlAmmissib() {
            return utenteCtrlAmmissib;
        }

        /**
         * Imposta il valore della proprietà utenteCtrlAmmissib.
         * 
         * @param value
         *     allowed object is
         *     {@link UserType }
         *     
         */
        public void setUtenteCtrlAmmissib(UserType value) {
            this.utenteCtrlAmmissib = value;
        }

    }


    /**
     * <p>Classe Java per anonymous complex type.
     * 
     * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
     * 
     * <pre>
     * &lt;complexType&gt;
     *   &lt;complexContent&gt;
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *       &lt;sequence&gt;
     *         &lt;element name="DataStesura" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/&gt;
     *         &lt;element name="LuogoStesura" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
     *         &lt;element name="Estensore" type="{}UserType" maxOccurs="unbounded" minOccurs="0"/&gt;
     *         &lt;element name="UffProduttore" type="{}UOType" maxOccurs="unbounded" minOccurs="0"/&gt;
     *       &lt;/sequence&gt;
     *     &lt;/restriction&gt;
     *   &lt;/complexContent&gt;
     * &lt;/complexType&gt;
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "dataStesura",
        "luogoStesura",
        "estensore",
        "uffProduttore"
    })
    public static class DatiProduzione {

        @XmlElement(name = "DataStesura")
        @XmlSchemaType(name = "date")
        protected XMLGregorianCalendar dataStesura;
        @XmlElement(name = "LuogoStesura")
        protected String luogoStesura;
        @XmlElement(name = "Estensore")
        protected List<UserType> estensore;
        @XmlElement(name = "UffProduttore")
        protected List<UOType> uffProduttore;

        /**
         * Recupera il valore della proprietà dataStesura.
         * 
         * @return
         *     possible object is
         *     {@link XMLGregorianCalendar }
         *     
         */
        public XMLGregorianCalendar getDataStesura() {
            return dataStesura;
        }

        /**
         * Imposta il valore della proprietà dataStesura.
         * 
         * @param value
         *     allowed object is
         *     {@link XMLGregorianCalendar }
         *     
         */
        public void setDataStesura(XMLGregorianCalendar value) {
            this.dataStesura = value;
        }

        /**
         * Recupera il valore della proprietà luogoStesura.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getLuogoStesura() {
            return luogoStesura;
        }

        /**
         * Imposta il valore della proprietà luogoStesura.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setLuogoStesura(String value) {
            this.luogoStesura = value;
        }

        /**
         * Gets the value of the estensore property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the estensore property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getEstensore().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link UserType }
         * 
         * 
         */
        public List<UserType> getEstensore() {
            if (estensore == null) {
                estensore = new ArrayList<UserType>();
            }
            return this.estensore;
        }

        /**
         * Gets the value of the uffProduttore property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the uffProduttore property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getUffProduttore().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link UOType }
         * 
         * 
         */
        public List<UOType> getUffProduttore() {
            if (uffProduttore == null) {
                uffProduttore = new ArrayList<UOType>();
            }
            return this.uffProduttore;
        }

    }


    /**
     * <p>Classe Java per anonymous complex type.
     * 
     * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
     * 
     * <pre>
     * &lt;complexType&gt;
     *   &lt;complexContent&gt;
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *       &lt;sequence&gt;
     *         &lt;element name="DataOraSped" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
     *         &lt;element name="MezzoTrasmissione" type="{}OggDiTabDiSistemaType" minOccurs="0"/&gt;
     *         &lt;element name="DataRaccomandata" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/&gt;
     *         &lt;element name="NroRaccomandata" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
     *         &lt;element name="DestinatarioEsterno" type="{}DestinatarioEsternoType" maxOccurs="unbounded" minOccurs="0"/&gt;
     *         &lt;element name="UtenteSpedizione" type="{}UserType" minOccurs="0"/&gt;
     *         &lt;element name="UffSpedizione" type="{}UOType" minOccurs="0"/&gt;
     *       &lt;/sequence&gt;
     *     &lt;/restriction&gt;
     *   &lt;/complexContent&gt;
     * &lt;/complexType&gt;
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "dataOraSped",
        "mezzoTrasmissione",
        "dataRaccomandata",
        "nroRaccomandata",
        "destinatarioEsterno",
        "utenteSpedizione",
        "uffSpedizione"
    })
    public static class DatiUscita {

        @XmlElement(name = "DataOraSped")
        @XmlSchemaType(name = "dateTime")
        protected XMLGregorianCalendar dataOraSped;
        @XmlElement(name = "MezzoTrasmissione")
        protected OggDiTabDiSistemaType mezzoTrasmissione;
        @XmlElement(name = "DataRaccomandata")
        @XmlSchemaType(name = "date")
        protected XMLGregorianCalendar dataRaccomandata;
        @XmlElement(name = "NroRaccomandata")
        protected String nroRaccomandata;
        @XmlElement(name = "DestinatarioEsterno")
        protected List<DestinatarioEsternoType> destinatarioEsterno;
        @XmlElement(name = "UtenteSpedizione")
        protected UserType utenteSpedizione;
        @XmlElement(name = "UffSpedizione")
        protected UOType uffSpedizione;

        /**
         * Recupera il valore della proprietà dataOraSped.
         * 
         * @return
         *     possible object is
         *     {@link XMLGregorianCalendar }
         *     
         */
        public XMLGregorianCalendar getDataOraSped() {
            return dataOraSped;
        }

        /**
         * Imposta il valore della proprietà dataOraSped.
         * 
         * @param value
         *     allowed object is
         *     {@link XMLGregorianCalendar }
         *     
         */
        public void setDataOraSped(XMLGregorianCalendar value) {
            this.dataOraSped = value;
        }

        /**
         * Recupera il valore della proprietà mezzoTrasmissione.
         * 
         * @return
         *     possible object is
         *     {@link OggDiTabDiSistemaType }
         *     
         */
        public OggDiTabDiSistemaType getMezzoTrasmissione() {
            return mezzoTrasmissione;
        }

        /**
         * Imposta il valore della proprietà mezzoTrasmissione.
         * 
         * @param value
         *     allowed object is
         *     {@link OggDiTabDiSistemaType }
         *     
         */
        public void setMezzoTrasmissione(OggDiTabDiSistemaType value) {
            this.mezzoTrasmissione = value;
        }

        /**
         * Recupera il valore della proprietà dataRaccomandata.
         * 
         * @return
         *     possible object is
         *     {@link XMLGregorianCalendar }
         *     
         */
        public XMLGregorianCalendar getDataRaccomandata() {
            return dataRaccomandata;
        }

        /**
         * Imposta il valore della proprietà dataRaccomandata.
         * 
         * @param value
         *     allowed object is
         *     {@link XMLGregorianCalendar }
         *     
         */
        public void setDataRaccomandata(XMLGregorianCalendar value) {
            this.dataRaccomandata = value;
        }

        /**
         * Recupera il valore della proprietà nroRaccomandata.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getNroRaccomandata() {
            return nroRaccomandata;
        }

        /**
         * Imposta il valore della proprietà nroRaccomandata.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setNroRaccomandata(String value) {
            this.nroRaccomandata = value;
        }

        /**
         * Gets the value of the destinatarioEsterno property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the destinatarioEsterno property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getDestinatarioEsterno().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link DestinatarioEsternoType }
         * 
         * 
         */
        public List<DestinatarioEsternoType> getDestinatarioEsterno() {
            if (destinatarioEsterno == null) {
                destinatarioEsterno = new ArrayList<DestinatarioEsternoType>();
            }
            return this.destinatarioEsterno;
        }

        /**
         * Recupera il valore della proprietà utenteSpedizione.
         * 
         * @return
         *     possible object is
         *     {@link UserType }
         *     
         */
        public UserType getUtenteSpedizione() {
            return utenteSpedizione;
        }

        /**
         * Imposta il valore della proprietà utenteSpedizione.
         * 
         * @param value
         *     allowed object is
         *     {@link UserType }
         *     
         */
        public void setUtenteSpedizione(UserType value) {
            this.utenteSpedizione = value;
        }

        /**
         * Recupera il valore della proprietà uffSpedizione.
         * 
         * @return
         *     possible object is
         *     {@link UOType }
         *     
         */
        public UOType getUffSpedizione() {
            return uffSpedizione;
        }

        /**
         * Imposta il valore della proprietà uffSpedizione.
         * 
         * @param value
         *     allowed object is
         *     {@link UOType }
         *     
         */
        public void setUffSpedizione(UOType value) {
            this.uffSpedizione = value;
        }

    }


    /**
     * <p>Classe Java per anonymous complex type.
     * 
     * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
     * 
     * <pre>
     * &lt;complexType&gt;
     *   &lt;simpleContent&gt;
     *     &lt;extension base="&lt;&gt;NomeUDType"&gt;
     *       &lt;attribute name="DifferenteDaAttuale" type="{}FlagSiNoType" /&gt;
     *       &lt;attribute name="ModificatoAlTsRich" type="{}FlagSiNoType" /&gt;
     *     &lt;/extension&gt;
     *   &lt;/simpleContent&gt;
     * &lt;/complexType&gt;
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "value"
    })
    public static class NomeUD {

        @XmlValue
        protected String value;
        @XmlAttribute(name = "DifferenteDaAttuale")
        protected String differenteDaAttuale;
        @XmlAttribute(name = "ModificatoAlTsRich")
        protected String modificatoAlTsRich;

        /**
         * Recupera il valore della proprietà value.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getValue() {
            return value;
        }

        /**
         * Imposta il valore della proprietà value.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setValue(String value) {
            this.value = value;
        }

        /**
         * Recupera il valore della proprietà differenteDaAttuale.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getDifferenteDaAttuale() {
            return differenteDaAttuale;
        }

        /**
         * Imposta il valore della proprietà differenteDaAttuale.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setDifferenteDaAttuale(String value) {
            this.differenteDaAttuale = value;
        }

        /**
         * Recupera il valore della proprietà modificatoAlTsRich.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getModificatoAlTsRich() {
            return modificatoAlTsRich;
        }

        /**
         * Imposta il valore della proprietà modificatoAlTsRich.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setModificatoAlTsRich(String value) {
            this.modificatoAlTsRich = value;
        }

    }


    /**
     * <p>Classe Java per anonymous complex type.
     * 
     * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
     * 
     * <pre>
     * &lt;complexType&gt;
     *   &lt;simpleContent&gt;
     *     &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema&gt;string"&gt;
     *       &lt;attribute name="DifferenteDaAttuale" type="{}FlagSiNoType" /&gt;
     *       &lt;attribute name="ModificatoAlTsRich" type="{}FlagSiNoType" /&gt;
     *     &lt;/extension&gt;
     *   &lt;/simpleContent&gt;
     * &lt;/complexType&gt;
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "value"
    })
    public static class NoteUD {

        @XmlValue
        protected String value;
        @XmlAttribute(name = "DifferenteDaAttuale")
        protected String differenteDaAttuale;
        @XmlAttribute(name = "ModificatoAlTsRich")
        protected String modificatoAlTsRich;

        /**
         * Recupera il valore della proprietà value.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getValue() {
            return value;
        }

        /**
         * Imposta il valore della proprietà value.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setValue(String value) {
            this.value = value;
        }

        /**
         * Recupera il valore della proprietà differenteDaAttuale.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getDifferenteDaAttuale() {
            return differenteDaAttuale;
        }

        /**
         * Imposta il valore della proprietà differenteDaAttuale.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setDifferenteDaAttuale(String value) {
            this.differenteDaAttuale = value;
        }

        /**
         * Recupera il valore della proprietà modificatoAlTsRich.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getModificatoAlTsRich() {
            return modificatoAlTsRich;
        }

        /**
         * Imposta il valore della proprietà modificatoAlTsRich.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setModificatoAlTsRich(String value) {
            this.modificatoAlTsRich = value;
        }

    }


    /**
     * <p>Classe Java per anonymous complex type.
     * 
     * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
     * 
     * <pre>
     * &lt;complexType&gt;
     *   &lt;simpleContent&gt;
     *     &lt;extension base="&lt;&gt;OggettoUDType"&gt;
     *       &lt;attribute name="DifferenteDaAttuale" type="{}FlagSiNoType" /&gt;
     *       &lt;attribute name="ModificatoAlTsRich" type="{}FlagSiNoType" /&gt;
     *     &lt;/extension&gt;
     *   &lt;/simpleContent&gt;
     * &lt;/complexType&gt;
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "value"
    })
    public static class OggettoUD {

        @XmlValue
        protected String value;
        @XmlAttribute(name = "DifferenteDaAttuale")
        protected String differenteDaAttuale;
        @XmlAttribute(name = "ModificatoAlTsRich")
        protected String modificatoAlTsRich;

        /**
         * Recupera il valore della proprietà value.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getValue() {
            return value;
        }

        /**
         * Imposta il valore della proprietà value.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setValue(String value) {
            this.value = value;
        }

        /**
         * Recupera il valore della proprietà differenteDaAttuale.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getDifferenteDaAttuale() {
            return differenteDaAttuale;
        }

        /**
         * Imposta il valore della proprietà differenteDaAttuale.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setDifferenteDaAttuale(String value) {
            this.differenteDaAttuale = value;
        }

        /**
         * Recupera il valore della proprietà modificatoAlTsRich.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getModificatoAlTsRich() {
            return modificatoAlTsRich;
        }

        /**
         * Imposta il valore della proprietà modificatoAlTsRich.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setModificatoAlTsRich(String value) {
            this.modificatoAlTsRich = value;
        }

    }


    /**
     * <p>Classe Java per anonymous complex type.
     * 
     * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
     * 
     * <pre>
     * &lt;complexType&gt;
     *   &lt;complexContent&gt;
     *     &lt;extension base="{}OggDiTabDiSistemaType"&gt;
     *       &lt;attribute name="DifferenteDaAttuale" type="{}FlagSiNoType" /&gt;
     *       &lt;attribute name="ModificatoAlTsRich" type="{}FlagSiNoType" /&gt;
     *     &lt;/extension&gt;
     *   &lt;/complexContent&gt;
     * &lt;/complexType&gt;
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "")
    public static class Stato
        extends OggDiTabDiSistemaType
    {

        @XmlAttribute(name = "DifferenteDaAttuale")
        protected String differenteDaAttuale;
        @XmlAttribute(name = "ModificatoAlTsRich")
        protected String modificatoAlTsRich;

        /**
         * Recupera il valore della proprietà differenteDaAttuale.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getDifferenteDaAttuale() {
            return differenteDaAttuale;
        }

        /**
         * Imposta il valore della proprietà differenteDaAttuale.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setDifferenteDaAttuale(String value) {
            this.differenteDaAttuale = value;
        }

        /**
         * Recupera il valore della proprietà modificatoAlTsRich.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getModificatoAlTsRich() {
            return modificatoAlTsRich;
        }

        /**
         * Imposta il valore della proprietà modificatoAlTsRich.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setModificatoAlTsRich(String value) {
            this.modificatoAlTsRich = value;
        }

    }


    /**
     * <p>Classe Java per anonymous complex type.
     * 
     * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
     * 
     * <pre>
     * &lt;complexType&gt;
     *   &lt;complexContent&gt;
     *     &lt;extension base="{}OggDiTabDiSistemaType"&gt;
     *       &lt;attribute name="DifferenteDaAttuale" type="{}FlagSiNoType" /&gt;
     *       &lt;attribute name="ModificatoAlTsRich" type="{}FlagSiNoType" /&gt;
     *     &lt;/extension&gt;
     *   &lt;/complexContent&gt;
     * &lt;/complexType&gt;
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "")
    public static class StatoDettaglio
        extends OggDiTabDiSistemaType
    {

        @XmlAttribute(name = "DifferenteDaAttuale")
        protected String differenteDaAttuale;
        @XmlAttribute(name = "ModificatoAlTsRich")
        protected String modificatoAlTsRich;

        /**
         * Recupera il valore della proprietà differenteDaAttuale.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getDifferenteDaAttuale() {
            return differenteDaAttuale;
        }

        /**
         * Imposta il valore della proprietà differenteDaAttuale.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setDifferenteDaAttuale(String value) {
            this.differenteDaAttuale = value;
        }

        /**
         * Recupera il valore della proprietà modificatoAlTsRich.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getModificatoAlTsRich() {
            return modificatoAlTsRich;
        }

        /**
         * Imposta il valore della proprietà modificatoAlTsRich.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setModificatoAlTsRich(String value) {
            this.modificatoAlTsRich = value;
        }

    }

}
