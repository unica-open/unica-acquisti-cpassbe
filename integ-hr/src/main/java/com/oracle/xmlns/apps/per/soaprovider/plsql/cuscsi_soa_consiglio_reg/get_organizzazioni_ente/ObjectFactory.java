
package com.oracle.xmlns.apps.per.soaprovider.plsql.cuscsi_soa_consiglio_reg.get_organizzazioni_ente;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.oracle.xmlns.apps.per.soaprovider.plsql.cuscsi_soa_consiglio_reg.get_organizzazioni_ente package. 
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

    private final static QName _InputParametersPCODSTRUTTURA_QNAME = new QName("http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_consiglio_reg/get_organizzazioni_ente/", "P_COD_STRUTTURA");
    private final static QName _InputParametersPNOME_QNAME = new QName("http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_consiglio_reg/get_organizzazioni_ente/", "P_NOME");
    private final static QName _InputParametersPDATAESTRAZIONE_QNAME = new QName("http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_consiglio_reg/get_organizzazioni_ente/", "P_DATA_ESTRAZIONE");
    private final static QName _OutputParametersOPNOME_QNAME = new QName("http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_consiglio_reg/get_organizzazioni_ente/", "OP_NOME");
    private final static QName _OutputParametersOPCODTIPOLOGIA_QNAME = new QName("http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_consiglio_reg/get_organizzazioni_ente/", "OP_COD_TIPOLOGIA");
    private final static QName _OutputParametersOPDESTIPOLOGIA_QNAME = new QName("http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_consiglio_reg/get_organizzazioni_ente/", "OP_DES_TIPOLOGIA");
    private final static QName _OutputParametersOPDATAINIZIO_QNAME = new QName("http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_consiglio_reg/get_organizzazioni_ente/", "OP_DATA_INIZIO");
    private final static QName _OutputParametersOPDATAFINE_QNAME = new QName("http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_consiglio_reg/get_organizzazioni_ente/", "OP_DATA_FINE");
    private final static QName _OutputParametersOPCODUBICAZIONE_QNAME = new QName("http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_consiglio_reg/get_organizzazioni_ente/", "OP_COD_UBICAZIONE");
    private final static QName _OutputParametersOPDESCRIZIONEESTESA_QNAME = new QName("http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_consiglio_reg/get_organizzazioni_ente/", "OP_DESCRIZIONE_ESTESA");
    private final static QName _OutputParametersOPCODSTRUTTURA_QNAME = new QName("http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_consiglio_reg/get_organizzazioni_ente/", "OP_COD_STRUTTURA");
    private final static QName _OutputParametersOPPROVVEDIMENTO_QNAME = new QName("http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_consiglio_reg/get_organizzazioni_ente/", "OP_PROVVEDIMENTO");
    private final static QName _OutputParametersOPDATAPROVVEDIMENTO_QNAME = new QName("http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_consiglio_reg/get_organizzazioni_ente/", "OP_DATA_PROVVEDIMENTO");
    private final static QName _OutputParametersOPTELEFONO_QNAME = new QName("http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_consiglio_reg/get_organizzazioni_ente/", "OP_TELEFONO");
    private final static QName _OutputParametersOPTELEFONO2_QNAME = new QName("http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_consiglio_reg/get_organizzazioni_ente/", "OP_TELEFONO2");
    private final static QName _OutputParametersOPTELEFONO3_QNAME = new QName("http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_consiglio_reg/get_organizzazioni_ente/", "OP_TELEFONO3");
    private final static QName _OutputParametersOPFAX_QNAME = new QName("http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_consiglio_reg/get_organizzazioni_ente/", "OP_FAX");
    private final static QName _OutputParametersOPEMAIL_QNAME = new QName("http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_consiglio_reg/get_organizzazioni_ente/", "OP_EMAIL");
    private final static QName _OutputParametersOPAOO_QNAME = new QName("http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_consiglio_reg/get_organizzazioni_ente/", "OP_AOO");
    private final static QName _OutputParametersOPORIGINESTRUTTURA_QNAME = new QName("http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_consiglio_reg/get_organizzazioni_ente/", "OP_ORIGINE_STRUTTURA");
    private final static QName _OutputParametersOPCODREGIONALE_QNAME = new QName("http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_consiglio_reg/get_organizzazioni_ente/", "OP_COD_REGIONALE");
    private final static QName _OutputParametersOPCODSTRUTTURAPADRE_QNAME = new QName("http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_consiglio_reg/get_organizzazioni_ente/", "OP_COD_STRUTTURA_PADRE");
    private final static QName _OutputParametersOPCODTIPOLOGIAPADRE_QNAME = new QName("http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_consiglio_reg/get_organizzazioni_ente/", "OP_COD_TIPOLOGIA_PADRE");
    private final static QName _OutputParametersOPMSGDATA_QNAME = new QName("http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_consiglio_reg/get_organizzazioni_ente/", "OP_MSG_DATA");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.oracle.xmlns.apps.per.soaprovider.plsql.cuscsi_soa_consiglio_reg.get_organizzazioni_ente
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
    @XmlElementDecl(namespace = "http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_consiglio_reg/get_organizzazioni_ente/", name = "P_COD_STRUTTURA", scope = InputParameters.class)
    public JAXBElement<String> createInputParametersPCODSTRUTTURA(String value) {
        return new JAXBElement<String>(_InputParametersPCODSTRUTTURA_QNAME, String.class, InputParameters.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_consiglio_reg/get_organizzazioni_ente/", name = "P_NOME", scope = InputParameters.class)
    public JAXBElement<String> createInputParametersPNOME(String value) {
        return new JAXBElement<String>(_InputParametersPNOME_QNAME, String.class, InputParameters.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_consiglio_reg/get_organizzazioni_ente/", name = "P_DATA_ESTRAZIONE", scope = InputParameters.class)
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
    @XmlElementDecl(namespace = "http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_consiglio_reg/get_organizzazioni_ente/", name = "OP_NOME", scope = OutputParameters.class)
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
    @XmlElementDecl(namespace = "http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_consiglio_reg/get_organizzazioni_ente/", name = "OP_COD_TIPOLOGIA", scope = OutputParameters.class)
    public JAXBElement<String> createOutputParametersOPCODTIPOLOGIA(String value) {
        return new JAXBElement<String>(_OutputParametersOPCODTIPOLOGIA_QNAME, String.class, OutputParameters.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_consiglio_reg/get_organizzazioni_ente/", name = "OP_DES_TIPOLOGIA", scope = OutputParameters.class)
    public JAXBElement<String> createOutputParametersOPDESTIPOLOGIA(String value) {
        return new JAXBElement<String>(_OutputParametersOPDESTIPOLOGIA_QNAME, String.class, OutputParameters.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
     */
    @XmlElementDecl(namespace = "http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_consiglio_reg/get_organizzazioni_ente/", name = "OP_DATA_INIZIO", scope = OutputParameters.class)
    public JAXBElement<XMLGregorianCalendar> createOutputParametersOPDATAINIZIO(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_OutputParametersOPDATAINIZIO_QNAME, XMLGregorianCalendar.class, OutputParameters.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
     */
    @XmlElementDecl(namespace = "http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_consiglio_reg/get_organizzazioni_ente/", name = "OP_DATA_FINE", scope = OutputParameters.class)
    public JAXBElement<XMLGregorianCalendar> createOutputParametersOPDATAFINE(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_OutputParametersOPDATAFINE_QNAME, XMLGregorianCalendar.class, OutputParameters.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_consiglio_reg/get_organizzazioni_ente/", name = "OP_COD_UBICAZIONE", scope = OutputParameters.class)
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
    @XmlElementDecl(namespace = "http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_consiglio_reg/get_organizzazioni_ente/", name = "OP_DESCRIZIONE_ESTESA", scope = OutputParameters.class)
    public JAXBElement<String> createOutputParametersOPDESCRIZIONEESTESA(String value) {
        return new JAXBElement<String>(_OutputParametersOPDESCRIZIONEESTESA_QNAME, String.class, OutputParameters.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_consiglio_reg/get_organizzazioni_ente/", name = "OP_COD_STRUTTURA", scope = OutputParameters.class)
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
    @XmlElementDecl(namespace = "http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_consiglio_reg/get_organizzazioni_ente/", name = "OP_PROVVEDIMENTO", scope = OutputParameters.class)
    public JAXBElement<String> createOutputParametersOPPROVVEDIMENTO(String value) {
        return new JAXBElement<String>(_OutputParametersOPPROVVEDIMENTO_QNAME, String.class, OutputParameters.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
     */
    @XmlElementDecl(namespace = "http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_consiglio_reg/get_organizzazioni_ente/", name = "OP_DATA_PROVVEDIMENTO", scope = OutputParameters.class)
    public JAXBElement<XMLGregorianCalendar> createOutputParametersOPDATAPROVVEDIMENTO(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_OutputParametersOPDATAPROVVEDIMENTO_QNAME, XMLGregorianCalendar.class, OutputParameters.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_consiglio_reg/get_organizzazioni_ente/", name = "OP_TELEFONO", scope = OutputParameters.class)
    public JAXBElement<String> createOutputParametersOPTELEFONO(String value) {
        return new JAXBElement<String>(_OutputParametersOPTELEFONO_QNAME, String.class, OutputParameters.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_consiglio_reg/get_organizzazioni_ente/", name = "OP_TELEFONO2", scope = OutputParameters.class)
    public JAXBElement<String> createOutputParametersOPTELEFONO2(String value) {
        return new JAXBElement<String>(_OutputParametersOPTELEFONO2_QNAME, String.class, OutputParameters.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_consiglio_reg/get_organizzazioni_ente/", name = "OP_TELEFONO3", scope = OutputParameters.class)
    public JAXBElement<String> createOutputParametersOPTELEFONO3(String value) {
        return new JAXBElement<String>(_OutputParametersOPTELEFONO3_QNAME, String.class, OutputParameters.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_consiglio_reg/get_organizzazioni_ente/", name = "OP_FAX", scope = OutputParameters.class)
    public JAXBElement<String> createOutputParametersOPFAX(String value) {
        return new JAXBElement<String>(_OutputParametersOPFAX_QNAME, String.class, OutputParameters.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_consiglio_reg/get_organizzazioni_ente/", name = "OP_EMAIL", scope = OutputParameters.class)
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
    @XmlElementDecl(namespace = "http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_consiglio_reg/get_organizzazioni_ente/", name = "OP_AOO", scope = OutputParameters.class)
    public JAXBElement<String> createOutputParametersOPAOO(String value) {
        return new JAXBElement<String>(_OutputParametersOPAOO_QNAME, String.class, OutputParameters.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_consiglio_reg/get_organizzazioni_ente/", name = "OP_ORIGINE_STRUTTURA", scope = OutputParameters.class)
    public JAXBElement<String> createOutputParametersOPORIGINESTRUTTURA(String value) {
        return new JAXBElement<String>(_OutputParametersOPORIGINESTRUTTURA_QNAME, String.class, OutputParameters.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_consiglio_reg/get_organizzazioni_ente/", name = "OP_COD_REGIONALE", scope = OutputParameters.class)
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
    @XmlElementDecl(namespace = "http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_consiglio_reg/get_organizzazioni_ente/", name = "OP_COD_STRUTTURA_PADRE", scope = OutputParameters.class)
    public JAXBElement<String> createOutputParametersOPCODSTRUTTURAPADRE(String value) {
        return new JAXBElement<String>(_OutputParametersOPCODSTRUTTURAPADRE_QNAME, String.class, OutputParameters.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_consiglio_reg/get_organizzazioni_ente/", name = "OP_COD_TIPOLOGIA_PADRE", scope = OutputParameters.class)
    public JAXBElement<String> createOutputParametersOPCODTIPOLOGIAPADRE(String value) {
        return new JAXBElement<String>(_OutputParametersOPCODTIPOLOGIAPADRE_QNAME, String.class, OutputParameters.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_consiglio_reg/get_organizzazioni_ente/", name = "OP_MSG_DATA", scope = OutputParameters.class)
    public JAXBElement<String> createOutputParametersOPMSGDATA(String value) {
        return new JAXBElement<String>(_OutputParametersOPMSGDATA_QNAME, String.class, OutputParameters.class, value);
    }

}
