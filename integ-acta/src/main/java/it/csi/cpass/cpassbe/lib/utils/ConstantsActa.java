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
package it.csi.cpass.cpassbe.lib.utils;

import java.awt.Color;


public class ConstantsActa {
    
	//COSTANTI ACTA
		//property:	
		public static final String ACTA_APPLICATION_KEY = "APPLICATION_KEY";
		public static final String ACTA_APPLICATION_INFO = "APPLICATION_INFO";
		public static final String QUERY_DESC_NODO = "descNodo";
		public static final String QUERY_CODICE = "codice";
		public static final String QUERY_OBJECT_ID = "objectId";
		public static final String QUERY_ID_DOCUMENTO = "idDocumento";
		public static final String QUERY_DB_KEY = "dbKey";
		public static final String QUERY_DB_KEY_TEMP = "dbKeyTemp";
		public static final String QUERY_STATO = "stato";
		public static final String QUERY_ID_STATO = "idStato";
		public static final String QUERY_ID_CLASSIFICAZIONE = "idClassificazione";
		public static final String QUERY_ID_CORRISPONDENTI_LIST = "idCorrispondentiList";
		public static final String QUERY_TIPO_API = "tipoAPI";
		public static final String QUERY_TIPO_MD = "tipoMD";
		public static final String QUERY_ID_SOGGETTO = "idSoggetto";
		public static final String QUERY_CODICE_FISCALE = "codiceFiscale";
		public static final String QUERY_PARTITA_IVA = "partitaIva";
		public static final String QUERY_DATA_PROTOCOLLO = "dataProtocollo";
		public static final String QUERY_INTERNO = "interno";
		public static final String QUERY_DENOMINAZIONE = "denominazione";
		public static final String QUERY_INDICE_CLASSIFICAZIONE = "indiceClassificazione";
		public static final String QUERY_INDICE_CLASSIFICAZIONE_ESTESA = "indiceClassificazioneEstesa";
		public static final String QUERY_OGGETTO = "oggetto";
		public static final String QUERY_DESCRIZIONE_STATO_REGISTRAZIONE = "descrizioneStatoRegistrazione";
		public static final String QUERY_DESCRIZIONE_TIPO_REGISTRAZIONE = "descrizioneTipoRegistrazione";
		public static final String QUERY_DESCRIZIONE = "descrizione";
		public static final String QUERY_TIPO_SMISTAMENTO = "tipoSmistamento";
		public static final String QUERY_ID_PROTOCOLLO_LIST = "idProtocolloList";
		public static final String QUERY_ID_ANNOTAZIONI_LIST = "idAnnotazioniList";
		public static final String QUERY_DATA_INIZIO_VALIDITA = "dataInizioValidita";
		public static final String QUERY_DATA_FINE_VALIDITA = "dataFineValidita";
		public static final String QUERY_ID_REGISTRAZIONE = "idRegistrazione";
		public static final String QUERY_DATA_INIZIO_VALIDITA_SOGGETTO = "dataInizioValiditaSoggetto";
		public static final String QUERY_DATA_FINE_VALIDITA_SOGGETTO = "dataFineValiditaSoggetto";
		public static final String QUERY_DESC_STATO_SOGGETTO = "descStatoSoggetto";
		public static final String QUERY_ID_TIPO_SOGGETTO = "idTipoSoggetto";
		public static final String QUERY_CODICE_TIPO_SOGGETTO = "codiceTipoSoggetto";
		public static final String QUERY_CODICE_FONTE = "codiceFonte";
		public static final String QUERY_DESC_NODO_RESPONSABILE = "descNodoResponsabile";
		public static final String QUERY_UUID = "uuid";
		public static final String QUERY_UUID_SERIE = QUERY_UUID;
		public static final String QUERY_UUID_DOCUMENTO = "uuidDocumento";
		public static final String QUERY_OBJECT_ID_DOC_SEMPLICE = "objectId_IF_DocumentoSemplicePropertiesType";
		public static final String QUERY_CHIAVE_ESTERNA = "chiaveEsterna";
		public static final String QUERY_AOO_PROTOCOLLANTE = "idAOOProtocollante";
		
		
    /** identificativo dell'applicativo */
	
	//public static final String APPLICATION_CODE = "cpass";
	//public static final String APPLICATION_VERSIONE = "5.7.7-001";
	//public static final String APPLICATION_CODE_IRIDE = "CPL";
	//public static final String APPLICATION_CODE_PROTOCOLLO = "APPJ";
	//public static final String APPLICATION_CODE_CLI = "DA0001";
    
