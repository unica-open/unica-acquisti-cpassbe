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
 * &lt;p&gt;Classe Java per enumSesso.
 * 
 * &lt;p&gt;Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * &lt;pre&gt;
 * &amp;lt;simpleType name="enumSesso"&amp;gt;
 *   &amp;lt;restriction base="{common.acaris.acta.doqui.it}string"&amp;gt;
 *     &amp;lt;enumeration value="Maschio"/&amp;gt;
 *     &amp;lt;enumeration value="Femmina"/&amp;gt;
 *     &amp;lt;enumeration value="Irrilevante"/&amp;gt;
 *   &amp;lt;/restriction&amp;gt;
 * &amp;lt;/simpleType&amp;gt;
 * &lt;/pre&gt;
 * 
 */
@XmlType(name = "enumSesso")
@XmlEnum
public enum EnumSesso {

    @XmlEnumValue("Maschio")
    MASCHIO("Maschio"),
    @XmlEnumValue("Femmina")
    FEMMINA("Femmina"),
    @XmlEnumValue("Irrilevante")
    IRRILEVANTE("Irrilevante");
    private final String value;

    EnumSesso(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static EnumSesso fromValue(String v) {
        for (EnumSesso c: EnumSesso.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
