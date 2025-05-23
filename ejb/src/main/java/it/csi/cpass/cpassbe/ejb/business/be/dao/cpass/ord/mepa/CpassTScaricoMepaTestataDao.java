/*-
 * ========================LICENSE_START=================================
 * CPASS BackEnd - EJB submodule
 * %%
 * Copyright (C) 2019 - 2021 CSI Piemonte
 * %%
 * SPDX-FileCopyrightText: Copyright 2019 - 2021 | CSI Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 * =========================LICENSE_END==================================
 */
package it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.ord.mepa;

import java.util.List;

import it.csi.cpass.cpassbe.ejb.business.be.dao.BaseEntityDao;
import it.csi.cpass.cpassbe.ejb.entity.ord.mepa.CpassTScaricoMepaTestata;
import it.csi.cpass.cpassbe.ejb.util.jpa.Page;

public interface CpassTScaricoMepaTestataDao extends BaseEntityDao<Integer, CpassTScaricoMepaTestata> {

	/**
	 * Recupero le testate da caricare relative all'utente
	 * @param statoId id stato DA_CARICARE per tipo ORDINE_MEPA
	 * @param codiciUfficio lista codiciUfficio relativi al settore dell'utente
	 * @return
	 */
	Page<CpassTScaricoMepaTestata> getOrdiniMepaDaCaricare(Integer statoId, List<String> codiciUfficio,int page, int size, String sortField, String sortDirection);

	List<CpassTScaricoMepaTestata> getOrdiniMepaDaCaricareSenzaCodUfficio(Integer statoId);

	CpassTScaricoMepaTestata findScaricoMepaTestataByOrderId(String orderId);

}
