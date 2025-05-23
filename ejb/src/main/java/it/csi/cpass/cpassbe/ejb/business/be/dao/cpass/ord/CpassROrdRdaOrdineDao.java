/*-
 * ========================LICENSE_START=================================
 * CPASS BackEnd - EJB submodule
 * %%
 * Copyright (C) 2019 - 2021 CSI Piemonte
 * %%
 * SPDX-FileCopyrightText: Copyright 2019 - 2021 | CSI Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 * =========================LICENSE_END==================================
 */
package it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.ord;

import java.util.List;
import java.util.UUID;

import it.csi.cpass.cpassbe.ejb.business.be.dao.BaseEntityDao;
import it.csi.cpass.cpassbe.ejb.entity.ord.CpassROrdRdaOrdine;


public interface CpassROrdRdaOrdineDao extends BaseEntityDao<Integer,CpassROrdRdaOrdine> {

	List<CpassROrdRdaOrdine> findByTestataOrdineId(UUID testataOrdineId);

	void deleteByTestataOrdineTestataRda(UUID testataOrdineId, UUID testataRdaId);

}
