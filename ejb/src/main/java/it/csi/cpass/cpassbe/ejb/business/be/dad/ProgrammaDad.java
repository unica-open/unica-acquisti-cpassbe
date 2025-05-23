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

import org.apache.commons.lang3.StringUtils;

import it.csi.cpass.cpassbe.ejb.business.be.dad.sort.ProgrammaSort;
import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.CpassTEnteDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.decodifiche.CpassDStatoDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.pba.CpassTPbaProgrammaDao;
import it.csi.cpass.cpassbe.ejb.entity.CpassDStato;
import it.csi.cpass.cpassbe.ejb.entity.CpassTEnte;
import it.csi.cpass.cpassbe.ejb.entity.pba.CpassTPbaProgramma;
import it.csi.cpass.cpassbe.ejb.exception.NotFoundException;
import it.csi.cpass.cpassbe.ejb.mapper.CpassMappers;
import it.csi.cpass.cpassbe.ejb.util.jpa.Page;
import it.csi.cpass.cpassbe.lib.dto.pba.Programma;
import it.csi.cpass.cpassbe.lib.util.pagination.PagedList;
import it.csi.cpass.cpassbe.lib.util.pagination.Sort;

/**
 * Data Access Delegate for programmas
 */
@ApplicationScoped
public class ProgrammaDad extends BaseDad {

	@Inject private CpassTPbaProgrammaDao cpassTPbaProgrammaDao;
	@Inject private CpassDStatoDao cpassDStatoDao;
	@Inject private CpassTEnteDao cpassTEnteDao;

	/**
	 * Gets the max anno programma
	 * @param anno
	 * @param enteId
	 * @param statoCodice
	 * @return the max statoCodice
	 */
	public Integer getMaxVersioneProgrammaByAnnoEnteStato(Integer anno, UUID enteId, String statoCodice) {
		return getMaxVersioneProgrammaByAnnoEnteStatoValido(anno,  enteId,  statoCodice, Boolean.FALSE);
	}

	/**
	 * Gets the max anno programma
	 * @param anno
	 * @param enteId
	 * @param statoCodice
	 * @return the max statoCodice
	 */
	public Integer getMaxVersioneProgrammaByAnnoEnteStatoValido(Integer anno, UUID enteId, String statoCodice, Boolean solovalidi) {
		return cpassTPbaProgrammaDao.getMaxVersioneProgrammaByAnnoEnteStato(anno, enteId, statoCodice, solovalidi);
	}

	/**
	 * Find by id
	 * @param uuid the uuid
	 * @return the model instance
	 */
	public Optional<Programma> getProgramma(UUID uuid) {
		//		return cpassTPbaProgrammaDao.findOne(uuid).map(CpassMappers.PROGRAMMA::toModel);
		final CpassTPbaProgramma ctpp = cpassTPbaProgrammaDao.findOne(uuid).orElse(null);
		if(ctpp == null) {
			return Optional.ofNullable(null);
		}
		final Programma programma = CpassMappers.PROGRAMMA.toModel(ctpp);
		return Optional.ofNullable(programma);
	}
	/**
	 * Find paginated
	 * @param programma the intervento importi
	 * @param page the page
	 * @param size the size
	 * @return the model instances
	 */
	public PagedList<Programma> getProgrammi(Programma programma, int page, int size) {
		final Page<CpassTPbaProgramma> cpassTPbaProgrammas = cpassTPbaProgrammaDao.findPaginated(
				programma.getAnno(),
				//programma.getReferente(),
				programma.getNumeroProvvedimento(),
				programma.getDescrizioneProvvedimento(),
				programma.getDataProvvedimento(),
				programma.getDataPubblicazione(),
				programma.getUrl(),
				page,
				size);
		return toPagedList(cpassTPbaProgrammas, page, size, CpassMappers.PROGRAMMA::toModel);
	}

	/**
	 * Deletes by uuid
	 * @param uuid the uuid
	 */
	public void deleteProgrammaLogically(UUID uuid) {
		cpassTPbaProgrammaDao.deleteLogically(uuid);
	}

