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
package it.csi.cpass.cpassbe.ejb.mapper;

import java.util.Collection;
import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import it.csi.cpass.cpassbe.ejb.entity.CpassDUnitaMisura;
import it.csi.cpass.cpassbe.lib.dto.UnitaMisura;
import it.csi.cpass.cpassbe.lib.mapper.BaseMapperInterface;

@Mapper
public interface  UnitaMisuraMapper extends BaseMapperInterface<UnitaMisura, CpassDUnitaMisura> {

	@Override
	@Mapping(source = "unitaMisuraCodice", target = "codice")
	@Mapping(source = "unitaMisuraDescrizione", target = "descrizione")
	@Mapping(source = "unitaMisuraAmbitoUtilizzo", target = "ambitoUtilizzo")
	UnitaMisura toModel(CpassDUnitaMisura entity);

	@Override
	@IterableMapping(elementTargetType = UnitaMisura.class)
	List<UnitaMisura> toModels(Collection<CpassDUnitaMisura> entities);

	@Override
	@InheritInverseConfiguration(name="toModel")
	CpassDUnitaMisura toEntity(UnitaMisura model);

	@Override
	@IterableMapping(elementTargetType = CpassDUnitaMisura.class)
	List<CpassDUnitaMisura> toEntities(Collection<UnitaMisura> models);

}
