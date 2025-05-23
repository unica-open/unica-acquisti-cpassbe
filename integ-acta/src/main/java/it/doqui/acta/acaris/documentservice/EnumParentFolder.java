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

package it.doqui.acta.acaris.documentservice;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * &lt;p&gt;Classe Java per enumParentFolder.
 * 
 * &lt;p&gt;Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * &lt;pre&gt;
 * &amp;lt;simpleType name="enumParentFolder"&amp;gt;
 *   &amp;lt;restriction base="{common.acaris.acta.doqui.it}string"&amp;gt;
 *     &amp;lt;enumeration value="Aggregazione"/&amp;gt;
 *     &amp;lt;enumeration value="Classificazione.GruppoAllegati"/&amp;gt;
 *     &amp;lt;enumeration value="fascicoloTemporaneo"/&amp;gt;
 *   &amp;lt;/restriction&amp;gt;
 * &amp;lt;/simpleType&amp;gt;
 * &lt;/pre&gt;
 * 
 */
@XmlType(name = "enumParentFolder")
@XmlEnum
public enum EnumParentFolder {

    @XmlEnumValue("Aggregazione")
    AGGREGAZIONE("Aggregazione"),
    @XmlEnumValue("Classificazione.GruppoAllegati")
    CLASSIFICAZIONE_GRUPPO_ALLEGATI("Classificazione.GruppoAllegati"),
    @XmlEnumValue("fascicoloTemporaneo")
    FASCICOLO_TEMPORANEO("fascicoloTemporaneo");
    private final String value;

    EnumParentFolder(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static EnumParentFolder fromValue(String v) {
        for (EnumParentFolder c: EnumParentFolder.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
