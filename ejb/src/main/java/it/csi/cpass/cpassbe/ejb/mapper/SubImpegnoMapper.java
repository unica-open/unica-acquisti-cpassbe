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
package it.csi.cpass.cpassbe.ejb.mapper;

import java.util.Collection;
import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import it.csi.cpass.cpassbe.ejb.entity.CpassTSubimpegno;
import it.csi.cpass.cpassbe.lib.dto.Subimpegno;
import it.csi.cpass.cpassbe.lib.mapper.BaseMapperInterface;

/**
 * Mapper between Subimpegno and CpassTSubimpegno
 */
@Mapper(uses = { StringMapper.class, FornitoreMapper.class, EnteMapper.class })
public interface SubImpegnoMapper extends BaseMapperInterface<Subimpegno, CpassTSubimpegno> {

	@Override
	@Mapping(source = "impegnoAnno", target = "impegno.anno")
	@Mapping(source = "impegnoAnnoEsercizio", target = "impegno.annoEsercizio")
	@Mapping(source = "impegnoNumero", target = "impegno.numero")

	@Mapping(source = "importoAttuale", target = "importoAttuale")
	@Mapping(source = "importoIniziale", target = "importoIniziale")
	@Mapping(source = "liqAnnoPrec", target = "liquidatoAnnoPrecedente")

	@Mapping(source = "provvedimentoAnno", target = "annoProvvedimento")
	@Mapping(source = "provvedimentoNumero", target = "numeroProvvedimento")
	@Mapping(source = "provvedimentoSettore", target = "settoreProvvedimento")

	@Mapping(source = "stato", target = "stato")
	@Mapping(source = "subimpegnoAnno", target = "anno")
	@Mapping(source = "subimpegnoAnnoEsercizio", target = "annoEsercizio")
	@Mapping(source = "subimpegnoNumero", target = "numero")

	@Mapping(source = "cpassTEnte", target = "ente")
	@Mapping(source = "cpassTFornitore", target = "fornitore")

	@Mapping(source = "cpassTImpegno", target = "impegno")
	Subimpegno toModel(CpassTSubimpegno entity);

	@Override
	@IterableMapping(elementTargetType = Subimpegno.class)
	List<Subimpegno> toModels(Collection<CpassTSubimpegno> entities);

	@Override
	@InheritInverseConfiguration(name="toModel")
	CpassTSubimpegno toEntity(Subimpegno model);

	@Override
	@IterableMapping(elementTargetType = CpassTSubimpegno.class)
	List<CpassTSubimpegno> toEntities(Collection<Subimpegno> models);

}
