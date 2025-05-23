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

package it.doqui.acta.acaris.common;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * &lt;p&gt;Classe Java per enumServiceException.
 * 
 * &lt;p&gt;Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * &lt;pre&gt;
 * &amp;lt;simpleType name="enumServiceException"&amp;gt;
 *   &amp;lt;restriction base="{common.acaris.acta.doqui.it}string"&amp;gt;
 *     &amp;lt;enumeration value="invalidArgument"/&amp;gt;
 *     &amp;lt;enumeration value="objectNotFound"/&amp;gt;
 *     &amp;lt;enumeration value="notSupported"/&amp;gt;
 *     &amp;lt;enumeration value="permissionDenied"/&amp;gt;
 *     &amp;lt;enumeration value="runtime"/&amp;gt;
 *     &amp;lt;enumeration value="constraint"/&amp;gt;
 *     &amp;lt;enumeration value="contentAlreadyExists"/&amp;gt;
 *     &amp;lt;enumeration value="filterNotValid"/&amp;gt;
 *     &amp;lt;enumeration value="storage"/&amp;gt;
 *     &amp;lt;enumeration value="streamNotSupported"/&amp;gt;
 *     &amp;lt;enumeration value="updateConflict"/&amp;gt;
 *     &amp;lt;enumeration value="versioning"/&amp;gt;
 *     &amp;lt;enumeration value="systemError"/&amp;gt;
 *     &amp;lt;enumeration value="unrecoverableError"/&amp;gt;
 *     &amp;lt;enumeration value="configurationError"/&amp;gt;
 *   &amp;lt;/restriction&amp;gt;
 * &amp;lt;/simpleType&amp;gt;
 * &lt;/pre&gt;
 * 
 */
@XmlType(name = "enumServiceException")
@XmlEnum
public enum EnumServiceException {

    @XmlEnumValue("invalidArgument")
    INVALID_ARGUMENT("invalidArgument"),
    @XmlEnumValue("objectNotFound")
    OBJECT_NOT_FOUND("objectNotFound"),
    @XmlEnumValue("notSupported")
    NOT_SUPPORTED("notSupported"),
    @XmlEnumValue("permissionDenied")
    PERMISSION_DENIED("permissionDenied"),
    @XmlEnumValue("runtime")
    RUNTIME("runtime"),
    @XmlEnumValue("constraint")
    CONSTRAINT("constraint"),
    @XmlEnumValue("contentAlreadyExists")
    CONTENT_ALREADY_EXISTS("contentAlreadyExists"),
    @XmlEnumValue("filterNotValid")
    FILTER_NOT_VALID("filterNotValid"),
    @XmlEnumValue("storage")
    STORAGE("storage"),
    @XmlEnumValue("streamNotSupported")
    STREAM_NOT_SUPPORTED("streamNotSupported"),
    @XmlEnumValue("updateConflict")
    UPDATE_CONFLICT("updateConflict"),
    @XmlEnumValue("versioning")
    VERSIONING("versioning"),
    @XmlEnumValue("systemError")
    SYSTEM_ERROR("systemError"),
    @XmlEnumValue("unrecoverableError")
    UNRECOVERABLE_ERROR("unrecoverableError"),
    @XmlEnumValue("configurationError")
    CONFIGURATION_ERROR("configurationError");
    private final String value;

    EnumServiceException(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static EnumServiceException fromValue(String v) {
        for (EnumServiceException c: EnumServiceException.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
