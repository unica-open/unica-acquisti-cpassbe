package it.csi.cpass.cpassbe.lib.dto;

import java.io.Serializable;

public class RuoloPermesso extends BaseDto<Integer> implements Serializable {
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	private Ruolo ruolo;
	private Permesso permesso;


	/**
	 * Constructor
	 * @param id the id
	 */
	public RuoloPermesso(Integer id) {
		super(id);
	}

	public RuoloPermesso() {
	}

	/**
	 * @return the ruolo
	 */
	public Ruolo getRuolo() {
		return ruolo;
	}

	/**
	 * @param ruolo the ruolo to set
	 */
	public void setRuolo(Ruolo ruolo) {
		this.ruolo = ruolo;
	}

	/**
	 * @return the permesso
	 */
	public Permesso getPermesso() {
		return permesso;
	}

	/**
	 * @param permesso the permesso to set
	 */
	public void setPermesso(Permesso permesso) {
		this.permesso = permesso;
	}



}
