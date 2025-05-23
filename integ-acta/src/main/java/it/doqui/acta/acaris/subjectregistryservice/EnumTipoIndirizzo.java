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
 * &lt;p&gt;Classe Java per enumTipoIndirizzo.
 * 
 * &lt;p&gt;Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * &lt;pre&gt;
 * &amp;lt;simpleType name="enumTipoIndirizzo"&amp;gt;
 *   &amp;lt;restriction base="{common.acaris.acta.doqui.it}string"&amp;gt;
 *     &amp;lt;enumeration value="DomicilioAnagrafico"/&amp;gt;
 *     &amp;lt;enumeration value="DomicilioFiscale"/&amp;gt;
 *     &amp;lt;enumeration value="Residenza"/&amp;gt;
 *     &amp;lt;enumeration value="Lavoro"/&amp;gt;
 *   &amp;lt;/restriction&amp;gt;
 * &amp;lt;/simpleType&amp;gt;
 * &lt;/pre&gt;
 * 
 */
@XmlType(name = "enumTipoIndirizzo")
@XmlEnum
public enum EnumTipoIndirizzo {

    @XmlEnumValue("DomicilioAnagrafico")
    DOMICILIO_ANAGRAFICO("DomicilioAnagrafico"),
    @XmlEnumValue("DomicilioFiscale")
    DOMICILIO_FISCALE("DomicilioFiscale"),
    @XmlEnumValue("Residenza")
    RESIDENZA("Residenza"),
    @XmlEnumValue("Lavoro")
    LAVORO("Lavoro");
    private final String value;

    EnumTipoIndirizzo(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static EnumTipoIndirizzo fromValue(String v) {
        for (EnumTipoIndirizzo c: EnumTipoIndirizzo.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
