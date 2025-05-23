
package com.oracle.xmlns.apps.per.soaprovider.plsql.cuscsi_soa_giunta_regionale.get_dati_indirizzo_dip;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.oracle.xmlns.apps.per.soaprovider.plsql.cuscsi_soa_giunta_regionale.get_dati_indirizzo_dip package. 
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

    private final static QName _InputParametersPMATRICOLAHR_QNAME = new QName("http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_giunta_regionale/get_dati_indirizzo_dip/", "P_MATRICOLA_HR");
    private final static QName _InputParametersPCODICEFISCALE_QNAME = new QName("http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_giunta_regionale/get_dati_indirizzo_dip/", "P_CODICE_FISCALE");
    private final static QName _InputParametersPDATAESTRAZIONE_QNAME = new QName("http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_giunta_regionale/get_dati_indirizzo_dip/", "P_DATA_ESTRAZIONE");
    private final static QName _InputParametersPPRINCIPALE_QNAME = new QName("http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_giunta_regionale/get_dati_indirizzo_dip/", "P_PRINCIPALE");
    private final static QName _OutputParametersOPTIPOLOGIA_QNAME = new QName("http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_giunta_regionale/get_dati_indirizzo_dip/", "OP_TIPOLOGIA");
    private final static QName _OutputParametersOPINDIRIZZO_QNAME = new QName("http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_giunta_regionale/get_dati_indirizzo_dip/", "OP_INDIRIZZO");
    private final static QName _OutputParametersOPCIVICO_QNAME = new QName("http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_giunta_regionale/get_dati_indirizzo_dip/", "OP_CIVICO");
    private final static QName _OutputParametersOPBISINTERNOLETT_QNAME = new QName("http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_giunta_regionale/get_dati_indirizzo_dip/", "OP_BIS_INTERNO_LETT");
    private final static QName _OutputParametersOPCAP_QNAME = new QName("http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_giunta_regionale/get_dati_indirizzo_dip/", "OP_CAP");
    private final static QName _OutputParametersOPCITTA_QNAME = new QName("http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_giunta_regionale/get_dati_indirizzo_dip/", "OP_CITTA");
    private final static QName _OutputParametersOPPROVINCIA_QNAME = new QName("http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_giunta_regionale/get_dati_indirizzo_dip/", "OP_PROVINCIA");
    private final static QName _OutputParametersOPNAZIONE_QNAME = new QName("http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_giunta_regionale/get_dati_indirizzo_dip/", "OP_NAZIONE");
    private final static QName _OutputParametersOPTELEFONO_QNAME = new QName("http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_giunta_regionale/get_dati_indirizzo_dip/", "OP_TELEFONO");
    private final static QName _OutputParametersOPALTROTELEFONO_QNAME = new QName("http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_giunta_regionale/get_dati_indirizzo_dip/", "OP_ALTRO_TELEFONO");
    private final static QName _OutputParametersOPMSGDATA_QNAME = new QName("http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_giunta_regionale/get_dati_indirizzo_dip/", "OP_MSG_DATA");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.oracle.xmlns.apps.per.soaprovider.plsql.cuscsi_soa_giunta_regionale.get_dati_indirizzo_dip
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
    @XmlElementDecl(namespace = "http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_giunta_regionale/get_dati_indirizzo_dip/", name = "P_MATRICOLA_HR", scope = InputParameters.class)
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
    @XmlElementDecl(namespace = "http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_giunta_regionale/get_dati_indirizzo_dip/", name = "P_CODICE_FISCALE", scope = InputParameters.class)
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
    @XmlElementDecl(namespace = "http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_giunta_regionale/get_dati_indirizzo_dip/", name = "P_DATA_ESTRAZIONE", scope = InputParameters.class)
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
    @XmlElementDecl(namespace = "http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_giunta_regionale/get_dati_indirizzo_dip/", name = "P_PRINCIPALE", scope = InputParameters.class)
    public JAXBElement<String> createInputParametersPPRINCIPALE(String value) {
        return new JAXBElement<String>(_InputParametersPPRINCIPALE_QNAME, String.class, InputParameters.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_giunta_regionale/get_dati_indirizzo_dip/", name = "OP_TIPOLOGIA", scope = OutputParameters.class)
    public JAXBElement<String> createOutputParametersOPTIPOLOGIA(String value) {
        return new JAXBElement<String>(_OutputParametersOPTIPOLOGIA_QNAME, String.class, OutputParameters.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_giunta_regionale/get_dati_indirizzo_dip/", name = "OP_INDIRIZZO", scope = OutputParameters.class)
    public JAXBElement<String> createOutputParametersOPINDIRIZZO(String value) {
        return new JAXBElement<String>(_OutputParametersOPINDIRIZZO_QNAME, String.class, OutputParameters.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_giunta_regionale/get_dati_indirizzo_dip/", name = "OP_CIVICO", scope = OutputParameters.class)
    public JAXBElement<String> createOutputParametersOPCIVICO(String value) {
        return new JAXBElement<String>(_OutputParametersOPCIVICO_QNAME, String.class, OutputParameters.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_giunta_regionale/get_dati_indirizzo_dip/", name = "OP_BIS_INTERNO_LETT", scope = OutputParameters.class)
    public JAXBElement<String> createOutputParametersOPBISINTERNOLETT(String value) {
        return new JAXBElement<String>(_OutputParametersOPBISINTERNOLETT_QNAME, String.class, OutputParameters.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_giunta_regionale/get_dati_indirizzo_dip/", name = "OP_CAP", scope = OutputParameters.class)
    public JAXBElement<String> createOutputParametersOPCAP(String value) {
        return new JAXBElement<String>(_OutputParametersOPCAP_QNAME, String.class, OutputParameters.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_giunta_regionale/get_dati_indirizzo_dip/", name = "OP_CITTA", scope = OutputParameters.class)
    public JAXBElement<String> createOutputParametersOPCITTA(String value) {
        return new JAXBElement<String>(_OutputParametersOPCITTA_QNAME, String.class, OutputParameters.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_giunta_regionale/get_dati_indirizzo_dip/", name = "OP_PROVINCIA", scope = OutputParameters.class)
    public JAXBElement<String> createOutputParametersOPPROVINCIA(String value) {
        return new JAXBElement<String>(_OutputParametersOPPROVINCIA_QNAME, String.class, OutputParameters.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_giunta_regionale/get_dati_indirizzo_dip/", name = "OP_NAZIONE", scope = OutputParameters.class)
    public JAXBElement<String> createOutputParametersOPNAZIONE(String value) {
        return new JAXBElement<String>(_OutputParametersOPNAZIONE_QNAME, String.class, OutputParameters.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_giunta_regionale/get_dati_indirizzo_dip/", name = "OP_TELEFONO", scope = OutputParameters.class)
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
    @XmlElementDecl(namespace = "http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_giunta_regionale/get_dati_indirizzo_dip/", name = "OP_ALTRO_TELEFONO", scope = OutputParameters.class)
    public JAXBElement<String> createOutputParametersOPALTROTELEFONO(String value) {
        return new JAXBElement<String>(_OutputParametersOPALTROTELEFONO_QNAME, String.class, OutputParameters.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_giunta_regionale/get_dati_indirizzo_dip/", name = "OP_MSG_DATA", scope = OutputParameters.class)
    public JAXBElement<String> createOutputParametersOPMSGDATA(String value) {
        return new JAXBElement<String>(_OutputParametersOPMSGDATA_QNAME, String.class, OutputParameters.class, value);
    }

}
