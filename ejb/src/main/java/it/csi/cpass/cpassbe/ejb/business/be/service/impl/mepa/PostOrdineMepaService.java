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
package it.csi.cpass.cpassbe.ejb.business.be.service.impl.mepa;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import it.csi.cpass.cpassbe.ejb.business.be.dad.CommonDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.DecodificaDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.DestinatarioOrdineDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.RigaOrdineDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.ScaricoMepaTestataDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.SettoreDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.TestataOrdineDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.mepa.PostOrdineMepaRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.mepa.PostOrdineMepaResponse;
import it.csi.cpass.cpassbe.ejb.util.ConstantsCPassStato;
import it.csi.cpass.cpassbe.ejb.util.ConstantsCPassStato.StatoOrdineMepaEnum;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;
import it.csi.cpass.cpassbe.lib.dto.AliquoteIva;
import it.csi.cpass.cpassbe.lib.dto.Fornitore;
import it.csi.cpass.cpassbe.lib.dto.ListinoFornitore;
import it.csi.cpass.cpassbe.lib.dto.Ods;
import it.csi.cpass.cpassbe.lib.dto.Settore;
import it.csi.cpass.cpassbe.lib.dto.SettoreIndirizzo;
import it.csi.cpass.cpassbe.lib.dto.Stato;
import it.csi.cpass.cpassbe.lib.dto.UnitaMisura;
import it.csi.cpass.cpassbe.lib.dto.custom.FormTestataOrdineMepa;
import it.csi.cpass.cpassbe.lib.dto.custom.RigaMepaWrapper;
import it.csi.cpass.cpassbe.lib.dto.error.MsgCpassOrd;
import it.csi.cpass.cpassbe.lib.dto.ord.Destinatario;
import it.csi.cpass.cpassbe.lib.dto.ord.RigaOrdine;
import it.csi.cpass.cpassbe.lib.dto.ord.TestataOrdine;
import it.csi.cpass.cpassbe.lib.dto.ord.mepa.ScaricoMepaRiga;
import it.csi.cpass.cpassbe.lib.dto.ord.mepa.ScaricoMepaSconti;
import it.csi.cpass.cpassbe.lib.dto.ord.mepa.ScaricoMepaTestata;
import it.csi.cpass.cpassbe.lib.util.pagination.PagedList;

public class PostOrdineMepaService extends BaseScaricoMepaService<PostOrdineMepaRequest, PostOrdineMepaResponse> {

	private final DestinatarioOrdineDad destinatarioOrdineDad;
	private final CommonDad commonDad;
	private final TestataOrdineDad testataOrdineDad;
	private final RigaOrdineDad rigaOrdineDad;
	private final SettoreDad settoreDad;

	/**
	 * Constructor
	 *
	 * @param configurationHelper   the configuration helper
	 * @param scaricoMepaTestataDad the scarico MEPA dad
	 */
	public PostOrdineMepaService(ConfigurationHelper configurationHelper,
			ScaricoMepaTestataDad scaricoMepaTestataDad,
			TestataOrdineDad testataOrdineDad,
			RigaOrdineDad rigaOrdineDad,
			DecodificaDad decodificaDad,
			DestinatarioOrdineDad destinatarioOrdineDad,
			CommonDad commonDad,
			SettoreDad settoreDad) {
		super(configurationHelper, scaricoMepaTestataDad, decodificaDad);
		this.testataOrdineDad = testataOrdineDad;
		this.rigaOrdineDad = rigaOrdineDad;
		this.destinatarioOrdineDad = destinatarioOrdineDad;
		this.commonDad = commonDad;
		this.settoreDad = settoreDad;
	}

	@Override
	protected void checkServiceParams() {
		checkNotNull(request.getFormTestataOrdineMepa(), "formTestataOrdineMepa");
		checkNotNull(request.getScaricoMepaTestata(), "scaricoMepaTestata");
		checkNotNull(request.getFornitore(), "fornitore");

		// inizializzo la percentuale
		request.getFormTestataOrdineMepa().getRighe().forEach(rigaMepaWrapper -> {
			if (rigaMepaWrapper.getScaricoMepaRiga().getClassifiedtaxcategoryPercent() == null) {
				rigaMepaWrapper.getScaricoMepaRiga().setClassifiedtaxcategoryPercent(BigDecimal.ZERO);
			}
		});
	}

