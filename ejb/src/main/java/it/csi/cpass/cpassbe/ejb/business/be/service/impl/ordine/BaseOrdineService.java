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
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import it.csi.cpass.cpassbe.ejb.business.be.dad.DecodificaDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.DestinatarioOrdineDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.ImpegnoDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.RdaDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.RigaOrdineDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.RmsDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.ScaricoMepaTestataDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.SettoreDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.SystemDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.TestataOrdineDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.UtenteDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.base.BaseService;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.base.BaseRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.base.BaseResponse;
import it.csi.cpass.cpassbe.ejb.business.be.utility.UtilityImpegni;
import it.csi.cpass.cpassbe.ejb.exception.NotFoundException;
import it.csi.cpass.cpassbe.ejb.external.ExternalHelperLookup;
import it.csi.cpass.cpassbe.ejb.mapper.CpassMappers;
import it.csi.cpass.cpassbe.ejb.util.ConstantsCPassParametro;
import it.csi.cpass.cpassbe.ejb.util.ConstantsCPassParametro.ChiaveEnum;
import it.csi.cpass.cpassbe.ejb.util.ConstantsCPassStato;
import it.csi.cpass.cpassbe.ejb.util.ConstantsCPassStato.StatoOrdineMepaEnum;
import it.csi.cpass.cpassbe.ejb.util.ConstantsCPassStato.StatoRigaRdaEnum;
import it.csi.cpass.cpassbe.ejb.util.ConstantsCPassStato.StatoRigaRmsEnum;
import it.csi.cpass.cpassbe.ejb.util.ConstantsCpassOrdTipoOrdine;
import it.csi.cpass.cpassbe.ejb.util.NumberUtility;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;
import it.csi.cpass.cpassbe.lib.IntegrationConstants;
import it.csi.cpass.cpassbe.lib.dto.ApiError;
import it.csi.cpass.cpassbe.lib.dto.Ente;
import it.csi.cpass.cpassbe.lib.dto.FiltroFornitore;
import it.csi.cpass.cpassbe.lib.dto.Fornitore;
import it.csi.cpass.cpassbe.lib.dto.Impegno;
import it.csi.cpass.cpassbe.lib.dto.Parametro;
import it.csi.cpass.cpassbe.lib.dto.Provvedimento;
import it.csi.cpass.cpassbe.lib.dto.Settore;
import it.csi.cpass.cpassbe.lib.dto.Stato;
import it.csi.cpass.cpassbe.lib.dto.Subimpegno;
import it.csi.cpass.cpassbe.lib.dto.Utente;
import it.csi.cpass.cpassbe.lib.dto.error.MsgCpassOrd;
import it.csi.cpass.cpassbe.lib.dto.ord.Destinatario;
import it.csi.cpass.cpassbe.lib.dto.ord.FiltroImpegni;
import it.csi.cpass.cpassbe.lib.dto.ord.ImpegnoAssociato;
import it.csi.cpass.cpassbe.lib.dto.ord.RigaOrdine;
import it.csi.cpass.cpassbe.lib.dto.ord.SubImpegnoAssociato;
import it.csi.cpass.cpassbe.lib.dto.ord.TestataOrdine;
import it.csi.cpass.cpassbe.lib.dto.ord.mepa.ScaricoMepaTestata;
import it.csi.cpass.cpassbe.lib.dto.ord.rda.RigaRda;
import it.csi.cpass.cpassbe.lib.dto.ord.rda.TestataRda;
import it.csi.cpass.cpassbe.lib.dto.rms.RigaRms;
import it.csi.cpass.cpassbe.lib.dto.rms.RmsStatiRigaRms;
import it.csi.cpass.cpassbe.lib.external.FornitoreHelper;
import it.csi.cpass.cpassbe.lib.external.ImpegnoHelper;
import it.csi.cpass.cpassbe.lib.external.itf.ExternalServiceResolveWrapper;
import it.csi.cpass.cpassbe.lib.util.pagination.PagedList;
import it.csi.cpass.cpassbe.lib.util.threadlocal.CpassThreadLocalContainer;

