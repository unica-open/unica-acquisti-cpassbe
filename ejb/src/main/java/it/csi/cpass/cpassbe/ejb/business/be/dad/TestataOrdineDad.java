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

import it.csi.cpass.cpassbe.ejb.business.be.dad.sort.TestataOrdineSort;
import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.CpassTFornitoreDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.CpassTImpegnoDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.CpassTSettoreDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.CpassTSubimpegnoDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.ord.CpassTOrdDestinatarioOrdineDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.ord.CpassTOrdImpegnoAssociatoDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.ord.CpassTOrdSubimpegnoAssociatoDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.ord.CpassTOrdTestataOrdineDao;
import it.csi.cpass.cpassbe.ejb.entity.CpassTEnte;
import it.csi.cpass.cpassbe.ejb.entity.CpassTImpegno;
import it.csi.cpass.cpassbe.ejb.entity.CpassTSettore;
import it.csi.cpass.cpassbe.ejb.entity.CpassTSubimpegno;
import it.csi.cpass.cpassbe.ejb.entity.ord.CpassTFornitore;
import it.csi.cpass.cpassbe.ejb.entity.ord.CpassTOrdDestinatarioOrdine;
import it.csi.cpass.cpassbe.ejb.entity.ord.CpassTOrdImpegnoAssociato;
import it.csi.cpass.cpassbe.ejb.entity.ord.CpassTOrdSubimpegnoAssociato;
import it.csi.cpass.cpassbe.ejb.entity.ord.CpassTOrdTestataOrdine;
import it.csi.cpass.cpassbe.ejb.exception.NotFoundException;
import it.csi.cpass.cpassbe.ejb.mapper.CpassMappers;
import it.csi.cpass.cpassbe.ejb.util.CpassEnum;
import it.csi.cpass.cpassbe.ejb.util.CpassThreadLocalContainer;
import it.csi.cpass.cpassbe.ejb.util.jpa.Page;
import it.csi.cpass.cpassbe.lib.dto.Impegno;
import it.csi.cpass.cpassbe.lib.dto.Settore;
import it.csi.cpass.cpassbe.lib.dto.Subimpegno;
import it.csi.cpass.cpassbe.lib.dto.Utente;
import it.csi.cpass.cpassbe.lib.dto.ord.Destinatario;
import it.csi.cpass.cpassbe.lib.dto.ord.RigaOrdine;
import it.csi.cpass.cpassbe.lib.dto.ord.TestataOrdine;
import it.csi.cpass.cpassbe.lib.util.pagination.PagedList;
import it.csi.cpass.cpassbe.lib.util.pagination.Sort;

/**
 * Data Access Delegate for testataOrdines
 */
@ApplicationScoped
public class TestataOrdineDad extends BaseDad {

	@Inject
	private CpassTOrdTestataOrdineDao cpassTOrdTestataOrdineDao;
	@Inject
	private CpassTFornitoreDao cpassTFornitoreDao;
	@Inject
	private CpassTImpegnoDao cpassTImpegnoDao;
	@Inject
	private CpassTSubimpegnoDao cpassTSubImpegnoDao;
	@Inject
	private CpassTOrdImpegnoAssociatoDao cpassTOrdImpegnoAssociatoDao;
	@Inject
	private CpassTOrdSubimpegnoAssociatoDao cpassTOrdSubimpegnoAssociatoDao;
	@Inject
	private CpassTSettoreDao cpassTSettoreDao;
	@Inject
	private CpassTOrdDestinatarioOrdineDao cpassTOrdDestinatarioDao;

	/**
	 * Find by id
	 * 
	 * @param uuid the uuid
	 * @return the model instance
	 */
	public Optional<TestataOrdine> getTestataOrdine(UUID uuid) {
//		return cpassTOrdTestataOrdineDao.findOne(uuid).map(CpassMappers.TESTATA_ORDINE::toModel);
		return cpassTOrdTestataOrdineDao.findById(uuid).map(CpassMappers.TESTATA_ORDINE::toModel);
	}
	
