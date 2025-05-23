
package com.oracle.xmlns.apps.per.soaprovider.plsql.cuscsi_soa_giunta_regionale.get_dati_assegnazione;

import java.math.BigDecimal;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.oracle.xmlns.apps.per.soaprovider.plsql.cuscsi_soa_giunta_regionale.get_dati_assegnazione package. 
 * &lt;p&gt;An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _InputParametersPMATRICOLAHR_QNAME = new QName("http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_giunta_regionale/get_dati_assegnazione/", "P_MATRICOLA_HR");
    private final static QName _InputParametersPCODICEFISCALE_QNAME = new QName("http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_giunta_regionale/get_dati_assegnazione/", "P_CODICE_FISCALE");
    private final static QName _InputParametersPDATAESTRAZIONE_QNAME = new QName("http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_giunta_regionale/get_dati_assegnazione/", "P_DATA_ESTRAZIONE");
    private final static QName _OutputParametersOPCODSTRUTTURA_QNAME = new QName("http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_giunta_regionale/get_dati_assegnazione/", "OP_COD_STRUTTURA");
    private final static QName _OutputParametersOPCODREGIONALE_QNAME = new QName("http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_giunta_regionale/get_dati_assegnazione/", "OP_COD_REGIONALE");
    private final static QName _OutputParametersOPNOMEUO_QNAME = new QName("http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_giunta_regionale/get_dati_assegnazione/", "OP_NOME_UO");
    private final static QName _OutputParametersOPCODMANSIONE_QNAME = new QName("http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_giunta_regionale/get_dati_assegnazione/", "OP_COD_MANSIONE");
    private final static QName _OutputParametersOPDESMANSIONE_QNAME = new QName("http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_giunta_regionale/get_dati_assegnazione/", "OP_DES_MANSIONE");
    private final static QName _OutputParametersOPCODPROFILOPROFESS_QNAME = new QName("http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_giunta_regionale/get_dati_assegnazione/", "OP_COD_PROFILO_PROFESS");
    private final static QName _OutputParametersOPDESPROFILOPROFESS_QNAME = new QName("http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_giunta_regionale/get_dati_assegnazione/", "OP_DES_PROFILO_PROFESS");
    private final static QName _OutputParametersOPCODUBICAZIONE_QNAME = new QName("http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_giunta_regionale/get_dati_assegnazione/", "OP_COD_UBICAZIONE");
    private final static QName _OutputParametersOPDESUBICAZIONE_QNAME = new QName("http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_giunta_regionale/get_dati_assegnazione/", "OP_DES_UBICAZIONE");
    private final static QName _OutputParametersOPCODPOSIZIONE_QNAME = new QName("http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_giunta_regionale/get_dati_assegnazione/", "OP_COD_POSIZIONE");
    private final static QName _OutputParametersOPDESPOSIZIONE_QNAME = new QName("http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_giunta_regionale/get_dati_assegnazione/", "OP_DES_POSIZIONE");
    private final static QName _OutputParametersOPCODTIPORAPPLAV_QNAME = new QName("http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_giunta_regionale/get_dati_assegnazione/", "OP_COD_TIPO_RAPP_LAV");
    private final static QName _OutputParametersOPDESTIPORAPPLAV_QNAME = new QName("http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_giunta_regionale/get_dati_assegnazione/", "OP_DES_TIPO_RAPP_LAV");
    private final static QName _OutputParametersOPCODRUOLOESTERNO_QNAME = new QName("http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_giunta_regionale/get_dati_assegnazione/", "OP_COD_RUOLO_ESTERNO");
    private final static QName _OutputParametersOPDESRUOLOESTERNO_QNAME = new QName("http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_giunta_regionale/get_dati_assegnazione/", "OP_DES_RUOLO_ESTERNO");
    private final static QName _OutputParametersOPCODSTATOASSEGN_QNAME = new QName("http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_giunta_regionale/get_dati_assegnazione/", "OP_COD_STATO_ASSEGN");
    private final static QName _OutputParametersOPDESSTATOASSEGN_QNAME = new QName("http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_giunta_regionale/get_dati_assegnazione/", "OP_DES_STATO_ASSEGN");
    private final static QName _OutputParametersOPFLAGRESPONSABILE_QNAME = new QName("http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_giunta_regionale/get_dati_assegnazione/", "OP_FLAG_RESPONSABILE");
    private final static QName _OutputParametersOPCODCONTRATTO_QNAME = new QName("http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_giunta_regionale/get_dati_assegnazione/", "OP_COD_CONTRATTO");
    private final static QName _OutputParametersOPDESCONTRATTO_QNAME = new QName("http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_giunta_regionale/get_dati_assegnazione/", "OP_DES_CONTRATTO");
    private final static QName _OutputParametersOPDATACONTRATTO_QNAME = new QName("http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_giunta_regionale/get_dati_assegnazione/", "OP_DATA_CONTRATTO");
    private final static QName _OutputParametersOPCODPROFILO_QNAME = new QName("http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_giunta_regionale/get_dati_assegnazione/", "OP_COD_PROFILO");
    private final static QName _OutputParametersOPDESPROFILO_QNAME = new QName("http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_giunta_regionale/get_dati_assegnazione/", "OP_DES_PROFILO");
    private final static QName _OutputParametersOPCODPOSECONOMICA_QNAME = new QName("http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_giunta_regionale/get_dati_assegnazione/", "OP_COD_POS_ECONOMICA");
    private final static QName _OutputParametersOPDESPOSECONOMICA_QNAME = new QName("http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_giunta_regionale/get_dati_assegnazione/", "OP_DES_POS_ECONOMICA");
    private final static QName _OutputParametersOPCODORARIO_QNAME = new QName("http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_giunta_regionale/get_dati_assegnazione/", "OP_COD_ORARIO");
    private final static QName _OutputParametersOPDESORARIO_QNAME = new QName("http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_giunta_regionale/get_dati_assegnazione/", "OP_DES_ORARIO");
    private final static QName _OutputParametersOPCODCAPITOLO_QNAME = new QName("http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_giunta_regionale/get_dati_assegnazione/", "OP_COD_CAPITOLO");
    private final static QName _OutputParametersOPDESCAPITOLO_QNAME = new QName("http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_giunta_regionale/get_dati_assegnazione/", "OP_DES_CAPITOLO");
    private final static QName _OutputParametersOPCODRAGGRCED_QNAME = new QName("http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_giunta_regionale/get_dati_assegnazione/", "OP_COD_RAGGR_CED");
    private final static QName _OutputParametersOPDESRAGGRCED_QNAME = new QName("http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_giunta_regionale/get_dati_assegnazione/", "OP_DES_RAGGR_CED");
    private final static QName _OutputParametersOPCODPROGRECO_QNAME = new QName("http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_giunta_regionale/get_dati_assegnazione/", "OP_COD_PROGR_ECO");
    private final static QName _OutputParametersOPDESPROGRECO_QNAME = new QName("http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_giunta_regionale/get_dati_assegnazione/", "OP_DES_PROGR_ECO");
    private final static QName _OutputParametersOPLIBROMATRINAIL_QNAME = new QName("http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_giunta_regionale/get_dati_assegnazione/", "OP_LIBRO_MATR_INAIL");
    private final static QName _OutputParametersOPCODMODASSUNZIONE_QNAME = new QName("http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_giunta_regionale/get_dati_assegnazione/", "OP_COD_MOD_ASSUNZIONE");
    private final static QName _OutputParametersOPDESMODASSUNZIONE_QNAME = new QName("http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_giunta_regionale/get_dati_assegnazione/", "OP_DES_MOD_ASSUNZIONE");
    private final static QName _OutputParametersOPDATASCADCONTR_QNAME = new QName("http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_giunta_regionale/get_dati_assegnazione/", "OP_DATA_SCAD_CONTR");
    private final static QName _OutputParametersOPDATAENTRRUOLO_QNAME = new QName("http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_giunta_regionale/get_dati_assegnazione/", "OP_DATA_ENTR_RUOLO");
    private final static QName _OutputParametersOPPERCSCAVALCO_QNAME = new QName("http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_giunta_regionale/get_dati_assegnazione/", "OP_PERC_SCAVALCO");
    private final static QName _OutputParametersOPPERCPARTTIME_QNAME = new QName("http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_giunta_regionale/get_dati_assegnazione/", "OP_PERC_PART_TIME");
    private final static QName _OutputParametersOPPERCPARTTIMEV_QNAME = new QName("http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_giunta_regionale/get_dati_assegnazione/", "OP_PERC_PART_TIME_V");
    private final static QName _OutputParametersOPPERCORARIORID_QNAME = new QName("http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_giunta_regionale/get_dati_assegnazione/", "OP_PERC_ORARIO_RID");
    private final static QName _OutputParametersOPCODREASANNOT_QNAME = new QName("http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_giunta_regionale/get_dati_assegnazione/", "OP_COD_REAS_ANNOT");
    private final static QName _OutputParametersOPDESREASANNOT_QNAME = new QName("http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_giunta_regionale/get_dati_assegnazione/", "OP_DES_REAS_ANNOT");
    private final static QName _OutputParametersOPMSGDATA_QNAME = new QName("http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_giunta_regionale/get_dati_assegnazione/", "OP_MSG_DATA");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.oracle.xmlns.apps.per.soaprovider.plsql.cuscsi_soa_giunta_regionale.get_dati_assegnazione
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link InputParameters }
     * 
     */
    public InputParameters createInputParameters() {
        return new InputParameters();
    }

    /**
     * Create an instance of {@link OutputParameters }
     * 
     */
    public OutputParameters createOutputParameters() {
        return new OutputParameters();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_giunta_regionale/get_dati_assegnazione/", name = "P_MATRICOLA_HR", scope = InputParameters.class)
    public JAXBElement<String> createInputParametersPMATRICOLAHR(String value) {
        return new JAXBElement<String>(_InputParametersPMATRICOLAHR_QNAME, String.class, InputParameters.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_giunta_regionale/get_dati_assegnazione/", name = "P_CODICE_FISCALE", scope = InputParameters.class)
    public JAXBElement<String> createInputParametersPCODICEFISCALE(String value) {
        return new JAXBElement<String>(_InputParametersPCODICEFISCALE_QNAME, String.class, InputParameters.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_giunta_regionale/get_dati_assegnazione/", name = "P_DATA_ESTRAZIONE", scope = InputParameters.class)
    public JAXBElement<String> createInputParametersPDATAESTRAZIONE(String value) {
        return new JAXBElement<String>(_InputParametersPDATAESTRAZIONE_QNAME, String.class, InputParameters.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_giunta_regionale/get_dati_assegnazione/", name = "OP_COD_STRUTTURA", scope = OutputParameters.class)
    public JAXBElement<String> createOutputParametersOPCODSTRUTTURA(String value) {
        return new JAXBElement<String>(_OutputParametersOPCODSTRUTTURA_QNAME, String.class, OutputParameters.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_giunta_regionale/get_dati_assegnazione/", name = "OP_COD_REGIONALE", scope = OutputParameters.class)
    public JAXBElement<String> createOutputParametersOPCODREGIONALE(String value) {
        return new JAXBElement<String>(_OutputParametersOPCODREGIONALE_QNAME, String.class, OutputParameters.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_giunta_regionale/get_dati_assegnazione/", name = "OP_NOME_UO", scope = OutputParameters.class)
    public JAXBElement<String> createOutputParametersOPNOMEUO(String value) {
        return new JAXBElement<String>(_OutputParametersOPNOMEUO_QNAME, String.class, OutputParameters.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_giunta_regionale/get_dati_assegnazione/", name = "OP_COD_MANSIONE", scope = OutputParameters.class)
    public JAXBElement<String> createOutputParametersOPCODMANSIONE(String value) {
        return new JAXBElement<String>(_OutputParametersOPCODMANSIONE_QNAME, String.class, OutputParameters.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_giunta_regionale/get_dati_assegnazione/", name = "OP_DES_MANSIONE", scope = OutputParameters.class)
    public JAXBElement<String> createOutputParametersOPDESMANSIONE(String value) {
        return new JAXBElement<String>(_OutputParametersOPDESMANSIONE_QNAME, String.class, OutputParameters.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_giunta_regionale/get_dati_assegnazione/", name = "OP_COD_PROFILO_PROFESS", scope = OutputParameters.class)
    public JAXBElement<String> createOutputParametersOPCODPROFILOPROFESS(String value) {
        return new JAXBElement<String>(_OutputParametersOPCODPROFILOPROFESS_QNAME, String.class, OutputParameters.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_giunta_regionale/get_dati_assegnazione/", name = "OP_DES_PROFILO_PROFESS", scope = OutputParameters.class)
    public JAXBElement<String> createOutputParametersOPDESPROFILOPROFESS(String value) {
        return new JAXBElement<String>(_OutputParametersOPDESPROFILOPROFESS_QNAME, String.class, OutputParameters.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_giunta_regionale/get_dati_assegnazione/", name = "OP_COD_UBICAZIONE", scope = OutputParameters.class)
    public JAXBElement<String> createOutputParametersOPCODUBICAZIONE(String value) {
        return new JAXBElement<String>(_OutputParametersOPCODUBICAZIONE_QNAME, String.class, OutputParameters.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_giunta_regionale/get_dati_assegnazione/", name = "OP_DES_UBICAZIONE", scope = OutputParameters.class)
    public JAXBElement<String> createOutputParametersOPDESUBICAZIONE(String value) {
        return new JAXBElement<String>(_OutputParametersOPDESUBICAZIONE_QNAME, String.class, OutputParameters.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_giunta_regionale/get_dati_assegnazione/", name = "OP_COD_POSIZIONE", scope = OutputParameters.class)
    public JAXBElement<String> createOutputParametersOPCODPOSIZIONE(String value) {
        return new JAXBElement<String>(_OutputParametersOPCODPOSIZIONE_QNAME, String.class, OutputParameters.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_giunta_regionale/get_dati_assegnazione/", name = "OP_DES_POSIZIONE", scope = OutputParameters.class)
    public JAXBElement<String> createOutputParametersOPDESPOSIZIONE(String value) {
        return new JAXBElement<String>(_OutputParametersOPDESPOSIZIONE_QNAME, String.class, OutputParameters.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_giunta_regionale/get_dati_assegnazione/", name = "OP_COD_TIPO_RAPP_LAV", scope = OutputParameters.class)
    public JAXBElement<String> createOutputParametersOPCODTIPORAPPLAV(String value) {
        return new JAXBElement<String>(_OutputParametersOPCODTIPORAPPLAV_QNAME, String.class, OutputParameters.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_giunta_regionale/get_dati_assegnazione/", name = "OP_DES_TIPO_RAPP_LAV", scope = OutputParameters.class)
    public JAXBElement<String> createOutputParametersOPDESTIPORAPPLAV(String value) {
        return new JAXBElement<String>(_OutputParametersOPDESTIPORAPPLAV_QNAME, String.class, OutputParameters.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_giunta_regionale/get_dati_assegnazione/", name = "OP_COD_RUOLO_ESTERNO", scope = OutputParameters.class)
    public JAXBElement<String> createOutputParametersOPCODRUOLOESTERNO(String value) {
        return new JAXBElement<String>(_OutputParametersOPCODRUOLOESTERNO_QNAME, String.class, OutputParameters.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_giunta_regionale/get_dati_assegnazione/", name = "OP_DES_RUOLO_ESTERNO", scope = OutputParameters.class)
    public JAXBElement<String> createOutputParametersOPDESRUOLOESTERNO(String value) {
        return new JAXBElement<String>(_OutputParametersOPDESRUOLOESTERNO_QNAME, String.class, OutputParameters.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_giunta_regionale/get_dati_assegnazione/", name = "OP_COD_STATO_ASSEGN", scope = OutputParameters.class)
    public JAXBElement<String> createOutputParametersOPCODSTATOASSEGN(String value) {
        return new JAXBElement<String>(_OutputParametersOPCODSTATOASSEGN_QNAME, String.class, OutputParameters.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_giunta_regionale/get_dati_assegnazione/", name = "OP_DES_STATO_ASSEGN", scope = OutputParameters.class)
    public JAXBElement<String> createOutputParametersOPDESSTATOASSEGN(String value) {
        return new JAXBElement<String>(_OutputParametersOPDESSTATOASSEGN_QNAME, String.class, OutputParameters.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}
     */
    @XmlElementDecl(namespace = "http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_giunta_regionale/get_dati_assegnazione/", name = "OP_FLAG_RESPONSABILE", scope = OutputParameters.class)
    public JAXBElement<Integer> createOutputParametersOPFLAGRESPONSABILE(Integer value) {
        return new JAXBElement<Integer>(_OutputParametersOPFLAGRESPONSABILE_QNAME, Integer.class, OutputParameters.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_giunta_regionale/get_dati_assegnazione/", name = "OP_COD_CONTRATTO", scope = OutputParameters.class)
    public JAXBElement<String> createOutputParametersOPCODCONTRATTO(String value) {
        return new JAXBElement<String>(_OutputParametersOPCODCONTRATTO_QNAME, String.class, OutputParameters.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_giunta_regionale/get_dati_assegnazione/", name = "OP_DES_CONTRATTO", scope = OutputParameters.class)
    public JAXBElement<String> createOutputParametersOPDESCONTRATTO(String value) {
        return new JAXBElement<String>(_OutputParametersOPDESCONTRATTO_QNAME, String.class, OutputParameters.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_giunta_regionale/get_dati_assegnazione/", name = "OP_DATA_CONTRATTO", scope = OutputParameters.class)
    public JAXBElement<String> createOutputParametersOPDATACONTRATTO(String value) {
        return new JAXBElement<String>(_OutputParametersOPDATACONTRATTO_QNAME, String.class, OutputParameters.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_giunta_regionale/get_dati_assegnazione/", name = "OP_COD_PROFILO", scope = OutputParameters.class)
    public JAXBElement<String> createOutputParametersOPCODPROFILO(String value) {
        return new JAXBElement<String>(_OutputParametersOPCODPROFILO_QNAME, String.class, OutputParameters.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_giunta_regionale/get_dati_assegnazione/", name = "OP_DES_PROFILO", scope = OutputParameters.class)
    public JAXBElement<String> createOutputParametersOPDESPROFILO(String value) {
        return new JAXBElement<String>(_OutputParametersOPDESPROFILO_QNAME, String.class, OutputParameters.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_giunta_regionale/get_dati_assegnazione/", name = "OP_COD_POS_ECONOMICA", scope = OutputParameters.class)
    public JAXBElement<String> createOutputParametersOPCODPOSECONOMICA(String value) {
        return new JAXBElement<String>(_OutputParametersOPCODPOSECONOMICA_QNAME, String.class, OutputParameters.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_giunta_regionale/get_dati_assegnazione/", name = "OP_DES_POS_ECONOMICA", scope = OutputParameters.class)
    public JAXBElement<String> createOutputParametersOPDESPOSECONOMICA(String value) {
        return new JAXBElement<String>(_OutputParametersOPDESPOSECONOMICA_QNAME, String.class, OutputParameters.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_giunta_regionale/get_dati_assegnazione/", name = "OP_COD_ORARIO", scope = OutputParameters.class)
    public JAXBElement<String> createOutputParametersOPCODORARIO(String value) {
        return new JAXBElement<String>(_OutputParametersOPCODORARIO_QNAME, String.class, OutputParameters.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_giunta_regionale/get_dati_assegnazione/", name = "OP_DES_ORARIO", scope = OutputParameters.class)
    public JAXBElement<String> createOutputParametersOPDESORARIO(String value) {
        return new JAXBElement<String>(_OutputParametersOPDESORARIO_QNAME, String.class, OutputParameters.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_giunta_regionale/get_dati_assegnazione/", name = "OP_COD_CAPITOLO", scope = OutputParameters.class)
    public JAXBElement<String> createOutputParametersOPCODCAPITOLO(String value) {
        return new JAXBElement<String>(_OutputParametersOPCODCAPITOLO_QNAME, String.class, OutputParameters.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_giunta_regionale/get_dati_assegnazione/", name = "OP_DES_CAPITOLO", scope = OutputParameters.class)
    public JAXBElement<String> createOutputParametersOPDESCAPITOLO(String value) {
        return new JAXBElement<String>(_OutputParametersOPDESCAPITOLO_QNAME, String.class, OutputParameters.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_giunta_regionale/get_dati_assegnazione/", name = "OP_COD_RAGGR_CED", scope = OutputParameters.class)
    public JAXBElement<String> createOutputParametersOPCODRAGGRCED(String value) {
        return new JAXBElement<String>(_OutputParametersOPCODRAGGRCED_QNAME, String.class, OutputParameters.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_giunta_regionale/get_dati_assegnazione/", name = "OP_DES_RAGGR_CED", scope = OutputParameters.class)
    public JAXBElement<String> createOutputParametersOPDESRAGGRCED(String value) {
        return new JAXBElement<String>(_OutputParametersOPDESRAGGRCED_QNAME, String.class, OutputParameters.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_giunta_regionale/get_dati_assegnazione/", name = "OP_COD_PROGR_ECO", scope = OutputParameters.class)
    public JAXBElement<String> createOutputParametersOPCODPROGRECO(String value) {
        return new JAXBElement<String>(_OutputParametersOPCODPROGRECO_QNAME, String.class, OutputParameters.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_giunta_regionale/get_dati_assegnazione/", name = "OP_DES_PROGR_ECO", scope = OutputParameters.class)
    public JAXBElement<String> createOutputParametersOPDESPROGRECO(String value) {
        return new JAXBElement<String>(_OutputParametersOPDESPROGRECO_QNAME, String.class, OutputParameters.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_giunta_regionale/get_dati_assegnazione/", name = "OP_LIBRO_MATR_INAIL", scope = OutputParameters.class)
    public JAXBElement<String> createOutputParametersOPLIBROMATRINAIL(String value) {
        return new JAXBElement<String>(_OutputParametersOPLIBROMATRINAIL_QNAME, String.class, OutputParameters.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_giunta_regionale/get_dati_assegnazione/", name = "OP_COD_MOD_ASSUNZIONE", scope = OutputParameters.class)
    public JAXBElement<String> createOutputParametersOPCODMODASSUNZIONE(String value) {
        return new JAXBElement<String>(_OutputParametersOPCODMODASSUNZIONE_QNAME, String.class, OutputParameters.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_giunta_regionale/get_dati_assegnazione/", name = "OP_DES_MOD_ASSUNZIONE", scope = OutputParameters.class)
    public JAXBElement<String> createOutputParametersOPDESMODASSUNZIONE(String value) {
        return new JAXBElement<String>(_OutputParametersOPDESMODASSUNZIONE_QNAME, String.class, OutputParameters.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
     */
    @XmlElementDecl(namespace = "http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_giunta_regionale/get_dati_assegnazione/", name = "OP_DATA_SCAD_CONTR", scope = OutputParameters.class)
    public JAXBElement<XMLGregorianCalendar> createOutputParametersOPDATASCADCONTR(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_OutputParametersOPDATASCADCONTR_QNAME, XMLGregorianCalendar.class, OutputParameters.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
     */
    @XmlElementDecl(namespace = "http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_giunta_regionale/get_dati_assegnazione/", name = "OP_DATA_ENTR_RUOLO", scope = OutputParameters.class)
    public JAXBElement<XMLGregorianCalendar> createOutputParametersOPDATAENTRRUOLO(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_OutputParametersOPDATAENTRRUOLO_QNAME, XMLGregorianCalendar.class, OutputParameters.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     */
    @XmlElementDecl(namespace = "http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_giunta_regionale/get_dati_assegnazione/", name = "OP_PERC_SCAVALCO", scope = OutputParameters.class)
    public JAXBElement<BigDecimal> createOutputParametersOPPERCSCAVALCO(BigDecimal value) {
        return new JAXBElement<BigDecimal>(_OutputParametersOPPERCSCAVALCO_QNAME, BigDecimal.class, OutputParameters.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     */
    @XmlElementDecl(namespace = "http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_giunta_regionale/get_dati_assegnazione/", name = "OP_PERC_PART_TIME", scope = OutputParameters.class)
    public JAXBElement<BigDecimal> createOutputParametersOPPERCPARTTIME(BigDecimal value) {
        return new JAXBElement<BigDecimal>(_OutputParametersOPPERCPARTTIME_QNAME, BigDecimal.class, OutputParameters.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     */
    @XmlElementDecl(namespace = "http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_giunta_regionale/get_dati_assegnazione/", name = "OP_PERC_PART_TIME_V", scope = OutputParameters.class)
    public JAXBElement<BigDecimal> createOutputParametersOPPERCPARTTIMEV(BigDecimal value) {
        return new JAXBElement<BigDecimal>(_OutputParametersOPPERCPARTTIMEV_QNAME, BigDecimal.class, OutputParameters.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     */
    @XmlElementDecl(namespace = "http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_giunta_regionale/get_dati_assegnazione/", name = "OP_PERC_ORARIO_RID", scope = OutputParameters.class)
    public JAXBElement<BigDecimal> createOutputParametersOPPERCORARIORID(BigDecimal value) {
        return new JAXBElement<BigDecimal>(_OutputParametersOPPERCORARIORID_QNAME, BigDecimal.class, OutputParameters.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_giunta_regionale/get_dati_assegnazione/", name = "OP_COD_REAS_ANNOT", scope = OutputParameters.class)
    public JAXBElement<String> createOutputParametersOPCODREASANNOT(String value) {
        return new JAXBElement<String>(_OutputParametersOPCODREASANNOT_QNAME, String.class, OutputParameters.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_giunta_regionale/get_dati_assegnazione/", name = "OP_DES_REAS_ANNOT", scope = OutputParameters.class)
    public JAXBElement<String> createOutputParametersOPDESREASANNOT(String value) {
        return new JAXBElement<String>(_OutputParametersOPDESREASANNOT_QNAME, String.class, OutputParameters.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_giunta_regionale/get_dati_assegnazione/", name = "OP_MSG_DATA", scope = OutputParameters.class)
    public JAXBElement<String> createOutputParametersOPMSGDATA(String value) {
        return new JAXBElement<String>(_OutputParametersOPMSGDATA_QNAME, String.class, OutputParameters.class, value);
    }

}
