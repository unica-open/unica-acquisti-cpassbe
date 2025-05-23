
package com.oracle.xmlns.apps.per.soaprovider.plsql.cuscsi_soa_comune_torino.get_dati_assegnazione;

import java.math.BigDecimal;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * &lt;p&gt;Classe Java per anonymous complex type.
 * 
 * &lt;p&gt;Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * &lt;pre&gt;
 * &amp;lt;complexType&amp;gt;
 *   &amp;lt;complexContent&amp;gt;
 *     &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&amp;gt;
 *       &amp;lt;sequence&amp;gt;
 *         &amp;lt;element name="OP_COD_STRUTTURA" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="OP_NOME_UO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="OP_COD_MANSIONE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="OP_DES_MANSIONE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="OP_COD_SERVIZIO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="OP_DES_SERVIZIO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="OP_COD_UBICAZIONE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="OP_DES_UBICAZIONE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="OP_COD_POSIZIONE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="OP_DES_POSIZIONE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="OP_COD_TIPO_RAPP_LAV" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="OP_DES_TIPO_RAPP_LAV" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="OP_COD_PESO_PO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="OP_DES_PESO_PO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="OP_FLAG_RESPONSABILE" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="OP_COD_CONTRATTO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="OP_DES_CONTRATTO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="OP_DATA_CONTRATTO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="OP_COD_PROFILO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="OP_DES_PROFILO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="OP_COD_POS_ECONOMICA" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="OP_DES_POS_ECONOMICA" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="OP_COD_ORARIO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="OP_DES_ORARIO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="OP_COD_PROGR_ECO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="OP_DES_PROGR_ECO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="OP_COD_PROFILO_PROF" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="OP_DES_PROFILO_PROF" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="OP_COD_PROFILO_SPEC" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="OP_DES_PROFILO_SPEC" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="OP_COD_QUAL_FUNZ" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="OP_DES_QUAL_FUNZ" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="OP_COD_RIF_CONTR" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="OP_DES_RIF_CONTR" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="OP_COD_RETRIB_MESE_P" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="OP_COD_RAGGR_DIP" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="OP_DES_RAGGR_DIP" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="OP_LIBRO_MATR_INAIL" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="OP_COD_MOD_ASSUNZIONE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="OP_DES_MOD_ASSUNZIONE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="OP_DATA_SCAD_CONTR" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="OP_DATA_ENTR_RUOLO" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="OP_PERC_SCAVALCO" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="OP_PERC_PART_TIME" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="OP_PERC_PART_TIME_V" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="OP_PERC_ORARIO_RID" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="OP_COD_REAS_ANNOT" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="OP_DES_REAS_ANNOT" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="OP_MSG_DATA" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *       &amp;lt;/sequence&amp;gt;
 *     &amp;lt;/restriction&amp;gt;
 *   &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &lt;/pre&gt;
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "opcodstruttura",
    "opnomeuo",
    "opcodmansione",
    "opdesmansione",
    "opcodservizio",
    "opdesservizio",
    "opcodubicazione",
    "opdesubicazione",
    "opcodposizione",
    "opdesposizione",
    "opcodtiporapplav",
    "opdestiporapplav",
    "opcodpesopo",
    "opdespesopo",
    "opflagresponsabile",
    "opcodcontratto",
    "opdescontratto",
    "opdatacontratto",
    "opcodprofilo",
    "opdesprofilo",
    "opcodposeconomica",
    "opdesposeconomica",
    "opcodorario",
    "opdesorario",
    "opcodprogreco",
    "opdesprogreco",
    "opcodprofiloprof",
    "opdesprofiloprof",
    "opcodprofilospec",
    "opdesprofilospec",
    "opcodqualfunz",
    "opdesqualfunz",
    "opcodrifcontr",
    "opdesrifcontr",
    "opcodretribmesep",
    "opcodraggrdip",
    "opdesraggrdip",
    "oplibromatrinail",
    "opcodmodassunzione",
    "opdesmodassunzione",
    "opdatascadcontr",
    "opdataentrruolo",
    "oppercscavalco",
    "oppercparttime",
    "oppercparttimev",
    "oppercorariorid",
    "opcodreasannot",
    "opdesreasannot",
    "opmsgdata"
})
@XmlRootElement(name = "OutputParameters")
public class OutputParameters {

