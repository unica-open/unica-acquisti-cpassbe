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
package it.csi.cpass.cpassbe.ejb.business.be.service.impl.ordine.rda;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import it.csi.cpass.cpassbe.ejb.business.be.dad.CommonDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.DecodificaDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.RdaDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.ordine.rda.PostTestataRdaRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.ordine.rda.PostTestataRdaResponse;
import it.csi.cpass.cpassbe.ejb.util.ConstantsCPassStato;
import it.csi.cpass.cpassbe.ejb.util.ConstantsCPassStato.StatoRdaEnum;
import it.csi.cpass.cpassbe.ejb.util.ConstantsCPassStato.StatoRigaRdaEnum;
import it.csi.cpass.cpassbe.ejb.util.CpassEnum;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;
import it.csi.cpass.cpassbe.lib.dto.Settore;
import it.csi.cpass.cpassbe.lib.dto.Stato;
import it.csi.cpass.cpassbe.lib.dto.Utente;
import it.csi.cpass.cpassbe.lib.dto.error.MsgCpassOrd;
import it.csi.cpass.cpassbe.lib.dto.ord.rda.RigaRda;
import it.csi.cpass.cpassbe.lib.dto.ord.rda.TestataRda;
import it.csi.cpass.cpassbe.lib.dto.rms.RigaRms;
import it.csi.cpass.cpassbe.lib.util.threadlocal.CpassThreadLocalContainer;

/**
 * Saves an TestataRda
 */
public class PostTestataRdaService extends BaseRdaService<PostTestataRdaRequest, PostTestataRdaResponse> {

	private final DecodificaDad decodificaDad;
	private final CommonDad commonDad;
	private TestataRda testataRda;

	/**
	 * Constructor
	 * @param configurationHelper the configuration helper
	 * @param rdaDad the rda DAD
	 * @param decodificaDad the decodifica DAD
	 * @param commonDad the common DAD
	 */
	public PostTestataRdaService(ConfigurationHelper configurationHelper, RdaDad rdaDad, DecodificaDad decodificaDad, CommonDad commonDad) {
		super(configurationHelper, rdaDad);
		this.decodificaDad = decodificaDad;
		this.commonDad = commonDad;
	}

	@Override
	protected void checkServiceParams() {
		testataRda = request.getTestataRda();
		checkNotNull(testataRda, "testataRda", Boolean.TRUE);
		checkModel( testataRda.getUfficio(),"ufficio", Boolean.TRUE);
		checkNotNull( testataRda.getDescrizione(),"descrizione");
		//checkCondition(testataRda.getRigaRda().isEmpty(),CoreError.GENERIC_ERROR.getError("error", "La lista di altri dati può contenere un unico elemento"));
	}

