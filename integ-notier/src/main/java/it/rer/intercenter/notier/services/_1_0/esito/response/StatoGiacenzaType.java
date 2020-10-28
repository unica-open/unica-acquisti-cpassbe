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
 * <p>Classe Java per StatoGiacenzaType.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * <p>
 * <pre>
 * &lt;simpleType name="StatoGiacenzaType"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}NMTOKEN"&gt;
 *     &lt;enumeration value="DA_RECAPITARE"/&gt;
 *     &lt;enumeration value="IN_LAVORAZIONE"/&gt;
 *     &lt;enumeration value="RECAPITATO"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "StatoGiacenzaType")
@XmlEnum
public enum StatoGiacenzaType {

    DA_RECAPITARE,
    IN_LAVORAZIONE,
    RECAPITATO;

    public String value() {
        return name();
    }

    public static StatoGiacenzaType fromValue(String v) {
        return valueOf(v);
    }

}
