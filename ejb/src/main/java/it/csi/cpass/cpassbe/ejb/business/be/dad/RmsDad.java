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

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import it.csi.cpass.cpassbe.ejb.business.be.dad.sort.TestataRmsSort;
import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.ord.CpassVRmsRdaOrdineDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.rms.CpassRRmsStatiRigaRmsDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.rms.CpassTRegoleSmistamentoRmsDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.rms.CpassTRmsRigaRmsDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.rms.CpassTRmsTestataRmsDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.view.CpassVRmsDaSmistareDao;
import it.csi.cpass.cpassbe.ejb.entity.CpassTEnte;
import it.csi.cpass.cpassbe.ejb.entity.rms.CpassRRmsStatiRigaRms;
import it.csi.cpass.cpassbe.ejb.entity.rms.CpassTRegoleSmistamentoRms;
import it.csi.cpass.cpassbe.ejb.entity.rms.CpassTRmsRigaRms;
import it.csi.cpass.cpassbe.ejb.entity.rms.CpassTRmsTestataRms;
import it.csi.cpass.cpassbe.ejb.entity.view.CpassVRmsDaSmistare;
import it.csi.cpass.cpassbe.ejb.exception.NotFoundException;
import it.csi.cpass.cpassbe.ejb.mapper.CpassMappers;
import it.csi.cpass.cpassbe.ejb.util.CpassEnum;
import it.csi.cpass.cpassbe.ejb.util.jpa.Page;
import it.csi.cpass.cpassbe.lib.dto.Ente;
import it.csi.cpass.cpassbe.lib.dto.Settore;
import it.csi.cpass.cpassbe.lib.dto.SettoreIndirizzo;
import it.csi.cpass.cpassbe.lib.dto.Stato;
import it.csi.cpass.cpassbe.lib.dto.Utente;
import it.csi.cpass.cpassbe.lib.dto.rms.RegoleSmistamentoRms;
import it.csi.cpass.cpassbe.lib.dto.rms.RigaRms;
import it.csi.cpass.cpassbe.lib.dto.rms.RmsDaSmistare;
import it.csi.cpass.cpassbe.lib.dto.rms.RmsStatiRigaRms;
import it.csi.cpass.cpassbe.lib.dto.rms.TestataRms;
import it.csi.cpass.cpassbe.lib.util.pagination.PagedList;
import it.csi.cpass.cpassbe.lib.util.pagination.Sort;
import it.csi.cpass.cpassbe.lib.util.threadlocal.CpassThreadLocalContainer;

/**
 * Data Access Delegate for testataOrdines
 */
@ApplicationScoped
public class RmsDad extends BaseDad {

	@Inject
	private CpassTRmsTestataRmsDao cpassTRmsTestataRmsDao;
	@Inject
	private CpassTRmsRigaRmsDao cpassTRmsRigaRmsDao;
	@Inject
	private CommonDad commonDad;
	@Inject
	private CpassVRmsDaSmistareDao cpassVRmsDaSmistareDao;
	@Inject
	private CpassTRegoleSmistamentoRmsDao cpassTRegoleSmistamentoRmsDao;
	@Inject
	private CpassRRmsStatiRigaRmsDao cpassRRmsStatiRigaRmsDao;
	@Inject
	private CpassVRmsRdaOrdineDao cpassVRmsRdaOrdineDao;


	/**
	 * Find by id
	 *
	 * @param uuid the uuid
	 * @return the model instance
	 */
	public Optional<TestataRms> getTestataRmsById(UUID uuid) {
		return cpassTRmsTestataRmsDao.findById(uuid).map(CpassMappers.TESTATA_RMS::toModel);
	}

	/**
	 * Find by anno e numero
	 *
	 * @param anno the anno
	 * @param numero the numero
	 * @return the TestataRms instance
	 */
	public Optional<TestataRms> getByAnnoENumero(Integer anno, Integer numero, UUID enteId) {
		final Optional<CpassTRmsTestataRms> optionalTestata = cpassTRmsTestataRmsDao.findByAnnoENumero(anno, numero, enteId);
		return optionalTestata.map(CpassMappers.TESTATA_RMS::toModel);
	}

