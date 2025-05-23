/*-
 * ========================LICENSE_START=================================
 * CPASS BackEnd - EJB submodule
 * %%
 * Copyright (C) 2019 - 2021 CSI Piemonte
 * %%
 * SPDX-FileCopyrightText: Copyright 2019 - 2021 | CSI Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 * =========================LICENSE_END==================================
 */
package it.csi.cpass.cpassbe.ejb.business.be.dad;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.apache.logging.log4j.util.Strings;

import it.csi.cpass.cpassbe.ejb.business.be.dad.sort.ScaricoMepaTestataEvasioneSort;
import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.CpassTFornitoreDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.CpassTSettoreDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.CpassTUfficioDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.decodifiche.CpassDStatoDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.ord.CpassDOrdTipoOrdineDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.ord.CpassTOrdTestataOrdineDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.ord.mepa.CpassTScaricoMepaRigaDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.ord.mepa.CpassTScaricoMepaScontiDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.ord.mepa.CpassTScaricoMepaTestataDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.ord.mepa.CpassTScaricoMepaXmlDao;
import it.csi.cpass.cpassbe.ejb.entity.CpassDStato;
import it.csi.cpass.cpassbe.ejb.entity.CpassTEnte;
import it.csi.cpass.cpassbe.ejb.entity.CpassTSettore;
import it.csi.cpass.cpassbe.ejb.entity.CpassTUfficio;
import it.csi.cpass.cpassbe.ejb.entity.ord.CpassTFornitore;
import it.csi.cpass.cpassbe.ejb.entity.ord.CpassTOrdTestataOrdine;
import it.csi.cpass.cpassbe.ejb.entity.ord.mepa.CpassTScaricoMepaTestata;
import it.csi.cpass.cpassbe.ejb.exception.NotFoundException;
import it.csi.cpass.cpassbe.ejb.mapper.CpassMappers;
import it.csi.cpass.cpassbe.ejb.util.ConstantsCPassStato;
import it.csi.cpass.cpassbe.ejb.util.ConstantsCPassStato.StatoOrdineEnum;
import it.csi.cpass.cpassbe.ejb.util.ConstantsCPassStato.StatoOrdineMepaEnum;
import it.csi.cpass.cpassbe.ejb.util.CpassEnum;
import it.csi.cpass.cpassbe.ejb.util.jpa.Page;
import it.csi.cpass.cpassbe.lib.dto.Fornitore;
import it.csi.cpass.cpassbe.lib.dto.Settore;
import it.csi.cpass.cpassbe.lib.dto.Utente;
import it.csi.cpass.cpassbe.lib.dto.custom.FormTestataOrdineMepa;
import it.csi.cpass.cpassbe.lib.dto.ord.TestataOrdine;
import it.csi.cpass.cpassbe.lib.dto.ord.mepa.ScaricoMepaTestata;
import it.csi.cpass.cpassbe.lib.util.pagination.PagedList;
import it.csi.cpass.cpassbe.lib.util.pagination.Sort;
import it.csi.cpass.cpassbe.lib.util.threadlocal.CpassThreadLocalContainer;

@ApplicationScoped
public class ScaricoMepaTestataDad extends BaseDad {

	@Inject
	private CpassDStatoDao cpassDStatoDao;
	@Inject
	private CpassTScaricoMepaTestataDao cpassTScaricoMepaTestataDao;
	@Inject
	private CpassTScaricoMepaRigaDao cpassTScaricoMepaRigaDao;
	@Inject
	private CpassTScaricoMepaScontiDao cpassTScaricoMepaScontiDao;
	@Inject
	private CpassTScaricoMepaXmlDao cpassTScaricoMepaXmlDao;
	@Inject
	private CpassTFornitoreDao cpassTFornitoreDao;
	@Inject
	private CpassDOrdTipoOrdineDao cpassDOrdTipoOrdineDao;
	@Inject
	private CpassTSettoreDao cpassTSettoreDao;
	@Inject
	private CpassTOrdTestataOrdineDao cpassTOrdTestataOrdineDao;
	@Inject
	private CommonDad commonDad;
	@Inject
	private CpassTUfficioDao cpassTUfficioDao;

