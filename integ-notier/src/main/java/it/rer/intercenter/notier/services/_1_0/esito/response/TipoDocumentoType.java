/*-
 * ========================LICENSE_START=================================
 * CPASS BackEnd - INTEGRATION submodule - NotiER
 * %%
 * Copyright (C) 2019 - 2021 CSI Piemonte
 * %%
 * SPDX-FileCopyrightText: Copyright 2019 - 2021 | CSI Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 * =========================LICENSE_END==================================
 */

package it.rer.intercenter.notier.services._1_0.esito.response;

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
 *     &lt;enumeration value="NOTIFICA_MDN"/&gt;
 *     &lt;enumeration value="NOTIFICA_DI_SCARTO"/&gt;
 *     &lt;enumeration value="RICEVUTA_DI_CONSEGNA"/&gt;
 *     &lt;enumeration value="NOTIFICA_DI_MANCATA_CONSEGNA"/&gt;
 *     &lt;enumeration value="NOTIFICA_DI_ESITO_COMMITTENTE"/&gt;
 *     &lt;enumeration value="NOTIFICA_DI_ESITO_CEDENTE"/&gt;
 *     &lt;enumeration value="NOTIFICA_SCARTO_ESITO_COMMITTENTE"/&gt;
 *     &lt;enumeration value="NOTIFICA_DI_DECORRENZA_TERMINI"/&gt;
 *     &lt;enumeration value="ATTESTAZIONE_DI_FILE_NON_RECAPITABILE"/&gt;
 *     &lt;enumeration value="NOTIFICA_METADATI_FILE_FATTURA"/&gt;
 *     &lt;enumeration value="ESITO_CONSERVAZIONE"/&gt;
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
    NOTIFICA_MDN,
    NOTIFICA_DI_SCARTO,
    RICEVUTA_DI_CONSEGNA,
    NOTIFICA_DI_MANCATA_CONSEGNA,
    NOTIFICA_DI_ESITO_COMMITTENTE,
    NOTIFICA_DI_ESITO_CEDENTE,
    NOTIFICA_SCARTO_ESITO_COMMITTENTE,
    NOTIFICA_DI_DECORRENZA_TERMINI,
    ATTESTAZIONE_DI_FILE_NON_RECAPITABILE,
    NOTIFICA_METADATI_FILE_FATTURA,
    ESITO_CONSERVAZIONE;

    public String value() {
        return name();
    }

    public static TipoDocumentoType fromValue(String v) {
        return valueOf(v);
    }

}
