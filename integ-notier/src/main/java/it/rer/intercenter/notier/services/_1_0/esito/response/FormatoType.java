/*-
 * ========================LICENSE_START=================================
 * CPASS BackEnd - INTEGRATION submodule - NotiER
 * %%
 * Copyright (C) 2019 - 2020 CSI Piemonte
 * %%
 * SPDX-FileCopyrightText: Copyright 2019 - 2020 | CSI Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 * =========================LICENSE_END==================================
 */

package it.rer.intercenter.notier.services._1_0.esito.response;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per FormatoType.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * <p>
 * <pre>
 * &lt;simpleType name="FormatoType"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}NMTOKEN"&gt;
 *     &lt;enumeration value="UBL"/&gt;
 *     &lt;enumeration value="SDI"/&gt;
 *     &lt;enumeration value="PARER"/&gt;
 *     &lt;enumeration value="UBL_EU"/&gt;
 *     &lt;enumeration value="BIS_3"/&gt;
 *     &lt;enumeration value="NSO"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "FormatoType")
@XmlEnum
public enum FormatoType {

    UBL,
    SDI,
    PARER,
    UBL_EU,
    BIS_3,
    NSO;

    public String value() {
        return name();
    }

    public static FormatoType fromValue(String v) {
        return valueOf(v);
    }

}
