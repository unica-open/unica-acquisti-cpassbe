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
import java.util.List;
import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import it.csi.cpass.cpassbe.ejb.business.be.dad.sort.InterventoSort;
import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.CpassDAliquoteIvaDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.CpassDCpvDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.CpassDOggettiSpesaDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.CpassDStatoDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.CpassDUnitaMisuraDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.CpassVCpvDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.CpassVCpvOdsDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.ord.CpassDOrdStatoNsoDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.ord.CpassDOrdTipoOrdineDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.ord.CpassDOrdTipoProceduraDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.ord.CpassDStatoElOrdineDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.ord.evasione.CpassDOrdCausaleSospensioneEvasioneDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.ord.evasione.CpassDOrdTipoEvasioneDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.pba.CpassDPbaAcquistoVariatoDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.pba.CpassDPbaAusaDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.pba.CpassDPbaModAffidamentoDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.pba.CpassDPbaNutDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.pba.CpassDPbaPrioritaDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.pba.CpassDPbaRicompresoTipoDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.pba.CpassDPbaRisorsaDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.pba.CpassDPbaSettoreInterventiDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.pba.CpassDPbaTipoAcquistoDao;
import it.csi.cpass.cpassbe.ejb.entity.CpassDAliquoteIva;
import it.csi.cpass.cpassbe.ejb.entity.CpassDCpv;
import it.csi.cpass.cpassbe.ejb.entity.CpassDStato;
import it.csi.cpass.cpassbe.ejb.entity.CpassDUnitaMisura;
import it.csi.cpass.cpassbe.ejb.entity.ord.CpassDOggettiSpesa;
import it.csi.cpass.cpassbe.ejb.entity.ord.CpassDOrdStatoNso;
import it.csi.cpass.cpassbe.ejb.entity.ord.CpassDOrdTipoOrdine;
import it.csi.cpass.cpassbe.ejb.entity.ord.CpassDOrdTipoProcedura;
import it.csi.cpass.cpassbe.ejb.entity.ord.CpassDStatoElOrdine;
import it.csi.cpass.cpassbe.ejb.entity.ord.evasione.CpassDOrdCausaleSospensioneEvasione;
import it.csi.cpass.cpassbe.ejb.entity.ord.evasione.CpassDOrdTipoEvasione;
import it.csi.cpass.cpassbe.ejb.entity.pba.CpassDPbaAcquistoVariato;
import it.csi.cpass.cpassbe.ejb.entity.pba.CpassDPbaAusa;
import it.csi.cpass.cpassbe.ejb.entity.pba.CpassDPbaModAffidamento;
import it.csi.cpass.cpassbe.ejb.entity.pba.CpassDPbaNuts;
import it.csi.cpass.cpassbe.ejb.entity.pba.CpassDPbaPriorita;
import it.csi.cpass.cpassbe.ejb.entity.pba.CpassDPbaRicompresoTipo;
import it.csi.cpass.cpassbe.ejb.entity.pba.CpassDPbaRisorsa;
import it.csi.cpass.cpassbe.ejb.entity.pba.CpassDPbaSettoreInterventi;
import it.csi.cpass.cpassbe.ejb.entity.pba.CpassDPbaTipoAcquisto;
import it.csi.cpass.cpassbe.ejb.entity.view.CpassVCpv;
import it.csi.cpass.cpassbe.ejb.entity.view.CpassVCpvOds;
import it.csi.cpass.cpassbe.ejb.mapper.CpassMappers;
import it.csi.cpass.cpassbe.ejb.util.jpa.Page;
import it.csi.cpass.cpassbe.lib.dto.AliquoteIva;
import it.csi.cpass.cpassbe.lib.dto.Cpv;
import it.csi.cpass.cpassbe.lib.dto.Stato;
import it.csi.cpass.cpassbe.lib.dto.UnitaMisura;
import it.csi.cpass.cpassbe.lib.dto.ord.OggettiSpesa;
import it.csi.cpass.cpassbe.lib.dto.ord.StatoElOrdine;
import it.csi.cpass.cpassbe.lib.dto.ord.StatoNso;
import it.csi.cpass.cpassbe.lib.dto.ord.TipoOrdine;
import it.csi.cpass.cpassbe.lib.dto.ord.TipoProcedura;
import it.csi.cpass.cpassbe.lib.dto.ord.evasione.CausaleSospensioneEvasione;
import it.csi.cpass.cpassbe.lib.dto.ord.evasione.TipoEvasione;
import it.csi.cpass.cpassbe.lib.dto.pba.AcquistoVariato;
import it.csi.cpass.cpassbe.lib.dto.pba.Ausa;
import it.csi.cpass.cpassbe.lib.dto.pba.ModalitaAffidamento;
import it.csi.cpass.cpassbe.lib.dto.pba.Nuts;
import it.csi.cpass.cpassbe.lib.dto.pba.Priorita;
import it.csi.cpass.cpassbe.lib.dto.pba.RicompresoTipo;
import it.csi.cpass.cpassbe.lib.dto.pba.Risorsa;
import it.csi.cpass.cpassbe.lib.dto.pba.SettoreInterventi;
import it.csi.cpass.cpassbe.lib.dto.pba.TipoAcquisto;
import it.csi.cpass.cpassbe.lib.util.pagination.PagedList;
import it.csi.cpass.cpassbe.lib.util.pagination.Sort;

