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

import it.csi.cpass.cpassbe.ejb.entity.CpassDElaborazioneTipo;
import it.csi.cpass.cpassbe.lib.dto.ElaborazioneTipo;
import it.csi.cpass.cpassbe.lib.mapper.BaseMapperInterface;

/**
 * Mapper between Stato and CpassDPbaNuts
 */
@Mapper
public interface ElaborazioneTipoMapper extends BaseMapperInterface<ElaborazioneTipo, CpassDElaborazioneTipo> {

	@Override
	@Mapping(source = "elaborazioneTipoCodice", target = "codice")
	@Mapping(source = "elaborazioneTipoDescrizione", target = "descrizione")
	@Mapping(source = "moduloCodice", target = "moduloCodice")
	ElaborazioneTipo toModel(CpassDElaborazioneTipo entity);

	@Override
	@IterableMapping(elementTargetType = ElaborazioneTipo.class)
	List<ElaborazioneTipo> toModels(Collection<CpassDElaborazioneTipo> entities);

	@Override
	@InheritInverseConfiguration(name = "toModel")
	@Mapping(target = "cpassTElaboraziones", ignore = true)
	CpassDElaborazioneTipo toEntity(ElaborazioneTipo model);

	@Override
	@IterableMapping(elementTargetType = CpassDElaborazioneTipo.class)
	List<CpassDElaborazioneTipo> toEntities(Collection<ElaborazioneTipo> models);

}
