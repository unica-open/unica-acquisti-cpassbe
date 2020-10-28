
package it.csi.siac.documenti.svc._1;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the it.csi.siac.documenti.svc._1 package. 
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

    private final static QName _ElaboraDocumento_QNAME = new QName("http://siac.csi.it/documenti/svc/1.0", "elaboraDocumento");
    private final static QName _ElaboraDocumentoResponse_QNAME = new QName("http://siac.csi.it/documenti/svc/1.0", "elaboraDocumentoResponse");
    private final static QName _ElaboraDocumentoAsync_QNAME = new QName("http://siac.csi.it/documenti/svc/1.0", "elaboraDocumentoAsync");
    private final static QName _ElaboraDocumentoAsyncResponse_QNAME = new QName("http://siac.csi.it/documenti/svc/1.0", "elaboraDocumentoAsyncResponse");
    private final static QName _LeggiStatoElaborazioneDocumento_QNAME = new QName("http://siac.csi.it/documenti/svc/1.0", "leggiStatoElaborazioneDocumento");
    private final static QName _LeggiStatoElaborazioneDocumentoResponse_QNAME = new QName("http://siac.csi.it/documenti/svc/1.0", "leggiStatoElaborazioneDocumentoResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: it.csi.siac.documenti.svc._1
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ElaboraDocumento }
     * 
     */
    public ElaboraDocumento createElaboraDocumento() {
        return new ElaboraDocumento();
    }

    /**
     * Create an instance of {@link ElaboraDocumentoResponse }
     * 
     */
    public ElaboraDocumentoResponse createElaboraDocumentoResponse() {
        return new ElaboraDocumentoResponse();
    }

    /**
     * Create an instance of {@link ElaboraDocumentoAsyncResponse }
     * 
     */
    public ElaboraDocumentoAsyncResponse createElaboraDocumentoAsyncResponse() {
        return new ElaboraDocumentoAsyncResponse();
    }

    /**
     * Create an instance of {@link LeggiStatoElaborazioneDocumento }
     * 
     */
    public LeggiStatoElaborazioneDocumento createLeggiStatoElaborazioneDocumento() {
        return new LeggiStatoElaborazioneDocumento();
    }

    /**
     * Create an instance of {@link LeggiStatoElaborazioneDocumentoResponse }
     * 
     */
    public LeggiStatoElaborazioneDocumentoResponse createLeggiStatoElaborazioneDocumentoResponse() {
        return new LeggiStatoElaborazioneDocumentoResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ElaboraDocumento }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ElaboraDocumento }{@code >}
     */
    @XmlElementDecl(namespace = "http://siac.csi.it/documenti/svc/1.0", name = "elaboraDocumento")
    public JAXBElement<ElaboraDocumento> createElaboraDocumento(ElaboraDocumento value) {
        return new JAXBElement<ElaboraDocumento>(_ElaboraDocumento_QNAME, ElaboraDocumento.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ElaboraDocumentoResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ElaboraDocumentoResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://siac.csi.it/documenti/svc/1.0", name = "elaboraDocumentoResponse")
    public JAXBElement<ElaboraDocumentoResponse> createElaboraDocumentoResponse(ElaboraDocumentoResponse value) {
        return new JAXBElement<ElaboraDocumentoResponse>(_ElaboraDocumentoResponse_QNAME, ElaboraDocumentoResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ElaboraDocumento }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ElaboraDocumento }{@code >}
     */
    @XmlElementDecl(namespace = "http://siac.csi.it/documenti/svc/1.0", name = "elaboraDocumentoAsync")
    public JAXBElement<ElaboraDocumento> createElaboraDocumentoAsync(ElaboraDocumento value) {
        return new JAXBElement<ElaboraDocumento>(_ElaboraDocumentoAsync_QNAME, ElaboraDocumento.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ElaboraDocumentoAsyncResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ElaboraDocumentoAsyncResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://siac.csi.it/documenti/svc/1.0", name = "elaboraDocumentoAsyncResponse")
    public JAXBElement<ElaboraDocumentoAsyncResponse> createElaboraDocumentoAsyncResponse(ElaboraDocumentoAsyncResponse value) {
        return new JAXBElement<ElaboraDocumentoAsyncResponse>(_ElaboraDocumentoAsyncResponse_QNAME, ElaboraDocumentoAsyncResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LeggiStatoElaborazioneDocumento }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link LeggiStatoElaborazioneDocumento }{@code >}
     */
    @XmlElementDecl(namespace = "http://siac.csi.it/documenti/svc/1.0", name = "leggiStatoElaborazioneDocumento")
    public JAXBElement<LeggiStatoElaborazioneDocumento> createLeggiStatoElaborazioneDocumento(LeggiStatoElaborazioneDocumento value) {
        return new JAXBElement<LeggiStatoElaborazioneDocumento>(_LeggiStatoElaborazioneDocumento_QNAME, LeggiStatoElaborazioneDocumento.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LeggiStatoElaborazioneDocumentoResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link LeggiStatoElaborazioneDocumentoResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://siac.csi.it/documenti/svc/1.0", name = "leggiStatoElaborazioneDocumentoResponse")
    public JAXBElement<LeggiStatoElaborazioneDocumentoResponse> createLeggiStatoElaborazioneDocumentoResponse(LeggiStatoElaborazioneDocumentoResponse value) {
        return new JAXBElement<LeggiStatoElaborazioneDocumentoResponse>(_LeggiStatoElaborazioneDocumentoResponse_QNAME, LeggiStatoElaborazioneDocumentoResponse.class, null, value);
    }

}
