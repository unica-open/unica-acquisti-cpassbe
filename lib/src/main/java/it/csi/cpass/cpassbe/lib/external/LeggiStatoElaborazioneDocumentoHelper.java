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

import it.csi.cpass.cpassbe.lib.dto.batch.VerificaInvioEvasioneInContabilita;
import it.csi.cpass.cpassbe.lib.external.itf.BaseHelper;
import it.csi.cpass.cpassbe.lib.external.res.ExternalServiceResponseWrapper;

/**
 * Helper for invoking external services for DocumentoSpesa
 */
public interface LeggiStatoElaborazioneDocumentoHelper extends BaseHelper {

	ExternalServiceResponseWrapper<VerificaInvioEvasioneInContabilita> leggiStatoElaborazioneDocumento(Map<String, String> params, Integer idVerifica);


}
