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
import java.util.Optional;
import java.util.UUID;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;

import it.csi.cpass.cpassbe.ejb.business.be.dad.sort.InterventoSort;
import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.CpassDCpvDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.CpassDRuoloDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.CpassDStatoDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.CpassRInterventoCpvDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.CpassTEnteDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.CpassTMetadatiFunzioneDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.CpassTParametroDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.CpassTSettoreDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.CpassTUtenteDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.pba.CpassRPbaStoricoInterventoRupDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.pba.CpassTPbaInterventoAltriDatiDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.pba.CpassTPbaInterventoDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.pba.CpassTPbaInterventoImportiDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.pba.CpassVStatoInterventoInfoDao;
import it.csi.cpass.cpassbe.ejb.entity.CpassDCpv;
import it.csi.cpass.cpassbe.ejb.entity.CpassDRuolo;
import it.csi.cpass.cpassbe.ejb.entity.CpassDStato;
import it.csi.cpass.cpassbe.ejb.entity.CpassTEnte;
import it.csi.cpass.cpassbe.ejb.entity.CpassTMetadatiFunzione;
import it.csi.cpass.cpassbe.ejb.entity.CpassTParametro;
import it.csi.cpass.cpassbe.ejb.entity.CpassTSettore;
import it.csi.cpass.cpassbe.ejb.entity.CpassTUtente;
import it.csi.cpass.cpassbe.ejb.entity.pba.CpassRInterventoCpv;
import it.csi.cpass.cpassbe.ejb.entity.pba.CpassRPbaStoricoInterventoRup;
import it.csi.cpass.cpassbe.ejb.entity.pba.CpassTPbaIntervento;
import it.csi.cpass.cpassbe.ejb.entity.pba.CpassTPbaInterventoAltriDati;
import it.csi.cpass.cpassbe.ejb.entity.pba.CpassTPbaInterventoImporti;
import it.csi.cpass.cpassbe.ejb.entity.pba.CpassTPbaProgramma;
import it.csi.cpass.cpassbe.ejb.exception.NotFoundException;
import it.csi.cpass.cpassbe.ejb.mapper.CpassMappers;
import it.csi.cpass.cpassbe.ejb.util.ConstantsCPassParametro;
import it.csi.cpass.cpassbe.ejb.util.ConstantsCPassRisorsa;
import it.csi.cpass.cpassbe.ejb.util.CpassEnum;
import it.csi.cpass.cpassbe.ejb.util.CpassRuoloEnum;
import it.csi.cpass.cpassbe.ejb.util.CpassThreadLocalContainer;
import it.csi.cpass.cpassbe.ejb.util.jpa.Page;
import it.csi.cpass.cpassbe.lib.dto.Cpv;
import it.csi.cpass.cpassbe.lib.dto.MetadatiFunzione;
import it.csi.cpass.cpassbe.lib.dto.Utente;
import it.csi.cpass.cpassbe.lib.dto.custom.StatoInterventoInfo;
import it.csi.cpass.cpassbe.lib.dto.pba.Intervento;
import it.csi.cpass.cpassbe.lib.dto.pba.InterventoAltriDati;
import it.csi.cpass.cpassbe.lib.dto.pba.InterventoImporti;
import it.csi.cpass.cpassbe.lib.dto.pba.Programma;
import it.csi.cpass.cpassbe.lib.dto.pba.StoricoInterventoRup;
import it.csi.cpass.cpassbe.lib.util.pagination.PagedList;
import it.csi.cpass.cpassbe.lib.util.pagination.Sort;

/**
 * Data Access Delegate for interventos
 */
@ApplicationScoped
public class InterventoDad extends BaseDad {

	@Inject
	private CpassTEnteDao cpassTEnteDao;
	@Inject
	private CpassTUtenteDao cpassTUtenteDao;
	@Inject
	private CpassDStatoDao cpassDStatoDao;
	@Inject
	private CpassTPbaInterventoDao cpassTPbaInterventoDao;
	@Inject
	private CpassTPbaInterventoImportiDao cpassTPbaInterventoImportiDao;
	@Inject
	private CpassTPbaInterventoAltriDatiDao cpassTPbaInterventoAltriDatiDao;
	@Inject
	private CpassDRuoloDao cpassDRuoloDao;
	@Inject
	private CpassTSettoreDao cpassTSettoreDao;
	@Inject
	private CpassDCpvDao cpassDCpvDao;
	@Inject
	private CpassRInterventoCpvDao cpassRInterventoCpvDao;
	@Inject
	private CpassRPbaStoricoInterventoRupDao cpassRPbaStoricoInterventoRupDao;
	@Inject
	private CpassTMetadatiFunzioneDao cpassTMetadatiFunzioneDao;
	@Inject
	private CpassTParametroDao cpassTParametroDao;
	@Inject
	private CpassVStatoInterventoInfoDao cpassVStatoInterventoInfoDao;
	