	/**
	 * Deletes by uuid
	 * @param uuid the uuid
	 */
	public void deleteProgramma(UUID uuid) {
		cpassTPbaProgrammaDao.delete(uuid);
	}


	/**
	 * Inserts the programma
	 * @param programma the Programma
	 * @return the model instance
	 */
	public Programma saveProgramma(Programma programma) {

		if (StringUtils.isBlank(programma.getCodiceMit())) {
			// ('FS' + CodiceFiscaleSA(11 caratteri) + anno(4 cifre) + progressivo(3 cifre))
			final String cf = cpassTEnteDao.findOne(programma.getEnte().getId()).map(CpassTEnte::getEnteCodiceFiscale).orElse("");
			final String anno = String.valueOf(programma.getAnno());
			final String progressivo = StringUtils.leftPad(String.valueOf(programma.getVersione()),3,'0');
			final String codiceMit = "FS"+cf+anno+ progressivo;
			programma.setCodiceMit(codiceMit);
		}
		CpassTPbaProgramma cpassTPbaProgramma = CpassMappers.PROGRAMMA.toEntity(programma);
		cpassTPbaProgramma = cpassTPbaProgrammaDao.insert(cpassTPbaProgramma);
		cpassTPbaProgrammaDao.flush();
		programma.setId(cpassTPbaProgramma.getId());

		return programma;
	}


	/**
	 * Updates the programma
	 * @param programma the Programma
	 * @return the model instance
	 */
	public Programma updateProgramma(Programma programma) {
		final CpassTPbaProgramma cpassTPbaProgramma = CpassMappers.PROGRAMMA.toEntity(programma);
		cpassTPbaProgrammaDao.update(cpassTPbaProgramma);
		return programma;
	}

	/**
	 * Find paginated
	 * @param page the page
	 * @param size the size
	 * @param sort
	 * @param programma the programma
	 * @return the model instances
	 */
	public PagedList<Programma> getRicercaProgrammi(int page, int size, Sort sort, Programma programma) {
		final Page<CpassTPbaProgramma> list = cpassTPbaProgrammaDao.findPaginated(programma.getAnno()
				, programma.getNumeroProvvedimento()
				, programma.getDescrizioneProvvedimento()
				, programma.getDataProvvedimento()
				, programma.getDataPubblicazione()
				, programma.getUrl()
				, page
				, size
				//InterventoSort.byModelName(sort.getField()).getQueryName(),
				//sort.getOrder().getSortDirection()
				);
		return toPagedList(list, page, size, CpassMappers.PROGRAMMA::toModel);
	}

	/**
	 * Updates the stato programma
	 * @param id the id
	 * @param statoCodice the stato codice
	 * @param statoTipo the stato tipo
	 */
	public void updateStatoProgramma(UUID id, String statoCodice, String statoTipo) {
		final CpassTPbaProgramma cpassTPbaProgramma = cpassTPbaProgrammaDao.findOne(id).orElseThrow(() -> new NotFoundException("programma"));
		final CpassDStato cpassDStato = cpassDStatoDao.findByCodiceTipo(statoCodice,  statoTipo).orElseThrow(() -> new NotFoundException("stato"));
		cpassTPbaProgramma.setCpassDStato(cpassDStato);
		cpassTPbaProgramma.setDataApprovazione(new Date());
		cpassTPbaProgrammaDao.update(cpassTPbaProgramma);
	}
	/**
	 *
	 * @param settoreId
	 * @param anno
	 * @param versione
	 * @param solovalidi
	 * @return the programmi by settore and anno and versione
	 */
	public List<Programma> getProgrammiBySettoreAnnoVersione(UUID settoreId, Integer anno, Integer versione, Boolean solovalidi ) {
		final List<CpassTPbaProgramma> entities = cpassTPbaProgrammaDao.getProgrammiBySettoreAnnoVersioneStato(settoreId, anno, versione,null, solovalidi );
		List<Programma> listaProgramma = CpassMappers.PROGRAMMA.toModels(entities);
		listaProgramma = aggiungiInfoUltimoBiennio(listaProgramma);
		return  listaProgramma;
	}

