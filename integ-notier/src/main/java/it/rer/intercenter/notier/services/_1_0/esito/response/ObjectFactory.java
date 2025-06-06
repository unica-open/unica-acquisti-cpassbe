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

package it.rer.intercenter.notier.services._1_0.esito.response;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the it.rer.intercenter.notier.services._1_0.esito.response package. 
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


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: it.rer.intercenter.notier.services._1_0.esito.response
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link EsitoRichiesta }
     * 
     */
    public EsitoRichiesta createEsitoRichiesta() {
        return new EsitoRichiesta();
    }

    /**
     * Create an instance of {@link EsitoType }
     * 
     */
    public EsitoType createEsitoType() {
        return new EsitoType();
    }

    /**
     * Create an instance of {@link DocumentoType }
     * 
     */
    public DocumentoType createDocumentoType() {
        return new DocumentoType();
    }

    /**
     * Create an instance of {@link ListaDocumentiType }
     * 
     */
    public ListaDocumentiType createListaDocumentiType() {
        return new ListaDocumentiType();
    }

    /**
     * Create an instance of {@link DocumentiType }
     * 
     */
    public DocumentiType createDocumentiType() {
        return new DocumentiType();
    }

    /**
     * Create an instance of {@link ChiaveDocumentoType }
     * 
     */
    public ChiaveDocumentoType createChiaveDocumentoType() {
        return new ChiaveDocumentoType();
    }

    /**
     * Create an instance of {@link RappresentazioneType }
     * 
     */
    public RappresentazioneType createRappresentazioneType() {
        return new RappresentazioneType();
    }

    /**
     * Create an instance of {@link UrnCollegatiType }
     * 
     */
    public UrnCollegatiType createUrnCollegatiType() {
        return new UrnCollegatiType();
    }

}
