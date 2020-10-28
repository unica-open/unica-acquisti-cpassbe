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

import it.csi.cpass.cpassbe.ejb.business.be.dao.BaseEntityDao;
import it.csi.cpass.cpassbe.ejb.entity.CpassTElaborazione;

/**
 * Data Access Object interface for the entity CpassTUtente
 */
public interface CpassTElaborazioneDao extends BaseEntityDao<Integer, CpassTElaborazione> {

	List<CpassTElaborazione> findByEntityId(String entityId);

	public List<CpassTElaborazione> getElaborazione(String entitaId, String elaborazioneStato, String elaborazioneTipoCodice);
}
