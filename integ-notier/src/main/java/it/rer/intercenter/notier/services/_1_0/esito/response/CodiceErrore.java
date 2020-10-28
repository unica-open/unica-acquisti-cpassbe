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
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per CodiceErrore.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * <p>
 * <pre>
 * &lt;simpleType name="CodiceErrore"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}NMTOKEN"&gt;
 *     &lt;enumeration value="ERR-000-GENERICO"/&gt;
 *     &lt;enumeration value="ERR-INVIODOC-000-RICHIESTA-NON-VALIDA"/&gt;
 *     &lt;enumeration value="ERR-INVIODOC-001-XML-NON-VALIDO"/&gt;
 *     &lt;enumeration value="ERR-INVIODOC-002-NON-AUTORIZZATO"/&gt;
 *     &lt;enumeration value="ERR-INVIODOC-003-ORG-NON-VALIDA"/&gt;
 *     &lt;enumeration value="ERR-INVIODOC-004-TIPODOC-NON-VALIDO"/&gt;
 *     &lt;enumeration value="ERR-INVIODOC-005-FORMATO-RAPPR-NON-VALIDO"/&gt;
 *     &lt;enumeration value="ERR-INVIODOC-006-VERSIONE-RAPPR-NON-VALIDA"/&gt;
 *     &lt;enumeration value="ERR-INVIODOC-007-DOC-NON-VALIDO-XSD"/&gt;
 *     &lt;enumeration value="ERR-INVIODOC-008-DOC-NON-VALIDO-SCHEMATRON"/&gt;
 *     &lt;enumeration value="ERR-INVIODOC-009-DOC-DUPLICATO"/&gt;
 *     &lt;enumeration value="ERR-INVIODOC-010-DIMENSIONI-ECCESSIVE"/&gt;
 *     &lt;enumeration value="ERR-RECDOC-000-RICHIESTA-NON-VALIDA"/&gt;
 *     &lt;enumeration value="ERR-RECDOC-001-NON-AUTORIZZATO"/&gt;
 *     &lt;enumeration value="ERR-RECDOC-002-NON-TROVATO"/&gt;
 *     &lt;enumeration value="ERR-CONSDOC-000-RICHIESTA-NON-VALIDA"/&gt;
 *     &lt;enumeration value="ERR-CONSDOC-001-XML-NON-VALIDO"/&gt;
 *     &lt;enumeration value="ERR-CONSDOC-002-NON-AUTORIZZATO"/&gt;
 *     &lt;enumeration value="ERR-CONSDOC-003-NON-TROVATO"/&gt;
 *     &lt;enumeration value="ERR-CONSDOC-004-NON-APPLICABILE"/&gt;
 *     &lt;enumeration value="ERR-BLOCCOCONS-001-NON-AUTORIZZATO"/&gt;
 *     &lt;enumeration value="ERR-BLOCCOCONS-002-NON-TROVATO"/&gt;
 *     &lt;enumeration value="ERR-BLOCCOCONS-003-NON-APPLICABILE"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "CodiceErrore")
@XmlEnum
public enum CodiceErrore {

