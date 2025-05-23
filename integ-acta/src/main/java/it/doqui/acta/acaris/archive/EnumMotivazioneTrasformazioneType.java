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
 * &lt;p&gt;Classe Java per enumMotivazioneTrasformazioneType.
 * 
 * &lt;p&gt;Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * &lt;pre&gt;
 * &amp;lt;simpleType name="enumMotivazioneTrasformazioneType"&amp;gt;
 *   &amp;lt;restriction base="{common.acaris.acta.doqui.it}string"&amp;gt;
 *     &amp;lt;enumeration value="ScomposizioneDocumentoAggregato"/&amp;gt;
 *     &amp;lt;enumeration value="Sbustamento"/&amp;gt;
 *     &amp;lt;enumeration value="TrasformazionePerObsolescenza"/&amp;gt;
 *     &amp;lt;enumeration value="PassaggiodiStato"/&amp;gt;
 *   &amp;lt;/restriction&amp;gt;
 * &amp;lt;/simpleType&amp;gt;
 * &lt;/pre&gt;
 * 
 */
@XmlType(name = "enumMotivazioneTrasformazioneType")
@XmlEnum
public enum EnumMotivazioneTrasformazioneType {

    @XmlEnumValue("ScomposizioneDocumentoAggregato")
    SCOMPOSIZIONE_DOCUMENTO_AGGREGATO("ScomposizioneDocumentoAggregato"),
    @XmlEnumValue("Sbustamento")
    SBUSTAMENTO("Sbustamento"),
    @XmlEnumValue("TrasformazionePerObsolescenza")
    TRASFORMAZIONE_PER_OBSOLESCENZA("TrasformazionePerObsolescenza"),
    @XmlEnumValue("PassaggiodiStato")
    PASSAGGIODI_STATO("PassaggiodiStato");
    private final String value;

    EnumMotivazioneTrasformazioneType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static EnumMotivazioneTrasformazioneType fromValue(String v) {
        for (EnumMotivazioneTrasformazioneType c: EnumMotivazioneTrasformazioneType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
