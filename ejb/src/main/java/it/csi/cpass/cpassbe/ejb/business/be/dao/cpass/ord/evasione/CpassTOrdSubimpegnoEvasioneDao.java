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
import it.csi.cpass.cpassbe.ejb.entity.ord.evasione.CpassTOrdSubimpegnoEvasione;

/**
 * Data Access Object interface for the entity CpassTOrdSubimpegnoEvasione
 */
public interface CpassTOrdSubimpegnoEvasioneDao extends BaseAuditedEntityDao<UUID, CpassTOrdSubimpegnoEvasione> {

	public List<CpassTOrdSubimpegnoEvasione> getSubimpegni(UUID impegnoEvasioneId);
	
	public void delete(UUID idSubimpegnoEvasione);
	
	public BigDecimal calcolaTotaleEvaso(UUID subimpegnoOrdineId);
	
	public BigDecimal calcolaTotale(Integer impegnoAnno, Integer impegnoNumero, Integer subimpegnoAnno, Integer subimpegnoNumero);
	
	public List<CpassTOrdSubimpegnoEvasione> findByIdSubimpegnoOrdine(UUID subimpegnoOrdineId);

}
