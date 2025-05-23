/*-
 * ========================LICENSE_START=================================
 * CPASS BackEnd - INTEGRATION submodule - NotiER
 * %%
 * Copyright (C) 2019 - 2025 CSI Piemonte
 * %%
 * SPDX-FileCopyrightText: Copyright 2019 - 2020 | CSI Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 * =========================LICENSE_END==================================
 */
package it.csi.cpass.cpassbe.lib.external.impl.notier;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import it.csi.cpass.cpassbe.lib.dto.ord.Destinatario;
import it.csi.cpass.cpassbe.lib.dto.ord.TestataOrdine;
import it.csi.cpass.cpassbe.lib.external.itf.ConfigurationParam;
import it.csi.cpass.cpassbe.lib.util.serialization.JAXBUtility;
import it.csi.cpass.cpassbe.lib.utils.NotiERConfigurationParams;
import it.rer.intercenter.notier.services._1_0.inviodocumento.request.ChiaveDocumentoType;
import it.rer.intercenter.notier.services._1_0.inviodocumento.request.ConfigurazioneType;
import it.rer.intercenter.notier.services._1_0.inviodocumento.request.DocumentoType;
import it.rer.intercenter.notier.services._1_0.inviodocumento.request.FormatoType;
import it.rer.intercenter.notier.services._1_0.inviodocumento.request.InvioDocumento;
import it.rer.intercenter.notier.services._1_0.inviodocumento.request.MetadatiBusdoxType;
import it.rer.intercenter.notier.services._1_0.inviodocumento.request.RappresentazioneType;
import it.rer.intercenter.notier.services._1_0.inviodocumento.request.TipoDocumentoType;
import it.rer.intercenter.notier.services._1_0.inviodocumento.request.TipoMittenteType;

public class PeppolMetadati {

