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

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.Query;

import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.ord.CpassVOrdineEvasioneDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.impl.BaseEntityDaoImpl;
import it.csi.cpass.cpassbe.ejb.entity.ord.CpassVOrdineEvasione;

/**
 * Data Access Object implementor for the entity CpassVOrdineEvasioneDao
 */
@ApplicationScoped
public class CpassVOrdineEvasioneDaoImpl extends BaseEntityDaoImpl<Long, CpassVOrdineEvasione> implements CpassVOrdineEvasioneDao {




	@Override
	public List<CpassVOrdineEvasione> getListEvasioneByOrdineId(UUID idTestataOrdine) {
		final Map<String, Object> param = new HashMap<>();

		final StringBuilder sql = new StringBuilder().append("SELECT DISTINCT");
		sql.append(" CAST(cpass_v_ordine_evasione.testata_evasione_id AS VARCHAR)");
		sql.append(" ,cpass_v_ordine_evasione.evasione_anno ");
		sql.append(" ,cpass_v_ordine_evasione.evasione_numero ");
		sql.append(" ,cpass_v_ordine_evasione.evasione_totale_con_iva ");
		//26 Ripartito Su Impegni
		//27 In Contabilità
		sql.append(" ,cpass_v_ordine_evasione.fattura_anno ");
		sql.append(" ,cpass_v_ordine_evasione.fattura_numero ");
		sql.append(" ,cpass_v_ordine_evasione.fattura_tipo ");
		sql.append(" ,cpass_v_ordine_evasione.evasione_fornitore_codice ");
		sql.append(" ,cpass_v_ordine_evasione.evasione_fornitore_ragione_sociale ");
		sql.append(" ,cpass_v_ordine_evasione.evasione_stato_descrizione ");
		sql.append(" ,cpass_v_ordine_evasione.fattura_codice ");


		sql.append(" FROM cpass_v_ordine_evasione ");
		//sql.append("      ,cpass_d_stato ");
		//sql.append(" WHERE cpass_v_ordine_evasione.stato_id = cpass_d_stato.stato_id ");
		sql.append(" WHERE 1 = 1 ");
		sql.append(" AND cpass_v_ordine_evasione.testata_ordine_id =:idTestataOrdine ");
		sql.append(" ORDER BY cpass_v_ordine_evasione.evasione_anno, cpass_v_ordine_evasione.evasione_numero ");

		param.put("idTestataOrdine", idTestataOrdine);

		final Query query = composeNativeQuery(sql.toString(), param);

		final List<Object[]> list = query.getResultList();
		final List<CpassVOrdineEvasione> listaRis = new ArrayList<>();
		for(final Object[] obj : list) {
			final CpassVOrdineEvasione oe = new CpassVOrdineEvasione();
			oe.setTestataEvasioneId((String)obj[0]);
			oe.setEvasioneAnno((Integer)obj[1]);
			oe.setEvasioneNumero((Integer)obj[2]);
			oe.setEvasioneTotaleConIva((BigDecimal)obj[3]);
			oe.setFatturaAnno((Integer)obj[4]);
			oe.setFatturaNumero((String)obj[5]);
			oe.setFatturaTipo((String)obj[6]);
			oe.setEvasioneFornitoreCodice((String)obj[7]);
			oe.setEvasioneFornitoreRagioneSociale((String)obj[8]);
			oe.setEvasioneStatoDescrizione((String)obj[9]);
			oe.setFatturaCodice((String)obj[10]);

			listaRis.add(oe);
		}
		return listaRis;//findOrdineByIds(listaId);
	}
}







