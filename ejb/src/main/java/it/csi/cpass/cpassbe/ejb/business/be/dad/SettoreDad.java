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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.apache.commons.lang.StringUtils;

import it.csi.cpass.cpassbe.ejb.business.be.dad.sort.SettoreSort;
import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.CpassRSettoreCdcDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.CpassRUfficioSettoreDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.CpassTCdcDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.CpassTSettoreDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.CpassTSettoreIndirizzoDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.CpassTSettoreStoricoDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.ord.CpassDAooActaDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.ord.CpassRSettoreAooActaDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.ord.CpassTOrdSezioneDao;
import it.csi.cpass.cpassbe.ejb.entity.CpassRSettoreCdc;
import it.csi.cpass.cpassbe.ejb.entity.CpassRUfficioSettore;
import it.csi.cpass.cpassbe.ejb.entity.CpassTCdc;
import it.csi.cpass.cpassbe.ejb.entity.CpassTSettore;
import it.csi.cpass.cpassbe.ejb.entity.CpassTSettoreStorico;
import it.csi.cpass.cpassbe.ejb.entity.CpassTUfficio;
import it.csi.cpass.cpassbe.ejb.entity.ord.CpassDAooActa;
import it.csi.cpass.cpassbe.ejb.entity.ord.CpassRSettoreAooActa;
import it.csi.cpass.cpassbe.ejb.entity.ord.CpassTOrdSezione;
import it.csi.cpass.cpassbe.ejb.entity.ord.CpassTSettoreIndirizzo;
import it.csi.cpass.cpassbe.ejb.exception.NotFoundException;
import it.csi.cpass.cpassbe.ejb.mapper.CpassMappers;
import it.csi.cpass.cpassbe.ejb.util.CpassEnum;
import it.csi.cpass.cpassbe.ejb.util.jpa.Page;
import it.csi.cpass.cpassbe.lib.dto.Cdc;
import it.csi.cpass.cpassbe.lib.dto.Settore;
import it.csi.cpass.cpassbe.lib.dto.SettoreIndirizzo;
import it.csi.cpass.cpassbe.lib.dto.SettoreStorico;
import it.csi.cpass.cpassbe.lib.dto.Ufficio;
import it.csi.cpass.cpassbe.lib.dto.ord.AooActa;
import it.csi.cpass.cpassbe.lib.dto.ord.SettoreAooActa;
import it.csi.cpass.cpassbe.lib.dto.ord.Sezione;
import it.csi.cpass.cpassbe.lib.util.pagination.PagedList;
import it.csi.cpass.cpassbe.lib.util.pagination.Sort;

/**
 * Data Access Delegate for Settore
 */
@ApplicationScoped
public class SettoreDad extends BaseDad {

	@Inject private CpassTSettoreDao cpassTSettoreDao;
	@Inject private CpassTSettoreIndirizzoDao cpassTSettoreIndirizzoDao;
	@Inject private CpassTSettoreStoricoDao cpassTSettoreStoricoDao;
	@Inject private CpassRUfficioSettoreDao cpassRUfficioSettoreDao;
	@Inject private CpassRSettoreCdcDao cpassRSettoreCdcDao;
	@Inject private CpassDAooActaDao cpassDAooActaDao;
	@Inject private CpassRSettoreAooActaDao cpassRSettoreAooActaDao;
	@Inject private CpassTOrdSezioneDao cpassTOrdSezioneDao;
	@Inject private CpassTCdcDao cpassTCdcDao;
	/**
	 * Find paginated
	 * @param page the page
	 * @param size the size
	 * @param sort
	 * @param settore the settore
	 * @return the model instances
	 */
	public PagedList<Settore> postRicercaSettori(int page, int size, Sort sort, Settore settore,Boolean all) {
		String sortField = null;
		String sortDirection = null;
		if (sort != null) {
			sortField = SettoreSort.byModelName(sort.getField()).getQueryName();
			sortDirection = sort.getOrder().getSortDirection();
		}

		final Page<CpassTSettore> cpassTSettores = cpassTSettoreDao.findPaginated(
				settore.getCodice(),
				settore.getDescrizione(),
				settore.getUtenteSettoreDefault(),
				settore.getRup(),
				settore.getTipoSettore() != null ? settore.getTipoSettore().getId() : null,
						settore.getEnte() != null ? settore.getEnte().getId() : null,
								StringUtils.isEmpty(settore.getValidita()) ?  "true" : settore.getValidita(),
								StringUtils.isEmpty(settore.getValidita()) ?  "NOALL" : settore.getValidita(),
										page,
										size,
										sortField,
										sortDirection);

		return toPagedList(cpassTSettores, page, size, CpassMappers.SETTORE_CUSTOM::toModel);
	}

