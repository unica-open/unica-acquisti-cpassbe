/*-
 * ========================LICENSE_START=================================
 * CPASS BackEnd - LIB submodule
 * %%
 * Copyright (C) 2019 - 2020 CSI Piemonte
 * %%
 * SPDX-FileCopyrightText: Copyright 2019 - 2020 | CSI Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 * =========================LICENSE_END==================================
 */
package it.csi.cpass.cpassbe.lib.dto.error;

/**
 * CPASS error types
 */
public enum MsgCpassPba implements ErrorCreator {

	/** Message PBA-ACQ-E-0000 */
	PBAACQE0000 ("PBA-ACQ-E-0000", "Indicare il CUI lavoro o altra acquisizione"),
	/** Message PBA-ACQ-P-0001 */
	PBAACQP0001 ("PBA-ACQ-P-0001", "È stato inserito l'acquisto con CUI: {cui}"),
	
	/**
	 * Message PBA-ACQ-E-0002
	 * @deprecated use {@link CoreError#SYSSYSE0009}
	 */
	@Deprecated(forRemoval = true)
	PBAACQE0002 ("PBA-ACQ-E-0002", "Sono stati riscontrati i seguenti errori: {errors}"),
	
	/** Message */
	PBAPRGA0002 ("PBA-PRG-A-0002", MsgTypeEnum.WARNING.getCostante(), null, "Sono presenti acquisti in Bozza. Si desidera cancellarli e confermare il programma?"),
	/** Message PBA-ACQ-E-0003 */
	PBAACQE0003 ("PBA-ACQ-E-0003", "L'acquisto non è stato inserito per i seguenti motivi: {errors}"),
	/** Message PBA-ACQ-P-0004 */
	PBAACQP0004 ("PBA-ACQ-P-0004", ""),
	/** Message PBA-ACQ-E-0005 */
	PBAACQE0005 ("PBA-ACQ-E-0005", ""),
	/** Message PBA-ACQ-E-0006 */
	PBAACQE0006 ("PBA-ACQ-E-0006", "L'acquisto con CUI: {cui} è stato modificato correttamente"),
	/** Message PBA-ACQ-E-0007 */
	PBAACQE0007 ("PBA-ACQ-E-0007", "Gli acquisti selezionati NON sono stati annullati: {cui}"),
	/** Message PBA-ACQ-A-0008 */
	PBAACQA0008 ("PBA-ACQ-A-0008", "Si desidera annullare l'acquisto?"),
	/** Message PBA-ACQ-A-0009 */
	PBAACQA0009 ("PBA-ACQ-A-0009", "Si desidera validare l’/gli acquisto/sti selezionato/i?"),
	/** Message PBA-ACQ-A-0010 */
	PBAACQA0010 ("PBA-ACQ-A-0010", "L'operazione di annullamento è avvenuta correttamente"),
	/** Message PBA-ACQ-A-0011 */
	PBAACQA0011 ("PBA-ACQ-A-0011", "Indicare almeno un importo"),
	/** Message PBA-ACQ-E-0012 */
	PBAACQE0012 ("PBA-ACQ-E-0012", "Occorre selezionare un programma"),
	/** Message PBA-ACQ-E-0013 */
	PBAACQE0013 ("PBA-ACQ-E-0013", "Non è possibile CANCELLARE l'acquisto selezionato"),
	/** Message PBA-ACQ-A-0014 */
	PBAACQA0014 ("PBA-ACQ-A-0014", "Si desidera procedere con l'annullamento degli acquisti selezionati?"),
	/** Message PBA-ACQ-E-0015 */
	PBAACQE0015 ("PBA-ACQ-E-0015", "Gli acquisti selezionati risultano essere o già annullati o validati, pertanto non è possibile procedere con l'operazione di annullamento"),
	/** Message PBA-ACQ-A-0016 */
	PBAACQA0016 ("PBA-ACQ-A-0016", "Alcuni acquisti tra quelli selezionati non possono essere annullati. Si procederà ad annullare solo gli acquisti che non sono validati né già annullati"),
	/** Message PBA-ACQ-P-0017 */
	PBAACQP0017 ("PBA-ACQ-P-0017", ""),
	/** Message PBA-ACQ-E-0018 */
	PBAACQE0018 ("PBA-ACQ-E-0018", "Il CUI indicato non è valido"),
	/** Message PBA-ACQ-E-0019 */
	PBAACQE0019 ("PBA-ACQ-E-0019", "CUI non trovato"),
	/** Message PBA-ACQ-E-0020 */
	PBAACQE0020 ("PBA-ACQ-E-0020", "L'anno acquisto deve essere coerente con gli anni del programma"),
	/** Message PBA-ACQ-E-0021 */
	PBAACQE0021 ("PBA-ACQ-E-0021", "Tra gli acquisti selezionati si procederà a validare solo gli acquisti in stato VISTO. Continuare con l’operazione?"),
	/** Message PBA-ACQ-E-0022 */
	PBAACQE0022 ("PBA-ACQ-E-0022", "Il CUI lavoro o altra acquisizione indicato non è valido"),

