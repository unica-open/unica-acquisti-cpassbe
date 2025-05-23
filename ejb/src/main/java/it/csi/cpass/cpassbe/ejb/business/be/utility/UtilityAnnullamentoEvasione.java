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

import java.util.List;
import java.util.Optional;

import it.csi.cpass.cpassbe.ejb.business.be.dad.DecodificaDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.DestinatarioEvasioneDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.DestinatarioOrdineDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.DocumentoTrasportoDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.DocumentoTrasportoRigaDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.RigaEvasioneDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.RigaOrdineDad;
import it.csi.cpass.cpassbe.ejb.util.ConstantsCPassStato;
import it.csi.cpass.cpassbe.ejb.util.ConstantsCPassStato.StatoDocumentoTrasportoEnum;
import it.csi.cpass.cpassbe.lib.dto.Stato;
import it.csi.cpass.cpassbe.lib.dto.ord.Destinatario;
import it.csi.cpass.cpassbe.lib.dto.ord.RigaOrdine;
import it.csi.cpass.cpassbe.lib.dto.ord.evasione.DestinatarioEvasione;
import it.csi.cpass.cpassbe.lib.dto.ord.evasione.DocumentoTrasporto;
import it.csi.cpass.cpassbe.lib.dto.ord.evasione.DocumentoTrasportoRiga;
import it.csi.cpass.cpassbe.lib.dto.ord.evasione.RigaEvasione;

public class UtilityAnnullamentoEvasione {

	public static void cambioStatoRigaOrdineDaCancellazioneEvasione(DecodificaDad decodificaDad, RigaEvasioneDad rigaEvasioneDad, RigaOrdineDad rigaOrdineDad,RigaOrdine rigaOrdine) {
		final List<RigaEvasione> righeEvasionesPerRigaOrdine = rigaEvasioneDad.findByIdRigaOrdine(rigaOrdine.getId());

		String codiceStatoRigaOrdine = null;
		// TODO ANTO Da capire con anna questa condizione se sia stata mal interpretata
		//if (righeEvasionesPerRigaOrdine.size() > 0) {
		if (righeEvasionesPerRigaOrdine.size() > 1) {
			codiceStatoRigaOrdine = ConstantsCPassStato.StatoOrdineEvasioneEnum.RIGA_ORDINE_EVASA_PARZIALMENTE.getCostante();
		} else {
			codiceStatoRigaOrdine = ConstantsCPassStato.StatoOrdineEvasioneEnum.RIGA_ORDINE_DA_EVADERE.getCostante();
		}

		/*Stato statoRigaOrdine = decodificaDad.getStato(codiceStatoRigaOrdine,ConstantsCPassStato.TipoStatoEnum.RIGA_ORDINE.getCostante()).get();
		rigaOrdine.setStato(statoRigaOrdine);
		rigaOrdineDad.updateRigaOrdine(rigaOrdine);*/

		final Optional<Stato> statoRigaOrdineOptional = decodificaDad.getStatoOpt(codiceStatoRigaOrdine, ConstantsCPassStato.TipoStatoEnum.RIGA_ORDINE.getCostante());

		if (statoRigaOrdineOptional.isPresent()) {
			final Stato statoRigaOrdine = statoRigaOrdineOptional.get();
			rigaOrdine.setStato(statoRigaOrdine);
			rigaOrdineDad.updateRigaOrdine(rigaOrdine);
		}
	}

	public static void aggiornamentoStatoDestinatarioOrdine(DecodificaDad decodificaDad, DestinatarioEvasioneDad destinatarioEvasioneDad,
			DestinatarioOrdineDad destinatarioOrdineDad, Destinatario destinatarioOrdine){
		final List<DestinatarioEvasione> destinatarioEvasionesPerRigaOrdine = destinatarioEvasioneDad.findByIdDestinatarioOrdine(destinatarioOrdine.getId());

		String codiceStatoDestinatarioOrdine = null;
		if (destinatarioEvasionesPerRigaOrdine.size() > 0 ) {
			codiceStatoDestinatarioOrdine = ConstantsCPassStato.StatoOrdineEvasioneEnum.DEST_ORDINE_EVASO_PARZIALMENTE.getCostante();
		} else {
			codiceStatoDestinatarioOrdine = ConstantsCPassStato.StatoOrdineEvasioneEnum.DEST_ORDINE_DA_EVADERE.getCostante();
		}

		/*Stato statoDestinatarioOrdine = decodificaDad.getStato(codiceStatoDestinatarioOrdine,ConstantsCPassStato.TipoStatoEnum.DEST_ORDINE.getCostante()).get();
		destinatarioOrdine.setStato(statoDestinatarioOrdine);
		destinatarioOrdineDad.updateDestinatario(destinatarioOrdine);*/

		final Optional<Stato> statoDestinatarioOrdineOptional = decodificaDad.getStatoOpt(codiceStatoDestinatarioOrdine, ConstantsCPassStato.TipoStatoEnum.DEST_ORDINE.getCostante());

		if (statoDestinatarioOrdineOptional.isPresent()) {
			final Stato statoDestinatarioOrdine = statoDestinatarioOrdineOptional.get();
			destinatarioOrdine.setStato(statoDestinatarioOrdine);
			destinatarioOrdineDad.updateDestinatario(destinatarioOrdine);
		}
	}

	public static void aggiornamentoDocumentoDiTrasporto(DocumentoTrasportoRiga documentoTrasportoRiga,
			DocumentoTrasportoDad documentoTrasportoDad,
			DocumentoTrasportoRigaDad documentoTrasportoRigaDad,
			DecodificaDad decodificaDad) {
		if (documentoTrasportoRiga != null) {
			documentoTrasportoRiga.setRigaEvasione(null);
			documentoTrasportoRiga = documentoTrasportoRigaDad.updateDocumentoTrasportoRiga(documentoTrasportoRiga);

			DocumentoTrasporto documentoTrasporto = documentoTrasportoRiga.getDocumentoTrasporto();
			documentoTrasporto.setTestataEvasione(null);
			documentoTrasporto.setStato(decodificaDad.getStato(
					StatoDocumentoTrasportoEnum.DA_ABBINARE.getCostante(),
					ConstantsCPassStato.TipoStatoEnum.DOCUMENTO_DI_TRASPORTO.getCostante()));
			documentoTrasporto = documentoTrasportoDad.updateDocumentoTrasporto(documentoTrasporto);
		}
	}

}
