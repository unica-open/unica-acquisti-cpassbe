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
 * &lt;p&gt;Classe Java per enumTipoDocumentoArchivistico.
 * 
 * &lt;p&gt;Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * &lt;pre&gt;
 * &amp;lt;simpleType name="enumTipoDocumentoArchivistico"&amp;gt;
 *   &amp;lt;restriction base="{common.acaris.acta.doqui.it}string"&amp;gt;
 *     &amp;lt;enumeration value="DocumentoSemplice"/&amp;gt;
 *     &amp;lt;enumeration value="DocumentoDB"/&amp;gt;
 *     &amp;lt;enumeration value="DocumentoRegistro"/&amp;gt;
 *   &amp;lt;/restriction&amp;gt;
 * &amp;lt;/simpleType&amp;gt;
 * &lt;/pre&gt;
 * 
 */
@XmlType(name = "enumTipoDocumentoArchivistico")
@XmlEnum
public enum EnumTipoDocumentoArchivistico {

    @XmlEnumValue("DocumentoSemplice")
    DOCUMENTO_SEMPLICE("DocumentoSemplice"),
    @XmlEnumValue("DocumentoDB")
    DOCUMENTO_DB("DocumentoDB"),
    @XmlEnumValue("DocumentoRegistro")
    DOCUMENTO_REGISTRO("DocumentoRegistro");
    private final String value;

    EnumTipoDocumentoArchivistico(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static EnumTipoDocumentoArchivistico fromValue(String v) {
        for (EnumTipoDocumentoArchivistico c: EnumTipoDocumentoArchivistico.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
