/*-
 * ========================LICENSE_START=================================
 * CPASS BackEnd - INTEGRATION submodule - ACTA
 * %%
 * Copyright (C) 2019 - 2023 CSI Piemonte
 * %%
 * SPDX-FileCopyrightText: Copyright 2019 - 2023 | CSI Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 * =========================LICENSE_END==================================
 */
package it.csi.cpass.cpassbe.lib.external.impl.acta.wrapper;
import it.csi.cpass.cpassbe.lib.external.impl.acta.wrapper.base.BaseAcarisServiceWrapper;
import it.csi.cpass.cpassbe.lib.util.log.LogUtil;
/**
 * The Class AcarisServiceWrapper.
 */
public class VerificaArchiviazioneOrdineActaServiceWrapper extends BaseAcarisServiceWrapper{	
	protected final static LogUtil log = new LogUtil(VerificaArchiviazioneOrdineActaServiceWrapper.class);
	public Boolean verificaArchiviazione() {
		String methodName = "VerificaArchiviazione";
		log.info(methodName, "START "  );
		//todo implementare la logica di verifica
		log.info(methodName, "END "  );
		return Boolean.TRUE;
	}
}
