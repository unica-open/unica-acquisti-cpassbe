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
package it.csi.cpass.cpassbe.ejb.mapper.ord;

import java.util.Collection;
import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import it.csi.cpass.cpassbe.ejb.entity.ord.CpassTOrdTestataOrdine;
import it.csi.cpass.cpassbe.ejb.mapper.ImpegnoMapper;
import it.csi.cpass.cpassbe.ejb.mapper.SettoreMapper;
import it.csi.cpass.cpassbe.ejb.mapper.StatoMapper;
import it.csi.cpass.cpassbe.ejb.mapper.StringMapper;
import it.csi.cpass.cpassbe.ejb.mapper.UfficioMapper;
import it.csi.cpass.cpassbe.ejb.mapper.UtenteMapper;
import it.csi.cpass.cpassbe.lib.dto.ord.TestataOrdine;
import it.csi.cpass.cpassbe.lib.mapper.BaseMapperInterface;
import it.csi.cpass.cpassbe.lib.mapper.annotation.Minimal;

/**
 * Mapper between TestataOrdine and CpassTOrdTestataOrdine
 */
@Mapper(uses = { UtenteMapper.class, StatoMapper.class, StringMapper.class, TipoOrdineMapper.class, TipoProceduraMapper.class, UfficioMapper.class,
		SettoreMapper.class, ImpegnoMapper.class, StatoNsoMapper.class })
public interface TestataOrdineMapper extends BaseMapperInterface<TestataOrdine, CpassTOrdTestataOrdine> {

	@Override
	@Mapping(source = "consegnaCap", target = "consegnaCap")
	@Mapping(source = "consegnaDataA", target = "consegnaDataA")
	@Mapping(source = "consegnaDataDa", target = "consegnaDataDa")
	@Mapping(source = "consegnaIndirizzo", target = "consegnaIndirizzo")
	@Mapping(source = "consegnaLocalita", target = "consegnaLocalita")
	@Mapping(source = "consegnaRiferimento", target = "consegnaRiferimento")
	@Mapping(source = "dataAutorizzazione", target = "dataAutorizzazione")
	@Mapping(source = "dataConferma", target = "dataConferma")
	@Mapping(source = "dataEmissione", target = "dataEmissione")
	@Mapping(source = "dataScadenza", target = "dataScadenza")
	@Mapping(source = "dataAnnullamento", target = "dataAnnullamento")
	@Mapping(source = "descrizioneAcquisto", target = "descrizione")
	@Mapping(source = "note", target = "note")
	@Mapping(source = "cpassTFornitore", target = "fornitore")
	@Mapping(source = "lottoAnno", target = "lottoAnno")
	@Mapping(source = "lottoNumero", target = "lottoNumero")
	@Mapping(source = "ordineAnno", target = "anno")
	@Mapping(source = "ordineNumero", target = "numero")
	
	@Mapping(source = "provvedimentoAnno", target = "provvedimento.anno")
	@Mapping(source = "provvedimentoNumero", target = "provvedimento.numero")

	@Mapping(source = "cpassDOrdStatoNso", target = "statoNso")
	@Mapping(source = "totaleConIva", target = "totaleConIva")
	@Mapping(source = "totaleNoIva", target = "totaleNoIva")
	@Mapping(source = "cpassTUtente", target = "utenteCompilatore")
//	@Mapping(source = "cpassTOrdDestinatarios", target = "listDestinatario", qualifiedBy = Minimal.class)
	@Mapping(source = "cpassDOrdTipoOrdine", target = "tipoOrdine")
	@Mapping(source = "cpassDOrdTipoProcedura", target = "tipoProcedura")
	@Mapping(source = "numeroProcedura", target = "numeroProcedura")
	@Mapping(source = "cpassDStato", target = "stato")
	@Mapping(source = "cpassTSettore", target = "settore")
	@Mapping(source = "cpassTUfficio", target = "ufficio")
	TestataOrdine toModel(CpassTOrdTestataOrdine entity);

	@Override
	@IterableMapping(elementTargetType = TestataOrdine.class)
	List<TestataOrdine> toModels(Collection<CpassTOrdTestataOrdine> entities);

	@Override
	@InheritInverseConfiguration(name = "toModel")
	@Mapping(target = "cpassTOrdDestinatarios", ignore = true ,qualifiedBy = Minimal.class )
	CpassTOrdTestataOrdine toEntity(TestataOrdine model);

	@Override
	@IterableMapping(elementTargetType = CpassTOrdTestataOrdine.class)
	List<CpassTOrdTestataOrdine> toEntities(Collection<TestataOrdine> models);

	CpassTOrdTestataOrdine cloneToEntity(CpassTOrdTestataOrdine entity);

	TestataOrdine cloneToModel(TestataOrdine model);

}
