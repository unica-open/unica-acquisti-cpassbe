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
public enum MsgCpassOrd implements ErrorCreator {

	/** Message ORD-ORD-P-0001 */
	ORDORDP0001 ("ORD-ORD-P-0001", "E’ stato inserito l’ordine {{anno}}/{{numero}}."),
	/** Message ORD-ORD-E-0002 */
	ORDORDE0002 ("ORD-ORD-E-0002", "La ricerca non è andata a buon fine per i seguenti motivi: {errori}"),
	/** Message ORD-ORD-E-0003 */
	ORDORDE0003 ("ORD-ORD-E-0003", "Occorre indicare almeno un criterio di ricerca"),
	/** Message ORD-ORD-E-0004 */
	ORDORDE0004 ("ORD-ORD-E-0004", "Soggetto non trovato"),
	/** Message ORD-ORD-E-0005 */
	ORDORDE0005 ("ORD-ORD-E-0005", "Provvedimento non trovato"),
	/** Message ORD-ORD-E-0006 */
	ORDORDE0006 ("ORD-ORD-E-0006", "Provvedimento non di competenza della struttura emittente, ma del settore {cdc}"),
	/** Message ORD-ORD-P-0007 */
	ORDORDP0007 ("ORD-ORD-P-0007", "Operazione effettuata correttamente"),
	/** Message ORD-ORD-E-0008 */
	ORDORDE0008 ("ORD-ORD-E-0008", "Settore non valido"),
	/** Message ORD-ORD-E-0009 */
	ORDORDE0009 ("ORD-ORD-E-0009", "I dati dell’indirizzo del destinatario devono essere o tutti valorizzati o nessuno"),
	/** Message ORD-ORD-E-0010 */
	ORDORDE0010 ("ORD-ORD-E-0010", "Non è stato trovato alcun elemento valido"),
	/** Message ORD-ORD-E-0011 */
	ORDORDE0011 ("ORD-ORD-E-0011", CoreError.SYSSYSE0009.getCode(), "Codice CPV non valido"),
	/** Message ORD-ORD-E-0012 */
	ORDORDE0012 ("ORD-ORD-E-0012", CoreError.SYSSYSE0009.getCode(), "Oggetto di spesa non valido"),
	/** Message ORD-ORD-E-0013 */
	ORDORDE0013 ("ORD-ORD-E-0013", CoreError.SYSSYSE0009.getCode(), "Il codice CPV e l’oggetto di spesa sono incongruenti"),
	/** Message ORD-ORD-E-0014 */
	ORDORDE0014 ("ORD-ORD-E-0014", CoreError.SYSSYSE0009.getCode(), "Unità di misura non valida"),
	/** Message ORD-ORD-E-0015 */
	ORDORDE0015 ("ORD-ORD-E-0015", CoreError.SYSSYSE0009.getCode(), "Aliquota Iva non valida"),
	/** Message ORD-ORD-E-0016 */
	ORDORDE0016 ("ORD-ORD-E-0016", "La differenza tra l’iva indicata e l’iva calcolata supera il parametro di tolleranza"),
	/** Message ORD-ORD-A-0017 */
	ORDORDA0017 ("ORD-ORD-A-0017", "L’iva indicata e l’iva calcolata sono diverse, nei limiti del parametro di tolleranza. Si desidera comunque continuare con il salvataggio?"),
	/** Message ORD-ORD-E-0018 */
	ORDORDE0018 ("ORD-ORD-E-0018", CoreError.SYSSYSE0009.getCode(), "La quantità deve essere maggiore di 0"),
	/** Message ORD-ORD-E-0019 */
	ORDORDE0019 ("ORD-ORD-E-0019", CoreError.SYSSYSE0009.getCode(), "Il prezzo unitario deve essere maggiore di 0"),
	
	/** Message ORD-ORD-E-0021 */
	ORDORDE0021 ("ORD-ORD-E-0021", CoreError.SYSSYSE0009.getCode(), "Gli impegni non sono validi"),	
	
	/** Message ORD-ORD-I-0024 */
	ORDORDI0024 ("ORD-ORD-I-0024", "La riga è stata inserita, ma non è stato possibile inserire gli impegni. Controllare gli impegni associati all’ordine"),
	
	/** Message ORD-ORD-I-0025 */
	ORDORDI0025 ("ORD-ORD-I-0025", "La disponibilità residua sugli impegni non è sufficiente: rimangono ancora da finanziare {X} euro"),
	
	/** Message ORD-ORD-E-0026 */
	ORDORDE0026 ("ORD-ORD-E-0026", CoreError.SYSSYSE0009.getCode(), "Per alcuni impegni, la disponibilità residua ad ordinare non è sufficiente"),	
	
