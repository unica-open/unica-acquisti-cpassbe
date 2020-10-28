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

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import it.csi.cpass.cpassbe.ejb.business.be.dad.DestinatarioOrdineDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.RigaOrdineDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.TestataOrdineDad;
import it.csi.cpass.cpassbe.lib.dto.ord.Destinatario;
import it.csi.cpass.cpassbe.lib.dto.ord.RigaOrdine;
import it.csi.cpass.cpassbe.lib.dto.ord.TestataOrdine;

public class UtilityRigaOrdine {

	public static void aggiornamentoTotali(RigaOrdine rigaOrdineCurrent, UUID destinatarioId, TestataOrdineDad testataOrdineDad, DestinatarioOrdineDad destinatarioDad,
			RigaOrdineDad rigaOrdineDad) {
		// per allineare gli importi
		Destinatario destinatario = destinatarioDad.getDestinatario(destinatarioId).get();
		TestataOrdine testataOrdine = destinatario.getTestataOrdine();

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
		List<RigaOrdine> rigaOrdines = rigaOrdineDad.findByTestataOrdine(testataOrdine.getId(), rigaOrdineIdDaEscludere);
		for (RigaOrdine rigaOrdine : rigaOrdines) {
			totaleConIva = totaleConIva.add(rigaOrdine.getImportoTotale());
			totaleNoIva = totaleNoIva.add(rigaOrdine.getImportoNetto());
		}

		testataOrdine.setTotaleConIva(totaleConIva);
		testataOrdine.setTotaleNoIva(totaleNoIva);
		testataOrdineDad.updateTestataOrdine(testataOrdine, false);
	}

}