/**
 * Data Access Delegate for decodificas
 */
@ApplicationScoped
public class DecodificaDad extends BaseDad {

	@Inject private CpassDPbaAcquistoVariatoDao cpassDPbaAcquistoVariatoDao;
	@Inject private CpassDPbaAusaDao cpassDPbaAusaDao;
	@Inject private CpassDCpvDao cpassDCpvDao;
	@Inject private CpassVCpvDao cpassVCpvDao;
	@Inject private CpassVCpvOdsDao cpassVCpvOdsDao;
	@Inject private CpassDPbaModAffidamentoDao cpassDPbaModAffidamentoDao;
	@Inject private CpassDPbaNutDao cpassDPbaNutDao;
	@Inject private CpassDPbaPrioritaDao cpassDPbaPrioritaDao;
	@Inject private CpassDPbaRicompresoTipoDao cpassDPbaRicompresoTipoDao;
	@Inject private CpassDPbaRisorsaDao cpassDRisorseDao;
	@Inject private CpassDPbaSettoreInterventiDao cpassDPbaSettoreInterventiDao;
	@Inject private CpassDStatoDao cpassDStatoDao;
	@Inject private CpassDOrdTipoOrdineDao cpassDTipoOrdineDao;
	@Inject private CpassDOrdTipoProceduraDao cpassDOrdTipoProceduraDao;
	@Inject private CpassDStatoElOrdineDao cpassDStatoElOrdineDao;
	@Inject private CpassDAliquoteIvaDao cpassDAliquoteIvaDao;
	@Inject private CpassDUnitaMisuraDao cpassDUnitaMisuraDao;
	@Inject private CpassDOggettiSpesaDao cpassDOggettiSpesaDao;
	@Inject private CpassDOrdStatoNsoDao cpassDOrdStatoNsoDao;
	@Inject private CpassDOrdTipoEvasioneDao cpassDOrdTipoEvasioneDao;
	@Inject private CpassDOrdCausaleSospensioneEvasioneDao cpassDOrdCausaleSospensioneEvasioneDao;
	@Inject private CpassDPbaTipoAcquistoDao cpassDPbaTipoAcquistoDao;
	


	/**
	 * Returns the Cpvs
	 * @return the cpvs
	 */
	public List<Cpv> getCpvs() {
		List<CpassDCpv> cpassDCpvs = cpassDCpvDao.findAll();
		return CpassMappers.CPV.toModels(cpassDCpvs);
	}
	
	public Cpv getCpvByCodice(String codice) {
		CpassDCpv cpv = cpassDCpvDao.findByCodice(codice);
		return CpassMappers.CPV.toModel(cpv);
	}

	/**
	 * Returns the Cpvs
	 * @return the cpvs
	 */
	public List<Cpv> getTreeCpvs() {
		String methodName = "getTreeCpvs";
		List<CpassVCpv> cpassVCpvs = cpassVCpvDao.getTreeCpvs();
		int level = estraiLivelloMaxCpv(cpassVCpvs) ;
		List<Cpv> ris = associaCpvGerarchico(cpassVCpvs, level, new ArrayList<>());
		log.trace(methodName, () -> "result size: " + ris.size());
		return ris;
	}
	
