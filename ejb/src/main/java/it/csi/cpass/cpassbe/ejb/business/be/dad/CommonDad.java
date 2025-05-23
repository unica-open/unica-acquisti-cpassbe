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
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import it.csi.cpass.cpassbe.ejb.business.be.dad.sort.OdsSort;
import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.CpassTAggiornamenoStrutturaDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.CpassTFornitoreDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.CpassTGestioneCampoDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.CpassTListinoFornitoreDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.CpassTMetadatiFunzioneDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.CpassTProgressivoDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.CpassTSettoreDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.CpassTUfficioDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.decodifiche.CpassDOggettiSpesaDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.view.CpassVSettoreDao;
import it.csi.cpass.cpassbe.ejb.entity.CpassDOggettiSpesa;
import it.csi.cpass.cpassbe.ejb.entity.CpassTAggiornamentoStruttura;
import it.csi.cpass.cpassbe.ejb.entity.CpassTEnte;
import it.csi.cpass.cpassbe.ejb.entity.CpassTGestioneCampo;
import it.csi.cpass.cpassbe.ejb.entity.CpassTListinoFornitore;
import it.csi.cpass.cpassbe.ejb.entity.CpassTMetadatiFunzione;
import it.csi.cpass.cpassbe.ejb.entity.CpassTProgressivo;
import it.csi.cpass.cpassbe.ejb.entity.CpassTProgressivoPk;
import it.csi.cpass.cpassbe.ejb.entity.CpassTSettore;
import it.csi.cpass.cpassbe.ejb.entity.CpassTUfficio;
import it.csi.cpass.cpassbe.ejb.entity.ord.CpassTFornitore;
import it.csi.cpass.cpassbe.ejb.entity.view.CpassVSettore;
import it.csi.cpass.cpassbe.ejb.exception.NotFoundException;
import it.csi.cpass.cpassbe.ejb.mapper.CpassMappers;
import it.csi.cpass.cpassbe.ejb.util.DateUtility;
import it.csi.cpass.cpassbe.ejb.util.jpa.Page;
import it.csi.cpass.cpassbe.lib.dto.ApiError;
import it.csi.cpass.cpassbe.lib.dto.Ente;
import it.csi.cpass.cpassbe.lib.dto.GestioneCampo;
import it.csi.cpass.cpassbe.lib.dto.ListinoFornitore;
import it.csi.cpass.cpassbe.lib.dto.MetadatiFunzione;
import it.csi.cpass.cpassbe.lib.dto.Ods;
import it.csi.cpass.cpassbe.lib.dto.Ufficio;
import it.csi.cpass.cpassbe.lib.dto.VSettore;
import it.csi.cpass.cpassbe.lib.dto.custom.AlberoSettoriWrapper;
import it.csi.cpass.cpassbe.lib.dto.error.MsgCpassBo;
import it.csi.cpass.cpassbe.lib.util.pagination.PagedList;
import it.csi.cpass.cpassbe.lib.util.pagination.Sort;
import it.csi.cpass.cpassbe.lib.util.threadlocal.CpassThreadLocalContainer;

/**
 * Data Access Delegate for CommonDad
 */
@ApplicationScoped
public class CommonDad extends BaseDad {

	@Inject private CpassTUfficioDao cpassTUfficioDao;
	@Inject private CpassVSettoreDao cpassVSettoreDao;
	@Inject private CpassTSettoreDao cpassTSettoreDao;
	@Inject private CpassTListinoFornitoreDao cpassTListinoFornitoreDao;
	@Inject private CpassTMetadatiFunzioneDao cpassTMetadatiFunzioneDao;
	@Inject private CpassTProgressivoDao cpassTProgressivoDao;
	@Inject private CpassDOggettiSpesaDao cpassDOggettiSpesaDao;
	@Inject private CpassTAggiornamenoStrutturaDao cpassTAggiornamenoStrutturaDao;
	@Inject private CpassTGestioneCampoDao cpassTGestioneCampoDao;
	@Inject private CpassTFornitoreDao cpassTFornitoreDao;

	/**
	 * @return the Ufficios by settore
	 */
	public List<Ufficio> getUfficiBySettore(UUID settoreId) {
		final List<UUID> listaId= new ArrayList<>();
		listaId.add(settoreId);
		return getUfficiBySettori(listaId);
	}

	/**
	 * Returns the Ufficios by settore
	 * @return the Ufficios by settore
	 */
	public List<Ufficio> getUfficiBySettori(List<UUID> settoreId) {
		final List<CpassTUfficio> cpassTUfficios = cpassTUfficioDao.getUfficiBySettore(settoreId);
		return CpassMappers.UFFICIO.toModels(cpassTUfficios);
	}

