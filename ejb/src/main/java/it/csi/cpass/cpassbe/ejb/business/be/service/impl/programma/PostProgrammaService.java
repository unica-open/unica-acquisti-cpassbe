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
package it.csi.cpass.cpassbe.ejb.business.be.service.impl.programma;

import java.util.List;

import it.csi.cpass.cpassbe.ejb.business.be.dad.DecodificaDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.InterventoDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.ProgrammaDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.programma.PostProgrammaRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.programma.PostProgrammaResponse;
import it.csi.cpass.cpassbe.ejb.entity.pba.CpassTPbaIntervento;
import it.csi.cpass.cpassbe.ejb.mapper.CpassMappers;
import it.csi.cpass.cpassbe.ejb.util.CpassEnum;
import it.csi.cpass.cpassbe.ejb.util.CpassStatiEnum;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;
import it.csi.cpass.cpassbe.lib.dto.error.MsgCpassPba;
import it.csi.cpass.cpassbe.lib.dto.pba.Intervento;
import it.csi.cpass.cpassbe.lib.dto.pba.Programma;

/**
 * Saves an Programma
 */
public class PostProgrammaService extends BaseProgrammaService<PostProgrammaRequest, PostProgrammaResponse> {

	private Programma programma;
	private final DecodificaDad decodificaDad;
	private final InterventoDad interventoDad;
	/**
	 * Constructor
	 * @param configurationHelper the configuration helper
	 * @param programmaDad the programma DAD
	 * @param decodificaDad 
	 */
	public PostProgrammaService(ConfigurationHelper configurationHelper, ProgrammaDad programmaDad, InterventoDad interventoDad,DecodificaDad decodificaDad) {
		super(configurationHelper, programmaDad);
		this.decodificaDad = decodificaDad;
		this.interventoDad = interventoDad;
	}

	@Override
	protected void checkServiceParams() {
		programma = request.getProgramma();
		checkNotNull(programma, "programma", true);
		checkNotNull(programma.getAnno(),"prima annualita");
		checkNotNull(programma.getDescrizione(), "descrizione programma");
		checkModel(programma.getUtenteReferente(), "utente referente");
		checkModel(programma.getEnte(), "ente");
	}

	@Override
	protected void execute() {
		programma = request.getProgramma();		
		programma.setStato(isEntityPresent(() -> decodificaDad.getStato(CpassStatiEnum.PRO_BOZZA.getCostante(), CpassEnum.PROGRAMMA.getCostante()), "stato")); 
		Integer maxVerPrec = programmaDad.getMaxVersioneProgrammaByAnnoEnteStato(programma.getAnno(),programma.getEnte().getId(),"");
		programma.setVersione(maxVerPrec + 1);			
		
		// copia IdRicevutoMit da precedente versione
		List<Programma> programmas = programmaDad.getProgrammiByAnnoVersioneEnteStato(programma.getAnno(), programma.getVersione(), programma.getEnte().getId(), null,	false, null);
		if (programmas != null && programmas.size() > 0) {
			programma.setIdRicevutoMit(programmas.get(0).getIdRicevutoMit());
		}  

		List<Programma> programmiInBozza = programmaDad.getProgrammiByAnnoVersioneEnteStato(programma.getAnno(),null,programma.getEnte().getId(),CpassStatiEnum.PRO_BOZZA.getCostante(), true, null);
		checkBusinessCondition(programmiInBozza.size()==0, MsgCpassPba.PBAPRGE0037.getError(  "prima_annualita",programma.getAnno(),"seconda_annualita", Integer.valueOf(programma.getAnno().intValue() + 1)));		
		programma = programmaDad.saveProgramma(programma);	
		programmaDad.flush();

		/*
		Occorre verificare se nell’ultima versione del programma del biennio precedente sono presenti acuisti 
		con acquisto_variato_codice = 1 (ndr corrispondente alla casistica lettera a) cancellazione) 
		e se vi sono, per ognuno di essi occorre inserire nelle tabelle degli acquisti gli appositi record sul programma nuovo
		*/				
		//del predevente programma estraggo gli interventi che hanno acquisto_variato_codice = 1 
		
		maxVerPrec = programmaDad.getMaxVersioneProgrammaByAnnoEnteStatoValido(programma.getAnno()-1, programma.getEnte().getId(), "", Boolean.TRUE);
		List<Intervento> listaInterventidaRibaltareAnnoPrec= interventoDad.getInterventiNonRiproponibiliByUltimoProgrammaPrecedente( programma.getAnno()-1,maxVerPrec);
		interventoDad.salvaSuNuovoProgrammaInterventiNonRiproposti(programma, listaInterventidaRibaltareAnnoPrec);
		response.setProgramma(programma);		
		
	}
}
