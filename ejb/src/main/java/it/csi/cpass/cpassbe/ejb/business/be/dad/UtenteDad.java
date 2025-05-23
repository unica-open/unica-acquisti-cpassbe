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
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import it.csi.cpass.cpassbe.ejb.business.be.dad.sort.ModuloRuoloPermessoSort;
import it.csi.cpass.cpassbe.ejb.business.be.dad.sort.RuoloPermessoSort;
import it.csi.cpass.cpassbe.ejb.business.be.dad.sort.UtenteSort;
import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.CpassRDirigenteSettoreDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.CpassRModuloRuoloPermessoDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.CpassROrdUtenteSezioneDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.CpassRRuoloPermessoDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.CpassRRuoloUtenteSettoreDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.CpassRUtenteRupSettoreDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.CpassRUtenteSettoreDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.CpassTSettoreDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.CpassTUtenteDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.CpassTUtenteRupDelegheDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.decodifiche.CpassDModuloDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.decodifiche.CpassDPermessoDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.decodifiche.CpassDRuoloDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.ord.CpassTOrdSezioneDao;
import it.csi.cpass.cpassbe.ejb.entity.CpassDModulo;
import it.csi.cpass.cpassbe.ejb.entity.CpassDPermesso;
import it.csi.cpass.cpassbe.ejb.entity.CpassDRuolo;
import it.csi.cpass.cpassbe.ejb.entity.CpassRDirigenteSettore;
import it.csi.cpass.cpassbe.ejb.entity.CpassRModuloRuoloPermesso;
import it.csi.cpass.cpassbe.ejb.entity.CpassRRuoloPermesso;
import it.csi.cpass.cpassbe.ejb.entity.CpassRRuoloUtenteSettore;
import it.csi.cpass.cpassbe.ejb.entity.CpassRUtenteRupSettore;
import it.csi.cpass.cpassbe.ejb.entity.CpassRUtenteSettore;
import it.csi.cpass.cpassbe.ejb.entity.CpassTSettore;
import it.csi.cpass.cpassbe.ejb.entity.CpassTUtente;
import it.csi.cpass.cpassbe.ejb.entity.CpassTUtenteRupDeleghe;
import it.csi.cpass.cpassbe.ejb.entity.ord.CpassROrdUtenteSezione;
import it.csi.cpass.cpassbe.ejb.entity.ord.CpassTOrdSezione;
import it.csi.cpass.cpassbe.ejb.exception.NotFoundException;
import it.csi.cpass.cpassbe.ejb.mapper.CpassMappers;
import it.csi.cpass.cpassbe.ejb.util.CpassRuoloEnum;
import it.csi.cpass.cpassbe.ejb.util.StringUtility;
import it.csi.cpass.cpassbe.ejb.util.jpa.Page;
import it.csi.cpass.cpassbe.lib.dto.ApiError;
import it.csi.cpass.cpassbe.lib.dto.Modulo;
import it.csi.cpass.cpassbe.lib.dto.ModuloRuoloPermesso;
import it.csi.cpass.cpassbe.lib.dto.Permesso;
import it.csi.cpass.cpassbe.lib.dto.Ruolo;
import it.csi.cpass.cpassbe.lib.dto.RuoloPermesso;
import it.csi.cpass.cpassbe.lib.dto.RuoloUtenteSettore;
import it.csi.cpass.cpassbe.lib.dto.Settore;
import it.csi.cpass.cpassbe.lib.dto.SettoreRuoliPermessi;
import it.csi.cpass.cpassbe.lib.dto.Utente;
import it.csi.cpass.cpassbe.lib.dto.UtenteRupDeleghe;
import it.csi.cpass.cpassbe.lib.dto.UtenteSettore;
import it.csi.cpass.cpassbe.lib.dto.error.MsgCpassBo;
import it.csi.cpass.cpassbe.lib.dto.ord.Sezione;
import it.csi.cpass.cpassbe.lib.util.pagination.PagedList;
import it.csi.cpass.cpassbe.lib.util.pagination.Sort;
/**
 * Data Access Delegate for utente
 */
@ApplicationScoped
public class UtenteDad extends BaseDad {
	@Inject private CpassTUtenteDao cpassTUtenteDao;
	@Inject private CpassTSettoreDao cpassTSettoreDao;
	@Inject private CpassDModuloDao cpassDModuloDao;
	@Inject private CpassDPermessoDao cpassDPermessoDao;
	@Inject private CpassDRuoloDao cpassDRuoloDao;
	@Inject private CpassRUtenteSettoreDao cpassRUtenteSettoreDao;
	@Inject private CpassRRuoloUtenteSettoreDao cpassRRuoloUtenteSettoreDao;
	@Inject private CpassTOrdSezioneDao cpassTOrdSezioneDao;
	@Inject private CpassTUtenteRupDelegheDao cpassTUtenteRupDelegheDao;
	@Inject private CpassRUtenteRupSettoreDao cpassRUtenteRupSettoreDao;
	@Inject private CpassRDirigenteSettoreDao cpassRDirigenteSettoreDao;
	@Inject private CpassROrdUtenteSezioneDao cpassROrdUtenteSezioneDao;
	@Inject private CpassRRuoloPermessoDao cpassRRuoloPermessoDao;
	@Inject private CpassRModuloRuoloPermessoDao cpassRModuloRuoloPermessoDao;

	/**
	 * Find by id
	 * @param uuid the uuid
	 * @return the model instance
	 */
	public Utente getUtenteCompleto(UUID uuid) {
		final String methodName = "getUtenteCompleto";
		final Utente ute = cpassTUtenteDao.findById(uuid).map(CpassMappers.UTENTE_COMPLETO::toModel).orElseThrow(() -> new NotFoundException("utente"));
		//elimino dalla lista gli utenti settori non piu' validi
		final List<UtenteSettore> listUtenteSettoreValido = ute.getUtenteSettores().stream().filter(el -> el.getValido()).collect(Collectors.toList());
		for(final UtenteSettore us : listUtenteSettoreValido) {
			//elimino dalla lista i ruoli utenti settori non piu' validi
			final List<RuoloUtenteSettore> listRuoloUtenteSettoreValido = us.getRuoloUtenteSettores().stream().filter(el -> el.getValido()).collect(Collectors.toList());
			us.setRuoloUtenteSettores(listRuoloUtenteSettoreValido);
		}
		ute.setUtenteSettores(listUtenteSettoreValido);
		for(final UtenteSettore usett : ute.getUtenteSettores()) {
			log.info(methodName, usett.getId());
			final UUID settoreId = usett.getSettore().getId();
			final UUID utenteId  = usett.getUtente().getId();
			//dirigente
			final Optional<CpassTUtente> cpassTUtenteDirigente = cpassTUtenteDao.getDirigenteSettoreByUtenteIdSettoreId(settoreId,utenteId);
			usett.setDirigente(cpassTUtenteDirigente.isPresent());
			// rup
			final Optional<CpassTUtente> cpassTUtenteRup = cpassTUtenteDao.getUtenteRupSettoreByUtenteIdSettoreId(settoreId,utenteId);
			usett.setRup(cpassTUtenteRup.isPresent());
			//sezioni
			final List<Sezione> seziones = CpassMappers.SEZIONE.toModels(cpassTOrdSezioneDao.getSezioniByUtente(settoreId,utenteId));
			usett.setSeziones(seziones);
		}
		return ute;
	}
	