	/**
	 * Find by anno e numero
	 * 
	 * @param anno the anno
	 * @param numero the numero
	 * @return the TestataOrdine instance
	 */
	public TestataOrdine getByAnnoENumero(Integer anno, Integer numero, UUID enteId) {
		Optional<CpassTOrdTestataOrdine> optionalTestata = cpassTOrdTestataOrdineDao.findByAnnoENumero(anno, numero, enteId);
		TestataOrdine testataOrdine = optionalTestata.map(CpassMappers.TESTATA_ORDINE::toModel).orElseThrow(() -> new NotFoundException("testataOrdine"));
		return testataOrdine;
	}

	/**
	 * Find by id
	 * 
	 * @param uuid the uuid
	 * @return the model instance
	 */
	public TestataOrdine getTestataOrdineModel(UUID uuid) {
		Optional<CpassTOrdTestataOrdine> optionalTestata = cpassTOrdTestataOrdineDao.findById(uuid);
		TestataOrdine testataOrdine = optionalTestata.map(CpassMappers.TESTATA_ORDINE::toModel).orElseThrow(() -> new NotFoundException("testataOrdine"));

		// impegni
		List<CpassTOrdImpegnoAssociato> impegnoAssociatos = cpassTOrdImpegnoAssociatoDao.getImpegniAssociati(testataOrdine.getId());
		if (impegnoAssociatos != null) {
			for (CpassTOrdImpegnoAssociato cpassTOrdImpegnoAssociato : impegnoAssociatos) {
				Impegno impegno = CpassMappers.IMPEGNO.toModel(cpassTOrdImpegnoAssociato.getCpassTImpegno());
				testataOrdine.getListImpegno().add(impegno);

				// subimpegni
				List<CpassTOrdSubimpegnoAssociato> subimpegnoAssociatos = cpassTOrdSubimpegnoAssociatoDao
						.getSubimpegniAssociati(cpassTOrdImpegnoAssociato.getImpegnoAssociatoId());
				if (subimpegnoAssociatos != null) {
					for (CpassTOrdSubimpegnoAssociato cpassTOrdSubimpegnoAssociato : subimpegnoAssociatos) {
						Subimpegno subimpegno = CpassMappers.SUBIMPEGNO.toModel(cpassTOrdSubimpegnoAssociato.getCpassTSubimpegno());
						impegno.getSubimpegni().add(subimpegno);
					}
				}
			}
		}
		
		//destinatari
		List<CpassTOrdDestinatarioOrdine> destinatariEntities = cpassTOrdDestinatarioDao.findByOrdine(testataOrdine.getId());
		List<Destinatario> destinatari = CpassMappers.DESTINATARIO.toModels(destinatariEntities);
		
		testataOrdine.setListDestinatario(destinatari);

		return testataOrdine;
	}

	/**
	 * Deletes by uuid
	 * 
	 * @param uuid the uuid
	 */
	public void deleteTestataOrdine(UUID uuid) {
		cpassTOrdTestataOrdineDao.delete(uuid);
	}

	/**
	 * Inserts the testataOrdine
	 * 
	 * @param testataOrdine the testataOrdine
	 * @return the model instance
	 */
	public TestataOrdine saveTestataOrdine(TestataOrdine testataOrdine) {
		CpassTOrdTestataOrdine cpassTOrdTestataOrdine = CpassMappers.TESTATA_ORDINE.toEntity(testataOrdine);

		// fornitore
		CpassTFornitore cpassTFornitoreNew = cpassTOrdTestataOrdine.getCpassTFornitore();
		if (cpassTFornitoreNew.getNaturaGiuridica() != null && cpassTFornitoreNew.getNaturaGiuridica().length() > 5) {
			cpassTFornitoreNew.setNaturaGiuridica(cpassTFornitoreNew.getNaturaGiuridica().substring(0, 5));
		}

		CpassTFornitore cpassTFornitoreOld = cpassTFornitoreDao.getFornitoreByCodice(cpassTFornitoreNew.getCodice());
		if (cpassTFornitoreOld == null) {
			cpassTFornitoreNew = cpassTFornitoreDao.insert(cpassTFornitoreNew);
		} else {
			cpassTFornitoreNew.setId(cpassTFornitoreOld.getId());
			cpassTFornitoreNew = cpassTFornitoreDao.update(cpassTFornitoreNew);
		}
		cpassTOrdTestataOrdine.setCpassTFornitore(cpassTFornitoreNew);

		// testata
		Calendar calendar = Calendar.getInstance();
		int annoCorrente = calendar.get(Calendar.YEAR);
		cpassTOrdTestataOrdine.setOrdineAnno(annoCorrente);
		cpassTOrdTestataOrdine.setDataEmissione(calendar.getTime());

		Optional<CpassTSettore> optionalSettore = cpassTSettoreDao.findOne(testataOrdine.getSettore().getId());
		CpassTEnte cpassTEnte = optionalSettore.get().getCpassTEnte();
		String codice = cpassTEnte.getEnteCodice() + "-" + annoCorrente;
		Integer intProgressivo = super.getProgressivo(CpassEnum.ORDINE_TESTATA.getCostante(), codice);
		cpassTOrdTestataOrdine.setOrdineNumero(intProgressivo);

//		if (testataOrdine.getProvvedimento() != null) {
//			cpassTOrdTestataOrdine.setProvvedimentoAnno(testataOrdine.getProvvedimento().getAnno());
//			cpassTOrdTestataOrdine.setProvvedimentoNumero("" + testataOrdine.getProvvedimento().getNumero());
//		}
		cpassTOrdTestataOrdine = cpassTOrdTestataOrdineDao.insert(cpassTOrdTestataOrdine);

		testataOrdine.setId(cpassTOrdTestataOrdine.getId());
		testataOrdine.setAnno(cpassTOrdTestataOrdine.getOrdineAnno());
		testataOrdine.setNumero(cpassTOrdTestataOrdine.getOrdineNumero());

		// impegni
		insertImpegni(testataOrdine, cpassTOrdTestataOrdine, cpassTFornitoreNew, cpassTEnte);

		return testataOrdine;
	}