	/**
	 * Returns the CpvOdss
	 * @return the cpvOdss
	 */
	public List<Cpv> getTreeCpvOdss() {
		String methodName = "getTreeCpvOdss";
		List<CpassVCpvOds> cpassVCpvOdss = cpassVCpvOdsDao.getTreeCpvs();
		int level = estraiLivelloMaxCpvOds(cpassVCpvOdss) ;
		List<Cpv> ris = associaCpvOdsGerarchico(cpassVCpvOdss, level, new ArrayList<>());
		log.trace(methodName, () -> "result size: " + ris.size());
		return ris;
	}
	/**
	 * Estrazione del massimo livello tra le CPV
	 * @param cpassVCpvs le CPV
	 * @return il livello
	 */
	private int estraiLivelloMaxCpv(List<CpassVCpv> cpassVCpvs) {
		int ris = 0;
		for(CpassVCpv cpassCpv : cpassVCpvs) {
			int livello = cpassCpv.getLivello().intValue();
			ris = livello > ris ? livello : ris;
		}
		return ris;
	}
	private int estraiLivelloMaxCpvOds(List<CpassVCpvOds> cpassVCpvOdss) {
		int ris = 0;
		for(CpassVCpvOds cpassCpv : cpassVCpvOdss) {
			int livello = cpassCpv.getLivello().intValue();
			ris = livello > ris ? livello : ris;
		}
		return ris;
	}

	/**
	 * Associazione del CPV gerarchico
	 * @param listaCpassVCpvsAll la lista di tutte le CPV
	 * @param level il livello
	 * @param listaCpvLivelloFiglio la lista dei figli
	 * @return la lista delle CPV
	 */
	private List<Cpv> associaCpvGerarchico(List<CpassVCpv> listaCpassVCpvsAll, int level,  List<Cpv> listaCpvLivelloFiglio) {
		String methodName = "associaCpvGerarchico";
		List<Cpv> listaCpvLivello = new ArrayList<>();
		int index = 0;
		for(CpassVCpv cpassCpv: listaCpassVCpvsAll) {
			int livello = cpassCpv.getLivello().intValue();
			if(livello == level) {
				Cpv cpv = CpassMappers.TREECPV.toModel(cpassCpv);
				listaCpvLivello.add(cpv);
				List<Cpv> lista = cpv.getListCpv();
				for(Cpv figlio : listaCpvLivelloFiglio) {
					if (figlio.getCodicePadre().equals(cpv.getCodice())){
						lista.add(figlio);
					}
				}
				cpv.setListCpv(lista);
			}else if(livello < level) {
				log.trace(methodName, () -> "listaCpvLivello " + listaCpvLivello.size());
				listaCpvLivelloFiglio = listaCpvLivello;
				break;
			}
			index++;
		}

		if (level > 1){
			return associaCpvGerarchico(
					listaCpassVCpvsAll.subList(index, listaCpassVCpvsAll.size()),
					level - 1,
					listaCpvLivelloFiglio);
		}
		log.trace(methodName, () -> "listaCpvLivello ultimo " + listaCpvLivello.size());
		return listaCpvLivello;
	}
	private List<Cpv> associaCpvOdsGerarchico(List<CpassVCpvOds> listaCpassVCpvsAll, int level,  List<Cpv> listaCpvLivelloFiglio) {
		String methodName = "associaCpvOdsGerarchico";
		List<Cpv> listaCpvLivello = new ArrayList<>();
		int index = 0;
		for(CpassVCpvOds cpassCpv: listaCpassVCpvsAll) {
			int livello = cpassCpv.getLivello().intValue();
			if(livello == level) {
				Cpv cpv = CpassMappers.TREECPVODS.toModel(cpassCpv);
				listaCpvLivello.add(cpv);
				List<Cpv> lista = cpv.getListCpv();
				for(Cpv figlio : listaCpvLivelloFiglio) {
					if (figlio.getCodicePadre().equals(cpv.getCodice())){
						lista.add(figlio);
					}
				}
				cpv.setListCpv(lista);
			}else if(livello < level) {
				log.trace(methodName, () -> "listaCpvLivello " + listaCpvLivello.size());
				listaCpvLivelloFiglio = listaCpvLivello;
				break;
			}
			index++;
		}

		if (level > 1){
			return associaCpvOdsGerarchico(
					listaCpassVCpvsAll.subList(index, listaCpassVCpvsAll.size()),
					level - 1,
					listaCpvLivelloFiglio);
		}
		log.trace(methodName, () -> "listaCpvLivello ultimo " + listaCpvLivello.size());
		return listaCpvLivello;
	}

