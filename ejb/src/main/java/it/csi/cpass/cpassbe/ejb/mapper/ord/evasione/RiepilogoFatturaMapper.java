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
package it.csi.cpass.cpassbe.ejb.mapper.ord.evasione;

import java.util.Collection;
import java.util.List;

import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;

import it.csi.cpass.cpassbe.ejb.entity.view.CpassVRiepilogoFatturaEvasione;
import it.csi.cpass.cpassbe.lib.dto.ord.evasione.RiepilogoFatturaEvasione;
import it.csi.cpass.cpassbe.lib.mapper.BaseMapperInterface;

/**
 * Mapper between RiepilogoFatturaEvasione and CpassVRiepilogoFatturaEvasione
 */
@Mapper
public abstract class RiepilogoFatturaMapper implements BaseMapperInterface<RiepilogoFatturaEvasione, CpassVRiepilogoFatturaEvasione> {


	@Override
	public RiepilogoFatturaEvasione toModel(CpassVRiepilogoFatturaEvasione entity) {
		if (entity == null) {
			return null;
		}
		RiepilogoFatturaEvasione vo = new RiepilogoFatturaEvasione();		
		vo.setId(entity.getId());		
		vo.setRiepilogoFatturaEvasioneId(entity.getRiepilogoFatturaEvasioneId());		
		vo.setTestataEvasioneId(entity.getTestataEvasioneId());		
		vo.setImpegnoAnno(entity.getImpegnoAnno());
		vo.setImpegnoAnnoEsercizio(entity.getImpegnoAnnoEsercizio());
		vo.setImpegnoNumero(entity.getImpegnoNumero());
		vo.setRipartito(entity.getRipartito());
		vo.setSospeso(entity.getSospeso());
		vo.setSubimpegnoAnno(entity.getSubimpegnoAnno());
		vo.setSubimpegnoNumero(entity.getSubimpegnoNumero());
		vo.setCausaleSospensioneCodice(entity.getCausaleSospensioneCodice());
		vo.setCausaleSospensioneDescrizione(entity.getCausaleSospensioneDescrizione());
		vo.setDataSospensione(entity.getDataSospensione());
		return vo;
	}

	@Override
	@IterableMapping(elementTargetType = RiepilogoFatturaEvasione.class)
	public abstract List<RiepilogoFatturaEvasione> toModels(Collection<CpassVRiepilogoFatturaEvasione> entities);

	@Override
	@IterableMapping(elementTargetType = CpassVRiepilogoFatturaEvasione.class)
	public abstract List<CpassVRiepilogoFatturaEvasione> toEntities(Collection<RiepilogoFatturaEvasione> models);

	
	@Override
	public CpassVRiepilogoFatturaEvasione toEntity(RiepilogoFatturaEvasione model) {
		if (model == null) {
			return null;
		}
		CpassVRiepilogoFatturaEvasione cpassVRiepilogoFatturaEvasione = new CpassVRiepilogoFatturaEvasione();		
		cpassVRiepilogoFatturaEvasione.setId(model.getRiepilogoFatturaEvasioneId());		
		return cpassVRiepilogoFatturaEvasione;
	}
}