    @XmlElementRef(name = "OP_COD_STRUTTURA", namespace = "http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_comune_torino/get_dati_assegnazione/", type = JAXBElement.class, required = false)
    protected JAXBElement<String> opcodstruttura;
    @XmlElementRef(name = "OP_NOME_UO", namespace = "http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_comune_torino/get_dati_assegnazione/", type = JAXBElement.class, required = false)
    protected JAXBElement<String> opnomeuo;
    @XmlElementRef(name = "OP_COD_MANSIONE", namespace = "http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_comune_torino/get_dati_assegnazione/", type = JAXBElement.class, required = false)
    protected JAXBElement<String> opcodmansione;
    @XmlElementRef(name = "OP_DES_MANSIONE", namespace = "http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_comune_torino/get_dati_assegnazione/", type = JAXBElement.class, required = false)
    protected JAXBElement<String> opdesmansione;
    @XmlElementRef(name = "OP_COD_SERVIZIO", namespace = "http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_comune_torino/get_dati_assegnazione/", type = JAXBElement.class, required = false)
    protected JAXBElement<String> opcodservizio;
    @XmlElementRef(name = "OP_DES_SERVIZIO", namespace = "http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_comune_torino/get_dati_assegnazione/", type = JAXBElement.class, required = false)
    protected JAXBElement<String> opdesservizio;
    @XmlElementRef(name = "OP_COD_UBICAZIONE", namespace = "http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_comune_torino/get_dati_assegnazione/", type = JAXBElement.class, required = false)
    protected JAXBElement<String> opcodubicazione;
    @XmlElementRef(name = "OP_DES_UBICAZIONE", namespace = "http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_comune_torino/get_dati_assegnazione/", type = JAXBElement.class, required = false)
    protected JAXBElement<String> opdesubicazione;
    @XmlElementRef(name = "OP_COD_POSIZIONE", namespace = "http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_comune_torino/get_dati_assegnazione/", type = JAXBElement.class, required = false)
    protected JAXBElement<String> opcodposizione;
    @XmlElementRef(name = "OP_DES_POSIZIONE", namespace = "http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_comune_torino/get_dati_assegnazione/", type = JAXBElement.class, required = false)
    protected JAXBElement<String> opdesposizione;
    @XmlElementRef(name = "OP_COD_TIPO_RAPP_LAV", namespace = "http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_comune_torino/get_dati_assegnazione/", type = JAXBElement.class, required = false)
    protected JAXBElement<String> opcodtiporapplav;
    @XmlElementRef(name = "OP_DES_TIPO_RAPP_LAV", namespace = "http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_comune_torino/get_dati_assegnazione/", type = JAXBElement.class, required = false)
    protected JAXBElement<String> opdestiporapplav;
    @XmlElementRef(name = "OP_COD_PESO_PO", namespace = "http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_comune_torino/get_dati_assegnazione/", type = JAXBElement.class, required = false)
    protected JAXBElement<String> opcodpesopo;
    @XmlElementRef(name = "OP_DES_PESO_PO", namespace = "http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_comune_torino/get_dati_assegnazione/", type = JAXBElement.class, required = false)
    protected JAXBElement<String> opdespesopo;
    @XmlElementRef(name = "OP_FLAG_RESPONSABILE", namespace = "http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_comune_torino/get_dati_assegnazione/", type = JAXBElement.class, required = false)
    protected JAXBElement<Integer> opflagresponsabile;
    @XmlElementRef(name = "OP_COD_CONTRATTO", namespace = "http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_comune_torino/get_dati_assegnazione/", type = JAXBElement.class, required = false)
    protected JAXBElement<String> opcodcontratto;
    @XmlElementRef(name = "OP_DES_CONTRATTO", namespace = "http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_comune_torino/get_dati_assegnazione/", type = JAXBElement.class, required = false)
    protected JAXBElement<String> opdescontratto;
    @XmlElementRef(name = "OP_DATA_CONTRATTO", namespace = "http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_comune_torino/get_dati_assegnazione/", type = JAXBElement.class, required = false)
    protected JAXBElement<String> opdatacontratto;
    @XmlElementRef(name = "OP_COD_PROFILO", namespace = "http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_comune_torino/get_dati_assegnazione/", type = JAXBElement.class, required = false)
    protected JAXBElement<String> opcodprofilo;
    @XmlElementRef(name = "OP_DES_PROFILO", namespace = "http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_comune_torino/get_dati_assegnazione/", type = JAXBElement.class, required = false)
    protected JAXBElement<String> opdesprofilo;
    @XmlElementRef(name = "OP_COD_POS_ECONOMICA", namespace = "http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_comune_torino/get_dati_assegnazione/", type = JAXBElement.class, required = false)
    protected JAXBElement<String> opcodposeconomica;
    @XmlElementRef(name = "OP_DES_POS_ECONOMICA", namespace = "http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_comune_torino/get_dati_assegnazione/", type = JAXBElement.class, required = false)
    protected JAXBElement<String> opdesposeconomica;
    @XmlElementRef(name = "OP_COD_ORARIO", namespace = "http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_comune_torino/get_dati_assegnazione/", type = JAXBElement.class, required = false)
    protected JAXBElement<String> opcodorario;
    @XmlElementRef(name = "OP_DES_ORARIO", namespace = "http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_comune_torino/get_dati_assegnazione/", type = JAXBElement.class, required = false)
    protected JAXBElement<String> opdesorario;
    @XmlElementRef(name = "OP_COD_PROGR_ECO", namespace = "http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_comune_torino/get_dati_assegnazione/", type = JAXBElement.class, required = false)
    protected JAXBElement<String> opcodprogreco;
    @XmlElementRef(name = "OP_DES_PROGR_ECO", namespace = "http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_comune_torino/get_dati_assegnazione/", type = JAXBElement.class, required = false)
    protected JAXBElement<String> opdesprogreco;
    @XmlElementRef(name = "OP_COD_PROFILO_PROF", namespace = "http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_comune_torino/get_dati_assegnazione/", type = JAXBElement.class, required = false)
    protected JAXBElement<String> opcodprofiloprof;
    @XmlElementRef(name = "OP_DES_PROFILO_PROF", namespace = "http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_comune_torino/get_dati_assegnazione/", type = JAXBElement.class, required = false)
    protected JAXBElement<String> opdesprofiloprof;
    @XmlElementRef(name = "OP_COD_PROFILO_SPEC", namespace = "http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_comune_torino/get_dati_assegnazione/", type = JAXBElement.class, required = false)
    protected JAXBElement<String> opcodprofilospec;
    @XmlElementRef(name = "OP_DES_PROFILO_SPEC", namespace = "http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_comune_torino/get_dati_assegnazione/", type = JAXBElement.class, required = false)
    protected JAXBElement<String> opdesprofilospec;
    @XmlElementRef(name = "OP_COD_QUAL_FUNZ", namespace = "http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_comune_torino/get_dati_assegnazione/", type = JAXBElement.class, required = false)
    protected JAXBElement<String> opcodqualfunz;
    @XmlElementRef(name = "OP_DES_QUAL_FUNZ", namespace = "http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_comune_torino/get_dati_assegnazione/", type = JAXBElement.class, required = false)
    protected JAXBElement<String> opdesqualfunz;
    @XmlElementRef(name = "OP_COD_RIF_CONTR", namespace = "http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_comune_torino/get_dati_assegnazione/", type = JAXBElement.class, required = false)
    protected JAXBElement<String> opcodrifcontr;
    @XmlElementRef(name = "OP_DES_RIF_CONTR", namespace = "http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_comune_torino/get_dati_assegnazione/", type = JAXBElement.class, required = false)
    protected JAXBElement<String> opdesrifcontr;
    @XmlElementRef(name = "OP_COD_RETRIB_MESE_P", namespace = "http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_comune_torino/get_dati_assegnazione/", type = JAXBElement.class, required = false)
    protected JAXBElement<String> opcodretribmesep;
    @XmlElementRef(name = "OP_COD_RAGGR_DIP", namespace = "http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_comune_torino/get_dati_assegnazione/", type = JAXBElement.class, required = false)
    protected JAXBElement<String> opcodraggrdip;
    @XmlElementRef(name = "OP_DES_RAGGR_DIP", namespace = "http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_comune_torino/get_dati_assegnazione/", type = JAXBElement.class, required = false)
    protected JAXBElement<String> opdesraggrdip;
    @XmlElementRef(name = "OP_LIBRO_MATR_INAIL", namespace = "http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_comune_torino/get_dati_assegnazione/", type = JAXBElement.class, required = false)
    protected JAXBElement<String> oplibromatrinail;
    @XmlElementRef(name = "OP_COD_MOD_ASSUNZIONE", namespace = "http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_comune_torino/get_dati_assegnazione/", type = JAXBElement.class, required = false)
    protected JAXBElement<String> opcodmodassunzione;
    @XmlElementRef(name = "OP_DES_MOD_ASSUNZIONE", namespace = "http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_comune_torino/get_dati_assegnazione/", type = JAXBElement.class, required = false)
    protected JAXBElement<String> opdesmodassunzione;
    @XmlElementRef(name = "OP_DATA_SCAD_CONTR", namespace = "http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_comune_torino/get_dati_assegnazione/", type = JAXBElement.class, required = false)
    protected JAXBElement<XMLGregorianCalendar> opdatascadcontr;
    @XmlElementRef(name = "OP_DATA_ENTR_RUOLO", namespace = "http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_comune_torino/get_dati_assegnazione/", type = JAXBElement.class, required = false)
    protected JAXBElement<XMLGregorianCalendar> opdataentrruolo;
    @XmlElementRef(name = "OP_PERC_SCAVALCO", namespace = "http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_comune_torino/get_dati_assegnazione/", type = JAXBElement.class, required = false)
    protected JAXBElement<BigDecimal> oppercscavalco;
    @XmlElementRef(name = "OP_PERC_PART_TIME", namespace = "http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_comune_torino/get_dati_assegnazione/", type = JAXBElement.class, required = false)
    protected JAXBElement<BigDecimal> oppercparttime;
    @XmlElementRef(name = "OP_PERC_PART_TIME_V", namespace = "http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_comune_torino/get_dati_assegnazione/", type = JAXBElement.class, required = false)
    protected JAXBElement<BigDecimal> oppercparttimev;
    @XmlElementRef(name = "OP_PERC_ORARIO_RID", namespace = "http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_comune_torino/get_dati_assegnazione/", type = JAXBElement.class, required = false)
    protected JAXBElement<BigDecimal> oppercorariorid;
    @XmlElementRef(name = "OP_COD_REAS_ANNOT", namespace = "http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_comune_torino/get_dati_assegnazione/", type = JAXBElement.class, required = false)
    protected JAXBElement<String> opcodreasannot;
    @XmlElementRef(name = "OP_DES_REAS_ANNOT", namespace = "http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_comune_torino/get_dati_assegnazione/", type = JAXBElement.class, required = false)
    protected JAXBElement<String> opdesreasannot;
    @XmlElementRef(name = "OP_MSG_DATA", namespace = "http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_comune_torino/get_dati_assegnazione/", type = JAXBElement.class, required = false)
    protected JAXBElement<String> opmsgdata;

