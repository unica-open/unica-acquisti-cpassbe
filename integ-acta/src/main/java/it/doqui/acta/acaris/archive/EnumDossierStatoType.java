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
 * &lt;p&gt;Classe Java per enumDossierStatoType.
 * 
 * &lt;p&gt;Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * &lt;pre&gt;
 * &amp;lt;simpleType name="enumDossierStatoType"&amp;gt;
 *   &amp;lt;restriction base="{common.acaris.acta.doqui.it}string"&amp;gt;
 *     &amp;lt;enumeration value="Aperto"/&amp;gt;
 *     &amp;lt;enumeration value="AttesaDiChiusura"/&amp;gt;
 *     &amp;lt;enumeration value="ChiusoInCorrente"/&amp;gt;
 *     &amp;lt;enumeration value="ChiusoInDeposito"/&amp;gt;
 *     &amp;lt;enumeration value="Congelato"/&amp;gt;
 *     &amp;lt;enumeration value="Spostato"/&amp;gt;
 *     &amp;lt;enumeration value="Scartato"/&amp;gt;
 *     &amp;lt;enumeration value="Cancellato"/&amp;gt;
 *   &amp;lt;/restriction&amp;gt;
 * &amp;lt;/simpleType&amp;gt;
 * &lt;/pre&gt;
 * 
 */
@XmlType(name = "enumDossierStatoType")
@XmlEnum
public enum EnumDossierStatoType {

    @XmlEnumValue("Aperto")
    APERTO("Aperto"),
    @XmlEnumValue("AttesaDiChiusura")
    ATTESA_DI_CHIUSURA("AttesaDiChiusura"),
    @XmlEnumValue("ChiusoInCorrente")
    CHIUSO_IN_CORRENTE("ChiusoInCorrente"),
    @XmlEnumValue("ChiusoInDeposito")
    CHIUSO_IN_DEPOSITO("ChiusoInDeposito"),
    @XmlEnumValue("Congelato")
    CONGELATO("Congelato"),
    @XmlEnumValue("Spostato")
    SPOSTATO("Spostato"),
    @XmlEnumValue("Scartato")
    SCARTATO("Scartato"),
    @XmlEnumValue("Cancellato")
    CANCELLATO("Cancellato");
    private final String value;

    EnumDossierStatoType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static EnumDossierStatoType fromValue(String v) {
        for (EnumDossierStatoType c: EnumDossierStatoType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
