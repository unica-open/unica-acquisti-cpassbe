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
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import it.csi.cpass.cpassbe.ejb.business.be.dad.sort.TestataEvasioneSort;
import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.CpassTSettoreDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.ord.evasione.CpassTOrdDestinatarioEvasioneDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.ord.evasione.CpassTOrdRigaEvasioneDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.ord.evasione.CpassTOrdTestataEvasioneDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.ord.evasione.CpassVRiepilogoFatturaEvasioneDao;
import it.csi.cpass.cpassbe.ejb.entity.CpassTEnte;
import it.csi.cpass.cpassbe.ejb.entity.CpassTSettore;
import it.csi.cpass.cpassbe.ejb.entity.ord.evasione.CpassTOrdDestinatarioEvasione;
import it.csi.cpass.cpassbe.ejb.entity.ord.evasione.CpassTOrdRigaEvasione;
import it.csi.cpass.cpassbe.ejb.entity.ord.evasione.CpassTOrdTestataEvasione;
import it.csi.cpass.cpassbe.ejb.entity.view.CpassVRiepilogoFatturaEvasione;
import it.csi.cpass.cpassbe.ejb.exception.NotFoundException;
import it.csi.cpass.cpassbe.ejb.mapper.CpassMappers;
import it.csi.cpass.cpassbe.ejb.util.CpassEnum;
import it.csi.cpass.cpassbe.ejb.util.jpa.Page;
import it.csi.cpass.cpassbe.lib.dto.Impegno;
import it.csi.cpass.cpassbe.lib.dto.Ods;
import it.csi.cpass.cpassbe.lib.dto.Settore;
import it.csi.cpass.cpassbe.lib.dto.Subimpegno;
import it.csi.cpass.cpassbe.lib.dto.Utente;
import it.csi.cpass.cpassbe.lib.dto.ord.evasione.DestinatarioEvasione;
import it.csi.cpass.cpassbe.lib.dto.ord.evasione.RiepilogoFatturaEvasione;
import it.csi.cpass.cpassbe.lib.dto.ord.evasione.TestataEvasione;
import it.csi.cpass.cpassbe.lib.util.pagination.PagedList;
import it.csi.cpass.cpassbe.lib.util.pagination.Sort;
import it.csi.cpass.cpassbe.lib.util.threadlocal.CpassThreadLocalContainer;

/**
 * Data Access Delegate for testataEvasiones
 */
@ApplicationScoped
public class TestataEvasioneDad extends BaseDad {

	@Inject
	private CpassTOrdTestataEvasioneDao cpassTOrdTestataEvasioneDao;
	@Inject
	private CpassTOrdDestinatarioEvasioneDao cpassTOrdDestinatarioDao;
	@Inject
	private CpassTSettoreDao cpassTSettoreDao;
	@Inject
	private CpassVRiepilogoFatturaEvasioneDao cpassVRiepilogoFatturaEvasioneDao;
	@Inject
	private CommonDad commonDad;
	@Inject
	private CpassTOrdRigaEvasioneDao cpassTOrdRigaEvasioneDao;

	/**
	 * Find by id
	 *
	 * @param uuid the uuid
	 * @return the model instance
	 */
	public Optional<TestataEvasione> getTestataEvasione(UUID uuid) {
		return cpassTOrdTestataEvasioneDao.findOne(uuid).map(CpassMappers.TESTATA_EVASIONE::toModel);
	}

	/**
	 * Find by anno e numero
	 *
	 * @param anno   the anno
	 * @param numero the numero
	 * @return the TestataEvasione instance
	 */
	public TestataEvasione getByAnnoENumero(Integer anno, Integer numero, UUID enteId) {
		final Optional<CpassTOrdTestataEvasione> optionalTestata = cpassTOrdTestataEvasioneDao.findByAnnoENumero(anno, numero, enteId);
		final TestataEvasione testataEvasione = optionalTestata.map(CpassMappers.TESTATA_EVASIONE::toModel).orElseThrow(() -> new NotFoundException("testataEvasione"));
		return testataEvasione;
	}

