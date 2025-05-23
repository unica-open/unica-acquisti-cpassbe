/*-
 * ========================LICENSE_START=================================
 * CPASS BackEnd - EJB submodule
 * %%
 * Copyright (C) 2019 - 2025 CSI Piemonte
 * %%
 * SPDX-FileCopyrightText: Copyright 2019 - 2020 | CSI Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 * =========================LICENSE_END==================================
 */
package it.csi.cpass.cpassbe.ejb.business.be.utility;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import it.csi.cpass.cpassbe.ejb.business.be.dad.UtenteDad;
import it.csi.cpass.cpassbe.ejb.util.ConstantsCPassStato;
import it.csi.cpass.cpassbe.ejb.util.ConstantsDecodifiche;
import it.csi.cpass.cpassbe.ejb.util.CpassRuoloEnum;
import it.csi.cpass.cpassbe.lib.dto.Modulo;
import it.csi.cpass.cpassbe.lib.dto.Ruolo;
import it.csi.cpass.cpassbe.lib.dto.Settore;
import it.csi.cpass.cpassbe.lib.dto.Utente;
import it.csi.cpass.cpassbe.lib.dto.ord.TestataOrdine;
import it.csi.cpass.cpassbe.lib.dto.ord.evasione.TestataEvasione;
import it.csi.cpass.cpassbe.lib.dto.ord.rda.TestataRda;
import it.csi.cpass.cpassbe.lib.dto.rms.TestataRms;
import it.csi.cpass.cpassbe.lib.util.threadlocal.CpassThreadLocalContainer;

public class UtilityVisibilitaDocumentale {

	public static boolean isUtentePrivilegiato(UtenteDad utenteDad, Utente utenteConnesso, Settore settoreCorrente) {

		boolean bPrivilegiato;

		final List<Ruolo> ruolos = utenteDad.getRuoliByUtenteSettore(utenteConnesso.getId(), settoreCorrente.getId());

		final Ruolo ruolo = ruolos.stream().filter(x-> x.getCodice().equalsIgnoreCase(CpassRuoloEnum.ADMIN.getCodice())
				|| x.getCodice().equalsIgnoreCase(CpassRuoloEnum.ADMIN_ENTE.getCodice())
				|| x.getCodice().equalsIgnoreCase(CpassRuoloEnum.OSSERVATORE_RMS.getCodice())
				).findAny().orElse(null);

		bPrivilegiato = ruolo != null;

		return bPrivilegiato;
	}


	public static boolean isUtenteAdmin(UtenteDad utenteDad, Utente utenteConnesso, Settore settoreCorrente) {

		boolean bPrivilegiato;

		final List<Ruolo> ruolos = utenteDad.getRuoliByUtenteSettore(utenteConnesso.getId(), settoreCorrente.getId());

		final Ruolo ruolo = ruolos.stream().filter(x-> x.getCodice().equalsIgnoreCase(CpassRuoloEnum.ADMIN.getCodice())
				|| x.getCodice().equalsIgnoreCase(CpassRuoloEnum.ADMIN_ENTE.getCodice())
				).findAny().orElse(null);

		bPrivilegiato = ruolo != null;

		return bPrivilegiato;
	}
	public static boolean isVisibile(UtenteDad utenteDad, TestataOrdine testataOrdine) {
		return isVisibileModuloOrdine(utenteDad, testataOrdine.getStato().getCodice(), testataOrdine.getUtenteCompilatore().getCodiceFiscale(), testataOrdine.getSettore().getId(), testataOrdine.getDataEmissione(), ConstantsDecodifiche.ModuloEnum.ORD.getCodice());
	}

	public static boolean isVisibile(UtenteDad utenteDad, TestataEvasione testataEvasione) {
		return isVisibileModuloOrdine(utenteDad, testataEvasione.getStato().getCodice(), testataEvasione.getUtenteCompilatore().getCodiceFiscale(), testataEvasione.getSettore().getId(), testataEvasione.getDataInserimento(), ConstantsDecodifiche.ModuloEnum.ORD.getCodice());
	}

	public static boolean isVisibile(UtenteDad utenteDad, TestataRms testataRms) {
		return isVisibileModuloRms(utenteDad, testataRms.getStato().getCodice(), testataRms.getUtente().getCodiceFiscale(), testataRms.getSettoreEmittente().getId(), testataRms.getDataCreazione(), ConstantsDecodifiche.ModuloEnum.RMS.getCodice());
	}

