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
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.ord.CpassTOrdDestinatarioInvioNsoDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.ord.CpassTOrdDestinatarioInvioNsoXmlDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.ord.CpassTOrdDestinatarioOrdineDao;
import it.csi.cpass.cpassbe.ejb.entity.ord.CpassTOrdDestinatarioInvioNso;
import it.csi.cpass.cpassbe.ejb.entity.ord.CpassTOrdDestinatarioInvioNsoXml;
import it.csi.cpass.cpassbe.ejb.entity.ord.CpassTOrdDestinatarioOrdine;
import it.csi.cpass.cpassbe.ejb.mapper.CpassMappers;
import it.csi.cpass.cpassbe.lib.dto.ord.Destinatario;
import it.csi.cpass.cpassbe.lib.dto.ord.DestinatarioInvioNso;
import it.csi.cpass.cpassbe.lib.dto.ord.DestinatarioInvioNsoXml;

@ApplicationScoped
public class DestinatarioOrdineDad extends BaseDad {

	@Inject
	private CpassTOrdDestinatarioOrdineDao cpassTOrdDestinatarioDao;
	@Inject
	private CpassTOrdDestinatarioInvioNsoDao cpassTOrdDestinatarioInvioNsoDao;
	@Inject
	private CpassTOrdDestinatarioInvioNsoXmlDao cpassTOrdDestinatarioInvioNsoXmlDao;

	/**
	 * 
	 * @param uuid
	 * @return
	 */
	public Optional<Destinatario> getDestinatario(UUID uuid) {
		return cpassTOrdDestinatarioDao.findOne(uuid).map(CpassMappers.DESTINATARIO::toModel);
	}

	/**
	 * 
	 * @param idOrdine
	 * @return
	 */
	public List<Destinatario> findByOrdine(UUID idOrdine) {
		final List<CpassTOrdDestinatarioOrdine> listaDestinatari = cpassTOrdDestinatarioDao.findByOrdine(idOrdine);
		return CpassMappers.DESTINATARIO.toModels(listaDestinatari);
	}

