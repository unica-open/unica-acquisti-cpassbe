/*-
 * ========================LICENSE_START=================================
 * CPASS BackEnd - INTEGRATION submodule - NotiER
 * %%
 * Copyright (C) 2019 - 2021 CSI Piemonte
 * %%
 * SPDX-FileCopyrightText: Copyright 2019 - 2021 | CSI Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 * =========================LICENSE_END==================================
 */

package it.gov.mef.rgs.nso.docs.xsd.ordini.messaggi.v1;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the it.gov.mef.rgs.nso.docs.xsd.ordini.messaggi.v1 package. 
 * <p>An ObjectFactory allows you to programatically 
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

    private final static QName _RicevutaConsegna_QNAME = new QName("http://nso.rgs.mef.gov.it/docs/xsd/ordini/messaggi/v1.0", "RicevutaConsegna");
    private final static QName _NotificaMancataConsegna_QNAME = new QName("http://nso.rgs.mef.gov.it/docs/xsd/ordini/messaggi/v1.0", "NotificaMancataConsegna");
    private final static QName _NotificaScarto_QNAME = new QName("http://nso.rgs.mef.gov.it/docs/xsd/ordini/messaggi/v1.0", "NotificaScarto");
    private final static QName _AttestazioneTrasmissione_QNAME = new QName("http://nso.rgs.mef.gov.it/docs/xsd/ordini/messaggi/v1.0", "AttestazioneTrasmissione");
    private final static QName _MetadatiInvioFile_QNAME = new QName("http://nso.rgs.mef.gov.it/docs/xsd/ordini/messaggi/v1.0", "MetadatiInvioFile");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: it.gov.mef.rgs.nso.docs.xsd.ordini.messaggi.v1
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link RicevutaConsegnaType }
     * 
     */
    public RicevutaConsegnaType createRicevutaConsegnaType() {
        return new RicevutaConsegnaType();
    }

    /**
     * Create an instance of {@link NotificaMancataConsegnaType }
     * 
     */
    public NotificaMancataConsegnaType createNotificaMancataConsegnaType() {
        return new NotificaMancataConsegnaType();
    }

    /**
     * Create an instance of {@link NotificaScartoType }
     * 
     */
    public NotificaScartoType createNotificaScartoType() {
        return new NotificaScartoType();
    }

    /**
     * Create an instance of {@link AttestazioneTrasmissioneType }
     * 
     */
    public AttestazioneTrasmissioneType createAttestazioneTrasmissioneType() {
        return new AttestazioneTrasmissioneType();
    }

    /**
     * Create an instance of {@link MetadatiInvioFileType }
     * 
     */
    public MetadatiInvioFileType createMetadatiInvioFileType() {
        return new MetadatiInvioFileType();
    }

    /**
     * Create an instance of {@link RiferimentoArchivioType }
     * 
     */
    public RiferimentoArchivioType createRiferimentoArchivioType() {
        return new RiferimentoArchivioType();
    }

    /**
     * Create an instance of {@link ListaErroriType }
     * 
     */
    public ListaErroriType createListaErroriType() {
        return new ListaErroriType();
    }

    /**
     * Create an instance of {@link ErroreType }
     * 
     */
    public ErroreType createErroreType() {
        return new ErroreType();
    }

    /**
     * Create an instance of {@link RiceventeType }
     * 
     */
    public RiceventeType createRiceventeType() {
        return new RiceventeType();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RicevutaConsegnaType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link RicevutaConsegnaType }{@code >}
     */
    @XmlElementDecl(namespace = "http://nso.rgs.mef.gov.it/docs/xsd/ordini/messaggi/v1.0", name = "RicevutaConsegna")
    public JAXBElement<RicevutaConsegnaType> createRicevutaConsegna(RicevutaConsegnaType value) {
        return new JAXBElement<RicevutaConsegnaType>(_RicevutaConsegna_QNAME, RicevutaConsegnaType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link NotificaMancataConsegnaType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link NotificaMancataConsegnaType }{@code >}
     */
    @XmlElementDecl(namespace = "http://nso.rgs.mef.gov.it/docs/xsd/ordini/messaggi/v1.0", name = "NotificaMancataConsegna")
    public JAXBElement<NotificaMancataConsegnaType> createNotificaMancataConsegna(NotificaMancataConsegnaType value) {
        return new JAXBElement<NotificaMancataConsegnaType>(_NotificaMancataConsegna_QNAME, NotificaMancataConsegnaType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link NotificaScartoType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link NotificaScartoType }{@code >}
     */
    @XmlElementDecl(namespace = "http://nso.rgs.mef.gov.it/docs/xsd/ordini/messaggi/v1.0", name = "NotificaScarto")
    public JAXBElement<NotificaScartoType> createNotificaScarto(NotificaScartoType value) {
        return new JAXBElement<NotificaScartoType>(_NotificaScarto_QNAME, NotificaScartoType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AttestazioneTrasmissioneType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link AttestazioneTrasmissioneType }{@code >}
     */
    @XmlElementDecl(namespace = "http://nso.rgs.mef.gov.it/docs/xsd/ordini/messaggi/v1.0", name = "AttestazioneTrasmissione")
    public JAXBElement<AttestazioneTrasmissioneType> createAttestazioneTrasmissione(AttestazioneTrasmissioneType value) {
        return new JAXBElement<AttestazioneTrasmissioneType>(_AttestazioneTrasmissione_QNAME, AttestazioneTrasmissioneType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MetadatiInvioFileType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link MetadatiInvioFileType }{@code >}
     */
    @XmlElementDecl(namespace = "http://nso.rgs.mef.gov.it/docs/xsd/ordini/messaggi/v1.0", name = "MetadatiInvioFile")
    public JAXBElement<MetadatiInvioFileType> createMetadatiInvioFile(MetadatiInvioFileType value) {
        return new JAXBElement<MetadatiInvioFileType>(_MetadatiInvioFile_QNAME, MetadatiInvioFileType.class, null, value);
    }

}
