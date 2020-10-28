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

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import it.csi.cpass.cpassbe.ejb.entity.ord.CpassDOggettiSpesa;
import it.csi.cpass.cpassbe.ejb.mapper.AliquoteIvaMapper;
import it.csi.cpass.cpassbe.ejb.mapper.CpvMapper;
import it.csi.cpass.cpassbe.ejb.mapper.ListinoFornitoreMapper;
import it.csi.cpass.cpassbe.ejb.mapper.StringMapper;
import it.csi.cpass.cpassbe.ejb.mapper.UnitaMisuraMapper;
import it.csi.cpass.cpassbe.lib.dto.ord.OggettiSpesa;
import it.csi.cpass.cpassbe.lib.mapper.BaseMapperInterface;

@Mapper(uses = { StringMapper.class, AliquoteIvaMapper.class, CpvMapper.class, UnitaMisuraMapper.class, ListinoFornitoreMapper.class })
public interface OggettiSpesaMapper extends BaseMapperInterface<OggettiSpesa, CpassDOggettiSpesa> {

	@Override
	@Mapping(source = "inventariabile", target = "inventariabile")
	@Mapping(source = "oggettiSpesaCodice", target = "codice")	
	@Mapping(source = "oggettiSpesaDescrizione", target = "descrizione")	
	@Mapping(source = "prezzoUnitario", target = "prezzoUnitario")	
	
	@Mapping(source = "cpassDAliquoteIva", target = "aliquoteIva")	
	@Mapping(source = "cpassDCpv", target = "cpv")	
	@Mapping(source = "cpassDUnitaMisura", target = "unitaMisura")	
	@Mapping(source = "cpassTListinoFornitores", target = "listinoFornitores")	
	
	OggettiSpesa toModel(CpassDOggettiSpesa entity);

	@Override
	@IterableMapping(elementTargetType = OggettiSpesa.class)
	List<OggettiSpesa> toModels(Collection<CpassDOggettiSpesa> entities);

	@Override
	@InheritInverseConfiguration(name="toModel")
	//@Mapping(target = "cpassTPbaInterventos", ignore = true)
	CpassDOggettiSpesa toEntity(OggettiSpesa model);

	@Override
	@IterableMapping(elementTargetType = CpassDOggettiSpesa.class)
	List<CpassDOggettiSpesa> toEntities(Collection<OggettiSpesa> models);
}
