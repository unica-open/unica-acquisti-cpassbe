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

import it.csi.cpass.cpassbe.ejb.entity.pba.CpassTPbaAcquistiDaTrasmettere;
import it.csi.cpass.cpassbe.lib.dto.pba.AcquistiDaTrasmettere;
import it.csi.cpass.cpassbe.lib.mapper.BaseMapperInterface;

/**
 * Mapper between AcquistiDaTrasmettere and CpassTPbaAcquistiDaTrasmettere
 */
@Mapper
public interface AcquistiDaTrasmettereMapper extends BaseMapperInterface<AcquistiDaTrasmettere, CpassTPbaAcquistiDaTrasmettere> {

	@Override
	AcquistiDaTrasmettere toModel(CpassTPbaAcquistiDaTrasmettere entity);

	@Override
	@IterableMapping(elementTargetType = AcquistiDaTrasmettere.class)
	List<AcquistiDaTrasmettere> toModels(Collection<CpassTPbaAcquistiDaTrasmettere> entities);

	@Override
	@InheritInverseConfiguration(name="toModel")
	CpassTPbaAcquistiDaTrasmettere toEntity(AcquistiDaTrasmettere model);

	@Override
	@IterableMapping(elementTargetType = CpassTPbaAcquistiDaTrasmettere.class)
	List<CpassTPbaAcquistiDaTrasmettere> toEntities(Collection<AcquistiDaTrasmettere> models);

	CpassTPbaAcquistiDaTrasmettere cloneToEntity(CpassTPbaAcquistiDaTrasmettere entity);

	AcquistiDaTrasmettere cloneToModel(AcquistiDaTrasmettere model);


}
