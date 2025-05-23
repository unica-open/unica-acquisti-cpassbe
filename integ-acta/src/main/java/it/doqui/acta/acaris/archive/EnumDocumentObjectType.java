/*-
 * ========================LICENSE_START=================================
 * CPASS BackEnd - INTEGRATION submodule - ACTA
 * %%
 * Copyright (C) 2019 - 2023 CSI Piemonte
 * %%
 * SPDX-FileCopyrightText: Copyright 2019 - 2023 | CSI Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 * =========================LICENSE_END==================================
 */

package it.doqui.acta.acaris.archive;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * &lt;p&gt;Classe Java per enumDocumentObjectType.
 * 
 * &lt;p&gt;Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * &lt;pre&gt;
 * &amp;lt;simpleType name="enumDocumentObjectType"&amp;gt;
 *   &amp;lt;restriction base="{common.acaris.acta.doqui.it}string"&amp;gt;
 *     &amp;lt;enumeration value="ContenutoFisicoPropertiesType"/&amp;gt;
 *     &amp;lt;enumeration value="DocumentoDBPropertiesType"/&amp;gt;
 *     &amp;lt;enumeration value="DocumentoRegistroPropertiesType"/&amp;gt;
 *     &amp;lt;enumeration value="DocumentoSemplicePropertiesType"/&amp;gt;
 *     &amp;lt;enumeration value="ClipsMetallicaPropertiesType"/&amp;gt;
 *   &amp;lt;/restriction&amp;gt;
 * &amp;lt;/simpleType&amp;gt;
 * &lt;/pre&gt;
 * 
 */
@XmlType(name = "enumDocumentObjectType")
@XmlEnum
public enum EnumDocumentObjectType {

    @XmlEnumValue("ContenutoFisicoPropertiesType")
    CONTENUTO_FISICO_PROPERTIES_TYPE("ContenutoFisicoPropertiesType"),
    @XmlEnumValue("DocumentoDBPropertiesType")
    DOCUMENTO_DB_PROPERTIES_TYPE("DocumentoDBPropertiesType"),
    @XmlEnumValue("DocumentoRegistroPropertiesType")
    DOCUMENTO_REGISTRO_PROPERTIES_TYPE("DocumentoRegistroPropertiesType"),
    @XmlEnumValue("DocumentoSemplicePropertiesType")
    DOCUMENTO_SEMPLICE_PROPERTIES_TYPE("DocumentoSemplicePropertiesType"),
    @XmlEnumValue("ClipsMetallicaPropertiesType")
    CLIPS_METALLICA_PROPERTIES_TYPE("ClipsMetallicaPropertiesType");
    private final String value;

    EnumDocumentObjectType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static EnumDocumentObjectType fromValue(String v) {
        for (EnumDocumentObjectType c: EnumDocumentObjectType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
