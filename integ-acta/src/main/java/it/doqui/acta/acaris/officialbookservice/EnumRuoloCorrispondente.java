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
 * &lt;p&gt;Classe Java per enumRuoloCorrispondente.
 * 
 * &lt;p&gt;Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * &lt;pre&gt;
 * &amp;lt;simpleType name="enumRuoloCorrispondente"&amp;gt;
 *   &amp;lt;restriction base="{common.acaris.acta.doqui.it}string"&amp;gt;
 *     &amp;lt;enumeration value="PerCompetenza"/&amp;gt;
 *     &amp;lt;enumeration value="PerQuantoDiCompetenza"/&amp;gt;
 *     &amp;lt;enumeration value="PerConoscenza"/&amp;gt;
 *   &amp;lt;/restriction&amp;gt;
 * &amp;lt;/simpleType&amp;gt;
 * &lt;/pre&gt;
 * 
 */
@XmlType(name = "enumRuoloCorrispondente")
@XmlEnum
public enum EnumRuoloCorrispondente {

    @XmlEnumValue("PerCompetenza")
    PER_COMPETENZA("PerCompetenza"),
    @XmlEnumValue("PerQuantoDiCompetenza")
    PER_QUANTO_DI_COMPETENZA("PerQuantoDiCompetenza"),
    @XmlEnumValue("PerConoscenza")
    PER_CONOSCENZA("PerConoscenza");
    private final String value;

    EnumRuoloCorrispondente(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static EnumRuoloCorrispondente fromValue(String v) {
        for (EnumRuoloCorrispondente c: EnumRuoloCorrispondente.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
