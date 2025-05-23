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
 * &lt;p&gt;Classe Java per enumPolicyObjectType.
 * 
 * &lt;p&gt;Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * &lt;pre&gt;
 * &amp;lt;simpleType name="enumPolicyObjectType"&amp;gt;
 *   &amp;lt;restriction base="{common.acaris.acta.doqui.it}string"&amp;gt;
 *     &amp;lt;enumeration value="ActaACEPropertiesType"/&amp;gt;
 *     &amp;lt;enumeration value="PrincipalId"/&amp;gt;
 *   &amp;lt;/restriction&amp;gt;
 * &amp;lt;/simpleType&amp;gt;
 * &lt;/pre&gt;
 * 
 */
@XmlType(name = "enumPolicyObjectType")
@XmlEnum
public enum EnumPolicyObjectType {

    @XmlEnumValue("ActaACEPropertiesType")
    ACTA_ACE_PROPERTIES_TYPE("ActaACEPropertiesType"),
    @XmlEnumValue("PrincipalId")
    PRINCIPAL_ID("PrincipalId");
    private final String value;

    EnumPolicyObjectType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static EnumPolicyObjectType fromValue(String v) {
        for (EnumPolicyObjectType c: EnumPolicyObjectType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