	/**
	 * Finds the settore by its codice
	 * @param codice the codice to filter by
	 * @return the settore
	 */
	public Optional<Settore> findByCodice(String codice, UUID enteId) {
		return findByCodice(codice, enteId, Boolean.TRUE, "like");
	}

	/**
	 * Finds the settore by its codice
	 * @param codice the codice to filter by
	 * @return the settore
	 */
	public Optional<Settore> findByCodiceObbligatorio(String codice, UUID enteId,Boolean valido) {
		// TODO mettere mano a questa pezza
		if(codice == null || codice.trim().equals("")) {
			codice = "###AAA###";
		}
		return findByCodice(codice,enteId,valido,"like");
	}

	/**
	 * Finds the settore by its codice
	 * @param codice the codice to filter by
	 * @return the settore
	 */
	public Optional<Settore> findByCodice(String codice, UUID enteId,Boolean valido) {
		return findByCodice( codice,  enteId, valido,"like");
	}

	/**
	 * Finds the settore by its codice
	 * @param codice the codice to filter by
	 * @return the settore
	 */
	public Optional<Settore> findByCodice(String codice, UUID enteId,Boolean valido, String operation) {
		return cpassTSettoreDao.findByCodice(codice, enteId, valido,operation).map(CpassMappers.SETTORE_CUSTOM::toModel);
	}

	/**
	 * Finds the settore by its codice
	 * @param codice the codice to filter by
	 * @return the settore
	 */
	public Optional<Settore> findByCodiceObbligatorio(String codice, UUID enteId) {
		return findByCodiceObbligatorio( codice,  enteId,Boolean.TRUE);
	}

	/**
	 * Finds the settore by its id only if it is not cancelled
	 * <p><strong>NOTE</strong>: is executed in a transaction even if the calling method is non-transactional
	 * @param id the id to filter by
	 * @return the settore
	 */
	@Transactional(value = TxType.REQUIRED)
	public Settore findOne(UUID id) {
		final Optional<CpassTSettore> optional = cpassTSettoreDao.findOne(id);
		if (optional.isEmpty()) {
			return null;
		}
		final CpassTSettore settore = optional.get();
		return CpassMappers.SETTORE_CUSTOM.toModel(settore);
	}

	/**
	 * Finds the settore by its id
	 * <p><strong>NOTE</strong>: is executed in a transaction even if the calling method is non-transactional
	 * @param id the id to filter by
	 * @return the settore
	 */
	@Transactional(value = TxType.REQUIRED)
	public Settore findById(UUID id) {
		final Optional<CpassTSettore> optional = cpassTSettoreDao.findById(id);
		if (optional.isEmpty()) {
			return null;
		}
		final CpassTSettore settore = optional.get();
		return CpassMappers.SETTORE_CUSTOM.toModel(settore);
	}

	/**
	 *
	 * @param id
	 * @return
	 */
	public Optional<Settore> findByIdValid(UUID idSettore) {
		final Optional<Settore> optional = cpassTSettoreDao.findOne(idSettore).map(CpassMappers.SETTORE_CUSTOM::toModel);
		return optional;
	}

	/**
	 * @param id
	 * @return
	 */
	public Optional<Settore> findByIdOpt(UUID idSettore) {
		final Optional<Settore> optional = cpassTSettoreDao.findById(idSettore).map(CpassMappers.SETTORE_CUSTOM::toModel);
		return optional;
	}

