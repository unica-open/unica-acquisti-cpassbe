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
import it.csi.cpass.cpassbe.ejb.business.be.service.request.ordine.rda.DeleteTestataRdaByIdRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.ordine.rda.DeleteTestataRdaByIdResponse;
import it.csi.cpass.cpassbe.ejb.util.ConstantsCPassStato;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;
import it.csi.cpass.cpassbe.lib.dto.Stato;
import it.csi.cpass.cpassbe.lib.dto.Utente;
import it.csi.cpass.cpassbe.lib.dto.error.MsgCpassOrd;
import it.csi.cpass.cpassbe.lib.dto.ord.rda.RigaRda;
import it.csi.cpass.cpassbe.lib.dto.ord.rda.TestataRda;
import it.csi.cpass.cpassbe.lib.dto.rms.RigaRms;
import it.csi.cpass.cpassbe.lib.dto.rms.RmsRigaRda;
import it.csi.cpass.cpassbe.lib.dto.rms.RmsStatiRigaRms;
import it.csi.cpass.cpassbe.lib.util.threadlocal.CpassThreadLocalContainer;

public class DeleteTestataRdaByIdService extends BaseService<DeleteTestataRdaByIdRequest, DeleteTestataRdaByIdResponse> {

	private final RdaDad rdaDad;
	private final RmsDad rmsDad;
	private final DecodificaDad decodificaDad;
	/**
	 * Constructor
	 *
	 * @param configurationHelper
	 * @param testataOrdineDad
	 * @param destinatarioDad
	 * @param rigaOrdineDad
	 * @param impegnoDad
	 */
	public DeleteTestataRdaByIdService(ConfigurationHelper configurationHelper, RdaDad rdaDad, RmsDad rmsDad, DecodificaDad decodificaDad) {
		super(configurationHelper);
		this.rdaDad = rdaDad;
		this.rmsDad = rmsDad;
		this.decodificaDad = decodificaDad;
	}

	@Override
	protected void checkServiceParams() {
		final UUID idTestataOrdine = request.getIdTestataRda();
		checkNotNull(idTestataOrdine, "idTestataOrdine", Boolean.TRUE);
	}

	@Override
	protected void execute() {
		final TestataRda testataRda = isEntityPresent(() -> rdaDad.getTestataRdaById(request.getIdTestataRda()), "testata rda");
		//List<RigaRms> listaRigheRmsByTestataRda = rmsDad.getRigheRmsByRdaId(request.getIdTestataRda());
		final Utente utente = CpassThreadLocalContainer.UTENTE_CONNESSO.get();
		final Stato stRmsDaEvadere              = decodificaDad.getStato(ConstantsCPassStato.StatoRigaRmsEnum.DAE.getCostante(),ConstantsCPassStato.TipoStatoEnum.RIGA_RMS.getCostante());
		final Stato stTestataRdaAnnullata       = decodificaDad.getStato(ConstantsCPassStato.StatoRdaEnum.ANNULLATA.getCostante(),ConstantsCPassStato.TipoStatoEnum.RDA.getCostante());
		final Stato stRigaRdaAnnullataDaEvadere = decodificaDad.getStato(ConstantsCPassStato.StatoRigaRdaEnum.ADE.getCostante(),ConstantsCPassStato.TipoStatoEnum.RIGA_RDA.getCostante());
		final Stato stRigaRdaDaEvadere          = decodificaDad.getStato(ConstantsCPassStato.StatoRigaRdaEnum.DAE.getCostante(),ConstantsCPassStato.TipoStatoEnum.RIGA_RDA.getCostante());

		boolean nonAnnullabile = false;
		for(final RigaRda rda: testataRda.getRigaRda()) {
			final String statoCode = rda.getStato().getCodice();
			if(!statoCode.equals(stRigaRdaDaEvadere.getCodice())) {
				nonAnnullabile = Boolean.TRUE;
			}
		}
		checkBusinessCondition(!nonAnnullabile, MsgCpassOrd.ORDORDE0153.getError() );

		for(final RigaRda rrda: testataRda.getRigaRda()) {
			final List<RigaRms> righeRms = rmsDad.getRigaRmsByRigaRdaId(rrda.getId());
			for(final RigaRms rms: righeRms) {
				final List<RmsRigaRda> rrmsrda = rdaDad.getRRmsRdaRmsByRigheRdaRmsId(rrda.getId(), rms.getId());
				rms.setRigaRda(null);
				rms.setQuantitaSuRda(rms.getQuantitaSuRda().subtract(rrmsrda.get(0).getQuantita()));
				if (rms.getQuantitaSuRda().compareTo(BigDecimal.ZERO) == 0) {
					rms.setStato(stRmsDaEvadere);
				}

				rmsDad.updateRigaRms(rms);
				insertStatiRigaRms(rms, stRmsDaEvadere, utente);

			}
			if(testataRda.getStato().getCodice().equals(ConstantsCPassStato.StatoRdaEnum.BOZZA.getCostante())) {
				rdaDad.deleteRRigaRdaRmsByRdaId(rrda.getId	());
				rdaDad.deleteRigaRdaById(rrda.getId());
			}else {
				rrda.setStato(stRigaRdaAnnullataDaEvadere);
				rdaDad.updateRigaRda(rrda);
			}
		}
		testataRda.setStato(stTestataRdaAnnullata);
		rdaDad.updateTestataRda(testataRda);
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
