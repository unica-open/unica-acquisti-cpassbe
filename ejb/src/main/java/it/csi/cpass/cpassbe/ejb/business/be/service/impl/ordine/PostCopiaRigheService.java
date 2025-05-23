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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import it.csi.cpass.cpassbe.ejb.business.be.dad.DecodificaDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.DestinatarioOrdineDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.ImpegnoDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.RigaOrdineDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.SettoreDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.TestataOrdineDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.base.BaseService;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.ordine.PostCopiaRigheRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.ordine.PostCopiaRigheResponse;
import it.csi.cpass.cpassbe.ejb.business.be.utility.UtilityImpegni;
import it.csi.cpass.cpassbe.ejb.business.be.utility.UtilityRigaOrdine;
import it.csi.cpass.cpassbe.ejb.business.be.utility.UtilityVerificheOrdine;
import it.csi.cpass.cpassbe.ejb.exception.NotFoundException;
import it.csi.cpass.cpassbe.ejb.external.ExternalHelperLookup;
import it.csi.cpass.cpassbe.ejb.mapper.CpassMappers;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;
import it.csi.cpass.cpassbe.lib.IntegrationConstants;
import it.csi.cpass.cpassbe.lib.dto.Ente;
import it.csi.cpass.cpassbe.lib.dto.Fornitore;
import it.csi.cpass.cpassbe.lib.dto.Impegno;
import it.csi.cpass.cpassbe.lib.dto.Provvedimento;
import it.csi.cpass.cpassbe.lib.dto.Settore;
import it.csi.cpass.cpassbe.lib.dto.Subimpegno;
import it.csi.cpass.cpassbe.lib.dto.error.MsgCpassOrd;
import it.csi.cpass.cpassbe.lib.dto.ord.Destinatario;
import it.csi.cpass.cpassbe.lib.dto.ord.FiltroImpegni;
import it.csi.cpass.cpassbe.lib.dto.ord.RigaOrdine;
import it.csi.cpass.cpassbe.lib.dto.ord.TestataOrdine;
import it.csi.cpass.cpassbe.lib.external.ImpegnoHelper;
import it.csi.cpass.cpassbe.lib.external.itf.ExternalServiceResolveWrapper;
import it.csi.cpass.cpassbe.lib.util.pagination.PagedList;

public class PostCopiaRigheService extends BaseService<PostCopiaRigheRequest, PostCopiaRigheResponse> {

	private final ExternalHelperLookup externalHelperLookup;
	private final RigaOrdineDad rigaOrdineDad;
	private final DestinatarioOrdineDad destinatarioDad;
	private final DecodificaDad decodificaDad;
	private final ImpegnoDad impegnoDad;
	private final SettoreDad settoreDad;
	private final TestataOrdineDad testataOrdineDad;
	private UUID idFrom;
	private UUID idTo;

	/**
	 * Constructor
	 *
	 * @param configurationHelper the configuration helper
	 * @param testataOrdineDad    the testataOrdine DAD
	 * @param decodificaDad       the decodifica DAD
	 */
	public PostCopiaRigheService(ConfigurationHelper configurationHelper, ExternalHelperLookup externalHelperLookup, RigaOrdineDad rigaOrdineDad, TestataOrdineDad testataOrdineDad,DestinatarioOrdineDad destinatarioDad,
			DecodificaDad decodificaDad, ImpegnoDad impegnoDad,SettoreDad settoreDad) {
		super(configurationHelper);
		this.externalHelperLookup 	= externalHelperLookup;
		this.rigaOrdineDad 			= rigaOrdineDad;
		this.destinatarioDad 		= destinatarioDad;
		this.decodificaDad 			= decodificaDad;
		this.impegnoDad 			= impegnoDad;
		this.settoreDad 			= settoreDad;
		this.testataOrdineDad 		= testataOrdineDad;
	}

	@Override
	protected void checkServiceParams() {
		idFrom = request.getIdFrom();
		idTo = request.getIdTo();

		checkNotNull(idFrom, "idFrom", Boolean.TRUE);
		checkNotNull(idTo, "idTo", Boolean.TRUE);
	}