	@Override
	protected void execute() {
		final FormTestataOrdineMepa formTestataOrdineMepa = request.getFormTestataOrdineMepa();
		final ScaricoMepaTestata scaricoMepaTestata = request.getScaricoMepaTestata();
		final Fornitore fornitore = request.getFornitore();

		// N.B. Mancano i totali calcolati sulle righe, e tipoAcquistoId
		final TestataOrdine testataOrdine = scaricoMepaTestataDad.creaESalvaTestataOrdine(formTestataOrdineMepa, scaricoMepaTestata, fornitore);

		List<SettoreIndirizzo> settoreIndirizzo = settoreDad.postRicercaSettoreIndirizzi(formTestataOrdineMepa.getDestinatario().getSettore());
		settoreIndirizzo = settoreIndirizzo.stream().filter(el -> el.getPrincipale()).collect(Collectors.toList());

		Destinatario destinatario = creaDestinatario(formTestataOrdineMepa, testataOrdine, settoreIndirizzo.get(0) );

		destinatario = destinatarioOrdineDad.saveDestinatario(destinatario);

		BigDecimal totaleNoIva = BigDecimal.ZERO;
		BigDecimal totaleConIva = BigDecimal.ZERO;

		final List<RigaMepaWrapper> rigaMepaWrapperList = formTestataOrdineMepa.getRighe();
		if (!rigaMepaWrapperList.isEmpty()) {
			for (int i = 0; i < rigaMepaWrapperList.size(); i++) {
				// uso l'oggetto wrapper in quanto associa scaricoMepaRiga con l'ods selezionato nel form
				final RigaMepaWrapper rigaMepaWrapper = rigaMepaWrapperList.get(i);
				final RigaOrdine rigaOrdine = rigaOrdineDad.saveRigaOrdine(creaRigaOrdine(rigaMepaWrapper, destinatario, fornitore));

				// aggiorno il conteggio per la testata
				totaleNoIva  = totaleNoIva.add(rigaOrdine.getImportoNetto());
				totaleConIva = totaleConIva.add(rigaOrdine.getImportoTotale());

				// aggiorno tipoAcquisto(=settoreInterventi) sulla testata
				if (i == 0 && rigaOrdine.getOds().getCpv() != null) {
					testataOrdine.setTipoAcquisto(rigaOrdine.getOds().getCpv().getSettoreInterventi());
				}
			}
		}

		// aggiorno i totali sulla testata ordine
		testataOrdine.setTotaleConIva(totaleConIva);
		testataOrdine.setTotaleNoIva(totaleNoIva);
		final Optional<Settore> optionalSettore = settoreDad.findByIdValid(testataOrdine.getSettore().getId());


		checkBusinessCondition(optionalSettore.isPresent(), () -> MsgCpassOrd.ORDORDE0050.getError());
		testataOrdineDad.updateTestataOrdine(testataOrdine);

		final Optional<Stato> stato = decodificaDad.getStatoOpt(
				StatoOrdineMepaEnum.CARICATO.getCostante(),
				ConstantsCPassStato.TipoStatoEnum.ORDINE_MEPA.getCostante());
		if (stato.isPresent()) {
			scaricoMepaTestata.setStato(stato.get());
			scaricoMepaTestataDad.updateScaricoMepaTestata(scaricoMepaTestata);
		}


		response.setTestataOrdine(testataOrdine);
	}

	private Destinatario creaDestinatario(FormTestataOrdineMepa formTestataOrdineMepa, TestataOrdine testataOrdine, SettoreIndirizzo settoreIndirizzo) {
		final Destinatario destinatario = new Destinatario();
		destinatario.setIndirizzo(formTestataOrdineMepa.getDestinatario().getIndirizzo());
		destinatario.setNumCivico(formTestataOrdineMepa.getDestinatario().getNumCivico());
		destinatario.setLocalita(formTestataOrdineMepa.getDestinatario().getLocalita());
		destinatario.setProvincia(formTestataOrdineMepa.getDestinatario().getProvincia());
		destinatario.setCap(formTestataOrdineMepa.getDestinatario().getCap());
		destinatario.setContatto(formTestataOrdineMepa.getDestinatario().getContatto());
		destinatario.setEmail(formTestataOrdineMepa.getDestinatario().getEmail());
		destinatario.setTelefono(formTestataOrdineMepa.getDestinatario().getTelefono());
		destinatario.setSettore(formTestataOrdineMepa.getDestinatario().getSettore());
		destinatario.setStato(decodificaDad.getStato(
				ConstantsCPassStato.StatoOrdineEvasioneEnum.DEST_ORDINE_DA_EVADERE.getCostante(), ConstantsCPassStato.TipoStatoEnum.DEST_ORDINE.getCostante()));
		destinatario.setTestataOrdine(testataOrdine);


		destinatario.setSettoreIndirizzo(settoreIndirizzo);
		return destinatario;
	}