	/**
	 * Find by id
	 * 
	 * @param uuid the uuid
	 * @return the model instance
	 */
	public Optional<Intervento> getIntervento(UUID uuid) {
		return cpassTPbaInterventoDao.findOne(uuid).map(CpassMappers.INTERVENTO::toModel);
	}

	/**
	 * Find paginated
	 * 
	 * @param page the page
	 * @param size the size
	 * @return the model instances
	 */
	public PagedList<Intervento> getInterventi(int page, int size) {
		Page<CpassTPbaIntervento> cpassTPbaInterventos = cpassTPbaInterventoDao.findAll(page, size);
		return toPagedList(cpassTPbaInterventos, page, size, CpassMappers.INTERVENTO::toModel);
	}

	/**
	 * Find paginated
	 * 
	 * @param page       the page
	 * @param size       the size
	 * @param sort
	 * @param intervento the intervento
	 * @return the model instances
	 */
	public PagedList<Intervento> getRicercaInterventi(int page, int size, Sort sort, Intervento intervento, UUID settoreId,String ordinamento) {
		UUID enteId = checkUtenteAndGetEnte(settoreId);

		String sortField     = null;
		String sortDirection = null;
		if(StringUtils.isEmpty(ordinamento)) {			
			if (sort != null) {
				sortField = InterventoSort.byModelName(sort.getField()).getQueryName();
				sortDirection = sort.getOrder().getSortDirection();
			}
		}			
		
		Page<CpassTPbaIntervento> cpassTPbaInterventos = cpassTPbaInterventoDao.findPaginated(intervento.getCui(), intervento.getAnnoAvvio(),
				intervento.getCup() != null ? intervento.getCup() : null, intervento.getLottoFunzionale(), intervento.getDurataMesi(),
				intervento.getNuovoAffidamento(), intervento.getDescrizioneAcquisto() != null ? intervento.getDescrizioneAcquisto() : null,
				intervento.getUtenteRup() != null ? intervento.getUtenteRup().getCognome() : null,
				// intervento.getProgramma() != null ? intervento.getProgramma().getAnno() : null,
				getId(intervento.getProgramma()), 
				getId(intervento.getAusa()), 
				//getId(intervento.getInterventoRicompreso()),
				intervento.getRicompresoCui(),
				getId(intervento.getInterventoCopia()), 
				getId(intervento.getSettoreInterventi()), 
				getId(intervento.getCpv()), getId(intervento.getNuts()),
				getId(intervento.getPriorita()), getId(intervento.getModalitaAffidamento()), 
				getId(intervento.getStato()),
				getId(intervento.getAcquistoVariato()), 
				getId(intervento.getRicompresoTipo()), 
				getId(intervento.getSettore()), enteId, page, size, sortField, sortDirection,ordinamento);

		PagedList<Intervento> pagedList = toPagedList(cpassTPbaInterventos, page, size, CpassMappers.INTERVENTO::toModel);
		for (Intervento interventoItem : pagedList.getList()) {
			// cerca la risorsa capitale privato valorizzata
			for (InterventoImporti interventoImporti : interventoItem.getListInterventoImporti()) {
				if (interventoImporti.getRisorsa().getTipo().equals(ConstantsCPassRisorsa.TipoEnum.CAPITALE_PRIVATO.getTipo())) {
					boolean importiTuttiAZero = interventoImporti.getImportoAnnoPrimo().signum() == 0 && interventoImporti.getImportoAnnoSecondo().signum() == 0
							&& interventoImporti.getImportoAnniSuccessivi().signum() == 0;
					if (!importiTuttiAZero) {
						interventoItem.setRisorsaIdCapitalePrivato(interventoImporti.getRisorsa().getId());
					}
				}
			}
		}
		return pagedList;
	}



	private UUID checkUtenteAndGetEnte(UUID settoreId) {
		if (settoreId == null) {
			return null;
		}
		Utente utenteConnesso = CpassThreadLocalContainer.UTENTE_CONNESSO.get();
		boolean bRefpAdmin = false;
		List<CpassDRuolo> cpassDRuolos = cpassDRuoloDao.getRuoliByUtenteSettore(settoreId, utenteConnesso.getId());
		for (CpassDRuolo cpassDRuolo : cpassDRuolos) {
			if (cpassDRuolo.getRuoloCodice().equalsIgnoreCase(CpassRuoloEnum.REFP.getCodice())
					|| cpassDRuolo.getRuoloCodice().equalsIgnoreCase(CpassRuoloEnum.ADMIN.getCodice())) {
				bRefpAdmin = true;
			}
		}

		UUID enteId = null;
		if (!bRefpAdmin) {
			Optional<CpassTSettore> optionalSettore = cpassTSettoreDao.findOne(settoreId);
			CpassTSettore cpassTSettore = optionalSettore.get();
			enteId = cpassTSettore.getCpassTEnte().getId();
		}
		return enteId;
	}

