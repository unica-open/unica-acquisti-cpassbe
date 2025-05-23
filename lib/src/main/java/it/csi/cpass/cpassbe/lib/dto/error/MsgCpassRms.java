/*-
 * ========================LICENSE_START=================================
 * CPASS BackEnd - LIB submodule
 * %%
 * Copyright (C) 2019 - 2021 CSI Piemonte
 * %%
 * SPDX-FileCopyrightText: Copyright 2019 - 2021 | CSI Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 * =========================LICENSE_END==================================
 */
package it.csi.cpass.cpassbe.lib.dto.error;

public enum MsgCpassRms implements ErrorCreator {

	/** Message RMS-RMS-E-0003 */
	RMSRMSE0003 ("RMS-RMS-E-0003", CoreError.SYSSYSE0009.getCode(), "Settore non valido"),
	/** Message RMS-RMS-E-0005 */
	RMSRMSE0005 ("RMS-RMS-E-0005", CoreError.SYSSYSE0009.getCode(), "Codice CPV non valido"),
	/** Message RMS-RMS-E-0006 */
	RMSRMSE0006 ("RMS-RMS-E-0006", CoreError.SYSSYSE0009.getCode(), "Oggetto di spesa non valido"),
	/** Message RMS-RMS-E-0007 */
	RMSRMSE0007 ("RMS-RMS-E-0007", CoreError.SYSSYSE0009.getCode(), "Il codice CPV e l'oggetto di spesa sono incongruenti"),
	/** Message RMS-RMS-E-0008 */
	RMSRMSE0008 ("RMS-RMS-E-0008", CoreError.SYSSYSE0009.getCode(), "La quantita' deve essere maggiore di 0"),
	/** Message RMS-RMS-E-0009 */
	//RMSRMSE0009 ("RMS-RMS-E-0009", CoreError.SYSSYSE0009.getCode(), "La quantita' indicata non può superare la massima quantita' richiedibile"),
	RMSRMSE0016 ("RMS-RMS-E-0016",  ""),
	RMSRMSE0017 ("RMS-RMS-E-0017",  ""),

	RMSRMSE0019 ("RMS-RMS-E-0019", "Nessuna RMS trovata"),
	RMSRMSE0020 ("RMS-RMS-E-0020", "RMS inesistente oppure non di competenza dell’utente/settore emittente"),

	RMSRMSE0021 ("RMS-RMS-E-0021", "L'annullamento non e' possibile, perche' almeno una delle righe e' gia' smistata o in lavorazione"),
	/** Message RMS-RMS-E-0022 */
	RMSRMSE0022 ("RMS-RMS-E-0022", CoreError.SYSSYSE0009.getCode(), "Se l'oggetto di spesa indicato è generico, occorre compilare adeguatamente le note"),
	/** Message RMS-RMS-E-0023 */
	RMSRMSE0023 ("RMS-RMS-E-0023", CoreError.SYSSYSE0009.getCode(),"L'oggetto di spesa è già indicato in un'altra riga dell'RMS"),
	RMSRMSE0024 ("RMS-RMS-E-0024", CoreError.SYSSYSE0009.getCode(),"nessuna regola smistamento trovata"),
	RMSRMSE0025 ("RMS-RMS-E-0025", CoreError.SYSSYSE0009.getCode(),"File vuoto"),
	RMSRMSE0027 ("RMS-RMS-E-0027", CoreError.SYSSYSE0009.getCode(),"Il settore della sezione scelta non e' coerente"),
	RMSRMSE0030 ("RMS-RMS-E-0030", CoreError.SYSSYSE0010.getCode(), " riga {riga} colonna {colonna} descrizione {descrizione} valore {valore}"),
	RMSRMSE0028 ("RMS-RMS-E-0028", CoreError.SYSSYSE0009.getCode(), "Occorre indicare o un centro di acquisto o un magazzino a cui smistare le RMS(si ricorda che la scelta è esclusiva, i due dati non possono coesistere)"),

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
	private MsgCpassRms(String code, String message) {
		this(code, null, message);
	}

	/**
	 * Private constructor
	 * @param code the code
	 * @param group the group
	 * @param message the message
	 */
	private MsgCpassRms(String code, String group, String message) {
		this.code = code;
		this.type = MsgTypeEnum.ERROR.getCostante();
		this.group = group;
		this.message = message;
	}

	private MsgCpassRms(String code, String type, String group, String message) {
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
