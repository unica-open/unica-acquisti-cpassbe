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

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import it.csi.cpass.cpassbe.ejb.entity.pba.CpassDPbaTipoAcquisto;
import it.csi.cpass.cpassbe.lib.dto.pba.TipoAcquisto;
import it.csi.cpass.cpassbe.lib.mapper.BaseMapperInterface;

/**
 * Mapper between Ausa and CpassDPbaAusa
 */
@Mapper
public interface TipoAcquistoMapper extends BaseMapperInterface<TipoAcquisto, CpassDPbaTipoAcquisto> {

	@Override
	@Mapping(source = "tipoAcquistoCodice", target = "codice")
	@Mapping(source = "tipoAcquistoDescrizione", target = "descrizione")
	@Mapping(source = "tipoAcquistoDefault", target = "flgdefault")
	TipoAcquisto toModel(CpassDPbaTipoAcquisto entity);

	@Override
	@IterableMapping(elementTargetType = TipoAcquisto.class)
	List<TipoAcquisto> toModels(Collection<CpassDPbaTipoAcquisto> entities);

	@Override
	@InheritInverseConfiguration(name="toModel")
	CpassDPbaTipoAcquisto toEntity(TipoAcquisto model);

	@Override
	@IterableMapping(elementTargetType = CpassDPbaTipoAcquisto.class)
	List<CpassDPbaTipoAcquisto> toEntities(Collection<TipoAcquisto> models);

}