	/**
	 * Find by id
	 *
	 * @param uuid the uuid
	 * @return the model instance
	 */
	public TestataEvasione getTestataEvasioneModel(UUID uuid) {
		final Optional<CpassTOrdTestataEvasione> optionalTestata = cpassTOrdTestataEvasioneDao.findOne(uuid);
		final TestataEvasione testataEvasione = optionalTestata.map(CpassMappers.TESTATA_EVASIONE::toModel).orElseThrow(() -> new NotFoundException("testataEvasione"));
		// destinatari evasione
		final List<CpassTOrdDestinatarioEvasione> destinatariEntities = cpassTOrdDestinatarioDao.findByTestataEvasione(testataEvasione.getId());
		final List<DestinatarioEvasione> destinatarioEvasiones = CpassMappers.DESTINATARIO_EVASIONE.toModels(destinatariEntities);
		testataEvasione.setDestinatarioEvasiones(destinatarioEvasiones);
		return testataEvasione;
	}

	/**
	 * Deletes by uuid
	 *
	 * @param uuid the uuid
	 */
	public void deleteTestataEvasione(UUID uuid) {
		cpassTOrdTestataEvasioneDao.delete(uuid);
	}
	/**
	 * 
	 * @param uuid
	 */
	public void deleteLogicallyTestataEvasione(UUID uuid) {
		cpassTOrdTestataEvasioneDao.deleteLogically(uuid);
	}

	/**
	 * Inserts the testataEvasione
	 *
	 * @param testataEvasione the testataEvasione
	 * @return the model instance
	 */
	public TestataEvasione saveTestataEvasione(TestataEvasione testataEvasione) {
		CpassTOrdTestataEvasione cpassTOrdTestataEvasione = CpassMappers.TESTATA_EVASIONE.toEntity(testataEvasione);
		// anno
		final Calendar calendar = Calendar.getInstance();
		final int annoCorrente = calendar.get(Calendar.YEAR);
		cpassTOrdTestataEvasione.setEvasioneAnno(annoCorrente);
		// numero
		final Optional<CpassTSettore> optionalSettore = cpassTSettoreDao.findOne(testataEvasione.getSettore().getId());
		final CpassTEnte cpassTEnte = optionalSettore.orElseThrow(() -> new NotFoundException("ente")).getCpassTEnte();
		final String codice = cpassTEnte.getEnteCodice() + "-" + annoCorrente;
		final Integer intProgressivo = commonDad.getProgressivo(CpassEnum.EVASIONE_TESTATA.getCostante(), codice, cpassTEnte);
		cpassTOrdTestataEvasione.setEvasioneNumero(intProgressivo);
		// date
		cpassTOrdTestataEvasione.setDataInserimento(calendar.getTime());
		cpassTOrdTestataEvasione = cpassTOrdTestataEvasioneDao.saveAndFlush(cpassTOrdTestataEvasione);
		testataEvasione = CpassMappers.TESTATA_EVASIONE.toModel(cpassTOrdTestataEvasione);
		return testataEvasione;
	}