	@Override
	protected void execute() {

		final List<RigaOrdine> scartate = new ArrayList<>();

		final List<RigaOrdine> righeOld = rigaOrdineDad.getRigheByDestinatario(request.getIdFrom());
		final Destinatario oldDestinatario = destinatarioDad.getDestinatario(idFrom).orElseThrow(() -> new NotFoundException("destinatario from"));
		final Destinatario newDestinatario = destinatarioDad.getDestinatario(idTo).orElseThrow(() -> new NotFoundException("destinatario to"));
		final TestataOrdine testataOrdine = oldDestinatario.getTestataOrdine();

		final Settore settore = oldDestinatario.getTestataOrdine().getSettore();
		final Ente ente = settore.getEnte();

		int newProgressivo = 0;
		List<Impegno> impegniSiac = null;

		for(final RigaOrdine rigaOrdine : righeOld) {
			final UUID idRiga = rigaOrdine.getId();
			if(!UtilityVerificheOrdine.rigaPerCopiaIsValid(rigaOrdine, decodificaDad)) {
				scartate.add(rigaOrdine);
				break;
			}
			newProgressivo++;
			RigaOrdine rigaOrdineInCopia = prepareToCopyRigaOrdine(rigaOrdine, newDestinatario, newProgressivo);

			// controllo sugli impegni
			final List<Impegno> impegniRiga = impegnoDad.getImpegniByRiga(idRiga);
			final List<Impegno> impegniToSave = new ArrayList<>();

			//mi occupo degli impegni se sono presenti
			if(impegniRiga != null && impegniRiga.size() > 0) {
				for (final Impegno impegno : impegniRiga) {
					if(impegniSiac == null) {
						impegniSiac = getImpegniSiacDefinitiviByImpegnoOrdine(testataOrdine,impegno,ente.getId()); //passo il primo impegno in quanto mi serve sapere solo il suo anno e numero provvedimento, comune a tutto l'ordine
					}
					if(impegniSiac!=null) {
						for (final Impegno impegnoSIAC : impegniSiac) {
							if (impegno.getAnnoEsercizio().equals(impegnoSIAC.getAnnoEsercizio()) && impegno.getAnno().equals(impegnoSIAC.getAnno()) && impegno.getNumero().equals(impegnoSIAC.getNumero())) {
								Impegno impegnoToSave = null;
								final BigDecimal disponibileSiac = UtilityImpegni.calcolaDisponibileAdOrdinare(impegnoDad, impegnoSIAC, ente.getId(), null); // testataOrdineId);

								// L’impegno deve essere definitivo e il disponibile ad ordinare deve essere maggiore, al più uguale, all'importo della riga
								if (disponibileSiac.compareTo(impegno.getImporto()) >= 0) {
									//copia del vecchio impegno ma la svuoto dai subimpegni (e aggiungerò solo quelli validati)
									impegnoToSave = impegno;
									//preparo una lista nuova di subimpegni validati per aggiungerla all'impegno che salverò
									final List<Subimpegno> subimpegniToSave = new ArrayList<>();
									for(final Subimpegno subimpegno : impegno.getSubimpegni()) {
										for(final Subimpegno subimpegnoSIAC : impegnoSIAC.getSubimpegni()) {
											if (subimpegno.getAnnoEsercizio().equals(subimpegnoSIAC.getAnnoEsercizio()) && subimpegno.getAnno().equals(subimpegnoSIAC.getAnno())&& subimpegno.getNumero().equals(subimpegnoSIAC.getNumero())) {
												final BigDecimal disponibileSubSiac = UtilityImpegni.calcolaDisponibileAdOrdinare(impegnoDad, impegnoSIAC, subimpegnoSIAC, ente.getId());
												// eseguo lo stesso controllo per i subimpegni
												final BigDecimal subImporto = subimpegno.getImporto() != null ? subimpegno.getImporto() : BigDecimal.ZERO;

												if(subimpegnoSIAC.getStato().equalsIgnoreCase(IntegrationConstants.IMPEGNO_STATO_DEFINITIVO) && disponibileSubSiac.compareTo(subImporto) >= 0) {
													// se passa i controlli aggiungo il subimpegno alla lista di subimpegni dell'impegno che sto copiando
													subimpegniToSave.add(subimpegno);
												}
												// interrompo il ciclo in cui ho trovato il subimpegno SIAC corrispondente al subimpegno in esame
												break;
											}
										}
									}
									//aggiungo i subimpegni validati (se non ve ne sono, la lista sarà vuota)
									impegnoToSave.setSubimpegni(subimpegniToSave);
									if(impegnoToSave != null) {
										impegniToSave.add(impegnoToSave);
									}
									// interrompo il ciclo in cui ho trovato l'impegno SIAC corrispondente all'impegno in esame
									break;
								}
							}
						}
					}
				}
			}

			rigaOrdineInCopia = rigaOrdineDad.saveRigaOrdine(rigaOrdineInCopia);
			UtilityRigaOrdine.aggiornamentoTotali(rigaOrdine, testataOrdine.getId(), testataOrdineDad,rigaOrdineDad);

			if(impegniToSave.size() > 0) {
				final Fornitore fornitore = rigaOrdineInCopia.getDestinatario().getTestataOrdine().getFornitore();
				impegnoDad.insertImpegni(rigaOrdineInCopia, impegniToSave, CpassMappers.FORNITORE.toEntity(fornitore), CpassMappers.ENTE.toEntity(ente));
			}
		}

		if(righeOld != null && righeOld.size() > 0) {
			checkBusinessCondition(righeOld.size() > scartate.size(), MsgCpassOrd.ORDORDE0035.getError());
			if(scartate.size() > 0 && !(righeOld.size() <= scartate.size())) {
				response.addApiError(MsgCpassOrd.ORDORDP0036.getError());
			}
		}

		final List<RigaOrdine> refreshedRighe = rigaOrdineDad.getRigheByDestinatario(request.getIdTo());
		//TODO aggiornare gli importi della testata
		response.setRigheOrdine(refreshedRighe);
	}

