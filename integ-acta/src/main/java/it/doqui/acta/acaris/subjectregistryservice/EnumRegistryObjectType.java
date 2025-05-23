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
 * &lt;p&gt;Classe Java per enumRegistryObjectType.
 * 
 * &lt;p&gt;Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * &lt;pre&gt;
 * &amp;lt;simpleType name="enumRegistryObjectType"&amp;gt;
 *   &amp;lt;restriction base="{common.acaris.acta.doqui.it}string"&amp;gt;
 *     &amp;lt;enumeration value="SoggettoPropertiesType"/&amp;gt;
 *     &amp;lt;enumeration value="IndirizzoPropertiesType"/&amp;gt;
 *     &amp;lt;enumeration value="LogAnagraficaPropertiesType"/&amp;gt;
 *     &amp;lt;enumeration value="CategoriaAnagraficaPropertiesType"/&amp;gt;
 *     &amp;lt;enumeration value="HistorySoggettiEsterniPropertiesType"/&amp;gt;
 *   &amp;lt;/restriction&amp;gt;
 * &amp;lt;/simpleType&amp;gt;
 * &lt;/pre&gt;
 * 
 */
@XmlType(name = "enumRegistryObjectType")
@XmlEnum
public enum EnumRegistryObjectType {

    @XmlEnumValue("SoggettoPropertiesType")
    SOGGETTO_PROPERTIES_TYPE("SoggettoPropertiesType"),
    @XmlEnumValue("IndirizzoPropertiesType")
    INDIRIZZO_PROPERTIES_TYPE("IndirizzoPropertiesType"),
    @XmlEnumValue("LogAnagraficaPropertiesType")
    LOG_ANAGRAFICA_PROPERTIES_TYPE("LogAnagraficaPropertiesType"),
    @XmlEnumValue("CategoriaAnagraficaPropertiesType")
    CATEGORIA_ANAGRAFICA_PROPERTIES_TYPE("CategoriaAnagraficaPropertiesType"),
    @XmlEnumValue("HistorySoggettiEsterniPropertiesType")
    HISTORY_SOGGETTI_ESTERNI_PROPERTIES_TYPE("HistorySoggettiEsterniPropertiesType");
    private final String value;

    EnumRegistryObjectType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static EnumRegistryObjectType fromValue(String v) {
        for (EnumRegistryObjectType c: EnumRegistryObjectType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
