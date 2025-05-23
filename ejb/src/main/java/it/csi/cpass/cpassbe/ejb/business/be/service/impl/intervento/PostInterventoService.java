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
import java.util.UUID;

import it.csi.cpass.cpassbe.ejb.business.be.dad.DecodificaDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.InterventoDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.ProgrammaDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.SystemDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.intervento.PostInterventoRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.intervento.PostInterventoResponse;
import it.csi.cpass.cpassbe.ejb.util.ConstantsCPassParametro;
import it.csi.cpass.cpassbe.ejb.util.ConstantsCPassStato.StatoInterventiEnum;
import it.csi.cpass.cpassbe.ejb.util.CpassEnum;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;
import it.csi.cpass.cpassbe.lib.dto.Parametro;
import it.csi.cpass.cpassbe.lib.dto.Utente;
import it.csi.cpass.cpassbe.lib.dto.error.CoreError;
import it.csi.cpass.cpassbe.lib.dto.error.MsgCpassPba;
import it.csi.cpass.cpassbe.lib.dto.pba.Intervento;
import it.csi.cpass.cpassbe.lib.dto.pba.InterventoImporti;
import it.csi.cpass.cpassbe.lib.util.threadlocal.CpassThreadLocalContainer;

/**
 * Saves an Intervento
 */
public class PostInterventoService extends BaseInterventoService<PostInterventoRequest, PostInterventoResponse> {

	private final DecodificaDad decodificaDad;
	private final ProgrammaDad programmaDad;
	private Intervento intervento;
	private final SystemDad systemDad;

	/**
	 * Constructor
	 * @param configurationHelper the configuration helper
	 * @param interventoDad the intervento DAD
	 * @param decodificaDad the deodifica DAD
	 * @param programmaDad the programma DAD
	 */
	public PostInterventoService(ConfigurationHelper configurationHelper, InterventoDad interventoDad, DecodificaDad decodificaDad, ProgrammaDad programmaDad,SystemDad systemDad) {
		super(configurationHelper, interventoDad);
		this.decodificaDad = decodificaDad;
		this.programmaDad = programmaDad;
		this.systemDad = systemDad;
	}

	@Override
	protected void checkServiceParams() {
		intervento = request.getIntervento();
		checkNotNull(intervento, "intervento", Boolean.TRUE);
		checkNotNull( intervento.getAnnoAvvio(),"anno avvio", Boolean.TRUE);
		checkNotNull( intervento.getDurataMesi(),"durata mesi");
		checkNotNull( intervento.getDescrizioneAcquisto(),"descrizione acquisto");
		checkModel(intervento.getUtenteRup(), "utente Rup");
		checkModel(intervento.getSettoreInterventi(), "settore interventi");
		checkModel(intervento.getCpv(), "cpv");
		checkModel(intervento.getProgramma(), "programma");
		checkModel(intervento.getNuts(), "nuts");
		checkModel(intervento.getPriorita(), "priorita");
		checkModel(intervento.getModalitaAffidamento(), "modalita affidamento");
		checkCondition(intervento.getListInterventoAltriDati() == null
				|| intervento.getListInterventoAltriDati().isEmpty()
				|| intervento.getListInterventoAltriDati().size() < 2,
				CoreError.GENERIC_ERROR.getError("error", "La lista di altri dati può contenere un unico elemento"));
	}