	/**
	 * 
	 * @param utenteId
	 * @param idSettore
	 * @param listaUtentiSezioni
	 */
	private void cancellaRelazioniUtenteSuSettore(UUID utenteId,UUID idSettore,List<CpassROrdUtenteSezione> listaUtentiSezioni) {
		//2.6.7 CPASS_T_UTENTE_RUP_DELEGHE
		cpassTUtenteRupDelegheDao.deleteLogicallyByUtenteId(utenteId);
		//2.6.6 CPASS_R_ORD_UTENTE_SEZIONE
		if(listaUtentiSezioni ==null) {
			listaUtentiSezioni           = cpassROrdUtenteSezioneDao.getUtenteSezioneByUtenteId(utenteId,idSettore);
		}
		for(final CpassROrdUtenteSezione us : listaUtentiSezioni) {
			cpassROrdUtenteSezioneDao.deleteLogically(us.getId());
		}		
		//2.6.5 CPASS_R_UTENTE_RUP_SETTORE
		cpassRUtenteRupSettoreDao.deleteLogicallyByUtenteId(utenteId,idSettore);
		//2.6.4 CPASS_R_DIRIGENTE_SETTORE
		cpassRDirigenteSettoreDao.deleteLogicallyByUtenteId(utenteId,idSettore,new Date());
		//2.6.3 CPASS_R_RUOLO_UTENTE_SETTORE
		cpassRRuoloUtenteSettoreDao.deleteLogicallyByUtenteId(utenteId,idSettore);
		//2.6.2 CPASS_R_UTENTE_SETTORE
		cpassRUtenteSettoreDao.deleteLogicallyByUtenteId(utenteId,idSettore);
	}

	/**
	 * Inserts the utente
	 * @param utente the utente
	 * @return Utente the model instance
	 */
	public Utente inserisciUtente(Utente utente) {
		CpassTUtente cpassTUtenteNew = CpassMappers.UTENTE.toEntity(utente);
		cpassTUtenteNew = cpassTUtenteDao.insert(cpassTUtenteNew);
		cpassTUtenteDao.flush();
		//inserimento utente settore
		for(final UtenteSettore utenteSettore :utente.getUtenteSettores()){
			CpassRUtenteSettore cpassRUtenteSettore = CpassMappers.UTENTE_SETTORE.toEntity(utenteSettore);
			CpassTSettore cpassTSettore = cpassRUtenteSettore.getCpassTSettore();			
			cpassRUtenteSettore = inserisciSeNonPresenteAttivoUtenteSettore(cpassTUtenteNew, cpassRUtenteSettore);
			//inserimento ruolo utente settore ed eventuale rup
			for(final RuoloUtenteSettore ruoloUtenteSettore : utenteSettore.getRuoloUtenteSettores()) {
				if(ruoloUtenteSettore.getRuolo().getCodice().equals(CpassRuoloEnum.RUP.getCodice())) {
					utenteSettore.setRup(Boolean.TRUE);				
					inserisciRupUtenteSettore(cpassRUtenteSettore.getCpassTUtente(),cpassRUtenteSettore.getCpassTSettore());								
				}
				final CpassRRuoloUtenteSettore rus = CpassMappers.RUOLO_UTENTE_SETTORE.toEntity(ruoloUtenteSettore);
				rus.setCpassRUtenteSettore(cpassRUtenteSettore);
				rus.setDataValiditaInizio(rus.getDataValiditaInizio() == null? new Date() : rus.getDataValiditaInizio());
				cpassRRuoloUtenteSettoreDao.insert(rus );
			}
			//inserimento dirigente settore
			if(utenteSettore.getDirigente()!=null && utenteSettore.getDirigente()) {
				//Se l'utente e' stato indicato su un certo settore come dirigente, occorre ricercare eventuali altri utenti presenti
				//come dirigenti sullo stesso settore e aggiornare i loro record validi (dovrebbe essercene al massimo 1) come segue:
				//Data fine validità valorizzata
				List<CpassRDirigenteSettore> listaDirigenti = cpassRDirigenteSettoreDao.findByRDirigenteSettore(null,cpassTSettore.getId(),new Date());
				//--Issue373
				for(CpassRDirigenteSettore dirigenteSettore: listaDirigenti) {
					dirigenteSettore.setDataValiditaFine(new Date());
					cpassRDirigenteSettoreDao.saveAndFlush(dirigenteSettore);
				}
				inserisciDirigenteSettore(cpassTUtenteNew, cpassTSettore);
			}
			if(utenteSettore.getSeziones() !=null) {
				for(final Sezione sezione : utenteSettore.getSeziones()) {
					final CpassTOrdSezione cpassTOrdSezione =  CpassMappers.SEZIONE.toEntity(sezione);
					inserisciSezioneEntity(cpassTUtenteNew, cpassTSettore, cpassTOrdSezione);
				}
			}
		}
		ApiError ae = null;
		//inserimento rup deleghe
		if(utente.getUtenteRupDeleghes() != null) {
			for(final UtenteRupDeleghe utenteRupDeleghe: utente.getUtenteRupDeleghes()) {
				final CpassTUtenteRupDeleghe cpassTUtenteRupDeleghe = CpassMappers.UTENTE_RUP_DELEGHE_MAPPER.toEntity(utenteRupDeleghe);
				final Optional<Utente> utenteDelegato = getUtente(cpassTUtenteRupDeleghe.getCpassTUtenteRupDelegato().getId());
				if(utenteDelegato.isPresent()) {
					cpassTUtenteRupDeleghe.setCpassTUtenteRup(cpassTUtenteNew);
					cpassTUtenteRupDelegheDao.insert(cpassTUtenteRupDeleghe);
				}else {
					ae = MsgCpassBo.BACUTEE0016.getError();
				}
			}
		}
		final Utente utenteInserito = CpassMappers.UTENTE.toModel(cpassTUtenteNew);
		utenteInserito.setError(ae);
		return utenteInserito;
	}
	/**
	 * 
	 * @param cpassTUtenteNew
	 * @param cpassRUtenteSettore
	 * @return
	 */
	private CpassRUtenteSettore inserisciSeNonPresenteAttivoUtenteSettore(CpassTUtente cpassTUtenteNew,CpassRUtenteSettore cpassRUtenteSettore) {
		
		List<CpassRUtenteSettore> listaUS = cpassRUtenteSettoreDao.findByUtenteSettore(cpassTUtenteNew.getUtenteId(), cpassRUtenteSettore.getCpassTSettore().getId(), new Date());
		if(listaUS==null || listaUS.size()==0) {
			cpassRUtenteSettore.setCpassTUtente(cpassTUtenteNew);
			cpassRUtenteSettore.setDataValiditaInizio(new Date());
			cpassRUtenteSettore = cpassRUtenteSettoreDao.insert(cpassRUtenteSettore);
			cpassRUtenteSettoreDao.flush();
		}else {
			cpassRUtenteSettore.setCpassTUtente(cpassTUtenteNew);
		}
		return cpassRUtenteSettore;
	}

	/**
	 * 
	 * @param cpassTUtenteNew
	 * @param cpassTSettore
	 * @param cpassRDirigenteSettore
	 */
	private void inserisciDirigenteSettore(CpassTUtente cpassTUtenteNew, final CpassTSettore cpassTSettore) {
		CpassRDirigenteSettore cpassRDirigenteSettore = new CpassRDirigenteSettore();
		cpassRDirigenteSettore.setCpassTSettore(cpassTSettore);
		cpassRDirigenteSettore.setCpassTUtente(cpassTUtenteNew);
		cpassRDirigenteSettore.setDataValiditaInizio(new Date());
		cpassRDirigenteSettore.setDataValiditaInizio(cpassRDirigenteSettore.getDataValiditaInizio() == null? new Date() : cpassRDirigenteSettore.getDataValiditaInizio());
		cpassRDirigenteSettoreDao.insert(cpassRDirigenteSettore);
	}
	/**
	 * 
	 * @param utente
	 * @param settore
	 */
	private void inserisciRupUtenteSettore(CpassTUtente utente, CpassTSettore settore) {
		CpassRUtenteRupSettore urs = new CpassRUtenteRupSettore();
		urs.setCpassTSettore(settore);
		urs.setCpassTUtente(utente);
		urs.setDataValiditaInizio(new Date());
		urs.setDataValiditaFine(null);
		cpassRUtenteRupSettoreDao.save(urs);
		cpassRUtenteRupSettoreDao.flush();
	}