	/**
	 * Deletes by uuid
	 * 
	 * @param uuid the uuid
	 */
	public void deleteIntervento(UUID uuid) {
		cpassTPbaInterventoDao.deleteLogically(uuid);
	}

	/**
	 * Inserts the intervento
	 * 
	 * @param intervento the intervento
	 * @return the model instance
	 */
	public Intervento saveIntervento(Intervento intervento) {
		CpassTPbaIntervento cpassTPbaIntervento = CpassMappers.INTERVENTO.toEntity(intervento);
		if (StringUtils.isBlank(intervento.getCui())) {
			/* Settore (F=forniture; S=servizi) + CF amministrazione + Anno Programma + progressivo di 5 cifre per annualità programma */
			String settore = intervento.getSettoreInterventi().getCodice();
			String cf = cpassTEnteDao.findOne(intervento.getProgramma().getEnte().getId()).map(CpassTEnte::getEnteCodiceFiscale).orElse("");
			Integer annoProgramma = intervento.getProgramma().getAnno();
			String codice = settore + cf + annoProgramma;
			Integer intProgressivo = super.getProgressivo(CpassEnum.INTERVENTO_CUI.getCostante(), codice);
			String progressivo = StringUtils.leftPad(String.valueOf(intProgressivo), 5, '0');
			cpassTPbaIntervento.setInterventoCui(codice + progressivo);
			cpassTPbaIntervento.setFlagCuiNonGenerato(Boolean.FALSE);
		} else {
			cpassTPbaIntervento.setFlagCuiNonGenerato(Boolean.TRUE);
		}
		cpassTPbaIntervento = cpassTPbaInterventoDao.insert(cpassTPbaIntervento);
		intervento.setId(cpassTPbaIntervento.getId());
		intervento.setCui(cpassTPbaIntervento.getInterventoCui());

		// salvataggio importi di quel dato intervento
		for (InterventoImporti interventoImporti : intervento.getListInterventoImporti()) {
			CpassTPbaInterventoImporti cpassTPbaInterventoImporti = CpassMappers.INTERVENTO_IMPORTI.toEntity(interventoImporti);
			cpassTPbaInterventoImporti.setCpassTPbaIntervento(cpassTPbaIntervento);
			
			if (cpassTPbaInterventoImporti.getRichiestaMotivazione() == null) {
				cpassTPbaInterventoImporti.setRichiestaMotivazione(Boolean.FALSE);
			}
			cpassTPbaInterventoImporti = cpassTPbaInterventoImportiDao.insert(cpassTPbaInterventoImporti);
			interventoImporti.setId(cpassTPbaInterventoImporti.getId());
		}

		// inserisco "altri dati"
		if (intervento.getListInterventoAltriDati() != null && intervento.getListInterventoAltriDati().size() > 0) {
			InterventoAltriDati interventoAltriDati = intervento.getListInterventoAltriDati().get(0);
			CpassTPbaInterventoAltriDati cpassTPbaInterventoAltriDati = CpassMappers.INTERVENTO_ALTRI_DATI.toEntity(interventoAltriDati);
			cpassTPbaInterventoAltriDati.setCpassTPbaIntervento(cpassTPbaIntervento);
			cpassTPbaInterventoAltriDatiDao.insert(cpassTPbaInterventoAltriDati);
		}
				
		// inserisco "lista cpv "
		for(Cpv cpv : intervento.getListCpv()) {
			CpassDCpv cpassTCpv = CpassMappers.CPV.toEntity(cpv);
			CpassRInterventoCpv cpassRInterventoCpv = new CpassRInterventoCpv();
			cpassRInterventoCpv.setCpassTPbaIntervento(cpassTPbaIntervento);
			cpassRInterventoCpv.setCpassDCpv(cpassTCpv);
			cpassRInterventoCpvDao.insert(cpassRInterventoCpv);
		}
		
		//flush Altri dati
		if (intervento.getListInterventoAltriDati() != null && intervento.getListInterventoAltriDati().size() > 0) {
			List<CpassTPbaInterventoAltriDati> cpassTPbaInterventiAltriDati = cpassTPbaInterventoAltriDatiDao.getByInterventoId(intervento.getId());
			List<InterventoAltriDati> interventiAltriDati = CpassMappers.INTERVENTO_ALTRI_DATI.toModels(cpassTPbaInterventiAltriDati);		
			intervento.setListInterventoAltriDati(interventiAltriDati);
		}
		return intervento;
	}

