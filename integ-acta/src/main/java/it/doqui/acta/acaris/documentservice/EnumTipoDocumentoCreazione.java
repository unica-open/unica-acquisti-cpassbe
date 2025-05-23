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
 * &lt;p&gt;Classe Java per enumTipoDocumentoCreazione.
 * 
 * &lt;p&gt;Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * &lt;pre&gt;
 * &amp;lt;simpleType name="enumTipoDocumentoCreazione"&amp;gt;
 *   &amp;lt;restriction base="{common.acaris.acta.doqui.it}string"&amp;gt;
 *     &amp;lt;enumeration value="documentoArchivistico.semplice"/&amp;gt;
 *     &amp;lt;enumeration value="documentoArchivistico.db"/&amp;gt;
 *     &amp;lt;enumeration value="documentoArchivistico.registro"/&amp;gt;
 *     &amp;lt;enumeration value="documentoFisico"/&amp;gt;
 *     &amp;lt;enumeration value="contenutoFisico"/&amp;gt;
 *   &amp;lt;/restriction&amp;gt;
 * &amp;lt;/simpleType&amp;gt;
 * &lt;/pre&gt;
 * 
 */
@XmlType(name = "enumTipoDocumentoCreazione")
@XmlEnum
public enum EnumTipoDocumentoCreazione {

    @XmlEnumValue("documentoArchivistico.semplice")
    DOCUMENTO_ARCHIVISTICO_SEMPLICE("documentoArchivistico.semplice"),
    @XmlEnumValue("documentoArchivistico.db")
    DOCUMENTO_ARCHIVISTICO_DB("documentoArchivistico.db"),
    @XmlEnumValue("documentoArchivistico.registro")
    DOCUMENTO_ARCHIVISTICO_REGISTRO("documentoArchivistico.registro"),
    @XmlEnumValue("documentoFisico")
    DOCUMENTO_FISICO("documentoFisico"),
    @XmlEnumValue("contenutoFisico")
    CONTENUTO_FISICO("contenutoFisico");
    private final String value;

    EnumTipoDocumentoCreazione(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static EnumTipoDocumentoCreazione fromValue(String v) {
        for (EnumTipoDocumentoCreazione c: EnumTipoDocumentoCreazione.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