	/**
	 *
	 * @param rigaRmsId
	 * @return
	 */
	public List<TestataRms> findTestataRmsByRigaRmsId(UUID rigaRmsId) {
		final List<CpassTRmsTestataRms> lista = cpassTRmsTestataRmsDao.findTestataRmsByRigaRmsId(rigaRmsId);
		final List<TestataRms> listaris = CpassMappers.TESTATA_RMS.toModels(lista);
		return listaris;
	}

	/**
	 *
	 * @param testata
	 * @return TestataRms
	 */
	public TestataRms saveTestataRms(TestataRms testata) {
		final CpassTRmsTestataRms entity = CpassMappers.TESTATA_RMS.toEntity(testata);

		final Calendar calendar = Calendar.getInstance();
		final int annoCorrente = calendar.get(Calendar.YEAR);
		entity.setRmsAnno(annoCorrente);

		final String codice = testata.getEnte().getCodice() + "-" + annoCorrente;
		final Integer intProgressivo = commonDad.getProgressivo(CpassEnum.RMS_TESTATA.getCostante(), codice);
		entity.setRmsNumero(intProgressivo);

		final CpassTRmsTestataRms ret = cpassTRmsTestataRmsDao.saveAndFlush(entity);
		return CpassMappers.TESTATA_RMS.toModel(ret);
	}

	/**
	 *
	 * @param testata
	 * @return TestataRms
	 */
	public TestataRms updateTestataRms(TestataRms testata) {
		final CpassTRmsTestataRms entity = CpassMappers.TESTATA_RMS.toEntity(testata);
		final CpassTRmsTestataRms ret = cpassTRmsTestataRmsDao.update(entity);
		return CpassMappers.TESTATA_RMS.toModel(ret);
	}

	/**
	 * Inserts the rigaRms
	 *
	 * @param rigaRms to insert
	 * @return the model instance
	 */

	public RigaRms insertRigaRms(RigaRms rigaRms) {
		final CpassTRmsRigaRms rigaRmsEntity = CpassMappers.RIGA_RMS.toEntity(rigaRms);

		//incremento il progressivo
		if(rigaRms.getProgressivoRiga() == null || rigaRms.getProgressivoRiga().equals(0)) {
			Integer maxProgressivo = 0;
			final List<CpassTRmsRigaRms> righeRms = cpassTRmsRigaRmsDao.findByTestataRms( rigaRms.getTestataRms().getId(),null );
			for(final CpassTRmsRigaRms riga : righeRms) {
				if(riga.getProgressivoRiga() > maxProgressivo) {
					maxProgressivo = riga.getProgressivoRiga();
				}
			}
			maxProgressivo++;
			rigaRmsEntity.setProgressivoRiga(maxProgressivo);
		}
		final CpassTRmsRigaRms cpassTRmsRigaRms = cpassTRmsRigaRmsDao.insert(rigaRmsEntity);
		final RigaRms result = CpassMappers.RIGA_RMS.toModel(cpassTRmsRigaRms);
		return result;
	}

	/**
	 *
	 * @param testataRmsId
	 * @param testataRmsId
	 * @return
	 */
	public List<RigaRms> findRigheByTestataRms(UUID testataRmsId, Integer progessivo) {
		final List<CpassTRmsRigaRms> lista = cpassTRmsRigaRmsDao.findByTestataRms( testataRmsId,  progessivo);
		final List<RigaRms> listaRis = CpassMappers.RIGA_RMS.toModels(lista);
		return listaRis;
	}
	/**
	 * 
	 * @param idTestata
	 * @return
	 */
	public TestataRms findOneTestata(UUID idTestata) {
		final Optional<CpassTRmsTestataRms> testataRmsOptional = cpassTRmsTestataRmsDao.findOne(idTestata);
		if (testataRmsOptional.isPresent()) {
			return CpassMappers.TESTATA_RMS.toModel(testataRmsOptional.get());
		} else {
			throw new NotFoundException("testata rms");
		}
	}
	/**
	 * 
	 * @param idRiga
	 * @return
	 */
	public RigaRms findOneRiga(UUID idRiga) {
		final Optional<CpassTRmsRigaRms> rigaRmsOptional = cpassTRmsRigaRmsDao.findOne(idRiga);
		if (rigaRmsOptional.isPresent()) {
			return CpassMappers.RIGA_RMS.toModel(rigaRmsOptional.get());
		} else {
			throw new NotFoundException("riga rms");
		}
	}

