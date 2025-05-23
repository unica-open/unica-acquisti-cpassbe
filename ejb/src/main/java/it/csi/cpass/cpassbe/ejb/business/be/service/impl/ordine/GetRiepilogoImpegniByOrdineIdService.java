/*-
 * ========================LICENSE_START=================================
 * CPASS BackEnd - EJB submodule
 * %%
 * Copyright (C) 2019 - 2025 CSI Piemonte
 * %%
 * SPDX-FileCopyrightText: Copyright 2019 - 2020 | CSI Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 * =========================LICENSE_END==================================
 */
package it.csi.cpass.cpassbe.ejb.business.be.service.impl.ordine;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.List;

import it.csi.cpass.cpassbe.ejb.business.be.dad.ImpegnoDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.SettoreDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.SystemDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.TestataOrdineDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.ordine.GetRiepilogoImpegniByOrdineIdRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.ordine.GetRiepilogoImpegniByOrdineIdResponse;
import it.csi.cpass.cpassbe.ejb.util.NumberUtility;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;
import it.csi.cpass.cpassbe.lib.dto.ord.RiepilogoImpegni;
import it.csi.cpass.cpassbe.lib.dto.ord.TestataOrdine;
import it.csi.cpass.cpassbe.lib.dto.ord.VOrdine;

public class GetRiepilogoImpegniByOrdineIdService extends BaseOrdineService<GetRiepilogoImpegniByOrdineIdRequest, GetRiepilogoImpegniByOrdineIdResponse> {

	private final ImpegnoDad impegnoDad;
	/**
	 * Constructor
	 *
	 * @param configurationHelper the configuration helper
	 * @param testataOrdineDad    the testataOrdine DAD
	 */
	public GetRiepilogoImpegniByOrdineIdService(ConfigurationHelper configurationHelper, TestataOrdineDad testataOrdineDad, ImpegnoDad impegnoDad, SystemDad systemDad,SettoreDad settoreDad) {
		super(configurationHelper, testataOrdineDad,settoreDad);
		this.impegnoDad = impegnoDad;
	}

	@Override
	protected void checkServiceParams() {
		checkNotNull(request.getId(), "id");
	}

	@Override
	protected void execute() {
		final RiepilogoImpegni riepilogoImpegni = new RiepilogoImpegni();
		final TestataOrdine testataOrdine = testataOrdineDad.getTestataOrdine(request.getId());
		final List<VOrdine> listaAggregata = impegnoDad.getListImpegniRiepilogoByOrdineId(request.getId());
		BigDecimal importoImpegnato;
		BigDecimal val = BigDecimal.ZERO;

		for(final VOrdine io : listaAggregata) {
			if(io.getSubimpegnoImporto()!=null && io.getSubimpegnoImporto().compareTo(BigDecimal.ZERO) != 0){
				val = val.add(io.getSubimpegnoImporto());
			}else {
				if(io.getImportoImpegno()!=null && io.getImportoImpegno().compareTo(BigDecimal.ZERO) != 0){
					val = val.add( io.getImportoImpegno());
				}
			}

		}
		importoImpegnato = val;
		riepilogoImpegni.setImpegniOrdine(listaAggregata);
		riepilogoImpegni.setTotaleOrdineConIVA(testataOrdine.getTotaleConIva() == null ? BigDecimal.ZERO : testataOrdine.getTotaleConIva());
		riepilogoImpegni.setTotaleOrdineNoIVA(testataOrdine.getTotaleNoIva() == null ? BigDecimal.ZERO : testataOrdine.getTotaleNoIva());
		riepilogoImpegni.setTotaleImpegnato(importoImpegnato);

		riepilogoImpegni.setTotaliCoerenti(false);
		final BigDecimal totaleConIva = testataOrdine.getTotaleConIva() == null ? BigDecimal.ZERO : testataOrdine.getTotaleConIva();

		new MathContext(2);

		if(NumberUtility.arrotondaDueDec(importoImpegnato).equals(NumberUtility.arrotondaDueDec (totaleConIva))) {
			riepilogoImpegni.setTotaliCoerenti(Boolean.TRUE);
		}
		response.setRiepilogoImpegni(riepilogoImpegni);
	}

	/*
	public BigDecimal arrotonda (BigDecimal value ) {
        return arrotonda ( value , 2) ;
    }

	public BigDecimal arrotonda (BigDecimal value , int numeroDecimali) {
        MathContext m = new MathContext(34, RoundingMode.HALF_UP);
        BigDecimal ris = value.setScale(numeroDecimali, m.getRoundingMode());
        return ris;
    }
	 */
}
