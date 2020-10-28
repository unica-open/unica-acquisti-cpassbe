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
import java.util.UUID;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.CpassTElaborazioneDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.CpassTFornitoreDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.CpassTListinoFornitoreDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.CpassTMetadatiFunzioneDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.CpassTUfficioDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.CpassVSettoreDao;
import it.csi.cpass.cpassbe.ejb.entity.CpassTElaborazione;
import it.csi.cpass.cpassbe.ejb.entity.CpassTListinoFornitore;
import it.csi.cpass.cpassbe.ejb.entity.CpassTMetadatiFunzione;
import it.csi.cpass.cpassbe.ejb.entity.CpassTUfficio;
import it.csi.cpass.cpassbe.ejb.entity.ord.CpassTFornitore;
import it.csi.cpass.cpassbe.ejb.entity.view.CpassVSettore;
import it.csi.cpass.cpassbe.ejb.mapper.CpassMappers;
import it.csi.cpass.cpassbe.ejb.util.jpa.Page;
import it.csi.cpass.cpassbe.lib.dto.Elaborazione;
import it.csi.cpass.cpassbe.lib.dto.Fornitore;
import it.csi.cpass.cpassbe.lib.dto.ListinoFornitore;
import it.csi.cpass.cpassbe.lib.dto.MetadatiFunzione;
import it.csi.cpass.cpassbe.lib.dto.Settore;
import it.csi.cpass.cpassbe.lib.dto.Ufficio;
import it.csi.cpass.cpassbe.lib.util.pagination.PagedList;
import it.csi.cpass.cpassbe.lib.util.pagination.Sort;

/**
 * Data Access Delegate for decodificas
 */
@ApplicationScoped
public class CommonDad extends BaseDad {

	@Inject private CpassTUfficioDao cpassTUfficioDao;
	@Inject private CpassVSettoreDao cpassVSettoreDao;
	@Inject private CpassTListinoFornitoreDao cpassTListinoFornitoreDao;
	@Inject private CpassTFornitoreDao cpassTFornitoreDao;
	@Inject private CpassTElaborazioneDao cpassTElaborazioneDao;
	@Inject private CpassTMetadatiFunzioneDao cpassTMetadatiFunzioneDao;

	/**
	 * Returns the Ufficios by settore
	 * @return the Ufficios by settore
	 */
	public List<Ufficio> getUfficiBySettore(UUID settoreId) {
		List<CpassTUfficio> cpassTUfficios = cpassTUfficioDao.getUfficiBySettore(settoreId);
		return CpassMappers.UFFICIO.toModels(cpassTUfficios);
	}
	
	/**
	 * Returns the Settores Tree by Ente
	 * @return the settores Tree by Ente
	 */
	public List<Settore> getSettoreTreeByEnte(UUID enteId) {
		String methodName = "getSettoreTreeByEnte";
		List<CpassVSettore> cpassVSettores = cpassVSettoreDao.getSettoreTreeByEnte(enteId);
		int level = estraiLivelloMaxSettore(cpassVSettores) ;
		List<Settore> ris = associaSettoreGerarchico(cpassVSettores, level, new ArrayList<>());
		log.trace(methodName, () -> "result size: " + ris.size());
		return ris;
	}
	