	/**
	 * Returns the Cpv
	 * @param id the id
	 * @return the cpvs
	 */
	public Optional<Cpv> getCpv(Integer id) {
		return cpassDCpvDao.findOne(id).map(CpassMappers.CPV::toModel);
	}

	/**
	 * Returns the ModalitaAffidamentos
	 * @return the modalitaAffidamentos
	 */
	public List<ModalitaAffidamento> getModalitaAffidamentos() {
		List<CpassDPbaModAffidamento> cpassDPbaModAffidamentos = cpassDPbaModAffidamentoDao.findAll();
		return CpassMappers.MODALITA_AFFIDAMENTO.toModels(cpassDPbaModAffidamentos);
	}

	/**
	 * Returns the ModalitaAffidamento
	 * @param id the id
	 * @return the ModalitaAffidamento
	 */
	public Optional<ModalitaAffidamento> getModalitaAffidamento(Integer id) {
		return cpassDPbaModAffidamentoDao.findOne(id).map(CpassMappers.MODALITA_AFFIDAMENTO::toModel);
	}

	/**
	 * Returns the Nuts
	 * @return the nuts
	 */
	public List<Nuts> getNuts() {
		List<CpassDPbaNuts> cpassDPbaNuts = cpassDPbaNutDao.findAll();
		return CpassMappers.NUTS.toModels(cpassDPbaNuts);
	}

	/**
	 * Returns the nuts
	 * @param id the id
	 * @return the nuts
	 */
	public Optional<Nuts> getNut(Integer id) {
		return cpassDPbaNutDao.findOne(id).map(CpassMappers.NUTS::toModel);
	}

	/**
	 * Returns the Prioritas
	 * @return the prioritas
	 */
	public List<Priorita> getPrioritas() {
		List<CpassDPbaPriorita> cpassDPbaPrioritas = cpassDPbaPrioritaDao.findAll();
		return CpassMappers.PRIORITA.toModels(cpassDPbaPrioritas);
	}

	/**
	 * Returns the priorita
	 * @param id the id
	 * @return the priorita
	 */
	public Optional<Priorita> getPriorita(Integer id) {
		return cpassDPbaPrioritaDao.findOne(id).map(CpassMappers.PRIORITA::toModel);
	}

	/**
	 * Returns the Risorsas
	 * @return the risorsas
	 */
	public List<Risorsa> getRisorsas() {
		List<CpassDPbaRisorsa> cpassDRisorses = cpassDRisorseDao.getRisorseByTipo(null);
		return CpassMappers.RISORSA.toModels(cpassDRisorses);
	}

	/**
	 * Returns the Risorsas
	 * @param tipo the tipo
	 * @return the risorsas
	 */
	public List<Risorsa> getRisorsasByTipo(String tipo) {
		List<CpassDPbaRisorsa> cpassDRisorses = cpassDRisorseDao.getRisorseByTipo(tipo);
		return CpassMappers.RISORSA.toModels(cpassDRisorses);
	}

	/**
	 * Returns the Risorsa
	 * @param id
	 * @return the risorsa
	 */
	public Optional<Risorsa> getRisorsa(Integer id) {
		return cpassDRisorseDao.findOne(id).map(CpassMappers.RISORSA::toModel);
	}

	/**
	 * Returns the SettoreInterventis
	 * @return the settoreInterventi
	 */
	public List<SettoreInterventi> getSettoreInterventis() {
		List<CpassDPbaSettoreInterventi> cpassDPbaSettoreInterventis = cpassDPbaSettoreInterventiDao.findAll();
		return CpassMappers.SETTORE_INTERVENTI.toModels(cpassDPbaSettoreInterventis);
	}

	/**
	 * Returns the SettoreInterventi
	 * @param id the id
	 * @return the settoreInterventi
	 */
	public Optional<SettoreInterventi> getSettoreInterventi(Integer id) {
		return cpassDPbaSettoreInterventiDao.findOne(id).map(CpassMappers.SETTORE_INTERVENTI::toModel);
	}

