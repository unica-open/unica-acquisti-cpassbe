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
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.CpassROdsDatiContabiliDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.ord.CpassTOrdImpegnoAssociatoDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.ord.CpassTOrdRigaOrdineDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.ord.CpassTOrdSubimpegnoAssociatoDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.ord.evasione.CpassTOrdRigaEvasioneDao;
import it.csi.cpass.cpassbe.ejb.entity.CpassDOggettiSpesa;
import it.csi.cpass.cpassbe.ejb.entity.CpassROdsDatiContabili;
import it.csi.cpass.cpassbe.ejb.entity.ord.CpassTOrdImpegnoAssociato;
import it.csi.cpass.cpassbe.ejb.entity.ord.CpassTOrdRigaOrdine;
import it.csi.cpass.cpassbe.ejb.entity.ord.CpassTOrdSubimpegnoAssociato;
import it.csi.cpass.cpassbe.ejb.exception.NotFoundException;
import it.csi.cpass.cpassbe.ejb.mapper.CpassMappers;
import it.csi.cpass.cpassbe.ejb.util.ConstantsCPassStato;
import it.csi.cpass.cpassbe.ejb.util.ConstantsDecodifiche;
import it.csi.cpass.cpassbe.lib.dto.Impegno;
import it.csi.cpass.cpassbe.lib.dto.Ods;
import it.csi.cpass.cpassbe.lib.dto.OdsDatiContabili;
import it.csi.cpass.cpassbe.lib.dto.Settore;
import it.csi.cpass.cpassbe.lib.dto.Subimpegno;
import it.csi.cpass.cpassbe.lib.dto.Utente;
import it.csi.cpass.cpassbe.lib.dto.ord.Destinatario;
import it.csi.cpass.cpassbe.lib.dto.ord.ImpegnoAssociato;
import it.csi.cpass.cpassbe.lib.dto.ord.RigaOrdine;
import it.csi.cpass.cpassbe.lib.dto.ord.SubImpegnoAssociato;
import it.csi.cpass.cpassbe.lib.dto.ord.TestataOrdine;
import it.csi.cpass.cpassbe.lib.dto.pba.SettoreInterventi;
import it.csi.cpass.cpassbe.lib.util.threadlocal.CpassThreadLocalContainer;

@ApplicationScoped
public class RigaOrdineDad extends BaseDad {

	@Inject
	private CpassTOrdRigaOrdineDao cpassTOrdRigaOrdineDao;
	@Inject
	private CpassTOrdImpegnoAssociatoDao cpassTOrdImpegnoAssociatoDao;
	@Inject
	private CpassTOrdSubimpegnoAssociatoDao cpassTOrdSubimpegnoAssociatoDao;
	@Inject
	private CpassTOrdRigaEvasioneDao cpassTOrdRigaEvasioneDao;
	@Inject
	private CpassROdsDatiContabiliDao cpassROdsDatiContabiliDao;
	/**
	 * 
	 * @param key
	 * @return
	 */
	public RigaOrdine findOne(UUID key) {
		final Optional<CpassTOrdRigaOrdine> cpassTRigaOrdineOptional = cpassTOrdRigaOrdineDao.findOne(key);
		if (cpassTRigaOrdineOptional.isPresent()) {
			final CpassTOrdRigaOrdine cpassTOrdRigaOrdine = cpassTRigaOrdineOptional.get();
			return CpassMappers.RIGA_ORDINE.toModel(cpassTOrdRigaOrdine);
		} else {
			throw new NotFoundException("riga ordine");
		}
	}
	/**
	 * 
	 * @param idDestinatario
	 * @return
	 */
	public List<RigaOrdine> getRigheByDestinatario(UUID idDestinatario) {
		return getRigheByDestinatarioAndprogressivo(idDestinatario, null);
	}
	/**
	 * 
	 * @param idDestinatario
	 * @param progressivo
	 * @return
	 */
	public List<RigaOrdine> getRigheByDestinatarioAndprogressivo(UUID idDestinatario, Integer progressivo) {
		final List<CpassTOrdRigaOrdine> righeOrdine = cpassTOrdRigaOrdineDao.findByIdDestinatario(idDestinatario, progressivo);
		return CpassMappers.RIGA_ORDINE.toModels(righeOrdine);
	}
	/**
	 * 
	 * @param idDestinatarios
	 * @return
	 */
	public List<RigaOrdine> getRigheByDestinatarios(List<UUID> idDestinatarios) {
		final List<CpassTOrdRigaOrdine> righeOrdine = cpassTOrdRigaOrdineDao.findByIdDestinatarios(idDestinatarios);
		return CpassMappers.RIGA_ORDINE.toModels(righeOrdine);
	}

