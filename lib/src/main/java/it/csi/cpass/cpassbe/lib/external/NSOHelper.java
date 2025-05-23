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
package it.csi.cpass.cpassbe.lib.external;

import java.util.Map;

import it.csi.cpass.cpassbe.lib.dto.SettoreIndirizzo;
import it.csi.cpass.cpassbe.lib.dto.ord.Destinatario;
import it.csi.cpass.cpassbe.lib.dto.ord.TestataOrdine;
import it.csi.cpass.cpassbe.lib.dto.ord.nso.EsitoInvioDocumento;
import it.csi.cpass.cpassbe.lib.dto.ord.nso.EsitoRecuperoDocumentoTrasporto;
import it.csi.cpass.cpassbe.lib.dto.ord.nso.EsitoRecuperoListaDocumenti;
import it.csi.cpass.cpassbe.lib.dto.ord.nso.EsitoRecuperoNotificaDiScarto;
import it.csi.cpass.cpassbe.lib.dto.ord.nso.EsitoRecuperoNotificaMdn;
import it.csi.cpass.cpassbe.lib.dto.ord.nso.EsitoRecuperoRicevutaDiConsegna;
import it.csi.cpass.cpassbe.lib.dto.ord.nso.TipoOrdineNSO;
import it.csi.cpass.cpassbe.lib.external.dto.RigheOrdineConTotali;
import it.csi.cpass.cpassbe.lib.external.itf.BaseHelper;
import it.csi.cpass.cpassbe.lib.external.res.ExternalServiceResponseWrapper;

/**
 * Helper for invoking external services for NSO
 */
public interface NSOHelper extends BaseHelper {

	/**
	 *
	 * @param params
	 * @param testataOrdine
	 * @param destinatarioOrdine
	 * @param righeOrdineConTotali
	 * @param nsoListener
	 * @return
	 */
	ExternalServiceResponseWrapper<EsitoInvioDocumento> invioDocumentoNSO(
			TipoOrdineNSO tipoOrdineNSO, Map<String, String> params, TestataOrdine testataOrdine,Destinatario destinatarioOrdine, RigheOrdineConTotali righeOrdineConTotali,SettoreIndirizzo indirizzoPrincipaleSettoreEmittente, NSOListener nsoListener);

	/**
	 *
	 * @param params
	 * @param idnotier
	 * @param parametri
	 * @return EsitoRichiestaInterno
	 */
	ExternalServiceResponseWrapper<EsitoRecuperoListaDocumenti> recuperoListaDocumenti(Map<String, String> params, String idnotier, String parametri );

	/**
	 *
	 * @param params
	 * @param urn
	 * @param idNotier
	 * @return DocumentoTrasporto
	 */
	ExternalServiceResponseWrapper<EsitoRecuperoDocumentoTrasporto> recuperoDDT(Map<String, String> params, String urn, String idNotier);

	/**
	 *
	 * @param params
	 * @param urn
	 * @return String
	 */
	ExternalServiceResponseWrapper<EsitoRecuperoNotificaMdn> recuperoNotificaMdn(Map<String, String> params, String urn);

	/**
	 *
	 * @param params
	 * @param urn
	 * @return EsitoRecuperoDocumento
	 */
	ExternalServiceResponseWrapper<EsitoRecuperoRicevutaDiConsegna> recuperoRicevutaDiConsegna(Map<String, String> params, String urn);

	/**
	 *
	 * @param params
	 * @param urn
	 * @return EsitoRecuperoDocumento
	 */
	ExternalServiceResponseWrapper<EsitoRecuperoNotificaDiScarto> recuperoNotificaDiScarto(Map<String, String> params, String urn);


}