    /**
     * Recupera il valore della proprietà opcodstruttura.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getOPCODSTRUTTURA() {
        return opcodstruttura;
    }

    /**
     * Imposta il valore della proprietà opcodstruttura.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setOPCODSTRUTTURA(JAXBElement<String> value) {
        this.opcodstruttura = value;
    }

    /**
     * Recupera il valore della proprietà opnomeuo.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getOPNOMEUO() {
        return opnomeuo;
    }

    /**
     * Imposta il valore della proprietà opnomeuo.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setOPNOMEUO(JAXBElement<String> value) {
        this.opnomeuo = value;
    }

    /**
     * Recupera il valore della proprietà opcodmansione.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getOPCODMANSIONE() {
        return opcodmansione;
    }

    /**
     * Imposta il valore della proprietà opcodmansione.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setOPCODMANSIONE(JAXBElement<String> value) {
        this.opcodmansione = value;
    }

    /**
     * Recupera il valore della proprietà opdesmansione.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getOPDESMANSIONE() {
        return opdesmansione;
    }

    /**
     * Imposta il valore della proprietà opdesmansione.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setOPDESMANSIONE(JAXBElement<String> value) {
        this.opdesmansione = value;
    }

    /**
     * Recupera il valore della proprietà opcodservizio.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getOPCODSERVIZIO() {
        return opcodservizio;
    }

    /**
     * Imposta il valore della proprietà opcodservizio.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setOPCODSERVIZIO(JAXBElement<String> value) {
        this.opcodservizio = value;
    }

    /**
     * Recupera il valore della proprietà opdesservizio.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getOPDESSERVIZIO() {
        return opdesservizio;
    }

    /**
     * Imposta il valore della proprietà opdesservizio.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setOPDESSERVIZIO(JAXBElement<String> value) {
        this.opdesservizio = value;
    }

    /**
     * Recupera il valore della proprietà opcodubicazione.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getOPCODUBICAZIONE() {
        return opcodubicazione;
    }

    /**
     * Imposta il valore della proprietà opcodubicazione.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setOPCODUBICAZIONE(JAXBElement<String> value) {
        this.opcodubicazione = value;
    }

    /**
     * Recupera il valore della proprietà opdesubicazione.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getOPDESUBICAZIONE() {
        return opdesubicazione;
    }

    /**
     * Imposta il valore della proprietà opdesubicazione.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setOPDESUBICAZIONE(JAXBElement<String> value) {
        this.opdesubicazione = value;
    }

    /**
     * Recupera il valore della proprietà opcodposizione.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getOPCODPOSIZIONE() {
        return opcodposizione;
    }

    /**
     * Imposta il valore della proprietà opcodposizione.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setOPCODPOSIZIONE(JAXBElement<String> value) {
        this.opcodposizione = value;
    }

    /**
     * Recupera il valore della proprietà opdesposizione.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getOPDESPOSIZIONE() {
        return opdesposizione;
    }

    /**
     * Imposta il valore della proprietà opdesposizione.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setOPDESPOSIZIONE(JAXBElement<String> value) {
        this.opdesposizione = value;
    }

    /**
     * Recupera il valore della proprietà opcodtiporapplav.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getOPCODTIPORAPPLAV() {
        return opcodtiporapplav;
    }

    /**
     * Imposta il valore della proprietà opcodtiporapplav.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setOPCODTIPORAPPLAV(JAXBElement<String> value) {
        this.opcodtiporapplav = value;
    }

    /**
     * Recupera il valore della proprietà opdestiporapplav.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getOPDESTIPORAPPLAV() {
        return opdestiporapplav;
    }

    /**
     * Imposta il valore della proprietà opdestiporapplav.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setOPDESTIPORAPPLAV(JAXBElement<String> value) {
        this.opdestiporapplav = value;
    }

    /**
     * Recupera il valore della proprietà opcodpesopo.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getOPCODPESOPO() {
        return opcodpesopo;
    }

    /**
     * Imposta il valore della proprietà opcodpesopo.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setOPCODPESOPO(JAXBElement<String> value) {
        this.opcodpesopo = value;
    }

    /**
     * Recupera il valore della proprietà opdespesopo.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getOPDESPESOPO() {
        return opdespesopo;
    }

    /**
     * Imposta il valore della proprietà opdespesopo.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setOPDESPESOPO(JAXBElement<String> value) {
        this.opdespesopo = value;
    }

    /**
     * Recupera il valore della proprietà opflagresponsabile.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Integer }{@code >}
     *     
     */
    public JAXBElement<Integer> getOPFLAGRESPONSABILE() {
        return opflagresponsabile;
    }