	/**
	 *
	 * @param utenteId
	 * @return
	 */
	public List<SettoreRuoliPermessi> getSettoriRuoliPermessiByUtenteService(UUID utenteId) {
		final List<CpassTSettore> cpassTSettori = cpassTSettoreDao.getSettoriByUtenteId(utenteId);
		final List<Settore> listaSettori = CpassMappers.SETTORE_CUSTOM.toModels(cpassTSettori);
		final List<SettoreRuoliPermessi> listaSettoreRuoliPermessi = new ArrayList<>();

		for(final Settore settore : listaSettori) {
			final SettoreRuoliPermessi srp = new SettoreRuoliPermessi();
			srp.setSettore(settore);
			final List<CpassDPermesso> entities = cpassDPermessoDao.getByUtenteIdAndSettoreIdAndModuloId(utenteId, settore.getId(), null);
			final List<Permesso> listPermessi = CpassMappers.PERMESSO.toModels(entities);
			srp.setListPermessi(listPermessi);
			final List<CpassDRuolo> cpassListRuoli = cpassDRuoloDao.getRuoliByUtenteSettore(settore.getId(),utenteId);
			final List<Ruolo> listRuoli = CpassMappers.RUOLO.toModels(cpassListRuoli);
			srp.setListRuoli(listRuoli);
			listaSettoreRuoliPermessi.add(srp);
		}
		return listaSettoreRuoliPermessi;
	}

	/**
	 *
	 * @param listaUtentiCompleti
	 * @param idSettoreOld
	 * @param idSettoreNew
	 * @param utenteConnessoCf
	 * @return
	 */
	public void spostaUtentiDalSettore(List<Utente> listaUtentiCompleti, UUID idSettoreOld, UUID idSettoreNew,String utenteConnessoCf) {
		final List<Utente> listaUtentiCompletiDaSpostare = filtraUtentiSpostabili(listaUtentiCompleti, idSettoreNew, idSettoreOld);
		for(final Utente uteDaspostare: listaUtentiCompletiDaSpostare) {
			//List<UtenteSettore> listUtenteSettoreValido = uteDaspostare.getUtenteSettores().stream().filter(el -> el.getValido()).collect(Collectors.toList());
			final List<CpassRUtenteSettore> cpasslistUtenteSettoreOld = cpassRUtenteSettoreDao.findByUtenteSettore(uteDaspostare.getId(), idSettoreOld,new Date());
			final List<UtenteSettore> listUtenteSettoreOld            = CpassMappers.UTENTE_SETTORE.toModels(cpasslistUtenteSettoreOld);
			final List<CpassROrdUtenteSezione> listaUtentiSezioni     = cpassROrdUtenteSezioneDao.getUtenteSezioneByUtenteId(uteDaspostare.getId(),idSettoreOld);
			//cancella relazioni utente settore vecchio
			cancellaRelazioniUtenteSuSettore(uteDaspostare.getId(),idSettoreOld,listaUtentiSezioni);
			
			//inserisci relazioni utente settore
			for(final UtenteSettore utenteSettoreOld :listUtenteSettoreOld){
				CpassRUtenteSettore cpassRUtenteSettoreNew = new CpassRUtenteSettore();
				
				// se trovo corrispondenza vuol dire che il settore nuovo su cui voglio spostare è già attivo per quell'utente
				Boolean utenteSettorePresente = Boolean.FALSE;
				final List<CpassRUtenteSettore> cpasslistUtenteSettoreNew =cpassRUtenteSettoreDao.findByUtenteSettore(uteDaspostare.getId(), idSettoreNew, new Date());
				if(cpasslistUtenteSettoreNew==null || cpasslistUtenteSettoreNew.size()==0) {
					final CpassTSettore cpassTSettoreNew  = new CpassTSettore();
					cpassTSettoreNew.setId(idSettoreNew);
					cpassRUtenteSettoreNew.setCpassTSettore(cpassTSettoreNew);				
					final CpassTUtente cpassTUtente = new CpassTUtente();
					cpassTUtente.setUtenteId(uteDaspostare.getId());
					cpassRUtenteSettoreNew.setCpassTUtente(cpassTUtente);			
					cpassRUtenteSettoreNew.setDataValiditaInizio(new Date());
					cpassRUtenteSettoreNew.setUtenteSettoreDefault(utenteSettoreOld.getUtenteSettoreDefault());
					cpassRUtenteSettoreNew = cpassRUtenteSettoreDao.save(cpassRUtenteSettoreNew);
					cpassRUtenteSettoreDao.flush();
				}else {
					utenteSettorePresente  = Boolean.TRUE;
					cpassRUtenteSettoreNew = cpasslistUtenteSettoreNew.get(0);
				}			
				//inserimento ruolo utente settore in caso di nuovo utente sezione
				final List<RuoloUtenteSettore> listRuoloUtenteSettoreValido = utenteSettoreOld.getRuoloUtenteSettores().stream().filter(el -> el.getValido()).collect(Collectors.toList());
				for(final RuoloUtenteSettore ruoloUtenteSettore : listRuoloUtenteSettoreValido) {
					CpassDRuolo cpassDRuolo = CpassMappers.RUOLO.toEntity(ruoloUtenteSettore.getRuolo());
					if(!utenteSettorePresente) {
						inserisciRuoloUtenteSettore(cpassRUtenteSettoreNew, cpassDRuolo);
					}else {
						//inserimento ruolo utente settore chiedendomi se devo inserire o riusare il ruolo già precedente
						List<CpassRRuoloUtenteSettore> ruoloGiaPresenteSulSettore = cpassRRuoloUtenteSettoreDao.findByRuoloUtenteSettore(cpassRUtenteSettoreNew.getId(), ruoloUtenteSettore.getRuolo().getId());
						if(ruoloGiaPresenteSulSettore==null || ruoloGiaPresenteSulSettore.size()==0) {
							inserisciRuoloUtenteSettore(cpassRUtenteSettoreNew, cpassDRuolo);
						}						
					}
				}
			}
		}
	}
	
	/**
	 * 
	 * @param cpassRUtenteSettoreNew
	 * @param cpassRRuoloUtenteSettore
	 */
	private void inserisciRuoloUtenteSettore(CpassRUtenteSettore cpassRUtenteSettoreNew,CpassDRuolo cpassDRuolo) {
		CpassRRuoloUtenteSettore cpassRRuoloUtenteSettore = new CpassRRuoloUtenteSettore();
		//se ho un rup inserisco in tabella RupUtenteSettore
		if(cpassDRuolo.getRuoloCodice().equals(CpassRuoloEnum.RUP.getCodice())) {
			inserisciRupUtenteSettore(cpassRUtenteSettoreNew.getCpassTUtente(),cpassRUtenteSettoreNew.getCpassTSettore());
		}
		cpassRRuoloUtenteSettore.setId(null);
		cpassRRuoloUtenteSettore.setCpassDRuolo(cpassDRuolo);
		cpassRRuoloUtenteSettore.setCpassRUtenteSettore(cpassRUtenteSettoreNew);
		cpassRRuoloUtenteSettore.setDataValiditaInizio(new Date());
		cpassRRuoloUtenteSettoreDao.insert(cpassRRuoloUtenteSettore);
	}

