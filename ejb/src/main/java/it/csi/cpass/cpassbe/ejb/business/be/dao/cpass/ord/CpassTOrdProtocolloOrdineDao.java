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
package it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.ord;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import it.csi.cpass.cpassbe.ejb.business.be.dao.BaseAuditedEntityDao;
import it.csi.cpass.cpassbe.ejb.entity.ord.CpassTOrdProtocolloOrdine;

public interface CpassTOrdProtocolloOrdineDao extends BaseAuditedEntityDao<Integer, CpassTOrdProtocolloOrdine> {
	List<CpassTOrdProtocolloOrdine> findProtocolloByOrderId(UUID testataOrdineId);
	void deleteProtocolloByTestataordineId(UUID testataOrdineId);
	Optional<CpassTOrdProtocolloOrdine> getProtocolloOrdineByAnnoNumero(Integer anno, String numero, UUID testataOrdineId);
	Optional<CpassTOrdProtocolloOrdine> getProtocolloOrdineByIndiceclassificazione(String indiceclassificazione,UUID testataOrdineId);
	Optional<CpassTOrdProtocolloOrdine> getProtocolloOrdineByStrutturaAggregativa(String voceTitolario, String numeroFascicolo, UUID testataOrdineId);
}
