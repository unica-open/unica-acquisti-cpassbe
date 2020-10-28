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
import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.ord.evasione.CpassTOrdTestataEvasioneDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.ord.evasione.CpassVRiepilogoFatturaEvasioneDao;
import it.csi.cpass.cpassbe.ejb.entity.CpassTEnte;
import it.csi.cpass.cpassbe.ejb.entity.CpassTSettore;
import it.csi.cpass.cpassbe.ejb.entity.ord.evasione.CpassTOrdDestinatarioEvasione;
import it.csi.cpass.cpassbe.ejb.entity.ord.evasione.CpassTOrdTestataEvasione;
import it.csi.cpass.cpassbe.ejb.entity.view.CpassVRiepilogoFatturaEvasione;
import it.csi.cpass.cpassbe.ejb.exception.NotFoundException;
import it.csi.cpass.cpassbe.ejb.mapper.CpassMappers;
import it.csi.cpass.cpassbe.ejb.util.CpassEnum;
import it.csi.cpass.cpassbe.ejb.util.CpassThreadLocalContainer;
import it.csi.cpass.cpassbe.ejb.util.jpa.Page;
import it.csi.cpass.cpassbe.lib.dto.Impegno;
import it.csi.cpass.cpassbe.lib.dto.Settore;
import it.csi.cpass.cpassbe.lib.dto.Subimpegno;
import it.csi.cpass.cpassbe.lib.dto.Utente;
import it.csi.cpass.cpassbe.lib.dto.ord.OggettiSpesa;
import it.csi.cpass.cpassbe.lib.dto.ord.evasione.DestinatarioEvasione;
import it.csi.cpass.cpassbe.lib.dto.ord.evasione.RiepilogoFatturaEvasione;
import it.csi.cpass.cpassbe.lib.dto.ord.evasione.TestataEvasione;
import it.csi.cpass.cpassbe.lib.util.pagination.PagedList;
import it.csi.cpass.cpassbe.lib.util.pagination.Sort;

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
		Optional<CpassTOrdTestataEvasione> optionalTestata = cpassTOrdTestataEvasioneDao.findByAnnoENumero(anno, numero, enteId);
		TestataEvasione testataEvasione = optionalTestata.map(CpassMappers.TESTATA_EVASIONE::toModel).orElseThrow(() -> new NotFoundException("testataEvasione"));
		return testataEvasione;
	}

	/**
	 * Find by id
	 * 
	 * @param uuid the uuid
	 * @return the model instance
	 */
	public TestataEvasione getTestataEvasioneModel(UUID uuid) {
		Optional<CpassTOrdTestataEvasione> optionalTestata = cpassTOrdTestataEvasioneDao.findOne(uuid);
		TestataEvasione testataEvasione = optionalTestata.map(CpassMappers.TESTATA_EVASIONE::toModel)
				.orElseThrow(() -> new NotFoundException("testataEvasione"));

		// destinatari evasione
		List<CpassTOrdDestinatarioEvasione> destinatariEntities = cpassTOrdDestinatarioDao.findByTestataEvasione(testataEvasione.getId());
		List<DestinatarioEvasione> destinatarioEvasiones = CpassMappers.DESTINATARIO_EVASIONE.toModels(destinatariEntities);
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
		Calendar calendar = Calendar.getInstance();
		int annoCorrente = calendar.get(Calendar.YEAR);
		cpassTOrdTestataEvasione.setEvasioneAnno(annoCorrente);

		// numero
		Optional<CpassTSettore> optionalSettore = cpassTSettoreDao.findOne(testataEvasione.getSettore().getId());
		CpassTEnte cpassTEnte = optionalSettore.get().getCpassTEnte();
		String codice = cpassTEnte.getEnteCodice() + "-" + annoCorrente;
		Integer intProgressivo = super.getProgressivo(CpassEnum.EVASIONE_TESTATA.getCostante(), codice);
		cpassTOrdTestataEvasione.setEvasioneNumero(intProgressivo);

		// date
		cpassTOrdTestataEvasione.setDataInserimento(calendar.getTime());

		cpassTOrdTestataEvasione = cpassTOrdTestataEvasioneDao.insert(cpassTOrdTestataEvasione);

		testataEvasione.setId(cpassTOrdTestataEvasione.getId());
		testataEvasione.setEvasioneAnno(cpassTOrdTestataEvasione.getEvasioneAnno());
		testataEvasione.setEvasioneNumero(cpassTOrdTestataEvasione.getEvasioneNumero());

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
		return testataEvasione;
	}

	public List<RiepilogoFatturaEvasione> getRiepilogoFatturaByIdEvasione(UUID testataEvasioneId) {
		List<CpassVRiepilogoFatturaEvasione> lista = cpassVRiepilogoFatturaEvasioneDao.getRiepilogoFatturaByIdEvasione(testataEvasioneId);
		List<RiepilogoFatturaEvasione> ris = CpassMappers.RIEPILOGO_FATTURA.toModels(lista);
		return ris;
	}
	
	public long countRicercaEvasioni(
			Integer numeroEvasioneDa, Integer annoEvasioneDa, Integer numeroEvasioneA, Integer annoEvasioneA, Date dataInserimentoDa, Date dataInserimentoA,
			Integer annoOrdineDa, Integer numeroOrdineDa, Integer annoOrdineA, Integer numeroOrdineA, Date dataEmissioneDa, Date dataEmissioneA, Integer annoProvvedimento, Integer numeroProvvedimento,
			TestataEvasione testataEvasione, DestinatarioEvasione destinatario, Impegno impegno, Subimpegno subimpegno, OggettiSpesa oggetiSpesa) {

		String strNumeroProvvedimento = null;
		if (numeroProvvedimento != null ) {
			strNumeroProvvedimento = numeroProvvedimento.toString();
		}
		
		long count = cpassTOrdTestataEvasioneDao.countRicercaEvasioni(
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
				strNumeroProvvedimento,
				getId(impegno),
				getId(subimpegno),
				getId(oggetiSpesa));
				
		return count;
		
	}
	
	
	public PagedList<TestataEvasione> getRicercaEvasioni(int page, int size, Sort sort, 
			Integer numeroEvasioneDa, Integer annoEvasioneDa, Integer numeroEvasioneA, Integer annoEvasioneA, Date dataInserimentoDa, Date dataInserimentoA,
			Integer annoOrdineDa, Integer numeroOrdineDa, Integer annoOrdineA, Integer numeroOrdineA, Date dataEmissioneDa, Date dataEmissioneA, Integer numeroProvvedimento, Integer annoProvvedimento,
			TestataEvasione testataEvasione, DestinatarioEvasione destinatario, Impegno impegno, Subimpegno subimpegno, OggettiSpesa oggetiSpesa) {
		
		Utente utenteConnesso = CpassThreadLocalContainer.UTENTE_CONNESSO.get();
		Settore settoreCorrente = CpassThreadLocalContainer.SETTORE_UTENTE.get();
		
		String sortField = null;
		String sortDirection = null;
		
		if (sort != null) {
			if (TestataEvasioneSort.byModelName(sort.getField())!=null)
				sortField = TestataEvasioneSort.byModelName(sort.getField()).getQueryName();
			sortDirection = sort.getOrder().getSortDirection();
		}
		
		String strNumeroProvvedimento = null;
		if (numeroProvvedimento != null ) {
			strNumeroProvvedimento = numeroProvvedimento.toString();
		}
		Page<CpassTOrdTestataEvasione> cpassTOrdTestataEvasiones = cpassTOrdTestataEvasioneDao.findPaginated(
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
				strNumeroProvvedimento,
				getId(impegno),
				getId(subimpegno),
				getId(oggetiSpesa),
				page,
				size,
				sortField,
				sortDirection,
				true,
				utenteConnesso.getCodiceFiscale()
				,utenteConnesso.getId()
				,(settoreCorrente!=null ? settoreCorrente.getId() : null)
				);
				
		PagedList<TestataEvasione> pagedList = toPagedList(cpassTOrdTestataEvasiones, page, size, CpassMappers.TESTATA_EVASIONE::toModel);
		return pagedList;
	}

}
