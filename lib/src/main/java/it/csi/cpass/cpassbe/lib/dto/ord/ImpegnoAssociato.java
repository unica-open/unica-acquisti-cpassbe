/*-
 * ========================LICENSE_START=================================
 * CPASS BackEnd - LIB submodule
 * %%
 * Copyright (C) 2019 - 2025 CSI Piemonte
 * %%
 * SPDX-FileCopyrightText: Copyright 2019 - 2020 | CSI Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 * =========================LICENSE_END==================================
 */
package it.csi.cpass.cpassbe.lib.dto.ord;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

import it.csi.cpass.cpassbe.lib.dto.BaseDto;
import it.csi.cpass.cpassbe.lib.dto.Impegno;

public class ImpegnoAssociato extends BaseDto<UUID> implements Serializable {

	private static final long serialVersionUID = 1L;

	private UUID id;
	private Integer anno;
	private Integer annoEsercizio;
	private Integer numero;
	private Impegno impegno;
	private TestataOrdine testataOrdine;
	private List<SubImpegnoAssociato> subimpegniAssociati;

	@Override
	public UUID getId() {
		return id;
	}
	@Override
	public void setId(UUID id) {
		this.id = id;
	}
	public Integer getAnno() {
		return anno;
	}
	public void setAnno(Integer anno) {
		this.anno = anno;
	}
	public Integer getAnnoEsercizio() {
		return annoEsercizio;
	}
	public void setAnnoEsercizio(Integer annoEsercizio) {
		this.annoEsercizio = annoEsercizio;
	}
	public Integer getNumero() {
		return numero;
	}
	public void setNumero(Integer numero) {
		this.numero = numero;
	}
	public Impegno getImpegno() {
		return impegno;
	}
	public void setImpegno(Impegno impegno) {
		this.impegno = impegno;
	}
	public TestataOrdine getTestataOrdine() {
		return testataOrdine;
	}
	public void setTestataOrdine(TestataOrdine testataOrdine) {
		this.testataOrdine = testataOrdine;
	}
	public List<SubImpegnoAssociato> getSubimpegniAssociati() {
		return subimpegniAssociati;
	}
	public void setSubimpegniAssociati(List<SubImpegnoAssociato> subimpegniAssociati) {
		this.subimpegniAssociati = subimpegniAssociati;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}


}
