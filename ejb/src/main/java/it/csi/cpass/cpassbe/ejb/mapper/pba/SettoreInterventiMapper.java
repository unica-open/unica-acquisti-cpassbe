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

import it.csi.cpass.cpassbe.ejb.entity.pba.CpassDPbaSettoreInterventi;
import it.csi.cpass.cpassbe.lib.dto.pba.SettoreInterventi;
import it.csi.cpass.cpassbe.lib.mapper.BaseMapperInterface;

/**
 * Mapper between SettoreInterventi and CpassDPbaSettoreInterventi
 */
@Mapper
public interface SettoreInterventiMapper extends BaseMapperInterface<SettoreInterventi, CpassDPbaSettoreInterventi> {

	@Override
	@Mapping(source = "settoreInterventiDescrizione", target = "descrizione")
	@Mapping(source = "settoreInterventiCodice", target = "codice")
	SettoreInterventi toModel(CpassDPbaSettoreInterventi entity);

	@Override
	@IterableMapping(elementTargetType = SettoreInterventi.class)
	List<SettoreInterventi> toModels(Collection<CpassDPbaSettoreInterventi> entities);

	@Override
	@InheritInverseConfiguration(name="toModel")
	@Mapping(target = "cpassTPbaInterventos", ignore = true)
	CpassDPbaSettoreInterventi toEntity(SettoreInterventi model);

	@Override
	@IterableMapping(elementTargetType = CpassDPbaSettoreInterventi.class)
	List<CpassDPbaSettoreInterventi> toEntities(Collection<SettoreInterventi> models);
}
