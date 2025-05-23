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

import it.csi.cpass.cpassbe.ejb.entity.CpassDCpv;
import it.csi.cpass.cpassbe.lib.dto.Cpv;
import it.csi.cpass.cpassbe.lib.mapper.BaseMapperInterface;

/**
 * Mapper between Cpv and CpassDCpv
 */
@Mapper
public interface CpvMapper extends BaseMapperInterface<Cpv, CpassDCpv> {

	@Override
	@Mapping(source = "cpvCategoria", target = "categoria")
	@Mapping(source = "cpvClasse", target = "classe")
	@Mapping(source = "cpvCodice", target = "codice")
	@Mapping(source = "cpvCodicePadre", target = "codicePadre")
	@Mapping(source = "cpvDescrizione", target = "descrizione")
	@Mapping(source = "cpvDivisione", target = "divisione")
	@Mapping(source = "cpvGruppo", target = "gruppo")
	@Mapping(source = "cpvTipologia", target = "tipologia")
	@Mapping(source = "cpassDPbaSettoreInterventi.settoreInterventiId", target = "settoreInterventi.id")
	// @Mapping(source = "cpassDOggettiSpesas", target = "oggettiSpesa")

	Cpv toModel(CpassDCpv entity);

	@Override
	@IterableMapping(elementTargetType = Cpv.class)
	List<Cpv> toModels(Collection<CpassDCpv> entities);

	@Override
	@InheritInverseConfiguration(name = "toModel")
	@Mapping(target = "cpassTPbaInterventos", ignore = true)
	CpassDCpv toEntity(Cpv model);

	@Override
	@IterableMapping(elementTargetType = CpassDCpv.class)
	List<CpassDCpv> toEntities(Collection<Cpv> models);
}
