/*-
 * ========================LICENSE_START=================================
 * CPASS BackEnd - EXPOSED submodule
 * %%
 * Copyright (C) 2019 - 2025 CSI Piemonte
 * %%
 * SPDX-FileCopyrightText: Copyright 2019 - 2020 | CSI Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 * =========================LICENSE_END==================================
 */

package it.csi.cpass.cpassbe.data.verificaevasione;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the it.csi.cpass.cpassbe.data.verificaevasione package. 
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

    private final static QName _VerificaInput_QNAME = new QName("http://cpass.csi.it/cpassbe/data/verificaevasione", "VerificaInput");
    private final static QName _VerificaOutput_QNAME = new QName("http://cpass.csi.it/cpassbe/data/verificaevasione", "VerificaOutput");
    private final static QName _VerificaEvasione_QNAME = new QName("http://cpass.csi.it/cpassbe/data/verificaevasione", "verificaEvasione");
    private final static QName _VerificaEvasioneResponse_QNAME = new QName("http://cpass.csi.it/cpassbe/data/verificaevasione", "verificaEvasioneResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: it.csi.cpass.cpassbe.data.verificaevasione
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Evasioni }
     * 
     */
    public Evasioni createEvasioni() {
        return new Evasioni();
    }

    /**
     * Create an instance of {@link EsitoServizio }
     * 
     */
    public EsitoServizio createEsitoServizio() {
        return new EsitoServizio();
    }

    /**
     * Create an instance of {@link VerificaEvasione }
     * 
     */
    public VerificaEvasione createVerificaEvasione() {
        return new VerificaEvasione();
    }

    /**
     * Create an instance of {@link VerificaEvasioneResponse }
     * 
     */
    public VerificaEvasioneResponse createVerificaEvasioneResponse() {
        return new VerificaEvasioneResponse();
    }

    /**
     * Create an instance of {@link Impegno }
     * 
     */
    public Impegno createImpegno() {
        return new Impegno();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Evasioni }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Evasioni }{@code >}
     */
    @XmlElementDecl(namespace = "http://cpass.csi.it/cpassbe/data/verificaevasione", name = "VerificaInput")
    public JAXBElement<Evasioni> createVerificaInput(Evasioni value) {
        return new JAXBElement<Evasioni>(_VerificaInput_QNAME, Evasioni.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EsitoServizio }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link EsitoServizio }{@code >}
     */
    @XmlElementDecl(namespace = "http://cpass.csi.it/cpassbe/data/verificaevasione", name = "VerificaOutput")
    public JAXBElement<EsitoServizio> createVerificaOutput(EsitoServizio value) {
        return new JAXBElement<EsitoServizio>(_VerificaOutput_QNAME, EsitoServizio.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link VerificaEvasione }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link VerificaEvasione }{@code >}
     */
    @XmlElementDecl(namespace = "http://cpass.csi.it/cpassbe/data/verificaevasione", name = "verificaEvasione")
    public JAXBElement<VerificaEvasione> createVerificaEvasione(VerificaEvasione value) {
        return new JAXBElement<VerificaEvasione>(_VerificaEvasione_QNAME, VerificaEvasione.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link VerificaEvasioneResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link VerificaEvasioneResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://cpass.csi.it/cpassbe/data/verificaevasione", name = "verificaEvasioneResponse")
    public JAXBElement<VerificaEvasioneResponse> createVerificaEvasioneResponse(VerificaEvasioneResponse value) {
        return new JAXBElement<VerificaEvasioneResponse>(_VerificaEvasioneResponse_QNAME, VerificaEvasioneResponse.class, null, value);
    }

}