	/**
	 * Inserts the intervento
	 * 
	 * @param interventoOld
	 * @param interventoCopiaTipo
	 * @param interventoImportoCopiaTipo
	 * @param programmaNew
	 * @return the model instance
	 */
	public Intervento saveInterventoDaCopia(Intervento interventoOld, String interventoCopiaTipo, String interventoImportiCopiaTipo, Programma programmaNew) {
		Intervento interventoNew;
		interventoNew = CpassMappers.INTERVENTO.cloneToModel(interventoOld);
		interventoNew.setId(null);
		interventoNew.setProgramma(programmaNew);
		interventoNew.setMotivazioneNonRiproposto(interventoOld.getMotivazioneNonRiproposto());
		interventoNew.setListInterventoImporti(new ArrayList<>());
		interventoNew.setInterventoCopia(interventoOld);
		interventoNew.setInterventoCopiaTipo(interventoCopiaTipo);
		interventoNew.setInterventoImportiCopiaTipo(interventoImportiCopiaTipo);
		interventoNew.setAnnoAvvio(programmaNew.getAnno());

		if (interventoCopiaTipo.equals(CpassEnum.COPIA_MANTENENDO_CUI.getCostante()) || interventoCopiaTipo.equals(CpassEnum.ACQ_NON_RIPROPOSTO.getCostante())) {
			interventoNew.setFlagCuiNonGenerato(Boolean.TRUE);
		} else {
			/* Settore (F=forniture; S=servizi) + CF amministrazione + Anno Programma + progressivo di 5 cifre per annualità programma */
			String settore = interventoNew.getSettoreInterventi().getCodice();
			String cf = cpassTEnteDao.findOne(programmaNew.getEnte().getId()).map(CpassTEnte::getEnteCodiceFiscale).orElse("");
			Integer annoProgramma = programmaNew.getAnno();
			String codice = settore + cf + annoProgramma;
			Integer intProgressivo = super.getProgressivo(CpassEnum.INTERVENTO_CUI.getCostante(), codice);
			String progressivo = StringUtils.leftPad(String.valueOf(intProgressivo), 5, '0');
			interventoNew.setCui(codice + progressivo);
			interventoNew.setFlagCuiNonGenerato(Boolean.FALSE);
		}

		CpassTPbaIntervento cpassTPbaIntervento = CpassMappers.INTERVENTO.toEntity(interventoNew);
		CpassTPbaIntervento cpassTPbaInterventoInserito = cpassTPbaInterventoDao.insert(cpassTPbaIntervento);
		interventoNew.setId(cpassTPbaInterventoInserito.getId());
		interventoNew.setCui(cpassTPbaInterventoInserito.getInterventoCui());

		// salvataggio importi di quel dato intervento
		List<InterventoImporti> listInterventoImporti = new ArrayList<InterventoImporti>();
		for (InterventoImporti interventoImporti : interventoOld.getListInterventoImporti()) {

			if (!StringUtils.isBlank(interventoImportiCopiaTipo) && interventoImportiCopiaTipo.equals(CpassEnum.IMPORTI_TRASLATI.getCostante())) {
				interventoImporti.setImportoAnniSuccessivi(interventoImporti.getImportoAnnoSecondo());
				interventoImporti.setImportoAnnoSecondo(interventoImporti.getImportoAnnoPrimo());
				interventoImporti.setImportoAnnoPrimo(BigDecimal.ZERO);
			}

			CpassTPbaInterventoImporti cpassTPbaInterventoImporti = CpassMappers.INTERVENTO_IMPORTI.toEntity(interventoImporti);
			cpassTPbaInterventoImporti.setId(null);
			cpassTPbaInterventoImporti.setCpassTPbaIntervento(cpassTPbaInterventoInserito);
			cpassTPbaInterventoImporti = cpassTPbaInterventoImportiDao.insert(cpassTPbaInterventoImporti);
			interventoImporti.setId(cpassTPbaInterventoImporti.getId());
			listInterventoImporti.add(interventoImporti);
		}
		interventoNew.setListInterventoImporti(listInterventoImporti);
		// inserisco "altri dati"*/		
		if (interventoOld.getListInterventoAltriDati() != null && interventoOld.getListInterventoAltriDati().size() > 0) {
			InterventoAltriDati interventoAltriDati = interventoOld.getListInterventoAltriDati().get(0);
			CpassTPbaInterventoAltriDati cpassTPbaInterventoAltriDati = CpassMappers.INTERVENTO_ALTRI_DATI.toEntity(interventoAltriDati);
			cpassTPbaInterventoAltriDati.setCpassTPbaIntervento(cpassTPbaIntervento);
			cpassTPbaInterventoAltriDati = cpassTPbaInterventoAltriDatiDao.insert(cpassTPbaInterventoAltriDati);			
			List<InterventoAltriDati> listInterventoAltriDati = new ArrayList<InterventoAltriDati>();
			listInterventoAltriDati.add(CpassMappers.INTERVENTO_ALTRI_DATI.toModel(cpassTPbaInterventoAltriDati));
			interventoNew.setListInterventoAltriDati(listInterventoAltriDati);
		}
				
		// inserisco "lista cpv "
		for(Cpv cpv : interventoOld.getListCpv()) {
			CpassDCpv cpassTCpv = CpassMappers.CPV.toEntity(cpv);
			CpassRInterventoCpv cpassRInterventoCpv = new CpassRInterventoCpv();
			cpassRInterventoCpv.setCpassTPbaIntervento(cpassTPbaIntervento);
			cpassRInterventoCpv.setCpassDCpv(cpassTCpv);
			cpassRInterventoCpvDao.insert(cpassRInterventoCpv);
		}
		return interventoNew;
	}