	/**
	 * @param listaUtentiCompleti
	 */
	private List<Utente> filtraUtentiSpostabili(List<Utente> listaUtentiCompleti,UUID idSettoreNew,UUID idSettoreOld) {
		final List<Utente> listaUtentiScremati = new ArrayList<>();
		final String methodName="filtraUtentiSpostabili";
		for(final Utente uteDaspostare: listaUtentiCompleti) {
			boolean scartoNew = Boolean.FALSE;
			boolean scartoOld = Boolean.TRUE;
			for(final UtenteSettore utenteSettore :uteDaspostare.getUtenteSettores()){
				if(utenteSettore.getSettore().getId().compareTo(idSettoreNew) == 0 && (utenteSettore.getDataValiditaFine() == null || new Date().compareTo(utenteSettore.getDataValiditaFine())<0)) {
					log.info(methodName, "esiste il settore nuovo ed è valido quindi scarto");
					log.info(methodName, "utenteSettore.getDataValiditaFine() --> " + utenteSettore.getDataValiditaFine());
					scartoNew = Boolean.TRUE;
				}
				if(utenteSettore.getSettore().getId().compareTo(idSettoreOld)==0 && (utenteSettore.getDataValiditaFine() == null || new Date().compareTo(utenteSettore.getDataValiditaFine())>=0)) {
					log.info(methodName, "esiste il settore vecchio ed è valido NON SCARTO");
					log.info(methodName, "utenteSettore.getDataValiditaFine() --> " + utenteSettore.getDataValiditaFine());
					scartoOld = Boolean.FALSE;
				}
			}
			if(!(scartoNew && scartoOld)) {
				listaUtentiScremati.add(uteDaspostare);
			}
		}
		return listaUtentiScremati;
	}

	/**
	 * Updates the utente
	 * @param utente the utente
	 * @return the model instance
	 */
	public Utente updateUtente(Utente utenteNew) {
		final Utente utenteOld = getUtenteCompleto(utenteNew.getId());
		modificaDelegheSuUtente(utenteNew, utenteOld);
		//modifica utenti-settori
		final Collection<UtenteSettore> listaUtenteSettoreOldDaEliminare = modificaUtentiSettoriInModifica(utenteNew, utenteOld);		
		//cancella  utenti-settori
		cancellaUtentiSettoriInModifica(listaUtenteSettoreOldDaEliminare);		
		//inserisci  utenti-settori 
		inserisciUtentiSettoriNuoviInModifica(utenteNew);
		//valorizza tabella r_rup		
		final CpassTUtente cpassTutente = CpassMappers.UTENTE.toEntity(utenteNew);
		cpassTUtenteDao.update(cpassTutente,Boolean.FALSE);
		return utenteNew;
	}

	/**
	 * @param utenteNew
	 * @param utenteOld
	 */
	private void modificaDelegheSuUtente(Utente utenteNew, Utente utenteOld) {
		final Map<Integer, UtenteRupDeleghe> listaDelegheOld = listToMap(utenteOld.getUtenteRupDeleghes());
		boolean inserisco = Boolean.FALSE;
		Date dataIns = new Date();
		//ciclo insert
		for(final UtenteRupDeleghe utenteRupDeleghe: utenteNew.getUtenteRupDeleghes()) {
			final CpassTUtenteRupDeleghe cpassTUtenteRupDelegheNew = CpassMappers.UTENTE_RUP_DELEGHE_MAPPER.toEntity(utenteRupDeleghe);
			if(utenteRupDeleghe.getId()== null) {
				if(cpassTUtenteRupDelegheNew.getDataValiditaInizio()==null) {
					cpassTUtenteRupDelegheNew.setDataValiditaInizio(dataIns);
				}else {
					dataIns = cpassTUtenteRupDelegheNew.getDataValiditaInizio();
				}
				cpassTUtenteRupDelegheDao.insert(cpassTUtenteRupDelegheNew);
				inserisco = Boolean.TRUE;
			}
		}

		//ciclo update
		for(final UtenteRupDeleghe utenteRupDeleghe: utenteNew.getUtenteRupDeleghes()) {
			final CpassTUtenteRupDeleghe cpassTUtenteRupDelegheNew = CpassMappers.UTENTE_RUP_DELEGHE_MAPPER.toEntity(utenteRupDeleghe);
			if(utenteRupDeleghe.getId()!= null) {
				//update
				if(inserisco) {
					if(cpassTUtenteRupDelegheNew.getDataValiditaFine()==null || new Date().compareTo(cpassTUtenteRupDelegheNew.getDataValiditaFine())<0) {
						cpassTUtenteRupDelegheNew.setDataValiditaFine(new Date());
					}
				}
				cpassTUtenteRupDelegheDao.update(cpassTUtenteRupDelegheNew);
				listaDelegheOld.remove(utenteRupDeleghe.getId());
			}
		}

		for( final UtenteRupDeleghe cancellabile: listaDelegheOld.values()) {
			cancellabile.setDataValiditaFine(new Date());
			cpassTUtenteRupDelegheDao.update( CpassMappers.UTENTE_RUP_DELEGHE_MAPPER.toEntity(cancellabile));
		}
	}

	/**
	 *
	 * @param utenteNew
	 * @return List<UtenteSettore>
	 */
	private List<UtenteSettore> inserisciUtentiSettoriNuoviInModifica(Utente utenteNew) {
		final List<UtenteSettore> ris = new ArrayList<>();
		for(final UtenteSettore utenteSettNew : utenteNew.getUtenteSettores()) {
			final CpassRUtenteSettore cpassRUtenteSettore = CpassMappers.UTENTE_SETTORE.toEntity(utenteSettNew);
			if (utenteSettNew.getId() == null ) {
				
				CpassRUtenteSettore cpassRUtenteSettoreNew = inserisciSeNonPresenteAttivoUtenteSettore( CpassMappers.UTENTE.toEntity(utenteNew), cpassRUtenteSettore);				
				if(utenteSettNew.getDirigente()!=null && !utenteSettNew.getDirigente()) {
					utenteSettNew.setDirigente(Boolean.FALSE);
				}

				if(utenteSettNew.getDirigente()) {
					//mi chiedo se su questo settore ho altri dirigenti
					final List<CpassRDirigenteSettore> altriDirigentiSuStessoSettore = cpassRDirigenteSettoreDao.findByRAltriDirigentiSuStessoSettore(cpassRUtenteSettoreNew.getCpassTUtente().getId(),cpassRUtenteSettoreNew.getCpassTSettore().getId(),new Date());
					//chiudo eventuali altri dirigenti sullo stesso settore //dato che il dirigente è solo 1 x settore
					for(CpassRDirigenteSettore dirigenteAltri :altriDirigentiSuStessoSettore) {
						dirigenteAltri.setDataValiditaFine(new Date());
						cpassRDirigenteSettoreDao.saveAndFlush(dirigenteAltri);
					}
					inserisciDirigenteSettore( cpassRUtenteSettoreNew.getCpassTUtente(),   cpassRUtenteSettoreNew.getCpassTSettore());
				}

				for(final RuoloUtenteSettore ruoloUS : utenteSettNew.getRuoloUtenteSettores()) {
					utenteSettNew.setRup(Boolean.FALSE);
					if(ruoloUS.getRuolo().getCodice().equals(CpassRuoloEnum.RUP.getCodice())) {
						utenteSettNew.setRup(Boolean.TRUE);
						inserisciRupUtenteSettore(cpassRUtenteSettore.getCpassTUtente(),cpassRUtenteSettore.getCpassTSettore());			
					}
					final CpassRRuoloUtenteSettore entityRUS = CpassMappers.RUOLO_UTENTE_SETTORE.toEntity(ruoloUS);
					entityRUS.setCpassRUtenteSettore(cpassRUtenteSettoreNew);
					entityRUS.setDataValiditaInizio(entityRUS.getDataValiditaInizio() == null? new Date() : entityRUS.getDataValiditaInizio());
					cpassRRuoloUtenteSettoreDao.insert(entityRUS);
				}
				
				if(utenteSettNew.getSeziones()!= null) {
					for(final Sezione sezione : utenteSettNew.getSeziones()) {
						CpassTOrdSezione cpassTOrdSezione= CpassMappers.SEZIONE.toEntity(sezione);
						inserisciSezioneEntity(cpassRUtenteSettoreNew.getCpassTUtente(), cpassRUtenteSettoreNew.getCpassTSettore(), cpassTOrdSezione);
					}
				}
			}
		}
		return ris;
	}

