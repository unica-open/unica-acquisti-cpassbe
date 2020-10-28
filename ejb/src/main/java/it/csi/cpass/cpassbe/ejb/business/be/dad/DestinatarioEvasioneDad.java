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

import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.ord.evasione.CpassTOrdDestinatarioEvasioneDao;
import it.csi.cpass.cpassbe.ejb.entity.ord.evasione.CpassTOrdDestinatarioEvasione;
import it.csi.cpass.cpassbe.ejb.entity.ord.evasione.CpassTOrdRigaEvasione;
import it.csi.cpass.cpassbe.ejb.mapper.CpassMappers;
import it.csi.cpass.cpassbe.lib.dto.ord.evasione.DestinatarioEvasione;
import it.csi.cpass.cpassbe.lib.dto.ord.evasione.RigaEvasione;

@ApplicationScoped
public class DestinatarioEvasioneDad extends BaseDad {

	@Inject
	private CpassTOrdDestinatarioEvasioneDao cpassTOrdDestinatarioEvasioneDao;

	public DestinatarioEvasione findOne(UUID key) {
		CpassTOrdDestinatarioEvasione cpassTOrdDestinatarioEvasione = cpassTOrdDestinatarioEvasioneDao.findOne(key).get();
		return CpassMappers.DESTINATARIO_EVASIONE.toModel(cpassTOrdDestinatarioEvasione);
	}
	
	public Optional<DestinatarioEvasione> getDestinatarioEvasione(UUID uuid) {
		return cpassTOrdDestinatarioEvasioneDao.findOne(uuid).map(CpassMappers.DESTINATARIO_EVASIONE::toModel);
	}

	public List<DestinatarioEvasione> findByEvasione(UUID idEvasione) {
		List<CpassTOrdDestinatarioEvasione> listaDestinatari = cpassTOrdDestinatarioEvasioneDao.findByTestataEvasione(idEvasione);
		return CpassMappers.DESTINATARIO_EVASIONE.toModels(listaDestinatari);
	}
	
	public List<DestinatarioEvasione> findByIdDestinatarioOrdine(UUID idDestinatarioOrdine) {
		List<CpassTOrdDestinatarioEvasione> destinatarioEvasiones = cpassTOrdDestinatarioEvasioneDao.findByIdDestinatarioOrdine(idDestinatarioOrdine);
		return CpassMappers.DESTINATARIO_EVASIONE.toModels(destinatarioEvasiones);
	}

	public List<DestinatarioEvasione> getDestinatariPerCopia(UUID idEvasione) {
		List<CpassTOrdDestinatarioEvasione> listaDestinatari = cpassTOrdDestinatarioEvasioneDao.findByTestataEvasione(idEvasione);
		List<DestinatarioEvasione> result = new ArrayList<DestinatarioEvasione>();

		for (CpassTOrdDestinatarioEvasione entity : listaDestinatari) {
			DestinatarioEvasione tempDest = CpassMappers.DESTINATARIO_EVASIONE.toModel(entity);
			if (entity.getCpassTOrdRigaEvasiones() != null && entity.getCpassTOrdRigaEvasiones().size() > 0) {
				result.add(tempDest);
			}
		}

		return result;
	}

	/**
	 * Inserts the destinatario
	 * 
	 * @param destinatario the destinatario
	 * @return the model instance
	 */
	public DestinatarioEvasione saveDestinatarioEvasione(DestinatarioEvasione destinatarioEvasione) {
		CpassTOrdDestinatarioEvasione destinatarioEvasioneEntity = CpassMappers.DESTINATARIO_EVASIONE.toEntity(destinatarioEvasione);

		// incremento il progressivo
		List<CpassTOrdDestinatarioEvasione> destinatariEvasione = cpassTOrdDestinatarioEvasioneDao
				.findByTestataEvasione(destinatarioEvasione.getTestataEvasione().getId());

		Integer maxProgressivo = 0;
		for (CpassTOrdDestinatarioEvasione dest : destinatariEvasione) {
			if (dest.getProgressivo() > maxProgressivo) {
				maxProgressivo = dest.getProgressivo();
			}
		}

		destinatarioEvasioneEntity.setProgressivo(++maxProgressivo);
		return CpassMappers.DESTINATARIO_EVASIONE.toModel(cpassTOrdDestinatarioEvasioneDao.insert(destinatarioEvasioneEntity));
	}

	public DestinatarioEvasione updateDestinatarioEvasione(DestinatarioEvasione destinatarioEvasione) {
		CpassTOrdDestinatarioEvasione destinatarioEvasioneEntity = CpassMappers.DESTINATARIO_EVASIONE.toEntity(destinatarioEvasione);
		return CpassMappers.DESTINATARIO_EVASIONE.toModel(cpassTOrdDestinatarioEvasioneDao.update(destinatarioEvasioneEntity));
	}

	public void deleteDestinatarioEvasione(UUID idDestinatarioEvasione) {
		cpassTOrdDestinatarioEvasioneDao.delete(idDestinatarioEvasione);
	}

	public void deleteLogicallyDestinatarioEvasione(UUID idDestinatarioEvasione) {
		cpassTOrdDestinatarioEvasioneDao.deleteLogically(idDestinatarioEvasione);
	}

}
