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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import it.csi.cpass.cpassbe.ejb.business.be.dad.sort.DocumentoTrasportoSort;
import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.CpassTUfficioDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.ord.CpassTOrdTestataOrdineDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.ord.evasione.CpassTOrdDocumentoTrasportoDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.ord.evasione.CpassTOrdDocumentoTrasportoXMLDao;
import it.csi.cpass.cpassbe.ejb.entity.CpassTUfficio;
import it.csi.cpass.cpassbe.ejb.entity.ord.CpassTOrdTestataOrdine;
import it.csi.cpass.cpassbe.ejb.entity.ord.evasione.CpassTOrdDocumentoTrasporto;
import it.csi.cpass.cpassbe.ejb.entity.ord.evasione.CpassTOrdDocumentoTrasportoXml;
import it.csi.cpass.cpassbe.ejb.exception.NotFoundException;
import it.csi.cpass.cpassbe.ejb.mapper.CpassMappers;
import it.csi.cpass.cpassbe.ejb.util.jpa.Page;
import it.csi.cpass.cpassbe.ejb.util.jpa.PageImpl;
import it.csi.cpass.cpassbe.lib.dto.ord.evasione.DocumentoTrasporto;
import it.csi.cpass.cpassbe.lib.dto.ord.evasione.DocumentoTrasportoXML;
import it.csi.cpass.cpassbe.lib.dto.ord.evasione.custom.RicercaDdt;
import it.csi.cpass.cpassbe.lib.util.pagination.PagedList;
import it.csi.cpass.cpassbe.lib.util.pagination.Sort;

/**
 * Data Access Delegate for DocumentoTrasporto
 */
@ApplicationScoped
public class DocumentoTrasportoDad extends BaseDad {

	@Inject
	private CpassTOrdDocumentoTrasportoDao documentoTrasportoDao;
	@Inject
	private CpassTOrdDocumentoTrasportoXMLDao documentoTrasportoXMLDao;
	@Inject
	private CpassTUfficioDao cpassTUfficioDao;
	@Inject
	private CpassTOrdTestataOrdineDao testataOrdineDao;
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	public DocumentoTrasporto findById(Integer id) {
		DocumentoTrasporto documentoTrasporto = null;
		final CpassTOrdDocumentoTrasporto entity = documentoTrasportoDao.findOne(id).orElse(null);
		if (entity != null) {
			documentoTrasporto = CpassMappers.DOCUMENTO_TRASPORTO.toModel(entity);
		}

		return documentoTrasporto;
	}

	/**
	 * 
	 * @param settoreId
	 * @param ricercaDdt
	 * @param page
	 * @param size
	 * @param sort
	 * @return
	 */
	public PagedList<DocumentoTrasporto> ricercaDocumentoTrasporto(UUID settoreId, RicercaDdt ricercaDdt, Integer page, Integer size, Sort sort) {
		String sortField = null;
		String sortDirection = null;
		List<CpassTOrdTestataOrdine> testataOrdineList = null;
		List<UUID> idOrdineList = null;
		UUID idFornitore = null;
		Integer idStato = null;
		final List<UUID> settoriId = new ArrayList<>();
		settoriId.add(settoreId);
		final List<String> idNotierList = getListIdNotier(cpassTUfficioDao.getUfficiBySettore(settoriId));
		final Date dataConsegna = ricercaDdt.getDataConsegna();

		if (ricercaDdt.getOrdineAnno() != null || ricercaDdt.getOrdineNumero() != null) {
			testataOrdineList = testataOrdineDao.findTestataByAnnoOrNumero(ricercaDdt.getOrdineAnno(), ricercaDdt.getOrdineNumero());
		}
		if (testataOrdineList != null && testataOrdineList.size() > 0) {
			idOrdineList = getListaId(testataOrdineList);
		}
		if (ricercaDdt.getFornitore() != null) {
			idFornitore = ricercaDdt.getFornitore().getId();
		}
		if (ricercaDdt.getStatoDdt() != null) {
			idStato = ricercaDdt.getStatoDdt().getId();
		}

		if (sort != null) {
			if (DocumentoTrasportoSort.byModelName(sort.getField())!=null) {
				sortField = DocumentoTrasportoSort.byModelName(sort.getField()).getQueryName();
			}
			sortDirection = sort.getOrder().getSortDirection();
		}

		Page<CpassTOrdDocumentoTrasporto> entityList = new PageImpl<>(0);
		if (idNotierList.size() > 0) {
			entityList = documentoTrasportoDao.ricercaDocumentoTrasporto(
					idNotierList,
					idOrdineList,
					idStato,
					dataConsegna,
					idFornitore,
					page,
					size,
					sortField,
					sortDirection
					);
		}

		return toPagedList(entityList, page, size, CpassMappers.DOCUMENTO_TRASPORTO::toModel);
	}

