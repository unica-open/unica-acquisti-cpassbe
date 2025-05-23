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
 * &lt;p&gt;Classe Java per enumDocPrimarioType.
 * 
 * &lt;p&gt;Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * &lt;pre&gt;
 * &amp;lt;simpleType name="enumDocPrimarioType"&amp;gt;
 *   &amp;lt;restriction base="{common.acaris.acta.doqui.it}string"&amp;gt;
 *     &amp;lt;enumeration value="SignatureDetached"/&amp;gt;
 *     &amp;lt;enumeration value="AllegatoXML"/&amp;gt;
 *     &amp;lt;enumeration value="DocumentoSingolo"/&amp;gt;
 *   &amp;lt;/restriction&amp;gt;
 * &amp;lt;/simpleType&amp;gt;
 * &lt;/pre&gt;
 * 
 */
@XmlType(name = "enumDocPrimarioType")
@XmlEnum
public enum EnumDocPrimarioType {

    @XmlEnumValue("SignatureDetached")
    SIGNATURE_DETACHED("SignatureDetached"),
    @XmlEnumValue("AllegatoXML")
    ALLEGATO_XML("AllegatoXML"),
    @XmlEnumValue("DocumentoSingolo")
    DOCUMENTO_SINGOLO("DocumentoSingolo");
    private final String value;

    EnumDocPrimarioType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static EnumDocPrimarioType fromValue(String v) {
        for (EnumDocPrimarioType c: EnumDocPrimarioType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
