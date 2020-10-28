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
import it.csi.cpass.cpassbe.ejb.entity.CpassTUtente;

/**
 * Data Access Object interface for the entity CpassTUtente
 */
public interface CpassTUtenteDao extends BaseAuditedEntityDao<UUID, CpassTUtente> {

	/**
	 * Finds the entity by its CF
	 * @param utenteCodiceFiscale the utente codice fiscale
	 * @return the entity
	 */
	Optional<CpassTUtente> findUtenteByCf(String utenteCodiceFiscale);

	/**
	 * Gets the utente by settore and ruolo
	 * @param settoreId
	 * @param ruoloCodice
	 * @return the utenti
	 */
	List<CpassTUtente> getUtenteBySettoreRuolo(UUID settoreId, String ruoloCodice);

	List<CpassTUtente> getRupsBySettoreId(UUID idIntervento);

	Optional<CpassTUtente> getUtenteCorrenteDelegatoByUtenteRupId(UUID utenteRupId);

	Optional<CpassTUtente> getUtenteRupDelegante(UUID uuid);
	
}
