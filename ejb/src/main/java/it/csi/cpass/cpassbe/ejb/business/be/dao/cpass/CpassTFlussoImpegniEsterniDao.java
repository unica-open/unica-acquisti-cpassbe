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
import it.csi.cpass.cpassbe.ejb.entity.CpassTFlussoImpegniEsterni;

/**
 * Data Access Object interface for the entity CpassTFlussoImpegniEsterni
 */
public interface CpassTFlussoImpegniEsterniDao extends BaseEntityDao<Integer, CpassTFlussoImpegniEsterni> {
	List<CpassTFlussoImpegniEsterni> getFlussoImpegniEsterniByEnte     (UUID enteId,String esito,Integer limit,Integer offset, Date dataCaricamento, Integer numelab);
	List<CpassTFlussoImpegniEsterni> getFlussoImpegniEsterniElaborabili(UUID enteId,String esito,Integer limit,Integer offset, Date dataCaricamento, Integer numelab);
	void resetSequence();
	void deleteAll(Date dataStorico);
}