	private void insertImpegni(TestataOrdine testataOrdine, CpassTOrdTestataOrdine cpassTOrdTestataOrdine, CpassTFornitore cpassTFornitoreNew,
			CpassTEnte cpassTEnte) {
		if (testataOrdine.getListImpegno() != null && testataOrdine.getListImpegno().size() > 0) {
			for (Impegno impegno : testataOrdine.getListImpegno()) {

				CpassTImpegno cpassTImpegno = CpassMappers.IMPEGNO.toEntity(impegno);
				
				// controllo lunghezza massima
				if (cpassTImpegno.getImpegnoDescrizione() != null && cpassTImpegno.getImpegnoDescrizione().length() > 150) {
					cpassTImpegno.setImpegnoDescrizione(cpassTImpegno.getImpegnoDescrizione().substring(0, 150));
				}

				cpassTImpegno.setCpassTEnte(cpassTEnte);
				cpassTImpegno.setCpassTFornitore(cpassTFornitoreNew);

				CpassTImpegno cpassTImpegnoPresente = cpassTImpegnoDao.getImpegnoByChiaveLogica(cpassTImpegno.getImpegnoAnnoEsercizio(),
						cpassTImpegno.getImpegnoAnno(), cpassTImpegno.getImpegnoNumero(), cpassTImpegno.getCpassTEnte().getId());
				if (cpassTImpegnoPresente == null) {
					cpassTImpegnoPresente = cpassTImpegnoDao.insert(cpassTImpegno);
				}

				// associato
				CpassTOrdImpegnoAssociato cpassTOrdImpegnoAssociato = new CpassTOrdImpegnoAssociato();
				cpassTOrdImpegnoAssociato.setCpassTImpegno(cpassTImpegnoPresente);
				cpassTOrdImpegnoAssociato.setCpassTOrdTestataOrdine(cpassTOrdTestataOrdine);
				cpassTOrdImpegnoAssociato.setImpegnoAnno(cpassTImpegnoPresente.getImpegnoAnno());
				cpassTOrdImpegnoAssociato.setImpegnoAnnoEsercizio(cpassTImpegnoPresente.getImpegnoAnnoEsercizio());
				cpassTOrdImpegnoAssociato.setImpegnoNumero(cpassTImpegnoPresente.getImpegnoNumero());
				// cpassTOrdImpegnoAssociato.setImpegnoProgressivo(1);
				cpassTOrdImpegnoAssociatoDao.insert(cpassTOrdImpegnoAssociato);

				// subimpegno
				if (impegno.getSubimpegni() != null && impegno.getSubimpegni().size() > 0) {
					for (Subimpegno subimpegno : impegno.getSubimpegni()) {
						CpassTSubimpegno cpassTSubimpegno = CpassMappers.SUBIMPEGNO.toEntity(subimpegno);

						cpassTSubimpegno.setCpassTEnte(cpassTEnte);
						cpassTSubimpegno.setCpassTFornitore(cpassTFornitoreNew);
						cpassTSubimpegno.setCpassTImpegno(cpassTImpegnoPresente);

						cpassTSubimpegno.setImpegnoAnno(impegno.getAnno());
						cpassTSubimpegno.setImpegnoAnnoEsercizio(impegno.getAnnoEsercizio());
						cpassTSubimpegno.setImpegnoNumero(impegno.getNumero());
						
						CpassTSubimpegno cpassTSubimpegnoPresente = cpassTSubImpegnoDao.getSubimpegnoByChiaveLogica(cpassTSubimpegno.getImpegnoAnnoEsercizio(),
								cpassTSubimpegno.getImpegnoAnno(), cpassTSubimpegno.getImpegnoNumero(), cpassTSubimpegno.getCpassTEnte().getId(),
								cpassTSubimpegno.getSubimpegnoAnno(), cpassTSubimpegno.getSubimpegnoNumero());
						if (cpassTSubimpegnoPresente == null) {
							cpassTSubimpegnoPresente = cpassTSubImpegnoDao.insert(cpassTSubimpegno);
						}

						// associato
						CpassTOrdSubimpegnoAssociato cpassTOrdSubimpegnoAssociato = new CpassTOrdSubimpegnoAssociato();
						cpassTOrdSubimpegnoAssociato.setCpassTOrdImpegnoAssociato(cpassTOrdImpegnoAssociato);
						cpassTOrdSubimpegnoAssociato.setCpassTSubimpegno(cpassTSubimpegnoPresente);

						cpassTOrdSubimpegnoAssociato.setImpegnoAnno(cpassTImpegnoPresente.getImpegnoAnno());
						cpassTOrdSubimpegnoAssociato.setImpegnoAnnoEsercizio(cpassTImpegnoPresente.getImpegnoAnnoEsercizio());
						cpassTOrdSubimpegnoAssociato.setImpegnoNumero(cpassTImpegnoPresente.getImpegnoNumero());

						cpassTOrdSubimpegnoAssociato.setSubimpegnoAnno(cpassTSubimpegnoPresente.getSubimpegnoAnno());
						cpassTOrdSubimpegnoAssociato.setSubimpegnoImporto(cpassTSubimpegnoPresente.getImportoAttuale());
						cpassTOrdSubimpegnoAssociato.setSubimpegnoNumero(cpassTSubimpegnoPresente.getSubimpegnoNumero());

						cpassTOrdSubimpegnoAssociatoDao.insert(cpassTOrdSubimpegnoAssociato);
					}
				}
			}
		}
	}