	/**
	 * 
	 * @param utenteNew
	 * @param utenteOld
	 * @return Collection<UtenteSettore>
	 */
	private Collection<UtenteSettore> modificaUtentiSettoriInModifica(Utente utenteNew, Utente utenteOld) {
		final Map<Integer, UtenteSettore> listaUtenteSettoreOld = listToMap(utenteOld.getUtenteSettores());
		for(final UtenteSettore utenteSettNew : utenteNew.getUtenteSettores()) {
			if (utenteSettNew.getId() != null ) {
				final UtenteSettore utenteSettOld = listaUtenteSettoreOld.get(utenteSettNew.getId());
				final List<RuoloUtenteSettore> ruoloUtenteSettoreAttivo = utenteSettNew.getRuoloUtenteSettores().stream().filter(el -> el.getValido()).collect(Collectors.toList());
				if(utenteSettNew.getDataValiditaFine()!=null && ruoloUtenteSettoreAttivo.size() > 0){
					utenteSettOld.setDataValiditaFine(null);
					cpassRUtenteSettoreDao.update(CpassMappers.UTENTE_SETTORE.toEntity(utenteSettOld));
					cpassRUtenteSettoreDao.flush();
				}
				final CpassRUtenteSettore cpassRUtenteSettoreNew = CpassMappers.UTENTE_SETTORE.toEntity(utenteSettNew);
				gestisciRupModifica(utenteSettNew, cpassRUtenteSettoreNew);
				gestisciDirigente(utenteSettNew, cpassRUtenteSettoreNew);
				final Boolean xstNewRuolo = gestisciRuoloUtenteSettoriModifica(utenteSettNew, utenteSettOld, cpassRUtenteSettoreNew);
				
				gestisciSezioni(utenteNew, utenteSettNew, utenteSettOld);
				
				if(utenteSettNew.getDataValiditaFine()==null && ruoloUtenteSettoreAttivo.size()==0 && !xstNewRuolo){
					utenteSettOld.setDataValiditaFine(new Date());
					cpassRUtenteSettoreDao.update(CpassMappers.UTENTE_SETTORE.toEntity(utenteSettOld));
					cpassRUtenteSettoreDao.flush();
				}
				if(utenteSettNew.getDataValiditaFine()!=null && ruoloUtenteSettoreAttivo.size()> 0 && xstNewRuolo){
					utenteSettOld.setDataValiditaFine(null);
					cpassRUtenteSettoreDao.update(CpassMappers.UTENTE_SETTORE.toEntity(utenteSettOld));
					cpassRUtenteSettoreDao.flush();
				}
				// identifico gli utenti settori da eliminare logicamente
				listaUtenteSettoreOld.remove(utenteSettNew.getId());
			}
		}
		return 	listaUtenteSettoreOld.values();
	}

	/**
	 * @param utenteSettNew
	 * @param cpassRUtenteSettoreNew
	 */
	private void gestisciDirigente(UtenteSettore utenteSettNew, CpassRUtenteSettore cpassRUtenteSettoreNew) {
		//Dirigente mi chiedo se l'utente sia dirigente attivo sul settore in esame
		final List<CpassRDirigenteSettore> sonDirigenteSuQuestoSettore   = cpassRDirigenteSettoreDao.findByRDirigenteSettore(cpassRUtenteSettoreNew.getCpassTUtente().getId(),cpassRUtenteSettoreNew.getCpassTSettore().getId(),new Date());
		//mi chiedo se su questo settore ho altri dirigenti
		final List<CpassRDirigenteSettore> altriDirigentiSuStessoSettore = cpassRDirigenteSettoreDao.findByRAltriDirigentiSuStessoSettore(cpassRUtenteSettoreNew.getCpassTUtente().getId(),cpassRUtenteSettoreNew.getCpassTSettore().getId(),new Date());

		if(utenteSettNew.getDirigente() && (sonDirigenteSuQuestoSettore != null && sonDirigenteSuQuestoSettore.size()>0)) {
			//se devo essere dirigente
			if(utenteSettNew.getDirigente()) {
				//chiudo eventuali altri dirigenti sullo stesso settore //dato che il dirigente è solo 1 x settore
				for(CpassRDirigenteSettore dirigenteAltri :altriDirigentiSuStessoSettore) {
					dirigenteAltri.setDataValiditaFine(new Date());
					cpassRDirigenteSettoreDao.saveAndFlush(dirigenteAltri);
				}
				// se non sono ancora mappato come dirigente su questo settore inserisco il record
				if (sonDirigenteSuQuestoSettore == null || sonDirigenteSuQuestoSettore.size()==0){
					final CpassRDirigenteSettore cpassRDirigenteSettore = new CpassRDirigenteSettore();
					cpassRDirigenteSettore.setCpassTSettore(cpassRUtenteSettoreNew.getCpassTSettore());
					cpassRDirigenteSettore.setCpassTUtente(cpassRUtenteSettoreNew.getCpassTUtente());
					cpassRDirigenteSettoreDao.insert(cpassRDirigenteSettore);
				}
			}
		}
		// caso di flag dirigente a false se esiste la mappatura a dirigente attiva la chiudo
		if(!utenteSettNew.getDirigente()) {
			if (sonDirigenteSuQuestoSettore != null && sonDirigenteSuQuestoSettore.size()>0){
				final CpassRDirigenteSettore dsNew = sonDirigenteSuQuestoSettore.get(0);
				dsNew.setDataValiditaFine(new Date());
				cpassRDirigenteSettoreDao.update(dsNew);
			}
		}
		cpassRDirigenteSettoreDao.flush();
	}

	/**
	 * @param utenteSettNew
	 * @param utenteSettOld
	 * @param cpassRUtenteSettoreNew
	 */
	private Boolean gestisciRuoloUtenteSettoriModifica(UtenteSettore utenteSettNew, UtenteSettore utenteSettOld,CpassRUtenteSettore cpassRUtenteSettoreNew) {
		//ruolo utente settore
		Boolean xstNewRuoloUtenteSettore = Boolean.FALSE;
		final Map<Integer, RuoloUtenteSettore> listaRUSOld = listToMap(utenteSettOld.getRuoloUtenteSettores());
		for(final RuoloUtenteSettore ruoloUS : utenteSettNew.getRuoloUtenteSettores()) {
			final CpassRRuoloUtenteSettore entityRUS = CpassMappers.RUOLO_UTENTE_SETTORE.toEntity(ruoloUS);
			entityRUS.setCpassRUtenteSettore(cpassRUtenteSettoreNew);
			if(ruoloUS.getId()== null) {
				cpassRRuoloUtenteSettoreDao.insert(entityRUS);
				xstNewRuoloUtenteSettore = Boolean.TRUE;
			}else {
				//per come è attualmente il db non necessito di fare update ma mantengo esattamente le cose come stanno
				//cpassRRuoloUtenteSettoreDao.update(entityRUS);
				listaRUSOld.remove(ruoloUS.getId());
			}
		}
		cpassRRuoloUtenteSettoreDao.flush();
		//cancello i vecchi
		for(final RuoloUtenteSettore rusDaCancellare : listaRUSOld.values()) {
			rusDaCancellare.setDataValiditaFine(new Date());
			cpassRRuoloUtenteSettoreDao.update(CpassMappers.RUOLO_UTENTE_SETTORE.toEntity(rusDaCancellare));
		}
		return xstNewRuoloUtenteSettore;
	}

