/*-
 * ========================LICENSE_START=================================
 * CPASS BackEnd - EJB submodule
 * %%
 * Copyright (C) 2019 - 2021 CSI Piemonte
 * %%
 * SPDX-FileCopyrightText: Copyright 2019 - 2021 | CSI Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 * =========================LICENSE_END==================================
 */
package it.csi.cpass.cpassbe.ejb.business.be.dad;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import it.csi.cpass.cpassbe.ejb.business.be.dad.sort.RmsDaEvadereSort;
import it.csi.cpass.cpassbe.ejb.business.be.dad.sort.TestataRdaSort;
import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.decodifiche.CpassDStatoDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.ord.CpassTOrdTestataOrdineDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.ord.rda.CpassTOrdRigaRdaDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.ord.rda.CpassTOrdTestataRdaDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.rms.CpassRRmsRigaRdaDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.rms.CpassTRmsRigaRmsDao;
import it.csi.cpass.cpassbe.ejb.entity.CpassDStato;
import it.csi.cpass.cpassbe.ejb.entity.ord.CpassTOrdTestataOrdine;
import it.csi.cpass.cpassbe.ejb.entity.ord.rda.CpassTOrdRigaRda;
import it.csi.cpass.cpassbe.ejb.entity.ord.rda.CpassTOrdTestataRda;
import it.csi.cpass.cpassbe.ejb.entity.rms.CpassRRmsRigaRda;
import it.csi.cpass.cpassbe.ejb.entity.rms.CpassTRmsRigaRms;
import it.csi.cpass.cpassbe.ejb.exception.NotFoundException;
import it.csi.cpass.cpassbe.ejb.mapper.CpassMappers;
import it.csi.cpass.cpassbe.ejb.util.ConstantsCPassStato;
import it.csi.cpass.cpassbe.ejb.util.jpa.Page;
import it.csi.cpass.cpassbe.lib.dto.Settore;
import it.csi.cpass.cpassbe.lib.dto.Stato;
import it.csi.cpass.cpassbe.lib.dto.Utente;
import it.csi.cpass.cpassbe.lib.dto.ord.Sezione;
import it.csi.cpass.cpassbe.lib.dto.ord.TestataOrdine;
import it.csi.cpass.cpassbe.lib.dto.ord.rda.RigaRda;
import it.csi.cpass.cpassbe.lib.dto.ord.rda.TestataRda;
import it.csi.cpass.cpassbe.lib.dto.rms.RigaRms;
import it.csi.cpass.cpassbe.lib.dto.rms.RmsRigaRda;
import it.csi.cpass.cpassbe.lib.dto.rms.RmsStatiRigaRms;
import it.csi.cpass.cpassbe.lib.dto.rms.TestataRms;
import it.csi.cpass.cpassbe.lib.util.pagination.PagedList;
import it.csi.cpass.cpassbe.lib.util.pagination.Sort;
import it.csi.cpass.cpassbe.lib.util.threadlocal.CpassThreadLocalContainer;

/**
 * Data Access Delegate for rda
 */
@ApplicationScoped
public class RdaDad extends BaseDad {

