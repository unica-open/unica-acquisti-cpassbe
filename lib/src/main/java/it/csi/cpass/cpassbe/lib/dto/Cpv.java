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
package it.csi.cpass.cpassbe.lib.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import it.csi.cpass.cpassbe.lib.dto.pba.SettoreInterventi;

/**
 * The Class Cpv.
 */
public class Cpv extends BaseDto<Integer> implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	/** The livello. */
	private Integer livello;
	/** The codice. */
	private String codice;
	/** The descrizione. */
	private String descrizione;
	/** The codice padre. */
	private String codicePadre;
	/** The tipologia. */
	private String tipologia;
	/** The divisione. */
	private String divisione;
	/** The gruppo. */
	private String gruppo;
	/** The classe. */
	private String classe;
	/** The categoria. */
	private String categoria;
	/** The settoreInterventi. */
	private SettoreInterventi settoreInterventi;
	/** The lista cpv. */
	private List<Cpv> listCpv = new ArrayList<>();
	/** The cpvIdPadre. */
	private Integer idPadre;
	
	/** Default constructor */
	public Cpv() {}

	/**
	 * Constructor
	 * @param id the id
	 */
	public Cpv(Integer id) {
		super(id);
	}

	/**
	 * Gets the codice.
	 * @return the codice
	 */
	public String getCodice() {
		return codice;
	}

	/**
	 * Sets the codice.
	 * @param codice the new codice
	 */
	public void setCodice(String codice) {
		this.codice = codice;
	}

	/**
	 * Gets the codice padre.
	 * @return the codice padre
	 */
	public String getCodicePadre() {
		return codicePadre;
	}

	/**
	 * Sets the codice padre.
	 * @param codicePadre the new codice padre
	 */
	public void setCodicePadre(String codicePadre) {
		this.codicePadre = codicePadre;
	}

	/**
	 * Gets the tipologia.
	 * @return the tipologia
	 */
	public String getTipologia() {
		return tipologia;
	}

	/**
	 * Sets the tipologia.
	 * @param tipologia the new tipologia
	 */
	public void setTipologia(String tipologia) {
		this.tipologia = tipologia;
	}

	/**
	 * Gets the divisione.
	 * @return the divisione
	 */
	public String getDivisione() {
		return divisione;
	}

	/**
	 * Sets the divisione.
	 * @param divisione the new divisione
	 */
	public void setDivisione(String divisione) {
		this.divisione = divisione;
	}

	/**
	 * Gets the gruppo.
	 * @return the gruppo
	 */
	public String getGruppo() {
		return gruppo;
	}

	/**
	 * Sets the gruppo.
	 * @param gruppo the new gruppo
	 */
	public void setGruppo(String gruppo) {
		this.gruppo = gruppo;
	}

	/**
	 * Gets the classe.
	 * @return the classe
	 */
	public String getClasse() {
		return classe;
	}

	/**
	 * Sets the classe.
	 * @param classe the new classe
	 */
	public void setClasse(String classe) {
		this.classe = classe;
	}

	/**
	 * Gets the categoria.
	 * @return the categoria
	 */
	public String getCategoria() {
		return categoria;
	}

	/**
	 * Sets the categoria.
	 * @param categoria the new categoria
	 */
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	/**
	 * Gets the descrizione.
	 * @return the descrizione
	 */
	public String getDescrizione() {
		return descrizione;
	}

	/**
	 * Sets the descrizione.
	 * @param descrizione the new descrizione
	 */
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}


	/**
	 * @return the settoreInterventi
	 */
	public SettoreInterventi getSettoreInterventi() {
		return settoreInterventi;
	}

	/**
	 * @param settoreInterventi the settoreInterventi to set
	 */
	public void setSettoreInterventi(SettoreInterventi settoreInterventi) {
		this.settoreInterventi = settoreInterventi;
	}

	/**
	 * @return the listCpv
	 */
	public List<Cpv> getListCpv() {
		//return listCpv;
		return this.listCpv = listCpv != null ? listCpv : new ArrayList<>();
	}

	/**
	 * @param listCpv the listCpv to set
	 */
	public void setListCpv(List<Cpv> listCpv) {
		this.listCpv = listCpv;
	}

	
	/**
	 * @return the livello
	 */
	public Integer getLivello() {
		return livello;
	}

	/**
	 * @param livello the livello to set
	 */
	public void setLivello(Integer livello) {
		this.livello = livello;
	}

	
	/**
	 * @return the idPadre
	 */
	public Integer getIdPadre() {
		return idPadre;
	}

	/**
	 * @param idPadre the idPadre to set
	 */
	public void setIdPadre(Integer idPadre) {
		this.idPadre = idPadre;
	}

	@Override
	public String toString() {
		return new StringBuilder()
			.append("Cpv [codice=").append(codice)
			.append(", descrizione=").append(descrizione)
			.append(", codicePadre=").append(codicePadre)
			.append(", tipologia=").append(tipologia)
			.append(", divisione=").append(divisione)
			.append(", gruppo=").append(gruppo)
			.append(", classe=").append(classe)
			.append(", categoria=").append(categoria)
			.append(", settoreInterventi=").append(settoreInterventi)
			.append(", listCpv=").append(listCpv)
			.append(", livello=").append(livello)
			.append(", idPadre=").append(idPadre)
			.append(", id=").append(id)
			.append("]")
			.toString();
	}

}