	/**
	 * Returns the Ufficios by settore
	 * @return the Ufficios by settore
	 */
	public List<Ufficio> getUfficiValidiByEnte(UUID enteId) {
		final List<CpassTUfficio> cpassTUfficios = cpassTUfficioDao.getUfficiValidiByEnte(enteId);
		return CpassMappers.UFFICIO.toModels(cpassTUfficios);
	}

	/**
	 * Returns the Ufficios by settore
	 * @return the Ufficios by settore
	 */
	public Ufficio getUfficioById(Integer uffId) {
		final Optional<CpassTUfficio> optionalTUfficios = cpassTUfficioDao.findOne(uffId);

		if (optionalTUfficios.isPresent()) {
			final CpassTUfficio cpassTUfficios = optionalTUfficios.get();
			return CpassMappers.UFFICIO.toModel(cpassTUfficios);
		} else {
			return null;
		}
	}

	/**
	 * @return the Ufficio by id
	 */
	public Optional<Ufficio> getUfficioById(String codice, UUID settoreId) {
		final Optional<CpassTUfficio> cpassTUfficio = cpassTUfficioDao.getUfficioByCodice(codice, settoreId);
		return cpassTUfficio.map(CpassMappers.UFFICIO::toModel);
	}

	/**
	 * Returns the Ufficios by enteId
	 * @return the Ufficios by enteId
	 */
	public List<Ufficio> getUfficiByEnte(UUID enteId) {
		final List<CpassTSettore> settori = cpassTSettoreDao.getSettoriByEnteId(enteId);
		final List<UUID> settoriId  = new ArrayList<>();
		for(final CpassTSettore settore :settori) {
			settoriId.add(settore.getId());
		}
		final List<CpassTUfficio> cpassTUfficios = cpassTUfficioDao.getUfficiBySettore(settoriId);
		return CpassMappers.UFFICIO.toModels(cpassTUfficios);
	}

	/**
	 * Returns the Settores Tree by Ente
	 * @return the settores Tree by Ente
	 */
	public AlberoSettoriWrapper getSettoreTreeByEnte(UUID enteId,String validi) {
		final String methodName = "getSettoreTreeByEnte";
		final List<CpassVSettore> cpassVSettores = cpassVSettoreDao.getSettoreTreeByEnte(enteId, validi);
		final int maxLevel = estraiLivelloMaxSettore(cpassVSettores) ;
		final List<VSettore> ris = associaSettoreGerarchico(cpassVSettores, maxLevel, new ArrayList<>());
		log.trace(methodName, () -> "result size: " + ris.size());
		return new AlberoSettoriWrapper(ris, maxLevel);
	}

	/**
	 * Estrazione del massimo livello tra i settori
	 * @param cpassVSettores i Settori
	 * @return il livello
	 */
	private int estraiLivelloMaxSettore(List<CpassVSettore> cpassVSettores) {
		int ris = 0;
		for(final CpassVSettore cpassSettore : cpassVSettores) {
			final int livello = cpassSettore.getLivello().intValue();
			ris = livello > ris ? livello : ris;
		}
		return ris;
	}

	/**
	 * Associazione del CPV gerarchico
	 * @param listaCpassVSettoreAll
	 * @param level
	 * @param listaSettoreLivelloFiglio
	 * @return la lista delle CPV
	 */
	private List<VSettore> associaSettoreGerarchico(List<CpassVSettore> listaCpassVSettoreAll, int level,  List<VSettore> listaSettoreLivelloFiglio) {
		final String methodName = "associaSettoreGerarchico";
		final List<VSettore> listaSettoreLivello = new ArrayList<>();
		int index = 0;
		for(final CpassVSettore cpassSettore: listaCpassVSettoreAll) {
			final int livello = cpassSettore.getLivello().intValue();
			if(livello == level) {
				final VSettore settore = CpassMappers.TREESETTORE.toModel(cpassSettore);
				listaSettoreLivello.add(settore);
				final List<VSettore> lista = settore.getListSettore(); // aggiungere attributo in classe Settore
				for(final VSettore figlio : listaSettoreLivelloFiglio) {
					if (figlio.getIdPadre().equals(settore.getId())){ // aggiungere attributo in classe Settore
						lista.add(figlio);
					}
				}
				settore.setListSettore(lista);
			}else if(livello < level) {
				log.trace(methodName, "listaSettoreLivello " + listaSettoreLivello.size());
				listaSettoreLivelloFiglio = listaSettoreLivello;
				break;
			}
			index++;
		}
		if (level > 1){
			return associaSettoreGerarchico(
					listaCpassVSettoreAll.subList(index, listaCpassVSettoreAll.size()),
					level - 1,
					listaSettoreLivelloFiglio);
		}
		log.trace(methodName, () -> "listaSettoreLivello ultimo " + listaSettoreLivello.size());
		return listaSettoreLivello;
	}

