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
package it.csi.cpass.cpassbe.ejb.mapper.ord.evasione;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import it.csi.cpass.cpassbe.ejb.entity.ord.evasione.CpassTOrdRigaEvasione;
import it.csi.cpass.cpassbe.ejb.mapper.ListinoFornitoreMapper;
import it.csi.cpass.cpassbe.ejb.mapper.SettoreCustomMapper;
import it.csi.cpass.cpassbe.ejb.mapper.StatoMapper;
import it.csi.cpass.cpassbe.ejb.mapper.StringMapper;
import it.csi.cpass.cpassbe.ejb.mapper.ord.OggettiSpesaMapper;
import it.csi.cpass.cpassbe.ejb.mapper.ord.RigaOrdineMapper;
import it.csi.cpass.cpassbe.lib.dto.ord.evasione.RigaEvasione;
import it.csi.cpass.cpassbe.lib.mapper.BaseMapperInterface;

//@Mapper(uses = { StringMapper.class, TestataEvasioneMapper.class, SettoreMapper.class, DestinatarioOrdineMapper.class,
//		StatoMapper.class, OggettiSpesaMapper.class, RigaOrdineMapper.class, ListinoFornitoreMapper.class })
@Mapper(uses = { StringMapper.class, TestataEvasioneMapper.class, SettoreCustomMapper.class, DestinatarioEvasioneMapper.class,
		StatoMapper.class, OggettiSpesaMapper.class, RigaOrdineMapper.class, ListinoFornitoreMapper.class })
public interface RigaEvasioneMapper extends BaseMapperInterface<RigaEvasione, CpassTOrdRigaEvasione> {

	@Override
	@Mapping(source = "progressivo", target = "progressivo")
	@Mapping(source = "cpassDAliquoteIva", target = "aliquoteIva")
	@Mapping(source = "cpassDOggettiSpesa", target = "oggettiSpesa")
	@Mapping(source = "cpassDStato", target = "stato")
	@Mapping(source = "cpassTListinoFornitore", target = "listinoFornitore")
	@Mapping(source = "cpassTOrdDestinatarioEvasione", target = "destinatarioEvasione")
	@Mapping(source = "cpassTOrdRigaOrdine", target = "rigaOrdine")
	@Mapping(source = "quantitaEvasa", target = "quantitaEvasa")
	RigaEvasione toModel(CpassTOrdRigaEvasione entity);

	@Override
	@InheritInverseConfiguration(name = "toModel")
	CpassTOrdRigaEvasione toEntity(RigaEvasione model);

	CpassTOrdRigaEvasione cloneToEntity(CpassTOrdRigaEvasione entity);

	RigaEvasione cloneToModel(RigaEvasione model);

}