	@Inject private CpassTRmsRigaRmsDao cpassTRmsRigaRmsDao;
	@Inject private CpassDStatoDao cpassDStatoDao;
	@Inject private CpassTOrdTestataRdaDao cpassTOrdTestataRdaDao;
	@Inject private CpassTOrdTestataOrdineDao cpassTOrdTestataOrdineDao;
	@Inject private CpassTOrdRigaRdaDao cpassTOrdRigaRdaDao;
	@Inject private CpassRRmsRigaRdaDao    cpassRRmsRigaRdaDao;
	@Inject private RmsDad rmsDad;
	@Inject private DecodificaDad decodificaDad;
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
	 * @param dataAutorizzazioneDa
	 * @param dataAutorizzazioneA
	 * @param testataRms
	 * @param rigaRms
	 * @param statoRiga
	 * @param sogliaQuantita
	 * @param sezione
	 * @return
	 */
	public PagedList<RigaRms> getRicercaRmsDaEvadere(int page, int size, Sort sort,Integer numeroRmsDa, Integer numeroRmsA,Integer annoRmsDa,Integer annoRmsA,Date dataInserimentoDa,Date dataInserimentoA, Date dataAutorizzazioneDa,Date dataAutorizzazioneA,TestataRms testataRms,RigaRms rigaRms,List<Stato> statoRiga, BigDecimal sogliaQuantita, Sezione sezione) {
		final Utente utenteConnesso = CpassThreadLocalContainer.UTENTE_CONNESSO.get();
		final Settore settoreCorrente = CpassThreadLocalContainer.SETTORE_UTENTE.get();
		String sortField = null;
		String sortDirection = null;
		if (sort != null) {
			if (RmsDaEvadereSort.byModelName(sort.getField())!=null) {
				sortField = RmsDaEvadereSort.byModelName(sort.getField()).getQueryName();
			}
			sortDirection = sort.getOrder().getSortDirection();
		}

		final UUID settoreAcquistoId = CpassThreadLocalContainer.SETTORE_UTENTE.get().getId();
		final List<Integer> statiRigaRmsId = new ArrayList<>();
		if(statoRiga != null) {
			for(final Stato stato:statoRiga) {
				statiRigaRmsId.add(stato.getId());
			}
		}
		final Stato statoTestataRicerca = decodificaDad.getStato(ConstantsCPassStato.StatoRmsEnum.AUTORIZZATA.getCostante(), ConstantsCPassStato.TipoStatoEnum.RMS.getCostante());

		testataRms.setStato(statoTestataRicerca);
		final Page<CpassTRmsRigaRms> cpassTRmsTestataRms = cpassTRmsRigaRmsDao.getRicercaRigheRmsDaEvadere(
				numeroRmsDa,
				numeroRmsA,
				annoRmsDa,
				annoRmsA,
				dataInserimentoDa,
				dataInserimentoA,
				dataAutorizzazioneDa,
				dataAutorizzazioneA,
				getId(testataRms.getSettoreEmittente()),
				getId(testataRms.getSettoreDestinatario()),
				testataRms.getRichiestaMagazzino(),
				getId(testataRms.getStato()),
				statiRigaRmsId,
				sogliaQuantita,
				//sezioneId,
				(sezione != null) ? getId(sezione) : null,
						(rigaRms != null) ? getId(rigaRms.getOggettiSpesa()) : null,
								(rigaRms != null && rigaRms.getOggettiSpesa() != null) ? getId(rigaRms.getOggettiSpesa().getCpv()) : null,
										settoreAcquistoId,
										page,
										size,
										sortField,
										sortDirection,
										false,
										utenteConnesso.getCodiceFiscale()
										,utenteConnesso.getId()
										,settoreCorrente.getId()
										,settoreCorrente.getEnte().getId()
				);
		final PagedList<RigaRms> pagedList = toPagedList(cpassTRmsTestataRms, page, size, CpassMappers.RIGA_RMS_REVERSE::toModel);
		return pagedList;
	}

	/**
	 * Find by id
	 *
	 * @param uuid the uuid
	 * @return the model instance
	 */
	public Optional<TestataRda> getTestataRdaById(UUID uuid) {
		return cpassTOrdTestataRdaDao.findOne(uuid).map(CpassMappers.TESTATA_RDA::toModel);
	}

	/**
	 * Find by id
	 *
	 * @param uuid the uuid
	 * @return the model instance
	 */
	public Optional<RigaRda> getRigaRdaById(UUID uuid) {
		return cpassTOrdRigaRdaDao.findOne(uuid).map(CpassMappers.RIGA_RDA::toModel);
	}
	/**
	 * 
	 * @param testataRdaId
	 * @return
	 */
	public List<TestataOrdine> getTestataOrdineByTestataRdaId(UUID testataRdaId){
		final List<CpassTOrdTestataOrdine> entities = cpassTOrdTestataOrdineDao.findTestataOrdineByRdaId(testataRdaId);
		final List<TestataOrdine> models = CpassMappers.TESTATA_ORDINE.toModels(entities);
		return models;
	}

