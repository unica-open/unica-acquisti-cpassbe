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
package it.csi.cpass.cpassbe.ejb.mapper.ord;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;

import it.csi.cpass.cpassbe.ejb.entity.ord.CpassVOrdine;
import it.csi.cpass.cpassbe.lib.dto.ord.VOrdine;
import it.csi.cpass.cpassbe.lib.mapper.BaseMapperInterface;

/**
 * Mapper between settore and CpassVCpv
 */
@Mapper
public abstract class VOrdineMapper implements BaseMapperInterface<VOrdine, CpassVOrdine> {


	@Override
	public VOrdine toModel(CpassVOrdine entity) {
		if (entity == null) {
			return null;
		}
		VOrdine vo = new VOrdine();
		vo.setId(entity.getOrdineId());		
		vo.setTestataOrdineId(entity.getTestataOrdineId());
		//vo.setDestinatarioId(entity.getDestinatarioId());
		//vo.setRigaOrdineId(entity.getRigaOrdineId());
		//vo.setImpegnoOrdineId(entity.getImpegnoOrdineId());
		vo.setImpegnoId(entity.getImpegnoId());
		vo.setSubimpegnoOrdineId(entity.getSubimpegnoOrdineId());
			
		vo.setNumeroCapitolo(entity.getNumeroCapitolo());
		vo.setNumeroArticolo(entity.getNumeroArticolo());
		vo.setImpegnoAnnoEsercizio(entity.getImpegnoAnnoEsercizio());
		vo.setImpegnoAnno(entity.getImpegnoAnno());
		vo.setImpegnoNumero(entity.getImpegnoNumero());
		vo.setSubimpegnoAnno(entity.getSubimpegnoAnno());
		vo.setSubimpegnoNumero(entity.getSubimpegnoNumero());
		vo.setImportoImpegno(entity.getImportoImpegno());
		vo.setSubimpegnoImporto(entity.getSubimpegnoImporto());

		
		return vo;
	}

	@Override
	@IterableMapping(elementTargetType = VOrdine.class)
	public abstract List<VOrdine> toModels(Collection<CpassVOrdine> entities);

	@Override
	@IterableMapping(elementTargetType = CpassVOrdine.class)
	public abstract List<CpassVOrdine> toEntities(Collection<VOrdine> models);

	
	@Override
	public CpassVOrdine toEntity(VOrdine model) {
		if (model == null) {
			return null;
		}
		CpassVOrdine cpassVOrdine = new CpassVOrdine();
		
		cpassVOrdine.setId(model.getOrdineId());
		
		cpassVOrdine.setTestataOrdineId(model.getTestataOrdineId());
		//cpassVOrdine.setDestinatarioId(model.getDestinatarioId());
		//cpassVOrdine.setRigaOrdineId(model.getRigaOrdineId());
		//cpassVOrdine.setImpegnoOrdineId(model.getImpegnoOrdineId());
		cpassVOrdine.setImpegnoId(model.getImpegnoId());
		cpassVOrdine.setSubimpegnoOrdineId(model.getSubimpegnoOrdineId());
		
		cpassVOrdine.setNumeroCapitolo(model.getNumeroCapitolo());
		cpassVOrdine.setNumeroArticolo(model.getNumeroArticolo());
		cpassVOrdine.setImpegnoAnnoEsercizio(model.getImpegnoAnnoEsercizio());
		cpassVOrdine.setImpegnoAnno(model.getImpegnoAnno());
		cpassVOrdine.setImpegnoNumero(model.getImpegnoNumero());
		cpassVOrdine.setSubimpegnoAnno(model.getSubimpegnoAnno());
		cpassVOrdine.setSubimpegnoNumero(model.getSubimpegnoNumero());
		cpassVOrdine.setImportoImpegno(model.getImportoImpegno());
		cpassVOrdine.setSubimpegnoImporto(model.getSubimpegnoImporto());
		return cpassVOrdine;
	}
}
