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
package it.csi.cpass.cpassbe.ejb.business.be.service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import it.csi.cpass.cpassbe.ejb.business.be.dad.ImpegnoDad;
import it.csi.cpass.cpassbe.ejb.entity.ord.CpassTOrdImpegnoOrdine;
import it.csi.cpass.cpassbe.ejb.util.ConstantsCPassParametro;
import it.csi.cpass.cpassbe.lib.dto.Impegno;
import it.csi.cpass.cpassbe.lib.dto.Subimpegno;
import it.csi.cpass.cpassbe.lib.dto.ord.ImpegnoOrdine;
import it.csi.cpass.cpassbe.lib.dto.ord.SubimpegnoOrdine;
import it.csi.cpass.cpassbe.lib.util.log.LogUtil;

public class UtilityImpegni {

	/** The logger */
	protected final static LogUtil log = new LogUtil(UtilityImpegni.class);

	/**
	 * 
	 * @param impegnoDad
	 * @param impegno
	 * @param enteId
	 * @param testataOrdineId
	 * @return
	 */
	public static BigDecimal calcolaDisponibileAdOrdinare(ImpegnoDad impegnoDad, Impegno impegno, UUID enteId, UUID testataOrdineId) {
		// Disponibile = Importo Attuale Impegno - Ordinato non Liquidato Anno Precedente - Ordinato
		BigDecimal ordinato = new BigDecimal(0);
		Impegno impegnoSalvato = impegnoDad.getImpegnoByChiaveLogica(impegno.getAnnoEsercizio(), impegno.getAnno(), impegno.getNumero(), enteId);
		if (impegnoSalvato != null) {
			ordinato = impegnoDad.calcolaOrdinato(impegnoSalvato.getId(), testataOrdineId);
		}

		// Ordinato non Liquidato Anno Precedente = CPASS_T_IMPEGNO.liq_anno_prec
		BigDecimal ordinatoNonLiquidatoAnnoPrecedente = new BigDecimal(0);
		if (impegnoSalvato != null) {
			ordinatoNonLiquidatoAnnoPrecedente = impegnoSalvato.getLiquidatoAnnoPrecedente();
		}

		BigDecimal disponibile = impegno.getImportoAttuale().subtract(ordinatoNonLiquidatoAnnoPrecedente).subtract(ordinato);
		return disponibile;
	}

	/**
	 * 
	 * @param impegnoDad
	 * @param subimpegno
	 * @return
	 */
	public static BigDecimal calcolaDisponibileAdOrdinare(ImpegnoDad impegnoDad, Impegno impegno, Subimpegno subimpegno, UUID enteId) {
		// Disponibile = Importo Attuale SubImpegno – Ordinato non Liquidato Anno Precedente - Ordinato
		BigDecimal ordinato = new BigDecimal(0);
		Subimpegno subimpegnoSalvato = impegnoDad.getSubimpegnoByChiaveLogica(impegno.getAnnoEsercizio(), impegno.getAnno(), impegno.getNumero(), enteId,
				subimpegno.getAnno(), subimpegno.getNumero());
		if (subimpegnoSalvato != null) {
			ordinato = impegnoDad.calcolaSubimpegnoOrdinato(subimpegnoSalvato.getId());
		}

		// Ordinato non Liquidato Anno Precedente = CPASS_T_SUBIMPEGNO.liq_anno_prec , letto con subimpegno_id
		BigDecimal ordinatoNonLiquidatoAnnoPrecedente = new BigDecimal(0);
		if (subimpegnoSalvato != null) {
			ordinatoNonLiquidatoAnnoPrecedente = subimpegnoSalvato.getLiquidatoAnnoPrecedente();
		}

		BigDecimal disponibile = subimpegno.getImportoAttuale().subtract(subimpegno.getLiquidatoAnnoPrecedente()).subtract(ordinato);
		return disponibile;
	}

	public static List<Impegno> controlloAnnoCongruente(ImpegnoDad impegnoDad, UUID testataOrdineId, CpassTOrdImpegnoOrdine cpassTOrdImpegnoOrdine,
			List<Impegno> listImpegnoNew) {
		final String methodName = "controlloAnnoCongruente";

		if (listImpegnoNew != null && listImpegnoNew.size() > 0) {
			Calendar calendarNow = Calendar.getInstance();
			List<Impegno> listImpegnoDopoFiltro = new ArrayList<Impegno>();

			// Se l’anno degli impegni già collegati è <= anno corrente
			int annoCorrente = calendarNow.get(Calendar.YEAR);
			if (cpassTOrdImpegnoOrdine.getImpegnoAnno() <= annoCorrente) {
				for (Impegno impegno : listImpegnoNew) {
					if (impegno.getAnno() <= annoCorrente) {
						listImpegnoDopoFiltro.add(impegno);
					}
				}

			} else {
				// Se l’anno degli impegni già collegati è = anno corrente +1
				if (cpassTOrdImpegnoOrdine.getImpegnoAnno() == (annoCorrente + 1)) {
					for (Impegno impegno : listImpegnoNew) {
						if (impegno.getAnno() == (annoCorrente + 1)) {
							listImpegnoDopoFiltro.add(impegno);
						}
					}
				}
			}

			listImpegnoNew = listImpegnoDopoFiltro;
		}

		return listImpegnoNew;
	}