	/**
	 * Find by anno e numero
	 *
	 * @param anno the anno
	 * @param numero the numero
	 * @return the TestataOrdine instance
	 */
	public Optional<TestataRda> getByAnnoENumero(Integer anno, Integer numero, UUID enteId) {
		final Optional<CpassTOrdTestataRda> optionalTestata = cpassTOrdTestataRdaDao.findByAnnoENumero(anno, numero, enteId);
		return optionalTestata.map(CpassMappers.TESTATA_RDA::toModel);
	}

	/**
	 *
	 * @param rdaNumeroDa
	 * @param rdaNumeroA
	 * @param rdaAnnoDa
	 * @param rdaAnnoA
	 * @param dataInserimentoDa
	 * @param dataInserimentoA
	 * @param testataRda
	 * @param rigaRda
	 * @param settoreDestinatario
	 * @return long
	 */
	public long countRicercaRda(Integer rdaNumeroDa, Integer rdaNumeroA,Integer rdaAnnoDa,Integer rdaAnnoA,Date dataInserimentoDa,Date dataInserimentoA,TestataRda testataRda,RigaRda rigaRda, Settore settoreDestinatario) {
		final long count = cpassTOrdTestataRdaDao.countRicercaRda(
				rdaNumeroDa,
				rdaNumeroA,
				rdaAnnoDa,
				rdaAnnoA,
				dataInserimentoDa,
				dataInserimentoA,
				getId(testataRda.getSettoreEmittente()),
				getId(settoreDestinatario),
				getId(testataRda.getStato()),
				getId(rigaRda.getStato()),
				getId(rigaRda.getOggettiSpesa()),
				getId(rigaRda.getOggettiSpesa().getCpv())
				);

		return count;

	}
	/**
	 *
	 * @param page
	 * @param size
	 * @param sort
	 * @param rdaNumeroDa
	 * @param rdaNumeroA
	 * @param rdaAnnoDa
	 * @param rdaAnnoA
	 * @param dataInserimentoDa
	 * @param dataInserimentoA
	 * @param testataRda
	 * @param rigaRda
	 * @param settoreDestinatario
	 * @return PagedList<TestataRda>
	 */
	public PagedList<TestataRda> getRicercaRda(int page, int size, Sort sort,Integer rdaNumeroDa, Integer rdaNumeroA,Integer rdaAnnoDa,Integer rdaAnnoA,Date dataInserimentoDa,Date dataInserimentoA,TestataRda testataRda,RigaRda rigaRda, Settore settoreDestinatario) {
		final Utente utenteConnesso = CpassThreadLocalContainer.UTENTE_CONNESSO.get();
		final Settore settoreCorrente = CpassThreadLocalContainer.SETTORE_UTENTE.get();
		String sortField = null;
		String sortDirection = null;

		if (sort != null) {
			if (TestataRdaSort.byModelName(sort.getField())!=null) {
				sortField = TestataRdaSort.byModelName(sort.getField()).getQueryName();
			}
			sortDirection = sort.getOrder().getSortDirection();
		}

		final Page<CpassTOrdTestataRda> cpassTOrdTestataRda = cpassTOrdTestataRdaDao.findPaginated(
				rdaNumeroDa,
				rdaNumeroA,
				rdaAnnoDa,
				rdaAnnoA,
				dataInserimentoDa,
				dataInserimentoA,
				getId(testataRda.getSettoreEmittente()),
				getId(settoreDestinatario),
				getId(testataRda.getStato()),
				getId(rigaRda.getStato()),
				getId(rigaRda.getOggettiSpesa()),
				getId(rigaRda.getOggettiSpesa().getCpv()),
				page,
				size,
				sortField,
				sortDirection,
				Boolean.TRUE,
				utenteConnesso.getCodiceFiscale()
				,utenteConnesso.getId()
				,(settoreCorrente!=null ? settoreCorrente.getId() : null)
				,(settoreCorrente!=null ? settoreCorrente.getEnte().getId() : null)
				);
		final PagedList<TestataRda> pagedList = toPagedList(cpassTOrdTestataRda, page, size, CpassMappers.TESTATA_RDA::toModel);
		findTestataRmsForRigaRda(pagedList);
		return pagedList;
	}
	/**
	 * 
	 * @param testataRda
	 * @return
	 */
	public TestataRda insertTestataERigheRda(TestataRda testataRda) {
		CpassTOrdTestataRda entity = CpassMappers.TESTATA_RDA.toEntity(testataRda);
		entity = cpassTOrdTestataRdaDao.save(entity);
		testataRda.setId(entity.getId());
		cpassTOrdTestataRdaDao.flush();
		for (final RigaRda rigaRda : testataRda.getRigaRda()) {
			CpassTOrdRigaRda cpassTOrdRigaRda = CpassMappers.RIGA_RDA.toEntity(rigaRda);
			cpassTOrdRigaRda.setCpassTOrdTestataRda(entity);
			cpassTOrdRigaRda = cpassTOrdRigaRdaDao.insert(cpassTOrdRigaRda);
			// serve settare id modelRiga inserito ?
			cpassTOrdRigaRdaDao.flush();

			// inserisco la relazione tra rms e rda
			for(final RigaRda rigaRdaXRel : rigaRda.getListaRelazioniConRms()) {
				final RigaRms rigaRms=rigaRdaXRel.getRigaRms().get(0);
				///////////////////////
				final CpassTRmsRigaRms cpassTRmsRigaRms = CpassMappers.RIGA_RMS.toEntity(rigaRms);

				//BigDecimal quantitaSuRdaNew = cpassTRmsRigaRms.getQuantitaSuRda().add(rigaRda.getQuantitaSuRdaDaEvadere()!=null ? rigaRda.getQuantitaSuRdaDaEvadere() : BigDecimal.ZERO);
				final BigDecimal quantitaSuRdaNew = cpassTRmsRigaRms.getQuantitaSuRda().add(rigaRdaXRel.getQuantitaSuRdaDaEvadere()!=null ? rigaRdaXRel.getQuantitaSuRdaDaEvadere() : BigDecimal.ZERO);
				cpassTRmsRigaRms.setQuantitaSuRda(quantitaSuRdaNew );

				Optional<CpassDStato> statoRigaRms = cpassDStatoDao.findByCodiceTipo(ConstantsCPassStato.StatoRigaRmsEnum.WIP.getCostante(), ConstantsCPassStato.TipoStatoEnum.RIGA_RMS.getCostante());
				if(BigDecimal.ZERO.equals(quantitaSuRdaNew)) {
					statoRigaRms = cpassDStatoDao.findByCodiceTipo(ConstantsCPassStato.StatoRigaRmsEnum.DAE.getCostante(), ConstantsCPassStato.TipoStatoEnum.RIGA_RMS.getCostante());
				}
				cpassTRmsRigaRms.setCpassDStato(statoRigaRms.orElseThrow(() -> new NotFoundException("stato")));
				cpassTRmsRigaRmsDao.update(cpassTRmsRigaRms);

				// gestione storico stati rms
				final RmsStatiRigaRms statiRigaRms = new RmsStatiRigaRms();
				statiRigaRms.setRigaRms(rigaRms);
				statiRigaRms.setStato(statoRigaRms.orElseThrow(() -> new NotFoundException("stato")).getStatoDescrizione());
				statiRigaRms.setDataModifica(new Date());
				statiRigaRms.setUtenteModifica(cpassTRmsRigaRms.getUtenteModifica());
				rmsDad.insertStatiRigaRms(statiRigaRms);
				/////////////////////////////////////////////////////////

				final CpassRRmsRigaRda cpassRRmsRigaRda = new CpassRRmsRigaRda();
				cpassRRmsRigaRda.setUtenteModifica(CpassThreadLocalContainer.UTENTE_CONNESSO.get().getCodiceFiscale());
				cpassRRmsRigaRda.setDataModifica(new Date());
				cpassRRmsRigaRda.setQuantita(rigaRdaXRel.getQuantitaSuRdaDaEvadere());
				cpassRRmsRigaRda.setCpassTRmsRigaRms(CpassMappers.RIGA_RMS.toEntity(rigaRdaXRel.getRigaRms().get(0)));
				cpassRRmsRigaRda.setCpassTOrdRigaRda(cpassTOrdRigaRda);
				cpassRRmsRigaRdaDao.insert(cpassRRmsRigaRda);
			}
		}
		return testataRda;
	}

