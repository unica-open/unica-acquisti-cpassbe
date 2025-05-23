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

package it.doqui.acta.acaris.common;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * &lt;p&gt;Classe Java per enumCommonObjectType.
 * 
 * &lt;p&gt;Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * &lt;pre&gt;
 * &amp;lt;simpleType name="enumCommonObjectType"&amp;gt;
 *   &amp;lt;restriction base="{common.acaris.acta.doqui.it}string"&amp;gt;
 *     &amp;lt;enumeration value="AnnotazioniPropertiesType"/&amp;gt;
 *     &amp;lt;enumeration value="ProtocolloPropertiesType"/&amp;gt;
 *   &amp;lt;/restriction&amp;gt;
 * &amp;lt;/simpleType&amp;gt;
 * &lt;/pre&gt;
 * 
 */
@XmlType(name = "enumCommonObjectType")
@XmlEnum
public enum EnumCommonObjectType {

    @XmlEnumValue("AnnotazioniPropertiesType")
    ANNOTAZIONI_PROPERTIES_TYPE("AnnotazioniPropertiesType"),
    @XmlEnumValue("ProtocolloPropertiesType")
    PROTOCOLLO_PROPERTIES_TYPE("ProtocolloPropertiesType");
    private final String value;

    EnumCommonObjectType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static EnumCommonObjectType fromValue(String v) {
        for (EnumCommonObjectType c: EnumCommonObjectType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
