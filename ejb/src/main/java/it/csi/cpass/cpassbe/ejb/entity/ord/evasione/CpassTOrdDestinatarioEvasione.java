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
package it.csi.cpass.cpassbe.ejb.entity.ord.evasione;

import java.io.Serializable;
import javax.persistence.*;

import it.csi.cpass.cpassbe.ejb.entity.CpassTSettore;
import it.csi.cpass.cpassbe.ejb.entity.base.BaseAuditedEntity;
import it.csi.cpass.cpassbe.ejb.entity.ord.CpassDStatoElOrdine;
import it.csi.cpass.cpassbe.ejb.entity.ord.CpassTOrdDestinatarioOrdine;
import it.csi.cpass.cpassbe.ejb.entity.ord.evasione.CpassTOrdRigaEvasione;
import it.csi.cpass.cpassbe.ejb.entity.ord.evasione.CpassTOrdTestataEvasione;
import it.csi.cpass.cpassbe.lib.util.uuid.UuidUtils;

import java.util.List;
import java.util.UUID;


/**
 * The persistent class for the cpass_t_ord_destinatario_evasione database table.
 * 
 */
@Entity
@Table(name="cpass_t_ord_destinatario_evasione")
@NamedQuery(name="CpassTOrdDestinatarioEvasione.findAll", query="SELECT c FROM CpassTOrdDestinatarioEvasione c")
public class CpassTOrdDestinatarioEvasione extends BaseAuditedEntity<UUID> implements Serializable {
	private static final long serialVersionUID = 1L;

	/** The UUID namespace */
	public static final UUID NAMESPACE = UuidUtils.generateUUIDv5FromNamespaceAndString(UuidUtils.NAMESPACE_OID, "cpass_t_ord_destinatario_evasione");

	@Id
	@Column(name="destinatario_evasione_id")
	private UUID destinatarioEvasioneId;

	private Integer progressivo;

	//bi-directional many-to-one association to CpassDStatoElOrdine
	@ManyToOne
	@JoinColumn(name="stato_el_ordine_id")
	private CpassDStatoElOrdine cpassDStatoElOrdine;

	//bi-directional many-to-one association to CpassTOrdDestinatario
	@ManyToOne
	@JoinColumn(name="destinatario_id")
	private CpassTOrdDestinatarioOrdine cpassTOrdDestinatarioOrdine;

	//bi-directional many-to-one association to CpassTOrdTestataEvasione
	@ManyToOne
	@JoinColumn(name="testata_evasione_id")
	private CpassTOrdTestataEvasione cpassTOrdTestataEvasione;

	//bi-directional many-to-one association to CpassTSettore
	@ManyToOne
	@JoinColumn(name="settore_destinatario_id")
	private CpassTSettore cpassTSettore;

	//bi-directional many-to-one association to CpassTOrdRigaEvasione
	@OneToMany(mappedBy="cpassTOrdDestinatarioEvasione")
	private List<CpassTOrdRigaEvasione> cpassTOrdRigaEvasiones;
	
	private String cap;
	private String email;
	private String indirizzo;
	private String localita;

	@Column(name="num_civico")
	private String numCivico;
	private String contatto;
	private String provincia;
	private String telefono;

	public CpassTOrdDestinatarioEvasione() {
	}

	public UUID getDestinatarioEvasioneId() {
		return this.destinatarioEvasioneId;
	}

	public void setDestinatarioEvasioneId(UUID destinatarioEvasioneId) {
		this.destinatarioEvasioneId = destinatarioEvasioneId;
	}

	public Integer getProgressivo() {
		return this.progressivo;
	}

	public void setProgressivo(Integer progressivo) {
		this.progressivo = progressivo;
	}

	public CpassDStatoElOrdine getCpassDStatoElOrdine() {
		return this.cpassDStatoElOrdine;
	}

	public void setCpassDStatoElOrdine(CpassDStatoElOrdine cpassDStatoElOrdine) {
		this.cpassDStatoElOrdine = cpassDStatoElOrdine;
	}

	public CpassTOrdDestinatarioOrdine getCpassTOrdDestinatarioOrdine() {
		return this.cpassTOrdDestinatarioOrdine;
	}

	public void setCpassTOrdDestinatarioOrdine(CpassTOrdDestinatarioOrdine cpassTOrdDestinatarioOrdine) {
		this.cpassTOrdDestinatarioOrdine = cpassTOrdDestinatarioOrdine;
	}

	public CpassTOrdTestataEvasione getCpassTOrdTestataEvasione() {
		return this.cpassTOrdTestataEvasione;
	}

	public void setCpassTOrdTestataEvasione(CpassTOrdTestataEvasione cpassTOrdTestataEvasione) {
		this.cpassTOrdTestataEvasione = cpassTOrdTestataEvasione;
	}

	public CpassTSettore getCpassTSettore() {
		return this.cpassTSettore;
	}

	public void setCpassTSettore(CpassTSettore cpassTSettore) {
		this.cpassTSettore = cpassTSettore;
	}

	public List<CpassTOrdRigaEvasione> getCpassTOrdRigaEvasiones() {
		return this.cpassTOrdRigaEvasiones;
	}

	public void setCpassTOrdRigaEvasiones(List<CpassTOrdRigaEvasione> cpassTOrdRigaEvasiones) {
		this.cpassTOrdRigaEvasiones = cpassTOrdRigaEvasiones;
	}

	public CpassTOrdRigaEvasione addCpassTOrdRigaEvasione(CpassTOrdRigaEvasione cpassTOrdRigaEvasione) {
		getCpassTOrdRigaEvasiones().add(cpassTOrdRigaEvasione);
		cpassTOrdRigaEvasione.setCpassTOrdDestinatarioEvasione(this);

		return cpassTOrdRigaEvasione;
	}

	public CpassTOrdRigaEvasione removeCpassTOrdRigaEvasione(CpassTOrdRigaEvasione cpassTOrdRigaEvasione) {
		getCpassTOrdRigaEvasiones().remove(cpassTOrdRigaEvasione);
		cpassTOrdRigaEvasione.setCpassTOrdDestinatarioEvasione(null);
		return cpassTOrdRigaEvasione;
	}
	
	public String getCap() {
		return cap;
	}

	public void setCap(String cap) {
		this.cap = cap;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getIndirizzo() {
		return indirizzo;
	}

	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}

	public String getLocalita() {
		return localita;
	}

	public void setLocalita(String localita) {
		this.localita = localita;
	}

	public String getNumCivico() {
		return numCivico;
	}

	public void setNumCivico(String numCivico) {
		this.numCivico = numCivico;
	}

	public String getContatto() {
		return contatto;
	}

	public void setContatto(String contatto) {
		this.contatto = contatto;
	}

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	@Override
	public UUID getId() {
		return destinatarioEvasioneId;
	}

	@Override
	public void setId(UUID id) {
		destinatarioEvasioneId = id;
	}

	@Override
	public void initId() {
		this.destinatarioEvasioneId = UuidUtils.generateUUIDv5FromNamespaceAndString(NAMESPACE, cpassTOrdTestataEvasione.getId()+"|"+ progressivo);
	}

}
