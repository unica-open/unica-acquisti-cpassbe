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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import it.csi.cpass.cpassbe.ejb.business.be.dad.sort.InterventoSort;
import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.decodifiche.CpassDAliquoteIvaDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.decodifiche.CpassDCpvDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.decodifiche.CpassDMotiviEsclusioneCigDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.decodifiche.CpassDOggettiSpesaDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.decodifiche.CpassDPermessoDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.decodifiche.CpassDProvvedimentoTipoDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.decodifiche.CpassDStatoDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.decodifiche.CpassDTipoSettoreDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.decodifiche.CpassDUnitaMisuraDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.ord.CpassDOrdStatoNsoDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.ord.CpassDOrdTipoOrdineDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.ord.CpassDOrdTipoProceduraDao;
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
import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.pba.CpassDPbaTipoProceduraDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.view.CpassVCpvDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.view.CpassVCpvOdsDao;
import it.csi.cpass.cpassbe.ejb.entity.CpassDAliquoteIva;
import it.csi.cpass.cpassbe.ejb.entity.CpassDCpv;
import it.csi.cpass.cpassbe.ejb.entity.CpassDMotiviEsclusioneCig;
import it.csi.cpass.cpassbe.ejb.entity.CpassDOggettiSpesa;
import it.csi.cpass.cpassbe.ejb.entity.CpassDPermesso;
import it.csi.cpass.cpassbe.ejb.entity.CpassDProvvedimentoTipo;
import it.csi.cpass.cpassbe.ejb.entity.CpassDStato;
import it.csi.cpass.cpassbe.ejb.entity.CpassDTipoSettore;
import it.csi.cpass.cpassbe.ejb.entity.CpassDUnitaMisura;
import it.csi.cpass.cpassbe.ejb.entity.ord.CpassDOrdStatoNso;
import it.csi.cpass.cpassbe.ejb.entity.ord.CpassDOrdTipoOrdine;
import it.csi.cpass.cpassbe.ejb.entity.ord.CpassDOrdTipoProcedura;
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
import it.csi.cpass.cpassbe.ejb.entity.pba.CpassDPbaTipoProcedura;
import it.csi.cpass.cpassbe.ejb.entity.view.CpassVCpv;
import it.csi.cpass.cpassbe.ejb.entity.view.CpassVCpvOds;
import it.csi.cpass.cpassbe.ejb.exception.NotFoundException;
import it.csi.cpass.cpassbe.ejb.mapper.CpassMappers;
import it.csi.cpass.cpassbe.ejb.util.StringUtility;
import it.csi.cpass.cpassbe.ejb.util.jpa.Page;
import it.csi.cpass.cpassbe.lib.dto.AliquoteIva;
import it.csi.cpass.cpassbe.lib.dto.Cpv;
import it.csi.cpass.cpassbe.lib.dto.Ente;
import it.csi.cpass.cpassbe.lib.dto.MotiviEsclusioneCig;
import it.csi.cpass.cpassbe.lib.dto.Ods;
import it.csi.cpass.cpassbe.lib.dto.Permesso;
import it.csi.cpass.cpassbe.lib.dto.ProvvedimentoTipo;
import it.csi.cpass.cpassbe.lib.dto.Stato;
import it.csi.cpass.cpassbe.lib.dto.TipoSettore;
import it.csi.cpass.cpassbe.lib.dto.UnitaMisura;
import it.csi.cpass.cpassbe.lib.dto.ord.StatoNso;
import it.csi.cpass.cpassbe.lib.dto.ord.TipoOrdine;
import it.csi.cpass.cpassbe.lib.dto.ord.TipoProceduraOrd;
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
import it.csi.cpass.cpassbe.lib.dto.pba.TipoProceduraPba;
import it.csi.cpass.cpassbe.lib.util.pagination.PagedList;
import it.csi.cpass.cpassbe.lib.util.pagination.Sort;
import it.csi.cpass.cpassbe.lib.util.threadlocal.CpassThreadLocalContainer;

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
	@Inject private CpassDPbaTipoProceduraDao cpassDPbaTipoProceduraDao;
	@Inject private CpassDAliquoteIvaDao cpassDAliquoteIvaDao;
	@Inject private CpassDUnitaMisuraDao cpassDUnitaMisuraDao;
	@Inject private CpassDOggettiSpesaDao cpassDOggettiSpesaDao;
	@Inject private CpassDOrdStatoNsoDao cpassDOrdStatoNsoDao;
	@Inject private CpassDOrdTipoEvasioneDao cpassDOrdTipoEvasioneDao;
	@Inject private CpassDOrdCausaleSospensioneEvasioneDao cpassDOrdCausaleSospensioneEvasioneDao;
	@Inject private CpassDPbaTipoAcquistoDao cpassDPbaTipoAcquistoDao;
	@Inject private CpassDProvvedimentoTipoDao cpassDProvvedimentoTipoDao;
	@Inject private CpassDMotiviEsclusioneCigDao cpassDMotiviEsclusioneCigDao;
	@Inject private CpassDTipoSettoreDao cpassDTipoSettoreDao;
	@Inject private CpassDPermessoDao cpassDPermessoDao;

	/**
	 * Returns the Cpvs
	 * @return the cpvs
	 */
	public List<Cpv> getCpvs() {
		final List<CpassDCpv> cpassDCpvs = cpassDCpvDao.findAll();
		return CpassMappers.CPV.toModels(cpassDCpvs);
	}

	/**
	 * Returns the Cpvs
	 * @return the cpvs
	 */
	public List<Ods> getOds() {
		final List<CpassDOggettiSpesa> lista = cpassDOggettiSpesaDao.findAll();
		return CpassMappers.OGGETTO_SPESA.toModels(lista);
	}
	/**
	 * 
	 * @param enteId
	 * @return
	 */
	public List<Ods> getOdsByEnteId(UUID enteId) {
		final List<CpassDOggettiSpesa> lista = cpassDOggettiSpesaDao.getOdsByEnteId( enteId);
		return CpassMappers.OGGETTO_SPESA_MINIMAL.toModels(lista);
	}
	/**
	 * 
	 * @param codice
	 * @return
	 */
	public Cpv getCpvByCodice(String codice) {
		final CpassDCpv cpv = cpassDCpvDao.findByCodice(codice);
		return CpassMappers.CPV.toModel(cpv);
	}

	/**
	 * Returns the Cpvs
	 * @return the cpvs
	 */
	public List<Cpv> getTreeCpvs() {
		final String methodName = "getTreeCpvs";
		final List<CpassVCpv> cpassVCpvs = cpassVCpvDao.getTreeCpvs();
		final int level = estraiLivelloMaxCpv(cpassVCpvs) ;
		final List<Cpv> ris = associaCpvGerarchico(cpassVCpvs, level, new ArrayList<>());
		log.trace(methodName, () -> "result size: " + ris.size());
		return ris;
	}

	/**
	 * Returns the CpvOdss
	 * @return the cpvOdss
	 */
	public List<Cpv> getTreeCpvOdss() {
		final String methodName = "getTreeCpvOdss";
		final List<CpassVCpvOds> cpassVCpvOdss = cpassVCpvOdsDao.getTreeCpvs();
		final int level = estraiLivelloMaxCpvOds(cpassVCpvOdss) ;
		final List<Cpv> ris = associaCpvOdsGerarchico(cpassVCpvOdss, level, new ArrayList<>());
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
		for(final CpassVCpv cpassCpv : cpassVCpvs) {
			final int livello = cpassCpv.getLivello().intValue();
			ris = livello > ris ? livello : ris;
		}
		return ris;
	}
	/**
	 * 
	 * @param cpassVCpvOdss
	 * @return
	 */
	private int estraiLivelloMaxCpvOds(List<CpassVCpvOds> cpassVCpvOdss) {
		int ris = 0;
		for(final CpassVCpvOds cpassCpv : cpassVCpvOdss) {
			final int livello = cpassCpv.getLivello().intValue();
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
		final String methodName = "associaCpvGerarchico";
		final List<Cpv> listaCpvLivello = new ArrayList<>();
		int index = 0;
		for(final CpassVCpv cpassCpv: listaCpassVCpvsAll) {
			final int livello = cpassCpv.getLivello().intValue();
			if(livello == level) {
				final Cpv cpv = CpassMappers.TREECPV.toModel(cpassCpv);
				listaCpvLivello.add(cpv);
				final List<Cpv> lista = cpv.getListCpv();
				for(final Cpv figlio : listaCpvLivelloFiglio) {
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
	/**
	 * 
	 * @param listaCpassVCpvsAll
	 * @param level
	 * @param listaCpvLivelloFiglio
	 * @return
	 */
	private List<Cpv> associaCpvOdsGerarchico(List<CpassVCpvOds> listaCpassVCpvsAll, int level,  List<Cpv> listaCpvLivelloFiglio) {
		final String methodName = "associaCpvOdsGerarchico";
		final List<Cpv> listaCpvLivello = new ArrayList<>();
		int index = 0;
		for(final CpassVCpvOds cpassCpv: listaCpassVCpvsAll) {
			final int livello = cpassCpv.getLivello().intValue();
			if(livello == level) {
				final Cpv cpv = CpassMappers.TREECPVODS.toModel(cpassCpv);
				listaCpvLivello.add(cpv);
				final List<Cpv> lista = cpv.getListCpv();
				for(final Cpv figlio : listaCpvLivelloFiglio) {
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
		final List<CpassDPbaModAffidamento> cpassDPbaModAffidamentos = cpassDPbaModAffidamentoDao.findAll();
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
		final List<CpassDPbaNuts> cpassDPbaNuts = cpassDPbaNutDao.findAll();
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
		final List<CpassDPbaPriorita> cpassDPbaPrioritas = cpassDPbaPrioritaDao.findAll();
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
		final List<CpassDPbaRisorsa> cpassDRisorses = cpassDRisorseDao.getRisorseByTipo(null);
		return CpassMappers.RISORSA.toModels(cpassDRisorses);
	}

	/**
	 * Returns the Risorsas
	 * @param tipo the tipo
	 * @return the risorsas
	 */
	public List<Risorsa> getRisorsasByTipo(String tipo) {
		final List<CpassDPbaRisorsa> cpassDRisorses = cpassDRisorseDao.getRisorseByTipo(tipo);
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
		final List<CpassDPbaSettoreInterventi> cpassDPbaSettoreInterventis = cpassDPbaSettoreInterventiDao.findAll();
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
		final List<CpassDStato> cpassDStatos = cpassDStatoDao.findAll();
		return CpassMappers.STATO.toModels(cpassDStatos);
	}

	/**
	 * Returns the stato
	 * @param tipo the tipo
	 * @return the STATO
	 */
	public List<Stato> getStatosByTipo(String tipo) {
		final List<CpassDStato> cpassDStatos = cpassDStatoDao.getStatosByTipo( tipo);
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
	public Stato getStato(String codice, String tipo) {
		final Optional<Stato> optStato = cpassDStatoDao.findByCodiceTipo(codice, tipo).map(CpassMappers.STATO::toModel);
		return optStato.orElseThrow(() -> new NotFoundException("stato"));
	}
	/**
	 * 
	 * @param codice
	 * @param tipo
	 * @return
	 */
	public Optional<Stato> getStatoOpt(String codice, String tipo) {
		final Optional<Stato> optStato = cpassDStatoDao.findByCodiceTipo(codice, tipo).map(CpassMappers.STATO::toModel);
		return optStato;
	}

	/**
	 * Returns the Ausas
	 * @return the Ausas
	 */
	public List<Ausa> getAusas() {
		final List<CpassDPbaAusa> cpassDPbaAusas = cpassDPbaAusaDao.findAll();
		return CpassMappers.AUSA.toModels(cpassDPbaAusas);
	}

	/**
	 * Returns the RicompresoTipo
	 * @return the RicompresoTipo
	 */
	public List<RicompresoTipo> getRicompresoTipos() {
		final List<CpassDPbaRicompresoTipo> cpassDPbaRicompresoTipos = cpassDPbaRicompresoTipoDao.findAll();
		return CpassMappers.RICOMPRESO_TIPO.toModels(cpassDPbaRicompresoTipos);
	}

	/**
	 * Returns the AcquistiVariati
	 * @return the AcquistiVariati
	 */
	public List<AcquistoVariato> getAcquistiVariati() {
		final List<CpassDPbaAcquistoVariato> cpassDAcquistiVariati = cpassDPbaAcquistoVariatoDao.findAllOrdinato();
		return CpassMappers.ACQUISTO_VARIATO.toModels(cpassDAcquistiVariati);
	}

	/**
	 * Returns the TipoOrdines
	 * @return the TipoOrdines
	 */
	public List<TipoOrdine> getTipoOrdines(String noTypeCode) {
		List<CpassDOrdTipoOrdine> cpassDOrdTipoOrdines = new ArrayList<>();
		if(org.apache.commons.lang.StringUtils.isEmpty(noTypeCode)) {
			cpassDOrdTipoOrdines = cpassDTipoOrdineDao.findValid();
		}else {
			cpassDOrdTipoOrdines = cpassDTipoOrdineDao.getListaValidTipoOrdineExcludeCode(noTypeCode);
		}
		return CpassMappers.TIPO_ORDINE.toModels(cpassDOrdTipoOrdines);
	}

	/**
	 * Returns the TipoOrdines
	 */
	public List<TipoOrdine> getTipoOrdines() {
		return getTipoOrdines("");
	}

	/**
	 * Returns the TipoProceduras
	 * @return the TipoProceduras
	 */
	public List<TipoProceduraOrd> getTipoProceduraOrds(UUID enteId) {
		final List<CpassDOrdTipoProcedura> cpassDOrdTipoProceduras = cpassDOrdTipoProceduraDao.findValid(enteId);
		return CpassMappers.TIPO_PROCEDURA_ORD.toModels(cpassDOrdTipoProceduras);
	}

	/**
	 * Returns the TipoProceduras
	 * @return the TipoProceduras
	 */
	public List<TipoProceduraPba> getTipoProceduraPbas(UUID enteId) {
		final List<CpassDPbaTipoProcedura> cpassDPbaTipoProceduras = cpassDPbaTipoProceduraDao.findValid(enteId);
		return CpassMappers.TIPO_PROCEDURA_PBA.toModels(cpassDPbaTipoProceduras);
	}

	/**
	 *
	 * @return  List<AliquoteIva>
	 */
	public List<AliquoteIva> getAliquoteIva() {
		final List<CpassDAliquoteIva> cpassDAliquoteIva = cpassDAliquoteIvaDao.findValid();
		return CpassMappers.ALIQUOTE_IVA.toModels(cpassDAliquoteIva);

	}
	/**
	 * 
	 * @param id
	 * @return
	 */
	public AliquoteIva getAliquoteIvaById(Integer id) {
		final Optional<CpassDAliquoteIva> cpassDAliquoteIva = cpassDAliquoteIvaDao.findOne(id);
		return cpassDAliquoteIva.isPresent() ? CpassMappers.ALIQUOTE_IVA.toModel(cpassDAliquoteIva.get()) : null;
	}
	/**
	 * 
	 * @param codice
	 * @return
	 */
	public AliquoteIva getAliquoteIvaByCodice(String codice) {
		final Optional<CpassDAliquoteIva> cpassDAliquoteIva = cpassDAliquoteIvaDao.findAliquoteIvaByCodice(codice);
		return cpassDAliquoteIva.isPresent() ? CpassMappers.ALIQUOTE_IVA.toModel(cpassDAliquoteIva.get()): null;
	}

	/**
	 *
	 * @return List<UnitaMisura>
	 */
	public List<UnitaMisura> getUnitaMisura() {
		final List<CpassDUnitaMisura> cpassDUnitaMisura = cpassDUnitaMisuraDao.findValid();
		return CpassMappers.UNITA_MISURA.toModels(cpassDUnitaMisura);

	}
	/**
	 * 
	 * @param id
	 * @return
	 */
	public UnitaMisura getUnitaMisuraById(Integer id) {
		final Optional<CpassDUnitaMisura> cpassDUnitaMisura = cpassDUnitaMisuraDao.findOne(id);
		return cpassDUnitaMisura.isPresent() ? CpassMappers.UNITA_MISURA.toModel(cpassDUnitaMisura.get()) : null;
	}
	/**
	 * 
	 * @param codice
	 * @return
	 */
	public UnitaMisura getUnitaMisuraByCodice(String codice) {
		final Optional<CpassDUnitaMisura> cpassDUnitaMisura = cpassDUnitaMisuraDao.findValidByCodice(codice);
		return cpassDUnitaMisura.isPresent() ? CpassMappers.UNITA_MISURA.toModel(cpassDUnitaMisura.get()) : null;
	}
	/**
	 * 
	 * @param codice
	 * @return
	 */
	public Ods getOdsByCodice(String codice) {
		final Ente ente = CpassThreadLocalContainer.SETTORE_UTENTE.get().getEnte();
		final CpassDOggettiSpesa ods = cpassDOggettiSpesaDao.findByCodice(codice, ente.getId());
		return ods != null ? CpassMappers.OGGETTO_SPESA.toModel(ods) : null;
	}
	/**
	 * 
	 * @param codice
	 * @param enteId
	 * @return
	 */
	public Ods findODSByCodice(String codice, UUID enteId) {
		final CpassDOggettiSpesa entity = cpassDOggettiSpesaDao.findByCodice(codice, enteId);
		return CpassMappers.OGGETTO_SPESA.toModel(entity);
	}
	/**
	 * 
	 * @param id
	 * @return
	 */
	public Ods getOdsById(Integer id) {
		final Optional<CpassDOggettiSpesa> ods = cpassDOggettiSpesaDao.findOne(id);
		return ods.isPresent() ? CpassMappers.OGGETTO_SPESA.toModel(ods.orElseThrow(() -> new NotFoundException("ods"))) : null;
	}

	/**
	 * Returns the statoNso
	 * @param tipo the tipo
	 * @return the STATONSO
	 */
	public List<StatoNso> getStatoNsosByTipo(String tipo) {
		final List<CpassDOrdStatoNso> cpassDOrdStatoNsos = cpassDOrdStatoNsoDao.getStatoNsosByTipo(tipo);
		return CpassMappers.STATO_NSO.toModels(cpassDOrdStatoNsos);
	}
	/**
	 * 
	 * @param codice
	 * @param tipo
	 * @return
	 */
	public StatoNso getStatoNso(String codice, String tipo) {
		final Optional<StatoNso> opTStato = cpassDOrdStatoNsoDao.findByCodiceTipo(codice, tipo).map(CpassMappers.STATO_NSO::toModel);
		return opTStato.orElseThrow(() -> new NotFoundException("stato"));
	}
	/**
	 * 
	 * @param codice
	 * @param tipo
	 * @return
	 */
	public Optional<StatoNso> getStatoNsoOpt(String codice, String tipo) {
		final Optional<StatoNso> opTStato = cpassDOrdStatoNsoDao.findByCodiceTipo(codice, tipo).map(CpassMappers.STATO_NSO::toModel);
		return opTStato;
	}
	/**
	 * 
	 * @param codice
	 * @return
	 */
	public TipoEvasione getTipoEvasioneByCodice(String codice) {
		final CpassDOrdTipoEvasione cpassDOrdTipoEvasione = cpassDOrdTipoEvasioneDao.findByCodice(codice);
		return CpassMappers.TIPO_EVASIONE.toModel(cpassDOrdTipoEvasione);
	}
	/**
	 * 
	 * @return
	 */
	public List<CausaleSospensioneEvasione> getAllCausaleSospensioneAttive() {
		final List<CpassDOrdCausaleSospensioneEvasione> cpassDOrdCausaleSospensioneEvasiones = cpassDOrdCausaleSospensioneEvasioneDao.findAllValide();
		return CpassMappers.CAUSALE_SOSPENSIONE_EVASIONE.toModels(cpassDOrdCausaleSospensioneEvasiones);
	}

	/**
	 * Returns the TipoOrdines
	 * @return the TipoOrdines
	 */
	public List<TipoEvasione> getTipoEvasiones() {
		final List<CpassDOrdTipoEvasione> cpassDOrdTipoEvasiones = cpassDOrdTipoEvasioneDao.findValid();
		return CpassMappers.TIPO_EVASIONE.toModels(cpassDOrdTipoEvasiones);
	}
	/**
	 * 
	 * @return
	 */
	public List<TipoAcquisto> getTipoAcquistos() {
		final List<CpassDPbaTipoAcquisto> cpassDPbaTipoAcquistos = cpassDPbaTipoAcquistoDao.findAll();
		return CpassMappers.TIPO_ACQUISTO.toModels(cpassDPbaTipoAcquistos);
	}
	/**
	 * 
	 * @param enteId
	 * @return
	 */
	public List<ProvvedimentoTipo> getProvvedimentoTipo(UUID enteId) {
		final List<CpassDProvvedimentoTipo> cpassDProvvedimentoTipos = cpassDProvvedimentoTipoDao.findAllByEnte(enteId);
		return CpassMappers.PROVVEDIMENTO_TIPO.toModels(cpassDProvvedimentoTipos);
	}
	/**
	 * 
	 * @param page
	 * @param size
	 * @param sort
	 * @param oggettiSpesa
	 * @return
	 */
	public PagedList<Cpv> getRicercaCpvOggettiSpesa(int page, int size, Sort sort, Ods oggettiSpesa) {
		final Ente ente = CpassThreadLocalContainer.SETTORE_UTENTE.get().getEnte();
		String sortField = null;
		String sortDirection = null;
		if (sort != null) {
			sortField = InterventoSort.byModelName(sort.getField()).getQueryName();
			sortDirection = sort.getOrder().getSortDirection();
		}

		Integer cpvId = null;
		if(oggettiSpesa.getCpv()!= null && oggettiSpesa.getCpv().getId() != null) {
			cpvId= oggettiSpesa.getCpv().getId();
		}
		final Page<CpassDCpv> listaCpvs = cpassDCpvDao.findCpvOdsPaginated(
				StringUtility.trim(oggettiSpesa.getCodice()),
				StringUtility.trim(oggettiSpesa.getDescrizione()),
				cpvId,
				oggettiSpesa.getGenericoStr(),
				ente.getId(),
				page,
				size,
				sortField,
				sortDirection);

		for (final CpassDCpv cpv : listaCpvs.getContent()) {
			final List<CpassDOggettiSpesa> listOds = cpassDOggettiSpesaDao.findOdsByCpvId(cpv.getId());
			cpv.setCpassDOggettiSpesas(listOds);
		}
		final PagedList<Cpv> pagedList = toPagedList(listaCpvs, page, size, CpassMappers.CPV_ODS::toModel);
		return pagedList;
	}
	/**
	 * 
	 * @return
	 */
	public List<MotiviEsclusioneCig> getMotiviEsclusioneCigs() {
		final List<CpassDMotiviEsclusioneCig> lista = cpassDMotiviEsclusioneCigDao.findAll();
		return CpassMappers.MOTIVI_ESCLUSIONE_CIG.toModels(lista);
	}
	/**
	 * 
	 * @param enteId
	 * @return
	 */
	public List<TipoSettore> getTipoSettores(UUID enteId) {
		final List<CpassDTipoSettore> lista = cpassDTipoSettoreDao.findByEnteId(enteId);
		return CpassMappers.TIPO_SETTORE.toModels(lista);
	}
	/**
	 * 
	 * @param codeTipoSettore
	 * @param enteId
	 * @return
	 */
	public Optional<TipoSettore> getTipoSettoreByCodeAndEnteId(String codeTipoSettore, UUID enteId) {
		final Optional<TipoSettore> opt = cpassDTipoSettoreDao.getTipoSettoreByCodeAndEnteId( codeTipoSettore,  enteId).map(CpassMappers.TIPO_SETTORE::toModel);
		return opt;
	}
	/**
	 * 
	 * @param codeTipoSettore
	 * @param enteId
	 * @return
	 */
	public Optional<TipoSettore> getTipoSettoreByCodeObbligatorioAndEnteId(String codeTipoSettore, UUID enteId) {
		// TODO mettere mano a questa pezza
		if(codeTipoSettore == null || codeTipoSettore.trim().equals("")) {
			codeTipoSettore = "###AAA###";
		}
		final Optional<TipoSettore> opt = cpassDTipoSettoreDao.getTipoSettoreByCodeAndEnteId( codeTipoSettore,  enteId).map(CpassMappers.TIPO_SETTORE::toModel);
		return opt;
	}
	/**
	 * 
	 * @param moduli
	 * @return
	 */
	public List<Permesso> getPermessiByModuli(List<String> moduli) {
		final List<CpassDPermesso> cpassDPermessi = cpassDPermessoDao.getPermessiByModuliAndDisattivabile(moduli.toArray(new String[0]), "SI");
		final List<Permesso> permessi = CpassMappers.PERMESSO.toModels(cpassDPermessi);
		return permessi;
	}
	/**
	 * 
	 * @param permesso
	 * @param flag
	 */
	public void updatePermesso(Permesso permesso, Boolean flag) {
		final CpassDPermesso entity = CpassMappers.PERMESSO.toEntity(permesso);
		entity.setAttivo(flag);
		cpassDPermessoDao.update(entity);
	}
}
