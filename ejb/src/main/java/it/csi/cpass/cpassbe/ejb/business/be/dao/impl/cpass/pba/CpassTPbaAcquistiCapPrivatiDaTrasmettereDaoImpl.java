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
package it.csi.cpass.cpassbe.ejb.business.be.dao.impl.cpass.pba;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.pba.CpassTPbaAcquistiCapPrivatiDaTrasmettereDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.impl.BaseEntityDaoImpl;
import it.csi.cpass.cpassbe.ejb.entity.pba.CpassTPbaAcquistiCapPrivatiDaTrasmettere;
import it.csi.cpass.cpassbe.ejb.util.jpa.JpaQueryHelper;


@ApplicationScoped
public class  CpassTPbaAcquistiCapPrivatiDaTrasmettereDaoImpl extends BaseEntityDaoImpl<Integer, CpassTPbaAcquistiCapPrivatiDaTrasmettere> implements CpassTPbaAcquistiCapPrivatiDaTrasmettereDao {


	@Override
	public void deleteByProgrammaId(UUID programmaId) {
		final StringBuilder sb = new StringBuilder();
		sb.append("DELETE FROM CpassTPbaAcquistiCapPrivatiDaTrasmettere tab1 ");
		sb.append(" WHERE tab1.programmaId = :programmaId");
		final Map<String, Object> params = new HashMap<>();
		params.put("programmaId", programmaId);
		final Query query = composeQuery(sb, params);
		query.executeUpdate();
	}

	@Override
	public List<CpassTPbaAcquistiCapPrivatiDaTrasmettere> getAcquistiDaTrasmettereCapPrivatoByIntgerventoId(UUID interventoId) {
		final Map<String, Object> params = new HashMap<>();
		final StringBuilder jpql = new StringBuilder().append("FROM CpassTPbaAcquistiCapPrivatiDaTrasmettere int  WHERE 1=1 ");
		JpaQueryHelper.andFieldEquals(jpql, params, "int.interventoId", "interventoId", interventoId);
		jpql.append(" order by int.interventoId");
		final TypedQuery<CpassTPbaAcquistiCapPrivatiDaTrasmettere> query = composeTypedQuery(jpql, params);
		return query.getResultList();	}

}