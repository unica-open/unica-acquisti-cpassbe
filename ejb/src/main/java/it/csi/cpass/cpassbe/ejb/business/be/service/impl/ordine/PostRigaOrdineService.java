/*-
 * ========================LICENSE_START=================================
 * CPASS BackEnd - EJB submodule
 * %%
 * Copyright (C) 2019 - 2020 CSI Piemonte
 * %%
 * SPDX-FileCopyrightText: Copyright 2019 - 2020 | CSI Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 * =========================LICENSE_END==================================
 */
package it.csi.cpass.cpassbe.ejb.business.be.service.impl.ordine;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import it.csi.cpass.cpassbe.ejb.business.be.dad.CommonDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.DecodificaDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.DestinatarioOrdineDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.ImpegnoDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.RigaOrdineDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.SystemDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.TestataOrdineDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.UtenteDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.UtilityImpegni;
import it.csi.cpass.cpassbe.ejb.business.be.service.UtilityRigaOrdine;
import it.csi.cpass.cpassbe.ejb.business.be.service.UtilityVerificheOrdine;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.base.BaseService;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.ordine.PostRigaOrdineRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.ordine.PostRigaOrdineResponse;
import it.csi.cpass.cpassbe.ejb.external.ExternalHelperLookup;
import it.csi.cpass.cpassbe.ejb.mapper.CpassMappers;
import it.csi.cpass.cpassbe.ejb.util.ConstantsCPassParametro.ChiaveEnum;
import it.csi.cpass.cpassbe.ejb.util.ConstantsCPassParametro;
import it.csi.cpass.cpassbe.ejb.util.CpassThreadLocalContainer;
import it.csi.cpass.cpassbe.ejb.util.UtilityArrotondamento;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;
import it.csi.cpass.cpassbe.lib.IntegrationConstants;
import it.csi.cpass.cpassbe.lib.dto.AliquoteIva;
import it.csi.cpass.cpassbe.lib.dto.ApiError;
import it.csi.cpass.cpassbe.lib.dto.Cpv;
import it.csi.cpass.cpassbe.lib.dto.Ente;
import it.csi.cpass.cpassbe.lib.dto.Fornitore;
import it.csi.cpass.cpassbe.lib.dto.Impegno;
import it.csi.cpass.cpassbe.lib.dto.ListinoFornitore;
import it.csi.cpass.cpassbe.lib.dto.Parametro;
import it.csi.cpass.cpassbe.lib.dto.Settore;
import it.csi.cpass.cpassbe.lib.dto.Subimpegno;
import it.csi.cpass.cpassbe.lib.dto.UnitaMisura;
import it.csi.cpass.cpassbe.lib.dto.Utente;
import it.csi.cpass.cpassbe.lib.dto.error.MsgCpassOrd;
import it.csi.cpass.cpassbe.lib.dto.ord.Destinatario;
import it.csi.cpass.cpassbe.lib.dto.ord.FiltroImpegni;
import it.csi.cpass.cpassbe.lib.dto.ord.ImpegnoAssociato;
import it.csi.cpass.cpassbe.lib.dto.ord.OggettiSpesa;
import it.csi.cpass.cpassbe.lib.dto.ord.RigaOrdine;
import it.csi.cpass.cpassbe.lib.dto.ord.SubImpegnoAssociato;
import it.csi.cpass.cpassbe.lib.external.ImpegnoHelper;
import it.csi.cpass.cpassbe.lib.external.itf.ExternalServiceResolveWrapper;
import it.csi.cpass.cpassbe.lib.util.pagination.PagedList;

public class PostRigaOrdineService extends BaseService<PostRigaOrdineRequest, PostRigaOrdineResponse> {

	private final ExternalHelperLookup externalHelperLookup;
	private final DestinatarioOrdineDad destinatarioDad;
	private final RigaOrdineDad rigaOrdineDad;
	private final DecodificaDad decodificaDad;
	private final ImpegnoDad impegnoDad;
	private final SystemDad systemDad;
	private final UtenteDad utenteDad;
	private final CommonDad commonDad;
	private final TestataOrdineDad testataOrdineDad;
	private RigaOrdine rigaOrdine;

