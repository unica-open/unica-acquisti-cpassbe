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
 * &lt;p&gt;Classe Java per enumTipologiaNumerazioneType.
 * 
 * &lt;p&gt;Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * &lt;pre&gt;
 * &amp;lt;simpleType name="enumTipologiaNumerazioneType"&amp;gt;
 *   &amp;lt;restriction base="{common.acaris.acta.doqui.it}string"&amp;gt;
 *     &amp;lt;enumeration value="Annuale"/&amp;gt;
 *     &amp;lt;enumeration value="Legislatura"/&amp;gt;
 *     &amp;lt;enumeration value="Libera"/&amp;gt;
 *     &amp;lt;enumeration value="Continua"/&amp;gt;
 *     &amp;lt;enumeration value="Ereditata"/&amp;gt;
 *   &amp;lt;/restriction&amp;gt;
 * &amp;lt;/simpleType&amp;gt;
 * &lt;/pre&gt;
 * 
 */
@XmlType(name = "enumTipologiaNumerazioneType")
@XmlEnum
public enum EnumTipologiaNumerazioneType {

    @XmlEnumValue("Annuale")
    ANNUALE("Annuale"),
    @XmlEnumValue("Legislatura")
    LEGISLATURA("Legislatura"),
    @XmlEnumValue("Libera")
    LIBERA("Libera"),
    @XmlEnumValue("Continua")
    CONTINUA("Continua"),
    @XmlEnumValue("Ereditata")
    EREDITATA("Ereditata");
    private final String value;

    EnumTipologiaNumerazioneType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static EnumTipologiaNumerazioneType fromValue(String v) {
        for (EnumTipologiaNumerazioneType c: EnumTipologiaNumerazioneType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
