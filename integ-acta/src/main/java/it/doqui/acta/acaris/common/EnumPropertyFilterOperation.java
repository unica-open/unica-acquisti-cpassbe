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

package it.doqui.acta.acaris.common;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * &lt;p&gt;Classe Java per enumPropertyFilterOperation.
 * 
 * &lt;p&gt;Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * &lt;pre&gt;
 * &amp;lt;simpleType name="enumPropertyFilterOperation"&amp;gt;
 *   &amp;lt;restriction base="{common.acaris.acta.doqui.it}string"&amp;gt;
 *     &amp;lt;enumeration value="none"/&amp;gt;
 *     &amp;lt;enumeration value="all"/&amp;gt;
 *     &amp;lt;enumeration value="query"/&amp;gt;
 *     &amp;lt;enumeration value="getProperties"/&amp;gt;
 *     &amp;lt;enumeration value="getDescendants"/&amp;gt;
 *     &amp;lt;enumeration value="getChildren"/&amp;gt;
 *     &amp;lt;enumeration value="getFolderParent"/&amp;gt;
 *     &amp;lt;enumeration value="getObjectParents"/&amp;gt;
 *   &amp;lt;/restriction&amp;gt;
 * &amp;lt;/simpleType&amp;gt;
 * &lt;/pre&gt;
 * 
 */
@XmlType(name = "enumPropertyFilterOperation")
@XmlEnum
public enum EnumPropertyFilterOperation {

    @XmlEnumValue("none")
    NONE("none"),
    @XmlEnumValue("all")
    ALL("all"),
    @XmlEnumValue("query")
    QUERY("query"),
    @XmlEnumValue("getProperties")
    GET_PROPERTIES("getProperties"),
    @XmlEnumValue("getDescendants")
    GET_DESCENDANTS("getDescendants"),
    @XmlEnumValue("getChildren")
    GET_CHILDREN("getChildren"),
    @XmlEnumValue("getFolderParent")
    GET_FOLDER_PARENT("getFolderParent"),
    @XmlEnumValue("getObjectParents")
    GET_OBJECT_PARENTS("getObjectParents");
    private final String value;

    EnumPropertyFilterOperation(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static EnumPropertyFilterOperation fromValue(String v) {
        for (EnumPropertyFilterOperation c: EnumPropertyFilterOperation.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
