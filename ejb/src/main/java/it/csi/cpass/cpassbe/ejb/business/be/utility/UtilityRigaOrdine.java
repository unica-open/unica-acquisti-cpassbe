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

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import it.csi.cpass.cpassbe.ejb.business.be.dad.DestinatarioOrdineDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.RigaOrdineDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.TestataOrdineDad;
import it.csi.cpass.cpassbe.ejb.exception.NotFoundException;
import it.csi.cpass.cpassbe.lib.dto.ord.Destinatario;
import it.csi.cpass.cpassbe.lib.dto.ord.RigaOrdine;
import it.csi.cpass.cpassbe.lib.dto.ord.TestataOrdine;
import it.csi.cpass.cpassbe.lib.util.log.LogUtil;

public class UtilityRigaOrdine {

	private static final LogUtil log = new LogUtil(UtilityRigaOrdine.class);

	public static void aggiornamentoTotali(RigaOrdine rigaOrdineCurrent, UUID destinatarioId, TestataOrdineDad testataOrdineDad, DestinatarioOrdineDad destinatarioDad,RigaOrdineDad rigaOrdineDad) {
		log.info("", "aggiornamentoTotali");
		// per allineare gli importi
		final Destinatario destinatario = destinatarioDad.getDestinatario(destinatarioId).orElseThrow(() -> new NotFoundException("destinatario"));
		final TestataOrdine testataOrdine = destinatario.getTestataOrdine();

		BigDecimal totaleConIva = new BigDecimal(0);
		BigDecimal totaleNoIva = new BigDecimal(0);
		UUID rigaOrdineIdDaEscludere = null;

		if (rigaOrdineCurrent != null) {
			totaleConIva = rigaOrdineCurrent.getImportoTotale();
			totaleNoIva = rigaOrdineCurrent.getImportoNetto();
			rigaOrdineIdDaEscludere = rigaOrdineCurrent.getId();
		}

		if (totaleConIva == null) {
			totaleConIva = new BigDecimal(0);
		}
		if (totaleNoIva == null) {
			totaleNoIva = new BigDecimal(0);
		}

		// sommo gli importi delle altre righe dell'ordine
		final List<RigaOrdine> rigaOrdines = rigaOrdineDad.findByTestataOrdine(testataOrdine.getId(), rigaOrdineIdDaEscludere);
		for (final RigaOrdine rigaOrdine : rigaOrdines) {
			totaleConIva = totaleConIva.add(rigaOrdine.getImportoTotale());
			totaleNoIva = totaleNoIva.add(rigaOrdine.getImportoNetto());
		}

		testataOrdine.setTotaleConIva(totaleConIva);
		testataOrdine.setTotaleNoIva(totaleNoIva);
		testataOrdineDad.updateTestataOrdineLigth(testataOrdine);
	}

	public static void aggiornamentoTotali(RigaOrdine rigaOrdineCurrent, UUID testataOrdineId, TestataOrdineDad testataOrdineDad, RigaOrdineDad rigaOrdineDad) {
		log.info("aggiornamentoTotali", "START");
		// per allineare gli importi
		final TestataOrdine testataOrdine = testataOrdineDad.getTestataOrdine(testataOrdineId);
		BigDecimal totaleConIva = BigDecimal.ZERO;
		BigDecimal totaleNoIva = BigDecimal.ZERO;
		// sommo gli importi delle altre righe dell'ordine
		final List<RigaOrdine> rigaOrdines = rigaOrdineDad.findByTestataOrdine(testataOrdine.getId());
		for (final RigaOrdine rigaOrdine : rigaOrdines) {
			totaleConIva = totaleConIva.add(rigaOrdine.getImportoTotale());
			totaleNoIva = totaleNoIva.add(rigaOrdine.getImportoNetto());
		}
		testataOrdine.setTotaleConIva(totaleConIva);
		testataOrdine.setTotaleNoIva(totaleNoIva);
		testataOrdineDad.updateTestataOrdineLigth(testataOrdine);
	}


	public static BigDecimal calcolaImportoNetto (BigDecimal prezzoUnitario, BigDecimal quantita) {
		if (prezzoUnitario==null) {
			prezzoUnitario = BigDecimal.ZERO;
		}
		if (quantita==null) {
			quantita = new BigDecimal(0);
		}

		return prezzoUnitario.multiply(quantita);
	}
	public static BigDecimal calcolaImportoIva (BigDecimal importoNetto, BigDecimal percentuale) {
		if (importoNetto==null) {
			importoNetto =  BigDecimal.ZERO;
		}

		if (percentuale==null) {
			percentuale = new BigDecimal(0);
		}
		return importoNetto.multiply(percentuale.divide(new BigDecimal(100)));
	}

}
