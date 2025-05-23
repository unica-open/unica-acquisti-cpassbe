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

package it.doqui.acta.acaris.backoffice;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * &lt;p&gt;Classe Java per enumTipologiaEnteType.
 * 
 * &lt;p&gt;Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * &lt;pre&gt;
 * &amp;lt;simpleType name="enumTipologiaEnteType"&amp;gt;
 *   &amp;lt;restriction base="{common.acaris.acta.doqui.it}string"&amp;gt;
 *     &amp;lt;enumeration value="ASL"/&amp;gt;
 *     &amp;lt;enumeration value="AutoritaAmministrativaIndipendente"/&amp;gt;
 *     &amp;lt;enumeration value="CameraDiCommercio"/&amp;gt;
 *     &amp;lt;enumeration value="Comune"/&amp;gt;
 *     &amp;lt;enumeration value="ComunitaMontana"/&amp;gt;
 *     &amp;lt;enumeration value="EnteStrutturaAssociativa"/&amp;gt;
 *     &amp;lt;enumeration value="EnteAutonomoLiricoIstituzioniConcertisticheAssimilate"/&amp;gt;
 *     &amp;lt;enumeration value="EnteRegolazioneAttivitaEconomica"/&amp;gt;
 *     &amp;lt;enumeration value="EnteAziendaOspedaliera"/&amp;gt;
 *     &amp;lt;enumeration value="EnteIstituzioneRicercaNonStrumentale"/&amp;gt;
 *     &amp;lt;enumeration value="EnteNazionalePrevidenzaAssistenzaSociale"/&amp;gt;
 *     &amp;lt;enumeration value="EnteParco"/&amp;gt;
 *     &amp;lt;enumeration value="EnteDirittoAlloStudio"/&amp;gt;
 *     &amp;lt;enumeration value="EnteTurismo"/&amp;gt;
 *     &amp;lt;enumeration value="EntePortuale"/&amp;gt;
 *     &amp;lt;enumeration value="EnteProduttoreServiziCulturali"/&amp;gt;
 *     &amp;lt;enumeration value="EnteProduttoreServiziEconomici"/&amp;gt;
 *     &amp;lt;enumeration value="EnteRegionaleSviluppo"/&amp;gt;
 *     &amp;lt;enumeration value="EnteRegionaleRicercaAmbiente"/&amp;gt;
 *     &amp;lt;enumeration value="IstitutoStazioneSperimentaleRicerca"/&amp;gt;
 *     &amp;lt;enumeration value="MinisteroPresidenzaConsiglio"/&amp;gt;
 *     &amp;lt;enumeration value="OrganoCostituzionaleRilievoCostituzionale"/&amp;gt;
 *     &amp;lt;enumeration value="Provincia"/&amp;gt;
 *     &amp;lt;enumeration value="Regione"/&amp;gt;
 *     &amp;lt;enumeration value="UniversitaIstitutiIstruzione"/&amp;gt;
 *     &amp;lt;enumeration value="AltroEnte"/&amp;gt;
 *   &amp;lt;/restriction&amp;gt;
 * &amp;lt;/simpleType&amp;gt;
 * &lt;/pre&gt;
 * 
 */
@XmlType(name = "enumTipologiaEnteType")
@XmlEnum
public enum EnumTipologiaEnteType {

    ASL("ASL"),
    @XmlEnumValue("AutoritaAmministrativaIndipendente")
    AUTORITA_AMMINISTRATIVA_INDIPENDENTE("AutoritaAmministrativaIndipendente"),
    @XmlEnumValue("CameraDiCommercio")
    CAMERA_DI_COMMERCIO("CameraDiCommercio"),
    @XmlEnumValue("Comune")
    COMUNE("Comune"),
    @XmlEnumValue("ComunitaMontana")
    COMUNITA_MONTANA("ComunitaMontana"),
    @XmlEnumValue("EnteStrutturaAssociativa")
    ENTE_STRUTTURA_ASSOCIATIVA("EnteStrutturaAssociativa"),
    @XmlEnumValue("EnteAutonomoLiricoIstituzioniConcertisticheAssimilate")
    ENTE_AUTONOMO_LIRICO_ISTITUZIONI_CONCERTISTICHE_ASSIMILATE("EnteAutonomoLiricoIstituzioniConcertisticheAssimilate"),
    @XmlEnumValue("EnteRegolazioneAttivitaEconomica")
    ENTE_REGOLAZIONE_ATTIVITA_ECONOMICA("EnteRegolazioneAttivitaEconomica"),
    @XmlEnumValue("EnteAziendaOspedaliera")
    ENTE_AZIENDA_OSPEDALIERA("EnteAziendaOspedaliera"),
    @XmlEnumValue("EnteIstituzioneRicercaNonStrumentale")
    ENTE_ISTITUZIONE_RICERCA_NON_STRUMENTALE("EnteIstituzioneRicercaNonStrumentale"),
    @XmlEnumValue("EnteNazionalePrevidenzaAssistenzaSociale")
    ENTE_NAZIONALE_PREVIDENZA_ASSISTENZA_SOCIALE("EnteNazionalePrevidenzaAssistenzaSociale"),
    @XmlEnumValue("EnteParco")
    ENTE_PARCO("EnteParco"),
    @XmlEnumValue("EnteDirittoAlloStudio")
    ENTE_DIRITTO_ALLO_STUDIO("EnteDirittoAlloStudio"),
    @XmlEnumValue("EnteTurismo")
    ENTE_TURISMO("EnteTurismo"),
    @XmlEnumValue("EntePortuale")
    ENTE_PORTUALE("EntePortuale"),
    @XmlEnumValue("EnteProduttoreServiziCulturali")
    ENTE_PRODUTTORE_SERVIZI_CULTURALI("EnteProduttoreServiziCulturali"),
    @XmlEnumValue("EnteProduttoreServiziEconomici")
    ENTE_PRODUTTORE_SERVIZI_ECONOMICI("EnteProduttoreServiziEconomici"),
    @XmlEnumValue("EnteRegionaleSviluppo")
    ENTE_REGIONALE_SVILUPPO("EnteRegionaleSviluppo"),
    @XmlEnumValue("EnteRegionaleRicercaAmbiente")
    ENTE_REGIONALE_RICERCA_AMBIENTE("EnteRegionaleRicercaAmbiente"),
    @XmlEnumValue("IstitutoStazioneSperimentaleRicerca")
    ISTITUTO_STAZIONE_SPERIMENTALE_RICERCA("IstitutoStazioneSperimentaleRicerca"),
    @XmlEnumValue("MinisteroPresidenzaConsiglio")
    MINISTERO_PRESIDENZA_CONSIGLIO("MinisteroPresidenzaConsiglio"),
    @XmlEnumValue("OrganoCostituzionaleRilievoCostituzionale")
    ORGANO_COSTITUZIONALE_RILIEVO_COSTITUZIONALE("OrganoCostituzionaleRilievoCostituzionale"),
    @XmlEnumValue("Provincia")
    PROVINCIA("Provincia"),
    @XmlEnumValue("Regione")
    REGIONE("Regione"),
    @XmlEnumValue("UniversitaIstitutiIstruzione")
    UNIVERSITA_ISTITUTI_ISTRUZIONE("UniversitaIstitutiIstruzione"),
    @XmlEnumValue("AltroEnte")
    ALTRO_ENTE("AltroEnte");
    private final String value;

    EnumTipologiaEnteType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static EnumTipologiaEnteType fromValue(String v) {
        for (EnumTipologiaEnteType c: EnumTipologiaEnteType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