	/**
	 * Inserts the rigaOrdine
	 *
	 * @param rigaOrdine to insert
	 * @return the model instance
	 */
	public RigaOrdine saveRigaOrdine(RigaOrdine rigaOrdine) {
		final CpassTOrdRigaOrdine rigaOrdineEntity = CpassMappers.RIGA_ORDINE.toEntity(rigaOrdine);
		//incremento il progressivo
		if(rigaOrdine.getProgressivo() == null || rigaOrdine.getProgressivo().equals(0)) {
			Integer maxProgressivo = 0;
			final List<CpassTOrdRigaOrdine> righeOrdine = cpassTOrdRigaOrdineDao.findByIdDestinatario(rigaOrdine.getDestinatario().getId(),null);
			for(final CpassTOrdRigaOrdine riga : righeOrdine) {
				if(riga.getProgressivo() > maxProgressivo) {
					maxProgressivo = riga.getProgressivo();
				}
			}
			maxProgressivo++;
			rigaOrdineEntity.setProgressivo(maxProgressivo);
		}
		final RigaOrdine result = CpassMappers.RIGA_ORDINE.toModel(cpassTOrdRigaOrdineDao.insert(rigaOrdineEntity));
		cpassTOrdRigaOrdineDao.flush();
		return result;
	}

