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

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;

import fdewsappjricercagateway.Ordine;
import it.csi.cpass.cpassbe.lib.dto.DocumentoSpesa;
import it.csi.cpass.cpassbe.lib.mapper.BaseMapperInterface;


@Mapper
public abstract class DocumentoSpesaSicrawebMapper implements BaseMapperInterface<DocumentoSpesa, fdewsappjricercagateway.DocumentoSpesa> {
	@Override
	public DocumentoSpesa toModel(fdewsappjricercagateway.DocumentoSpesa entity) {
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
		ds.setCodiceExt(entity.getCodice());

		List<String> numeriOrdine = new ArrayList<>();
		for(Ordine ordine : entity.getElencoOrdini()) {
			numeriOrdine.add(ordine.getNumeroOrdine());
		}
		
		ds.setNumeriOrdine(numeriOrdine);		
		return ds;
	}

	@Override
	@IterableMapping(elementTargetType = DocumentoSpesa.class)
	public abstract List<DocumentoSpesa> toModels(Collection<fdewsappjricercagateway.DocumentoSpesa> entities);

	@Override
	@IterableMapping(elementTargetType = fdewsappjricercagateway.DocumentoSpesa.class)
	public abstract List<fdewsappjricercagateway.DocumentoSpesa> toEntities(Collection<DocumentoSpesa> models);

	
	@Override
	public fdewsappjricercagateway.DocumentoSpesa toEntity(DocumentoSpesa model) {
		if (model == null) {
			return null;
		}
		fdewsappjricercagateway.DocumentoSpesa documentoSpesaSiac = new fdewsappjricercagateway.DocumentoSpesa();
		//TODO da fare se serve
		return documentoSpesaSiac;
	}
}
