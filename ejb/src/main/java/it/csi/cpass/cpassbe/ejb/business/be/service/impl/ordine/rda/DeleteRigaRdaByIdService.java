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
package it.csi.cpass.cpassbe.ejb.business.be.service.impl.ordine.rda;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import it.csi.cpass.cpassbe.ejb.business.be.dad.DecodificaDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.RdaDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.RmsDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.base.BaseService;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.ordine.rda.DeleteRigaRdaByIdRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.ordine.rda.DeleteRigaRdaByIdResponse;
import it.csi.cpass.cpassbe.ejb.util.ConstantsCPassStato;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;
import it.csi.cpass.cpassbe.lib.dto.Stato;
import it.csi.cpass.cpassbe.lib.dto.Utente;
import it.csi.cpass.cpassbe.lib.dto.error.MsgCpassOrd;
import it.csi.cpass.cpassbe.lib.dto.ord.rda.RigaRda;
import it.csi.cpass.cpassbe.lib.dto.rms.RigaRms;
import it.csi.cpass.cpassbe.lib.dto.rms.RmsRigaRda;
import it.csi.cpass.cpassbe.lib.dto.rms.RmsStatiRigaRms;
import it.csi.cpass.cpassbe.lib.util.threadlocal.CpassThreadLocalContainer;

public class DeleteRigaRdaByIdService extends BaseService<DeleteRigaRdaByIdRequest, DeleteRigaRdaByIdResponse> {

	private final RdaDad rdaDad;
	private final RmsDad rmsDad;
	private final DecodificaDad decodificaDad;

	public DeleteRigaRdaByIdService(ConfigurationHelper configurationHelper, RdaDad rdaDad, RmsDad rmsDad,DecodificaDad decodificaDad ) {
		super(configurationHelper);
		this.rdaDad = rdaDad;
		this.rmsDad = rmsDad;
		this.decodificaDad = decodificaDad;
	}

	@Override
	protected void checkServiceParams() {
		final UUID idRiga = request.getIdRiga();
		checkNotNull(idRiga, "idRiga", Boolean.TRUE);
	}

	@Override
	protected void execute() {
		final RigaRda rigaRda = isEntityPresent(() -> rdaDad.getRigaRdaById(request.getIdRiga()), "riga rda");
		final List<RigaRms> listaRigheRmsByTestataRda = rmsDad.getRigheRmsByRdaId(rigaRda.getTestataRda().getId());
		final Utente utente = CpassThreadLocalContainer.UTENTE_CONNESSO.get();

		checkBusinessCondition(listaRigheRmsByTestataRda.size() > 1, MsgCpassOrd.ORDORDE0150.getError());
		final List<RigaRms> listaRigheRms = rmsDad.getRigaRmsByRigaRdaId(request.getIdRiga());
		final Stato stDaEvadere = decodificaDad.getStato(ConstantsCPassStato.StatoRigaRmsEnum.DAE.getCostante(),ConstantsCPassStato.TipoStatoEnum.RIGA_RMS.getCostante());
		for(final RigaRms rms: listaRigheRms) {
			final List<RmsRigaRda> rrmsrda = rdaDad.getRRmsRdaRmsByRigheRdaRmsId(request.getIdRiga(), rms.getId());
			rms.setRigaRda(null);
			rms.setQuantitaSuRda(rms.getQuantitaSuRda().subtract(rrmsrda.get(0).getQuantita()));
			if (rms.getQuantitaSuRda().compareTo(BigDecimal.ZERO) == 0) {
				rms.setStato(stDaEvadere);
			}
			rmsDad.updateRigaRms(rms);
			insertStatiRigaRms(rms, stDaEvadere, utente);

		}
		rdaDad.deleteRRigaRdaRmsByRdaId(request.getIdRiga());
		rdaDad.deleteRigaRdaById(request.getIdRiga());
		//ORD-ORD-P007
	}

	private void insertStatiRigaRms(RigaRms riga, Stato stato, Utente utenteConnesso) {
		final RmsStatiRigaRms statiRigaRms = new RmsStatiRigaRms();
		statiRigaRms.setRigaRms(riga);
		statiRigaRms.setStato(riga.getStato().getDescrizione());
		statiRigaRms.setDataModifica(new Date());
		statiRigaRms.setUtenteModifica(utenteConnesso.getCodiceFiscale());
		if(riga.getMotivoRifiuto() != null) {
			statiRigaRms.setMotivazione(riga.getMotivoRifiuto());
		}
		if(riga.getMotivoEvasioneManuale() != null) {
			statiRigaRms.setMotivazione(riga.getMotivoEvasioneManuale());
		}

		rmsDad.insertStatiRigaRms(statiRigaRms);

	}
}
