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
package it.csi.cpass.cpassbe.ejb.business.be.dad;

import java.util.Optional;
import java.util.UUID;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.ord.evasione.CpassTOrdDocumentoTrasportoRigaDao;
import it.csi.cpass.cpassbe.ejb.entity.ord.evasione.CpassTOrdDocumentoTrasporto;
import it.csi.cpass.cpassbe.ejb.entity.ord.evasione.CpassTOrdDocumentoTrasportoRiga;
import it.csi.cpass.cpassbe.ejb.mapper.CpassMappers;
import it.csi.cpass.cpassbe.lib.dto.Fornitore;
import it.csi.cpass.cpassbe.lib.dto.ord.evasione.DocumentoTrasportoRiga;

/**
 * Data Access Delegate for DocumentoTrasporto
 */
@ApplicationScoped
public class DocumentoTrasportoRigaDad extends BaseDad {

	@Inject
	CpassTOrdDocumentoTrasportoRigaDao documentoTrasportoRigaDao;

	/**
	 * Inserts the new DocumentoTrasportoRiga
	 *
	 * @param documentoTrasportoRiga to insert
	 * @return the model instance
	 */
	public DocumentoTrasportoRiga saveDocumentoTrasportoRiga(DocumentoTrasportoRiga documentoTrasportoRiga) {
		final CpassTOrdDocumentoTrasportoRiga cpassTOrdDocumentoTrasportoRiga = CpassMappers.DOCUMENTO_TRASPORTO_RIGA.toEntity(documentoTrasportoRiga);
		final CpassTOrdDocumentoTrasportoRiga result = documentoTrasportoRigaDao.saveAndFlush(cpassTOrdDocumentoTrasportoRiga);
		return CpassMappers.DOCUMENTO_TRASPORTO_RIGA.toModel(result);
	}

	/**
	 * Updates an existing DocumentoTrasportoRiga
	 *
	 * @param documentoTrasportoRiga to update
	 * @return the model instance
	 */
	public DocumentoTrasportoRiga updateDocumentoTrasportoRiga(DocumentoTrasportoRiga documentoTrasportoRiga) {
		final CpassTOrdDocumentoTrasportoRiga cpassTOrdDocumentoTrasportoRiga = CpassMappers.DOCUMENTO_TRASPORTO_RIGA.toEntity(documentoTrasportoRiga);
		// recupero dal model il documento di trasporto con i dati del fornitore
		final CpassTOrdDocumentoTrasporto cpassTOrdDocumentoTrasporto = CpassMappers.DOCUMENTO_TRASPORTO.toEntity(documentoTrasportoRiga.getDocumentoTrasporto());
		cpassTOrdDocumentoTrasportoRiga.setCpassTOrdDocumentoTrasporto(cpassTOrdDocumentoTrasporto);
		final CpassTOrdDocumentoTrasportoRiga resultEntity = documentoTrasportoRigaDao.update(cpassTOrdDocumentoTrasportoRiga);
		final DocumentoTrasportoRiga result = CpassMappers.DOCUMENTO_TRASPORTO_RIGA.toModel(resultEntity);
		// recupero il fornitore dall'entity aggiornata
		result.setDocumentoTrasporto(CpassMappers.DOCUMENTO_TRASPORTO.toModel(cpassTOrdDocumentoTrasportoRiga.getCpassTOrdDocumentoTrasporto()));
		return result;
	}

	/**
	 * Deletes existing DocumentoTrasportoRiga
	 *
	 * @param documentoTrasportoRigaId integer
	 */
	public void deleteDocumentoTrasporto(Integer documentoTrasportoRigaId) {
		documentoTrasportoRigaDao.delete(documentoTrasportoRigaId);
	}
	
	/**
	 * 
	 * @param rigaEvasioneId
	 * @return
	 */
	public DocumentoTrasportoRiga findByRigaEvasioneId(UUID rigaEvasioneId) {
		DocumentoTrasportoRiga documentoTrasportoRiga = null;
		final Optional<CpassTOrdDocumentoTrasportoRiga> cpassTOrdDocumentoTrasportoRiga = documentoTrasportoRigaDao.findByRigaEvasioneId(rigaEvasioneId);

		if (cpassTOrdDocumentoTrasportoRiga.isPresent()) {
			documentoTrasportoRiga = CpassMappers.DOCUMENTO_TRASPORTO_RIGA.toModel(cpassTOrdDocumentoTrasportoRiga.get());
			final Fornitore fornitore = CpassMappers.FORNITORE.toModel(cpassTOrdDocumentoTrasportoRiga.get().getCpassTOrdDocumentoTrasporto().getCpassTFornitore());
			documentoTrasportoRiga.getDocumentoTrasporto().setFornitore(fornitore);
		}
		return documentoTrasportoRiga;
	}

}