	public List<Intervento> saveInterventi(List<Intervento> interventi) {
		List<Intervento> ris = new ArrayList<>();
		for (Intervento intervento : interventi) {
			ris.add(saveIntervento(intervento));
		}
		return ris;
	}

	/**
	 * Saves the interventi
	 * 
	 * @param interventi
	 * @return the interventi
	 */
	/*
	public List<Intervento> saveInterventiNonRipropostiInCreazioneNuovoProgramma(List<Intervento> interventi) {
		List<Intervento> ris = new ArrayList<>();
		for (Intervento intervento : interventi) {
			ris.add(saveIntervento(intervento));
		}
		return ris;
	}
	*/
	/**
	 * Updates the intervento
	 * 
	 * @param intervento the intervento
	 * @return the model instance
	 */
	public Intervento updateIntervento(Intervento intervento) {
		CpassTPbaIntervento cpassTPbaIntervento = CpassMappers.INTERVENTO.toEntity(intervento);		
		cpassTPbaIntervento = cpassTPbaInterventoDao.update(cpassTPbaIntervento);
		for (InterventoImporti interventoImporti : intervento.getListInterventoImporti()) {
			//CpassTPbaInterventoImporti cpassTPbaInterventoImporti = cpassTPbaInterventoImportiDao.findOne(interventoImporti.getId()).get();
			CpassTPbaInterventoImporti cpassTPbaInterventoImporti = CpassMappers.INTERVENTO_IMPORTI.toEntity(interventoImporti);
			cpassTPbaInterventoImporti.setCpassTPbaIntervento(cpassTPbaIntervento);
			if (cpassTPbaInterventoImporti.getRichiestaMotivazione() == null) {
				cpassTPbaInterventoImporti.setRichiestaMotivazione(Boolean.FALSE);
			}
			cpassTPbaInterventoImportiDao.save(cpassTPbaInterventoImporti);
		}
		// cancello vecchi "altri dati"
		List<CpassTPbaInterventoAltriDati> cpassTPbaInterventoAltriDatis = cpassTPbaInterventoAltriDatiDao.getByInterventoId(intervento.getId());
		if (cpassTPbaInterventoAltriDatis != null && cpassTPbaInterventoAltriDatis.size() > 0) {
			for (CpassTPbaInterventoAltriDati cpassTPbaInterventoAltriDati : cpassTPbaInterventoAltriDatis) {
				cpassTPbaInterventoAltriDatiDao.delete(cpassTPbaInterventoAltriDati.getId());
			}
		}
		cpassTPbaInterventoAltriDatiDao.flushAndClear();

		// inserisco "altri dati"
		if (intervento.getListInterventoAltriDati() != null && intervento.getListInterventoAltriDati().size() > 0) {
			InterventoAltriDati interventoAltriDati = intervento.getListInterventoAltriDati().get(0);
			CpassTPbaInterventoAltriDati cpassTPbaInterventoAltriDati = CpassMappers.INTERVENTO_ALTRI_DATI.toEntity(interventoAltriDati);
			cpassTPbaInterventoAltriDati.setCpassTPbaIntervento(cpassTPbaIntervento);
			cpassTPbaInterventoAltriDatiDao.insert(cpassTPbaInterventoAltriDati);
		}

		/////////////////////////////lista CPV///////////////
		List<CpassRInterventoCpv> lista = cpassRInterventoCpvDao.getCpassRInterventoCpvByIntervento(cpassTPbaIntervento.getId());
		if (cpassTPbaInterventoAltriDatis != null ) {
			for (CpassRInterventoCpv cpassRInterventoCpv : lista) {
				cpassRInterventoCpvDao.delete(cpassRInterventoCpv.getId());
			}
		}
		cpassRInterventoCpvDao.flushAndClear();

		for(Cpv cpv : intervento.getListCpv()) {
			CpassDCpv cpassTCpv = CpassMappers.CPV.toEntity(cpv);
			CpassRInterventoCpv cpassRInterventoCpv = new CpassRInterventoCpv();
			cpassRInterventoCpv.setCpassTPbaIntervento(cpassTPbaIntervento);
			cpassRInterventoCpv.setCpassDCpv(cpassTCpv);
			cpassRInterventoCpvDao.update(cpassRInterventoCpv);
		}
		
		return intervento;
	}

