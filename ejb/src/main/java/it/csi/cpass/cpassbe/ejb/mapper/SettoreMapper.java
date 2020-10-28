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
package it.csi.cpass.cpassbe.ejb.mapper;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;

import it.csi.cpass.cpassbe.ejb.entity.CpassRUtenteSettore;
import it.csi.cpass.cpassbe.ejb.entity.CpassTSettore;
import it.csi.cpass.cpassbe.ejb.util.CpassThreadLocalContainer;
import it.csi.cpass.cpassbe.lib.dto.Settore;
import it.csi.cpass.cpassbe.lib.mapper.BaseMapperInterface;
import it.csi.cpass.cpassbe.lib.util.collection.CollectionUtils;

/**
 * Mapper between settore and CpassTSettore
 */
@Mapper
public abstract class SettoreMapper implements BaseMapperInterface<Settore, CpassTSettore> {


	@Override
	public Settore toModel(CpassTSettore entity) {
		if (entity == null) {
			return null;
		}
		Settore settore = new Settore();

		settore.setLocalita(entity.getSettoreLocalita());
		settore.setDescrizione(entity.getSettoreDescrizione());
		settore.setCap(entity.getSettoreCap());
		
		settore.setEnte(CpassMappers.ENTE.toModel(entity.getCpassTEnte()));
		
		settore.setCodice(entity.getSettoreCodice());
		settore.setIndirizzo(entity.getSettoreIndirizzo());
		settore.setProvincia(entity.getSettoreProvincia());
		settore.setTelefono(entity.getSettoreTelefono());		
		settore.setNumCivico(entity.getSettoreNumCivico());
		settore.setContatto(entity.getSettoreContatto());
		settore.setEmail(entity.getSettoreEmail());		
		settore.setId(entity.getId());
		settore.setDataCancellazione(entity.getDataCancellazione());
		settore.setDataCreazione(entity.getDataCreazione());
		settore.setDataModifica(entity.getDataModifica());
		settore.setUtenteCancellazione(entity.getUtenteCancellazione());
		settore.setUtenteCreazione(entity.getUtenteCreazione());
		settore.setUtenteModifica(entity.getUtenteModifica());
		settore.setOptlock(entity.getOptlock());
		
		// fix CPASS-245 - PBA26 Trasmettere programma: errore (in elaborazione asincrona non è presente un utente connesso)
		if (CpassThreadLocalContainer.UTENTE_CONNESSO.get() != null) {
			UUID idUtente = CpassThreadLocalContainer.UTENTE_CONNESSO.get().getId();
			CpassRUtenteSettore settoreCollegato = CollectionUtils.collectionToStream(entity.getCpassRUtenteSettores())
				.filter(us -> us.getCpassTUtente() != null && idUtente.equals(us.getCpassTUtente().getUtenteId()))
				.reduce((acc, el) -> el)
				.orElse(new CpassRUtenteSettore());
		
			settore.setRup(CpassMappers.UTENTE.toModel(settoreCollegato.getCpassTUtente()));
			settore.setUtenteSettoreDefault(settoreCollegato.getUtenteSettoreDefault());
		}
		
		//      In poche istruzioni fa il codice commentato sotto NON TOCCARE QUESTO COMMENTO (se non scrivi una lamda non sei NESSUNO!!!!!!!!)
		//		settore.setUtenteSettoreDefault(false);
		//		for(CpassRUtenteSettore us :listaUtentiSettori) {
		//			if(us.getCpassTUtente().getUtenteId().equals(idUtente)){
		//				settore.setUtenteSettoreDefault(us.getUtenteSettoreDefault());
		//			}
		//		}
		return settore;
	}

	@Override
	@IterableMapping(elementTargetType = Settore.class)
	public abstract List<Settore> toModels(Collection<CpassTSettore> entities);

	@Override
	@IterableMapping(elementTargetType = CpassTSettore.class)
	public abstract List<CpassTSettore> toEntities(Collection<Settore> models);

	
	@Override
	public CpassTSettore toEntity(Settore model) {
		if (model == null) {
			return null;
		}

		CpassTSettore cpassTSettore = new CpassTSettore();
		cpassTSettore.setSettoreCodice(model.getCodice());
		cpassTSettore.setSettoreLocalita(model.getLocalita());
		cpassTSettore.setSettoreIndirizzo(model.getIndirizzo());
		cpassTSettore.setCpassTEnte(CpassMappers.ENTE.toEntity(model.getEnte()));
		cpassTSettore.setSettoreCap(model.getCap());
		cpassTSettore.setSettoreTelefono(model.getTelefono());
		cpassTSettore.setSettoreProvincia(model.getProvincia());
		cpassTSettore.setSettoreDescrizione(model.getDescrizione());
		cpassTSettore.setDataCancellazione(model.getDataCancellazione());
		cpassTSettore.setDataCreazione(model.getDataCreazione());
		cpassTSettore.setDataModifica(model.getDataModifica());
		cpassTSettore.setOptlock(model.getOptlock());
		cpassTSettore.setUtenteCancellazione(model.getUtenteCancellazione());
		cpassTSettore.setUtenteCreazione(model.getUtenteCreazione());
		cpassTSettore.setUtenteModifica(model.getUtenteModifica());
		cpassTSettore.setId(model.getId());
		cpassTSettore.setSettoreNumCivico(model.getNumCivico());
		cpassTSettore.setSettoreContatto(model.getContatto());
		cpassTSettore.setSettoreEmail(model.getEmail());
		return cpassTSettore;
	}
	
}