	/**
	 * Updates the testataOrdine
	 * 
	 * @param testataOrdine the testataOrdine
	 * @return the model instance
	 */
	public TestataOrdine updateTestataOrdine(TestataOrdine testataOrdine) {
		return updateTestataOrdine(testataOrdine, true);
	}
	
	public TestataOrdine updateTestataOrdine(TestataOrdine testataOrdine, boolean bUpdateAssociati) {
		CpassTOrdTestataOrdine cpassTOrdTestataOrdine = CpassMappers.TESTATA_ORDINE.toEntity(testataOrdine);

		// fornitore
		CpassTFornitore cpassTFornitoreNew = cpassTOrdTestataOrdine.getCpassTFornitore();
		if (cpassTFornitoreNew.getNaturaGiuridica() != null && cpassTFornitoreNew.getNaturaGiuridica().length() > 5) {
			cpassTFornitoreNew.setNaturaGiuridica(cpassTFornitoreNew.getNaturaGiuridica().substring(0, 5));
		}

		CpassTFornitore cpassTFornitoreOld = cpassTFornitoreDao.getFornitoreByCodice(cpassTFornitoreNew.getCodice());
		if (cpassTFornitoreOld == null) {
			cpassTFornitoreNew = cpassTFornitoreDao.insert(cpassTFornitoreNew);
		} else {
			cpassTFornitoreNew.setId(cpassTFornitoreOld.getId());
			cpassTFornitoreNew = cpassTFornitoreDao.update(cpassTFornitoreNew);
		}
		cpassTOrdTestataOrdine.setCpassTFornitore(cpassTFornitoreNew);

		// aggiorno testata
		cpassTOrdTestataOrdine = cpassTOrdTestataOrdineDao.update(cpassTOrdTestataOrdine);

		if (bUpdateAssociati) {
			// aggiorno impegni/subimpegni associati
			CpassTFornitore cpassTFornitore = cpassTOrdTestataOrdine.getCpassTFornitore();

			Optional<CpassTSettore> optionalSettore = cpassTSettoreDao.findOne(testataOrdine.getSettore().getId());
			CpassTEnte cpassTEnte = optionalSettore.get().getCpassTEnte();
			
			// cancello associati
			deleteAssociati(testataOrdine);
			cpassTOrdImpegnoAssociatoDao.flushAndClear(); // per poterli re-inserire

			insertImpegni(testataOrdine, cpassTOrdTestataOrdine, cpassTFornitore, cpassTEnte);
		}
		
		return testataOrdine;
	}

