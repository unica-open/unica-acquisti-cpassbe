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

package it.doqui.acta.acaris.officialbookservice;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * &lt;p&gt;Classe Java per enumTipologiaCorrispondente.
 * 
 * &lt;p&gt;Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * &lt;pre&gt;
 * &amp;lt;simpleType name="enumTipologiaCorrispondente"&amp;gt;
 *   &amp;lt;restriction base="{common.acaris.acta.doqui.it}string"&amp;gt;
 *     &amp;lt;enumeration value="PA"/&amp;gt;
 *     &amp;lt;enumeration value="AE"/&amp;gt;
 *     &amp;lt;enumeration value="PG"/&amp;gt;
 *     &amp;lt;enumeration value="PF"/&amp;gt;
 *   &amp;lt;/restriction&amp;gt;
 * &amp;lt;/simpleType&amp;gt;
 * &lt;/pre&gt;
 * 
 */
@XmlType(name = "enumTipologiaCorrispondente")
@XmlEnum
public enum EnumTipologiaCorrispondente {

    PA,
    AE,
    PG,
    PF;

    public String value() {
        return name();
    }

    public static EnumTipologiaCorrispondente fromValue(String v) {
        return valueOf(v);
    }

}