	public PagedList<ListinoFornitore> getRicercaListinoFornitore(int page, int size, Sort sort,ListinoFornitore listinoFornitore) {
		final String sortField = null;
		final String sortDirection = null;
		/*
		if (sort != null) {
			sortField = InterventoSort.byModelName(sort.getField()).getQueryName();
			sortDirection = sort.getOrder().getSortDirection();
		}
		 */
		final Page<CpassTListinoFornitore> lista = cpassTListinoFornitoreDao.findPaginated(
				listinoFornitore.getCodiceOds(),
				listinoFornitore.getDescrizione(),
				getId(listinoFornitore.getFornitore()),
				getId(listinoFornitore.getOggettiSpesa()),
				page,
				size,
				sortField,
				sortDirection);

		final PagedList<ListinoFornitore> pagedList = toPagedList(lista, page, size, CpassMappers.LISTINO_FORNITORE::toModel);
		// aggiungo gli oggetti ODS completi
		pagedList.stream().forEach(el -> {
			final Ods oggettiSpesa = CpassMappers.OGGETTO_SPESA.toModel(cpassDOggettiSpesaDao.findOne(el.getOggettiSpesa().getId()).orElse(null));
			el.setOggettiSpesa(oggettiSpesa);
		});

		return pagedList;
	}
	/**
	 * 
	 * @param listinoFornitore
	 * @return
	 */
	public ListinoFornitore saveListinoFornitore(ListinoFornitore listinoFornitore) {
		CpassTListinoFornitore cpassTListinoFornitore = CpassMappers.LISTINO_FORNITORE.toEntity(listinoFornitore);
		cpassTListinoFornitore = cpassTListinoFornitoreDao.insert(cpassTListinoFornitore);
		listinoFornitore.setId(cpassTListinoFornitore.getId());
		return listinoFornitore;
	}
	/**
	 * 
	 * @param modulo
	 * @param funzione
	 * @return
	 */
	public List<MetadatiFunzione> getMetadatiByModuoloFunzione(String modulo, String funzione) {
		final List<CpassTMetadatiFunzione> listCpassTMetadatiFunzione = cpassTMetadatiFunzioneDao.getMetadatiFunzioneByModuloFunzione(modulo,  funzione);
		final List<MetadatiFunzione> lista = CpassMappers.METADATI_FUNZIONE.toModels(listCpassTMetadatiFunzione);
		return lista;
	}
	/**
	 * 
	 * @param settoreId
	 * @param settorePadreId
	 * @return
	 */
	public Boolean isMySectorParent(UUID settoreId, UUID settorePadreId) {
		final Optional<CpassVSettore> fhather = cpassVSettoreDao.getMySectorFather(settoreId);
		if (fhather.isPresent()) {
			final UUID parenteSettoreId = fhather.get().getSettoreId();
			if(parenteSettoreId.equals(settorePadreId)) {
				return Boolean.TRUE;
			}else {
				isMySectorParent(parenteSettoreId, settorePadreId);
			}

		}
		return Boolean.FALSE;
	}

