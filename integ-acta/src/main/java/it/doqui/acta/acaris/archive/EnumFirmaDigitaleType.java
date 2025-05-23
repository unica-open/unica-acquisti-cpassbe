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
 * &lt;p&gt;Classe Java per enumFirmaDigitaleType.
 * 
 * &lt;p&gt;Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * &lt;pre&gt;
 * &amp;lt;simpleType name="enumFirmaDigitaleType"&amp;gt;
 *   &amp;lt;restriction base="{common.acaris.acta.doqui.it}string"&amp;gt;
 *     &amp;lt;enumeration value="NA"/&amp;gt;
 *     &amp;lt;enumeration value="MarcaDetached"/&amp;gt;
 *     &amp;lt;enumeration value="Enveloped"/&amp;gt;
 *     &amp;lt;enumeration value="EnvelopedMultipart"/&amp;gt;
 *     &amp;lt;enumeration value="FirmaDetached"/&amp;gt;
 *   &amp;lt;/restriction&amp;gt;
 * &amp;lt;/simpleType&amp;gt;
 * &lt;/pre&gt;
 * 
 */
@XmlType(name = "enumFirmaDigitaleType")
@XmlEnum
public enum EnumFirmaDigitaleType {

    NA("NA"),
    @XmlEnumValue("MarcaDetached")
    MARCA_DETACHED("MarcaDetached"),
    @XmlEnumValue("Enveloped")
    ENVELOPED("Enveloped"),
    @XmlEnumValue("EnvelopedMultipart")
    ENVELOPED_MULTIPART("EnvelopedMultipart"),
    @XmlEnumValue("FirmaDetached")
    FIRMA_DETACHED("FirmaDetached");
    private final String value;

    EnumFirmaDigitaleType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static EnumFirmaDigitaleType fromValue(String v) {
        for (EnumFirmaDigitaleType c: EnumFirmaDigitaleType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