	/**
	 * 
	 * @param idOrdine
	 * @return
	 */
	public List<Destinatario> getDestinatariPerCopia(UUID idOrdine) {
		final List<CpassTOrdDestinatarioOrdine> listaDestinatari = cpassTOrdDestinatarioDao.findByOrdine(idOrdine);
		final List<Destinatario> result = new ArrayList<>();

		for(final CpassTOrdDestinatarioOrdine entity : listaDestinatari) {
			final Destinatario tempDest = CpassMappers.DESTINATARIO.toModel(entity);

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
		final CpassTOrdDestinatarioOrdine destinatarioEntity = CpassMappers.DESTINATARIO.toEntity(destinatario);
		//incremento il progressivo
		final List<CpassTOrdDestinatarioOrdine> destinatariOrdine = cpassTOrdDestinatarioDao.findByOrdine(destinatario.getTestataOrdine().getId());
		Integer maxProgressivo = 0;
		for(final CpassTOrdDestinatarioOrdine dest : destinatariOrdine) {
			if(dest.getProgressivo() > maxProgressivo) {
				maxProgressivo = dest.getProgressivo();
			}
		}
		maxProgressivo++;
		destinatarioEntity.setProgressivo(maxProgressivo);
		return CpassMappers.DESTINATARIO.toModel(cpassTOrdDestinatarioDao.saveAndFlush(destinatarioEntity));
	}
	
	/**
	 * 
	 * @param destinatario
	 * @return
	 */
	public Destinatario updateDestinatario(Destinatario destinatario) {
		CpassTOrdDestinatarioOrdine destinatarioEntity = CpassMappers.DESTINATARIO.toEntity(destinatario);
		destinatarioEntity = cpassTOrdDestinatarioDao.update(destinatarioEntity);
		cpassTOrdDestinatarioDao.flush();
		return CpassMappers.DESTINATARIO.toModel(destinatarioEntity);
	}
	
	/**
	 * 
	 * @param idDestinatario
	 */
	public void deleteDestinatario(UUID idDestinatario) {

		cpassTOrdDestinatarioDao.delete(idDestinatario);
	}

	/**
	 * 
	 * @param idTestata
	 * @param progressivoDestinatario
	 * @return
	 */
	public List<Destinatario> getDestinatarioByTestataAndProgressivo(UUID idTestata, Integer progressivoDestinatario) {
		final List<CpassTOrdDestinatarioOrdine> listaDestinatari = cpassTOrdDestinatarioDao.getDestinatarioByTestataAndProgressivo( idTestata,  progressivoDestinatario);
		return CpassMappers.DESTINATARIO.toModels(listaDestinatari);
	}

	// metodi relativi a invio NSO, Valutare se spostare in DAD dedicato
	/**
	 * 
	 * @param id
	 * @return
	 */
	public Optional<DestinatarioInvioNso> getDestinatarioInvioNso(Integer id) {
		return cpassTOrdDestinatarioInvioNsoDao.findOne(id).map(CpassMappers.DESTINATARIO_INVIO_NSO::toModel);
	}
	
	/**
	 * 
	 * @param idDestinatario
	 * @return
	 */
	public Optional<DestinatarioInvioNso> getUltimoInvioNsoByDestinatario(UUID idDestinatario) {
		return cpassTOrdDestinatarioInvioNsoDao.getUltimoInvioByDestinatario(idDestinatario).map(CpassMappers.DESTINATARIO_INVIO_NSO::toModel);
	}
	/**
	 * 
	 * @param idTestataOrdine
	 * @return
	 */
	public Optional<DestinatarioInvioNso> getUltimoInvioNsoByOrdine(UUID idTestataOrdine) {
		return cpassTOrdDestinatarioInvioNsoDao.getUltimoInvioByOrdine(idTestataOrdine).map(CpassMappers.DESTINATARIO_INVIO_NSO::toModel);
	}
	/**
	 * 
	 * @param model
	 * @return
	 */
	public DestinatarioInvioNso saveDestinatarioInvioNso(DestinatarioInvioNso model) {
		final CpassTOrdDestinatarioInvioNso entity = CpassMappers.DESTINATARIO_INVIO_NSO.toEntity(model);
		return CpassMappers.DESTINATARIO_INVIO_NSO.toModel(cpassTOrdDestinatarioInvioNsoDao.insert(entity));
	}
	/**
	 * 
	 * @param model
	 * @return
	 */
	public DestinatarioInvioNsoXml insertDestinatarioInvioNsoXml(DestinatarioInvioNsoXml model) {
		final CpassTOrdDestinatarioInvioNsoXml entity = CpassMappers.DESTINATARIO_INVIO_NSO_XML.toEntity(model);
		return CpassMappers.DESTINATARIO_INVIO_NSO_XML.toModel(cpassTOrdDestinatarioInvioNsoXmlDao.insert(entity));
	}
	/**
	 * 
	 * @param model
	 * @return
	 */
	public DestinatarioInvioNsoXml saveDestinatarioInvioNsoXml(DestinatarioInvioNsoXml model) {
		final CpassTOrdDestinatarioInvioNsoXml entity = CpassMappers.DESTINATARIO_INVIO_NSO_XML.toEntity(model);
		return CpassMappers.DESTINATARIO_INVIO_NSO_XML.toModel(cpassTOrdDestinatarioInvioNsoXmlDao.save(entity));
	}
	/**
	 * 
	 * @param model
	 * @return
	 */
	public DestinatarioInvioNso updateDestinatarioInvioNso(DestinatarioInvioNso model) {
		final CpassTOrdDestinatarioInvioNso entity = CpassMappers.DESTINATARIO_INVIO_NSO.toEntity(model);
		return CpassMappers.DESTINATARIO_INVIO_NSO.toModel(cpassTOrdDestinatarioInvioNsoDao.update(entity));
	}
	/**
	 * 
	 * @param idNotier
	 * @return
	 */
	public List<DestinatarioInvioNso> findDestinatarioInvioNsoSenzaNotifica(String idNotier){
		final List<CpassTOrdDestinatarioInvioNso> entities = cpassTOrdDestinatarioInvioNsoDao.findByIdNotier(idNotier);
		return CpassMappers.DESTINATARIO_INVIO_NSO.toModels(entities);
	}
	/**
	 * 
	 * @param idEnte
	 * @param daysNumb
	 * @return
	 */
	public List<DestinatarioInvioNsoXml> findDestinatarioInvioNsoByEnteDaStoricizzare(UUID idEnte,Date daysNumb){
		final List<CpassTOrdDestinatarioInvioNsoXml> entities = cpassTOrdDestinatarioInvioNsoXmlDao.findDestinatarioInvioNsoByEnteDaStoricizzare(idEnte,daysNumb);
		return CpassMappers.DESTINATARIO_INVIO_NSO_XML.toModels(entities);
	}
	/**
	 * 
	 * @param testataOrdineId
	 */
	public void deleteDestFromTestataordine(UUID testataOrdineId) {
		cpassTOrdDestinatarioDao.deleteFromTestataordine(testataOrdineId);
	}
	/**
	 * 
	 * @param testataOrdineId
	 */
	public void updateDestFromTestataordine(UUID testataOrdineId) {
		cpassTOrdDestinatarioDao.updateFromTestataordine(testataOrdineId);
	}

}
