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
package it.csi.cpass.cpassbe.ejb.business.be.dao.cpass;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import it.csi.cpass.cpassbe.ejb.business.be.dao.BaseAuditedEntityDao;
import it.csi.cpass.cpassbe.ejb.entity.CpassTSettore;
import it.csi.cpass.cpassbe.ejb.util.jpa.Page;
import it.csi.cpass.cpassbe.lib.dto.Utente;

/**
 * Data Access Object interface for the entity CpassTUtente
 */
public interface CpassTSettoreDao extends BaseAuditedEntityDao<UUID, CpassTSettore> {
	
	/**
	 * 
	 */
	Optional<CpassTSettore> findById(UUID id);
	
	/**
	 * 
	 * @param codice
	 * @return
	 */
	CpassTSettore findByCodice(String codice);

	/**
	 *
	 * @param utenteId
	 * @return List<Settore>
	 */
	List<CpassTSettore> getSettoriByUtenteId(UUID utenteId);

	Page<CpassTSettore> findPaginated(String cap,
									  String codice,
									  String descrizione,
									  String indirizzo,
									  String localita,
									  String provincia,
									  String telefono,
									  Boolean utenteSettoreDefault,
									  Utente rup,
									  Long tipoSettore,
									  UUID enteId,
									  int page,
									  int size,
									  String sortField,
									  String sortDirection

	);
	
	/**
	 * 
	 * @param rupId
	 * @return
	 */
	List<CpassTSettore> getSettoriByRupId(UUID rupId);
	 /**
	  * 
	  * @param settoreId
	  * @return
	  */
	List<CpassTSettore> getSettoriBySettorePadre(UUID settoreId);
}
