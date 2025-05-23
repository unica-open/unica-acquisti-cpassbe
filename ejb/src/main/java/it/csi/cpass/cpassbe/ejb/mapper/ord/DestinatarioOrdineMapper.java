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

import org.mapstruct.BeforeMapping;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import it.csi.cpass.cpassbe.ejb.entity.ord.CpassTOrdDestinatarioOrdine;
import it.csi.cpass.cpassbe.ejb.mapper.SettoreCustomMapper;
import it.csi.cpass.cpassbe.ejb.mapper.SettoreIndirizzoReverseMapper;
import it.csi.cpass.cpassbe.ejb.mapper.StatoMapper;
import it.csi.cpass.cpassbe.ejb.mapper.StringMapper;
import it.csi.cpass.cpassbe.lib.dto.ord.Destinatario;
import it.csi.cpass.cpassbe.lib.mapper.BaseMapperInterface;

@Mapper(uses = { StringMapper.class, StatoMapper.class, TestataOrdineMapper.class, SettoreCustomMapper.class, StatoNsoMapper.class, SettoreIndirizzoReverseMapper.class })
public interface DestinatarioOrdineMapper extends BaseMapperInterface<Destinatario, CpassTOrdDestinatarioOrdine> {

	@BeforeMapping
	default
	void checkPropertyNullBefore(Destinatario model,  @MappingTarget CpassTOrdDestinatarioOrdine entity) {
		if (model.getStatoNso() != null && model.getStatoNso().getId() == null && model.getStatoNso().getCodice() == null) {
			model.setStatoNso(null);
		}
	}

	@Override
	@Mapping(source = "progressivo", target = "progressivo")
	@Mapping(source = "cap", target = "cap")
	@Mapping(source = "contatto", target = "contatto")
	@Mapping(source = "dataInvioNso", target = "dataInvioNso")
	@Mapping(source = "email", target = "email")
	@Mapping(source = "indirizzo", target = "indirizzo")
	@Mapping(source = "localita", target = "localita")
	@Mapping(source = "numCivico", target = "numCivico")
	@Mapping(source = "provincia", target = "provincia")
	@Mapping(source = "cpassDOrdStatoNso", target = "statoNso")
	@Mapping(source = "telefono", target = "telefono")
	@Mapping(source = "cpassDStato", target = "stato")
	@Mapping(source = "cpassTOrdTestataOrdine", target = "testataOrdine")
	@Mapping(source = "cpassTSettore", target = "settore")
	@Mapping(source = "settoreIndirizzoCodice", target = "indirizzoCodice")
	@Mapping(source = "cpassTSettoreIndirizzo", target = "settoreIndirizzo")
	Destinatario toModel(CpassTOrdDestinatarioOrdine entity);

	@Override
	@InheritInverseConfiguration(name = "toModel")
	// @Mapping(target = "cpassTOrdDestinatarios", ignore = true, qualifiedBy = Minimal.class )
	CpassTOrdDestinatarioOrdine toEntity(Destinatario model);

	CpassTOrdDestinatarioOrdine cloneToEntity(CpassTOrdDestinatarioOrdine entity);

	Destinatario cloneToModel(Destinatario model);

}
