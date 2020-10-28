/*-
 * ========================LICENSE_START=================================
 * CPASS BackEnd - INTEGRATION submodule - SIAC
 * %%
 * Copyright (C) 2019 - 2020 CSI Piemonte
 * %%
 * SPDX-FileCopyrightText: Copyright 2019 - 2020 | CSI Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 * =========================LICENSE_END==================================
 */
package it.csi.cpass.cpassbe.lib.mapper;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import it.csi.cpass.cpassbe.lib.dto.Subimpegno;
import it.csi.cpass.cpassbe.lib.mapper.annotation.TrimmedString;
import it.csi.siac.integ.data._1.SubImpegno;

/**
 * Mapper between Programma and CpassTPbaProgramma
 */
@Mapper(uses = {
	StringMapper.class
})
public interface SubimpegnoSiacMapper extends BaseMapperInterface<Subimpegno, SubImpegno> {

	@Override
	@Mapping(target = "annoEsercizio", expression = "java( java.util.Calendar.getInstance().get(java.util.Calendar.YEAR) )")
	@Mapping(source = "annoSubImpegno", target = "anno")
	@Mapping(source = "numeroSubImpegno", target = "numero")
	@Mapping(source = "descrizione", target = "descrizione", qualifiedBy = TrimmedString.class)
	@Mapping(source = "stato.codice", target = "stato", qualifiedBy = TrimmedString.class)
	@Mapping(source = "importo", target = "importoAttuale")
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
	@Mapping(target = "impegno", ignore = true)
	Subimpegno toModel(it.csi.siac.integ.data._1.SubImpegno entity);

	@Override
	@IterableMapping(elementTargetType = Subimpegno.class)
	List<Subimpegno> toModels(Collection<SubImpegno> entities);

	@Override
	@InheritInverseConfiguration(name="toModel")
	SubImpegno toEntity(Subimpegno model);

	@Override
	@IterableMapping(elementTargetType = SubImpegno.class)
	List<SubImpegno> toEntities(Collection<Subimpegno> models);
}
