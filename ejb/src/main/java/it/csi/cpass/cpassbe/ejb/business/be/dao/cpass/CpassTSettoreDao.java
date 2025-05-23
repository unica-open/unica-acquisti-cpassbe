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
	@Override
	Optional<CpassTSettore> findById(UUID id);

	/**
	 *
	 * @param codice
	 * @return
	 */
	Optional<CpassTSettore> findByCodice(String codice,UUID enteId,Boolean valido,String operation);

	/**
	 *
	 * @param utenteId
	 * @return List<Settore>
	 */
	List<CpassTSettore> getSettoriByUtenteId(UUID utenteId);


	Page<CpassTSettore> findPaginated(
			String codice,
			String descrizione,
			Boolean utenteSettoreDefault,
			Utente rup,
			Integer tipoSettore,
			UUID enteId,
			String valido,
			String all,
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
	List<CpassTSettore> getSettoriBySettorePadre(UUID settoreId,Boolean valido);

	/**
	 *
	 * @param utenteId
	 * @return List<Settore>
	 */
	List<CpassTSettore> getSettoriByEnteId(UUID enteId);

	/**
	 *
	 * @param cdcCode
	 * @param enteId
	 * @return List<CpassTSettore>
	 */
	List<CpassTSettore> getSettoriByCdc(String codiceStrutturaProponente, UUID enteId);


}
