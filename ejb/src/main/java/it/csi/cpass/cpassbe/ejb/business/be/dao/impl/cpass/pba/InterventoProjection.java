/*-
 * ========================LICENSE_START=================================
 * CPASS BackEnd - EJB submodule
 * %%
 * Copyright (C) 2019 - 2025 CSI Piemonte
 * %%
 * SPDX-FileCopyrightText: Copyright 2019 - 2025 | CSI Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 * =========================LICENSE_END==================================
 */
package it.csi.cpass.cpassbe.ejb.business.be.dao.impl.cpass.pba;

import it.csi.cpass.cpassbe.ejb.entity.CpassDStato;
import it.csi.cpass.cpassbe.ejb.entity.pba.CpassTPbaIntervento;

public class InterventoProjection {
	public InterventoProjection(CpassTPbaIntervento cpassTPbaIntervento, CpassDStato cpassDStato) {
		cpassTPbaIntervento.setCpassDStato(cpassDStato);
	}

}
