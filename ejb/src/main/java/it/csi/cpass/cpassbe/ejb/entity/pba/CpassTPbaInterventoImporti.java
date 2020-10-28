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
package it.csi.cpass.cpassbe.ejb.entity.pba;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import it.csi.cpass.cpassbe.ejb.entity.base.BaseAuditedEntity;
import it.csi.cpass.cpassbe.lib.util.uuid.UuidUtils;


/**
 * The persistent class for the cpass_t_intervento_importi database table.
 *
 */
@Entity
@Table(name="cpass_t_pba_intervento_importi")
@NamedQuery(name="CpassTPbaInterventoImporti.findAll", query="SELECT c FROM CpassTPbaInterventoImporti c")
public class CpassTPbaInterventoImporti extends BaseAuditedEntity<UUID> implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	/** The UUID namespace */
	public static final UUID NAMESPACE = UuidUtils.generateUUIDv5FromNamespaceAndString(UuidUtils.NAMESPACE_OID, "cpass_t_intervento_importi");

	/** The intervento importi id. */
	@Id
	@Column(name="intervento_importi_id", unique=true, nullable=false)
	private UUID interventoImportiId;

	/** The intervento importi importo anni successivi. */
	@Column(name="intervento_importi_importo_anni_successivi", nullable=false, precision=131089)
	private BigDecimal interventoImportiImportoAnniSuccessivi;

	/** The intervento importi importo anno primo. */
	@Column(name="intervento_importi_importo_anno_primo", nullable=false, precision=131089)
	private BigDecimal interventoImportiImportoAnnoPrimo;

	/** The intervento importi importo anno secondo. */
	@Column(name="intervento_importi_importo_anno_secondo", nullable=false, precision=131089)
	private BigDecimal interventoImportiImportoAnnoSecondo;

	@Column(name="motivazione")
    private String motivazione;
	
	@Column(name="richiesta_motivazione")
    private Boolean richiestaMotivazione;

    
	/** The cpass D risorsa. */
	//bi-directional many-to-one association to CpassDPbaRisorsa
	@ManyToOne
	@JoinColumn(name="risorsa_id", nullable=false)
	private CpassDPbaRisorsa cpassDPbaRisorsa;

	/** The cpass T intervento. */
	//bi-directional many-to-one association to CpassTPbaIntervento
	@ManyToOne
	@JoinColumn(name="intervento_id", nullable=false)
	private CpassTPbaIntervento cpassTPbaIntervento;

	/**
	 * Gets the intervento importi id.
	 *
	 * @return the intervento importi id
	 */
	public UUID getInterventoImportiId() {
		return this.interventoImportiId;
	}

	/**
	 * Sets the intervento importi id.
	 *
	 * @param interventoImportiId the new intervento importi id
	 */
	public void setInterventoImportiId(UUID interventoImportiId) {
		this.interventoImportiId = interventoImportiId;
	}

	/**
	 * Gets the intervento importi importo anni successivi.
	 *
	 * @return the intervento importi importo anni successivi
	 */
	public BigDecimal getInterventoImportiImportoAnniSuccessivi() {
		return this.interventoImportiImportoAnniSuccessivi;
	}

	/**
	 * Sets the intervento importi importo anni successivi.
	 *
	 * @param interventoImportiImportoAnniSuccessivi the new intervento importi importo anni successivi
	 */
	public void setInterventoImportiImportoAnniSuccessivi(BigDecimal interventoImportiImportoAnniSuccessivi) {
		this.interventoImportiImportoAnniSuccessivi = interventoImportiImportoAnniSuccessivi;
	}

	/**
	 * Gets the intervento importi importo anno primo.
	 *
	 * @return the intervento importi importo anno primo
	 */
	public BigDecimal getInterventoImportiImportoAnnoPrimo() {
		return this.interventoImportiImportoAnnoPrimo;
	}

	/**
	 * Sets the intervento importi importo anno primo.
	 *
	 * @param interventoImportiImportoAnnoPrimo the new intervento importi importo anno primo
	 */
	public void setInterventoImportiImportoAnnoPrimo(BigDecimal interventoImportiImportoAnnoPrimo) {
		this.interventoImportiImportoAnnoPrimo = interventoImportiImportoAnnoPrimo;
	}

	/**
	 * Gets the intervento importi importo anno secondo.
	 *
	 * @return the intervento importi importo anno secondo
	 */
	public BigDecimal getInterventoImportiImportoAnnoSecondo() {
		return this.interventoImportiImportoAnnoSecondo;
	}

	/**
	 * Sets the intervento importi importo anno secondo.
	 *
	 * @param interventoImportiImportoAnnoSecondo the new intervento importi importo anno secondo
	 */
	public void setInterventoImportiImportoAnnoSecondo(BigDecimal interventoImportiImportoAnnoSecondo) {
		this.interventoImportiImportoAnnoSecondo = interventoImportiImportoAnnoSecondo;
	}

	/**
	 * Gets the cpass D risorsa.
	 *
	 * @return the cpass D risorsa
	 */
	public CpassDPbaRisorsa getCpassDPbaRisorsa() {
		return this.cpassDPbaRisorsa;
	}

	/**
	 * Sets the cpass D risorsa.
	 *
	 * @param cpassDPbaRisorsa the new cpass D risorsa
	 */
	public void setCpassDPbaRisorsa(CpassDPbaRisorsa cpassDPbaRisorsa) {
		this.cpassDPbaRisorsa = cpassDPbaRisorsa;
	}

	/**
	 * Gets the cpass T intervento.
	 *
	 * @return the cpass T intervento
	 */
	public CpassTPbaIntervento getCpassTPbaIntervento() {
		return this.cpassTPbaIntervento;
	}

	/**
	 * Sets the cpass T intervento.
	 *
	 * @param cpassTPbaIntervento the new cpass T intervento
	 */
	public void setCpassTPbaIntervento(CpassTPbaIntervento cpassTPbaIntervento) {
		this.cpassTPbaIntervento = cpassTPbaIntervento;
	}

	@Override
	public UUID getId() {
		return interventoImportiId;
	}

	@Override
	public void setId(UUID id) {
		interventoImportiId = id;
	}

	
	/**
	 * @return the motivazione
	 */
	public String getMotivazione() {
		return motivazione;
	}

	/**
	 * @param motivazione the motivazione to set
	 */
	public void setMotivazione(String motivazione) {
		this.motivazione = motivazione;
	}

	/**
	 * @return the richiestaMotivazione
	 */
	public Boolean getRichiestaMotivazione() {
		return richiestaMotivazione;
	}

	/**
	 * @param richiestaMotivazione the richiestaMotivazione to set
	 */
	public void setRichiestaMotivazione(Boolean richiestaMotivazione) {
		this.richiestaMotivazione = richiestaMotivazione;
	}

	@Override
	public void initId() {
		if(cpassTPbaIntervento != null && cpassTPbaIntervento.getInterventoId() != null && cpassDPbaRisorsa != null && cpassDPbaRisorsa.getId() != null) {
			this.interventoImportiId = UuidUtils.generateUUIDv5FromNamespaceAndString(NAMESPACE, cpassTPbaIntervento.getInterventoId().toString() + "_" + cpassDPbaRisorsa.getId().toString());
		}
	}
}
