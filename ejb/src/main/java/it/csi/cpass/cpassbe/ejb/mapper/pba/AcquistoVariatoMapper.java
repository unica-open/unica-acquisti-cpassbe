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
package it.csi.cpass.cpassbe.ejb.mapper.pba;

import java.util.Collection;
import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import it.csi.cpass.cpassbe.ejb.entity.CpassDStato;
import it.csi.cpass.cpassbe.ejb.entity.pba.CpassDPbaAcquistoVariato;
import it.csi.cpass.cpassbe.lib.dto.pba.AcquistoVariato;
import it.csi.cpass.cpassbe.lib.mapper.BaseMapperInterface;

/**
 * Mapper between AcquistoVariato and CpassDPbaAcquistoVariato
 */
@Mapper
public interface AcquistoVariatoMapper extends BaseMapperInterface<AcquistoVariato, CpassDPbaAcquistoVariato> {

	@Override
	@Mapping(source = "acquistoVariatoCodice", target = "codice")
	@Mapping(source = "acquistoVariatoDescrizione", target = "descrizione")
	@Mapping(source = "acquistoVariatoDescrizioneEstesa", target = "descrizioneEstesa")
	AcquistoVariato toModel(CpassDPbaAcquistoVariato entity);

	@Override
	@IterableMapping(elementTargetType = AcquistoVariato.class)
	List<AcquistoVariato> toModels(Collection<CpassDPbaAcquistoVariato> entities);

	@Override
	@InheritInverseConfiguration(name = "toModel")
	@Mapping(target = "cpassTPbaInterventos", ignore = true)
	CpassDPbaAcquistoVariato toEntity(AcquistoVariato model);

	@Override
	@IterableMapping(elementTargetType = CpassDStato.class)
	List<CpassDPbaAcquistoVariato> toEntities(Collection<AcquistoVariato> models);

}
