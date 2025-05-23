/*-
 * ========================LICENSE_START=================================
 * CPASS BackEnd - EJB submodule
 * %%
 * Copyright (C) 2019 - 2025 CSI Piemonte
 * %%
 * SPDX-FileCopyrightText: Copyright 2019 - 2025 | CSI Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 * =========================LICENSE_END==================================
 */
package it.csi.cpass.cpassbe.ejb.business.be.dao.impl.cpass;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.CpassRRuoloPermessoDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.impl.BaseEntityDaoImpl;
import it.csi.cpass.cpassbe.ejb.entity.CpassRRuoloPermesso;
import it.csi.cpass.cpassbe.ejb.util.jpa.JpaQueryHelper;
import it.csi.cpass.cpassbe.ejb.util.jpa.Page;

@ApplicationScoped
public class CpassRRuoloPermessoDaoImpl extends BaseEntityDaoImpl<Integer, CpassRRuoloPermesso> implements CpassRRuoloPermessoDao {
	@Override
	public Page<CpassRRuoloPermesso> getPermessiSelezionabili(Integer moduloId,int page,int size, String sortField, String sortDirection) {
		final Map<String, Object> params = new HashMap<>();

		final StringBuilder jpql = new StringBuilder().append("FROM CpassRRuoloPermesso rrp WHERE rrp.cpassDRuolo.selezionabileDaProcedura='SI' ");
		JpaQueryHelper.andFieldEquals(jpql, params, "rrp.cpassDPermesso.cpassDModulo.moduloId", "moduloId", moduloId);
		if (sortField != null ) {
			jpql.append(" ORDER BY ").append(sortField);
			if(sortDirection != null) {
				jpql.append(" ").append(sortDirection);
			}
		}
		if (sortField == null) {
			jpql.append(" ORDER BY rrp.cpassDPermesso.cpassDModulo.moduloCodice DESC, rrp.cpassDRuolo.ruoloCodice DESC, rrp.cpassDPermesso.permessoCodice DESC ");
		}
		final Page<CpassRRuoloPermesso> ris = getPagedResult(jpql, params, page, size);
		return ris;
	}

	@Override
	public void deleteById(Integer ruoloPermessoId) {
		final StringBuilder sb = new StringBuilder();
		sb.append("DELETE FROM CpassRRuoloPermesso rrp");
		sb.append(" WHERE rrp.ruoloPermessoId = :ruoloPermessoId");
		final Map<String, Object> params = new HashMap<>();
		params.put("ruoloPermessoId", ruoloPermessoId);
		final Query query = composeQuery(sb, params);
		query.executeUpdate();
	}

	@Override
	public List<CpassRRuoloPermesso> getRuoloPermessoList(Integer moduloId) {
		final Map<String, Object> params = new HashMap<>();

		final StringBuilder jpql = new StringBuilder().append("FROM CpassRRuoloPermesso rrp WHERE rrp.cpassDRuolo.selezionabileDaProcedura='SI' ");
		JpaQueryHelper.andFieldEquals(jpql, params, "rrp.cpassDPermesso.cpassDModulo.moduloId", "moduloId", moduloId);
		final TypedQuery<CpassRRuoloPermesso> query = composeTypedQuery(jpql, params);
		return query.getResultList();
	}

}
