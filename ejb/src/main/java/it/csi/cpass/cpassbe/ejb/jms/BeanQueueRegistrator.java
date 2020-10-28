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

	/** Il delay per le invocazioni */
	private static final long DELAY = 2000L;
	// private final BeanLogger log = new BeanLogger(getClass());

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
			// Non transacted
			session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			messageProducer = session.createProducer(queue);

			connection.start();
			QueueMessage message = new QueueMessage(serializable);
			// QueueMessage message = new QueueMessage(resourceToRegister, params);

			ObjectMessage objectMessage = session.createObjectMessage(message);
			messageProducer.send(objectMessage);

		} catch (JMSException jmse) {
			// jmse.printStackTrace();
			log.error(methodName, "JMSException in registering: " + jmse.getMessage(), jmse);
			
		} finally {
			jmsMessageProducerClose(messageProducer);
			jmsSessionClose(session);
			// NON ESEGUIBILE! Vietato dalle specifiche J2EE
//			jmsConnectionStop(connection);
			jmsConnectionClose(connection);
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
		} catch (JMSException e) {
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
		} catch (JMSException e) {
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
		} catch (JMSException e) {
			// Ignoro l'errore
			log.warn(methodName, "Exception in session closing: " + e.getMessage());
		}
	}

	/**
	 * Delay dell'invocazione. <br/>
	 * <b>NOTA BENE!!</b> <br/>
	 * Non dovrei agire come nell'implementazione attuale. Ma al momento va bene cos&igrave;
	 */
	@SuppressWarnings("unused")
	private void delay() {
		final String methodName = "delay";
		String threadName = Thread.currentThread().getName();
		log.debug(methodName, "The thread " + threadName + " shall be delayed for " + DELAY + "ms");
		try {
			Thread.sleep(DELAY);
		} catch (InterruptedException e) {
			log.debug(methodName,
					"Interrupted sleep for thread " + threadName + " with cause " + e.getMessage() + " - resuming invocation");
			// Ignores interruptions
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
