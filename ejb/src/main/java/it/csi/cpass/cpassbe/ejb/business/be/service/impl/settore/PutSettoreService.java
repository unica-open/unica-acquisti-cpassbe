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
package it.csi.cpass.cpassbe.ejb.business.be.service.impl.settore;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import it.csi.cpass.cpassbe.ejb.business.be.dad.SettoreDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.UtenteDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.settore.PutSettoreRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.settore.PutSettoreResponse;
import it.csi.cpass.cpassbe.ejb.entity.CpassRRuoloUtenteSettore;
import it.csi.cpass.cpassbe.ejb.entity.CpassRUtenteSettore;
import it.csi.cpass.cpassbe.ejb.util.StringUtility;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;
import it.csi.cpass.cpassbe.lib.dto.Cdc;
import it.csi.cpass.cpassbe.lib.dto.Ente;
import it.csi.cpass.cpassbe.lib.dto.Settore;
import it.csi.cpass.cpassbe.lib.dto.SettoreIndirizzo;
import it.csi.cpass.cpassbe.lib.dto.Ufficio;
import it.csi.cpass.cpassbe.lib.dto.Utente;
import it.csi.cpass.cpassbe.lib.dto.error.MsgCpassBo;
import it.csi.cpass.cpassbe.lib.util.threadlocal.CpassThreadLocalContainer;

/**
 * Saves an Settore
 */
public class PutSettoreService extends BaseSettoreService<PutSettoreRequest, PutSettoreResponse> {

	private Settore settore;
	private final UtenteDad utenteDad;

	/**
	 * Constructor
	 * @param configurationHelper 	the configuration helper
	 * @param interventoDad 		the intervento DAD
	 * @param decodificaDad 		the decodifica DAD
	 * @param programmaDad  		the programma DAD
	 */
	public PutSettoreService(ConfigurationHelper configurationHelper, SettoreDad settoreDad,UtenteDad utenteDad) {
		super(configurationHelper, settoreDad);
		this.utenteDad = utenteDad;
	}

	@Override
	protected void checkServiceParams() {
		settore = request.getSettore();
	}

	@Override
	protected void execute() {
		final String methodName = "execute";
		log.info(methodName, "Start");
		final Utente utente = CpassThreadLocalContainer.UTENTE_CONNESSO.get();
		final Ente ente = CpassThreadLocalContainer.SETTORE_UTENTE.get().getEnte();
		settore.setEnte(ente);
		final Boolean gerarchiaCorretta = controlloGerarchiasettori(settore);
		checkBusinessCondition(gerarchiaCorretta,MsgCpassBo.BACSTRE0007.getError());

		final List<SettoreIndirizzo> listaSettoreIndirizzoNew = settore.getSettoreIndirizzos();
		final List<Ufficio> listaUfficiNew = settore.getUffici();
		final List<Cdc> listaCdcNew = settore.getCdcs();

		final Settore settoreOld = settoreDad.findById(settore.getId());
		final List<SettoreIndirizzo> listaSettoreIndirizzoOld = settoreOld.getSettoreIndirizzos();
		final List<Ufficio> listaUfficiOld = settoreOld.getUffici();
		final List<Cdc> listaCdcOld = settoreOld.getCdcs();

		if(settore.getDataCancellazione()!=null) {
			settore.setUtenteCancellazione(utente.getCodiceFiscale());
		}
		final Settore settoreNew = settoreDad.saveSettore(settore);

		if(settoreNew.getDataCancellazione()!=null) {
			chiudiUtenteSettore(ente.getId(), settoreNew.getId(),settoreNew.getDataCancellazione());
			chiudiUffici(listaUfficiOld, settoreNew);
			chiudiCdc(listaCdcOld, settoreNew);
			chiudiIndirizzi(listaSettoreIndirizzoOld,settoreNew);
		}else {
			gestioneUffici(listaUfficiOld, listaUfficiNew, settoreNew);
			gestioneCdc(listaCdcOld, listaCdcNew, settoreNew);
			gestioneIndirizzi(listaSettoreIndirizzoOld,listaSettoreIndirizzoNew,settoreNew);
		}

		response.setSettore(settoreNew);
		log.info(methodName, "Stop");
	}

	private void chiudiUtenteSettore(UUID utenteId , UUID settoreId, Date dataValiditaFine) {
		final List<CpassRUtenteSettore> lista = utenteDad.findByUtenteSettore(utenteId ,  settoreId,new Date());
		for(CpassRUtenteSettore  rus : lista) {
			if(rus.getDataValiditaFine()!= null) {
				rus.setDataValiditaFine(dataValiditaFine);
				rus = utenteDad.saveUtenteSettore(rus);
				chiudiRuoloUtenteSettore(rus.getUtenteSettoreId(),dataValiditaFine);
			}
		}
	}

	private void chiudiRuoloUtenteSettore(Integer utenteSettoreId,Date dataValiditaFine) {
		final List<CpassRRuoloUtenteSettore> lista = utenteDad.findByRuoloUtenteSettore(utenteSettoreId,null);
		for(final CpassRRuoloUtenteSettore  rus : lista) {
			rus.setDataValiditaFine(dataValiditaFine);
			utenteDad.saveRuoloUtenteSettore(rus);
		}
	}

