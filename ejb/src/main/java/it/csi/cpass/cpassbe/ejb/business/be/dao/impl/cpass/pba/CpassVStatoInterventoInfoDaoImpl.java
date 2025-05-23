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

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.Query;

import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.pba.CpassVStatoInterventoInfoDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.impl.BaseDaoImpl;
import it.csi.cpass.cpassbe.ejb.util.ConstantsCPassStato.StatoInterventiEnum;
import it.csi.cpass.cpassbe.ejb.util.CpassEnum;
import it.csi.cpass.cpassbe.lib.dto.custom.StatoInterventoInfo;

@ApplicationScoped
public class CpassVStatoInterventoInfoDaoImpl extends BaseDaoImpl<StatoInterventoInfo> implements CpassVStatoInterventoInfoDao {

	@Override
	public List<StatoInterventoInfo> getUltimoStatoInfoByIntervento(UUID interventoId) {
		final StringBuilder sql = new StringBuilder();
		final Map<String, Object> param = new HashMap<>();
		sql.append(" select stato,data_inserimento,cognome,nome FROM ( ");
		sql.append(" WITH cpass_t_utente_ext AS ( ");
		sql.append(" 	select  ");
		sql.append("     	cpass_t_utente.utente_id, ");
		sql.append("     	cpass_t_utente.utente_codice_fiscale, ");
		sql.append("     	cpass_t_utente.utente_cognome, ");
		sql.append("         cpass_t_utente.utente_nome ");
		sql.append(" 	FROM ");
		sql.append(" 		cpass_t_utente ");
		sql.append("     UNION ");
		sql.append("     	select  ");
		sql.append("         uuid_generate_v4(), ");
		sql.append("     	'"+CpassEnum.UTENTE_SISTEMA_CF.getCostante()+"', ");
		sql.append("     	'utente', ");
		sql.append("        'sistema' ");
		sql.append(" ) ");
		sql.append(" select ");
		sql.append(" 	'"+StatoInterventiEnum.BOZZA.getCostante()+"' stato ");
		sql.append(" 	,cpass_t_pba_intervento.data_creazione   data_inserimento ");
		sql.append(" 	,cpass_t_utente_ext.utente_cognome       cognome  ");
		sql.append(" 	,cpass_t_utente_ext.utente_nome          nome ");
		sql.append(" 	,1          livello ");
		sql.append(" FROM ");
		sql.append(" 	cpass_t_pba_intervento ");
		sql.append(" 	,cpass_t_utente_ext ");
		sql.append(" where  ");
		sql.append(" 	cpass_t_pba_intervento.utente_creazione = cpass_t_utente_ext.utente_codice_fiscale ");
		sql.append(" 	AND cpass_t_pba_intervento.intervento_id = :interventoId ");

		sql.append(" UNION ");
		sql.append(" select  ");
		sql.append("   '"+StatoInterventiEnum.RIFIUTATO.getCostante()+"'  stato ");
		sql.append("   ,cpass_t_pba_intervento.data_rifiuto data_inserimento ");
		sql.append("   ,cpass_t_utente.utente_cognome cognome ");
		sql.append("   ,cpass_t_utente.utente_nome nome ");
		sql.append("   ,2          livello ");
		sql.append(" FROM ");
		sql.append("   cpass_t_pba_intervento ");
		sql.append("   ,cpass_t_utente ");
		sql.append(" where  ");
		sql.append("   cpass_t_pba_intervento.utente_rifiuto_id = cpass_t_utente.utente_id ");
		sql.append("   AND cpass_t_pba_intervento.data_rifiuto IS NOT NULL ");
		sql.append("   AND cpass_t_pba_intervento.intervento_id = :interventoId ");

		sql.append(" UNION ");
		sql.append(" select  ");
		sql.append("   '"+StatoInterventiEnum.VISTO.getCostante()+"' stato ");
		sql.append("   ,cpass_t_pba_intervento.data_visto data_inserimento ");
		sql.append("   ,cpass_t_utente.utente_cognome cognome ");
		sql.append("   ,cpass_t_utente.utente_nome nome ");
		sql.append("   ,3          livello ");
		sql.append(" FROM ");
		sql.append(" 	cpass_t_pba_intervento ");
		sql.append(" 	,cpass_t_utente ");
		sql.append(" where ");
		sql.append("   cpass_t_pba_intervento.utente_visto_id = cpass_t_utente.utente_id ");
		sql.append("   AND cpass_t_pba_intervento.data_visto IS NOT NULL ");
		sql.append("   AND cpass_t_pba_intervento.intervento_id = :interventoId ");


		sql.append(" UNION ");
		sql.append(" select  ");
		sql.append("   '"+StatoInterventiEnum.VALIDATO.getCostante()+"'  stato ");
		sql.append("   ,cpass_t_pba_intervento.data_validazione data_inserimento ");
		sql.append("   ,cpass_t_utente.utente_cognome cognome ");
		sql.append("   ,cpass_t_utente.utente_nome nome ");
		sql.append("   ,4          livello ");
		sql.append(" FROM ");
		sql.append("   cpass_t_pba_intervento ");
		sql.append("   ,cpass_t_utente ");
		sql.append(" where  ");
		sql.append("   cpass_t_pba_intervento.utente_visto_id = cpass_t_utente.utente_id ");
		sql.append("   AND cpass_t_pba_intervento.data_validazione IS NOT NULL ");
		sql.append("   AND cpass_t_pba_intervento.intervento_id = :interventoId ");



		sql.append(" UNION ");
		sql.append(" select  ");
		sql.append("   '"+StatoInterventiEnum.CANCELLATO.getCostante()+"'  stato ");
		sql.append("   ,cpass_t_pba_intervento.data_modifica data_inserimento ");
		sql.append("   ,cpass_t_utente_ext.utente_cognome cognome ");
		sql.append("   ,cpass_t_utente_ext.utente_nome nome ");
		sql.append("   ,5          livello ");
		sql.append(" FROM ");
		sql.append("    cpass_t_pba_intervento ");
		sql.append("   ,cpass_d_stato ");
		sql.append("   ,cpass_t_utente_ext ");
		sql.append(" where  ");
		sql.append("   cpass_t_pba_intervento.utente_modifica = cpass_t_utente_ext.utente_codice_fiscale ");

		sql.append("   AND cpass_t_pba_intervento.stato_id =  	cpass_d_stato.stato_id ");
		sql.append("   AND cpass_d_stato.stato_codice = '"+StatoInterventiEnum.CANCELLATO.getCostante()+"' ");
		sql.append("   AND cpass_t_pba_intervento.intervento_id = :interventoId ");

		sql.append(" ) as cambioStati ");
		sql.append(" order by livello, data_inserimento DESC ");

		param.put("interventoId", interventoId);
		final Query query = composeNativeQuery(sql.toString(), param);

		final List<Object[]> list = query.getResultList();
		final List<StatoInterventoInfo> listaRis = new ArrayList<>();
		for(final Object[] obj : list) {
			final StatoInterventoInfo el = new StatoInterventoInfo();
			el .setStato((String)obj[0]);
			el.setDataInserimento((Date)obj[1]);
			el.setUtenteCognome((String)obj[2]);
			el.setUtenteNome((String)obj[3]);
			listaRis.add(el);
		}
		return listaRis;//findOrdineByIds(listaId);
	}


}