	/**
	 * cancellazioni riga
	 * @param idRiga
	 */
	public void deleteRiga(UUID idRiga) {
		cpassTRmsRigaRmsDao.delete(idRiga);
	}

	/**
	 * cancellazioni riga
	 */
	public void rigaflush() {
		cpassTRmsRigaRmsDao.flush();
	}


	/**
	 * deleteLogicaRiga
	 * @param idRiga
	 */
	public void deleteLogicaRiga(UUID idRiga) {
		cpassTRmsRigaRmsDao.deleteLogically(idRiga);
	}

	/**
	 * deleteRigheByTestataRms
	 * @param idTestataRms
	 */
	public void deleteRigheByTestataRms(UUID idTestataRms) {
		cpassTRmsRigaRmsDao.deleteRigaByTestataRms(idTestataRms);
	}

	/**
	 * deleteLogicaRigheByTestataRms
	 * @param testataRmsId
	 */
	public void deleteLogicaRigheByTestataRms(UUID testataRmsId) {
		final List<CpassTRmsRigaRms> lista = cpassTRmsRigaRmsDao.findByTestataRms( testataRmsId,  null);
		for(final CpassTRmsRigaRms riga: lista) {
			cpassTRmsRigaRmsDao.deleteRigaByTestataRms(riga.getId());
		}
	}

