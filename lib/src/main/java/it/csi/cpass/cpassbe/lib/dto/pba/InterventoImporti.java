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
package it.csi.cpass.cpassbe.lib.dto.pba;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

import it.csi.cpass.cpassbe.lib.dto.BaseAuditedDto;

/**
 * The Class InterventoImporti.
 */
public class InterventoImporti extends BaseAuditedDto<UUID> implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The importo anno primo. */
	private BigDecimal importoAnnoPrimo;
	/** The importo anno secondo. */
	private BigDecimal importoAnnoSecondo;
	/** The importo anni successivi. */
	private BigDecimal importoAnniSuccessivi;
	/** The risorsa. */
	private Risorsa risorsa;
	/** The intervento. */
	private Intervento intervento;
    private String motivazione;
    private Boolean richiestaMotivazione;

    	  /** Default constructor */
	public InterventoImporti() {}

	/**
	 * Constructor
	 * @param id the id
	 */
	public InterventoImporti(UUID id) {
		super(id);
	}

	/**
	 * Gets the importo anno primo.
	 * @return the importo anno primo
	 */
	public BigDecimal getImportoAnnoPrimo() {
		return importoAnnoPrimo;
	}

	/**
	 * Sets the importo anno primo.
	 * @param importoAnnoPrimo the new importo anno primo
	 */
	public void setImportoAnnoPrimo(BigDecimal importoAnnoPrimo) {
		this.importoAnnoPrimo = importoAnnoPrimo;
	}

	/**
	 * Gets the importo anno secondo.
	 * @return the importo anno secondo
	 */
	public BigDecimal getImportoAnnoSecondo() {
		return importoAnnoSecondo;
	}

	/**
	 * Sets the importo anno secondo.
	 * @param importoAnnoSecondo the new importo anno secondo
	 */
	public void setImportoAnnoSecondo(BigDecimal importoAnnoSecondo) {
		this.importoAnnoSecondo = importoAnnoSecondo;
	}

	/**
	 * Gets the importo anni successivi.
	 * @return the importo anni successivi
	 */
	public BigDecimal getImportoAnniSuccessivi() {
		return importoAnniSuccessivi;
	}

	/**
	 * Sets the importo anni successivi.
	 * @param importoAnniSuccessivi the new importo anni successivi
	 */
	public void setImportoAnniSuccessivi(BigDecimal importoAnniSuccessivi) {
		this.importoAnniSuccessivi = importoAnniSuccessivi;
	}

	/**
	 * Gets the risorsa.
	 * @return the risorsa
	 */
	public Risorsa getRisorsa() {
		return risorsa;
	}

	/**
	 * Sets the risorsa.
	 * @param risorsa the new risorsa
	 */
	public void setRisorsa(Risorsa risorsa) {
		this.risorsa = risorsa;
	}

	/**
	 * Gets the intervento.
	 * @return the intervento
	 */
	public Intervento getIntervento() {
		return intervento;
	}

	/**
	 * Sets the intervento.
	 * @param intervento the new intervento
	 */
	public void setIntervento(Intervento intervento) {
		this.intervento = intervento;
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
	 * @return the richiesta_motivazione
	 */
	public Boolean getRichiestaMotivazione() {
		return richiestaMotivazione;
	}

	/**
	 * @param richiesta_motivazione the richiesta_motivazione to set
	 */
	public void setRichiestaMotivazione(Boolean richiestaMotivazione) {
		this.richiestaMotivazione = richiestaMotivazione;
	}

	@Override
	public String toString() {
		return new StringBuilder()
			.append("InterventoImporti [importoAnnoPrimo=").append(importoAnnoPrimo)
			.append(", importoAnnoSecondo=").append(importoAnnoSecondo)
			.append(", importoAnniSuccessivi=").append(importoAnniSuccessivi)
			.append(", risorsa=").append(risorsa)
			.append(", intervento=").append(intervento)
			.append(innerToString())
			.append("]")
			.toString();
	}

}