	/**
	 * Inserts the testataRda
	 *
	 * @param testataRda the testataRda
	 * @return the model instance
	 */
	public TestataRda insertTestataERigheRda_old(TestataRda testataRda) {
		CpassTOrdTestataRda entity = CpassMappers.TESTATA_RDA.toEntity(testataRda);
		entity = cpassTOrdTestataRdaDao.save(entity);
		testataRda.setId(entity.getId());
		cpassTOrdTestataRdaDao.flush();
		for (final RigaRda rigaRda : testataRda.getRigaRda()) {
			CpassTOrdRigaRda cpassTOrdRigaRda = CpassMappers.RIGA_RDA.toEntity(rigaRda);
			cpassTOrdRigaRda.setCpassTOrdTestataRda(entity);
			cpassTOrdRigaRda = cpassTOrdRigaRdaDao.insert(cpassTOrdRigaRda);
			// serve settare id modelRiga inserito ?
			cpassTOrdRigaRdaDao.flush();

			for (final RigaRms rigaRms : rigaRda.getRigaRms()) {
				final CpassTRmsRigaRms cpassTRmsRigaRms = CpassMappers.RIGA_RMS.toEntity(rigaRms);
				//cpassTRmsRigaRms.setCpassTOrdRigaRda(cpassTOrdRigaRda);
				final BigDecimal quantitaSuRdaNew = cpassTRmsRigaRms.getQuantitaSuRda().add(rigaRda.getQuantitaSuRdaDaEvadere()!=null ? rigaRda.getQuantitaSuRdaDaEvadere() : BigDecimal.ZERO);
				cpassTRmsRigaRms.setQuantitaSuRda(quantitaSuRdaNew );
				Optional<CpassDStato> 	statoRigaRmsOpt = cpassDStatoDao.findByCodiceTipo(ConstantsCPassStato.StatoRigaRmsEnum.WIP.getCostante(), ConstantsCPassStato.TipoStatoEnum.RIGA_RMS.getCostante());
				if(BigDecimal.ZERO.equals(quantitaSuRdaNew)) {
					statoRigaRmsOpt = cpassDStatoDao.findByCodiceTipo(ConstantsCPassStato.StatoRigaRmsEnum.DAE.getCostante(), ConstantsCPassStato.TipoStatoEnum.RIGA_RMS.getCostante());
				}

				if(statoRigaRmsOpt.isPresent()) {
					cpassTRmsRigaRms.setCpassDStato(statoRigaRmsOpt.get());
				}
				cpassTRmsRigaRmsDao.update(cpassTRmsRigaRms);

				// gestione storico stati rms
				final RmsStatiRigaRms statiRigaRms = new RmsStatiRigaRms();
				statiRigaRms.setRigaRms(rigaRms);

				if(statoRigaRmsOpt.isPresent()) {
					statiRigaRms.setStato(statoRigaRmsOpt.get().getStatoDescrizione());
				}
				statiRigaRms.setDataModifica(new Date());
				statiRigaRms.setUtenteModifica(cpassTRmsRigaRms.getUtenteModifica());
				rmsDad.insertStatiRigaRms(statiRigaRms);
				// inserisco la relazione tra rms e rda
				for(final RigaRda rigaRdaXRel : rigaRda.getListaRelazioniConRms()) {
					final CpassRRmsRigaRda cpassRRmsRigaRda = new CpassRRmsRigaRda();
					cpassRRmsRigaRda.setUtenteModifica(cpassTRmsRigaRms.getUtenteModifica());
					cpassRRmsRigaRda.setDataModifica(new Date());
					cpassRRmsRigaRda.setQuantita(rigaRdaXRel.getQuantitaSuRdaDaEvadere());
					cpassRRmsRigaRda.setCpassTRmsRigaRms(CpassMappers.RIGA_RMS.toEntity(rigaRms));
					cpassRRmsRigaRda.setCpassTOrdRigaRda(cpassTOrdRigaRda);
					cpassRRmsRigaRdaDao.insert(cpassRRmsRigaRda);
				}

			}
		}
		return testataRda;
	}
	/**
	 * 
	 * @param idRiga
	 * @return
	 */
	public RigaRda findOneRiga(UUID idRiga) {
		return CpassMappers.RIGA_RDA.toModel(cpassTOrdRigaRdaDao.findOne(idRiga).orElseThrow(() -> new NotFoundException("stato")));
	}
	/**
	 * 
	 * @param rigaRda
	 * @return
	 */
	public RigaRda updateRigaRda(RigaRda rigaRda) {
		final CpassTOrdRigaRda entity = CpassMappers.RIGA_RDA.toEntity(rigaRda);
		return CpassMappers.RIGA_RDA.toModel(cpassTOrdRigaRdaDao.update(entity));
	}
	private void findTestataRmsForRigaRda(PagedList<TestataRda> pagedList) {
		if(pagedList != null && pagedList.getTotalElements() > 0) {
			final List<TestataRda> tmpList = pagedList.getList();
			if(tmpList != null) {
				for(final TestataRda trda : tmpList) {
					final List<RigaRda> tmpRigaList = trda.getRigaRda();
					for(final RigaRda riga: tmpRigaList) {
						if(riga.getRigaRms() != null && riga.getRigaRms().size() > 0) {
							final UUID testataRmsId = riga.getRigaRms().get(0).getTestataRms().getId();
							final TestataRms testataRms = rmsDad.findOneTestata(testataRmsId);
							riga.getRigaRms().get(0).setTestataRms(testataRms);
						}
					}
				}
			}
		}
	}
	/**
	 * 
	 * @param idRiga
	 */
	public void deleteRigaRdaById(UUID idRiga) {
		cpassTOrdRigaRdaDao.delete(idRiga);
	}
	/**
	 * 
	 * @param idRiga
	 */
	public void deleteRRigaRdaRmsByRdaId(UUID idRiga) {
		cpassRRmsRigaRdaDao.deleteByRdaId(idRiga);
	}

