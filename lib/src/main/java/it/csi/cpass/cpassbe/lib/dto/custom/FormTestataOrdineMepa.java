/*-
 * ========================LICENSE_START=================================
 * CPASS BackEnd - LIB submodule
 * %%
 * Copyright (C) 2019 - 2021 CSI Piemonte
 * %%
 * SPDX-FileCopyrightText: Copyright 2019 - 2021 | CSI Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 * =========================LICENSE_END==================================
 */
package it.csi.cpass.cpassbe.lib.dto.custom;

import java.util.List;

import it.csi.cpass.cpassbe.lib.dto.Fornitore;
import it.csi.cpass.cpassbe.lib.dto.MotiviEsclusioneCig;
import it.csi.cpass.cpassbe.lib.dto.Provvedimento;
import it.csi.cpass.cpassbe.lib.dto.ord.Destinatario;
import it.csi.cpass.cpassbe.lib.dto.ord.TipoProceduraOrd;

public class FormTestataOrdineMepa {
	private Fornitore fornitore;
	private Provvedimento provvedimento;
	private TipoProceduraOrd tipoProceduraOrd;
	private String numeroProcedura;
	private Destinatario destinatario;
	private List<RigaMepaWrapper> righe;
	private String cig;
	private MotiviEsclusioneCig motiviEsclusioneCig;


	public Fornitore getFornitore() {
		return fornitore;
	}

	public void setFornitore(Fornitore fornitore) {
		this.fornitore = fornitore;
	}

	public Provvedimento getProvvedimento() {
		return provvedimento;
	}

	public void setProvvedimento(Provvedimento provvedimento) {
		this.provvedimento = provvedimento;
	}

	public TipoProceduraOrd getTipoProceduraOrd() {
		return tipoProceduraOrd;
	}

	public void setTipoProceduraOrd(TipoProceduraOrd tipoProceduraOrd) {
		this.tipoProceduraOrd = tipoProceduraOrd;
	}

	public String getNumeroProcedura() {
		return numeroProcedura;
	}

	public void setNumeroProcedura(String numeroProcedura) {
		this.numeroProcedura = numeroProcedura;
	}

	public Destinatario getDestinatario() {
		return destinatario;
	}

	public void setDestinatario(Destinatario destinatario) {
		this.destinatario = destinatario;
	}

	public List<RigaMepaWrapper> getRighe() {
		return righe;
	}

	public void setRighe(List<RigaMepaWrapper> righe) {
		this.righe = righe;
	}

	/**
	 * @return the cig
	 */
	public String getCig() {
		return cig;
	}

	/**
	 * @param cig the cig to set
	 */
	public void setCig(String cig) {
		this.cig = cig;
	}

	/**
	 * @return the motiviEsclusioneCig
	 */
	public MotiviEsclusioneCig getMotiviEsclusioneCig() {
		return motiviEsclusioneCig;
	}

	/**
	 * @param motiviEsclusioneCig the motiviEsclusioneCig to set
	 */
	public void setMotiviEsclusioneCig(MotiviEsclusioneCig motiviEsclusioneCig) {
		this.motiviEsclusioneCig = motiviEsclusioneCig;
	}

}
