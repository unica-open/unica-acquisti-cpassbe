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
package it.csi.cpass.cpassbe.ejb.business.be.service.request.ordine.rda;


import java.util.Date;

import it.csi.cpass.cpassbe.ejb.business.be.service.request.base.BasePagedRequest;
import it.csi.cpass.cpassbe.lib.dto.Settore;
import it.csi.cpass.cpassbe.lib.dto.ord.rda.RigaRda;
import it.csi.cpass.cpassbe.lib.dto.ord.rda.TestataRda;

/**
 * Request for reading the Rms paginated
 */
public class GetRicercaRdaRequest extends BasePagedRequest {

	private final Integer rdaAnnoDa;
	private final Integer rdaNumeroDa;
	private final Integer rdaAnnoA;
	private final Integer rdaNumeroA;
	private final Date dataInserimentoDa;
	private final Date dataInserimentoA;
	private final TestataRda testataRda;
	private final RigaRda rigaRda;
	private final Settore settoreDestinatario;

	public GetRicercaRdaRequest(Integer page, Integer size, String sort, String direction, Integer rdaAnnoDa, Integer rdaNumeroDa, Integer rdaAnnoA,
			Integer rdaNumeroA, Date dataInserimentoDa, Date dataInserimentoA, TestataRda testataRda, RigaRda rigaRda,
			Settore settoreDestinatario) {
		super(size, page, sort, direction);
		this.rdaAnnoDa = rdaAnnoDa;
		this.rdaNumeroDa = rdaNumeroDa;
		this.rdaAnnoA = rdaAnnoA;
		this.rdaNumeroA = rdaNumeroA;
		this.dataInserimentoDa = dataInserimentoDa;
		this.dataInserimentoA = dataInserimentoA;
		this.testataRda = testataRda;
		this.rigaRda = rigaRda;
		this.settoreDestinatario = settoreDestinatario;
	}

	public Integer getRdaAnnoDa() {
		return rdaAnnoDa;
	}

	public Integer getRdaNumeroDa() {
		return rdaNumeroDa;
	}

	public Integer getRdaAnnoA() {
		return rdaAnnoA;
	}

	public Integer getRdaNumeroA() {
		return rdaNumeroA;
	}

	public Date getDataInserimentoDa() {
		return dataInserimentoDa;
	}

	public Date getDataInserimentoA() {
		return dataInserimentoA;
	}

	public TestataRda getTestataRda() {
		return testataRda;
	}

	public RigaRda getRigaRda() {
		return rigaRda;
	}

	public Settore getSettoreDestinatario() {
		return settoreDestinatario;
	}

}