/**
 * Base class for services for /testataOrdine path
 * @param <Q> the request type
 * @param <R> the response type
 */
public abstract class BaseOrdineService<Q extends BaseRequest, R extends BaseResponse> extends BaseService<Q, R> {

	/** Data Access Delegate for TestataOrdine */
	protected final TestataOrdineDad testataOrdineDad;
	protected SettoreDad settoreDad;
	/**
	 * Constructor
	 * @param configurationHelper the configuration helper
	 * @param testataOrdineDad the testataOrdine dad
	 */
	protected BaseOrdineService(ConfigurationHelper configurationHelper, TestataOrdineDad testataOrdineDad,SettoreDad settoreDad) {
		super(configurationHelper);
		this.testataOrdineDad = testataOrdineDad;
		this.settoreDad =  settoreDad;
	}

	protected List<ApiError> checkFornitoreValido(ExternalHelperLookup externalHelperLookup, TestataOrdine testataOrdine,UUID enteId) {
		final List<ApiError> apiErrors = new ArrayList<>();

		final FiltroFornitore filtroFornitore = new FiltroFornitore();
		filtroFornitore.setFornitore(testataOrdine.getFornitore());
		filtroFornitore.setStatoFornitore(IntegrationConstants.SOGGETTO_STATO_VALIDO);

		final ExternalServiceResolveWrapper<FornitoreHelper> handlerFornitore = externalHelperLookup.lookup(FornitoreHelper.class,enteId);
		final List<Fornitore> fornitori = invokeExternalService(handlerFornitore,() -> handlerFornitore.getInstance().getFornitori(handlerFornitore.getParams(), filtroFornitore));
		if (fornitori == null || fornitori.size() == 0) {
			apiErrors.add(MsgCpassOrd.ORDORDE0004.getError());
		}
		return apiErrors;
	}