    public static final String DATEFORMAT = "dd/MM/yyyy"; 
    
	public static final String PERC_FORMAT = "##0.00";

	public static final String NUM_FORMAT_UTENTE = "###,###,###,##0.00";
	
	public static final String NUM_FORMAT_EXCEL = "###########0.00";

	public static final String NUM_FORMAT_CALCOLATO = "###########0.00###";
	
	public static final String NUM_FORMAT_REPORT = "###,###,###,##0.00000";

	public static final char DEC_SEPARATOR = ',';

	public static final char GRP_SEPARATOR = '.';


	// Costanti relative agli eventi in broadcast
	public static final String STATOVISUAL_DAIMPOSTARE = "STATOVISUAL_DAIMPOSTARE";

	// Costanti relative agli eventi di errore
	public static final String ERROR_EVENT = "ERROR_EVENT";
	
	public static final int COD_AZIENDA = 1;
	
	//costante contenente la posizione del file contenente l'sql per gli ordini
	public static final String FILE_SQL_ORDINI = "it.csi.appj.appjcom.integration.dao.ordini.sqlOrdini";
	public static final String FILE_SQL_GENERAL = "it.csi.appj.appjcom.integration.dao.general.sqlGeneral";
	public static final String FILE_SQL_INFOUSER = "it.csi.appj.appjcom.integration.dao.infouser.sqlInfoUser";
	public static final String FILE_SQL_ANAGUFFICI = "it.csi.appj.appjcom.integration.dao.anaguffici.sqlAnaguffici";
	public static final String FILE_SQL_MENU = "it.csi.appj.appjcom.integration.dao.menu.sqlMenu";
	public static final String FILE_SQL_ODS = "it.csi.appj.appjcom.integration.dao.ods.sqlOds";
	public static final String FILE_SQL_MODELLOGUIDA = "it.csi.appj.appjcom.integration.dao.modelloguida.sqlModelloGuida";
	public static final String FILE_SQL_RMSE_ACCENTRATA="it.csi.appj.appjcom.integration.dao.rmseaccentrata.sqlRmseAccentrata";
	public static final String FILE_SQL_BUDGET="it.csi.appj.appjcom.integration.dao.budget.sqlBudget";
	public static final String FILE_SQL_RMS="it.csi.appj.appjcom.integration.dao.rms.sqlRms";
	public static final String FILE_SQL_COMUNICAZIONE = "it.csi.appj.appjcom.integration.dao.comunicazione.sqlComunicazione";
	public static final String FILE_SQL_EVASIONE_ORDINI = "it.csi.appj.appjcom.integration.dao.evasioneOrdini.sqlEvasioneOrdini";
	public static final String FILE_SQL_FATTURE = "it.csi.appj.appjcom.integration.dao.fatture.sqlFatture";
	public static final String FILE_SQL_STRUTTURA_ORGANIZZATIVA = "it.csi.appj.appjcom.integration.dao.strutturaOrganizzativa.sqlStrutturaOrganizzativa";
	public static final String FILE_SQL_DELEGHE = "it.csi.appj.appjcom.integration.dao.gestioneDeleghe.sqlDeleghe";
	public static final String FILE_SQL_TABELLE = "it.csi.appj.appjcom.integration.dao.gestioneTabelle.sqlTabelle";
	public static final String FILE_SQL_APPARECCHIATURE = "it.csi.appj.appjcom.integration.dao.apparecchiature.sqlApparecchiature";
	
	public static final String ID_DOC_ACQ_ORD = "ORD";
	public static final String ID_DOC_ACQ_BUD = "BUD";
	public static final String ID_DOC_ACQ_RMS = "RMS";
	public static final String ID_FL_CENTRALIZZATO_BUD = "N";
	
	public static final int MAX_NUM_ROWS = 500;
	public static final String COD_VALUTA_EURO = "01";
	public static final String ORD_TESTATA_VERIF_SI = "SI";
	public static final String ORD_TESTATA_VERIF_NO = "NO";
	
	public static final String TIPO_USER_NORMALE = "02";
	public static final String TIPO_USER_PRIVILEGIATO = "03";
	public static final String CITTA_DI_TORINO = "CITTA' DI TORINO";

    //	Arrotondamento di default
	//public static final int ROUNDIG_MODE_DEFAULT = BigDecimal.ROUND_HALF_UP;
	
