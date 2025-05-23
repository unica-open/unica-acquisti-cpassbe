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

package it.doqui.acta.acaris.documentservice;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * &lt;p&gt;Classe Java per enumTipoOperazione.
 * 
 * &lt;p&gt;Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * &lt;pre&gt;
 * &amp;lt;simpleType name="enumTipoOperazione"&amp;gt;
 *   &amp;lt;restriction base="{common.acaris.acta.doqui.it}string"&amp;gt;
 *     &amp;lt;enumeration value="soloMetadati"/&amp;gt;
 *     &amp;lt;enumeration value="elettronico"/&amp;gt;
 *     &amp;lt;enumeration value="aggiuntaDocumentoFisico"/&amp;gt;
 *     &amp;lt;enumeration value="aggiuntaContenutoFisico"/&amp;gt;
 *   &amp;lt;/restriction&amp;gt;
 * &amp;lt;/simpleType&amp;gt;
 * &lt;/pre&gt;
 * 
 */
@XmlType(name = "enumTipoOperazione")
@XmlEnum
public enum EnumTipoOperazione {

    @XmlEnumValue("soloMetadati")
    SOLO_METADATI("soloMetadati"),
    @XmlEnumValue("elettronico")
    ELETTRONICO("elettronico"),
    @XmlEnumValue("aggiuntaDocumentoFisico")
    AGGIUNTA_DOCUMENTO_FISICO("aggiuntaDocumentoFisico"),
    @XmlEnumValue("aggiuntaContenutoFisico")
    AGGIUNTA_CONTENUTO_FISICO("aggiuntaContenutoFisico");
    private final String value;

    EnumTipoOperazione(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static EnumTipoOperazione fromValue(String v) {
        for (EnumTipoOperazione c: EnumTipoOperazione.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
