/*-
 * ========================LICENSE_START=================================
 * CPASS BackEnd - EJB submodule
 * %%
 * Copyright (C) 2019 - 2025 CSI Piemonte
 * %%
 * SPDX-FileCopyrightText: Copyright 2019 - 2025 | CSI Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 * =========================LICENSE_END==================================
 */
package it.csi.cpass.cpassbe.ejb.business.be.dad;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.CpassTFornitoreDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.CpassTImpegnoDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.CpassTSubimpegnoDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.decodifiche.CpassDStatoDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.ord.CpassTOrdImpegnoOrdineDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.ord.CpassTOrdSubimpegnoOrdineDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.ord.CpassVOrdineConsultazioneDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.ord.CpassVOrdineDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.ord.CpassVOrdineEvasioneDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.ord.evasione.CpassTOrdImpegnoEvasioneDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.ord.evasione.CpassTOrdSubimpegnoEvasioneDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.ord.evasione.CpassVEvasioneDao;
import it.csi.cpass.cpassbe.ejb.entity.CpassTImpegno;
import it.csi.cpass.cpassbe.ejb.entity.CpassTSubimpegno;
import it.csi.cpass.cpassbe.ejb.entity.ord.CpassVOrdineEvasione;
import it.csi.cpass.cpassbe.ejb.entity.view.CpassVEvasione;
import it.csi.cpass.cpassbe.ejb.exception.NotFoundException;
import it.csi.cpass.cpassbe.ejb.util.ConstantsCPassStato.StatoEvasioneEnum;
import it.csi.cpass.cpassbe.ejb.util.ConstantsCPassStato.TipoStatoEnum;
import it.csi.cpass.cpassbe.lib.dto.Ente;
import it.csi.cpass.cpassbe.lib.dto.Impegno;
import it.csi.cpass.cpassbe.lib.dto.Subimpegno;
import it.csi.cpass.cpassbe.lib.dto.ord.consultazioni.ConsultazioniEvasione;
import it.csi.cpass.cpassbe.lib.dto.ord.consultazioni.ConsultazioniImpegno;
import it.csi.cpass.cpassbe.lib.dto.ord.consultazioni.ConsultazioniOrdine;
import it.csi.cpass.cpassbe.lib.dto.ord.consultazioni.ConsultazioniRipartizioneEvasione;
import it.csi.cpass.cpassbe.lib.dto.ord.consultazioni.RicercaXConsultazioni;
import it.csi.cpass.cpassbe.lib.util.threadlocal.CpassThreadLocalContainer;


 /**
  * Data Access Delegate for ConsultazioniDad
  */
 @ApplicationScoped
 public class ConsultazioniDad extends BaseDad {

	 private static final String STATO = "stato";
	 @Inject
	 private CpassTImpegnoDao cpassTImpegnoDao;
	 @Inject
	 private CpassTSubimpegnoDao cpassTsubimpegnoDao;
	 @Inject
	 private CpassTOrdSubimpegnoOrdineDao cpassTOrdSubimpegnoOrdineDao;
	 @Inject
	 private CpassTOrdImpegnoOrdineDao cpassTOrdImpegnoOrdineDao;
	 @Inject
	 private CpassTOrdSubimpegnoEvasioneDao cpassTOrdSubimpegnoEvasioneDao;
	 @Inject
	 private CpassTOrdImpegnoEvasioneDao cpassTOrdImpegnoEvasioneDao;
	 @Inject
	 private CpassDStatoDao cpassDStatoDao;
	 @Inject
	 private CpassVOrdineConsultazioneDao cpassVOrdineConsultazioneDao;
	 @Inject
	 private CpassVOrdineDao cpassVOrdineDao;
	 @Inject
	 private CpassVEvasioneDao cpassVEvasioneDao;
	 @Inject
	 private CpassVOrdineEvasioneDao cpassVOrdineEvasioneDao;
	 @Inject
	 private CpassTFornitoreDao cpassTFornitoreDao;


	 /**
	  * 
	  * @param listaImpegniAll
	  * @param enteId
	  * @param filtroAnnoEsercizio
	  * @return
	  */
	 public List<ConsultazioniImpegno> valorizzaConsultazioniImpegno(List<Impegno> listaImpegniAll,UUID enteId,Integer filtroAnnoEsercizio) {
		 final List<ConsultazioniImpegno> listaConsultazioniImpegno = new ArrayList<>();
		 final Integer statoInContId = cpassDStatoDao.findByCodiceTipo(StatoEvasioneEnum.IN_CONTABILITA.getCostante(), TipoStatoEnum.EVASIONE.getCostante()).orElseThrow(() -> new NotFoundException(STATO)).getId();

		 for(final Impegno impegno:listaImpegniAll) {
			 if(impegno.getSubimpegni()==null || impegno.getSubimpegni().size()==0) {
				 ConsultazioniImpegno consultazioniImpegno = inizializzaConsultazioniImpegnoXImp(enteId, impegno, filtroAnnoEsercizio,statoInContId);
				 consultazioniImpegno =arricchisciDiOrdini(consultazioniImpegno, enteId);
				 listaConsultazioniImpegno.add(consultazioniImpegno );
			 }else {
				 for(final Subimpegno sub : impegno.getSubimpegni()) {
					 ConsultazioniImpegno consultazioniImpegno = initzializzaConsultazioniImpegnoXSubImp(enteId, impegno, sub,filtroAnnoEsercizio,statoInContId);
					 consultazioniImpegno =arricchisciDiOrdini(consultazioniImpegno, enteId);
					 listaConsultazioniImpegno.add(consultazioniImpegno);
				 }
			 }
		 }
		 return listaConsultazioniImpegno;
	 }
	 /**
	  * 
	  * @param listaConsultazioniImpegno
	  * @param enteId
	  * @return
	  */
	 private ConsultazioniImpegno arricchisciDiOrdini(ConsultazioniImpegno listaConsultazioniImpegno,UUID enteId) {
		 final ConsultazioniImpegno ci =listaConsultazioniImpegno;
		 List<ConsultazioniOrdine> listaConsultazioniOrdine = getConsultazioniOrdineByImpegno(ci,enteId);
		 listaConsultazioniOrdine = arricchisciDiEvasioni(listaConsultazioniOrdine);
		 ci.setListaConsultazioniOrdine(listaConsultazioniOrdine);
		 return ci;
	 }
	 /**
	  * 
	  * @param listaConsultazioniOrdine
	  * @return
	  */
	 private List<ConsultazioniOrdine> arricchisciDiEvasioni(List<ConsultazioniOrdine> listaConsultazioniOrdine) {
		 final Integer statoInContId = cpassDStatoDao.findByCodiceTipo(StatoEvasioneEnum.IN_CONTABILITA.getCostante(), TipoStatoEnum.EVASIONE.getCostante()).orElseThrow(() -> new NotFoundException(STATO)).getId();
		 final Ente ente = CpassThreadLocalContainer.SETTORE_UTENTE.get().getEnte();
		 for(final ConsultazioniOrdine co : listaConsultazioniOrdine) {
			 final List<CpassVOrdineEvasione> listaEvasione = cpassVOrdineEvasioneDao.getListEvasioneByOrdineId(UUID.fromString(co.getId()));
			 List<ConsultazioniEvasione> listaConsultazioniEvasione = new ArrayList<>();
			 for(final CpassVOrdineEvasione elemento: listaEvasione) {
				 final ConsultazioniEvasione ce = new ConsultazioniEvasione();
				 ce.setAnnoEvasione(elemento.getEvasioneAnno());
				 ce.setNumeroEvasione(elemento.getEvasioneNumero());
				 ce.setStatoDesc(elemento.getEvasioneStatoDescrizione());
				 ce.setStatoCode(elemento.getEvasioneStatoCodice());
				 ce.setEvaso(elemento.getEvasioneTotaleConIva());
				 //calcolo basandomi sui sub se non trovo calcolo su imp
				 BigDecimal ripSuEva = cpassVEvasioneDao.getRipartitoSuEvasioneById(UUID. fromString(elemento.getTestataEvasioneId()), null, Boolean.TRUE);
				 if(ripSuEva.compareTo(BigDecimal.ZERO) == 0) {
					 ripSuEva = cpassVEvasioneDao.getRipartitoSuEvasioneById(UUID. fromString(elemento.getTestataEvasioneId()), null, Boolean.FALSE);
				 }
				 ce.setRipartitoSuImpegni(ripSuEva);

				 //calcolo basandomi sui sub se non trovo calcolo su imp
				 BigDecimal ripInCont = cpassVEvasioneDao.getRipartitoSuEvasioneById(UUID. fromString(elemento.getTestataEvasioneId()), statoInContId, Boolean.TRUE);
				 if(ripInCont.compareTo(BigDecimal.ZERO) == 0) {
					 ripInCont = cpassVEvasioneDao.getRipartitoSuEvasioneById(UUID. fromString(elemento.getTestataEvasioneId()), statoInContId, Boolean.FALSE);
				 }
				 ce.setInContabilita(ripInCont);
				 ce.setFornitoreDesc(elemento.getEvasioneFornitoreRagioneSociale());
				 ce.setDocumentoFatturaTipo(elemento.getFatturaTipo());
				 ce.setDocumentoFatturaAnno(elemento.getFatturaAnno() != null ? ""+ elemento.getFatturaAnno(): "");
				 ce.setDocumentoFatturaNumero(elemento.getFatturaNumero()!= null ? ""+ elemento.getFatturaNumero(): "");
				 //Fornitore non dell'evasione ma della fattura
				 //ce.setFornitoreRagioneSociale(elemento.getEvasioneFornitoreRagioneSociale());
				 //ce.setFornitoreCodice(elemento.getEvasioneFornitoreCodice());
				 ce.setFornitoreCodice(elemento.getFatturaCodice());
				 if(elemento.getFatturaCodice()!=null) {
					 final String ragSocFornitore = cpassTFornitoreDao.getFornitoreByCodice(elemento.getFatturaCodice(),getId(ente)).getRagioneSociale();
					 ce.setFornitoreRagioneSociale(ragSocFornitore);
				 }
				 ce.setId(UUID.fromString(elemento.getTestataEvasioneId()));
				 listaConsultazioniEvasione.add(ce);
			 }
			 listaConsultazioniEvasione = arricchisciDiRipartizione(listaConsultazioniEvasione);
			 co.setListaConsultazioniEvasione(listaConsultazioniEvasione );
		 }
		 return listaConsultazioniOrdine;
	 }

	 /**
	  * 
	  * @param listaConsultazioniEvasione
	  * @return
	  */
	 private List<ConsultazioniEvasione> arricchisciDiRipartizione(List<ConsultazioniEvasione> listaConsultazioniEvasione) {

		 for(final ConsultazioniEvasione ce :listaConsultazioniEvasione) {
			 final List<ConsultazioniRipartizioneEvasione> listaConsultazioniRipartizioneEvasione = new ArrayList<>();

			 final List<CpassVEvasione> lista = cpassVEvasioneDao.getListaSubImpegniByEvasioneId(ce.getId());

			 for(final CpassVEvasione evasione : lista) {
				 final ConsultazioniRipartizioneEvasione cre = new ConsultazioniRipartizioneEvasione();
				 if(evasione.getImpegnoAnno()==null) {
					 continue;
				 }
				 cre.setAnnoEsercizio(evasione.getImpegnoAnnoEsercizio());
				 cre.setAnnoImpegno(evasione.getImpegnoAnno());
				 cre.setNumeroImpegno(evasione.getImpegnoNumero());
				 cre.setAnnoSubImpegno(evasione.getSubimpegnoAnno());
				 cre.setNumeroSubImpegno(evasione.getSubimpegnoNumero());
				 if(evasione.getSubimpegnoAnno()!=null) {
					 cre.setRipartito(evasione.getSubImportoRipartito());
					 cre.setSospeso(evasione.getSubImportoSospeso());
				 }else {
					 cre.setRipartito(evasione.getImportoRipartito());
					 cre.setSospeso(evasione.getImportoSospeso());
				 }
				 cre.setDataSospensione(evasione.getDataSospensione());
				 cre.setCausaleSospensione(evasione.getCausaleSospensioneDescrizione());
				 listaConsultazioniRipartizioneEvasione.add(cre);
			 }
			 ce.setListaConsultazioniRipartizioneEvasione(listaConsultazioniRipartizioneEvasione);
		 }
		 return listaConsultazioniEvasione;
	 }

	 /**
	  * 
	  * @param ci
	  * @param enteId
	  * @return
	  */
	 private List<ConsultazioniOrdine> getConsultazioniOrdineByImpegno(ConsultazioniImpegno ci,UUID enteId) {
		 final List<ConsultazioniOrdine> list = cpassVOrdineConsultazioneDao.getListOrdineByImpegni(ci.getAnnoEsercizio(),ci.getAnnoImpegno(),ci.getNumeroImpegno(),ci.getAnnoSubImpegno(),ci.getNumeroSubImpegno());
		 if(ci.getAnnoSubImpegno()==null) {
			 importiSuImpPerOrdine(list,ci.getAnnoEsercizio(),ci.getAnnoImpegno(),ci.getNumeroImpegno(),enteId);
		 }else {
			 importiSuSubImpPerOrdine(list,ci.getAnnoEsercizio(),ci.getAnnoImpegno(),ci.getNumeroImpegno(),ci.getAnnoSubImpegno(),ci.getNumeroSubImpegno(), enteId);
		 }
		 return list;
	 }

	 /**
	  * 
	  * @param list
	  * @param annoEsercizio
	  * @param annoImpegno
	  * @param numeroImpegno
	  * @param enteId
	  * @return
	  */
	 protected List<ConsultazioniOrdine> importiSuImpPerOrdine(List<ConsultazioniOrdine> list,Integer annoEsercizio ,Integer annoImpegno ,Integer numeroImpegno,UUID enteId) {
		 final Integer statoInContId = cpassDStatoDao.findByCodiceTipo(StatoEvasioneEnum.IN_CONTABILITA.getCostante(), TipoStatoEnum.EVASIONE.getCostante()).orElseThrow(() -> new NotFoundException(STATO)).getId();
		 final List<ConsultazioniOrdine> ris = new ArrayList<>();
		 for(final ConsultazioniOrdine ordine: list) {
			 //Impegnato
			 final BigDecimal impegnato = cpassVOrdineDao.getImpegnatoByOrdineId(UUID. fromString(ordine.getId()),annoImpegno,  numeroImpegno);
			 ordine.setImpegnato(impegnato);
			 //Evaso
			 final BigDecimal evaso              = cpassVOrdineDao.getEvasoByOrdineId             (UUID. fromString(ordine.getId()), annoEsercizio, annoImpegno, numeroImpegno, enteId, null);
			 ordine.setEvaso(evaso);
			 //Evaso in contabilita
			 final BigDecimal evasoInContabilita = cpassVOrdineDao.getEvasoByOrdineId(UUID. fromString(ordine.getId()), annoEsercizio, annoImpegno, numeroImpegno, enteId, statoInContId);
			 ordine.setInContabilita(evasoInContabilita);
			 ris.add(ordine);
		 }
		 return ris;
	 }

	 /**
	  * 
	  * @param list
	  * @param annoEsercizio
	  * @param annoImpegno
	  * @param numeroImpegno
	  * @param annoSubImpegno
	  * @param numeroSubImpegno
	  * @param enteId
	  * @return
	  */
	  
	 protected List<ConsultazioniOrdine> importiSuSubImpPerOrdine(List<ConsultazioniOrdine> list,Integer annoEsercizio ,Integer annoImpegno ,Integer numeroImpegno,Integer annoSubImpegno,Integer  numeroSubImpegno, UUID enteId) {
		 final Integer statoInContId = cpassDStatoDao.findByCodiceTipo(StatoEvasioneEnum.IN_CONTABILITA.getCostante(), TipoStatoEnum.EVASIONE.getCostante()).orElseThrow(() -> new NotFoundException(STATO)).getId();
		 final List<ConsultazioniOrdine> ris = new ArrayList<>();
		 for(final ConsultazioniOrdine ordine: list) {
			 //Impegnato
			 final BigDecimal impegnato = cpassVOrdineDao.getsubImpegnatoByOrdineId(UUID. fromString(ordine.getId()),  annoSubImpegno,  numeroSubImpegno);
			 ordine.setImpegnato(impegnato);
			 //Evaso
			 final BigDecimal evaso = cpassVOrdineDao.getEvasoSubByOrdineId(UUID. fromString(ordine.getId()), annoEsercizio, annoImpegno,  numeroImpegno, annoSubImpegno,  numeroSubImpegno, enteId, null);
			 ordine.setEvaso(evaso);
			 //Evaso in contabilita
			 final BigDecimal evasoInContabilita = cpassVOrdineDao.getEvasoSubByOrdineId(UUID. fromString(ordine.getId()),annoEsercizio, annoImpegno,  numeroImpegno, annoSubImpegno,  numeroSubImpegno, enteId,statoInContId);
			 ordine.setInContabilita(evasoInContabilita);
			 ris.add(ordine);
		 }
		 return ris;
	 }

	 /**
	  * 
	  * @param list
	  * @return
	  */
	 protected List<ConsultazioniOrdine> calcolatiPerOrdine(List<ConsultazioniOrdine> list) {
		 final Integer statoInContId = cpassDStatoDao.findByCodiceTipo(StatoEvasioneEnum.IN_CONTABILITA.getCostante(), TipoStatoEnum.EVASIONE.getCostante()).orElseThrow(() -> new NotFoundException(STATO)).getId();
		 final List<ConsultazioniOrdine> ris = new ArrayList<>();
		 for(final ConsultazioniOrdine ordine: list) {
			 BigDecimal impegnato = cpassVOrdineDao.getsubImpegnatoByOrdineId(UUID. fromString(ordine.getId()),null,  null);
			 if(impegnato.compareTo(BigDecimal.ZERO) == 0) {
				 impegnato = cpassVOrdineDao.getImpegnatoByOrdineId(UUID. fromString(ordine.getId()),null,  null);
			 }
			 ordine.setImpegnato(impegnato);
			 //Evaso
			 final BigDecimal evaso = cpassVOrdineDao.getEvasoByOrdineId(UUID. fromString(ordine.getId()),null);
			 ordine.setEvaso(evaso);
			 //Evaso in contabilita
			 final BigDecimal evasoInContabilita = cpassVOrdineDao.getEvasoByOrdineId(UUID. fromString(ordine.getId()),statoInContId);
			 ordine.setInContabilita(evasoInContabilita);
			 ris.add(ordine);
		 }
		 return ris;
	 }

	 /**
	  * 
	  * @param enteId
	  * @param impegno
	  * @param filtroAnnoEsercizio
	  * @param statoInContId
	  * @return
	  */
	 private ConsultazioniImpegno inizializzaConsultazioniImpegnoXImp(UUID enteId, Impegno impegno,Integer filtroAnnoEsercizio,Integer statoInContId) {
		 final ConsultazioniImpegno consultazioniImpegno = new ConsultazioniImpegno();
		 consultazioniImpegno.setAnnoEsercizio(impegno.getAnnoEsercizio());
		 consultazioniImpegno.setAnnoImpegno(impegno.getAnno());
		 consultazioniImpegno.setNumeroImpegno(impegno.getNumero());
		 final BigDecimal importo = impegno.getImportoAttuale();
		 consultazioniImpegno.setImporto(importo);
		 consultazioniImpegno.setAnnoSubImpegno(null);
		 consultazioniImpegno.setNumeroSubImpegno(null);
		 consultazioniImpegno.setStatoCode(impegno.getStato());
		 consultazioniImpegno.setFornitoreCodice(impegno.getFornitore().getCodice());
		 consultazioniImpegno.setFornitoreRagioneSociale(impegno.getFornitore().getRagioneSociale());
		 //Ord. Non Liq. Anni Prec.
		 final List<CpassTImpegno> impegnoLocale = cpassTImpegnoDao.getImpegnoByChiaveLogica(impegno.getAnnoEsercizio(), impegno.getAnno(), impegno.getNumero(), enteId);
		 BigDecimal liqAnnoPrec = BigDecimal.ZERO;
		 if(impegnoLocale!=null && impegnoLocale.size()>0 && impegnoLocale.get(0).getLiqAnnoPrec()!=null) {
			 liqAnnoPrec=impegnoLocale.get(0).getLiqAnnoPrec();
		 }
		 consultazioniImpegno.setOrdNonLiqAnniPrec(liqAnnoPrec);
		 //Ordinato
		 final BigDecimal impordinato = cpassTOrdImpegnoOrdineDao.calcolaOrdinatoImpegno( impegno.getAnnoEsercizio(), impegno.getAnno(), impegno.getNumero(),filtroAnnoEsercizio, enteId);
		 consultazioniImpegno.setOrdinato(impordinato);
		 // Disponibile ad Ordinare Calcolato come  6.Importo – 7 Ord. Non Liq. Anni Prec – 8 Ordinato
		 final BigDecimal dispAOrdinare = importo.subtract(liqAnnoPrec).subtract(impordinato);
		 consultazioniImpegno.setDisponibileAdOrdinare(dispAOrdinare);
		 // Evaso
		 final BigDecimal impEvaso = cpassTOrdImpegnoEvasioneDao.calcolaImpegnoEvaso( impegno.getAnnoEsercizio(), impegno.getAnno(), impegno.getNumero(),filtroAnnoEsercizio, enteId,null);
		 consultazioniImpegno.setEvaso(impEvaso);
		 // in Contabilita
		 final BigDecimal impCons = cpassTOrdImpegnoEvasioneDao.calcolaImpegnoEvaso( impegno.getAnnoEsercizio(), impegno.getAnno(), impegno.getNumero(),filtroAnnoEsercizio, enteId,statoInContId);
		 consultazioniImpegno.setInContabilita(impCons);
		 return consultazioniImpegno;
	 }

	 /**
	  * 
	  * @param enteId
	  * @param impegno
	  * @param sub
	  * @param filtroAnnoEsercizio
	  * @param statoInContId
	  * @return
	  */

	 private ConsultazioniImpegno initzializzaConsultazioniImpegnoXSubImp(UUID enteId, Impegno impegno, Subimpegno sub,Integer filtroAnnoEsercizio,Integer statoInContId) {
		 final ConsultazioniImpegno consultazioniImpegno =new ConsultazioniImpegno();
		 consultazioniImpegno.setAnnoEsercizio(impegno.getAnnoEsercizio());
		 consultazioniImpegno.setAnnoImpegno(impegno.getAnno());
		 consultazioniImpegno.setNumeroImpegno(impegno.getNumero());
		 final BigDecimal importo = sub.getImportoAttuale();
		 consultazioniImpegno.setImporto(importo);
		 consultazioniImpegno.setAnnoSubImpegno(sub.getAnno());
		 consultazioniImpegno.setNumeroSubImpegno(sub.getNumero());
		 consultazioniImpegno.setStatoCode(sub.getStato());
		 consultazioniImpegno.setFornitoreCodice(sub.getFornitore().getCodice());
		 consultazioniImpegno.setFornitoreRagioneSociale(sub.getFornitore().getRagioneSociale());
		 //Ord. Non Liq. Anni Prec.
		 final List<CpassTSubimpegno> subimpegnoLocale = cpassTsubimpegnoDao.getSubimpegnoByChiaveLogica(impegno.getAnnoEsercizio(), impegno.getAnno(), impegno.getNumero(), enteId,sub.getAnno(),sub.getNumero());
		 BigDecimal liqAnnoPrec = BigDecimal.ZERO;
		 if(subimpegnoLocale!=null && subimpegnoLocale.size()>0 && subimpegnoLocale.get(0).getLiqAnnoPrec()!=null) {
			 liqAnnoPrec = subimpegnoLocale.get(0).getLiqAnnoPrec();
		 }
		 consultazioniImpegno.setOrdNonLiqAnniPrec(liqAnnoPrec);
		 //Ordinato
		 final BigDecimal impordinato = cpassTOrdSubimpegnoOrdineDao.calcolaOrdinatoSubImpegno( impegno.getAnnoEsercizio(), impegno.getAnno(), impegno.getNumero(),sub.getAnno(),sub.getNumero(),filtroAnnoEsercizio, enteId);
		 consultazioniImpegno.setOrdinato(impordinato);
		 // Disponibile ad Ordinare Calcolato come  6.Importo – 7 Ord. Non Liq. Anni Prec – 8 Ordinato
		 //BigDecimal dispAOrdinare = importo.min(liqAnnoPrec).min(impordinato);
		 final BigDecimal dispAOrdinare = importo.subtract(liqAnnoPrec).subtract(impordinato);
		 consultazioniImpegno.setDisponibileAdOrdinare(dispAOrdinare);
		 // Evaso
		 final BigDecimal subimpEvaso = cpassTOrdSubimpegnoEvasioneDao.calcolaSubimpegnoEvaso( impegno.getAnnoEsercizio(), impegno.getAnno(), impegno.getNumero(),sub.getAnno(),sub.getNumero(),filtroAnnoEsercizio, enteId,null);
		 consultazioniImpegno.setEvaso(subimpEvaso);
		 //In Contabilita
		 final BigDecimal subimpCont= cpassTOrdSubimpegnoEvasioneDao.calcolaSubimpegnoEvaso( impegno.getAnnoEsercizio(), impegno.getAnno(), impegno.getNumero(),sub.getAnno(),sub.getNumero(),filtroAnnoEsercizio, enteId,statoInContId);
		 consultazioniImpegno.setInContabilita(subimpCont);
		 return consultazioniImpegno;
	 }
	 /**
	  * 
	  * @param filtro
	  * @return
	  */
	 public List<ConsultazioniOrdine> valorizzaConsultazioniXOrdine(RicercaXConsultazioni filtro) {
		 List<ConsultazioniOrdine> listaConsultazioniOrdine = cpassVOrdineDao.getOrdiniXConsultazioni(filtro);
		 listaConsultazioniOrdine = calcolatiPerOrdine(listaConsultazioniOrdine);
		 listaConsultazioniOrdine = arricchisciDiEvasioni(listaConsultazioniOrdine);
		 return listaConsultazioniOrdine;
	 }

 }
