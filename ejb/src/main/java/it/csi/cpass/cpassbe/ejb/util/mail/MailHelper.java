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
package it.csi.cpass.cpassbe.ejb.util.mail;

import java.io.UnsupportedEncodingException;
import java.util.Collection;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

import javax.activation.DataHandler;
import javax.annotation.PostConstruct;
import javax.ejb.Asynchronous;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;
import javax.ws.rs.core.MediaType;

import it.csi.cpass.cpassbe.ejb.exception.BusinessException;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationValue;

/**
 * Helper for mail support
 */
@Singleton
public class MailHelper {

	@EJB private ConfigurationHelper configurationHelper;

	private Session session;
	private InternetAddress innerFrom;

	/**
	 * Initialization
	 */
	@PostConstruct
	private void init() {
		if(session == null) {
			synchronized(MailHelper.class) {
				if(session == null) {
					initSession();
				}
			}
		}
	}

	/**
	 * Initializzation for mail session
	 */
	private void initSession() {
		// Init the properties
		final Properties props = new Properties();
		props.put("mail.smtp.auth", configurationHelper.getProperty(ConfigurationValue.MAIL_SMTP_AUTH));
		props.put("mail.smtp.starttls.enable", configurationHelper.getProperty(ConfigurationValue.MAIL_SMTP_STARTTLS_ENABLE));
		props.put("mail.smtp.host", configurationHelper.getProperty(ConfigurationValue.MAIL_SMTP_HOST));
		props.put("mail.smtp.port", configurationHelper.getProperty(ConfigurationValue.MAIL_SMTP_PORT));

		session = Session.getInstance(props, new AuthenticatorHelper(configurationHelper.getProperty(ConfigurationValue.MAIL_USERNAME), configurationHelper.getProperty(ConfigurationValue.MAIL_PASSWORD)));
		try {

			innerFrom = new InternetAddress(configurationHelper.getProperty(ConfigurationValue.MAIL_FROM), configurationHelper.getProperty(ConfigurationValue.MAIL_FROM_NAME));
		} catch (final UnsupportedEncodingException e) {
			throw new IllegalStateException("Illegal state in setting property for mail", e);
		}
	}

	/**
	 * Sends a mail.
	 *
	 * @param to          the recipients
	 * @param subject     the subject
	 * @param text        the text
	 * @param attachments the attachments
	 */
	public void send(Collection<String> to, String subject, String text, List<Attachment> attachments) {
		send(to, subject, innerFrom, text, attachments);
	}

	/**
	 * Sends a mail.
	 *
	 * @param to          the recipients
	 * @param subject     the subject
	 * @param from        the from
	 * @param text        the text
	 * @param attachments the attachments
	 */
	public void send(Collection<String> to, String subject, String from, String text, List<Attachment> attachments) {
		Address fromAddress;
		try {
			fromAddress = new InternetAddress(from);
			send(to, subject, fromAddress, text, attachments);
		} catch (final AddressException e) {
			throw new BusinessException(composeErrorMessage(e), e);
		}
	}

	/**
	 * Sends a mail.
	 *
	 * @param to          the recipients
	 * @param subject     the subject
	 * @param from        the from
	 * @param text        the text
	 * @param attachments the attachments
	 */
	public void send(Collection<String> to, String subject, Address from, String text, List<Attachment> attachments) {
		innerSend(to, subject, from, text, MediaType.TEXT_PLAIN, attachments);
	}

	/**
	 * Sends a mail.
	 *
	 * @param to          the recipients
	 * @param subject     the subject
	 * @param html        the html
	 * @param attachments the attachments
	 */
	public void sendHtml(Collection<String> to, String subject, String html, List<Attachment> attachments) {
		sendHtml(to, subject, innerFrom, html, attachments);
	}

	/**
	 * Sends a mail.
	 *
	 * @param to          the recipients
	 * @param subject     the subject
	 * @param from        the from
	 * @param html        the html
	 * @param attachments the attachments
	 */
	public void sendHtml(Collection<String> to, String subject, String from, String html, List<Attachment> attachments) {
		Address fromAddress;
		try {
			fromAddress = new InternetAddress(from);
			sendHtml(to, subject, fromAddress, html, attachments);
		} catch (final AddressException e) {
			throw new BusinessException(composeErrorMessage(e), e);
		}
	}

	/**
	 * Sends a mail.
	 *
	 * @param to          the recipients
	 * @param subject     the subject
	 * @param from        the from
	 * @param text        the text
	 * @param attachments the attachments
	 */
	public void sendHtml(Collection<String> to, String subject, Address from, String text, List<Attachment> attachments) {
		innerSend(to, subject, from, text, MediaType.TEXT_HTML, attachments);
	}