	/**
	 *
	 * @param modulo
	 * @param funzione
	 * @param tipo
	 * @param listMetadatiFunzione
	 * @return
	 */
	public String getOrdinamentoByModuloFunzioneTipo(String modulo, String funzione, String tipo,List<MetadatiFunzione> listMetadatiFunzione) {
		String result     = "";
		String separatore = "";
		String direzione  = "";
		for (final MetadatiFunzione mf:listMetadatiFunzione) {
			direzione  = " DESC ";
			if(mf.getAscendente()==null || mf.getAscendente()) {
				direzione = " ASC ";
			}
			final CpassTMetadatiFunzione cpassTMetadatiFunzione = cpassTMetadatiFunzioneDao.getMetadatiFunzioneByModuloFunzioneChiave(modulo, funzione,mf.getChiaveColonna());
			if(tipo.equals("JPQL")) {
				result += separatore + cpassTMetadatiFunzione.getJpql() + direzione ;
			}else if(tipo.equals("SQL")) {
				result += separatore + cpassTMetadatiFunzione.getStringaSql() + direzione ;
			}else {
				return result;
			}
			separatore = ", ";
		}
		return result;
	}
	/**
	 * 
	 * @param tipo
	 * @param codice
	 * @return
	 */
	public Integer getProgressivo(String tipo, String codice) {
		return getProgressivoAndSaveNew(tipo, codice,null,null);
	}
	/**
	 * 
	 * @param tipo
	 * @param codice
	 * @param parEnte
	 * @return
	 */
	public Integer getProgressivo(String tipo, String codice,Ente parEnte) {
		return getProgressivoAndSaveNew(tipo, codice, parEnte, null);
	}
	/**
	 * 
	 * @param tipo
	 * @param codice
	 * @param cpassParente
	 * @return
	 */
	public Integer getProgressivo(String tipo, String codice,CpassTEnte cpassParente) {
		return getProgressivoAndSaveNew(tipo, codice, null, cpassParente);
	}

	/**
	 *
	 * @param tipo
	 * @param codice
	 * @param parEnte
	 * @param cpassParente
	 * @return Integer
	 */
	public Integer getProgressivoAndSaveNew(String tipo, String codice, Ente parEnte, CpassTEnte cpassParente) {
		final String methodName = "getProgressivo";
		final CpassTProgressivoPk id = new CpassTProgressivoPk();
		id.setProgressivoTipo(tipo);
		id.setProgressivoCodice(codice);
		if(parEnte !=null ) {
			id.setEnteId(parEnte.getId());
		} else if(cpassParente != null) {
			id.setEnteId(cpassParente.getId());
		}else {
			final Ente ente = CpassThreadLocalContainer.SETTORE_UTENTE.get().getEnte();
			id.setEnteId(ente.getId());
		}
		final Optional<CpassTProgressivo> cpassTProgressivoPpt = cpassTProgressivoDao.findOne(id);
		CpassTProgressivo cpassTProgressivo = null;
		Integer numero = 1;
		if(cpassTProgressivoPpt.isEmpty()) {
			final CpassTProgressivo ctp = new CpassTProgressivo();
			ctp.setId(id);
			ctp.setProgressivoNumero(numero);
			cpassTProgressivo = cpassTProgressivoDao.insert(ctp);
			cpassTProgressivoDao.flushAndClear();
		}else {
			cpassTProgressivo = cpassTProgressivoPpt.orElseThrow(() -> new NotFoundException("progressivo"));
			cpassTProgressivo.setProgressivoNumero(cpassTProgressivo.getProgressivoNumero().intValue() + 1);
			cpassTProgressivo = cpassTProgressivoDao.saveAndFlush(cpassTProgressivo);
			numero = cpassTProgressivo.getProgressivoNumero();
		}
		log.trace(methodName, "Returning CpassTProgressivo for tipo = \"" + tipo + "\", codice = \"" + codice + "\": " + numero);
		return numero;
	}
	/**
	 * 
	 * @param tipo
	 * @param chiave
	 * @param ente
	 * @param progressivoNew
	 * @return
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public CpassTProgressivo setProgressivo(String tipo,String chiave, Ente ente, Integer progressivoNew) {
		final String methodName = "setProgressivo";
		final CpassTProgressivoPk id = new CpassTProgressivoPk();
		id.setProgressivoTipo(tipo);
		id.setProgressivoCodice(chiave);
		id.setEnteId(ente.getId());
		CpassTProgressivo cpassTProgressivo = cpassTProgressivoDao.findOne(id).orElseGet(() -> {
			CpassTProgressivo ctp = new CpassTProgressivo();
			ctp.setId(id);
			ctp.setProgressivoNumero(progressivoNew);
			ctp = cpassTProgressivoDao.insert(ctp);
			cpassTProgressivoDao.flush();
			return ctp;
		});

		if(cpassTProgressivo.getProgressivoNumero().intValue() < progressivoNew.intValue()) {
			cpassTProgressivo.setProgressivoNumero(progressivoNew);
			log.info(methodName, "aggiorno il progressivo con quello nuovo calcolato progressivoOld --> "+cpassTProgressivo.getProgressivoNumero() +" progressivoNew --> "+ progressivoNew);
			cpassTProgressivo = cpassTProgressivoDao.save(cpassTProgressivo);
			cpassTProgressivoDao.flush();
		}

		return cpassTProgressivo;
	}

	/**
	 * 
	 * @param fornitoreId
	 * @param oggettiSpesaId
	 * @return
	 */
	
