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

import it.csi.cpass.cpassbe.ejb.business.be.dao.BaseEntityDao;
import it.csi.cpass.cpassbe.ejb.entity.CpassTElaborazioneParametro;

/**
 * Data Access Object interface for the entity CpassTElaborazioneParametro
 */
public interface CpassTElaborazioneParametroDao extends BaseEntityDao<Integer, CpassTElaborazioneParametro> {

	public CpassTElaborazioneParametro getParametro(Integer elaborazioneId, String chiave);

}
