/*-
 * ========================LICENSE_START=================================
 * CPASS BackEnd - INTEGRATION submodule - NotiER
 * %%
 * Copyright (C) 2019 - 2020 CSI Piemonte
 * %%
 * SPDX-FileCopyrightText: Copyright 2019 - 2020 | CSI Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 * =========================LICENSE_END==================================
 */

package oasis.names.specification.ubl.schema.xsd.commonsignaturecomponents_2;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the oasis.names.specification.ubl.schema.xsd.commonsignaturecomponents_2 package. 
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

    private final static QName _UBLDocumentSignatures_QNAME = new QName("urn:oasis:names:specification:ubl:schema:xsd:CommonSignatureComponents-2", "UBLDocumentSignatures");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: oasis.names.specification.ubl.schema.xsd.commonsignaturecomponents_2
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link UBLDocumentSignaturesType }
     * 
     */
    public UBLDocumentSignaturesType createUBLDocumentSignaturesType() {
        return new UBLDocumentSignaturesType();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UBLDocumentSignaturesType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link UBLDocumentSignaturesType }{@code >}
     */
    @XmlElementDecl(namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonSignatureComponents-2", name = "UBLDocumentSignatures")
    public JAXBElement<UBLDocumentSignaturesType> createUBLDocumentSignatures(UBLDocumentSignaturesType value) {
        return new JAXBElement<UBLDocumentSignaturesType>(_UBLDocumentSignatures_QNAME, UBLDocumentSignaturesType.class, null, value);
    }

}
