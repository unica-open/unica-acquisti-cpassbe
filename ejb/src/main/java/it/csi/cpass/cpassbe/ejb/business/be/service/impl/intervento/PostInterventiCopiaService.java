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
package it.csi.cpass.cpassbe.ejb.business.be.service.impl.intervento;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import it.csi.cpass.cpassbe.ejb.business.be.dad.InterventoDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.ProgrammaDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.intervento.PostInterventiCopiaRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.intervento.PostInterventiCopiaResponse;
import it.csi.cpass.cpassbe.ejb.util.CpassEnum;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;
import it.csi.cpass.cpassbe.lib.dto.ApiError;
import it.csi.cpass.cpassbe.lib.dto.error.MsgCpassPba;
import it.csi.cpass.cpassbe.lib.dto.pba.Intervento;
import it.csi.cpass.cpassbe.lib.dto.pba.InterventoImporti;
import it.csi.cpass.cpassbe.lib.dto.pba.Programma;

/**
 * Saves an Intervento
 */
public class PostInterventiCopiaService extends BaseInterventoService<PostInterventiCopiaRequest, PostInterventiCopiaResponse> {

	private final ProgrammaDad programmaDad;
	private List<Intervento> listaInterventoOld;
	private Programma  programma;
	private String interventoCopiaTipo;
	private String interventoImportoCopiaTipo;
	private List<Intervento> lista = new ArrayList<Intervento>();

	/**
	 * Constructor
	 * @param configurationHelper the configuration helper
	 * @param interventoDad the intervento DAD
	 * @param programmaDad the programma DAD
	 */
	public PostInterventiCopiaService(ConfigurationHelper configurationHelper, InterventoDad interventoDad, ProgrammaDad programmaDad) {
		super(configurationHelper, interventoDad);
		this.programmaDad = programmaDad;
	}

	@Override
	protected void checkServiceParams() {		
		interventoCopiaTipo = request.getInterventoCopiaTipo();
		interventoImportoCopiaTipo = request.getInterventoImportoCopiaTipo();	
		checkNotNull(interventoCopiaTipo,"interventoCopiaTipo");
	}

	@Override
	protected void execute() {	
		listaInterventoOld = request.getInterventi();
		programma          = isEntityPresent(() -> programmaDad.getProgramma(request.getIdProgramma()),"programma");
		
		List<ApiError> listaErrori = new ArrayList<ApiError>();
		
		for(Intervento interventoOld :listaInterventoOld) {			
			ApiError errore = null;
			
			Intervento intervento = isEntityPresent(() -> interventoDad.getIntervento(interventoOld.getId()),"intervento old");		
			intervento.setMotivazioneNonRiproposto(interventoOld.getMotivazioneNonRiproposto() != null ? interventoOld.getMotivazioneNonRiproposto() : "" );
			
			
			//controllo che l'intervento non sia stato gia copiato nel nuovo programma
			Intervento interventoCopia = isNotEntityPresent(() -> interventoDad.getInterventoEsistenteProgramma(interventoOld.getId() ,programma.getId()),"intervento");	
						
			//controllo che negli importi ce ne sia abbiano almeno 1 diverso da 0
			if(!StringUtils.isBlank(interventoImportoCopiaTipo) && interventoImportoCopiaTipo.equals(CpassEnum.IMPORTI_TRASLATI.getCostante())) {
				if (controlloImportiTuttiAZero(intervento)) {
					errore = MsgCpassPba.PBAACQE0038.getError("cui", intervento.getCui());
				}
			}

			if (errore == null) {
				Intervento interventoNew = interventoDad.saveInterventoDaCopia(intervento,interventoCopiaTipo,interventoImportoCopiaTipo, programma);
				lista.add(interventoNew);
			}else {
				listaErrori.add(errore);
			}
		}
		response.setInterventi(lista);
		response.setApiErrors(listaErrori);
	}
	
	private boolean controlloImportiTuttiAZero(Intervento interventoOld) {
		String methodName="controlloImportiTuttiAZero";
		boolean ris = true;
		for(InterventoImporti interventoImporti : interventoOld.getListInterventoImporti()) {
			BigDecimal ias = interventoImporti.getImportoAnnoSecondo(); 
			BigDecimal iap = interventoImporti.getImportoAnnoPrimo();
			if (!ias.equals(BigDecimal.ZERO) || ! iap.equals(BigDecimal.ZERO)) {
				ris = false;
			}
		}	
		return ris;
	}	
}
