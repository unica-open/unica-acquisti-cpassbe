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

package it.doqui.acta.acaris.common.prt;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * &lt;p&gt;Classe Java per enumFonte.
 * 
 * &lt;p&gt;Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * &lt;pre&gt;
 * &amp;lt;simpleType name="enumFonte"&amp;gt;
 *   &amp;lt;restriction base="{common.acaris.acta.doqui.it}string"&amp;gt;
 *     &amp;lt;enumeration value="OA"/&amp;gt;
 *     &amp;lt;enumeration value="SA"/&amp;gt;
 *     &amp;lt;enumeration value="INPA"/&amp;gt;
 *     &amp;lt;enumeration value="HR"/&amp;gt;
 *     &amp;lt;enumeration value="IPA"/&amp;gt;
 *   &amp;lt;/restriction&amp;gt;
 * &amp;lt;/simpleType&amp;gt;
 * &lt;/pre&gt;
 * 
 */
@XmlType(name = "enumFonte", namespace = "prt.common.acaris.acta.doqui.it")
@XmlEnum
public enum EnumFonte {

    OA,
    SA,
    INPA,
    HR,
    IPA;

    public String value() {
        return name();
    }

    public static EnumFonte fromValue(String v) {
        return valueOf(v);
    }

}
