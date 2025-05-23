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
 * &lt;p&gt;Classe Java per enumTipoFirmaDigitaleType.
 * 
 * &lt;p&gt;Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * &lt;pre&gt;
 * &amp;lt;simpleType name="enumTipoFirmaDigitaleType"&amp;gt;
 *   &amp;lt;restriction base="{common.acaris.acta.doqui.it}string"&amp;gt;
 *     &amp;lt;enumeration value="FirmaSemplice"/&amp;gt;
 *     &amp;lt;enumeration value="FirmaMultiplaParallela"/&amp;gt;
 *     &amp;lt;enumeration value="FirmaMultiplaControfirma"/&amp;gt;
 *     &amp;lt;enumeration value="FirmaMultiplaCatena"/&amp;gt;
 *     &amp;lt;enumeration value="MarcaTemporale"/&amp;gt;
 *   &amp;lt;/restriction&amp;gt;
 * &amp;lt;/simpleType&amp;gt;
 * &lt;/pre&gt;
 * 
 */
@XmlType(name = "enumTipoFirmaDigitaleType")
@XmlEnum
public enum EnumTipoFirmaDigitaleType {

    @XmlEnumValue("FirmaSemplice")
    FIRMA_SEMPLICE("FirmaSemplice"),
    @XmlEnumValue("FirmaMultiplaParallela")
    FIRMA_MULTIPLA_PARALLELA("FirmaMultiplaParallela"),
    @XmlEnumValue("FirmaMultiplaControfirma")
    FIRMA_MULTIPLA_CONTROFIRMA("FirmaMultiplaControfirma"),
    @XmlEnumValue("FirmaMultiplaCatena")
    FIRMA_MULTIPLA_CATENA("FirmaMultiplaCatena"),
    @XmlEnumValue("MarcaTemporale")
    MARCA_TEMPORALE("MarcaTemporale");
    private final String value;

    EnumTipoFirmaDigitaleType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static EnumTipoFirmaDigitaleType fromValue(String v) {
        for (EnumTipoFirmaDigitaleType c: EnumTipoFirmaDigitaleType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