	/**
	 *
	 * @param settoreId
	 * @return
	 */
	public List<Settore> getMySectorFamily(UUID settoreId) {
		final List<CpassTSettore> listaPadre = cpassTSettoreDao.getSettoriBySettorePadre(settoreId,Boolean.TRUE);
		List<CpassTSettore> listaAllFigli = new ArrayList<>(listaPadre);
		listaAllFigli = getMySectorFamily(listaPadre, listaAllFigli);
		return CpassMappers.SETTORE_CUSTOM.toModels(listaAllFigli);
	}

	/**
	 *
	 * @param listaSettori
	 * @param listaAllFigli
	 * @return
	 */
	private List<CpassTSettore> getMySectorFamily(List<CpassTSettore> listaSettori,List<CpassTSettore> listaAllFigli) {
		final List<CpassTSettore> listaSettoriPadri = listaSettori;
		List<CpassTSettore> listaNewSon = new ArrayList<>();
		for( final CpassTSettore set : listaSettoriPadri) {
			final List<CpassTSettore> sons = cpassTSettoreDao.getSettoriBySettorePadre(set.getSettoreId(),Boolean.TRUE);
			listaNewSon = addSons(listaNewSon,sons);
		}

		listaAllFigli.addAll(listaNewSon);
		if (listaNewSon.size() >0 ) {
			getMySectorFamily(listaNewSon, listaAllFigli);
		}
		return listaAllFigli;
	}

	/**
	 *
	 * @param listaAllFigli
	 * @param listaNewSon
	 * @return
	 */
	private List<CpassTSettore> addSons(List<CpassTSettore> listaAllFigli, List<CpassTSettore> listaNewSon) {
		final List<CpassTSettore> listaAllFigliNew = listaAllFigli;
		for( final CpassTSettore son : listaNewSon) {
			listaAllFigliNew.add(son);
		}
		return listaAllFigliNew;
	}

	/**
	 *
	 * @return List<Settore>
	 */
	public List<Settore> getSettori() {
		final List<CpassTSettore> settore = cpassTSettoreDao.findAll();
		return CpassMappers.SETTORE_CUSTOM.toModels(settore);
	}

	/**
	 *
	 * @param enteId
	 * @return
	 */
	public List<Settore> getSettoriByEnteId(UUID enteId) {
		final List<CpassTSettore> settore = cpassTSettoreDao.getSettoriByEnteId(enteId);
		return CpassMappers.SETTORE_CUSTOM.toModels(settore);
	}

	/**
	 *
	 * @param enteId
	 * @return
	 */
	public List<Settore> getSettoriMinimalByEnteId(UUID enteId) {
		final List<CpassTSettore> settore = cpassTSettoreDao.getSettoriByEnteId(enteId);
		return CpassMappers.SETTORE_CUSTOM_MINIMAL.toModels(settore);
	}

	/**
	 *
	 * @param settore
	 * @return
	 */
	public Settore saveSettore(Settore settore) {
		CpassTSettore cpassTsettore = new CpassTSettore();
		final CpassTSettore entity = CpassMappers.SETTORE_CUSTOM.toEntity(settore);
		cpassTsettore = cpassTSettoreDao.save(entity);
		return CpassMappers.SETTORE_CUSTOM.toModel(cpassTsettore);
	}

	/**
	 *
	 * @param settore
	 * @return
	 */
	public Settore saveSettoreAndFlush(Settore settore) {
		CpassTSettore cpassTsettore = new CpassTSettore();
		cpassTsettore = cpassTSettoreDao.saveAndFlush(CpassMappers.SETTORE_CUSTOM.toEntity(settore));
		return CpassMappers.SETTORE_CUSTOM.toModel(cpassTsettore);
	}

