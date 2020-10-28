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
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;

import it.csi.cpass.cpassbe.ejb.business.be.dad.sort.ProgrammaSort;
import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.CpassDStatoDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.CpassTEnteDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.pba.CpassTPbaProgrammaDao;
import it.csi.cpass.cpassbe.ejb.entity.CpassDStato;
import it.csi.cpass.cpassbe.ejb.entity.CpassTEnte;
import it.csi.cpass.cpassbe.ejb.entity.pba.CpassTPbaProgramma;
import it.csi.cpass.cpassbe.ejb.exception.NotFoundException;
import it.csi.cpass.cpassbe.ejb.mapper.CpassMappers;
import it.csi.cpass.cpassbe.ejb.util.ConstantsCPassStato;
import it.csi.cpass.cpassbe.ejb.util.CpassStatiEnum;
import it.csi.cpass.cpassbe.ejb.util.jpa.Page;
import it.csi.cpass.cpassbe.lib.dto.pba.Intervento;
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
		CpassTPbaProgramma ctpp = cpassTPbaProgrammaDao.findOne(uuid).orElse(null);
		if(ctpp == null) {
			return Optional.ofNullable(null);
		}
		Programma programma = CpassMappers.PROGRAMMA.toModel(ctpp);
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
		Page<CpassTPbaProgramma> cpassTPbaProgrammas = cpassTPbaProgrammaDao.findPaginated(
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
			String cf = cpassTEnteDao.findOne(programma.getEnte().getId()).map(CpassTEnte::getEnteCodiceFiscale).orElse("");
			String anno = String.valueOf(programma.getAnno());
			String progressivo = StringUtils.leftPad(String.valueOf(programma.getVersione()),3,'0');
			String codiceMit = "FS"+cf+anno+ progressivo;
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
		CpassTPbaProgramma cpassTPbaProgramma = CpassMappers.PROGRAMMA.toEntity(programma);
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
		Page<CpassTPbaProgramma> list = cpassTPbaProgrammaDao.findPaginated(programma.getAnno()
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
		CpassTPbaProgramma cpassTPbaProgramma = cpassTPbaProgrammaDao.findOne(id).orElseThrow(() -> new NotFoundException("programma"));
		CpassDStato cpassDStato = cpassDStatoDao.findByCodiceTipo(statoCodice,  statoTipo).orElseThrow(() -> new NotFoundException("stato"));
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
		List<CpassTPbaProgramma> entities = cpassTPbaProgrammaDao.getProgrammiBySettoreAnnoVersioneStato(settoreId, anno, versione,null, solovalidi );
		return CpassMappers.PROGRAMMA.toModels(entities);

	}
	
	
	/**
	 * @param settoreId
	 * @param solovalidi 
	 * @return the programmi by settore
	 */
	public List<Programma> getProgrammiBySettore(UUID settoreId,  Boolean solovalidi) {
		List<CpassTPbaProgramma> entities = cpassTPbaProgrammaDao.getProgrammiBySettoreAnnoVersioneStato(settoreId,null,null,null,solovalidi);
		return CpassMappers.PROGRAMMA.toModels(entities);
	}
	
	/**
	 * 
	 * @param settoreId
	 * @param statoCodice 
	 * @return the programmi by settore and stato
	 */
	public List<Programma> getProgrammiBySettoreAndStato(UUID settoreId,  String statoCodice) {
		List<CpassTPbaProgramma> entities = cpassTPbaProgrammaDao.getProgrammiBySettoreAnnoVersioneStato(settoreId,null,null,statoCodice,Boolean.TRUE);
		return CpassMappers.PROGRAMMA.toModels(entities);
	}
	
	/**
	 * 
	 * @param settoreId
	 * @return the ultimi programmi by settore and stato bozza
	 */
	public List<Programma> getUltimiProgrammiBySettoreAndStatoBozza(UUID settoreId) {
		List<Programma> ris = new ArrayList<>();
		List<CpassTPbaProgramma> entities = cpassTPbaProgrammaDao.getProgrammiBySettoreAnnoVersioneStato(settoreId,null,null,CpassStatiEnum.PRO_BOZZA.getCostante(),Boolean.TRUE);
		List<Programma> listaProgrammaBozza = CpassMappers.PROGRAMMA.toModels(entities);

		int annoApprovato = -1;
		List<CpassTPbaProgramma> listUltimiApprovati = cpassTPbaProgrammaDao.getProgrammiBySettoreAnnoVersioneStato(settoreId, null, null,
				ConstantsCPassStato.StatoEnum.CONFERMATO.getCostante(), Boolean.TRUE);
		if (listUltimiApprovati.size() > 0) {
			annoApprovato = listUltimiApprovati.get(0).getProgrammaAnno().intValue();
		}

		if(listaProgrammaBozza.size()==0 || listaProgrammaBozza.get(0).getAnno().intValue() < annoApprovato) {
			return ris;
		}
		
		int annoInEsame = -1;
		for(Programma programma : listaProgrammaBozza) {
			int annoProgramma = programma.getAnno().intValue();
			if (annoProgramma >=annoApprovato) {
				ris.add(programma);
			}
		}
		return ris;
	}
	
	/**
	 * 
	 * @param settoreId
	 * @param statoCodice 
	 * @return the ultimi programmi by settore and stato
	 */
	public List<Programma> getUltimiProgrammiBySettoreAndStato(UUID settoreId,  String statoCodice) {
		List<Programma> ris = new ArrayList<>();
		List<CpassTPbaProgramma> entities = cpassTPbaProgrammaDao.getProgrammiBySettoreAnnoVersioneStato(settoreId,null,null,statoCodice,Boolean.TRUE);
		List<Programma> lista = CpassMappers.PROGRAMMA.toModels(entities);
		int annoInEsame = -1;
		for(Programma programma : lista) {
			int annoProgramma = programma.getAnno().intValue();
			if(annoInEsame==-1 || annoInEsame == annoProgramma) {
				ris.add(programma);
				annoInEsame = annoProgramma;
			}else {
				break;
			}
		}
		return ris;
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
	public List<Programma> getProgrammiByAnnoVersioneEnteStato(Integer anno, Integer versione,UUID enteId, String statoCodice, boolean solovalidi, Sort sort) {
		String sortField = null;
		String sortDirection = null;
		if (sort != null) {
			sortField = ProgrammaSort.byModelName(sort.getField()).getQueryName();
			sortDirection = sort.getOrder().getSortDirection();
		}
		List<CpassTPbaProgramma> entities = cpassTPbaProgrammaDao.getProgrammiByAnnoVersioneEnteStato(anno, versione, enteId, statoCodice, solovalidi, sortField, sortDirection);
		return CpassMappers.PROGRAMMA.toModels(entities);
	}

	/**
	 * 
	 * @return the programmi trasmissione MIT
	 */
	public List<Programma> getProgrammiTrasmissioneMIT() {
		List<CpassTPbaProgramma> entities = cpassTPbaProgrammaDao.getProgrammiTrasmissioneMIT();
		return CpassMappers.PROGRAMMA.toModels(entities);
	}
	
}
