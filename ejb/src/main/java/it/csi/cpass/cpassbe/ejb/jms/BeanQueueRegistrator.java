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
package it.csi.cpass.cpassbe.ejb.jms;

import java.io.Serializable;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.Session;

import it.csi.cpass.cpassbe.lib.util.log.LogUtil;

/**
 * Registrator for Bean Queue
 */
@Stateless
public class BeanQueueRegistrator {
	private final LogUtil log = new LogUtil(getClass());

	// Binding connection factory named java:/JmsXA to alias java:jboss/DefaultJMSConnectionFactory
	@Resource(mappedName = "java:/JmsXA")
	private ConnectionFactory connectionFactory;

	// @Resource(mappedName = "java:/queue/OrchanprQueue")
	@Resource(mappedName = "java:/queue/CPassQueue")
	private Queue queue;

	/**
	 * Registration to the JMS queue.
	 * @param serializable the serializable to register
	 */
	public void register(Serializable serializable) {
		final String methodName = "register";
		log.info(methodName, "register");
		// delay();
		Connection connection = null;
		Session session = null;
		MessageProducer messageProducer = null;
		try {
			connection = connectionFactory.createConnection();
			log.info(methodName, "connessione");
			// Non transacted
			session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			messageProducer = session.createProducer(queue);
			connection.start();
			log.info(methodName, "connessione start");
			final QueueMessage message = new QueueMessage(serializable);
			// QueueMessage message = new QueueMessage(resourceToRegister, params);
			final ObjectMessage objectMessage = session.createObjectMessage(message);
			messageProducer.send(objectMessage);
			log.info(methodName, "fine registrazione ");
		} catch (final JMSException jmse) {
			// jmse.printStackTrace();
			log.error(methodName, "JMSException in registering: " + jmse.getMessage(), jmse);
		} finally {
			log.info(methodName, "finally Inizio ");
			jmsMessageProducerClose(messageProducer);
			jmsSessionClose(session);
			// NON ESEGUIBILE! Vietato dalle specifiche J2EE
			// jmsConnectionStop(connection);
			jmsConnectionClose(connection);
			log.info(methodName, "finally Fine ");
		}

		// return result;
	}

	/**
	 * Chiusura del MessageProducer JMS.
	 *
	 * @param messageProducer il producer da chiudere
	 */
	private void jmsMessageProducerClose(MessageProducer messageProducer) {
		final String methodName = "jmsMessageProducerClose";
		try {
			if (messageProducer != null) {
				messageProducer.close();
			}
		} catch (final JMSException e) {
			log.warn(methodName, "Exception in session closing: " + e.getMessage());
		}
	}

	/**
	 * Chiusura della Session JMS.
	 *
	 * @param session la sessione da chiudere
	 */
	private void jmsSessionClose(Session session) {
		final String methodName = "jmsSessionClose";
		try {
			if (session != null) {
				session.close();
			}
		} catch (final JMSException e) {
			log.warn(methodName, "Exception in session closing: " + e.getMessage());
		}
	}

	//	/**
	//	 * Stop della Connection JMS.
	//	 *
	//	 * @param connection la connessione da fermare
	//	 */
	//	private void jmsConnectionStop(Connection connection) {
	//		final String methodName = "jmsConnectionStop";
	//		try {
	//			if(connection != null) {
	//				connection.stop();
	//			}
	//		} catch (JMSException e) {
	//			log.warn(methodName, "Exception in connection stop: " + e.getMessage());
	//		}
	//	}

	/**
	 * Chiusura della Connection JMS.
	 *
	 * @param connection la connessione da chiudere
	 */
	private void jmsConnectionClose(Connection connection) {
		final String methodName = "jmsConnectionClose";
		try {
			if (connection != null) {
				connection.close();
			}
		} catch (final JMSException e) {
			// Ignoro l'errore
			log.warn(methodName, "Exception in session closing: " + e.getMessage());
		}
	}

	/**
	 * Messaggio per la coda.
	 */
	public static class QueueMessage implements Serializable {
		private static final long serialVersionUID = 1L;
		private final Serializable serializable;

		/**
		 * Costruttore per il messaggio
		 * @param serializable the serializable
		 */
		public QueueMessage(Serializable serializable) {
			// public QueueMessage(String lookupString, DatiCallBackEventoDTO datiCallback) {
			this.serializable = serializable;
		}

		/**
		 * @return the serializable
		 */
		public Serializable getSerializable() {
			return serializable;
		}
	}

}
