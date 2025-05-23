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
package it.csi.cpass.cpassbe.ejb.mapper.ord;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import it.csi.cpass.cpassbe.ejb.entity.ord.CpassTOrdRigaOrdine;
import it.csi.cpass.cpassbe.ejb.mapper.AliquoteIvaMapper;
import it.csi.cpass.cpassbe.ejb.mapper.CpvMapper;
import it.csi.cpass.cpassbe.ejb.mapper.ListinoFornitoreMapper;
import it.csi.cpass.cpassbe.ejb.mapper.StatoMapper;
import it.csi.cpass.cpassbe.ejb.mapper.StringMapper;
import it.csi.cpass.cpassbe.ejb.mapper.UnitaMisuraMapper;
import it.csi.cpass.cpassbe.ejb.mapper.UtenteMapper;
import it.csi.cpass.cpassbe.lib.dto.ord.RigaOrdine;
import it.csi.cpass.cpassbe.lib.mapper.BaseMapperInterface;

@Mapper(uses = { UtenteMapper.class, StringMapper.class, AliquoteIvaMapper.class, CpvMapper.class, OggettiSpesaMapper.class,
		StatoMapper.class, UnitaMisuraMapper.class, DestinatarioOrdineMapper.class,ListinoFornitoreMapper.class })
public interface RigaOrdineMapper extends BaseMapperInterface<RigaOrdine, CpassTOrdRigaOrdine> {

	/* dani 16/07 da scommentare se si presenta un errore su listino fornitore in modifica della riga
	@BeforeMapping
	default
	void checkPropertyNullBefore(RigaOrdine model,  @MappingTarget CpassTOrdRigaOrdine entity) {
        if (model.getListinoFornitore() != null && model.getListinoFornitore().getId() == null) {
        	model.setListinoFornitore(null);
        }
    }*/

	@Override
	@Mapping(source = "rigaOrdineId", target = "id")
	@Mapping(source = "consegnaParziale", target = "consegnaParziale")
	@Mapping(source = "importoNetto", target = "importoNetto")
	@Mapping(source = "importoIva", target = "importoIva")
	@Mapping(source = "importoSconto", target = "importoSconto")
	@Mapping(source = "importoSconto2", target = "importoSconto2")
	@Mapping(source = "importoTotale", target = "importoTotale")
	@Mapping(source = "percentualeSconto", target = "percentualeSconto")
	@Mapping(source = "percentualeSconto2", target = "percentualeSconto2")
	@Mapping(source = "prezzoUnitario", target = "prezzoUnitario")
	@Mapping(source = "progressivo", target = "progressivo")
	@Mapping(source = "quantita", target = "quantita")
	@Mapping(source = "cpassDAliquoteIva", target = "aliquoteIva")
	@Mapping(source = "cpassDOggettiSpesa", target = "ods")
	@Mapping(source = "cpassDStato", target = "stato")
	@Mapping(source = "cpassDUnitaMisura", target = "unitaMisura")
	@Mapping(source = "cpassTOrdDestinatario", target = "destinatario")
	@Mapping(source = "cpassTListinoFornitore", target = "listinoFornitore")
	@Mapping(source = "note", target = "note")
	RigaOrdine toModel(CpassTOrdRigaOrdine entity);

	@Override
	@InheritInverseConfiguration(name = "toModel")
	CpassTOrdRigaOrdine toEntity(RigaOrdine model);

	CpassTOrdRigaOrdine cloneToEntity(CpassTOrdRigaOrdine entity);

	RigaOrdine cloneToModel(RigaOrdine model);


}
