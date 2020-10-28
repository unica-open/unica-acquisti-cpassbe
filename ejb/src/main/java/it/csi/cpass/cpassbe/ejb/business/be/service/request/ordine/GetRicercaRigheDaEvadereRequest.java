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
package it.csi.cpass.cpassbe.ejb.business.be.service.request.ordine;


import java.util.Date;
import java.util.List;

import it.csi.cpass.cpassbe.ejb.business.be.service.request.base.BaseRequest;
import it.csi.cpass.cpassbe.lib.dto.Impegno;
import it.csi.cpass.cpassbe.lib.dto.Subimpegno;
import it.csi.cpass.cpassbe.lib.dto.ord.Destinatario;
import it.csi.cpass.cpassbe.lib.dto.ord.OggettiSpesa;
import it.csi.cpass.cpassbe.lib.dto.ord.RigaOrdine;
import it.csi.cpass.cpassbe.lib.dto.ord.TestataOrdine;

/**
 * Request for reading the Ordines paginated
 */
public class GetRicercaRigheDaEvadereRequest implements BaseRequest {


	private final Integer annoOrdineDa;
	private final Integer numeroOrdineDa;
	private final Integer annoOrdineA;
	private final Integer numeroOrdineA;
	private final Date dataEmissioneDa;
	private final Date dataEmissioneA;
	private final TestataOrdine testataOrdine;
	private final Destinatario destinatario;
	private final Impegno impegno;
	private final Subimpegno subimpegno;
	private final RigaOrdine rigaOrdine;
	private final List<OggettiSpesa> odsList;
	
//	private final UUID settoreId;
	
	/**
	 * Constructor
	 * @param size the size
	 * @param page the page
	 * @param sort the sort
	 * @param direction the direction
	 * @param intervento the intervento
	 */
	public GetRicercaRigheDaEvadereRequest(Integer annoOrdineDa, Integer numeroOrdineDa, Integer annoOrdineA, Integer numeroOrdineA, Date dataEmissioneDa, Date dataEmissioneA, TestataOrdine testataOrdine, Destinatario destinatario,Impegno impegno, Subimpegno subimpegno, RigaOrdine rigaOrdine, List<OggettiSpesa> odsList) {
		this.annoOrdineDa = annoOrdineDa;
		this.numeroOrdineDa = numeroOrdineDa;
		this.annoOrdineA = annoOrdineA;
		this.numeroOrdineA = numeroOrdineA;
		this.dataEmissioneDa = dataEmissioneDa;
		this.dataEmissioneA = dataEmissioneA;
		this.testataOrdine = testataOrdine;
		this.destinatario = destinatario;
		this.impegno = impegno;
		this.subimpegno = subimpegno;
		this.rigaOrdine = rigaOrdine;
		this.odsList = odsList;
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
	 * @return the testataOrdine
	 */
	public TestataOrdine getTestataOrdine() {
		return testataOrdine;
	}
	
	/**
	 * @return the destinatario
	 */
	public Destinatario getDestinatario() {
		return destinatario;
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
	 * @return the rigaOrdine
	 */
	public RigaOrdine getRigaOrdine() {
		return rigaOrdine;
	}
	/**
	 * @return the odsList
	 */
	public List<OggettiSpesa> getOdsList() {
		return odsList;
	}
}
