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

import it.csi.cpass.cpassbe.ejb.entity.pba.CpassTPbaAcquistiCapPrivatiDaTrasmettere;
import it.csi.cpass.cpassbe.lib.dto.pba.AcquistiCapPrivatiDaTrasmettere;
import it.csi.cpass.cpassbe.lib.mapper.BaseMapperInterface;

/**
 * Mapper between AcquistiDaTrasmettere and CpassTPbaAcquistiDaTrasmettere
 */
@Mapper
public interface AcquistiCapPrivatiDaTrasmettereMapper extends BaseMapperInterface<AcquistiCapPrivatiDaTrasmettere, CpassTPbaAcquistiCapPrivatiDaTrasmettere> {

	@Override
	AcquistiCapPrivatiDaTrasmettere toModel(CpassTPbaAcquistiCapPrivatiDaTrasmettere entity);

	@Override
	@IterableMapping(elementTargetType = AcquistiCapPrivatiDaTrasmettere.class)
	List<AcquistiCapPrivatiDaTrasmettere> toModels(Collection<CpassTPbaAcquistiCapPrivatiDaTrasmettere> entities);

	@Override
	@InheritInverseConfiguration(name="toModel")
	CpassTPbaAcquistiCapPrivatiDaTrasmettere toEntity(AcquistiCapPrivatiDaTrasmettere model);

	@Override
	@IterableMapping(elementTargetType = CpassTPbaAcquistiCapPrivatiDaTrasmettere.class)
	List<CpassTPbaAcquistiCapPrivatiDaTrasmettere> toEntities(Collection<AcquistiCapPrivatiDaTrasmettere> models);

	CpassTPbaAcquistiCapPrivatiDaTrasmettere cloneToEntity(CpassTPbaAcquistiCapPrivatiDaTrasmettere entity);

	AcquistiCapPrivatiDaTrasmettere cloneToModel(AcquistiCapPrivatiDaTrasmettere model);


}
