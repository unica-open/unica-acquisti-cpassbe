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
 * &lt;p&gt;Classe Java per enumTipoIndirizzoFonteEsterna.
 * 
 * &lt;p&gt;Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * &lt;pre&gt;
 * &amp;lt;simpleType name="enumTipoIndirizzoFonteEsterna"&amp;gt;
 *   &amp;lt;restriction base="{common.acaris.acta.doqui.it}string"&amp;gt;
 *     &amp;lt;enumeration value="DOMICILIO FISCALE"/&amp;gt;
 *     &amp;lt;enumeration value="RESIDENZA"/&amp;gt;
 *     &amp;lt;enumeration value="SEDE LEGALE"/&amp;gt;
 *   &amp;lt;/restriction&amp;gt;
 * &amp;lt;/simpleType&amp;gt;
 * &lt;/pre&gt;
 * 
 */
@XmlType(name = "enumTipoIndirizzoFonteEsterna")
@XmlEnum
public enum EnumTipoIndirizzoFonteEsterna {

    @XmlEnumValue("DOMICILIO FISCALE")
    DOMICILIO_FISCALE("DOMICILIO FISCALE"),
    RESIDENZA("RESIDENZA"),
    @XmlEnumValue("SEDE LEGALE")
    SEDE_LEGALE("SEDE LEGALE");
    private final String value;

    EnumTipoIndirizzoFonteEsterna(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static EnumTipoIndirizzoFonteEsterna fromValue(String v) {
        for (EnumTipoIndirizzoFonteEsterna c: EnumTipoIndirizzoFonteEsterna.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
