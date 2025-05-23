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

import it.csi.cpass.cpassbe.ejb.entity.CpassTElaborazioneMessaggio;
import it.csi.cpass.cpassbe.lib.dto.ElaborazioneMessaggio;
import it.csi.cpass.cpassbe.lib.mapper.BaseMapperInterface;

/**
 * Mapper between Cpv and CpassDCpv
 */
@Mapper(uses = {ElaborazioneMapper.class})
public interface ElaborazioneMessaggioMapper extends BaseMapperInterface<ElaborazioneMessaggio, CpassTElaborazioneMessaggio> {

	@Override

	@Mapping(source = "cpassTElaborazione", target = "elaborazione")
	@Mapping(source = "elaborazioneMessaggioTipo", target = "tipo")
	@Mapping(source = "elaborazioneMessaggioCode", target = "code")
	@Mapping(source = "elaborazioneMessaggioDescrizione", target = "descrizione")

	ElaborazioneMessaggio toModel(CpassTElaborazioneMessaggio entity);

	@Override
	@IterableMapping(elementTargetType = ElaborazioneMessaggio.class)
	List<ElaborazioneMessaggio> toModels(Collection<CpassTElaborazioneMessaggio> entities);

	@Override
	@InheritInverseConfiguration(name="toModel")
	CpassTElaborazioneMessaggio toEntity(ElaborazioneMessaggio model);

	@Override
	@IterableMapping(elementTargetType = CpassTElaborazioneMessaggio.class)
	List<CpassTElaborazioneMessaggio> toEntities(Collection<ElaborazioneMessaggio> models);
}