	public static final String PARAMETRO_TOLLERANZA_RIGA = "TOLLERANZA_RIGA"; 
	
	public static final String TIPO_STATO_DOC_BOZ = "BOZ";
	public static final String TIPO_STATO_DOC_CON = "CON";
	public static final String TIPO_STATO_DOC_AUT = "AUT";
	
	//Costanti per lo stato testata modello guida
	
	public static final String STATO_ANNULLATO = "ANNULLATO";
	public static final String STATO_APERTO = "APERTO";
	public static final String STATO_SCADUTO = "SCADUTO";
	public static final String STATO_CHIUSO = "CHIUSO";
	public static final String SPECIFICO = "S";
	public static final String GENERICO = "G";
	public static final String INVENTARIABILE_SI = "S";
	public static final String INVENTARIABILE_NO = "N";
	
	
	
	//	Costanti per la scelta dello stato del modello guida da stampare
	public static final String STATO_ENTRAMBI = "STATO_ENTRAMBI";
	//Costanti per la scelta del tipo di stampa modello guida
	public static final String STAMPA_DETTAGLIO = "STAMPA_DETTAGLIO";
	public static final String STAMPA_ELENCO = "STAMPA_ELENCO";
	
	
	
	//Costanti per la validit� degli ordini di spesa del modello guida
	
	public static final String RIGA_ANNULLATA = "ANNULLATA";
	public static final String RIGA_VALIDA = "VALIDA";
	
	//Costanti stringa per le query SQL
	
	public static final String SQL_AND = " AND ";
	public static final String SQL_OR = " OR "; 
	
	//Costanti RMSE
	public static final String RMSE_DOC_ACQ_RSE ="RSE";
	
	public static final String RMSE_TIPO_DOC_AUT = "AUT";
	public static final String RMSE_TIPO_DOC_MAN = "MAN";
	public static final String RMSE_TIPO_DOC_RIF = "RIF";
	public static final String RMSE_TIPO_DOC_PRO = "PRO";
	public static final String RMSE_TIPO_DOC_PRE = "PRE";
	
	public static final String TIPO_STATO_TRA = "TRA";
	public static final String TIPO_STATO_APP = "APP";
	public static final String TIPO_STATO_PRE = "PRE";
	
	public static final String RMSE_TIPO_STATO_RIFIUTATA = "RIFIUTO RICHIESTA";
	public static final String RMSE_TIPO_STATO_PROTOCOLLATA = "PROTOCOLLATA";
	
	public static final String ODS_FITTIZIO = "Oggetto di spesa fittizio";
	
	public static final String TIPO_CONTATORE_PRE = "PRE";
	
	public static final String TIPO_CAUSALE_BLOCCANTE = "BLK";
	public static final String TIPO_CAUSALE_ANOMALIA = "ANO";
	public static final String TIPO_CAUSALE_DESCRITTIVA = "DES";
	
	public static final String RMSE_TIPO_STATO_VOCE_DAE = "DAE";
	public static final String RMSE_TIPO_STATO_VOCE_CDE = "CDE";
	public static final String RMSE_TIPO_STATO_VOCE_CEP = "CEP";
	public static final String RMSE_TIPO_STATO_VOCE_EVP = "EVP";
	public static final String RMSE_TIPO_STATO_VOCE_EVM = "EVM";
	public static final String RMSE_TIPO_STATO_VOCE_RIF = "RIF";
	public static final String RMSE_TIPO_STATO_VOCE_EVT = "EVT";
	
	public static final String RMS_STATO_VOCE_EVT = "EVT";
	
	public static final String PROTOCOLLO_STATO_SMISTATI = "PRO";
	public static final String PROTOCOLLO_STATO_RIFIUTATI = "RIF";
	public static final String PROTOCOLLO_STATO_ENTRAMBI = "ENTRAMBI";
	public static final String PROTOCOLLO_STATO_ANNULLATO = "PROTOCOLLO ANNULLATO";
	
