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
 * &lt;p&gt;Classe Java per enumOfficialBookObjectType.
 * 
 * &lt;p&gt;Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * &lt;pre&gt;
 * &amp;lt;simpleType name="enumOfficialBookObjectType"&amp;gt;
 *   &amp;lt;restriction base="{common.acaris.acta.doqui.it}string"&amp;gt;
 *     &amp;lt;enumeration value="RegistrazionePropertiesType"/&amp;gt;
 *     &amp;lt;enumeration value="CorrispondentePropertiesType"/&amp;gt;
 *     &amp;lt;enumeration value="AnnotazioneOBPropertiesType"/&amp;gt;
 *     &amp;lt;enumeration value="RegistroProtocolloPropertiesType"/&amp;gt;
 *     &amp;lt;enumeration value="LogProtocolloPropertiesType"/&amp;gt;
 *     &amp;lt;enumeration value="MessaggioPropertiesType"/&amp;gt;
 *     &amp;lt;enumeration value="CorrispondenteMessaggioPropertiesType"/&amp;gt;
 *     &amp;lt;enumeration value="MessInterscambioPropertiesType"/&amp;gt;
 *     &amp;lt;enumeration value="ContentMessInterscambioPropertiesType"/&amp;gt;
 *   &amp;lt;/restriction&amp;gt;
 * &amp;lt;/simpleType&amp;gt;
 * &lt;/pre&gt;
 * 
 */
@XmlType(name = "enumOfficialBookObjectType")
@XmlEnum
public enum EnumOfficialBookObjectType {

    @XmlEnumValue("RegistrazionePropertiesType")
    REGISTRAZIONE_PROPERTIES_TYPE("RegistrazionePropertiesType"),
    @XmlEnumValue("CorrispondentePropertiesType")
    CORRISPONDENTE_PROPERTIES_TYPE("CorrispondentePropertiesType"),
    @XmlEnumValue("AnnotazioneOBPropertiesType")
    ANNOTAZIONE_OB_PROPERTIES_TYPE("AnnotazioneOBPropertiesType"),
    @XmlEnumValue("RegistroProtocolloPropertiesType")
    REGISTRO_PROTOCOLLO_PROPERTIES_TYPE("RegistroProtocolloPropertiesType"),
    @XmlEnumValue("LogProtocolloPropertiesType")
    LOG_PROTOCOLLO_PROPERTIES_TYPE("LogProtocolloPropertiesType"),
    @XmlEnumValue("MessaggioPropertiesType")
    MESSAGGIO_PROPERTIES_TYPE("MessaggioPropertiesType"),
    @XmlEnumValue("CorrispondenteMessaggioPropertiesType")
    CORRISPONDENTE_MESSAGGIO_PROPERTIES_TYPE("CorrispondenteMessaggioPropertiesType"),
    @XmlEnumValue("MessInterscambioPropertiesType")
    MESS_INTERSCAMBIO_PROPERTIES_TYPE("MessInterscambioPropertiesType"),
    @XmlEnumValue("ContentMessInterscambioPropertiesType")
    CONTENT_MESS_INTERSCAMBIO_PROPERTIES_TYPE("ContentMessInterscambioPropertiesType");
    private final String value;

    EnumOfficialBookObjectType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static EnumOfficialBookObjectType fromValue(String v) {
        for (EnumOfficialBookObjectType c: EnumOfficialBookObjectType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
