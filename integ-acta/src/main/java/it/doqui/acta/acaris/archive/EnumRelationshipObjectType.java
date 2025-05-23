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
 * &lt;p&gt;Classe Java per enumRelationshipObjectType.
 * 
 * &lt;p&gt;Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * &lt;pre&gt;
 * &amp;lt;simpleType name="enumRelationshipObjectType"&amp;gt;
 *   &amp;lt;restriction base="{common.acaris.acta.doqui.it}string"&amp;gt;
 *     &amp;lt;enumeration value="DocumentAssociationPropertiesType"/&amp;gt;
 *     &amp;lt;enumeration value="HistoryModificheTecnichePropertiesType"/&amp;gt;
 *     &amp;lt;enumeration value="DocumentCompositionPropertiesType"/&amp;gt;
 *     &amp;lt;enumeration value="HistoryVecchieVersioniPropertiesType"/&amp;gt;
 *   &amp;lt;/restriction&amp;gt;
 * &amp;lt;/simpleType&amp;gt;
 * &lt;/pre&gt;
 * 
 */
@XmlType(name = "enumRelationshipObjectType")
@XmlEnum
public enum EnumRelationshipObjectType {

    @XmlEnumValue("DocumentAssociationPropertiesType")
    DOCUMENT_ASSOCIATION_PROPERTIES_TYPE("DocumentAssociationPropertiesType"),
    @XmlEnumValue("HistoryModificheTecnichePropertiesType")
    HISTORY_MODIFICHE_TECNICHE_PROPERTIES_TYPE("HistoryModificheTecnichePropertiesType"),
    @XmlEnumValue("DocumentCompositionPropertiesType")
    DOCUMENT_COMPOSITION_PROPERTIES_TYPE("DocumentCompositionPropertiesType"),
    @XmlEnumValue("HistoryVecchieVersioniPropertiesType")
    HISTORY_VECCHIE_VERSIONI_PROPERTIES_TYPE("HistoryVecchieVersioniPropertiesType");
    private final String value;

    EnumRelationshipObjectType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static EnumRelationshipObjectType fromValue(String v) {
        for (EnumRelationshipObjectType c: EnumRelationshipObjectType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
