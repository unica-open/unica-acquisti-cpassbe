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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import it.csi.cpass.cpassbe.ejb.business.be.dad.DecodificaDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.InterventoDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.UtenteDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.intervento.PutInterventoStatoBozzaDaRifiutoRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.intervento.PutInterventoStatoBozzaDaRifiutoResponse;
import it.csi.cpass.cpassbe.ejb.util.CpassEnum;
import it.csi.cpass.cpassbe.ejb.util.CpassStatiEnum;
import it.csi.cpass.cpassbe.ejb.util.CpassThreadLocalContainer;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;
import it.csi.cpass.cpassbe.lib.dto.ApiError;
import it.csi.cpass.cpassbe.lib.dto.Settore;
import it.csi.cpass.cpassbe.lib.dto.Stato;
import it.csi.cpass.cpassbe.lib.dto.Utente;
import it.csi.cpass.cpassbe.lib.dto.pba.Intervento;

/**
 * Saves an stato Intervento
 */
public class PutInterventoStatoBozzaDaRifiutoService extends BaseInterventoService<PutInterventoStatoBozzaDaRifiutoRequest, PutInterventoStatoBozzaDaRifiutoResponse> {

	
	private UtenteDad utenteDad;
	private DecodificaDad decodificaDad;
	private List<Intervento> listaIntervento;
	private Stato stato;
	/**
	 * Constructor
	 * @param configurationHelper the configuration helper
	 * @param interventoDad the intervento DAD
	 */
	public PutInterventoStatoBozzaDaRifiutoService(ConfigurationHelper configurationHelper, InterventoDad interventoDad, UtenteDad utenteDad,DecodificaDad decodificaDad) {
		super(configurationHelper, interventoDad);
		this.utenteDad = utenteDad;
		this.decodificaDad = decodificaDad;
	}

	@Override
	protected void checkServiceParams() {
//		intervento = request.getIntervento();
//		checkModel(intervento, "intervento");
//		checkNotNull( intervento.getOptlock(),"opt look");
	}

	@Override
	protected void execute() {
		
		listaIntervento = request.getInterventi();
		stato           = isEntityPresent(() -> decodificaDad.getStato(CpassStatiEnum.INT_BOZZA.getCostante(), CpassEnum.INTERVENTO.getCostante()), "stato");
		Utente utenteConnesso = CpassThreadLocalContainer.UTENTE_CONNESSO.get();
		Settore settoreCorrente = CpassThreadLocalContainer.SETTORE_UTENTE.get();
		
		List<ApiError> listaErrori = new ArrayList<ApiError>(); // vedere gestione lista errori di PostInterventiCopiaService
		
		for(Intervento intervento :listaIntervento) {
		
			//TODO da parlare con Alessandro in merito alla gestione concorrenza
			Intervento interventoAttuale = isEntityPresent(() -> interventoDad.getIntervento(intervento.getId()), "intervento");
			// da rivedere 
//			List<Ruolo> ruoli = utenteDad.getRuoliByUtenteSettore(utenteConnesso.getId(), request.getSettoreId());
//	        if (!containsRuolo(ruoli,"RUP")) {
//	        	checkBusinessCondition(!interventoAttuale.getStato().getCodice().equals(CpassStatiEnum.INT_VALIDATO.getCostante()), MsgCpassPba.PBAACQE0013.getError());
//	        }
			
			//TODO inserire il controllo sul fatto che l'utente sia abilitato o meno al cambio stato
			checkOptlock(intervento.getOptlock(), interventoAttuale.getOptlock());
			
			Date now = new Date();
			intervento.setDataRifiuto(now);
			intervento.setUtenteRifiuto(utenteConnesso);
			intervento.setStato(stato);
			interventoDad.updateStatoIntervento(intervento);
		}	
	}

//	private boolean containsRuolo(List<Ruolo> ruoli,String ruoloCode) {
//		boolean ris = false;
//		for(Ruolo ruolo : ruoli) {
//			if (ruolo.getCodice().equals(ruoloCode)){
//				ris = true;
//			}
//		}
//		return ris;
//	}
}
