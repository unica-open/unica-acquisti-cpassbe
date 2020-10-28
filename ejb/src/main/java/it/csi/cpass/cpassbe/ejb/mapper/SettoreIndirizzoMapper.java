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

import it.csi.cpass.cpassbe.ejb.entity.CpassTSettore;
import it.csi.cpass.cpassbe.ejb.entity.ord.CpassTSettoreIndirizzo;
import it.csi.cpass.cpassbe.lib.dto.Settore;
import it.csi.cpass.cpassbe.lib.dto.SettoreIndirizzo;
import it.csi.cpass.cpassbe.lib.mapper.BaseMapperInterface;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;

import java.util.Collection;
import java.util.List;

@Mapper
public abstract class SettoreIndirizzoMapper implements BaseMapperInterface<SettoreIndirizzo, CpassTSettoreIndirizzo> {

	@Override
	public SettoreIndirizzo toModel(CpassTSettoreIndirizzo entity) {
		if (entity == null) {
			return null;
		}
		SettoreIndirizzo settoreIndirizzo = new SettoreIndirizzo();
		settoreIndirizzo.setIndirizzo(entity.getIndirizzo());
		settoreIndirizzo.setNumCivico(entity.getNumCivico());
		settoreIndirizzo.setLocalita(entity.getLocalita());
		settoreIndirizzo.setProvincia(entity.getProvincia());
		settoreIndirizzo.setCap(entity.getCap());
		settoreIndirizzo.setContatto(entity.getContatto());
		settoreIndirizzo.setEmail(entity.getEmail());
		settoreIndirizzo.setTelefono(entity.getTelefono());
		settoreIndirizzo.setSettore(CpassMappers.SETTORE.toModel(entity.getCpassTSettore()));

		return settoreIndirizzo;
	}

	@Override
	@IterableMapping(elementTargetType = Settore.class)
	public abstract List<SettoreIndirizzo> toModels(Collection<CpassTSettoreIndirizzo> entities);

	@Override
	@IterableMapping(elementTargetType = CpassTSettore.class)
	public abstract List<CpassTSettoreIndirizzo> toEntities(Collection<SettoreIndirizzo> models);


	@Override
	public CpassTSettoreIndirizzo toEntity(SettoreIndirizzo model) {
		if (model == null) {
			return null;
		}

		CpassTSettoreIndirizzo cpassTSettoreIndirizzo = new CpassTSettoreIndirizzo();
		cpassTSettoreIndirizzo.setIndirizzo(model.getIndirizzo());
		cpassTSettoreIndirizzo.setNumCivico(model.getNumCivico());
		cpassTSettoreIndirizzo.setLocalita(model.getLocalita());
		cpassTSettoreIndirizzo.setProvincia(model.getProvincia());
		cpassTSettoreIndirizzo.setCap(model.getCap());
		cpassTSettoreIndirizzo.setContatto(model.getContatto());
		cpassTSettoreIndirizzo.setEmail(model.getEmail());
		cpassTSettoreIndirizzo.setTelefono(model.getTelefono());

		return cpassTSettoreIndirizzo;
	}

}