	public Optional<ListinoFornitore> getListinoFornitore(UUID fornitoreId, Integer oggettiSpesaId ) {
		final Optional<CpassTListinoFornitore> cpassTListinoFornitore = cpassTListinoFornitoreDao.findByFornitoreOds(fornitoreId, oggettiSpesaId);
		return cpassTListinoFornitore.map(CpassMappers.LISTINO_FORNITORE::toModel);
	}
	/**
	 * 
	 * @param enteCode
	 * @return
	 */
	public List<CpassTAggiornamentoStruttura> getAggiornamentoStrutture(String enteCode) {
		final List<CpassTAggiornamentoStruttura> lista = cpassTAggiornamenoStrutturaDao.findByEnte(enteCode);
		return lista;
	}
	/**
	 * 
	 * @param as
	 * @return
	 */
	public CpassTAggiornamentoStruttura saveAggiornamentoStruttura(CpassTAggiornamentoStruttura as) {
		return cpassTAggiornamenoStrutturaDao.save(as);
	}
	/**
	 * 
	 * @param enteId
	 * @return
	 */
	public List<GestioneCampo> getGestioneCampi(UUID enteId) {
		final List<CpassTGestioneCampo> entity = cpassTGestioneCampoDao.findByEnte(enteId);
		final List<GestioneCampo> lista = CpassMappers.GESTIONE_CAMPO.toModels(entity);
		return lista;
	}

