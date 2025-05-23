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
 * &lt;p&gt;Classe Java per enumObjectType.
 * 
 * &lt;p&gt;Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * &lt;pre&gt;
 * &amp;lt;simpleType name="enumObjectType"&amp;gt;
 *   &amp;lt;restriction base="{common.acaris.acta.doqui.it}string"&amp;gt;
 *     &amp;lt;enumeration value="ContenutoFisicoPropertiesType"/&amp;gt;
 *     &amp;lt;enumeration value="VerifyReportPropertiesType"/&amp;gt;
 *     &amp;lt;enumeration value="GruppoAllegatiPropertiesType"/&amp;gt;
 *     &amp;lt;enumeration value="DocumentoDBPropertiesType"/&amp;gt;
 *     &amp;lt;enumeration value="DocumentoRegistroPropertiesType"/&amp;gt;
 *     &amp;lt;enumeration value="DocumentoSemplicePropertiesType"/&amp;gt;
 *     &amp;lt;enumeration value="ClipsMetallicaPropertiesType"/&amp;gt;
 *     &amp;lt;enumeration value="TitolarioPropertiesType"/&amp;gt;
 *     &amp;lt;enumeration value="VocePropertiesType"/&amp;gt;
 *     &amp;lt;enumeration value="ClassificazionePropertiesType"/&amp;gt;
 *     &amp;lt;enumeration value="FascicoloTemporaneoPropertiesType"/&amp;gt;
 *     &amp;lt;enumeration value="DocumentoFisicoPropertiesType"/&amp;gt;
 *     &amp;lt;enumeration value="SottofascicoloPropertiesType"/&amp;gt;
 *     &amp;lt;enumeration value="DossierPropertiesType"/&amp;gt;
 *     &amp;lt;enumeration value="VolumeSerieFascicoliPropertiesType"/&amp;gt;
 *     &amp;lt;enumeration value="VolumeSerieTipologicaDocumentiPropertiesType"/&amp;gt;
 *     &amp;lt;enumeration value="VolumeFascicoliPropertiesType"/&amp;gt;
 *     &amp;lt;enumeration value="VolumeSottofascicoliPropertiesType"/&amp;gt;
 *     &amp;lt;enumeration value="SerieDossierPropertiesType"/&amp;gt;
 *     &amp;lt;enumeration value="SerieTipologicaDocumentiPropertiesType"/&amp;gt;
 *     &amp;lt;enumeration value="SerieFascicoliPropertiesType"/&amp;gt;
 *     &amp;lt;enumeration value="FascicoloRealeEreditatoPropertiesType"/&amp;gt;
 *     &amp;lt;enumeration value="FascicoloRealeLiberoPropertiesType"/&amp;gt;
 *     &amp;lt;enumeration value="FascicoloRealeLegislaturaPropertiesType"/&amp;gt;
 *     &amp;lt;enumeration value="FascicoloRealeAnnualePropertiesType"/&amp;gt;
 *     &amp;lt;enumeration value="FascicoloRealeContinuoPropertiesType"/&amp;gt;
 *     &amp;lt;enumeration value="FascicoloStdPropertiesType"/&amp;gt;
 *     &amp;lt;enumeration value="AnnotazionePropertiesType"/&amp;gt;
 *     &amp;lt;enumeration value="DocumentAssociationPropertiesType"/&amp;gt;
 *     &amp;lt;enumeration value="HistoryModificheTecnichePropertiesType"/&amp;gt;
 *     &amp;lt;enumeration value="DocumentCompositionPropertiesType"/&amp;gt;
 *     &amp;lt;enumeration value="HistoryVecchieVersioniPropertiesType"/&amp;gt;
 *     &amp;lt;enumeration value="ActaACEPropertiesType"/&amp;gt;
 *     &amp;lt;enumeration value="SoggettoPropertiesType"/&amp;gt;
 *     &amp;lt;enumeration value="RegistrazionePropertiesType"/&amp;gt;
 *     &amp;lt;enumeration value="CorrispondentePropertiesType"/&amp;gt;
 *     &amp;lt;enumeration value="RegistroPropertiesType"/&amp;gt;
 *     &amp;lt;enumeration value="RegistroProtocolloPropertiesType"/&amp;gt;
 *     &amp;lt;enumeration value="MessInterscambioPropertiesType"/&amp;gt;
 *     &amp;lt;enumeration value="ContentMessInterscambioPropertiesType"/&amp;gt;
 *     &amp;lt;enumeration value="EntePropertiesType"/&amp;gt;
 *     &amp;lt;enumeration value="StrutturaPropertiesType"/&amp;gt;
 *     &amp;lt;enumeration value="NodoPropertiesType"/&amp;gt;
 *     &amp;lt;enumeration value="AOOPropertiesType"/&amp;gt;
 *     &amp;lt;enumeration value="UtentePropertiesType"/&amp;gt;
 *     &amp;lt;enumeration value="FormaDocumentariaPropertiesType"/&amp;gt;
 *     &amp;lt;enumeration value="ProfiloPropertiesType"/&amp;gt;
 *     &amp;lt;enumeration value="GruppoAOOPropertiesType"/&amp;gt;
 *     &amp;lt;enumeration value="SistemaPropertiesType"/&amp;gt;
 *     &amp;lt;enumeration value="UtenteProfilo"/&amp;gt;
 *     &amp;lt;enumeration value="ProtocolloPropertiesType"/&amp;gt;
 *     &amp;lt;enumeration value="NodoConStruttura"/&amp;gt;
 *     &amp;lt;enumeration value="SmistamentoDestinatarioPropertiesType"/&amp;gt;
 *     &amp;lt;enumeration value="SmistamentoMittentePropertiesType"/&amp;gt;
 *   &amp;lt;/restriction&amp;gt;
 * &amp;lt;/simpleType&amp;gt;
 * &lt;/pre&gt;
 * 
 */