	PBAPRGE0023 ("PBA-PRG-E-0023", CoreError.SYSSYSE0009.getCode(), "Non sono stati valorizzati i seguenti campi: { errori }"),
	
	/** Message PBA-PRG-P-0024 */
	PBAPRGP0024 ("PBA-PRG-P-0024", "È stato inserito il programma: {prima_annualita} - {seconda_annualita} - versione {versione}"),
	/** Message PBA-PRG-E-0025 */
	PBAPRGE0025 ("PBA-PRG-E-0025", "Non è possibile inserire il programma"),
	/** Message PBA-PRG-P-0026 */
	PBAPRGP0026 ("PBA-PRG-P-0026", "Il programma {prima_annualita} - {prima_annualita_p1} - versione {versione} è stato modificato correttamente"),
	/** Message PBA-PRG-E-0027 */
	PBAPRGE0027 ("PBA-PRG-E-0027", "L'operazione non è andata a buon fine per i seguenti motivi: {errori}"),
	/** Message PBA-PRG-A-0028 */
	PBAPRGA0028 ("PBA-PRG-A-0028", "Si desidera annullare il programma?"),
	/** Message PBA-PRG-P-0029 */
	PBAPRGP0029 ("PBA-PRG-P-0029", "L'operazione di annullamento è avvenuta correttamente"),
	/** Message PBA-PRG-E-0030 */
	PBAPRGE0030 ("PBA-ACQ-P-0030", "L’operazione è stata effettuata con successo"),
	/** Message PBA-PRG-A-0031 */
	PBAPRGA0031 ("PBA-PRG-A-0031", "Si desidera confermare il programma?"),
	/** Message PBA-PRG-P-0032 */
	PBAPRGP0032 ("PBA-PRG-P-0032", "Il programma è stato confermato"),
	/** Message PBA-PRG-E-0033 */
	PBAPRGE0033 ("PBA-PRG-E-0033", CoreError.SYSSYSE0009.getCode(), "Sono presenti acquisti associati."),
	/** Message PBA-PRG-E-0034 */
	PBAPRGE0034 ("PBA-PRG-E-0034", "Il programma non è stato annullato: {prima_annualita} - {seconda_annualita} - versione {versione}"),
	
	/** Message PBA-PRG-E-0036 */
	PBAPRGE0036 ("PBA-PRG-E-0036", "Non è possibile annullare un programma associato ad acquisti non annullati. Occorre procedere prima all'annullamento degli acquisti"),
	/** Message PBA-PRG-E-0037 */
	PBAPRGE0037 ("PBA-PRG-E-0037", "Il programma {prima_annualita} - {seconda_annualita} è stato già inserito."),
	/** Message PBA-ACQ-A-0038 */
	//PBAACQA0038	 ("PBA-ACQ-A-0038", "L'elaborazione di copia acquisti anni precedenti è stata avviata. L'elaborazione sarà visibile in asincrona"),
	PBAACQE0038	 ("PBA-ACQ-E-0038", "Non e' stato possibile copiare gli acquisti seguenti poiche' traslando gli importi la tabella degli importi risulterebbe tutta a 0,00€. {cui}"),
	/** Message PBA-PRG-P-0039 */
	PBAPRGP0039  ("PBA-PRG-P-0039", ""),
	/** Message PBA-ACQ-I-0040 */
	PBAACQI0040	 ("PBA-ACQ-I-0040", "Gli acquisti selezionati sono stati già copiati per il biennio selezionato"),
	/** Message PBA-ACQ-E-0041 */
	PBAACQE0041  ("PBA-ACQ-E-0041", CoreError.SYSSYSE0009.getCode(), "I file sono vuoti"),
	/** Message PBA-ACQ-E-0042 */
	PBAACQE0042  ("PBA-ACQ-E-0042", CoreError.SYSSYSE0009.getCode(), "Il programma {prima_annualita} - {seconda_annualita} - versione {versione} non può essere inserito: la versione deve essere {nuova_versione}"),
	/** Message PBA-ACQ-E-0043 */
	PBAACQE0043  ("PBA-ACQ-E-0043", CoreError.SYSSYSE0009.getCode(), "Per l'anno {anno} mancano alcune versioni precedenti"),
	/** Message PBA-ACQ-E-0044 */
	PBAACQE0044  ("PBA-ACQ-E-0044", CoreError.SYSSYSE0009.getCode(), "Il referente indicato non è presente in archivio"),
	/** Message PBA-ACQ-E-0045 */
	PBAACQE0045  ("PBA-ACQ-E-0045", CoreError.SYSSYSE0010.getCode(), "File {nome_file}: Valore non valido nella riga {riga} colonna {colonna} descrizione {descrizione} valore {valore}"),
	/** Message PBA-PGR-A-0046 */
	PBAPGRA0046  ("PBA-PGR-A-0046", "Si desidera trasmettere i dati del programma selezionato e di tutti i suoi acquisti al Ministero? (Si ricorda che la scelta è definitiva)"),
	/** Message */
	PBAACQE0047  ("PBA-ACQ-E-0047", CoreError.SYSSYSE0010.getCode(), "File {nome_file}: Riga {riga} non valida, mancano dei campi"),
	/** Message PBA-PRG-E-0048 */
	PBAPRGE0048 ("PBA-PRG-A-0048", "Si desidera apporre il visto sul/sugli acquisto/sti selezionato/i?"),

