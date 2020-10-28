/*-
 * ========================LICENSE_START=================================
 * CPASS BackEnd - EJB submodule
 * %%
 * Copyright (C) 2019 - 2020 CSI Piemonte
 * %%
 * SPDX-FileCopyrightText: Copyright 2019 - 2020 | CSI Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 * =========================LICENSE_END==================================
 */
package it.csi.cpass.cpassbe.ejb.business.be.service.impl.ordine;

import java.util.List;
import java.util.UUID;

import it.csi.cpass.cpassbe.ejb.business.be.dad.DestinatarioOrdineDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.ImpegnoDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.RigaOrdineDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.TestataOrdineDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.base.BaseService;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.ordine.DeleteTestataOrdineRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.ordine.DeleteTestataOrdineResponse;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;
import it.csi.cpass.cpassbe.lib.dto.Impegno;
import it.csi.cpass.cpassbe.lib.dto.ord.Destinatario;
import it.csi.cpass.cpassbe.lib.dto.ord.RigaOrdine;
import it.csi.cpass.cpassbe.lib.dto.ord.TestataOrdine;

public class DeleteTestataOrdineService extends BaseService<DeleteTestataOrdineRequest, DeleteTestataOrdineResponse> {
	
	private final TestataOrdineDad testataOrdineDad;
	private final DestinatarioOrdineDad destinatarioDad;
	private final RigaOrdineDad rigaOrdineDad;
	private final ImpegnoDad impegnoDad;

	/**
	 * Constructor
	 * 
	 * @param configurationHelper the configuration helper
	 * @param testataOrdineDad    the testataOrdine DAD
	 * @param decodificaDad       the decodifica DAD
	 */
	public DeleteTestataOrdineService(ConfigurationHelper configurationHelper, TestataOrdineDad testataOrdineDad, DestinatarioOrdineDad destinatarioDad, RigaOrdineDad rigaOrdineDad, ImpegnoDad impegnoDad) {
		super(configurationHelper);
		this.testataOrdineDad = testataOrdineDad;
		this.destinatarioDad = destinatarioDad;
		this.rigaOrdineDad = rigaOrdineDad;
		this.impegnoDad = impegnoDad;
	}

	@Override
	protected void checkServiceParams() {
		UUID idTestataOrdine = request.getIdTestataOrdine();
     	checkNotNull(idTestataOrdine, "idTestataOrdine", true);
	}

	@Override
	protected void execute() {
		
		TestataOrdine testataOrdine = testataOrdineDad.getTestataOrdineModel(request.getIdTestataOrdine());
		
		for(Destinatario destinatario : testataOrdine.getListDestinatario()) {
		
			List<RigaOrdine> righe = rigaOrdineDad.getRigheByDestinatario(destinatario.getId());
					
			// elimino tutte le righe collegate
			for(RigaOrdine riga : righe) {
				
				//elimino gli impegni (subimpegni compresi)
				impegnoDad.deleteImpegniByRiga(riga.getId());
				
				//e la riga
				rigaOrdineDad.deleteRiga(riga.getId());;
			}
			
			// elimino il destinatario
			destinatarioDad.deleteDestinatario(destinatario.getId());
		}
		
		testataOrdineDad.deleteAssociati(testataOrdine);
		
		testataOrdineDad.deleteTestataOrdine(request.getIdTestataOrdine());
	}

}
