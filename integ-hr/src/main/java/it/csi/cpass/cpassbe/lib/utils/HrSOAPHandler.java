/*-
 * ========================LICENSE_START=================================
 * CPASS BackEnd - LIB submodule
 * %%
 * Copyright (C) 2019 - 2025 CSI Piemonte
 * %%
 * SPDX-FileCopyrightText: Copyright 2019 - 2020 | CSI Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 * =========================LICENSE_END==================================
 */
package it.csi.cpass.cpassbe.lib.utils;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.xml.namespace.QName;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPHeader;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;

import it.csi.cpass.cpassbe.lib.util.log.LogUtil;

/**
 * SOAP handler for injection the OAuth2 authorization headers
 * @author Marchino Alessandro
 */
public class HrSOAPHandler implements SOAPHandler<SOAPMessageContext> {
	protected final LogUtil log = new LogUtil(getClass());
	private final String username;
	private final String pw;

	/**
	 * Injection constructor
	 * @param oAuth2Helper the helper
	 */
	public HrSOAPHandler(String username, String pw) {
		this.username = username;
		this.pw = pw;
	}

	@Override
	public boolean handleMessage(SOAPMessageContext context) {
		String methodName = "handleMessage";
		Boolean isOutbound = (Boolean) context.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);
		if (Boolean.TRUE.equals(isOutbound)) {
			// Only for outbound messages (ie: requests)
			@SuppressWarnings("unchecked")
			Map<String, List<String>> headers = (Map<String, List<String>>) context.get(MessageContext.HTTP_REQUEST_HEADERS);
			if (headers == null) {
				headers = new HashMap<>();
			}
			context.put(MessageContext.HTTP_REQUEST_HEADERS, headers);
			try {
				addWsseSec(context);
			} catch (SOAPException e) {
				log.error(methodName, e);
			}
		}
		return true;
	}

	private void addWsseSec(SOAPMessageContext smc) throws SOAPException {

		SOAPEnvelope envelope = smc.getMessage().getSOAPPart().getEnvelope();
		SOAPHeader header = envelope.getHeader();
        if (header == null) {
            header = envelope.addHeader();
        }
        SOAPElement security = header.addChildElement("Security", "wsse", "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd");
        SOAPElement usernameToken =security.addChildElement("UsernameToken", "wsse");
        //da ricerca
        //usernameToken.addAttribute(new QName("xmlns:wsu"), "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd");
        //se non funge provare con questa 
        usernameToken.addAttribute(new QName("xmlns:wsse"), "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd");
        
        SOAPElement usernameSOAPElement = usernameToken.addChildElement("Username", "wsse");
        usernameSOAPElement.addTextNode(username);

        SOAPElement passwordSOAPElement = usernameToken.addChildElement("Password", "wsse");
        passwordSOAPElement.setAttribute("Type", "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-username-token-profile-1.0#PasswordText");
        passwordSOAPElement.addTextNode(pw);        
        /*
        try {
			smc.getMessage().writeTo(System.out);
		} catch (IOException e) {
			log.error(methodName, e);
		}
        */
	}

	@Override
	public boolean handleFault(SOAPMessageContext context) {
		return false;
	}

	@Override
	public void close(MessageContext context) {
	}

	@Override
	public Set<QName> getHeaders() {
		return null;
	}

}


/*
	<wsse:Security xmlns:wsse="http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd" xmlns="http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd" xmlns:env="http://schemas.xmlsoap.org/soap/envelope/" soap:mustUnderstand="1">
	<wsse:UsernameToken xmlns:wsse="http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd" xmlns="http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd">	
		<wsse:Username>WS_UNICA_BKOFF_CMTO</wsse:Username>
		<wsse:Password Type="http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-username-token-profile-1.0#PasswordText">mypass$1</wsse:Password>
	</wsse:UsernameToken></wsse:Security></soap:Header>
	*/
