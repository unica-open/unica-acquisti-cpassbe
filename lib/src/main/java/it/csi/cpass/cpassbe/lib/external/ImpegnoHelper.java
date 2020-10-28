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

import java.util.Map;

import it.csi.cpass.cpassbe.lib.dto.Impegno;
import it.csi.cpass.cpassbe.lib.dto.ord.FiltroImpegni;
import it.csi.cpass.cpassbe.lib.external.itf.BaseHelper;
import it.csi.cpass.cpassbe.lib.external.res.ExternalServiceResponseWrapper;
import it.csi.cpass.cpassbe.lib.util.pagination.PagedList;

/**
 * Helper for invoking external services for Impegno
 */
public interface ImpegnoHelper extends BaseHelper {

	/**
	 * Retrieves a list of Impegno via the given filter
	 * @param params the params
	 * @param impegno the filter to apply
	 * @param page the page
	 * @param size the size
	 * @return the element corresponding to the filter
	 */
	ExternalServiceResponseWrapper<PagedList<Impegno>> getImpegni(Map<String, String> params, FiltroImpegni filtroImpegni, int page, int size);
}
