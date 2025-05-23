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


import java.util.Date;

import it.csi.cpass.cpassbe.ejb.business.be.service.request.base.BasePagedRequest;
import it.csi.cpass.cpassbe.lib.dto.Impegno;
import it.csi.cpass.cpassbe.lib.dto.Ods;
import it.csi.cpass.cpassbe.lib.dto.Subimpegno;
import it.csi.cpass.cpassbe.lib.dto.ord.evasione.DestinatarioEvasione;
import it.csi.cpass.cpassbe.lib.dto.ord.evasione.TestataEvasione;

/**
 * Request for reading the Evasiones paginated
 */
public class GetRicercaEvasioniRequest extends BasePagedRequest {


	private final Integer annoEvasioneDa;
	private final Integer numeroEvasioneDa;
	private final Integer annoEvasioneA;
	private final Integer numeroEvasioneA;
	private final Date dataInserimentoDa;
	private final Date dataInserimentoA;
	private final Integer annoOrdineDa;
	private final Integer numeroOrdineDa;
	private final Integer annoOrdineA;
	private final Integer numeroOrdineA;
	private final Date dataEmissioneDa;
	private final Date dataEmissioneA;
	private final Integer annoProvvedimento;
	private final String numeroProvvedimento;
	private final String provvedimentoTipo;
	private final TestataEvasione testataEvasione;
	private final DestinatarioEvasione destinatarioEvasione;
	private final Impegno impegno;
	private final Subimpegno subimpegno;
	private final Ods oggettiSpesa;

	//	private final UUID settoreId;

	/**
	 * Constructor
	 * @param size the size
	 * @param page the page
	 * @param sort the sort
	 * @param direction the direction
	 * @param intervento the intervento
	 */
	public GetRicercaEvasioniRequest(Integer page, Integer size, String sort, String direction
			, Integer annoEvasioneDa, Integer numeroEvasioneDa, Integer annoEvasioneA, Integer numeroEvasioneA, Date dataInserimentoDa, Date dataInserimentoA
			, Integer annoOrdineDa, Integer numeroOrdineDa, Integer annoOrdineA, Integer numeroOrdineA, Date dataEmissioneDa, Date dataEmissioneA, Integer annoProvvedimento, String numeroProvvedimento
			, String provvedimentoTipo, TestataEvasione testataEvasione, DestinatarioEvasione destinatarioEvasione,Impegno impegno, Subimpegno subimpegno, Ods oggettiSpesa) {
		super(size, page, sort, direction);
		this.annoEvasioneDa = annoEvasioneDa;
		this.numeroEvasioneDa = numeroEvasioneDa;
		this.annoEvasioneA = annoEvasioneA;
		this.numeroEvasioneA = numeroEvasioneA;
		this.dataInserimentoDa = dataInserimentoDa;
		this.dataInserimentoA = dataInserimentoA;
		this.annoOrdineDa = annoOrdineDa;
		this.numeroOrdineDa = numeroOrdineDa;
		this.annoOrdineA = annoOrdineA;
		this.numeroOrdineA = numeroOrdineA;
		this.dataEmissioneDa = dataEmissioneDa;
		this.dataEmissioneA = dataEmissioneA;
		this.annoProvvedimento = annoProvvedimento;
		this.numeroProvvedimento = numeroProvvedimento;
		this.testataEvasione = testataEvasione;
		this.destinatarioEvasione = destinatarioEvasione;
		this.impegno = impegno;
		this.subimpegno = subimpegno;
		this.oggettiSpesa = oggettiSpesa;
		this.provvedimentoTipo = provvedimentoTipo;

	}

	/**
	 * @return the annoEvasioneDa
	 */
	public Integer getAnnoEvasioneDa() {
		return annoEvasioneDa;
	}

	/**
	 * @return the numeroEvasioneDa
	 */
	public Integer getNumeroEvasioneDa() {
		return numeroEvasioneDa;
	}

	/**
	 * @return the annoEvasioneA
	 */
	public Integer getAnnoEvasioneA() {
		return annoEvasioneA;
	}

	/**
	 * @return the numeroEvasioneA
	 */
	public Integer getNumeroEvasioneA() {
		return numeroEvasioneA;
	}

	/**
	 * @return the dataInserimentoDa
	 */
	public Date getDataInserimentoDa() {
		return dataInserimentoDa;
	}

	/**
	 * @return the dataInserimentoA
	 */
	public Date getDataInserimentoA() {
		return dataInserimentoA;
	}

	/**
	 * @return the annoOrdineDa
	 */
	public Integer getAnnoOrdineDa() {
		return annoOrdineDa;
	}

	/**
	 * @return the numeroOrdineDa
	 */
	public Integer getNumeroOrdineDa() {
		return numeroOrdineDa;
	}

	/**
	 * @return the annoOrdineA
	 */
	public Integer getAnnoOrdineA() {
		return annoOrdineA;
	}

	/**
	 * @return the numeroOrdineA
	 */
	public Integer getNumeroOrdineA() {
		return numeroOrdineA;
	}

	/**
	 * @return the dataEmissioneDa
	 */
	public Date getDataEmissioneDa() {
		return dataEmissioneDa;
	}

	/**
	 * @return the dataEmissioneA
	 */
	public Date getDataEmissioneA() {
		return dataEmissioneA;
	}

	/**
	 * @return the annoProvvedimento
	 */
	public Integer getAnnoProvvedimento() {
		return annoProvvedimento;
	}

	/**
	 * @return the numeroProvvedimento
	 */
	public String getNumeroProvvedimento() {
		return numeroProvvedimento;
	}

	/**
	 * @return the testataEvasione
	 */
	public TestataEvasione getTestataEvasione() {
		return testataEvasione;
	}

	/**
	 * @return the destinatario
	 */
	public DestinatarioEvasione getDestinatarioEvasione() {
		return destinatarioEvasione;
	}

	/**
	 * @return the impegno
	 */
	public Impegno getImpegno() {
		return impegno;
	}

	/**
	 * @return the subimpegno
	 */
	public Subimpegno getSubimpegno() {
		return subimpegno;
	}

	/**
	 * @return the oggettiSpesa
	 */
	public Ods getOggettiSpesa() {
		return oggettiSpesa;
	}

	public String getProvvedimentoTipo() {
		return provvedimentoTipo;
	}


}
