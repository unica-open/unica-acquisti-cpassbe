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

import it.csi.cpass.cpassbe.lib.dto.Ente;
import it.csi.cpass.cpassbe.lib.dto.Utente;
import it.csi.cpass.cpassbe.lib.dto.ord.ProtocolloOrdine;
import it.csi.cpass.cpassbe.lib.dto.ord.TestataOrdine;
import it.csi.cpass.cpassbe.lib.external.dto.GestoreDocumentoActa;
import it.csi.cpass.cpassbe.lib.external.itf.BaseHelper;
import it.csi.cpass.cpassbe.lib.external.res.ExternalServiceResponseWrapper;

/**
 * Helper for invoking external services for Fornitore
 */
public interface DocumentaleHelper extends BaseHelper {
	/**
	 *
	 * @param params
	 * @param anno
	 * @param numero
	 * @param idAOO
	 * @param settoreCorrenteCodice
	 * @return ExternalServiceResponseWrapper<ProtocolloOrdine>
	 */
	ExternalServiceResponseWrapper<ProtocolloOrdine> getProtocolloOrigin(Map<String, String> params, Integer anno, String numero,Long idAOO,String settoreCorrenteCodice) ;
	/**
	 *
	 * @param params
	 * @param idAOO
	 * @param indiceclassificazioneEstesa
	 * @param settoreCorrenteCodice
	 * @return ExternalServiceResponseWrapper<ProtocolloOrdine>
	 */
	ExternalServiceResponseWrapper<ProtocolloOrdine> getStrutturaAggregativaXIndiceclassificazioneEstesa(Map<String, String> params, Long idAOO,String indiceclassificazioneEstesa,String settoreCorrenteCodice) ;
	/**
	 *
	 * @param params
	 * @param idAOO
	 * @param voceTitolario
	 * @param numeroFascicolo
	 * @param aooDossier
	 * @param settoreCorrenteCodice
	 * @return ExternalServiceResponseWrapper<ProtocolloOrdine>
	 */
	ExternalServiceResponseWrapper<ProtocolloOrdine> getStrutturaAggregativaXStrutturaAggregativa(Map<String, String> params,Long idAOO, String voceTitolario, String numeroFascicolo, String aooDossier,String settoreCorrenteCodice);
	/**
	 *
	 * @param params
	 * @param idAOO
	 * @param uuidDocumentoOrdine
	 * @param settoreCorrenteCodice
	 * @return ExternalServiceResponseWrapper<GestoreDocumentoActa>
	 */
	ExternalServiceResponseWrapper<GestoreDocumentoActa> verificaDocumentoProtocollato(Map<String, String> params,Long idAOO,String uuidDocumentoOrdine,String settoreCorrenteCodice);


	ExternalServiceResponseWrapper<GestoreDocumentoActa>  archiviaOrdineNelVolume(Map<String, String> params,
			String serieTipologica,
			String ufficio,byte[] fileDoc,
			TestataOrdine testataOrdineUtente ,
			Utente utenteCompilatore,
			Ente ente,
			String autoreGiuridico,
			String destinatarioGiuridico,
			String destinatarioFisico,
			Long idAOO,
			String settoreCorrenteCodice
			);


	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * archiviazione sotto la serie tipologica
	 * @param params
	 * @param serieTipologica
	 * @param ufficio
	 * @param fileDoc
	 * @param testataOrdineUtente
	 * @param utenteCompilatore
	 * @param ente
	 * @param autoreGiuridico
	 * @param destinatarioGiuridico
	 * @param destinatarioFisico
	 * @param idAOO
	 * @param settoreCorrenteCodice
	 * @return ExternalServiceResponseWrapper<GestoreDocumentoActa>
	 */
	ExternalServiceResponseWrapper<GestoreDocumentoActa>  archiviaOrdineInSerieTipologica(Map<String, String> params,
																			String serieTipologica,
																			String ufficio,byte[] fileDoc,
																			TestataOrdine testataOrdineUtente ,
																			Utente utenteCompilatore,
																			Ente ente,
																			String autoreGiuridico,
																			String destinatarioGiuridico,
																			String destinatarioFisico,
																			Long idAOO,
																			String settoreCorrenteCodice
																			);
	@Deprecated
	ExternalServiceResponseWrapper<Boolean> verificaArchiviazioneOrdine(Map<String, String> params, Long idAOO,String settoreCorrenteCodice);

	/**
	 * ATTENZIONE questa chiamata non è attualmente utilizzata nel progetto CPASS nell'eventualità eliminare questo commento
	 * @param params
	 * @param ufficio
	 * @param idAOO
	 * @param settoreCorrenteCodice
	 * @return
	 */
	@SuppressWarnings("unused") //indica una variabile o un metodo non utilizzato.
	ExternalServiceResponseWrapper<String>  getSerieTipologica(Map<String, String> params, String ufficio,Long idAOO,String settoreCorrenteCodice);

	/**
	 *
	 * @param params
	 * @param idAOO
	 * @param testataOrdine
	 * @param gda
	 * @param settoreCorrenteCodice
	 * @return
	 */
	@SuppressWarnings("unused") //indica una variabile o un metodo non utilizzato.
	ExternalServiceResponseWrapper<ProtocolloOrdine> protocollaOrdine(Map<String, String> params, Long idAOO,TestataOrdine testataOrdine,ProtocolloOrdine gda,String settoreCorrenteCodice);

	/*
	ExternalServiceResponseWrapper<GestoreDocumentoActa>  archiviaEProtocollaOrdine(Map<String, String> params,
																		String serieTipologica,
																		String ufficio,byte[] fileDoc,
																		TestataOrdine testataOrdineUtente ,
																		Utente utenteCompilatore,
																		Ente ente,
																		String autoreGiuridico,
																		String destinatarioGiuridico,
																		String destinatarioFisico,
																		Long idAOO
																		);
	*/
}
