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
package it.csi.cpass.cpassbe.ejb.business.be.dao.cpass;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import it.csi.cpass.cpassbe.ejb.business.be.dao.BaseEntityDao;
import it.csi.cpass.cpassbe.ejb.entity.CpassTFlussoSubimpegniEsterni;

/**
 * Data Access Object interface for the entity CpassTFlussoSubimpegniEsterni
 */
public interface CpassTFlussoSubImpegniEsterniDao extends BaseEntityDao<Integer, CpassTFlussoSubimpegniEsterni> {

	List<CpassTFlussoSubimpegniEsterni> getFlussoSubImpegniEsterniByEnte(UUID enteId,String esito,Integer limit, Integer offset, Date dataCaricamento, Integer numelab);

	void resetSequence();

	void deleteAll(Date dataStorico);


}
