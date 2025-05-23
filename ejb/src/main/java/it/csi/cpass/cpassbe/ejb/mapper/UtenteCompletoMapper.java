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

import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import it.csi.cpass.cpassbe.ejb.entity.CpassTUtente;
import it.csi.cpass.cpassbe.lib.dto.Utente;
import it.csi.cpass.cpassbe.lib.mapper.BaseMapperInterface;

/**
 * Mapper between Ente and CpassTEnte
 */
@Mapper(uses = { StringMapper.class, UtenteRupDelegheMapper.class, UtenteSettoreMapper.class })
public interface UtenteCompletoMapper extends BaseMapperInterface<Utente, CpassTUtente> {

	@Override
	@Mapping(source = "utenteNome", target = "nome")
	@Mapping(source = "utenteCognome", target = "cognome")
	@Mapping(source = "utenteCodiceFiscale", target = "codiceFiscale")
	@Mapping(source = "email", target = "email")
	@Mapping(source = "telefono", target = "telefono")
	@Mapping(source = "cpassRUtenteSettores", target = "utenteSettores")
	@Mapping(source = "cpassTUtenteRupDeleghes", target = "utenteRupDeleghes")

	Utente toModel(CpassTUtente entity);

	@Override
	@IterableMapping(elementTargetType = Utente.class)
	List<Utente> toModels(Collection<CpassTUtente> entities);

	@Override
	@IterableMapping(elementTargetType = CpassTUtente.class)
	List<CpassTUtente> toEntities(Collection<Utente> models);

}
