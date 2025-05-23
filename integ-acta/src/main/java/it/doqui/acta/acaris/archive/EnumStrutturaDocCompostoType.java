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
 * &lt;p&gt;Classe Java per enumStrutturaDocCompostoType.
 * 
 * &lt;p&gt;Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * &lt;pre&gt;
 * &amp;lt;simpleType name="enumStrutturaDocCompostoType"&amp;gt;
 *   &amp;lt;restriction base="{common.acaris.acta.doqui.it}string"&amp;gt;
 *     &amp;lt;enumeration value="NA"/&amp;gt;
 *     &amp;lt;enumeration value="DocumentoEngine"/&amp;gt;
 *     &amp;lt;enumeration value="DocumentoRendition"/&amp;gt;
 *   &amp;lt;/restriction&amp;gt;
 * &amp;lt;/simpleType&amp;gt;
 * &lt;/pre&gt;
 * 
 */
@XmlType(name = "enumStrutturaDocCompostoType")
@XmlEnum
public enum EnumStrutturaDocCompostoType {

    NA("NA"),
    @XmlEnumValue("DocumentoEngine")
    DOCUMENTO_ENGINE("DocumentoEngine"),
    @XmlEnumValue("DocumentoRendition")
    DOCUMENTO_RENDITION("DocumentoRendition");
    private final String value;

    EnumStrutturaDocCompostoType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static EnumStrutturaDocCompostoType fromValue(String v) {
        for (EnumStrutturaDocCompostoType c: EnumStrutturaDocCompostoType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