	/**
	 *
	 * @param ods
	 * @param ente
	 * @return
	 */
	public Ods postOggettoSpesa( Ods ods,Ente ente) {
		CpassDOggettiSpesa cpassDOggettiSpesa = CpassMappers.OGGETTO_SPESA.toEntity(ods);
		//cpassDOggettiSpesa.setCpassTEnte(CpassMappers.ENTE.toEntity(ente));
		cpassDOggettiSpesa = cpassDOggettiSpesaDao.insert(cpassDOggettiSpesa);
		cpassDOggettiSpesaDao.flush();
		//listino fornitore
		if(ods.getListinoFornitores()!=null) {
			for(final ListinoFornitore lf : ods.getListinoFornitores()) {
				CpassTListinoFornitore entity = CpassMappers.LISTINO_FORNITORE.toEntity(lf);
				entity.setCpassDOggettiSpesa(cpassDOggettiSpesa);
				final CpassTFornitore cpassTFornitoreNew = getFornitore(entity,ente);
				entity.setCpassTFornitore(cpassTFornitoreNew);
				entity = cpassTListinoFornitoreDao.insert(entity);
			}
		}
		//N.B non cancellare attualmente non usato probabile evoluzione futura
		/*
		if(ods.getOdsDatiContabilis()!=null) {
			for( OdsDatiContabili dc : ods.getOdsDatiContabilis()) {
				CpassROdsDatiContabili entity = CpassMappers.ODS_DATI_CONTABILI.toEntity(dc);
				entity.setCpassDOggettiSpesa(cpassDOggettiSpesa);
				entity = cpassROdsDatiContabiliDao.insert(entity);
			}
		}
		 */
		return CpassMappers.OGGETTO_SPESA.toModel(cpassDOggettiSpesa);
	}
	/**
	 *
	 * @param odsNew
	 * @param utenteConnesso
	 * @param ente
	 * @return
	 */
	public Ods putOggettoSpesa( Ods odsNew,String utenteConnesso,Ente ente) {
		final Optional<CpassDOggettiSpesa> cpassDOggettiSpesaOld = cpassDOggettiSpesaDao.findById(odsNew.getId());
		if(cpassDOggettiSpesaOld.isPresent()) {
			CpassMappers.OGGETTO_SPESA.toModel(cpassDOggettiSpesaOld.get());
		}
		odsNew.setDataValiditaFine(DateUtility.getDateWithoutTime(odsNew.getDataValiditaFine()));
		odsNew.setEnte(ente);
		CpassDOggettiSpesa cpassDOggettiSpesaNew = CpassMappers.OGGETTO_SPESA.toEntity(odsNew);
		cpassDOggettiSpesaNew = cpassDOggettiSpesaDao.update(cpassDOggettiSpesaNew,false);
		cpassDOggettiSpesaDao.flush();
		//gestione lisino fornitore

		final List<ListinoFornitore> listinoOld = odsNew.getListinoFornitores().stream().filter(el -> el.getDataCancellazione() == null ).collect(Collectors.toList());

		if(listinoOld !=null) {
			final Map<Integer, ListinoFornitore> listaListinoFornitoreOldMap = listToMap(listinoOld);
			inserisciModificaListiniFornitori(odsNew.getListinoFornitores(),listaListinoFornitoreOldMap, utenteConnesso,cpassDOggettiSpesaNew,ente);
		}
		return CpassMappers.OGGETTO_SPESA.toModel(cpassDOggettiSpesaNew);
	}
	/**
	 *
	 * @param odsNew
	 * @param utenteConnesso
	 * @param ente
	 * @return
	 */
	public Ods putOggettoSpesaMassiva( Ods odsNew,String utenteConnesso,Ente ente) {
		final Optional<CpassDOggettiSpesa> cpassDOggettiSpesaOld = cpassDOggettiSpesaDao.findById(odsNew.getId());
		if(cpassDOggettiSpesaOld.isPresent()) {
			CpassMappers.OGGETTO_SPESA.toModel(cpassDOggettiSpesaOld.get());
		}
		odsNew.setDataValiditaFine(DateUtility.getDateWithoutTime(odsNew.getDataValiditaFine()));
		odsNew.setEnte(ente);
		CpassDOggettiSpesa cpassDOggettiSpesaNew = CpassMappers.OGGETTO_SPESA.toEntity(odsNew);
		cpassDOggettiSpesaNew = cpassDOggettiSpesaDao.update(cpassDOggettiSpesaNew,false);
		cpassDOggettiSpesaDao.flush();
		//gestione lisino fornitore
		/*
		List<ListinoFornitore> listinoOld = odsNew.getListinoFornitores().stream().filter(el -> el.getDataCancellazione() == null ).collect(Collectors.toList());

		if(listinoOld !=null) {
			Map<Integer, ListinoFornitore> listaListinoFornitoreOldMap = listToMap(listinoOld);
			inserisciModificaListiniFornitori(odsNew.getListinoFornitores(),listaListinoFornitoreOldMap, utenteConnesso,cpassDOggettiSpesaNew,ente);
		}
		 */
		//inserisciModificaListiniFornitoriMassiva(odsNew.getListinoFornitores(), utenteConnesso,cpassDOggettiSpesaNew,ente);

		//tirarsi il codce listino se c'è non lo inseisco
		if(odsNew!=null && odsNew.getListinoFornitores()!=null) {
			for( final ListinoFornitore lfNew: odsNew.getListinoFornitores()) {

				final Page<CpassTListinoFornitore> listinoDB = cpassTListinoFornitoreDao.findPaginated(lfNew.getCodiceOds(),null,lfNew.getFornitore().getId(),null,1,10, null, null);
				if(listinoDB.getTotalElements()==0) {
					final CpassTListinoFornitore entity = CpassMappers.LISTINO_FORNITORE.toEntity(lfNew);
					entity.setCpassDOggettiSpesa(cpassDOggettiSpesaNew);
					final CpassTFornitore cpassTFornitoreNew = getFornitore(entity,ente);
					entity.setCpassTFornitore(cpassTFornitoreNew);
					cpassTListinoFornitoreDao.insert(entity);
				}
			}
		}
		return CpassMappers.OGGETTO_SPESA.toModel(cpassDOggettiSpesaNew);
	}
	/**
	 *
	 * @param listinoFornitores
	 * @param listaListinoFornitoreOldMap
	 * @param utenteConnesso
	 * @param cpassDOggettiSpesaNew
	 * @param ente
	 */
	private void inserisciModificaListiniFornitori(List<ListinoFornitore> listinoFornitores,Map<Integer, ListinoFornitore> listaListinoFornitoreOldMap,String utenteConnesso,CpassDOggettiSpesa cpassDOggettiSpesaNew,Ente ente) {
		for( final ListinoFornitore lfNew: listinoFornitores) {
			if(lfNew.getId()==null) {
				final CpassTListinoFornitore entity = CpassMappers.LISTINO_FORNITORE.toEntity(lfNew);
				entity.setCpassDOggettiSpesa(cpassDOggettiSpesaNew);
				final CpassTFornitore cpassTFornitoreNew = getFornitore(entity,ente);
				entity.setCpassTFornitore(cpassTFornitoreNew);
				cpassTListinoFornitoreDao.insert(entity);
			}else {
				listaListinoFornitoreOldMap.remove(lfNew.getId());
			}
		}
		for( final ListinoFornitore lfDaCancellare : listaListinoFornitoreOldMap.values()) {
			final CpassTListinoFornitore entity = CpassMappers.LISTINO_FORNITORE.toEntity(lfDaCancellare);
			entity.setCpassDOggettiSpesa(cpassDOggettiSpesaNew);
			entity.setDataCancellazione(new Date());
			entity.setUtenteCancellazione(utenteConnesso);
			cpassTListinoFornitoreDao.update(entity);
		}
	}
	/**
	 *
	 * @param listinoFornitores
	 * @param utenteConnesso
	 * @param cpassDOggettiSpesaNew
	 * @param ente
	 */
	/* N.B. non buttare
	private void inserisciModificaListiniFornitoriMassiva(List<ListinoFornitore> listinoFornitores, String utenteConnesso,CpassDOggettiSpesa cpassDOggettiSpesaNew,Ente ente) {
		for( ListinoFornitore lfNew: listinoFornitores) {
			CpassTListinoFornitore entity = CpassMappers.LISTINO_FORNITORE.toEntity(lfNew);
			entity.setCpassDOggettiSpesa(cpassDOggettiSpesaNew);
			CpassTFornitore cpassTFornitoreNew = getFornitore(entity,ente);
			entity.setCpassTFornitore(cpassTFornitoreNew);
			cpassTListinoFornitoreDao.insert(entity);
		}
	}
	 */
	/**
	 * 
	 * @param entity
	 * @param ente
	 * @return
	 */
	 
