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
package it.csi.cpass.cpassbe.ejb.business.be.service.impl.intervento;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;

import it.csi.cpass.cpassbe.ejb.business.be.dad.DecodificaDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.InterventoDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.ProgrammaDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.SystemDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.intervento.PostInterventiCopiaRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.intervento.PostInterventiCopiaResponse;
import it.csi.cpass.cpassbe.ejb.exception.NotFoundException;
import it.csi.cpass.cpassbe.ejb.util.ConstantsCPassParametro;
import it.csi.cpass.cpassbe.ejb.util.ConstantsCPassStato.StatoInterventiEnum;
import it.csi.cpass.cpassbe.ejb.util.CpassEnum;
import it.csi.cpass.cpassbe.ejb.util.StringUtility;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;
import it.csi.cpass.cpassbe.lib.dto.ApiError;
import it.csi.cpass.cpassbe.lib.dto.Parametro;
import it.csi.cpass.cpassbe.lib.dto.Stato;
import it.csi.cpass.cpassbe.lib.dto.Utente;
import it.csi.cpass.cpassbe.lib.dto.error.MsgCpassPba;
import it.csi.cpass.cpassbe.lib.dto.pba.Intervento;
import it.csi.cpass.cpassbe.lib.dto.pba.InterventoImporti;
import it.csi.cpass.cpassbe.lib.dto.pba.Programma;
import it.csi.cpass.cpassbe.lib.util.threadlocal.CpassThreadLocalContainer;

/**
 * Saves an Intervento
 */
public class PostInterventiCopiaService extends BaseInterventoService<PostInterventiCopiaRequest, PostInterventiCopiaResponse> {

	private final ProgrammaDad programmaDad;
	private final DecodificaDad decodificaDad;
	private final SystemDad systemDad;
	private List<Intervento> listaInterventoOld;
	private Programma  programma;
	private String interventoCopiaTipo;
	private String interventoImportoCopiaTipo;
	private final List<Intervento> lista = new ArrayList<>();

	/**
	 * Constructor
	 * @param configurationHelper the configuration helper
	 * @param interventoDad the intervento DAD
	 * @param programmaDad the programma DAD
	 * @param decodificaDad the decodifica DAD
	 */
	public PostInterventiCopiaService(ConfigurationHelper configurationHelper, InterventoDad interventoDad, ProgrammaDad programmaDad, DecodificaDad decodificaDad, SystemDad systemDad) {
		super(configurationHelper, interventoDad);
		this.programmaDad = programmaDad;
		this.decodificaDad = decodificaDad;
		this.systemDad = systemDad;
	}

	@Override
	protected void checkServiceParams() {
		interventoCopiaTipo = request.getInterventoCopiaTipo();
		interventoImportoCopiaTipo = request.getInterventoImportoCopiaTipo();
		checkNotNull(interventoCopiaTipo,"interventoCopiaTipo");
	}