	protected void inserimentoAutomaticoFinanziari(RigaOrdine rigaOrdine, DestinatarioOrdineDad destinatarioDad, RigaOrdineDad rigaOrdineDad,UtenteDad utenteDad,
			SystemDad systemDad,ImpegnoDad impegnoDad, ExternalHelperLookup externalHelperLookup) {
		//inserimento automatico finanziario ordine
		final Destinatario destinatarioRiga = destinatarioDad.getDestinatario(rigaOrdine.getDestinatario().getId()).orElseThrow(() -> new NotFoundException("destinatario riga"));
		rigaOrdine.setDestinatario(destinatarioRiga);
		final List<ImpegnoAssociato> impegniAssociati = rigaOrdineDad.getImpegnoAssociatoOrdine(rigaOrdine.getDestinatario().getTestataOrdine().getId());

		if(impegniAssociati.isEmpty()) {
			return;
		}

		final List<Impegno> validi = new ArrayList<>();
		List<Impegno> impegniRiga = new ArrayList<>();

		final String nomeParametro = ChiaveEnum.ASSOC_IMPEGNI_ORD.name();
		final Utente utenteConnesso = CpassThreadLocalContainer.UTENTE_CONNESSO.get();
		final List<Settore> settoreUtente = utenteDad.getSettoriByUtente(utenteConnesso.getId());
		final Ente ente = settoreUtente.get(0).getEnte();
		final UUID enteId = ente.getId();
		final Parametro assImpOrd = systemDad.getParametro(nomeParametro, ConstantsCPassParametro.RiferimentoEnum.IMPEGNO.getCostante(), enteId);
		List<Impegno> impegniSiac = null;

		//validazione
		for(final ImpegnoAssociato impegnoAssociato : impegniAssociati) {
			final Impegno tempImpegno = impegnoAssociato.getImpegno();
			if (impegniSiac == null) {
				// Cerco l'impegno aggiornato su SIAC
				// cerco gli impegno su SIAC solo se vi sono impegni associati e solo la prima volta, al secondo ciclo utilizzo gli impegni già cercati,
				// sulla base dell'anno e numero provvedimento
				impegniSiac = getImpegniSiacDefinitiviByImpegnoOrdine(tempImpegno,rigaOrdine.getDestinatario().getTestataOrdine(),externalHelperLookup,enteId);
			}

			//Cerco l'impegno tra l'elenco degli impegni siac (già filtrati per stato definitivo)
			Impegno tempSiac =null;
			if (impegniSiac != null) {
				tempSiac = impegniSiac.stream().filter(x -> x.getAnno().equals(tempImpegno.getAnno()) && x.getNumero().equals(tempImpegno.getNumero())&& x.getAnnoEsercizio().equals(tempImpegno.getAnnoEsercizio())).findAny().orElse(null);
			}

			if (tempSiac==null) {
				continue;
			}

			// L’impegno non deve essere già associato alla riga
			// ottento prima la lista aggiornata di impegni associati alla riga
			impegniRiga = impegnoDad.getImpegniByRiga(rigaOrdine.getId());
			final Impegno impRiga = impegniRiga.stream().filter(x -> x.getAnno().equals(tempImpegno.getAnno()) && x.getNumero().equals(tempImpegno.getNumero()) && x.getAnnoEsercizio().equals(tempImpegno.getAnnoEsercizio())).findAny().orElse(null);
			if (impRiga != null) {
				continue;
			}

			// Il disponibile ad ordinare deve essere maggiore di 0
			final BigDecimal disponibileAOrdinare = UtilityImpegni.calcolaDisponibileAdOrdinare(impegnoDad, tempSiac, enteId, null);
			if(disponibileAOrdinare.compareTo(BigDecimal.ZERO) <= 0) {
				continue;
			} else {
				tempImpegno.setDisponibile(disponibileAOrdinare);
			}

			// inizio il controllo sul subimpegno
			// svuolo la lista subimpegni del tempImpegno e la popolo solo con quelli validi
			tempImpegno.setSubimpegni(new ArrayList<>());
			boolean bAddImpegnoValido = Boolean.TRUE;
			if (impegnoAssociato.getSubimpegniAssociati() != null && impegnoAssociato.getSubimpegniAssociati().size() > 0) {
				// se è presente un subimpegno ed esso non viene inserito per problemi sul fornitore,
				// allora non deve essere inserito neanche il record dell'impegno
				bAddImpegnoValido = false;
				for (final SubImpegnoAssociato subimpegnoAssociato : impegnoAssociato.getSubimpegniAssociati()) {
					// Il subimpegno non deve essere già associato alla riga
					boolean subimpegnoExists = false;
					for (final Impegno impegnoRiga : impegniRiga) {
						if (impegnoRiga.getAnno().equals(subimpegnoAssociato.getAnnoSubImpegno())
								&& impegnoRiga.getNumero().equals(subimpegnoAssociato.getNumeroSubImpegno())
								&& impegnoRiga.getAnnoEsercizio().equals(subimpegnoAssociato.getAnnoEsercizio())) {
							subimpegnoExists = Boolean.TRUE;
							continue;
						}
						for (final Subimpegno subimpegnoRiga : impegnoRiga.getSubimpegni()) {
							if (subimpegnoRiga.getAnno().equals(subimpegnoAssociato.getAnnoSubImpegno())
									&& subimpegnoRiga.getNumero().equals(subimpegnoAssociato.getNumeroSubImpegno())
									&& subimpegnoRiga.getAnnoEsercizio().equals(subimpegnoAssociato.getAnnoEsercizio())) {
								subimpegnoExists = true;
								continue;
							}
						}
					}

					if (!subimpegnoExists) {
						// Se si tratta di subimpegno, esso deve essere collegato al fornitore dell’ordine
						final Subimpegno tempSIFromAssociato = impegnoDad.getById(subimpegnoAssociato.getSubImpegno().getId());
						if (tempSIFromAssociato!=null && tempSIFromAssociato.getFornitore() != null && tempSIFromAssociato.getFornitore().getCodice()
								.equals(rigaOrdine.getDestinatario().getTestataOrdine().getFornitore().getCodice())) {
							// se il subimpegno è valido lo aggiungo al tempImpegno valido
							tempImpegno.getSubimpegni().add(subimpegnoAssociato.getSubImpegno());
							bAddImpegnoValido = Boolean.TRUE;
						}
					}
				}
			}
			if (bAddImpegnoValido) {
				validi.add(tempImpegno);
			}
		}

		//BigDecimal residuoDaAssegnare = NumberUtility.arrotondaDueDec(rigaOrdine.getImportoTotale());
		BigDecimal subResiduo = NumberUtility.arrotondaDueDec(rigaOrdine.getImportoTotale());

		final List<Impegno> impegniToSave = new ArrayList<>();
		if(assImpOrd != null && assImpOrd.getValore().equals("DISP_CRESC")) {
			riordinoImpegni(validi);
		}
		warnBusinessCondition(validi != null && validi.size() > 0, MsgCpassOrd.ORDORDI0024.getError());//"riga",rigaOrdine));
		for(final Impegno impegnoToSave : validi) {
			// assegno il residuo ai vari impegni
			//if(impegnoToSave.getDisponibile().compareTo(residuoDaAssegnare) < 0) {
			if(impegnoToSave.getDisponibile().compareTo(subResiduo) < 0) {
				final BigDecimal importo = impegnoToSave.getDisponibile();
				impegnoToSave.setImporto(importo);
				if(impegnoToSave.getSubimpegni().isEmpty()) {
					//residuoDaAssegnare = residuoDaAssegnare.subtract(importo);
					subResiduo = subResiduo.subtract(importo);

				} else {
					//residuoDaAssegnare = assegnaImportiASubimpegni(impegnoToSave, subResiduo, enteId, impegnoDad);
					subResiduo = assegnaImportiASubimpegni(impegnoToSave, subResiduo, enteId, impegnoDad);
				}

				// assegno l'importo ai subimpegni
				//BigDecimal subResiduo = new BigDecimal(importo.doubleValue());

				impegniToSave.add(impegnoToSave);
			} else {
				impegnoToSave.setImporto(subResiduo);
				//non rimuovere, all'interno del metodo viene settato importo dei subimp.
				subResiduo = assegnaImportiASubimpegni(impegnoToSave, subResiduo, enteId, impegnoDad);
				subResiduo = BigDecimal.ZERO;
				/*impegnoToSave.setImporto(residuoDaAssegnare);
				residuoDaAssegnare = assegnaImportiASubimpegni(impegnoToSave, residuoDaAssegnare, enteId, impegnoDad);
				residuoDaAssegnare = BigDecimal.ZERO;*/
				impegniToSave.add(impegnoToSave);
				break;
			}
		}
		final Fornitore fornitore = rigaOrdine.getDestinatario().getTestataOrdine().getFornitore();
		impegnoDad.insertImpegni(rigaOrdine, impegniToSave, CpassMappers.FORNITORE.toEntity(fornitore), CpassMappers.ENTE.toEntity(ente));
		//gli impegni inseriti sono stati salvati tutti ma non è stato consumato l'intero disponibile, lancio un warning 2021/5 ordine 2020 / 7 impegno
		/*BigDecimal residuoDaAssegnareRound = NumberUtility.arrotondaDueDec(residuoDaAssegnare == null ? BigDecimal.ZERO : residuoDaAssegnare);
		warnBusinessCondition(residuoDaAssegnare.compareTo(BigDecimal.ZERO) == 0, MsgCpassOrd.ORDORDI0025.getError("residuo",residuoDaAssegnareRound.toString()));*/
		final BigDecimal residuoDaAssegnareRound = NumberUtility.arrotondaDueDec(subResiduo == null ? BigDecimal.ZERO : subResiduo);
		warnBusinessCondition(residuoDaAssegnareRound.compareTo(BigDecimal.ZERO) == 0, MsgCpassOrd.ORDORDI0025.getError("residuo",residuoDaAssegnareRound.toString()));
	}


