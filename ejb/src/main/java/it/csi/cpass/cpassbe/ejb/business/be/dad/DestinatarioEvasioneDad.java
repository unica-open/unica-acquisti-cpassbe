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
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.decodifiche.CpassDStatoDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.ord.evasione.CpassTOrdDestinatarioEvasioneDao;
import it.csi.cpass.cpassbe.ejb.entity.CpassDStato;
import it.csi.cpass.cpassbe.ejb.entity.ord.evasione.CpassTOrdDestinatarioEvasione;
import it.csi.cpass.cpassbe.ejb.exception.NotFoundException;
import it.csi.cpass.cpassbe.ejb.mapper.CpassMappers;
import it.csi.cpass.cpassbe.ejb.util.ConstantsCPassStato;
import it.csi.cpass.cpassbe.lib.dto.ord.evasione.DestinatarioEvasione;

@ApplicationScoped
public class DestinatarioEvasioneDad extends BaseDad {

	@Inject
	private CpassTOrdDestinatarioEvasioneDao cpassTOrdDestinatarioEvasioneDao;
	@Inject
	private CpassDStatoDao cpassDStatoDao;
	/**
	 * 
	 * @param key
	 * @return
	 */
	public DestinatarioEvasione findOne(UUID key) {
		final Optional<CpassTOrdDestinatarioEvasione> cpassTOrdDestinatarioEvasioneOptional = cpassTOrdDestinatarioEvasioneDao.findOne(key);
		if (cpassTOrdDestinatarioEvasioneOptional.isPresent()) {
			final CpassTOrdDestinatarioEvasione cpassTOrdDestinatarioEvasione = cpassTOrdDestinatarioEvasioneOptional.get();
			return CpassMappers.DESTINATARIO_EVASIONE.toModel(cpassTOrdDestinatarioEvasione);
		}
		throw new NotFoundException("destinatario evasione");
	}
	/**
	 * 
	 * @param uuid
	 * @return
	 */
	public Optional<DestinatarioEvasione> getDestinatarioEvasione(UUID uuid) {
		return cpassTOrdDestinatarioEvasioneDao.findOne(uuid).map(CpassMappers.DESTINATARIO_EVASIONE::toModel);
	}
	/**
	 * 
	 * @param idEvasione
	 * @return
	 */
	public List<DestinatarioEvasione> findByEvasione(UUID idEvasione) {
		final List<CpassTOrdDestinatarioEvasione> listaDestinatari = cpassTOrdDestinatarioEvasioneDao.findByTestataEvasione(idEvasione);
		return CpassMappers.DESTINATARIO_EVASIONE.toModels(listaDestinatari);
	}
	/**
	 * 
	 * @param idDestinatarioOrdine
	 * @return
	 */
	public List<DestinatarioEvasione> findByIdDestinatarioOrdine(UUID idDestinatarioOrdine) {
		final List<CpassTOrdDestinatarioEvasione> destinatarioEvasiones = cpassTOrdDestinatarioEvasioneDao.findByIdDestinatarioOrdine(idDestinatarioOrdine);
		return CpassMappers.DESTINATARIO_EVASIONE.toModels(destinatarioEvasiones);
	}
	/**
	 * 
	 * @param idEvasione
	 * @return
	 */
	public List<DestinatarioEvasione> getDestinatariPerCopia(UUID idEvasione) {
		final List<CpassTOrdDestinatarioEvasione> listaDestinatari = cpassTOrdDestinatarioEvasioneDao.findByTestataEvasione(idEvasione);
		final List<DestinatarioEvasione> result = new ArrayList<>();

		for (final CpassTOrdDestinatarioEvasione entity : listaDestinatari) {
			final DestinatarioEvasione tempDest = CpassMappers.DESTINATARIO_EVASIONE.toModel(entity);
			if (entity.getCpassTOrdRigaEvasiones() != null && entity.getCpassTOrdRigaEvasiones().size() > 0) {
				result.add(tempDest);
			}
		}

		return result;
	}

	/**
	 * Inserts the destinatario
	 *
	 * @param destinatarioEvasione the destinatario
	 * @return the model instance
	 */
	public DestinatarioEvasione saveDestinatarioEvasione(DestinatarioEvasione destinatarioEvasione) {
		final CpassTOrdDestinatarioEvasione destinatarioEvasioneEntity = CpassMappers.DESTINATARIO_EVASIONE.toEntity(destinatarioEvasione);

		// incremento il progressivo
		final List<CpassTOrdDestinatarioEvasione> destinatariEvasione = cpassTOrdDestinatarioEvasioneDao
				.findByTestataEvasione(destinatarioEvasione.getTestataEvasione().getId());

		Integer maxProgressivo = 0;
		for (final CpassTOrdDestinatarioEvasione dest : destinatariEvasione) {
			if (dest.getProgressivo() > maxProgressivo) {
				maxProgressivo = dest.getProgressivo();
			}
		}

		maxProgressivo++;
		destinatarioEvasioneEntity.setProgressivo(maxProgressivo);
		final CpassTOrdDestinatarioEvasione aaa = cpassTOrdDestinatarioEvasioneDao.insert(destinatarioEvasioneEntity);
		cpassTOrdDestinatarioEvasioneDao.flush();
		return CpassMappers.DESTINATARIO_EVASIONE.toModel(aaa);
	}
	/**
	 * 
	 * @param destinatarioEvasione
	 * @return
	 */
	public DestinatarioEvasione updateDestinatarioEvasione(DestinatarioEvasione destinatarioEvasione) {
		final CpassTOrdDestinatarioEvasione destinatarioEvasioneEntity = CpassMappers.DESTINATARIO_EVASIONE.toEntity(destinatarioEvasione);
		return CpassMappers.DESTINATARIO_EVASIONE.toModel(cpassTOrdDestinatarioEvasioneDao.update(destinatarioEvasioneEntity));
	}
	/**
	 * 
	 * @param idDestinatarioEvasione
	 */
	public void deleteDestinatarioEvasione(UUID idDestinatarioEvasione) {
		cpassTOrdDestinatarioEvasioneDao.delete(idDestinatarioEvasione);
	}
	/**
	 * 
	 * @param idDestinatarioEvasione
	 */
	public void deleteLogicallyDestinatarioEvasione(UUID idDestinatarioEvasione) {
		cpassTOrdDestinatarioEvasioneDao.deleteLogically(idDestinatarioEvasione);
	}
	/**
	 * 
	 * @param id
	 * @param statoCodice
	 */
	public void updateStatoDestinatarioEvasione(UUID id, String statoCodice) {
		final CpassTOrdDestinatarioEvasione cpassTOrdDestinatarioEvasione = cpassTOrdDestinatarioEvasioneDao.findOne(id).orElseThrow(() -> new NotFoundException("destinatarioEvasione"));

		final Optional<CpassDStato> cpassDStatoOptional = cpassDStatoDao.findByCodiceTipo(statoCodice, ConstantsCPassStato.TipoStatoEnum.DESTINATARIO_EVASIONE.getCostante());
		if (cpassDStatoOptional.isPresent()) {
			final CpassDStato cpassDStato = cpassDStatoOptional.get();
			cpassTOrdDestinatarioEvasione.setCpassDStato(cpassDStato);
			cpassTOrdDestinatarioEvasioneDao.update(cpassTOrdDestinatarioEvasione);
		} else {
			throw new NotFoundException("stato");
		}
	}

}