    /**
     * Imposta il valore della proprietà opflagresponsabile.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Integer }{@code >}
     *     
     */
    public void setOPFLAGRESPONSABILE(JAXBElement<Integer> value) {
        this.opflagresponsabile = value;
    }

    /**
     * Recupera il valore della proprietà opcodcontratto.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getOPCODCONTRATTO() {
        return opcodcontratto;
    }

    /**
     * Imposta il valore della proprietà opcodcontratto.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setOPCODCONTRATTO(JAXBElement<String> value) {
        this.opcodcontratto = value;
    }

    /**
     * Recupera il valore della proprietà opdescontratto.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getOPDESCONTRATTO() {
        return opdescontratto;
    }

    /**
     * Imposta il valore della proprietà opdescontratto.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setOPDESCONTRATTO(JAXBElement<String> value) {
        this.opdescontratto = value;
    }

    /**
     * Recupera il valore della proprietà opdatacontratto.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getOPDATACONTRATTO() {
        return opdatacontratto;
    }

    /**
     * Imposta il valore della proprietà opdatacontratto.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setOPDATACONTRATTO(JAXBElement<String> value) {
        this.opdatacontratto = value;
    }

    /**
     * Recupera il valore della proprietà opcodprofilo.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getOPCODPROFILO() {
        return opcodprofilo;
    }

    /**
     * Imposta il valore della proprietà opcodprofilo.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setOPCODPROFILO(JAXBElement<String> value) {
        this.opcodprofilo = value;
    }

    /**
     * Recupera il valore della proprietà opdesprofilo.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getOPDESPROFILO() {
        return opdesprofilo;
    }

    /**
     * Imposta il valore della proprietà opdesprofilo.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setOPDESPROFILO(JAXBElement<String> value) {
        this.opdesprofilo = value;
    }

    /**
     * Recupera il valore della proprietà opcodposeconomica.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getOPCODPOSECONOMICA() {
        return opcodposeconomica;
    }

    /**
     * Imposta il valore della proprietà opcodposeconomica.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setOPCODPOSECONOMICA(JAXBElement<String> value) {
        this.opcodposeconomica = value;
    }

    /**
     * Recupera il valore della proprietà opdesposeconomica.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getOPDESPOSECONOMICA() {
        return opdesposeconomica;
    }

    /**
     * Imposta il valore della proprietà opdesposeconomica.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setOPDESPOSECONOMICA(JAXBElement<String> value) {
        this.opdesposeconomica = value;
    }

    /**
     * Recupera il valore della proprietà opcodorario.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getOPCODORARIO() {
        return opcodorario;
    }

    /**
     * Imposta il valore della proprietà opcodorario.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setOPCODORARIO(JAXBElement<String> value) {
        this.opcodorario = value;
    }

    /**
     * Recupera il valore della proprietà opdesorario.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getOPDESORARIO() {
        return opdesorario;
    }

    /**
     * Imposta il valore della proprietà opdesorario.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setOPDESORARIO(JAXBElement<String> value) {
        this.opdesorario = value;
    }

    /**
     * Recupera il valore della proprietà opcodprogreco.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getOPCODPROGRECO() {
        return opcodprogreco;
    }

    /**
     * Imposta il valore della proprietà opcodprogreco.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setOPCODPROGRECO(JAXBElement<String> value) {
        this.opcodprogreco = value;
    }

    /**
     * Recupera il valore della proprietà opdesprogreco.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getOPDESPROGRECO() {
        return opdesprogreco;
    }

    /**
     * Imposta il valore della proprietà opdesprogreco.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setOPDESPROGRECO(JAXBElement<String> value) {
        this.opdesprogreco = value;
    }

    /**
     * Recupera il valore della proprietà opcodprofiloprof.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getOPCODPROFILOPROF() {
        return opcodprofiloprof;
    }

    /**
     * Imposta il valore della proprietà opcodprofiloprof.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setOPCODPROFILOPROF(JAXBElement<String> value) {
        this.opcodprofiloprof = value;
    }

    /**
     * Recupera il valore della proprietà opdesprofiloprof.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getOPDESPROFILOPROF() {
        return opdesprofiloprof;
    }

    /**
     * Imposta il valore della proprietà opdesprofiloprof.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setOPDESPROFILOPROF(JAXBElement<String> value) {
        this.opdesprofiloprof = value;
    }

    /**
     * Recupera il valore della proprietà opcodprofilospec.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getOPCODPROFILOSPEC() {
        return opcodprofilospec;
    }

    /**
     * Imposta il valore della proprietà opcodprofilospec.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setOPCODPROFILOSPEC(JAXBElement<String> value) {
        this.opcodprofilospec = value;
    }

    /**
     * Recupera il valore della proprietà opdesprofilospec.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getOPDESPROFILOSPEC() {
        return opdesprofilospec;
    }

    /**
     * Imposta il valore della proprietà opdesprofilospec.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setOPDESPROFILOSPEC(JAXBElement<String> value) {
        this.opdesprofilospec = value;
    }

    /**
     * Recupera il valore della proprietà opcodqualfunz.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getOPCODQUALFUNZ() {
        return opcodqualfunz;
    }

    /**
     * Imposta il valore della proprietà opcodqualfunz.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setOPCODQUALFUNZ(JAXBElement<String> value) {
        this.opcodqualfunz = value;
    }

    /**
     * Recupera il valore della proprietà opdesqualfunz.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getOPDESQUALFUNZ() {
        return opdesqualfunz;
    }

    /**
     * Imposta il valore della proprietà opdesqualfunz.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setOPDESQUALFUNZ(JAXBElement<String> value) {
        this.opdesqualfunz = value;
    }

    /**
     * Recupera il valore della proprietà opcodrifcontr.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getOPCODRIFCONTR() {
        return opcodrifcontr;
    }

    /**
     * Imposta il valore della proprietà opcodrifcontr.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setOPCODRIFCONTR(JAXBElement<String> value) {
        this.opcodrifcontr = value;
    }

    /**
     * Recupera il valore della proprietà opdesrifcontr.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getOPDESRIFCONTR() {
        return opdesrifcontr;
    }

    /**
     * Imposta il valore della proprietà opdesrifcontr.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setOPDESRIFCONTR(JAXBElement<String> value) {
        this.opdesrifcontr = value;
    }

    /**
     * Recupera il valore della proprietà opcodretribmesep.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getOPCODRETRIBMESEP() {
        return opcodretribmesep;
    }

    /**
     * Imposta il valore della proprietà opcodretribmesep.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setOPCODRETRIBMESEP(JAXBElement<String> value) {
        this.opcodretribmesep = value;
    }

    /**
     * Recupera il valore della proprietà opcodraggrdip.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getOPCODRAGGRDIP() {
        return opcodraggrdip;
    }

    /**
     * Imposta il valore della proprietà opcodraggrdip.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setOPCODRAGGRDIP(JAXBElement<String> value) {
        this.opcodraggrdip = value;
    }

    /**
     * Recupera il valore della proprietà opdesraggrdip.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getOPDESRAGGRDIP() {
        return opdesraggrdip;
    }

    /**
     * Imposta il valore della proprietà opdesraggrdip.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setOPDESRAGGRDIP(JAXBElement<String> value) {
        this.opdesraggrdip = value;
    }

    /**
     * Recupera il valore della proprietà oplibromatrinail.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getOPLIBROMATRINAIL() {
        return oplibromatrinail;
    }

    /**
     * Imposta il valore della proprietà oplibromatrinail.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setOPLIBROMATRINAIL(JAXBElement<String> value) {
        this.oplibromatrinail = value;
    }

    /**
     * Recupera il valore della proprietà opcodmodassunzione.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getOPCODMODASSUNZIONE() {
        return opcodmodassunzione;
    }

    /**
     * Imposta il valore della proprietà opcodmodassunzione.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setOPCODMODASSUNZIONE(JAXBElement<String> value) {
        this.opcodmodassunzione = value;
    }

    /**
     * Recupera il valore della proprietà opdesmodassunzione.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getOPDESMODASSUNZIONE() {
        return opdesmodassunzione;
    }

    /**
     * Imposta il valore della proprietà opdesmodassunzione.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setOPDESMODASSUNZIONE(JAXBElement<String> value) {
        this.opdesmodassunzione = value;
    }

    /**
     * Recupera il valore della proprietà opdatascadcontr.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
     *     
     */
    public JAXBElement<XMLGregorianCalendar> getOPDATASCADCONTR() {
        return opdatascadcontr;
    }