	/**
	 *  cancellazione fisica testata e righe associate
	 * @param testataRmsId
	 */
	public void deleteTestataRms(UUID testataRmsId) {
		// devo prima cancellare la cpass_r_rms_stati_riga_rms
		List<CpassTRmsRigaRms>listaRigaRms = cpassTRmsRigaRmsDao.getRigheRmsByTestataId(testataRmsId);
		for(CpassTRmsRigaRms riga:listaRigaRms) {
			deleteRStatiRigaRmsByRmsId(riga.getId());
		}

		//cancello le righe
		cpassTRmsRigaRmsDao.deleteRigaByTestataRms(testataRmsId);
		cpassTRmsRigaRmsDao.flush();
		//cancello la testata
		cpassTRmsTestataRmsDao.delete(testataRmsId);
		cpassTRmsTestataRmsDao.flush();
	}
	/**
	 * deleteLogicaTestata
	 * @param testataRmsId
	 */
	public void deleteLogicaTestata(UUID testataRmsId) {
		final List<CpassTRmsRigaRms> listaRighe = cpassTRmsRigaRmsDao.findByTestataRms( testataRmsId,  null);
		for(final CpassTRmsRigaRms riga: listaRighe) {
			cpassTRmsRigaRmsDao.deleteLogically(riga.getId());
		}
		cpassTRmsRigaRmsDao.flush();
		cpassTRmsTestataRmsDao.deleteLogically(testataRmsId);
	}
	/**
	 * 
	 * @param page
	 * @param size
	 * @param sort
	 * @param numeroRmsDa
	 * @param numeroRmsA
	 * @param annoRmsDa
	 * @param annoRmsA
	 * @param dataInserimentoDa
	 * @param dataInserimentoA
	 * @param testataRms
	 * @param rigaRms
	 * @param settoreEmittente
	 * @param settore
	 * @param settoreIndirizzo
	 * @param utenteConnesso
	 * @param settoreCorrente
	 * @return
	 */
	public PagedList<TestataRms> getRicercaRms(int page,
			int size,
			Sort sort,
			Integer numeroRmsDa,
			Integer numeroRmsA,
			Integer annoRmsDa,
			Integer annoRmsA,
			Date dataInserimentoDa,
			Date dataInserimentoA,
			TestataRms testataRms,
			RigaRms rigaRms,
			Settore settoreEmittente,
			Settore settore,
			SettoreIndirizzo settoreIndirizzo,
			Utente utenteConnesso,
			Settore settoreCorrente
			) {
		String sortField = null;
		String sortDirection = null;
		if (sort != null) {
			if (TestataRmsSort.byModelName(sort.getField())!=null) {
				sortField = TestataRmsSort.byModelName(sort.getField()).getQueryName();
			}
			sortDirection = sort.getOrder().getSortDirection();
		}
		final Page<CpassTRmsTestataRms> cpassTRmsTestataRms = cpassTRmsTestataRmsDao.findPaginated(
				numeroRmsDa,
				numeroRmsA,
				annoRmsDa,
				annoRmsA,
				dataInserimentoDa,
				dataInserimentoA,
				getId(testataRms.getSettoreEmittente()),
				getId(testataRms.getSettoreDestinatario()),
				testataRms.getRichiestaMagazzino(),
				getId(testataRms.getStato()),
				getId(rigaRms.getStato()),
				getId(rigaRms.getOggettiSpesa()),
				getId(rigaRms.getOggettiSpesa().getCpv()),
				settoreEmittente,
				settore,
				settoreIndirizzo,
				page,
				size,
				sortField,
				sortDirection,
				true,
				utenteConnesso.getCodiceFiscale()
				,utenteConnesso.getId()
				,settoreCorrente.getId()
				,settoreCorrente.getEnte().getId(),
				testataRms
				);
		final PagedList<TestataRms> pagedList = toPagedList(cpassTRmsTestataRms, page, size, CpassMappers.TESTATA_RMS::toModel);
		return pagedList;
	}
	/**
	 * 
	 * @param numeroRmsDa
	 * @param numeroRmsA
	 * @param annoRmsDa
	 * @param annoRmsA
	 * @param dataInserimentoDa
	 * @param dataInserimentoA
	 * @param testataRms
	 * @param rigaRms
	 * @param ente
	 * @param settoreEmittente
	 * @param settore
	 * @param settoreIndirizzo
	 * @return
	 */
	public long countRicercaRms(Integer numeroRmsDa, Integer numeroRmsA,Integer annoRmsDa,Integer annoRmsA,Date dataInserimentoDa,Date dataInserimentoA,TestataRms testataRms,RigaRms rigaRms, Ente ente, Settore settoreEmittente, Settore settore, SettoreIndirizzo settoreIndirizzo) {

		final long count = cpassTRmsTestataRmsDao.countRicercaRms(
				numeroRmsDa,
				numeroRmsA,
				annoRmsDa,
				annoRmsA,
				dataInserimentoDa,
				dataInserimentoA,
				getId(testataRms.getSettoreEmittente()),
				getId(testataRms.getSettoreDestinatario()),
				testataRms.getRichiestaMagazzino(),
				getId(testataRms.getStato()),
				getId(rigaRms.getStato()),
				getId(rigaRms.getOggettiSpesa()),
				getId(rigaRms.getOggettiSpesa().getCpv()),
				settoreEmittente,
				settore,
				settoreIndirizzo,
				getId(ente),
				testataRms
				);
		return count;
	}
	/**
	 * 
	 * @param rigaRms
	 * @return
	 */
	public RigaRms updateRigaRms(RigaRms rigaRms) {
		final CpassTRmsRigaRms rigaRmsEntity = CpassMappers.RIGA_RMS.toEntity(rigaRms);
		//TODO da rifare il taccone qui di sotto probabile sia da farsi un'intervento a fe
		if(rigaRmsEntity.getCpassTOrdSezione()!=null && rigaRmsEntity.getCpassTOrdSezione().getId() == null) {
			rigaRmsEntity.setCpassTOrdSezione(null);
		}
		final RigaRms model = CpassMappers.RIGA_RMS.toModel(cpassTRmsRigaRmsDao.update(rigaRmsEntity));
		cpassTRmsRigaRmsDao.flush();
		return model;
	}
	/**
	 * 
	 * @param enteId
	 * @return
	 */
	public List<RmsDaSmistare> getRmsDaSmistareAll(UUID enteId) {
		final CpassVRmsDaSmistare cpassVRmsDaSmistare = new CpassVRmsDaSmistare();
		cpassVRmsDaSmistare.setEnteId(enteId);
		final List<CpassVRmsDaSmistare> entity = cpassVRmsDaSmistareDao.getRmsDaSmistare(cpassVRmsDaSmistare );
		final List<RmsDaSmistare> lista = CpassMappers.RMS_DA_SMISTARE.toModels(entity);
		return lista;
	}
	/**
	 *
	 * @param enteId
	 * @param oggettiSpesaCodice
	 * @param cpvCodice
	 * @param livelloCpv
	 * @return
	 */
	public List<RegoleSmistamentoRms> getRegoleSmistamentoRms(	UUID enteId,
			String oggettiSpesaCodice,
			String cpvCodice,
			String livelloCpv) {
		final CpassVRmsDaSmistare cpassVRmsDaSmistare = new CpassVRmsDaSmistare();
		cpassVRmsDaSmistare.setEnteId(enteId);
		final CpassTRegoleSmistamentoRms cpassTRegoleSmistamentoRms = new CpassTRegoleSmistamentoRms();
		final CpassTEnte cpassTEnte = new CpassTEnte();
		cpassTEnte.setEnteId(enteId);
		cpassTRegoleSmistamentoRms.setCpassTEnte(cpassTEnte );
		cpassTRegoleSmistamentoRms.setOggettiSpesaCodice(oggettiSpesaCodice);
		cpassTRegoleSmistamentoRms.setCpvCodice(cpvCodice);
		cpassTRegoleSmistamentoRms.setLivelloCpv(livelloCpv);
		final List<CpassTRegoleSmistamentoRms> entity = cpassTRegoleSmistamentoRmsDao.getRegoleSmistamentoRms(cpassTRegoleSmistamentoRms, enteId);
		final List<RegoleSmistamentoRms> lista = CpassMappers.Regole_Smistamento_Rms.toModels(entity);
		return lista;
	}
	/**
	 * 
	 * @param regoleSmistamentoRms
	 * @return
	 */
	public RegoleSmistamentoRms saveRegoleSmistamentoRms(RegoleSmistamentoRms regoleSmistamentoRms) {
		CpassTRegoleSmistamentoRms cpassTRegoleSmistamentoRms = CpassMappers.Regole_Smistamento_Rms.toEntity(regoleSmistamentoRms);
		cpassTRegoleSmistamentoRms = cpassTRegoleSmistamentoRmsDao.save(cpassTRegoleSmistamentoRms);
		regoleSmistamentoRms.setId(cpassTRegoleSmistamentoRms.getId());
		return regoleSmistamentoRms;
	}
	/**
	 * 
	 * @param rigaRmsId
	 * @return
	 */
	public RigaRms getRigaRmsById(UUID rigaRmsId) {
		final Optional<CpassTRmsRigaRms> riga = cpassTRmsRigaRmsDao.findById(rigaRmsId);
		if (riga.isPresent()) {
			return CpassMappers.RIGA_RMS.toModel(riga.get());
		} else {
			throw new NotFoundException("riga");
		}
	}