	public void deleteAssociati(TestataOrdine testataOrdine) {
		// NON funziona delete con join
		// cpassTOrdSubimpegnoAssociatoDao.deleteByTestataOrdine(cpassTOrdTestataOrdine.getId());
		List<CpassTOrdImpegnoAssociato> impegnoAssociatos = cpassTOrdImpegnoAssociatoDao.getImpegniAssociati(testataOrdine.getId());
		if (impegnoAssociatos != null) {
			for (CpassTOrdImpegnoAssociato cpassTOrdImpegnoAssociato : impegnoAssociatos) {
			
				List<CpassTOrdSubimpegnoAssociato> subimpegnoAssociatos = cpassTOrdSubimpegnoAssociatoDao.getSubimpegniAssociati(cpassTOrdImpegnoAssociato.getId());
				if (subimpegnoAssociatos != null) {
					for (CpassTOrdSubimpegnoAssociato cpassTOrdSubimpegnoAssociato : subimpegnoAssociatos) {
						cpassTOrdSubimpegnoAssociatoDao.delete(cpassTOrdSubimpegnoAssociato.getId());
					}
				}
			}
		}
		
		cpassTOrdImpegnoAssociatoDao.deleteByTestataOrdine(testataOrdine.getId());
	}

	/**
	 * Updates the stato testataOrdine
	 * 
	 * @param id          the id
	 * @param statoCodice the stato codice
	 * @param statoTipo   the stato tipo
	 */
	public void updateStatoTestataOrdine(UUID id, String statoCodice, String statoTipo) {
//		CpassTPbaTestataOrdine cpassTPbaTestataOrdine = cpassTPbaTestataOrdineDao.findOne(id).orElseThrow(() -> new NotFoundException("testataOrdine"));
//		CpassDStato cpassDStato = cpassDStatoDao.findByCodiceTipo(statoCodice, statoTipo).orElseThrow(() -> new NotFoundException("stato"));
//		cpassTPbaTestataOrdine.setCpassDStato(cpassDStato);
//		cpassTPbaTestataOrdineDao.update(cpassTPbaTestataOrdine);
	}

	public long countRicercaOrdini(Integer annoOrdineDa, Integer numeroOrdineDa, Integer annoOrdineA, Integer numeroOrdineA, Date dataEmissioneDa, Date dataEmissioneA, TestataOrdine testataOrdine, Destinatario destinatario,Impegno impegno, Subimpegno subimpegno, RigaOrdine rigaOrdine) {

		String numeroProvvedimento = null;
		if (testataOrdine.getProvvedimento().getNumero() != null ) {
			numeroProvvedimento = testataOrdine.getProvvedimento().getNumero().toString();
		}
		long count = cpassTOrdTestataOrdineDao.countRicercaOrdini(
				annoOrdineDa,
				numeroOrdineDa,
				annoOrdineA, 
				numeroOrdineA, 
				dataEmissioneDa, 
				dataEmissioneA,
				getId(testataOrdine.getTipoOrdine()),
				getId(testataOrdine.getStato()),
				getId(testataOrdine.getStatoNso()),
				testataOrdine.getLottoAnno(),
				testataOrdine.getLottoNumero(),
				getId(testataOrdine.getTipoProcedura()),
				testataOrdine.getNumeroProcedura(),
				getId(testataOrdine.getSettore()),
				getId(destinatario),
				getId(testataOrdine.getFornitore()),
				testataOrdine.getProvvedimento().getAnno(),
				numeroProvvedimento,
				getId(impegno),
				getId(subimpegno),
				getId(rigaOrdine.getOds()),
				getId(rigaOrdine.getOds().getCpv()));
				
		return count;
		
	}
	
