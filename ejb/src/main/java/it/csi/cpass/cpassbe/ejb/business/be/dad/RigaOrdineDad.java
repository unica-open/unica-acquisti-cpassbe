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

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.ord.CpassTOrdImpegnoAssociatoDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.ord.CpassTOrdRigaOrdineDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.ord.CpassTOrdSubimpegnoAssociatoDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.ord.evasione.CpassTOrdRigaEvasioneDao;
import it.csi.cpass.cpassbe.ejb.entity.ord.CpassTOrdImpegnoAssociato;
import it.csi.cpass.cpassbe.ejb.entity.ord.CpassTOrdRigaOrdine;
import it.csi.cpass.cpassbe.ejb.entity.ord.CpassTOrdSubimpegnoAssociato;
import it.csi.cpass.cpassbe.ejb.mapper.CpassMappers;
import it.csi.cpass.cpassbe.ejb.util.ConstantsCPassStatoElOrdine;
import it.csi.cpass.cpassbe.ejb.util.CpassThreadLocalContainer;
import it.csi.cpass.cpassbe.lib.dto.Impegno;
import it.csi.cpass.cpassbe.lib.dto.Settore;
import it.csi.cpass.cpassbe.lib.dto.Subimpegno;
import it.csi.cpass.cpassbe.lib.dto.Utente;
import it.csi.cpass.cpassbe.lib.dto.ord.Destinatario;
import it.csi.cpass.cpassbe.lib.dto.ord.ImpegnoAssociato;
import it.csi.cpass.cpassbe.lib.dto.ord.OggettiSpesa;
import it.csi.cpass.cpassbe.lib.dto.ord.RigaOrdine;
import it.csi.cpass.cpassbe.lib.dto.ord.SubImpegnoAssociato;
import it.csi.cpass.cpassbe.lib.dto.ord.TestataOrdine;

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
	
	
	public RigaOrdine findOne(UUID key) {
		CpassTOrdRigaOrdine cpassTOrdRigaOrdine = cpassTOrdRigaOrdineDao.findOne(key).get();
		return CpassMappers.RIGA_ORDINE.toModel(cpassTOrdRigaOrdine);
	}
	
	public List<RigaOrdine> getRigheByDestinatario(UUID idDestinatario) {
		List<CpassTOrdRigaOrdine> righeOrdine = cpassTOrdRigaOrdineDao.findByIdDestinatario(idDestinatario);
		return CpassMappers.RIGA_ORDINE.toModels(righeOrdine);
	}
	
	/**
	 * Inserts the rigaOrdine
	 * 
	 * @param rigaOrdine to insert
	 * @return the model instance
	 */
	public RigaOrdine saveRigaOrdine(RigaOrdine rigaOrdine) {
		
		CpassTOrdRigaOrdine rigaOrdineEntity = CpassMappers.RIGA_ORDINE.toEntity(rigaOrdine);
		
		//incremento il progressivo
		if(rigaOrdine.getProgressivo() == null || rigaOrdine.getProgressivo().equals(0)) {
			Integer maxProgressivo = 0;
			List<CpassTOrdRigaOrdine> righeOrdine = cpassTOrdRigaOrdineDao.findByIdDestinatario(rigaOrdine.getDestinatario().getId());
			
			for(CpassTOrdRigaOrdine riga : righeOrdine) {
				if(riga.getProgressivo() > maxProgressivo) {
					maxProgressivo = riga.getProgressivo();
				}
			}
			
			rigaOrdineEntity.setProgressivo(++maxProgressivo);
		}
		
		RigaOrdine result = CpassMappers.RIGA_ORDINE.toModel(cpassTOrdRigaOrdineDao.insert(rigaOrdineEntity));
		
		return result;
	}
	
	/**
	 * Updates the rigaOrdine
	 * 
	 * @param rigaOrdine to update
	 * @return the model instance
	 */
	public RigaOrdine updateRigaOrdine(RigaOrdine rigaOrdine) {
		
		CpassTOrdRigaOrdine rigaOrdineEntity = CpassMappers.RIGA_ORDINE.toEntity(rigaOrdine);
		
		RigaOrdine result = CpassMappers.RIGA_ORDINE.toModel(cpassTOrdRigaOrdineDao.update(rigaOrdineEntity));
		
		return result;
	}
	
	public void deleteRiga(UUID idRiga) {
		cpassTOrdRigaOrdineDao.delete(idRiga);
	}
	
	public void deleteRigaByDestinatario(UUID idDestinatario) {
		cpassTOrdRigaOrdineDao.deleteByDestinatario(idDestinatario);
	}
	
	public List<ImpegnoAssociato> getImpegnoAssociatoOrdine(UUID idTestataOrdine) {
		
		List<ImpegnoAssociato> result = new ArrayList<ImpegnoAssociato>();
		
		List<CpassTOrdImpegnoAssociato> impegnoAssociatos = cpassTOrdImpegnoAssociatoDao.getImpegniAssociati(idTestataOrdine);
		if (impegnoAssociatos != null) {
			for (CpassTOrdImpegnoAssociato cpassTOrdImpegnoAssociato : impegnoAssociatos) {
				
				ImpegnoAssociato toAdd = CpassMappers.IMPEGNO_ASSOCIATO.toModel(cpassTOrdImpegnoAssociato);

				// subimpegni
				List<CpassTOrdSubimpegnoAssociato> subimpegnoAssociatos = cpassTOrdSubimpegnoAssociatoDao
						.getSubimpegniAssociati(cpassTOrdImpegnoAssociato.getImpegnoAssociatoId());
				
				if (subimpegnoAssociatos != null) {
					List<SubImpegnoAssociato> subToAdd = new ArrayList<SubImpegnoAssociato>();
					for (CpassTOrdSubimpegnoAssociato cpassTOrdSubimpegnoAssociato : subimpegnoAssociatos) {
						subToAdd.add(CpassMappers.SUBIMPEGNO_ASSOCIATO.toModel(cpassTOrdSubimpegnoAssociato));
					}
					
					toAdd.setSubimpegniAssociati(subToAdd);
				}
				
				result.add(toAdd);
			}
		}
		
		return result;
	}
	
	public List<SubImpegnoAssociato> getSubImpegnoAssociatoOrdine(UUID idImpegnoAssociato) {
		
		List<CpassTOrdSubimpegnoAssociato> impegniAssociatiEntity = cpassTOrdSubimpegnoAssociatoDao.getSubimpegniAssociati(idImpegnoAssociato);
		
		List<SubImpegnoAssociato> result = CpassMappers.SUBIMPEGNO_ASSOCIATO.toModels(impegniAssociatiEntity);
		
		return result;
	}
	
	public List<RigaOrdine> findByTestataOrdine(UUID testataOrdineId, UUID rigaOrdineId) {
		List<CpassTOrdRigaOrdine> cpassTOrdRigaOrdines = cpassTOrdRigaOrdineDao.findByTestataOrdine(testataOrdineId, rigaOrdineId);
		List<RigaOrdine> rigaOrdines = CpassMappers.RIGA_ORDINE.toModels(cpassTOrdRigaOrdines);
		return rigaOrdines;
	}
	
	public long countRigheDaEvadere(Integer annoOrdineDa, Integer numeroOrdineDa, Integer annoOrdineA, Integer numeroOrdineA, Date dataEmissioneDa, Date dataEmissioneA, TestataOrdine testataOrdine, Destinatario destinatario,Impegno impegno, Subimpegno subimpegno, RigaOrdine rigaOrdine, List<OggettiSpesa> odsList) {

		String numeroProvvedimento = null;
		if (testataOrdine.getProvvedimento().getNumero() != null ) {
			numeroProvvedimento = testataOrdine.getProvvedimento().getNumero().toString();
		}

		List<Integer> oggettoSpesaIdList = new ArrayList<Integer>();
		if (odsList != null && odsList.size()>0) {
			for(OggettiSpesa el : odsList) {
				Integer odsId = getId(el);
				if (odsId != null)
					oggettoSpesaIdList.add(odsId);
			}
		}
		Integer rigaOrdine_cpvId = null;
		if (rigaOrdine!=null) {
			Integer rigaOrdine_odsId = getId(rigaOrdine.getOds());
			if(rigaOrdine_odsId !=null )
				oggettoSpesaIdList.add(rigaOrdine_odsId);
			rigaOrdine_cpvId = getId(rigaOrdine.getOds().getCpv());
		}
		long count = cpassTOrdRigaOrdineDao.countDaEvadere(
				annoOrdineDa,
				numeroOrdineDa,
				annoOrdineA, 
				numeroOrdineA, 
				dataEmissioneDa, 
				dataEmissioneA,
				getId(testataOrdine.getTipoOrdine()),
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
				oggettoSpesaIdList,
				rigaOrdine_cpvId);
				
		return count;
		
	}
	
	public List<RigaOrdine> getRigheDaEvadere(
			Integer annoOrdineDa, Integer numeroOrdineDa, Integer annoOrdineA, Integer numeroOrdineA, Date dataEmissioneDa, Date dataEmissioneA, TestataOrdine testataOrdine, Destinatario destinatario,Impegno impegno, Subimpegno subimpegno, RigaOrdine rigaOrdine, List<OggettiSpesa> odsList){
		
		Utente utenteConnesso = CpassThreadLocalContainer.UTENTE_CONNESSO.get();
		Settore settoreCorrente = CpassThreadLocalContainer.SETTORE_UTENTE.get();

		String numeroProvvedimento = null;
		if (testataOrdine.getProvvedimento().getNumero() != null ) {
			numeroProvvedimento = testataOrdine.getProvvedimento().getNumero().toString();
		}
//		List<Integer> oggettoSpesaIdList = new ArrayList<Integer>();
//		List<Integer> cpvIdList = new ArrayList<Integer>();
//		if (rigaOrdine != null && rigaOrdine.size()>0) {
//			for(RigaOrdine el : rigaOrdine) {
//				if (el.getOds()!=null) {
//					Integer odsId = getId(el.getOds());
//					Integer cpvId = getId(el.getOds().getCpv());
//					if (odsId != null)
//						oggettoSpesaIdList.add(getId(el.getOds()));
//					if (cpvId != null)		
//						cpvIdList.add(cpvId);
//				}
//			}
//		}
		List<Integer> oggettoSpesaIdList = new ArrayList<Integer>();
		if (odsList != null && odsList.size()>0) {
			for(OggettiSpesa el : odsList) {
				Integer odsId = getId(el);
				if (odsId != null)
					oggettoSpesaIdList.add(odsId);
			}
		}
		Integer rigaOrdine_cpvId = null;
		if (rigaOrdine!=null) {
			Integer rigaOrdine_odsId = getId(rigaOrdine.getOds());
			if(rigaOrdine_odsId !=null )
				oggettoSpesaIdList.add(rigaOrdine_odsId);
			rigaOrdine_cpvId = getId(rigaOrdine.getOds().getCpv());
		}		
		List<CpassTOrdRigaOrdine> cpassTOrdRigaOrdines = cpassTOrdRigaOrdineDao.findDaEvadere(
				annoOrdineDa,
				numeroOrdineDa,
				annoOrdineA, 
				numeroOrdineA, 
				dataEmissioneDa, 
				dataEmissioneA,
				getId(testataOrdine.getTipoOrdine()),
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
				oggettoSpesaIdList,
				rigaOrdine_cpvId,
				true,
				utenteConnesso.getCodiceFiscale()
				,utenteConnesso.getId()
				,(settoreCorrente!=null ? settoreCorrente.getId() : null)
				);
				
		List<RigaOrdine> listaris = CpassMappers.RIGA_ORDINE.toModels(cpassTOrdRigaOrdines);	
		
		for(RigaOrdine riga: listaris) {
			if (riga.getStatoElOrdine() != null ) {
				if (riga.getStatoElOrdine().getCodice().equals(ConstantsCPassStatoElOrdine.StatoEnum.RIGA_ORDINE_DA_EVADERE.getCostante()))
					riga.setImportoDaEvadere(riga.getImportoTotale());
				else if (riga.getStatoElOrdine().getCodice().equals(ConstantsCPassStatoElOrdine.StatoEnum.RIGA_ORDINE_EVASA_PARZIALMENTE.getCostante())) {
					BigDecimal totaleEvaso = cpassTOrdRigaEvasioneDao.calcolaTotale(riga.getId());
					riga.setImportoDaEvadere(riga.getImportoTotale().subtract(totaleEvaso));
				}
			}
		}
		
//		Map <Fornitore, Map<TestataOrdine, Map<Destinatario, List<RigaOrdine>>>> map = new HashMap<Fornitore, Map< TestataOrdine, Map <Destinatario, List<RigaOrdine>>>>();
//		
//		for (RigaOrdine riga : listaris) {
//			
//			Fornitore fornitore = riga.getDestinatario().getTestataOrdine().getFornitore();
//			Destinatario destinatarioRiga = riga.getDestinatario();
//			TestataOrdine testata = destinatarioRiga.getTestataOrdine();
//			
//			if(map.get(fornitore) == null) {
//				
//				List<RigaOrdine> rigaOrdineList = new ArrayList<RigaOrdine>();
//				rigaOrdineList.add(riga);
//				
//				Map<Destinatario, List<RigaOrdine>> destinatarioMap = new  HashMap <Destinatario, List<RigaOrdine>>();
//				destinatarioMap.put(destinatarioRiga, rigaOrdineList);
//				
//				Map<TestataOrdine, Map<Destinatario, List<RigaOrdine>>> testataOrdineMap = new HashMap<TestataOrdine, Map<Destinatario, List<RigaOrdine>>>();
//				testataOrdineMap.put(testata, destinatarioMap);
//				
//				map.put(fornitore, testataOrdineMap);
//			}
//			else {
//				
//				Map<TestataOrdine, Map<Destinatario, List<RigaOrdine>>> testataMap = map.get(fornitore);
//				if (testataMap.get(testata) == null) {
//					
//					List<RigaOrdine> rigaOrdineList = new ArrayList<RigaOrdine>();
//					rigaOrdineList.add(riga);
//					
//					Map<Destinatario, List<RigaOrdine>> destinatarioMap = new  HashMap <Destinatario, List<RigaOrdine>>();
//					destinatarioMap.put(destinatarioRiga, rigaOrdineList);
//					
//					testataMap.put(testata, destinatarioMap);
//					
//				} else {
//					
//					Map<Destinatario, List<RigaOrdine>> destinatarioMap = testataMap.get(testata);
//					
//					if (destinatarioMap.get(destinatarioRiga) == null) {
//						
//						List<RigaOrdine> rigaOrdineList = new ArrayList<RigaOrdine>();
//						rigaOrdineList.add(riga);
//						destinatarioMap.put(destinatarioRiga, rigaOrdineList);
//						
//					}else {
//						
//						destinatarioMap.get(destinatarioRiga).add(riga);
//						
//					}
//				}
//			}
//			
//		}
//		for (Iterator<Fornitore> iFornitore = map.keySet().iterator(); iFornitore.hasNext();) {
//			Fornitore fornitore = iFornitore.next();
//			// fornitore
//			SceltaRigaOrdineDaEvadere sceltaRigaOrdineDaEvadere = new SceltaRigaOrdineDaEvadere();
//			Map<TestataOrdine, Map<Destinatario, List<RigaOrdine>>> mapTestata = map.get(fornitore);
//			List<TestataOrdine> listTestataOrdine = new ArrayList<TestataOrdine>();
//			for (Iterator<TestataOrdine> iTestata = mapTestata.keySet().iterator(); iTestata.hasNext();) {
//				TestataOrdine testata = iTestata.next();
//				
//				Map<Destinatario, List<RigaOrdine>> mapDestinatario = mapTestata.get(testata);
//				List<Destinatario> listDestinatario = new ArrayList<>();
//				
//				for (Iterator<Destinatario> iDestinatario = mapDestinatario.keySet().iterator(); iDestinatario.hasNext();) {
//					Destinatario destinatarioRiga = iDestinatario.next();
//					listDestinatario.add(destinatarioRiga);
//					
//					for (RigaOrdine rigaOrdine2 : mapDestinatario.get(destinatarioRiga)) {
//						
//					}
//					testata.setListDestinatario(listDestinatario);
//				}
//				
//				listTestataOrdine.add(testata);
//				
//			}
//			sceltaRigaOrdineDaEvadere.setFornitore(fornitore);
//			sceltaRigaOrdineDaEvadere.setListTestataOrdine(listTestataOrdine);
//		}
		
		return listaris;
	}

}