	/**
	 * Estrazione del massimo livello tra i settori
	 * @param CpassVSettore i Settori
	 * @return il livello
	 */
	private int estraiLivelloMaxSettore(List<CpassVSettore> cpassVSettores) {
		int ris = 0;
		for(CpassVSettore cpassSettore : cpassVSettores) {
			int livello = cpassSettore.getLivello().intValue();
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
	private List<Settore> associaSettoreGerarchico(List<CpassVSettore> listaCpassVSettoreAll, int level,  List<Settore> listaSettoreLivelloFiglio) {
		String methodName = "associaSettoreGerarchico";
		List<Settore> listaSettoreLivello = new ArrayList<>();
		int index = 0;
		for(CpassVSettore cpassSettore: listaCpassVSettoreAll) {
			int livello = cpassSettore.getLivello().intValue();
			if(livello == level) {
				Settore settore = CpassMappers.TREESETTORE.toModel(cpassSettore);
				listaSettoreLivello.add(settore);
				List<Settore> lista = settore.getListSettore(); // aggiungere attributo in classe Settore
				for(Settore figlio : listaSettoreLivelloFiglio) {
					if (figlio.getIdPadre().equals(settore.getId())){ // aggiungere attributo in classe Settore
						lista.add(figlio);
					}
				}
				settore.setListSettore(lista);
			}else if(livello < level) {
				log.trace(methodName, () -> "listaSettoreLivello " + listaSettoreLivello.size());
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
		String sortField = null;
		String sortDirection = null;
		/*
		if (sort != null) {
			sortField = InterventoSort.byModelName(sort.getField()).getQueryName();
			sortDirection = sort.getOrder().getSortDirection();
		}
		*/
		Page<CpassTListinoFornitore> lista = cpassTListinoFornitoreDao.findPaginated(
				listinoFornitore.getCodiceOds(),
				listinoFornitore.getDescrizione(),
				getId(listinoFornitore.getFornitore()),
				getId(listinoFornitore.getOggettiSpesa()),
				page,
				size,
				sortField,
				sortDirection);
				
		PagedList<ListinoFornitore> pagedList = toPagedList(lista, page, size, CpassMappers.LISTINO_FORNITORE::toModel);
		return pagedList;
	}

	
	public ListinoFornitore saveListinoFornitore(ListinoFornitore listinoFornitore) {
		CpassTListinoFornitore cpassTListinoFornitore = CpassMappers.LISTINO_FORNITORE.toEntity(listinoFornitore);
		cpassTListinoFornitore = cpassTListinoFornitoreDao.insert(cpassTListinoFornitore);
		listinoFornitore.setId(cpassTListinoFornitore.getId());
		return listinoFornitore;
	}
	
	public List<Fornitore> postRicercaFornitoreInterno(Fornitore fornitore) {
		String methodName = "postRicercaFornitoreInterno";
		List<CpassTFornitore> cpassTFornitores = cpassTFornitoreDao.getFornitore(
				fornitore.getCodice(), 
				fornitore.getCodiceFiscale(), 
				fornitore.getPartitaIva(), 
				fornitore.getRagioneSociale());
		
		return CpassMappers.FORNITORE.toModels(cpassTFornitores);
	}

	public List<Elaborazione> postRicercaElaborazione(Elaborazione elaborazione, String elaborazioneTipoCodice) {
		String methodName = "postRicercaElaborazione";
		List<CpassTElaborazione> cpassTElaboraziones = cpassTElaborazioneDao.getElaborazione(elaborazione.getEntitaId(), elaborazione.getStato(),elaborazioneTipoCodice);
		return CpassMappers.ELABORAZIONE.toModels(cpassTElaboraziones);
	}
	
	public Fornitore getRicercaFornitoreInterno(UUID fornitoreId) {
		String methodName = "getRicercaFornitoreInterno";
		CpassTFornitore cpassTFornitore = cpassTFornitoreDao.findOne(fornitoreId).orElse(new CpassTFornitore());		
		return CpassMappers.FORNITORE.toModel(cpassTFornitore);
	}

	public List<MetadatiFunzione> getMetadatiByModuoloFunzione(String modulo, String funzione) {
		List<CpassTMetadatiFunzione> listCpassTMetadatiFunzione = cpassTMetadatiFunzioneDao.getMetadatiFunzioneByModuloFunzione(modulo,  funzione);
		List<MetadatiFunzione> lista = CpassMappers.METADATI_FUNZIONE.toModels(listCpassTMetadatiFunzione);		
		return lista;
	}	

	// da capire perchè getId corrisponde a getId e non invece a vsettore_id
	public Boolean isMySectorParent(UUID settoreId, UUID settorePadreId) {
		 Optional<CpassVSettore> fhather = cpassVSettoreDao.getMySectorFather(settoreId);
		 if (fhather.isPresent()) {			 
			 UUID parenteSettoreId = fhather.get().getSettoreId();
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
		for (MetadatiFunzione mf:listMetadatiFunzione) {
			direzione  = " DESC ";
			if(mf.getAscendente()==null || mf.getAscendente()) {
				direzione = " ASC ";
			}			
			CpassTMetadatiFunzione cpassTMetadatiFunzione = cpassTMetadatiFunzioneDao.getMetadatiFunzioneByModuloFunzioneChiave(modulo, funzione,mf.getChiaveColonna());
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

	
}