	/**
	 * Returns the stato
	 * @return the STATO
	 */
	public List<Stato> getStatos() {
		List<CpassDStato> cpassDStatos = cpassDStatoDao.findAll();
		return CpassMappers.STATO.toModels(cpassDStatos);
	}

	/**
	 * Returns the stato
	 * @param tipo the tipo
	 * @return the STATO
	 */
	public List<Stato> getStatosByTipo(String tipo) {
		List<CpassDStato> cpassDStatos = cpassDStatoDao.getStatosByTipo( tipo);
		return CpassMappers.STATO.toModels(cpassDStatos);
	}
	/**
	 * Returns the stato
	 * @param id the id
	 * @return the stato
	 */
	public Optional<Stato> getStato(Integer id) {
		return cpassDStatoDao.findOne(id).map(CpassMappers.STATO::toModel);
	}

	/**
	 * Returns the stato by code and tipo
	 * @param codice the codice
	 * @param tipo the tipo
	 * @return the nuts
	 */
	public Optional<Stato> getStato(String codice, String tipo) {
		return cpassDStatoDao.findByCodiceTipo(codice, tipo).map(CpassMappers.STATO::toModel);
	}
	
	/**
	 * Returns the statoElOrdine by code and tipo
	 * @param codice the codice
	 * @param tipo the tipo
	 * @return the nuts
	 */
	public StatoElOrdine getStatoElOrdine(String codice, String tipo) {
		CpassDStatoElOrdine result = cpassDStatoElOrdineDao.findByCodiceTipo(codice, tipo);
		return result != null ? CpassMappers.STATO_EL_ORDINE.toModel(result) : null;
	}

	/**
	 * Returns the Ausas
	 * @return the Ausas
	 */
	public List<Ausa> getAusas() {
		List<CpassDPbaAusa> cpassDPbaAusas = cpassDPbaAusaDao.findAll();
		return CpassMappers.AUSA.toModels(cpassDPbaAusas);
	}

	/**
	 * Returns the RicompresoTipo
	 * @return the RicompresoTipo
	 */
	public List<RicompresoTipo> getRicompresoTipos() {
		List<CpassDPbaRicompresoTipo> cpassDPbaRicompresoTipos = cpassDPbaRicompresoTipoDao.findAll();
		return CpassMappers.RICOMPRESO_TIPO.toModels(cpassDPbaRicompresoTipos);
	}

	/**
	 * Returns the AcquistiVariati
	 * @return the AcquistiVariati
	 */
	public List<AcquistoVariato> getAcquistiVariati() {
		List<CpassDPbaAcquistoVariato> cpassDAcquistiVariati = cpassDPbaAcquistoVariatoDao.findAllOrdinato();
		return CpassMappers.ACQUISTO_VARIATO.toModels(cpassDAcquistiVariati);
	}

	/**
	 * Returns the TipoOrdines
	 * @return the TipoOrdines
	 */
	public List<TipoOrdine> getTipoOrdines() {
		List<CpassDOrdTipoOrdine> cpassDOrdTipoOrdines = cpassDTipoOrdineDao.findValid();
		return CpassMappers.TIPO_ORDINE.toModels(cpassDOrdTipoOrdines);
	}

	/**
	 * Returns the TipoProceduras
	 * @return the TipoProceduras
	 */
	public List<TipoProcedura> getTipoProceduras() {
		List<CpassDOrdTipoProcedura> cpassDOrdTipoProceduras = cpassDOrdTipoProceduraDao.findValid();
		return CpassMappers.TIPO_PROCEDURA.toModels(cpassDOrdTipoProceduras);
		
//		TipoProcedura tipoProcedura = new TipoProcedura();
//		tipoProcedura.setId(222L);
//		tipoProcedura.setCodice("COD-TP");
//		tipoProcedura.setDescrizione("DESC TIPO PROC TEST");
//		tipoProcedura.setNumero("NUM TEST");
//
//		ArrayList<TipoProcedura> tipoProceduras = new ArrayList<TipoProcedura>();
//		tipoProceduras.add(tipoProcedura);
//
//		return tipoProceduras;
	}