	/**
	 * @param utenteNew
	 * @param utenteSettNew
	 * @param utenteSettOld
	 */
	private void gestisciSezioni(Utente utenteNew, UtenteSettore utenteSettNew, UtenteSettore utenteSettOld) {
		//SEZIONI
		Map<Integer, Sezione> listaSezioniOld = listToMap(utenteSettOld.getSeziones());
		//scremo le sezioni in base al settore specifico in analisi
		listaSezioniOld = listaSezioniOldBySettore(listaSezioniOld, utenteSettNew.getSettore().getId());
		for(final Sezione sezione : utenteSettNew.getSeziones()) {
			final CpassTOrdSezione cpassTOrdSezione = CpassMappers.SEZIONE.toEntity(sezione);
			final List<CpassROrdUtenteSezione> sezioneRicercata = cpassROrdUtenteSezioneDao.getUtenteSezioneByUtenteId(utenteNew.getId(),utenteSettNew.getSettore().getId());
			if(sezioneRicercata == null || sezioneRicercata.size() == 0) {
				CpassTSettore cpassTSettore = new CpassTSettore();
				cpassTSettore.setId(utenteSettNew.getSettore().getId());	
				CpassTUtente cpassTUtente = CpassMappers.UTENTE.toEntity(utenteNew);		
				inserisciSezioneEntity(cpassTUtente, cpassTSettore, cpassTOrdSezione);
			}else {
				// eventualmente update ma al momento non essendoci altri metadati rispetto alle sole chiavi
				// di fk non serve sia fatto nulla se non escludere dalla lista odl come oggetti da non cancellare
				listaSezioniOld.remove(sezione.getId());
			}
		}
		cpassROrdUtenteSezioneDao.flush();
		//cancello le vecchie associazioni con le sezioni
		for( final Sezione sezioniDaSganciare : listaSezioniOld.values()) {
			final List<CpassROrdUtenteSezione> listaR = cpassROrdUtenteSezioneDao.getUtenteSezioneByUtenteId(utenteNew.getId(), sezioniDaSganciare.getSettore().getId());
			for(final CpassROrdUtenteSezione rus : listaR) {
				cpassROrdUtenteSezioneDao.deleteLogically(rus.getId());
			}
		}
	}
	
	/**
	 * 
	 * @param cpassTUtente
	 * @param cpassTSettore
	 * @param cpassTOrdSezione
	 */
	private void inserisciSezioneEntity(CpassTUtente cpassTUtente, CpassTSettore cpassTSettore,final CpassTOrdSezione cpassTOrdSezione) {
		final CpassROrdUtenteSezione cpassROrdUtenteSezione = new CpassROrdUtenteSezione();
		cpassROrdUtenteSezione.setCpassTUtente(cpassTUtente);
		cpassROrdUtenteSezione.setCpassTOrdSezione(cpassTOrdSezione);
		cpassROrdUtenteSezione.setCpassTSettore(cpassTSettore);							
		cpassROrdUtenteSezioneDao.insert(cpassROrdUtenteSezione);
	}
	/**
	 *
	 * @param listaSezioniOld
	 * @param settoreId
	 * @return
	 */
	private Map<Integer, Sezione> listaSezioniOldBySettore(Map<Integer, Sezione> listaSezioniOld, UUID settoreId) {
		final List<Sezione> ris = new ArrayList<>();
		for(final Sezione sezione : listaSezioniOld.values()) {
			if(sezione.getSettore().getId().compareTo(settoreId) > -1 ) {
				ris.add(sezione);
			}
		}
		return listToMap(ris);
	}

	/**
	 * 
	 * @param listaUtenteSettoreOldDaEliminare
	 */
	private void cancellaUtentiSettoriInModifica(Collection<UtenteSettore> listaUtenteSettoreOldDaEliminare) {
		for(final UtenteSettore utenteSettore : listaUtenteSettoreOldDaEliminare) {
			final CpassRUtenteSettore cpassRUtenteSettore = CpassMappers.UTENTE_SETTORE.toEntity(utenteSettore);
			// chiudere i ruoli utenti settori
			for(final CpassRRuoloUtenteSettore rus : cpassRUtenteSettore.getCpassRRuoloUtenteSettores()) {
				if(rus.getDataValiditaFine() == null){
					rus.setDataValiditaFine(new Date());
					cpassRRuoloUtenteSettoreDao.update(rus);
					cpassRRuoloUtenteSettoreDao.flush();
					if(rus.getCpassDRuolo().getRuoloCodice().equals(CpassRuoloEnum.RUP.getCodice())) {
						// chiudere il rup
						final List<CpassRUtenteRupSettore> urs = cpassRUtenteRupSettoreDao.getRupBySettoreUtenteAttivi(utenteSettore.getSettore().getId(),utenteSettore.getUtente().getId(),new Date());
						if(urs != null && urs.size()>0) {
							final CpassRUtenteRupSettore rusOld = urs.get(0);
							rusOld.setDataValiditaFine(new Date());
							cpassRUtenteRupSettoreDao.update(rusOld);
							cpassRUtenteRupSettoreDao.flush();
						}
					}
				}
			}
			// chiudere le relazioni con il ruolo di dirigente
			cpassRDirigenteSettoreDao.deleteLogicallyByUtenteId(utenteSettore.getUtente().getId(),utenteSettore.getSettore().getId(),new Date());
			cpassRDirigenteSettoreDao.flush();
			if(cpassRUtenteSettore.getDataValiditaFine() == null) {
				cpassRUtenteSettore.setDataValiditaFine(new Date());
				cpassRUtenteSettoreDao.update(cpassRUtenteSettore);
				cpassRUtenteSettoreDao.flush();
			}
		}
	}
	/**
	 * 
	 * @param utenteSettNew
	 * @param cpassRUtenteSettoreNew
	 */
	private void gestisciRupModifica(UtenteSettore utenteSettNew, CpassRUtenteSettore cpassRUtenteSettoreNew) {
		//RUP se l'utente settore ha un ruolo rup attivo lo inserisco anche nella CpassRUtenteSettore
		final List<CpassRRuoloUtenteSettore> listRup = cpassRUtenteSettoreNew.getCpassRRuoloUtenteSettores().stream().filter(el -> el.getCpassDRuolo().getRuoloCodice().equalsIgnoreCase(CpassRuoloEnum.RUP.getCodice()) && el.getDataValiditaFine() == null ).collect(Collectors.toList());
		if(listRup != null && listRup.size()>0) {
			final CpassRUtenteRupSettore uRs = new CpassRUtenteRupSettore();
			uRs.setCpassTSettore(cpassRUtenteSettoreNew.getCpassTSettore());
			uRs.setCpassTUtente(cpassRUtenteSettoreNew.getCpassTUtente());
			uRs.setDataValiditaInizio(new Date());
			final List<CpassRUtenteRupSettore> rup = cpassRUtenteRupSettoreDao.getRupBySettoreUtenteAttivi(cpassRUtenteSettoreNew.getCpassTSettore().getId(), cpassRUtenteSettoreNew.getCpassTUtente().getId(), new Date());
			if(rup == null || rup.size() == 0) {
				cpassRUtenteRupSettoreDao.insert(uRs);
			}
		}else {
			//se l'utente settore NON ha un ruolo rup attivo lo lo cerco nella CpassRUtenteSettore ed eventualmente lo chiudo
			final List<CpassRUtenteRupSettore> rus = cpassRUtenteRupSettoreDao.getRupBySettoreUtenteAttivi(cpassRUtenteSettoreNew.getCpassTSettore().getId(),cpassRUtenteSettoreNew.getCpassTUtente().getId(),new Date());
			if(rus != null && rus.size()>0) {
				final CpassRUtenteRupSettore rusOld = rus.get(0);
				rusOld.setDataValiditaFine(new Date());
				cpassRUtenteRupSettoreDao.update(rusOld);
				cpassRUtenteRupSettoreDao.flush();
			}
		}
	}