	PBAACQA0048 ("PBA-ACQ-A-0048", "Si desidera vistare gli acquisti selezionati?"),
	PBAACQA0049 ("PBA-ACQ-A-0049", "Si desidera rifiutare gli acquisti selezionati?"),
	PBAACQA0050 ("PBA-ACQ-A-0050", "Si desidera prendere in carico gli acquisti selezionati?"),
	PBAACQA0051 ("PBA-ACQ-A-0051", "Si desidera volturare gli acquisti selezionati?"),
	PBAACQP0052 ("PBA-ACQ-A-0052", "Si desidera apporre il visto e validare gli acquisti selezionati?"),
	PBAACQE0053 ("PBA-ACQ-I-0053", ""),
	PBAACQA0054 ("PBA-ACQ-A-0054", "Tra gli acquisti selezionati si procederà ad apporre il visto solo sugli acquisti in stato BOZZA. Continuare con l’operazione?"),
	PBAACQP0055 ("PBA-ACQ-P-0055", "Tra gli acquisti selezionati  L’operazione sarà applicata solo sugli acquisti in stato “BOZZA” oppure “VISTO”. Continuare con l’operazione?"),
	PBAACQE0056 ("PBA-ACQ-E-0056", ""),
	PBAACQA0057 ("PBA-ACQ-A-0057", "Tra gli acquisti selezionati si procederà a rifiutare solo quelli in stato VISTO. Continuare con l’operazione?"),
	PBAACQP0058 ("PBA-ACQ-P-0058", "Gli acquisti selezionati sono stati presi in carico"),
	PBAACQE0059 ("PBA-ACQ-E-0059", "Non è possibile prendere in carico nessuno tra gli Acquisti selezionati"),
	PBAACQA0060 ("PBA-ACQ-A-0060", "Tra gli acquisti selezionati si effettuerà l’operazione di presa in carico solo su quelli non annullati. Continuare con l’operazione?"),
	PBAACQP0061 ("PBA-ACQ-P-0061", ""),
	PBAACQE0062 ("PBA-ACQ-E-0062", ""),
	PBAACQA0063 ("PBA-ACQ-A-0063", "Tra gli acquisti selezionati si effettuerà l’operazione di voltura solo su quelli non annullati. Continuare con l’operazione?"),
	PBAACQE0064 ("PBA-ACQ-E-0064", "Non è stato trovato alcun elemento valido"),
	PBAACQE0065 ("PBA-ACQ-E-0065", "Le spese già sostenute non possono superare il totale dell’acquisto"),
	PBAACQE0066 ("PBA-ACQ-E-0066", "Gli importi IVA non possono superare i totali dell’acquisto"),
	PBAPRGA0067 ("PBA-PRG-A-0067", "Si desidera riportare il programma in stato BOZZA?"),
	PBAPRGE0068 ("PBA-PRG-E-0068", "Esiste già un programma dello stesso biennio (con versione differente) in BOZZA"),
	PBAPRGA0069 ("PBA-PRG-A-0069", "Si desidera procedere con l’operazione di copia del programma?"),
	PBAPRGE0070 ("PBA-PRG-E-0070", "Esiste già una versione del programma in bozza")
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
	private MsgCpassPba(String code, String message) {
		this(code, null, message);
	}

	/**
	 * Private constructor
	 * @param code the code
	 * @param group the group
	 * @param message the message
	 */
	private MsgCpassPba(String code, String group, String message) {
		this.code = code;
		this.type = "ERROR";
		this.group = group;
		this.message = message;
	}
	
	private MsgCpassPba(String code, String type, String group, String message) {
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