	/**
	 * Updates the testataEvasione
	 *
	 * @param testataEvasione
	 * @return
	 */
	public TestataEvasione updateTestataEvasione(TestataEvasione testataEvasione) {
		CpassTOrdTestataEvasione cpassTOrdTestataEvasione = CpassMappers.TESTATA_EVASIONE.toEntity(testataEvasione);
		cpassTOrdTestataEvasione = cpassTOrdTestataEvasioneDao.update(cpassTOrdTestataEvasione);
		return CpassMappers.TESTATA_EVASIONE.toModel(cpassTOrdTestataEvasione);
	}
	/**
	 * 
	 * @param testataEvasioneId
	 * @return
	 */
	public List<RiepilogoFatturaEvasione> getRiepilogoFatturaByIdEvasione(UUID testataEvasioneId) {
		final List<CpassVRiepilogoFatturaEvasione> lista = cpassVRiepilogoFatturaEvasioneDao.getRiepilogoFatturaByIdEvasione(testataEvasioneId);
		final List<RiepilogoFatturaEvasione> ris = CpassMappers.RIEPILOGO_FATTURA.toModels(lista);
		return ris;
	}
	/**
	 * 
	 * @param numeroEvasioneDa
	 * @param annoEvasioneDa
	 * @param numeroEvasioneA
	 * @param annoEvasioneA
	 * @param dataInserimentoDa
	 * @param dataInserimentoA
	 * @param annoOrdineDa
	 * @param numeroOrdineDa
	 * @param annoOrdineA
	 * @param numeroOrdineA
	 * @param dataEmissioneDa
	 * @param dataEmissioneA
	 * @param annoProvvedimento
	 * @param numeroProvvedimento
	 * @param provvedimentoTipo
	 * @param testataEvasione
	 * @param destinatario
	 * @param impegno
	 * @param subimpegno
	 * @param oggetiSpesa
	 * @return
	 */
	public long countRicercaEvasioni(
			Integer numeroEvasioneDa, Integer annoEvasioneDa, Integer numeroEvasioneA, Integer annoEvasioneA, Date dataInserimentoDa, Date dataInserimentoA,
			Integer annoOrdineDa, Integer numeroOrdineDa, Integer annoOrdineA, Integer numeroOrdineA, Date dataEmissioneDa, Date dataEmissioneA, Integer annoProvvedimento, String numeroProvvedimento,
			String provvedimentoTipo, TestataEvasione testataEvasione, DestinatarioEvasione destinatario, Impegno impegno, Subimpegno subimpegno, Ods oggetiSpesa) {

		String strProvvedimentoTipo = null;
		if(provvedimentoTipo != null) {
			strProvvedimentoTipo = provvedimentoTipo;
		}
		final long count = cpassTOrdTestataEvasioneDao.countRicercaEvasioni(
				numeroEvasioneDa,
				annoEvasioneDa,
				numeroEvasioneA,
				annoEvasioneA,
				dataInserimentoDa,
				dataInserimentoA,
				annoOrdineDa,
				numeroOrdineDa,
				annoOrdineA,
				numeroOrdineA,
				dataEmissioneDa,
				dataEmissioneA,
				getId(testataEvasione.getTipoEvasione()),
				getId(testataEvasione.getStato()),
				getId(testataEvasione.getSettore()),
				getId(destinatario),
				getId(testataEvasione.getFornitore()),
				annoProvvedimento,
				numeroProvvedimento,
				strProvvedimentoTipo,
				getId(impegno),
				getId(subimpegno),
				getId(oggetiSpesa));

		return count;
	}
	
