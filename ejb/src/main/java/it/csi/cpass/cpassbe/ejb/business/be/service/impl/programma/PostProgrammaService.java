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
package it.csi.cpass.cpassbe.ejb.business.be.service.impl.programma;

import java.util.List;
import java.util.UUID;

import it.csi.cpass.cpassbe.ejb.business.be.dad.DecodificaDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.InterventoDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.ProgrammaDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.SystemDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.programma.PostProgrammaRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.programma.PostProgrammaResponse;
import it.csi.cpass.cpassbe.ejb.util.ConstantsCPassParametro;
import it.csi.cpass.cpassbe.ejb.util.ConstantsCPassStato.StatoProgrammaEnum;
import it.csi.cpass.cpassbe.ejb.util.CpassEnum;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;
import it.csi.cpass.cpassbe.lib.dto.Parametro;
import it.csi.cpass.cpassbe.lib.dto.Utente;
import it.csi.cpass.cpassbe.lib.dto.error.MsgCpassPba;
import it.csi.cpass.cpassbe.lib.dto.pba.Intervento;
import it.csi.cpass.cpassbe.lib.dto.pba.Programma;
import it.csi.cpass.cpassbe.lib.util.threadlocal.CpassThreadLocalContainer;

/**
 * Saves an Programma
 */
public class PostProgrammaService extends BaseProgrammaService<PostProgrammaRequest, PostProgrammaResponse> {

	private Programma programma;
	private final DecodificaDad decodificaDad;
	private final InterventoDad interventoDad;
	private final SystemDad systemDad;
	/**
	 * Constructor
	 * @param configurationHelper the configuration helper
	 * @param programmaDad the programma DAD
	 * @param decodificaDad
	 */
	public PostProgrammaService(ConfigurationHelper configurationHelper, ProgrammaDad programmaDad, InterventoDad interventoDad,DecodificaDad decodificaDad, SystemDad systemDad) {
		super(configurationHelper, programmaDad);
		this.decodificaDad = decodificaDad;
		this.interventoDad = interventoDad;
		this.systemDad = systemDad;
	}

	@Override
	protected void checkServiceParams() {
		programma = request.getProgramma();
		checkNotNull(programma, "programma", Boolean.TRUE);
		checkNotNull(programma.getAnno(),"prima annualita");
		checkNotNull(programma.getDescrizione(), "descrizione programma");
		checkModel(programma.getUtenteReferente(), "utente referente");
		checkModel(programma.getEnte(), "ente");
	}

	@Override
	protected void execute() {
		final UUID enteId = CpassThreadLocalContainer.SETTORE_UTENTE.get().getEnte().getId();
		programma = request.getProgramma();
		final Utente utenteConnesso = CpassThreadLocalContainer.UTENTE_CONNESSO.get();
		programma.setStato(isEntityPresent(() -> decodificaDad.getStatoOpt(StatoProgrammaEnum.BOZZA.getCostante(), CpassEnum.PROGRAMMA.getCostante()), "stato"));
		Integer maxVerPrec = programmaDad.getMaxVersioneProgrammaByAnnoEnteStato(programma.getAnno(),programma.getEnte().getId(),"");
		programma.setVersione(maxVerPrec + 1);

		// copia IdRicevutoMit da precedente versione
		/* patch-1.2.0
		List<Programma> programmas = programmaDad.getProgrammiByAnnoVersioneEnteStato(programma.getAnno(), programma.getVersione(), programma.getEnte().getId(), null,	false, null);
		if (programmas != null && programmas.size() > 0) {
			programma.setIdRicevutoMit(programmas.get(0).getIdRicevutoMit());
		}
		 */
		final Parametro parametroDurataProgramma = systemDad.getParametro(ConstantsCPassParametro.ChiaveEnum.DURATA_PROGRAMMA.getCostante(),ConstantsCPassParametro.RiferimentoEnum.PBA.getCostante(),enteId);

		if (parametroDurataProgramma != null && parametroDurataProgramma.getValore().equalsIgnoreCase("TRIENNALE")) {
			programma.setAnnoFineProgramma(programma.getAnno()+2);
		}else {
			programma.setAnnoFineProgramma(programma.getAnno()+1);
		}

		final List<Programma> programmiInBozza = programmaDad.getProgrammiByAnnoVersioneEnteStato(programma.getAnno(),programma.getAnnoFineProgramma(),null,programma.getEnte().getId(),StatoProgrammaEnum.BOZZA.getCostante(), true, null);
		checkBusinessCondition(programmiInBozza.size()==0, MsgCpassPba.PBAPRGE0037.getError(  "prima_annualita",programma.getAnno(),"seconda_annualita", programma.getAnnoFineProgramma()));
		programma = programmaDad.saveProgramma(programma);

		/*
		Occorre verificare se nell’ultima versione del programma del biennio precedente sono presenti acuisti
		con acquisto_variato_codice = 1 (ndr corrispondente alla casistica lettera a) cancellazione)
		e se vi sono, per ognuno di essi occorre inserire nelle tabelle degli acquisti gli appositi record sul programma nuovo
		 */

		//del predevente programma estraggo gli interventi che hanno acquisto_variato_codice = 1
		maxVerPrec = programmaDad.getMaxVersioneProgrammaByAnnoEnteStatoValido(programma.getAnno()-1, programma.getEnte().getId(), "", Boolean.TRUE);

		//controllare il valore del parametro DURATA_PROGRAMMA se è triennale annofinevalidità = anno +2 altrimenti anno +1

		final List<Intervento> listaInterventidaRibaltareAnnoPrec= interventoDad.getInterventiNonRiproponibiliByUltimoProgrammaPrecedente( programma.getAnno()-1,maxVerPrec);
		interventoDad.salvaSuNuovoProgrammaInterventiNonRiproposti(utenteConnesso,programma, listaInterventidaRibaltareAnnoPrec);
		response.setProgramma(programma);

	}
}