	/**
	 * Constructor
	 * 
	 * @param configurationHelper the configuration helper
	 * @param testataOrdineDad    the testataOrdine DAD
	 * @param decodificaDad       the decodifica DAD
	 */
	public PostRigaOrdineService(ConfigurationHelper configurationHelper, ExternalHelperLookup externalHelperLookup, RigaOrdineDad rigaOrdineDad, DestinatarioOrdineDad destinatarioDad, DecodificaDad decodificaDad, ImpegnoDad impegnoDad,
			SystemDad systemDad, UtenteDad utenteDad, CommonDad commonDad, TestataOrdineDad testataOrdineDad) {
		super(configurationHelper);
		this.externalHelperLookup = externalHelperLookup;
		this.rigaOrdineDad = rigaOrdineDad;
		this.destinatarioDad = destinatarioDad;
		this.decodificaDad = decodificaDad;
		this.impegnoDad = impegnoDad;
		this.utenteDad = utenteDad;
		this.systemDad = systemDad;
		this.commonDad = commonDad;
		this.testataOrdineDad = testataOrdineDad;
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
		final String methodName = "execute";
		boolean hasErrors = false;
		//Fornitore fornitore = rigaOrdine.getDestinatario().getTestataOrdine().getFornitore();
		
		//controllo che esista il cpv
		Cpv cpv = decodificaDad.getCpvByCodice(rigaOrdine.getOds().getCpv().getCodice());
		if(cpv == null) {
			hasErrors = true;
			response.addApiError(MsgCpassOrd.ORDORDE0011.getError());
			return;
		}

		//controllo che esista l'ods
		OggettiSpesa ods = decodificaDad.getOdsByCodice(rigaOrdine.getOds().getCodice());
		if(ods == null) {
			hasErrors = true;
			response.addApiError(MsgCpassOrd.ORDORDE0012.getError());
			return;
		}

		// controllo congruenza tra i due
		if(!ods.getCpv().getCodice().equals(cpv.getCodice())) {
			hasErrors = true;
			response.addApiError(MsgCpassOrd.ORDORDE0013.getError());
		}

		// controllo validita unita di misura
		List<UnitaMisura> unitaMisuraValid = decodificaDad.getUnitaMisura();
		boolean isUmValid = false;
		for(UnitaMisura um : unitaMisuraValid) {
			if(um.getCodice().equals(rigaOrdine.getUnitaMisura().getCodice())) {
				isUmValid = true;
				break;
			}
		}
		if(!isUmValid) {
			hasErrors = true;
			response.addApiError(MsgCpassOrd.ORDORDE0014.getError());
		}

		// controllo validita aliquotaIva
		List<AliquoteIva> aliquotaValid = decodificaDad.getAliquoteIva();
		boolean isAIValid = false;
		for(AliquoteIva ai : aliquotaValid) {
			if(ai.getCodice().equals(rigaOrdine.getAliquoteIva().getCodice())) {
				isAIValid = true;
				break;
			}
		}
		if(!isAIValid) {
			hasErrors = true;
			response.addApiError(MsgCpassOrd.ORDORDE0015.getError());
		}

		//controllo quantita
		if(rigaOrdine.getQuantita().compareTo(BigDecimal.ZERO) < 1) {
			hasErrors = true;
			response.addApiError(MsgCpassOrd.ORDORDE0018.getError());
		}

		//controllo prezzoUnitario
		BigDecimal pU = rigaOrdine.getPrezzoUnitario();
		if(pU.compareTo(BigDecimal.ZERO) < 1) {
			hasErrors = true;
			response.addApiError(MsgCpassOrd.ORDORDE0019.getError());
		}
		
		//controllo importoNetto
		BigDecimal netto = rigaOrdine.getImportoNetto();
		if(netto.compareTo(BigDecimal.ZERO) < 1) {
			hasErrors = true;
			response.addApiError(MsgCpassOrd.ORDORDE0067.getError());
		}
		
		//controllo importoTotale
		BigDecimal totale = rigaOrdine.getImportoTotale();
		if(totale.compareTo(BigDecimal.ZERO) < 1) {
			hasErrors = true;
			response.addApiError(MsgCpassOrd.ORDORDE0068.getError());
		}

		//controllo che l'ods della riga ordine non sia già assegnato ad un altra riga ordine dello stesso ordine
		List<RigaOrdine> righeDestinatario = rigaOrdineDad.getRigheByDestinatario(rigaOrdine.getDestinatario().getId());
		boolean isFirstOdsOfOrdine = true;
		for(RigaOrdine riga : righeDestinatario) {
			if(riga.getOds().getCodice().equalsIgnoreCase(rigaOrdine.getOds().getCodice())) {
				isFirstOdsOfOrdine = false;
				break;
			}
		}
		if(!isFirstOdsOfOrdine) {
			hasErrors = true;
			response.addApiError(MsgCpassOrd.ORDORDE0031.getError());
		}
				
		if(!hasErrors) {
		
			//controllo diff iva
			BigDecimal diff = UtilityVerificheOrdine.getDeltaIvasRiga(request.getRigaOrdine());
			Utente utenteConnesso = CpassThreadLocalContainer.UTENTE_CONNESSO.get();
			List<Settore> settoreUtente = utenteDad.getSettoriByUtente(utenteConnesso.getId());
			
			String nomeParametro = ChiaveEnum.ORD_TOLLERANZA_IVA.name();
			Parametro paramTolleranza = systemDad.getParametro(nomeParametro, ConstantsCPassParametro.RiferimentoEnum.RIGA_ORDINE.getCostante(),
					settoreUtente.get(0).getEnte().getId());

			Double tolleranza = Double.parseDouble(paramTolleranza.getValore());
	
			Double twoDecimalDiff = UtilityArrotondamento.arrotondaDueDecimali(diff.doubleValue());
			int confronto = twoDecimalDiff.compareTo(tolleranza);
	
			if(request.getFlagBypassControlloIva() == null || !request.getFlagBypassControlloIva().equals(true)) {
	
				if(!twoDecimalDiff.equals(0.0)) {
	
					if(confronto > 0) {
						response.addApiError(MsgCpassOrd.ORDORDE0016.getError());
					} else if (confronto < 0) {
						response.addApiError(MsgCpassOrd.ORDORDA0017.getError());
					}
	
				} else {
					inserisciListino(ods);
					rigaOrdine = rigaOrdineDad.saveRigaOrdine(rigaOrdine);
					response.setRigaOrdine(rigaOrdine);
					
					UtilityRigaOrdine.aggiornamentoTotali(rigaOrdine, rigaOrdine.getDestinatario().getId(), testataOrdineDad, destinatarioDad, rigaOrdineDad);
	
					try {
						inserimentoAutomaticoFinanziari(rigaOrdine);
					} catch(Exception e) {
						log.error(methodName, e.getMessage(), e);
						// errore specifico quando siac resta appeso e il salvataggio automatico dei finanziari non va a buon fine
						ApiError error = MsgCpassOrd.ORDORDI0024.getError();
						Map<String, Object> params = new HashMap<String, Object>();
						params.put("riga", rigaOrdine);
						error.setParams(params);
						response.addApiError(error);
					}
				}
	
			} else {
	
				//controllo comunque che vi siano l'iva sia nella tolleranza prima di salvare
				checkBusinessCondition(confronto <= 0, MsgCpassOrd.ORDORDE0016.getError());
				inserisciListino(ods);
	
				rigaOrdine = rigaOrdineDad.saveRigaOrdine(rigaOrdine);
				response.setRigaOrdine(rigaOrdine);
				
				UtilityRigaOrdine.aggiornamentoTotali(rigaOrdine, rigaOrdine.getDestinatario().getId(), testataOrdineDad, destinatarioDad, rigaOrdineDad);
	
				try {
					inserimentoAutomaticoFinanziari(rigaOrdine);
				} catch(Exception e) {
					log.error(methodName, e.getMessage(), e);
					// errore specifico quando siac resta appeso e il salvataggio automatico dei finanziari non va a buon fine
					ApiError error = MsgCpassOrd.ORDORDI0024.getError();
					Map<String, Object> params = new HashMap<String, Object>();
					params.put("riga", rigaOrdine);
					error.setParams(params);
					response.addApiError(error);
				}
			}			
		}
	}