	/**
	 * 
	 * @param page
	 * @param size
	 * @param sort
	 * @return
	 */
	public PagedList<RuoloPermesso> getPermessiDaGestire(int page, int size, Sort sort) {
		String sortField = null;
		String sortDirection = null;

		if (sort != null && StringUtility.isNotEmpty(sort.getField())) {
			sortField = RuoloPermessoSort.byModelName(sort.getField()).getQueryName();
			sortDirection = sort.getOrder().getSortDirection();
		}

		final Page<CpassRRuoloPermesso> cpassRRuoloPermesso = cpassRRuoloPermessoDao.getPermessiSelezionabili(null,page, size, sortField, sortDirection );
		final PagedList<RuoloPermesso> pagedList = toPagedList(cpassRRuoloPermesso, page, size, CpassMappers.RUOLO_PERMESSO::toModel);
		return pagedList;
	}
	
	/**
	 * 
	 * @param page
	 * @param size
	 * @param sort
	 * @return
	 */
	public PagedList<ModuloRuoloPermesso> getPermessiDaGestireNonAttivi(int page, int size, Sort sort) {
		String sortField = null;
		String sortDirection = null;
		if (sort != null) {
			sortField = ModuloRuoloPermessoSort.byModelName(sort.getField()).getQueryName();
			sortDirection = sort.getOrder().getSortDirection();
		}
		final Page<CpassRModuloRuoloPermesso> cpassRRuoloPermesso = cpassRModuloRuoloPermessoDao.getPermessiSelezionabiliNonAttivi(null,page, size, sortField, sortDirection);
		final PagedList<ModuloRuoloPermesso> pagedList = toPagedList(cpassRRuoloPermesso, page, size, CpassMappers.MODULO_RUOLO_PERMESSO::toModel);
		return pagedList;
	}
	/**
	 * 
	 * @param ruoloPermessoId
	 */
	public void deleteRuoloPermessoById(Integer ruoloPermessoId) {
		cpassRRuoloPermessoDao.deleteById(ruoloPermessoId);
	}
	/**
	 * 
	 * @return
	 */
	public List<RuoloPermesso> getRuoloPermessoList() {
		final List<CpassRRuoloPermesso> cpassRRuoloPermesso = cpassRRuoloPermessoDao.getRuoloPermessoList(null);
		final List<RuoloPermesso> pagedList = CpassMappers.RUOLO_PERMESSO.toModels(cpassRRuoloPermesso);
		return pagedList;
	}
	/**
	 * 
	 * @return
	 */
	public List<ModuloRuoloPermesso> getRuoloPermessoNonAttiviList() {
		final List<CpassRModuloRuoloPermesso> cpassRRuoloPermesso = cpassRModuloRuoloPermessoDao.getRuoloPermessoNonAttiviList(null);
		final List<ModuloRuoloPermesso> pagedList = CpassMappers.MODULO_RUOLO_PERMESSO.toModels(cpassRRuoloPermesso);
		return pagedList;
	}

	/**
	 * Find by id
	 * @param uuid the uuid
	 * @return the model instance
	 */
	public Optional<Utente> getUtente(UUID uuid) {
		return cpassTUtenteDao.findOne(uuid).map(CpassMappers.UTENTE::toModel);
	}
	/**
	 *
	 * @param uuid
	 * @return Optional<Utente>
	 */
	public Optional<Utente> getUtenteById(UUID uuid) {
		return cpassTUtenteDao.findById(uuid).map(CpassMappers.UTENTE::toModel);
	}

	/**
	 * Find by id
	 * @param uuid the uuid
	 * @return the model instance
	 */
	public Optional<Utente> getUtenteRupDelegante(UUID uuid) {
		return cpassTUtenteDao.getUtenteRupDelegante(uuid).map(CpassMappers.UTENTE::toModel);
	}

	/**
	 * Find paginated
	 * @param page the page
	 * @param size the size
	 * @return the model instances
	 */
	public PagedList<Utente> getUtenti(int page, int size) {
		final Page<CpassTUtente> cpassTUtentes = cpassTUtenteDao.findAll(page, size);
		return toPagedList(cpassTUtentes, page, size, CpassMappers.UTENTE::toModel);
	}

	/**
	 *
	 * @param utenteId
	 * @param utenteConnessoCf
	 * @param soloRelazioni voglio cancellare tutte le relazioni dell'utente ma non l'utente stesso
	 */
	public void deleteUtente(UUID utenteId) {
		cancellaRelazioniUtenteSuSettore(utenteId,null,null);
		cpassTUtenteDao.deleteLogically(utenteId);
	}
	
	/**
	 * Gets an Utente by its codice fiscale
	 * @param codiceFiscale
	 * @return the utente instance
	 */
	public Optional<Utente> getUtenteByCf(String codiceFiscale,Boolean valido) {
		final Optional<CpassTUtente> cpassTUtente = cpassTUtenteDao.findUtenteByCf(codiceFiscale,valido);
		return cpassTUtente.map(CpassMappers.UTENTE::toModel);
	}
	