	public static final String RIBALTAMENTO_BUDGET_ANNOPRECEDENTE ="PRECEDENTE";
	public static final String RIBALTAMENTO_BUDGET_IMPORTOZERO="IMPORTO_ZERO";
	public static final String RIBALTAMENTO_BUDGET_AUMENTO = "AUMENTO";
	public static final String RIBALTAMENTO_BUDGET_DECURTAZIONE ="DECURTAZIONE";
	
	
	public static final String PRESELEZIONE_BUDGET_BOZZA_ENTRAMBI ="ENTRAMBI";
	public static final String PRESELEZIONE_BUDGET_BOZZA_NO ="N";
	public static final String PRESELEZIONE_BUDGET_BOZZA_SI = "S";
	public static final String PRESELEZIONE_BUDGET_BOZZA_TITOLARE ="TITOLARE";
	public static final String PRESELEZIONE_BUDGET_BOZZA_UEB ="BILANCIO";
	
	
	public static final String STAMPA_BUDGET_ESTRAZIONE_PREVISIONE = "ESTRAZIONE_PREVISIONE";
	public static final String STAMPA_BUDGET_ESTRAZIONE_PROGRAMMATICHE = "ESTRAZIONE_PROGRAMMATICHE";
	public static final String STAMPA_BUDGET_ESTRAZIONE_PREDISPOSTO = "ESTRAZIONE_PREDISPOSTO";
	
	public static final String BUDGET_STATO_BOZ = "BOZ";
	public static final String BUDGET_STATO_PRE = "PRE";
	public static final String BUDGET_STATO_TRA = "TRA";
	public static final String BUDGET_STATO_CON = "CON";
	public static final String BUDGET_STATO_APP = "APP";
	
	//Protocollo
	public static final String PROGETTO_PROTOCOLLO = "PROTOCOLLO";
	public static final String PROGETTO_ACTA = "ACTA";
	public static final String PROTOCOLLO_TIPO_PARTENZA = "Partenza";
	public static final String PROTOCOLLO_TIPO_ARRIVO = "Arrivo";
	
	//Costanti generiche
	public static final String SELECT_OPTION_COMBO = "Seleziona";
	public static final String SELEZIONARE_OPTION_COMBO = "Selezionare";
	
	
	// Ordini Controller
	
	public static final String ORDINICONTROLLER = "it.csi.appj.appjcom.client.ordini.controller.OrdiniController" ;
	// Destinatario Fittizio
	public static final String DESTFITTIZIOCOD = "99999999";
	public static final String DESTFITTIZIOTIPO = "99";
	
	
	public static final char specialCharAll = '%';
	
	
	
	//Stampe RMS
	public static final String STAMPA_SINGOLA_RMS = "Stampa Singola RMS";
	public static final String STAMPA_ELENCO_RMS = "Stampa Elenco RMS";
	
	//Verifica Prezzo
	public static final String KEY_BLOCCO = "BLOCCO";
	public static final String KEY_SQUADRATURA = "SQUADRATURA";
	public static final String KEY_RIGAFULL = "RIGAFULL";
	

	public static final Color GRIGETTO = new Color(220,220,220);
	public static final Color GRIGETTO_PER_STAMPE = new Color(204,204,204);
	public static final Color AZZURRINO = new Color(153,204,255);
	

	public static final String ORDINE_TIPO_STATO_CONFERMATO="CON";
	public static final String ORDINE_TIPO_STATO_AUTORIZZATO="AUT";
	public static final String ORDINE_TIPO_STATO_SOSPESO="SOS";
	public static final String ORDINE_TIPO_STATO_ANNULLATO_CONFERMATO="ACO";
	public static final String ORDINE_TIPO_STATO_ANNULATO_AUTORIZZATO="AAU";
	public static final String ORDINE_TIPO_STATO_BOZZA="BOZ";
	public static final String ORDINE_TIPO_STATO_INVIATO="INV";
	public static final String ORDINE_TIPO_STATO_VISTATO="VIS";
	
	
	//STAMPA LISTA PER ORDINI
	public static final String ESTRAZIONE_EXCEL = "ESTRAZIONE_EXCEL";
	public static final String LISTA_ORDINI_IMPEGNO = "LISTA_ORDINI_IMPEGNO";
	public static final String LISTA_ORDINI_PROVV_SPESA = "LISTA_ORDINI_PROVV_SPESA";
	public static final String LISTA_ORDINI = "LISTA_ORDINI";
	
	
	
	public static final int CONFERMA_DIFFERENZA = 1;
	public static final int CONFERMA_FORNITORE_SOSPESO = 2;
	public static final int CONFERMA_INCONGRUENZA_FORNITORI = 4;
	public static final int CONFERMA_NO_INDIR_ALTERNATIVI = 8;
	
	
	public static final int FROM_RICERCA_DOCUMENTO = 0;
	public static final int FROM_RICERCA_REGISTRAZIONE = 1;
	public static final int FROM_RICERCA_PROTOCOLLO = 2;
	
