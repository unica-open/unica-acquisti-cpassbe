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
import it.csi.cpass.cpassbe.lib.dto.error.MsgCpassOrd;
import it.csi.cpass.cpassbe.lib.dto.ord.ProtocolloOrdine;
import it.csi.cpass.cpassbe.lib.external.impl.acta.wrapper.base.BaseAcarisServiceWrapper;
import it.csi.cpass.cpassbe.lib.util.log.LogUtil;
import it.doqui.acta.acaris.common.EnumPropertyFilter;
import it.doqui.acta.acaris.common.EnumQueryOperator;
import it.doqui.acta.acaris.common.ObjectIdType;
import it.doqui.acta.acaris.common.QueryResponse;
import it.doqui.acta.acaris.objectservice.AcarisException;  
/**
 * The Class AcarisServiceWrapper.
 */
public class CercaStrutturaAggregativaXIndiceClassificazioneActaServiceWrapper extends BaseAcarisServiceWrapper{
	
	protected final static LogUtil log = new LogUtil(CercaStrutturaAggregativaXIndiceClassificazioneActaServiceWrapper.class);

	public ProtocolloOrdine ricerca(String indiceClassificazioneEsteso) throws AcarisException {
		String methodName = "ricerca X Indice Classificazione Acta";
		ProtocolloOrdine protocollo = new ProtocolloOrdine();
		QueryResponse res = queryObjectService("AggregazioneICEView", indiceClassificazioneEsteso , "indiceClassificazioneEstesa" ,EnumPropertyFilter.ALL,EnumQueryOperator.EQUALS,null,Boolean.TRUE );

		if (res == null || res.getObject() == null || res.getObject().getObjects().size() == 0) {
			log.error(methodName, "STEP 1 " + MsgCpassOrd.ORDORDE0169.getMessage());
			errorCode = MsgCpassOrd.ORDORDE0169.getCode();			
			errorDesc = MsgCpassOrd.ORDORDE0169.getMessage();
			protocollo.setDescErrore(errorDesc);
			protocollo.setCodErrore(errorCode);
			return protocollo;
		}else {
			String idTipoAggregazione = getPropertyValueByName(res.getObject().getObjects(), "idTipoAggregazione");
			String ecmUuidNodo        = getPropertyValueByName(res.getObject().getObjects(), "ecmUuidNodo");
			String targetQuery        = getTargetQueryByTipoAggregazione(idTipoAggregazione);
			// secondo step
			QueryResponse res1 = queryObjectService(targetQuery, ecmUuidNodo , "ecmUuidNodo" ,EnumPropertyFilter.ALL,EnumQueryOperator.EQUALS,null,Boolean.TRUE );
			try {
				ObjectIdType strutturaAggregativaObjectId = res1.getObject().getObjects().get(0).getObjectId();
				protocollo.setStrutturaAggregativaObjectId(strutturaAggregativaObjectId.getValue());
			}	catch (Exception e) {
				log.error(methodName, "STEP 2 " + MsgCpassOrd.ORDORDE0169.getMessage() + " " + e);
				errorCode = MsgCpassOrd.ORDORDE0169.getCode();			
				errorDesc = MsgCpassOrd.ORDORDE0169.getMessage();
				protocollo.setDescErrore(errorDesc);
				protocollo.setCodErrore(errorCode);
				return protocollo;
			}

		}
		return protocollo;
	}
}