	/**
	 *
	 * @param impegno
	 * @return List<Impegno>
	 */
	private List<Impegno> getImpegniSiacDefinitiviByImpegnoOrdine(Impegno impegno, TestataOrdine testataOrdine, ExternalHelperLookup externalHelperLookup,UUID enteId) {
		final Impegno impegnoFiltro = new Impegno();
		impegnoFiltro.setAnnoProvvedimento(impegno.getAnnoProvvedimento());
		impegnoFiltro.setNumeroProvvedimento(impegno.getNumeroProvvedimento());
		final Subimpegno subimpegnoFiltro = new Subimpegno();
		subimpegnoFiltro.setImpegno(impegnoFiltro);
		final FiltroImpegni filtroImpegni = new FiltroImpegni();
		filtroImpegni.setSubimpegno(subimpegnoFiltro);
		filtroImpegni.setStatoImpegno(IntegrationConstants.IMPEGNO_STATO_DEFINITIVO);
		filtroImpegni.setTestataOrdine(testataOrdine);
		final ExternalServiceResolveWrapper<ImpegnoHelper> handler = externalHelperLookup.lookup(ImpegnoHelper.class,enteId);
		arricchisciProvvedimentocolSettore( filtroImpegni ,  enteId);
		final PagedList<Impegno> pagedListImpegni = invokeExternalService(handler,() -> handler.getInstance().getImpegniEsterni(handler.getParams(), filtroImpegni, 1, 0,Boolean.TRUE,Boolean.TRUE));
		return pagedListImpegni != null ? pagedListImpegni.getList() : new ArrayList<>();
	}

