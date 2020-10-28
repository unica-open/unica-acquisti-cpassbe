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

import it.csi.cpass.cpassbe.ejb.business.be.dao.BaseEntityDao;
import it.csi.cpass.cpassbe.ejb.entity.ord.CpassDStatoElOrdine;

/**
 * Data Access Object interface for the entity CpassDPbaNuts
 */
public interface CpassDStatoElOrdineDao extends BaseEntityDao<Integer, CpassDStatoElOrdine> {
	/**
	 * Retrieves the statos by tipo
	 * @param statoTipo the stato tipo
	 * @return the statos
	 */
	List<CpassDStatoElOrdine> getStatoElOrdineByTipo(String statoTipo);
	
	CpassDStatoElOrdine findByCodiceTipo(String codice, String statoTipo);

}
