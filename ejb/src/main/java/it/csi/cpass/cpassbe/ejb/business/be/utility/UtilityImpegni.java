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
package it.csi.cpass.cpassbe.ejb.business.be.utility;

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
	 * @param impegnoEsterno
	 * @param enteId
	 * @param testataOrdineId
	 * @return
	 */
	public static BigDecimal calcolaDisponibileAdOrdinare(ImpegnoDad impegnoDad, Impegno impegnoEsterno, UUID enteId, UUID testataOrdineId) {
		// Disponibile = Importo Attuale Impegno - Ordinato non Liquidato Anno Precedente - Ordinato
		BigDecimal ordinato = new BigDecimal(0);
		final List<Impegno> impegnoSalvato = impegnoDad.getImpegnoByChiaveLogica(impegnoEsterno.getAnnoEsercizio(), impegnoEsterno.getAnno(), impegnoEsterno.getNumero(), enteId);
		// Ordinato non Liquidato Anno Precedente = CPASS_T_IMPEGNO.liq_anno_prec
		BigDecimal ordinatoNonLiquidatoAnnoPrecedente = new BigDecimal(0);
		if (!impegnoSalvato.isEmpty()) {
			ordinato = impegnoDad.calcolaOrdinato(impegnoSalvato.get(0).getId(),Calendar.getInstance().get(Calendar.YEAR), testataOrdineId);
			ordinatoNonLiquidatoAnnoPrecedente = impegnoSalvato.get(0).getLiquidatoAnnoPrecedente();
		}
		BigDecimal disponibile = BigDecimal.ZERO;
		//log.info("calcolaDisponibileAdOrdinare ","sub esistenti " + impegnoEsterno.getSubimpegni()!= null );
		if(impegnoEsterno.getSubimpegni()!= null && impegnoEsterno.getSubimpegni().size()>0 && impegnoEsterno.getSubimpegni().get(0).getImportoAttuale()!=null) {
			disponibile = impegnoEsterno.getSubimpegni().get(0).getImportoAttuale().subtract(ordinatoNonLiquidatoAnnoPrecedente).subtract(ordinato);
		}else {
			disponibile = impegnoEsterno.getImportoAttuale().subtract(ordinatoNonLiquidatoAnnoPrecedente).subtract(ordinato);
		}
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
		final Subimpegno subimpegnoSalvato = impegnoDad.getSubimpegnoByChiaveLogica(impegno.getAnnoEsercizio(), impegno.getAnno(), impegno.getNumero(), enteId,subimpegno.getAnno(), subimpegno.getNumero());
		if (subimpegnoSalvato != null) {
			ordinato = impegnoDad.calcolaSubimpegnoOrdinato(subimpegnoSalvato.getId(),Calendar.getInstance().get(Calendar.YEAR));
		}

		// Ordinato non Liquidato Anno Precedente = CPASS_T_SUBIMPEGNO.liq_anno_prec , letto con subimpegno_id
		/*
		BigDecimal ordinatoNonLiquidatoAnnoPrecedente = new BigDecimal(0);
		if (subimpegnoSalvato != null) {
			ordinatoNonLiquidatoAnnoPrecedente = subimpegnoSalvato.getLiquidatoAnnoPrecedente();
		}
		 */
		// Ordinato non Liquidato Anno Precedente = CPASS_T_SUBIMPEGNO.liq_anno_prec , letto con subimpegno_id
		BigDecimal ordinatoNonLiquidatoAnnoPrecedente = new BigDecimal(0);
		if (subimpegnoSalvato != null) {
			ordinatoNonLiquidatoAnnoPrecedente = subimpegnoSalvato.getLiquidatoAnnoPrecedente();
		}else {
			ordinatoNonLiquidatoAnnoPrecedente = subimpegno.getLiquidatoAnnoPrecedente();
		}

		final BigDecimal disponibile = subimpegno.getImportoAttuale().subtract(ordinatoNonLiquidatoAnnoPrecedente).subtract(ordinato);
		return disponibile;
	}

	public static List<Impegno> controlloAnnoCongruente(ImpegnoDad impegnoDad, UUID testataOrdineId, CpassTOrdImpegnoOrdine cpassTOrdImpegnoOrdine,
			List<Impegno> listImpegnoNew) {
		if (listImpegnoNew != null && listImpegnoNew.size() > 0) {
			final Calendar calendarNow = Calendar.getInstance();
			final List<Impegno> listImpegnoDopoFiltro = new ArrayList<>();

			// Se l’anno degli impegni già collegati è <= anno corrente
			final int annoCorrente = calendarNow.get(Calendar.YEAR);
			if (cpassTOrdImpegnoOrdine.getImpegnoAnno() <= annoCorrente) {
				for (final Impegno impegno : listImpegnoNew) {
					if (impegno.getAnno() <= annoCorrente) {
						listImpegnoDopoFiltro.add(impegno);
					}
				}

			} else {
				// Se l’anno degli impegni già collegati è = anno corrente +1
				if (cpassTOrdImpegnoOrdine.getImpegnoAnno() == (annoCorrente + 1)) {
					for (final Impegno impegno : listImpegnoNew) {
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
		final List<CpassTOrdImpegnoOrdine> impegnoOrdines = impegnoDad.getImpegniCollegati(testataOrdineId);
		if (impegnoOrdines != null && impegnoOrdines.size() > 0) {
			return impegnoOrdines.get(0);
		}
		return null;
	}

	public static List<Impegno> controlloDataAnniFuturi(Map<String, String> mapParams, List<Impegno> listImpegnoNew, Date dataEmissione) {
		final String methodName = "controlloDataAnniFuturi";

		if (listImpegnoNew != null && listImpegnoNew.size() > 0) {
			final Calendar calendarNow = Calendar.getInstance();

			StringBuilder strDataOrdiniFuturi = new StringBuilder().append(getParameter(mapParams, ConstantsCPassParametro.ChiaveEnum.DATA_ORDINI_FUTURI.getCostante()));
			strDataOrdiniFuturi.append("/").append(calendarNow.get(Calendar.YEAR));

			final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Date dataOrdiniFuturi = null;
			try {
				dataOrdiniFuturi = sdf.parse(strDataOrdiniFuturi.toString());
			} catch (final Exception e) {
				log.error(methodName, e.getMessage(), e);
			}

			if (dataOrdiniFuturi != null) {
				final List<Impegno> listImpegnoDopoFiltroDataOrdiniFuturi = new ArrayList<>();
				Date dataConfronto = null;
				if (dataEmissione == null) {
					// caso "in inserimento testata ordine"
					dataConfronto = calendarNow.getTime();
				} else {
					// caso "Modificare testata ordine"
					dataConfronto = dataEmissione;
				}

				for (final Impegno impegno : listImpegnoNew) {
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
		final Calendar calendar = Calendar.getInstance();
		final int annoCorrente = calendar.get(Calendar.YEAR);
		if (impegnoOrdine.getImpegnoAnnoEsercizio() == annoCorrente) {
			return impegnoOrdine.getImpegno();

		} else if (impegnoOrdine.getImpegnoAnnoEsercizio() < annoCorrente) {
			final List<Impegno> impegnoList = impegnoDad.getImpegnoByChiaveLogica(
					annoCorrente, impegnoOrdine.getImpegnoAnno(), impegnoOrdine.getImpegnoNumero(), enteId);
			return impegnoList != null && impegnoList.size() > 0 ? impegnoList.get(0) : null;
		}
		return null;
	}

	public static Subimpegno verificaSubimpegniRibaltati(ImpegnoDad impegnoDad, UUID enteId, SubimpegnoOrdine subimpegnoOrdine) {
		final Calendar calendar = Calendar.getInstance();
		final int annoCorrente = calendar.get(Calendar.YEAR);
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
