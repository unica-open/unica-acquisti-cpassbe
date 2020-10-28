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

import it.csi.cpass.cpassbe.ejb.business.be.dad.DecodificaDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.InterventoDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.InterventoImportiDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.ProgrammaDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.intervento.PutInterventoRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.intervento.PutInterventoResponse;
import it.csi.cpass.cpassbe.ejb.util.ConstantsCPassRisorsa;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;
import it.csi.cpass.cpassbe.lib.dto.error.CoreError;
import it.csi.cpass.cpassbe.lib.dto.error.MsgCpassPba;
import it.csi.cpass.cpassbe.lib.dto.pba.Intervento;
import it.csi.cpass.cpassbe.lib.dto.pba.InterventoImporti;

/**
 * Saves an Intervento
 */
public class PutInterventoService extends BaseInterventoService<PutInterventoRequest, PutInterventoResponse> {

	private final DecodificaDad decodificaDad;
	private final ProgrammaDad programmaDad;
	private final InterventoImportiDad interventoImportiDad;
	private Intervento intervento;

	/**
	 * Constructor
	 * @param configurationHelper the configuration helper
	 * @param interventoDad the intervento DAD
	 * @param decodificaDad the decodifica DAD
	 * @param programmaDad the programma DAD
	 */
	public PutInterventoService(ConfigurationHelper configurationHelper, InterventoDad interventoDad, InterventoImportiDad interventoImportiDad, DecodificaDad decodificaDad, ProgrammaDad programmaDad) {
		super(configurationHelper, interventoDad);
		this.decodificaDad = decodificaDad;
		this.programmaDad = programmaDad;
		this.interventoImportiDad = interventoImportiDad;
	}

	@Override
	protected void checkServiceParams() {
		intervento = request.getIntervento();
		checkModel(request.getIntervento(), "intervento");
		checkNotNull( intervento.getAnnoAvvio(),"anno avvio");
		checkNotNull( intervento.getDurataMesi(),"durata mesi");
		checkNotNull( intervento.getDescrizioneAcquisto(),"descrizione acquisto");
		checkNotNull( intervento.getOptlock(),"opt look");
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
		String methodName = "execute";
		log.info(methodName, "Start");
		Intervento interventoAttuale = isEntityPresent(() -> interventoDad.getIntervento(intervento.getId()), "intervento");
		
		intervento.setCpv(isEntityPresent(() -> decodificaDad.getCpv(intervento.getCpv().getId()), "cpv"));
		intervento.setNuts(isEntityPresent(() -> decodificaDad.getNut(intervento.getNuts().getId()), "nuts"));
		intervento.setSettoreInterventi(isEntityPresent(() -> decodificaDad.getSettoreInterventi(intervento.getSettoreInterventi().getId()), "settore interventi"));
		intervento.setPriorita(isEntityPresent(() -> decodificaDad.getPriorita(intervento.getPriorita().getId()), "priorita"));
		intervento.setModalitaAffidamento(isEntityPresent(() -> decodificaDad.getModalitaAffidamento(intervento.getModalitaAffidamento().getId()), "modalita affidamento"));
		intervento.setProgramma(isEntityPresent(() -> programmaDad.getProgramma(intervento.getProgramma().getId()),"programma"));
		intervento.setStato(isEntityPresent(() -> decodificaDad.getStato(intervento.getStato().getId()), "stato"));
		//se non vengono passati i parametri desumo siano false
		initField(intervento.getLottoFunzionale(), () -> intervento.setLottoFunzionale(Boolean.FALSE));
		initField(intervento.getNuovoAffidamento(), () -> intervento.setNuovoAffidamento(Boolean.FALSE));
		initField(intervento.getFlagCuiNonGenerato(), () -> intervento.setFlagCuiNonGenerato(Boolean.FALSE));
		if (intervento.getRicompresoTipo()!=null && intervento.getRicompresoTipo().getDescrizione().toUpperCase().equals("SI")) {
			checkBusinessCondition(controlloFormaleCui(intervento.getRicompresoCui()), MsgCpassPba.PBAACQE0022.getError());
		}	
		boolean importiTuttiAZero = true;
		InterventoImporti interventoImportiPrivati = null;
		// se ho importi passati a null li inizializzo a 0
		List<InterventoImporti> listaImporti = new ArrayList<>();
		for(InterventoImporti ii : intervento.getListInterventoImporti()) {
			initField(ii.getImportoAnnoSecondo()   , () -> ii.setImportoAnnoSecondo(BigDecimal.ZERO));
			initField(ii.getImportoAnnoPrimo()     , () -> ii.setImportoAnnoPrimo(BigDecimal.ZERO));
			initField(ii.getImportoAnniSuccessivi(), () -> ii.setImportoAnniSuccessivi(BigDecimal.ZERO));
			if(importiTuttiAZero) {
				importiTuttiAZero = ii.getImportoAnnoPrimo().signum() == 0 && ii.getImportoAnnoSecondo().signum() == 0 && ii.getImportoAnniSuccessivi().signum() == 0;
			}			
			listaImporti.add(ii);
			
			interventoImportiPrivati = getInterventoImportiPrivati(ii, intervento, decodificaDad);
		}
		
		if (interventoImportiPrivati != null) {
			// togliere vecchio importo capitale privato
			InterventoImporti interventoImportiFilter = new InterventoImporti();
			interventoImportiFilter.setIntervento(interventoAttuale);
			List<InterventoImporti> interventoImportis = interventoImportiDad.getInterventiImporti(interventoImportiFilter, 0, 0).getList();
			
			for (InterventoImporti interventoImportiOld : interventoImportis) {
				if (interventoImportiOld.getRisorsa().getTipo().equals(ConstantsCPassRisorsa.TipoEnum.CAPITALE_PRIVATO.getTipo())) {
					
					if (interventoImportiOld.getRisorsa().getId().equals(interventoImportiPrivati.getRisorsa().getId())) {
						interventoImportiPrivati.setId(interventoImportiOld.getId());
						interventoImportiPrivati.setOptlock(interventoImportiOld.getOptlock());
					} else {
						interventoImportiDad.deleteNotLogically(interventoImportiOld.getId());
					}
				}
			}

			if (interventoImportiPrivati.getId() != null) {
				interventoImportiPrivati.setIntervento(interventoAttuale);
				interventoImportiDad.updateInterventoImporti(interventoImportiPrivati);
			} else {
				interventoImportiPrivati.setIntervento(interventoAttuale);
				interventoImportiDad.saveInterventoImporti(interventoImportiPrivati);
			}
		}

		intervento.setListInterventoImporti(listaImporti);
		checkBusinessCondition(!importiTuttiAZero, MsgCpassPba.PBAACQA0011.getError());
		
		/////////////////////////////fine lista CPV///////////////

		//controllo che l'anno di avvio sia o il primo anno del programma o al massimo quello successivo
		int annoAvvio = intervento.getAnnoAvvio().intValue();
		int annoProgramma = intervento.getProgramma().getAnno().intValue();
		int annoProgramma1 = annoProgramma +1;
		checkBusinessCondition((annoAvvio == annoProgramma || annoAvvio == annoProgramma1), MsgCpassPba.PBAACQE0020.getError()); 

		// controllo per la concorrenza
		checkOptlock(interventoAttuale.getOptlock(), intervento.getOptlock());
		
		setAuditData(intervento, interventoAttuale);
		interventoDad.updateIntervento(intervento);
		log.info(methodName, "Stop");

	}



}
