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

package it.doqui.acta.acaris.management;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * &lt;p&gt;Classe Java per enumTipoProvvType.
 * 
 * &lt;p&gt;Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * &lt;pre&gt;
 * &amp;lt;simpleType name="enumTipoProvvType"&amp;gt;
 *   &amp;lt;restriction base="{common.acaris.acta.doqui.it}string"&amp;gt;
 *     &amp;lt;enumeration value="ProvvedimentoOriginatore"/&amp;gt;
 *     &amp;lt;enumeration value="ProvvedimentoDiModifica"/&amp;gt;
 *   &amp;lt;/restriction&amp;gt;
 * &amp;lt;/simpleType&amp;gt;
 * &lt;/pre&gt;
 * 
 */
@XmlType(name = "enumTipoProvvType")
@XmlEnum
public enum EnumTipoProvvType {

    @XmlEnumValue("ProvvedimentoOriginatore")
    PROVVEDIMENTO_ORIGINATORE("ProvvedimentoOriginatore"),
    @XmlEnumValue("ProvvedimentoDiModifica")
    PROVVEDIMENTO_DI_MODIFICA("ProvvedimentoDiModifica");
    private final String value;

    EnumTipoProvvType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static EnumTipoProvvType fromValue(String v) {
        for (EnumTipoProvvType c: EnumTipoProvvType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
