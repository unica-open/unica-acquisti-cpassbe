/*-
 * ========================LICENSE_START=================================
 * CPASS BackEnd - EJB submodule
 * %%
 * Copyright (C) 2019 - 2021 CSI Piemonte
 * %%
 * SPDX-FileCopyrightText: Copyright 2019 - 2021 | CSI Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 * =========================LICENSE_END==================================
 */
package it.csi.cpass.cpassbe.ejb.business.be.service.impl.ordine.rda;



import java.util.Date;
import java.util.UUID;

import it.csi.cpass.cpassbe.ejb.business.be.dad.DecodificaDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.RdaDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.base.BaseService;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.ordine.rda.PutCambioStatoRdaRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.ordine.rda.PutCambioStatoRdaResponse;
import it.csi.cpass.cpassbe.ejb.util.ConstantsCPassStato;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;
import it.csi.cpass.cpassbe.lib.dto.Ods;
import it.csi.cpass.cpassbe.lib.dto.Stato;
import it.csi.cpass.cpassbe.lib.dto.UnitaMisura;
import it.csi.cpass.cpassbe.lib.dto.error.MsgCpassOrd;
import it.csi.cpass.cpassbe.lib.dto.ord.rda.RigaRda;
import it.csi.cpass.cpassbe.lib.dto.ord.rda.TestataRda;


public class PutCambioStatoRdaService  extends BaseService<PutCambioStatoRdaRequest,PutCambioStatoRdaResponse>{

	private final RdaDad rdaDad;
	private final DecodificaDad decodificaDad;
	private       UUID   rdaId;
	private       String statoCode;

	public PutCambioStatoRdaService(ConfigurationHelper configurationHelper, RdaDad rdaDad, DecodificaDad decodificaDad) {
		super(configurationHelper);
		this.rdaDad    = rdaDad;
		this.decodificaDad = decodificaDad;
	}

	@Override
	protected void checkServiceParams() {
		rdaId      = request.getRdaId();
		statoCode  = request.getStatoCode();
		checkNotNull(statoCode, "statoCode");
		checkNotNull(rdaId, "rdaId", Boolean.TRUE);
	}

	@Override
	protected void execute() {
		final TestataRda testataRda = isEntityPresent(() -> rdaDad.getTestataRdaById(rdaId), "testata rda");
		final Stato stato = isEntityPresent(() -> decodificaDad.getStatoOpt(statoCode,ConstantsCPassStato.TipoStatoEnum.RDA.getCostante()), "stato");

		if(statoCode.equalsIgnoreCase(ConstantsCPassStato.StatoRdaEnum.AUTORIZZATA.getCostante())) {
			checkBusinessCondition(testataRda.getRigaRda().size()>0, MsgCpassOrd.ORDORDE0161.getError());
			final Date now          = new Date();
			boolean odsValido = Boolean.TRUE;
			boolean umValido  = Boolean.TRUE;
			//controllo validita' ods
			for(final RigaRda rigaRda : testataRda.getRigaRda()) {
				final Ods ods = rigaRda.getOggettiSpesa();
				if(ods!=null && ods.getDataCancellazione()!= null) {
					odsValido = false;
				}
			}
			//controllo validita' unita di misura
			for(final RigaRda rigaRda : testataRda.getRigaRda()) {
				final UnitaMisura um = rigaRda.getUnitaMisura();
				if(um!=null) {
					final boolean pos1 =um.getDataValiditaFine()  !=null &&  now.after(um.getDataValiditaFine());
					final boolean pos2 =um.getDataValiditaInizio()!=null &&  now.before(um.getDataValiditaInizio());
					if(pos1 || pos2) {
						umValido = false;
					}
				}
			}
			checkBusinessCondition(odsValido, MsgCpassOrd.ORDORDE0053.getError());
			checkBusinessCondition(umValido , MsgCpassOrd.ORDORDE0055.getError());
		}

		testataRda.setStato(stato);
		rdaDad.updateTestataRda(testataRda);

		response.setTestataRda(testataRda);
	}
}
