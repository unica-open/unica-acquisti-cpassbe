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
 * &lt;p&gt;Classe Java per enumTipoCorrispondente.
 * 
 * &lt;p&gt;Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * &lt;pre&gt;
 * &amp;lt;simpleType name="enumTipoCorrispondente"&amp;gt;
 *   &amp;lt;restriction base="{common.acaris.acta.doqui.it}string"&amp;gt;
 *     &amp;lt;enumeration value="Mittente"/&amp;gt;
 *     &amp;lt;enumeration value="Destinatario"/&amp;gt;
 *   &amp;lt;/restriction&amp;gt;
 * &amp;lt;/simpleType&amp;gt;
 * &lt;/pre&gt;
 * 
 */
@XmlType(name = "enumTipoCorrispondente")
@XmlEnum
public enum EnumTipoCorrispondente {

    @XmlEnumValue("Mittente")
    MITTENTE("Mittente"),
    @XmlEnumValue("Destinatario")
    DESTINATARIO("Destinatario");
    private final String value;

    EnumTipoCorrispondente(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static EnumTipoCorrispondente fromValue(String v) {
        for (EnumTipoCorrispondente c: EnumTipoCorrispondente.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
