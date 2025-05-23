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
package it.csi.cpass.cpassbe.ejb.mapper;

import java.util.Collection;
import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import it.csi.cpass.cpassbe.ejb.entity.CpassTEnte;
import it.csi.cpass.cpassbe.lib.dto.Ente;
import it.csi.cpass.cpassbe.lib.mapper.BaseMapperInterface;

/**
 * Mapper between Ente and CpassTEnte
 */
@Mapper
public interface EnteMapper extends BaseMapperInterface<Ente, CpassTEnte> {

	@Override
	@Mapping(source = "enteCodiceFiscale", target = "codiceFiscale")
	@Mapping(source = "enteCodice", target = "codice")
	@Mapping(source = "enteDenominazione", target = "denominazione")
	@Mapping(source = "codiceIpaAmministrazione", target = "codiceIpaAmministrazione")
	@Mapping(source = "dipartimento", target = "dipartimento")
	@Mapping(source = "ufficio", target = "ufficio")
	@Mapping(source = "regione", target = "regione")
	@Mapping(source = "provincia", target = "provincia")
	@Mapping(source = "indirizzo", target = "indirizzo")
	@Mapping(source = "telefono", target = "telefono")
	@Mapping(source = "email", target = "email")
	@Mapping(source = "emailpec", target = "emailPEC")
	@Mapping(source = "pathLogo", target = "pathLogo")
	@Mapping(source = "link", target = "link")
	Ente toModel(CpassTEnte entity);

	@Override
	@IterableMapping(elementTargetType = Ente.class)
	List<Ente> toModels(Collection<CpassTEnte> entities);

	@Override
	@IterableMapping(elementTargetType = CpassTEnte.class)
	List<CpassTEnte> toEntities(Collection<Ente> models);

	@Override
	@InheritInverseConfiguration(name = "toModel")
	@Mapping(target = "cpassTPbaProgrammas", ignore = true)
	CpassTEnte toEntity(Ente model);

}
