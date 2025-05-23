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
 * &lt;p&gt;Classe Java per enumTipoRegistrazioneDaCreare.
 * 
 * &lt;p&gt;Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * &lt;pre&gt;
 * &amp;lt;simpleType name="enumTipoRegistrazioneDaCreare"&amp;gt;
 *   &amp;lt;restriction base="{common.acaris.acta.doqui.it}string"&amp;gt;
 *     &amp;lt;enumeration value="RegistrazionRapida"/&amp;gt;
 *     &amp;lt;enumeration value="Protocollazione"/&amp;gt;
 *     &amp;lt;enumeration value="ProtocollazioneDocumentoEsistente"/&amp;gt;
 *     &amp;lt;enumeration value="ProtocollazioneDaSmistamento"/&amp;gt;
 *   &amp;lt;/restriction&amp;gt;
 * &amp;lt;/simpleType&amp;gt;
 * &lt;/pre&gt;
 * 
 */
@XmlType(name = "enumTipoRegistrazioneDaCreare")
@XmlEnum
public enum EnumTipoRegistrazioneDaCreare {

    @XmlEnumValue("RegistrazionRapida")
    REGISTRAZION_RAPIDA("RegistrazionRapida"),
    @XmlEnumValue("Protocollazione")
    PROTOCOLLAZIONE("Protocollazione"),
    @XmlEnumValue("ProtocollazioneDocumentoEsistente")
    PROTOCOLLAZIONE_DOCUMENTO_ESISTENTE("ProtocollazioneDocumentoEsistente"),
    @XmlEnumValue("ProtocollazioneDaSmistamento")
    PROTOCOLLAZIONE_DA_SMISTAMENTO("ProtocollazioneDaSmistamento");
    private final String value;

    EnumTipoRegistrazioneDaCreare(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static EnumTipoRegistrazioneDaCreare fromValue(String v) {
        for (EnumTipoRegistrazioneDaCreare c: EnumTipoRegistrazioneDaCreare.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
