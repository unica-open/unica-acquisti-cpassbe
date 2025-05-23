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

package it.doqui.acta.acaris.archive;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * &lt;p&gt;Classe Java per enumEsitoPassoRapportoVerificaType.
 * 
 * &lt;p&gt;Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * &lt;pre&gt;
 * &amp;lt;simpleType name="enumEsitoPassoRapportoVerificaType"&amp;gt;
 *   &amp;lt;restriction base="{common.acaris.acta.doqui.it}string"&amp;gt;
 *     &amp;lt;enumeration value="NonOk"/&amp;gt;
 *     &amp;lt;enumeration value="Ok"/&amp;gt;
 *     &amp;lt;enumeration value="DaVerificare"/&amp;gt;
 *   &amp;lt;/restriction&amp;gt;
 * &amp;lt;/simpleType&amp;gt;
 * &lt;/pre&gt;
 * 
 */
@XmlType(name = "enumEsitoPassoRapportoVerificaType")
@XmlEnum
public enum EnumEsitoPassoRapportoVerificaType {

    @XmlEnumValue("NonOk")
    NON_OK("NonOk"),
    @XmlEnumValue("Ok")
    OK("Ok"),
    @XmlEnumValue("DaVerificare")
    DA_VERIFICARE("DaVerificare");
    private final String value;

    EnumEsitoPassoRapportoVerificaType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static EnumEsitoPassoRapportoVerificaType fromValue(String v) {
        for (EnumEsitoPassoRapportoVerificaType c: EnumEsitoPassoRapportoVerificaType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