	/**
	 * @param validi
	 */
	private void riordinoImpegni(List<Impegno> validi) {
		validi.sort(new Comparator<Impegno>() {
			@Override
			public int compare(Impegno o1, Impegno o2) {
				if(!o1.getDisponibile().equals(o2.getDisponibile())) {
					return o1.getDisponibile().compareTo(o2.getDisponibile());
				} else {
					if(!o1.getAnno().equals(o2.getAnno())) {
						return o1.getAnno().compareTo(o2.getAnno());
					} else {
						return o1.getNumero().compareTo(o2.getNumero());
					}
				}
			}
		});
	}
	private BigDecimal assegnaImportiASubimpegni(Impegno impegno, BigDecimal subResiduo, UUID enteId, ImpegnoDad impegnoDad) {
		for(final Subimpegno subimpegno : impegno.getSubimpegni()) {
			final BigDecimal disponibile = UtilityImpegni.calcolaDisponibileAdOrdinare(impegnoDad, impegno, subimpegno, enteId);
			subimpegno.setDisponibile(disponibile);
			if(subimpegno.getDisponibile().compareTo(subResiduo) < 0) {
				subimpegno.setImporto(subimpegno.getDisponibile());
				subResiduo = subResiduo.subtract(subimpegno.getDisponibile());

			} else {
				subimpegno.setImporto(subResiduo);
				subResiduo = BigDecimal.ZERO;
				break;

			}
		}
		return subResiduo;
	}