	/**
	 *
	 * @param rus
	 * @return
	 */
	public CpassRUtenteSettore saveUtenteSettore(CpassRUtenteSettore rus) {
		return cpassRUtenteSettoreDao.save(rus);
	}
	/**
	 *
	 * @param rus
	 * @return
	 */
	public CpassRRuoloUtenteSettore saveRuoloUtenteSettore(CpassRRuoloUtenteSettore rus) {
		return cpassRRuoloUtenteSettoreDao.save(rus);
	}
	/**
	 *
	 * @param enteId
	 * @param selezionabileDaProcedura
	 * @return
	 */
	public List<Ruolo> getRuoliByEnte(UUID enteId, String selezionabileDaProcedura) {
		final List<CpassDRuolo> cpassListRuoli = cpassDRuoloDao.getRuoliByEnte(enteId, selezionabileDaProcedura);
		final List<Ruolo> listRuoli = CpassMappers.RUOLO.toModels(cpassListRuoli);
		return listRuoli;
	}
	/**
	 *
	 * @param page
	 * @param size
	 * @param sort
	 * @param dirigente
	 * @param utente
	 * @param ruolo
	 * @param settore
	 * @param enteId
	 * @param checkDataValiditaFine
	 * @return
	 */
	public PagedList<Utente> getRicercaUtenti(int page, int size, Sort sort, Boolean dirigente, Utente utente,Ruolo ruolo, Settore settore,UUID enteId, Boolean checkDataValiditaFine) {
		String sortField = null;
		String sortDirection = null;
		//checkDataValiditaFine = true;
		if (sort != null) {
			if (UtenteSort.byModelName(sort.getField())!=null) {
				sortField = UtenteSort.byModelName(sort.getField()).getQueryName();
			}
			sortDirection = sort.getOrder().getSortDirection();
		}

		final Page<CpassTUtente> cpassTUtentes = cpassTUtenteDao.findPaginated(  dirigente != null ? dirigente : Boolean.FALSE
																				,utente != null ? utente : new Utente()
				                                                                ,ruolo != null ? ruolo : new Ruolo()
						                                                        ,settore != null ? settore : new Settore()
								                                                ,enteId
								                                                ,checkDataValiditaFine
								                                                ,page
								                                                ,size
								                                                ,sortField
								                                                ,sortDirection);

		final PagedList<Utente> pagedList = toPagedList(cpassTUtentes, page, size, CpassMappers.UTENTE::toModel);
		return pagedList;
	}
	/**
	 *
	 * @param dirigente
	 * @param utente
	 * @param ruolo
	 * @param settore
	 * @param enteId
	 * @return
	 */
	public List<Utente> getRicercaUtentiNoPage( Boolean dirigente, Utente utente,Ruolo ruolo, Settore settore,UUID enteId) {
		final List<CpassTUtente> cpassTUtentes = cpassTUtenteDao.findPaginatedNoPage(    dirigente != null ? dirigente : Boolean.FALSE
																						,utente    != null ? utente : new Utente()
																						,ruolo     != null ? ruolo : new Ruolo()
																						,settore   != null ? settore : new Settore()
																						,enteId
		);
		return CpassMappers.UTENTE.toModels(cpassTUtentes);
	}

	/**
	 * Gets the Settore by the utente
	 * @param utenteId the utente id
	 * @return the settore instances
	 */
	public List<Settore> getSettoriByUtente(UUID utenteId) {
		final List<CpassTSettore> cpassTSettori = cpassTSettoreDao.getSettoriByUtenteId(utenteId);
		final List<Settore> listaSettori = CpassMappers.SETTORE_CUSTOM.toModels(cpassTSettori);
		return listaSettori;
	}
	/**
	 * Gets the Modulo by the utente and settore
	 * @param utenteId the utente id
	 * @param settoreId the settore id
	 * @return the modulo instances
	 */
	public List<Modulo> getModuliByUtenteAndSettore(UUID utenteId, UUID settoreId) {
		final List<CpassDModulo> entities = cpassDModuloDao.getByUtenteIdAndSettoreId(utenteId, settoreId);
		return CpassMappers.MODULO.toModels(entities);
	}

	/**
	 * Gets the Modulo by the utente and settore
	 * @param utenteId the utente id
	 * @param settoreId the settore id
	 * @return the modulo instances
	 */
	public List<Modulo> getModuliByUtenteAndSettore(UUID utenteId, UUID settoreId, Date checkDate) {
		final List<CpassDModulo> entities = cpassDModuloDao.getByUtenteIdAndSettoreId(utenteId, settoreId, checkDate);
		return CpassMappers.MODULO.toModels(entities);
	}

	/**
	 * Gets the Permesso by the utente and settore and modulo
	 * @param utenteId the utente id
	 * @param settoreId the settore id
	 * @param moduloId the modulo id
	 * @return the permesso instances
	 */
	public List<Permesso> getPermessiByUtenteAndSettoreAndModulo(UUID utenteId, UUID settoreId, Integer moduloId) {
		final List<CpassDPermesso> entities = cpassDPermessoDao.getByUtenteIdAndSettoreIdAndModuloId(utenteId, settoreId, moduloId);
		return CpassMappers.PERMESSO.toModels(entities);
	}

	/**
	 * Gets the Permesso by the utente and settore and modulo
	 * @param utenteId the utente id
	 * @param settoreId the settore id
	 * @return the permesso instances
	 */
	public List<Permesso> getPermessiByUtenteAndSettore(UUID utenteId, UUID settoreId) {
		final List<CpassDPermesso> entities = cpassDPermessoDao.getByUtenteIdAndSettoreId(utenteId, settoreId);
		return CpassMappers.PERMESSO.toModels(entities);
	}

	/**
	 * Gets the utente by the settore
	 * @param settoreId the settore id
	 * @param ruoloCodice
	 * @return the utente
	 */
	public List<Utente> getUtenteBySettoreRuolo(UUID settoreId, String ruoloCodice) {
		final List<CpassTUtente> entities = cpassTUtenteDao.getUtenteBySettoreRuolo(settoreId,ruoloCodice);
		return CpassMappers.UTENTE.toModels(entities);
	}
	/**
	 * Gets the utente by role
	 * @param ruoloCodice
	 * @return the utente
	 */
	public List<Utente> getUtenteByRuolo(String ruoloCodice) {
		final List<CpassTUtente> entities = cpassTUtenteDao.getUtenteBySettoreRuolo(null,ruoloCodice);
		return CpassMappers.UTENTE.toModels(entities);
	}
	/**
	 *
	 * @param utenteId
	 * @param settoreId
	 * @return List<Ruolo>
	 */
	public List<Ruolo> getRuoliByUtenteSettore(UUID utenteId, UUID settoreId) {
		final List<CpassDRuolo> entities = cpassDRuoloDao.getRuoliByUtenteSettore(settoreId, utenteId);
		return CpassMappers.RUOLO.toModels(entities);
	}
	/**
	 *
	 * @param utenteId
	 * @param settoreId
	 * @return List<CpassRUtenteSettore>
	 */
	public List<CpassRUtenteSettore> findByUtenteSettore(UUID utenteId, UUID settoreId,Date data) {
		final List<CpassRUtenteSettore> entities =  cpassRUtenteSettoreDao.findByUtenteSettore(utenteId, settoreId,data);
		return entities;
	}
	/**
	 *
	 * @param utenteSettoreId
	 * @param ruoloId
	 * @return List<CpassRRuoloUtenteSettore>
	 */
	public List<CpassRRuoloUtenteSettore> findByRuoloUtenteSettore(Integer utenteSettoreId, Integer ruoloId) {
		return  cpassRRuoloUtenteSettoreDao.findByRuoloUtenteSettore(utenteSettoreId,ruoloId);
	}
	/**
	 *
	 * @param settoreId
	 * @return List<Utente>
	 */
	public List<Utente> getRupsBySettoreId(UUID settoreId) {
		final List<CpassTUtente> entities = cpassTUtenteDao.getRupsBySettoreId(settoreId);
		return CpassMappers.UTENTE.toModels(entities);
	}

	/**
	 * Gets the Settore by the utente RUP
	 * @param utenteId the utente id
	 * @return the settore instances
	 */
	public List<Settore> getSettoriByRupId(UUID rupId) {
		final List<CpassTSettore> listaSettori = cpassTSettoreDao.getSettoriByRupId(rupId);
		return CpassMappers.SETTORE_CUSTOM.toModels(listaSettori);
	}
	/**
	 * 
	 * @param ruoloPermesso
	 * @return
	 */
	public RuoloPermesso insertRRuoloPermesso (RuoloPermesso ruoloPermesso) {
		CpassRRuoloPermesso cpassRRuoloPermesso = CpassMappers.RUOLO_PERMESSO.toEntity(ruoloPermesso);
		cpassRRuoloPermesso = cpassRRuoloPermessoDao.insert(cpassRRuoloPermesso);
		cpassRRuoloPermessoDao.flush();
		return CpassMappers.RUOLO_PERMESSO.toModel(cpassRRuoloPermesso);
	}
}