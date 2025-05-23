/*-
 * ========================LICENSE_START=================================
 * CPASS BackEnd - ANGULAR submodule
 * %%
 * Copyright (C) 2019 - 2021 CSI Piemonte
 * %%
 * SPDX-FileCopyrightText: Copyright 2019 - 2021 | CSI Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 * =========================LICENSE_END==================================
 */
package it.csi.cpass.cpassbe.angular.util.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.CsiLogAuditDao;
import it.csi.cpass.cpassbe.lib.util.log.LogUtil;
/**
 * Servlet implementation class LogOut
 */
@WebServlet("/logout")
public class LogOutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String SHIBBOLETH_COOKIE_PREFIX ="_shibsession_";
	private static final String LOGOUT_URL_INIT_PARAM = "logout.shibbolethSSOURL";
	//https://dev-unica-acquisti-coto.nivolapiemonte.it/cpass-devsliv1wrup/Shibboleth.sso/Logout
	private String shibbolethSSOLogoutUrl;

	private final LogUtil log = new LogUtil(getClass());
	//private CsiLogAuditDao csiLogAuditDao;
	@EJB private CsiLogAuditDao csiLogAuditDao;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		this.shibbolethSSOLogoutUrl = config.getInitParameter(LOGOUT_URL_INIT_PARAM);
		log.warn("init", "INIZIALIZZATA SERVLET LOGOUT");
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		final String methodName = "doGet";
		log.info(methodName, "START");
		String cf = request.getParameter("parCf");
		//Utente utente = CpassThreadLocalContainer.UTENTE_CONNESSO.get();
		//insertCsiLogAudit(utente.getCodiceFiscale());
		//insertCsiLogAudit(cf);
		String shibCookie = "";
		String url = "";
		Cookie[] listaCookies = request.getCookies();
		if(listaCookies == null) {
			log.fatal(methodName, "non ho Cookie: ");
			return;
		}
		List<String> listValoriCookieShibbolet = new ArrayList<>();
		for(Cookie co : listaCookies) {
			log.debug(methodName, "Cookie name: " + co.getName());
			log.debug(methodName, "Cookie value: " + co.getValue());
			if(co.getName().indexOf(SHIBBOLETH_COOKIE_PREFIX)!=-1) {
				shibCookie = co.getName().replace(SHIBBOLETH_COOKIE_PREFIX,"");
				listValoriCookieShibbolet.add(shibCookie);
				log.info(methodName, "Cookie name: " + co.getName());
				log.info(methodName, "Cookie value: " + co.getValue());
				log.info(methodName, "ShibCookie: " + shibCookie);
			}

		}
		int lenValore = 0;
		shibCookie    = "";

		for (String valorePossibile : listValoriCookieShibbolet) {
			lenValore = valorePossibile.length();
			if(shibCookie.length() == 0 || lenValore<shibCookie.length()) {
				shibCookie =valorePossibile;
			}
		}

		log.info(methodName, "shibCookie selezionato --> "+ shibCookie);
		if("".equalsIgnoreCase(shibCookie)) {
			log.warn(methodName, "***********************************************************************");
			log.info(methodName, "controllare i Cookie perchè nessuno soddisfa le condizioni");
			log.warn(methodName, "***********************************************************************");
		}else {
			url = estraiUrl(shibCookie);
		}

		response.sendRedirect(url);
		log.info(methodName, "STOP");
	}

	private String estraiUrl(String shib) {
		final String methodName = "estraiUrl";
		log.debug(methodName, "start");
		log.debug(methodName, "shibbolethSSOLogoutURL " + shibbolethSSOLogoutUrl);
		String replacedURL = shibbolethSSOLogoutUrl.replace("%%SHIB%%", shib);
		log.info(methodName, "url di logout --> " + replacedURL);
		return replacedURL;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
/*
	protected void insertCsiLogAudit(String cf) {
		//CsiLogAuditDao csiLogAuditDao = new CsiLogAuditDaoImpl();
		CsiLogAudit csiLogAudith = new CsiLogAudit();
		csiLogAudith.setCfUtente(cf);
		csiLogAudith.setDataOra(new Date());
		csiLogAudith.setOperazione("LOGOUT");
		csiLogAuditDao.insert(csiLogAudith );
	}
*/
}
