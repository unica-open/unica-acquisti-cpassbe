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
package it.csi.cpass.cpassbe.lib.dto.pba;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

import it.csi.cpass.cpassbe.lib.dto.BaseDto;
import it.csi.cpass.cpassbe.lib.dto.Cpv;
import it.csi.cpass.cpassbe.lib.dto.MotiviEsclusioneCig;

public class InterventoAltriDati extends BaseDto<UUID> implements Serializable {
	private static final long serialVersionUID = 3654685856049539088L;

	private String note;
	private String codiceInterno;
	private BigDecimal speseSostenute;
	private BigDecimal ivaPrimoAnno;
	private BigDecimal ivaSecondoAnno;
	private BigDecimal ivaTerzoAnno;
	private BigDecimal ivaAnniSuccessivi;
	private BigDecimal importoIvaMatRic;
	private BigDecimal importoIvaVerdi;
	private BigDecimal importoNettoIvaMatRic;
	private BigDecimal importoNettoIvaVerdi;
	private BigDecimal importoTotMatRic;
	private BigDecimal importoTotVerdi;
	private String normativaRiferimento;
	private String oggettoMatRic;
	private String oggettoverdi;
	private Intervento intervento;
	private Cpv cpvMatRic;
	private Cpv cpvVerdi;
	private TipoAcquisto tipoAcquistoMatRic;
	private TipoAcquisto tipoAcquistoVerdi;
	private Boolean fondiPnrr;
	private MotiviEsclusioneCig motiviEsclusioneCig;

	/**
	 * Constructor
	 *
	 * @param id the id
	 */
	public InterventoAltriDati(UUID id) {
		super(id);
	}

	/** Default constructor */
	public InterventoAltriDati() {}



	/**
	 * @return the note
	 */
	public String getNote() {
		return note;
	}

	/**
	 * @param note the note to set
	 */
	public void setNote(String note) {
		this.note = note;
	}

	/**
	 * @return the codiceInterno
	 */
	public String getCodiceInterno() {
		return codiceInterno;
	}

	/**
	 * @param codiceInterno the codiceInterno to set
	 */
	public void setCodiceInterno(String codiceInterno) {
		this.codiceInterno = codiceInterno;
	}

	/**
	 * @return the speseSostenute
	 */
	public BigDecimal getSpeseSostenute() {
		return speseSostenute;
	}

	/**
	 * @param speseSostenute the speseSostenute to set
	 */
	public void setSpeseSostenute(BigDecimal speseSostenute) {
		this.speseSostenute = speseSostenute;
	}

	/**
	 * @return the ivaPrimoAnno
	 */
	public BigDecimal getIvaPrimoAnno() {
		return ivaPrimoAnno;
	}

	/**
	 * @param ivaPrimoAnno the ivaPrimoAnno to set
	 */
	public void setIvaPrimoAnno(BigDecimal ivaPrimoAnno) {
		this.ivaPrimoAnno = ivaPrimoAnno;
	}

	/**
	 * @return the ivaSecondoAnno
	 */
	public BigDecimal getIvaSecondoAnno() {
		return ivaSecondoAnno;
	}

	/**
	 * @param ivaSecondoAnno the ivaSecondoAnno to set
	 */
	public void setIvaSecondoAnno(BigDecimal ivaSecondoAnno) {
		this.ivaSecondoAnno = ivaSecondoAnno;
	}

	/**
	 * @return the ivaAnniSuccessivi
	 */
	public BigDecimal getIvaAnniSuccessivi() {
		return ivaAnniSuccessivi;
	}

	/**
	 * @param ivaAnniSuccessivi the ivaAnniSuccessivi to set
	 */
	public void setIvaAnniSuccessivi(BigDecimal ivaAnniSuccessivi) {
		this.ivaAnniSuccessivi = ivaAnniSuccessivi;
	}


	/**
	 * @return the importoIvaMatRic
	 */
	public BigDecimal getImportoIvaMatRic() {
		return importoIvaMatRic;
	}

	/**
	 * @param importoIvaMatRic the importoIvaMatRic to set
	 */
	public void setImportoIvaMatRic(BigDecimal importoIvaMatRic) {
		this.importoIvaMatRic = importoIvaMatRic;
	}

	/**
	 * @return the importoIvaVerdi
	 */
	public BigDecimal getImportoIvaVerdi() {
		return importoIvaVerdi;
	}

	/**
	 * @param importoIvaVerdi the importoIvaVerdi to set
	 */
	public void setImportoIvaVerdi(BigDecimal importoIvaVerdi) {
		this.importoIvaVerdi = importoIvaVerdi;
	}

	/**
	 * @return the importoNettoIvaMatRic
	 */
	public BigDecimal getImportoNettoIvaMatRic() {
		return importoNettoIvaMatRic;
	}

	/**
	 * @param importoNettoIvaMatRic the importoNettoIvaMatRic to set
	 */
	public void setImportoNettoIvaMatRic(BigDecimal importoNettoIvaMatRic) {
		this.importoNettoIvaMatRic = importoNettoIvaMatRic;
	}

	/**
	 * @return the importoNettoIvaVerdi
	 */
	public BigDecimal getImportoNettoIvaVerdi() {
		return importoNettoIvaVerdi;
	}

	/**
	 * @param importoNettoIvaVerdi the importoNettoIvaVerdi to set
	 */
	public void setImportoNettoIvaVerdi(BigDecimal importoNettoIvaVerdi) {
		this.importoNettoIvaVerdi = importoNettoIvaVerdi;
	}

	/**
	 * @return the importoTotMatRic
	 */
	public BigDecimal getImportoTotMatRic() {
		return importoTotMatRic;
	}