	/** Message ORD-ORD-E-0027 */
	ORDORDE0027 ("ORD-ORD-E-0027", CoreError.SYSSYSE0009.getCode(), "Non è possibile collegare sia impegni futuri che impegni di competenza/residui"),	
	
	/** Message ORD-ORD-E-0028 */
	ORDORDA0028 ("ORD-ORD-A-0028", MsgTypeEnum.WARNING.getCostante(), null, "Il fornitore dell'ordine non è citato sugli impegni utilizzati: potrebbe essere presente una classe di soggetti; verificare i dati nel sistema contabile. Si desidera continuare con l'operazione?"),	
	
	/** Message ORD-ORD-E-0029 */
	ORDORDE0029 ("ORD-ORD-E-0029", CoreError.SYSSYSE0009.getCode(), "Il fornitore dell'ordine non è citato sugli impegni utilizzati. Non è possibile continuare con l'operazione."),	
		
	/** Message ORD-ORD-E-0030 */
	ORDORDE0030 ("ORD-ORD-E-0030", "Il destinatario relativo a questo settore esiste già"),
	
	/** Message ORD-ORD-E-0031 */
	ORDORDE0031 ("ORD-ORD-E-0031", CoreError.SYSSYSE0009.getCode(), "La riga relativa all’oggetto di spesa indicato esiste già per questo destinatario"),
	
	ORDORDE0035 ("ORD-ORD-E-0035", "L’operazione non è andata a buon fine: le righe del destinatario selezionato sono state tutte scartate perché contengono elementi non validi."),
	ORDORDP0036 ("ORD-ORD-P-0036", "L’operazione di copia è andata a buon fine, ma alcuni impegni non sono stati copiati, perché non validi o senza disponibilità ad ordinare. Verificare i dati."),
	ORDORDA0041 ("ORD-ORD-A-0041", "Si desidera annullare l’ordine <<Anno Ordine >> / << Numero Ordine >> ?"),
	
	ORDORDE0043 ("ORD-ORD-E-0043", "Nessun ordine trovato"),
	ORDORDE0044 ("ORD-ORD-E-0044", "Ordine/i non di competenza dell’utente/settore emittente"),
	
	ORDORDE0046 ("ORD-ORD-I-0046", "Tutti i controlli sull’ordine sono andati a buon fine"),
	ORDORDE0047 ("ORD-ORD-E-0047", CoreError.SYSSYSE0009.getCode(), "L’ordine non è completo"),
	ORDORDE0048 ("ORD-ORD-E-0048", CoreError.SYSSYSE0009.getCode(), "Il fornitore non è valido"),
	ORDORDE0049 ("ORD-ORD-E-0049", CoreError.SYSSYSE0009.getCode(), "Il provvedimento di spesa non esiste"),
	ORDORDE0050 ("ORD-ORD-E-0050", CoreError.SYSSYSE0009.getCode(), "Il settore emittente è stato chiuso"),
	ORDORDE0051 ("ORD-ORD-E-0051", CoreError.SYSSYSE0009.getCode(), "Uno o più destinatari risultano chiusi"),
	ORDORDE0052 ("ORD-ORD-E-0052", CoreError.SYSSYSE0009.getCode(), "Uno o più indirizzi indicati risultano chiusi"),
	ORDORDE0053 ("ORD-ORD-E-0053", CoreError.SYSSYSE0009.getCode(), "Uno o più oggetti di spesa non sono più validi"),
	ORDORDE0054 ("ORD-ORD-E-0054", CoreError.SYSSYSE0009.getCode(), "Le aliquote iva non sono più valide"),
	ORDORDE0055 ("ORD-ORD-E-0055", CoreError.SYSSYSE0009.getCode(), "Le unità di misura non sono più valide"),
	ORDORDE0056 ("ORD-ORD-E-0056", CoreError.SYSSYSE0009.getCode(), "Sono stati riscontrati problemi di disponibilità per i seguenti impegni: {impegni}"),
	ORDORDE0057 ("ORD-ORD-E-0057", CoreError.SYSSYSE0009.getCode(), "Non sono state indicate le date di consegna sulla testata dell’ordine"),
	ORDORDE0058 ("ORD-ORD-E-0058", CoreError.SYSSYSE0009.getCode(), "Non è stato indicato l’indirizzo unico di consegna o, in alternativa, gli indirizzi di consegna sui destinatari"),
	ORDORDE0059 ("ORD-ORD-E-0059", CoreError.SYSSYSE0009.getCode(), "Per alcune righe, gli importi non coincidono con i totali indicati sugli impegni"),
	ORDORDE0060 ("ORD-ORD-E-0060", CoreError.SYSSYSE0009.getCode(), "Per alcune righe, la differenza tra l’iva indicata e l’iva calcolata supera il parametro di tolleranza"),
	ORDORDI0061 ("ORD-ORD-I-0061", MsgTypeEnum.INFO.getCostante(), null, "Per alcune righe, l’iva indicata e l’iva calcolata sono diverse, nei limiti del parametro di tolleranza. Si potrà comunque continuare con l’iter dell’ordine"),
	ORDORDA0062 ("ORD-ORD-A-0062", "Tutti i controlli sono andati a buon fine. Si vuol procedere con la conferma dell’ordine?"),
	ORDORDA0063 ("ORD-ORD-A-0063", "Tutti i controlli sono andati a buon fine. Si vuol procedere con l’autorizzazione dell’ordine?"),
	ORDORDA0064 ("ORD-ORD-A-0064", "Il protocollo dell’ordine non è annullato. Si desidera comunque continuare con l’operazione di annullamento dell’ordine <<Anno Ordine >> / << Numero Ordine >>?"),
	ORDORDE0065 ("ORD-ORD-E-0065", "Il protocollo dell’ordine non è annullato. Impossibile continuare con l’operazione di annullamento dell’ordine."),
	ORDORDE0067 ("ORD-ORD-E-0067", CoreError.SYSSYSE0009.getCode(), "L’importo netto deve essere maggiore di 0"),
	ORDORDE0068 ("ORD-ORD-E-0068", CoreError.SYSSYSE0009.getCode(), "L’importo totale deve essere maggiore di 0"),
	
