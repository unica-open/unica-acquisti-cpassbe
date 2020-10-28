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

import it.csi.cpass.cpassbe.ejb.business.be.dad.DecodificaDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.InterventoDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.ProgrammaDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.intervento.PostInterventoRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.intervento.PostInterventoResponse;
import it.csi.cpass.cpassbe.ejb.util.CpassEnum;
import it.csi.cpass.cpassbe.ejb.util.CpassStatiEnum;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;
import it.csi.cpass.cpassbe.lib.dto.error.CoreError;
import it.csi.cpass.cpassbe.lib.dto.error.MsgCpassPba;
import it.csi.cpass.cpassbe.lib.dto.pba.Intervento;
import it.csi.cpass.cpassbe.lib.dto.pba.InterventoImporti;

/**
 * Saves an Intervento
 */
public class PostInterventoService extends BaseInterventoService<PostInterventoRequest, PostInterventoResponse> {

	private final DecodificaDad decodificaDad;
	private final ProgrammaDad programmaDad;
	private Intervento intervento;

	/**
	 * Constructor
	 * @param configurationHelper the configuration helper
	 * @param interventoDad the intervento DAD
	 * @param decodificaDad the deodifica DAD
	 * @param programmaDad the programma DAD
	 */
	public PostInterventoService(ConfigurationHelper configurationHelper, InterventoDad interventoDad, DecodificaDad decodificaDad, ProgrammaDad programmaDad) {
		super(configurationHelper, interventoDad);
		this.decodificaDad = decodificaDad;
		this.programmaDad = programmaDad;
	}

	@Override
	protected void checkServiceParams() {
		intervento = request.getIntervento();
		checkNotNull(intervento, "intervento", true);
		checkNotNull( intervento.getAnnoAvvio(),"anno avvio", true);
		checkNotNull( intervento.getDurataMesi(),"durata mesi");
		checkNotNull( intervento.getDescrizioneAcquisto(),"descrizione acquisto");
		checkModel(intervento.getUtenteRup(), "utente Rup");
		checkModel(intervento.getSettoreInterventi(), "settore interventi");
		checkModel(intervento.getCpv(), "cpv");
		checkModel(intervento.getProgramma(), "programma");
		checkModel(intervento.getNuts(), "nuts");
		checkModel(intervento.getPriorita(), "priorita");
		checkModel(intervento.getModalitaAffidamento(), "modalita affidamento");
		
		checkCondition(intervento.getListInterventoAltriDati() == null || intervento.getListInterventoAltriDati().isEmpty() || intervento.getListInterventoAltriDati().size() < 2,
				CoreError.GENERIC_ERROR.getError("error", "La lista di altri dati può contenere un unico elemento"));
	}

