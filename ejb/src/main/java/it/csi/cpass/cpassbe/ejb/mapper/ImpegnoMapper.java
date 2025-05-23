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

import org.mapstruct.BeforeMapping;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import it.csi.cpass.cpassbe.ejb.entity.CpassTImpegno;
import it.csi.cpass.cpassbe.lib.dto.Impegno;
import it.csi.cpass.cpassbe.lib.mapper.BaseMapperInterface;

/**
 * Mapper between Impegno and CpassTImpegno
 */
@Mapper(uses = { StringMapper.class, FornitoreMapper.class, EnteMapper.class })
public interface ImpegnoMapper extends BaseMapperInterface<Impegno, CpassTImpegno> {

	@BeforeMapping
	default
	void checkPropertyNullBefore(Impegno model,  @MappingTarget CpassTImpegno entity) {
		if (model.getFornitore() != null && model.getFornitore().getId() == null && model.getFornitore().getCodice() == null) {
			model.setFornitore(null);
		}
	}

	@Override
	@Mapping(source = "descrizioneCapitolo", target = "descrizioneCapitolo")
	@Mapping(source = "impegnoAnno", target = "anno")
	@Mapping(source = "impegnoAnnoEsercizio", target = "annoEsercizio")
	@Mapping(source = "impegnoDescrizione", target = "descrizione")
	@Mapping(source = "impegnoNumero", target = "numero")
	@Mapping(source = "importoAttuale", target = "importoAttuale")
	@Mapping(source = "importoIniziale", target = "importoIniziale")
	@Mapping(source = "liqAnnoPrec", target = "liquidatoAnnoPrecedente")
	@Mapping(source = "numeroArticolo", target = "numeroArticolo")
	@Mapping(source = "numeroCapitolo", target = "numeroCapitolo")
	@Mapping(source = "provvedimentoAnno", target = "annoProvvedimento")
	@Mapping(source = "provvedimentoNumero", target = "numeroProvvedimento")
	@Mapping(source = "provvedimentoSettore", target = "settoreProvvedimento")

	//	La lista di subimpegni è valorizzata in modo programmatico all'occorrenza, mappare tramite mapstruct duplicherebbe gli elementi , quindi l'eventuale utilizzo della mappatura richiederebbe una rivisitazione
	//  corposa del codice scritto.
	//	@Mapping(source = "cpassTSubimpegnos", target = "subimpegni")

	@Mapping(source = "stato", target = "stato")
	@Mapping(source = "cpassTEnte", target = "ente")
	@Mapping(source = "cpassTFornitore", target = "fornitore")


	Impegno toModel(CpassTImpegno entity);

	@Override
	@IterableMapping(elementTargetType = Impegno.class)
	List<Impegno> toModels(Collection<CpassTImpegno> entities);

	@Override
	@InheritInverseConfiguration(name="toModel")
	CpassTImpegno toEntity(Impegno model);

	@Override
	@IterableMapping(elementTargetType = CpassTImpegno.class)
	List<CpassTImpegno> toEntities(Collection<Impegno> models);

}
