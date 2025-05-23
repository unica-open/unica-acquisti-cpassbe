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
import javax.persistence.TypedQuery;

import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.CpassRModuloRuoloPermessoDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.impl.BaseEntityDaoImpl;
import it.csi.cpass.cpassbe.ejb.entity.CpassRModuloRuoloPermesso;
import it.csi.cpass.cpassbe.ejb.util.jpa.JpaQueryHelper;
import it.csi.cpass.cpassbe.ejb.util.jpa.Page;

@ApplicationScoped
public class CpassRModuloRuoloPermessoDaoImpl extends BaseEntityDaoImpl<Integer, CpassRModuloRuoloPermesso> implements CpassRModuloRuoloPermessoDao {

	@Override
	public Page<CpassRModuloRuoloPermesso> getPermessiSelezionabiliNonAttivi(Integer moduloId, int page, int size, String sortField, String sortDirection) {
		final Map<String, Object> params = new HashMap<>();

		final StringBuilder jpql = new StringBuilder().append("FROM CpassRModuloRuoloPermesso rmrp WHERE rmrp.cpassDRuolo.selezionabileDaProcedura='SI' ");
		jpql.append("AND NOT EXISTS ( ");
		jpql.append("SELECT 1 FROM CpassRRuoloPermesso rrp ");
		jpql.append("WHERE rrp.cpassDPermesso.permessoId = rmrp.cpassDPermesso.permessoId ");
		jpql.append("AND rrp.cpassDRuolo.ruoloId = rmrp.cpassDRuolo.ruoloId) ");

		JpaQueryHelper.andFieldEquals(jpql, params, "rmrp.cpassDPermesso.cpassDModulo.moduloId", "moduloId", moduloId);

		if (sortField != null ) {
			jpql.append(" ORDER BY ").append(sortField);
			if(sortDirection != null) {
				jpql.append(" ").append(sortDirection);
			}
		}
		if (sortField == null) {
			jpql.append(" ORDER BY rmrp.cpassDPermesso.cpassDModulo.moduloCodice DESC, rmrp.cpassDRuolo.ruoloCodice DESC, rmrp.cpassDPermesso.permessoCodice DESC ");
		}
		final Page<CpassRModuloRuoloPermesso> ris = getPagedResult(jpql, params, page, size);
		return ris;
	}

	@Override
	public List<CpassRModuloRuoloPermesso> getRuoloPermessoNonAttiviList(Integer moduloId) {
		final Map<String, Object> params = new HashMap<>();

		final StringBuilder jpql = new StringBuilder().append("FROM CpassRModuloRuoloPermesso rmrp WHERE rmrp.cpassDRuolo.selezionabileDaProcedura='SI' ");
		jpql.append("AND NOT EXISTS ( ");
		jpql.append("SELECT 1 FROM CpassRRuoloPermesso rrp ");
		jpql.append("WHERE rrp.cpassDPermesso.permessoId = rmrp.cpassDPermesso.permessoId ");
		jpql.append("AND rrp.cpassDRuolo.ruoloId = rmrp.cpassDRuolo.ruoloId) ");

		JpaQueryHelper.andFieldEquals(jpql, params, "rmrp.cpassDPermesso.cpassDModulo.moduloId", "moduloId", moduloId);

		final TypedQuery<CpassRModuloRuoloPermesso> query = composeTypedQuery(jpql, params);
		return query.getResultList();


	}


}
