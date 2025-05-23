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
package it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.ord;

import java.util.List;
import java.util.Optional;

import it.csi.cpass.cpassbe.ejb.business.be.dao.BaseAuditedEntityDao;
import it.csi.cpass.cpassbe.ejb.entity.ord.CpassDOrdTipoOrdine;

/**
 * Data Access Object interface for the entity CpassDTipoOrdine
 */
public interface CpassDOrdTipoOrdineDao extends BaseAuditedEntityDao<Integer, CpassDOrdTipoOrdine> {
	/**
	 * Finds the valid types
	 * @return the active types
	 */
	List<CpassDOrdTipoOrdine> findValid();


	List<CpassDOrdTipoOrdine> getListaValidTipoOrdineExcludeCode(String codice);

	/**
	 * Cerca stato per codice
	 * @param codice
	 * @return CpassDOrdTipoOrdine
	 */
	Optional<CpassDOrdTipoOrdine> findValidByCodice(String codice);

}
