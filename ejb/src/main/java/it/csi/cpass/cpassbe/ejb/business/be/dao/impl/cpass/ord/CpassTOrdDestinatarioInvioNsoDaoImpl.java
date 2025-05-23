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
package it.csi.cpass.cpassbe.ejb.business.be.dao.impl.cpass.ord;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.TypedQuery;

import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.ord.CpassTOrdDestinatarioInvioNsoDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.impl.BaseEntityDaoImpl;
import it.csi.cpass.cpassbe.ejb.entity.ord.CpassTOrdDestinatarioInvioNso;
import it.csi.cpass.cpassbe.ejb.util.CpassEnum;
import it.csi.cpass.cpassbe.ejb.util.jpa.JpaQueryHelper;

/**
 * Data Access Object implementor for the entity CpassTOrdDestinatarioInvioNso
 */
@ApplicationScoped
public class CpassTOrdDestinatarioInvioNsoDaoImpl extends BaseEntityDaoImpl<Integer, CpassTOrdDestinatarioInvioNso> implements CpassTOrdDestinatarioInvioNsoDao {
	@Override
	public Optional<CpassTOrdDestinatarioInvioNso> getUltimoInvioByDestinatario(UUID id){
		final Map<String, Object> params = new HashMap<>();
		final StringBuilder jpql = new StringBuilder()
				.append("FROM CpassTOrdDestinatarioInvioNso din ")
				.append(" WHERE 1 = 1 ");
		JpaQueryHelper.andFieldEquals(jpql, params, "din.cpassTOrdDestinatarioOrdine.destinatarioId", "destinatarioId", id);
		jpql.append(" ORDER BY din.progressivoInvio desc");
		final TypedQuery<CpassTOrdDestinatarioInvioNso> query = composeTypedQuery(jpql, params);
		return query.getResultList().stream().findFirst();
	}

	@Override
	public Optional<CpassTOrdDestinatarioInvioNso> getUltimoInvioByOrdine(UUID id){
		final Map<String, Object> params = new HashMap<>();
		final StringBuilder jpql = new StringBuilder()
				.append("FROM CpassTOrdDestinatarioInvioNso din ")
				.append(" WHERE 1 = 1 ");
		JpaQueryHelper.andFieldEquals(jpql, params, "din.cpassTOrdTestataOrdine.testataOrdineId", "testataOrdineId", id);
		jpql.append(" ORDER BY din.progressivoInvio desc");
		final TypedQuery<CpassTOrdDestinatarioInvioNso> query = composeTypedQuery(jpql, params);
		return query.getResultList().stream().findFirst();
	}

	@Override
	public List<CpassTOrdDestinatarioInvioNso> findByIdNotier(String idNotier){
		final Map<String, Object> params = new HashMap<>();

		params.put("esitoInvio", CpassEnum.OK.getCostante());
		params.put("esitoConsegna", CpassEnum.KO.getCostante());
		params.put("idNotier", idNotier);

		StringBuilder jpql = new StringBuilder()
				.append("FROM CpassTOrdDestinatarioInvioNso din ")
				.append(" WHERE 1 = 1 ")
				.append(" and din.esitoInvio = :esitoInvio")
				.append(" and ( (din.esitoConsegnaMdn is null or din.esitoConsegnaMdn = :esitoConsegna) ")
				.append(" or (din.esitoConsegnaNso is null or din.esitoConsegnaNso = :esitoConsegna)) ")
				.append(" and ( (din.cpassTOrdDestinatarioOrdine is not null and din.cpassTOrdDestinatarioOrdine.cpassTOrdTestataOrdine.cpassTUfficio.idNotier = :idNotier) ")
				//			.append(" or (din.cpassTOrdTestataOrdine is not null and din.cpassTOrdTestataOrdine.cpassTUfficio.idNotier = :idNotier ) ")
				.append(" ) ");

		TypedQuery<CpassTOrdDestinatarioInvioNso> query = composeTypedQuery(jpql, params);
		final List<CpassTOrdDestinatarioInvioNso> unionList = query.getResultList();

		jpql = new StringBuilder()
				.append("FROM CpassTOrdDestinatarioInvioNso din ")
				.append(" WHERE 1 = 1 ")
				.append(" and din.esitoInvio = :esitoInvio")
				.append(" and ( (din.esitoConsegnaMdn is null or din.esitoConsegnaMdn = :esitoConsegna) ")
				.append(" or (din.esitoConsegnaNso is null or din.esitoConsegnaNso = :esitoConsegna)) ")
				.append(" and din.cpassTOrdTestataOrdine is not null and din.cpassTOrdTestataOrdine.cpassTUfficio.idNotier = :idNotier ");

		query = composeTypedQuery(jpql, params);
		unionList.addAll(query.getResultList());

		return unionList;

		/*
		StringBuilder jpql = new StringBuilder();
		jpql.append(" select ctodin.* from ")
		.append(" cpass_t_ord_destinatario_invio_nso ctodin ")
		.append(" , cpass_t_ord_destinatario_ordine ctodo ")
		.append(" , cpass_t_ord_testata_ordine ctoto  ")
		.append(" , cpass_t_ufficio ctu ")
		.append(" where ")
		.append(" ctodin.esito_invio = :esitoInvio ")
		.append(" and ( ")
		.append(" (ctodin.esito_consegna_mdn is null or ctodin.esito_consegna_mdn = :esitoConsegna) ")
		.append(" or ")
		.append(" (ctodin.esito_consegna_nso is null or ctodin.esito_consegna_nso = :esitoConsegna) ")
		.append(" ) ")
		.append(" and ctodo.destinatario_id = ctodin.destinatario_id ")
		.append(" and ctoto.testata_ordine_id = ctodo.testata_ordine_id ")
		.append(" and ctoto.ufficio_id = ctu.ufficio_id  ")
		.append(" and ctu.id_notier = :idNotier ")
		.append(" union ")
		.append(" select ctodin.* from ")
		.append(" cpass_t_ord_destinatario_invio_nso ctodin ")
		.append(" , cpass_t_ord_testata_ordine ctoto ")
		.append(" , cpass_t_ufficio ctu ")
		.append(" where ")
		.append(" ctodin.esito_invio = 'OK' ")
		.append(" and ( ")
		.append(" (ctodin.esito_consegna_mdn is null or ctodin.esito_consegna_mdn = 'KO') ")
		.append(" or ")
		.append(" (ctodin.esito_consegna_nso is null or ctodin.esito_consegna_nso = 'KO') ")
		.append(" ) ")
		.append(" and ctodin.testata_ordine_id = ctoto.testata_ordine_id ")
		.append(" and ctoto.ufficio_id = ctu.ufficio_id ")
		.append(" and ctu.id_notier = :idNotier	");

		Query query = composeNativeQuery(jpql.toString(), params);

		List<Object[]> resultList = query.getResultList();

		List<CpassTOrdDestinatarioInvioNso> listCpassTOrdDestinatarioInvioNso = new ArrayList<CpassTOrdDestinatarioInvioNso>();
		for(Object[] obj : resultList) {
			listCpassTOrdDestinatarioInvioNso.add(UUID.fromString((String)obj[0]));
		}
	    if(listaId.isEmpty()) {
			return new ArrayList<CpassTOrdTestataOrdine>();
		}	*/
	}
}