	public static CpassTOrdImpegnoOrdine verificaPresenzaImpegniCollegatiOrdine(ImpegnoDad impegnoDad, UUID testataOrdineId) {
		List<CpassTOrdImpegnoOrdine> impegnoOrdines = impegnoDad.getImpegniCollegati(testataOrdineId);
		if (impegnoOrdines != null && impegnoOrdines.size() > 0) {
			return impegnoOrdines.get(0);
		}
		return null;
	}

	public static List<Impegno> controlloDataAnniFuturi(Map<String, String> mapParams, List<Impegno> listImpegnoNew, Date dataEmissione) {
		final String methodName = "controlloDataAnniFuturi";

		if (listImpegnoNew != null && listImpegnoNew.size() > 0) {
			Calendar calendarNow = Calendar.getInstance();

			String strDataOrdiniFuturi = getParameter(mapParams, ConstantsCPassParametro.ChiaveEnum.DATA_ORDINI_FUTURI.getCostante());
			strDataOrdiniFuturi += "/" + calendarNow.get(Calendar.YEAR);

			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Date dataOrdiniFuturi = null;
			try {
				dataOrdiniFuturi = sdf.parse(strDataOrdiniFuturi);
			} catch (Exception e) {
				log.error(methodName, e.getMessage(), e);
			}

			if (dataOrdiniFuturi != null) {
				List<Impegno> listImpegnoDopoFiltroDataOrdiniFuturi = new ArrayList<Impegno>();
				Date dataConfronto = null;
				if (dataEmissione == null) {
					// caso "in inserimento testata ordine"
					dataConfronto = calendarNow.getTime();
				} else {
					// caso "Modificare testata ordine"
					dataConfronto = dataEmissione;
				}

				for (Impegno impegno : listImpegnoNew) {
					if (dataOrdiniFuturi.after(dataConfronto)) {
						// tenere solo gli impegni Impegni.annoimpegno <= anno corrente
						if (impegno.getAnno().intValue() <= calendarNow.get(Calendar.YEAR)) {
							listImpegnoDopoFiltroDataOrdiniFuturi.add(impegno);
						}
					} else {
						// esporre anche gli impegni con Impegni.annoimpegno = anno corrente + 1
						if (impegno.getAnno().intValue() <= (calendarNow.get(Calendar.YEAR) + 1)) {
							listImpegnoDopoFiltroDataOrdiniFuturi.add(impegno);
						}
					}
				}

				listImpegnoNew = listImpegnoDopoFiltroDataOrdiniFuturi;
			}
		}
		return listImpegnoNew;
	}
	
	public static Impegno verificaImpegniRibaltati(ImpegnoDad impegnoDad, UUID enteId, ImpegnoOrdine impegnoOrdine) {
		Calendar calendar = Calendar.getInstance();
		int annoCorrente = calendar.get(Calendar.YEAR);
		if (impegnoOrdine.getImpegnoAnnoEsercizio() == annoCorrente) {
			return impegnoOrdine.getImpegno();

		} else if (impegnoOrdine.getImpegnoAnnoEsercizio() < annoCorrente) {
			return impegnoDad.getImpegnoByChiaveLogica(annoCorrente, impegnoOrdine.getImpegnoAnno(), impegnoOrdine.getImpegnoNumero(), enteId);
		}
		return null;
	}

	public static Subimpegno verificaSubimpegniRibaltati(ImpegnoDad impegnoDad, UUID enteId, SubimpegnoOrdine subimpegnoOrdine) {
		Calendar calendar = Calendar.getInstance();
		int annoCorrente = calendar.get(Calendar.YEAR);
		if (subimpegnoOrdine.getImpegnoAnnoEsercizio() == annoCorrente) {
			return subimpegnoOrdine.getSubimpegno();

		} else if (subimpegnoOrdine.getImpegnoAnnoEsercizio() < annoCorrente) {
			return impegnoDad.getSubimpegnoByChiaveLogica(annoCorrente, subimpegnoOrdine.getImpegnoAnno(), subimpegnoOrdine.getImpegnoNumero(), enteId,
					subimpegnoOrdine.getSubimpegnoAnno(), subimpegnoOrdine.getSubimpegnoNumero());
		}
		return null;
	}

	/**
	 * Gets the parameter
	 * 
	 * @param params
	 * @param paramName
	 * @return
	 */
	protected static String getParameter(Map<String, String> params, String paramName) {
		return params.get(paramName);
	}

}