	public static String getXmlMetadati(TestataOrdine testataOrdine, Destinatario destinatarioOrdine,int maxProgressivoInvio, Map<String, String> params) {
		
		String unicoDestinatario = getParameter(params, NotiERConfigurationParams.NSO_UNICO_DESTINATARIO);
	
		InvioDocumento invioDocumento = new InvioDocumento();
		
		invioDocumento.setVersione("1.0");

		DocumentoType documentoType = new DocumentoType();
		invioDocumento.setDocumento(documentoType);

		ChiaveDocumentoType chiaveDocumentoType = new ChiaveDocumentoType();
		chiaveDocumentoType.setMittente(testataOrdine.getUfficio().getIdNotier());
		chiaveDocumentoType.setTipoMittente(TipoMittenteType.NOTIER);
		chiaveDocumentoType.setAnno(testataOrdine.getAnno());
		chiaveDocumentoType.setNumero(PeppolXmlUtils.getOrderId(testataOrdine, destinatarioOrdine, maxProgressivoInvio,unicoDestinatario.equalsIgnoreCase("TRUE")));
		chiaveDocumentoType.setTipoDocumento(TipoDocumentoType.ORDINE);
		documentoType.setChiave(chiaveDocumentoType);

		RappresentazioneType rappresentazioneType = new RappresentazioneType();
		rappresentazioneType.setFormato(FormatoType.BIS_3);
		rappresentazioneType.setVersione("2.1");
		documentoType.setRappresentazione(rappresentazioneType);

		ConfigurazioneType configurazioneType = new ConfigurazioneType();
		configurazioneType.setInvioSdi(false);
		configurazioneType.setInvioPeppol(true);
		configurazioneType.setInvioConservazione(false);
		configurazioneType.setApprovazioneAutomatica(false);
		configurazioneType.setAccettaChiaveDuplicata(false);
		invioDocumento.setConfigurazione(configurazioneType);

		// se non completa da errore 
		// SAXParseException: cvc-complex-type.2.4.b: The content of element 'Integrazione' is not complete
//		IntegrazioneType integrazioneType = new IntegrazioneType();
//		integrazioneType.setRegistro(null);
//		invioDocumento.setIntegrazione(integrazioneType);

		// TODO forse da passare soltanto in modifica
		// CollegamentoDocumentoException: Non esiste su NoTIER un documento con urn urn:notier:S04VFA:20201215:110_1_15122020175950:CP:ORDINE
//		CollegamentoType collegamentoType = new CollegamentoType();
//		// ‘urn:notier:’||CPASS_T_UFFICIO.ufficio_codice||’:’||data di sistema||’:’||
//		// CPASS_T_ORD_TESTATA_ORDINE.numero||’_’||CPASS_T_ORD_DESTINATARIO_ORDINE.progressivo||’_’||data sistema con ora minuti e
//		// secondi||’:CP:ORDINE’
//		// es. urn:notier:A17LZ5:20200525:414_2_04062020183145:CP:ORDINE
//		SimpleDateFormat sdfSistema = new SimpleDateFormat("yyyyMMdd");
//		SimpleDateFormat sdfSistemaCompleta = new SimpleDateFormat("ddMMyyyyHHmmss");
//		Date date = new Date();
//		String urn = "urn:notier:" + testataOrdine.getUfficio().getCodice() + ":" + sdfSistema.format(date) + ":" + testataOrdine.getNumero() + "_"
//				+ destinatarioOrdine.getProgressivo() + "_" + sdfSistemaCompleta.format(date) + ":CP:ORDINE";
//		collegamentoType.setUrn(urn);
//		invioDocumento.setCollegamento(collegamentoType);

		MetadatiBusdoxType metadatiBusdoxType = new MetadatiBusdoxType();
		String recipient = null;
		if (!StringUtils.isBlank(testataOrdine.getFornitore().getPartitaIva())) {
			//recipient = "9906:"+testataOrdine.getFornitore().getPartitaIva();
			recipient = "0211:"+testataOrdine.getFornitore().getPartitaIva();
		} else {
			//recipient = "9906:"+testataOrdine.getFornitore().getCodiceFiscale();
			recipient = "0210:"+testataOrdine.getFornitore().getCodiceFiscale();
		}
		//"9906:"+recipient fornitore 9906:partitaIva o codiceFiscale vedere se serve IT davanti
		//NSO_RECIPIENT_ID valorizzato solo in ambiente di test 0201:testap
		String recipientIdentifier = StringUtils.isBlank(getParameter(params, NotiERConfigurationParams.NSO_RECIPIENT_ID)) ? recipient : getParameter(params, NotiERConfigurationParams.NSO_RECIPIENT_ID);
		String senderIdentifier = StringUtils.isBlank(getParameter(params, NotiERConfigurationParams.NSO_SENDER_IDENTIFIER)) ? "0201:" : getParameter(params, NotiERConfigurationParams.NSO_SENDER_IDENTIFIER);
		metadatiBusdoxType.setRecipientIdentifier(recipientIdentifier);// "0201:testap" valorizzato solo per TEST 
		metadatiBusdoxType.setSenderIdentifier(senderIdentifier+testataOrdine.getUfficio().getCodice());   // 0201:codice ufficio mittente ( destinatario dell'ordine) codice_uffcio della cpass_t_ufficio , valore di test 0201:7K3KWF
		metadatiBusdoxType.setDocumentIdentifier(getParameter(params, NotiERConfigurationParams.NSO_DOCUMENT_ID));//"urn:oasis:names:specification:ubl:schema:xsd:Order-2::Order##urn:fdc:peppol.eu:poacc:trns:order:3:restrictive:urn:www.agid.gov.it:trns:ordine:3.1::2.1");
		metadatiBusdoxType.setProcessIdentifier(getParameter(params, NotiERConfigurationParams.NSO_PROFILE_ID));
		invioDocumento.setMetadatiBusdox(metadatiBusdoxType);

		return JAXBUtility.marshall(invioDocumento);
	}
	private static String getParameter(Map<String, String> params, ConfigurationParam param) {
		return params.get(param.getParamName());
	}
}
