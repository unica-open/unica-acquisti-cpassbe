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
package it.csi.cpass.cpassbe.ejb.business.be.service.request.evasione;

import java.util.UUID;

import it.csi.cpass.cpassbe.ejb.business.be.service.request.base.BaseRequest;

public class GetEsposizioneImpegniByRigaOrdineRequest implements BaseRequest {

	private final UUID idRigaEvasione;
	private final Boolean bDistribuzioneTotaleRigaSugliImpegni;

	/**
	 * Constructor
	 *
	 * @param idRigaOrdine the riga Evasione id
	 */
	public GetEsposizioneImpegniByRigaOrdineRequest(UUID idRigaEvasione, Boolean bDistribuzioneTotaleRigaSugliImpegni) {
		this.idRigaEvasione = idRigaEvasione;
		this.bDistribuzioneTotaleRigaSugliImpegni = bDistribuzioneTotaleRigaSugliImpegni;
	}

	public UUID getIdRigaEvasione() {
		return idRigaEvasione;
	}

	public Boolean isDistribuzioneTotaleRigaSugliImpegni() {
		return bDistribuzioneTotaleRigaSugliImpegni;
	}

}
