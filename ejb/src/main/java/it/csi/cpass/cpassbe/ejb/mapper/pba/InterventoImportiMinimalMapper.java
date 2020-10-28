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
package it.csi.cpass.cpassbe.ejb.mapper.pba;

import java.util.Collection;
import java.util.List;

import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import it.csi.cpass.cpassbe.ejb.entity.pba.CpassTPbaInterventoImporti;
import it.csi.cpass.cpassbe.lib.dto.pba.InterventoImporti;
import it.csi.cpass.cpassbe.lib.mapper.BaseMapperInterface;

/**
 * Mapper between InterventoImporti and CpassTPbaIntervento
 * <br>
 */
@Mapper(uses = {RisorsaMapper.class})
public interface InterventoImportiMinimalMapper extends BaseMapperInterface<InterventoImporti, CpassTPbaInterventoImporti> {

	@Override
	@Mapping(source = "interventoImportiImportoAnnoPrimo", target = "importoAnnoPrimo")
	@Mapping(source = "interventoImportiImportoAnnoSecondo", target = "importoAnnoSecondo")
	@Mapping(source = "interventoImportiImportoAnniSuccessivi", target = "importoAnniSuccessivi")
	@Mapping(source = "cpassDPbaRisorsa", target = "risorsa")
	@Mapping(source = "cpassTPbaIntervento", target = "intervento",ignore = true)
	InterventoImporti toModel(CpassTPbaInterventoImporti entity);

	@Override
	@IterableMapping(elementTargetType = InterventoImporti.class)
	List<InterventoImporti> toModels(Collection<CpassTPbaInterventoImporti> entities);
	
	@Override
	@IterableMapping(elementTargetType = CpassTPbaInterventoImporti.class)
	List<CpassTPbaInterventoImporti> toEntities(Collection<InterventoImporti> models);
	
}