	/**
	 * Sends a mail.
	 *
	 * @param to          the recipients
	 * @param subject     the subject
	 * @param from        the from
	 * @param text        the text
	 * @param mediaType   the media type
	 * @param attachments the attachments
	 */
	private void innerSend(Collection<String> to, String subject, Address from, String text, String mediaType, List<Attachment> attachments) {
		final String addressList = to.stream().collect(Collectors.joining(","));

		final Message message = new MimeMessage(session);
		try {
			message.setRecipients(RecipientType.TO, InternetAddress.parse(addressList));
			message.setSubject(subject);
			message.setFrom(from);

			handleContent(text, mediaType, attachments, message);

			Transport.send(message);
		} catch (final MessagingException e) {
			throw new BusinessException(composeErrorMessage(e), e);
		}
	}

	/**
	 * Sends a mail asynchronously.
	 *
	 * @param to          the recipients
	 * @param subject     the subject
	 * @param text        the text
	 * @param attachments the attachments
	 */
	@Asynchronous
	public void sendAsync(Collection<String> to, String subject, String text, List<Attachment> attachments) {
		send(to, subject, text, attachments);
	}

	/**
	 * Sends a mail asynchronously.
	 *
	 * @param to          the recipients
	 * @param subject     the subject
	 * @param from        the from
	 * @param text        the text
	 * @param attachments the attachments
	 */
	@Asynchronous
	public void sendAsync(Collection<String> to, String subject, String from, String text, List<Attachment> attachments) {
		send(to, subject, from, text, attachments);
	}

	/**
	 * Sends a mail asynchronously.
	 *
	 * @param to          the recipients
	 * @param subject     the subject
	 * @param from        the from
	 * @param text        the text
	 * @param attachments the attachments
	 */
	@Asynchronous
	public void sendAsync(Collection<String> to, String subject, Address from, String text, List<Attachment> attachments) {
		send(to, subject, from, text, attachments);
	}

	/**
	 * Sends a mail asynchronously.
	 *
	 * @param to          the recipients
	 * @param subject     the subject
	 * @param html        the html
	 * @param attachments the attachments
	 */
	@Asynchronous
	public void sendHtmlAsync(Collection<String> to, String subject, String html, List<Attachment> attachments) {
		sendHtml(to, subject, html, attachments);
	}

	/**
	 * Sends a mail asynchronously.
	 *
	 * @param to          the recipients
	 * @param subject     the subject
	 * @param from        the from
	 * @param html        the html
	 * @param attachments the attachments
	 */
	@Asynchronous
	public void sendHtmlAsync(Collection<String> to, String subject, String from, String html, List<Attachment> attachments) {
		sendHtml(to, subject, from, html, attachments);
	}

	/**
	 * Sends a mail asynchronously.
	 *
	 * @param to          the recipients
	 * @param subject     the subject
	 * @param from        the from
	 * @param html        the html
	 * @param attachments the attachments
	 */
	@Asynchronous
	public void sendHtmlAsync(Collection<String> to, String subject, Address from, String html, List<Attachment> attachments) {
		sendHtml(to, subject, from, html, attachments);
	}

	/**
	 * Handle the attachments to the message.
	 *
	 * @param text        the text
	 * @param mediaType   the media type
	 * @param attachments the attachments
	 * @param message     the message
	 * @throws MessagingException in case of an exception in content handling
	 */
	private void handleContent(String text, String mediaType, List<Attachment> attachments, Message message) throws MessagingException {
		if(attachments == null || attachments.isEmpty()) {
			// Send a plain email
			message.setContent(text, mediaType);
			return;
		}
		// Send a multipart message
		final Multipart multipart = new MimeMultipart();
		// body
		final MimeBodyPart textBodyPart = new MimeBodyPart();
		textBodyPart.setContent(text, mediaType);
		multipart.addBodyPart(textBodyPart);

		// Add attachments
		for(final Attachment attachment : attachments) {
			final MimeBodyPart attachmentBodyPart = new MimeBodyPart();
			final ByteArrayDataSource bads = new ByteArrayDataSource(attachment.getBytes(), attachment.getMediaType());
			attachmentBodyPart.setDataHandler(new DataHandler(bads));
			attachmentBodyPart.setFileName(attachment.getFileName());
			multipart.addBodyPart(attachmentBodyPart);
		}
		message.setContent(multipart);
	}

	/**
	 * Composes the error message
	 * @param e the error
	 * @return the message
	 */
	private String composeErrorMessage(Exception e) {
		return "Error in mail message composition: " + e.getMessage();
	}
}