	/**
	 * Updates the testataRda
	 *
	 * @param testataRda
	 * @return
	 */
	public TestataRda updateTestataRda(TestataRda testataRda) {
		CpassTOrdTestataRda cpassTOrdTestataRda = CpassMappers.TESTATA_RDA.toEntity(testataRda);
		cpassTOrdTestataRda = cpassTOrdTestataRdaDao.update(cpassTOrdTestataRda);
		return CpassMappers.TESTATA_RDA.toModel(cpassTOrdTestataRda);
	}
	/**
	 * 
	 * @param rigaRmsId
	 * @param testataRdaId
	 * @return
	 */
	public List<RigaRda> getRigaRdaByRmsIdAndTestataRda(UUID rigaRmsId, UUID testataRdaId) {
		final List<CpassTOrdRigaRda> cpassTOrdRigaRda = cpassTOrdRigaRdaDao.getRigaRdaByRmsIdAndTestataRda( rigaRmsId,  testataRdaId);
		return CpassMappers.RIGA_RDA.toModels(cpassTOrdRigaRda);
	}
	/**
	 * 
	 * @param rigaRdaId
	 * @param rigaRmsId
	 * @return
	 */
	public List<RmsRigaRda> getRRmsRdaRmsByRigheRdaRmsId(UUID rigaRdaId, UUID rigaRmsId){
		List<CpassRRmsRigaRda> cpassRRmsRigaRda = new ArrayList<>();
		cpassRRmsRigaRda = cpassRRmsRigaRdaDao.getRRmsRdaRmsByRigheRdaRmsId(rigaRdaId, rigaRmsId);
		return CpassMappers.RMS_RIGA_RDA.toModels(cpassRRmsRigaRda);
	}
}