	ORDORDA0073 ("ORD-ORD-A-0073", MsgTypeEnum.WARNING.getCostante(), null, "Per alcune righe, l’iva indicata e l’iva calcolata sono diverse, nei limiti del parametro di tolleranza. Si desidera procedere?"),
	ORDORDE0074 ("ORD-ORD-E-0074", "Soggetto non trovato oppure non valido"),
	
	ORDORDE0074B ("ORD-ORD-E-0074B", "Documento Spesa non trovato oppure non valido"),

	ORDORDE0075 ("ORD-ORD-E-0075", CoreError.SYSSYSE0009.getCode(), "L’ordine non è autorizzato"),
	ORDORDE0076 ("ORD-ORD-E-0076", CoreError.SYSSYSE0009.getCode(), "L’ordine non ha destinatari trasmessi a NSO"),
	ORDORDE0077 ("ORD-ORD-E-0077", CoreError.SYSSYSE0009.getCode(), "L’ordine è già totalmente evaso"),
	ORDORDE0078 ("ORD-ORD-E-0078", CoreError.SYSSYSE0009.getCode(), "L’ordine è collegato ad impegni futuri, non può essere ancora evaso"),
	
	ORDORDE0081 ("ORD-ORD-E-0081", "Nessuna evasione trovata"),
	ORDORDE0082 ("ORD-ORD-E-0082", "Evasione/i non di competenza dell'utente/settore emittente"),
	
	ORDORDE0086 ("ORD-ORD-E-0086", CoreError.SYSSYSE0009.getCode(), "L’impegno {anno} / {numero} non è presente nel sistema contabile"),
	ORDORDE0087 ("ORD-ORD-E-0087", CoreError.SYSSYSE0009.getCode(), "L’impegno {anno} / {numero} non è un impegno riaccertato dell’impegno {anno-impegno-ordine} / {numero-impegno-ordine}"),
	ORDORDE0088 ("ORD-ORD-E-0088", CoreError.SYSSYSE0009.getCode(), "L’impegno {anno} / {numero} non è definitivo"),
	ORDORDE0089 ("ORD-ORD-E-0089", CoreError.SYSSYSE0009.getCode(), "Per l’impegno {anno} / {numero} è stato superato il disponibile a liquidare"),
	ORDORDE0090 ("ORD-ORD-E-0090", CoreError.SYSSYSE0009.getCode(), "I documenti trovati sono in uno stato non compatibile con l'aggancio agli ordini"),
	ORDORDE0091 ("ORD-ORD-E-0091", "Documento non trovato"),
	ORDORDE0092 ("ORD-ORD-E-0092", CoreError.SYSSYSE0009.getCode(), "Gli ordini citati nel documento non coincidono con gli ordini collegati all’evasione"),
	ORDORDE0093 ("ORD-ORD-E-0093", CoreError.SYSSYSE0009.getCode(), "Il totale liquidabile del documento non coincide con il totale evaso"),
	
