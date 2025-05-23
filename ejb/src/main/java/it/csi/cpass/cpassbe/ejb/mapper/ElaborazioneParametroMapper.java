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

import it.csi.cpass.cpassbe.ejb.entity.CpassTElaborazioneParametro;
import it.csi.cpass.cpassbe.lib.dto.ElaborazioneParametro;
import it.csi.cpass.cpassbe.lib.mapper.BaseMapperInterface;

/**
 * Mapper between ElaborazioneParametro and CpassTElaborazioneParametro
 */
// @Mapper(uses = { ElaborazioneMapper.class })
@Mapper
public interface ElaborazioneParametroMapper extends BaseMapperInterface<ElaborazioneParametro, CpassTElaborazioneParametro> {

	@Override

	@Mapping(source = "cpassTElaborazione", target = "elaborazione")
	@Mapping(source = "elaborazioneParametroChiave", target = "chiave")
	@Mapping(source = "elaborazioneParametroValore", target = "valore")

	ElaborazioneParametro toModel(CpassTElaborazioneParametro entity);

	@Override
	@IterableMapping(elementTargetType = ElaborazioneParametro.class)
	List<ElaborazioneParametro> toModels(Collection<CpassTElaborazioneParametro> entities);

	@Override
	@InheritInverseConfiguration(name = "toModel")
	CpassTElaborazioneParametro toEntity(ElaborazioneParametro model);

	@Override
	@IterableMapping(elementTargetType = CpassTElaborazioneParametro.class)
	List<CpassTElaborazioneParametro> toEntities(Collection<ElaborazioneParametro> models);
}
