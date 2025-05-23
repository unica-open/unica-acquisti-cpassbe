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

import java.util.Date;
import java.util.List;
import java.util.UUID;

import it.csi.cpass.cpassbe.ejb.business.be.dao.BaseEntityDao;
import it.csi.cpass.cpassbe.ejb.entity.CpassTElaborazione;

/**
 * Data Access Object interface for the entity CpassTUtente
 */
public interface CpassTElaborazioneDao extends BaseEntityDao<Integer, CpassTElaborazione> {

	List<CpassTElaborazione> findByEntityId(String entityId);

	public List<CpassTElaborazione> getElaborazione(String entitaId, String elaborazioneStato, String elaborazioneTipoCodice, UUID enteId);

	//public List<CpassTElaborazione> getElaborazioneById(Integer entitaId, String elaborazioneStato, String elaborazioneTipoCodice);

	List<CpassTElaborazione> getElaborazioneDocumentoTrasportoScartato(String idDocumentoTrasporto);

	List<CpassTElaborazione> getElaborazioneByEnteAndType(UUID idEnte, List<Integer> listaElaborazioneTipoId, Date data, Integer numElabGiornata);
}
