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
package it.csi.cpass.cpassbe.lib.external;

import java.util.List;
import java.util.Map;

import it.csi.cpass.cpassbe.lib.dto.DocumentoSpesa;
import it.csi.cpass.cpassbe.lib.external.itf.BaseHelper;
import it.csi.cpass.cpassbe.lib.external.res.ExternalServiceResponseWrapper;

/**
 * Helper for invoking external services for DocumentoSpesa
 */
public interface DocumentoSpesaHelper extends BaseHelper {
	
	public static String STATO_FATTURA_ANNULLATO = "A";

	/**
	 * Retrieves a list of DocumentoSpesa via the given filter
	 * @param params the params
	 * @param fornitore the filter to apply
	 * @return the element corresponding to the filter
	 */
	ExternalServiceResponseWrapper<List<DocumentoSpesa>> getDocumentoSpesa(Map<String, String> params, DocumentoSpesa filtroDocumentoSpesa);

	ExternalServiceResponseWrapper<List<DocumentoSpesa>> getDocumentoSpesaRipartibile(Map<String, String> params, DocumentoSpesa filtroDocumentoSpesa);
}
