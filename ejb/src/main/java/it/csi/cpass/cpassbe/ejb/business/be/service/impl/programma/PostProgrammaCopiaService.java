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
import it.csi.cpass.cpassbe.ejb.business.be.service.request.programma.PostProgrammaCopiaRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.programma.PostProgrammaCopiaResponse;
import it.csi.cpass.cpassbe.ejb.util.ConstantsCPassStato;
import it.csi.cpass.cpassbe.ejb.util.CpassEnum;
import it.csi.cpass.cpassbe.ejb.util.CpassStatiEnum;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;
import it.csi.cpass.cpassbe.lib.dto.Stato;
import it.csi.cpass.cpassbe.lib.dto.error.MsgCpassPba;
import it.csi.cpass.cpassbe.lib.dto.pba.Intervento;
import it.csi.cpass.cpassbe.lib.dto.pba.Programma;
import it.csi.cpass.cpassbe.lib.util.pagination.Sort;
import it.csi.cpass.cpassbe.lib.util.pagination.Sort.Direction;

/**
 * Copy a Programma
 */
public class PostProgrammaCopiaService extends BaseProgrammaService<PostProgrammaCopiaRequest, PostProgrammaCopiaResponse> {

	private final InterventoDad interventoDad;
	private final DecodificaDad decodificaDad;

	/**
	 * Constructor
	 * 
	 * @param configurationHelper the configuration helper
	 * @param programmaDad        the programma DAD
	 * @param decodificaDad
	 */
	public PostProgrammaCopiaService(ConfigurationHelper configurationHelper, ProgrammaDad programmaDad, InterventoDad interventoDad,DecodificaDad decodificaDad) {
		super(configurationHelper, programmaDad);
		this.interventoDad = interventoDad;
		this.decodificaDad = decodificaDad;
	}

	@Override
	protected void checkServiceParams() {
		checkNotNull(request.getProgramma(), "programma", true);
	}

	@Override
	protected void execute() {
		final Programma programma = request.getProgramma();

		List<Programma> listaProgramma = programmaDad.getProgrammiByAnnoVersioneEnteStato(programma.getAnno(), null, programma.getEnte().getId(),ConstantsCPassStato.StatoEnum.BOZZA.getCostante(), false, new Sort("versione", Direction.ASC));
		checkBusinessCondition(listaProgramma.size() == 0, MsgCpassPba.PBAPRGE0070.getError());
		response.setProgramma(programma);

		if (request.getSoloControlli() != null && !request.getSoloControlli().booleanValue()) {
			Programma programmaCopia = new Programma();
			programmaCopia.setAnno(programma.getAnno());
			programmaCopia.setEnte(programma.getEnte());
			programmaCopia.setStato(isEntityPresent(() -> decodificaDad.getStato(ConstantsCPassStato.StatoEnum.BOZZA.getCostante(), ConstantsCPassStato.TipoEnum.PROGRAMMA.getCostante()),"stato"));
			programmaCopia.setUtenteReferente(programma.getUtenteReferente());
			programmaCopia.setDescrizione(programma.getDescrizione());
			programmaCopia.setVersione(Integer.valueOf(1));
			Integer maxVer = programmaDad.getMaxVersioneProgrammaByAnnoEnteStato(programma.getAnno(), programma.getEnte().getId(), "");
			programmaCopia.setVersione(Integer.valueOf(maxVer + 1));

			// copia IdRicevutoMit da precedente versione
			List<Programma> programmas = programmaDad.getProgrammiByAnnoVersioneEnteStato(programma.getAnno(), programma.getVersione(),programma.getEnte().getId(), null, false, null);
			if (programmas != null && programmas.size() > 0) {
				programma.setIdRicevutoMit(programmas.get(0).getIdRicevutoMit());
			}

			programmaCopia = programmaDad.saveProgramma(programmaCopia);
			programmaDad.flush();

			// carico tutti gli interventi del programma non annullati
			List<Intervento> interventos = interventoDad.getInterventoByProgrammaStato(programma.getId(), CpassStatiEnum.INT_CANCELLATO.getCostante(),false);
			Stato statoBozza =	isEntityPresent(() -> decodificaDad.getStato(CpassStatiEnum.INT_BOZZA.getCostante(), CpassEnum.INTERVENTO.getCostante()), "stato");
			for (Intervento intervento : interventos) {				
				Intervento interventoOld = new Intervento();
				interventoOld.setId(intervento.getId());
				intervento.setId(null);
				intervento.setProgramma(programmaCopia);
				intervento.setInterventoCopia(interventoOld);				
				intervento.setStato(statoBozza);
				intervento.setInterventoCopiaTipo("ACQ_DA_COPIA_PROGRAMMA");
				intervento.setDataVisto(null);
				intervento.setDataValidazione(null);
				intervento.setDataRifiuto(null);
				intervento.setUtenteVisto(null);
				intervento.setUtenteValidazione(null);
				intervento.setUtenteRifiuto(null);
				interventoDad.saveIntervento(intervento);
			}
			response.setProgramma(programmaCopia);
		}
	}

}