	/**
	 * Finds an intervento by its CUI
	 * 
	 * @param cui         the CUI
	 * @param idProgramma the id programma
	 * @return the model instance
	 */
	public Optional<Intervento> findInterventoByCUI(String cui, UUID idProgramma, UUID settoreId) {
		UUID enteId = checkUtenteAndGetEnte(settoreId);
		Optional<CpassTPbaIntervento> optionalIntervento = cpassTPbaInterventoDao.findByCUI(cui, idProgramma, enteId);
		if (optionalIntervento.isEmpty()) {
			return optionalIntervento.map(CpassMappers.INTERVENTO::toModel);
		} else {
			CpassTPbaIntervento cpassTPbaIntervento = optionalIntervento.get();
			Intervento intervento = CpassMappers.INTERVENTO.toModel(cpassTPbaIntervento);
			for (CpassRInterventoCpv cpassRInterventoCpv : cpassTPbaIntervento.getCpassRInterventoCpvs()) {
				intervento.getListCpv().add(CpassMappers.CPV.toModel(cpassRInterventoCpv.getCpassDCpv()));
			}			
			Optional<Intervento> optional = Optional.of(intervento);
			return optional;
		}
	}

	/**
	 * Finds an intervento by its CUI
	 * 
	 * @param cui         the CUI
	 * @param idProgramma the id programma
	 * @return the model instance
	 */
	public Optional<Intervento> findInterventoRicompreso(String cui, UUID idProgramma) {
		return cpassTPbaInterventoDao.findInterventoRicompreso(cui, idProgramma).map(CpassMappers.INTERVENTO::toModel);
	}

	/**
	 * Updates the stato intervento
	 * 
	 * @param id          the id
	 * @param statoCodice the stato codice
	 * @param statoTipo   the stato tipo
	 */
	public void updateStatoIntervento(UUID id, String statoCodice, String statoTipo) {
		CpassTPbaIntervento cpassTPbaIntervento = cpassTPbaInterventoDao.findOne(id).orElseThrow(() -> new NotFoundException("intervento"));
		CpassDStato cpassDStato = cpassDStatoDao.findByCodiceTipo(statoCodice, statoTipo).orElseThrow(() -> new NotFoundException("stato"));
		cpassTPbaIntervento.setCpassDStato(cpassDStato);
		cpassTPbaInterventoDao.update(cpassTPbaIntervento);
	}
	/**
	 * Updates the stato intervento e relativi campi 
	 * 
	 * @param intervento intervento
	 */	
	public void updateStatoIntervento(Intervento intervento) {
		CpassTPbaIntervento cpassTPbaIntervento = CpassMappers.INTERVENTO.toEntity(intervento);
		cpassTPbaIntervento = cpassTPbaInterventoDao.update(cpassTPbaIntervento);
	}

	/**
	 * 
	 * @param idProgramma
	 * @param statoCode
	 * @return the interventi by programma and stato
	 */
	public List<Intervento> getInterventoByProgrammaStato(UUID idProgramma, String statoCode) {
		return getInterventoByProgrammaStato(idProgramma, statoCode, true);
	}

	/**
	 * 
	 * @param idProgramma
	 * @param statoCode
	 * @param operatoreEqualsStato
	 * @return the interventi by programma and stato
	 */
	public List<Intervento> getInterventoByProgrammaStato(UUID idProgramma, String statoCode, boolean operatoreEqualsStato) {
		List<CpassTPbaIntervento> entities = cpassTPbaInterventoDao.getInterventoByProgrammaStato(idProgramma, statoCode, operatoreEqualsStato);
		return CpassMappers.INTERVENTO.toModels(entities);
	}

