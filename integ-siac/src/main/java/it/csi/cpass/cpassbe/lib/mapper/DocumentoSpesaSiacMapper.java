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

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;

import it.csi.cpass.cpassbe.lib.dto.DocumentoSpesa;
import it.csi.siac.ricerche.data._1.Ordine;

/**
 * Mapper between DocumentoSpesa and "DocumentoSpesa SIAC"
 */
/*
@Mapper(uses = {
	SubimpegnoSiacMapper.class, StringMapper.class
})
public interface DocumentoSpesaSiacMapper extends BaseMapperInterface<DocumentoSpesa, it.csi.siac.ricerche.data._1.DocumentoSpesa> {

	@Override
	@Mapping(source = "annoRepertorio", target = "annoProtocollo")
	@Mapping(source = "numeroRepertorio", target = "numeroProtocollo", qualifiedBy = TrimmedString.class)
	@Mapping(source = "registroRepertorio", target = "registroProtocollo", qualifiedBy = TrimmedString.class)
	@Mapping(source = "annoDocumento", target = "annoDocumento")
	@Mapping(source = "numeroDocumento", target = "numeroDocumento", qualifiedBy = TrimmedString.class)
	@Mapping(source = "tipoDocumento", target = "tipoDocumento", qualifiedBy = TrimmedString.class)
	@Mapping(source = "codiceSoggetto", target = "codiceFornitore", qualifiedBy = TrimmedString.class)
	@Mapping(source = "stato.codice", target = "codiceStato", qualifiedBy = TrimmedString.class)
	@Mapping(source = "stato.descrizione", target = "descrizioneStato", qualifiedBy = TrimmedString.class)
	@Mapping(source = "importoDocumento", target = "totaleDocumento")
	@Mapping(source = "importoNettoDocumento", target = "totaleLiquidabileDocumento")
	@Mapping(target = "numeriOrdine", ignore = true)
	DocumentoSpesa toModel(it.csi.siac.ricerche.data._1.DocumentoSpesa entity);

	@Override
	@IterableMapping(elementTargetType = DocumentoSpesa.class)
	List<DocumentoSpesa> toModels(Collection<it.csi.siac.ricerche.data._1.DocumentoSpesa> entities);

	@Override
	@InheritInverseConfiguration(name="toModel")
	it.csi.siac.ricerche.data._1.DocumentoSpesa toEntity(DocumentoSpesa model);

	@Override
	@IterableMapping(elementTargetType = it.csi.siac.ricerche.data._1.DocumentoSpesa.class)
	List<it.csi.siac.ricerche.data._1.DocumentoSpesa> toEntities(Collection<DocumentoSpesa> models);
}
*/
@Mapper
public abstract class DocumentoSpesaSiacMapper implements BaseMapperInterface<DocumentoSpesa, it.csi.siac.ricerche.data._1.DocumentoSpesa> {
	@Override
	public DocumentoSpesa toModel(it.csi.siac.ricerche.data._1.DocumentoSpesa entity) {
		if (entity == null) {
			return null;
		}
		DocumentoSpesa ds = new DocumentoSpesa();
		ds.setAnnoProtocollo(entity.getAnnoRepertorio());
		ds.setNumeroProtocollo(entity.getNumeroRepertorio());
		ds.setRegistroProtocollo(entity.getRegistroRepertorio());
		ds.setAnnoDocumento(entity.getAnnoDocumento());
		ds.setNumeroDocumento(entity.getNumeroDocumento());
		ds.setTipoDocumento(entity.getTipoDocumento());
		ds.setCodiceFornitore(entity.getCodiceSoggetto());
		ds.setCodiceStato(entity.getStato().getCodice());
		ds.setDescrizioneStato(entity.getStato().getDescrizione());
		ds.setTotaleDocumento(entity.getImportoDocumento());
		ds.setTotaleLiquidabileDocumento(entity.getImportoNettoDocumento());

		List<String> numeriOrdine = new ArrayList<>();
		for(Ordine ordine : entity.getElencoOrdini()) {
			numeriOrdine.add(ordine.getNumeroOrdine());
		}
		
		ds.setNumeriOrdine(numeriOrdine);		
		return ds;
	}

	@Override
	@IterableMapping(elementTargetType = DocumentoSpesa.class)
	public abstract List<DocumentoSpesa> toModels(Collection<it.csi.siac.ricerche.data._1.DocumentoSpesa> entities);

	@Override
	@IterableMapping(elementTargetType = it.csi.siac.ricerche.data._1.DocumentoSpesa.class)
	public abstract List<it.csi.siac.ricerche.data._1.DocumentoSpesa> toEntities(Collection<DocumentoSpesa> models);

	
	@Override
	public it.csi.siac.ricerche.data._1.DocumentoSpesa toEntity(DocumentoSpesa model) {
		if (model == null) {
			return null;
		}
		it.csi.siac.ricerche.data._1.DocumentoSpesa documentoSpesaSiac = new it.csi.siac.ricerche.data._1.DocumentoSpesa();
		//TODO da fare se serve
		return documentoSpesaSiac;
	}
}