	public static boolean isVisibile(UtenteDad utenteDad, TestataRda testataRda) {
		return isVisibileModuloRms(utenteDad, testataRda.getStato().getCodice(), testataRda.getUtenteCompilatore().getCodiceFiscale(), testataRda.getSettoreEmittente().getId(), testataRda.getDataCreazione(), ConstantsDecodifiche.ModuloEnum.ORD.getCodice());
	}

	private static boolean isVisibileModuloOrdine(UtenteDad utenteDad, String statoDocumento, String proprietarioDocumento,
			UUID settoreDocumento, Date dataInserimentoDocumento, String codiceModulo) {
		final Utente utenteConnesso = CpassThreadLocalContainer.UTENTE_CONNESSO.get();
		final Settore settoreCorrente = CpassThreadLocalContainer.SETTORE_UTENTE.get();
		if (settoreCorrente == null) {
			return true;
		}
		boolean isUtentePrivilegiato = isUtenteAdmin(utenteDad, utenteConnesso, settoreCorrente);

		boolean bVisibilitaDocumentale = isUtentePrivilegiato;
		// PRIVILEGIATO che ha la visibilità sui documenti di tutte le strutture senza limiti temporali

		if (!bVisibilitaDocumentale) {
			// Le regole suddette NON si applicano ai documenti in stato “BOZZA”; in quel caso, l’unico utente a poterli visualizzare (e gestire) è
			// l’utente che li ha creati oppure un utente privilegiato
			// Se il documento è in qualsiasi altro stato != BOZZA allora puo' essere visualizzato dall'utente se questi è abilitato sul settoreDocumento

			if(statoDocumento.equals(ConstantsCPassStato.StatoEnum.BOZZA.getCostante())) {
				bVisibilitaDocumentale = proprietarioDocumento.equals(utenteConnesso.getCodiceFiscale());
			}else {
				final List<Modulo> moduli = utenteDad.getModuliByUtenteAndSettore(utenteConnesso.getId(), settoreDocumento, dataInserimentoDocumento);
				final Modulo modulo = moduli.stream().filter(x->x.getCodice().equals(codiceModulo)).findAny().orElse(null);
				bVisibilitaDocumentale = modulo != null;
			}
		}
		return bVisibilitaDocumentale;
	}

	private static boolean isVisibileModuloRms(UtenteDad utenteDad, String statoDocumento, String proprietarioDocumento,
			UUID settoreDocumento, Date dataInserimentoDocumento, String codiceModulo) {
		final Utente utenteConnesso = CpassThreadLocalContainer.UTENTE_CONNESSO.get();
		final Settore settoreCorrente = CpassThreadLocalContainer.SETTORE_UTENTE.get();
		if (settoreCorrente == null) {
			return true;
		}
		boolean isUtentePrivilegiato = isUtentePrivilegiato(utenteDad, utenteConnesso, settoreCorrente);

		boolean bVisibilitaDocumentale = isUtentePrivilegiato;
		// PRIVILEGIATO che ha la visibilità sui documenti di tutte le strutture senza limiti temporali

		if (!bVisibilitaDocumentale) {
			// Le regole suddette NON si applicano ai documenti in stato “BOZZA”; in quel caso, l’unico utente a poterli visualizzare (e gestire) è
			// l’utente che li ha creati oppure un utente privilegiato
			// Se il documento è in qualsiasi altro stato != BOZZA allora puo' essere visualizzato dall'utente se questi è abilitato sul settoreDocumento

			if(statoDocumento.equals(ConstantsCPassStato.StatoEnum.BOZZA.getCostante())) {
				bVisibilitaDocumentale = proprietarioDocumento.equals(utenteConnesso.getCodiceFiscale());
			}else {
				final List<Modulo> moduli = utenteDad.getModuliByUtenteAndSettore(utenteConnesso.getId(), settoreDocumento, dataInserimentoDocumento);
				final Modulo modulo = moduli.stream().filter(x->x.getCodice().equals(codiceModulo)).findAny().orElse(null);
				bVisibilitaDocumentale = modulo != null;
			}
		}
		return bVisibilitaDocumentale;
	}


}
