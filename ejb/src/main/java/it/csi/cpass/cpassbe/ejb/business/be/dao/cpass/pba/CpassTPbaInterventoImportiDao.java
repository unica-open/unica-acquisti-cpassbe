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
package it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.pba;

import java.math.BigDecimal;
import java.util.UUID;

import it.csi.cpass.cpassbe.ejb.business.be.dao.BaseAuditedEntityDao;
import it.csi.cpass.cpassbe.ejb.entity.pba.CpassTPbaInterventoImporti;
import it.csi.cpass.cpassbe.ejb.util.jpa.Page;

/**
 * Data Access Object interface for the entity CpassTPbaInterventoImporti
 */
public interface CpassTPbaInterventoImportiDao extends BaseAuditedEntityDao<UUID, CpassTPbaInterventoImporti> {

	/**
	 * Finds the page relative to the given params
	 * @param interventoImportiImportoAnnoPrimo the intervento importi importo anno primo
	 * @param interventoImportiImportoAnnoSecondo the intervento importi importo anno secondo
	 * @param interventoImportiImportoAnniSuccessivi the intervento importi importo anni successivi
	 * @param interventoId the intervento id
	 * @param risorseId the risorse id
	 * @param page the page
	 * @param size the size
	 * @return the paged results
	 */
	Page<CpassTPbaInterventoImporti> findPaginated(
			BigDecimal interventoImportiImportoAnnoPrimo,
			BigDecimal interventoImportiImportoAnnoSecondo,
			BigDecimal interventoImportiImportoAnniSuccessivi,
			UUID interventoId,
			Integer risorseId,
			int page,
			int size);

}
