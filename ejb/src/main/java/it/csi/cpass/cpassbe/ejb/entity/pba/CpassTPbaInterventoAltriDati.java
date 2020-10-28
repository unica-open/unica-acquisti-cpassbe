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
import javax.persistence.*;

import it.csi.cpass.cpassbe.ejb.entity.CpassDCpv;
import it.csi.cpass.cpassbe.ejb.entity.base.BaseEntity;
import it.csi.cpass.cpassbe.lib.util.uuid.UuidUtils;
import java.math.BigDecimal;
import java.util.UUID;


/**
 * The persistent class for the cpass_t_pba_intervento_altri_dati database table.
 * 
 */
@Entity
@Table(name="cpass_t_pba_intervento_altri_dati")
@NamedQuery(name="CpassTPbaInterventoAltriDati.findAll", query="SELECT c FROM CpassTPbaInterventoAltriDati c")
public class CpassTPbaInterventoAltriDati implements Serializable, BaseEntity<UUID> {
	
	
	private static final long serialVersionUID = 1L;

	/**  The UUID namespace. */
	public static final UUID NAMESPACE = UuidUtils.generateUUIDv5FromNamespaceAndString(UuidUtils.NAMESPACE_OID, "cpass_t_pba_intervento_altri_dati");

	@Id
	@Column(name="intervento_altri_dati_id")
	private UUID interventoAltriDatiId;

	@Column(name="codice_interno")
	private String codiceInterno;

	@Column(name="iva_anni_successivi")
	private BigDecimal ivaAnniSuccessivi;

	@Column(name="iva_primo_anno")
	private BigDecimal ivaPrimoAnno;

	@Column(name="iva_secondo_anno")
	private BigDecimal ivaSecondoAnno;

	private String note;

	@Column(name="spese_sostenute")
	private BigDecimal speseSostenute;

	//////////////////
	@Column(name="importo_iva_mat_ric")
	private BigDecimal importoIvaMatRic;

	@Column(name="importo_iva_verdi")
	private BigDecimal importoIvaVerdi;

	@Column(name="importo_netto_iva_mat_ric")
	private BigDecimal importoNettoIvaMatRic;

	@Column(name="importo_netto_iva_verdi")
	private BigDecimal importoNettoIvaVerdi;

	@Column(name="importo_tot_mat_ric")
	private BigDecimal importoTotMatRic;

	@Column(name="importo_tot_verdi")
	private BigDecimal importoTotVerdi;

	@Column(name="normativa_riferimento")
	private String normativaRiferimento;

	@Column(name="oggetto_mat_ric")
	private String oggettoMatRic;

	private String oggettoverdi;

	//bi-directional many-to-one association to CpassDCpv
	@ManyToOne
	@JoinColumn(name="cpv_mat_ric_id")
	private CpassDCpv cpassDCpvMatRic;

	//bi-directional many-to-one association to CpassDCpv
	@ManyToOne
	@JoinColumn(name="cpv_verdi_id")
	private CpassDCpv cpassDCpvVerdi;

	//bi-directional many-to-one association to CpassDPbaTipoAcquisto
	@ManyToOne
	@JoinColumn(name="tipo_acquisto_mat_riciclati_id")
	private CpassDPbaTipoAcquisto cpassDPbaTipoAcquistoMatRic;

	//bi-directional many-to-one association to CpassDPbaTipoAcquisto
	@ManyToOne
	@JoinColumn(name="tipo_acquisto_verdi_id")
	private CpassDPbaTipoAcquisto cpassDPbaTipoAcquistoVerdi;

	
	
	////////////////////////
	
	//bi-directional many-to-one association to CpassTPbaIntervento
	@ManyToOne
	@JoinColumn(name="intervento_id")
	private CpassTPbaIntervento cpassTPbaIntervento;

	public CpassTPbaInterventoAltriDati() {
	}



	
	/**
	 * @return the interventoAltriDatiId
	 */
	public UUID getInterventoAltriDatiId() {
		return interventoAltriDatiId;
	}




	/**
	 * @param interventoAltriDatiId the interventoAltriDatiId to set
	 */
	public void setInterventoAltriDatiId(UUID interventoAltriDatiId) {
		this.interventoAltriDatiId = interventoAltriDatiId;
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
	 * @return the cpassTPbaIntervento
	 */
	public CpassTPbaIntervento getCpassTPbaIntervento() {
		return cpassTPbaIntervento;
	}




	/**
	 * @param cpassTPbaIntervento the cpassTPbaIntervento to set
	 */
	public void setCpassTPbaIntervento(CpassTPbaIntervento cpassTPbaIntervento) {
		this.cpassTPbaIntervento = cpassTPbaIntervento;
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
	 * @return the cpassDCpvMatRic
	 */
	public CpassDCpv getCpassDCpvMatRic() {
		return cpassDCpvMatRic;
	}

	/**
	 * @param cpassDCpvMatRic the cpassDCpvMatRic to set
	 */
	public void setCpassDCpvMatRic(CpassDCpv cpassDCpvMatRic) {
		this.cpassDCpvMatRic = cpassDCpvMatRic;
	}

	

	/**
	 * @return the cpassDCpvVerdi
	 */
	public CpassDCpv getCpassDCpvVerdi() {
		return cpassDCpvVerdi;
	}




	/**
	 * @param cpassDCpvVerdi the cpassDCpvVerdi to set
	 */
	public void setCpassDCpvVerdi(CpassDCpv cpassDCpvVerdi) {
		this.cpassDCpvVerdi = cpassDCpvVerdi;
	}




	/**
	 * @return the cpassDPbaTipoAcquistoMatRic
	 */
	public CpassDPbaTipoAcquisto getCpassDPbaTipoAcquistoMatRic() {
		return cpassDPbaTipoAcquistoMatRic;
	}




	/**
	 * @param cpassDPbaTipoAcquistoMatRic the cpassDPbaTipoAcquistoMatRic to set
	 */
	public void setCpassDPbaTipoAcquistoMatRic(CpassDPbaTipoAcquisto cpassDPbaTipoAcquistoMatRic) {
		this.cpassDPbaTipoAcquistoMatRic = cpassDPbaTipoAcquistoMatRic;
	}




	/**
	 * @return the cpassDPbaTipoAcquistoVerdi
	 */
	public CpassDPbaTipoAcquisto getCpassDPbaTipoAcquistoVerdi() {
		return cpassDPbaTipoAcquistoVerdi;
	}




	/**
	 * @param cpassDPbaTipoAcquistoVerdi the cpassDPbaTipoAcquistoVerdi to set
	 */
	public void setCpassDPbaTipoAcquistoVerdi(CpassDPbaTipoAcquisto cpassDPbaTipoAcquistoVerdi) {
		this.cpassDPbaTipoAcquistoVerdi = cpassDPbaTipoAcquistoVerdi;
	}




	@Override
	public UUID getId() {
		return interventoAltriDatiId;
	}

	@Override
	public void setId(UUID id) {
		interventoAltriDatiId = id;
	}

	@Override
	public void initId() {
		this.interventoAltriDatiId = UuidUtils.generateUUIDv5FromNamespaceAndString(NAMESPACE, cpassTPbaIntervento.getId().toString());
	}

}