	protected CpassTFornitore getFornitore(CpassTListinoFornitore entity,Ente ente) {
		// fornitore
		CpassTFornitore cpassTFornitoreNew = entity.getCpassTFornitore();
		cpassTFornitoreNew.setCpassTEnte(CpassMappers.ENTE.toEntity(ente));
		if (cpassTFornitoreNew.getNaturaGiuridica() != null && cpassTFornitoreNew.getNaturaGiuridica().length() > 5) {
			cpassTFornitoreNew.setNaturaGiuridica(cpassTFornitoreNew.getNaturaGiuridica().substring(0, 5));
		}

		final CpassTFornitore cpassTFornitoreOld = cpassTFornitoreDao.getFornitoreByCodice(cpassTFornitoreNew.getCodice(), getId(ente));
		if (cpassTFornitoreOld == null) {
			cpassTFornitoreNew = cpassTFornitoreDao.insert(cpassTFornitoreNew);
		} else {
			cpassTFornitoreNew.setId(cpassTFornitoreOld.getId());
			cpassTFornitoreNew = cpassTFornitoreDao.update(cpassTFornitoreNew, false);
		}
		cpassTFornitoreDao.flush();
		return cpassTFornitoreNew;
	}
	/**
	 *
	 * @param idOds
	 * @return
	 */
	public ApiError controlloDelOggettoSpesa( Integer idOds) {
		final CpassDOggettiSpesa ods = cpassDOggettiSpesaDao.findById(idOds).orElseThrow(() -> new NotFoundException("oggetto spesa"));
		if(
				//!ods.getCpassTListinoFornitores().isEmpty() &&
				ods.getCpassROdsDatiContabilis().isEmpty() &&
				ods.getCpassTOrdRigaEvasiones().isEmpty()  &&
				ods.getCpassTOrdRigaOrdines().isEmpty()    &&
				ods.getCpassTOrdRigaRdas().isEmpty()       &&
				ods.getCpassTRmsRigaRms().isEmpty()
				) {
			return MsgCpassBo.BACODSA0007.getError();//fisica
		}else {
			return MsgCpassBo.BACODSA0008.getError();//logica
		}
	}