	/**
	 * 
	 * @param enteId
	 */
	public void cancellaRegoleSmistamentoRms(UUID enteId) {
		cpassTRegoleSmistamentoRmsDao.cancellaRegoleSmistamentoRms(enteId);
	}
	/**
	 * 
	 * @param rigaRdaId
	 * @return
	 */
	public List<RigaRms> getRigaRmsByRigaRdaId(UUID rigaRdaId) {
		final List<CpassTRmsRigaRms> lista = cpassTRmsRigaRmsDao.getRigaRmsByRigaRdaId(rigaRdaId);
		final List<RigaRms> listaris = CpassMappers.RIGA_RMS.toModels(lista);
		return listaris;

	}
	/**
	 * 
	 * @param rdaId
	 * @return
	 */
	public List<RigaRms> getRigheRmsByRdaId(UUID rdaId) {
		final List<CpassTRmsRigaRms> lista = cpassTRmsRigaRmsDao.getRigheRmsByRdaId(rdaId);
		final List<RigaRms> listaris = CpassMappers.RIGA_RMS.toModels(lista);
		return listaris;
	}
	/**
	 * 
	 * @param page
	 * @param size
	 * @param sort
	 * @param numeroRmsDa
	 * @param numeroRmsA
	 * @param annoRmsDa
	 * @param annoRmsA
	 * @param dataInserimentoDa
	 * @param dataInserimentoA
	 * @param testataRms
	 * @param rigaRms
	 * @param ente
	 * @return
	 */
	public PagedList<RigaRms> getRicercaRmsDaSmistare(int page,
			int size,
			Sort sort,
			Integer numeroRmsDa,
			Integer numeroRmsA,
			Integer annoRmsDa,
			Integer annoRmsA,
			Date dataInserimentoDa,
			Date dataInserimentoA,
			TestataRms testataRms,
			RigaRms rigaRms,
			Ente ente
			) {

		final Utente utenteConnesso   = CpassThreadLocalContainer.UTENTE_CONNESSO.get();
		final Settore settoreCorrente = CpassThreadLocalContainer.SETTORE_UTENTE.get();
		String sortField = null;
		String sortDirection = null;

		if (sort != null) {
			if (TestataRmsSort.byModelName(sort.getField())!=null) {
				sortField = TestataRmsSort.byModelName(sort.getField()).getQueryName();
			}
			sortDirection = sort.getOrder().getSortDirection();
		}
		final UUID settoreAcquistoId = null;
		final Boolean flgNonCompetenza = Boolean.TRUE;
		final List<Integer> statiRigaRmsId = new ArrayList<>();
		for(final Stato stato : rigaRms.getStatiRigaRms()) {
			statiRigaRmsId.add(stato.getId());
		}

		final Page<CpassTRmsRigaRms> cpassTRmsTestataRms = cpassTRmsRigaRmsDao.getRicercaRigheRms(
				numeroRmsDa,
				numeroRmsA,
				annoRmsDa,
				annoRmsA,
				dataInserimentoDa,
				dataInserimentoA,
				getId(testataRms.getSettoreEmittente()),
				getId(testataRms.getSettoreDestinatario()),
				testataRms.getRichiestaMagazzino(),
				getId(testataRms.getStato()),
				statiRigaRmsId,
				getId(rigaRms.getOggettiSpesa()),

				(rigaRms != null && rigaRms.getOggettiSpesa() != null) ? getId(rigaRms.getOggettiSpesa().getCpv()) : null,
						settoreAcquistoId,
						page,
						size,
						sortField,
						sortDirection,
						false,
						utenteConnesso.getCodiceFiscale()
						,utenteConnesso.getId()
						,(settoreCorrente!=null ? settoreCorrente.getId() : null),
						getId(ente),
						flgNonCompetenza
				);
		final PagedList<RigaRms> pagedList = toPagedList(cpassTRmsTestataRms, page, size, CpassMappers.RIGA_RMS_REVERSE::toModel);
		return pagedList;
	}
	/**
	 * 
	 * @param rdaId
	 * @return
	 */
	public List<RigaRms> getRigheRmsByRdaIdReverse(UUID rdaId) {
		final List<CpassTRmsRigaRms> lista = cpassTRmsRigaRmsDao.getRigheRmsByRdaId(rdaId);
		final List<RigaRms> listaris = CpassMappers.RIGA_RMS_REVERSE.toModels(lista);
		return listaris;
	}
	/**
	 * 
	 * @param statiRigaRms
	 * @return
	 */
	public RmsStatiRigaRms insertStatiRigaRms(RmsStatiRigaRms statiRigaRms) {
		final CpassRRmsStatiRigaRms statiRigaRmsEntity = CpassMappers.STATI_RIGA_RMS.toEntity(statiRigaRms);
		final CpassRRmsStatiRigaRms ris = cpassRRmsStatiRigaRmsDao.insert(statiRigaRmsEntity);
		return CpassMappers.STATI_RIGA_RMS.toModel(ris);
	}
	/**
	 * 
	 * @param rigaRmsId
	 * @return
	 */
	public BigDecimal getQuantitaNonEvasaSuOrdine(UUID rigaRmsId) {
		final BigDecimal ris = cpassVRmsRdaOrdineDao.getQuantitaNonEvasaSuOrdine(rigaRmsId);
		return ris ;
	}
	/**
	 * 
	 * @param rigaRmsId
	 * @param testataOrdineId
	 * @return
	 */
	public BigDecimal getQuantitaEvasaSuAltriOrdini(UUID rigaRmsId,UUID testataOrdineId) {
		final BigDecimal ris = cpassVRmsRdaOrdineDao.getQuantitaEvasaSuAltriOrdini(rigaRmsId,testataOrdineId);
		return ris ;
	}
	/**
	 * 
	 * @param rigaRmsId
	 */
	public void deleteRStatiRigaRmsByRmsId(UUID rigaRmsId) {
		cpassRRmsStatiRigaRmsDao.deleteByRmsId(rigaRmsId);
	}
}
