
package it.csi.siac.documenti.svc._1;

import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.3.3
 * Generated source version: 2.2
 * 
 */
@WebServiceClient(name = "DocumentiService", targetNamespace = "http://siac.csi.it/documenti/svc/1.0", wsdlLocation = "/wsdl/bilancio-cre--BILANCIO_contabilia_documentiService1.0.wsdl")
public class DocumentiService_Service
    extends Service
{

    private final static URL DOCUMENTISERVICE_WSDL_LOCATION;
    private final static WebServiceException DOCUMENTISERVICE_EXCEPTION;
    private final static QName DOCUMENTISERVICE_QNAME = new QName("http://siac.csi.it/documenti/svc/1.0", "DocumentiService");

    static {
        DOCUMENTISERVICE_WSDL_LOCATION = it.csi.siac.documenti.svc._1.DocumentiService_Service.class.getResource("/wsdl/bilancio-cre--BILANCIO_contabilia_documentiService1.0.wsdl");
        WebServiceException e = null;
        if (DOCUMENTISERVICE_WSDL_LOCATION == null) {
            e = new WebServiceException("Cannot find '/wsdl/bilancio-cre--BILANCIO_contabilia_documentiService1.0.wsdl' wsdl. Place the resource correctly in the classpath.");
        }
        DOCUMENTISERVICE_EXCEPTION = e;
    }

    public DocumentiService_Service() {
        super(__getWsdlLocation(), DOCUMENTISERVICE_QNAME);
    }

    public DocumentiService_Service(WebServiceFeature... features) {
        super(__getWsdlLocation(), DOCUMENTISERVICE_QNAME, features);
    }

    public DocumentiService_Service(URL wsdlLocation) {
        super(wsdlLocation, DOCUMENTISERVICE_QNAME);
    }

    public DocumentiService_Service(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, DOCUMENTISERVICE_QNAME, features);
    }

    public DocumentiService_Service(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public DocumentiService_Service(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns DocumentiService
     */
    @WebEndpoint(name = "DocumentiServicePort")
    public DocumentiService getDocumentiServicePort() {
        return super.getPort(new QName("http://siac.csi.it/documenti/svc/1.0", "DocumentiServicePort"), DocumentiService.class);
    }

    /**
     * 
     * @param features
     *     A list of {&#064;link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the &lt;code&gt;features&lt;/code&gt; parameter will have their default values.
     * @return
     *     returns DocumentiService
     */
    @WebEndpoint(name = "DocumentiServicePort")
    public DocumentiService getDocumentiServicePort(WebServiceFeature... features) {
        return super.getPort(new QName("http://siac.csi.it/documenti/svc/1.0", "DocumentiServicePort"), DocumentiService.class, features);
    }

    private static URL __getWsdlLocation() {
        if (DOCUMENTISERVICE_EXCEPTION!= null) {
            throw DOCUMENTISERVICE_EXCEPTION;
        }
        return DOCUMENTISERVICE_WSDL_LOCATION;
    }

}
