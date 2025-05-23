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
package it.csi.cpass.cpassbe.ejb.business.be.dad;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.ord.CpassDAooActaDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.ord.CpassRUfficioSerieDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.ord.CpassTOrdProtocolloOrdineDao;
import it.csi.cpass.cpassbe.ejb.entity.ord.CpassDAooActa;
import it.csi.cpass.cpassbe.ejb.entity.ord.CpassRUfficioSerie;
import it.csi.cpass.cpassbe.ejb.entity.ord.CpassTOrdProtocolloOrdine;
import it.csi.cpass.cpassbe.ejb.exception.NotFoundException;
import it.csi.cpass.cpassbe.ejb.mapper.CpassMappers;
import it.csi.cpass.cpassbe.ejb.util.StringUtility;
import it.csi.cpass.cpassbe.lib.dto.ord.ProtocolloOrdine;
import it.csi.cpass.cpassbe.lib.dto.ord.UfficioSerie;

/**
 * Data Access Delegate for Settore
 */
@ApplicationScoped
public class DocumentaleDad extends BaseDad {

	@Inject private CpassTOrdProtocolloOrdineDao cpassTOrdProtocolloOrdineDao;
	@Inject private CpassRUfficioSerieDao cpassRUfficioSerieDao;
	@Inject private CpassDAooActaDao cpassDAooActaDao;

	/**
	 * Finds the settore by its codice
	 * @param codice the codice to filter by
	 * @return the settore
	 */
	public Optional<ProtocolloOrdine> getProtocolloOrdineByAnnoNumero(Integer anno, String numero,UUID testataOrdineId,UUID enteId) {
		final Optional<ProtocolloOrdine> ris = cpassTOrdProtocolloOrdineDao.getProtocolloOrdineByAnnoNumero(anno, numero, testataOrdineId).map(CpassMappers.PROTOCOLLO_ORDINE::toModel);
		if(ris.isPresent()) {
			if(!StringUtility.isEmpty(ris.orElseThrow(() -> new NotFoundException("protocollo")).getAoo())) {
				final List<CpassDAooActa> aoo = cpassDAooActaDao.findByActaOrigId(Integer.parseInt(ris.orElseThrow(() -> new NotFoundException("protocollo")).getAoo()), enteId);
				ris.get().setAooCode(aoo.get(0).getAooCodice());
			}
			if(!StringUtility.isEmpty(ris.orElseThrow(() -> new NotFoundException("protocollo")).getAooOrig())) {
				final List<CpassDAooActa> aoo = cpassDAooActaDao.findByActaOrigId(Integer.parseInt(ris.orElseThrow(() -> new NotFoundException("protocollo")).getAooOrig()), enteId);
				ris.orElseThrow(() -> new NotFoundException("protocollo")).setAooOrigCode(aoo.get(0).getAooCodice());
			}
		}
		return ris;
	}
	
	/**
	 * 
	 * @param indiceclassificazione
	 * @param testataOrdineId
	 * @return
	 */
	public Optional<ProtocolloOrdine> getProtocolloOrdineByIndiceclassificazione(String indiceclassificazione,UUID testataOrdineId) {
		return cpassTOrdProtocolloOrdineDao.getProtocolloOrdineByIndiceclassificazione(indiceclassificazione, testataOrdineId).map(CpassMappers.PROTOCOLLO_ORDINE::toModel);
	}

	/**
	 * 
	 * @param voceTitolario
	 * @param numeroFascicolo
	 * @param testataOrdineId
	 * @return
	 */
	public Optional<ProtocolloOrdine> getProtocolloOrdineByStrutturaAggregativa(String voceTitolario, String numeroFascicolo, UUID testataOrdineId) {
		return cpassTOrdProtocolloOrdineDao.getProtocolloOrdineByStrutturaAggregativa(voceTitolario, numeroFascicolo, testataOrdineId).map(CpassMappers.PROTOCOLLO_ORDINE::toModel);
	}

	/**
	 * 
	 * @param testataOrdineId
	 * @param enteId
	 * @return
	 */
	public List<ProtocolloOrdine> getProtocolloOrdineByTestataOrdineId(UUID testataOrdineId, UUID enteId){
		final List<CpassTOrdProtocolloOrdine> entities = cpassTOrdProtocolloOrdineDao.findProtocolloByOrderId(testataOrdineId);
		final List<ProtocolloOrdine> models =  CpassMappers.PROTOCOLLO_ORDINE.toModels(entities);
		for(final ProtocolloOrdine prot : models) {
			if(!StringUtility.isEmpty(prot.getAoo())) {
				final List<CpassDAooActa> aoo = cpassDAooActaDao.findByActaOrigId(Integer.parseInt(prot.getAoo()), enteId);
				prot.setAooCode(aoo.get(0).getAooCodice());
			}
			if(!StringUtility.isEmpty(prot.getAooOrig())) {
				final List<CpassDAooActa> aoo = cpassDAooActaDao.findByActaOrigId(Integer.parseInt(prot.getAooOrig()), enteId);
				if(!aoo.isEmpty()) {
					prot.setAooOrigCode(aoo.get(0).getAooCodice());
				}
			}
		}
		return models;
	}
	
	/**
	 * 
	 * @param ufficioId
	 * @return
	 */
	public List<CpassRUfficioSerie> getUfficioSerieByUfficioId(Integer ufficioId){
		final List<CpassRUfficioSerie> entities = cpassRUfficioSerieDao.getUfficioSerieByUfficioId(ufficioId);
		return entities;
	}

	/**
	 * 
	 * @param ufficioSerie
	 * @return
	 */
	public CpassRUfficioSerie saveUfficioSerie(UfficioSerie ufficioSerie ){
		final CpassRUfficioSerie cpassRUfficioSerie = CpassMappers.UFFICIO_SERIE.toEntity(ufficioSerie);
		return cpassRUfficioSerieDao.save(cpassRUfficioSerie);
	}
	
	/**
	 * 
	 * @param protocollo
	 */
	public void saveProtocollo(ProtocolloOrdine protocollo) {
		final CpassTOrdProtocolloOrdine entity = CpassMappers.PROTOCOLLO_ORDINE.toEntity(protocollo);
		cpassTOrdProtocolloOrdineDao.save(entity);
	}

}