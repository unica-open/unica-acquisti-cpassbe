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
package it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.decodifiche;

import java.util.List;
import java.util.UUID;

import it.csi.cpass.cpassbe.ejb.business.be.dao.BaseEntityDao;
import it.csi.cpass.cpassbe.ejb.entity.CpassDCpv;
import it.csi.cpass.cpassbe.ejb.util.jpa.Page;

/**
 * Data Access Object interface for the entity CpassDCpv
 */
public interface CpassDCpvDao extends BaseEntityDao<Integer, CpassDCpv> {

	public CpassDCpv findByCodice(String codice);

	/**
	 *
	 * @param idIntervento
	 * @return
	 */
	List<CpassDCpv> getCpvsByInterventoId(UUID idIntervento);
	/**
	 *
	 * @param inventariabile
	 * @param codice
	 * @param descrizione
	 * @param cpvId
	 * @param generico
	 * @param page
	 * @param size
	 * @param sortField
	 * @param sortDirection
	 * @return
	 */
	Page<CpassDCpv> findCpvOdsPaginated(
			String  codice
			,String  descrizione
			,Integer cpvId
			,String genericoStr
			,UUID enteId
			,int page
			,int size
			,String sortField
			,String sortDirection
			) ;

}