	/////////////////////// SETTORE INDIRIZZO ////////////////////////////////////////////////////
	/**
	 *
	 * @param SettoreIndirizzo ind
	 * @return SettoreIndirizzo
	 */
	public SettoreIndirizzo saveAndFlushSettoreIndirizzo(SettoreIndirizzo ind) {
		if(ind.getEsternoEnte()==null) {
			ind.setEsternoEnte(Boolean.FALSE);
		}
		final CpassTSettoreIndirizzo sett_indir = CpassMappers.SETTORE_INDIRIZZO.toEntity(ind);
		final CpassTSettoreIndirizzo cpassTsettoreIndirizzo = cpassTSettoreIndirizzoDao.saveAndFlush(sett_indir);
		return CpassMappers.SETTORE_INDIRIZZO.toModel(cpassTsettoreIndirizzo);
	}

	/**
	 *
	 * @param SettoreIndirizzo ind
	 * @return SettoreIndirizzo
	 */
	public void chiudiLogicallySettoreIndirizzo(SettoreIndirizzo ind,Settore settore) {
		if(settore.getDataCancellazione() == null) {
			cpassTSettoreIndirizzoDao.deleteLogically(ind.getId());
		}else {
			final CpassTSettoreIndirizzo entity = CpassMappers.SETTORE_INDIRIZZO.toEntity(ind);
			entity.setDataCancellazione(settore.getDataCancellazione());
			entity.setUtenteCancellazione(settore.getUtenteCancellazione());
			cpassTSettoreIndirizzoDao.save(entity);
		}
	}
	/**
	 * 
	 */
	public void settoreIndirizzoflush() {
		cpassTSettoreIndirizzoDao.flush();
	}

	/**
	 *
	 * @param uff
	 * @param settOld
	 */
	public void chiudiRelazioneUfficioSettore(Ufficio uff, Settore sett) {
		final CpassRUfficioSettore rus = cpassRUfficioSettoreDao.findByUffIdSettId(sett.getId(),uff.getId());
		if(sett.getDataCancellazione()==null) {
			rus.setDataValiditaFine(new Date());
		}else {
			rus.setDataValiditaFine(sett.getDataCancellazione());
		}
		cpassRUfficioSettoreDao.save(rus);
		cpassRUfficioSettoreDao.flush();
	}

	/**
	 *
	 * @param cdc
	 * @param settOld
	 */
	public void chiudiRelazioneCdcSettore(Integer cdcId, UUID settId) {
		final List<CpassRSettoreCdc> lista = cpassRSettoreCdcDao.findByCdcIdSettId(settId,cdcId);
		for( final CpassRSettoreCdc rSettCdc : lista) {
			cpassRSettoreCdcDao.deleteLogically(rSettCdc.getId());
			cpassRSettoreCdcDao.flush();
		}
	}

	public void chiudiRelazioneCdcSettore(Integer rSettoreCdcId) {
		final Optional<CpassRSettoreCdc> rSettCdcOpt = cpassRSettoreCdcDao.findById(rSettoreCdcId);
		final CpassRSettoreCdc rSettCdc = rSettCdcOpt.orElseThrow(() -> new NotFoundException("settore"));
		cpassRSettoreCdcDao.deleteLogically(rSettCdc.getId());
		cpassRSettoreCdcDao.flush();
	}
	
	/**
	 * 
	 * @param rSettoreAooActaId
	 */
	public void chiudiRelazioneAooActaSettore(Integer rSettoreAooActaId) {
		final Optional<CpassRSettoreAooActa> rSettAooActaOpt = cpassRSettoreAooActaDao.findOne(rSettoreAooActaId);
		final CpassRSettoreAooActa rSettAooActa = rSettAooActaOpt.orElseThrow(() -> new NotFoundException("settore"));
		rSettAooActa.setDataFineValidita(new Date());
		cpassRSettoreAooActaDao.update(rSettAooActa);
		cpassRSettoreAooActaDao.flush();
	}

	/**
	 *
	 * @param cdc
	 * @param settOld
	 */
	public List<CpassRSettoreCdc> findByCdcIdSettId(Integer cdcId, UUID settId) {
		final List<CpassRSettoreCdc> lista = cpassRSettoreCdcDao.findByCdcIdSettId(settId,cdcId);
		return lista;
	}

