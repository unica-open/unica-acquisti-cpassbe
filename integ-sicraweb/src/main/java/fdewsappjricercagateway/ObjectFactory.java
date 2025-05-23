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

package fdewsappjricercagateway;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;

import it.saga.library.messages.SagaException;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the fdewsappjricercagateway package. 
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

    private final static QName _RicercaSinteticaSoggetti_QNAME = new QName("urn:FdeWSAppjRicercaGateway", "ricercaSinteticaSoggetti");
    private final static QName _RicercaSinteticaSoggettiResponse_QNAME = new QName("urn:FdeWSAppjRicercaGateway", "ricercaSinteticaSoggettiResponse");
    private final static QName _Fault_QNAME = new QName("urn:FdeWSAppjRicercaGateway", "fault");
    private final static QName _RicercaDocumentoSpesa_QNAME = new QName("urn:FdeWSAppjRicercaGateway", "ricercaDocumentoSpesa");
    private final static QName _RicercaDocumentoSpesaResponse_QNAME = new QName("urn:FdeWSAppjRicercaGateway", "ricercaDocumentoSpesaResponse");
    private final static QName _RicercaImpegno_QNAME = new QName("urn:FdeWSAppjRicercaGateway", "ricercaImpegno");
    private final static QName _RicercaImpegnoResponse_QNAME = new QName("urn:FdeWSAppjRicercaGateway", "ricercaImpegnoResponse");
    private final static QName _RicercaDettaglioSoggetto_QNAME = new QName("urn:FdeWSAppjRicercaGateway", "ricercaDettaglioSoggetto");
    private final static QName _RicercaDettaglioSoggettoResponse_QNAME = new QName("urn:FdeWSAppjRicercaGateway", "ricercaDettaglioSoggettoResponse");
    private final static QName _RicercaCapitoloUscitaGestione_QNAME = new QName("urn:FdeWSAppjRicercaGateway", "ricercaCapitoloUscitaGestione");
    private final static QName _RicercaCapitoloUscitaGestioneResponse_QNAME = new QName("urn:FdeWSAppjRicercaGateway", "ricercaCapitoloUscitaGestioneResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: fdewsappjricercagateway
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link RicercaSinteticaSoggetti }
     * 
     */
    public RicercaSinteticaSoggetti createRicercaSinteticaSoggetti() {
        return new RicercaSinteticaSoggetti();
    }

    /**
     * Create an instance of {@link RicercaSinteticaSoggettiResponse }
     * 
     */
    public RicercaSinteticaSoggettiResponse createRicercaSinteticaSoggettiResponse() {
        return new RicercaSinteticaSoggettiResponse();
    }

    /**
     * Create an instance of {@link RicercaDocumentoSpesa }
     * 
     */
    public RicercaDocumentoSpesa createRicercaDocumentoSpesa() {
        return new RicercaDocumentoSpesa();
    }

    /**
     * Create an instance of {@link RicercaDocumentoSpesaResponse }
     * 
     */
    public RicercaDocumentoSpesaResponse createRicercaDocumentoSpesaResponse() {
        return new RicercaDocumentoSpesaResponse();
    }

    /**
     * Create an instance of {@link RicercaImpegno }
     * 
     */
    public RicercaImpegno createRicercaImpegno() {
        return new RicercaImpegno();
    }

    /**
     * Create an instance of {@link RicercaImpegnoResponse }
     * 
     */
    public RicercaImpegnoResponse createRicercaImpegnoResponse() {
        return new RicercaImpegnoResponse();
    }

    /**
     * Create an instance of {@link RicercaDettaglioSoggetti }
     * 
     */
    public RicercaDettaglioSoggetti createRicercaDettaglioSoggetti() {
        return new RicercaDettaglioSoggetti();
    }

    /**
     * Create an instance of {@link RicercaDettaglioSoggettiResponse }
     * 
     */
    public RicercaDettaglioSoggettiResponse createRicercaDettaglioSoggettiResponse() {
        return new RicercaDettaglioSoggettiResponse();
    }

    /**
     * Create an instance of {@link RicercaCapitoloUscitaGestione }
     * 
     */
    public RicercaCapitoloUscitaGestione createRicercaCapitoloUscitaGestione() {
        return new RicercaCapitoloUscitaGestione();
    }

    /**
     * Create an instance of {@link RicercaCapitoloUscitaGestioneResponse }
     * 
     */
    public RicercaCapitoloUscitaGestioneResponse createRicercaCapitoloUscitaGestioneResponse() {
        return new RicercaCapitoloUscitaGestioneResponse();
    }

    /**
     * Create an instance of {@link Stato }
     * 
     */
    public Stato createStato() {
        return new Stato();
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
     * Create an instance of {@link Messaggio }
     * 
     */
    public Messaggio createMessaggio() {
        return new Messaggio();
    }

    /**
     * Create an instance of {@link Contatti }
     * 
     */
    public Contatti createContatti() {
        return new Contatti();
    }

    /**
     * Create an instance of {@link Recapito }
     * 
     */
    public Recapito createRecapito() {
        return new Recapito();
    }

    /**
     * Create an instance of {@link Sede }
     * 
     */
    public Sede createSede() {
        return new Sede();
    }

    /**
     * Create an instance of {@link ModalitaPagamento }
     * 
     */
    public ModalitaPagamento createModalitaPagamento() {
        return new ModalitaPagamento();
    }

    /**
     * Create an instance of {@link NaturaGiuridica }
     * 
     */
    public NaturaGiuridica createNaturaGiuridica() {
        return new NaturaGiuridica();
    }

    /**
     * Create an instance of {@link Soggetto }
     * 
     */
    public Soggetto createSoggetto() {
        return new Soggetto();
    }

    /**
     * Create an instance of {@link ArrayOfXsdAnyType }
     * 
     */
    public ArrayOfXsdAnyType createArrayOfXsdAnyType() {
        return new ArrayOfXsdAnyType();
    }

    /**
     * Create an instance of {@link Documento }
     * 
     */
    public Documento createDocumento() {
        return new Documento();
    }

    /**
     * Create an instance of {@link Ordine }
     * 
     */
    public Ordine createOrdine() {
        return new Ordine();
    }

    /**
     * Create an instance of {@link DocumentoSpesa }
     * 
     */
    public DocumentoSpesa createDocumentoSpesa() {
        return new DocumentoSpesa();
    }

    /**
     * Create an instance of {@link RicercaMovimentoGestione }
     * 
     */
    public RicercaMovimentoGestione createRicercaMovimentoGestione() {
        return new RicercaMovimentoGestione();
    }

    /**
     * Create an instance of {@link PianoDeiContiFinanziario }
     * 
     */
    public PianoDeiContiFinanziario createPianoDeiContiFinanziario() {
        return new PianoDeiContiFinanziario();
    }

    /**
     * Create an instance of {@link StrutturaAmministrativa }
     * 
     */
    public StrutturaAmministrativa createStrutturaAmministrativa() {
        return new StrutturaAmministrativa();
    }

    /**
     * Create an instance of {@link Provvedimento }
     * 
     */
    public Provvedimento createProvvedimento() {
        return new Provvedimento();
    }

    /**
     * Create an instance of {@link MovimentoGestione }
     * 
     */
    public MovimentoGestione createMovimentoGestione() {
        return new MovimentoGestione();
    }

    /**
     * Create an instance of {@link SubImpegno }
     * 
     */
    public SubImpegno createSubImpegno() {
        return new SubImpegno();
    }

    /**
     * Create an instance of {@link Impegno }
     * 
     */
    public Impegno createImpegno() {
        return new Impegno();
    }

    /**
     * Create an instance of {@link RicercaCapitolo }
     * 
     */
    public RicercaCapitolo createRicercaCapitolo() {
        return new RicercaCapitolo();
    }

    /**
     * Create an instance of {@link ClassificatoreGenerico }
     * 
     */
    public ClassificatoreGenerico createClassificatoreGenerico() {
        return new ClassificatoreGenerico();
    }

    /**
     * Create an instance of {@link TipoFinanziamento }
     * 
     */
    public TipoFinanziamento createTipoFinanziamento() {
        return new TipoFinanziamento();
    }

    /**
     * Create an instance of {@link TipoFondo }
     * 
     */
    public TipoFondo createTipoFondo() {
        return new TipoFondo();
    }

    /**
     * Create an instance of {@link Titolo }
     * 
     */
    public Titolo createTitolo() {
        return new Titolo();
    }

    /**
     * Create an instance of {@link Capitolo }
     * 
     */
    public Capitolo createCapitolo() {
        return new Capitolo();
    }

    /**
     * Create an instance of {@link Importo }
     * 
     */
    public Importo createImporto() {
        return new Importo();
    }

    /**
     * Create an instance of {@link ImportoCapitoloUscitaGestione }
     * 
     */
    public ImportoCapitoloUscitaGestione createImportoCapitoloUscitaGestione() {
        return new ImportoCapitoloUscitaGestione();
    }

    /**
     * Create an instance of {@link Macroaggregato }
     * 
     */
    public Macroaggregato createMacroaggregato() {
        return new Macroaggregato();
    }

    /**
     * Create an instance of {@link Missione }
     * 
     */
    public Missione createMissione() {
        return new Missione();
    }

    /**
     * Create an instance of {@link Programma }
     * 
     */
    public Programma createProgramma() {
        return new Programma();
    }

    /**
     * Create an instance of {@link CapitoloUscitaGestione }
     * 
     */
    public CapitoloUscitaGestione createCapitoloUscitaGestione() {
        return new CapitoloUscitaGestione();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RicercaSinteticaSoggetti }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link RicercaSinteticaSoggetti }{@code >}
     */
    @XmlElementDecl(namespace = "urn:FdeWSAppjRicercaGateway", name = "ricercaSinteticaSoggetti")
    public JAXBElement<RicercaSinteticaSoggetti> createRicercaSinteticaSoggetti(RicercaSinteticaSoggetti value) {
        return new JAXBElement<RicercaSinteticaSoggetti>(_RicercaSinteticaSoggetti_QNAME, RicercaSinteticaSoggetti.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RicercaSinteticaSoggettiResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link RicercaSinteticaSoggettiResponse }{@code >}
     */
    @XmlElementDecl(namespace = "urn:FdeWSAppjRicercaGateway", name = "ricercaSinteticaSoggettiResponse")
    public JAXBElement<RicercaSinteticaSoggettiResponse> createRicercaSinteticaSoggettiResponse(RicercaSinteticaSoggettiResponse value) {
        return new JAXBElement<RicercaSinteticaSoggettiResponse>(_RicercaSinteticaSoggettiResponse_QNAME, RicercaSinteticaSoggettiResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SagaException }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link SagaException }{@code >}
     */
    @XmlElementDecl(namespace = "urn:FdeWSAppjRicercaGateway", name = "fault")
    public JAXBElement<SagaException> createFault(SagaException value) {
        return new JAXBElement<SagaException>(_Fault_QNAME, SagaException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RicercaDocumentoSpesa }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link RicercaDocumentoSpesa }{@code >}
     */
    @XmlElementDecl(namespace = "urn:FdeWSAppjRicercaGateway", name = "ricercaDocumentoSpesa")
    public JAXBElement<RicercaDocumentoSpesa> createRicercaDocumentoSpesa(RicercaDocumentoSpesa value) {
        return new JAXBElement<RicercaDocumentoSpesa>(_RicercaDocumentoSpesa_QNAME, RicercaDocumentoSpesa.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RicercaDocumentoSpesaResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link RicercaDocumentoSpesaResponse }{@code >}
     */
    @XmlElementDecl(namespace = "urn:FdeWSAppjRicercaGateway", name = "ricercaDocumentoSpesaResponse")
    public JAXBElement<RicercaDocumentoSpesaResponse> createRicercaDocumentoSpesaResponse(RicercaDocumentoSpesaResponse value) {
        return new JAXBElement<RicercaDocumentoSpesaResponse>(_RicercaDocumentoSpesaResponse_QNAME, RicercaDocumentoSpesaResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RicercaImpegno }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link RicercaImpegno }{@code >}
     */
    @XmlElementDecl(namespace = "urn:FdeWSAppjRicercaGateway", name = "ricercaImpegno")
    public JAXBElement<RicercaImpegno> createRicercaImpegno(RicercaImpegno value) {
        return new JAXBElement<RicercaImpegno>(_RicercaImpegno_QNAME, RicercaImpegno.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RicercaImpegnoResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link RicercaImpegnoResponse }{@code >}
     */
    @XmlElementDecl(namespace = "urn:FdeWSAppjRicercaGateway", name = "ricercaImpegnoResponse")
    public JAXBElement<RicercaImpegnoResponse> createRicercaImpegnoResponse(RicercaImpegnoResponse value) {
        return new JAXBElement<RicercaImpegnoResponse>(_RicercaImpegnoResponse_QNAME, RicercaImpegnoResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RicercaDettaglioSoggetti }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link RicercaDettaglioSoggetti }{@code >}
     */
    @XmlElementDecl(namespace = "urn:FdeWSAppjRicercaGateway", name = "ricercaDettaglioSoggetto")
    public JAXBElement<RicercaDettaglioSoggetti> createRicercaDettaglioSoggetto(RicercaDettaglioSoggetti value) {
        return new JAXBElement<RicercaDettaglioSoggetti>(_RicercaDettaglioSoggetto_QNAME, RicercaDettaglioSoggetti.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RicercaDettaglioSoggettiResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link RicercaDettaglioSoggettiResponse }{@code >}
     */
    @XmlElementDecl(namespace = "urn:FdeWSAppjRicercaGateway", name = "ricercaDettaglioSoggettoResponse")
    public JAXBElement<RicercaDettaglioSoggettiResponse> createRicercaDettaglioSoggettoResponse(RicercaDettaglioSoggettiResponse value) {
        return new JAXBElement<RicercaDettaglioSoggettiResponse>(_RicercaDettaglioSoggettoResponse_QNAME, RicercaDettaglioSoggettiResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RicercaCapitoloUscitaGestione }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link RicercaCapitoloUscitaGestione }{@code >}
     */
    @XmlElementDecl(namespace = "urn:FdeWSAppjRicercaGateway", name = "ricercaCapitoloUscitaGestione")
    public JAXBElement<RicercaCapitoloUscitaGestione> createRicercaCapitoloUscitaGestione(RicercaCapitoloUscitaGestione value) {
        return new JAXBElement<RicercaCapitoloUscitaGestione>(_RicercaCapitoloUscitaGestione_QNAME, RicercaCapitoloUscitaGestione.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RicercaCapitoloUscitaGestioneResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link RicercaCapitoloUscitaGestioneResponse }{@code >}
     */
    @XmlElementDecl(namespace = "urn:FdeWSAppjRicercaGateway", name = "ricercaCapitoloUscitaGestioneResponse")
    public JAXBElement<RicercaCapitoloUscitaGestioneResponse> createRicercaCapitoloUscitaGestioneResponse(RicercaCapitoloUscitaGestioneResponse value) {
        return new JAXBElement<RicercaCapitoloUscitaGestioneResponse>(_RicercaCapitoloUscitaGestioneResponse_QNAME, RicercaCapitoloUscitaGestioneResponse.class, null, value);
    }

}