	@Override
	protected void execute() {
		final Utente utenteConnesso = CpassThreadLocalContainer.UTENTE_CONNESSO.get();
		final Settore settoreCorrente = CpassThreadLocalContainer.SETTORE_UTENTE.get();
		// controlli
		checkBusinessCondition(!testataRda.getRigaRda().isEmpty(), MsgCpassOrd.ORDORDE0144.getError());
		final List<RigaRda> odsGenerici = testataRda.getRigaRda().stream().filter((x) -> x.getOggettiSpesa().getGenerico()).collect(Collectors.toList());
		checkBusinessCondition(odsGenerici.isEmpty(), MsgCpassOrd.ORDORDE0145.getError());
		final Stato dae = isEntityPresent(() -> decodificaDad.getStatoOpt(StatoRigaRdaEnum.DAE.getCostante(), ConstantsCPassStato.TipoStatoEnum.RIGA_RDA.getCostante()), "stato riga");
		// raggruppo le Rda passate per ods
		final List<RigaRda> righeRdaGroupByOds = new ArrayList<>();
		final Map<Integer, List<RigaRda>> mapRigaRdaGroupOds = testataRda.getRigaRda().stream().collect(Collectors.groupingBy(a-> a.getOggettiSpesa().getId()));

		// DATI della testata RDA
		testataRda.setAnno(LocalDate.now().getYear());
		testataRda.setStato(isEntityPresent(() -> decodificaDad.getStatoOpt(StatoRdaEnum.BOZZA.getCostante(), ConstantsCPassStato.TipoStatoEnum.RDA.getCostante()), "stato"));
		testataRda.setUtenteCompilatore(utenteConnesso);
		testataRda.setSettoreEmittente(settoreCorrente);
		testataRda.setEnte(settoreCorrente.getEnte());
		final String codice = settoreCorrente.getEnte().getCodice() + "-" + testataRda.getAnno();
		final Integer intProgressivo = commonDad.getProgressivo(CpassEnum.RDA_TESTATA.getCostante(), codice);
		testataRda.setNumero(intProgressivo);
		// per ogni riga riga aggregata x ODS creo l'oggetto  rigaRda
		int progressivoRiga = 1;
		for(final List<RigaRda> rdaRow : mapRigaRdaGroupOds.values()) {
			final BigDecimal sumQta                 = rdaRow.stream().map(x-> x.getQuantita()).reduce(BigDecimal.ZERO, BigDecimal::add);



			final BigDecimal quantitaSuRdaDaEvadere = rdaRow.stream().map(x-> x.getQuantitaSuRdaDaEvadere()).reduce(BigDecimal.ZERO, BigDecimal::add);



			controlliSuQta(sumQta, quantitaSuRdaDaEvadere);
			final List<RigaRms> rigaRmss = rdaRow.stream().flatMap(x-> x.getRigaRms().stream()).collect(Collectors.toList());
			final RigaRda rigaRda = new RigaRda();
			rigaRda.setProgressivoRiga(progressivoRiga);
			progressivoRiga++;
			rigaRda.setOggettiSpesa(rdaRow.get(0).getOggettiSpesa());
			rigaRda.setUnitaMisura(rdaRow.get(0).getOggettiSpesa().getUnitaMisura());
			rigaRda.setQuantita(quantitaSuRdaDaEvadere);
			//rigaRda.setQuantitaSuRdaDaEvadere(quantitaSuRdaDaEvadere);
			rigaRda.setStato(dae);
			rigaRda.setRigaRms(rigaRmss);
			rigaRda.setEnte(settoreCorrente.getEnte());

			final List<RigaRda> listaRelazioniConRms = testataRda.getRigaRda().stream().filter((x) -> x.getOggettiSpesa().getId().equals( rigaRda.getOggettiSpesa().getId() )).collect(Collectors.toList());
			rigaRda.setListaRelazioniConRms(listaRelazioniConRms);

			righeRdaGroupByOds.add(rigaRda);
		}
		testataRda.setRigaRda(righeRdaGroupByOds);


		testataRda = rdaDad.insertTestataERigheRda(testataRda);
		response.setTestataRda(testataRda);
	}

	/**
	 * @param sumQta
	 * @param quantitaSuRdaDaEvadere
	 */
	private void controlliSuQta(BigDecimal sumQta, BigDecimal quantitaSuRdaDaEvadere) {
		boolean quantitaDaEvadereMaggiore = Boolean.FALSE;
		boolean quantitaDaEvadereZero = Boolean.FALSE;
		if (quantitaSuRdaDaEvadere.compareTo(sumQta) > 0) {
			quantitaDaEvadereMaggiore = Boolean.TRUE;
		}
		if(quantitaSuRdaDaEvadere == BigDecimal.ZERO) {
			quantitaDaEvadereZero = Boolean.TRUE;
		}
		checkBusinessCondition(!quantitaDaEvadereMaggiore, MsgCpassOrd.ORDORDE0182.getError());
		checkBusinessCondition(!quantitaDaEvadereZero, MsgCpassOrd.ORDORDE0183.getError());
	}

}