	private RigaOrdine creaRigaOrdine(RigaMepaWrapper rigaMepaWrapper, Destinatario destinatario, Fornitore fornitore) {

		final RigaOrdine rigaOrdine = new RigaOrdine();
		final ScaricoMepaRiga rigaMepa = rigaMepaWrapper.getScaricoMepaRiga();
		final Ods ods = rigaMepaWrapper.getOds();

		rigaOrdine.setConsegnaParziale(rigaMepa.getOrderlineLinePartialDeliveryIndicator());

		final BigDecimal lineExtensionAmount = rigaMepa.getOrderlineLineExtensionAmount();
		final BigDecimal quantita = rigaMepa.getOrderlineQuantity();
		final BigDecimal prezzoUnitario = lineExtensionAmount.divide(quantita);
		rigaOrdine.setPrezzoUnitario(prezzoUnitario);
		rigaOrdine.setQuantita(quantita);

		//sconti
		final List<ScaricoMepaSconti> scaricoMepaScontiList = rigaMepa.getScaricoMepaScontis().stream()
				.filter(sc -> Boolean.FALSE.equals(sc.getOrderlineLineAllowancechargeIndicator()))
				.collect(Collectors.toList());
		if (!scaricoMepaScontiList.isEmpty()) {
			rigaOrdine.setPercentualeSconto(
					scaricoMepaScontiList.get(0).getOrderlineLineAllowancechargeMultiplierFactorNumeric());
			rigaOrdine.setImportoSconto(
					scaricoMepaScontiList.get(0).getOrderlineLineAllowancechargeAmount()
					);

			if (scaricoMepaScontiList.size() > 1) {
				rigaOrdine.setPercentualeSconto2(
						scaricoMepaScontiList.get(1).getOrderlineLineAllowancechargeMultiplierFactorNumeric());
				rigaOrdine.setImportoSconto2(
						scaricoMepaScontiList.get(1).getOrderlineLineAllowancechargeAmount()
						);
			}
		}

		final BigDecimal importoNetto = lineExtensionAmount;
		final BigDecimal percent = rigaMepa.getClassifiedtaxcategoryPercent().divide(new BigDecimal(100));
		rigaOrdine.setImportoIva(importoNetto.multiply(percent));
		final BigDecimal importoTotale = importoNetto.add(rigaOrdine.getImportoIva());
		rigaOrdine.setImportoNetto(importoNetto);
		rigaOrdine.setImportoTotale(importoTotale);
		rigaOrdine.setNote(rigaMepa.getOrderlineNote());

		final Stato stato = decodificaDad.getStato(ConstantsCPassStato.StatoOrdineEvasioneEnum.RIGA_ORDINE_DA_EVADERE.getCostante(), ConstantsCPassStato.TipoStatoEnum.RIGA_ORDINE.getCostante());
		rigaOrdine.setStato(stato);

		// ODS
		if (ods != null) {
			rigaOrdine.setOds(ods);
		}
		final UnitaMisura unitaMisura = decodificaDad.getUnitaMisuraByCodice(rigaMepa.getOrderlineQuantityUnitCode());
		if (unitaMisura != null) {
			rigaOrdine.setUnitaMisura(unitaMisura);
		}else {
			generaException(MsgCpassOrd.ORDORDE0175.getError("unita_misura",rigaMepa.getOrderlineQuantityUnitCode()));
		}

		final Optional<AliquoteIva> aliquoteIva = decodificaDad.getAliquoteIva().stream().filter(al -> al.getPercentuale().compareTo(rigaMepa.getClassifiedtaxcategoryPercent()) == 0).findFirst();
		if(aliquoteIva.isPresent()) {
			rigaOrdine.setAliquoteIva(aliquoteIva.get());
		}
		rigaOrdine.setDestinatario(destinatario);

		ListinoFornitore listinoFornitore = null;
		final ListinoFornitore listinoFornitoreRicerca = new ListinoFornitore();
		listinoFornitoreRicerca.setFornitore(fornitore);
		listinoFornitoreRicerca.setCodiceOds(rigaMepa.getItemSellersId());
		final PagedList<ListinoFornitore> pagedResListino = commonDad.getRicercaListinoFornitore(0, 0, null, listinoFornitoreRicerca);
		if (!pagedResListino.isEmpty()) {
			listinoFornitore = pagedResListino.get(0);
		}
		if (listinoFornitore != null) {
			rigaOrdine.setListinoFornitore(listinoFornitore);
		}
		return rigaOrdine;
	}


}
