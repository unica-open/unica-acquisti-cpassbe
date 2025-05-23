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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.Query;

import org.apache.commons.lang3.StringUtils;

import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.CpassTElaborazioneMessaggioDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.impl.BaseEntityDaoImpl;
import it.csi.cpass.cpassbe.ejb.entity.CpassTElaborazione;
import it.csi.cpass.cpassbe.ejb.entity.CpassTElaborazioneMessaggio;

/**
 * Data Access Object implementor for the entity CpassTComunicazione
 */
@ApplicationScoped
public class CpassTElaborazioneMessaggioDaoImpl extends BaseEntityDaoImpl<Integer, CpassTElaborazioneMessaggio> implements CpassTElaborazioneMessaggioDao {

	@Override
	public List<CpassTElaborazioneMessaggio> getMessaggiByUltimaElaborazione(String entitaId, String elaborazioneTipoCodice, UUID enteId) {
		final StringBuilder sql = new StringBuilder();
		final Map<String, Object> param = new HashMap<>();
		sql.append(" WITH tmp AS (");
		sql.append(" select  max(elab.elaborazione_id) max_elab_id");
		sql.append(" from cpass_t_elaborazione elab ,cpass_d_elaborazione_tipo elabtipo ");
		sql.append(" where elab.elaborazione_tipo_id = elabtipo.elaborazione_tipo_id ");
		sql.append("   and elab.ente_id = :enteId ");
		if(entitaId !=null) {
			sql.append("   and elab.entita_id = :entitaId ");
			param.put("entitaId", entitaId);
		}
		param.put("enteId", enteId);
		if(StringUtils.isNotEmpty(elaborazioneTipoCodice)) {
			sql.append("   and elabtipo.elaborazione_tipo_codice = :elaborazioneTipoCodice ");
			param.put("elaborazioneTipoCodice", elaborazioneTipoCodice);
		}
		sql.append(" )");
		sql.append(" select ");
		sql.append("  mess.elaborazione_messaggio_id");
		sql.append(" ,mess.elaborazione_id");
		sql.append(" ,mess.elaborazione_messaggio_tipo");
		sql.append(" ,mess.elaborazione_messaggio_code");
		sql.append(" ,mess.elaborazione_messaggio_descrizione");
		sql.append(" FROM cpass_t_elaborazione_messaggio mess");
		sql.append(" join tmp on tmp.max_elab_id = mess.elaborazione_id");
		sql.append(" order by mess.elaborazione_messaggio_id");

		log.info("", "sql--> " + sql);
		//param.put("interventoId", interventoId);
		final Query query = composeNativeQuery(sql.toString(), param);

		final List<Object[]> list=  query.getResultList();
		final List<CpassTElaborazioneMessaggio> listaRis = new ArrayList<>();
		for(final Object[] obj : list) {
			final CpassTElaborazione el = new CpassTElaborazione();
			el.setElaborazioneId((Integer)obj[0]);

			final CpassTElaborazioneMessaggio elmess = new CpassTElaborazioneMessaggio();
			elmess.setElaborazioneMessaggioId((Integer)obj[0]);
			elmess.setCpassTElaborazione(el);
			elmess.setElaborazioneMessaggioTipo((String)obj[2]);
			elmess.setElaborazioneMessaggioCode((String)obj[3]);
			elmess.setElaborazioneMessaggioDescrizione((String)obj[4]);
			listaRis.add(elmess);
		}
		return listaRis;
	}
}
