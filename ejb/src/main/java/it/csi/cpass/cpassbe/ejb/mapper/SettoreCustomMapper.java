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
package it.csi.cpass.cpassbe.ejb.mapper;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;

import it.csi.cpass.cpassbe.ejb.entity.CpassRSettoreCdc;
import it.csi.cpass.cpassbe.ejb.entity.CpassRUfficioSettore;
import it.csi.cpass.cpassbe.ejb.entity.CpassRUtenteSettore;
import it.csi.cpass.cpassbe.ejb.entity.CpassTCdc;
import it.csi.cpass.cpassbe.ejb.entity.CpassTSettore;
import it.csi.cpass.cpassbe.ejb.entity.CpassTUfficio;
import it.csi.cpass.cpassbe.ejb.entity.ord.CpassDAooActa;
import it.csi.cpass.cpassbe.ejb.entity.ord.CpassRSettoreAooActa;
import it.csi.cpass.cpassbe.lib.dto.Cdc;
import it.csi.cpass.cpassbe.lib.dto.Settore;
import it.csi.cpass.cpassbe.lib.dto.Ufficio;
import it.csi.cpass.cpassbe.lib.dto.ord.AooActa;
import it.csi.cpass.cpassbe.lib.mapper.BaseMapperInterface;
import it.csi.cpass.cpassbe.lib.util.collection.CollectionUtils;
import it.csi.cpass.cpassbe.lib.util.threadlocal.CpassThreadLocalContainer;

/**
 * Mapper between settore and CpassTSettore
 */
@Mapper(uses = {TipoSettoreMapper.class})
public abstract class SettoreCustomMapper implements BaseMapperInterface<Settore, CpassTSettore> {