	public PagedList<TestataOrdine> getRicercaOrdini(int page, int size, Sort sort, 
			Integer annoOrdineDa, Integer numeroOrdineDa, Integer annoOrdineA, Integer numeroOrdineA, Date dataEmissioneDa, Date dataEmissioneA, TestataOrdine testataOrdine, Destinatario destinatario,Impegno impegno, Subimpegno subimpegno, RigaOrdine rigaOrdine
			) {
		
//		UUID enteId = checkUtenteAndGetEnte(settoreId);

		Utente utenteConnesso = CpassThreadLocalContainer.UTENTE_CONNESSO.get();
		Settore settoreCorrente = CpassThreadLocalContainer.SETTORE_UTENTE.get();
		
		String sortField = null;
		String sortDirection = null;
		
		if (sort != null) {
			if (TestataOrdineSort.byModelName(sort.getField())!=null)
				sortField = TestataOrdineSort.byModelName(sort.getField()).getQueryName();
			sortDirection = sort.getOrder().getSortDirection();
		}
		
		String numeroProvvedimento = null;
		if (testataOrdine.getProvvedimento().getNumero() != null ) {
			numeroProvvedimento = testataOrdine.getProvvedimento().getNumero().toString();
		}
		Page<CpassTOrdTestataOrdine> cpassTOrdTestataOrdines = cpassTOrdTestataOrdineDao.findPaginated(
				annoOrdineDa,
				numeroOrdineDa,
				annoOrdineA, 
				numeroOrdineA, 
				dataEmissioneDa, 
				dataEmissioneA,
				getId(testataOrdine.getTipoOrdine()),
				getId(testataOrdine.getStato()),
				getId(testataOrdine.getStatoNso()),
				testataOrdine.getLottoAnno(),
				testataOrdine.getLottoNumero(),
				getId(testataOrdine.getTipoProcedura()),
				testataOrdine.getNumeroProcedura(),
				getId(testataOrdine.getSettore()),
				getId(destinatario),
				getId(testataOrdine.getFornitore()),
				testataOrdine.getProvvedimento().getAnno(),
				numeroProvvedimento,
				getId(impegno),
				getId(subimpegno),
				getId(rigaOrdine.getOds()),
				getId(rigaOrdine.getOds().getCpv()),
				page,
				size,
				sortField,
				sortDirection,
				true,
				utenteConnesso.getCodiceFiscale()
				,utenteConnesso.getId()
				,(settoreCorrente!=null ? settoreCorrente.getId() : null)
				);
				
		PagedList<TestataOrdine> pagedList = toPagedList(cpassTOrdTestataOrdines, page, size, CpassMappers.TESTATA_ORDINE::toModel);
		return pagedList;
	}

	/**
	 * 
	 * @param evasioneId
	 * @return
	 */
	public List<TestataOrdine> findTestateOrdineByEvasioneId(UUID evasioneId){
		List<CpassTOrdTestataOrdine> listaOrdini = cpassTOrdTestataOrdineDao.findTestateOrdineByEvasioneId(evasioneId);
		List<TestataOrdine> listaris = CpassMappers.TESTATA_ORDINE.toModels(listaOrdini);		
		return listaris;
	}

	public List<TestataOrdine> findTestateOrdineByDestinatarioEvasioneId(UUID destinatarioEvasioneId) {
		List<CpassTOrdTestataOrdine> listaOrdini = cpassTOrdTestataOrdineDao.findTestateOrdineByDestinatarioEvasioneId(destinatarioEvasioneId);
		List<TestataOrdine> listaris = CpassMappers.TESTATA_ORDINE.toModels(listaOrdini);		
		return listaris;
	}

	public List<TestataOrdine> findTestateOrdineByRigaEvasioneId(UUID rigaEvasioneId) {
		List<CpassTOrdTestataOrdine> listaOrdini = cpassTOrdTestataOrdineDao.findTestateOrdineByRigaEvasioneId(rigaEvasioneId);
		List<TestataOrdine> listaris = CpassMappers.TESTATA_ORDINE.toModels(listaOrdini);		
		return listaris;
	}
	

}
