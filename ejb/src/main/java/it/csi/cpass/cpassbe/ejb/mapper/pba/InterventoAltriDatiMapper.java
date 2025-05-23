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

import org.mapstruct.BeforeMapping;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import it.csi.cpass.cpassbe.ejb.entity.pba.CpassTPbaInterventoAltriDati;
import it.csi.cpass.cpassbe.ejb.mapper.CpvMapper;
import it.csi.cpass.cpassbe.lib.dto.pba.InterventoAltriDati;
import it.csi.cpass.cpassbe.lib.mapper.BaseMapperInterface;

/**
 * Mapper between  InterventoAltriDati  and CpassTPbaInterventoAltriDati
 */

@Mapper(uses = {CpvMapper.class, TipoAcquistoMapper.class})
public interface InterventoAltriDatiMapper extends BaseMapperInterface<InterventoAltriDati, CpassTPbaInterventoAltriDati> {

	@BeforeMapping
	default
	void checkPropertyNullBefore(InterventoAltriDati model,  @MappingTarget CpassTPbaInterventoAltriDati entity) {
		if (model.getCpvMatRic() != null && model.getCpvMatRic().getId() == null && model.getCpvMatRic().getCodice() == null) {
			model.setCpvMatRic(null);
		}
		if (model.getCpvVerdi() != null && model.getCpvVerdi().getId() == null && model.getCpvVerdi().getCodice() == null) {
			model.setCpvVerdi(null);
		}
	}


	@Override
	@Mapping(source = "cpassDCpvMatRic", target = "cpvMatRic")
	@Mapping(source = "cpassDCpvVerdi", target = "cpvVerdi")
	@Mapping(source = "cpassTPbaIntervento", target = "intervento")
	@Mapping(source = "cpassDPbaTipoAcquistoMatRic", target = "tipoAcquistoMatRic")
	@Mapping(source = "cpassDPbaTipoAcquistoVerdi", target = "tipoAcquistoVerdi")
	@Mapping(source = "cpassDMotiviEsclusioneCig", target = "motiviEsclusioneCig")

	InterventoAltriDati toModel(CpassTPbaInterventoAltriDati entity);

	@Override
	@IterableMapping(elementTargetType = InterventoAltriDati.class)
	List<InterventoAltriDati> toModels(Collection<CpassTPbaInterventoAltriDati> entities);

	@Override
	@InheritInverseConfiguration(name="toModel")
	CpassTPbaInterventoAltriDati toEntity(InterventoAltriDati model);

	@Override
	@IterableMapping(elementTargetType = CpassTPbaInterventoAltriDati.class)
	List<CpassTPbaInterventoAltriDati> toEntities(Collection<InterventoAltriDati> models);



}
