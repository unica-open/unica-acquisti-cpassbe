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

import java.util.Base64;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import it.csi.cpass.cpassbe.ejb.business.be.dad.sort.TestataOrdineSort;
import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.CpassTFornitoreDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.CpassTImpegnoDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.CpassTSettoreDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.CpassTSubimpegnoDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.decodifiche.CpassDProvvedimentoTipoDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.ord.CpassDAooActaDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.ord.CpassROrdRdaOrdineDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.ord.CpassTOrdDestinatarioOrdineDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.ord.CpassTOrdDocumentiOrdineDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.ord.CpassTOrdImpegnoAssociatoDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.ord.CpassTOrdProtocolloOrdineDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.ord.CpassTOrdSezioneDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.ord.CpassTOrdSubimpegnoAssociatoDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.ord.CpassTOrdTestataOrdineDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.ord.CpassVOrdineDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.ord.rda.CpassTOrdTestataRdaDao;
import it.csi.cpass.cpassbe.ejb.entity.CpassDProvvedimentoTipo;
import it.csi.cpass.cpassbe.ejb.entity.CpassTEnte;
import it.csi.cpass.cpassbe.ejb.entity.CpassTImpegno;
import it.csi.cpass.cpassbe.ejb.entity.CpassTSettore;
import it.csi.cpass.cpassbe.ejb.entity.CpassTSubimpegno;
import it.csi.cpass.cpassbe.ejb.entity.ord.CpassDAooActa;
import it.csi.cpass.cpassbe.ejb.entity.ord.CpassROrdRdaOrdine;
import it.csi.cpass.cpassbe.ejb.entity.ord.CpassTFornitore;
import it.csi.cpass.cpassbe.ejb.entity.ord.CpassTOrdDestinatarioOrdine;
import it.csi.cpass.cpassbe.ejb.entity.ord.CpassTOrdDocumentiOrdine;
import it.csi.cpass.cpassbe.ejb.entity.ord.CpassTOrdImpegnoAssociato;
import it.csi.cpass.cpassbe.ejb.entity.ord.CpassTOrdProtocolloOrdine;
import it.csi.cpass.cpassbe.ejb.entity.ord.CpassTOrdRigaOrdine;
import it.csi.cpass.cpassbe.ejb.entity.ord.CpassTOrdSezione;
import it.csi.cpass.cpassbe.ejb.entity.ord.CpassTOrdSubimpegnoAssociato;
import it.csi.cpass.cpassbe.ejb.entity.ord.CpassTOrdTestataOrdine;
import it.csi.cpass.cpassbe.ejb.entity.ord.CpassVOrdine;
import it.csi.cpass.cpassbe.ejb.entity.ord.rda.CpassTOrdTestataRda;
import it.csi.cpass.cpassbe.ejb.exception.NotFoundException;
import it.csi.cpass.cpassbe.ejb.mapper.CpassMappers;
import it.csi.cpass.cpassbe.ejb.util.CpassEnum;
import it.csi.cpass.cpassbe.ejb.util.StringUtility;
import it.csi.cpass.cpassbe.ejb.util.jpa.Page;
import it.csi.cpass.cpassbe.lib.dto.Ente;
import it.csi.cpass.cpassbe.lib.dto.Impegno;
import it.csi.cpass.cpassbe.lib.dto.Settore;
import it.csi.cpass.cpassbe.lib.dto.SettoreIndirizzo;
import it.csi.cpass.cpassbe.lib.dto.Subimpegno;
import it.csi.cpass.cpassbe.lib.dto.Utente;
import it.csi.cpass.cpassbe.lib.dto.ord.Destinatario;
import it.csi.cpass.cpassbe.lib.dto.ord.DocumentiOrdine;
import it.csi.cpass.cpassbe.lib.dto.ord.OrdRdaOrdine;
import it.csi.cpass.cpassbe.lib.dto.ord.ProtocolloOrdine;
import it.csi.cpass.cpassbe.lib.dto.ord.RigaOrdine;
import it.csi.cpass.cpassbe.lib.dto.ord.Sezione;
import it.csi.cpass.cpassbe.lib.dto.ord.TestataOrdine;
import it.csi.cpass.cpassbe.lib.dto.ord.rda.TestataRda;
import it.csi.cpass.cpassbe.lib.util.pagination.PagedList;
import it.csi.cpass.cpassbe.lib.util.pagination.Sort;
import it.csi.cpass.cpassbe.lib.util.threadlocal.CpassThreadLocalContainer;

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
	@Inject
	private CommonDad commonDad;
	@Inject
	private CpassTOrdTestataRdaDao cpassTOrdTestataRdaDao;
	@Inject
	private CpassTOrdProtocolloOrdineDao cpassTOrdProtocolloOrdineDao;
	@Inject
	private CpassROrdRdaOrdineDao cpassROrdRdaOrdineDao;
	@Inject
	private CpassTOrdSezioneDao cpassTOrdSezioneDao;
	@Inject
	private CpassDProvvedimentoTipoDao cpassDProvvedimentoTipoDao;
	@Inject
	private CpassTOrdDocumentiOrdineDao cpassTOrdDocumentiOrdineDao;
	@Inject
	private CpassDAooActaDao cpassDAooActaDao;
	@Inject
	private CpassVOrdineDao cpassVOrdineDao;

	/**
	 * Find by id
	 *
	 * @param uuid the uuid
	 * @return the model instance
	 */
	public TestataOrdine getTestataOrdine(UUID uuid) {
		return cpassTOrdTestataOrdineDao.findById(uuid).map(CpassMappers.TESTATA_ORDINE::toModel).orElseThrow(() -> new NotFoundException("testata ordine"));
	}
	/**
	 * 
	 * @param statiIds
	 * @return
	 */
	public List<CpassVOrdine> getListVOrdineByStati(List<Integer> statiIds) {
		return cpassVOrdineDao.getListVOrdineByStati(statiIds);
	}

	/**
	 * Find by anno e numero
	 *
	 * @param anno the anno
	 * @param numero the numero
	 * @return the TestataOrdine instance
	 */
	public TestataOrdine getByAnnoENumero(Integer anno, Integer numero, UUID enteId) {
		final Optional<CpassTOrdTestataOrdine> optionalTestata = cpassTOrdTestataOrdineDao.findByAnnoENumero(anno, numero, enteId);
		return optionalTestata.map(CpassMappers.TESTATA_ORDINE::toModel).orElseThrow(() -> new NotFoundException("testata ordine"));
	}
	
	/**
	 * 
	 * @param anno
	 * @param numero
	 * @param enteId
	 * @return
	 */
	public Optional<TestataOrdine> getByAnnoENumeroOpt(Integer anno, Integer numero, UUID enteId) {
		final Optional<CpassTOrdTestataOrdine> optionalTestata = cpassTOrdTestataOrdineDao.findByAnnoENumero(anno, numero, enteId);
		final Optional<TestataOrdine> ris = optionalTestata.map(CpassMappers.TESTATA_ORDINE::toModel);
		return ris;
	}

	/**
	 * Find by id
	 *
	 * @param uuid the uuid
	 * @return the model instance
	 */
	//TODO riscrivere la lettura degli impegni perchè ora il mapper impegno rimappa anche la lista subimpegno
	public TestataOrdine getTestataOrdineModel(UUID uuid,UUID enteId) {
		final Optional<CpassTOrdTestataOrdine> optionalTestata = cpassTOrdTestataOrdineDao.findById(uuid);
		final TestataOrdine testataOrdine = optionalTestata.map(CpassMappers.TESTATA_ORDINE::toModel).orElseThrow(() -> new NotFoundException("testataOrdine"));
		// completa il mapping del tipoProvvedimento (altrimenti manca la descrizione)
		final Optional<CpassDProvvedimentoTipo> optionalProvvedimentoTipo = cpassDProvvedimentoTipoDao.findByCodice(testataOrdine.getProvvedimento().getProvvedimentoTipo().getCodice(),enteId);
		testataOrdine.getProvvedimento().setProvvedimentoTipo(optionalProvvedimentoTipo.map(CpassMappers.PROVVEDIMENTO_TIPO::toModel).orElse(null));
		// impegni
		final List<CpassTOrdImpegnoAssociato> impegnoAssociatos = cpassTOrdImpegnoAssociatoDao.getImpegniAssociati(testataOrdine.getId());
		if (impegnoAssociatos != null) {
			for (final CpassTOrdImpegnoAssociato cpassTOrdImpegnoAssociato : impegnoAssociatos) {
				final Impegno impegno = CpassMappers.IMPEGNO.toModel(cpassTOrdImpegnoAssociato.getCpassTImpegno());
				testataOrdine.getListImpegno().add(impegno);
				// subimpegni
				final List<CpassTOrdSubimpegnoAssociato> subimpegnoAssociatos = cpassTOrdSubimpegnoAssociatoDao.getSubimpegniAssociati(cpassTOrdImpegnoAssociato.getImpegnoAssociatoId());
				if (subimpegnoAssociatos != null) {
					for (final CpassTOrdSubimpegnoAssociato cpassTOrdSubimpegnoAssociato : subimpegnoAssociatos) {
						final Subimpegno subimpegno = CpassMappers.SUBIMPEGNO.toModel(cpassTOrdSubimpegnoAssociato.getCpassTSubimpegno());
						impegno.getSubimpegni().add(subimpegno);
					}
				}
			}
		}
		//destinatari
		final List<CpassTOrdDestinatarioOrdine> destinatariEntities = cpassTOrdDestinatarioDao.findByOrdine(testataOrdine.getId());
		final List<Destinatario> destinatari = CpassMappers.DESTINATARIO.toModels(destinatariEntities);
		for(final Destinatario destinatario:  destinatari) {
			if(destinatario.getSettoreIndirizzo()!=null) {
				destinatario.getSettore().setSede(destinatario.getSettoreIndirizzo().getDescrizione());
				if(destinatario.getSettoreIndirizzo().getPrincipale() !=null && destinatario.getSettoreIndirizzo().getPrincipale()) {
					destinatario.getSettore().setSede(CpassEnum.PRINCIPALE.getCostante());
				}
			}
		}
		testataOrdine.setListDestinatario(destinatari);
		// controllo presenza ods associati.
		final Integer countOdsAssociati = cpassTOrdTestataOrdineDao.countOggettiSpesaLegatiAOrdine(testataOrdine.getId());
		if(countOdsAssociati > 0) {
			testataOrdine.setHasOds(Boolean.TRUE);
		}

		final List<CpassTOrdProtocolloOrdine> entities = cpassTOrdProtocolloOrdineDao.findProtocolloByOrderId(testataOrdine.getId());
		final List<ProtocolloOrdine> protocolloOrdines=  CpassMappers.PROTOCOLLO_ORDINE.toModels(entities);
		for(final ProtocolloOrdine po : protocolloOrdines) {
			if(!StringUtility.isEmpty(po.getAoo())) {
				final List<CpassDAooActa> aoo = cpassDAooActaDao.findByActaOrigId(Integer.parseInt(po.getAoo()), enteId);
				if(aoo!=null && aoo.size()>0) {
					po.setAooCode(aoo.get(0).getAooCodice());
				}
			}
			if(!StringUtility.isEmpty(po.getAooOrig())) {
				final List<CpassDAooActa> aoo = cpassDAooActaDao.findByActaOrigId(Integer.parseInt(po.getAooOrig()), enteId);
				if(aoo!=null && aoo.size()>0) {
					po.setAooOrigCode(aoo.get(0).getAooCodice());
				}
			}
		}
		testataOrdine.setProtocolloOrdines(protocolloOrdines);
		return testataOrdine;
	}

	/**
	 * Deletes by uuid
	 *
	 * @param uuid the uuid
	 */
	public void deleteTestataOrdine(UUID idTestataOrdine) {
		cpassTOrdTestataOrdineDao.delete(idTestataOrdine);
	}
	
	/**
	 * 
	 * @param idTestataOrdine
	 */
	public void deleteProtocolliOrdineByTestataOrdine(UUID idTestataOrdine) {
		cpassTOrdProtocolloOrdineDao.deleteProtocolloByTestataordineId(idTestataOrdine);
	}

	/**
	 * Inserts the testataOrdine
	 *
	 * @param testataOrdine the testataOrdine
	 * @return the model instance
	 */
	public TestataOrdine insertTestataOrdine(TestataOrdine testataOrdine) {
		CpassTOrdTestataOrdine cpassTOrdTestataOrdine = CpassMappers.TESTATA_ORDINE.toEntity(testataOrdine);

		// fornitore
		CpassTFornitore cpassTFornitoreNew = cpassTOrdTestataOrdine.getCpassTFornitore();
		final Ente ente = CpassThreadLocalContainer.SETTORE_UTENTE.get().getEnte();
		cpassTFornitoreNew.setCpassTEnte(CpassMappers.ENTE.toEntity(ente));
		if (cpassTFornitoreNew.getNaturaGiuridica() != null && cpassTFornitoreNew.getNaturaGiuridica().length() > 5) {
			cpassTFornitoreNew.setNaturaGiuridica(cpassTFornitoreNew.getNaturaGiuridica().substring(0, 5));
		}

		final CpassTFornitore cpassTFornitoreOld = cpassTFornitoreDao.getFornitoreByCodice(cpassTFornitoreNew.getCodice(), getId(ente));
		if (cpassTFornitoreOld == null) {
			cpassTFornitoreNew.setIndirizzo(testataOrdine.getFornitore().getIndirizzoConSedime());
			cpassTFornitoreNew = cpassTFornitoreDao.insert(cpassTFornitoreNew);
		} else {
			cpassTFornitoreNew.setId(cpassTFornitoreOld.getId());
			cpassTFornitoreNew = cpassTFornitoreDao.update(cpassTFornitoreNew, false);
		}
		cpassTFornitoreDao.flush();
		cpassTOrdTestataOrdine.setCpassTFornitore(cpassTFornitoreNew);
		// testata
		final Calendar calendar = Calendar.getInstance();
		final int annoCorrente = calendar.get(Calendar.YEAR);
		cpassTOrdTestataOrdine.setOrdineAnno(annoCorrente);
		cpassTOrdTestataOrdine.setDataEmissione(calendar.getTime());
		final Optional<CpassTSettore> optionalSettore = cpassTSettoreDao.findOne(testataOrdine.getSettore().getId());
		final CpassTEnte cpassTEnte = optionalSettore.orElseThrow(() -> new NotFoundException("ente")).getCpassTEnte();
		final String codice = cpassTEnte.getEnteCodice() + "-" + annoCorrente;
		final Integer intProgressivo = commonDad.getProgressivo(CpassEnum.ORDINE_TESTATA.getCostante(), codice, cpassTEnte);
		cpassTOrdTestataOrdine.setOrdineNumero(intProgressivo);
		if (testataOrdine.getProvvedimento() != null) {
			cpassTOrdTestataOrdine.setProvvedimentoAnno(testataOrdine.getProvvedimento().getAnno());
			cpassTOrdTestataOrdine.setProvvedimentoNumero("" + testataOrdine.getProvvedimento().getNumero());
			cpassTOrdTestataOrdine.setProvvedimentoTipo(testataOrdine.getProvvedimento().getProvvedimentoTipo().getCodice());
			cpassTOrdTestataOrdine.setProvvedimentoDescrizione(testataOrdine.getProvvedimento().getDescrizione());

			if(testataOrdine.getProvvedimento().getSettore() != null) {
				cpassTOrdTestataOrdine.setProvvedimentoSettore(testataOrdine.getProvvedimento().getSettore().getCodice());
			}
		}
		cpassTOrdTestataOrdine = cpassTOrdTestataOrdineDao.insert(cpassTOrdTestataOrdine);
		cpassTOrdTestataOrdineDao.flush();
		// impegni
		insertImpegni(testataOrdine, cpassTOrdTestataOrdine, cpassTFornitoreNew, cpassTEnte);
		if(cpassTOrdTestataOrdine.getCpassTOrdProtocolloOrdines() != null && cpassTOrdTestataOrdine.getCpassTOrdProtocolloOrdines().size() > 0) {
			//inserisci il protocollo
			insertProtocollo(cpassTOrdTestataOrdine, cpassTOrdTestataOrdine.getCpassTOrdProtocolloOrdines().get(0));
		}
		//documenti
		insertDocumenti(testataOrdine.getDocumentiOrdines(),cpassTOrdTestataOrdine);
		return CpassMappers.TESTATA_ORDINE.toModel(cpassTOrdTestataOrdine);
	}

	/**
	 * 
	 * @param testataOrdine
	 * @param cpassTOrdTestataOrdine
	 */
	private void updateDocumenti(TestataOrdine testataOrdine, CpassTOrdTestataOrdine cpassTOrdTestataOrdine) {
		final List<CpassTOrdDocumentiOrdine> documentiDB =  cpassTOrdDocumentiOrdineDao.getDocumentiByOrdineTestataId(testataOrdine.getId());
		if(documentiDB != null) {
			final List<Integer> setDocumentiOrdineTODELETE = documentiDB.stream().map(x -> x.getId()).collect(Collectors.toList());

			if(testataOrdine.getDocumentiOrdines() !=null && testataOrdine.getDocumentiOrdines().size()>0) {
				final List<Integer> idDocumentiOrdineCLIENT = testataOrdine.getDocumentiOrdines().stream().filter(a->a.getId()!=null).map(x -> x.getId()).collect(Collectors.toList());
				setDocumentiOrdineTODELETE.removeAll(idDocumentiOrdineCLIENT);
			}

			for(final Integer idDocumentiOrdine: setDocumentiOrdineTODELETE) {
				cpassTOrdDocumentiOrdineDao.deleteById(idDocumentiOrdine);
			}
		}

		if(testataOrdine.getDocumentiOrdines()!= null) {
			final List<DocumentiOrdine> documentiOrdineTOINSERT = testataOrdine.getDocumentiOrdines().stream().filter(a->a.getId()==null).collect(Collectors.toList());
			insertDocumenti(documentiOrdineTOINSERT, cpassTOrdTestataOrdine);
		}

	}
	/**
	 * 
	 * @param list
	 * @param cpassTOrdTestataOrdine
	 */
	private void insertDocumenti(List<DocumentiOrdine> list, CpassTOrdTestataOrdine cpassTOrdTestataOrdine) {
		if(list!=null) {
			for(final DocumentiOrdine doc : list) {
				final CpassTOrdDocumentiOrdine entity = CpassMappers.DOCUMENTO_ORDINE_MINIMAL.toEntity(doc);
				log.info("insertDocumenti", java.util.Base64.getDecoder().decode(doc.getFileDocumento()));
				entity.setFile(java.util.Base64.getDecoder().decode(doc.getFileDocumento()));
				entity.setCpassTOrdTestataOrdine(cpassTOrdTestataOrdine );
				cpassTOrdDocumentiOrdineDao.insert(entity);
			}
		}
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	private void insertProtocollo(CpassTOrdTestataOrdine entity,CpassTOrdProtocolloOrdine protocollo) {
		protocollo.setCpassTOrdTestataOrdine(entity);
		cpassTOrdProtocolloOrdineDao.insert(protocollo);
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	private void updateProtocollo(CpassTOrdTestataOrdine entity,CpassTOrdProtocolloOrdine protocollo) {
		protocollo.setCpassTOrdTestataOrdine(entity);
		cpassTOrdProtocolloOrdineDao.update(protocollo);
	}
	/**
	 * 
	 * @param testataOrdineId
	 */
	public void deleteProtocolloByTestataordineId(UUID testataOrdineId) {
		cpassTOrdProtocolloOrdineDao.deleteProtocolloByTestataordineId(testataOrdineId);
		cpassTOrdProtocolloOrdineDao.flush();
	}
	/**
	 * 
	 * @param testataOrdine
	 * @param cpassTOrdTestataOrdine
	 * @param cpassTFornitoreNew
	 * @param cpassTEnte
	 */
	private void insertImpegni(TestataOrdine testataOrdine, CpassTOrdTestataOrdine cpassTOrdTestataOrdine, CpassTFornitore cpassTFornitoreNew,CpassTEnte cpassTEnte) {
		if (testataOrdine.getListImpegno() != null && testataOrdine.getListImpegno().size() > 0) {
			for (final Impegno impegno : testataOrdine.getListImpegno()) {
				final CpassTImpegno cpassTImpegno = CpassMappers.IMPEGNO.toEntity(impegno);
				// controllo lunghezza massima
				if (cpassTImpegno.getImpegnoDescrizione() != null && cpassTImpegno.getImpegnoDescrizione().length() > 150) {
					cpassTImpegno.setImpegnoDescrizione(cpassTImpegno.getImpegnoDescrizione().substring(0, 150));
				}
				cpassTImpegno.setCpassTEnte(cpassTEnte);
				cpassTImpegno.setCpassTFornitore(cpassTFornitoreNew);
				//CONTROLLARE LA FONTE
				final List<CpassTImpegno> cpassTImpegnoPresente = cpassTImpegnoDao.getImpegnoByChiaveLogica(cpassTImpegno.getImpegnoAnnoEsercizio(),cpassTImpegno.getImpegnoAnno(), cpassTImpegno.getImpegnoNumero(), cpassTImpegno.getCpassTEnte().getId());
				if (cpassTImpegnoPresente.isEmpty()) {
					cpassTImpegnoPresente.add(cpassTImpegnoDao.insert(cpassTImpegno));
				}
				// associato
				final CpassTOrdImpegnoAssociato cpassTOrdImpegnoAssociato = new CpassTOrdImpegnoAssociato();
				cpassTOrdImpegnoAssociato.setCpassTImpegno(cpassTImpegnoPresente.get(0));
				cpassTOrdImpegnoAssociato.setCpassTOrdTestataOrdine(cpassTOrdTestataOrdine);
				cpassTOrdImpegnoAssociato.setImpegnoAnno(cpassTImpegnoPresente.get(0).getImpegnoAnno());
				cpassTOrdImpegnoAssociato.setImpegnoAnnoEsercizio(cpassTImpegnoPresente.get(0).getImpegnoAnnoEsercizio());
				cpassTOrdImpegnoAssociato.setImpegnoNumero(cpassTImpegnoPresente.get(0).getImpegnoNumero());
				// cpassTOrdImpegnoAssociato.setImpegnoProgressivo(1);
				cpassTOrdImpegnoAssociatoDao.insert(cpassTOrdImpegnoAssociato);
				// subimpegno
				if (impegno.getSubimpegni() != null && impegno.getSubimpegni().size() > 0) {
					for (final Subimpegno subimpegno : impegno.getSubimpegni()) {
						CpassTSubimpegno cpassTSubimpegno = CpassMappers.SUBIMPEGNO.toEntity(subimpegno);

						cpassTSubimpegno.setCpassTEnte(cpassTEnte);
						cpassTSubimpegno.setCpassTFornitore(cpassTFornitoreNew);
						cpassTSubimpegno.setCpassTImpegno(cpassTImpegnoPresente.get(0));

						cpassTSubimpegno.setImpegnoAnno(impegno.getAnno());
						cpassTSubimpegno.setImpegnoAnnoEsercizio(impegno.getAnnoEsercizio());
						cpassTSubimpegno.setImpegnoNumero(impegno.getNumero());
						final List<CpassTSubimpegno> cpassTSubimpegnoPresente = cpassTSubImpegnoDao.getSubimpegnoByChiaveLogica(cpassTSubimpegno.getImpegnoAnnoEsercizio(),
								cpassTSubimpegno.getImpegnoAnno(), cpassTSubimpegno.getImpegnoNumero(), cpassTSubimpegno.getCpassTEnte().getId(),
								cpassTSubimpegno.getSubimpegnoAnno(), cpassTSubimpegno.getSubimpegnoNumero());
						if (cpassTSubimpegnoPresente == null || cpassTSubimpegnoPresente.size() == 0) {
							if (cpassTSubimpegnoPresente != null) {
								cpassTSubimpegnoPresente.add(cpassTSubImpegnoDao.insert(cpassTSubimpegno));
							}
						}
						// associato
						final CpassTOrdSubimpegnoAssociato cpassTOrdSubimpegnoAssociato = new CpassTOrdSubimpegnoAssociato();
						cpassTOrdSubimpegnoAssociato.setCpassTOrdImpegnoAssociato(cpassTOrdImpegnoAssociato);
						cpassTOrdSubimpegnoAssociato.setImpegnoAnno(cpassTImpegnoPresente.get(0).getImpegnoAnno());
						cpassTOrdSubimpegnoAssociato.setImpegnoAnnoEsercizio(cpassTImpegnoPresente.get(0).getImpegnoAnnoEsercizio());
						cpassTOrdSubimpegnoAssociato.setImpegnoNumero(cpassTImpegnoPresente.get(0).getImpegnoNumero());
						if(cpassTSubimpegnoPresente != null && !cpassTSubimpegnoPresente.isEmpty()) {
							cpassTSubimpegno = cpassTSubimpegnoPresente.get(0);
							cpassTOrdSubimpegnoAssociato.setSubimpegnoAnno(cpassTSubimpegno.getSubimpegnoAnno());
							cpassTOrdSubimpegnoAssociato.setSubimpegnoImporto(cpassTSubimpegno.getImportoAttuale());
							cpassTOrdSubimpegnoAssociato.setSubimpegnoNumero(cpassTSubimpegno.getSubimpegnoNumero());
							cpassTOrdSubimpegnoAssociato.setCpassTSubimpegno(cpassTSubimpegno);
						}
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
		return updateTestataOrdine(testataOrdine, Boolean.TRUE);
	}
	/**
	 * 
	 * @param testataOrdine
	 * @param bUpdateAssociati
	 * @return
	 */
	public TestataOrdine updateTestataOrdine(TestataOrdine testataOrdine, boolean bUpdateAssociati) {
		CpassTOrdTestataOrdine cpassTOrdTestataOrdine = CpassMappers.TESTATA_ORDINE.toEntity(testataOrdine);
		// fornitore
		CpassTFornitore cpassTFornitoreNew = cpassTOrdTestataOrdine.getCpassTFornitore();
		final Ente ente = CpassThreadLocalContainer.SETTORE_UTENTE.get().getEnte();
		cpassTFornitoreNew.setCpassTEnte(CpassMappers.ENTE.toEntity(ente));
		if (cpassTFornitoreNew.getNaturaGiuridica() != null && cpassTFornitoreNew.getNaturaGiuridica().length() > 5) {
			cpassTFornitoreNew.setNaturaGiuridica(cpassTFornitoreNew.getNaturaGiuridica().substring(0, 5));
		}
		final CpassTFornitore cpassTFornitoreOld = cpassTFornitoreDao.getFornitoreByCodice(cpassTFornitoreNew.getCodice(), getId(ente));
		if (cpassTFornitoreOld == null) {
			cpassTFornitoreNew = cpassTFornitoreDao.insert(cpassTFornitoreNew);
		} else {
			cpassTFornitoreNew.setId(cpassTFornitoreOld.getId());
			cpassTFornitoreNew = cpassTFornitoreDao.update(cpassTFornitoreNew);
		}
		cpassTOrdTestataOrdine.setCpassTFornitore(cpassTFornitoreNew);
		// mi salvo l'oggetto protocollo
		CpassTOrdProtocolloOrdine cpassTOrdProtocolloOrdineNew = new CpassTOrdProtocolloOrdine();

		if(cpassTOrdTestataOrdine.getCpassTOrdProtocolloOrdines() != null && cpassTOrdTestataOrdine.getCpassTOrdProtocolloOrdines().size() > 0) {
			cpassTOrdProtocolloOrdineNew = cpassTOrdTestataOrdine.getCpassTOrdProtocolloOrdines().get(0);
		}
		// aggiorno testata
		cpassTOrdTestataOrdine = cpassTOrdTestataOrdineDao.update(cpassTOrdTestataOrdine,false);
		cpassTOrdTestataOrdineDao.flush();
		if (bUpdateAssociati) {
			// aggiorno impegni/subimpegni associati
			final CpassTFornitore cpassTFornitore = cpassTOrdTestataOrdine.getCpassTFornitore();
			final Optional<CpassTSettore> optionalSettore = cpassTSettoreDao.findOne(testataOrdine.getSettore().getId());
			//Optional<CpassTSettore> optionalSettore = cpassTSettoreDao.findById(testataOrdine.getSettore().getId());
			final CpassTEnte cpassTEnte = optionalSettore.orElseThrow(() -> new NotFoundException("settore")).getCpassTEnte();
			// cancello associati
			deleteAssociati(testataOrdine);
			insertImpegni(testataOrdine, cpassTOrdTestataOrdine, cpassTFornitore, cpassTEnte);
		}

		if(cpassTOrdProtocolloOrdineNew != null) {
			cpassTOrdProtocolloOrdineNew.setCpassTOrdTestataOrdine(cpassTOrdTestataOrdine);
			cpassTOrdProtocolloOrdineDao.save(cpassTOrdProtocolloOrdineNew);
		}
		updateDocumenti(testataOrdine,cpassTOrdTestataOrdine);
		return testataOrdine;
	}
	/**
	 * 
	 * @param testataOrdine
	 */
	public void deleteAssociati(TestataOrdine testataOrdine) {
		final List<CpassTOrdImpegnoAssociato> impegnoAssociatos = cpassTOrdImpegnoAssociatoDao.getImpegniAssociati(testataOrdine.getId());
		if (impegnoAssociatos != null) {
			for (final CpassTOrdImpegnoAssociato cpassTOrdImpegnoAssociato : impegnoAssociatos) {

				final List<CpassTOrdSubimpegnoAssociato> subimpegnoAssociatos = cpassTOrdSubimpegnoAssociatoDao.getSubimpegniAssociati(cpassTOrdImpegnoAssociato.getId());
				if (subimpegnoAssociatos != null) {
					for (final CpassTOrdSubimpegnoAssociato cpassTOrdSubimpegnoAssociato : subimpegnoAssociatos) {
						cpassTOrdSubimpegnoAssociatoDao.delete(cpassTOrdSubimpegnoAssociato.getId());
					}
				}
			}
		}
		cpassTOrdImpegnoAssociatoDao.deleteByTestataOrdine(testataOrdine.getId());
		cpassTOrdImpegnoAssociatoDao.flushAndClear(); // per poterli re-inserire
	}
	/**
	 * 
	 * @param testataOrdineId
	 */
	public void deleteAssociatiById(UUID testataOrdineId) {
		final List<CpassTOrdImpegnoAssociato> impegnoAssociatos = cpassTOrdImpegnoAssociatoDao.getImpegniAssociati(testataOrdineId);
		if (impegnoAssociatos != null) {
			for (final CpassTOrdImpegnoAssociato cpassTOrdImpegnoAssociato : impegnoAssociatos) {

				final List<CpassTOrdSubimpegnoAssociato> subimpegnoAssociatos = cpassTOrdSubimpegnoAssociatoDao.getSubimpegniAssociati(cpassTOrdImpegnoAssociato.getId());
				if (subimpegnoAssociatos != null) {
					for (final CpassTOrdSubimpegnoAssociato cpassTOrdSubimpegnoAssociato : subimpegnoAssociatos) {
						cpassTOrdSubimpegnoAssociatoDao.delete(cpassTOrdSubimpegnoAssociato.getId());
					}
				}
			}
		}
		cpassTOrdImpegnoAssociatoDao.deleteByTestataOrdine(testataOrdineId);
		cpassTOrdImpegnoAssociatoDao.flushAndClear(); // per poterli re-inserire
	}
	/**
	 * 
	 * @param annoOrdineDa
	 * @param numeroOrdineDa
	 * @param annoOrdineA
	 * @param numeroOrdineA
	 * @param dataEmissioneDa
	 * @param dataEmissioneA
	 * @param testataOrdine
	 * @param impegno
	 * @param subimpegno
	 * @param rigaOrdine
	 * @param settoreEmittente
	 * @param settore
	 * @param settoreIndirizzo
	 * @return
	 */
	public long countRicercaOrdini(Integer annoOrdineDa, Integer numeroOrdineDa, Integer annoOrdineA, Integer numeroOrdineA, Date dataEmissioneDa, Date dataEmissioneA, TestataOrdine testataOrdine,Impegno impegno, Subimpegno subimpegno, RigaOrdine rigaOrdine, Settore settoreEmittente, Settore settore, SettoreIndirizzo settoreIndirizzo) {

		String numeroProvvedimento = null;
		if (testataOrdine.getProvvedimento().getNumero() != null ) {
			numeroProvvedimento = testataOrdine.getProvvedimento().getNumero().toString();
		}
		final long count = cpassTOrdTestataOrdineDao.countRicercaOrdini(
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
				getId(testataOrdine.getTipoProceduraOrd()),
				testataOrdine.getNumeroProcedura(),
				getId(testataOrdine.getSettore()),
				getId(settore),
				getId(testataOrdine.getFornitore()),
				testataOrdine.getProvvedimento().getAnno(),
				numeroProvvedimento,
				getId(impegno),
				getId(subimpegno),
				getId(rigaOrdine.getOds()),
				getId(rigaOrdine.getOds().getCpv()),
				settoreEmittente,
				getId(settoreIndirizzo)
				);

		return count;

	}
	/**
	 * 
	 * @param page
	 * @param size
	 * @param sort
	 * @param annoOrdineDa
	 * @param numeroOrdineDa
	 * @param annoOrdineA
	 * @param numeroOrdineA
	 * @param dataEmissioneDa
	 * @param dataEmissioneA
	 * @param testataOrdine
	 * @param impegno
	 * @param subimpegno
	 * @param rigaOrdine
	 * @param settoreEmittente
	 * @param settore
	 * @param settoreIndirizzo
	 * @return
	 */
	public PagedList<TestataOrdine> getRicercaOrdini(int page, int size, Sort sort,
			Integer annoOrdineDa, Integer numeroOrdineDa, Integer annoOrdineA, Integer numeroOrdineA, Date dataEmissioneDa, Date dataEmissioneA, TestataOrdine testataOrdine,Impegno impegno, Subimpegno subimpegno, RigaOrdine rigaOrdine, Settore settoreEmittente, Settore settore, SettoreIndirizzo settoreIndirizzo
			) {
		final Utente utenteConnesso = CpassThreadLocalContainer.UTENTE_CONNESSO.get();
		final Settore settoreCorrente = CpassThreadLocalContainer.SETTORE_UTENTE.get();
		String sortField = null;
		String sortDirection = null;
		if (sort != null) {
			if (TestataOrdineSort.byModelName(sort.getField())!=null) {
				sortField = TestataOrdineSort.byModelName(sort.getField()).getQueryName();
			}
			sortDirection = sort.getOrder().getSortDirection();
		}

		String numeroProvvedimento = null;
		if (testataOrdine.getProvvedimento().getNumero() != null ) {
			numeroProvvedimento = testataOrdine.getProvvedimento().getNumero().toString();
		}
		final Page<CpassTOrdTestataOrdine> cpassTOrdTestataOrdines = cpassTOrdTestataOrdineDao.findPaginated(
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
				getId(testataOrdine.getTipoProceduraOrd()),
				testataOrdine.getNumeroProcedura(),
				getId(testataOrdine.getSettore()),
				getId(settore),
				getId(testataOrdine.getFornitore()),
				testataOrdine.getProvvedimento().getAnno(),
				numeroProvvedimento,
				getId(impegno),
				getId(subimpegno),
				getId(rigaOrdine.getOds()),
				getId(rigaOrdine.getOds().getCpv()),
				settoreEmittente,
				getId(settoreIndirizzo),
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
		final PagedList<TestataOrdine> pagedList = toPagedList(cpassTOrdTestataOrdines, page, size, CpassMappers.TESTATA_ORDINE::toModel);
		return pagedList;
	}

	/**
	 *
	 * @param evasioneId
	 * @return
	 */
	public List<TestataOrdine> findTestateOrdineByEvasioneId(UUID evasioneId){
		final List<CpassTOrdTestataOrdine> listaOrdini = cpassTOrdTestataOrdineDao.findTestateOrdineByEvasioneId(evasioneId);
		final List<TestataOrdine> listaris = CpassMappers.TESTATA_ORDINE.toModels(listaOrdini);
		return listaris;
	}
	/**
	 * 
	 * @param idOrdine
	 * @param idEvasione
	 * @return
	 */
	public List<RigaOrdine> findRigheByOrdineAndEvasioneId(UUID idOrdine, UUID idEvasione) {
		final List<CpassTOrdRigaOrdine> listaRighe = cpassTOrdTestataOrdineDao.findRigheByOrdineAndEvasioneId(idOrdine, idEvasione);
		final List<RigaOrdine> listaRis = CpassMappers.RIGA_ORDINE.toModels(listaRighe);
		return listaRis;
	}
	/**
	 * 
	 * @param evasioneId
	 * @return
	 */
	public List<TestataOrdine> findAnteprimeOrdineByEvasioneId(UUID evasioneId){
		final List<CpassTOrdTestataOrdine> listaOrdini = cpassTOrdTestataOrdineDao.findTestateOrdineByEvasioneId(evasioneId);
		final List<TestataOrdine> listaris = CpassMappers.TESTATA_ORDINE.toModels(listaOrdini);
		return listaris;
	}
	/**
	 * 
	 * @param destinatarioEvasioneId
	 * @return
	 */
	public List<TestataOrdine> findTestateOrdineByDestinatarioEvasioneId(UUID destinatarioEvasioneId) {
		final List<CpassTOrdTestataOrdine> listaOrdini = cpassTOrdTestataOrdineDao.findTestateOrdineByDestinatarioEvasioneId(destinatarioEvasioneId);
		final List<TestataOrdine> listaris = CpassMappers.TESTATA_ORDINE.toModels(listaOrdini);
		return listaris;
	}
	/**
	 * 
	 * @param rigaEvasioneId
	 * @return
	 */
	public List<TestataOrdine> findTestateOrdineByRigaEvasioneId(UUID rigaEvasioneId) {
		final List<CpassTOrdTestataOrdine> listaOrdini = cpassTOrdTestataOrdineDao.findTestateOrdineByRigaEvasioneId(rigaEvasioneId);
		final List<TestataOrdine> listaris = CpassMappers.TESTATA_ORDINE.toModels(listaOrdini);
		return listaris;
	}
	/**
	 * Find by anno e numero e stato
	 *
	 * @param anno the anno
	 * @param numero the numero
	 * @param stato the stato
	 * @param enteId the ente
	 * @return the TestataOrdine instance
	 */
	public TestataOrdine getByAnnoENumeroEStato(Integer anno, Integer numero, String stato, UUID enteId) {
		final Optional<CpassTOrdTestataOrdine> optionalTestata = cpassTOrdTestataOrdineDao.findByAnnoENumeroEStato(anno, numero, stato, enteId);
		final TestataOrdine testataOrdine = optionalTestata.map(CpassMappers.TESTATA_ORDINE::toModel).orElse(null);
		return testataOrdine;
	}
	/**
	 * Updates the testataOrdine
	 *
	 * @param testataOrdine the testataOrdine
	 * @return the model instance
	 */
	public TestataOrdine updateTestataOrdineLigth(TestataOrdine model) {
		final CpassTOrdTestataOrdine entity = CpassMappers.TESTATA_ORDINE.toEntity(model);
		final TestataOrdine result = CpassMappers.TESTATA_ORDINE.toModel(cpassTOrdTestataOrdineDao.update(entity));
		return result;
	}
	/**
	 * 
	 * @param testataOrdineId
	 * @return
	 */
	public List<TestataRda> getTestataRdaByTestataOrdineId(UUID testataOrdineId){
		final List<CpassTOrdTestataRda> entities = cpassTOrdTestataRdaDao.findTestataRdaByOrderId(testataOrdineId);
		final List<TestataRda> models = CpassMappers.TESTATA_RDA.toModels(entities);
		return models;
	}
	/**
	 * 
	 * @param model
	 * @return
	 */
	public OrdRdaOrdine saveOrdRdaOrdine(OrdRdaOrdine model){
		final CpassROrdRdaOrdine entity = CpassMappers.RDA_ORDINE.toEntity(model);
		model = CpassMappers.RDA_ORDINE.toModel(cpassROrdRdaOrdineDao.insert(entity));
		return model;
	}
	/**
	 * 
	 * @param enteId
	 * @return
	 */
	public List<Sezione> getSezioniByEnte(UUID enteId) {
		final List<CpassTOrdSezione> entity = cpassTOrdSezioneDao.getSezioniByEnte(enteId);
		final List<Sezione> lista = CpassMappers.SEZIONE.toModels(entity);
		return lista;
	}
	/**
	 * 
	 * @param page
	 * @param size
	 * @param sort
	 * @param sez
	 * @return
	 */
	public PagedList<Sezione> getSezionii(int page, int size, Sort sort,Sezione sez) {
		final Page<CpassTOrdSezione> cpassTOrdSezione = cpassTOrdSezioneDao.findPaginated(sez.getSezioneCodice(),
				sez.getSezioneDescrizione(),
				getId(sez.getEnte()),
				page,
				size,
				null,
				null
				);
		final PagedList<Sezione> pagedList = toPagedList(cpassTOrdSezione, page, size, CpassMappers.SEZIONE::toModel);
		return pagedList;
	}
	/**
	 * 
	 * @param id
	 * @return
	 */
	public Optional<Sezione> getSezioneById(Integer id) {
		return cpassTOrdSezioneDao.findOne(id).map(CpassMappers.SEZIONE::toModel);
	}
	/**
	 * 
	 * @param testataOrdineId
	 * @param testataRdaId
	 */
	public void deleteRelRdaOrdine (UUID testataOrdineId, UUID testataRdaId) {
		cpassROrdRdaOrdineDao.deleteByTestataOrdineTestataRda(testataOrdineId, testataRdaId);
	}
	/**
	 * 
	 * @param testataOrdineId
	 * @param minimal
	 * @return
	 */
	public List<DocumentiOrdine> getDocumentiByOrdineTestataId(UUID testataOrdineId,boolean minimal) {
		final List<CpassTOrdDocumentiOrdine> entities = cpassTOrdDocumentiOrdineDao.getDocumentiByOrdineTestataId(testataOrdineId);
		List<DocumentiOrdine> models = null;
		if(minimal) {
			models = CpassMappers.DOCUMENTO_ORDINE_MINIMAL.toModels(entities);
		}else {
			models = CpassMappers.DOCUMENTO_ORDINE.toModels(entities);
		}
		return models;
	}
	/**
	 * 
	 * @param id
	 * @return
	 */
	public DocumentiOrdine getDocumentoById(Integer id) {
		final Optional<CpassTOrdDocumentiOrdine> entityOptional = cpassTOrdDocumentiOrdineDao.findOne(id);
		if (entityOptional.isPresent()) {
			final CpassTOrdDocumentiOrdine entity = entityOptional.get();
			final DocumentiOrdine model = CpassMappers.DOCUMENTO_ORDINE.toModel(entity);
			final String fileDocumento = Base64.getEncoder().encodeToString(entity.getFile());
			model.setFileDocumento(fileDocumento);
			return model;
		} else {
			throw new NotFoundException("documenti ordine");
		}
	}
	/**
	 * 
	 * @param stato
	 * @param enteId
	 * @return
	 */
	public List<TestataOrdine> findByStato(String stato, UUID enteId) {
		final List<CpassTOrdTestataOrdine> testateOrdine = cpassTOrdTestataOrdineDao.findByStato(stato, enteId);
		return testateOrdine.stream()
				.map(CpassMappers.TESTATA_ORDINE::toModel)
				.collect(Collectors.toList());
	}
	/**
	 * 
	 * @param testataOrdineId
	 */
	public void updateTestataordineById(UUID testataOrdineId) {
		cpassTOrdTestataOrdineDao.updateTestataOrdine(testataOrdineId);
	}
}
