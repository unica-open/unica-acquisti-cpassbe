/*-
 * ========================LICENSE_START=================================
 * CPASS BackEnd - WAR submodule
 * %%
 * Copyright (C) 2019 - 2021 CSI Piemonte
 * %%
 * SPDX-FileCopyrightText: Copyright 2019 - 2021 | CSI Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 * =========================LICENSE_END==================================
 */
package it.csi.cpass.cpassbe.web.dto;

import java.util.Date;

import it.csi.cpass.cpassbe.lib.dto.Settore;
import it.csi.cpass.cpassbe.lib.dto.ord.rda.RigaRda;
import it.csi.cpass.cpassbe.lib.dto.ord.rda.TestataRda;

public class RicercaRda {

	private Integer rdaAnnoDa;
	private Integer rdaNumeroDa;
	private Integer rdaAnnoA;
	private Integer rdaNumeroA;
	private Date dataInserimentoDa;
	private Date dataInserimentoA;
	private TestataRda testataRda;
	private RigaRda rigaRda;
	private Settore settoreDestinatario;




	public Integer getRdaAnnoDa() {
		return rdaAnnoDa;
	}
	public void setRdaAnnoDa(Integer rdaAnnoDa) {
		this.rdaAnnoDa = rdaAnnoDa;
	}
	public Integer getRdaNumeroDa() {
		return rdaNumeroDa;
	}
	public void setRdaNumeroDa(Integer rdaNumeroDa) {
		this.rdaNumeroDa = rdaNumeroDa;
	}
	public Integer getRdaAnnoA() {
		return rdaAnnoA;
	}
	public void setRdaAnnoA(Integer rdaAnnoA) {
		this.rdaAnnoA = rdaAnnoA;
	}
	public Integer getRdaNumeroA() {
		return rdaNumeroA;
	}
	public void setRdaNumeroA(Integer rdaNumeroA) {
		this.rdaNumeroA = rdaNumeroA;
	}
	public Date getDataInserimentoDa() {
		return dataInserimentoDa;
	}
	public void setDataInserimentoDa(Date dataInserimentoDa) {
		this.dataInserimentoDa = dataInserimentoDa;
	}
	public Date getDataInserimentoA() {
		return dataInserimentoA;
	}
	public void setDataInserimentoA(Date dataInserimentoA) {
		this.dataInserimentoA = dataInserimentoA;
	}
	public TestataRda getTestataRda() {
		return testataRda;
	}
	public void setTestataRda(TestataRda testataRda) {
		this.testataRda = testataRda;
	}
	public RigaRda getRigaRda() {
		return rigaRda;
	}
	public void setRigaRda(RigaRda rigaRda) {
		this.rigaRda = rigaRda;
	}

	public Settore getSettoreDestinatario() {
		return settoreDestinatario;
	}
	public void setSettoreDestinatario(Settore settoreDestinatario) {
		this.settoreDestinatario = settoreDestinatario;
	}



}
