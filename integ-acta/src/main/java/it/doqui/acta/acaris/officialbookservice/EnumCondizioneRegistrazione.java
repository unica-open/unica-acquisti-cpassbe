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
 * &lt;p&gt;Classe Java per enumCondizioneRegistrazione.
 * 
 * &lt;p&gt;Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * &lt;pre&gt;
 * &amp;lt;simpleType name="enumCondizioneRegistrazione"&amp;gt;
 *   &amp;lt;restriction base="{common.acaris.acta.doqui.it}string"&amp;gt;
 *     &amp;lt;enumeration value="Registrazione"/&amp;gt;
 *     &amp;lt;enumeration value="Classificazione"/&amp;gt;
 *     &amp;lt;enumeration value="Smistamento"/&amp;gt;
 *     &amp;lt;enumeration value="ModificaDocumento"/&amp;gt;
 *   &amp;lt;/restriction&amp;gt;
 * &amp;lt;/simpleType&amp;gt;
 * &lt;/pre&gt;
 * 
 */
@XmlType(name = "enumCondizioneRegistrazione")
@XmlEnum
public enum EnumCondizioneRegistrazione {

    @XmlEnumValue("Registrazione")
    REGISTRAZIONE("Registrazione"),
    @XmlEnumValue("Classificazione")
    CLASSIFICAZIONE("Classificazione"),
    @XmlEnumValue("Smistamento")
    SMISTAMENTO("Smistamento"),
    @XmlEnumValue("ModificaDocumento")
    MODIFICA_DOCUMENTO("ModificaDocumento");
    private final String value;

    EnumCondizioneRegistrazione(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static EnumCondizioneRegistrazione fromValue(String v) {
        for (EnumCondizioneRegistrazione c: EnumCondizioneRegistrazione.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
