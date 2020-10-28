/*-
 * ========================LICENSE_START=================================
 * CPASS BackEnd - INTEGRATION submodule - SIAC
 * %%
 * Copyright (C) 2019 - 2020 CSI Piemonte
 * %%
 * SPDX-FileCopyrightText: Copyright 2019 - 2020 | CSI Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 * =========================LICENSE_END==================================
 */
package it.csi.cpass.cpassbe.lib.external.impl.siac;

import java.util.List;
import java.util.Map;

import it.csi.cpass.cpassbe.lib.dto.FiltroFornitore;
import it.csi.cpass.cpassbe.lib.dto.Fornitore;
import it.csi.cpass.cpassbe.lib.external.FornitoreHelper;
import it.csi.cpass.cpassbe.lib.external.res.ExternalServiceResponseWrapper;

/**
 * Example CDI implementation helper
 */
//@RequestScoped
//@ExternalServiceImpl("FornitoreHelperCDIImpl-SIAC")
public class FornitoreHelperCDIImpl extends BaseSiacHelperImpl implements FornitoreHelper {

	@Override
	public ExternalServiceResponseWrapper<List<Fornitore>> getFornitori(Map<String, String> params, FiltroFornitore filtroFornitore) {
		FornitoreHelperImpl impl = new FornitoreHelperImpl();
		return impl.getFornitori(params, filtroFornitore);
	}

}