	/**
	 * Updates the rigaOrdine
	 *
	 * @param rigaOrdine to update
	 * @return the model instance
	 */
	public RigaOrdine updateRigaOrdine(RigaOrdine rigaOrdine) {
		final CpassTOrdRigaOrdine rigaOrdineEntity = CpassMappers.RIGA_ORDINE.toEntity(rigaOrdine);
		final RigaOrdine result = CpassMappers.RIGA_ORDINE.toModel(cpassTOrdRigaOrdineDao.update(rigaOrdineEntity));
		return result;
	}
	/**
	 * 
	 * @param idRiga
	 */
	public void deleteRiga(UUID idRiga) {
		cpassTOrdRigaOrdineDao.delete(idRiga);
	}
	/**
	 * 
	 * @param idDestinatario
	 */
	public void deleteRigaByDestinatario(UUID idDestinatario) {
		cpassTOrdRigaOrdineDao.deleteByDestinatario(idDestinatario);
	}
	/**
	 * 
	 * @param idTestataOrdine
	 * @return
	 */
	public List<ImpegnoAssociato> getImpegnoAssociatoOrdine(UUID idTestataOrdine) {
		final List<ImpegnoAssociato> result = new ArrayList<>();
		final List<CpassTOrdImpegnoAssociato> impegnoAssociatos = cpassTOrdImpegnoAssociatoDao.getImpegniAssociati(idTestataOrdine);
		if (impegnoAssociatos != null) {
			for (final CpassTOrdImpegnoAssociato cpassTOrdImpegnoAssociato : impegnoAssociatos) {
				final ImpegnoAssociato toAdd = CpassMappers.IMPEGNO_ASSOCIATO.toModel(cpassTOrdImpegnoAssociato);
				// subimpegni
				final List<CpassTOrdSubimpegnoAssociato> subimpegnoAssociatos = cpassTOrdSubimpegnoAssociatoDao
						.getSubimpegniAssociati(cpassTOrdImpegnoAssociato.getImpegnoAssociatoId());

				if (subimpegnoAssociatos != null) {
					final List<SubImpegnoAssociato> subToAdd = new ArrayList<>();
					for (final CpassTOrdSubimpegnoAssociato cpassTOrdSubimpegnoAssociato : subimpegnoAssociatos) {
						subToAdd.add(CpassMappers.SUBIMPEGNO_ASSOCIATO.toModel(cpassTOrdSubimpegnoAssociato));
					}
					toAdd.setSubimpegniAssociati(subToAdd);
				}
				result.add(toAdd);
			}
		}
		return result;
	}
	/**
	 * 
	 * @param idImpegnoAssociato
	 * @return
	 */
	public List<SubImpegnoAssociato> getSubImpegnoAssociatoOrdine(UUID idImpegnoAssociato) {
		final List<CpassTOrdSubimpegnoAssociato> impegniAssociatiEntity = cpassTOrdSubimpegnoAssociatoDao.getSubimpegniAssociati(idImpegnoAssociato);
		final List<SubImpegnoAssociato> result = CpassMappers.SUBIMPEGNO_ASSOCIATO.toModels(impegniAssociatiEntity);
		return result;
	}
	/**
	 * 
	 * @param testataOrdineId
	 * @param rigaOrdineId
	 * @return
	 */
	public List<RigaOrdine> findByTestataOrdine(UUID testataOrdineId, UUID rigaOrdineId) {
		final List<CpassTOrdRigaOrdine> cpassTOrdRigaOrdines = cpassTOrdRigaOrdineDao.findByTestataOrdine(testataOrdineId, rigaOrdineId);
		final List<RigaOrdine> rigaOrdines = CpassMappers.RIGA_ORDINE.toModels(cpassTOrdRigaOrdines);
		return rigaOrdines;
	}
	/**
	 * 
	 * @param testataOrdineId
	 * @return
	 */
	public List<RigaOrdine> findByTestataOrdine(UUID testataOrdineId) {
		return findByTestataOrdine(testataOrdineId, null);
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
	 * @param destinatario
	 * @param impegno
	 * @param subimpegno
	 * @param rigaOrdine
	 * @param odsList
	 * @return
	 */
	public long countRigheDaEvadere(Integer annoOrdineDa, Integer numeroOrdineDa, Integer annoOrdineA, Integer numeroOrdineA, Date dataEmissioneDa, Date dataEmissioneA, TestataOrdine testataOrdine, Destinatario destinatario,Impegno impegno, Subimpegno subimpegno, RigaOrdine rigaOrdine, List<Ods> odsList) {
		String numeroProvvedimento = null;
		String codiceProvvedimentoTipo = null;
		if(testataOrdine.getProvvedimento()!= null){
			if (testataOrdine.getProvvedimento().getNumero() != null ) {
				numeroProvvedimento = testataOrdine.getProvvedimento().getNumero().toString();
			}
			if(testataOrdine.getProvvedimento().getProvvedimentoTipo() != null) {
				codiceProvvedimentoTipo = testataOrdine.getProvvedimento().getProvvedimentoTipo().getCodice();
			}
		}

		final List<Integer> oggettoSpesaIdList = new ArrayList<>();
		if (odsList != null && odsList.size()>0) {
			for(final Ods el : odsList) {
				final Integer odsId = getId(el);
				if (odsId != null) {
					oggettoSpesaIdList.add(odsId);
				}
			}
		}
		Integer rigaOrdine_cpvId = null;
		if (rigaOrdine!=null) {
			final Integer rigaOrdine_odsId = getId(rigaOrdine.getOds());
			if(rigaOrdine_odsId !=null ) {
				oggettoSpesaIdList.add(rigaOrdine_odsId);
			}
			rigaOrdine_cpvId = getId(rigaOrdine.getOds().getCpv());
		}
		final long count = cpassTOrdRigaOrdineDao.countDaEvadere(
				annoOrdineDa,
				numeroOrdineDa,
				annoOrdineA,
				numeroOrdineA,
				dataEmissioneDa,
				dataEmissioneA,
				getId(testataOrdine.getTipoOrdine()),
				testataOrdine.getLottoAnno(),
				testataOrdine.getLottoNumero(),
				getId(testataOrdine.getTipoProceduraOrd()),
				testataOrdine.getNumeroProcedura(),
				getId(testataOrdine.getSettore()),
				getId(destinatario),
				getId(testataOrdine.getFornitore()),
				testataOrdine.getProvvedimento().getAnno(),
				numeroProvvedimento,
				codiceProvvedimentoTipo,
				getId(impegno),
				getId(subimpegno),
				oggettoSpesaIdList,
				rigaOrdine_cpvId);
		return count;
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
	 * @param destinatario
	 * @param impegno
	 * @param subimpegno
	 * @param rigaOrdine
	 * @param odsList
	 * @return
	 */
	public List<RigaOrdine> getRigheDaEvadere(
			Integer annoOrdineDa, Integer numeroOrdineDa, Integer annoOrdineA, Integer numeroOrdineA, Date dataEmissioneDa, Date dataEmissioneA, TestataOrdine testataOrdine, Destinatario destinatario,Impegno impegno, Subimpegno subimpegno, RigaOrdine rigaOrdine, List<Ods> odsList){
		final Utente utenteConnesso = CpassThreadLocalContainer.UTENTE_CONNESSO.get();
		final Settore settoreCorrente = CpassThreadLocalContainer.SETTORE_UTENTE.get();

		String numeroProvvedimento = null;
		String provvedimentoTipoCodice = null;
		if(testataOrdine.getProvvedimento().getProvvedimentoTipo() != null) {
			provvedimentoTipoCodice = testataOrdine.getProvvedimento().getProvvedimentoTipo().getCodice();
		}
		if (testataOrdine.getProvvedimento().getNumero() != null ) {
			numeroProvvedimento = testataOrdine.getProvvedimento().getNumero().toString();
		}
		
		final List<Integer> oggettoSpesaIdList = new ArrayList<>();
		if (odsList != null && odsList.size()>0) {
			for(final Ods el : odsList) {
				final Integer odsId = getId(el);
				if (odsId != null) {
					oggettoSpesaIdList.add(odsId);
				}
			}
		}
		Integer rigaOrdine_cpvId = null;
		if (rigaOrdine!=null) {
			final Integer rigaOrdine_odsId = getId(rigaOrdine.getOds());
			if(rigaOrdine_odsId !=null ) {
				oggettoSpesaIdList.add(rigaOrdine_odsId);
			}
			rigaOrdine_cpvId = getId(rigaOrdine.getOds().getCpv());
		}
		final List<CpassTOrdRigaOrdine> cpassTOrdRigaOrdines = cpassTOrdRigaOrdineDao.findDaEvadere(
				annoOrdineDa,
				numeroOrdineDa,
				annoOrdineA,
				numeroOrdineA,
				dataEmissioneDa,
				dataEmissioneA,
				getId(testataOrdine.getTipoOrdine()),
				testataOrdine.getLottoAnno(),
				testataOrdine.getLottoNumero(),
				getId(testataOrdine.getTipoProceduraOrd()),
				testataOrdine.getNumeroProcedura(),
				getId(testataOrdine.getSettore()),
				getId(destinatario),
				getId(testataOrdine.getFornitore()),
				testataOrdine.getProvvedimento().getAnno(),
				numeroProvvedimento,
				provvedimentoTipoCodice,
				getId(impegno),
				getId(subimpegno),
				oggettoSpesaIdList,
				rigaOrdine_cpvId,
				Boolean.TRUE,
				utenteConnesso.getCodiceFiscale()
				,utenteConnesso.getId()
				,(settoreCorrente!=null ? settoreCorrente.getId() : null)
				);

		final List<RigaOrdine> listaris = CpassMappers.RIGA_ORDINE.toModels(cpassTOrdRigaOrdines);

		for(final RigaOrdine riga: listaris) {
			if (riga.getStato() != null ) {
				if (riga.getStato().getCodice().equals(ConstantsCPassStato.StatoOrdineEvasioneEnum.RIGA_ORDINE_DA_EVADERE.getCostante())) {
					riga.setImportoDaEvadere(riga.getImportoTotale());
				} else if (riga.getStato().getCodice().equals(ConstantsCPassStato.StatoOrdineEvasioneEnum.RIGA_ORDINE_EVASA_PARZIALMENTE.getCostante())) {
					final BigDecimal totaleEvaso = cpassTOrdRigaEvasioneDao.calcolaTotale(riga.getId());
					riga.setImportoDaEvadere(riga.getImportoTotale().subtract(totaleEvaso));
				}
			}

			SettoreInterventi tipoAcquisto = null;
			if(riga.getDestinatario() != null && riga.getDestinatario().getTestataOrdine() != null) {
				tipoAcquisto = riga.getDestinatario().getTestataOrdine().getTipoAcquisto();
			}

			if(tipoAcquisto != null) {
				if(tipoAcquisto.getCodice().equals(ConstantsDecodifiche.SettoreInterventiEnum.FORNITURE.getCodice())) {
					final BigDecimal totaleQuantitaEvasa = cpassTOrdRigaEvasioneDao.calcolaQuantitaEvasa(riga.getId());
					riga.setQuantitaEvadibile(riga.getQuantita().subtract(totaleQuantitaEvasa));
				}
			}
		}
		return listaris;
	}
	/**
	 * 
	 * @param oggettiSpesaId
	 * @return
	 */
	public List<OdsDatiContabili> getOdsDatiContabiliByOds(Integer oggettiSpesaId) {
		final List<CpassROdsDatiContabili> entities = cpassROdsDatiContabiliDao.getCpassROdsDatiContabiliByOds(oggettiSpesaId);
		final List<OdsDatiContabili> models = CpassMappers.ODS_DATI_CONTABILI.toModels(entities);
		return models;
	}
	/**
	 * 
	 * @param rigaOrdine
	 */
	public void saveOdsDatiContabili(RigaOrdine rigaOrdine) {
		if (rigaOrdine == null || rigaOrdine.getOds()==null || rigaOrdine.getPrezzoUnitario()==null) {
			return;
		}
		final CpassDOggettiSpesa cpassDOggettiSpesa =  CpassMappers.OGGETTO_SPESA.toEntity(rigaOrdine.getOds());
		final List<CpassROdsDatiContabili> entities = cpassROdsDatiContabiliDao.getCpassROdsDatiContabiliByOds(cpassDOggettiSpesa.getId());
		CpassROdsDatiContabili cpassROdsDatiContabili = null;
		if(entities==null || entities.isEmpty()) {
			// inserimento
			cpassROdsDatiContabili = new CpassROdsDatiContabili();
			cpassROdsDatiContabili.setCpassDOggettiSpesa(cpassDOggettiSpesa);
			cpassROdsDatiContabili.setPrezzoMassimo(rigaOrdine.getPrezzoUnitario());
			cpassROdsDatiContabili.setPrezzoMinimo(rigaOrdine.getPrezzoUnitario());
		}else {
			cpassROdsDatiContabili = entities.get(0);
			cpassROdsDatiContabili.setPrezzoMassimo(rigaOrdine.getPrezzoUnitario().compareTo(cpassROdsDatiContabili.getPrezzoMassimo())>0 ? rigaOrdine.getPrezzoUnitario() : cpassROdsDatiContabili.getPrezzoMassimo());
			cpassROdsDatiContabili.setPrezzoMinimo(rigaOrdine.getPrezzoUnitario().compareTo(cpassROdsDatiContabili.getPrezzoMinimo())<0 ? rigaOrdine.getPrezzoUnitario() : cpassROdsDatiContabili.getPrezzoMinimo());
		}
		cpassROdsDatiContabili.setUltimoPrezzo(rigaOrdine.getPrezzoUnitario());
		cpassROdsDatiContabiliDao.save(cpassROdsDatiContabili);
	}
	/**
	 * 
	 * @param testataOrdineId
	 */
	public void deleteRigaOrdFromTestataordine(UUID testataOrdineId) {
		cpassTOrdRigaOrdineDao.deleteFromTestataordine(testataOrdineId);
	}
	/**
	 * 
	 * @param testataOrdineId
	 */
	public void updateRigaOrdFromTestataordine(UUID testataOrdineId) {
		cpassTOrdRigaOrdineDao.updateFromTestataordine(testataOrdineId);
	}

}
