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

import it.csi.cpass.cpassbe.ejb.business.be.dao.BaseEntityDao;
import it.csi.cpass.cpassbe.ejb.entity.ord.CpassVOrdine;
import it.csi.cpass.cpassbe.lib.dto.ord.consultazioni.ConsultazioniOrdine;

/**
 * Data Access Object interface for the entity CpassTPbaIntervento
 */
public interface CpassVOrdineConsultazioneDao extends BaseEntityDao<Long, CpassVOrdine> {

	List<ConsultazioniOrdine> getListOrdineByImpegni( Integer annoEsercizio
			, Integer annoImpegno
			, Integer numeroImpegno
			, Integer annoSubImpegno
			, Integer numeroSubImpegno);

}
