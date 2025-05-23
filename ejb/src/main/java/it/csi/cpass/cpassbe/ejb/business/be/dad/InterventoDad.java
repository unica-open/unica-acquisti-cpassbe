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
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;

import it.csi.cpass.cpassbe.ejb.business.be.dad.sort.InterventoSort;
import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.CpassRInterventoCpvDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.CpassTEnteDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.CpassTParametroDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.CpassTProgressivoDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.CpassTUtenteDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.decodifiche.CpassDCpvDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.decodifiche.CpassDStatoDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.pba.CpassRPbaStatiInterventoDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.pba.CpassRPbaStoricoInterventoRupDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.pba.CpassTPbaAcquistiCapPrivatiDaTrasmettereDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.pba.CpassTPbaAcquistiDaTrasmettereDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.pba.CpassTPbaInterventoAltriDatiDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.pba.CpassTPbaInterventoCigDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.pba.CpassTPbaInterventoDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.pba.CpassTPbaInterventoImportiDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.pba.CpassVStatoInterventoInfoDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.pba.CpassVUltimoProgrammaBiennioDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.view.CpassVSettoreDao;
import it.csi.cpass.cpassbe.ejb.entity.CpassDCpv;
import it.csi.cpass.cpassbe.ejb.entity.CpassDStato;
import it.csi.cpass.cpassbe.ejb.entity.CpassTEnte;
import it.csi.cpass.cpassbe.ejb.entity.CpassTParametro;
import it.csi.cpass.cpassbe.ejb.entity.CpassTUtente;
import it.csi.cpass.cpassbe.ejb.entity.pba.CpassRInterventoCpv;
import it.csi.cpass.cpassbe.ejb.entity.pba.CpassRPbaStatiIntervento;
import it.csi.cpass.cpassbe.ejb.entity.pba.CpassRPbaStoricoInterventoRup;
import it.csi.cpass.cpassbe.ejb.entity.pba.CpassTPbaAcquistiCapPrivatiDaTrasmettere;
import it.csi.cpass.cpassbe.ejb.entity.pba.CpassTPbaAcquistiDaTrasmettere;
import it.csi.cpass.cpassbe.ejb.entity.pba.CpassTPbaIntervento;
import it.csi.cpass.cpassbe.ejb.entity.pba.CpassTPbaInterventoAltriDati;
import it.csi.cpass.cpassbe.ejb.entity.pba.CpassTPbaInterventoCig;
import it.csi.cpass.cpassbe.ejb.entity.pba.CpassTPbaInterventoImporti;
import it.csi.cpass.cpassbe.ejb.entity.pba.CpassTPbaProgramma;
import it.csi.cpass.cpassbe.ejb.entity.view.CpassVSettore;
import it.csi.cpass.cpassbe.ejb.entity.view.CpassVUltimoProgrammaBiennio;
import it.csi.cpass.cpassbe.ejb.exception.NotFoundException;
import it.csi.cpass.cpassbe.ejb.mapper.CpassMappers;
import it.csi.cpass.cpassbe.ejb.util.ConstantsCPassParametro;
import it.csi.cpass.cpassbe.ejb.util.ConstantsCPassRisorsa;
import it.csi.cpass.cpassbe.ejb.util.ConstantsCPassStato.StatoInterventiEnum;
import it.csi.cpass.cpassbe.ejb.util.CpassEnum;
import it.csi.cpass.cpassbe.ejb.util.StringUtility;
import it.csi.cpass.cpassbe.ejb.util.jpa.Page;
import it.csi.cpass.cpassbe.lib.dto.Cpv;
import it.csi.cpass.cpassbe.lib.dto.Settore;
import it.csi.cpass.cpassbe.lib.dto.Stato;
import it.csi.cpass.cpassbe.lib.dto.Utente;
import it.csi.cpass.cpassbe.lib.dto.custom.StatoInterventoInfo;
import it.csi.cpass.cpassbe.lib.dto.pba.AcquistiCapPrivatiDaTrasmettere;
import it.csi.cpass.cpassbe.lib.dto.pba.AcquistiDaTrasmettere;
import it.csi.cpass.cpassbe.lib.dto.pba.Intervento;
import it.csi.cpass.cpassbe.lib.dto.pba.InterventoAltriDati;
import it.csi.cpass.cpassbe.lib.dto.pba.InterventoCig;
import it.csi.cpass.cpassbe.lib.dto.pba.InterventoImporti;
import it.csi.cpass.cpassbe.lib.dto.pba.Programma;
import it.csi.cpass.cpassbe.lib.dto.pba.StatiIntervento;
import it.csi.cpass.cpassbe.lib.dto.pba.StoricoInterventoRup;
import it.csi.cpass.cpassbe.lib.dto.pba.VIntervento;
import it.csi.cpass.cpassbe.lib.util.pagination.PagedList;
import it.csi.cpass.cpassbe.lib.util.pagination.Sort;
import it.csi.cpass.cpassbe.lib.util.threadlocal.CpassThreadLocalContainer;

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
	private CpassTPbaInterventoCigDao cpassTPbaInterventoCigDao;
	@Inject
	private CpassTPbaInterventoImportiDao cpassTPbaInterventoImportiDao;
	@Inject
	private CpassTPbaInterventoAltriDatiDao cpassTPbaInterventoAltriDatiDao;
	@Inject
	private CpassDCpvDao cpassDCpvDao;
	@Inject
	private CpassRInterventoCpvDao cpassRInterventoCpvDao;
	@Inject
	private CpassRPbaStoricoInterventoRupDao cpassRPbaStoricoInterventoRupDao;
	@Inject
	private CpassTParametroDao cpassTParametroDao;
	@Inject
	private CpassTProgressivoDao cpassTProgressivoDao;
	@Inject
	private CommonDad commonDad;
	@Inject
	private CpassVStatoInterventoInfoDao cpassVStatoInterventoInfoDao;
	@Inject
	private CpassRPbaStatiInterventoDao cpassRPbaStatiInterventoDao;
	@Inject
	private CpassVSettoreDao cpassVSettoreDao;
	@Inject
	private CpassVUltimoProgrammaBiennioDao cpassVUltimoProgrammaBiennio;
	@Inject
	private CpassTPbaAcquistiDaTrasmettereDao cpassTPbaAcquistiDaTrasmettereDao;
	@Inject
	private CpassTPbaAcquistiCapPrivatiDaTrasmettereDao cpassTPbaAcquistiCapPrivatiDaTrasmettereDao;

	/**
	 * Find by id
	 *
	 * @param uuid the uuid
	 * @return the model instance
	 */
	public Optional<Intervento> getInterventoOpt(UUID uuid) {
		final Optional<Intervento> optIntervento = cpassTPbaInterventoDao.findOne(uuid).map(CpassMappers.INTERVENTO::toModel);
		if (optIntervento.isPresent()) {
			setInterventoPresenteInAltroProgramma(optIntervento.get());
		}
		return optIntervento;
	}
	/**
	 * 
	 * @param cui
	 * @param programmaAnno
	 * @param enteId
	 * @return
	 */
	public List<Intervento> getInterventiSuprogrammiFuturi(String cui, Integer programmaAnno, UUID enteId) {
		final List<CpassTPbaIntervento> cpassTPbaInterventos  = cpassTPbaInterventoDao.getInterventiSuprogrammiFuturi(cui,programmaAnno,enteId);
		return CpassMappers.INTERVENTO.toModels(cpassTPbaInterventos);
	}

	/**
	 * Find paginated
	 *
	 * @param page the page
	 * @param size the size
	 * @return the model instances
	 */
	public PagedList<Intervento> getInterventi(int page, int size) {
		final Page<CpassTPbaIntervento> cpassTPbaInterventos = cpassTPbaInterventoDao.findAll(page, size);
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
	public PagedList<Intervento> getRicercaInterventi(int page, int size, Sort sort, Intervento intervento,String ordinamento) {
		final Settore settoreCorrente = CpassThreadLocalContainer.SETTORE_UTENTE.get();
		final UUID enteId = getId(settoreCorrente.getEnte());
		final boolean programmaIsUltimoBiennio = getIsprogrammaUltimoDelBiennio(intervento.getProgramma());
		String sortField     = null;
		String sortDirection = null;
		if(StringUtils.isEmpty(ordinamento)) {
			if (sort != null) {
				sortField = InterventoSort.byModelName(sort.getField()).getQueryName();
				sortDirection = sort.getOrder().getSortDirection();
			}
		}
		final List<UUID> listaSettore = new ArrayList<>();
		if(intervento.getSettore()!=null && intervento.getSettore().getId()!=null) {
			if(intervento.getElementiDipendenti()!=null && intervento.getElementiDipendenti().equals(Boolean.TRUE)) {
				final List<CpassVSettore> listaSons = cpassVSettoreDao.getSettoriSonsBySettoreAndEnte( intervento.getSettore().getId(), enteId);
				for(final CpassVSettore sett : listaSons) {
					listaSettore.add(sett.getId());
				}
			} else {
				listaSettore.add(intervento.getSettore().getId());
			}
		}

		final Page<CpassTPbaIntervento> cpassTPbaInterventos = cpassTPbaInterventoDao.findPaginated(intervento.getCui(), intervento.getAnnoAvvio(),
				intervento.getCup() != null ? intervento.getCup() : null, intervento.getLottoFunzionale(), intervento.getDurataMesi(),
						intervento.getNuovoAffidamento(), intervento.getDescrizioneAcquisto() != null ? intervento.getDescrizioneAcquisto() : null,
								intervento.getUtenteRup() != null ? intervento.getUtenteRup().getCognome() : null,
										getId(intervento.getProgramma()),
										getId(intervento.getAusa()),
										intervento.getRicompresoCui(),
										getId(intervento.getInterventoCopia()),
										getId(intervento.getSettoreInterventi()),
										getId(intervento.getCpv()), getId(intervento.getNuts()),
										getId(intervento.getPriorita()), getId(intervento.getModalitaAffidamento()),
										getId(intervento.getStato()),
										getId(intervento.getAcquistoVariato()),
										getId(intervento.getRicompresoTipo()),
										listaSettore,
										intervento.getSelNonRip(),
										intervento.getVersioneDefinitivaStr(),
										intervento.getVistoRagioneriaStr(),
										enteId, page, size, sortField, sortDirection,ordinamento);

		final PagedList<Intervento> pagedList = toPagedList(cpassTPbaInterventos, page, size, CpassMappers.INTERVENTO::toModel);
		for (final Intervento interventoItem : pagedList.getList()) {
			if(programmaIsUltimoBiennio) {
				interventoItem.getProgramma().setIsUltimoBiennio(Boolean.TRUE);
			} else {
				interventoItem.getProgramma().setIsUltimoBiennio(Boolean.FALSE);
			}
			// calcolo dell'ultimo stato sulla r_stati
			final StatiIntervento st = Collections.max(interventoItem.getStatiInterventos(), Comparator.comparing(StatiIntervento::getData));
			final String ultimoStatoDesc = st.getStato();
			interventoItem.setDescrizioneUltimoStato(ultimoStatoDesc);
			// cerca la risorsa capitale privato valorizzata
			for (final InterventoImporti interventoImporti : interventoItem.getListInterventoImporti()) {
				if (interventoImporti.getRisorsa().getTipo().equals(ConstantsCPassRisorsa.TipoEnum.CAPITALE_PRIVATO.getTipo())) {
					final boolean importiTuttiAZero = interventoImporti.getImportoAnnoPrimo().signum() == 0 && interventoImporti.getImportoAnnoSecondo().signum() == 0
							&& interventoImporti.getImportoAnnoTerzo().signum() == 0 && interventoImporti.getImportoAnniSuccessivi().signum() == 0;
					//boolean importiTuttiAZero = interventoImporti.getImportoAnnoPrimo().signum() == 0 && interventoImporti.getImportoAnnoSecondo().signum() == 0 && interventoImporti.getImportoAnniSuccessivi().signum() == 0;
					if (!importiTuttiAZero) {
						interventoItem.setRisorsaIdCapitalePrivato(interventoImporti.getRisorsa().getId());
					}
				}
			}
		}
		return pagedList;
	}
	/**
	 * 
	 * @param programma
	 * @return
	 */
	private Boolean getIsprogrammaUltimoDelBiennio(Programma programma) {
		final List<CpassVUltimoProgrammaBiennio> lista = cpassVUltimoProgrammaBiennio.getProgrammaUltimoDelBiennioById(programma.getId()) ;
		if(lista.size()>0) {
			return Boolean.TRUE;
		}
		return Boolean.FALSE;
	}

	/**
	 * Deletes by uuid
	 *
	 * @param uuid the uuid
	 */
	public void deleteInterventoLogically(UUID uuid) {
		cpassTPbaInterventoDao.deleteLogically(uuid);
	}

	/**
	 * Inserts the intervento
	 *
	 * @param intervento the intervento
	 * @return the model instance
	 */
	public Intervento saveIntervento(Intervento intervento,Utente utente) {
		CpassTPbaIntervento cpassTPbaIntervento = CpassMappers.INTERVENTO.toEntity(intervento);
		log.info("saveIntervento ", intervento.getCui());
		if (StringUtils.isEmpty(intervento.getCui())) {
			/* Settore (F=forniture; S=servizi) + CF amministrazione + Anno Programma + progressivo di 5 cifre per annualità programma */
			final String settore = intervento.getSettoreInterventi().getCodice();
			final String cf = cpassTEnteDao.findOne(intervento.getProgramma().getEnte().getId()).map(CpassTEnte::getEnteCodiceFiscale).orElse("");
			final Integer annoProgramma = intervento.getProgramma().getAnno();
			final String codice = settore + cf + annoProgramma;
			final Integer intProgressivo = commonDad.getProgressivo(CpassEnum.INTERVENTO_CUI.getCostante(), codice);
			final String progressivo = StringUtils.leftPad(String.valueOf(intProgressivo), 5, '0');
			cpassTPbaIntervento.setInterventoCui(codice + progressivo);
			cpassTPbaIntervento.setFlagCuiNonGenerato(Boolean.FALSE);
			log.info("saveInterventi","cui generato" + cpassTPbaIntervento.getInterventoCui());

		} else {
			cpassTPbaIntervento.setFlagCuiNonGenerato(Boolean.TRUE);
		}
		cpassTPbaIntervento = cpassTPbaInterventoDao.insert(cpassTPbaIntervento);
		intervento.setId(cpassTPbaIntervento.getId());
		intervento.setCui(cpassTPbaIntervento.getInterventoCui());
		cpassTPbaInterventoDao.flush();
		//inserisco lo stato nella r_stati
		//salvaStoricoStatoIntervento(utente, intervento,intervento.getStatoXStorico());
		salvaStoricoStatoIntervento( utente,  intervento, intervento.getStatoXStorico(),Boolean.FALSE);
		// salvataggio importi di quel dato intervento
		for (final InterventoImporti interventoImporti : intervento.getListInterventoImporti()) {
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
			final InterventoAltriDati interventoAltriDati = intervento.getListInterventoAltriDati().get(0);
			final CpassTPbaInterventoAltriDati cpassTPbaInterventoAltriDati = CpassMappers.INTERVENTO_ALTRI_DATI.toEntity(interventoAltriDati);
			cpassTPbaInterventoAltriDati.setCpassTPbaIntervento(cpassTPbaIntervento);
			cpassTPbaInterventoAltriDatiDao.insert(cpassTPbaInterventoAltriDati);
		}
		// inserisco "elenco cig"
		if (intervento.getInterventoCigs() != null && intervento.getInterventoCigs().size() > 0) {
			for(final InterventoCig cig : intervento.getInterventoCigs()) {
				final CpassTPbaInterventoCig cpassTPbaInterventoCig = new CpassTPbaInterventoCig();
				cpassTPbaInterventoCig.setCpassTPbaIntervento(cpassTPbaIntervento);
				cpassTPbaInterventoCig.setCig(cig.getCig());
				cpassTPbaInterventoCigDao.insert(cpassTPbaInterventoCig);
			}
		}
		// inserisco "lista cpv "
		for(final Cpv cpv : intervento.getListCpv()) {
			final CpassDCpv cpassTCpv = CpassMappers.CPV.toEntity(cpv);
			final CpassRInterventoCpv cpassRInterventoCpv = new CpassRInterventoCpv();
			cpassRInterventoCpv.setCpassTPbaIntervento(cpassTPbaIntervento);
			cpassRInterventoCpv.setCpassDCpv(cpassTCpv);
			cpassRInterventoCpvDao.insert(cpassRInterventoCpv);
		}
		//questa parte potrebbe essere accorpata senza la query nel blocco inserimento altri dati poche riche piu' in alto
		if (intervento.getListInterventoAltriDati() != null && intervento.getListInterventoAltriDati().size() > 0) {
			final List<CpassTPbaInterventoAltriDati> cpassTPbaInterventiAltriDati = cpassTPbaInterventoAltriDatiDao.getByInterventoId(intervento.getId());
			final List<InterventoAltriDati> interventiAltriDati = CpassMappers.INTERVENTO_ALTRI_DATI.toModels(cpassTPbaInterventiAltriDati);
			intervento.setListInterventoAltriDati(interventiAltriDati);
		}
		// se il flag capofila == true aggiorno intervento capofila id con l'id intervento medesimo
		if(intervento.getCapofila()) {
			final CpassTPbaIntervento cp = new CpassTPbaIntervento();
			cp.setId(cpassTPbaIntervento.getId());
			cpassTPbaIntervento.setCpassTPbaInterventoCapofila(cp );
			cpassTPbaInterventoDao.update(cpassTPbaIntervento);
		}
		return intervento;
	}
	/**
	 * 
	 * @param intervento
	 * @return
	 */
	public Intervento updateInterventoEasy(Intervento intervento) {
		final CpassTPbaIntervento cpassTPbaIntervento = CpassMappers.INTERVENTO.toEntity(intervento);
		final CpassTPbaIntervento ris = cpassTPbaInterventoDao.update (cpassTPbaIntervento);
		cpassTPbaInterventoDao.flush();
		return CpassMappers.INTERVENTO.toModel(ris);
	}
	/**
	 * 
	 * @param utente
	 * @param intervento
	 * @param statoCodice
	 * @param isRifiuto
	 */
	public void salvaStoricoStatoIntervento(Utente utente, Intervento intervento,String statoCodice,Boolean isRifiuto) {
		final CpassTPbaIntervento cpassTPbaIntervento = CpassMappers.INTERVENTO.toEntity(intervento);
		final CpassRPbaStatiIntervento rstato = new CpassRPbaStatiIntervento();
		rstato.setCpassTPbaIntervento(cpassTPbaIntervento);
		rstato.setData(new Date());
		rstato.setCpassTUtente(CpassMappers.UTENTE.toEntity(utente));
		if(statoCodice !=null && statoCodice.trim().length()>0 ) {
			rstato.setStato(statoCodice);
		}else {
			rstato.setStato(cpassTPbaIntervento.getCpassDStato().getStatoCodice());
		}

		if(isRifiuto) {
			rstato.setMotivazione(intervento.getMotivazioneRifiutoRagioneria());
		}

		if(StringUtility.isNotEmpty( intervento.getMotivazioneRiportaInBozza())) {
			rstato.setMotivazione(intervento.getMotivazioneRiportaInBozza());
		}

		if(StringUtility.isNotEmpty( intervento.getMotivazioneRifiuto())) {
			rstato.setMotivazione(intervento.getMotivazioneRifiuto());
		}
		cpassRPbaStatiInterventoDao.insert(rstato);
	}
	/**
	 * 
	 * @param utente
	 * @param cpassTPbaIntervento
	 * @param statoCodice
	 */
	private void salvaStoricoStatoIntervento(Utente utente, CpassTPbaIntervento cpassTPbaIntervento,String statoCodice) {
		final CpassRPbaStatiIntervento rstato = new CpassRPbaStatiIntervento();
		rstato.setCpassTPbaIntervento(cpassTPbaIntervento);
		rstato.setData(new Date());
		rstato.setCpassTUtente(CpassMappers.UTENTE.toEntity(utente));
		if(statoCodice !=null && statoCodice.trim().length()>0 ) {
			rstato.setStato(statoCodice);
		}else {
			rstato.setStato(cpassTPbaIntervento.getCpassDStato().getStatoCodice());
		}
		cpassRPbaStatiInterventoDao.insert(rstato);
	}
	/**
	 * 
	 * @param utente
	 * @param intervento
	 * @param statoCodice
	 */
	private void salvaStoricoStatoInterventoXCsv(Utente utente, Intervento intervento,String statoCodice) {
		final CpassTPbaIntervento cpassTPbaIntervento = CpassMappers.INTERVENTO.toEntity(intervento);
		final CpassRPbaStatiIntervento rstato = new CpassRPbaStatiIntervento();
		rstato.setCpassTPbaIntervento(cpassTPbaIntervento);
		rstato.setData(new Date());
		rstato.setCpassTUtente(CpassMappers.UTENTE.toEntity(utente));
		rstato.setStato(statoCodice);
		cpassRPbaStatiInterventoDao.insert(rstato);
	}
	/**
	 * 
	 * @param utente
	 * @param intervento
	 * @param statoCodice
	 */
	public void salvaStoricoStatoIntervento(Utente utente, Intervento intervento,String statoCodice) {
		salvaStoricoStatoIntervento( utente,  intervento, statoCodice,Boolean.FALSE);
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
	public Intervento saveInterventoDaCopia(Intervento interventoOld,
			String interventoCopiaTipo,
			String interventoImportiCopiaTipo,
			Programma programmaNew,
			Stato stato,
			String isVistoRagioneria,
			String isVersioneDefinitiva,
			Utente utente,
			String parametroDurataProgramma
			) {

		Intervento interventoNew;
		interventoNew = CpassMappers.INTERVENTO.cloneToModel(interventoOld);
		interventoNew.setId(null);
		interventoNew.setProgramma(programmaNew);
		interventoNew.setStato(stato);
		if (stato.getCodice().equalsIgnoreCase(StatoInterventiEnum.BOZZA.getCostante())) {
			interventoNew.setDataVisto(null);
			interventoNew.setUtenteVisto(null);
			interventoNew.setDataValidazione(null);
			interventoNew.setUtenteValidazione(null);
		}
		if (stato.getCodice().equalsIgnoreCase(StatoInterventiEnum.VALIDATO.getCostante())) {
			interventoNew.setDataVisto(new Date());
			interventoNew.setUtenteVisto(utente);
			interventoNew.setDataValidazione(new Date());
			interventoNew.setUtenteValidazione(utente);
		}

		interventoNew.setUtenteVistoRagioneria(null);
		interventoNew.setDataVistoRagioneria(null);

		interventoNew.setVistoRagioneria(Boolean.TRUE);
		if(isVistoRagioneria != null && isVistoRagioneria.equalsIgnoreCase("true")) {
			interventoNew.setVistoRagioneria(Boolean.FALSE);
		}

		interventoNew.setVersioneDefinitiva(Boolean.FALSE);
		if(isVersioneDefinitiva == null || isVersioneDefinitiva.equalsIgnoreCase("false")) {
			interventoNew.setVersioneDefinitiva(Boolean.TRUE);
		}
		//se acquisto non riproposto l'anno rimane invariato altrimenti
		//Inserire la prima annualità del programma di destinazione, 
		//se il programma di origine ha un biennio inferiore, altrimenti, se esso ha lo stesso biennio del programma di destinazione
		//,lasciare lo stesso valore
		if (!interventoCopiaTipo.equals(CpassEnum.ACQ_NON_RIPROPOSTO.getCostante())
				&& interventoOld.getProgramma().getAnno()<programmaNew.getAnno()) {
			interventoNew.setAnnoAvvio(programmaNew.getAnno());
		}
		//CPASS-514
		if(!StringUtils.isEmpty(interventoOld.getMotivazioneNonRiproposto())){
			interventoNew.setMotivazioneNonRiproposto(interventoOld.getMotivazioneNonRiproposto().trim());
			interventoNew.setAcquistoVariato(null);
		}
		interventoNew.setListInterventoImporti(new ArrayList<>());
		interventoNew.setInterventoCopia(interventoOld);
		interventoNew.setInterventoCopiaTipo(interventoCopiaTipo);
		interventoNew.setInterventoImportiCopiaTipo(interventoImportiCopiaTipo);
		if (interventoCopiaTipo.equals(CpassEnum.COPIA_MANTENENDO_CUI.getCostante()) || interventoCopiaTipo.equals(CpassEnum.ACQ_NON_RIPROPOSTO.getCostante())) {
			interventoNew.setFlagCuiNonGenerato(Boolean.TRUE);
		} else {
			/* Settore (F=forniture; S=servizi) + CF amministrazione + Anno Programma + progressivo di 5 cifre per annualità programma */
			final String settore = interventoNew.getSettoreInterventi().getCodice();
			final String cf = cpassTEnteDao.findOne(programmaNew.getEnte().getId()).map(CpassTEnte::getEnteCodiceFiscale).orElse("");
			final Integer annoProgramma = programmaNew.getAnno();
			final String codice = settore + cf + annoProgramma;
			final Integer intProgressivo = commonDad.getProgressivo(CpassEnum.INTERVENTO_CUI.getCostante(), codice);
			final String progressivo = StringUtils.leftPad(String.valueOf(intProgressivo), 5, '0');
			interventoNew.setCui(codice + progressivo);
			interventoNew.setFlagCuiNonGenerato(Boolean.FALSE);
		}
		final CpassTPbaIntervento cpassTPbaIntervento = CpassMappers.INTERVENTO.toEntity(interventoNew);
		final CpassTPbaIntervento cpassTPbaInterventoInserito = cpassTPbaInterventoDao.insert(cpassTPbaIntervento);
		cpassTPbaInterventoDao.flush();
		interventoNew.setId(cpassTPbaInterventoInserito.getId());
		interventoNew.setCui(cpassTPbaInterventoInserito.getInterventoCui());
		// salvataggio nello storico degli stati
		salvaStoricoStatoIntervento(utente, interventoNew,stato.getCodice());
		// salvataggio importi di quel dato intervento
		final List<InterventoImporti> listInterventoImporti = new ArrayList<>();
		for (final InterventoImporti interventoImporti : interventoOld.getListInterventoImporti()) {
			if (!StringUtils.isBlank(interventoImportiCopiaTipo) && interventoImportiCopiaTipo.equals(CpassEnum.IMPORTI_TRASLATI.getCostante())) {
				if (parametroDurataProgramma.equalsIgnoreCase("TRIENNALE")) {
					interventoImporti.setImportoAnnoPrimo(interventoImporti.getImportoAnnoSecondo());
					interventoImporti.setImportoAnnoSecondo(interventoImporti.getImportoAnnoTerzo());
					interventoImporti.setImportoAnnoTerzo(interventoImporti.getImportoAnniSuccessivi());
				}else {
					interventoImporti.setImportoAnnoPrimo(interventoImporti.getImportoAnnoSecondo());
					interventoImporti.setImportoAnnoSecondo(interventoImporti.getImportoAnniSuccessivi());

				}
				interventoImporti.setImportoAnniSuccessivi(BigDecimal.ZERO);
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
			final InterventoAltriDati interventoAltriDati = interventoOld.getListInterventoAltriDati().get(0);
			if (!StringUtils.isBlank(interventoImportiCopiaTipo) && interventoImportiCopiaTipo.equals(CpassEnum.IMPORTI_TRASLATI.getCostante())) {
				interventoAltriDati.setIvaPrimoAnno(interventoAltriDati.getIvaSecondoAnno());
				interventoAltriDati.setIvaSecondoAnno(interventoAltriDati.getIvaTerzoAnno());
				interventoAltriDati.setIvaTerzoAnno(interventoAltriDati.getIvaAnniSuccessivi());
				interventoAltriDati.setIvaAnniSuccessivi(BigDecimal.ZERO);
			}
			CpassTPbaInterventoAltriDati cpassTPbaInterventoAltriDati = CpassMappers.INTERVENTO_ALTRI_DATI.toEntity(interventoAltriDati);
			cpassTPbaInterventoAltriDati.setCpassTPbaIntervento(cpassTPbaIntervento);
			cpassTPbaInterventoAltriDati = cpassTPbaInterventoAltriDatiDao.insert(cpassTPbaInterventoAltriDati);
			final List<InterventoAltriDati> listInterventoAltriDati = new ArrayList<>();
			listInterventoAltriDati.add(CpassMappers.INTERVENTO_ALTRI_DATI.toModel(cpassTPbaInterventoAltriDati));
			interventoNew.setListInterventoAltriDati(listInterventoAltriDati);
		}
		// inserisco "lista cpv "
		for(final Cpv cpv : interventoOld.getListCpv()) {
			final CpassDCpv cpassTCpv = CpassMappers.CPV.toEntity(cpv);
			final CpassRInterventoCpv cpassRInterventoCpv = new CpassRInterventoCpv();
			cpassRInterventoCpv.setCpassTPbaIntervento(cpassTPbaIntervento);
			cpassRInterventoCpv.setCpassDCpv(cpassTCpv);
			cpassRInterventoCpvDao.insert(cpassRInterventoCpv);
		}
		return interventoNew;
	}
	/**
	 * Update the intervento
	 *
	 * @param interventoOld
	 * @param interventoCopiaTipo
	 * @param interventoImportoCopiaTipo
	 * @param programmaNew
	 * @return the model instance
	 */
	public void saveInterventoGiaAvviato(Intervento interventoOld,Utente utente) {
		interventoOld.setAvviato(Boolean.TRUE);
		interventoOld.setDataAvviato(new Date());
		interventoOld.setUtenteAvviato(utente);
		final CpassTPbaIntervento cpassTPbaIntervento = CpassMappers.INTERVENTO.toEntity(interventoOld);
		cpassTPbaInterventoDao.update(cpassTPbaIntervento);
		cpassTPbaInterventoDao.flush();
		// salvataggio nello storico degli stati
		salvaStoricoStatoIntervento(utente, interventoOld,StatoInterventiEnum.AVVIATO.getCostante());
		// inserimento cig
		for(final InterventoCig interventoCig : interventoOld.getInterventoCigs()) {
			final CpassTPbaInterventoCig cpassTPbaInterventoCig = CpassMappers.INTERVENTO_CIG.toEntity(interventoCig);
			cpassTPbaInterventoCig.setCpassTPbaIntervento(cpassTPbaIntervento);
			cpassTPbaInterventoCigDao.insert(cpassTPbaInterventoCig);

		}
		// inserisco "altri dati"*/
		if (interventoOld.getListInterventoAltriDati() != null && interventoOld.getListInterventoAltriDati().size() > 0) {
			final InterventoAltriDati interventoAltriDati = interventoOld.getListInterventoAltriDati().get(0);
			CpassTPbaInterventoAltriDati cpassTPbaInterventoAltriDati = CpassMappers.INTERVENTO_ALTRI_DATI.toEntity(interventoAltriDati);
			cpassTPbaInterventoAltriDati.setCpassTPbaIntervento(cpassTPbaIntervento);
			cpassTPbaInterventoAltriDati = cpassTPbaInterventoAltriDatiDao.save(cpassTPbaInterventoAltriDati);
		}
	}
	/**
	 * 
	 * @param interventi
	 * @param utente
	 * @return
	 */
	public List<Intervento> saveInterventoDaCaricamentoCsv(List<Intervento> interventi,Utente utente) {
		final List<Intervento> ris = new ArrayList<>();
		final String cf = cpassTEnteDao.findOne(interventi.get(0).getProgramma().getEnte().getId()).map(CpassTEnte::getEnteCodiceFiscale).orElse("");
		for (final Intervento intervento : interventi) {
			ris.add(saveInterventoDaCaricamentoCsv(intervento,utente,cf));
		}
		return ris;
		// La morte sua :)
		// return interventi.stream().map(this::saveIntervento).collect(Collectors.toList());
	}
	/**
	 * Updates the intervento
	 * @param intervento the intervento
	 * @return the model instance
	 */
	public Intervento updateIntervento(Intervento intervento) {
		CpassTPbaIntervento cpassTPbaIntervento = CpassMappers.INTERVENTO.toEntity(intervento);
		cpassTPbaIntervento = cpassTPbaInterventoDao.update(cpassTPbaIntervento);
		cpassTPbaInterventoDao.flush();
		for (final InterventoImporti interventoImporti : intervento.getListInterventoImporti()) {
			final CpassTPbaInterventoImporti cpassTPbaInterventoImporti = CpassMappers.INTERVENTO_IMPORTI.toEntity(interventoImporti);
			cpassTPbaInterventoImporti.setCpassTPbaIntervento(cpassTPbaIntervento);
			if(cpassTPbaInterventoImporti.getDataCreazione() == null) {
				cpassTPbaInterventoImporti.setDataCreazione(cpassTPbaIntervento.getDataCreazione());
			}
			if( StringUtility.isEmpty(cpassTPbaInterventoImporti.getUtenteCreazione())) {
				cpassTPbaInterventoImporti.setUtenteCreazione(cpassTPbaIntervento.getUtenteCreazione());
			}
			if (cpassTPbaInterventoImporti.getRichiestaMotivazione() == null) {
				cpassTPbaInterventoImporti.setRichiestaMotivazione(Boolean.FALSE);
			}
			cpassTPbaInterventoImportiDao.save(cpassTPbaInterventoImporti);
		}
		// cancello vecchi "altri dati"
		final List<CpassTPbaInterventoAltriDati> cpassTPbaInterventoAltriDatis = cpassTPbaInterventoAltriDatiDao.getByInterventoId(intervento.getId());
		if (cpassTPbaInterventoAltriDatis != null && cpassTPbaInterventoAltriDatis.size() > 0) {
			for (final CpassTPbaInterventoAltriDati cpassTPbaInterventoAltriDati : cpassTPbaInterventoAltriDatis) {
				cpassTPbaInterventoAltriDatiDao.delete(cpassTPbaInterventoAltriDati.getId());
			}
		}
		cpassTPbaInterventoAltriDatiDao.flushAndClear();
		// inserisco "altri dati"
		if (intervento.getListInterventoAltriDati() != null && intervento.getListInterventoAltriDati().size() > 0) {
			final InterventoAltriDati interventoAltriDati = intervento.getListInterventoAltriDati().get(0);
			if (interventoAltriDati.getCpvMatRic()!= null && interventoAltriDati.getCpvMatRic().getId()==null) {
				interventoAltriDati.setCpvMatRic(null);
			}
			if (interventoAltriDati.getCpvVerdi()!= null && interventoAltriDati.getCpvVerdi().getId()==null) {
				interventoAltriDati.setCpvVerdi(null);
			}
			final CpassTPbaInterventoAltriDati cpassTPbaInterventoAltriDati = CpassMappers.INTERVENTO_ALTRI_DATI.toEntity(interventoAltriDati);
			cpassTPbaInterventoAltriDati.setCpassTPbaIntervento(cpassTPbaIntervento);
			cpassTPbaInterventoAltriDatiDao.insert(cpassTPbaInterventoAltriDati);
		}
		/////////////////////////////lista CPV///////////////
		final List<CpassRInterventoCpv> lista = cpassRInterventoCpvDao.getCpassRInterventoCpvByIntervento(cpassTPbaIntervento.getId());
		if (cpassTPbaInterventoAltriDatis != null ) {
			for (final CpassRInterventoCpv cpassRInterventoCpv : lista) {
				cpassRInterventoCpvDao.delete(cpassRInterventoCpv.getId());
			}
		}
		cpassRInterventoCpvDao.flushAndClear();
		for(final Cpv cpv : intervento.getListCpv()) {
			final CpassDCpv cpassTCpv = CpassMappers.CPV.toEntity(cpv);
			final CpassRInterventoCpv cpassRInterventoCpv = new CpassRInterventoCpv();
			cpassRInterventoCpv.setCpassTPbaIntervento(cpassTPbaIntervento);
			cpassRInterventoCpv.setCpassDCpv(cpassTCpv);
			cpassRInterventoCpvDao.update(cpassRInterventoCpv);
		}
		return intervento;
	}
	/**
	 * 
	 * @param intervento
	 * @return
	 */
	public Intervento updateInterventoMinimal(Intervento intervento) {
		CpassTPbaIntervento cpassTPbaIntervento = CpassMappers.INTERVENTO.toEntity(intervento);
		cpassTPbaIntervento = cpassTPbaInterventoDao.update(cpassTPbaIntervento);
		return intervento;
	}
	/**
	 * Finds an intervento by its CUI
	 *
	 * @param cui         the CUI
	 * @param idProgramma the id programma
	 * @return the model instance
	 */
	public Optional<Intervento> findInterventoByCUI(String cui, UUID idProgramma) {
		final Settore settoreCorrente = CpassThreadLocalContainer.SETTORE_UTENTE.get();
		final UUID enteId = getId(settoreCorrente.getEnte());
		final Optional<CpassTPbaIntervento> optionalIntervento = cpassTPbaInterventoDao.findByCUI(cui, idProgramma, enteId);
		if (optionalIntervento.isEmpty()) {
			return optionalIntervento.map(CpassMappers.INTERVENTO::toModel);
		} else {
			final CpassTPbaIntervento cpassTPbaIntervento = optionalIntervento.orElseThrow(() -> new NotFoundException("int"));
			final Intervento intervento = CpassMappers.INTERVENTO.toModel(cpassTPbaIntervento);
			intervento.setVersioneDefinitivaStr(intervento.getVersioneDefinitiva() != null && intervento.getVersioneDefinitiva() ? "true" : "false");
			for (final CpassRInterventoCpv cpassRInterventoCpv : cpassTPbaIntervento.getCpassRInterventoCpvs()) {
				intervento.getListCpv().add(CpassMappers.CPV.toModel(cpassRInterventoCpv.getCpassDCpv()));
			}
			setInterventoPresenteInAltroProgramma(intervento);
			final Optional<Intervento> optional = Optional.of(intervento);
			return optional;
		}
	}

	/**
	 * @param intervento
	 */
	private void setInterventoPresenteInAltroProgramma(Intervento intervento) {
		final Settore settoreCorrente = CpassThreadLocalContainer.SETTORE_UTENTE.get();
		final UUID enteId = getId(settoreCorrente.getEnte());
		final List<Intervento> listTntervento = findInterventiByCUI(intervento.getCui(), enteId);
		intervento.setPresenteInAltroProgramma(listTntervento.size()>1);

	}
	/**
	 * 
	 * @param cui
	 * @param annoProgramma
	 * @param versioneProgramma
	 * @param enteId
	 * @return
	 */
	public List<Intervento> findInterventiOldByCUI(String cui, Integer annoProgramma, Integer versioneProgramma ,UUID enteId) {
		final List<CpassTPbaIntervento> lista = cpassTPbaInterventoDao.findInterventiOldByCUI(cui, annoProgramma,  versioneProgramma ,   enteId);
		return CpassMappers.INTERVENTO.toModels(lista);
	}
	/**
	 * 
	 * @param cui
	 * @param enteId
	 * @return
	 */
	public List<Intervento> findInterventiByCUI(String cui, UUID enteId) {
		final List<CpassTPbaIntervento> lista = cpassTPbaInterventoDao.findByCUI(cui,  enteId);
		return CpassMappers.INTERVENTO.toModels(lista);
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
	 * @param id          the id
	 * @param statoCodice the stato codice
	 * @param statoTipo   the stato tipo
	 */
	public void updateStatoIntervento(Intervento intervento, String statoCodice, String statoTipo,Utente utente) {
		final CpassTPbaIntervento cpassTPbaIntervento = cpassTPbaInterventoDao.findOne(intervento.getId()).orElseThrow(() -> new NotFoundException("intervento"));
		final CpassDStato cpassDStato = cpassDStatoDao.findByCodiceTipo(statoCodice, statoTipo).orElseThrow(() -> new NotFoundException("stato"));
		cpassTPbaIntervento.setCpassDStato(cpassDStato);
		cpassTPbaInterventoDao.update(cpassTPbaIntervento);
		salvaStoricoStatoIntervento(utente, intervento,intervento.getStatoXStorico());
	}
	/**
	 * 
	 * @param intervento
	 * @param utente
	 */
	public void updateStatoIntervento(Intervento intervento,Utente utente) {
		updateStatoInterventoRifiuto( intervento,utente ,Boolean.FALSE);
	}
	/**
	 * 
	 * @param intervento
	 * @param utente
	 * @param isRifiuto
	 */
	public void updateStatoInterventoRifiuto(Intervento intervento,Utente utente,Boolean isRifiuto) {
		CpassTPbaIntervento cpassTPbaIntervento = CpassMappers.INTERVENTO.toEntity(intervento);
		cpassTPbaIntervento = cpassTPbaInterventoDao.update(cpassTPbaIntervento);
		salvaStoricoStatoIntervento(utente, intervento,intervento.getStatoXStorico(), isRifiuto);
	}
	/**
	 *
	 * @param idProgramma
	 * @param statoCode
	 * @return the interventi by programma and stato
	 */
	public List<Intervento> getInterventoByProgrammaStato(UUID idProgramma, String statoCode) {
		return getInterventoByProgrammaStato(idProgramma, statoCode, Boolean.TRUE);
	}
	/**
	 * 
	 * @param idProgramma
	 * @param statoCode
	 * @return
	 */
	public List<UUID> getInterventoIdByProgrammaStato(UUID idProgramma, String statoCode) {
		return getInterventoIdByProgrammaStato(idProgramma, statoCode, Boolean.TRUE);
	}
	/**
	 *
	 * @param idProgramma
	 * @param statoCode
	 * @param operatoreEqualsStato
	 * @return the interventi by programma and stato
	 */
	public List<Intervento> getInterventoByProgrammaStato(UUID idProgramma, String statoCode, boolean operatoreEqualsStato) {
		final List<CpassTPbaIntervento> entities = cpassTPbaInterventoDao.getInterventoByProgrammaStato(idProgramma, statoCode, operatoreEqualsStato);
		return CpassMappers.INTERVENTO.toModels(entities);
	}
	/**
	 * 
	 * @param idProgramma
	 * @param statoCode
	 * @param operatoreEqualsStato
	 * @return
	 */
	public List<UUID> getInterventoIdByProgrammaStato(UUID idProgramma, String statoCode, boolean operatoreEqualsStato) {
		final List<UUID> entitiesId = cpassTPbaInterventoDao.getInterventoIdByProgrammaStato(idProgramma, statoCode, operatoreEqualsStato);
		return entitiesId;
	}
	/**
	 * 
	 * @param idProgramma
	 * @return
	 */
	public List<Intervento> getInterventiBloccantiPerConfermaProgramma(UUID idProgramma) {
		final List<CpassTPbaIntervento> entities = cpassTPbaInterventoDao.getInterventiBloccantiPerConfermaProgramma(idProgramma);
		return CpassMappers.INTERVENTO_MINIMAL.toModels(entities);
	}
	/**
	 * 
	 * @param idProgramma
	 * @return
	 */
	public List<UUID> getInterventiBloccantiIdPerConfermaProgramma(UUID idProgramma) {
		return  cpassTPbaInterventoDao.getInterventiBloccantiIdPerConfermaProgramma(idProgramma);
	}
	/**
	 * @param page
	 * @param size
	 * @param sort
	 * @param programmaIdOld
	 * @param programmaIdNew
	 * @return the interventi per copia
	 */
	public PagedList<Intervento> getRicercaInterventiXCopia(int page, int size, Sort sort, UUID programmaIdOld, UUID programmaIdNew, UUID utenteRupId) {
		final Utente utenteConnesso = CpassThreadLocalContainer.UTENTE_CONNESSO.get();
		final Settore settoreCorrente = CpassThreadLocalContainer.SETTORE_UTENTE.get();
		final Page<CpassTPbaIntervento> cpassTPbaInterventos = cpassTPbaInterventoDao.findPaginatedXCopia(  programmaIdOld
				, programmaIdNew
				, utenteRupId
				, page
				, size
				, InterventoSort.byModelName(sort.getField()).getQueryName()
				, sort.getOrder().getSortDirection()
				, getId(utenteConnesso)
				, getId(settoreCorrente)
				);
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
	/**
	 * 
	 * @param idIntervento
	 * @return
	 */
	public List<Cpv> getCpvsByInterventoId(UUID idIntervento) {
		final List<CpassDCpv> entities = cpassDCpvDao.getCpvsByInterventoId(idIntervento);
		return CpassMappers.CPV.toModels(entities);
	}
	/**
	 * 
	 * @param idIntervento
	 * @return
	 */
	public List<StatiIntervento> getStatiInterventoByInterventoId(UUID idIntervento) {
		final List<CpassRPbaStatiIntervento> entities = cpassRPbaStatiInterventoDao.getStatiInterventoByInterventoId(idIntervento);
		return CpassMappers.STATI_INTERVENTO.toModels(entities);
	}
	/**
	 * 
	 * @param idIntervento
	 * @return
	 */
	public StatiIntervento getStatoPrecedenteInterventoByInterventoId(UUID idIntervento) {
		final CpassRPbaStatiIntervento stato = cpassRPbaStatiInterventoDao.getStatoPrecedenteInterventoByInterventoId(idIntervento);
		return CpassMappers.STATI_INTERVENTO.toModel(stato);
	}
	/**
	 * 
	 * @param idIntervento
	 * @return
	 */
	public List<StatoInterventoInfo> getUltimoStatoInfoByIntervento(UUID idIntervento) {
		return cpassVStatoInterventoInfoDao.getUltimoStatoInfoByIntervento(idIntervento);
	}
	/**
	 * 
	 * @param idIntervento
	 * @return
	 */
	public List<StoricoInterventoRup> getStoricoRupsByInterventoIdService(UUID idIntervento) {
		final List<CpassRPbaStoricoInterventoRup> entities = cpassRPbaStoricoInterventoRupDao.getStoricoRupsByInterventoId(idIntervento);
		return CpassMappers.INTERVENTO_STORICO_RUP.toModels(entities);
	}
	/**
	 * 	
	 * @param intervento
	 * @param utenteRupVecchio
	 * @return
	 */
	public CpassRPbaStoricoInterventoRup archivioRup(Intervento intervento, Utente utenteRupVecchio) {
		// Archivio il vecchio Rup nello storico
		final CpassRPbaStoricoInterventoRup isr = new CpassRPbaStoricoInterventoRup();
		final Optional<CpassTPbaIntervento> cpassTPbaInterventoOptional = cpassTPbaInterventoDao.findOne(intervento.getId());
		if (cpassTPbaInterventoOptional.isPresent()) {
			final CpassTPbaIntervento cpassTPbaIntervento = cpassTPbaInterventoOptional.get();
			isr.setCpassTPbaIntervento(cpassTPbaIntervento);
		} else {
			throw new NotFoundException("intervento");
		}
		final CpassTUtente cpassTUtenteRup = CpassMappers.UTENTE.toEntity(utenteRupVecchio);
		isr.setCpassTUtenteRup(cpassTUtenteRup);
		isr.setDataStoricizzazione(new Date());
		final Utente utenteConnesso = CpassThreadLocalContainer.UTENTE_CONNESSO.get();

		final Optional<CpassTUtente> utenteConnessoOptional = cpassTUtenteDao.findOne(utenteConnesso.getId());
		if (utenteConnessoOptional.isPresent()) {
			final CpassTUtente cpassTUtenteConnesso = utenteConnessoOptional.get();
			isr.setCpassTUtente(cpassTUtenteConnesso);
		} else {
			throw new NotFoundException("utente");
		}
		final CpassRPbaStoricoInterventoRup ris = cpassRPbaStoricoInterventoRupDao.insert(isr);
		cpassRPbaStoricoInterventoRupDao.flushAndClear();
		return ris;
	}
	/**
	 * 
	 * @param annoPrec
	 * @param programmaVersioneMaxPrecedente
	 * @return
	 */
	public List<Intervento> getInterventiNonRiproponibiliByUltimoProgrammaPrecedente(Integer annoPrec,Integer programmaVersioneMaxPrecedente) {
		final List<CpassTPbaIntervento> entities = cpassTPbaInterventoDao.getInterventiNonRiproponibiliByUltimoProgrammaPrecedente( annoPrec, programmaVersioneMaxPrecedente);
		return CpassMappers.INTERVENTO.toModels(entities);
	}
	/**
	 * 
	 * @param utenteConnesso
	 * @param programma
	 * @param lista
	 * @return
	 */
	public List<Intervento> salvaSuNuovoProgrammaInterventiNonRiproposti(Utente utenteConnesso,Programma programma, List<Intervento> lista) {
		final List<CpassTPbaIntervento> listaNew = new ArrayList<>();
		final CpassTParametro motNonRipDefault = cpassTParametroDao.getParametro(ConstantsCPassParametro.ChiaveEnum.MOTIVAZIONE_NON_RIPROPOSTO_DEFAULT.getCostante(),ConstantsCPassParametro.RiferimentoEnum.NULL.getCostante(), programma.getEnte().getId());
		final CpassDStato statoValidatoInt = cpassDStatoDao.findByCodiceTipo(StatoInterventiEnum.VALIDATO.getCostante(), CpassEnum.INTERVENTO.getCostante()).orElseThrow(() -> new NotFoundException("stato"));
		final CpassDStato statoCancellatoInt = cpassDStatoDao.findByCodiceTipo(StatoInterventiEnum.CANCELLATO.getCostante(), CpassEnum.INTERVENTO.getCostante()).orElseThrow(() -> new NotFoundException("stato"));
		final CpassTPbaProgramma cpassTPbaProgramma = CpassMappers.PROGRAMMA.toEntity(programma);
		for(final Intervento model : lista) {
			final CpassTPbaIntervento entity = CpassMappers.INTERVENTO.toEntity(model);
			final List<CpassTPbaInterventoImporti> cpassTPbaInterventoImportis = CpassMappers.INTERVENTO_IMPORTI.toEntities(model.getListInterventoImporti());
			final List<CpassRInterventoCpv>        listaCpassRInterventoCpv    = cpassRInterventoCpvDao.getCpassRInterventoCpvByIntervento(entity.getId());
			final CpassTPbaIntervento cpassTPbaInterventoNew = CpassMappers.INTERVENTO.cloneToEntity(entity);
			cpassTPbaInterventoNew.setId(null);
			cpassTPbaInterventoNew.setCpassTPbaProgramma(cpassTPbaProgramma);
			cpassTPbaInterventoNew.setFlagCuiNonGenerato(Boolean.TRUE);
			cpassTPbaInterventoNew.setCpassTPbaInterventoCopia(entity);
			//Stesso valore dell’Acquisto di origine (dal quale si sta copiando)
			//Se sull’acquisto di origine, il campo è nullo, allora inserire il valore del parametro “Motivazione_non_riproposto_default”
			cpassTPbaInterventoNew.setMotivazioneNonRiproposto(motNonRipDefault.getValore());
			if(model.getMotivazioneNonRiproposto()!=null && !model.getMotivazioneNonRiproposto().trim().equals("")) {
				cpassTPbaInterventoNew.setMotivazioneNonRiproposto(model.getMotivazioneNonRiproposto());
			}
			//CPASS-514
			cpassTPbaInterventoNew.setCpassDPbaAcquistoVariato(null);
			cpassTPbaInterventoNew.setFlagCuiNonGenerato(Boolean.TRUE);
			cpassTPbaInterventoNew.setInterventoCopiaTipo(CpassEnum.ACQ_NON_RIPROPOSTO.getCostante());
			cpassTPbaInterventoNew.setInterventoImportiCopiaTipo(CpassEnum.IMP_NON_TRASLATI.getCostante());
			//Se CPASS_T_PBA_INTERVENTO.anno_avvio =  1 Prima annualità, occorre riportare lo stato “CANCELLATO”
			//Altrimenti, occorre riportare lo stato VALIDATO
			if(cpassTPbaInterventoNew.getInterventoAnnoAvvio().equals(programma.getAnno())) {
				cpassTPbaInterventoNew.setCpassDStato(statoCancellatoInt);
			}else {
				cpassTPbaInterventoNew.setCpassDStato(statoValidatoInt);
			}

			final CpassTPbaIntervento cpassTPbaInterventoInserito = cpassTPbaInterventoDao.insert(cpassTPbaInterventoNew);
			cpassTPbaInterventoDao.flush();
			//salvataggio dello storico
			if(cpassTPbaInterventoNew.getInterventoAnnoAvvio().equals(programma.getAnno())) {
				salvaStoricoStatoIntervento( utenteConnesso, cpassTPbaInterventoInserito,StatoInterventiEnum.CANCELLATO.getCostante());
			}else {
				salvaStoricoStatoIntervento( utenteConnesso, cpassTPbaInterventoInserito,StatoInterventiEnum.VALIDATO.getCostante());
			}
			// gestione lista importi
			final List<CpassTPbaInterventoImporti> listcpassTPbaInterventoImporti = new ArrayList<>();

			for ( CpassTPbaInterventoImporti cpassTPbaInterventoImporti : cpassTPbaInterventoImportis) {
				cpassTPbaInterventoImporti.setId(null);
				cpassTPbaInterventoImporti.setCpassTPbaIntervento(cpassTPbaInterventoInserito);
				cpassTPbaInterventoImporti = cpassTPbaInterventoImportiDao.insert(cpassTPbaInterventoImporti);
				listcpassTPbaInterventoImporti.add(cpassTPbaInterventoImporti);
			}
			cpassTPbaInterventoNew.setCpassTPbaInterventoImportis(listcpassTPbaInterventoImporti);
			// gestione lista altri dati
			final List<CpassTPbaInterventoAltriDati> listcpassTPbaInterventoAltriDati = new ArrayList<>();
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
				for(final CpassRInterventoCpv el : listaCpassRInterventoCpv) {
					//CpassDCpv cpassTCpv = CpassMappers.CPV.toEntity(cpv);
					final CpassRInterventoCpv cpassRInterventoCpv = new CpassRInterventoCpv();
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

	/**
	 * Finds an intervento by its CUI
	 * @param cui         the CUI
	 * @param idProgramma the id programma
	 * @return the model instance
	 */
	public Integer getMaxProgressivoCUIByChiave(String chiaveCui) {
		return cpassTPbaInterventoDao.getMaxProgressivoCUIByChiave(chiaveCui);
	}

	/**
	 * Flush of the entity manager
	 */
	public void flush() {
		cpassTProgressivoDao.flush();
	}

	/**
	 * Flush And Clear of the entity manager
	 */
	public void flushAndClear() {
		cpassTProgressivoDao.flushAndClear();
	}
	/**
	 * 
	 * @param programmaId
	 * @param myIdCapofila
	 * @param idSettore
	 * @return
	 */
	public List<Intervento> getInterventiCapofila(UUID programmaId,UUID myIdCapofila, UUID idSettore) {
		final List<CpassTPbaIntervento> entities = cpassTPbaInterventoDao.getInterventiCapofila(programmaId,myIdCapofila,idSettore);
		return CpassMappers.INTERVENTO.toModels(entities);
	}
	/**
	 * 
	 * @param idCapofila
	 * @param programmaId
	 * @return
	 */
	public List<Intervento> getInterventiByCapofilaId(UUID idCapofila,UUID programmaId) {
		final List<CpassTPbaIntervento> entities = cpassTPbaInterventoDao.getInterventiByCapofilaId(idCapofila,programmaId);
		return CpassMappers.INTERVENTO.toModels(entities);
	}

	public void gestisciCapofila(Intervento interventoNew, Programma programmaNew,UUID enteId) {
		// metodo che prende dall'anno precedente il cui recupera nel programma l'id del cui ribaltato e lo setta nel capofila se capofila e true capofila_id = id
		if(interventoNew.getCapofila()) {
			final Intervento interventoCapofila = new Intervento();
			interventoCapofila.setId(interventoNew.getId());
			interventoNew.setInterventoCapofila(interventoCapofila );
			cpassTPbaInterventoDao.update(CpassMappers.INTERVENTO.toEntity(interventoNew),false);
		}else {
			if(interventoNew.getInterventoCapofila() != null) {
				final String cuiCapofila = cpassTPbaInterventoDao.findById(interventoNew.getInterventoCapofila().getId()).orElseThrow(() -> new NotFoundException("int capofila")).getInterventoCui();
				final Optional<CpassTPbaIntervento> interventoCapofilaNuovo = cpassTPbaInterventoDao.findByCUI(cuiCapofila, programmaNew.getId(), enteId);
				final CpassTPbaIntervento entity = CpassMappers.INTERVENTO.toEntity(interventoNew);
				if(interventoCapofilaNuovo.isPresent()) {
					entity.setCpassTPbaInterventoCapofila(interventoCapofilaNuovo.get());
				}else {
					entity.setCpassTPbaInterventoCapofila(null);
				}
				cpassTPbaInterventoDao.update(entity,false);
			}
		}
	}
	/**
	 * 
	 * @param listaId
	 * @return
	 */
	public List<Intervento> getInterventiByListaId(List<UUID> listaId) {
		final List<CpassTPbaIntervento> entities = cpassTPbaInterventoDao.getInterventiByListaId(listaId);
		return CpassMappers.INTERVENTO.toModels(entities);
	}
	/**
	 * 
	 * @param intervento
	 * @param utente
	 * @param cf
	 * @return Intervento
	 */
	public Intervento saveInterventoDaCaricamentoCsv(Intervento intervento,Utente utente, String cf ) {
		CpassTPbaIntervento cpassTPbaIntervento = CpassMappers.INTERVENTO.toEntity(intervento);
		log.info("saveIntervento ", intervento.getCui());
		cpassTPbaIntervento = cpassTPbaInterventoDao.insert(cpassTPbaIntervento);
		intervento.setId(cpassTPbaIntervento.getId());
		intervento.setCui(cpassTPbaIntervento.getInterventoCui());
		cpassTPbaInterventoDao.flush();
		//inserisco lo stato nella r_stati
		salvaStoricoStatoInterventoXCsv(utente, intervento,intervento.getStato().getCodice());//intervento.getStatoXStrorico());
		// inserisco "altri dati"
		if (intervento.getListInterventoAltriDati() != null && intervento.getListInterventoAltriDati().size() > 0) {
			final InterventoAltriDati interventoAltriDati = intervento.getListInterventoAltriDati().get(0);
			final CpassTPbaInterventoAltriDati cpassTPbaInterventoAltriDati = CpassMappers.INTERVENTO_ALTRI_DATI.toEntity(interventoAltriDati);
			cpassTPbaInterventoAltriDati.setCpassTPbaIntervento(cpassTPbaIntervento);
			cpassTPbaInterventoAltriDatiDao.insert(cpassTPbaInterventoAltriDati);
		}
		// inserisco "lista cpv "
		for(final Cpv cpv : intervento.getListCpv()) {
			final CpassDCpv cpassTCpv = CpassMappers.CPV.toEntity(cpv);
			final CpassRInterventoCpv cpassRInterventoCpv = new CpassRInterventoCpv();
			cpassRInterventoCpv.setCpassTPbaIntervento(cpassTPbaIntervento);
			cpassRInterventoCpv.setCpassDCpv(cpassTCpv);
			cpassRInterventoCpvDao.insert(cpassRInterventoCpv);
		}
		return intervento;
	}
	/**
	 *
	 * @param idIntervento
	 * @return
	 */
	public List<InterventoCig> getCigByInterventoId(UUID idIntervento) {
		final List<CpassTPbaInterventoCig> entities = cpassTPbaInterventoCigDao.getCigByInterventoId(idIntervento);
		return CpassMappers.INTERVENTO_CIG.toModels(entities);
	}
	/**
	 *
	 * @param interventoId
	 * @param cigs
	 */
	public void postCigByInterventoId(UUID interventoId,List<String> cigs) {
		final CpassTPbaIntervento cpassTPbaIntervento = new CpassTPbaIntervento();
		cpassTPbaIntervento.setInterventoId(interventoId);
		for(final String cig : cigs) {
			final CpassTPbaInterventoCig entity = new CpassTPbaInterventoCig();
			entity.setCpassTPbaIntervento(cpassTPbaIntervento);
			entity.setCig(cig);
			cpassTPbaInterventoCigDao.insert(entity);
		}
	}
	/**
	 * 
	 * @param interventoId
	 */
	public void deleteCigByInterventoId(UUID interventoId) {
		cpassTPbaInterventoCigDao.deleteByIdIntervento(interventoId);
		cpassRPbaStatiInterventoDao.deleteByIdIntervento(interventoId);
		cpassTPbaInterventoAltriDatiDao.deleteByIdIntervento(interventoId);
	}
	/**
	 * 
	 * @param interventoId
	 * @param cig
	 */
	public void deleteByInterventoIdAndCig(UUID interventoId, String cig) {
		cpassTPbaInterventoCigDao.deleteByInterventoIdAndCig(interventoId, cig);
	}
	/**
	 * 
	 * @param interventoId
	 */
	public void deleteCigByInterventoIdMinimal(UUID interventoId) {
		cpassTPbaInterventoCigDao.deleteByIdIntervento(interventoId);
	}
	/**
	 * 
	 * @param interventoAltriDati
	 */
	public void saveAltriDati(InterventoAltriDati interventoAltriDati) {
		final CpassTPbaInterventoAltriDati cpassTPbaInterventoAltriDati = CpassMappers.INTERVENTO_ALTRI_DATI.toEntity(interventoAltriDati);
		cpassTPbaInterventoAltriDatiDao.save(cpassTPbaInterventoAltriDati);
	}
	/**
	 * 
	 * @param id
	 */
	public void deleteInterventoPhysically(UUID id) {
		cpassRPbaStoricoInterventoRupDao.deleteByIdIntervento(id);
		cpassTPbaInterventoImportiDao.deleteByIdIntervento(id);
		cpassTPbaInterventoAltriDatiDao.deleteByIdIntervento(id);
		cpassTPbaInterventoCigDao.deleteByIdIntervento(id);
		cpassRInterventoCpvDao.deleteByIdIntervento(id);
		cpassRPbaStatiInterventoDao.deleteByIdIntervento(id);
		cpassTPbaInterventoDao.deleteByIdIntervento(id);
	}
	/**
	 * 
	 * @param idProgramma
	 * @return
	 */
	public List<AcquistiDaTrasmettere> getAcquistiDaTrasmettereByProgrammaId(UUID idProgramma) {
		final List<CpassTPbaAcquistiDaTrasmettere> entities = cpassTPbaAcquistiDaTrasmettereDao.acquistiDaTrasmettereByProgrammaId(idProgramma);
		return CpassMappers.Acquisti_Da_Trasmettere.toModels(entities);
	}
	/**
	 * 
	 * @param interventoId
	 * @return
	 */
	public List<AcquistiCapPrivatiDaTrasmettere> getAcquistiDaTrasmettereCapPrivatoByIntgerventoId(UUID interventoId) {
		final List<CpassTPbaAcquistiCapPrivatiDaTrasmettere> entities = cpassTPbaAcquistiCapPrivatiDaTrasmettereDao.getAcquistiDaTrasmettereCapPrivatoByIntgerventoId(interventoId);
		return CpassMappers.Acquisti_Cap_Privati_Da_Trasmettere.toModels(entities);
	}
	/**
	 * 
	 * @param programmaId
	 */
	public void deleteAcquistiDaTrasmettereByProgrammaId(UUID programmaId) {
		cpassTPbaAcquistiDaTrasmettereDao.deleteByProgrammaId(programmaId);
	}
	/**
	 * 
	 * @param programmaId
	 */
	public void deleteAcquistiCapPrivatiDaTrasmettereByProgrammaId(UUID programmaId) {
		cpassTPbaAcquistiCapPrivatiDaTrasmettereDao.deleteByProgrammaId(programmaId);
	}
	/**
	 * 
	 * @param acquistiDaTrasmettere
	 */
	public void postAcquistiDaTrasmettere(AcquistiDaTrasmettere acquistiDaTrasmettere) {
		final CpassTPbaAcquistiDaTrasmettere cpassTPbaAcquistiDaTrasmettere = CpassMappers.Acquisti_Da_Trasmettere.toEntity(acquistiDaTrasmettere);
		cpassTPbaAcquistiDaTrasmettereDao.insert(cpassTPbaAcquistiDaTrasmettere );
	}
	/**
	 * 
	 * @param acquistiCapPrivatiDaTrasmettere
	 */
	public void postAcquistiCapPrivatiDaTrasmettere(AcquistiCapPrivatiDaTrasmettere acquistiCapPrivatiDaTrasmettere) {
		log.debug("postAcquistiCapPrivatiDaTrasmettere", "START");
		final CpassTPbaAcquistiCapPrivatiDaTrasmettere cpassTPbaAcquistiCapPrivatiDaTrasmettere = CpassMappers.Acquisti_Cap_Privati_Da_Trasmettere.toEntity(acquistiCapPrivatiDaTrasmettere);
		cpassTPbaAcquistiCapPrivatiDaTrasmettereDao.insert(cpassTPbaAcquistiCapPrivatiDaTrasmettere );
		log.debug("postAcquistiCapPrivatiDaTrasmettere", "STOP");
	}
	/**
	 * 
	 * @param programmaId
	 * @return
	 */
	public List<Intervento> getRicercaByProgrammaId(UUID programmaId) {
		final List<CpassTPbaIntervento> cpassTPbaInterventos = cpassTPbaInterventoDao.getRicercaByProgrammaId(programmaId);
		log.info("getRicercaByProgrammaId", cpassTPbaInterventos.size());
		final List<Intervento> ris = CpassMappers.INTERVENTO_MINIMAL.toModels(cpassTPbaInterventos);
		return ris;
	}
	/**
	 * 
	 * @param programmaId
	 * @return
	 */
	public List<VIntervento> getRicercaNativaByProgrammaId(UUID programmaId) {
		final List<VIntervento> listaIntervento = cpassTPbaInterventoDao.getRicercaNativaByProgrammaId(programmaId);
		return listaIntervento;
	}
}
