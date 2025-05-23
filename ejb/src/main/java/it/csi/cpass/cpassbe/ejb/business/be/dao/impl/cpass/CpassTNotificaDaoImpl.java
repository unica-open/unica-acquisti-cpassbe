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
package it.csi.cpass.cpassbe.ejb.business.be.dao.impl.cpass;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.Query;

import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.CpassTNotificaDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.impl.BaseEntityDaoImpl;
import it.csi.cpass.cpassbe.ejb.entity.CpassTNotifica;
import it.csi.cpass.cpassbe.ejb.entity.CpassTTestiNotifiche;

@ApplicationScoped
public class CpassTNotificaDaoImpl extends BaseEntityDaoImpl<Integer, CpassTNotifica> implements CpassTNotificaDao {
	/*
	@Override
	public List<CpassTNotifica> getNotificheGeneraliNonAssegnate(UUID utenteId) {

		Map<String, Object> params = new HashMap<>();
		StringBuilder jpql = new StringBuilder();
		jpql.append(" FROM CpassTNotifica ctn ");
		jpql.append(" WHERE 1 = 1 ");
		jpql.append(" AND ctn.flgGenerico IS TRUE ");
		jpql.append(" AND ctn.dataInizio <= :now AND ctn.dataFine > :now ");
		jpql.append(" AND ");
		jpql.append(" ( SELECT COUNT(*) FROM CpassRNotificaUtente crnu WHERE crnu.cpassTUtente.utenteId = :utenteId  ) = 0 ");

		params.put("now", new Date());
		params.put("utenteId", utenteId);

		TypedQuery<CpassTNotifica> query = composeTypedQuery(jpql, params);
		return query.getResultList();
	}
	 */
	@Override
	public List<CpassTNotifica> getNotificheGeneraliNonAssegnate(UUID utenteId) {
		final StringBuilder sql = new StringBuilder();
		final Map<String, Object> params = new HashMap<>();
		final List<CpassTNotifica> ris = new ArrayList<>();

		sql.append(" select");
		sql.append(" 	notifica_id");
		sql.append("   ,testo_id");
		sql.append("   ,entita_id");
		sql.append("   ,entita_tipo");
		sql.append("   ,fonte");
		sql.append("   ,data_inizio");
		sql.append("   ,data_fine");
		sql.append("   ,flg_generico");
		sql.append("   ,parametri");
		sql.append(" from ");
		sql.append(" 	Cpass_T_Notifica ");
		sql.append(" where ");
		sql.append("     data_inizio <= now() ");
		sql.append(" and data_fine   >  now() ");
		sql.append(" and flg_Generico = TRUE");
		sql.append(" and notifica_id not IN( ");
		sql.append("   select notifica_id");
		sql.append("   from cpass_r_notifica_utente ");
		sql.append("   where ");
		sql.append("       cpass_r_notifica_utente.notifica_id = notifica_id");
		sql.append("       and cpass_r_notifica_utente.utente_id = :utenteId ");
		//sql.append("       and cpass_r_notifica_utente.flg_letto = false ");
		sql.append(" ) ");

		params.put("utenteId", utenteId);
		//params.put("now", new Date());

		final Query query = composeNativeQuery(sql.toString(), params);
		final List<Object[]> resultList = query.getResultList();

		for(final Object[] obj : resultList) {
			final CpassTNotifica el = new CpassTNotifica();
			el.setId((Integer)obj[0]);
			final CpassTTestiNotifiche testi = new CpassTTestiNotifiche();
			testi.setId((Integer)obj[1]);
			el.setCpassTTestiNotifiche(testi);
			el.setEntitaId((String)obj[2]);
			el.setEntitaTipo((String)obj[3]);
			el.setFonte((String)obj[4]);
			el.setDataInizio((Date)obj[5]);
			el.setDataFine((Date)obj[6]);
			el.setFlgGenerico((Boolean)obj[7]);
			el.setParametri((String)obj[8]);
			ris.add(el);
		}
		return ris;
	}



}
