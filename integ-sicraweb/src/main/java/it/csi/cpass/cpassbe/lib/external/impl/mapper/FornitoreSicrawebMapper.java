/*-
 * ========================LICENSE_START=================================
 * CPASS BackEnd - INTEGRATION submodule - SIAC
 * %%
 * Copyright (C) 2019 - 2025 CSI Piemonte
 * %%
 * SPDX-FileCopyrightText: Copyright 2019 - 2020 | CSI Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 * =========================LICENSE_END==================================
 */
package it.csi.cpass.cpassbe.lib.external.impl.mapper;

import java.util.Collection;
import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import fdewsappjricercagateway.Soggetto;
import it.csi.cpass.cpassbe.lib.dto.Fornitore;
import it.csi.cpass.cpassbe.lib.mapper.BaseMapperInterface;
import it.csi.cpass.cpassbe.lib.mapper.annotation.TrimmedString;

/**
 * Mapper between Fornitore and Soggetto SIAC
 */
@Mapper(uses = {
		ModalitaPagamentoSicrawebMapper.class, StringMapper.class
})
public interface FornitoreSicrawebMapper extends BaseMapperInterface<Fornitore, Soggetto> {

	@Override
	@Mapping(source = "naturaGiuridica.codice", target = "naturaGiuridica", qualifiedBy = TrimmedString.class)
	@Mapping(source = "codice", target = "codice", qualifiedBy = TrimmedString.class)
	@Mapping(source = "ragioneSociale", target = "ragioneSociale", qualifiedBy = TrimmedString.class)
	@Mapping(source = "cognome", target = "cognome", qualifiedBy = TrimmedString.class)
	@Mapping(source = "nome", target = "nome", qualifiedBy = TrimmedString.class)
	@Mapping(source = "codiceFiscale", target = "codiceFiscale", qualifiedBy = TrimmedString.class)
	@Mapping(source = "codiceFiscaleEstero", target = "codiceFiscaleEstero", qualifiedBy = TrimmedString.class)
	@Mapping(source = "partitaIva", target = "partitaIva", qualifiedBy = TrimmedString.class)
	@Mapping(source = "indirizzoPrincipale.sedime", target = "sedime", qualifiedBy = TrimmedString.class)
	@Mapping(source = "indirizzoPrincipale.indirizzo", target = "indirizzo", qualifiedBy = TrimmedString.class)
	@Mapping(source = "indirizzoPrincipale.numeroCivico", target = "numeroCivico", qualifiedBy = TrimmedString.class)
	@Mapping(source = "indirizzoPrincipale.cap", target = "cap", qualifiedBy = TrimmedString.class)
	@Mapping(source = "indirizzoPrincipale.comune", target = "comune", qualifiedBy = TrimmedString.class)
	@Mapping(source = "indirizzoPrincipale.provincia", target = "provincia", qualifiedBy = TrimmedString.class)
	@Mapping(source = "stato.codice", target = "stato", qualifiedBy = TrimmedString.class)
	@Mapping(source = "codiciSoggettiCollegatiSuccessivi", target = "codiciFornitoriCollegatiSuccessivi")
	@Mapping(source = "elencoModalitaPagamento", target = "elencoModalitaPagamento")
	Fornitore toModel(Soggetto entity);
	
	@Override
	@IterableMapping(elementTargetType = Fornitore.class)
	List<Fornitore> toModels(Collection<Soggetto> entities);

	@Override
	@InheritInverseConfiguration(name="toModel")
	Soggetto toEntity(Fornitore model);

	@Override
	@IterableMapping(elementTargetType = Soggetto.class)
	List<Soggetto> toEntities(Collection<Fornitore> models);
}
