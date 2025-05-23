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
package it.csi.cpass.cpassbe.lib.util.soap;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.xml.namespace.QName;
import javax.xml.soap.AttachmentPart;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;

import it.csi.cpass.cpassbe.lib.util.log.LogUtil;
import it.csi.cpass.cpassbe.lib.util.threadlocal.CpassThreadLocalContainer;
/**
 * SOAP handler for request/response logging
 */
public class AllegatoSOAPHandler implements SOAPHandler<SOAPMessageContext> {
	protected final LogUtil log = new LogUtil(getClass());


	@Override
	public boolean handleMessage(final SOAPMessageContext context) {
		final SOAPMessage msg = context.getMessage();
		final boolean request = Boolean.TRUE.equals(context.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY));
		if (!request) {
			estraiAllegati(msg);
		}
		return true;
	}

	private void estraiAllegati(SOAPMessage msg) {
		// TODO Auto-generated method stub
		List<InputStream> listaAllegati = new ArrayList<>();
		for(Iterator<AttachmentPart> it = msg.getAttachments(); it.hasNext(); ) {
			InputStream xmlAllegato = null;
			AttachmentPart attachment = it.next();
		    String id = attachment.getContentId();
		    String type = attachment.getContentType();
			log.info("estraiAllegato",  "Attachment " + id + " has content type " + type);
			try {
				xmlAllegato =  attachment.getDataHandler().getInputStream();
			} catch (IOException | SOAPException e) {
				log.error("estraiAllegati",  "Attachment " + id + " has content type " + type+" ERROR "+ e.getMessage());
			}
			 listaAllegati.add(xmlAllegato);
		}
		CpassThreadLocalContainer.ALLEGATI.set(listaAllegati);

	}

	// Salvo i file restituiti dallla response
	/*
		private ArrayList salvoAllegatiResponse(SOAPMessage soapResponse)  throws Exception {

			FileOutputStream fost = null;
			InputStream inst = null;
			File xmlOne = null;
			int count = 0;
			int nrTotAtt = 0;
			ArrayList allegatiOut = new ArrayList();
			byte buffer[] = new byte [1024];


			nrTotAtt = soapResponse.countAttachments();

			if ( nrTotAtt > 0 ){
				//Verifico se esiste il metodo getAttachments().
				Iterator iter =soapResponse.getAttachments();

				if (iter!=null ){
					int i = 0;
					while (iter.hasNext()) {
					    AttachmentPart attachment = (AttachmentPart)iter.next();
					    String id = attachment.getContentId();
					    String type = attachment.getContentType();

					    System.out.print("Attachment " + id + " has content type " + type);
					    //Object content = attachment.getContent();

					    xmlOne = new File(_config.getTempPath()+File.separator+"file"+i);
					    fost = new FileOutputStream(xmlOne);

						inst = attachment.getDataHandler().getInputStream();
						fost = new FileOutputStream(xmlOne);
						while ( (count = inst.read(buffer)) > 0) {
							// scrivo il buffer
							fost.write(buffer, 0, count);
						}
						allegatiOut.add(_config.getTempPath()+File.separator+"file"+i);
					    i++;
					}
				}
			}
			return allegatiOut;
		}
		*/

	@Override
	public boolean handleFault(final SOAPMessageContext context) {
		return false;
	}

	@Override
	public void close(final MessageContext context) {
		// Not required for logging
	}

	@Override
	public Set<QName> getHeaders() {
		// Not required for logging
		return new HashSet<>();
	}


}