    /**
     * Imposta il valore della proprietà opdatascadcontr.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
     *     
     */
    public void setOPDATASCADCONTR(JAXBElement<XMLGregorianCalendar> value) {
        this.opdatascadcontr = value;
    }

    /**
     * Recupera il valore della proprietà opdataentrruolo.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
     *     
     */
    public JAXBElement<XMLGregorianCalendar> getOPDATAENTRRUOLO() {
        return opdataentrruolo;
    }

    /**
     * Imposta il valore della proprietà opdataentrruolo.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
     *     
     */
    public void setOPDATAENTRRUOLO(JAXBElement<XMLGregorianCalendar> value) {
        this.opdataentrruolo = value;
    }

    /**
     * Recupera il valore della proprietà oppercscavalco.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     *     
     */
    public JAXBElement<BigDecimal> getOPPERCSCAVALCO() {
        return oppercscavalco;
    }

    /**
     * Imposta il valore della proprietà oppercscavalco.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     *     
     */
    public void setOPPERCSCAVALCO(JAXBElement<BigDecimal> value) {
        this.oppercscavalco = value;
    }

    /**
     * Recupera il valore della proprietà oppercparttime.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     *     
     */
    public JAXBElement<BigDecimal> getOPPERCPARTTIME() {
        return oppercparttime;
    }

