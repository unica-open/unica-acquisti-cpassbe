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
package it.csi.cpass.cpassbe.ejb.business.be.service.impl.stampe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import it.csi.cpass.cpassbe.ejb.business.be.dad.DestinatarioOrdineDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.StampeDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.SystemDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.stampe.StampaRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.stampe.StampaResponse;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;
import it.csi.cpass.cpassbe.lib.dto.ParametroStampa;
import it.csi.cpass.cpassbe.lib.dto.ord.Destinatario;
import it.csi.cpass.cpassbe.lib.dto.stampe.ReportModel;
import it.csi.cpass.cpassbe.lib.dto.stampe.ReportModelContainer;
import it.csi.cpass.cpassbe.lib.util.serialization.JsonUtility;

/**
 * Retrieves
 */
public class StampaService extends BaseStampeService<StampaRequest, StampaResponse> {

	private final DestinatarioOrdineDad destinatarioOrdineDad;

	/**
	 * Constructor
	 * @param configurationHelper the configuration helper
	 * @param stampeDad the stampe DAD
	 */
	public StampaService(ConfigurationHelper configurationHelper, StampeDad stampeDad,DestinatarioOrdineDad destinatarioOrdineDad, SystemDad systemDad) {
		super(configurationHelper, stampeDad, systemDad);
		this.destinatarioOrdineDad = destinatarioOrdineDad;
	}

	@Override
	protected void checkServiceParams() {
		checkNotNull(request.getNomeStampaLogico(), "nome stampa Logico");
		//checkNotNull(request.getFormatFile(), "formato file");
	}

	@Override
	protected void execute() {
		String formatoFile = request.getFormatFile();

		final List<ParametroStampa> parametri = stampeDad.getParametriStampeByNomeStampa(request.getNomeStampaLogico());
		if(StringUtils.isBlank(formatoFile) || formatoFile.equals("default")) {
			formatoFile = parametri.get(0).getFormatoStampa();
		}

		final List<NameValuePair> params = new ArrayList<>();
		final String nomeStampa= parametri.get(0).getNomeStampa();
		final String nomeTemplate = parametri.get(0).getFileNameTemplate();

		// se il filename è passato uso quello diversamente utilizzo quello di default preso dalla tabella
		String fileName = request.getFileName();
		if(fileName==null || fileName.trim().equalsIgnoreCase("") || fileName.trim().equalsIgnoreCase("default")) {
			fileName = parametri.get(0).getFileName();
		}

		final Boolean multiplo= parametri.get(0).getMultiplo();
		for(int i = 0; i < parametri.size(); i++) {
			final ParametroStampa ps = parametri.get(i);
			params.add(new BasicNameValuePair(ps.getParametro(), request.getListaParametri().get(i)));
		}

		if(multiplo) {
			final ReportModelContainer rmc = demandaAllaCompetenza(fileName,nomeStampa,nomeTemplate,formatoFile,params);
			final String strObj = JsonUtility.serialize(rmc);
			if(rmc.getData().size()>1) {
				callBirtMulti(strObj,  nomeTemplate,fileName, "zip");
			}else {
				callBirt(strObj,  nomeTemplate,fileName, formatoFile);
			}
		}else {
			callBirt(params, nomeTemplate, fileName, formatoFile);
		}

	}

	private ReportModelContainer demandaAllaCompetenza(String fileName,String nomeStampa,String nomeTemplate, String formatoFile, List<NameValuePair> params) {
		ReportModelContainer rmc = new ReportModelContainer();
		if(nomeStampa.equalsIgnoreCase("VERBALE_CONSEGNA")) {
			final List<Destinatario> listaDestinatari = destinatarioOrdineDad.findByOrdine(UUID.fromString(params.get(0).getValue()));
			rmc  = creaObjStampaCopiaDestinatario(listaDestinatari,fileName,nomeStampa,nomeTemplate,formatoFile,params);
		}
		return rmc;
	}

	private ReportModelContainer creaObjStampaCopiaDestinatario(List<Destinatario> listDestinatario,String fileName,String nomeStampa,String nomeTemplate,String formatoFile,List<NameValuePair> listaParametri) {
		final ReportModelContainer rmc = new ReportModelContainer();
		rmc.setFormat("zip");
		rmc.setFileName(fileName);

		final List<ReportModel> data = new ArrayList<>();

		for(final Destinatario el :listDestinatario) {
			final ReportModel rm = new ReportModel();
			rm.set__report(nomeTemplate);
			rm.set__format(formatoFile);
			rm.set__attachment(Boolean.TRUE);
			//rm.setFileName(nomeStampa);
			rm.setFileName(fileName);
			final Map<String, Object> params = new HashMap<>();
			for(final NameValuePair nvp: listaParametri) {
				//params.put(nvp.getName(),nvp.getValue());
				params.put(nvp.getName(),el.getId().toString());
			}
			rm.setParams(params);
			data.add(rm );
		}
		rmc.setData(data);
		return rmc;
	}
}








