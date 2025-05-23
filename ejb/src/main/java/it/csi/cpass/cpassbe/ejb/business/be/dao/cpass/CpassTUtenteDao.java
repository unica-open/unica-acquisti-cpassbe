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
import it.csi.cpass.cpassbe.ejb.entity.CpassTUtente;
import it.csi.cpass.cpassbe.ejb.util.jpa.Page;
import it.csi.cpass.cpassbe.lib.dto.Ruolo;
import it.csi.cpass.cpassbe.lib.dto.Settore;
import it.csi.cpass.cpassbe.lib.dto.Utente;

/**
 * Data Access Object interface for the entity CpassTUtente
 */
public interface CpassTUtenteDao extends BaseAuditedEntityDao<UUID, CpassTUtente> {

	/**
	 * Finds the entity by its CF
	 * @param utenteCodiceFiscale the utente codice fiscale
	 * @return the entity
	 */
	Optional<CpassTUtente> findUtenteByCf(String utenteCodiceFiscale,Boolean valido);

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

	Page<CpassTUtente> findPaginated(Boolean dirigente, Utente utente,Ruolo ruolo, Settore settore,UUID enteId, Boolean checkDataValiditaFine, int page, int size,String sortField , String sortDirection);

	List<CpassTUtente> findPaginatedNoPage(Boolean dirigente, Utente utente,Ruolo ruolo, Settore settore,UUID enteId);

	Optional<CpassTUtente> getDirigenteSettoreByUtenteIdSettoreId(UUID settoreId, UUID utenteId);

	Optional<CpassTUtente> getUtenteRupSettoreByUtenteIdSettoreId(UUID settoreId, UUID utenteId);

}