	/**
	 * Elimina l'eventuale relazione della testata con l'rda, e riporta allo stato corretto righe rms e righe rda
	 * @param testataOrdine
	 * @param DecodificaDad decodificaDad
	 * @param RdaDad rdaDad
	 * @param RmsDad rmsDad
	 * */
	protected void scollegaRdaDaOrdine (TestataOrdine testataOrdine, DecodificaDad decodificaDad, RdaDad rdaDad, RmsDad rmsDad) {
		final Utente utenteConnesso = CpassThreadLocalContainer.UTENTE_CONNESSO.get();
		final List<TestataRda> listTestataRda = testataOrdineDad.getTestataRdaByTestataOrdineId(testataOrdine.getId());
		if (!listTestataRda.isEmpty()) {
			final Stato dae = isEntityPresent(() -> decodificaDad.getStatoOpt(StatoRigaRdaEnum.DAE.getCostante(),ConstantsCPassStato.TipoStatoEnum.RIGA_RDA.getCostante()), "stato riga rda");
			final Stato wip = isEntityPresent(() -> decodificaDad.getStatoOpt(StatoRigaRmsEnum.WIP.getCostante(),ConstantsCPassStato.TipoStatoEnum.RIGA_RMS.getCostante()), "stato riga rms");
			final Stato evp = isEntityPresent(() -> decodificaDad.getStatoOpt(StatoRigaRmsEnum.EVP.getCostante(),ConstantsCPassStato.TipoStatoEnum.RIGA_RMS.getCostante()), "stato riga rms");

			for(final TestataRda testataRda : listTestataRda) { // relazione rda/ordine 1:1 sebbene esista una tabella di relazione.
				for (final RigaRda rigaRda : testataRda.getRigaRda()) {
					rigaRda.setStato(dae);
					final List<RigaRms> listRigaRms = rmsDad.getRigaRmsByRigaRdaId(rigaRda.getId());
					rigaRda.setRigaRms(listRigaRms);
					rdaDad.updateRigaRda(rigaRda);
				}

				final List<RigaRms> listRigaRms = testataRda.getRigaRda().stream().flatMap(x -> x.getRigaRms().stream()).collect(Collectors.toList());

				for(final RigaRms rigaRms : listRigaRms) {


					final BigDecimal qtaSuOrd = rmsDad.getQuantitaEvasaSuAltriOrdini(rigaRms.getId(), testataOrdine.getId());
					if(qtaSuOrd.equals(BigDecimal.ZERO)) {
						rigaRms.setStato(wip);
					}else {
						rigaRms.setStato(evp);
					}
					rmsDad.updateRigaRms(rigaRms);

					final RmsStatiRigaRms statiRigaRms = new RmsStatiRigaRms();
					statiRigaRms.setRigaRms(rigaRms);
					statiRigaRms.setStato(rigaRms.getStato().getDescrizione());
					statiRigaRms.setDataModifica(new Date());
					statiRigaRms.setUtenteModifica(utenteConnesso.getUtenteModifica());
					rmsDad.insertStatiRigaRms(statiRigaRms);

				}
				testataOrdineDad.deleteRelRdaOrdine(testataOrdine.getId(), testataRda.getId());
			}
		}
	}

	protected void 	ripristinaScaricoMepaTestata(TestataOrdine testataOrdine, ScaricoMepaTestataDad scaricoMepaTestataDad, DecodificaDad decodificaDad)	{
		if (testataOrdine.getTipoOrdine().getTipologiaDocumentoCodice()
				.equals(ConstantsCpassOrdTipoOrdine.TipoOrdine.MEPA.getCostante()) &&
				testataOrdine.getScaricoMepaTestata() != null) {
			final ScaricoMepaTestata scaricoMepaTestata = testataOrdine.getScaricoMepaTestata();
			scaricoMepaTestata.setStato(decodificaDad.getStatoOpt(StatoOrdineMepaEnum.DA_CARICARE.getCostante(), ConstantsCPassStato.TipoStatoEnum.ORDINE_MEPA.getCostante()).orElse(null));
			scaricoMepaTestataDad.updateScaricoMepaTestata(scaricoMepaTestata);
		}
	}

	/**
	 *
	 * @param filtroImpegni
	 * @param enteId
	 */
	protected void arricchisciProvvedimentocolSettore(FiltroImpegni filtroImpegni , UUID enteId) {
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
			}else {
				log.warn("arricchisciProvvedimentocolSettore", "**********************************************************************************************************");
				log.warn("arricchisciProvvedimentocolSettore", "**********************************************************************************************************");
				log.warn("arricchisciProvvedimentocolSettore", "SETTORE NON TROVATO - NON VALIDO - NON ASSOCIASTO ALL'ENTE CORRETTO " +codiceSettore +" ENTE_ID -->"+enteId);
				log.warn("arricchisciProvvedimentocolSettore", "**********************************************************************************************************");
				log.warn("arricchisciProvvedimentocolSettore", "**********************************************************************************************************");
			}
			filtroImpegni.getTestataOrdine().setProvvedimento(provvedimento);
		}
	}
}
