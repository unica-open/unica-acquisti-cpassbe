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

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import it.csi.cpass.cpassbe.ejb.business.be.dad.DecodificaDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.RmsDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.base.BaseService;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.rms.PostRigaRmsRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.rms.PostRigaRmsResponse;
import it.csi.cpass.cpassbe.ejb.external.ExternalHelperLookup;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;
import it.csi.cpass.cpassbe.lib.dto.Cpv;
import it.csi.cpass.cpassbe.lib.dto.Ente;
import it.csi.cpass.cpassbe.lib.dto.Ods;
import it.csi.cpass.cpassbe.lib.dto.Settore;
import it.csi.cpass.cpassbe.lib.dto.Utente;
import it.csi.cpass.cpassbe.lib.dto.error.MsgCpassRms;
import it.csi.cpass.cpassbe.lib.dto.rms.RigaRms;
import it.csi.cpass.cpassbe.lib.dto.rms.RmsStatiRigaRms;
import it.csi.cpass.cpassbe.lib.util.threadlocal.CpassThreadLocalContainer;

public class PostRigaRmsService extends BaseService<PostRigaRmsRequest, PostRigaRmsResponse>{


	private final RmsDad rmsDad;
	private RigaRms rigaRms;
	private final DecodificaDad decodificaDad;
	/*
	private final SystemDad systemDad;
	private final UtenteDad utenteDad;
	private final CommonDad commonDad;*/

	public PostRigaRmsService(ConfigurationHelper configurationHelper,ExternalHelperLookup externalHelperLookup,RmsDad rmsDad, DecodificaDad decodificaDad) {
		super(configurationHelper);
		this.decodificaDad = decodificaDad;
		/*
		this.utenteDad = utenteDad;
		this.systemDad = systemDad;
		this.commonDad = commonDad;*/
		this.rmsDad=rmsDad;
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void checkServiceParams() {
		rigaRms = request.getRigaRms();
		checkNotNull(rigaRms.getQuantita(), "quantita", true);
	}

	@Override
	protected void execute() {
		boolean hasErrors = false;
		final Date now = new Date();
		final Settore settoreUtente = CpassThreadLocalContainer.SETTORE_UTENTE.get();
		final Utente utenteConnesso = CpassThreadLocalContainer.UTENTE_CONNESSO.get();
		final Ente ente = settoreUtente.getEnte();
		final Ods ods = decodificaDad.getOdsByCodice(rigaRms.getOggettiSpesa().getCodice());

		//controllo che esista il cpv e non sia scaduto
		final Cpv cpv = decodificaDad.getCpvByCodice(rigaRms.getOggettiSpesa().getCpv().getCodice());
		if(cpv == null) {
			hasErrors = Boolean.TRUE;
			response.addApiError(MsgCpassRms.RMSRMSE0005.getError());
			return;
		}


		//controllo che esista l'ods e non sia scaduto
		if(ods == null || (ods.getDataValiditaFine() != null && ods.getDataValiditaFine().before(now))) {
			hasErrors = Boolean.TRUE;
			response.addApiError(MsgCpassRms.RMSRMSE0006.getError());
			return;
		}

		// controllo congruenza tra i due
		if(!ods.getCpv().getCodice().equals(cpv.getCodice())) {
			hasErrors = Boolean.TRUE;
			response.addApiError(MsgCpassRms.RMSRMSE0007.getError());
		}

		//controllo se il tipo dell'oggetto di spesa è generico
		if(ods.getGenerico() != null && ods.getGenerico() && (rigaRms.getNote() == null || rigaRms.getNote().equals(""))){
			hasErrors = Boolean.TRUE;
			response.addApiError(MsgCpassRms.RMSRMSE0022.getError());
		}
		//confronto che la qta max richiedibile dell'ods se esiste sia al più uguale alla quantita indicata della riga
		//CPASS-692 - QuantitaMaxRichiedibile campo eliminato su riga rms
		/*if(ods.getQuantitaMaxRichiedibile() != null) {

			if(rigaRms.getQuantita().compareTo(ods.getQuantitaMaxRichiedibile()) > 0) {
				hasErrors = true;
				response.addApiError(MsgCpassRms.RMSRMSE0009.getError());
			}
		}*/
		//controllo quantita sia positiva
		if(rigaRms.getQuantita().compareTo(BigDecimal.ZERO) < 1) {
			hasErrors = Boolean.TRUE;
			response.addApiError(MsgCpassRms.RMSRMSE0008.getError());
		}
		/*
		 * L'ods indicato se specifico,non deve essere già presente in un' altra riga della
		 * stessa RMS, altrimenti occorre esporre il messaggio RMS-RMS-E-0023
		 */
		if(ods.getGenerico() == null ||  (ods.getGenerico() != null && !ods.getGenerico())) {
			final List<RigaRms> rigas = rmsDad.findRigheByTestataRms(rigaRms.getTestataRms().getId(), null);
			if(rigas != null && rigas.size() > 0) {
				boolean equalOds = false;
				for(int i = 0; i < rigas.size() && !equalOds; i++) {
					equalOds = rigas.get(i).getOggettiSpesa().getCodice().equals(ods.getCodice());
					if(equalOds) {
						hasErrors = Boolean.TRUE;
						response.addApiError(MsgCpassRms.RMSRMSE0023.getError());
					}
				}
			}

		}
		if(!hasErrors) {
			rigaRms.setEnte(ente);
			if(rigaRms.getMagazzino() == null || rigaRms.getMagazzino().getId() == null) {
				rigaRms.setMagazzino(null);
			}
			if(rigaRms.getSettoreAcquisto() == null || rigaRms.getSettoreAcquisto().getId() == null) {
				rigaRms.setSettoreAcquisto(null);
			}
			//TODO da rifare tarocco per poi capire meglio come gestirlo dato che dalla pagina arriva
			//in taluni casi l'oggetto a null e in altri con id valorizzato
			if(!(rigaRms.getSezione()!=null && rigaRms.getSezione().getId()!=null)) {
				rigaRms.setSezione(null);
			}
			rigaRms.setQuantitaSuRda(BigDecimal.ZERO);
			rigaRms = rmsDad.insertRigaRms(rigaRms);
			rmsDad.rigaflush();
			final RmsStatiRigaRms statiRigaRms = new RmsStatiRigaRms();
			//Stato statoRiga = isEntityPresent(() -> decodificaDad.getStato(StatoRigaRmsEnum.NEW.getCostante(), ConstantsCPassStato.TipoStatoEnum.RMS.getCostante()), "stato");
			statiRigaRms.setRigaRms(rigaRms);
			statiRigaRms.setStato(rigaRms.getStato().getDescrizione());
			statiRigaRms.setDataModifica(new Date());
			statiRigaRms.setUtenteModifica(utenteConnesso.getCodiceFiscale());

			rmsDad.insertStatiRigaRms(statiRigaRms);

			response.setRigaRms(rigaRms);
		}
	}
}
