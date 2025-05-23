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

import it.csi.cpass.cpassbe.ejb.entity.pba.CpassDPbaRicompresoTipo;
import it.csi.cpass.cpassbe.lib.dto.pba.RicompresoTipo;
import it.csi.cpass.cpassbe.lib.mapper.BaseMapperInterface;

/**
 * Mapper between RicompresoTipo and CpassDPbaNuts
 */
@Mapper
public interface RicompresoTipoMapper extends BaseMapperInterface<RicompresoTipo, CpassDPbaRicompresoTipo> {

	@Override
	@Mapping(source = "ricompresoTipoCodice", target = "codice")
	@Mapping(source = "ricompresoTipoDescrizione", target = "descrizione")
	@Mapping(source = "ricompresoTipoCuiObbligatorio", target = "cuiObbligatorio")
	@Mapping(source = "ricompresoTipoConteggioImporti", target = "conteggioImporti")
	RicompresoTipo toModel(CpassDPbaRicompresoTipo entity);

	@Override
	@IterableMapping(elementTargetType = RicompresoTipo.class)
	List<RicompresoTipo> toModels(Collection<CpassDPbaRicompresoTipo> entities);

	@Override
	@InheritInverseConfiguration(name="toModel")
	@Mapping(target = "cpassTPbaInterventos", ignore = true)
	CpassDPbaRicompresoTipo toEntity(RicompresoTipo model);

	@Override
	@IterableMapping(elementTargetType = CpassDPbaRicompresoTipo.class)
	List<CpassDPbaRicompresoTipo> toEntities(Collection<RicompresoTipo> models);

}
