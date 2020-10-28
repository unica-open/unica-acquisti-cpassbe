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
package it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.ord;

import java.util.List;
import java.util.Optional;

import it.csi.cpass.cpassbe.ejb.business.be.dao.BaseEntityDao;
import it.csi.cpass.cpassbe.ejb.entity.ord.CpassDOrdStatoNso;

/**
 * Data Access Object interface for the entity CpassDOrdStatoNso
 */
public interface CpassDOrdStatoNsoDao extends BaseEntityDao<Integer, CpassDOrdStatoNso> {
	
	/**
	 * Retrieves the stato by tipo
	 * @param statoTipo the stato tipo
	 * @return the statos
	 */
	List<CpassDOrdStatoNso> getStatoNsosByTipo(String statoNsoTipo);
	
	Optional<CpassDOrdStatoNso> findByCodiceTipo(String statoNsoCodice, String statoNsoTipo);

}