	/**
	 * @param page
	 * @param size
	 * @param sort
	 * @param programmaIdOld
	 * @param programmaIdNew
	 * @return the interventi per copia
	 */
	public PagedList<Intervento> getRicercaInterventiXCopia(int page, int size, Sort sort, UUID programmaIdOld, UUID programmaIdNew) {
		Page<CpassTPbaIntervento> cpassTPbaInterventos = cpassTPbaInterventoDao.findPaginatedXCopia(programmaIdOld, programmaIdNew, page, size,
				InterventoSort.byModelName(sort.getField()).getQueryName(), sort.getOrder().getSortDirection());
		return toPagedList(cpassTPbaInterventos, page, size, CpassMappers.INTERVENTO::toModel);
	}

	/**
	 * 
	 * @param interventoId
	 * @param programmaId
	 * @return
	 */
	public Optional<Intervento> getInterventoEsistenteProgramma(UUID interventoId, UUID programmaId) {
		return cpassTPbaInterventoDao.getInterventoEsistenteProgramma(interventoId, programmaId).map(CpassMappers.INTERVENTO::toModel);
	}

	public List<Cpv> getCpvsByInterventoId(UUID idIntervento) {
		List<CpassDCpv> entities = cpassDCpvDao.getCpvsByInterventoId(idIntervento);
		return CpassMappers.CPV.toModels(entities);
	}

	public List<StatoInterventoInfo> getUltimoStatoInfoByIntervento(UUID idIntervento) {
		return cpassVStatoInterventoInfoDao.getUltimoStatoInfoByIntervento(idIntervento);
	}
	
	
	
	public List<StoricoInterventoRup> getStoricoRupsByInterventoIdService(UUID idIntervento) {
		List<CpassRPbaStoricoInterventoRup> entities = cpassRPbaStoricoInterventoRupDao.getStoricoRupsByInterventoId(idIntervento);
		return CpassMappers.INTERVENTO_STORICO_RUP.toModels(entities);
	}

	public CpassRPbaStoricoInterventoRup archivioRup(Intervento intervento, Utente utenteRupVecchio) {
		//Archivio il vecchio Rup nello storico
		CpassRPbaStoricoInterventoRup isr = new CpassRPbaStoricoInterventoRup();	
		CpassTPbaIntervento cpassTPbaIntervento = cpassTPbaInterventoDao.findOne(intervento.getId()).get();
		isr.setCpassTPbaIntervento(cpassTPbaIntervento);
		CpassTUtente cpassTUtenteRup = CpassMappers.UTENTE.toEntity(utenteRupVecchio);//cpassTUtenteDao.findById(utenteRupVecchio.getId()).get();
		isr.setCpassTUtenteRup(cpassTUtenteRup);	
		isr.setDataStoricizzazione(new Date());	
		Utente utenteConnesso = CpassThreadLocalContainer.UTENTE_CONNESSO.get();
		isr.setCpassTUtente(cpassTUtenteDao.findOne(utenteConnesso.getId()).get());	
		CpassRPbaStoricoInterventoRup ris = cpassRPbaStoricoInterventoRupDao.insert(isr);
		cpassRPbaStoricoInterventoRupDao.flushAndClear();		
		return ris;
	}

	private String creaOrdinamento1(List<MetadatiFunzione> listMetadatiFunzione) {
		String result     = "";
		String separatore = "";
		String direzione  = "";		
		for (MetadatiFunzione mf:listMetadatiFunzione) {
			direzione  = " DESC ";
			if(mf.getAscendente()==null || mf.getAscendente()) {
				direzione = " ASC ";
			}			
			CpassTMetadatiFunzione cpassTMetadatiFunzione = cpassTMetadatiFunzioneDao.getMetadatiFunzioneByModuloFunzioneChiave("PBA", "RICERCA_INTERVENTO",mf.getChiaveColonna());
			result += separatore + cpassTMetadatiFunzione.getJpql() + direzione ;
			separatore = ", ";
		}
		return result;
	}

	public List<Intervento> getInterventiNonRiproponibiliByUltimoProgrammaPrecedente(Integer annoPrec,Integer programmaVersioneMaxPrecedente) {
		List<CpassTPbaIntervento> entities = cpassTPbaInterventoDao.getInterventiNonRiproponibiliByUltimoProgrammaPrecedente( annoPrec, programmaVersioneMaxPrecedente);	
		return CpassMappers.INTERVENTO.toModels(entities);
	}
	