	public static final boolean FUNZIONE_PER_DETT_FATT_AVVIABILE = true;
	public static final String FUNZIONE_PER_DETT_FATT_CDU = "FATACCESS";
	public static final String FUNZIONE_PER_DETT_FATT_CLASSE_CONTROLLER = "it.csi.appj.appjcom.client.documenti.controller.DocumentiController";
	public static final String FUNZIONE_PER_DETT_FATT_DESCRIZIONE = "Gestione Documenti";
	public static final String FUNZIONE_PER_DETT_FATT_COD_FUNZIONE = "ABB_DOC_2";
	public static final String FUNZIONE_PER_DETT_FATT_NOTE = "Interrogazione dei documenti abbinati. Abbinamento dei documenti alle evasioni ordini. Modifica dei documenti. Associazioni Causali e Penali. Modifica della situazione contabile e quadratura finanziaria. Iter documenti";
	public static final int FUNZIONE_PER_DETT_FATT_ORD = 10;
	
	public static final boolean FUNZIONE_PER_ORD_AVVIABILE = true;
	public static final String FUNZIONE_PER_ORD_CDU = "ORACCESS";
	public static final String FUNZIONE_PER_ORD_CLASSE_CONTROLLER = "it.csi.appj.appjcom.client.ordini.controller.OrdiniController";
	public static final String FUNZIONE_PER_ORD_DESCRIZIONE = "Gestione Ordini";
	public static final String FUNZIONE_PER_ORD_COD_FUNZIONE = "ORD_ORD_2";
	public static final String FUNZIONE_PER_ORD_NOTE = "Inserimento e modifca di un ordine singolo. Chiusura, conferma, Autorizzazione e protocollazione";
	public static final int FUNZIONE_PER_ORD_ORD = 0;
	
	//Costanti per la gestione dell'albero di navigazione di Gestione Gruppi e Classi
	public static final String OGGETTO_GRUPPO = "OGGETTO_GRUPPO";
	public static final String OGGETTO_CLASSE = "OGGETTO_CLASSE";
	
	//Costanti per la preselezione ods
	public static final String TIPO_RICERCA_ODS = "ods";
	public static final String TIPO_RICERCA_CLASSE = "classe";
	public static final String TIPO_RICERCA_GRUPPO = "gruppo";

	//Costanti per la preselezione apparecchiature utili per il tipo di ordinamento
	public static final String TIPO_ORDINAMENTO_ODS_DEST = "odsDest";
	public static final String TIPO_ORDINAMENTO_DEST_ODS = "destOds";

	//Costanti per la stampa apparecchiature
	public static final String TIPO_ESTRAZIONE_MATRICOLE = "elencoMatricole";
	public static final String TIPO_ESTRAZIONE_GARE = "predisposizioneGare";
	public static final String TIPO_ESTRAZIONE_BUDGET = "previsioneBudget";
	public static final String TIPO_CALCOLO_ANNUALITA = "annualita";
	public static final String TIPO_CALCOLO_GIORNI = "giorniEffettivi";
	
	
	public static final String SI="S";
	public static final String NO="N";
	
	public static final int MAGAZZINO_VIRTUALE=999;
	
	public static final String ADD_IN_LIST = "aggiungiElemento";
	public static final String REMOVE_FROM_LIST = "rimuoviElemento";
	
	public static final String STAMPA_ODS_ESTRAZIONE_GRUPPI = "STAMPA_ODS_ESTRAZIONE_GRUPPI";
	public static final String STAMPA_ODS_ESTRAZIONE_CLASSI = "STAMPA_ODS_ESTRAZIONE_CLASSI";
	public static final String STAMPA_ODS_ESTRAZIONE_ODS = "STAMPA_ODS_ESTRAZIONE_ODS";
	
	public static final String STATO_STRUTTURA_ORGANIZZATIVA_ANNULLATO = "ANNULLATO";
	public static final String STATO_STRUTTURA_ORGANIZZATIVA_VALIDO = "VALIDO";
	