	/**
	 *
	 * @param cdc
	 * @param settOld
	 */
	public List<SettoreAooActa> findByAooActaIdSettId(Integer aooActaId, UUID settId) {
		final List<CpassRSettoreAooActa> listaCpassRSettoreAooActa = cpassRSettoreAooActaDao.findByAooActaIdSettId(settId,aooActaId);
		return CpassMappers.SETTORE_AOO_ACTA.toModels(listaCpassRSettoreAooActa);
	}

	/**
	 * Find indirizzi of a Settore
	 * @param settore the settore
	 * @return the model instances
	 */
	public List<SettoreIndirizzo> postRicercaSettoreIndirizzi(Settore settore) {
		final List<CpassTSettoreIndirizzo> list = cpassTSettoreIndirizzoDao.findBySettore(settore);
		return CpassMappers.SETTORE_INDIRIZZO_REVERSE.toModels(list);
	}

	/**
	 *
	 * @param uff
	 * @param settOld
	 * @param settNew
	 */
	public void trasferisciRelazioneUfficioSettore(Ufficio uff, Settore settOld, Settore settNew) {
		final CpassTSettore cpassTSettoreNew = CpassMappers.SETTORE_CUSTOM.toEntity(settNew);
		final CpassRUfficioSettore rus = cpassRUfficioSettoreDao.findByUffIdSettId(settOld.getId(),uff.getId());
		rus.setCpassTSettore(cpassTSettoreNew);
		cpassRUfficioSettoreDao.save(rus);
		cpassRUfficioSettoreDao.flush();
	}

	/**
	 *
	 * @param uff
	 * @param settOld
	 * @param settNew
	 */
	public void insertUfficioSettore(Ufficio uff, Settore sett) {
		final CpassTSettore cpassTSettore = CpassMappers.SETTORE_CUSTOM.toEntity(sett);
		final CpassTUfficio cpassTUfficio= CpassMappers.UFFICIO.toEntity(uff);
		final CpassRUfficioSettore rus = new CpassRUfficioSettore();
		rus.setCpassTSettore(cpassTSettore);
		rus.setCpassTUfficio(cpassTUfficio);
		rus.setDataValiditaInizio(new Date());
		cpassRUfficioSettoreDao.save(rus);
		cpassRUfficioSettoreDao.flush();
	}

	/**
	 *
	 * @param uff
	 * @param settOld
	 * @param settNew
	 */
	public void insertCdcSettore(Cdc cdc, Settore sett) {
		final CpassTSettore cpassTSettore = CpassMappers.SETTORE_CUSTOM.toEntity(sett);
		final CpassTCdc cpassTCdc= CpassMappers.CDC.toEntity(cdc);
		final CpassRSettoreCdc rus = new CpassRSettoreCdc();
		rus.setCpassTSettore(cpassTSettore);
		rus.setCpassTCdc(cpassTCdc);
		cpassRSettoreCdcDao.save(rus);
		cpassRSettoreCdcDao.flush();
	}
	
	/**
	 * 
	 * @param cpassTCdc
	 * @param sett
	 */
	public void insertCdcSettoreXBatch(CpassTCdc cpassTCdc, Settore sett) {
		final CpassTSettore cpassTSettore = CpassMappers.SETTORE_CUSTOM.toEntity(sett);
		final CpassRSettoreCdc rus = new CpassRSettoreCdc();
		rus.setCpassTSettore(cpassTSettore);
		rus.setCpassTCdc(cpassTCdc);
		rus.setUtenteCreazione(CpassEnum.UTENTE_BATCH.getCostante());
		rus.setDataCreazione(new Date());
		rus.setDataModifica(new Date());
		rus.setUtenteModifica(CpassEnum.UTENTE_BATCH.getCostante());
		cpassRSettoreCdcDao.save(rus);
		cpassRSettoreCdcDao.flush();
	}
	
