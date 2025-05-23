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
 * &lt;p&gt;Classe Java per enumTipoContenitore.
 * 
 * &lt;p&gt;Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * &lt;pre&gt;
 * &amp;lt;simpleType name="enumTipoContenitore"&amp;gt;
 *   &amp;lt;restriction base="{common.acaris.acta.doqui.it}string"&amp;gt;
 *     &amp;lt;enumeration value="fascicoloTemporaneoTitolario"/&amp;gt;
 *     &amp;lt;enumeration value="fascicoloTemporaneoVoce"/&amp;gt;
 *     &amp;lt;enumeration value="strutturaAggregativa"/&amp;gt;
 *   &amp;lt;/restriction&amp;gt;
 * &amp;lt;/simpleType&amp;gt;
 * &lt;/pre&gt;
 * 
 */
@XmlType(name = "enumTipoContenitore")
@XmlEnum
public enum EnumTipoContenitore {

    @XmlEnumValue("fascicoloTemporaneoTitolario")
    FASCICOLO_TEMPORANEO_TITOLARIO("fascicoloTemporaneoTitolario"),
    @XmlEnumValue("fascicoloTemporaneoVoce")
    FASCICOLO_TEMPORANEO_VOCE("fascicoloTemporaneoVoce"),
    @XmlEnumValue("strutturaAggregativa")
    STRUTTURA_AGGREGATIVA("strutturaAggregativa");
    private final String value;

    EnumTipoContenitore(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static EnumTipoContenitore fromValue(String v) {
        for (EnumTipoContenitore c: EnumTipoContenitore.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
