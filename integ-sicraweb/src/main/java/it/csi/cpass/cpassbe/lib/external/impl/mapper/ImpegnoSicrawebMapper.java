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

import it.csi.cpass.cpassbe.lib.dto.Impegno;
import it.csi.cpass.cpassbe.lib.mapper.BaseMapperInterface;
import it.csi.cpass.cpassbe.lib.mapper.annotation.TrimmedString;

/**
 * Mapper between Impegno and "Impegno SICRAWEB"
 */
@Mapper(uses = {
	SubimpegnoSicrawebMapper.class, StringMapper.class
})
public interface ImpegnoSicrawebMapper extends BaseMapperInterface<Impegno, fdewsappjricercagateway.Impegno> {   

	@Override
	@Mapping(target = "annoEsercizio", expression = "java( java.util.Calendar.getInstance().get(java.util.Calendar.YEAR) )")
	@Mapping(source = "annoImpegno", target = "anno")
	@Mapping(source = "numeroImpegno", target = "numero")
	@Mapping(source = "descrizione", target = "descrizione", qualifiedBy = TrimmedString.class)
	@Mapping(source = "stato.codice", target = "stato", qualifiedBy = TrimmedString.class)
	@Mapping(source = "importo", target = "importoAttuale")
	//@Mapping(source = "importo", target = "importo")
	@Mapping(target = "importo", expression = "java( new java.math.BigDecimal(0) )")
	@Mapping(target = "importoIniziale", expression = "java( new java.math.BigDecimal(0) )")
	@Mapping(source = "disponibilitaLiquidare", target = "disponibilitaLiquidare")
	@Mapping(target = "liquidatoAnnoPrecedente", expression = "java( new java.math.BigDecimal(0) )")
	@Mapping(source = "numeroCapitolo", target = "numeroCapitolo")
	@Mapping(source = "numeroArticolo", target = "numeroArticolo")
	@Mapping(target = "descrizioneCapitolo", ignore = true)
	@Mapping(source = "provvedimento.annoProvvedimento", target = "annoProvvedimento")
	@Mapping(source = "provvedimento.numeroProvvedimento", target = "numeroProvvedimento")
	@Mapping(source = "provvedimento.sac.codice", target = "settoreProvvedimento", qualifiedBy = TrimmedString.class)
	@Mapping(target = "ente", ignore = true)
	@Mapping(source = "codiceSoggetto", target = "fornitore.codice", qualifiedBy = TrimmedString.class)
	@Mapping(source = "subImpegni", target = "subimpegni")
	@Mapping(source = "pdc.codice", target = "pdcCodice", qualifiedBy = TrimmedString.class)
	@Mapping(source = "pdc.descrizione", target = "pdcDescrizione", qualifiedBy = TrimmedString.class)
	@Mapping(source = "annoImpegnoRiaccertato", target = "annoImpegnoRiaccertato")
	@Mapping(source = "numImpegnoRiaccertato", target = "numImpegnoRiaccertato")
	@Mapping(source = "cig", target = "cig")
	Impegno toModel(fdewsappjricercagateway.Impegno entity);

	@Override
	@IterableMapping(elementTargetType = Impegno.class)
	List<Impegno> toModels(Collection<fdewsappjricercagateway.Impegno> entities);

	@Override
	@InheritInverseConfiguration(name="toModel")
	fdewsappjricercagateway.Impegno toEntity(Impegno model);

	@Override
	@IterableMapping(elementTargetType = fdewsappjricercagateway.Impegno.class)
	List<fdewsappjricercagateway.Impegno> toEntities(Collection<Impegno> models);
}
