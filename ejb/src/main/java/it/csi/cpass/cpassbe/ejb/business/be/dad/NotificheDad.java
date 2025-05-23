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
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.CpassRNotificaUtenteDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.CpassTNotificaDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.CpassTTestiNotificheDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.CpassTUtenteDao;
import it.csi.cpass.cpassbe.ejb.entity.CpassRNotificaUtente;
import it.csi.cpass.cpassbe.ejb.entity.CpassTNotifica;
import it.csi.cpass.cpassbe.ejb.entity.CpassTTestiNotifiche;
import it.csi.cpass.cpassbe.ejb.entity.CpassTUtente;
import it.csi.cpass.cpassbe.ejb.exception.NotFoundException;
import it.csi.cpass.cpassbe.ejb.mapper.CpassMappers;
import it.csi.cpass.cpassbe.ejb.util.CpassEnum;
import it.csi.cpass.cpassbe.lib.dto.Notifica;
import it.csi.cpass.cpassbe.lib.dto.TestoNotifica;
import it.csi.cpass.cpassbe.lib.dto.Utente;
/**
 * Data Access Delegate for utente
 */
@ApplicationScoped
public class NotificheDad extends BaseDad {
	@Inject private CpassTUtenteDao cpassTUtenteDao;
	@Inject private CpassTNotificaDao cpassTNotificaDao;
	@Inject private CpassRNotificaUtenteDao cpassRNotificaUtenteDao;
	@Inject private CpassTTestiNotificheDao cpassTTestiNotificheDao;

	/**
	 *
	 * @param utenteId
	 * @param flgLetto
	 * @return
	 */
	public List<Notifica> getNotificheAllByUtente(UUID utenteId, boolean flgLetto) {
		final List<CpassRNotificaUtente> listaNotificaUtente = cpassRNotificaUtenteDao.findByUtenteEStatoLette(utenteId, false);
		final List<CpassRNotificaUtente> listaAvvisiUtente = cpassRNotificaUtenteDao.findAvvisiByUtenteLetti(utenteId, null);

		final Set<Integer> idSet = new HashSet<>();
		final List<CpassRNotificaUtente> avvisiFiltrati = new ArrayList<>();

		for (final CpassRNotificaUtente avviso : listaAvvisiUtente) {
			if (idSet.add(avviso.getCpassTNotifica().getId())) {
				avvisiFiltrati.add(avviso);
			}
		}
		listaNotificaUtente.addAll(avvisiFiltrati);
		return getListaNotificheDaR(listaNotificaUtente);
	}