	private final String MEPA_ORDER_TYPE = "MEP"; // TODO: creare classe costanti tipo ordini
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	public ScaricoMepaTestata getTestataOrdineMepaById(Integer id) {
		return CpassMappers.SCARICO_MEPA_TESTATA.toModel(cpassTScaricoMepaTestataDao.findOne(id).orElse(null));
	}
	/**
	 * 
	 * @return
	 */
	public List<CpassTScaricoMepaTestata> getOrdiniMepaDaCaricareSenzaCodUfficio(){
		final Optional<CpassDStato> optStato = cpassDStatoDao.findByCodiceTipo(StatoOrdineMepaEnum.DA_CARICARE.getCostante(), ConstantsCPassStato.TipoStatoEnum.ORDINE_MEPA.getCostante());

		if (optStato.isPresent()) {
			final List<CpassTScaricoMepaTestata> xstMepaSenzaUfficio = cpassTScaricoMepaTestataDao.getOrdiniMepaDaCaricareSenzaCodUfficio(optStato.get().getStatoId());
			return xstMepaSenzaUfficio;
		} else {
			throw new NotFoundException("opt stato");
		}
	}

	/**
	 * 
	 * @param codiciUfficio
	 * @param page
	 * @param size
	 * @param sort
	 * @return
	 */
	public PagedList<ScaricoMepaTestata> getOrdiniMepaDaCaricare(List<String> codiciUfficio, int page, int size, Sort sort) {
		String sortField = null;
		String sortDirection = null;

		if (sort != null) {
			if (ScaricoMepaTestataEvasioneSort.byModelName(sort.getField())!=null) {
				sortField = ScaricoMepaTestataEvasioneSort.byModelName(sort.getField()).getQueryName();
			}
			sortDirection = sort.getOrder().getSortDirection();
		}

		final Optional<CpassDStato> optStato = cpassDStatoDao.findByCodiceTipo(StatoOrdineMepaEnum.DA_CARICARE.getCostante(),ConstantsCPassStato.TipoStatoEnum.ORDINE_MEPA.getCostante());

		if (optStato.isEmpty()) {
			throw new RuntimeException(ScaricoMepaTestataDad.class.getName() + " ERROR: Stato non trovato");
		}
		final Page<CpassTScaricoMepaTestata> cpassTScaricoMepaTestataPage = cpassTScaricoMepaTestataDao.getOrdiniMepaDaCaricare(optStato.get().getStatoId(), codiciUfficio, page, size, sortField, sortDirection);
		final PagedList<ScaricoMepaTestata> scaricoMepaTestataPagedList = toPagedList(cpassTScaricoMepaTestataPage, page, size, CpassMappers.SCARICO_MEPA_TESTATA::toModel);
		return scaricoMepaTestataPagedList;
	}
	/**
	 * 
	 * @param scaricoMepaTestata
	 * @return
	 */
	public ScaricoMepaTestata saveScaricoMepaTestata(ScaricoMepaTestata scaricoMepaTestata) {
		CpassTScaricoMepaTestata cpassTScaricoMepaTestata = CpassMappers.SCARICO_MEPA_TESTATA.toEntity(scaricoMepaTestata);
		cpassTScaricoMepaTestata = cpassTScaricoMepaTestataDao.saveAndFlush(cpassTScaricoMepaTestata);
		scaricoMepaTestata = CpassMappers.SCARICO_MEPA_TESTATA.toModel(cpassTScaricoMepaTestata);
		return scaricoMepaTestata;
	}
	/**
	 * 
	 * @param scaricoMepaTestata
	 * @return
	 */
	public ScaricoMepaTestata updateScaricoMepaTestata(ScaricoMepaTestata scaricoMepaTestata) {
		CpassTScaricoMepaTestata cpassTScaricoMepaTestata = CpassMappers.SCARICO_MEPA_TESTATA.toEntity(scaricoMepaTestata);
		cpassTScaricoMepaTestata = cpassTScaricoMepaTestataDao.update(cpassTScaricoMepaTestata);
		return CpassMappers.SCARICO_MEPA_TESTATA.toModel(cpassTScaricoMepaTestata);
	}
	/**
	 * 
	 * @param orderId
	 * @return
	 */
	public ScaricoMepaTestata findScaricoMepaTestataByOrderId(String orderId) {
		final ScaricoMepaTestata result = CpassMappers.SCARICO_MEPA_TESTATA.toModel(cpassTScaricoMepaTestataDao.findScaricoMepaTestataByOrderId(orderId));
		return result;
	}