	public static final String PARAMETRO_INTEGRAZIONE_ACTA = "SISTEMA_DOCUMENTALE";
	public static final String VALORE_PARAMETRO_SUITE = "SUITE";
	public static final String VALORE_PARAMETRO_ACTA = "ACTA";
	public static final String VALORE_PARAMETRO_NO_PROT = "NO PROT";
	public static final char DEC_SEPARATOR_CUSTOM = 0;
	public static final String NUM_CUSTOM_FORMAT_EXCEL = "###########000";;
	public static final String PARAMETRO_RICONOSCIMENTO_REPOSITORY = "RICONOSC_REPOSITORY";
	public static final String PARAMETRO_CREDENZ_PROTOCOLLO = "CREDENZ_PROTOCOLLO";
	public static final String PARAMETRO_FIRMA_DIGITALE = "FIRMA_DIGITALE";
	public static final String LIBRO_FIRMA = "LIBRO_FIRMA";
	public static final String PARAMETRO_ANNO_ACTA = "ANNO_ACTA";
	public static final String PARAMETRO_STATO_EFFICACIA_ORD = "STATO_EFFICACIA_ORD";
	public static final String PARAMETRO_STATO_EFFICACIA_FAT = "STATO_EFFICACIA_FAT";
	public static final String PARAMETRO_FORMA_DOC_ORDINI = "FORMA_DOC_ORDINI";
	public static final String PARAMETRO_FORMA_DOC_FATTURE = "FORMA_DOC_FATTURE";
	public static final String PARAMETRO_VITAL_RECORD_CODE = "VITAL_RECORD_CODE";
	public static final String PARAMETRO_CONTROLLO_FORNITORI = "CONTROLLO_FORNITORI";
	public static final String PARAMETRO_IDENT_DIGIT_PREDEF = "IDENT_DIGIT_PREDEF";
	public static final String PARAMETRO_ACTA_ANNI_PREC = "ACTA_ANNI_PREC";
	
	//Costanti, filtro per estrazione lista struttura organizzativa	
	public static final String STRUTTURA_STATO_ANNULLATO = "SA";
	public static final String STRUTTURA_STATO_TUTTI = "ST";
	public static final String STRUTTURA_STATO_VALIDO = "SV";
	// Costanti, ordinamento estrazione lista struttura organizzativa
	public static final String STRUTTURA_ORDINAMENTO_CODICE = "OC";
	public static final String STRUTTURA_ORDINAMENTO_DESCRIZIONE = "OD";
	
	
	
	public static final String MITTENTE_INTERNO = "I";
	public static final String MITTENTE_ESTERNO = "E";
	
	public static final String SMS_FIRMA_DIGITALE = "firma digitale";
	public static final String SMS_PROT_PARTENZA = "protocollazione in partenza";
	
	//moduli:
	public static final String MODULO_BKO = "BackOfficeServices";
	public static final String MODULO_OFFICIAL_BOOK = "OfficialBookServices";
	public static final String MODULO_SUBJECT_REGISTRIES = "SubjectRegistriesServices";
	public static final String MODULO_OBJECT = "ObjectServices";
	
	public static final String PRINCIPAL_SCADUTO = "ACARIS - Principal scaduto";
	
	public static final String ANNULLA_ELEMENTO_CORRENTE = "ANNULLA_ELEMENTO_CORRENTE";
	public static final String ANNULLA_ELEMENTO_E_DIPENDENZE = "ANNULLA_ELEMENTO_E DIPENDENZE";
	
	public static final int MAX_REC_PER_CHIAMATA = 500;
	public static final String INSERIRE = "INSERIRE";
	public static final String MODIFICARE = "MODIFICARE";
	
	public static final String TIPOLOGIA_ORD_INTERNO = "INT";
	
	public static final String ERRORE_SOGGETTI_MULTIPLI = "016";
	public static final String ERRORE_SOGGETTO_INESISTENTE = "012";
	public static final String ERRORE_STATO_SOGGETTO_NON_DEFINITIVO = "ErroreSoggettoNonDefinitivo";
	public static final String ERRORE_PARTITA_IVA_MOLTEPLICE = "errorePartitaIvaMolteplice:";
	public static final String ERRORE_SOGGETTO_GENERICO = "erroreGenericoSoggetto";
	
	//public static final String IDENTITA_DIGITALE_PREDEFINITA = "AAAAAA00A11B000J/CSI PIEMONTE/DEMO 21/ACTALIS_EU/20230308103241/16/qC6p3y3Sp3pWAUFUtdTojg==";
	public static final String GMS = "GMS";
	
	//public static final String ROOT_STRUTTURE = "ROOT_STRUTTURE";
	//public static final String NODO_RESP_APPJ = "APPJ";
	
	
}