	public void inserimentoAutomaticoFinanziari(RigaOrdine rigaOrdine) {

		//inserimento automatico finanziario ordine
		Destinatario destinatarioRiga = destinatarioDad.getDestinatario(rigaOrdine.getDestinatario().getId()).get();
		rigaOrdine.setDestinatario(destinatarioRiga);
		List<ImpegnoAssociato> impegniAssociati = rigaOrdineDad.getImpegnoAssociatoOrdine(rigaOrdine.getDestinatario().getTestataOrdine().getId());
		//List<SubImpegnoAssociato> subImpegniAssociati = rigaOrdineDad.getSubImpegnoAssociatoOrdine(rigaOrdine.getDestinatario().getTestataOrdine().getId());
		
		if(impegniAssociati == null || impegniAssociati.size() == 0) {
			return;
		}
		
		List<Impegno> validi = new ArrayList<Impegno>();
		List<Impegno> impegniRiga = new ArrayList<Impegno>();
		
		String nomeParametro = ChiaveEnum.ASSOC_IMPEGNI_ORD.name();
		Utente utenteConnesso = CpassThreadLocalContainer.UTENTE_CONNESSO.get();
		List<Settore> settoreUtente = utenteDad.getSettoriByUtente(utenteConnesso.getId());
		Ente ente = settoreUtente.get(0).getEnte();
		UUID enteId = ente.getId();
		Parametro assImpOrd = systemDad.getParametro(nomeParametro, ConstantsCPassParametro.RiferimentoEnum.IMPEGNO.getCostante(), enteId);
		
		List<Impegno> impegniSiac = null;

		if(impegniAssociati != null) {

			//validazione
			for(ImpegnoAssociato impegnoAssociato : impegniAssociati) {
				Impegno tempImpegno = impegnoAssociato.getImpegno();
				
				if (impegniSiac == null) {
					// Cerco l'impegno aggiornato su SIAC
					// cerco gli impegno su SIAC solo se vi sono impegni associati e solo la prima volta, al secondo ciclo utilizzo gli impegni già cercati,
					// sulla base dell'anno e numero provvedimento
					impegniSiac = getImpegniSiacDefinitiviByImpegnoOrdine(tempImpegno);
				}
				
				//Cerco l'impegno tra l'elenco degli impegni siac (già filtrati per stato definitivo)
				Impegno impegnoSiac = null;
				if (impegniSiac != null) {
					for (Impegno tempSiac : impegniSiac) {
						if (tempSiac.getAnno().equals(tempImpegno.getAnno()) && tempSiac.getNumero().equals(tempImpegno.getNumero())
								&& tempSiac.getAnnoEsercizio().equals(tempImpegno.getAnnoEsercizio())) {
							impegnoSiac = tempSiac;
							break;
						}
					}
				}
				// se non trovo l'impegno SIAC o non esiste più o non è più definitivo
				if (impegnoSiac == null) {
					break;
				}
				
				// L’impegno non deve essere già associato alla riga
				// ottento prima la lista aggiornata di impegni associati alla riga
				impegniRiga = impegnoDad.getImpegniByRiga(rigaOrdine.getId());
				boolean impegnoExists = false;
				for(Impegno impegnoRiga: impegniRiga) {
					if(impegnoRiga.getAnno().equals(tempImpegno.getAnno()) && impegnoRiga.getNumero().equals(tempImpegno.getNumero()) &&
							impegnoRiga.getAnnoEsercizio().equals(tempImpegno.getAnnoEsercizio())) {
						impegnoExists = true;
						break;
					}
				}
				if(impegnoExists) {
					break;
				}
				
				// Il disponibile ad ordinare deve essere maggiore di 0
				BigDecimal disponibileAOrdinare = UtilityImpegni.calcolaDisponibileAdOrdinare(impegnoDad, tempImpegno, enteId, null); // testataOrdineId);
				if(disponibileAOrdinare.compareTo(BigDecimal.ZERO) <= 0) {
					break;
				} else {
					tempImpegno.setDisponibile(disponibileAOrdinare);
				}
				
				// inizio il controllo sul subimpegno
				// svuolo la lista subimpegni del tempImpegno e la popolo solo con quelli validi
				tempImpegno.setSubimpegni(new ArrayList<Subimpegno>());
				
				boolean bAddImpegnoValido = true;
				
				if (impegnoAssociato.getSubimpegniAssociati() != null && impegnoAssociato.getSubimpegniAssociati().size() > 0) {
					// se è presente un subimpegno ed esso non viene inserito per problemi sul fornitore, 
					// allora non deve essere inserito neanche il record dell'impegno
					bAddImpegnoValido = false;
					
					for (SubImpegnoAssociato subimpegnoAssociato : impegnoAssociato.getSubimpegniAssociati()) {
						
						// Il subimpegno non deve essere già associato alla riga
						boolean subimpegnoExists = false;
						for (Impegno impegnoRiga : impegniRiga) {
							if (impegnoRiga.getAnno().equals(subimpegnoAssociato.getAnnoSubImpegno())
									&& impegnoRiga.getNumero().equals(subimpegnoAssociato.getNumeroSubImpegno())
									&& impegnoRiga.getAnnoEsercizio().equals(subimpegnoAssociato.getAnnoEsercizio())) {
								subimpegnoExists = true;
								break;
							}
							for (Subimpegno subimpegnoRiga : impegnoRiga.getSubimpegni()) {
								if (subimpegnoRiga.getAnno().equals(subimpegnoAssociato.getAnnoSubImpegno())
										&& subimpegnoRiga.getNumero().equals(subimpegnoAssociato.getNumeroSubImpegno())
										&& subimpegnoRiga.getAnnoEsercizio().equals(subimpegnoAssociato.getAnnoEsercizio())) {
									subimpegnoExists = true;
									break;
								}
							}
						}
						if (!subimpegnoExists) {
							// Se si tratta di subimpegno, esso deve essere collegato al fornitore dell’ordine
							Subimpegno tempSIFromAssociato = impegnoDad.getById(subimpegnoAssociato.getSubImpegno().getId());
							if (tempSIFromAssociato.getFornitore() != null && tempSIFromAssociato.getFornitore().getCodice()
									.equals(rigaOrdine.getDestinatario().getTestataOrdine().getFornitore().getCodice())) {
								// se il subimpegno è valido lo aggiungo al tempImpegno valido
								tempImpegno.getSubimpegni().add(subimpegnoAssociato.getSubImpegno());
								bAddImpegnoValido = true;
							}
						}
					}
				}

				if (bAddImpegnoValido) {
					validi.add(tempImpegno);
				}
			}

			BigDecimal residuoDaAssegnare = UtilityArrotondamento.arrotondaDueDecimali(rigaOrdine.getImportoTotale());

			List<Impegno> impegniToSave = new ArrayList<Impegno>();

			if(assImpOrd != null && assImpOrd.getValore().equals("DISP_CRESC")) {

				// disposizione per disponibile crescente 
				// ordino per disponibile, quindi per anno, quindi per numero
				validi.sort(new Comparator<Impegno>() {
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

			//se la validazione ha escluso tutti gli impegni lancio eccezione e interrompo il processo
			ApiError error = MsgCpassOrd.ORDORDI0024.getError();
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("riga", rigaOrdine);
			error.setParams(params);
			checkBusinessCondition(validi != null && validi.size() > 0, error);
			
			for(Impegno impegnoToSave : validi) {

				// assegno il residuo ai vari impegni
				if(impegnoToSave.getDisponibile().compareTo(residuoDaAssegnare) < 0) {

					BigDecimal importo = impegnoToSave.getDisponibile();
					impegnoToSave.setImporto(importo);
					residuoDaAssegnare = residuoDaAssegnare.subtract(importo);
					
					// assegno l'importo ai subimpegni
					BigDecimal subResiduo = new BigDecimal(importo.doubleValue());
					assegnaImportiASubimpegni(impegnoToSave, subResiduo, enteId);
					
					impegniToSave.add(impegnoToSave);

				} else {
					impegnoToSave.setImporto(residuoDaAssegnare);
					
					assegnaImportiASubimpegni(impegnoToSave, residuoDaAssegnare, enteId);
					
					residuoDaAssegnare = BigDecimal.ZERO;
					
					impegniToSave.add(impegnoToSave);
					break;
				}
			}

			Fornitore fornitore = rigaOrdine.getDestinatario().getTestataOrdine().getFornitore();
			impegnoDad.insertImpegni(rigaOrdine, impegniToSave, CpassMappers.FORNITORE.toEntity(fornitore), CpassMappers.ENTE.toEntity(ente));
			
			//gli impegni inseriti sono stati salvati tutti ma non è stato consumato l'intero disponibile, lancio un warning
			if(residuoDaAssegnare.compareTo(BigDecimal.ZERO) > 0) {
				Map<String, Object> paramsMap = new HashMap<String, Object>();
				paramsMap.put("x", residuoDaAssegnare);
				response.addApiError(MsgCpassOrd.ORDORDI0025.getError(paramsMap));
			} 
		}
	}
	
	private void assegnaImportiASubimpegni(Impegno impegno, BigDecimal subResiduo, UUID enteId) {
		for(Subimpegno subimpegno : impegno.getSubimpegni()) {
			
			BigDecimal disponibile = UtilityImpegni.calcolaDisponibileAdOrdinare(impegnoDad, impegno, subimpegno, enteId);
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
	}

	private List<Impegno> getImpegniSiacDefinitiviByImpegnoOrdine(Impegno impegno) {
		Impegno impegnoFiltro = new Impegno();
		impegnoFiltro.setAnnoProvvedimento(impegno.getAnnoProvvedimento());
		impegnoFiltro.setNumeroProvvedimento(impegno.getNumeroProvvedimento());

		Subimpegno subimpegnoFiltro = new Subimpegno();
		subimpegnoFiltro.setImpegno(impegnoFiltro);
		
		FiltroImpegni filtroImpegni = new FiltroImpegni();
		filtroImpegni.setSubimpegno(subimpegnoFiltro);
		filtroImpegni.setStatoImpegno(IntegrationConstants.IMPEGNO_STATO_DEFINITIVO);

		ExternalServiceResolveWrapper<ImpegnoHelper> handler = externalHelperLookup.lookup(ImpegnoHelper.class);
		PagedList<Impegno> pagedListImpegni = invokeExternalService(handler,
				() -> handler.getInstance().getImpegni(handler.getParams(), filtroImpegni, 1, 0));
		
		return pagedListImpegni != null ? pagedListImpegni.getList() : null;
	}
	
	private ListinoFornitore inserisciListino(OggettiSpesa ods) {
		//Inseriso se serve il listinoFornitore
		ListinoFornitore listino = rigaOrdine.getListinoFornitore();
		if(listino!=null && listino.getId()!= null && listino.getId()==-1) {
			//TODO inserire listino
			Fornitore fornitore = commonDad.getRicercaFornitoreInterno( listino.getFornitore().getId());		
			ListinoFornitore listinopassato = rigaOrdine.getListinoFornitore();
			listinopassato.setId(null);
			listinopassato.setFornitore(fornitore);
			listinopassato.setOggettiSpesa(ods);			
			listino = commonDad.saveListinoFornitore(rigaOrdine.getListinoFornitore());
			commonDad.flush();
			
		}
		return listino;
	}
}
