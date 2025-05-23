/*-
 * ========================LICENSE_START=================================
 * CPASS BackEnd - LIB submodule
 * %%
 * Copyright (C) 2019 - 2025 CSI Piemonte
 * %%
 * SPDX-FileCopyrightText: Copyright 2019 - 2020 | CSI Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 * =========================LICENSE_END==================================
 */
package it.csi.cpass.cpassbe.lib.dto.error;

/**
 * CPASS error types
 */
public enum MsgCpassBo implements ErrorCreator {
	BACSTRE0001 ("BAC-STR-P0001",CoreError.SYSSYSE0009.getCode(),"Non e' stato trovato alcun elemento valido"),
	BACSTRE0002 ("BAC-STR-P0002",CoreError.SYSSYSE0009.getCode(),"Occorre selezionare un codice ufficio"),
	BACSTRE0003 ("BAC-STR-P0003",CoreError.SYSSYSE0009.getCode(),"Il codice ufficio selezionato e' gia' associato al settore"),
	BACSTRE0004 ("BAC-STR-P0004",CoreError.SYSSYSE0009.getCode(),"Non sono stati indicati i seguenti campi:{lista}"),
	BACSTRE0005 ("BAC-STR-P0005",CoreError.SYSSYSE0009.getCode(),"Il settore con codice {codice} esiste gia'"),
	BACSTRE0006 ("BAC-STR-P0006",CoreError.SYSSYSE0009.getCode(),"Operazione effettuata correttamente"),
	BACSTRE0007 ("BAC-STR-E0007",CoreError.SYSSYSE0009.getCode(),"Il tipo settore selezionato non è coerente rispetto al settore padre"),

	BACUTEE0001 ("BAC-UTE-E-0001",CoreError.SYSSYSE0009.getCode(),"Con i filtri indicati, non e' stato trovato alcun utente"),
	BACUTEE0002 ("BAC-UTE-E-0002",CoreError.SYSSYSE0009.getCode(),"Non e' stato trovato alcun elemento valido"),
	BACUTEP0003 ("BAC-UTE-P-0003",CoreError.SYSSYSE0009.getCode(),"Operazione effettuata correttamente"),
	BACUTEE0004 ("BAC-UTE-E-0004",CoreError.SYSSYSE0009.getCode(),"Occorre indicare sia un ruolo che un settore"),
	BACUTEE0005 ("BAC-UTE-E-0005",CoreError.SYSSYSE0009.getCode(),"Il ruolo ed il settore indicati sono gia' presenti nell'elenco"),
	BACUTEE0006 ("BAC-UTE-E-0006",CoreError.SYSSYSE0009.getCode(),"E' gia' stato indicato un altro settore di default"),
	BACUTEE0007 ("BAC-UTE-E-0007",CoreError.SYSSYSE0009.getCode(),"La data fine validita' non puo' essere anteriore alla data odierna"),
	BACUTEE0008 ("BAC-UTE-E-0008",CoreError.SYSSYSE0009.getCode(),"La sezione e' gia' presente in elenco"),
	BACUTEE0009 ("BAC-UTE-E-0009",CoreError.SYSSYSE0009.getCode(),"Occorre associare all'utente almeno un ruolo su un settore"),
	BACUTEE0010 ("BAC-UTE-E-0010",CoreError.SYSSYSE0009.getCode(),"L'utente con codice fiscale {cf} e' gia' presente negli archivi"),
	BACUTEE0011 ("BAC-UTE-E-0011",CoreError.SYSSYSE0009.getCode(),"Il codice fiscale non e' presente in HR"),
	BACUTEE0012 ("BAC-UTE-E-0012",CoreError.SYSSYSE0009.getCode(),"La ricerca in HR non e' andata a buon fine, il codice fiscale non e' presente. Verificarne la correttezza"),
	BACUTEA0013 ("BAC-UTE-A-0013",CoreError.SYSSYSE0009.getCode(),"Si desidera cancellare l'utente consultato?"),
	BACUTEA0014 ("BAC-UTE-A-0014",CoreError.SYSSYSE0009.getCode(),"Si tenga presente che l'operazione di spostamento appone una data fine validita' a tutte le strutture presenti per gli utenti selezionati (anche quelle ancora valide). Si desidera continuare?"),
	BACUTEA0015 ("BAC-UTE-A-0015",CoreError.SYSSYSE0009.getCode(),"Si tenga presente che l'operazione di spostamento appone una data fine validita' a tutte le strutture presenti per gli utenti selezionati (anche quelle ancora valide). Inoltre prevede il decadimento automatico dell'opzione 'dirigente struttura' (che dovra' poi essere reinserita manualmente, andando in modifica sull'utente). Si desidera continuare?"),
	BACUTEE0016 ("BAC-UTE-E-0016",CoreError.SYSSYSE0009.getCode(),"Il delegato indicato non e' presente in archivio"),
	BACUTEE0017 ("BAC-UTE-E-0017",CoreError.SYSSYSE0009.getCode(),"Le date inizio e fine devono essere congruenti"),
	BACUTEE0018 ("BAC-UTE-E-0018",CoreError.SYSSYSE0009.getCode(),"Codice fiscale formalmente non corretto"),
	BACUTEA0020 ("BAC-UTE-A-0020",MsgTypeEnum.WARNING.getCostante(), null, "La ricerca in HR non è andata a buon fine, il codice fiscale non è presente. Si vuole continuare con l’inserimento?"),