	/**
	 *
	 * @param utenteId
	 * @param flgLetto
	 * @return
	 */
	public int getCountNotificheByUtente(UUID utenteId, Boolean flgLetto) {
		final CpassTUtente utente = cpassTUtenteDao.findById(utenteId).orElseThrow(() -> new NotFoundException("utente"));
		if (utente != null) {
			// Inserisco nella R le notifiche generali presenti solo su cpass_t_notifica che non hanno ancora un record sulla R con l'utente
			final List<CpassTNotifica> notificheGeneraliNonAssegnate = cpassTNotificaDao.getNotificheGeneraliNonAssegnate(utenteId);

			List<CpassRNotificaUtente> listaNotificheUtenteDaLeggere = new ArrayList<>();
			if (notificheGeneraliNonAssegnate != null && notificheGeneraliNonAssegnate.size() > 0) {
				for (final CpassTNotifica notifica : notificheGeneraliNonAssegnate) {
					final CpassRNotificaUtente notificaUtente = new CpassRNotificaUtente();
					notificaUtente.setCpassTUtente(utente);
					notificaUtente.setCpassTNotifica(notifica);
					notificaUtente.setFlgLetto(Boolean.TRUE);
					// Eseguo la insert sulla R
					cpassRNotificaUtenteDao.insert(notificaUtente);
				}
			}
			// leggo tutte quelle assegnate all'utente non lette (N.B. comprese quelle generali, che vengono estratte anche se già lette)
			listaNotificheUtenteDaLeggere = cpassRNotificaUtenteDao.findByUtenteEStatoLette(utenteId, flgLetto);
			//int nt = notificheGeneraliNonAssegnate == null ? 0 : notificheGeneraliNonAssegnate.size();
			return listaNotificheUtenteDaLeggere.size() + (notificheGeneraliNonAssegnate == null ? 0 : notificheGeneraliNonAssegnate.size()) ;
		} else {
			throw new NotFoundException("utente");
		}
	}
	/**
	 *
	 * @param listaNotificheUtente
	 * @return
	 */
	private List<Notifica> getListaNotificheDaR(List<CpassRNotificaUtente> listaNotificheUtente) {
		final List<Notifica> result = new ArrayList<>();

		for(final CpassRNotificaUtente notificaUtente : listaNotificheUtente) {
			final Notifica toAdd = CpassMappers.NOTIFICA.toModel(notificaUtente.getCpassTNotifica());
			toAdd.setFlagLetto(notificaUtente.getFlgLetto());
			result.add(toAdd);
		}
		return result;
	}
	/**
	 *
	 * @param utente
	 * @param listaNotifiche
	 */
	public void putAggiornaRNotifiche(Utente utente, List<Notifica> listaNotifiche) {
		for(final Notifica notifica : listaNotifiche) {
			CpassRNotificaUtente notificaUtente = cpassRNotificaUtenteDao.findByUtenteEIdNotifica(utente.getId(), notifica.getId());
			if(notificaUtente != null) {
				notificaUtente.setFlgLetto(notifica.getFlgLetto());
				notificaUtente = cpassRNotificaUtenteDao.update(notificaUtente);
			}
		}
	}
	/**
	 *
	 * @param notifica
	 * @param utente
	 * @return
	 */
	public Notifica saveNotificaUtente (Notifica notifica, Utente utente) {
		final CpassTNotifica cpassTNotifica = CpassMappers.NOTIFICA.toEntity(notifica);
		final CpassTUtente cpassTutente = CpassMappers.UTENTE.toEntity(utente);
		cpassTNotificaDao.insert(cpassTNotifica);
		final CpassRNotificaUtente cpassRNotificaUtente = new CpassRNotificaUtente();
		cpassRNotificaUtente.setCpassTNotifica(cpassTNotifica);
		cpassRNotificaUtente.setCpassTUtente(cpassTutente);
		cpassRNotificaUtente.setFlgLetto(false);
		cpassRNotificaUtenteDao.insert(cpassRNotificaUtente);
		return CpassMappers.NOTIFICA.toModel(cpassTNotifica);
	}
	/**
	 *
	 * @param notifica
	 * @return
	 */
	public Notifica saveAvviso(Notifica notifica) {
		final CpassTNotifica cpassTNotifica = CpassMappers.NOTIFICA.toEntity(notifica);
		final String codiceTestoNotifica = cpassTTestiNotificheDao.getCodice();
		final List<CpassTTestiNotifiche> allCpassTTestiNotifiche = cpassTTestiNotificheDao.findAll();
		final int maxTestoNotificaId = allCpassTTestiNotifiche.stream().mapToInt(CpassTTestiNotifiche::getId).max().orElse(0);
		final int nextTestoNotificaId = maxTestoNotificaId + 1;
		final CpassTTestiNotifiche cpassTTestiNotifiche = new CpassTTestiNotifiche();
		cpassTTestiNotifiche.setItTesto(notifica.getTestoNotifica().getIt());
		cpassTTestiNotifiche.setEnTesto(notifica.getTestoNotifica().getEn());
		cpassTTestiNotifiche.setCodice(codiceTestoNotifica);
		cpassTTestiNotifiche.setId(nextTestoNotificaId);
		cpassTTestiNotificheDao.insert(cpassTTestiNotifiche);
		cpassTNotifica.setFlgGenerico(Boolean.TRUE);
		cpassTNotifica.setCpassTTestiNotifiche(cpassTTestiNotifiche);
		cpassTNotifica.setEntitaTipo(CpassEnum.GEN.getCostante());
		cpassTNotifica.setFonte(CpassEnum.GEN.getCostante());
		cpassTNotificaDao.insert(cpassTNotifica);
		return CpassMappers.NOTIFICA.toModel(cpassTNotifica);
	}
	
	/**
	 *
	 * @param codice
	 * @return
	 */
	public Optional<TestoNotifica> getTestoNotifica(String codice) {
		return cpassTTestiNotificheDao.findByCodice(codice).map(CpassMappers.TESTO_NOTIFICA::toModel);
	}	
}
