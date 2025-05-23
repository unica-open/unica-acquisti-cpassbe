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
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * &lt;p&gt;Classe Java per enumTipoMessaggio.
 * 
 * &lt;p&gt;Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * &lt;pre&gt;
 * &amp;lt;simpleType name="enumTipoMessaggio"&amp;gt;
 *   &amp;lt;restriction base="{common.acaris.acta.doqui.it}string"&amp;gt;
 *     &amp;lt;enumeration value="Segnatura"/&amp;gt;
 *     &amp;lt;enumeration value="Conferma"/&amp;gt;
 *     &amp;lt;enumeration value="Aggiornamento"/&amp;gt;
 *     &amp;lt;enumeration value="Annullamento"/&amp;gt;
 *     &amp;lt;enumeration value="NotificaEccezione"/&amp;gt;
 *     &amp;lt;enumeration value="RicevutaPEC"/&amp;gt;
 *     &amp;lt;enumeration value="MailPECNonStrutturata"/&amp;gt;
 *     &amp;lt;enumeration value="MailNonPEC"/&amp;gt;
 *   &amp;lt;/restriction&amp;gt;
 * &amp;lt;/simpleType&amp;gt;
 * &lt;/pre&gt;
 * 
 */
@XmlType(name = "enumTipoMessaggio")
@XmlEnum
public enum EnumTipoMessaggio {

    @XmlEnumValue("Segnatura")
    SEGNATURA("Segnatura"),
    @XmlEnumValue("Conferma")
    CONFERMA("Conferma"),
    @XmlEnumValue("Aggiornamento")
    AGGIORNAMENTO("Aggiornamento"),
    @XmlEnumValue("Annullamento")
    ANNULLAMENTO("Annullamento"),
    @XmlEnumValue("NotificaEccezione")
    NOTIFICA_ECCEZIONE("NotificaEccezione"),
    @XmlEnumValue("RicevutaPEC")
    RICEVUTA_PEC("RicevutaPEC"),
    @XmlEnumValue("MailPECNonStrutturata")
    MAIL_PEC_NON_STRUTTURATA("MailPECNonStrutturata"),
    @XmlEnumValue("MailNonPEC")
    MAIL_NON_PEC("MailNonPEC");
    private final String value;

    EnumTipoMessaggio(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static EnumTipoMessaggio fromValue(String v) {
        for (EnumTipoMessaggio c: EnumTipoMessaggio.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