	/**
	 * 
	 * @param listaProgramma
	 * @return
	 */
	private List<Programma> aggiungiInfoUltimoBiennio(List<Programma> listaProgramma) {
		final List<Programma> listaArricchita = new ArrayList<>();
		Integer annoPrevius = 0;

		for(int i = 1; i <= listaProgramma.size(); i++) {
			final Programma progr = listaProgramma.get(i-1);
			if(progr.getAnno().equals(annoPrevius)) {
				progr.setIsUltimoBiennio(Boolean.FALSE);
			}else{
				progr.setIsUltimoBiennio(Boolean.TRUE);
			}
			//gestione primo record se lista discendente
			if(annoPrevius.equals(0)) {
				progr.setIsUltimoBiennio(Boolean.TRUE);
			}

			annoPrevius = progr.getAnno();
			listaArricchita.add(progr);
		}
		return listaArricchita;
	}

	/**
	 * @param settoreId
	 * @param solovalidi
	 * @return the programmi by settore
	 */
	public List<Programma> getProgrammiBySettore(UUID settoreId,  Boolean solovalidi) {
		final List<CpassTPbaProgramma> entities = cpassTPbaProgrammaDao.getProgrammiBySettoreAnnoVersioneStato(settoreId,null,null,null,solovalidi);
		List<Programma> listaProgramma = CpassMappers.PROGRAMMA.toModels(entities);
		listaProgramma = aggiungiInfoUltimoBiennio(listaProgramma);
		return  listaProgramma;
	}

	/**
	 *
	 * @param settoreId
	 * @param statoCodice
	 * @return the programmi by settore and stato
	 */
	public List<Programma> getProgrammiBySettoreAndStato(UUID settoreId,  List<String> listaStatoCodice) {
		final List<CpassTPbaProgramma> entities = cpassTPbaProgrammaDao.getProgrammiBySettoreAnnoVersioneStato(settoreId,null,null,listaStatoCodice,Boolean.TRUE);
		return  CpassMappers.PROGRAMMA.toModels(entities);
	}

	/**
	 *
	 * @param anno
	 * @param versione
	 * @param enteId
	 * @param statoCodice
	 * @param solovalidi
	 * @return the programmi by anno, versione, ente, stato
	 */
	public List<Programma> getProgrammiByAnnoVersioneEnteStato(Integer anno, Integer annoFineProgramma, Integer versione,UUID enteId, String statoCodice, boolean solovalidi, Sort sort) {
		String sortField = null;
		String sortDirection = null;
		if (sort != null) {
			sortField = ProgrammaSort.byModelName(sort.getField()).getQueryName();
			sortDirection = sort.getOrder().getSortDirection();
		}
		final List<CpassTPbaProgramma> entities = cpassTPbaProgrammaDao.getProgrammiByAnnoVersioneEnteStato(anno,annoFineProgramma, versione, enteId, statoCodice, solovalidi, sortField, sortDirection);
		return CpassMappers.PROGRAMMA.toModels(entities);
	}

	/**
	 *
	 * @return the programmi trasmissione MIT
	 */
	public List<Programma> getProgrammiTrasmissioneMIT(UUID enteId) {
		final List<CpassTPbaProgramma> entities = cpassTPbaProgrammaDao.getProgrammiTrasmissioneMIT(enteId);
		return  CpassMappers.PROGRAMMA.toModels(entities);
	}
	/**
	 * 
	 * @param enteId
	 * @return
	 */
	public List<Programma> getProgrammiConfermatiDaTrasmettere(UUID enteId) {
		final List<CpassTPbaProgramma> entities = cpassTPbaProgrammaDao.getProgrammiConfermatiDaTrasmettere( enteId);
		return  CpassMappers.PROGRAMMA.toModels(entities);
	}
}