	/**
	 * @param listaUfficiNew
	 * @param settoreOld
	 */
	private void gestioneUffici(List<Ufficio> listaUfficiOld,List<Ufficio> listaUfficiNew, Settore settore) {
		boolean daInserire;
		boolean dachiudere;
		//inserimento nuove relazioni
		for (final Ufficio uffNew: listaUfficiNew) {
			daInserire = true;
			for(final Ufficio uffOld: listaUfficiOld) {
				if(uffOld.getId().compareTo(uffNew.getId()) == 0) {
					daInserire = false;
					break;
				}
			}
			if(daInserire) {
				settoreDad.insertUfficioSettore(uffNew,settore);
			}
		}
		//cancellazione relazioni vecchie non piu' in essere
		for(final Ufficio uffOld: listaUfficiOld) {
			dachiudere = true;
			for (final Ufficio uffNew: listaUfficiNew) {
				if(uffOld.getId().compareTo(uffNew.getId()) == 0) {
					dachiudere = false;
					break;
				}
			}
			if(dachiudere) {
				// da chiudere
				settoreDad.chiudiRelazioneUfficioSettore(uffOld,settore);
			}
		}
	}

	private void gestioneCdc(List<Cdc> listaCdcOld,List<Cdc> listaCdcNew, Settore settore) {
		boolean daInserire;
		boolean dachiudere;
		//inserimento nuove relazioni
		for (final Cdc cdcNew: listaCdcNew) {
			daInserire = true;
			for(final Cdc cdcOld: listaCdcOld) {
				if(cdcOld.getId().compareTo(cdcNew.getId()) == 0) {
					daInserire = false;
					break;
				}
			}
			if(daInserire) {
				settoreDad.insertCdcSettore(cdcNew,settore);
			}
		}
		//cancellazione relazioni vecchie non piu' in essere
		for(final Cdc cdcOld: listaCdcOld) {
			dachiudere = true;
			for (final Cdc cdcNew: listaCdcNew) {
				if(cdcOld.getId().compareTo(cdcNew.getId()) == 0) {
					dachiudere = false;
					break;
				}
			}
			if(dachiudere) {
				// da chiudere
				settoreDad.chiudiRelazioneCdcSettore(cdcOld.getId(),settore.getId());
			}
		}
	}

	private void chiudiUffici(List<Ufficio> listaUfficiOld,Settore settore) {
		for(final Ufficio uffOld: listaUfficiOld) {
			settoreDad.chiudiRelazioneUfficioSettore(uffOld,settore);
		}
	}

	private void chiudiCdc(List<Cdc> listaCdcOld,Settore settore) {
		for(final Cdc cdcOld: listaCdcOld) {
			settoreDad.chiudiRelazioneCdcSettore(cdcOld.getId(),settore.getId());
		}
	}

	private void chiudiIndirizzi(List<SettoreIndirizzo> listaSettoreIndirizzoOld, Settore settore) {
		for(final SettoreIndirizzo siOld: listaSettoreIndirizzoOld) {
			settoreDad.chiudiLogicallySettoreIndirizzo(siOld,settore);
		}
	}

	private void gestioneIndirizzi(List<SettoreIndirizzo> listaSettoreIndirizzoOld,List<SettoreIndirizzo> listaSettoreIndirizzoNew, Settore settore) {
		boolean dachiudere;
		//inserimento nuove relazioni
		for (final SettoreIndirizzo siNew: listaSettoreIndirizzoNew) {
			siNew.setSettore(settore);
			boolean indirizzoEsistente = Boolean.FALSE;
			boolean principaleDaStoricizzare = Boolean.FALSE;
			SettoreIndirizzo siOldPrincipale = new SettoreIndirizzo();
			for (final SettoreIndirizzo siOld: listaSettoreIndirizzoOld) {
				if(siOld.equals(siNew)) {
					if(siNew.getPrincipale() && (
							!siNew.getIndirizzo().trim().equals(siOld.getIndirizzo().trim())
							|| !siNew.getDescrizione().trim().equals(siOld.getDescrizione().trim())
							|| !siNew.getProvincia().trim().equals(siOld.getProvincia().trim())
							|| !siNew.getLocalita().equals(siOld.getLocalita().trim())
							|| !siNew.getCap().trim().equals(siOld.getCap().trim())
							|| !siNew.getNumCivico().trim().equals(siOld.getNumCivico().trim())
							|| !StringUtility.isEqualsString(siNew.getContatto(),siOld.getContatto())
							//siNew.getContatto().trim().equals(siOld.getContatto().trim())
							)) {
						siOldPrincipale = siOld;
						principaleDaStoricizzare = Boolean.TRUE;
					}
					indirizzoEsistente = Boolean.TRUE;
					break;
				}
			}
			if(!indirizzoEsistente) {
				settoreDad.saveAndFlushSettoreIndirizzo(siNew);
			}else if(principaleDaStoricizzare) {
				siNew.setId(null);
				siNew.setOptlock(null);
				settoreDad.saveAndFlushSettoreIndirizzo(siNew);
				settoreDad.chiudiLogicallySettoreIndirizzo(siOldPrincipale,settore);
			}
		}
		//cancellazione relazioni vecchie non piu' in essere
		for(final SettoreIndirizzo siOld: listaSettoreIndirizzoOld) {
			dachiudere = true;
			for (final SettoreIndirizzo siNew: listaSettoreIndirizzoNew) {
				if(siOld.equals(siNew)) {
					dachiudere = false;
					break;
				}
			}
			if(dachiudere) {
				//da chiudere
				if(siOld.getDataCancellazione() !=null && (siOld.getDataCancellazione().compareTo( new Date())>0) ) {
					settoreDad.chiudiLogicallySettoreIndirizzo(siOld,settore);
				}
			}
		}
	}

}
