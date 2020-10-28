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
package it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.pba;

import java.util.List;

import it.csi.cpass.cpassbe.ejb.business.be.dao.BaseEntityDao;
import it.csi.cpass.cpassbe.ejb.entity.pba.CpassDPbaRisorsa;

/**
 * Data Access Object interface for the entity CpassDPbaRisorsa
 */
public interface CpassDPbaRisorsaDao extends BaseEntityDao<Integer, CpassDPbaRisorsa> {
	/**
	 * Finds the risorse by tipo
	 * @param tipo the tipo
	 * @return the risorsa
	 */
	List<CpassDPbaRisorsa> getRisorseByTipo(String tipo);

}
