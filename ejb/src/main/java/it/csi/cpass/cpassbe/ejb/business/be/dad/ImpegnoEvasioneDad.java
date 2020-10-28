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
import java.util.List;
import java.util.UUID;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.ord.evasione.CpassTOrdImpegnoEvasioneDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.ord.evasione.CpassTOrdSubimpegnoEvasioneDao;
import it.csi.cpass.cpassbe.ejb.entity.ord.CpassTOrdImpegnoOrdine;
import it.csi.cpass.cpassbe.ejb.entity.ord.CpassTOrdSubimpegnoOrdine;
import it.csi.cpass.cpassbe.ejb.entity.ord.evasione.CpassTOrdImpegnoEvasione;
import it.csi.cpass.cpassbe.ejb.entity.ord.evasione.CpassTOrdSubimpegnoEvasione;
import it.csi.cpass.cpassbe.ejb.mapper.CpassMappers;
import it.csi.cpass.cpassbe.lib.dto.ord.ImpegnoOrdine;
import it.csi.cpass.cpassbe.lib.dto.ord.evasione.ImpegnoEvasione;
import it.csi.cpass.cpassbe.lib.dto.ord.evasione.SubimpegnoEvasione;

@ApplicationScoped
public class ImpegnoEvasioneDad extends BaseDad {

	@Inject
	private CpassTOrdImpegnoEvasioneDao cpassTOrdImpegnoEvasioneDao;
	@Inject
	private CpassTOrdSubimpegnoEvasioneDao cpassTOrdSubimpegnoEvasioneDao;

	public ImpegnoEvasione insert(ImpegnoEvasione impegnoEvasione) {
		CpassTOrdImpegnoEvasione cpassTOrdImpegnoEvasione = CpassMappers.IMPEGNO_EVASIONE.toEntity(impegnoEvasione);
		ImpegnoEvasione result = CpassMappers.IMPEGNO_EVASIONE.toModel(cpassTOrdImpegnoEvasioneDao.insert(cpassTOrdImpegnoEvasione));
		return result;
	}

	public ImpegnoEvasione update(ImpegnoEvasione impegnoEvasione) {
		CpassTOrdImpegnoEvasione cpassTOrdImpegnoEvasione = CpassMappers.IMPEGNO_EVASIONE.toEntity(impegnoEvasione);
		ImpegnoEvasione result = CpassMappers.IMPEGNO_EVASIONE.toModel(cpassTOrdImpegnoEvasioneDao.update(cpassTOrdImpegnoEvasione));
		return result;
	}

	public List<ImpegnoEvasione> getImpegniByRigaEvasione(UUID idRigaEvasione) {
		List<ImpegnoEvasione> impegnoEvasionesRet = new ArrayList<ImpegnoEvasione>();

		// impegni
		List<CpassTOrdImpegnoEvasione> impegnoEvasiones = cpassTOrdImpegnoEvasioneDao.getImpegniEvasione(idRigaEvasione);
		if (impegnoEvasiones != null) {
			for (CpassTOrdImpegnoEvasione cpassTOrdImpegnoEvasione : impegnoEvasiones) {
				ImpegnoEvasione impegnoEvasione = CpassMappers.IMPEGNO_EVASIONE.toModel(cpassTOrdImpegnoEvasione);
				impegnoEvasione.setImportoRipartito(cpassTOrdImpegnoEvasione.getImportoRipartito());
				impegnoEvasione.setImportoSospeso(cpassTOrdImpegnoEvasione.getImportoSospeso());
				impegnoEvasione.setImportoLiquidato(cpassTOrdImpegnoEvasione.getImportoLiquidato());
				impegnoEvasionesRet.add(impegnoEvasione);

				// subimpegni
				List<CpassTOrdSubimpegnoEvasione> subimpegnoEvasiones = cpassTOrdSubimpegnoEvasioneDao
						.getSubimpegni(cpassTOrdImpegnoEvasione.getImpegnoEvasioneId());
				if (subimpegnoEvasiones != null) {
					for (CpassTOrdSubimpegnoEvasione cpassTOrdSubimpegnoEvasione : subimpegnoEvasiones) {
						SubimpegnoEvasione subimpegnoEvasione = CpassMappers.SUBIMPEGNO_EVASIONE.toModel(cpassTOrdSubimpegnoEvasione);
						subimpegnoEvasione.setImportoRipartito(cpassTOrdSubimpegnoEvasione.getImportoRipartito());
						subimpegnoEvasione.setImportoSospeso(cpassTOrdSubimpegnoEvasione.getImportoSospeso());
						subimpegnoEvasione.setImportoLiquidato(cpassTOrdSubimpegnoEvasione.getImportoLiquidato());
						impegnoEvasione.getSubimpegnoEvasiones().add(subimpegnoEvasione);

						// calcolo TotaleRipartibile
						BigDecimal totaleOrdinato = subimpegnoEvasione.getSubimpegnoOrdine().getSubimpegnoImporto();
						BigDecimal totaleGiaEvaso = cpassTOrdSubimpegnoEvasioneDao.calcolaTotaleEvaso(subimpegnoEvasione.getSubimpegnoOrdine().getId());
						BigDecimal totaleRipartibile = totaleOrdinato.subtract(totaleGiaEvaso);

						totaleRipartibile = totaleRipartibile.add(subimpegnoEvasione.getImportoRipartito()).add(subimpegnoEvasione.getImportoSospeso());
						subimpegnoEvasione.setTotaleRipartibile(totaleRipartibile);
					}

				} else {
					// calcolo TotaleRipartibile
					BigDecimal totaleOrdinato = impegnoEvasione.getImpegnoOrdine().getImporto();
					BigDecimal totaleGiaEvaso = cpassTOrdImpegnoEvasioneDao.calcolaTotaleEvaso(impegnoEvasione.getImpegnoOrdine().getId());
					BigDecimal totaleRipartibile = totaleOrdinato.subtract(totaleGiaEvaso);

					// fix NullPointerException
					if (impegnoEvasione.getImportoRipartito() == null) {
						impegnoEvasione.setImportoRipartito(new BigDecimal(0));
					}
					totaleRipartibile = totaleRipartibile.add(impegnoEvasione.getImportoRipartito()).add(impegnoEvasione.getImportoSospeso());
					impegnoEvasione.setTotaleRipartibile(totaleRipartibile);
				}
			}
		}

		return impegnoEvasionesRet;
	}