	@Override
	public Settore toModel(CpassTSettore entity) {
		if (entity == null) {
			return null;
		}
		final Settore settore = new Settore();
		settore.setDescrizione(entity.getSettoreDescrizione());
		settore.setEnte(CpassMappers.ENTE.toModel(entity.getCpassTEnte()));
		settore.setTipoSettore(CpassMappers.TIPO_SETTORE.toModel(entity.getCpassDTipoSettore()));
		settore.setCodice(entity.getSettoreCodice());
		settore.setId(entity.getId());

		settore.setDataValiditaInizio(entity.getDataValiditaInizio());
		settore.setDataValiditaFine(entity.getDataValiditaFine());

		settore.setDataCancellazione(entity.getDataCancellazione());
		settore.setDataCreazione(entity.getDataCreazione());
		settore.setDataModifica(entity.getDataModifica());
		settore.setUtenteCancellazione(entity.getUtenteCancellazione());
		settore.setUtenteCreazione(entity.getUtenteCreazione());
		settore.setUtenteModifica(entity.getUtenteModifica());
		settore.setOptlock(entity.getOptlock());
		settore.setFirma(entity.getFirma());

		if(entity.getCpassTSettorePadre() != null) {
			final Settore settorePadre = new Settore();
			settorePadre.setId(entity.getCpassTSettorePadre().getSettoreId());
			settorePadre.setCodice(entity.getCpassTSettorePadre().getSettoreCodice());
			settorePadre.setDescrizione(entity.getCpassTSettorePadre().getSettoreDescrizione());
			settore.setSettorePadre(settorePadre);
		}


		// fix CPASS-245 - PBA26 Trasmettere programma: errore (in elaborazione asincrona non è presente un utente connesso)
		if (CpassThreadLocalContainer.UTENTE_CONNESSO.get() != null) {
			final UUID idUtente = CpassThreadLocalContainer.UTENTE_CONNESSO.get().getId();
			final CpassRUtenteSettore settoreCollegato = CollectionUtils.collectionToStream(entity.getCpassRUtenteSettores())
					.filter(us -> us.getCpassTUtente() != null && idUtente.equals(us.getCpassTUtente().getUtenteId()))
					.reduce((acc, el) -> el)
					.orElse(new CpassRUtenteSettore());

			settore.setRup(CpassMappers.UTENTE.toModel(settoreCollegato.getCpassTUtente()));
			settore.setUtenteSettoreDefault(settoreCollegato.getUtenteSettoreDefault());
		}
		//settori Indirizzo
		settore.setSettoreIndirizzos(CpassMappers.SETTORE_INDIRIZZO.toModels(entity.getCpassTSettoreIndirizzos()));

		final List<Ufficio> listaUffici = new ArrayList<>();
		if(entity.getCpassRUfficioSettores()!= null) {
			for(final CpassRUfficioSettore cpassRUfficioSettores : entity.getCpassRUfficioSettores()) {
				if(cpassRUfficioSettores.getDataValiditaFine() == null) {
					final CpassTUfficio cpassTUfficio = cpassRUfficioSettores.getCpassTUfficio();
					listaUffici.add(CpassMappers.UFFICIO.toModel(cpassTUfficio));
				}
			}
		}
		settore.setUffici(listaUffici);

		final List<Cdc> listaCdc = new ArrayList<>();
		if(entity.getCpassRSettoreCdcs()!= null) {
			for(final CpassRSettoreCdc cpassRSettoreCdcs : entity.getCpassRSettoreCdcs()) {
				if(cpassRSettoreCdcs.getDataCancellazione() == null) {
					final CpassTCdc cpassTCdc = cpassRSettoreCdcs.getCpassTCdc();
					listaCdc.add(CpassMappers.CDC.toModel(cpassTCdc));
				}
			}
		}
		settore.setCdcs(listaCdc);

		//      In poche istruzioni fa il codice commentato sotto NON TOCCARE QUESTO COMMENTO (se non scrivi una lamda non sei NESSUNO!!!!!!!!)
		//		settore.setUtenteSettoreDefault(false);
		//		for(CpassRUtenteSettore us :listaUtentiSettori) {
		//			if(us.getCpassTUtente().getUtenteId().equals(idUtente)){
		//				settore.setUtenteSettoreDefault(us.getUtenteSettoreDefault());
		//			}
		//		}


		final List<AooActa> listaAooActa = new ArrayList<>();
		if(entity.getCpassRSettoreAooActas()!= null) {
			for(final CpassRSettoreAooActa cpassRSettoreAooActa : entity.getCpassRSettoreAooActas()) {
				final CpassDAooActa cpassDAooActa = cpassRSettoreAooActa.getCpassDAooActa();
				listaAooActa.add(CpassMappers.AOO_ACTA.toModel(cpassDAooActa));
			}
		}
		settore.setAooActas(listaAooActa);

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
		final CpassTSettore cpassTSettore = new CpassTSettore();
		cpassTSettore.setSettoreCodice(model.getCodice());
		cpassTSettore.setCpassTEnte(CpassMappers.ENTE.toEntity(model.getEnte()));
		cpassTSettore.setCpassDTipoSettore(CpassMappers.TIPO_SETTORE.toEntity(model.getTipoSettore()));
		cpassTSettore.setSettoreDescrizione(model.getDescrizione());
		cpassTSettore.setDataCancellazione(model.getDataCancellazione());
		cpassTSettore.setDataCreazione(model.getDataCreazione());
		cpassTSettore.setDataModifica(model.getDataModifica());

		cpassTSettore.setDataValiditaInizio(model.getDataValiditaInizio());
		cpassTSettore.setDataValiditaFine(model.getDataValiditaFine());

		cpassTSettore.setOptlock(model.getOptlock());
		cpassTSettore.setUtenteCancellazione(model.getUtenteCancellazione());
		cpassTSettore.setUtenteCreazione(model.getUtenteCreazione());
		cpassTSettore.setUtenteModifica(model.getUtenteModifica());
		cpassTSettore.setId(model.getId());
		cpassTSettore.setFirma(model.getFirma());

		cpassTSettore.setCpassTSettoreIndirizzos(CpassMappers.SETTORE_INDIRIZZO.toEntities(model.getSettoreIndirizzos()));
		if(model.getSettorePadre() != null) {
			final CpassTSettore cpassTsettorePadre = new CpassTSettore();
			cpassTsettorePadre.setId(model.getSettorePadre().getId());
			cpassTsettorePadre.setSettoreCodice(model.getSettorePadre().getCodice());
			cpassTsettorePadre.setSettoreDescrizione(model.getSettorePadre().getDescrizione());
			cpassTSettore.setCpassTSettorePadre(cpassTsettorePadre);
			//settore.setIdPadre(entity.getCpassTSettorePadre().getSettoreId());
		}
		// TODO da gestire la lista degli uffici
		/*
		List<Ufficio> listaUffici = new ArrayList<Ufficio>();
		if(entity.getCpassRUfficioSettores()!= null) {
			for(CpassRUfficioSettore cpassRUfficioSettores : entity.getCpassRUfficioSettores()) {
				CpassTUfficio cpassTUfficio = cpassRUfficioSettores.getCpassTUfficio();
				listaUffici.add(CpassMappers.UFFICIO.toModel(cpassTUfficio));
			}
		}
		settore.setUffici(listaUffici);
		 */


		return cpassTSettore;
	}
}
