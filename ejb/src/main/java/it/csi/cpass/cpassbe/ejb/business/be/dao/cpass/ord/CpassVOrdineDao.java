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
package it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.ord;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import it.csi.cpass.cpassbe.ejb.business.be.dao.BaseEntityDao;
import it.csi.cpass.cpassbe.ejb.entity.ord.CpassVOrdine;
import it.csi.cpass.cpassbe.lib.dto.ord.consultazioni.ConsultazioniOrdine;
import it.csi.cpass.cpassbe.lib.dto.ord.consultazioni.RicercaXConsultazioni;

/**
 * Data Access Object interface for the entity CpassVOrdineDao
 */
public interface CpassVOrdineDao extends BaseEntityDao<Long, CpassVOrdine> {

	List<CpassVOrdine> getListVOrdineByStati(List<Integer> statiIds);

	List<CpassVOrdine> getListImpegniByOrdineId(UUID testataOrdineId);

	List<CpassVOrdine> getListImpegniOrdinatiImpByOrdineId(UUID testataOrdineId);

	List<CpassVOrdine> getListImpegniRiepilogoByOrdineId(UUID idTestataOrdine);

	BigDecimal getImpegnatoByOrdineId(UUID idTestataOrdine,Integer impegnoAnno,Integer  impegnoNumero);

	BigDecimal getsubImpegnatoByOrdineId(UUID testataOrdineId, Integer annoSubImpegno,Integer  numeroSubImpegno);

	BigDecimal getEvasoByOrdineId(UUID idTestataOrdine, Integer statoId);

	BigDecimal getEvasoByOrdineId(UUID testataOrdineId,Integer impegnoAnnoEsercizio, Integer impegnoAnno, Integer impegnoNumero,UUID enteId,Integer statoId);

	BigDecimal getEvasoSubByOrdineId(UUID testataOrdineId,Integer impegnoAnnoEsercizio, Integer impegnoAnno, Integer impegnoNumero,Integer annoSubImpegno, Integer numeroSubImpegno,UUID enteId,Integer statoId);

	List<ConsultazioniOrdine> getOrdiniXConsultazioni(RicercaXConsultazioni filtro);

	BigDecimal getOrdinatoComplessivoAnno(Integer provvedimentoAnno, String provvedimentoNumero, String tipoprovvedimentoCodice, String settoreCodice,Integer annoEsercizio);

	BigDecimal getOrdinatoComplessivoAnnoSuImpegni(Integer provvedimentoAnno, String provvedimentoNumero, String tipoprovvedimentoCodice, String settoreCodice,Integer annoEsercizio);

	BigDecimal getOrdinatoComplessivoAnnoSuImpegniResidui(Integer provvedimentoAnno, String provvedimentoNumero, String tipoprovvedimentoCodice, String settoreCodice,Integer annoEsercizio);

	BigDecimal getOrdinatoComplessivoAnnoSuImpegniCompetenza(Integer provvedimentoAnno, String provvedimentoNumero, String tipoprovvedimentoCodice, String settoreCodice,Integer annoEsercizio);

	BigDecimal getOrdinatoComplessivoAnnoSuImpegniAnnoSuccessivo(Integer provvedimentoAnno, String provvedimentoNumero, String tipoprovvedimentoCodice, String settoreCodice,Integer annoEsercizio);

	BigDecimal getOrdinatoComplessivoAnniPrecedenti(Integer provvedimentoAnno, String provvedimentoNumero, String tipoprovvedimentoCodice, String settoreCodice,Integer annoEsercizio);

	BigDecimal getOrdinatoComplessivoAnniPrecedentiSuImpegni(Integer provvedimentoAnno, String provvedimentoNumero, String tipoprovvedimentoCodice, String settoreCodice,Integer annoEsercizio);

	BigDecimal getOrdinatoComplessivoAnniPrecedentiSuImpegniNonLiq(Integer provvedimentoAnno, String provvedimentoNumero, String tipoprovvedimentoCodice, String settoreCodice,Integer annoEsercizio);

	BigDecimal getOrdinatoComplessivoAnniPrecedentiSuImpegniAnnoEsercizio(Integer provvedimentoAnno,String provvedimentoNumero, String provvedimentoTipo, String settoreCodice, Integer annoEsercizio);



}
