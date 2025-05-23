
package com.oracle.xmlns.apps.per.soaprovider.plsql.cuscsi_soa_giunta_regionale.get_tipologia_org_ente;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.oracle.xmlns.apps.per.soaprovider.plsql.cuscsi_soa_giunta_regionale.get_tipologia_org_ente package. 
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

    private final static QName _InputParametersPDATAESTRAZIONE_QNAME = new QName("http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_giunta_regionale/get_tipologia_org_ente/", "P_DATA_ESTRAZIONE");
    private final static QName _OutputParametersCUSCSISOAGIUNTAREGIONALE24G_QNAME = new QName("http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_giunta_regionale/get_tipologia_org_ente/", "CUSCSI_SOA_GIUNTA_REGIONALE-24G");
    private final static QName _OutputParametersOPCODTIPOLOGIA_QNAME = new QName("http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_giunta_regionale/get_tipologia_org_ente/", "OP_COD_TIPOLOGIA");
    private final static QName _OutputParametersOPDESTIPOLOGIA_QNAME = new QName("http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_giunta_regionale/get_tipologia_org_ente/", "OP_DES_TIPOLOGIA");
    private final static QName _OutputParametersOPDATAINIZIO_QNAME = new QName("http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_giunta_regionale/get_tipologia_org_ente/", "OP_DATA_INIZIO");
    private final static QName _OutputParametersOPDATAFINE_QNAME = new QName("http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_giunta_regionale/get_tipologia_org_ente/", "OP_DATA_FINE");
    private final static QName _OutputParametersOPMSGDATA_QNAME = new QName("http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_giunta_regionale/get_tipologia_org_ente/", "OP_MSG_DATA");
    private final static QName _APPSCUSCSISOAGIUNTX5117459X5X2CODTIPOLOGIA_QNAME = new QName("http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_giunta_regionale/get_tipologia_org_ente/", "COD_TIPOLOGIA");
    private final static QName _APPSCUSCSISOAGIUNTX5117459X5X2DESTIPOLOGIA_QNAME = new QName("http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_giunta_regionale/get_tipologia_org_ente/", "DES_TIPOLOGIA");
    private final static QName _APPSCUSCSISOAGIUNTX5117459X5X2DATAINIZIO_QNAME = new QName("http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_giunta_regionale/get_tipologia_org_ente/", "DATA_INIZIO");
    private final static QName _APPSCUSCSISOAGIUNTX5117459X5X2DATAFINE_QNAME = new QName("http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_giunta_regionale/get_tipologia_org_ente/", "DATA_FINE");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.oracle.xmlns.apps.per.soaprovider.plsql.cuscsi_soa_giunta_regionale.get_tipologia_org_ente
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
     * Create an instance of {@link APPSCUSCSISOAGIUNTX5117459X5X1 }
     * 
     */
    public APPSCUSCSISOAGIUNTX5117459X5X1 createAPPSCUSCSISOAGIUNTX5117459X5X1() {
        return new APPSCUSCSISOAGIUNTX5117459X5X1();
    }

    /**
     * Create an instance of {@link APPSCUSCSISOAGIUNTX5117459X5X2 }
     * 
     */
    public APPSCUSCSISOAGIUNTX5117459X5X2 createAPPSCUSCSISOAGIUNTX5117459X5X2() {
        return new APPSCUSCSISOAGIUNTX5117459X5X2();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_giunta_regionale/get_tipologia_org_ente/", name = "P_DATA_ESTRAZIONE", scope = InputParameters.class)
    public JAXBElement<String> createInputParametersPDATAESTRAZIONE(String value) {
        return new JAXBElement<String>(_InputParametersPDATAESTRAZIONE_QNAME, String.class, InputParameters.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link APPSCUSCSISOAGIUNTX5117459X5X1 }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link APPSCUSCSISOAGIUNTX5117459X5X1 }{@code >}
     */
    @XmlElementDecl(namespace = "http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_giunta_regionale/get_tipologia_org_ente/", name = "CUSCSI_SOA_GIUNTA_REGIONALE-24G", scope = OutputParameters.class)
    public JAXBElement<APPSCUSCSISOAGIUNTX5117459X5X1> createOutputParametersCUSCSISOAGIUNTAREGIONALE24G(APPSCUSCSISOAGIUNTX5117459X5X1 value) {
        return new JAXBElement<APPSCUSCSISOAGIUNTX5117459X5X1>(_OutputParametersCUSCSISOAGIUNTAREGIONALE24G_QNAME, APPSCUSCSISOAGIUNTX5117459X5X1 .class, OutputParameters.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_giunta_regionale/get_tipologia_org_ente/", name = "OP_COD_TIPOLOGIA", scope = OutputParameters.class)
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
    @XmlElementDecl(namespace = "http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_giunta_regionale/get_tipologia_org_ente/", name = "OP_DES_TIPOLOGIA", scope = OutputParameters.class)
    public JAXBElement<String> createOutputParametersOPDESTIPOLOGIA(String value) {
        return new JAXBElement<String>(_OutputParametersOPDESTIPOLOGIA_QNAME, String.class, OutputParameters.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_giunta_regionale/get_tipologia_org_ente/", name = "OP_DATA_INIZIO", scope = OutputParameters.class)
    public JAXBElement<String> createOutputParametersOPDATAINIZIO(String value) {
        return new JAXBElement<String>(_OutputParametersOPDATAINIZIO_QNAME, String.class, OutputParameters.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_giunta_regionale/get_tipologia_org_ente/", name = "OP_DATA_FINE", scope = OutputParameters.class)
    public JAXBElement<String> createOutputParametersOPDATAFINE(String value) {
        return new JAXBElement<String>(_OutputParametersOPDATAFINE_QNAME, String.class, OutputParameters.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_giunta_regionale/get_tipologia_org_ente/", name = "OP_MSG_DATA", scope = OutputParameters.class)
    public JAXBElement<String> createOutputParametersOPMSGDATA(String value) {
        return new JAXBElement<String>(_OutputParametersOPMSGDATA_QNAME, String.class, OutputParameters.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_giunta_regionale/get_tipologia_org_ente/", name = "COD_TIPOLOGIA", scope = APPSCUSCSISOAGIUNTX5117459X5X2 .class)
    public JAXBElement<String> createAPPSCUSCSISOAGIUNTX5117459X5X2CODTIPOLOGIA(String value) {
        return new JAXBElement<String>(_APPSCUSCSISOAGIUNTX5117459X5X2CODTIPOLOGIA_QNAME, String.class, APPSCUSCSISOAGIUNTX5117459X5X2 .class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_giunta_regionale/get_tipologia_org_ente/", name = "DES_TIPOLOGIA", scope = APPSCUSCSISOAGIUNTX5117459X5X2 .class)
    public JAXBElement<String> createAPPSCUSCSISOAGIUNTX5117459X5X2DESTIPOLOGIA(String value) {
        return new JAXBElement<String>(_APPSCUSCSISOAGIUNTX5117459X5X2DESTIPOLOGIA_QNAME, String.class, APPSCUSCSISOAGIUNTX5117459X5X2 .class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_giunta_regionale/get_tipologia_org_ente/", name = "DATA_INIZIO", scope = APPSCUSCSISOAGIUNTX5117459X5X2 .class)
    public JAXBElement<String> createAPPSCUSCSISOAGIUNTX5117459X5X2DATAINIZIO(String value) {
        return new JAXBElement<String>(_APPSCUSCSISOAGIUNTX5117459X5X2DATAINIZIO_QNAME, String.class, APPSCUSCSISOAGIUNTX5117459X5X2 .class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_giunta_regionale/get_tipologia_org_ente/", name = "DATA_FINE", scope = APPSCUSCSISOAGIUNTX5117459X5X2 .class)
    public JAXBElement<String> createAPPSCUSCSISOAGIUNTX5117459X5X2DATAFINE(String value) {
        return new JAXBElement<String>(_APPSCUSCSISOAGIUNTX5117459X5X2DATAFINE_QNAME, String.class, APPSCUSCSISOAGIUNTX5117459X5X2 .class, value);
    }

}