@XmlType(name = "enumObjectType")
@XmlEnum
public enum EnumObjectType {

    @XmlEnumValue("ContenutoFisicoPropertiesType")
    CONTENUTO_FISICO_PROPERTIES_TYPE("ContenutoFisicoPropertiesType"),
    @XmlEnumValue("VerifyReportPropertiesType")
    VERIFY_REPORT_PROPERTIES_TYPE("VerifyReportPropertiesType"),
    @XmlEnumValue("GruppoAllegatiPropertiesType")
    GRUPPO_ALLEGATI_PROPERTIES_TYPE("GruppoAllegatiPropertiesType"),
    @XmlEnumValue("DocumentoDBPropertiesType")
    DOCUMENTO_DB_PROPERTIES_TYPE("DocumentoDBPropertiesType"),
    @XmlEnumValue("DocumentoRegistroPropertiesType")
    DOCUMENTO_REGISTRO_PROPERTIES_TYPE("DocumentoRegistroPropertiesType"),
    @XmlEnumValue("DocumentoSemplicePropertiesType")
    DOCUMENTO_SEMPLICE_PROPERTIES_TYPE("DocumentoSemplicePropertiesType"),
    @XmlEnumValue("ClipsMetallicaPropertiesType")
    CLIPS_METALLICA_PROPERTIES_TYPE("ClipsMetallicaPropertiesType"),
    @XmlEnumValue("TitolarioPropertiesType")
    TITOLARIO_PROPERTIES_TYPE("TitolarioPropertiesType"),
    @XmlEnumValue("VocePropertiesType")
    VOCE_PROPERTIES_TYPE("VocePropertiesType"),
    @XmlEnumValue("ClassificazionePropertiesType")
    CLASSIFICAZIONE_PROPERTIES_TYPE("ClassificazionePropertiesType"),
    @XmlEnumValue("FascicoloTemporaneoPropertiesType")
    FASCICOLO_TEMPORANEO_PROPERTIES_TYPE("FascicoloTemporaneoPropertiesType"),
    @XmlEnumValue("DocumentoFisicoPropertiesType")
    DOCUMENTO_FISICO_PROPERTIES_TYPE("DocumentoFisicoPropertiesType"),
    @XmlEnumValue("SottofascicoloPropertiesType")
    SOTTOFASCICOLO_PROPERTIES_TYPE("SottofascicoloPropertiesType"),
    @XmlEnumValue("DossierPropertiesType")
    DOSSIER_PROPERTIES_TYPE("DossierPropertiesType"),
    @XmlEnumValue("VolumeSerieFascicoliPropertiesType")
    VOLUME_SERIE_FASCICOLI_PROPERTIES_TYPE("VolumeSerieFascicoliPropertiesType"),
    @XmlEnumValue("VolumeSerieTipologicaDocumentiPropertiesType")
    VOLUME_SERIE_TIPOLOGICA_DOCUMENTI_PROPERTIES_TYPE("VolumeSerieTipologicaDocumentiPropertiesType"),
    @XmlEnumValue("VolumeFascicoliPropertiesType")
    VOLUME_FASCICOLI_PROPERTIES_TYPE("VolumeFascicoliPropertiesType"),
    @XmlEnumValue("VolumeSottofascicoliPropertiesType")
    VOLUME_SOTTOFASCICOLI_PROPERTIES_TYPE("VolumeSottofascicoliPropertiesType"),
    @XmlEnumValue("SerieDossierPropertiesType")
    SERIE_DOSSIER_PROPERTIES_TYPE("SerieDossierPropertiesType"),
    @XmlEnumValue("SerieTipologicaDocumentiPropertiesType")
    SERIE_TIPOLOGICA_DOCUMENTI_PROPERTIES_TYPE("SerieTipologicaDocumentiPropertiesType"),
    @XmlEnumValue("SerieFascicoliPropertiesType")
    SERIE_FASCICOLI_PROPERTIES_TYPE("SerieFascicoliPropertiesType"),
    @XmlEnumValue("FascicoloRealeEreditatoPropertiesType")
    FASCICOLO_REALE_EREDITATO_PROPERTIES_TYPE("FascicoloRealeEreditatoPropertiesType"),
    @XmlEnumValue("FascicoloRealeLiberoPropertiesType")
    FASCICOLO_REALE_LIBERO_PROPERTIES_TYPE("FascicoloRealeLiberoPropertiesType"),
    @XmlEnumValue("FascicoloRealeLegislaturaPropertiesType")
    FASCICOLO_REALE_LEGISLATURA_PROPERTIES_TYPE("FascicoloRealeLegislaturaPropertiesType"),
    @XmlEnumValue("FascicoloRealeAnnualePropertiesType")
    FASCICOLO_REALE_ANNUALE_PROPERTIES_TYPE("FascicoloRealeAnnualePropertiesType"),
    @XmlEnumValue("FascicoloRealeContinuoPropertiesType")
    FASCICOLO_REALE_CONTINUO_PROPERTIES_TYPE("FascicoloRealeContinuoPropertiesType"),
    @XmlEnumValue("FascicoloStdPropertiesType")
    FASCICOLO_STD_PROPERTIES_TYPE("FascicoloStdPropertiesType"),
    @XmlEnumValue("AnnotazionePropertiesType")
    ANNOTAZIONE_PROPERTIES_TYPE("AnnotazionePropertiesType"),
    @XmlEnumValue("DocumentAssociationPropertiesType")
    DOCUMENT_ASSOCIATION_PROPERTIES_TYPE("DocumentAssociationPropertiesType"),
    @XmlEnumValue("HistoryModificheTecnichePropertiesType")
    HISTORY_MODIFICHE_TECNICHE_PROPERTIES_TYPE("HistoryModificheTecnichePropertiesType"),
    @XmlEnumValue("DocumentCompositionPropertiesType")
    DOCUMENT_COMPOSITION_PROPERTIES_TYPE("DocumentCompositionPropertiesType"),
    @XmlEnumValue("HistoryVecchieVersioniPropertiesType")
    HISTORY_VECCHIE_VERSIONI_PROPERTIES_TYPE("HistoryVecchieVersioniPropertiesType"),
    @XmlEnumValue("ActaACEPropertiesType")
    ACTA_ACE_PROPERTIES_TYPE("ActaACEPropertiesType"),
    @XmlEnumValue("SoggettoPropertiesType")
    SOGGETTO_PROPERTIES_TYPE("SoggettoPropertiesType"),
    @XmlEnumValue("RegistrazionePropertiesType")
    REGISTRAZIONE_PROPERTIES_TYPE("RegistrazionePropertiesType"),
    @XmlEnumValue("CorrispondentePropertiesType")
    CORRISPONDENTE_PROPERTIES_TYPE("CorrispondentePropertiesType"),
    @XmlEnumValue("RegistroPropertiesType")
    REGISTRO_PROPERTIES_TYPE("RegistroPropertiesType"),
    @XmlEnumValue("RegistroProtocolloPropertiesType")
    REGISTRO_PROTOCOLLO_PROPERTIES_TYPE("RegistroProtocolloPropertiesType"),
    @XmlEnumValue("MessInterscambioPropertiesType")
    MESS_INTERSCAMBIO_PROPERTIES_TYPE("MessInterscambioPropertiesType"),
    @XmlEnumValue("ContentMessInterscambioPropertiesType")
    CONTENT_MESS_INTERSCAMBIO_PROPERTIES_TYPE("ContentMessInterscambioPropertiesType"),
    @XmlEnumValue("EntePropertiesType")
    ENTE_PROPERTIES_TYPE("EntePropertiesType"),
    @XmlEnumValue("StrutturaPropertiesType")
    STRUTTURA_PROPERTIES_TYPE("StrutturaPropertiesType"),
    @XmlEnumValue("NodoPropertiesType")
    NODO_PROPERTIES_TYPE("NodoPropertiesType"),
    @XmlEnumValue("AOOPropertiesType")
    AOO_PROPERTIES_TYPE("AOOPropertiesType"),
    @XmlEnumValue("UtentePropertiesType")
    UTENTE_PROPERTIES_TYPE("UtentePropertiesType"),
    @XmlEnumValue("FormaDocumentariaPropertiesType")
    FORMA_DOCUMENTARIA_PROPERTIES_TYPE("FormaDocumentariaPropertiesType"),
    @XmlEnumValue("ProfiloPropertiesType")
    PROFILO_PROPERTIES_TYPE("ProfiloPropertiesType"),
    @XmlEnumValue("GruppoAOOPropertiesType")
    GRUPPO_AOO_PROPERTIES_TYPE("GruppoAOOPropertiesType"),
    @XmlEnumValue("SistemaPropertiesType")
    SISTEMA_PROPERTIES_TYPE("SistemaPropertiesType"),
    @XmlEnumValue("UtenteProfilo")
    UTENTE_PROFILO("UtenteProfilo"),
    @XmlEnumValue("ProtocolloPropertiesType")
    PROTOCOLLO_PROPERTIES_TYPE("ProtocolloPropertiesType"),
    @XmlEnumValue("NodoConStruttura")
    NODO_CON_STRUTTURA("NodoConStruttura"),
    @XmlEnumValue("SmistamentoDestinatarioPropertiesType")
    SMISTAMENTO_DESTINATARIO_PROPERTIES_TYPE("SmistamentoDestinatarioPropertiesType"),
    @XmlEnumValue("SmistamentoMittentePropertiesType")
    SMISTAMENTO_MITTENTE_PROPERTIES_TYPE("SmistamentoMittentePropertiesType");
    private final String value;

    EnumObjectType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static EnumObjectType fromValue(String v) {
        for (EnumObjectType c: EnumObjectType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
