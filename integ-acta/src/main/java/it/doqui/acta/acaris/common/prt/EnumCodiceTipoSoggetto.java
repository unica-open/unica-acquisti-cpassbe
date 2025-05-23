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

package it.doqui.acta.acaris.common.prt;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * &lt;p&gt;Classe Java per enumCodiceTipoSoggetto.
 * 
 * &lt;p&gt;Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * &lt;pre&gt;
 * &amp;lt;simpleType name="enumCodiceTipoSoggetto"&amp;gt;
 *   &amp;lt;restriction base="{common.acaris.acta.doqui.it}string"&amp;gt;
 *     &amp;lt;enumeration value="AOO"/&amp;gt;
 *     &amp;lt;enumeration value="STR"/&amp;gt;
 *     &amp;lt;enumeration value="NOD"/&amp;gt;
 *     &amp;lt;enumeration value="UT"/&amp;gt;
 *     &amp;lt;enumeration value="PF"/&amp;gt;
 *     &amp;lt;enumeration value="PG"/&amp;gt;
 *     &amp;lt;enumeration value="UL"/&amp;gt;
 *     &amp;lt;enumeration value="AOO-INPA"/&amp;gt;
 *     &amp;lt;enumeration value="DIP"/&amp;gt;
 *     &amp;lt;enumeration value="BA"/&amp;gt;
 *     &amp;lt;enumeration value="FIL"/&amp;gt;
 *     &amp;lt;enumeration value="ORG"/&amp;gt;
 *     &amp;lt;enumeration value="ALT"/&amp;gt;
 *   &amp;lt;/restriction&amp;gt;
 * &amp;lt;/simpleType&amp;gt;
 * &lt;/pre&gt;
 * 
 */
@XmlType(name = "enumCodiceTipoSoggetto", namespace = "prt.common.acaris.acta.doqui.it")
@XmlEnum
public enum EnumCodiceTipoSoggetto {

    AOO("AOO"),
    STR("STR"),
    NOD("NOD"),
    UT("UT"),
    PF("PF"),
    PG("PG"),
    UL("UL"),
    @XmlEnumValue("AOO-INPA")
    AOO_INPA("AOO-INPA"),
    DIP("DIP"),
    BA("BA"),
    FIL("FIL"),
    ORG("ORG"),
    ALT("ALT");
    private final String value;

    EnumCodiceTipoSoggetto(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static EnumCodiceTipoSoggetto fromValue(String v) {
        for (EnumCodiceTipoSoggetto c: EnumCodiceTipoSoggetto.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
