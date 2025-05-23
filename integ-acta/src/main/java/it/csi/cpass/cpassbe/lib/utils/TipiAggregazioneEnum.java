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
package it.csi.cpass.cpassbe.lib.utils;

/**
 * Enum for Cpass
 */
public enum TipiAggregazioneEnum {
	
	T1("1","SerieTipologicaDocumentiPropertiesType"),
	T2("2","SerieFascicoliPropertiesType"),
	T3("3","SerieDossierPropertiesType"),
	T4("4","SottofascicoloPropertiesType"),
	T5("5","FascicoloRealeAnnualePropertiesType"),
	T6("6","FascicoloRealeContinuoPropertiesType"),
	T7("7","FascicoloRealeLiberoPropertiesType"),
	T8("8","FascicoloRealeEreditatoPropertiesType"),
	T9("9","FascicoloRealeLegislaturaPropertiesType"),
	T10("10","DossierPropertiesType"),
	T11("11","VolumeSottofascicoliPropertiesType"),
	T12("12","VolumeFascicoliPropertiesType"),
	T13("13","VolumeSerieFascicoliPropertiesType"),
	T14("14","VolumeSerieTipologicaDocumentiPropertiesType"),
	;
	
	
	private final String codice;
	private final String descrizione;
	
	private TipiAggregazioneEnum(String codice,String descrizione) {
		this.codice = codice;
		this.descrizione = descrizione;
	}
	
	/*
	private TipiAggregazioneEnum() {
		this.codice = this.name();
		this.descrizione = descrizione;
	}*/
    
	
	public String getCodice() {
		return codice;
	}

	public String getDescrizione() {
		return descrizione;
	}

}