	/**
	 *
	 * @param idOds
	 * @param utenteCancellazione
	 */
	public void ddelOggettoSpesa(Integer idOds, String utenteCancellazione) {
		final Optional<CpassDOggettiSpesa> optionalOds = cpassDOggettiSpesaDao.findById(idOds);
		if (optionalOds.isPresent()) {
			final CpassDOggettiSpesa ods = optionalOds.orElseThrow(() -> new NotFoundException("ods"));
			if (ods.getCpassROdsDatiContabilis().isEmpty() &&
					ods.getCpassTOrdRigaEvasiones().isEmpty() &&
					ods.getCpassTOrdRigaOrdines().isEmpty() &&
					ods.getCpassTOrdRigaRdas().isEmpty() &&
					ods.getCpassTRmsRigaRms().isEmpty()) {
				log.info("delOggettoSpesa", "Cancellazione fisica ODS");
				cpassTListinoFornitoreDao.deleteByOds(idOds);
				cpassTListinoFornitoreDao.flush();
				cpassDOggettiSpesaDao.delete(idOds);
			} else {
				final Optional<CpassDOggettiSpesa> optionalOdsToDelete = cpassDOggettiSpesaDao.findById(idOds);
				if (optionalOdsToDelete.isPresent()) {
					final CpassDOggettiSpesa cpassDOggettiSpesaDaCancellare = optionalOdsToDelete.orElseThrow(() -> new NotFoundException("ods"));
					cpassDOggettiSpesaDaCancellare.setDataValiditaFine(new Date());
					cpassDOggettiSpesaDaCancellare.setUtenteModifica(utenteCancellazione);
					cpassDOggettiSpesaDao.update(cpassDOggettiSpesaDaCancellare);
					cpassDOggettiSpesaDao.flush();
					cpassDOggettiSpesaDao.deleteLogically(idOds);
				}
			}
		}
	}
	/**
	 *
	 * @param idOds
	 * @param utenteCancellazione
	 */
	public void delOggettoSpesa(Integer idOds, String utenteCancellazione) {
		final Optional<CpassDOggettiSpesa> odsOptional = cpassDOggettiSpesaDao.findById(idOds);
		if (odsOptional.isPresent()) {
			final CpassDOggettiSpesa ods = odsOptional.orElseThrow(() -> new NotFoundException("ods"));
			if(
					ods.getCpassROdsDatiContabilis().isEmpty() &&
					ods.getCpassTOrdRigaEvasiones().isEmpty()  &&
					ods.getCpassTOrdRigaOrdines().isEmpty()    &&
					ods.getCpassTOrdRigaRdas().isEmpty()       &&
					ods.getCpassTRmsRigaRms().isEmpty()
					) {
				log.info("delOggettoSpesa", "Cancellazione fisica ODS");
				cpassTListinoFornitoreDao.deleteByOds(idOds);
				cpassTListinoFornitoreDao.flush();
				cpassDOggettiSpesaDao.delete(idOds);
			}else {
				final CpassDOggettiSpesa cpassDOggettiSpesaDaCancellare = odsOptional.orElseThrow(() -> new NotFoundException("ods"));
				cpassDOggettiSpesaDaCancellare.setDataValiditaFine(new Date());
				cpassDOggettiSpesaDaCancellare.setUtenteModifica(utenteCancellazione);
				cpassDOggettiSpesaDao.update(cpassDOggettiSpesaDaCancellare);
				cpassDOggettiSpesaDao.flush();
				cpassDOggettiSpesaDao.deleteLogically(idOds);
			}
		} else {

		}
	}
	/**
	 *
	 * @param page
	 * @param size
	 * @param sort
	 * @param oggettiSpesa
	 * @return
	 */
	public PagedList<Ods> getRicercaOggettiSpesa(int page, int size, Sort sort, Ods oggettiSpesa) {
		String sortField = null;
		String sortDirection = null;
		if (sort != null) {
			sortField = OdsSort.byModelName(sort.getField()).getQueryName();
			sortDirection = sort.getOrder().getSortDirection();
		}
		final Ente ente = CpassThreadLocalContainer.SETTORE_UTENTE.get().getEnte();

		final Page<CpassDOggettiSpesa> cpassDOggettiSpesas = cpassDOggettiSpesaDao.findPaginated(
				oggettiSpesa.getInventariabile(),
				oggettiSpesa.getCodice(),
				oggettiSpesa.getDescrizione(),
				getId(oggettiSpesa.getAliquoteIva()),
				getId(oggettiSpesa.getCpv()),
				oggettiSpesa.getCpv() != null ? oggettiSpesa.getCpv().getCodice() : null,
						getId(oggettiSpesa.getUnitaMisura()),
						oggettiSpesa.getGenerico(),
						oggettiSpesa.getValido(),
						getId(ente),
						page,
						size,
						sortField,
						sortDirection);

		final PagedList<Ods> pagedList = toPagedList(cpassDOggettiSpesas, page, size, CpassMappers.OGGETTO_SPESA::toModel);
		return pagedList;
	}
	/**
	 *
	 * @param id
	 * @return
	 */
	public Ods getOgettiSpesaById(Integer id) {
		final Optional<CpassDOggettiSpesa> cpassDOggettiSpesa = cpassDOggettiSpesaDao.findById(id);
		if (cpassDOggettiSpesa.isPresent()) {
			return CpassMappers.OGGETTO_SPESA.toModel(cpassDOggettiSpesa.get());
		} else {
			return null;
		}
	}
}
