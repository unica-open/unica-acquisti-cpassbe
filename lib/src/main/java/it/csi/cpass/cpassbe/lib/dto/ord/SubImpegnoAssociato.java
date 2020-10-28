/*-
 * ========================LICENSE_START=================================
 * CPASS BackEnd - LIB submodule
 * %%
 * Copyright (C) 2019 - 2020 CSI Piemonte
 * %%
 * SPDX-FileCopyrightText: Copyright 2019 - 2020 | CSI Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 * =========================LICENSE_END==================================
 */
package it.csi.cpass.cpassbe.lib.dto.ord;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

import it.csi.cpass.cpassbe.lib.dto.BaseDto;
import it.csi.cpass.cpassbe.lib.dto.Subimpegno;

public class SubImpegnoAssociato extends BaseDto<UUID> implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private UUID id;
	private Integer anno;
	private Integer annoEsercizio;
	private Integer numero;
	private Integer annoSubImpegno;
	private BigDecimal importoSubImpegno;
	private Integer numeroSubImpegno;
	private ImpegnoAssociato impegnoAssociato;
	private Subimpegno subImpegno;
	
	public UUID getId() {
		return id;
	}
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
	public Integer getAnnoSubImpegno() {
		return annoSubImpegno;
	}
	public void setAnnoSubImpegno(Integer annoSubImpegno) {
		this.annoSubImpegno = annoSubImpegno;
	}
	public BigDecimal getImportoSubImpegno() {
		return importoSubImpegno;
	}
	public void setImportoSubImpegno(BigDecimal importoSubImpegno) {
		this.importoSubImpegno = importoSubImpegno;
	}
	public Integer getNumeroSubImpegno() {
		return numeroSubImpegno;
	}
	public void setNumeroSubImpegno(Integer numeroSubImpegno) {
		this.numeroSubImpegno = numeroSubImpegno;
	}
	public ImpegnoAssociato getImpegnoAssociato() {
		return impegnoAssociato;
	}
	public void setImpegnoAssociato(ImpegnoAssociato impegnoAssociato) {
		this.impegnoAssociato = impegnoAssociato;
	}
	public Subimpegno getSubImpegno() {
		return subImpegno;
	}
	public void setSubImpegno(Subimpegno subImpegno) {
		this.subImpegno = subImpegno;
	}
	
	

}
