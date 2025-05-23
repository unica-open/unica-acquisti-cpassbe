/*-
 * ========================LICENSE_START=================================
 * CPASS BackEnd - EJB submodule
 * %%
 * Copyright (C) 2019 - 2021 CSI Piemonte
 * %%
 * SPDX-FileCopyrightText: Copyright 2019 - 2021 | CSI Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 * =========================LICENSE_END==================================
 */
package it.csi.cpass.cpassbe.ejb.business.be.service.impl.rms;

import java.util.Date;
import java.util.List;

import it.csi.cpass.cpassbe.ejb.business.be.dad.DecodificaDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.RmsDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.SettoreDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.rms.PutCambioStatoRigaRmsRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.rms.PutCambioStatoRigaRmsResponse;
import it.csi.cpass.cpassbe.ejb.util.ConstantsCPassStato;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;
import it.csi.cpass.cpassbe.lib.dto.Stato;
import it.csi.cpass.cpassbe.lib.dto.Utente;
import it.csi.cpass.cpassbe.lib.dto.rms.RigaRms;
import it.csi.cpass.cpassbe.lib.dto.rms.RmsStatiRigaRms;
import it.csi.cpass.cpassbe.lib.util.threadlocal.CpassThreadLocalContainer;

public class PutCambioStatoRigaRmsService extends BaseRmsService<PutCambioStatoRigaRmsRequest,PutCambioStatoRigaRmsResponse>{

	private List<RigaRms> righeRms;
	private final DecodificaDad decodificaDad;
	private String statoCode;

	public PutCambioStatoRigaRmsService(ConfigurationHelper configurationHelper, RmsDad rmsDad,
			DecodificaDad decodificaDad, SettoreDad settoreDad) {
		super(configurationHelper, rmsDad);
		this.decodificaDad = decodificaDad;
	}

	@Override
	protected void checkServiceParams() {
		checkNotNull(request.getRigaRmsList(), "righe rms");
		checkNotNull(request.getStatoCode(), "codice stato riga rms");
	}


	@Override
	protected void execute() {
		statoCode = request.getStatoCode();
		righeRms = request.getRigaRmsList();
		final Utente utenteConnesso   = CpassThreadLocalContainer.UTENTE_CONNESSO.get();

		if(statoCode.equals(ConstantsCPassStato.StatoRigaRmsEnum.RIF.getCostante())) {
			final Stato stato = isEntityPresent(() -> decodificaDad.getStatoOpt(statoCode, ConstantsCPassStato.TipoStatoEnum.RIGA_RMS.getCostante()),"stato non censito");
			for(final RigaRms riga: righeRms) {
				riga.setStato(stato);
				rmsDad.updateRigaRms(riga);

				insertStatiRigaRms(riga, stato, utenteConnesso);
			}

		}
		if(statoCode.equals(ConstantsCPassStato.StatoRigaRmsEnum.EVM.getCostante())) {
			final Stato stato = isEntityPresent(() -> decodificaDad.getStatoOpt(statoCode, ConstantsCPassStato.TipoStatoEnum.RIGA_RMS.getCostante()),"stato non censito");
			for(final RigaRms riga: righeRms) {
				riga.setStato(stato);
				rmsDad.updateRigaRms(riga);

				insertStatiRigaRms(riga, stato, utenteConnesso);
			}

		}
		if(statoCode.equals(ConstantsCPassStato.StatoRigaRmsEnum.IAG.getCostante())) {
			final Stato stato = isEntityPresent(() -> decodificaDad.getStatoOpt(statoCode, ConstantsCPassStato.TipoStatoEnum.RIGA_RMS.getCostante()),"stato non censito");
			for(final RigaRms riga: righeRms) {
				// alter table CPASS_T_RMS_RIGA_RMS add column DATA_ATTESA_GARA TIMESTAMP;
				//alter table CPASS_T_RMS_RIGA_RMS add column UTENTE_ATTESA_GARA VARCHAR(50);
				riga.setDataAtttesaGara(new Date());
				riga.setUtenteAttesaGara(utenteConnesso.getCodiceFiscale());
				riga.setStato(stato);
				rmsDad.updateRigaRms(riga);

				insertStatiRigaRms(riga, stato, utenteConnesso);

			}

		}
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