	@Override
	protected void execute() {
		intervento.setCpv(isEntityPresent(() -> decodificaDad.getCpv(intervento.getCpv().getId()), "cpv"));
		intervento.setNuts(isEntityPresent(() -> decodificaDad.getNut(intervento.getNuts().getId()), "nuts"));
		intervento.setSettoreInterventi(isEntityPresent(() -> decodificaDad.getSettoreInterventi(intervento.getSettoreInterventi().getId()), "settore interventi"));
		intervento.setPriorita(isEntityPresent(() -> decodificaDad.getPriorita(intervento.getPriorita().getId()), "priorita"));
		intervento.setModalitaAffidamento(isEntityPresent(() -> decodificaDad.getModalitaAffidamento(intervento.getModalitaAffidamento().getId()), "modalita affidamento"));
		intervento.setProgramma(isEntityPresent(() -> programmaDad.getProgramma(intervento.getProgramma().getId()), "programma"));
		// Impostazione dello stato a bozza
		intervento.setStato(isEntityPresent(() -> decodificaDad.getStato(CpassStatiEnum.INT_BOZZA.getCostante(), CpassEnum.INTERVENTO.getCostante()), "stato"));
		//se non vengono passati i parametri desumo siano false
		initField(intervento.getLottoFunzionale(), () -> intervento.setLottoFunzionale(Boolean.FALSE));
		initField(intervento.getNuovoAffidamento(), () -> intervento.setNuovoAffidamento(Boolean.FALSE));		
		initField(intervento.getFlagCuiNonGenerato(), () -> intervento.setFlagCuiNonGenerato(Boolean.FALSE));
		initField(intervento.getEsenteCup(), () -> intervento.setEsenteCup(Boolean.FALSE));

		/* Se l'attore ha spuntato il campo di mappa 18, allora deve avere indicato un valore anche nel campo 19.
		 * CUI lavoro o altra acquisizione, altrimenti esporre l'anomalia: "indicare il CUI lavoro o altra acquisizione"
		 */		
		if (intervento.getRicompresoTipo()!=null && intervento.getRicompresoTipo().getDescrizione().toUpperCase().equals("SI")) {
			checkBusinessCondition(controlloFormaleCui(intervento.getRicompresoCui()), MsgCpassPba.PBAACQE0022.getError());
		}	
		/*
		if (intervento.getRicompresoTipo()!=null && intervento.getRicompresoTipo().getDescrizione().toUpperCase().equals("SI")) {
			checkBusinessCondition(intervento.getInterventoRicompreso() != null && (intervento.getInterventoRicompreso().getId() != null || StringUtils.isNotBlank(intervento.getInterventoRicompreso().getCui())),MsgCpassPba.PBAACQE0000.getError());
			if(intervento.getInterventoRicompreso().getId() != null) {
				intervento.setInterventoRicompreso(isEntityPresent(() -> interventoDad.getIntervento(intervento.getInterventoRicompreso().getId()),"intervento ricompreso"));
			} else if(intervento.getInterventoRicompreso().getCui() != null) {
				//intervento.setInterventoRicompreso(isEntityPresent(() -> interventoDad.findInterventoByCUI(intervento.getInterventoRicompreso().getCui()),"intervento ricompreso"));
				Optional<Intervento> intRicompreso = interventoDad.findInterventoRicompreso(intervento.getInterventoRicompreso().getCui(),intervento.getProgramma().getId());
				checkBusinessCondition(intRicompreso.isPresent(), MsgCpassPba.PBAACQE0022.getError("entity", "intervento ricompreso"));
				intervento.setInterventoRicompreso(intRicompreso.orElse(null));
			}
		} else {
			intervento.setInterventoRicompreso(null);
		}
		*/
		boolean importiTuttiAZero = true;
		InterventoImporti interventoImportiPrivati = null;
		// se ho importi passati a null li inizializzo a 0
		for(InterventoImporti ii : intervento.getListInterventoImporti()) {
			initField(ii.getImportoAnnoPrimo(), () -> ii.setImportoAnnoPrimo(BigDecimal.ZERO));
			initField(ii.getImportoAnnoSecondo(), () -> ii.setImportoAnnoSecondo(BigDecimal.ZERO));
			initField(ii.getImportoAnniSuccessivi(), () -> ii.setImportoAnniSuccessivi(BigDecimal.ZERO));
			if(importiTuttiAZero) {
				importiTuttiAZero = ii.getImportoAnnoPrimo().signum() == 0 && ii.getImportoAnnoSecondo().signum() == 0 && ii.getImportoAnniSuccessivi().signum() == 0;
			}
			
			interventoImportiPrivati = getInterventoImportiPrivati(ii, intervento, decodificaDad);
		}
		
		if (interventoImportiPrivati != null) {
			intervento.getListInterventoImporti().add(interventoImportiPrivati);
		}
		
		checkBusinessCondition(!importiTuttiAZero, MsgCpassPba.PBAACQA0011.getError());
		int annoAvvio = intervento.getAnnoAvvio().intValue();
		int annoProgramma = intervento.getProgramma().getAnno().intValue();
		int annoProgramma1 = annoProgramma +1;
		checkBusinessCondition((annoAvvio == annoProgramma || annoAvvio == annoProgramma1), MsgCpassPba.PBAACQE0020.getError()); 
		intervento = interventoDad.saveIntervento(intervento);
		response.setIntervento(intervento);
	}

	
}
