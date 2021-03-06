
package it.csi.siac.documenti.svc._1;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.3.3
 * Generated source version: 2.2
 * 
 */
@WebService(name = "DocumentiService", targetNamespace = "http://siac.csi.it/documenti/svc/1.0")
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
@XmlSeeAlso({
    it.csi.siac.documenti.svc._1.ObjectFactory.class,
    it.csi.siac.integ.data._1.ObjectFactory.class
})
public interface DocumentiService {


    /**
     * 
     * @param elaboraDocumento
     * @return
     *     returns it.csi.siac.documenti.svc._1.ElaboraDocumentoResponse
     */
    @WebMethod
    @WebResult(name = "elaboraDocumentoResponse", targetNamespace = "http://siac.csi.it/documenti/svc/1.0", partName = "elaboraDocumentoResponse")
    public ElaboraDocumentoResponse elaboraDocumento(
        @WebParam(name = "elaboraDocumento", targetNamespace = "http://siac.csi.it/documenti/svc/1.0", partName = "elaboraDocumento")
        ElaboraDocumento elaboraDocumento);

    /**
     * 
     * @param elaboraDocumentoAsync
     * @return
     *     returns it.csi.siac.documenti.svc._1.ElaboraDocumentoAsyncResponse
     */
    @WebMethod
    @WebResult(name = "elaboraDocumentoAsyncResponse", targetNamespace = "http://siac.csi.it/documenti/svc/1.0", partName = "elaboraDocumentoAsyncResponse")
    public ElaboraDocumentoAsyncResponse elaboraDocumentoAsync(
        @WebParam(name = "elaboraDocumentoAsync", targetNamespace = "http://siac.csi.it/documenti/svc/1.0", partName = "elaboraDocumentoAsync")
        ElaboraDocumento elaboraDocumentoAsync);

    /**
     * 
     * @param leggiStatoElaborazioneDocumento
     * @return
     *     returns it.csi.siac.documenti.svc._1.LeggiStatoElaborazioneDocumentoResponse
     */
    @WebMethod
    @WebResult(name = "leggiStatoElaborazioneDocumentoResponse", targetNamespace = "http://siac.csi.it/documenti/svc/1.0", partName = "leggiStatoElaborazioneDocumentoResponse")
    public LeggiStatoElaborazioneDocumentoResponse leggiStatoElaborazioneDocumento(
        @WebParam(name = "leggiStatoElaborazioneDocumento", targetNamespace = "http://siac.csi.it/documenti/svc/1.0", partName = "leggiStatoElaborazioneDocumento")
        LeggiStatoElaborazioneDocumento leggiStatoElaborazioneDocumento);

}
