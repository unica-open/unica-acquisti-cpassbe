
package it.eng.auriga.repository2.webservices.getmetadataud;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.Action;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.3.3
 * Generated source version: 2.2
 * 
 */
@WebService(name = "WSIGetMetadataUd", targetNamespace = "http://getmetadataud.webservices.repository2.auriga.eng.it")
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
@XmlSeeAlso({
    ObjectFactory.class
})
public interface WSIGetMetadataUd {


    /**
     * 
     * @param service
     * @return
     *     returns it.eng.auriga.repository2.webservices.getmetadataud.ServiceResponse
     */
    @WebMethod
    @WebResult(name = "serviceResponse", targetNamespace = "http://getmetadataud.webservices.repository2.auriga.eng.it", partName = "serviceResponse")
    @Action(input = "http://getmetadataud.webservices.repository2.auriga.eng.it/WSIGetMetadataUd/serviceOperationRequest", output = "http://getmetadataud.webservices.repository2.auriga.eng.it/WSIGetMetadataUd/serviceOperationResponse")
    public ServiceResponse serviceOperation(
        @WebParam(name = "service", targetNamespace = "http://getmetadataud.webservices.repository2.auriga.eng.it", partName = "service")
        Service service);

}