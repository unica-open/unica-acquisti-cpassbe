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

package it.doqui.acta.acaris.subjectregistryservice;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * &lt;p&gt;Classe Java per enumTipologiaCreazioneSoggetto.
 * 
 * &lt;p&gt;Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * &lt;pre&gt;
 * &amp;lt;simpleType name="enumTipologiaCreazioneSoggetto"&amp;gt;
 *   &amp;lt;restriction base="{common.acaris.acta.doqui.it}string"&amp;gt;
 *     &amp;lt;enumeration value="SoggettoProvvisorio"/&amp;gt;
 *     &amp;lt;enumeration value="SoggettoDefinitivo"/&amp;gt;
 *   &amp;lt;/restriction&amp;gt;
 * &amp;lt;/simpleType&amp;gt;
 * &lt;/pre&gt;
 * 
 */
@XmlType(name = "enumTipologiaCreazioneSoggetto")
@XmlEnum
public enum EnumTipologiaCreazioneSoggetto {

    @XmlEnumValue("SoggettoProvvisorio")
    SOGGETTO_PROVVISORIO("SoggettoProvvisorio"),
    @XmlEnumValue("SoggettoDefinitivo")
    SOGGETTO_DEFINITIVO("SoggettoDefinitivo");
    private final String value;

    EnumTipologiaCreazioneSoggetto(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static EnumTipologiaCreazioneSoggetto fromValue(String v) {
        for (EnumTipologiaCreazioneSoggetto c: EnumTipologiaCreazioneSoggetto.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
