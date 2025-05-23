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
package it.csi.cpass.cpassbe.ejb.business.be.service.impl.ordine;

import java.math.BigDecimal;
import java.util.List;

import it.csi.cpass.cpassbe.ejb.business.be.dad.CommonDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.DecodificaDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.DestinatarioOrdineDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.FornitoreDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.RigaOrdineDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.SystemDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.TestataOrdineDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.UtenteDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.base.BaseService;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.ordine.PutRigaOrdineRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.ordine.PutRigaOrdineResponse;
import it.csi.cpass.cpassbe.ejb.business.be.utility.UtilityRigaOrdine;
import it.csi.cpass.cpassbe.ejb.business.be.utility.UtilityVerificheOrdine;
import it.csi.cpass.cpassbe.ejb.exception.BusinessException;
import it.csi.cpass.cpassbe.ejb.util.ConstantsCPassParametro;
import it.csi.cpass.cpassbe.ejb.util.ConstantsCPassParametro.ChiaveEnum;
import it.csi.cpass.cpassbe.ejb.util.NumberUtility;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;
import it.csi.cpass.cpassbe.lib.dto.AliquoteIva;
import it.csi.cpass.cpassbe.lib.dto.Cpv;
import it.csi.cpass.cpassbe.lib.dto.Ente;
import it.csi.cpass.cpassbe.lib.dto.Fornitore;
import it.csi.cpass.cpassbe.lib.dto.ListinoFornitore;
import it.csi.cpass.cpassbe.lib.dto.Ods;
import it.csi.cpass.cpassbe.lib.dto.Parametro;
import it.csi.cpass.cpassbe.lib.dto.Settore;
import it.csi.cpass.cpassbe.lib.dto.UnitaMisura;
import it.csi.cpass.cpassbe.lib.dto.error.MsgCpassOrd;
import it.csi.cpass.cpassbe.lib.dto.ord.RigaOrdine;
import it.csi.cpass.cpassbe.lib.util.threadlocal.CpassThreadLocalContainer;

public class PutRigaOrdineService extends BaseService<PutRigaOrdineRequest, PutRigaOrdineResponse> {

	private final RigaOrdineDad rigaOrdineDad;
	private final DecodificaDad decodificaDad;
	private final SystemDad systemDad;
	private RigaOrdine rigaOrdine;
	private final TestataOrdineDad testataOrdineDad;
	private final DestinatarioOrdineDad destinatarioDad;
	private final CommonDad commonDad;
	private final FornitoreDad fornitoreDad;
	/**
	 * Constructor
	 *
	 * @param configurationHelper the configuration helper
	 * @param testataOrdineDad    the testataOrdine DAD
	 * @param decodificaDad       the decodifica DAD
	 */
	public PutRigaOrdineService(ConfigurationHelper configurationHelper, RigaOrdineDad rigaOrdineDad, DecodificaDad decodificaDad, SystemDad systemDad,
			UtenteDad utenteDad, TestataOrdineDad testataOrdineDad, DestinatarioOrdineDad destinatarioDad, CommonDad commonDad, FornitoreDad fornitoreDad) {
		super(configurationHelper);
		this.rigaOrdineDad = rigaOrdineDad;
		this.decodificaDad = decodificaDad;
		this.systemDad = systemDad;
		this.testataOrdineDad = testataOrdineDad;
		this.destinatarioDad = destinatarioDad;
		this.commonDad = commonDad;
		this.fornitoreDad = fornitoreDad;
	}