	ORDORDE0096 ("ORD-ORD-E-0096", CoreError.SYSSYSE0009.getCode(), "Alcuni impegni/subimpegni indicati non sono compatibili con il fornitore della fattura collegata all’evasione"),
	ORDORDA0097 ("ORD-ORD-A-0097", MsgTypeEnum.WARNING.getCostante(), null, "Il fornitore della fattura non è citato sugli impegni utilizzati: potrebbe essere presente una classe di soggetti; verificare i dati nel sistema contabile. Si desidera continuare con l'operazione?"),
	ORDORDA0098 ("ORD-ORD-A-0098", MsgTypeEnum.WARNING.getCostante(), null, "Il fornitore dell’evasione e il fornitore della fattura non coincidono e non sono collegati. Si conferma il salvataggio?"),
	
	ORDORDE0101("ORD-ORD-E-0101", CoreError.SYSSYSE0009.getCode(), "Il totale evaso non può essere maggiore del totale liquidabile del documento"),
	ORDORDE0102("ORD-ORD-E-0102", CoreError.SYSSYSE0009.getCode(), "Il totale evaso è maggiore del totale liquidabile e la differenza supera il parametro di tolleranza"),
	ORDORDA0103("ORD-ORD-A-0103", MsgTypeEnum.WARNING.getCostante(), null, "Il totale evaso è maggiore del totale liquidabile nei limiti del parametro di tolleranza: continuare con l’operazione?"),
	ORDORDE0104("ORD-ORD-E-0104", CoreError.SYSSYSE0009.getCode(), "L’evasione non è completa: verificare che sia stata collegata una fattura e che ogni destinatario abbia almeno una riga ed un impegno collegati"),
	ORDORDE0105("ORD-ORD-E-0105", CoreError.SYSSYSE0009.getCode(), "Alcuni impegni non sono definitivi"),
	ORDORDE0106("ORD-ORD-E-0106", CoreError.SYSSYSE0009.getCode(), "Per alcun impegni è stato superato il disponibile a liquidare"),
	ORDORDA0107("ORD-ORD-A-0107", "Tutti i controlli sono andati a buon fine. Si vuol procedere con l’autorizzazione dell’evasione?"),
	ORDORDE0108("ORD-ORD-E-0108", CoreError.SYSSYSE0009.getCode(), "Lo stato della fattura collegata all’evasione non è compatibile con l’aggancio degli ordini"),
	ORDORDA0109("ORD-ORD-A-0109", "Si vuol procedere con l’invio degli impegni in contabilità?"),
	ORDORDA0110("ORD-ORD-A-0110", "Si desidera cancellare l’evasione: {{anno}} / {{numero}} ?"),
	ORDORDA0111("ORD-ORD-A-0111", "Si desidera annullare l’evasione: {{anno}} / {{numero}} ?"),
	ORDORDA0112("ORD-ORD-A-0112", MsgTypeEnum.WARNING.getCostante(), null, "Il documento collegato è annullato. L'evasione sarà annullata. Si vuole continuare con l'operazione di annullamento?"),
	ORDORDA0113("ORD-ORD-A-0113", MsgTypeEnum.WARNING.getCostante(), null, "L'evasione sarà annullata ma, prima di reinserirne una nuova, occorre verificare la fattura sul sistema contabile e cancellare gli eventuali impegni sulle quote ad essa collegate. Si desidera continuare con l'operazione?"),
	ORDORDE0114("ORD-ORD-E-0114", "Non è possibile annullare l’evasione; occorre prima andare a cancellare gli impegni sul documento nel sistema contabile"),
	ORDORDE0115("ORD-ORD-E-0115", "Non è possibile eliminare l’unica riga del destinatario"),
	ORDORDE0116("ORD-ORD-E-0116", "Non è possibile eliminare l’unico destinatario dell’evasione"),
	ORDORDE0117("ORD-ORD-E-0117", CoreError.SYSSYSE0009.getCode(), "L’ordine non ha destinatari da chiudere"),
	ORDORDE0118("ORD-ORD-E-0118", CoreError.SYSSYSE0009.getCode(), "Ci sono ancora delle evasioni collegate all’ordine che non hanno concluso il loro ciclo"),
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
	private MsgCpassOrd(String code, String message) {
		this(code, null, message);
	}

	/**
	 * Private constructor
	 * @param code the code
	 * @param group the group
	 * @param message the message
	 */
	private MsgCpassOrd(String code, String group, String message) {
		this.code = code;
		this.type = MsgTypeEnum.ERROR.getCostante();
		this.group = group;
		this.message = message;
	}
	
	private MsgCpassOrd(String code, String type, String group, String message) {
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
