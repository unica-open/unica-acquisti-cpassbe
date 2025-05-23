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

package it.doqui.acta.acaris.officialbookservice;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * &lt;p&gt;Classe Java per enumTipoPFPG.
 * 
 * &lt;p&gt;Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * &lt;pre&gt;
 * &amp;lt;simpleType name="enumTipoPFPG"&amp;gt;
 *   &amp;lt;restriction base="{common.acaris.acta.doqui.it}string"&amp;gt;
 *     &amp;lt;enumeration value="PersonaFisica"/&amp;gt;
 *     &amp;lt;enumeration value="PersonaGiuridica"/&amp;gt;
 *   &amp;lt;/restriction&amp;gt;
 * &amp;lt;/simpleType&amp;gt;
 * &lt;/pre&gt;
 * 
 */
@XmlType(name = "enumTipoPFPG")
@XmlEnum
public enum EnumTipoPFPG {

    @XmlEnumValue("PersonaFisica")
    PERSONA_FISICA("PersonaFisica"),
    @XmlEnumValue("PersonaGiuridica")
    PERSONA_GIURIDICA("PersonaGiuridica");
    private final String value;

    EnumTipoPFPG(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static EnumTipoPFPG fromValue(String v) {
        for (EnumTipoPFPG c: EnumTipoPFPG.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