	/**
	 * 
	 * @param page
	 * @param size
	 * @param sort
	 * @param numeroEvasioneDa
	 * @param annoEvasioneDa
	 * @param numeroEvasioneA
	 * @param annoEvasioneA
	 * @param dataInserimentoDa
	 * @param dataInserimentoA
	 * @param annoOrdineDa
	 * @param numeroOrdineDa
	 * @param annoOrdineA
	 * @param numeroOrdineA
	 * @param dataEmissioneDa
	 * @param dataEmissioneA
	 * @param annoProvvedimento
	 * @param numeroProvvedimento
	 * @param provvedimentoTipo
	 * @param testataEvasione
	 * @param destinatario
	 * @param impegno
	 * @param subimpegno
	 * @param oggetiSpesa
	 * @return
	 */
	public PagedList<TestataEvasione> getRicercaEvasioni(int page, int size, Sort sort,
			Integer numeroEvasioneDa, Integer annoEvasioneDa, Integer numeroEvasioneA, Integer annoEvasioneA, Date dataInserimentoDa, Date dataInserimentoA,
			Integer annoOrdineDa, Integer numeroOrdineDa, Integer annoOrdineA, Integer numeroOrdineA, Date dataEmissioneDa, Date dataEmissioneA, Integer annoProvvedimento, String numeroProvvedimento,
			String provvedimentoTipo, TestataEvasione testataEvasione, DestinatarioEvasione destinatario, Impegno impegno, Subimpegno subimpegno, Ods oggetiSpesa) {

		final Utente utenteConnesso = CpassThreadLocalContainer.UTENTE_CONNESSO.get();
		final Settore settoreCorrente = CpassThreadLocalContainer.SETTORE_UTENTE.get();

		String sortField = null;
		String sortDirection = null;

		if (sort != null) {
			if (TestataEvasioneSort.byModelName(sort.getField())!=null) {
				sortField = TestataEvasioneSort.byModelName(sort.getField()).getQueryName();
			}
			sortDirection = sort.getOrder().getSortDirection();
		}

		String strProvvedimentoTipo = null;
		if(provvedimentoTipo != null) {
			strProvvedimentoTipo = provvedimentoTipo;
		}
		final Page<CpassTOrdTestataEvasione> cpassTOrdTestataEvasiones = cpassTOrdTestataEvasioneDao.findPaginated(
				numeroEvasioneDa,
				annoEvasioneDa,
				numeroEvasioneA,
				annoEvasioneA,
				dataInserimentoDa,
				dataInserimentoA,
				annoOrdineDa,
				numeroOrdineDa,
				annoOrdineA,
				numeroOrdineA,
				dataEmissioneDa,
				dataEmissioneA,
				getId(testataEvasione.getTipoEvasione()),
				getId(testataEvasione.getStato()),
				getId(testataEvasione.getSettore()),
				getId(destinatario),
				getId(testataEvasione.getFornitore()),
				annoProvvedimento,
				numeroProvvedimento,
				strProvvedimentoTipo,
				getId(impegno),
				getId(subimpegno),
				getId(oggetiSpesa),
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

		final PagedList<TestataEvasione> pagedList = toPagedList(cpassTOrdTestataEvasiones, page, size, CpassMappers.TESTATA_EVASIONE::toModel);
		return pagedList;
	}
	/**
	 * 
	 * @param fatturaAnno
	 * @param fatturaNumero
	 * @param fatturaTipo
	 * @param fatturaCodice
	 * @return
	 */
	public List<TestataEvasione> findEvasioniByFattura(Integer fatturaAnno, String fatturaNumero, String fatturaTipo, String fatturaCodice) {
		final List<CpassTOrdTestataEvasione> listEvasiones = cpassTOrdTestataEvasioneDao.findTestateEvasioniByFattura(fatturaAnno, fatturaNumero, fatturaTipo, fatturaCodice);
		return listEvasiones != null ? CpassMappers.TESTATA_EVASIONE.toModels(listEvasiones) : null;
	}
	/**
	 * 
	 * @param fatturaAnno
	 * @param fatturaNumero
	 * @param fatturaTipo
	 * @param fatturaCodice
	 * @return
	 */
	public List<TestataEvasione> findEvasioniByFatturaModel(Integer fatturaAnno, String fatturaNumero, String fatturaTipo, String fatturaCodice) {
		final List<CpassTOrdTestataEvasione> listEvasiones = cpassTOrdTestataEvasioneDao.findTestateEvasioniByFattura(fatturaAnno, fatturaNumero, fatturaTipo, fatturaCodice);
		final List<TestataEvasione> listModels =  listEvasiones != null ? CpassMappers.TESTATA_EVASIONE.toModels(listEvasiones) : null;
		for(final TestataEvasione testataEvasione: listModels) {
			final List<CpassTOrdDestinatarioEvasione> destinatariEntities = cpassTOrdDestinatarioDao.findByTestataEvasione(testataEvasione.getId());
			final List<DestinatarioEvasione> destinatarioEvasiones = CpassMappers.DESTINATARIO_EVASIONE.toModels(destinatariEntities);
			testataEvasione.setDestinatarioEvasiones(destinatarioEvasiones);
		}
		return listModels;
	}
	
	/**
	 * 
	 * @param idDest
	 * @return
	 */
	public TestataEvasione getTestataEvasioneByDestinatario(UUID idDest) {
		final Optional<CpassTOrdTestataEvasione> optionalTestata = cpassTOrdTestataEvasioneDao.getTestataEvasioneByDestinatario(idDest);
		final TestataEvasione testataEvasione = optionalTestata.map(CpassMappers.TESTATA_EVASIONE::toModel).orElseThrow(() -> new NotFoundException("testataEvasione"));
		return testataEvasione;
	}

	/**
	 * Aggiorna il totale dell'evasione sommando gli importi letti sulle sue righe
	 * @param testataEvasione
	 * @return testataEvasione aggiornata
	 */
	public TestataEvasione aggiornaTotaleEvasione (TestataEvasione testataEvasione) {
		final List<CpassTOrdRigaEvasione> listCpassTOrdRigaEvasione =  cpassTOrdRigaEvasioneDao.findByTestataEvasione(testataEvasione.getId(), null);
		final BigDecimal totaleConIva = listCpassTOrdRigaEvasione.stream().map(x -> x.getImportoTotale()).reduce(BigDecimal.ZERO, BigDecimal::add);
		testataEvasione.setTotaleConIva(totaleConIva);
		return updateTestataEvasione (testataEvasione);
	}

}