	@Override
	protected void checkServiceParams() {
		rigaOrdine = request.getRigaOrdine();
		checkNotNull(rigaOrdine, "rigaOrdine", true);
		checkNotNull(rigaOrdine.getOds(), "ods", true);
		checkNotNull(rigaOrdine.getOds().getCodice(), "codiceOds", true);
		checkNotNull(rigaOrdine.getOds().getCpv(), "cpv", true);
		checkNotNull(rigaOrdine.getOds().getCpv().getCodice(), "codiceCpv", true);
		checkNotNull(rigaOrdine.getUnitaMisura(), "unitaMisura", true);
		checkNotNull(rigaOrdine.getAliquoteIva(), "aliquoteIva", true);
		checkNotNull(rigaOrdine.getPrezzoUnitario(), "prezzoUnitario", true);
		checkNotNull(rigaOrdine.getQuantita(), "quantita", true);
		checkNotNull(rigaOrdine.getImportoNetto(), "importoNetto", true);
		checkNotNull(rigaOrdine.getImportoIva(), "iva", true);
		checkNotNull(rigaOrdine.getImportoTotale(), "importoTotale", true);
	}

	@Override
	protected void execute() {
		boolean hasErrors = false;
		final Settore settoreUtente = CpassThreadLocalContainer.SETTORE_UTENTE.get();
		final Ente ente = settoreUtente.getEnte();
		//controllo che esista il cpv
		final Cpv cpv = decodificaDad.getCpvByCodice(rigaOrdine.getOds().getCpv().getCodice());
		if(cpv == null) {
			hasErrors = true;
			response.addApiError(MsgCpassOrd.ORDORDE0011.getError());
			return;
		}

		//controllo che esista l'ods
		final Ods ods = decodificaDad.getOdsByCodice(rigaOrdine.getOds().getCodice());
		if(ods == null) {
			hasErrors = Boolean.TRUE;
			response.addApiError(MsgCpassOrd.ORDORDE0012.getError());
			return;
		}

		// controllo congruenza tra i due
		if(!ods.getCpv().getCodice().equals(cpv.getCodice())) {
			hasErrors = Boolean.TRUE;
			response.addApiError(MsgCpassOrd.ORDORDE0013.getError());
		}

		// controllo validita unita di misura
		final List<UnitaMisura> unitaMisuraValid = decodificaDad.getUnitaMisura();
		boolean isUmValid = false;
		for(final UnitaMisura um : unitaMisuraValid) {
			if(um.getCodice().equals(rigaOrdine.getUnitaMisura().getCodice())) {
				isUmValid = Boolean.TRUE;
				break;
			}
		}
		if(!isUmValid) {
			hasErrors = Boolean.TRUE;
			response.addApiError(MsgCpassOrd.ORDORDE0014.getError());
		}

		// controllo validita aliquotaIva
		final List<AliquoteIva> aliquotaValid = decodificaDad.getAliquoteIva();
		boolean isAIValid = false;
		for(final AliquoteIva ai : aliquotaValid) {
			if(ai.getCodice().equals(rigaOrdine.getAliquoteIva().getCodice())) {
				isAIValid = Boolean.TRUE;
				break;
			}
		}
		if(!isAIValid) {
			hasErrors = Boolean.TRUE;
			response.addApiError(MsgCpassOrd.ORDORDE0015.getError());
		}

		//controllo quantita
		if(rigaOrdine.getQuantita().compareTo(BigDecimal.ZERO) < 1) {
			hasErrors = Boolean.TRUE;
			response.addApiError(MsgCpassOrd.ORDORDE0018.getError());
		}

		//controllo prezzoUnitario
		final BigDecimal pU = rigaOrdine.getPrezzoUnitario();
		if(pU.compareTo(BigDecimal.ZERO) < 1) {
			hasErrors = true;
			response.addApiError(MsgCpassOrd.ORDORDE0019.getError());
		}

		//controllo importoNetto
		final BigDecimal netto = rigaOrdine.getImportoNetto();
		if(netto.compareTo(BigDecimal.ZERO) < 1) {
			hasErrors = Boolean.TRUE;
			response.addApiError(MsgCpassOrd.ORDORDE0067.getError());
		}

		//controllo importoTotale
		final BigDecimal totale = rigaOrdine.getImportoTotale();
		if(totale.compareTo(BigDecimal.ZERO) < 1) {
			hasErrors = Boolean.TRUE;
			response.addApiError(MsgCpassOrd.ORDORDE0068.getError());
		}

		//controllo che l'ods della riga ordine non sia già assegnato ad un altra riga ordine dello stesso ordine
		final List<RigaOrdine> righeDestinatario = rigaOrdineDad.getRigheByDestinatario(rigaOrdine.getDestinatario().getId());
		boolean isFirstOdsOfOrdine = Boolean.TRUE;
		for(final RigaOrdine riga : righeDestinatario) {
			if(riga.getOds().getCodice().equalsIgnoreCase(rigaOrdine.getOds().getCodice())) {
				if(!riga.getId().equals(rigaOrdine.getId())) {
					isFirstOdsOfOrdine = false;
					break;
				}
			}
		}
		if(!isFirstOdsOfOrdine) {
			hasErrors = Boolean.TRUE;
			response.addApiError(MsgCpassOrd.ORDORDE0031.getError());
		}

		final BigDecimal diff = UtilityVerificheOrdine.getDeltaIvasRiga(request.getRigaOrdine());
		//Parametro paramTolleranza = systemDad.getParametro(nomeParametro, ConstantsCPassParametro.RiferimentoEnum.RIGA_ORDINE.getCostante(),settoreUtente.get(0).getEnte().getId());
		final Parametro  paramTolleranzaIva = systemDad.getParametro(ChiaveEnum.ORD_TOLLERANZA_IVA.name(), ConstantsCPassParametro.RiferimentoEnum.RIGA_ORDINE.getCostante(),ente.getId());
		final BigDecimal tolleranza         = new BigDecimal(paramTolleranzaIva.getValore());
		//final Double twoDecimalDiff = NumberUtility.arrotondaDueDecimali(diff.doubleValue());
		BigDecimal twoDecimalDiff = NumberUtility.arrotondaDueDec(diff);
		final int confronto = twoDecimalDiff.compareTo(tolleranza);

		if(request.getFlagBypassControlloIva() == null || !request.getFlagBypassControlloIva().equals(Boolean.TRUE)) {
			if(!twoDecimalDiff.equals(BigDecimal.ZERO)) {
				if(confronto > 0) {
					response.addApiError(MsgCpassOrd.ORDORDE0016.getError());
				} else {
					response.addApiError(MsgCpassOrd.ORDORDA0017.getError());
				}
			}
		}

		if(!hasErrors) {
			//controllo comunque che vi siano l'iva sia nella tolleranza prima di salvare
			checkBusinessCondition(confronto <= 0, MsgCpassOrd.ORDORDE0016.getError());
			rigaOrdine.setListinoFornitore(inserisciListino(ods));
			rigaOrdine = rigaOrdineDad.updateRigaOrdine(rigaOrdine);
			response.setRigaOrdine(rigaOrdine);
			if (response.getApiErrors().size() == 0) {
				UtilityRigaOrdine.aggiornamentoTotali(rigaOrdine, rigaOrdine.getDestinatario().getId(), testataOrdineDad, destinatarioDad, rigaOrdineDad);
			}
		}

		/*FIXME verificare se spostarlo nel BaseService*/
		if(!response.getApiErrors().isEmpty()) {
			serviceExceptionPreReturn();
			throw new BusinessException("BAD REQUEST",response.getApiErrors());
		}
	}

	private ListinoFornitore inserisciListino(Ods ods) {
		//Inseriso se serve il listinoFornitore
		ListinoFornitore listino = rigaOrdine.getListinoFornitore();
		if(listino!=null && listino.getId()!= null && listino.getId()==-1) {
			//TODO inserire listino
			final Fornitore fornitore = fornitoreDad.getRicercaFornitoreInterno( listino.getFornitore().getId());
			final ListinoFornitore listinopassato = rigaOrdine.getListinoFornitore();
			listinopassato.setId(null);
			listinopassato.setFornitore(fornitore);
			listinopassato.setOggettiSpesa(ods);
			listino = commonDad.saveListinoFornitore(rigaOrdine.getListinoFornitore());
		}else if(listino!=null && listino.getId()== null) {
			return null;
		}
		return listino;
	}
}
