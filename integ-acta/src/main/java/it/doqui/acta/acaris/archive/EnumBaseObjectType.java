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
 * &lt;p&gt;Classe Java per enumBaseObjectType.
 * 
 * &lt;p&gt;Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * &lt;pre&gt;
 * &amp;lt;simpleType name="enumBaseObjectType"&amp;gt;
 *   &amp;lt;restriction base="{common.acaris.acta.doqui.it}string"&amp;gt;
 *     &amp;lt;enumeration value="DocumentPropertiesType"/&amp;gt;
 *     &amp;lt;enumeration value="FolderPropertiesType"/&amp;gt;
 *     &amp;lt;enumeration value="RelationshipPropertiesType"/&amp;gt;
 *     &amp;lt;enumeration value="PolicyPropertiesType"/&amp;gt;
 *   &amp;lt;/restriction&amp;gt;
 * &amp;lt;/simpleType&amp;gt;
 * &lt;/pre&gt;
 * 
 */
@XmlType(name = "enumBaseObjectType")
@XmlEnum
public enum EnumBaseObjectType {

    @XmlEnumValue("DocumentPropertiesType")
    DOCUMENT_PROPERTIES_TYPE("DocumentPropertiesType"),
    @XmlEnumValue("FolderPropertiesType")
    FOLDER_PROPERTIES_TYPE("FolderPropertiesType"),
    @XmlEnumValue("RelationshipPropertiesType")
    RELATIONSHIP_PROPERTIES_TYPE("RelationshipPropertiesType"),
    @XmlEnumValue("PolicyPropertiesType")
    POLICY_PROPERTIES_TYPE("PolicyPropertiesType");
    private final String value;

    EnumBaseObjectType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static EnumBaseObjectType fromValue(String v) {
        for (EnumBaseObjectType c: EnumBaseObjectType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
