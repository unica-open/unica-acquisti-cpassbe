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
 * &lt;p&gt;Classe Java per enumModalitaCalcoloProgDocType.
 * 
 * &lt;p&gt;Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * &lt;pre&gt;
 * &amp;lt;simpleType name="enumModalitaCalcoloProgDocType"&amp;gt;
 *   &amp;lt;restriction base="{common.acaris.acta.doqui.it}string"&amp;gt;
 *     &amp;lt;enumeration value="IncrementoAutomatico"/&amp;gt;
 *     &amp;lt;enumeration value="Ereditato"/&amp;gt;
 *     &amp;lt;enumeration value="Libero"/&amp;gt;
 *     &amp;lt;enumeration value="IncrementoAutomaticoConAzzeramentoInizioAnno"/&amp;gt;
 *     &amp;lt;enumeration value="IncrementoAutomaticoConAzzeramentoInizioLegislatura"/&amp;gt;
 *   &amp;lt;/restriction&amp;gt;
 * &amp;lt;/simpleType&amp;gt;
 * &lt;/pre&gt;
 * 
 */
@XmlType(name = "enumModalitaCalcoloProgDocType")
@XmlEnum
public enum EnumModalitaCalcoloProgDocType {

    @XmlEnumValue("IncrementoAutomatico")
    INCREMENTO_AUTOMATICO("IncrementoAutomatico"),
    @XmlEnumValue("Ereditato")
    EREDITATO("Ereditato"),
    @XmlEnumValue("Libero")
    LIBERO("Libero"),
    @XmlEnumValue("IncrementoAutomaticoConAzzeramentoInizioAnno")
    INCREMENTO_AUTOMATICO_CON_AZZERAMENTO_INIZIO_ANNO("IncrementoAutomaticoConAzzeramentoInizioAnno"),
    @XmlEnumValue("IncrementoAutomaticoConAzzeramentoInizioLegislatura")
    INCREMENTO_AUTOMATICO_CON_AZZERAMENTO_INIZIO_LEGISLATURA("IncrementoAutomaticoConAzzeramentoInizioLegislatura");
    private final String value;

    EnumModalitaCalcoloProgDocType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static EnumModalitaCalcoloProgDocType fromValue(String v) {
        for (EnumModalitaCalcoloProgDocType c: EnumModalitaCalcoloProgDocType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
