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
 * &lt;p&gt;Classe Java per enumStatoRegistro.
 * 
 * &lt;p&gt;Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * &lt;pre&gt;
 * &amp;lt;simpleType name="enumStatoRegistro"&amp;gt;
 *   &amp;lt;restriction base="{common.acaris.acta.doqui.it}string"&amp;gt;
 *     &amp;lt;enumeration value="Bozza"/&amp;gt;
 *     &amp;lt;enumeration value="Aperto"/&amp;gt;
 *     &amp;lt;enumeration value="Chiuso"/&amp;gt;
 *     &amp;lt;enumeration value="Archiviato"/&amp;gt;
 *   &amp;lt;/restriction&amp;gt;
 * &amp;lt;/simpleType&amp;gt;
 * &lt;/pre&gt;
 * 
 */
@XmlType(name = "enumStatoRegistro")
@XmlEnum
public enum EnumStatoRegistro {

    @XmlEnumValue("Bozza")
    BOZZA("Bozza"),
    @XmlEnumValue("Aperto")
    APERTO("Aperto"),
    @XmlEnumValue("Chiuso")
    CHIUSO("Chiuso"),
    @XmlEnumValue("Archiviato")
    ARCHIVIATO("Archiviato");
    private final String value;

    EnumStatoRegistro(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static EnumStatoRegistro fromValue(String v) {
        for (EnumStatoRegistro c: EnumStatoRegistro.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
