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
import java.util.List;

import javax.enterprise.context.ApplicationScoped;

 import javax.inject.Inject;

import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.ord.CpassVOrdineDao;
import it.csi.cpass.cpassbe.lib.dto.Impegno;
import it.csi.cpass.cpassbe.lib.dto.ord.consultazioni.ConsultazioniRiepilogo;
import it.csi.cpass.cpassbe.lib.dto.ord.consultazioni.ConsultazioniRiepilogoAcquisti;
import it.csi.cpass.cpassbe.lib.dto.ord.consultazioni.ConsultazioniRiepilogoFinanziario;
import it.csi.cpass.cpassbe.lib.dto.ord.consultazioni.RicercaXConsultazioni;


 /**
  * Data Access Delegate for decodificas
  */
 @ApplicationScoped
 public class ConsultazioniRiepilogoDad extends BaseDad {

	 @Inject
	 private CpassVOrdineDao cpassVOrdineDao;
	 
	 /**
	  * 
	  * @param filtro
	  * @param listaImpegniEsercizio
	  * @param listaImpegniEsercizioProssimo
	  * @return
	  */
	 public ConsultazioniRiepilogo valorizzaConsultazioniXRiepilogo(RicercaXConsultazioni filtro,List<Impegno> listaImpegniEsercizio,List<Impegno> listaImpegniEsercizioProssimo) {

		 final ConsultazioniRiepilogo consultazioniRiepilogo = new ConsultazioniRiepilogo();
		 consultazioniRiepilogo.setAnnoEsercizio ( filtro.getAnnoEsercizio());
		 final ConsultazioniRiepilogoFinanziario consultazioniRiepilogoFinanziario = new ConsultazioniRiepilogoFinanziario();
		 // 1 prima sezione
		 final BigDecimal totaleResiduo = calcolaTotaleResiduo(listaImpegniEsercizio,filtro.getAnnoEsercizio());
		 consultazioniRiepilogoFinanziario.setTotaleResidui(totaleResiduo);
		 //2
		 final BigDecimal totaleCompetenza = calcolaTotaleCompetenza(listaImpegniEsercizio,filtro.getAnnoEsercizio());
		 consultazioniRiepilogoFinanziario.setTotaleCompetenza(totaleCompetenza);
		 //3
		 final Integer annoprossimo = filtro.getAnnoEsercizio() +1 ;
		 final BigDecimal totaleAnnoSuccessivo = calcolaTotaleAnnoSuccessivo(listaImpegniEsercizio,annoprossimo);
		 consultazioniRiepilogoFinanziario.setTotaleAnnoSuccessivo(totaleAnnoSuccessivo);
		 // seconda sezione

		 final ConsultazioniRiepilogoAcquisti consultazioniRiepilogoAcquisti = new ConsultazioniRiepilogoAcquisti();
		 //4
		 final BigDecimal ordinatoComplessivoAnno = cpassVOrdineDao.getOrdinatoComplessivoAnno(
				 filtro.getProvvedimento().getAnno()
				 ,filtro.getProvvedimento().getNumero()
				 ,filtro.getProvvedimento().getProvvedimentoTipo().getCodice()
				 ,filtro.getProvvedimento().getSettore().getCodice()
				 ,filtro.getAnnoEsercizio()
				 );
		 consultazioniRiepilogoAcquisti.setOrdinatoComplessivo(ordinatoComplessivoAnno);
		 //5
		 final BigDecimal ordinatoComplessivoAnnoSuImpegni = cpassVOrdineDao.getOrdinatoComplessivoAnnoSuImpegni(
				 filtro.getProvvedimento().getAnno()
				 ,filtro.getProvvedimento().getNumero()
				 ,filtro.getProvvedimento().getProvvedimentoTipo().getCodice()
				 ,filtro.getProvvedimento().getSettore().getCodice()
				 ,filtro.getAnnoEsercizio()
				 );
		 consultazioniRiepilogoAcquisti.setOrdinatoComplessivoSuImpegni(ordinatoComplessivoAnnoSuImpegni);

		 //7
		 final BigDecimal ordinatoComplessivoAnnoSuImpegniResidui = cpassVOrdineDao.getOrdinatoComplessivoAnnoSuImpegniResidui(
				 filtro.getProvvedimento().getAnno()
				 ,filtro.getProvvedimento().getNumero()
				 ,filtro.getProvvedimento().getProvvedimentoTipo().getCodice()
				 ,filtro.getProvvedimento().getSettore().getCodice()
				 ,filtro.getAnnoEsercizio()
				 );
		 consultazioniRiepilogoAcquisti.setOrdinatoComplessivoSuImpegniResidui(ordinatoComplessivoAnnoSuImpegniResidui);

		 //9////////////////////////////////////////////////////////////////////////////////////////////////////////////
		 final BigDecimal ordinatoComplessivoAnnoSuImpegniCompetenza = cpassVOrdineDao.getOrdinatoComplessivoAnnoSuImpegniCompetenza(
				 filtro.getProvvedimento().getAnno()
				 ,filtro.getProvvedimento().getNumero()
				 ,filtro.getProvvedimento().getProvvedimentoTipo().getCodice()
				 ,filtro.getProvvedimento().getSettore().getCodice()
				 ,filtro.getAnnoEsercizio()
				 );
		 consultazioniRiepilogoAcquisti.setOrdinatoComplessivoSuImpegniCompetenza(ordinatoComplessivoAnnoSuImpegniCompetenza);
		 //11////////////////////////////////////////////////////////////////////////////////////////////////////////////
		 final BigDecimal ordinatoComplessivoAnnoSuImpegniAnnoSuccessivo = cpassVOrdineDao.getOrdinatoComplessivoAnnoSuImpegniAnnoSuccessivo(
				 filtro.getProvvedimento().getAnno()
				 ,filtro.getProvvedimento().getNumero()
				 ,filtro.getProvvedimento().getProvvedimentoTipo().getCodice()
				 ,filtro.getProvvedimento().getSettore().getCodice()
				 ,filtro.getAnnoEsercizio()
				 );
		 consultazioniRiepilogoAcquisti.setOrdinatoComplessivoSuImpegniAnnoSuccessivo(ordinatoComplessivoAnnoSuImpegniAnnoSuccessivo);

		 //13/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		 final BigDecimal ordinatoComplessivoAnniPrecedenti = cpassVOrdineDao.getOrdinatoComplessivoAnniPrecedenti(
				 filtro.getProvvedimento().getAnno()
				 ,filtro.getProvvedimento().getNumero()
				 ,filtro.getProvvedimento().getProvvedimentoTipo().getCodice()
				 ,filtro.getProvvedimento().getSettore().getCodice()
				 ,filtro.getAnnoEsercizio()
				 );
		 consultazioniRiepilogoAcquisti.setOrdinatoComplessivoAnniPrecedenti(ordinatoComplessivoAnniPrecedenti);

		 //14/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		 final BigDecimal ordinatoComplessivoAnniPrecedentiSuImpegni = cpassVOrdineDao.getOrdinatoComplessivoAnniPrecedentiSuImpegni(
				 filtro.getProvvedimento().getAnno()
				 ,filtro.getProvvedimento().getNumero()
				 ,filtro.getProvvedimento().getProvvedimentoTipo().getCodice()
				 ,filtro.getProvvedimento().getSettore().getCodice()
				 ,filtro.getAnnoEsercizio()
				 );
		 consultazioniRiepilogoAcquisti.setOrdinatoComplessivoAnniPrecedentiSuImpegni(ordinatoComplessivoAnniPrecedentiSuImpegni);

		 //15/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		 final BigDecimal ordinatoComplessivoAnniPrecedentiSuImpegniNonLiq = cpassVOrdineDao.getOrdinatoComplessivoAnniPrecedentiSuImpegniNonLiq(
				 filtro.getProvvedimento().getAnno()
				 ,filtro.getProvvedimento().getNumero()
				 ,filtro.getProvvedimento().getProvvedimentoTipo().getCodice()
				 ,filtro.getProvvedimento().getSettore().getCodice()
				 ,filtro.getAnnoEsercizio()
				 );
		 consultazioniRiepilogoAcquisti.setOrdinatoComplessivoAnniPrecedentiSuImpegniNonLiq(ordinatoComplessivoAnniPrecedentiSuImpegniNonLiq);

		 //16/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		 final BigDecimal ordinatoComplessivoAnniPrecedentiSuImpegniAnnoEsercizio = cpassVOrdineDao.getOrdinatoComplessivoAnniPrecedentiSuImpegniAnnoEsercizio(
				 filtro.getProvvedimento().getAnno()
				 ,filtro.getProvvedimento().getNumero()
				 ,filtro.getProvvedimento().getProvvedimentoTipo().getCodice()
				 ,filtro.getProvvedimento().getSettore().getCodice()
				 ,filtro.getAnnoEsercizio()
				 );
		 consultazioniRiepilogoAcquisti.setOrdinatoComplessivoAnniPrecedentiSuImpegniAnnoEsercizio(ordinatoComplessivoAnniPrecedentiSuImpegniAnnoEsercizio);



		 //8
		 //Differenza dei campi 1 – 15 - 7
		 final BigDecimal ordinatoComplessivoDisponibileResidui = totaleResiduo.subtract(ordinatoComplessivoAnnoSuImpegniResidui).subtract(ordinatoComplessivoAnniPrecedentiSuImpegniNonLiq);
		 consultazioniRiepilogoAcquisti.setOrdinatoComplessivoDisponibileResidui(ordinatoComplessivoDisponibileResidui);

		 //10
		 //Differenza dei campi 2 – 16 – 9
		 final BigDecimal ordinatoComplessivoDisponibileCompetenza = totaleCompetenza.subtract(ordinatoComplessivoAnniPrecedentiSuImpegniAnnoEsercizio).subtract(ordinatoComplessivoAnnoSuImpegniCompetenza);
		 consultazioniRiepilogoAcquisti.setOrdinatoComplessivoDisponibileCompetenza(ordinatoComplessivoDisponibileCompetenza);

		 //Differenza dei campi 3 - 11
		 final BigDecimal ordinatoComplessivoDisponibileAnnoSuccessivo = totaleAnnoSuccessivo.subtract(ordinatoComplessivoAnnoSuImpegniAnnoSuccessivo);
		 consultazioniRiepilogoAcquisti.setOrdinatoComplessivoDisponibileAnnoSuccessivo(ordinatoComplessivoDisponibileAnnoSuccessivo);

		 //Sommatoria dei campi 8 + 10 + 12
		 final BigDecimal ordinatoComplessivoDisponibile = ordinatoComplessivoDisponibileResidui.add(ordinatoComplessivoDisponibileCompetenza).add(ordinatoComplessivoDisponibileAnnoSuccessivo);
		 consultazioniRiepilogoAcquisti.setOrdinatoComplessivoDisponibile(ordinatoComplessivoDisponibile);


		 consultazioniRiepilogo.setConsultazioniRiepilogoFinanziario(consultazioniRiepilogoFinanziario);
		 consultazioniRiepilogo.setConsultazioniRiepilogoAcquisti(consultazioniRiepilogoAcquisti);

		 return consultazioniRiepilogo;
	 }
	 /**
	  * 
	  * @param listaImpegniEsercizio
	  * @param annoEsercizioProssimo
	  * @return
	  */
	 private BigDecimal calcolaTotaleAnnoSuccessivo(List<Impegno> listaImpegniEsercizio, Integer annoEsercizioProssimo) {
		 BigDecimal ris = BigDecimal.ZERO;
		 for(final Impegno ie:listaImpegniEsercizio) {
			 if(annoEsercizioProssimo.equals(ie.getAnno())) {
				 ris = ris.add(ie.getImportoAttuale());
			 }
		 }
		 return ris;
	 }
	 /**
	  * 
	  * @param listaImpegniEsercizio
	  * @param annoEsercizio
	  * @return
	  */
	 private BigDecimal calcolaTotaleResiduo(List<Impegno> listaImpegniEsercizio,Integer annoEsercizio ) {
		 BigDecimal ris = BigDecimal.ZERO;
		 for(final Impegno ie:listaImpegniEsercizio) {
			 if(annoEsercizio.compareTo(ie.getAnno())>0) {
				 ris = ris.add(ie.getImportoAttuale());
			 }
		 }
		 return ris;
	 }
	 /**
	  * 
	  * @param listaImpegniEsercizio
	  * @param annoEsercizio
	  * @return
	  */
	 private BigDecimal calcolaTotaleCompetenza(List<Impegno> listaImpegniEsercizio,Integer annoEsercizio ) {
		 BigDecimal ris = BigDecimal.ZERO;
		 for(final Impegno ie:listaImpegniEsercizio) {
			 if(annoEsercizio.equals(ie.getAnno())) {
				 ris = ris.add(ie.getImportoAttuale());
			 }
		 }
		 return ris;
	 }

 }
