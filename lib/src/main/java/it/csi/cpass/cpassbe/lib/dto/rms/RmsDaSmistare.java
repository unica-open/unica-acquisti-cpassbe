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
package it.csi.cpass.cpassbe.lib.dto.rms;

import java.io.Serializable;
import java.util.UUID;

import it.csi.cpass.cpassbe.lib.dto.BaseAuditedDto;


public class RmsDaSmistare extends BaseAuditedDto<Long> implements Serializable {


	/**
	 *
	 */
	private static final long serialVersionUID = 74324586084832921L;

	private String codiceCpv;

	private String codiceOds;

	private Integer cpvId;

	private UUID enteId;

	private Integer oggettiSpesaId;

	private Boolean richiestaMagazzino;

	private UUID rigaRmsId;

	private String strutturaPadre;

	private UUID strutturaPadreId;

	private String strutturaRichiedente;

	private UUID strutturaRichiedenteId;

	private UUID testataRmsId;

	private Integer rmsAnno;

	private Integer rmsNumero;

	private Integer progressivoRiga;

	public RmsDaSmistare() {
	}

	/**
	 * @return the codiceCpv
	 */
	public String getCodiceCpv() {
		return codiceCpv;
	}

	/**
	 * @param codiceCpv the codiceCpv to set
	 */
	public void setCodiceCpv(String codiceCpv) {
		this.codiceCpv = codiceCpv;
	}

	/**
	 * @return the codiceOds
	 */
	public String getCodiceOds() {
		return codiceOds;
	}

	/**
	 * @param codiceOds the codiceOds to set
	 */
	public void setCodiceOds(String codiceOds) {
		this.codiceOds = codiceOds;
	}

	/**
	 * @return the cpvId
	 */
	public Integer getCpvId() {
		return cpvId;
	}

	/**
	 * @param cpvId the cpvId to set
	 */
	public void setCpvId(Integer cpvId) {
		this.cpvId = cpvId;
	}

	/**
	 * @return the enteId
	 */
	public UUID getEnteId() {
		return enteId;
	}

	/**
	 * @param enteId the enteId to set
	 */
	public void setEnteId(UUID enteId) {
		this.enteId = enteId;
	}

	/**
	 * @return the oggettiSpesaId
	 */
	public Integer getOggettiSpesaId() {
		return oggettiSpesaId;
	}

	/**
	 * @param oggettiSpesaId the oggettiSpesaId to set
	 */
	public void setOggettiSpesaId(Integer oggettiSpesaId) {
		this.oggettiSpesaId = oggettiSpesaId;
	}

	/**
	 * @return the richiestaMagazzino
	 */
	public Boolean getRichiestaMagazzino() {
		return richiestaMagazzino;
	}

	/**
	 * @param richiestaMagazzino the richiestaMagazzino to set
	 */
	public void setRichiestaMagazzino(Boolean richiestaMagazzino) {
		this.richiestaMagazzino = richiestaMagazzino;
	}

	/**
	 * @return the rigaRmsId
	 */
	public UUID getRigaRmsId() {
		return rigaRmsId;
	}

	/**
	 * @param rigaRmsId the rigaRmsId to set
	 */
	public void setRigaRmsId(UUID rigaRmsId) {
		this.rigaRmsId = rigaRmsId;
	}

	/**
	 * @return the strutturaPadre
	 */
	public String getStrutturaPadre() {
		return strutturaPadre;
	}

	/**
	 * @param strutturaPadre the strutturaPadre to set
	 */
	public void setStrutturaPadre(String strutturaPadre) {
		this.strutturaPadre = strutturaPadre;
	}

	/**
	 * @return the strutturaPadreId
	 */
	public UUID getStrutturaPadreId() {
		return strutturaPadreId;
	}

	/**
	 * @param strutturaPadreId the strutturaPadreId to set
	 */
	public void setStrutturaPadreId(UUID strutturaPadreId) {
		this.strutturaPadreId = strutturaPadreId;
	}

	/**
	 * @return the strutturaRichiedente
	 */
	public String getStrutturaRichiedente() {
		return strutturaRichiedente;
	}

	/**
	 * @param strutturaRichiedente the strutturaRichiedente to set
	 */
	public void setStrutturaRichiedente(String strutturaRichiedente) {
		this.strutturaRichiedente = strutturaRichiedente;
	}

	/**
	 * @return the strutturaRichiedenteId
	 */
	public UUID getStrutturaRichiedenteId() {
		return strutturaRichiedenteId;
	}

	/**
	 * @param strutturaRichiedenteId the strutturaRichiedenteId to set
	 */
	public void setStrutturaRichiedenteId(UUID strutturaRichiedenteId) {
		this.strutturaRichiedenteId = strutturaRichiedenteId;
	}

	/**
	 * @return the testataRmsId
	 */
	public UUID getTestataRmsId() {
		return testataRmsId;
	}

	/**
	 * @param testataRmsId the testataRmsId to set
	 */
	public void setTestataRmsId(UUID testataRmsId) {
		this.testataRmsId = testataRmsId;
	}

	/**
	 * @return the rmsAnno
	 */
	public Integer getRmsAnno() {
		return rmsAnno;
	}

	/**
	 * @param rmsAnno the rmsAnno to set
	 */
	public void setRmsAnno(Integer rmsAnno) {
		this.rmsAnno = rmsAnno;
	}

	/**
	 * @return the rmsNumero
	 */
	public Integer getRmsNumero() {
		return rmsNumero;
	}

	/**
	 * @param rmsNumero the rmsNumero to set
	 */
	public void setRmsNumero(Integer rmsNumero) {
		this.rmsNumero = rmsNumero;
	}

	/**
	 * @return the progressivoRiga
	 */
	public Integer getProgressivoRiga() {
		return progressivoRiga;
	}

	/**
	 * @param progressivoRiga the progressivoRiga to set
	 */
	public void setProgressivoRiga(Integer progressivoRiga) {
		this.progressivoRiga = progressivoRiga;
	}


}
