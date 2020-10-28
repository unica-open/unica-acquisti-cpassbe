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

package it.rer.intercenter.notier.services._1_0.inviodocumento.request;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per TipoDocumentoType.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * <p>
 * <pre>
 * &lt;simpleType name="TipoDocumentoType"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}NMTOKEN"&gt;
 *     &lt;enumeration value="FATTURA"/&gt;
 *     &lt;enumeration value="NOTA_DI_CREDITO"/&gt;
 *     &lt;enumeration value="DOCUMENTO_DI_TRASPORTO"/&gt;
 *     &lt;enumeration value="ORDINE"/&gt;
 *     &lt;enumeration value="NOTIFICA_DI_ESITO_COMMITTENTE"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "TipoDocumentoType")
@XmlEnum
public enum TipoDocumentoType {

    FATTURA,
    NOTA_DI_CREDITO,
    DOCUMENTO_DI_TRASPORTO,
    ORDINE,
    NOTIFICA_DI_ESITO_COMMITTENTE;

    public String value() {
        return name();
    }

    public static TipoDocumentoType fromValue(String v) {
        return valueOf(v);
    }

}
