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
 * &lt;p&gt;Classe Java per enumTipologiaSoggettoAssociato.
 * 
 * &lt;p&gt;Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * &lt;pre&gt;
 * &amp;lt;simpleType name="enumTipologiaSoggettoAssociato"&amp;gt;
 *   &amp;lt;restriction base="{common.acaris.acta.doqui.it}string"&amp;gt;
 *     &amp;lt;enumeration value="SoggettoActa"/&amp;gt;
 *     &amp;lt;enumeration value="Struttura"/&amp;gt;
 *     &amp;lt;enumeration value="Nodo"/&amp;gt;
 *     &amp;lt;enumeration value="Utente"/&amp;gt;
 *   &amp;lt;/restriction&amp;gt;
 * &amp;lt;/simpleType&amp;gt;
 * &lt;/pre&gt;
 * 
 */
@XmlType(name = "enumTipologiaSoggettoAssociato")
@XmlEnum
public enum EnumTipologiaSoggettoAssociato {

    @XmlEnumValue("SoggettoActa")
    SOGGETTO_ACTA("SoggettoActa"),
    @XmlEnumValue("Struttura")
    STRUTTURA("Struttura"),
    @XmlEnumValue("Nodo")
    NODO("Nodo"),
    @XmlEnumValue("Utente")
    UTENTE("Utente");
    private final String value;

    EnumTipologiaSoggettoAssociato(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static EnumTipologiaSoggettoAssociato fromValue(String v) {
        for (EnumTipologiaSoggettoAssociato c: EnumTipologiaSoggettoAssociato.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