    @XmlEnumValue("ERR-000-GENERICO")
    ERR_000_GENERICO("ERR-000-GENERICO"),
    @XmlEnumValue("ERR-INVIODOC-000-RICHIESTA-NON-VALIDA")
    ERR_INVIODOC_000_RICHIESTA_NON_VALIDA("ERR-INVIODOC-000-RICHIESTA-NON-VALIDA"),
    @XmlEnumValue("ERR-INVIODOC-001-XML-NON-VALIDO")
    ERR_INVIODOC_001_XML_NON_VALIDO("ERR-INVIODOC-001-XML-NON-VALIDO"),
    @XmlEnumValue("ERR-INVIODOC-002-NON-AUTORIZZATO")
    ERR_INVIODOC_002_NON_AUTORIZZATO("ERR-INVIODOC-002-NON-AUTORIZZATO"),
    @XmlEnumValue("ERR-INVIODOC-003-ORG-NON-VALIDA")
    ERR_INVIODOC_003_ORG_NON_VALIDA("ERR-INVIODOC-003-ORG-NON-VALIDA"),
    @XmlEnumValue("ERR-INVIODOC-004-TIPODOC-NON-VALIDO")
    ERR_INVIODOC_004_TIPODOC_NON_VALIDO("ERR-INVIODOC-004-TIPODOC-NON-VALIDO"),
    @XmlEnumValue("ERR-INVIODOC-005-FORMATO-RAPPR-NON-VALIDO")
    ERR_INVIODOC_005_FORMATO_RAPPR_NON_VALIDO("ERR-INVIODOC-005-FORMATO-RAPPR-NON-VALIDO"),
    @XmlEnumValue("ERR-INVIODOC-006-VERSIONE-RAPPR-NON-VALIDA")
    ERR_INVIODOC_006_VERSIONE_RAPPR_NON_VALIDA("ERR-INVIODOC-006-VERSIONE-RAPPR-NON-VALIDA"),
    @XmlEnumValue("ERR-INVIODOC-007-DOC-NON-VALIDO-XSD")
    ERR_INVIODOC_007_DOC_NON_VALIDO_XSD("ERR-INVIODOC-007-DOC-NON-VALIDO-XSD"),
    @XmlEnumValue("ERR-INVIODOC-008-DOC-NON-VALIDO-SCHEMATRON")
    ERR_INVIODOC_008_DOC_NON_VALIDO_SCHEMATRON("ERR-INVIODOC-008-DOC-NON-VALIDO-SCHEMATRON"),
    @XmlEnumValue("ERR-INVIODOC-009-DOC-DUPLICATO")
    ERR_INVIODOC_009_DOC_DUPLICATO("ERR-INVIODOC-009-DOC-DUPLICATO"),
    @XmlEnumValue("ERR-INVIODOC-010-DIMENSIONI-ECCESSIVE")
    ERR_INVIODOC_010_DIMENSIONI_ECCESSIVE("ERR-INVIODOC-010-DIMENSIONI-ECCESSIVE"),
    @XmlEnumValue("ERR-RECDOC-000-RICHIESTA-NON-VALIDA")
    ERR_RECDOC_000_RICHIESTA_NON_VALIDA("ERR-RECDOC-000-RICHIESTA-NON-VALIDA"),
    @XmlEnumValue("ERR-RECDOC-001-NON-AUTORIZZATO")
    ERR_RECDOC_001_NON_AUTORIZZATO("ERR-RECDOC-001-NON-AUTORIZZATO"),
    @XmlEnumValue("ERR-RECDOC-002-NON-TROVATO")
    ERR_RECDOC_002_NON_TROVATO("ERR-RECDOC-002-NON-TROVATO"),
    @XmlEnumValue("ERR-CONSDOC-000-RICHIESTA-NON-VALIDA")
    ERR_CONSDOC_000_RICHIESTA_NON_VALIDA("ERR-CONSDOC-000-RICHIESTA-NON-VALIDA"),
    @XmlEnumValue("ERR-CONSDOC-001-XML-NON-VALIDO")
    ERR_CONSDOC_001_XML_NON_VALIDO("ERR-CONSDOC-001-XML-NON-VALIDO"),
    @XmlEnumValue("ERR-CONSDOC-002-NON-AUTORIZZATO")
    ERR_CONSDOC_002_NON_AUTORIZZATO("ERR-CONSDOC-002-NON-AUTORIZZATO"),
    @XmlEnumValue("ERR-CONSDOC-003-NON-TROVATO")
    ERR_CONSDOC_003_NON_TROVATO("ERR-CONSDOC-003-NON-TROVATO"),
    @XmlEnumValue("ERR-CONSDOC-004-NON-APPLICABILE")
    ERR_CONSDOC_004_NON_APPLICABILE("ERR-CONSDOC-004-NON-APPLICABILE"),
    @XmlEnumValue("ERR-BLOCCOCONS-001-NON-AUTORIZZATO")
    ERR_BLOCCOCONS_001_NON_AUTORIZZATO("ERR-BLOCCOCONS-001-NON-AUTORIZZATO"),
    @XmlEnumValue("ERR-BLOCCOCONS-002-NON-TROVATO")
    ERR_BLOCCOCONS_002_NON_TROVATO("ERR-BLOCCOCONS-002-NON-TROVATO"),
    @XmlEnumValue("ERR-BLOCCOCONS-003-NON-APPLICABILE")
    ERR_BLOCCOCONS_003_NON_APPLICABILE("ERR-BLOCCOCONS-003-NON-APPLICABILE");
    private final String value;

    CodiceErrore(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static CodiceErrore fromValue(String v) {
        for (CodiceErrore c: CodiceErrore.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
