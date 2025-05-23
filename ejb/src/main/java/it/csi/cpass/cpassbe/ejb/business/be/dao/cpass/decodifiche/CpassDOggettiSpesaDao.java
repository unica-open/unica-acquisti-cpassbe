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
package it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.decodifiche;

import java.util.List;
import java.util.UUID;

import it.csi.cpass.cpassbe.ejb.business.be.dao.BaseAuditedEntityDao;
import it.csi.cpass.cpassbe.ejb.entity.CpassDOggettiSpesa;
import it.csi.cpass.cpassbe.ejb.util.jpa.Page;

/**
 * Data Access Object interface for the entity CpassDTipoOrdine
 */
public interface CpassDOggettiSpesaDao extends 	BaseAuditedEntityDao<Integer, CpassDOggettiSpesa> {


	CpassDOggettiSpesa findByCodice(String codice,UUID enteId);

	Page<CpassDOggettiSpesa> findPaginated(
			Boolean inventariabile
			,String codice
			,String descrizione
			,Integer aliquoteIvaid
			,Integer cpvId
			,String cpvCodice
			,Integer unitaMisuraId
			,Boolean generico
			,Boolean odsValidi
			,UUID enteId
			,int page
			,int size
			,String sortField
			,String sortDirection
			);

	List<CpassDOggettiSpesa> findOdsByCpvId(Integer cpvId);

	List<CpassDOggettiSpesa> getOdsByEnteId(UUID enteId);

}
