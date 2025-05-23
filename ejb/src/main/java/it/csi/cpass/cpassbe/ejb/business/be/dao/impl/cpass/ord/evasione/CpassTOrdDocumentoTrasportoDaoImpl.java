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
package it.csi.cpass.cpassbe.ejb.business.be.dao.impl.cpass.ord.evasione;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.TypedQuery;

import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.ord.evasione.CpassTOrdDocumentoTrasportoDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.impl.BaseEntityDaoImpl;
import it.csi.cpass.cpassbe.ejb.entity.ord.evasione.CpassTOrdDocumentoTrasporto;
import it.csi.cpass.cpassbe.ejb.util.jpa.JpaQueryHelper;
import it.csi.cpass.cpassbe.ejb.util.jpa.Page;

@ApplicationScoped
public class CpassTOrdDocumentoTrasportoDaoImpl extends BaseEntityDaoImpl<Integer, CpassTOrdDocumentoTrasporto> implements CpassTOrdDocumentoTrasportoDao {


	@Override
	public Page<CpassTOrdDocumentoTrasporto> ricercaDocumentoTrasporto(
			List<String> idNotierList, List<UUID> idOrdineList, Integer idStato, Date dataConsegna, UUID idFornitore,
			Integer page, Integer size, String sortField, String sortDirection) {

		final Map<String, Object> params = new HashMap<>();
		final StringBuilder jpql = new StringBuilder()
				.append("SELECT DISTINCT dt  FROM CpassTOrdDocumentoTrasporto dt ");
		if (idOrdineList != null && idOrdineList.size() > 0) {
			jpql.append(" join dt.cpassTOrdDocumentoTrasportoRigas dtr ");
		}
		jpql.append(" WHERE dt.idNotier IN :idNotierList ");
		params.put("idNotierList", idNotierList);

		if (idOrdineList != null && idOrdineList.size() > 0) {
			jpql.append(" AND dtr.cpassTOrdTestataOrdine.testataOrdineId IN :idOrdineList");
			params.put("idOrdineList", idOrdineList);
		}


		if(dataConsegna != null) {
			final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			final String strDate = dateFormat.format(dataConsegna);
			JpaQueryHelper.andFieldEquals(jpql, params, "dt.dataConsegna", "dataConsegna", strDate);
		}

		JpaQueryHelper.andFieldEquals(jpql, params, "dt.cpassDStato.statoId", "idStato", idStato);
		//JpaQueryHelper.andFieldEquals(jpql, params, "dt.dataConsegna", "dataConsegna", dataConsegna);
		JpaQueryHelper.andFieldEquals(jpql, params, "dt.cpassTFornitore.fornitoreId", "idFornitore", idFornitore);

		if (sortField != null && sortDirection != null) {
			jpql.append(" ORDER BY ").append("dt.").append(sortField).append(" ").append(sortDirection);
		}

		return getPagedResult(jpql, params, page, size);
	}

	@Override
	public CpassTOrdDocumentoTrasporto findDocumentoTrasportoByIdEvasione(UUID idEvasione) {
		final Map<String, Object> params = new HashMap<>();
		final StringBuilder jpql = new StringBuilder()
				.append("FROM CpassTOrdDocumentoTrasporto dt ")
				.append(" WHERE 1 = 1 ");
		JpaQueryHelper.andFieldEquals(jpql, params, "dt.cpassTOrdTestataEvasione.testataEvasioneId", "idEvasione", idEvasione);
		final TypedQuery<CpassTOrdDocumentoTrasporto> query = composeTypedQuery(jpql, params);
		final List<CpassTOrdDocumentoTrasporto> result = query.getResultList();
		return result != null && result.size() > 0 ? result.get(0) : null;
	}
}
