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

import it.csi.cpass.cpassbe.ejb.business.be.dao.BaseEntityDao;
import it.csi.cpass.cpassbe.ejb.entity.CpassTMetadatiFunzione;

/**
 * Data Access Object interface for the entity CpassTMetadatiFunzione
 */
public interface CpassTMetadatiFunzioneDao extends BaseEntityDao<Integer, CpassTMetadatiFunzione> {

	/**
	 *
	 * @param modulo
	 * @param funzione
	 * @return List<CpassTMetadatiFunzione>
	 */
	List<CpassTMetadatiFunzione> getMetadatiFunzioneByModuloFunzione(String modulo, String funzione);

	/**
	 *
	 * @param modulo
	 * @param funzione
	 * @return List<CpassTMetadatiFunzione>
	 */
	CpassTMetadatiFunzione getMetadatiFunzioneByModuloFunzioneChiave(String modulo, String funzione, String chiaveColonna);


}