	/**
	 * Crea una Testata ordine a partire dai dati di un ordine MEPA
	 *
	 * @param formTestataOrdineMepa
	 * @param scaricoMepaTestata
	 * @param fornitore
	 * @return la TestataOrdine creata
	 */
	public TestataOrdine creaESalvaTestataOrdine(FormTestataOrdineMepa formTestataOrdineMepa,
			ScaricoMepaTestata scaricoMepaTestata,
			Fornitore fornitore) {

		final Settore settore = CpassThreadLocalContainer.SETTORE_UTENTE.get();
		final Utente utente = CpassThreadLocalContainer.UTENTE_CONNESSO.get();
		CpassTOrdTestataOrdine cpassTOrdTestataOrdine = new CpassTOrdTestataOrdine();

		// fornitore
		CpassTFornitore cpassTFornitoreNew = CpassMappers.FORNITORE.toEntity(fornitore);
		cpassTFornitoreNew.setCpassTEnte(CpassMappers.ENTE.toEntity(settore.getEnte()));// dani, capire se il fornitore arrivi già con l'ente valorizzato
		if (cpassTFornitoreNew.getNaturaGiuridica() != null && cpassTFornitoreNew.getNaturaGiuridica().length() > 5) {
			cpassTFornitoreNew.setNaturaGiuridica(cpassTFornitoreNew.getNaturaGiuridica().substring(0, 5));
		}

		final CpassTFornitore cpassTFornitoreOld = cpassTFornitoreDao.getFornitoreByCodice(cpassTFornitoreNew.getCodice(), getId(settore.getEnte()));
		if (cpassTFornitoreOld == null) {
			cpassTFornitoreNew = cpassTFornitoreDao.insert(cpassTFornitoreNew);
		} else {
			cpassTFornitoreNew.setId(cpassTFornitoreOld.getId());
			cpassTFornitoreNew = cpassTFornitoreDao.update(cpassTFornitoreNew, false);
		}
		cpassTOrdTestataOrdine.setCpassTFornitore(cpassTFornitoreNew);
		// testata
		final Calendar calendar = Calendar.getInstance();
		final int annoCorrente = calendar.get(Calendar.YEAR);
		cpassDOrdTipoOrdineDao.findValidByCodice(MEPA_ORDER_TYPE).ifPresent(cpassTOrdTestataOrdine::setCpassDOrdTipoOrdine);
		cpassTOrdTestataOrdine.setOrdineAnno(annoCorrente);

		final Optional<CpassTSettore> optionalSettore = cpassTSettoreDao.findOne(settore.getId());
		final CpassTEnte cpassTEnte = optionalSettore.orElseThrow(() -> new NotFoundException("ente")).getCpassTEnte();

		final String codice = cpassTEnte.getEnteCodice() + "-" + annoCorrente;
		final Integer intProgressivo = commonDad.getProgressivo(CpassEnum.ORDINE_TESTATA.getCostante(), codice, cpassTEnte);
		cpassTOrdTestataOrdine.setOrdineNumero(intProgressivo);
		cpassTOrdTestataOrdine.setCpassTFornitore(cpassTFornitoreNew);
		cpassTOrdTestataOrdine.setCpassDOrdTipoProcedura(CpassMappers.TIPO_PROCEDURA_ORD.toEntity(formTestataOrdineMepa.getTipoProceduraOrd()));
		cpassTOrdTestataOrdine.setNumeroProcedura(formTestataOrdineMepa.getNumeroProcedura());
		cpassTOrdTestataOrdine.setCpassTUtente(CpassMappers.UTENTE.toEntity(utente));
		cpassTOrdTestataOrdine.setDataEmissione(calendar.getTime());

		if (scaricoMepaTestata.getNote()!=null && scaricoMepaTestata.getNote().length()>150) {
			cpassTOrdTestataOrdine.setDescrizioneAcquisto(scaricoMepaTestata.getNote().substring(0,149));
			cpassTOrdTestataOrdine.setNote(scaricoMepaTestata.getNote());
		}else {
			cpassTOrdTestataOrdine.setDescrizioneAcquisto(scaricoMepaTestata.getNote());
		}

		cpassTOrdTestataOrdine.setConsegnaRiferimento(scaricoMepaTestata.getCustomerReference());
		cpassTOrdTestataOrdine.setConsegnaIndirizzo(scaricoMepaTestata.getDeliveryLocationStreetName());
		//ISSUE-117
		final String codiceUfficio = scaricoMepaTestata.getBuyerCustomerPartyId();
		if(codiceUfficio != null) {
			final Optional<CpassTUfficio> cpassTUfficio = getUfficioById(codiceUfficio, settore.getId());
			if(cpassTUfficio.isPresent()) {
				cpassTOrdTestataOrdine.setCpassTUfficio(cpassTUfficio.get());
			}
		}

		cpassTOrdTestataOrdine.setConsegnaCap(scaricoMepaTestata.getDeliveryLocationPostalZone());
		cpassTOrdTestataOrdine.setConsegnaLocalita(scaricoMepaTestata.getDeliveryLocationCityName());
		cpassTOrdTestataOrdine.setProvvedimentoAnno(formTestataOrdineMepa.getProvvedimento().getAnno());
		cpassTOrdTestataOrdine.setProvvedimentoNumero(formTestataOrdineMepa.getProvvedimento().getNumero().toString());
		cpassTOrdTestataOrdine.setProvvedimentoTipo(formTestataOrdineMepa.getProvvedimento().getProvvedimentoTipo().getCodice());
		cpassTOrdTestataOrdine.setProvvedimentoSettore(formTestataOrdineMepa.getProvvedimento().getSettore().getCodice());
		cpassTOrdTestataOrdine.setProvvedimentoDescrizione(formTestataOrdineMepa.getProvvedimento().getDescrizione());
		cpassTOrdTestataOrdine.setCpassTSettore(CpassMappers.SETTORE_CUSTOM.toEntity(settore));
		cpassDStatoDao.findByCodiceTipo(StatoOrdineEnum.BOZZA.getCostante(), ConstantsCPassStato.TipoStatoEnum.ORDINE.getCostante()).ifPresent(cpassTOrdTestataOrdine::setCpassDStato);
		cpassTOrdTestataOrdine.setCig(formTestataOrdineMepa.getCig());
		cpassTOrdTestataOrdine.setCpassDMotiviEsclusioneCig(CpassMappers.MOTIVI_ESCLUSIONE_CIG.toEntity(formTestataOrdineMepa.getMotiviEsclusioneCig()));

		final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date dataScadenza = null;
		if (Strings.isNotEmpty(scaricoMepaTestata.getEndDate())) {
			try {
				dataScadenza = format.parse(scaricoMepaTestata.getEndDate());
			} catch (final ParseException e) {
				e.printStackTrace();
			}
		}
		if (dataScadenza != null) {
			cpassTOrdTestataOrdine.setDataScadenza(dataScadenza);
		}
		cpassTOrdTestataOrdine.setCpassTScaricoMepaTestata(CpassMappers.SCARICO_MEPA_TESTATA.toEntity(scaricoMepaTestata));
		// N.B. Restano da inserire i totali calcolati sulle righe, e tipoAcquistoId
		cpassTOrdTestataOrdine = cpassTOrdTestataOrdineDao.saveAndFlush(cpassTOrdTestataOrdine);
		return CpassMappers.TESTATA_ORDINE.toModel(cpassTOrdTestataOrdine);
	}
	/**
	 * 
	 * @param codice
	 * @param settoreId
	 * @return
	 */
	public Optional<CpassTUfficio> getUfficioById(String codice, UUID settoreId) {
		final Optional<CpassTUfficio> cpassTUfficio = cpassTUfficioDao.getUfficioByCodice(codice, settoreId);
		return cpassTUfficio;
	}
	/**
	 * 
	 * @param codice
	 * @return
	 */
	public Optional<CpassTUfficio> getUfficioByCodice(String codice) {
		final Optional<CpassTUfficio> cpassTUfficio = cpassTUfficioDao.getUfficioByCod(codice);
		return cpassTUfficio;
	}
	/**
	 * 
	 * @param testataMepaId
	 */
	public void rimuoviOrdineMepa(Integer testataMepaId) {
		final CpassTScaricoMepaTestata cpassTScaricoMepaTestata = cpassTScaricoMepaTestataDao.findOne(testataMepaId).orElse(null);

		if (cpassTScaricoMepaTestata != null) {
			// rimuovo righe collegate in cpassTScaricoMepaXml
			cpassTScaricoMepaTestata.getCpassTScaricoMepaXmls().forEach(xml -> {
				cpassTScaricoMepaXmlDao.delete(xml.getId());
			});
			// rimuovo righe collegate in cpassTScaricoMepaSconti e cpassTScaricoMepaRiga
			cpassTScaricoMepaTestata.getCpassTScaricoMepaRigas().forEach(riga -> {
				riga.getCpassTScaricoMepaScontis().forEach(sconto -> {
					cpassTScaricoMepaScontiDao.delete(sconto.getId());
				});
				cpassTScaricoMepaRigaDao.delete(riga.getId());
			});
			// rimuovo infine la testata
			cpassTScaricoMepaTestataDao.delete(cpassTScaricoMepaTestata.getId());
		}
	}
}
