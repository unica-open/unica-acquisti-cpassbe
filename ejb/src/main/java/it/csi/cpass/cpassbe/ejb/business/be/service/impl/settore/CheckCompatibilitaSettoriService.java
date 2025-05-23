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
package it.csi.cpass.cpassbe.ejb.business.be.service.impl.settore;

import java.util.ArrayList;
import java.util.List;

import it.csi.cpass.cpassbe.ejb.business.be.dad.SettoreDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.SystemDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.common.CheckCompatibilitaSettoriRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.common.CheckCompatibilitaSettoriResponse;
import it.csi.cpass.cpassbe.ejb.util.ConstantsCPassParametro;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;
import it.csi.cpass.cpassbe.lib.dto.ApiError;
import it.csi.cpass.cpassbe.lib.dto.Ente;
import it.csi.cpass.cpassbe.lib.dto.Parametro;
import it.csi.cpass.cpassbe.lib.dto.Settore;
import it.csi.cpass.cpassbe.lib.dto.error.MsgCpassOrd;
import it.csi.cpass.cpassbe.lib.util.threadlocal.CpassThreadLocalContainer;

public class CheckCompatibilitaSettoriService extends BaseSettoreService<CheckCompatibilitaSettoriRequest,CheckCompatibilitaSettoriResponse>{

	private final SystemDad systemDad;

	public CheckCompatibilitaSettoriService(ConfigurationHelper configurationHelper, SettoreDad  settoreDad, SystemDad systemDad) {
		super(configurationHelper, settoreDad);
		this.systemDad = systemDad;
	}

	@Override
	protected void checkServiceParams() {
		checkNotNull(request.getIdSettoreEmittente(), "settoreId Emittente");
	}

	@Override
	protected void execute() {
		final Parametro parametroVSP = systemDad.getParametro(ConstantsCPassParametro.ChiaveEnum.VERIFICA_STRUTTURA_PROVVEDIMENTO.getCostante(), ConstantsCPassParametro.RiferimentoEnum.PROVVEDIMENTO.getCostante(), null);
		if(request.getCodiceStrutturaProponente()==null && request.getIdSettoreDetermina() == null) {
			response.setCompatibili(Boolean.FALSE);
			return;
		}

		final Ente ente = CpassThreadLocalContainer.SETTORE_UTENTE.get().getEnte();

		//settore dell'ordine
		final Settore settoreEmittenteOrdine = settoreDad.findById(request.getIdSettoreEmittente());
		Settore settoreDirezione = null;
		/* N.B
		 * questo parametro guida il controllo tra settore emittente
		 */
		final Parametro paramAlgoritmo = systemDad.getParametro(ConstantsCPassParametro.ChiaveEnum.ALGORITMO_CONFRONTO_PER_DIREZIONE.getCostante(),ConstantsCPassParametro.RiferimentoEnum.PROVVEDIMENTO.getCostante(), ente.getId());
		final String algoritmoConfrontoperDirezione = paramAlgoritmo != null ? paramAlgoritmo.getValore() : "";
		if(algoritmoConfrontoperDirezione.equalsIgnoreCase("true")) {
			settoreDirezione = findDirezione(settoreEmittenteOrdine);
		}else{
			settoreDirezione = settoreEmittenteOrdine;
		}

		// settore del provvedimento
		final Settore settoreDetermina = settoreDad.findById(request.getIdSettoreDetermina());

		final List<ApiError> apiErrors = new ArrayList<>();
		if(settoreDirezione == null) {
			apiErrors.add(MsgCpassOrd.ORDORDE0002.getError("errori", "Nessuna Direzione trovata"));
			return;
		}

		if (request.getIdSettoreDetermina() == null) {
			final List<Settore> settoriDetermineProponenti = settoreDad.getSettoriByCdc(request.getCodiceStrutturaProponente(),ente.getId());
			if(settoriDetermineProponenti == null || settoriDetermineProponenti.size()==0) {
				if(parametroVSP.getValore().equals(ConstantsCPassParametro.ValoreEnum.VERIFICA_STRUTTURA_PROVVEDIMENTO_E.getCostante())) {
					apiErrors.add(MsgCpassOrd.ORDORDE0006.getError("cdc",settoreDetermina == null || settoreDetermina.getCodice()==null ?"":settoreDetermina.getCodice()));
				}
				response.setCompatibili(Boolean.FALSE);
				response.addApiErrors(apiErrors);
			}else {
				gestisciCasoCdc(settoriDetermineProponenti, settoreDirezione, apiErrors);
			}
			return;
		}

		if(settoreDetermina == null) {
			apiErrors.add(MsgCpassOrd.ORDORDE0002.getError("errori", "Settore del provvedimento di spesa non trovato"));
			return;
		}

		Boolean compatibili = settoreDirezione.getCodice().equals(settoreDetermina.getCodice());
		if(!compatibili) {
			compatibili = settoreDad.isSettoreRiorganizzato(settoreDirezione.getId(), settoreDetermina.getId());
		}

		if(!compatibili) {
			if(parametroVSP.getValore().equals(ConstantsCPassParametro.ValoreEnum.VERIFICA_STRUTTURA_PROVVEDIMENTO_E.getCostante())) {
				apiErrors.add(MsgCpassOrd.ORDORDE0006.getError("cdc",settoreDetermina.getCodice()==null ?"":settoreDetermina.getCodice() ));
			}
		}

		response.addApiErrors(apiErrors);
		response.setCompatibili(compatibili);
	}

	private void gestisciCasoCdc(List<Settore> settoriDetermineProponenti, Settore settoreDirezione,List<ApiError> apiErrors) {
		final Parametro parametro = systemDad.getParametro(ConstantsCPassParametro.ChiaveEnum.VERIFICA_STRUTTURA_PROVVEDIMENTO.getCostante(), ConstantsCPassParametro.RiferimentoEnum.PROVVEDIMENTO.getCostante(), null);
		Boolean compatibili = Boolean.FALSE;
		for(final Settore settDetProp : settoriDetermineProponenti) {
			compatibili = settoreDirezione.getCodice().equals(settDetProp.getCodice());

			if(!compatibili) {
				compatibili = settoreDad.isSettoreRiorganizzato(settoreDirezione.getId(), settDetProp.getId());
			}

			if(!compatibili) {
				if(parametro.getValore().equals(ConstantsCPassParametro.ValoreEnum.VERIFICA_STRUTTURA_PROVVEDIMENTO_E.getCostante())) {
					apiErrors.add(MsgCpassOrd.ORDORDE0006.getError("cdc",settDetProp.getCodice()==null ?"":settDetProp.getCodice() ));
				}
			}
			if(compatibili) {
				break;
			}
		}
		response.addApiErrors(apiErrors);
		response.setCompatibili(compatibili);
	}

	private Settore findDirezione(Settore settore) {
		Settore settoreDirezione = settore;
		while(settoreDirezione != null && settoreDirezione.getTipoSettore() != null && !settoreDirezione.getTipoSettore().getFlagDirezione()) {
			settoreDirezione = settoreDad.findById(settoreDirezione.getSettorePadre().getId());
		}
		return settoreDirezione != null && settoreDirezione.getTipoSettore() != null && settoreDirezione.getTipoSettore().getFlagDirezione() == true?settoreDirezione : null;
	}

}
