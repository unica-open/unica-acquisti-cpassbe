/*-
 * ========================LICENSE_START=================================
 * CPASS BackEnd - INTEGRATION submodule - NotiER
 * %%
 * Copyright (C) 2019 - 2020 CSI Piemonte
 * %%
 * SPDX-FileCopyrightText: Copyright 2019 - 2020 | CSI Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 * =========================LICENSE_END==================================
 */
package it.csi.cpass.cpassbe.lib.external.impl.notier;

import java.text.SimpleDateFormat;
import java.util.Date;

import it.csi.cpass.cpassbe.lib.dto.ord.Destinatario;
import it.csi.cpass.cpassbe.lib.dto.ord.TestataOrdine;
import it.csi.cpass.cpassbe.lib.util.serialization.JAXBUtility;
import it.rer.intercenter.notier.services._1_0.inviodocumento.request.ChiaveDocumentoType;
import it.rer.intercenter.notier.services._1_0.inviodocumento.request.CollegamentoType;
import it.rer.intercenter.notier.services._1_0.inviodocumento.request.ConfigurazioneType;
import it.rer.intercenter.notier.services._1_0.inviodocumento.request.DocumentoType;
import it.rer.intercenter.notier.services._1_0.inviodocumento.request.FormatoType;
import it.rer.intercenter.notier.services._1_0.inviodocumento.request.IntegrazioneType;
import it.rer.intercenter.notier.services._1_0.inviodocumento.request.InvioDocumento;
import it.rer.intercenter.notier.services._1_0.inviodocumento.request.MetadatiBusdoxType;
import it.rer.intercenter.notier.services._1_0.inviodocumento.request.RappresentazioneType;
import it.rer.intercenter.notier.services._1_0.inviodocumento.request.TipoDocumentoType;
import it.rer.intercenter.notier.services._1_0.inviodocumento.request.TipoMittenteType;

public class PeppolMetadati {
	
	public static String getXmlMetadati(TestataOrdine testataOrdine, Destinatario destinatarioOrdine) {
		InvioDocumento invioDocumento = new InvioDocumento();
		invioDocumento.setVersione("1.0");

		DocumentoType documentoType = new DocumentoType();
		invioDocumento.setDocumento(documentoType);

		ChiaveDocumentoType chiaveDocumentoType = new ChiaveDocumentoType();
		chiaveDocumentoType.setMittente(testataOrdine.getSettore().getCodice());
		chiaveDocumentoType.setTipoMittente(TipoMittenteType.NOTIER);
		chiaveDocumentoType.setAnno(testataOrdine.getAnno());
		chiaveDocumentoType.setNumero("" + testataOrdine.getNumero());
		chiaveDocumentoType.setTipoDocumento(TipoDocumentoType.ORDINE);
		documentoType.setChiave(chiaveDocumentoType);

		RappresentazioneType rappresentazioneType = new RappresentazioneType();
		rappresentazioneType.setFormato(FormatoType.UBL);
		rappresentazioneType.setVersione("2.1");
		documentoType.setRappresentazione(rappresentazioneType);

		ConfigurazioneType configurazioneType = new ConfigurazioneType();
		configurazioneType.setInvioSdi(false);
		configurazioneType.setInvioPeppol(true);
		configurazioneType.setInvioConservazione(false);
		configurazioneType.setApprovazioneAutomatica(false);
		configurazioneType.setAccettaChiaveDuplicata(false);
		invioDocumento.setConfigurazione(configurazioneType);

		IntegrazioneType integrazioneType = new IntegrazioneType();
		integrazioneType.setRegistro(null);
		invioDocumento.setIntegrazione(integrazioneType);

		CollegamentoType collegamentoType = new CollegamentoType();
		// ‘urn:notier:’||CPASS_T_UFFICIO.ufficio_codice||’:’||data di sistema||’:’||
		// CPASS_T_ORD_TESTATA_ORDINE.numero||’_’||CPASS_T_ORD_DESTINATARIO_ORDINE.progressivo||’_’||data sistema con ora minuti e
		// secondi||’:CP:ORDINE’
		// es. urn:notier:A17LZ5:20200525:414_2_04062020183145:CP:ORDINE
		SimpleDateFormat sdfSistema = new SimpleDateFormat("yyyyMMdd");
		SimpleDateFormat sdfSistemaCompleta = new SimpleDateFormat("ddMMyyyyHHmmss");
		Date date = new Date();
		String urn = "urn:notier:" + testataOrdine.getUfficio().getCodice() + ":" + sdfSistema.format(date) + ":" + testataOrdine.getNumero() + "_" + 
				destinatarioOrdine.getProgressivo() + "_" + sdfSistemaCompleta.format(date) + ":CP:ORDINE";
		collegamentoType.setUrn(urn);
		invioDocumento.setCollegamento(collegamentoType);

		MetadatiBusdoxType metadatiBusdoxType = new MetadatiBusdoxType();
		metadatiBusdoxType.setRecipientIdentifier("TOBD");
		metadatiBusdoxType.setSenderIdentifier("TOBD");
		metadatiBusdoxType.setDocumentIdentifier("TOBD");
		metadatiBusdoxType.setProcessIdentifier("TOBD");
		invioDocumento.setMetadatiBusdox(metadatiBusdoxType);

		return JAXBUtility.marshall(invioDocumento);
	}
	
}