	@Override
	protected void execute() {
		final UUID enteId     = CpassThreadLocalContainer.SETTORE_UTENTE.get().getEnte().getId();
		final Utente utente   = CpassThreadLocalContainer.UTENTE_CONNESSO.get();

		listaInterventoOld = request.getInterventi();
		programma          = isEntityPresent(() -> programmaDad.getProgramma(request.getIdProgramma()),"programma");
		final List<ApiError> listaErrori = new ArrayList<>();

		final Parametro isVistoRagioneria = systemDad.getParametro(ConstantsCPassParametro.ChiaveEnum.VISTO_RAGIONERIA.getCostante(),null, enteId);
		final Parametro isVersioneDefinitiva = systemDad.getParametro(ConstantsCPassParametro.ChiaveEnum.GESTIONE_ACQUISTO_VERS_DEFINITIVA.getCostante(),null, enteId);
		final Parametro parametroDurataProgrammaParam = systemDad.getParametro(ConstantsCPassParametro.ChiaveEnum.DURATA_PROGRAMMA.getCostante(),ConstantsCPassParametro.RiferimentoEnum.PBA.getCostante(),enteId );
		String parametroDurataProgramma = parametroDurataProgrammaParam.getValore() ;
		if(StringUtility.isEmpty(parametroDurataProgramma)) {
			parametroDurataProgramma = "TRIENNALE";
		}
		Stato stato                    = decodificaDad.getStatoOpt(StatoInterventiEnum.BOZZA.getCostante(), CpassEnum.INTERVENTO.getCostante()).orElseThrow(() -> new NotFoundException("stato")) ;
		final Stato statoValidatoInt   = decodificaDad.getStato(StatoInterventiEnum.VALIDATO.getCostante(), CpassEnum.INTERVENTO.getCostante());
		final Stato statoCancellatoInt = decodificaDad.getStato(StatoInterventiEnum.CANCELLATO.getCostante(), CpassEnum.INTERVENTO.getCostante());
		Stato statoEventualeDaparamero = stato;

		if (request.getStato() != null) {
			statoEventualeDaparamero = decodificaDad.getStatoOpt(request.getStato(), CpassEnum.INTERVENTO.getCostante()).orElseThrow(() -> new NotFoundException("stato")) ;
			//setto lo stato forzandolo con quello passato da parametro
			stato = statoEventualeDaparamero;
		}
		
		for(final Intervento interventoOld :listaInterventoOld) {
			ApiError errore = null;
			final Intervento intervento = isEntityPresent(() -> interventoDad.getInterventoOpt(interventoOld.getId()),"intervento old");
			//CPASS-872
			intervento.setUtenteCreazione(utente.getCodiceFiscale());
			intervento.setUtenteModifica(utente.getCodiceFiscale());
			intervento.setUtenteCancellazione(null);


			//CPASS-514
			if(!StringUtils.isEmpty(interventoOld.getMotivazioneNonRiproposto())){
				intervento.setMotivazioneNonRiproposto(interventoOld.getMotivazioneNonRiproposto().trim());
				// TODO issue-426
				if(intervento.getAnnoAvvio().equals(programma.getAnno()-1)) {
					intervento.setStato(statoValidatoInt);
					stato=statoValidatoInt;
				}else {
					intervento.setStato(statoCancellatoInt);
					stato=statoCancellatoInt;
				}
			}else{
				//issue-427
				stato = statoEventualeDaparamero ;
			}
			
			intervento.setAcquistoVariato(null);
			isNotEntityPresent(() -> interventoDad.getInterventoEsistenteProgramma(interventoOld.getId() ,programma.getId()),"intervento");

			//controllo che negli importi ce ne sia abbiano almeno 1 diverso da 0
			if(!StringUtils.isBlank(interventoImportoCopiaTipo) && interventoImportoCopiaTipo.equals(CpassEnum.IMPORTI_TRASLATI.getCostante())) {
				if (controlloImportiTuttiAZero(intervento)) {
					errore = MsgCpassPba.PBAACQE0038.getError("cui", intervento.getCui());
				}
			}


			if (errore == null) {
				final Intervento interventoNew = interventoDad.saveInterventoDaCopia(intervento
						, interventoCopiaTipo
						, interventoImportoCopiaTipo
						, programma,stato
						, isVistoRagioneria.getValore()
						, isVersioneDefinitiva.getValore()
						, utente
						, parametroDurataProgramma
						);
				lista.add(interventoNew);
			}else {
				listaErrori.add(errore);
			}
		}

		for (final Intervento interventoNew : lista) {
			interventoDad.gestisciCapofila(interventoNew,programma,enteId);
		}

		response.setInterventi(lista);
		response.setApiErrors(listaErrori);
	}

	private boolean controlloImportiTuttiAZero(Intervento interventoOld) {
		boolean ris = true;
		for(final InterventoImporti interventoImporti : interventoOld.getListInterventoImporti()) {
			final BigDecimal ias = interventoImporti.getImportoAnnoSecondo();
			final BigDecimal iap = interventoImporti.getImportoAnnoPrimo();
			final BigDecimal iat = interventoImporti.getImportoAnnoTerzo();
			if (!ias.equals(BigDecimal.ZERO) || ! iap.equals(BigDecimal.ZERO) || !iat.equals(BigDecimal.ZERO)) {
				ris = false;
			}
		}
		return ris;
	}
}
