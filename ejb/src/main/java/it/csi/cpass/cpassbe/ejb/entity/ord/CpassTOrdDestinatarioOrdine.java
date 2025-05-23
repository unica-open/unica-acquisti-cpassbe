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
package it.csi.cpass.cpassbe.ejb.entity.ord;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import it.csi.cpass.cpassbe.ejb.entity.CpassDStato;
import it.csi.cpass.cpassbe.ejb.entity.CpassTSettore;
import it.csi.cpass.cpassbe.ejb.entity.base.BaseAuditedEntity;
import it.csi.cpass.cpassbe.lib.util.uuid.UuidUtils;


/**
 * The persistent class for the cpass_t_ord_destinatario database table.
 *
 */
@Entity
@Table(name="cpass_t_ord_destinatario_ordine")
@NamedQuery(name="CpassTOrdDestinatarioOrdine.findAll", query="SELECT c FROM CpassTOrdDestinatarioOrdine c")
public class CpassTOrdDestinatarioOrdine  extends BaseAuditedEntity<UUID> implements Serializable {
	private static final long serialVersionUID = 1L;
	/** The UUID namespace */
	public static final UUID NAMESPACE = UuidUtils.generateUUIDv5FromNamespaceAndString(UuidUtils.NAMESPACE_OID, "cpass_t_ord_destinatario_ordine");


	@Id
	@Column(name="destinatario_id")
	private UUID destinatarioId;

	private Integer progressivo;

	private String cap;

	private String contatto;

	@Column(name="data_invio_nso")
	private Date dataInvioNso;

	private String email;

	private String indirizzo;

	private String localita;

	@Column(name="num_civico")
	private String numCivico;

	private String provincia;

	private String telefono;



	//bi-directional many-to-one association to CpassDStato
	@ManyToOne
	@JoinColumn(name="stato_id")
	private CpassDStato cpassDStato;

	//bi-directional many-to-one association to CpassTOrdTestataOrdine
	@ManyToOne
	@JoinColumn(name="testata_ordine_id")
	private CpassTOrdTestataOrdine cpassTOrdTestataOrdine;

	//bi-directional many-to-one association to CpassTSettore
	@ManyToOne
	@JoinColumn(name="settore_destinatario_id")
	private CpassTSettore cpassTSettore;

	//bi-directional many-to-one association to CpassTOrdRigaOrdine
	@OneToMany(mappedBy="cpassTOrdDestinatario")
	private List<CpassTOrdRigaOrdine> cpassTOrdRigaOrdines;

	// bi-directional many-to-one association to CpassDOrdStatoNso
	@ManyToOne
	@JoinColumn(name = "stato_nso_id")
	private CpassDOrdStatoNso cpassDOrdStatoNso;

	@Column(name="settore_indirizzo_codice")
	private String settoreIndirizzoCodice;

	@ManyToOne
	@JoinColumn(name = "settore_indirizzo_id")
	private CpassTSettoreIndirizzo cpassTSettoreIndirizzo;


	public CpassTOrdDestinatarioOrdine() {
	}

	public UUID getDestinatarioId() {
		return this.destinatarioId;
	}

	public void setDestinatarioId(UUID destinatarioId) {
		this.destinatarioId = destinatarioId;
	}

	public String getCap() {
		return this.cap;
	}

	public void setCap(String cap) {
		this.cap = cap;
	}

	public String getContatto() {
		return this.contatto;
	}

	public void setContatto(String contatto) {
		this.contatto = contatto;
	}

	public Date getDataInvioNso() {
		return this.dataInvioNso;
	}

	public void setDataInvioNso(Date dataInvioNso) {
		this.dataInvioNso = dataInvioNso;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getIndirizzo() {
		return this.indirizzo;
	}

	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}

	public String getLocalita() {
		return this.localita;
	}

	public void setLocalita(String localita) {
		this.localita = localita;
	}

	public String getNumCivico() {
		return this.numCivico;
	}

	public void setNumCivico(String numCivico) {
		this.numCivico = numCivico;
	}

	public Integer getProgressivo() {
		return this.progressivo;
	}

