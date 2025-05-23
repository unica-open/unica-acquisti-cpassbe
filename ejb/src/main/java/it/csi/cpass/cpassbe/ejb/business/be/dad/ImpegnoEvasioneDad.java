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
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.ord.evasione.CpassTOrdImpegnoEvasioneDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.ord.evasione.CpassTOrdSubimpegnoEvasioneDao;
import it.csi.cpass.cpassbe.ejb.entity.ord.evasione.CpassTOrdImpegnoEvasione;
import it.csi.cpass.cpassbe.ejb.entity.ord.evasione.CpassTOrdSubimpegnoEvasione;
import it.csi.cpass.cpassbe.ejb.mapper.CpassMappers;
import it.csi.cpass.cpassbe.lib.dto.ord.evasione.ImpegnoEvasione;
import it.csi.cpass.cpassbe.lib.dto.ord.evasione.RigaEvasione;
import it.csi.cpass.cpassbe.lib.dto.ord.evasione.SubimpegnoEvasione;

@ApplicationScoped
public class ImpegnoEvasioneDad extends BaseDad {

	@Inject
	private CpassTOrdImpegnoEvasioneDao cpassTOrdImpegnoEvasioneDao;
	@Inject
	private CpassTOrdSubimpegnoEvasioneDao cpassTOrdSubimpegnoEvasioneDao;
	/**
	 * 
	 * @param impegnoEvasione
	 * @return
	 */
	public ImpegnoEvasione insert(ImpegnoEvasione impegnoEvasione) {
		final CpassTOrdImpegnoEvasione cpassTOrdImpegnoEvasione = CpassMappers.IMPEGNO_EVASIONE.toEntity(impegnoEvasione);
		final ImpegnoEvasione result = CpassMappers.IMPEGNO_EVASIONE.toModel(cpassTOrdImpegnoEvasioneDao.insert(cpassTOrdImpegnoEvasione));
		return result;
	}

	/**
	 *
	 * @param impegnoEvasione
	 * @return ImpegnoEvasione
	 */
	public ImpegnoEvasione update(ImpegnoEvasione impegnoEvasione) {
		final CpassTOrdImpegnoEvasione cpassTOrdImpegnoEvasione = CpassMappers.IMPEGNO_EVASIONE.toEntity(impegnoEvasione);
		final ImpegnoEvasione result = CpassMappers.IMPEGNO_EVASIONE.toModel(cpassTOrdImpegnoEvasioneDao.update(cpassTOrdImpegnoEvasione));
		return result;
	}
	/**
	 *
	 * @param idRigaEvasione
	 * @return List<ImpegnoEvasione>
	 */
	public List<ImpegnoEvasione> getImpegniByRigaEvasione(UUID idRigaEvasione) {
		final List<ImpegnoEvasione> impegnoEvasionesRet = new ArrayList<>();
		// impegni
		final List<CpassTOrdImpegnoEvasione> impegnoEvasiones = cpassTOrdImpegnoEvasioneDao.getImpegniEvasione(idRigaEvasione);
		if (impegnoEvasiones != null) {
			for (final CpassTOrdImpegnoEvasione cpassTOrdImpegnoEvasione : impegnoEvasiones) {
				final ImpegnoEvasione impegnoEvasione = CpassMappers.IMPEGNO_EVASIONE.toModel(cpassTOrdImpegnoEvasione);
				impegnoEvasione.setImportoRipartito(cpassTOrdImpegnoEvasione.getImportoRipartito());
				impegnoEvasione.setImportoSospeso(cpassTOrdImpegnoEvasione.getImportoSospeso());
				impegnoEvasione.setImportoLiquidato(cpassTOrdImpegnoEvasione.getImportoLiquidato());
				// se sono in presenza di subimpegni
				final List<CpassTOrdSubimpegnoEvasione> subimpegnoEvasiones = cpassTOrdSubimpegnoEvasioneDao.getSubimpegni(cpassTOrdImpegnoEvasione.getImpegnoEvasioneId());
				if (subimpegnoEvasiones != null) {
					//inizializzo l'array subimpegni e' necessario dato che in alcuni casi risulta essere già popolato
					impegnoEvasione.setSubimpegnoEvasiones(new ArrayList<>());
					for (final CpassTOrdSubimpegnoEvasione cpassTOrdSubimpegnoEvasione : subimpegnoEvasiones) {

						final SubimpegnoEvasione subimpegnoEvasione = CpassMappers.SUBIMPEGNO_EVASIONE.toModel(cpassTOrdSubimpegnoEvasione);

						subimpegnoEvasione.setImportoRipartito(subimpegnoEvasione.getImportoRipartito());
						subimpegnoEvasione.setImportoSospeso(subimpegnoEvasione.getImportoSospeso());
						subimpegnoEvasione.setImportoLiquidato(subimpegnoEvasione.getImportoLiquidato());

						// calcolo TotaleRipartibile
						final BigDecimal totaleOrdinato = subimpegnoEvasione.getSubimpegnoOrdine().getSubimpegnoImporto();
						final BigDecimal totaleGiaEvaso = cpassTOrdSubimpegnoEvasioneDao.calcolaTotaleEvaso(subimpegnoEvasione.getSubimpegnoOrdine().getId());
						BigDecimal totaleRipartibile = totaleOrdinato.subtract(totaleGiaEvaso);

						totaleRipartibile = totaleRipartibile.add(subimpegnoEvasione.getImportoRipartito()).add(subimpegnoEvasione.getImportoSospeso());
						subimpegnoEvasione.setTotaleRipartibile(totaleRipartibile);
						impegnoEvasione.getSubimpegnoEvasiones().add(subimpegnoEvasione);
					}
				} else {
					// calcolo TotaleRipartibile
					final BigDecimal totaleOrdinato = impegnoEvasione.getImpegnoOrdine().getImporto();
					final BigDecimal totaleGiaEvaso = cpassTOrdImpegnoEvasioneDao.calcolaTotaleEvaso(impegnoEvasione.getImpegnoOrdine().getId());
					BigDecimal totaleRipartibile = totaleOrdinato.subtract(totaleGiaEvaso);
					// fix NullPointerException
					final BigDecimal impRip = impegnoEvasione.getImportoRipartito() == null ? BigDecimal.ZERO : impegnoEvasione.getImportoRipartito();
					impegnoEvasione.setImportoRipartito(impRip);
					// fix NullPointerException
					final BigDecimal impSospeso = impegnoEvasione.getImportoSospeso() == null ? BigDecimal.ZERO : impegnoEvasione.getImportoSospeso();
					impegnoEvasione.setImportoSospeso(impSospeso);

					totaleRipartibile = totaleRipartibile.add(impRip).add(impSospeso);
					impegnoEvasione.setTotaleRipartibile(totaleRipartibile);
				}
				impegnoEvasionesRet.add(impegnoEvasione);
			}
		}
		return impegnoEvasionesRet;
	}