    /**
     * Imposta il valore della proprietà oppercparttime.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     *     
     */
    public void setOPPERCPARTTIME(JAXBElement<BigDecimal> value) {
        this.oppercparttime = value;
    }

    /**
     * Recupera il valore della proprietà oppercparttimev.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     *     
     */
    public JAXBElement<BigDecimal> getOPPERCPARTTIMEV() {
        return oppercparttimev;
    }

    /**
     * Imposta il valore della proprietà oppercparttimev.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     *     
     */
    public void setOPPERCPARTTIMEV(JAXBElement<BigDecimal> value) {
        this.oppercparttimev = value;
    }

    /**
     * Recupera il valore della proprietà oppercorariorid.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     *     
     */
    public JAXBElement<BigDecimal> getOPPERCORARIORID() {
        return oppercorariorid;
    }

    /**
     * Imposta il valore della proprietà oppercorariorid.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     *     
     */
    public void setOPPERCORARIORID(JAXBElement<BigDecimal> value) {
        this.oppercorariorid = value;
    }

    /**
     * Recupera il valore della proprietà opcodreasannot.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getOPCODREASANNOT() {
        return opcodreasannot;
    }

    /**
     * Imposta il valore della proprietà opcodreasannot.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setOPCODREASANNOT(JAXBElement<String> value) {
        this.opcodreasannot = value;
    }

    /**
     * Recupera il valore della proprietà opdesreasannot.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getOPDESREASANNOT() {
        return opdesreasannot;
    }

    /**
     * Imposta il valore della proprietà opdesreasannot.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setOPDESREASANNOT(JAXBElement<String> value) {
        this.opdesreasannot = value;
    }

    /**
     * Recupera il valore della proprietà opmsgdata.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getOPMSGDATA() {
        return opmsgdata;
    }

    /**
     * Imposta il valore della proprietà opmsgdata.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setOPMSGDATA(JAXBElement<String> value) {
        this.opmsgdata = value;
    }

}