	/**
	 * 
	 * @param aooActa
	 * @param sett
	 */
	public void insertSettoreAooActa(AooActa aooActa, Settore sett) {
		final CpassTSettore cpassTSettore = CpassMappers.SETTORE_CUSTOM.toEntity(sett);
		final CpassDAooActa cpassDAooActa = CpassMappers.AOO_ACTA.toEntity(aooActa);
		final CpassRSettoreAooActa rus = new CpassRSettoreAooActa();
		rus.setCpassTSettore(cpassTSettore);
		rus.setCpassDAooActa(cpassDAooActa);
		cpassRSettoreAooActaDao.save(rus);
		cpassRSettoreCdcDao.flush();
	}
	/////////////////////// FINE SETTORE INDIRIZZO ////////////////////////////////////////////////////

	/////////////////////// SETTORE STORICO ////////////////////////////////////////////////////
	/**
	 *
	 * @param idSettoreAttuale
	 * @param idSettorePrecedente
	 * @return
	 */
	public boolean isSettoreRiorganizzato(UUID idSettoreAttuale, UUID idSettorePrecedente) {
		final Optional<CpassTSettoreStorico> ss = cpassTSettoreStoricoDao.isSettoreRiorganizzato(idSettoreAttuale, idSettorePrecedente);
		if (ss.isPresent()) {
			return Boolean.TRUE;
		}
		return Boolean.FALSE;
	}

	/**
	 *
	 * @param settoreStorico
	 * @return
	 */
	public SettoreStorico saveSettoreStorico(SettoreStorico settoreStorico) {
		final CpassTSettoreStorico entity = CpassMappers.SETTORE_STORICO.toEntity(settoreStorico);
		final CpassTSettoreStorico cpassTsettoreStorico = cpassTSettoreStoricoDao.save(entity);
		return CpassMappers.SETTORE_STORICO.toModel(cpassTsettoreStorico);
	}

	/**
	 *
	 * @param id
	 * @return List<UUID>
	 */
	public List<UUID> getListaSettoriDaRiorganizzazioniPassate(UUID id) {
		final List<CpassTSettoreStorico> listaSettoriStorici = cpassTSettoreStoricoDao.findBySettoriPrecedentiBySettoreAttuale(id);
		final List<UUID> ris = new ArrayList<>();
		for(final CpassTSettoreStorico ss:listaSettoriStorici) {
			ris.add(ss.getSettoreStorico().getId());
		}
		return ris;
	}
	/////////////////////// FINE SETTORE STORICO ////////////////////////////////////////////////////
	/**
	 * 
	 * @param idsettore
	 * @param enteId
	 * @return
	 */
	public Optional<AooActa> getAooByIdSettore(UUID idsettore,UUID enteId) {
		final Optional<CpassDAooActa> entity = cpassDAooActaDao.getAooByIdSettore(idsettore,enteId);
		return entity.map(CpassMappers.AOO_ACTA::toModel);
	}
	
	/**
	 * 
	 * @param settoreId
	 * @return
	 */
	public List<Sezione> getSezioniBySettoreId(UUID settoreId) {
		final List <CpassTOrdSezione> listaEntity =  cpassTOrdSezioneDao.getSezioniBySettoreId(settoreId);
		return CpassMappers.SEZIONE.toModels(listaEntity);
	}
	
	/**
	 * 
	 * @param enteId
	 * @return
	 */
	public List<Cdc> getCdcValidiByEnte(UUID enteId) {
		final List<CpassTCdc> cpassTCdcs = cpassTCdcDao.getCdcValidiByEnte(enteId);
		return CpassMappers.CDC.toModels(cpassTCdcs);

	}
	
	/**
	 * 
	 * @param codiceStrutturaProponente
	 * @param enteId
	 * @return
	 */
	public List<Settore> getSettoriByCdc(String codiceStrutturaProponente,UUID enteId) {
		final List<CpassTSettore> cpassTSettores = cpassTSettoreDao.getSettoriByCdc(codiceStrutturaProponente,enteId);
		return CpassMappers.SETTORE_CUSTOM.toModels(cpassTSettores);
	}
	/**
	 * 
	 * @param id
	 * @return
	 */
	public Optional<Cdc> findCdcById(Integer id) {
		return cpassTCdcDao.findById(id).map(CpassMappers.CDC::toModel);
	}
}