	public void setProgressivo(Integer progressivo) {
		this.progressivo = progressivo;
	}

	public String getProvincia() {
		return this.provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public String getTelefono() {
		return this.telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public CpassTOrdTestataOrdine getCpassTOrdTestataOrdine() {
		return this.cpassTOrdTestataOrdine;
	}

	/**
	 * @return the cpassDStato
	 */
	public CpassDStato getCpassDStato() {
		return cpassDStato;
	}

	/**
	 * @param cpassDStato the cpassDStato to set
	 */
	public void setCpassDStato(CpassDStato cpassDStato) {
		this.cpassDStato = cpassDStato;
	}

	public void setCpassTOrdTestataOrdine(CpassTOrdTestataOrdine cpassTOrdTestataOrdine) {
		this.cpassTOrdTestataOrdine = cpassTOrdTestataOrdine;
	}

	public CpassTSettore getCpassTSettore() {
		return this.cpassTSettore;
	}

	public void setCpassTSettore(CpassTSettore cpassTSettore) {
		this.cpassTSettore = cpassTSettore;
	}

	public List<CpassTOrdRigaOrdine> getCpassTOrdRigaOrdines() {
		return this.cpassTOrdRigaOrdines;
	}

	public void setCpassTOrdRigaOrdines(List<CpassTOrdRigaOrdine> cpassTOrdRigaOrdines) {
		this.cpassTOrdRigaOrdines = cpassTOrdRigaOrdines;
	}

	public CpassTOrdRigaOrdine addCpassTOrdRigaOrdine(CpassTOrdRigaOrdine cpassTOrdRigaOrdine) {
		getCpassTOrdRigaOrdines().add(cpassTOrdRigaOrdine);
		cpassTOrdRigaOrdine.setCpassTOrdDestinatario(this);

		return cpassTOrdRigaOrdine;
	}

	public CpassTOrdRigaOrdine removeCpassTOrdRigaOrdine(CpassTOrdRigaOrdine cpassTOrdRigaOrdine) {
		getCpassTOrdRigaOrdines().remove(cpassTOrdRigaOrdine);
		cpassTOrdRigaOrdine.setCpassTOrdDestinatario(null);

		return cpassTOrdRigaOrdine;
	}

	/**
	 * @return the cpassDOrdStatoNso
	 */
	public CpassDOrdStatoNso getCpassDOrdStatoNso() {
		return cpassDOrdStatoNso;
	}

	/**
	 * @param cpassDOrdStatoNso the cpassDOrdStatoNso to set
	 */
	public void setCpassDOrdStatoNso(CpassDOrdStatoNso cpassDOrdStatoNso) {
		this.cpassDOrdStatoNso = cpassDOrdStatoNso;
	}



	/**
	 * @return the settoreIndirizzoCodice
	 */
	public String getSettoreIndirizzoCodice() {
		return settoreIndirizzoCodice;
	}

	/**
	 * @param settoreIndirizzoCodice the settoreIndirizzoCodice to set
	 */
	public void setSettoreIndirizzoCodice(String settoreIndirizzoCodice) {
		this.settoreIndirizzoCodice = settoreIndirizzoCodice;
	}

	/**
	 * @return the cpassTSettoreIndirizzo
	 */
	public CpassTSettoreIndirizzo getCpassTSettoreIndirizzo() {
		return cpassTSettoreIndirizzo;
	}

	/**
	 * @param cpassTSettoreIndirizzo the cpassTSettoreIndirizzo to set
	 */
	public void setCpassTSettoreIndirizzo(CpassTSettoreIndirizzo cpassTSettoreIndirizzo) {
		this.cpassTSettoreIndirizzo = cpassTSettoreIndirizzo;
	}

	@Override
	public UUID getId() {
		return destinatarioId;
	}

	@Override
	public void setId(UUID id) {
		destinatarioId = id;
	}

	@Override
	public void initId() {
		this.destinatarioId = UuidUtils.generateUUIDv5FromNamespaceAndString(NAMESPACE, cpassTOrdTestataOrdine.getId()+"|"+ progressivo);
	}

}
