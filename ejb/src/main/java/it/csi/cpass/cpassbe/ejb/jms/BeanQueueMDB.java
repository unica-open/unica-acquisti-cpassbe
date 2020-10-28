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
package it.csi.cpass.cpassbe.ejb.jms;

import java.io.Serializable;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

import it.csi.cpass.cpassbe.ejb.business.be.dad.ElaborazioneDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.ElaborazioneMessaggioDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.ElaborazioneParametroDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.InterventoDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.ProgrammaDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.SystemDad;
import it.csi.cpass.cpassbe.ejb.business.be.facade.MITManager;
import it.csi.cpass.cpassbe.ejb.jms.BeanQueueRegistrator.QueueMessage;
import it.csi.cpass.cpassbe.ejb.util.ElaborazioneTipoEnum;
import it.csi.cpass.cpassbe.lib.dto.Elaborazione;
import it.csi.cpass.cpassbe.lib.util.log.LogUtil;

/**
 * MDB for BeanQueue
 */
@MessageDriven(name = "BeanQueueMDB", activationConfig = {
		@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
		@ActivationConfigProperty(propertyName = "destination", propertyValue = "queue/CPassQueue"),
		@ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge"),
		@ActivationConfigProperty(propertyName = "maxSession", propertyValue = "20") })
public class BeanQueueMDB implements MessageListener {

	private final LogUtil log = new LogUtil(getClass());

	@Inject
	private ProgrammaDad programmaDad;
	@Inject
	private InterventoDad interventoDad;
	@Inject
	private ElaborazioneDad elaborazioneDad;
	@Inject
	private ElaborazioneMessaggioDad elaborazioneMessaggioDad;
	@Inject
	private ElaborazioneParametroDad elaborazioneParametroDad;
	@Inject
	private SystemDad systemDad;

	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void onMessage(Message message) {
		final String methodName = "onMessage";
		if (message == null) {
			log.warn(methodName, "Messsaggio nullo ottenuto");
			return;
		}

		if (!(message instanceof ObjectMessage)) {
			log.warn(methodName, "Messaggio ricevuto di tipo non corretto: atteso ObjectMessage, ottenuto " + message.getClass());
			return;
		}
		
		String messageId = null;
		try {
			ObjectMessage objectMessage = (ObjectMessage) message;
			messageId = objectMessage.getJMSMessageID();
			
			Serializable ser = objectMessage.getObject();
			if (!(ser instanceof QueueMessage)) {
				log.warn(methodName, "Messaggio ricevuto di tipo non corretto: atteso QueueMessage, ottenuto " + ser.getClass().getName());
				return;
			}
			elaborateQueueMessage(messageId, (QueueMessage) ser);
		} catch (JMSException e) {
			log.error(methodName, "messageId: " + messageId + " - Errore JMS: " + e.getMessage(), e);
		} catch (Exception e) {
			log.error(methodName, "messageId: " + messageId + " - Errore generico: " + e.getMessage(), e);
		}
	}

	/**
	 * Elaborazione del messaggio accodato.
	 * 
	 * @param queueMessage il messaggio accodato
	 */
	private void elaborateQueueMessage(String messageId, QueueMessage queueMessage) {
		final String methodName = "elaborateQueueMessage";
		Serializable serializable = queueMessage.getSerializable();
		Elaborazione elaborazione = null;
		
//		if (serializable instanceof Elaborazione) {
//			elaborazione = (Elaborazione) serializable;
//		 id arriva null !
//			elaborazione = elaborazioneDad.loadElaborazione(elaborazione.getId());

		log.info(methodName, "messageId: " + messageId + " - serializable: " + serializable);
		if (serializable instanceof Integer) {
			elaborazione = elaborazioneDad.loadElaborazione((Integer) serializable);
		}

		if (elaborazione != null && elaborazione.getElaborazioneTipo().getCodice().equals(ElaborazioneTipoEnum.TRASMISSIONE_PROGRAMMA_MIT.getCostante())) {
			// trasmissione al MIT
			MITManager mitManager = new MITManager(systemDad, interventoDad, programmaDad, elaborazioneDad, elaborazioneMessaggioDad, elaborazioneParametroDad);
			mitManager.trasmetti(elaborazione, null);
		}
	}

}