	/**
	 *
	 * @param statoTipo
	 * @return
	 */
	public List<StatoElOrdine> getStatoElOrdineByTipo(String statoTipo) {
		List<CpassDStatoElOrdine> cpassDStatoElOrdines = cpassDStatoElOrdineDao.getStatoElOrdineByTipo(statoTipo);
		return CpassMappers.STATO_EL_ORDINE.toModels(cpassDStatoElOrdines);
	}

	/**
	 *
	 * @return  List<AliquoteIva>
	 */
	public List<AliquoteIva> getAliquoteIva() {
		List<CpassDAliquoteIva> cpassDAliquoteIva = cpassDAliquoteIvaDao.findValid();
		return CpassMappers.ALIQUOTE_IVA.toModels(cpassDAliquoteIva);

	}
	
	public AliquoteIva getAliquoteIvaById(Integer id) {
		Optional<CpassDAliquoteIva> cpassDAliquoteIva = cpassDAliquoteIvaDao.findOne(id);
		return cpassDAliquoteIva.isPresent() ? CpassMappers.ALIQUOTE_IVA.toModel(cpassDAliquoteIva.get()) : null;
	}

	/**
	 *
	 * @return List<UnitaMisura>
	 */
	public List<UnitaMisura> getUnitaMisura() {
		List<CpassDUnitaMisura> cpassDUnitaMisura = cpassDUnitaMisuraDao.findValid();
		return CpassMappers.UNITA_MISURA.toModels(cpassDUnitaMisura);

	}
	
	public UnitaMisura getUnitaMisuraById(Integer id) {
		Optional<CpassDUnitaMisura> cpassDUnitaMisura = cpassDUnitaMisuraDao.findOne(id);
		return cpassDUnitaMisura.isPresent() ? CpassMappers.UNITA_MISURA.toModel(cpassDUnitaMisura.get()) : null;
	}

	public PagedList<OggettiSpesa> getRicercaOggettiSpesa(int page, int size, Sort sort, OggettiSpesa oggettiSpesa) {
		String sortField = null;
		String sortDirection = null;
		if (sort != null) {
			sortField = InterventoSort.byModelName(sort.getField()).getQueryName();
			sortDirection = sort.getOrder().getSortDirection();
		}

		Page<CpassDOggettiSpesa> cpassDOggettiSpesas = cpassDOggettiSpesaDao.findPaginated(		
				oggettiSpesa.getInventariabile(),
				oggettiSpesa.getCodice(),
				oggettiSpesa.getDescrizione(),
				//oggettiSpesa.getDataValiditaFine(),
				//oggettiSpesa.getDataValiditaInizio(),				
				getId(oggettiSpesa.getAliquoteIva()),
				getId(oggettiSpesa.getCpv()),
				oggettiSpesa.getCpv() != null ? oggettiSpesa.getCpv().getCodice() : null,
				getId(oggettiSpesa.getUnitaMisura()),
				page,
				size,
				sortField,
				sortDirection);
		
		PagedList<OggettiSpesa> pagedList = toPagedList(cpassDOggettiSpesas, page, size, CpassMappers.OGGETTO_SPESA::toModel);
		
		return pagedList;
	}
	
	public OggettiSpesa getOdsByCodice(String codice) {
		CpassDOggettiSpesa ods = cpassDOggettiSpesaDao.findByCodice(codice);
		return ods != null ? CpassMappers.OGGETTO_SPESA.toModel(ods) : null;
	}
	
	public OggettiSpesa getOdsById(Integer id) {
		Optional<CpassDOggettiSpesa> ods = cpassDOggettiSpesaDao.findOne(id);
		return ods.isPresent() ? CpassMappers.OGGETTO_SPESA.toModel(ods.get()) : null;
	}
	
