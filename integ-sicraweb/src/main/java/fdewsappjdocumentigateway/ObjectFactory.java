/*-
 * ========================LICENSE_START=================================
 * CPASS BackEnd - INTEGRATION submodule - SICRAWEB
 * %%
 * Copyright (C) 2019 - 2023 CSI Piemonte
 * %%
 * SPDX-FileCopyrightText: Copyright 2019 - 2023 | CSI Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 * =========================LICENSE_END==================================
 */

package fdewsappjdocumentigateway;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;

import it.saga.library.messages.SagaException;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the fdewsappjdocumentigateway package. 
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

    private final static QName _ElaboraDocumento_QNAME = new QName("urn:FdeWSAppjDocumentiGateway", "elaboraDocumento");
    private final static QName _ElaboraDocumentoResponse_QNAME = new QName("urn:FdeWSAppjDocumentiGateway", "elaboraDocumentoResponse");
    private final static QName _Fault_QNAME = new QName("urn:FdeWSAppjDocumentiGateway", "fault");
    private final static QName _ElaboraDocumentoMessaggio_QNAME = new QName("", "messaggio");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: fdewsappjdocumentigateway
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
     * Create an instance of {@link Stato }
     * 
     */
    public Stato createStato() {
        return new Stato();
    }

    /**
     * Create an instance of {@link Messaggio }
     * 
     */
    public Messaggio createMessaggio() {
        return new Messaggio();
    }

    /**
     * Create an instance of {@link Ente }
     * 
     */
    public Ente createEnte() {
        return new Ente();
    }

    /**
     * Create an instance of {@link Errore }
     * 
     */
    public Errore createErrore() {
        return new Errore();
    }

    /**
     * Create an instance of {@link ArrayOfXsdAnyType }
     * 
     */
    public ArrayOfXsdAnyType createArrayOfXsdAnyType() {
        return new ArrayOfXsdAnyType();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ElaboraDocumento }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ElaboraDocumento }{@code >}
     */
    @XmlElementDecl(namespace = "urn:FdeWSAppjDocumentiGateway", name = "elaboraDocumento")
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
    @XmlElementDecl(namespace = "urn:FdeWSAppjDocumentiGateway", name = "elaboraDocumentoResponse")
    public JAXBElement<ElaboraDocumentoResponse> createElaboraDocumentoResponse(ElaboraDocumentoResponse value) {
        return new JAXBElement<ElaboraDocumentoResponse>(_ElaboraDocumentoResponse_QNAME, ElaboraDocumentoResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SagaException }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link SagaException }{@code >}
     */
    @XmlElementDecl(namespace = "urn:FdeWSAppjDocumentiGateway", name = "fault")
    public JAXBElement<SagaException> createFault(SagaException value) {
        return new JAXBElement<SagaException>(_Fault_QNAME, SagaException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Messaggio }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Messaggio }{@code >}
     */
    @XmlElementDecl(namespace = "", name = "messaggio", scope = ElaboraDocumento.class)
    public JAXBElement<Messaggio> createElaboraDocumentoMessaggio(Messaggio value) {
        return new JAXBElement<Messaggio>(_ElaboraDocumentoMessaggio_QNAME, Messaggio.class, ElaboraDocumento.class, value);
    }

}
