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
package it.csi.cpass.cpassbe.ejb.business.be.dao.impl.cpass;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.TypedQuery;

import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.CpassDOggettiSpesaDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.impl.BaseEntityDaoImpl;
import it.csi.cpass.cpassbe.ejb.entity.ord.CpassDOggettiSpesa;
import it.csi.cpass.cpassbe.ejb.util.jpa.JpaQueryHelper;
import it.csi.cpass.cpassbe.ejb.util.jpa.Page;

/**
 * Data Access Object implementor for the entity CpassDOrdTipoOrdine
 */
@ApplicationScoped
public class CpassDOggettiSpesaDaoImpl extends BaseEntityDaoImpl<Integer, CpassDOggettiSpesa> implements CpassDOggettiSpesaDao {

	@Override
	public Page<CpassDOggettiSpesa> findPaginated(			
			Boolean inventariabile
			,String codice
			,String descrizione
			,Integer aliquoteIvaid
			,Integer cpvId
			,String cpvCodice
			,Integer unitaMisuraId
			,int page
			,int size
			,String sortField
			,String sortDirection
) {
		
		Map<String, Object> params = new HashMap<>();
		Date now = new Date();

		StringBuilder jpql = new StringBuilder()
			.append("FROM CpassDOggettiSpesa os ")
			.append(" WHERE (os.dataCancellazione  IS NULL OR (os.dataCancellazione  IS NOT NULL and os.dataCancellazione   > :now )) ")
		    .append("   AND (os.dataValiditaInizio IS NULL OR (os.dataValiditaInizio IS NOT NULL and os.dataValiditaInizio <= :now )) ")
	        .append("   AND (os.dataValiditaFine   IS NULL OR (os.dataValiditaFine   IS NOT NULL and os.dataValiditaFine   >= :now )) ");
			 params.put("now", now);
		
		JpaQueryHelper.andFieldEquals(jpql, params, "os.inventariabile", "inventariabile", inventariabile);
		JpaQueryHelper.andFieldEquals(jpql, params, "os.oggettiSpesaCodice", "codice", codice);
		JpaQueryHelper.andFieldLike(jpql, params, "os.oggettiSpesaDescrizione", "descrizione", descrizione);

		JpaQueryHelper.andFieldEquals(jpql, params, "os.cpassDAliquoteIva.aliquoteIvaId", "aliquoteIvaid", aliquoteIvaid);
		JpaQueryHelper.andFieldEquals(jpql, params, "os.cpassDCpv.cpvId", "cpvId", cpvId);
		JpaQueryHelper.andFieldLike(jpql, params, "os.cpassDCpv.cpvCodice", "cpvCodice", cpvCodice);
		JpaQueryHelper.andFieldEquals(jpql, params, "os.cpassDUnitaMisura.unitaMisuraId", "unitaMisuraId", unitaMisuraId);

		return getPagedResult(jpql, params, page, size);
	
	}

	@Override
	public CpassDOggettiSpesa findByCodice(String codice) {
		Map<String, Object> params = new HashMap<>();
		Date now = new Date();

		StringBuilder jpql = new StringBuilder()
			.append("FROM CpassDOggettiSpesa os ")
			.append(" WHERE (os.dataCancellazione  IS NULL OR (os.dataCancellazione  IS NOT NULL and os.dataCancellazione   > :now ))")
		    .append("   AND (os.dataValiditaInizio IS NULL OR (os.dataValiditaInizio IS NOT NULL and os.dataValiditaInizio <= :now ))")
	        .append("   AND (os.dataValiditaFine   IS NULL OR (os.dataValiditaFine   IS NOT NULL and os.dataValiditaFine   >= :now ))");
			 params.put("now", now);
			 
		 JpaQueryHelper.andFieldEquals(jpql, params, "os.oggettiSpesaCodice", "codice", codice);
			 
		 TypedQuery<CpassDOggettiSpesa> query = composeTypedQuery(jpql, params);
		 
		List<CpassDOggettiSpesa> results = query.getResultList();
		
		return results != null && results.size() > 0 ? results.get(0) : null;
	}
}	
/*
 * 	private CpassDAliquoteIva cpassDAliquoteIva;

	//bi-directional many-to-one association to CpassDCpv
	@ManyToOne
	@JoinColumn(name="cpv_id")
	private CpassDCpv cpassDCpv;

	//bi-directional many-to-one association to CpassDUnitaMisura
	@ManyToOne
	@JoinColumn(name="unita_misura_id")
	private CpassDUnitaMisura cpassDUnitaMisura;
	*/