	public ImpegnoEvasione saveImpegnoEvasione(ImpegnoEvasione impegnoEvasione) {
		CpassTOrdImpegnoEvasione impegnoEvasioneEntity = CpassMappers.IMPEGNO_EVASIONE.toEntity(impegnoEvasione);

		// incremento il progressivo
		if (impegnoEvasione.getImpegnoProgressivo() == null || impegnoEvasione.getImpegnoProgressivo().equals(0)) {
			Integer maxProgressivo = 0;
			List<CpassTOrdImpegnoEvasione> righeEvasione = cpassTOrdImpegnoEvasioneDao.findByIdRigaEvasione(impegnoEvasione.getRigaEvasione().getId());
			for (CpassTOrdImpegnoEvasione riga : righeEvasione) {
				if (riga.getImpegnoProgressivo() > maxProgressivo) {
					maxProgressivo = riga.getImpegnoProgressivo();
				}
			}
			impegnoEvasioneEntity.setImpegnoProgressivo(++maxProgressivo);
		}

		ImpegnoEvasione result = CpassMappers.IMPEGNO_EVASIONE.toModel(cpassTOrdImpegnoEvasioneDao.insert(impegnoEvasioneEntity));
		return result;
	}
	
	public List<ImpegnoEvasione> findByIdImpegnoOrdine(UUID impegnoOrdineId) {
		List<CpassTOrdImpegnoEvasione> cpassTOrdImpegnoEvasiones = cpassTOrdImpegnoEvasioneDao.findByIdImpegnoOrdine(impegnoOrdineId);
		List<ImpegnoEvasione> impegnoEvasiones = CpassMappers.IMPEGNO_EVASIONE.toModels(cpassTOrdImpegnoEvasiones);
		return impegnoEvasiones;
	}

	public BigDecimal calcolaTotaleEvaso(UUID impegnoOrdineId) {
		return cpassTOrdImpegnoEvasioneDao.calcolaTotaleEvaso(impegnoOrdineId);
	}

	public BigDecimal calcolaTotale(Integer impegnoAnno, Integer impegnoNumero) {
		return cpassTOrdImpegnoEvasioneDao.calcolaTotale(impegnoAnno, impegnoNumero);
	}

	public void deleteImpegniEvasioneByRiga(UUID rigaEvasioneId) {
		List<CpassTOrdImpegnoEvasione> impegniEvasiones = cpassTOrdImpegnoEvasioneDao.getImpegniEvasione(rigaEvasioneId);
		if (impegniEvasiones != null) {
			for (CpassTOrdImpegnoEvasione impegnoEvasione : impegniEvasiones) {

				if (impegnoEvasione.getCpassTOrdSubimpegnoEvasiones() != null) {
					for (CpassTOrdSubimpegnoEvasione subImpegno : impegnoEvasione.getCpassTOrdSubimpegnoEvasiones()) {
						cpassTOrdSubimpegnoEvasioneDao.delete(subImpegno.getSubimpegnoEvasioneId());
					}
				}

				cpassTOrdImpegnoEvasioneDao.delete(impegnoEvasione.getId());
			}
		}
	}

	public void annullamentoLogicoImpegniEvasioneByRiga(UUID rigaEvasioneId) {
		List<CpassTOrdImpegnoEvasione> impegniEvasiones = cpassTOrdImpegnoEvasioneDao.getImpegniEvasione(rigaEvasioneId);
		if (impegniEvasiones != null) {
			for (CpassTOrdImpegnoEvasione impegnoEvasione : impegniEvasiones) {

				if (impegnoEvasione.getCpassTOrdSubimpegnoEvasiones() != null) {
					for (CpassTOrdSubimpegnoEvasione subimpegnoEvasione : impegnoEvasione.getCpassTOrdSubimpegnoEvasiones()) {
						
						subimpegnoEvasione.setImportoLiquidato(new BigDecimal(0));
						subimpegnoEvasione.setImportoRipartito(new BigDecimal(0));
						subimpegnoEvasione.setImportoSospeso(new BigDecimal(0));
						
						cpassTOrdSubimpegnoEvasioneDao.update(subimpegnoEvasione);
					}
				}
				
				impegnoEvasione.setImportoLiquidato(new BigDecimal(0));
				impegnoEvasione.setImportoRipartito(new BigDecimal(0));
				impegnoEvasione.setImportoSospeso(new BigDecimal(0));

				cpassTOrdImpegnoEvasioneDao.update(impegnoEvasione);
			}
		}
	}

}