	BACODSE0004 ("BAC-ODS-E-0004",CoreError.SYSSYSE0009.getCode(),"Occorre indicare almeno un criterio di ricerca"),
	BACODSE0005 ("BAC-ODS-E-0005",CoreError.SYSSYSE0009.getCode(),"Il codice dell'oggetto di spesa indicato esiste gia' negli archivi"),
	BACODSE0006 ("BAC-ODS-E-0006",CoreError.SYSSYSE0009.getCode(),"L'oggetto di spesa e' già stato utilizzato, non e' possibile modificarne il codice"),
	BACODSA0007 ("BAC-ODS-A-0007",CoreError.SYSSYSE0009.getCode(),"Si desidera eliminare l'oggetto di spesa?"),
	BACODSA0008 ("BAC-ODS-A-0008",CoreError.SYSSYSE0009.getCode(),"L'oggetto di spesa e' gia' stato utilizzato, non e' possibile eliminarlo. Se si continua con l'operazione, verra' apposta una data fine validita'. Si desidera continuare?"),
	BACODSE0009 ("BAC-ODS-E-0009",CoreError.SYSSYSE0009.getCode(),"Soggetto non trovato oppure non valido"),
	BACODSE0010 ("BAC-ODS-E-0010",CoreError.SYSSYSE0009.getCode(),""),
	BACODSE0011 ("BAC-ODS-E-0011",CoreError.SYSSYSE0009.getCode(),"la riga {{num-riga}} presenta un numero colonne non conformi a quelli attesi"),

	;

	private final String code;
	private final String type;
	private final String group;
	private final String message;

	/**
	 * Private constructor
	 * @param code the code
	 * @param message the message
	 */
	private MsgCpassBo(String code, String message) {
		this(code, null, message);
	}

	/**
	 * Private constructor
	 * @param code the code
	 * @param group the group
	 * @param message the message
	 */
	private MsgCpassBo(String code, String group, String message) {
		this.code = code;
		this.type = "ERROR";
		this.group = group;
		this.message = message;
	}

	private MsgCpassBo(String code, String type, String group, String message) {
		this.code = code;
		this.type = type;
		this.group = group;
		this.message = message;
	}

	@Override
	public String getCode() {
		return code;
	}

	@Override
	public String getType() {
		return type;
	}

	@Override
	public String getGroup() {
		return group;
	}

	@Override
	public String getMessage() {
		return message;
	}

}
