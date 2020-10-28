/*-
 * ========================LICENSE_START=================================
 * CPASS BackEnd - EJB submodule
 * %%
 * Copyright (C) 2019 - 2020 CSI Piemonte
 * %%
 * SPDX-FileCopyrightText: Copyright 2019 - 2020 | CSI Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 * =========================LICENSE_END==================================
 */
package it.csi.cpass.cpassbe.ejb.business.be.service;

import java.util.List;

import it.csi.cpass.cpassbe.ejb.business.be.dad.UtenteDad;
import it.csi.cpass.cpassbe.ejb.entity.CpassRUtenteSettore;
import it.csi.cpass.cpassbe.ejb.util.ConstantsCPassStato;
import it.csi.cpass.cpassbe.ejb.util.CpassRuoloEnum;
import it.csi.cpass.cpassbe.ejb.util.CpassThreadLocalContainer;
import it.csi.cpass.cpassbe.lib.dto.Ruolo;
import it.csi.cpass.cpassbe.lib.dto.Settore;
import it.csi.cpass.cpassbe.lib.dto.Utente;
import it.csi.cpass.cpassbe.lib.dto.ord.TestataOrdine;
import it.csi.cpass.cpassbe.lib.dto.ord.evasione.TestataEvasione;

public class UtilityVisibilitàDocumentale {
	
	public static boolean isUtentePrivilegiato (UtenteDad utenteDad, Utente utenteConnesso, Settore settoreCorrente) {
		
		boolean bPrivilegiato = false;
		List<Ruolo> ruolos = utenteDad.getRuoliByUtenteSettore(utenteConnesso.getId(), settoreCorrente.getId());
		for (Ruolo ruolo : ruolos) {
			if (ruolo.getCodice().equalsIgnoreCase(CpassRuoloEnum.ADMIN.getCodice())) {
				bPrivilegiato = true;
			}
		}
		return bPrivilegiato;
	}

	public static boolean isVisibile(UtenteDad utenteDad, TestataOrdine testataOrdine) {
		Utente utenteConnesso = CpassThreadLocalContainer.UTENTE_CONNESSO.get();
		Settore settoreCorrente = CpassThreadLocalContainer.SETTORE_UTENTE.get();
		if (settoreCorrente == null) {
			return true;
		}
//
//		boolean bPrivilegiato = false;
//		List<Ruolo> ruolos = utenteDad.getRuoliByUtenteSettore(utenteConnesso.getId(), settoreCorrente.getId());
//		for (Ruolo ruolo : ruolos) {
//			if (ruolo.getCodice().equalsIgnoreCase(CpassRuoloEnum.ADMIN.getCodice())) {
//				bPrivilegiato = true;
//			}
//		}

		boolean bPrivilegiato = isUtentePrivilegiato(utenteDad, utenteConnesso, settoreCorrente);
		boolean bVisibilitàDocumentale = false;
		if (bPrivilegiato) {
			// PRIVILEGIATO che ha la visibilità sui documenti di tutte le strutture senza limiti temporali
			bVisibilitàDocumentale = true;
		} else {
			// Le regole suddette NON si applicano ai documenti in stato “BOZZA”; in quel caso, l’unico utente a poterli visualizzare (e gestire) è
			// l’utente che li ha creati oppure un utente privilegiato
			if (testataOrdine.getStato().getCodice().equals(ConstantsCPassStato.StatoEnum.BOZZA.getCostante())) {
				if (testataOrdine.getUtenteCompilatore().getCodiceFiscale().equals(utenteConnesso.getCodiceFiscale())) {
					bVisibilitàDocumentale = true;
				}

			} else {
				// NORMALE che ha la visibilità sui documenti delle strutture su cui ha operatività, limitatamente alla DATA_FINE_VALIDITA
				List<CpassRUtenteSettore> settores = utenteDad.findByUtenteSettore(utenteConnesso.getId(), null);
				for (CpassRUtenteSettore cpassRUtenteSettore : settores) {
					if (testataOrdine.getSettore().getId().equals(cpassRUtenteSettore.getCpassTSettore().getId())) {
						if (cpassRUtenteSettore.getDataValiditaFine() == null) {
							bVisibilitàDocumentale = true;
						} else if (!testataOrdine.getDataEmissione().after(cpassRUtenteSettore.getDataValiditaFine())) {
							// i documenti con la data emissione non maggiore
							bVisibilitàDocumentale = true;
						}
					}
				}
			}
		}
		return bVisibilitàDocumentale;
	}
	public static boolean isVisibile(UtenteDad utenteDad, TestataEvasione testataEvasione) {
		Utente utenteConnesso = CpassThreadLocalContainer.UTENTE_CONNESSO.get();
		Settore settoreCorrente = CpassThreadLocalContainer.SETTORE_UTENTE.get();
		if (settoreCorrente == null) {
			return true;
		}
		
//		boolean bPrivilegiato = false;
//		List<Ruolo> ruolos = utenteDad.getRuoliByUtenteSettore(utenteConnesso.getId(), settoreCorrente.getId());
//		for (Ruolo ruolo : ruolos) {
//			if (ruolo.getCodice().equalsIgnoreCase(CpassRuoloEnum.ADMIN.getCodice())) {
//				bPrivilegiato = true;
//			}
//		}
		
		boolean bPrivilegiato = isUtentePrivilegiato(utenteDad, utenteConnesso, settoreCorrente);
		boolean bVisibilitàDocumentale = false;
		if (bPrivilegiato) {
			// PRIVILEGIATO che ha la visibilità sui documenti di tutte le strutture senza limiti temporali
			bVisibilitàDocumentale = true;
		} else {
			// Le regole suddette NON si applicano ai documenti in stato “BOZZA”; in quel caso, l’unico utente a poterli visualizzare (e gestire) è
			// l’utente che li ha creati oppure un utente privilegiato
			if (testataEvasione.getStato().getCodice().equals(ConstantsCPassStato.StatoEnum.BOZZA.getCostante())) {
				if (testataEvasione.getUtenteCompilatore().getCodiceFiscale().equals(utenteConnesso.getCodiceFiscale())) {
					bVisibilitàDocumentale = true;
				}
				
			} else {
				// NORMALE che ha la visibilità sui documenti delle strutture su cui ha operatività, limitatamente alla DATA_FINE_VALIDITA
				List<CpassRUtenteSettore> settores = utenteDad.findByUtenteSettore(utenteConnesso.getId(), null);
				for (CpassRUtenteSettore cpassRUtenteSettore : settores) {
					if (testataEvasione.getSettore().getId().equals(cpassRUtenteSettore.getCpassTSettore().getId())) {
						if (cpassRUtenteSettore.getDataValiditaFine() == null) {
							bVisibilitàDocumentale = true;
						} else if (!testataEvasione.getDataInserimento().after(cpassRUtenteSettore.getDataValiditaFine())) {
							// i documenti con la data emissione non maggiore
							bVisibilitàDocumentale = true;
						}
					}
				}
			}
		}
		return bVisibilitàDocumentale;
	}
	
}