	private List<Impegno> getImpegniSiacDefinitiviByImpegnoOrdine(TestataOrdine testataOrdine,Impegno impegno,UUID enteId) {
		final Impegno impegnoFiltro = new Impegno();
		impegnoFiltro.setAnnoProvvedimento(impegno.getAnnoProvvedimento());
		impegnoFiltro.setNumeroProvvedimento(impegno.getNumeroProvvedimento());
		//TODO capire se ci arriva il valore dal FE
		//impegnoFiltro.setCodiceTipoProvvedimento(impegno.getCodiceTipoProvvedimento());
		//impegnoFiltro.setSettoreProvvedimento(impegno.getSettoreProvvedimento());
		final Subimpegno subimpegnoFiltro = new Subimpegno();
		subimpegnoFiltro.setImpegno(impegnoFiltro);
		final FiltroImpegni filtroImpegni = new FiltroImpegni();
		filtroImpegni.setSubimpegno(subimpegnoFiltro);
		filtroImpegni.setStatoImpegno(IntegrationConstants.IMPEGNO_STATO_DEFINITIVO);
		filtroImpegni.setTestataOrdine(testataOrdine);
		final ExternalServiceResolveWrapper<ImpegnoHelper> handler = externalHelperLookup.lookup(ImpegnoHelper.class, enteId);

		arricchisciProvvedimentocolSettore(filtroImpegni,enteId);

		final PagedList<Impegno> pagedListImpegni = invokeExternalService(handler,() -> handler.getInstance().getImpegniEsterni(handler.getParams(), filtroImpegni, 1, 0,Boolean.TRUE,Boolean.FALSE));
		return pagedListImpegni != null ? pagedListImpegni.getList() : null;
	}

	private RigaOrdine prepareToCopyRigaOrdine(RigaOrdine oldRiga, Destinatario newDestinatario, int newProgressivo) {
		final RigaOrdine newRiga = oldRiga;
		newRiga.setId(null);
		newRiga.setDestinatario(newDestinatario);
		newRiga.setOptlock(null);
		newRiga.setProgressivo(newProgressivo);
		newRiga.setDataCreazione(null);
		newRiga.setDataModifica(null);
		newRiga.setDataCancellazione(null);
		newRiga.setUtenteCancellazione(null);
		newRiga.setUtenteCreazione(null);
		newRiga.setUtenteModifica(null);
		newRiga.setUtenteCancellazione(null);
		return newRiga;
	}

	private void arricchisciProvvedimentocolSettore(FiltroImpegni filtroImpegni , UUID enteId) {
		if(    filtroImpegni.getTestataOrdine()!=null
				&& filtroImpegni.getTestataOrdine().getProvvedimento()!=null
				&& filtroImpegni.getTestataOrdine().getProvvedimento().getSettore()!=null
				&& filtroImpegni.getTestataOrdine().getProvvedimento().getSettore().getCodice()!=null
				) {
			final Provvedimento provvedimento = filtroImpegni.getTestataOrdine().getProvvedimento();
			final String codiceSettore = provvedimento.getSettore().getCodice();
			final Optional<Settore> settore = settoreDad.findByCodice (codiceSettore,enteId,false);
			if(settore.isPresent()) {
				provvedimento.setSettore(settore.get());
			}
			filtroImpegni.getTestataOrdine().setProvvedimento(provvedimento);
		}
	}
}
