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
package it.csi.cpass.cpassbe.ejb.business.be.dao.cpass;

import it.csi.cpass.cpassbe.ejb.business.be.dao.BaseEntityDao;
import it.csi.cpass.cpassbe.ejb.entity.ord.CpassDOggettiSpesa;
import it.csi.cpass.cpassbe.ejb.util.jpa.Page;

/**
 * Data Access Object interface for the entity CpassDTipoOrdine
 */
public interface CpassDOggettiSpesaDao extends BaseEntityDao<Integer, CpassDOggettiSpesa> {
	
	CpassDOggettiSpesa findByCodice(String codice);

	Page<CpassDOggettiSpesa> findPaginated(
			Boolean inventariabile
			,String codice
			,String descrizione
			,Integer aliquoteIvaid
			,Integer cpvId
			,String cpvCodice
			,Integer unitaMisuraId
			,int page
			,int size
			,String sortField
			,String sortDirection
	);

}
