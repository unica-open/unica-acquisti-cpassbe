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
package it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.ord.evasione;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import it.csi.cpass.cpassbe.ejb.business.be.dao.BaseAuditedEntityDao;
import it.csi.cpass.cpassbe.ejb.entity.ord.evasione.CpassTOrdRigaEvasione;

public interface CpassTOrdRigaEvasioneDao extends BaseAuditedEntityDao<UUID, CpassTOrdRigaEvasione> {
	
	List<CpassTOrdRigaEvasione> findByIdDestinatarioEvasione(UUID idDestinatarioEvasione);
	
	List<CpassTOrdRigaEvasione> findByIdRigaOrdine(UUID idRigaOrdine);
	
	void deleteByDestinatarioEvasione(UUID idDestinatarioEvasione);
	
	List<CpassTOrdRigaEvasione> findByTestataEvasione(UUID testataEvasioneId, UUID rigaEvasioneId);

	BigDecimal calcolaTotale(UUID rigaOrdineId);
}
