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
package it.csi.cpass.cpassbe.ejb.business.be.dad;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.CpassDModuloDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.CpassDPermessoDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.CpassDRuoloDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.CpassRUtenteSettoreDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.CpassTSettoreDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.CpassTUtenteDao;
import it.csi.cpass.cpassbe.ejb.entity.CpassDModulo;
import it.csi.cpass.cpassbe.ejb.entity.CpassDPermesso;
import it.csi.cpass.cpassbe.ejb.entity.CpassDRuolo;
import it.csi.cpass.cpassbe.ejb.entity.CpassRUtenteSettore;
import it.csi.cpass.cpassbe.ejb.entity.CpassTSettore;
import it.csi.cpass.cpassbe.ejb.entity.CpassTUtente;
import it.csi.cpass.cpassbe.ejb.mapper.CpassMappers;
import it.csi.cpass.cpassbe.ejb.util.jpa.Page;
import it.csi.cpass.cpassbe.lib.dto.Modulo;
import it.csi.cpass.cpassbe.lib.dto.Permesso;
import it.csi.cpass.cpassbe.lib.dto.Ruolo;
import it.csi.cpass.cpassbe.lib.dto.Settore;
import it.csi.cpass.cpassbe.lib.dto.SettoreRuoliPermessi;
import it.csi.cpass.cpassbe.lib.dto.Utente;
import it.csi.cpass.cpassbe.lib.util.pagination.PagedList;

/**
 * Data Access Delegate for interventos
 */
@ApplicationScoped
public class UtenteDad extends BaseDad {

	@Inject private CpassTUtenteDao cpassTUtenteDao;
	@Inject private CpassTSettoreDao cpassTSettoreDao;
	@Inject private CpassDModuloDao cpassDModuloDao;
	@Inject private CpassDPermessoDao cpassDPermessoDao;
	@Inject private CpassDRuoloDao cpassDRuoloDao;
	@Inject private CpassRUtenteSettoreDao cpassRUtenteSettoreDao;

	/**
	 * Find by id
	 * @param uuid the uuid
	 * @return the model instance
	 */
	public Optional<Utente> getUtente(UUID uuid) {
		return cpassTUtenteDao.findOne(uuid).map(CpassMappers.UTENTE::toModel);
	}

	/**
	 * Find by id
	 * @param uuid the uuid
	 * @return the model instance
	 */
	public Optional<Utente> getUtenteRupDelegante(UUID uuid) {
		return cpassTUtenteDao.getUtenteRupDelegante(uuid).map(CpassMappers.UTENTE::toModel);	
	}
	
	/**
	 * Find paginated
	 * @param page the page
	 * @param size the size
	 * @return the model instances
	 */
	public PagedList<Utente> getUtenti(int page, int size) {
		Page<CpassTUtente> cpassTUtentes = cpassTUtenteDao.findAll(page, size);
		return toPagedList(cpassTUtentes, page, size, CpassMappers.UTENTE::toModel);
	}


	/**
	 * Deletes by uuid
	 * @param uuid the uuid
	 */
	public void deleteUtente(UUID uuid) {
		cpassTUtenteDao.deleteLogically(uuid);
	}
	/**
	 * Inserts the utente
	 * @param utente the utente
	 * @return the model instance
	 */
	public Utente saveUtente(Utente utente) {
		CpassTUtente cpassTutente = CpassMappers.UTENTE.toEntity(utente);
		cpassTUtenteDao.insert(cpassTutente);
		return utente;
	}
	/**
	 * Updates the utente
	 * @param utente the utente
	 * @return the model instance
	 */
	public Utente updateUtente(Utente utente) {
		CpassTUtente cpassTutente = CpassMappers.UTENTE.toEntity(utente);
		cpassTUtenteDao.update(cpassTutente);
		return utente;
	}
	/**
	 * Gets an Utente by its codice fiscale
	 * @param codiceFiscale
	 * @return the utente instance
	 */
	public Optional<Utente> getUtenteByCf(String codiceFiscale) {
		return cpassTUtenteDao.findUtenteByCf(codiceFiscale).map(CpassMappers.UTENTE::toModel);
	}

	/**
	 * Gets the Settore by the utente
	 * @param utenteId the utente id
	 * @return the settore instances
	 */
	public List<Settore> getSettoriByUtente(UUID utenteId) {
		List<CpassTSettore> cpassTSettori = cpassTSettoreDao.getSettoriByUtenteId(utenteId);
		List<Settore> listaSettori = CpassMappers.SETTORE.toModels(cpassTSettori);
		return listaSettori;		
	}
	