	/*
	 public PagedList<Intervento> getRicercaInterventi(int page, int size, Sort sort, Intervento intervento, UUID settoreId) {
		UUID enteId = checkUtenteAndGetEnte(settoreId);
		
		String sortField = null;
		String sortDirection = null;
		if (sort != null) {
			sortField = InterventoSort.byModelName(sort.getField()).getQueryName();
			sortDirection = sort.getOrder().getSortDirection();
		}
		
		Page<CpassTPbaIntervento> cpassTPbaInterventos = cpassTPbaInterventoDao.findPaginated(
				intervento.getCui(),
				intervento.getAnnoAvvio(),
				intervento.getCup() != null ? intervento.getCup() : null,
				intervento.getLottoFunzionale(),
				intervento.getDurataMesi(),
				intervento.getNuovoAffidamento(),
				intervento.getDescrizioneAcquisto()!= null ? intervento.getDescrizioneAcquisto() : null,
				intervento.getUtenteRup() != null ? intervento.getUtenteRup().getCognome() : null,
				//intervento.getProgramma() != null ? intervento.getProgramma().getAnno() : null,
				getId(intervento.getProgramma()),
				getId(intervento.getAusa()),
				getId(intervento.getInterventoRicompreso()),
				getId(intervento.getInterventoCopia()),
				getId(intervento.getSettoreInterventi()),
				getId(intervento.getCpv()),
				getId(intervento.getNuts()),
				getId(intervento.getPriorita()),
				getId(intervento.getModalitaAffidamento()),
				getId(intervento.getStato()),
				getId(intervento.getAcquistoVariato()),
				getId(intervento.getRicompresoTipo()),
				enteId,
				page,
				size,
				sortField,
				sortDirection);
				
		PagedList<Intervento> pagedList = toPagedList(cpassTPbaInterventos, page, size, CpassMappers.INTERVENTO::toModel);
		for (Intervento interventoItem : pagedList.getList()) {
			// cerca la risorsa capitale privato valorizzata
			for (InterventoImporti interventoImporti : interventoItem.getListInterventoImporti()) {
				if (interventoImporti.getRisorsa().getTipo().equals(ConstantsCPassRisorsa.TipoEnum.CAPITALE_PRIVATO.getTipo())) {
					boolean importiTuttiAZero = interventoImporti.getImportoAnnoPrimo().signum() == 0 
							&& interventoImporti.getImportoAnnoSecondo().signum() == 0
							&& interventoImporti.getImportoAnniSuccessivi().signum() == 0;
					if (!importiTuttiAZero) {
						interventoItem.setRisorsaIdCapitalePrivato(interventoImporti.getRisorsa().getId());
					}
				}
			}
		} 
		
		return pagedList;
	}
	 */
	/**
	 * Returns the statoNso
	 * @param tipo the tipo
	 * @return the STATONSO
	 */
	public List<StatoNso> getStatoNsosByTipo(String tipo) {
		List<CpassDOrdStatoNso> cpassDOrdStatoNsos = cpassDOrdStatoNsoDao.getStatoNsosByTipo(tipo);
		return CpassMappers.STATO_NSO.toModels(cpassDOrdStatoNsos);
	}
	
	public Optional<StatoNso> getStatoNso(String codice, String tipo) {
		return cpassDOrdStatoNsoDao.findByCodiceTipo(codice, tipo).map(CpassMappers.STATO_NSO::toModel);
	}

	public TipoEvasione getTipoEvasioneByCodice(String codice) {
		CpassDOrdTipoEvasione cpassDOrdTipoEvasione = cpassDOrdTipoEvasioneDao.findByCodice(codice);
		return CpassMappers.TIPO_EVASIONE.toModel(cpassDOrdTipoEvasione);
	}
	
	public List<CausaleSospensioneEvasione> getAllCausaleSospensioneAttive() {
		List<CpassDOrdCausaleSospensioneEvasione> cpassDOrdCausaleSospensioneEvasiones = cpassDOrdCausaleSospensioneEvasioneDao.findAllValide();
		return CpassMappers.CAUSALE_SOSPENSIONE_EVASIONE.toModels(cpassDOrdCausaleSospensioneEvasiones);
	}
	
	/**
	 * Returns the TipoOrdines
	 * @return the TipoOrdines
	 */
	public List<TipoEvasione> getTipoEvasiones() {
		List<CpassDOrdTipoEvasione> cpassDOrdTipoEvasiones = cpassDOrdTipoEvasioneDao.findValid();
		return CpassMappers.TIPO_EVASIONE.toModels(cpassDOrdTipoEvasiones);
	}

	public List<TipoAcquisto> getTipoAcquistos() {
		List<CpassDPbaTipoAcquisto> cpassDPbaTipoAcquistos = cpassDPbaTipoAcquistoDao.findAll();
		return CpassMappers.TIPO_ACQUISTO.toModels(cpassDPbaTipoAcquistos);
	}
	
}
