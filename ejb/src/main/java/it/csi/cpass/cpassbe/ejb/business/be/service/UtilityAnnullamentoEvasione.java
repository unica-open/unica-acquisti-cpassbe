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

import it.csi.cpass.cpassbe.ejb.business.be.dad.DecodificaDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.DestinatarioEvasioneDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.DestinatarioOrdineDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.RigaEvasioneDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.RigaOrdineDad;
import it.csi.cpass.cpassbe.ejb.util.ConstantsCPassStatoElOrdine;
import it.csi.cpass.cpassbe.lib.dto.ord.Destinatario;
import it.csi.cpass.cpassbe.lib.dto.ord.RigaOrdine;
import it.csi.cpass.cpassbe.lib.dto.ord.StatoElOrdine;
import it.csi.cpass.cpassbe.lib.dto.ord.evasione.DestinatarioEvasione;
import it.csi.cpass.cpassbe.lib.dto.ord.evasione.RigaEvasione;

public class UtilityAnnullamentoEvasione {

	public static void aggiornamentoStatoRigaOrdine(DecodificaDad decodificaDad, RigaEvasioneDad rigaEvasioneDad, RigaOrdineDad rigaOrdineDad,
			RigaOrdine rigaOrdine) {
		List<RigaEvasione> righeEvasionesPerRigaOrdine = rigaEvasioneDad.findByIdRigaOrdine(rigaOrdine.getId());

		String codiceStatoRigaOrdine = null;
		if (righeEvasionesPerRigaOrdine.size() > 0) {
			codiceStatoRigaOrdine = ConstantsCPassStatoElOrdine.StatoEnum.RIGA_ORDINE_EVASA_PARZIALMENTE.getCostante();
		} else {
			codiceStatoRigaOrdine = ConstantsCPassStatoElOrdine.StatoEnum.RIGA_ORDINE_DA_EVADERE.getCostante();
		}
		StatoElOrdine statoElOrdineRigaOrdine = decodificaDad.getStatoElOrdine(codiceStatoRigaOrdine,
				ConstantsCPassStatoElOrdine.TipoEnum.DEST_ORDINE.getCostante());
		rigaOrdine.setStatoElOrdine(statoElOrdineRigaOrdine);
		rigaOrdineDad.updateRigaOrdine(rigaOrdine);
	}

	public static void aggiornamentoStatoDestinatarioOrdine(DecodificaDad decodificaDad, DestinatarioEvasioneDad destinatarioEvasioneDad,
			DestinatarioOrdineDad destinatarioOrdineDad, Destinatario destinatarioOrdine) {
		List<DestinatarioEvasione> destinatarioEvasionesPerRigaOrdine = destinatarioEvasioneDad.findByIdDestinatarioOrdine(destinatarioOrdine.getId());

		String codiceStatoDestinatarioOrdine = null;
		if (destinatarioEvasionesPerRigaOrdine.size() > 0) {
			codiceStatoDestinatarioOrdine = ConstantsCPassStatoElOrdine.StatoEnum.DEST_ORDINE_EVASO_PARZIALMENTE.getCostante();
		} else {
			codiceStatoDestinatarioOrdine = ConstantsCPassStatoElOrdine.StatoEnum.DEST_ORDINE_DA_EVADERE.getCostante();
		}
		StatoElOrdine statoElOrdineDestinatarioOrdine = decodificaDad.getStatoElOrdine(codiceStatoDestinatarioOrdine,
				ConstantsCPassStatoElOrdine.TipoEnum.DEST_ORDINE.getCostante());
		destinatarioOrdine.setStatoElOrdine(statoElOrdineDestinatarioOrdine);
		destinatarioOrdineDad.updateDestinatario(destinatarioOrdine);
	}

}
