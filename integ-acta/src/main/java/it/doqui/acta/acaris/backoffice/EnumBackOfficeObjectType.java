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

package it.doqui.acta.acaris.backoffice;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * &lt;p&gt;Classe Java per enumBackOfficeObjectType.
 * 
 * &lt;p&gt;Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * &lt;pre&gt;
 * &amp;lt;simpleType name="enumBackOfficeObjectType"&amp;gt;
 *   &amp;lt;restriction base="{common.acaris.acta.doqui.it}string"&amp;gt;
 *     &amp;lt;enumeration value="EntePropertiesType"/&amp;gt;
 *     &amp;lt;enumeration value="GruppoAOOPropertiesType"/&amp;gt;
 *     &amp;lt;enumeration value="AOOPropertiesType"/&amp;gt;
 *     &amp;lt;enumeration value="StrutturaPropertiesType"/&amp;gt;
 *     &amp;lt;enumeration value="NodoPropertiesType"/&amp;gt;
 *     &amp;lt;enumeration value="UtentePropertiesType"/&amp;gt;
 *   &amp;lt;/restriction&amp;gt;
 * &amp;lt;/simpleType&amp;gt;
 * &lt;/pre&gt;
 * 
 */
@XmlType(name = "enumBackOfficeObjectType")
@XmlEnum
public enum EnumBackOfficeObjectType {

    @XmlEnumValue("EntePropertiesType")
    ENTE_PROPERTIES_TYPE("EntePropertiesType"),
    @XmlEnumValue("GruppoAOOPropertiesType")
    GRUPPO_AOO_PROPERTIES_TYPE("GruppoAOOPropertiesType"),
    @XmlEnumValue("AOOPropertiesType")
    AOO_PROPERTIES_TYPE("AOOPropertiesType"),
    @XmlEnumValue("StrutturaPropertiesType")
    STRUTTURA_PROPERTIES_TYPE("StrutturaPropertiesType"),
    @XmlEnumValue("NodoPropertiesType")
    NODO_PROPERTIES_TYPE("NodoPropertiesType"),
    @XmlEnumValue("UtentePropertiesType")
    UTENTE_PROPERTIES_TYPE("UtentePropertiesType");
    private final String value;

    EnumBackOfficeObjectType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static EnumBackOfficeObjectType fromValue(String v) {
        for (EnumBackOfficeObjectType c: EnumBackOfficeObjectType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