	/**
	 * 
	 * @param utenteId
	 * @return
	 */
	public List<SettoreRuoliPermessi> getSettoriRuoliPermessiByUtenteService(UUID utenteId) {
		List<CpassTSettore> cpassTSettori = cpassTSettoreDao.getSettoriByUtenteId(utenteId);
		List<Settore> listaSettori = CpassMappers.SETTORE.toModels(cpassTSettori);
		
		List<SettoreRuoliPermessi> listaSettoreRuoliPermessi = new ArrayList<SettoreRuoliPermessi>();
		
		for(Settore settore : listaSettori) {
			SettoreRuoliPermessi srp = new SettoreRuoliPermessi();
			srp.setSettore(settore);			
			List<CpassDPermesso> entities = cpassDPermessoDao.getByUtenteIdAndSettoreIdAndModuloId(utenteId, settore.getId(), null);
			List<Permesso> listPermessi = CpassMappers.PERMESSO.toModels(entities);
			srp.setListPermessi(listPermessi);
			List<CpassDRuolo> cpassListRuoli = cpassDRuoloDao.getRuoliByUtenteSettore(settore.getId(),utenteId);
			List<Ruolo> listRuoli = CpassMappers.RUOLO.toModels(cpassListRuoli);
			srp.setListRuoli(listRuoli);
			listaSettoreRuoliPermessi.add(srp);
		}
		return listaSettoreRuoliPermessi;		
	}	
	
	
	
	
	/**
	 * Gets the Modulo by the utente and settore
	 * @param utenteId the utente id
	 * @param settoreId the settore id
	 * @return the modulo instances
	 */
	public List<Modulo> getModuliByUtenteAndSettore(UUID utenteId, UUID settoreId) {
		List<CpassDModulo> entities = cpassDModuloDao.getByUtenteIdAndSettoreId(utenteId, settoreId);
		return CpassMappers.MODULO.toModels(entities);
	}
	
	/**
	 * Gets the Permesso by the utente and settore and modulo
	 * @param utenteId the utente id
	 * @param settoreId the settore id
	 * @param moduloId the modulo id
	 * @return the permesso instances
	 */
	public List<Permesso> getPermessiByUtenteAndSettoreAndModulo(UUID utenteId, UUID settoreId, Integer moduloId) {
		List<CpassDPermesso> entities = cpassDPermessoDao.getByUtenteIdAndSettoreIdAndModuloId(utenteId, settoreId, moduloId);
		return CpassMappers.PERMESSO.toModels(entities);
	}
	
	/**
	 * Gets the Permesso by the utente and settore and modulo
	 * @param utenteId the utente id
	 * @param settoreId the settore id
	 * @return the permesso instances
	 */
	public List<Permesso> getPermessiByUtenteAndSettore(UUID utenteId, UUID settoreId) {
		List<CpassDPermesso> entities = cpassDPermessoDao.getByUtenteIdAndSettoreId(utenteId, settoreId);
		return CpassMappers.PERMESSO.toModels(entities);
	}
	
	/**
	 * Gets the utente by the settore
	 * @param settoreId the settore id
	 * @param ruoloCodice 
	 * @return the utente
	 */
	public List<Utente> getUtenteBySettoreRuolo(UUID settoreId, String ruoloCodice) {
		List<CpassTUtente> entities = cpassTUtenteDao.getUtenteBySettoreRuolo(settoreId,ruoloCodice);
		return CpassMappers.UTENTE.toModels(entities);
	}

	public List<Ruolo> getRuoliByUtenteSettore(UUID utenteId, UUID settoreId) {
		List<CpassDRuolo> entities = cpassDRuoloDao.getRuoliByUtenteSettore(settoreId, utenteId);
		return CpassMappers.RUOLO.toModels(entities);
	}
	
	public List<CpassRUtenteSettore> findByUtenteSettore(UUID utenteId, UUID settoreId) {
		List<CpassRUtenteSettore> entities =  cpassRUtenteSettoreDao.findByUtenteSettore(utenteId, settoreId);
		return entities;
		// return CpassMappers.RUOLO.toModels(entities);
	}

	public List<Utente> getRupsBySettoreId(UUID idIntervento) {
		List<CpassTUtente> entities = cpassTUtenteDao.getRupsBySettoreId(idIntervento);
		return CpassMappers.UTENTE.toModels(entities);
	}
	
	/**
	 * Gets the Settore by the utente RUP
	 * @param utenteId the utente id
	 * @return the settore instances
	 */
	public List<Settore> getSettoriByRupId(UUID rupId) {
		List<CpassTSettore> listaSettori = cpassTSettoreDao.getSettoriByRupId(rupId);
		return CpassMappers.SETTORE.toModels(listaSettori);
		
	}
}
