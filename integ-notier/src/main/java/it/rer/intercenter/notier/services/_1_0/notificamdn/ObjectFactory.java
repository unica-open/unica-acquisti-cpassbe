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

package it.rer.intercenter.notier.services._1_0.notificamdn;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the it.rer.intercenter.notier.services._1_0.notificamdn package. 
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

    private final static QName _NotificaMDN_QNAME = new QName("http://notier.intercenter.rer.it/services/1.0/notificaMDN", "NotificaMDN");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: it.rer.intercenter.notier.services._1_0.notificamdn
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link NotificaMDNType }
     * 
     */
    public NotificaMDNType createNotificaMDNType() {
        return new NotificaMDNType();
    }

    /**
     * Create an instance of {@link EsitoType }
     * 
     */
    public EsitoType createEsitoType() {
        return new EsitoType();
    }

    /**
     * Create an instance of {@link DataOraTransitoDocumentoType }
     * 
     */
    public DataOraTransitoDocumentoType createDataOraTransitoDocumentoType() {
        return new DataOraTransitoDocumentoType();
    }

    /**
     * Create an instance of {@link EsitoTrasmissioneType }
     * 
     */
    public EsitoTrasmissioneType createEsitoTrasmissioneType() {
        return new EsitoTrasmissioneType();
    }

    /**
     * Create an instance of {@link IdentificativoDocumentoType }
     * 
     */
    public IdentificativoDocumentoType createIdentificativoDocumentoType() {
        return new IdentificativoDocumentoType();
    }

    /**
     * Create an instance of {@link IdentificativoTrasmissioneType }
     * 
     */
    public IdentificativoTrasmissioneType createIdentificativoTrasmissioneType() {
        return new IdentificativoTrasmissioneType();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link NotificaMDNType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link NotificaMDNType }{@code >}
     */
    @XmlElementDecl(namespace = "http://notier.intercenter.rer.it/services/1.0/notificaMDN", name = "NotificaMDN")
    public JAXBElement<NotificaMDNType> createNotificaMDN(NotificaMDNType value) {
        return new JAXBElement<NotificaMDNType>(_NotificaMDN_QNAME, NotificaMDNType.class, null, value);
    }

}
