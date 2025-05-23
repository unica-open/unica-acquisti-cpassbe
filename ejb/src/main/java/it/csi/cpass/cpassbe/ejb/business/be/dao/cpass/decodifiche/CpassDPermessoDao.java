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

import it.csi.cpass.cpassbe.ejb.business.be.dao.BaseEntityDao;
import it.csi.cpass.cpassbe.ejb.entity.CpassDPermesso;

/**
 * Data Access Object interface for the entity CpassDPermesso
 */
public interface CpassDPermessoDao extends BaseEntityDao<Integer, CpassDPermesso> {

	/**
	 * Gets by utente id and settore id and modulo id
	 * @param utenteId the utente id
	 * @param settoreId the settore id
	 * @param moduloId the modulo id
	 * @return the entities
	 */
	List<CpassDPermesso> getByUtenteIdAndSettoreIdAndModuloId(UUID utenteId, UUID settoreId, Integer moduloId);

	/**
	 * Gets by utente id and settore id and modulo id
	 * @param utenteId the utente id
	 * @param settoreId the settore id
	 * @return the entities
	 */
	List<CpassDPermesso> getByUtenteIdAndSettoreId(UUID utenteId, UUID settoreId);

	List<CpassDPermesso> getPermessiByModuliAndDisattivabile(String[] moduli, String disattivabile);
}
