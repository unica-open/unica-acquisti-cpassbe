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
package it.csi.cpass.cpassbe.ejb.mapper.ord;

import java.util.Collection;
import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import it.csi.cpass.cpassbe.ejb.entity.CpassDOggettiSpesa;
import it.csi.cpass.cpassbe.ejb.mapper.AliquoteIvaMapper;
import it.csi.cpass.cpassbe.ejb.mapper.CpvMapper;
import it.csi.cpass.cpassbe.ejb.mapper.ListinoFornitoreMapper;
import it.csi.cpass.cpassbe.ejb.mapper.OdsDatiContabiliMapper;
import it.csi.cpass.cpassbe.ejb.mapper.StringMapper;
import it.csi.cpass.cpassbe.ejb.mapper.UnitaMisuraMapper;
import it.csi.cpass.cpassbe.lib.dto.Ods;
import it.csi.cpass.cpassbe.lib.mapper.BaseMapperInterface;

@Mapper(uses = { StringMapper.class, AliquoteIvaMapper.class, CpvMapper.class, UnitaMisuraMapper.class, ListinoFornitoreMapper.class, OdsDatiContabiliMapper.class })
public interface OggettiSpesaMapper extends BaseMapperInterface<Ods, CpassDOggettiSpesa> {

	@Override
	@Mapping(source = "inventariabile", target = "inventariabile")
	@Mapping(source = "oggettiSpesaCodice", target = "codice")
	@Mapping(source = "oggettiSpesaDescrizione", target = "descrizione")
	@Mapping(source = "prezzoUnitario", target = "prezzoUnitario")
	@Mapping(source = "cpassDAliquoteIva", target = "aliquoteIva")
	@Mapping(source = "cpassDCpv", target = "cpv")
	@Mapping(source = "cpassDUnitaMisura", target = "unitaMisura")
	@Mapping(source = "cpassTListinoFornitores", target = "listinoFornitores")
	@Mapping(source = "quantitaMaxRichiedibile", target = "quantitaMaxRichiedibile")
	@Mapping(source = "cpassROdsDatiContabilis", target = "odsDatiContabilis")
	@Mapping(source = "cpassTEnte", target = "ente")
	@Mapping(source = "cpassTOrdRigaEvasiones", target = "rigaEvasiones")
	@Mapping(source = "cpassTOrdRigaOrdines", target = "rigaOrdines")
	@Mapping(source = "cpassTOrdRigaRdas", target = "rigaRdas")
	@Mapping(source = "cpassTRmsRigaRms", target = "rigaRms")
	Ods toModel(CpassDOggettiSpesa entity);

	@Override
	@IterableMapping(elementTargetType = Ods.class)
	List<Ods> toModels(Collection<CpassDOggettiSpesa> entities);

	@Override
	@InheritInverseConfiguration(name="toModel")
	//@Mapping(target = "cpassTPbaInterventos", ignore = true)
	CpassDOggettiSpesa toEntity(Ods model);

	@Override
	@IterableMapping(elementTargetType = CpassDOggettiSpesa.class)
	List<CpassDOggettiSpesa> toEntities(Collection<Ods> models);
}
