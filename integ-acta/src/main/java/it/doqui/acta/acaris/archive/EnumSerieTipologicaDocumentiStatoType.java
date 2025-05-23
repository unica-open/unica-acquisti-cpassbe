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
 * &lt;p&gt;Classe Java per enumSerieTipologicaDocumentiStatoType.
 * 
 * &lt;p&gt;Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * &lt;pre&gt;
 * &amp;lt;simpleType name="enumSerieTipologicaDocumentiStatoType"&amp;gt;
 *   &amp;lt;restriction base="{common.acaris.acta.doqui.it}string"&amp;gt;
 *     &amp;lt;enumeration value="Attiva"/&amp;gt;
 *     &amp;lt;enumeration value="Disattiva"/&amp;gt;
 *     &amp;lt;enumeration value="Congelata"/&amp;gt;
 *     &amp;lt;enumeration value="ChiusaInCorrente"/&amp;gt;
 *     &amp;lt;enumeration value="ChiusaInDeposito"/&amp;gt;
 *     &amp;lt;enumeration value="Cancellata"/&amp;gt;
 *   &amp;lt;/restriction&amp;gt;
 * &amp;lt;/simpleType&amp;gt;
 * &lt;/pre&gt;
 * 
 */
@XmlType(name = "enumSerieTipologicaDocumentiStatoType")
@XmlEnum
public enum EnumSerieTipologicaDocumentiStatoType {

    @XmlEnumValue("Attiva")
    ATTIVA("Attiva"),
    @XmlEnumValue("Disattiva")
    DISATTIVA("Disattiva"),
    @XmlEnumValue("Congelata")
    CONGELATA("Congelata"),
    @XmlEnumValue("ChiusaInCorrente")
    CHIUSA_IN_CORRENTE("ChiusaInCorrente"),
    @XmlEnumValue("ChiusaInDeposito")
    CHIUSA_IN_DEPOSITO("ChiusaInDeposito"),
    @XmlEnumValue("Cancellata")
    CANCELLATA("Cancellata");
    private final String value;

    EnumSerieTipologicaDocumentiStatoType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static EnumSerieTipologicaDocumentiStatoType fromValue(String v) {
        for (EnumSerieTipologicaDocumentiStatoType c: EnumSerieTipologicaDocumentiStatoType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