	/**
	 *
	 * @param impegnoEvasione
	 * @return ImpegnoEvasione
	 */
	public ImpegnoEvasione saveImpegnoEvasione(ImpegnoEvasione impegnoEvasione) {
		final CpassTOrdImpegnoEvasione impegnoEvasioneEntity = CpassMappers.IMPEGNO_EVASIONE.toEntity(impegnoEvasione);
		// incremento il progressivo
		if (impegnoEvasione.getImpegnoProgressivo() == null || impegnoEvasione.getImpegnoProgressivo().equals(0)) {
			Integer maxProgressivo = 0;
			final List<CpassTOrdImpegnoEvasione> righeEvasione = cpassTOrdImpegnoEvasioneDao.findByIdRigaEvasione(impegnoEvasione.getRigaEvasione().getId());
			for (final CpassTOrdImpegnoEvasione riga : righeEvasione) {
				if (riga.getImpegnoProgressivo() > maxProgressivo) {
					maxProgressivo = riga.getImpegnoProgressivo();
				}
			}
			maxProgressivo++;
			impegnoEvasioneEntity.setImpegnoProgressivo(maxProgressivo);
		}
		final CpassTOrdImpegnoEvasione ris = cpassTOrdImpegnoEvasioneDao.insert(impegnoEvasioneEntity);
		cpassTOrdImpegnoEvasioneDao.flush();
		final ImpegnoEvasione result = CpassMappers.IMPEGNO_EVASIONE.toModel(ris);
		return result;
	}
	/**
	 *
	 * @param impegnoOrdineId
	 * @return List<ImpegnoEvasione>
	 */
	public List<ImpegnoEvasione> findByIdImpegnoOrdine(UUID impegnoOrdineId) {
		final List<CpassTOrdImpegnoEvasione> cpassTOrdImpegnoEvasiones = cpassTOrdImpegnoEvasioneDao.findByIdImpegnoOrdine(impegnoOrdineId);
		final List<ImpegnoEvasione> impegnoEvasiones = CpassMappers.IMPEGNO_EVASIONE.toModels(cpassTOrdImpegnoEvasiones);
		return impegnoEvasiones;
	}
	/**
	 *
	 * @param impegnoOrdineId
	 * @return BigDecimal
	 */
	public BigDecimal calcolaTotaleEvaso(UUID impegnoOrdineId) {
		return cpassTOrdImpegnoEvasioneDao.calcolaTotaleEvaso(impegnoOrdineId);
	}
	/**
	 *
	 * @param impegnoAnno
	 * @param impegnoNumero
	 * @return BigDecimal
	 */
	public BigDecimal calcolaTotale(Integer impegnoAnno, Integer impegnoNumero, UUID testataEvasioneId) {
		return cpassTOrdImpegnoEvasioneDao.calcolaTotale(impegnoAnno, impegnoNumero, testataEvasioneId);
	}
	/**
	 *
	 * @param rigaEvasioneId
	 */
	public void deleteImpegniEvasioneByRiga(UUID rigaEvasioneId) {
		final List<CpassTOrdImpegnoEvasione> impegniEvasiones = cpassTOrdImpegnoEvasioneDao.getImpegniEvasione(rigaEvasioneId);
		if ( impegniEvasiones != null && !impegniEvasiones.isEmpty()) {
			for (final CpassTOrdImpegnoEvasione impegnoEvasione : impegniEvasiones) {

				if (impegnoEvasione.getCpassTOrdSubimpegnoEvasiones() != null) {
					for (final CpassTOrdSubimpegnoEvasione subImpegno : impegnoEvasione.getCpassTOrdSubimpegnoEvasiones()) {
						cpassTOrdSubimpegnoEvasioneDao.delete(subImpegno.getSubimpegnoEvasioneId());
					}
				}
				cpassTOrdImpegnoEvasioneDao.delete(impegnoEvasione.getId());
			}
			cpassTOrdImpegnoEvasioneDao.flushAndClear();
		}
	}
	/**
	 *
	 * @param rigaEvasioneId
	 */
	public void annullamentoLogicoImpegniEvasioneByRiga(UUID rigaEvasioneId) {
		final List<CpassTOrdImpegnoEvasione> impegniEvasiones = cpassTOrdImpegnoEvasioneDao.getImpegniEvasione(rigaEvasioneId);
		if (impegniEvasiones != null) {
			for (final CpassTOrdImpegnoEvasione impegnoEvasione : impegniEvasiones) {

				if (impegnoEvasione.getCpassTOrdSubimpegnoEvasiones() != null) {
					for (final CpassTOrdSubimpegnoEvasione subimpegnoEvasione : impegnoEvasione.getCpassTOrdSubimpegnoEvasiones()) {
						subimpegnoEvasione.setImportoLiquidato(BigDecimal.ZERO);
						subimpegnoEvasione.setImportoRipartito(BigDecimal.ZERO);
						subimpegnoEvasione.setImportoSospeso(BigDecimal.ZERO);
						cpassTOrdSubimpegnoEvasioneDao.update(subimpegnoEvasione);
					}
				}
				impegnoEvasione.setImportoLiquidato(BigDecimal.ZERO);
				impegnoEvasione.setImportoRipartito(BigDecimal.ZERO);
				impegnoEvasione.setImportoSospeso(BigDecimal.ZERO);
				cpassTOrdImpegnoEvasioneDao.update(impegnoEvasione);
			}
		}
	}
	/**
	 *
	 * @param uuid
	 * @return
	 */
	public Optional<ImpegnoEvasione> getImpegnoEvasione(UUID uuid) {
		return cpassTOrdImpegnoEvasioneDao.findOne(uuid).map(CpassMappers.IMPEGNO_EVASIONE::toModel);
	}
	/**
	 *
	 * @param idRigaEvasione
	 * @return List<ImpegnoEvasione>
	 */
	public List<ImpegnoEvasione> getByIdRigaEvasione(UUID idRigaEvasione) {
		final List<CpassTOrdImpegnoEvasione> entities = cpassTOrdImpegnoEvasioneDao.getImpegniEvasione(idRigaEvasione);
		return CpassMappers.IMPEGNO_EVASIONE.toModels(entities);
	}
	/**
	 *
	 * @param rigaEvasiones
	 * @param impegnoAnno
	 * @param impegnoNumero
	 * @return List<ImpegnoEvasione>
	 */
	public List<ImpegnoEvasione> getByIdsRigaEvasioneEImpegno(List<RigaEvasione> rigaEvasiones, Integer impegnoAnno, Integer impegnoNumero) {
		final List<CpassTOrdImpegnoEvasione> entities = cpassTOrdImpegnoEvasioneDao.findByIdsRigaEvasioneEImpegno(rigaEvasiones, impegnoAnno, impegnoNumero);
		return CpassMappers.IMPEGNO_EVASIONE.toModels(entities);
	}
	/**
	 *
	 * @param rigaEvasiones
	 * @param impegnoAnno
	 * @param impegnoNumero
	 * @param subimpegnoAnno
	 * @param subimpegnoNumero
	 * @return List<SubimpegnoEvasione>
	 */
	public List<SubimpegnoEvasione> getByIdsRigaEvasioneESubimpegno(List<RigaEvasione> rigaEvasiones, Integer impegnoAnno, Integer impegnoNumero,Integer subimpegnoAnno, Integer subimpegnoNumero) {
		final List<CpassTOrdSubimpegnoEvasione> entities = cpassTOrdSubimpegnoEvasioneDao.findByIdsRigaEvasioneESubimpegno(rigaEvasiones, impegnoAnno, impegnoNumero, subimpegnoAnno, subimpegnoNumero);
		return CpassMappers.SUBIMPEGNO_EVASIONE.toModels(entities);
	}
}
