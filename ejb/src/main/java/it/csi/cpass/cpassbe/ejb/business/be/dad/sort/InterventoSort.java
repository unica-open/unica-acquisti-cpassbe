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
package it.csi.cpass.cpassbe.ejb.business.be.dad.sort;

/**
 * Sort mapper for Intervento.
 */
public enum InterventoSort implements JpaSort {

	/** The anno programma. */
	ANNO_PROGRAMMA("annoProgramma", "int.cpassTPbaProgramma.programmaAnno"),
	/** The stato. */
	STATO("stato", "int.cpassDStato.statoId"),
	/** The priorita. */
	PRIORITA("priorita", "int.cpassDPbaPriorita.prioritaId"),
	/** The anno avvio. */
	ANNO_AVVIO("interventoAnnoAvvio", "int.interventoAnnoAvvio"),
	/** The cui. */
	CUI("cui", "int.interventoCui"),
	/** The settore. */
	SETTORE("settore", "int.cpassDPbaSettoreInterventi.settoreInterventiId"),
	/** The cup. */
	CUP("cup", "int.interventoCup"),
	/** The rup. */
	RUP("rup", "int.cpassTUtenteRup.utenteCodiceFiscale"),
	/** The descrizione. */
	DESCRIZIONE("descrizione", "int.interventoDescrizioneAcquisto"),
	/** The cpv. */
	CPV("cpv", "int.cpassDCpv.cpvId"),
	/** The id. */
	ID("id", "int.interventoId")
	;
	
	/** The model name. */
	private final String modelName;
	
	/** The query name. */
	private final String queryName;
	
	/**
	 * Constructor.
	 *
	 * @param modelName the model name
	 * @param queryName the query name
	 */
	private InterventoSort(String modelName, String queryName) {
		this.modelName = modelName;
		this.queryName = queryName;
	}
	
	@Override
	public String getQueryName() {
		return queryName;
	}
	
	@Override
	public String getModelName() {
		return modelName;
	}
	
	/**
	 * Retrieves the Sort by its model name.
	 *
	 * @param modelName the model name
	 * @return the sort
	 */
	public static InterventoSort byModelName(String modelName) {
		for(InterventoSort is : InterventoSort.values()) {
			if(is.modelName.equalsIgnoreCase(modelName)) {
				return is;
			}
		}
		return InterventoSort.ID;
	}
	
}