	@Override
	protected void execute() {
		final Utente utente = CpassThreadLocalContainer.UTENTE_CONNESSO.get();
		final UUID enteId = CpassThreadLocalContainer.SETTORE_UTENTE.get().getEnte().getId();
		intervento.setCpv(isEntityPresent(() -> decodificaDad.getCpv(intervento.getCpv().getId()), "cpv"));
		intervento.setNuts(isEntityPresent(() -> decodificaDad.getNut(intervento.getNuts().getId()), "nuts"));
		intervento.setSettoreInterventi(isEntityPresent(() -> decodificaDad.getSettoreInterventi(intervento.getSettoreInterventi().getId()), "settore interventi"));
		intervento.setPriorita(isEntityPresent(() -> decodificaDad.getPriorita(intervento.getPriorita().getId()), "priorita"));
		intervento.setModalitaAffidamento(isEntityPresent(() -> decodificaDad.getModalitaAffidamento(intervento.getModalitaAffidamento().getId()), "modalita affidamento"));
		intervento.setProgramma(isEntityPresent(() -> programmaDad.getProgramma(intervento.getProgramma().getId()), "programma"));
		if(intervento.getCup()!=null) {
			intervento.setCup(intervento.getCup().toUpperCase());
		}
		// Impostazione dello stato a bozza
		intervento.setStato(isEntityPresent(() -> decodificaDad.getStatoOpt(StatoInterventiEnum.BOZZA.getCostante(), CpassEnum.INTERVENTO.getCostante()), "stato"));
		//se non vengono passati i parametri desumo siano false
		initField(intervento.getLottoFunzionale(), () -> intervento.setLottoFunzionale(Boolean.FALSE));
		initField(intervento.getNuovoAffidamento(), () -> intervento.setNuovoAffidamento(Boolean.FALSE));
		initField(intervento.getFlagCuiNonGenerato(), () -> intervento.setFlagCuiNonGenerato(Boolean.FALSE));
		initField(intervento.getEsenteCup(), () -> intervento.setEsenteCup(Boolean.FALSE));
		initField(intervento.getCapofila(), () -> intervento.setCapofila(Boolean.FALSE));

		intervento.setVersioneDefinitiva(Boolean.TRUE);
		if(intervento.getVersioneDefinitivaStr()!= null && intervento.getVersioneDefinitivaStr().equalsIgnoreCase("false")) {
			intervento.setVersioneDefinitiva(Boolean.FALSE);
		}
		//gestione lotto
		if(intervento.getLottoFunzionale() == null || !intervento.getLottoFunzionale()) {
			intervento.setInterventoCapofila(null);
			intervento.setCapofila(Boolean.FALSE);
		}

		final Parametro isVistoRagioneria = systemDad.getParametro(ConstantsCPassParametro.ChiaveEnum.VISTO_RAGIONERIA.getCostante(),null, enteId);
		intervento.setVistoRagioneria(Boolean.TRUE);
		if(isVistoRagioneria.getValore() != null && isVistoRagioneria.getValore().equalsIgnoreCase("true")) {
			intervento.setVistoRagioneria(Boolean.FALSE);
		}
		final Parametro isVersioneDefinitiva = systemDad.getParametro(ConstantsCPassParametro.ChiaveEnum.GESTIONE_ACQUISTO_VERS_DEFINITIVA.getCostante(),null, enteId);
		if(isVersioneDefinitiva == null || isVersioneDefinitiva.getValore().equalsIgnoreCase("false")) {
			intervento.setVersioneDefinitiva(Boolean.TRUE);
		}
		/* Se l'attore ha spuntato il campo di mappa 18, allora deve avere indicato un valore anche nel campo 19.
		 * CUI lavoro o altra acquisizione, altrimenti esporre l'anomalia: "indicare il CUI lavoro o altra acquisizione"
		 */
		if (intervento.getRicompresoTipo()!=null && intervento.getRicompresoTipo().getDescrizione().toUpperCase().equals("SI")) {
			checkBusinessCondition(controlloFormaleCui(intervento.getRicompresoCui()), MsgCpassPba.PBAACQE0022.getError());
		}
		// Se la versione del programma è > 1, occorre verificare che nel campo 27 Acquisto aggiunto o variato sia stato inserito un valore
		// che ha CPASS_D_PBA_ACQUISTO_VARIATO.controlli = ‘INSERT’, altrimenti occorre esporre il messaggio PBA-ACQ-E0088
		if(intervento.getProgramma().getVersione()>1) {
			if(!(intervento.getAcquistoVariato()!=null && intervento.getAcquistoVariato().getControlli()!= null && intervento.getAcquistoVariato().getControlli().equalsIgnoreCase("INSERT"))) {
				generaException(MsgCpassPba.PBAACQE0092.getError());
			}
		}
		boolean importiTuttiAZero = Boolean.TRUE;
		InterventoImporti interventoImportiPrivati = null;
		// se ho importi passati a null li inizializzo a 0
		for(final InterventoImporti ii : intervento.getListInterventoImporti()) {
			initField(ii.getImportoAnnoPrimo(), () -> ii.setImportoAnnoPrimo(BigDecimal.ZERO));
			initField(ii.getImportoAnnoSecondo(), () -> ii.setImportoAnnoSecondo(BigDecimal.ZERO));
			initField(ii.getImportoAnnoTerzo(), () -> ii.setImportoAnnoTerzo(BigDecimal.ZERO));
			initField(ii.getImportoAnniSuccessivi(), () -> ii.setImportoAnniSuccessivi(BigDecimal.ZERO));
			if(importiTuttiAZero) {
				importiTuttiAZero = ii.getImportoAnnoPrimo().signum() == 0 && ii.getImportoAnnoSecondo().signum() == 0 && ii.getImportoAnnoTerzo().signum() == 0 && ii.getImportoAnniSuccessivi().signum() == 0;
			}
			interventoImportiPrivati = getInterventoImportiPrivati(ii, intervento, decodificaDad);
		}
		if (interventoImportiPrivati != null) {
			intervento.getListInterventoImporti().add(interventoImportiPrivati);
			final boolean importiCPTuttiAZero = interventoImportiPrivati.getImportoAnnoPrimo().signum() == 0 && interventoImportiPrivati.getImportoAnnoSecondo().signum() == 0 && interventoImportiPrivati.getImportoAnniSuccessivi().signum() == 0;
			if(!importiCPTuttiAZero) {
				intervento.setRisorsaIdCapitalePrivato(null);
			}
		}else {
			intervento.setRisorsaIdCapitalePrivato(null);
		}
		checkBusinessCondition(!importiTuttiAZero, MsgCpassPba.PBAACQA0011.getError());
		intervento = interventoDad.saveIntervento(intervento,utente);
		response.setIntervento(intervento);
	}
}