	public List<Intervento> salvaSuNuovoProgrammaInterventiNonRiproposti(Programma programma, List<Intervento> lista) {
		List<CpassTPbaIntervento> listaNew = new ArrayList<CpassTPbaIntervento>();
		
		CpassTParametro param = cpassTParametroDao.getParametro(ConstantsCPassParametro.ChiaveEnum.MOTIVAZIONE_NON_RIPROPOSTO_DEFAULT.getCostante(),
				ConstantsCPassParametro.RiferimentoEnum.NULL.getCostante(), programma.getEnte().getId());

		CpassTPbaProgramma cpassTPbaProgramma = CpassMappers.PROGRAMMA.toEntity(programma);
		for(Intervento model : lista) {
			CpassTPbaIntervento entity = CpassMappers.INTERVENTO.toEntity(model);						
			List<CpassTPbaInterventoImporti> cpassTPbaInterventoImportis = CpassMappers.INTERVENTO_IMPORTI.toEntities(model.getListInterventoImporti()); 			
			List<CpassRInterventoCpv>        listaCpassRInterventoCpv    = cpassRInterventoCpvDao.getCpassRInterventoCpvByIntervento(entity.getId());
			//entity.setCpassRInterventoCpvs(listaCpassRInterventoCpv);
			//entity.setCpassTPbaInterventoImportis(cpassTPbaInterventoImportis);
			CpassTPbaIntervento cpassTPbaInterventoNew = CpassMappers.INTERVENTO.cloneToEntity(entity);			
			cpassTPbaInterventoNew.setId(null);
			cpassTPbaInterventoNew.setCpassTPbaProgramma(cpassTPbaProgramma);			
			cpassTPbaInterventoNew.setFlagCuiNonGenerato(Boolean.TRUE);
			cpassTPbaInterventoNew.setCpassTPbaInterventoCopia(entity);
			cpassTPbaInterventoNew.setMotivazioneNonRiproposto(param.getValore());   // leggere dalla tabella parametri con chiave Motivazione_non_riproposto_default
			cpassTPbaInterventoNew.setFlagCuiNonGenerato(true);
			cpassTPbaInterventoNew.setInterventoCopiaTipo("ACQ_NON_RIPROPOSTO");
			cpassTPbaInterventoNew.setInterventoImportiCopiaTipo("IMP_NON_TRASLATI");
			CpassTPbaIntervento cpassTPbaInterventoInserito = cpassTPbaInterventoDao.insert(cpassTPbaInterventoNew);
			// gestione lista importi
			List<CpassTPbaInterventoImporti> listcpassTPbaInterventoImporti = new ArrayList<CpassTPbaInterventoImporti>();		

			for ( CpassTPbaInterventoImporti cpassTPbaInterventoImporti : cpassTPbaInterventoImportis) {
				cpassTPbaInterventoImporti.setId(null);
				cpassTPbaInterventoImporti.setCpassTPbaIntervento(cpassTPbaInterventoInserito);
				cpassTPbaInterventoImporti = cpassTPbaInterventoImportiDao.insert(cpassTPbaInterventoImporti);
				listcpassTPbaInterventoImporti.add(cpassTPbaInterventoImporti);
			}
			cpassTPbaInterventoNew.setCpassTPbaInterventoImportis(listcpassTPbaInterventoImporti);
			
			// gestione lista altri dati
			List<CpassTPbaInterventoAltriDati> listcpassTPbaInterventoAltriDati = new ArrayList<CpassTPbaInterventoAltriDati>();
			if(entity.getCpassTPbaInterventoAltriDatis()!=null) {
				for ( CpassTPbaInterventoAltriDati cpassTPbaInterventoaltriDati : entity.getCpassTPbaInterventoAltriDatis()) {
					cpassTPbaInterventoaltriDati.setId(null);
					cpassTPbaInterventoaltriDati.setCpassTPbaIntervento(cpassTPbaInterventoInserito);
					cpassTPbaInterventoaltriDati = cpassTPbaInterventoAltriDatiDao.insert(cpassTPbaInterventoaltriDati);
					listcpassTPbaInterventoAltriDati.add(cpassTPbaInterventoaltriDati);
				}
			}
			// inserisco "lista cpv "
			if(listaCpassRInterventoCpv!=null) {
				for(CpassRInterventoCpv el : listaCpassRInterventoCpv) {
					//CpassDCpv cpassTCpv = CpassMappers.CPV.toEntity(cpv);
					CpassRInterventoCpv cpassRInterventoCpv = new CpassRInterventoCpv();
					cpassRInterventoCpv.setCpassTPbaIntervento(cpassTPbaInterventoNew);
					cpassRInterventoCpv.setCpassDCpv(el.getCpassDCpv());
					cpassRInterventoCpvDao.insert(cpassRInterventoCpv);
				}
			}			
			cpassTPbaInterventoNew.setCpassTPbaInterventoImportis(listcpassTPbaInterventoImporti);
			listaNew.add(cpassTPbaInterventoNew);
		}
		return CpassMappers.INTERVENTO.toModels(listaNew);
	}
}
