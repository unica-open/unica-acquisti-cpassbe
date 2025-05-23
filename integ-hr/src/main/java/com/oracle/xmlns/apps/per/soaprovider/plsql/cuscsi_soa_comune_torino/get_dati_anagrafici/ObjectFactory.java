
package com.oracle.xmlns.apps.per.soaprovider.plsql.cuscsi_soa_comune_torino.get_dati_anagrafici;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.oracle.xmlns.apps.per.soaprovider.plsql.cuscsi_soa_comune_torino.get_dati_anagrafici package. 
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

    private final static QName _InputParametersPMATRICOLAHR_QNAME = new QName("http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_comune_torino/get_dati_anagrafici/", "P_MATRICOLA_HR");
    private final static QName _InputParametersPCODICEFISCALE_QNAME = new QName("http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_comune_torino/get_dati_anagrafici/", "P_CODICE_FISCALE");
    private final static QName _InputParametersPDATAESTRAZIONE_QNAME = new QName("http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_comune_torino/get_dati_anagrafici/", "P_DATA_ESTRAZIONE");
    private final static QName _OutputParametersOPMATRICOLAHR_QNAME = new QName("http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_comune_torino/get_dati_anagrafici/", "OP_MATRICOLA_HR");
    private final static QName _OutputParametersOPCODICEFISCALE_QNAME = new QName("http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_comune_torino/get_dati_anagrafici/", "OP_CODICE_FISCALE");
    private final static QName _OutputParametersOPCOGNOMENOME_QNAME = new QName("http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_comune_torino/get_dati_anagrafici/", "OP_COGNOME_NOME");
    private final static QName _OutputParametersOPCOGNOME_QNAME = new QName("http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_comune_torino/get_dati_anagrafici/", "OP_COGNOME");
    private final static QName _OutputParametersOPNOME_QNAME = new QName("http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_comune_torino/get_dati_anagrafici/", "OP_NOME");
    private final static QName _OutputParametersOPSESSO_QNAME = new QName("http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_comune_torino/get_dati_anagrafici/", "OP_SESSO");
    private final static QName _OutputParametersOPDATANASCITA_QNAME = new QName("http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_comune_torino/get_dati_anagrafici/", "OP_DATA_NASCITA");
    private final static QName _OutputParametersOPCODNAZIONENASCITA_QNAME = new QName("http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_comune_torino/get_dati_anagrafici/", "OP_COD_NAZIONE_NASCITA");
    private final static QName _OutputParametersOPDESNAZIONENASCITA_QNAME = new QName("http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_comune_torino/get_dati_anagrafici/", "OP_DES_NAZIONE_NASCITA");
    private final static QName _OutputParametersOPCODLUOGONASCITA_QNAME = new QName("http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_comune_torino/get_dati_anagrafici/", "OP_COD_LUOGO_NASCITA");
    private final static QName _OutputParametersOPDESLUOGONASCITA_QNAME = new QName("http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_comune_torino/get_dati_anagrafici/", "OP_DES_LUOGO_NASCITA");
    private final static QName _OutputParametersOPEXCODENTE_QNAME = new QName("http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_comune_torino/get_dati_anagrafici/", "OP_EX_COD_ENTE");
    private final static QName _OutputParametersOPDESEXCODENTE_QNAME = new QName("http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_comune_torino/get_dati_anagrafici/", "OP_DES_EX_COD_ENTE");
    private final static QName _OutputParametersOPUID_QNAME = new QName("http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_comune_torino/get_dati_anagrafici/", "OP_UID");
    private final static QName _OutputParametersOPTIPOPERSONA_QNAME = new QName("http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_comune_torino/get_dati_anagrafici/", "OP_TIPO_PERSONA");
    private final static QName _OutputParametersOPCITTADINANZA_QNAME = new QName("http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_comune_torino/get_dati_anagrafici/", "OP_CITTADINANZA");
    private final static QName _OutputParametersOPDATAPRIMAASSUNZIONE_QNAME = new QName("http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_comune_torino/get_dati_anagrafici/", "OP_DATA_PRIMA_ASSUNZIONE");
    private final static QName _OutputParametersOPDATAULTASSUNZIONE_QNAME = new QName("http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_comune_torino/get_dati_anagrafici/", "OP_DATA_ULT_ASSUNZIONE");
    private final static QName _OutputParametersOPEMAIL_QNAME = new QName("http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_comune_torino/get_dati_anagrafici/", "OP_EMAIL");
    private final static QName _OutputParametersOPMSGDATA_QNAME = new QName("http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_comune_torino/get_dati_anagrafici/", "OP_MSG_DATA");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.oracle.xmlns.apps.per.soaprovider.plsql.cuscsi_soa_comune_torino.get_dati_anagrafici
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
    @XmlElementDecl(namespace = "http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_comune_torino/get_dati_anagrafici/", name = "P_MATRICOLA_HR", scope = InputParameters.class)
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
    @XmlElementDecl(namespace = "http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_comune_torino/get_dati_anagrafici/", name = "P_CODICE_FISCALE", scope = InputParameters.class)
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
    @XmlElementDecl(namespace = "http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_comune_torino/get_dati_anagrafici/", name = "P_DATA_ESTRAZIONE", scope = InputParameters.class)
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
    @XmlElementDecl(namespace = "http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_comune_torino/get_dati_anagrafici/", name = "OP_MATRICOLA_HR", scope = OutputParameters.class)
    public JAXBElement<String> createOutputParametersOPMATRICOLAHR(String value) {
        return new JAXBElement<String>(_OutputParametersOPMATRICOLAHR_QNAME, String.class, OutputParameters.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_comune_torino/get_dati_anagrafici/", name = "OP_CODICE_FISCALE", scope = OutputParameters.class)
    public JAXBElement<String> createOutputParametersOPCODICEFISCALE(String value) {
        return new JAXBElement<String>(_OutputParametersOPCODICEFISCALE_QNAME, String.class, OutputParameters.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_comune_torino/get_dati_anagrafici/", name = "OP_COGNOME_NOME", scope = OutputParameters.class)
    public JAXBElement<String> createOutputParametersOPCOGNOMENOME(String value) {
        return new JAXBElement<String>(_OutputParametersOPCOGNOMENOME_QNAME, String.class, OutputParameters.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_comune_torino/get_dati_anagrafici/", name = "OP_COGNOME", scope = OutputParameters.class)
    public JAXBElement<String> createOutputParametersOPCOGNOME(String value) {
        return new JAXBElement<String>(_OutputParametersOPCOGNOME_QNAME, String.class, OutputParameters.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_comune_torino/get_dati_anagrafici/", name = "OP_NOME", scope = OutputParameters.class)
    public JAXBElement<String> createOutputParametersOPNOME(String value) {
        return new JAXBElement<String>(_OutputParametersOPNOME_QNAME, String.class, OutputParameters.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_comune_torino/get_dati_anagrafici/", name = "OP_SESSO", scope = OutputParameters.class)
    public JAXBElement<String> createOutputParametersOPSESSO(String value) {
        return new JAXBElement<String>(_OutputParametersOPSESSO_QNAME, String.class, OutputParameters.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
     */
    @XmlElementDecl(namespace = "http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_comune_torino/get_dati_anagrafici/", name = "OP_DATA_NASCITA", scope = OutputParameters.class)
    public JAXBElement<XMLGregorianCalendar> createOutputParametersOPDATANASCITA(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_OutputParametersOPDATANASCITA_QNAME, XMLGregorianCalendar.class, OutputParameters.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_comune_torino/get_dati_anagrafici/", name = "OP_COD_NAZIONE_NASCITA", scope = OutputParameters.class)
    public JAXBElement<String> createOutputParametersOPCODNAZIONENASCITA(String value) {
        return new JAXBElement<String>(_OutputParametersOPCODNAZIONENASCITA_QNAME, String.class, OutputParameters.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_comune_torino/get_dati_anagrafici/", name = "OP_DES_NAZIONE_NASCITA", scope = OutputParameters.class)
    public JAXBElement<String> createOutputParametersOPDESNAZIONENASCITA(String value) {
        return new JAXBElement<String>(_OutputParametersOPDESNAZIONENASCITA_QNAME, String.class, OutputParameters.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_comune_torino/get_dati_anagrafici/", name = "OP_COD_LUOGO_NASCITA", scope = OutputParameters.class)
    public JAXBElement<String> createOutputParametersOPCODLUOGONASCITA(String value) {
        return new JAXBElement<String>(_OutputParametersOPCODLUOGONASCITA_QNAME, String.class, OutputParameters.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_comune_torino/get_dati_anagrafici/", name = "OP_DES_LUOGO_NASCITA", scope = OutputParameters.class)
    public JAXBElement<String> createOutputParametersOPDESLUOGONASCITA(String value) {
        return new JAXBElement<String>(_OutputParametersOPDESLUOGONASCITA_QNAME, String.class, OutputParameters.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_comune_torino/get_dati_anagrafici/", name = "OP_EX_COD_ENTE", scope = OutputParameters.class)
    public JAXBElement<String> createOutputParametersOPEXCODENTE(String value) {
        return new JAXBElement<String>(_OutputParametersOPEXCODENTE_QNAME, String.class, OutputParameters.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_comune_torino/get_dati_anagrafici/", name = "OP_DES_EX_COD_ENTE", scope = OutputParameters.class)
    public JAXBElement<String> createOutputParametersOPDESEXCODENTE(String value) {
        return new JAXBElement<String>(_OutputParametersOPDESEXCODENTE_QNAME, String.class, OutputParameters.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_comune_torino/get_dati_anagrafici/", name = "OP_UID", scope = OutputParameters.class)
    public JAXBElement<String> createOutputParametersOPUID(String value) {
        return new JAXBElement<String>(_OutputParametersOPUID_QNAME, String.class, OutputParameters.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_comune_torino/get_dati_anagrafici/", name = "OP_TIPO_PERSONA", scope = OutputParameters.class)
    public JAXBElement<String> createOutputParametersOPTIPOPERSONA(String value) {
        return new JAXBElement<String>(_OutputParametersOPTIPOPERSONA_QNAME, String.class, OutputParameters.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_comune_torino/get_dati_anagrafici/", name = "OP_CITTADINANZA", scope = OutputParameters.class)
    public JAXBElement<String> createOutputParametersOPCITTADINANZA(String value) {
        return new JAXBElement<String>(_OutputParametersOPCITTADINANZA_QNAME, String.class, OutputParameters.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
     */
    @XmlElementDecl(namespace = "http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_comune_torino/get_dati_anagrafici/", name = "OP_DATA_PRIMA_ASSUNZIONE", scope = OutputParameters.class)
    public JAXBElement<XMLGregorianCalendar> createOutputParametersOPDATAPRIMAASSUNZIONE(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_OutputParametersOPDATAPRIMAASSUNZIONE_QNAME, XMLGregorianCalendar.class, OutputParameters.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
     */
    @XmlElementDecl(namespace = "http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_comune_torino/get_dati_anagrafici/", name = "OP_DATA_ULT_ASSUNZIONE", scope = OutputParameters.class)
    public JAXBElement<XMLGregorianCalendar> createOutputParametersOPDATAULTASSUNZIONE(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_OutputParametersOPDATAULTASSUNZIONE_QNAME, XMLGregorianCalendar.class, OutputParameters.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_comune_torino/get_dati_anagrafici/", name = "OP_EMAIL", scope = OutputParameters.class)
    public JAXBElement<String> createOutputParametersOPEMAIL(String value) {
        return new JAXBElement<String>(_OutputParametersOPEMAIL_QNAME, String.class, OutputParameters.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_comune_torino/get_dati_anagrafici/", name = "OP_MSG_DATA", scope = OutputParameters.class)
    public JAXBElement<String> createOutputParametersOPMSGDATA(String value) {
        return new JAXBElement<String>(_OutputParametersOPMSGDATA_QNAME, String.class, OutputParameters.class, value);
    }

}