	/**
	 * Inserts the new DocumentoTrasporto
	 *
	 * @param documentoTrasporto to insert
	 * @return the model instance
	 */
	public DocumentoTrasporto saveDocumentoTrasporto(DocumentoTrasporto documentoTrasporto) {
		final CpassTOrdDocumentoTrasporto cpassTOrdDocumentoTrasporto = CpassMappers.DOCUMENTO_TRASPORTO.toEntity(documentoTrasporto);
		final CpassTOrdDocumentoTrasporto result = documentoTrasportoDao.insert(cpassTOrdDocumentoTrasporto);
		documentoTrasportoDao.flush();
		return CpassMappers.DOCUMENTO_TRASPORTO.toModel(result);
	}

	/**
	 * Updates an existing DocumentoTrasporto
	 *
	 * @param documentoTrasporto to update
	 * @return the model instance
	 */
	public DocumentoTrasporto updateDocumentoTrasporto(DocumentoTrasporto documentoTrasporto) {
		final CpassTOrdDocumentoTrasporto cpassTOrdDocumentoTrasporto = CpassMappers.DOCUMENTO_TRASPORTO.toEntity(documentoTrasporto);
		final DocumentoTrasporto result = CpassMappers.DOCUMENTO_TRASPORTO.toModel(documentoTrasportoDao.saveAndFlush(cpassTOrdDocumentoTrasporto));
		return result;
	}

	/**
	 * Deletes existing DocumentoTrasporto
	 *
	 * @param documentoTrasportoId integer
	 */
	public void deleteDocumentoTrasporto(Integer documentoTrasportoId) {
		documentoTrasportoDao.delete(documentoTrasportoId);
	}
	
	/**
	 * 
	 * @param idEvasione
	 * @return
	 */
	public DocumentoTrasporto findDocumentoTrasportoByIdEvasione(UUID idEvasione) {
		final DocumentoTrasporto result = CpassMappers.DOCUMENTO_TRASPORTO.toModel(documentoTrasportoDao.findDocumentoTrasportoByIdEvasione(idEvasione));
		return result;
	}

	/**
	 * 	
	 * @param idEnte
	 * @param dataStorico
	 * @return
	 */
	public List<DocumentoTrasportoXML> findDDTFileByTestataEnteDaStoricizzare(UUID idEnte, Date dataStorico) {
		final List<DocumentoTrasportoXML> result = CpassMappers.DOCUMENTO_TRASPORTO_XML.toModels(documentoTrasportoXMLDao.findDDTFileByTestataEnteDaStoricizzare(idEnte, dataStorico));
		return result;
	}

	/**
	 *
	 * @param model
	 */
	public void saveDdtXml(DocumentoTrasportoXML model) {
		final CpassTOrdDocumentoTrasportoXml entity = CpassMappers.DOCUMENTO_TRASPORTO_XML.toEntity(model);
		documentoTrasportoXMLDao.save(entity);
	}

	/**
	 * 
	 * @param cpassTUfficioList
	 * @return
	 */
	private List<String> getListIdNotier(List<CpassTUfficio> cpassTUfficioList) {
		final List<String> idNotierList = new ArrayList<>();
		/* al diavolo le lambda
		if (Objects.nonNull(cpassTUfficioList) && cpassTUfficioList.size() > 0) {
			cpassTUfficioList.forEach(ent -> { idNotierList.add(ent.getIdNotier()); });
		}
		 */
		if (cpassTUfficioList !=null && cpassTUfficioList.size() > 0) {
			for( final CpassTUfficio ent :cpassTUfficioList) {
				if(ent!=null && ent.getIdNotier()!=null) {
					idNotierList.add(ent.getIdNotier());
				}
			}
		}
		return idNotierList;
	}
	
	/**
	 * 
	 * @param testataOrdineList
	 * @return
	 */
	private List<UUID> getListaId(List<CpassTOrdTestataOrdine> testataOrdineList) {
		final List<UUID> idList = new ArrayList<>();

		if (Objects.nonNull(testataOrdineList)) {
			testataOrdineList.forEach(ent -> { idList.add(ent.getId()); });
		}

		return idList;
	}
	
	/**
	 * 
	 * @param documentoTrasportoXml
	 * @param ddt
	 * @return
	 */
	public DocumentoTrasportoXML saveDocumentoTrasportoXMl(DocumentoTrasportoXML documentoTrasportoXml, DocumentoTrasporto ddt) {
		final CpassTOrdDocumentoTrasportoXml cpassTOrdDocumentoTrasportoXml = CpassMappers.DOCUMENTO_TRASPORTO_XML.toEntity(documentoTrasportoXml);

		final Optional<CpassTOrdDocumentoTrasporto> optionalDdtentity = documentoTrasportoDao.findOne(ddt.getId());
		if (optionalDdtentity.isPresent()) {
			final CpassTOrdDocumentoTrasporto ddtentity = optionalDdtentity.get();
			cpassTOrdDocumentoTrasportoXml.setCpassTOrdDocumentoTrasporto(ddtentity);
			final CpassTOrdDocumentoTrasportoXml result = documentoTrasportoXMLDao.save(cpassTOrdDocumentoTrasportoXml);
			return CpassMappers.DOCUMENTO_TRASPORTO_XML.toModel(result);
		} else {
			throw new NotFoundException("ddtentity");
		}
	}
}