	/**
	 * @param importoTotMatRic the importoTotMatRic to set
	 */
	public void setImportoTotMatRic(BigDecimal importoTotMatRic) {
		this.importoTotMatRic = importoTotMatRic;
	}

	/**
	 * @return the importoTotVerdi
	 */
	public BigDecimal getImportoTotVerdi() {
		return importoTotVerdi;
	}

	/**
	 * @param importoTotVerdi the importoTotVerdi to set
	 */
	public void setImportoTotVerdi(BigDecimal importoTotVerdi) {
		this.importoTotVerdi = importoTotVerdi;
	}

	/**
	 * @return the normativaRiferimento
	 */
	public String getNormativaRiferimento() {
		return normativaRiferimento;
	}

	/**
	 * @param normativaRiferimento the normativaRiferimento to set
	 */
	public void setNormativaRiferimento(String normativaRiferimento) {
		this.normativaRiferimento = normativaRiferimento;
	}

	/**
	 * @return the oggettoMatRic
	 */
	public String getOggettoMatRic() {
		return oggettoMatRic;
	}

	/**
	 * @param oggettoMatRic the oggettoMatRic to set
	 */
	public void setOggettoMatRic(String oggettoMatRic) {
		this.oggettoMatRic = oggettoMatRic;
	}

	/**
	 * @return the oggettoverdi
	 */
	public String getOggettoverdi() {
		return oggettoverdi;
	}

	/**
	 * @param oggettoverdi the oggettoverdi to set
	 */
	public void setOggettoverdi(String oggettoverdi) {
		this.oggettoverdi = oggettoverdi;
	}


	/**
	 * @return the cpvMatRic
	 */
	public Cpv getCpvMatRic() {
		return cpvMatRic;
	}

	/**
	 * @param cpvMatRic the cpvMatRic to set
	 */
	public void setCpvMatRic(Cpv cpvMatRic) {
		this.cpvMatRic = cpvMatRic;
	}

	/**
	 * @return the cpvVerdi
	 */
	public Cpv getCpvVerdi() {
		return cpvVerdi;
	}

	/**
	 * @param cpvVerdi the cpvVerdi to set
	 */
	public void setCpvVerdi(Cpv cpvVerdi) {
		this.cpvVerdi = cpvVerdi;
	}

	/**
	 * @return the tipoAcquistoMatRic
	 */
	public TipoAcquisto getTipoAcquistoMatRic() {
		return tipoAcquistoMatRic;
	}

	/**
	 * @param tipoAcquistoMatRic the tipoAcquistoMatRic to set
	 */
	public void setTipoAcquistoMatRic(TipoAcquisto tipoAcquistoMatRic) {
		this.tipoAcquistoMatRic = tipoAcquistoMatRic;
	}

	/**
	 * @return the tipoAcquistoVerdi
	 */
	public TipoAcquisto getTipoAcquistoVerdi() {
		return tipoAcquistoVerdi;
	}

	/**
	 * @param tipoAcquistoVerdi the tipoAcquistoVerdi to set
	 */
	public void setTipoAcquistoVerdi(TipoAcquisto tipoAcquistoVerdi) {
		this.tipoAcquistoVerdi = tipoAcquistoVerdi;
	}


	/**
	 * @return the intervento
	 */
	public Intervento getIntervento() {
		return intervento;
	}

	/**
	 * @param intervento the intervento to set
	 */
	public void setIntervento(Intervento intervento) {
		this.intervento = intervento;
	}



	/**
	 * @return the fondiPnrr
	 */
	public Boolean getFondiPnrr() {
		return fondiPnrr;
	}

	/**
	 * @param fondiPnrr the fondiPnrr to set
	 */
	public void setFondiPnrr(Boolean fondiPnrr) {
		this.fondiPnrr = fondiPnrr;
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


	/**
	 * @return the ivaTerzoAnno
	 */
	public BigDecimal getIvaTerzoAnno() {
		return ivaTerzoAnno;
	}

	/**
	 * @param ivaTerzoAnno the ivaTerzoAnno to set
	 */
	public void setIvaTerzoAnno(BigDecimal ivaTerzoAnno) {
		this.ivaTerzoAnno = ivaTerzoAnno;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("InterventoAltriDati [note=").append(note).append(", codiceInterno=").append(codiceInterno)
				.append(", speseSostenute=").append(speseSostenute).append(", ivaPrimoAnno=").append(ivaPrimoAnno)
				.append(", ivaSecondoAnno=").append(ivaSecondoAnno).append(", ivaAnniSuccessivi=")
				.append(ivaAnniSuccessivi).append(", importoIvaMatRic=").append(importoIvaMatRic)
				.append(", importoIvaVerdi=").append(importoIvaVerdi).append(", importoNettoIvaMatRic=")
				.append(importoNettoIvaMatRic).append(", importoNettoIvaVerdi=").append(importoNettoIvaVerdi)
				.append(", importoTotMatRic=").append(importoTotMatRic).append(", importoTotVerdi=")
				.append(importoTotVerdi).append(", normativaRiferimento=").append(normativaRiferimento)
				.append(", oggettoMatRic=").append(oggettoMatRic).append(", oggettoverdi=").append(oggettoverdi)
				.append(", intervento=").append(intervento).append(", cpvMatRic=").append(cpvMatRic)
				.append(", cpvVerdi=").append(cpvVerdi).append(", tipoAcquistoMatRic=").append(tipoAcquistoMatRic)
				.append(", tipoAcquistoVerdi=").append(tipoAcquistoVerdi).append(", id=").append(id).append("]");
		return builder.toString();
	}

}
