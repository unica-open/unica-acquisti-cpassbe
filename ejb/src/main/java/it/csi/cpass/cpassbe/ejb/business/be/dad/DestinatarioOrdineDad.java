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

import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.ord.CpassTOrdDestinatarioOrdineDao;
import it.csi.cpass.cpassbe.ejb.entity.ord.CpassTOrdDestinatarioOrdine;
import it.csi.cpass.cpassbe.ejb.mapper.CpassMappers;
import it.csi.cpass.cpassbe.lib.dto.ord.Destinatario;

@ApplicationScoped
public class DestinatarioOrdineDad extends BaseDad {
	
	@Inject
	private CpassTOrdDestinatarioOrdineDao cpassTOrdDestinatarioDao;
	
	public Optional<Destinatario> getDestinatario(UUID uuid) {
		return cpassTOrdDestinatarioDao.findOne(uuid).map(CpassMappers.DESTINATARIO::toModel);
	}
	
	
	public List<Destinatario> findByOrdine(UUID idOrdine) {
		List<CpassTOrdDestinatarioOrdine> listaDestinatari = cpassTOrdDestinatarioDao.findByOrdine(idOrdine);
		return CpassMappers.DESTINATARIO.toModels(listaDestinatari);
	}
	
	public List<Destinatario> getDestinatariPerCopia(UUID idOrdine) {
		List<CpassTOrdDestinatarioOrdine> listaDestinatari = cpassTOrdDestinatarioDao.findByOrdine(idOrdine);
		List<Destinatario> result = new ArrayList<Destinatario>();
		
		for(CpassTOrdDestinatarioOrdine entity : listaDestinatari) {
			Destinatario tempDest = CpassMappers.DESTINATARIO.toModel(entity);
			
			if(entity.getCpassTOrdRigaOrdines() != null && entity.getCpassTOrdRigaOrdines().size() > 0) {
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
	public Destinatario saveDestinatario(Destinatario destinatario) {
		
		CpassTOrdDestinatarioOrdine destinatarioEntity = CpassMappers.DESTINATARIO.toEntity(destinatario);
		
		
		//incremento il progressivo
		List<CpassTOrdDestinatarioOrdine> destinatariOrdine = cpassTOrdDestinatarioDao.findByOrdine(destinatario.getTestataOrdine().getId());
		
		Integer maxProgressivo = 0;
		
		for(CpassTOrdDestinatarioOrdine dest : destinatariOrdine) {
			if(dest.getProgressivo() > maxProgressivo) {
				maxProgressivo = dest.getProgressivo();
			}
		}
		
		destinatarioEntity.setProgressivo(++maxProgressivo);
		
		return CpassMappers.DESTINATARIO.toModel(cpassTOrdDestinatarioDao.insert(destinatarioEntity));
	}
	
	public Destinatario updateDestinatario(Destinatario destinatario) {
		
		CpassTOrdDestinatarioOrdine destinatarioEntity = CpassMappers.DESTINATARIO.toEntity(destinatario);
		
		return CpassMappers.DESTINATARIO.toModel(cpassTOrdDestinatarioDao.update(destinatarioEntity));
	}
	
	public void deleteDestinatario(UUID idDestinatario) {
		cpassTOrdDestinatarioDao.delete(idDestinatario);
	}

}
