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
package it.csi.cpass.cpassbe.ejb.business.be.service.impl.batch;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import it.csi.cpass.cpassbe.ejb.business.be.dad.CommonDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.ElaborazioneDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.ElaborazioneMessaggioDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.ElaborazioneTipoDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.EnteDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.ImpegnoDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.SystemDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.batch.GetCaricamentoAggiornamentiSubImpegniRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.batch.GetCaricamentoAggiornamentiSubImpegniResponse;
import it.csi.cpass.cpassbe.ejb.exception.NotFoundException;
import it.csi.cpass.cpassbe.ejb.util.ConstantsCPassElaborazione;
import it.csi.cpass.cpassbe.ejb.util.ConstantsCPassParametro;
import it.csi.cpass.cpassbe.ejb.util.ConstantsCPassParametro.ValoreEnum;
import it.csi.cpass.cpassbe.ejb.util.UtilityParametri;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;
import it.csi.cpass.cpassbe.ejb.validatori.ValidaSubImpegniEsterniCsv;
import it.csi.cpass.cpassbe.lib.dto.Elaborazione;
import it.csi.cpass.cpassbe.lib.dto.Ente;
import it.csi.cpass.cpassbe.lib.dto.FlussoSubimpegniEsterni;
import it.csi.cpass.cpassbe.lib.dto.Parametro;
import it.csi.cpass.cpassbe.lib.dto.error.CoreError;

/**
 * GetVerificaInvioContabilitaService
 */
public class GetCaricamentoAggiornamentiSubImpegniService extends BaseBatchService<GetCaricamentoAggiornamentiSubImpegniRequest, GetCaricamentoAggiornamentiSubImpegniResponse> {
	private final ImpegnoDad impegnoDad;
	private 		int rigaOk = 0;
	private 		int rigaKo = 0;

	/**
	 * Constructor
	 * @param configurationHelper the helper for the configuration
	 * @param commonDad the DAD for the common
	 */
	public GetCaricamentoAggiornamentiSubImpegniService(ConfigurationHelper configurationHelper,
			ElaborazioneDad elaborazioneDad,
			ElaborazioneTipoDad elaborazioneTipoDad,
			ElaborazioneMessaggioDad elaborazioneMessaggioDad,
			EnteDad enteDad,
			CommonDad commonDad,
			SystemDad systemDad,
			ImpegnoDad impegnoDad) {
		super(configurationHelper,elaborazioneDad,elaborazioneTipoDad,elaborazioneMessaggioDad,enteDad,commonDad,systemDad);
		this.impegnoDad =  impegnoDad;
	}

	@Override
	protected void execute() {
		final Ente ente = enteDad.getEnteByCodice(request.getEnte()).orElseThrow(() -> new NotFoundException("ente"));
		final Integer numelab = request.getNumelab();
		final String dataElab = request.getDataElab();
		final Map<String, String> params = UtilityParametri.readParams(systemDad, ConstantsCPassParametro.RiferimentoEnum.IMPEGNOEXT.getCostante(), ente.getId() );
		final String pathSubImpegniCsv = UtilityParametri.getParameter(params, ConstantsCPassParametro.ChiaveEnum.PATH_SUBIMPEGNI_CSV.getCostante());
		cancellaTabelleDiAppoggio(ente.getId());
		final Elaborazione elab = inizializzaElaborazione(ente.getId(),ConstantsCPassElaborazione.StatoEnum.CARICA_SUBIMPEGNO_TMP.getStatoDB(),ente.getCodice(),request.getNumelab(),request.getDataElab());

		try {
			caricaControllaFileCsvSubImpegni(ente, pathSubImpegniCsv,numelab,dataElab, elab);
			if(rigaKo > 0 ) {
				response.setEsito("KO");
				concludiElaborazione(elab, "KO", ConstantsCPassElaborazione.StatoEnum.CARICA_SUBIMPEGNO_TMP.getStatoDB() );
				log.warn("GetCaricamentoAggiornamentiSUBImpegniService", "elaborazione conclusa CON errori " + rigaKo);

			}else {
				response.setEsito("OK");
				concludiElaborazione(elab, "OK", ConstantsCPassElaborazione.StatoEnum.CARICA_SUBIMPEGNO_TMP.getStatoDB() );
				log.info("GetCaricamentoAggiornamentiSUBImpegniService", "elaborazione conclusa SENZA errori " + rigaOk);
			}

		} catch (final IOException e) {
			log.error("execute", "IOException ",e);
			concludiElaborazione(elab, "KO con errore imprevisto controllare log" , ConstantsCPassElaborazione.StatoEnum.CARICA_SUBIMPEGNO_TMP.getStatoDB() );
		}
		log.info("caricamento sub ", "dopo il caricamento ");
	}

	private void cancellaTabelleDiAppoggio(UUID enteId) {
		final Parametro gs = systemDad.getParametro("STORICO_GG_FLUSSI", "IMPEGNOEXT", enteId);
		if(gs!= null && gs.getValore()!= null) {
			impegnoDad.delFlussoSubImpegniEsterni(Integer.parseInt(gs.getValore()));
		}else {
			impegnoDad.delFlussoSubImpegniEsterni(5);
		}
	}

	/**
	 *
	 * @param ente
	 * @param pathSubImpegniCsv
	 * @return List<FlussoSubimpegniEsterni>
	 * @throws IOException
	 */
	private List<FlussoSubimpegniEsterni> caricaControllaFileCsvSubImpegni(Ente ente, String pathSubImpegniCsv,Integer numelab,String dataElab, Elaborazione elab) throws IOException {
		final String methodName= "caricaControllaFileCsvSubImpegni";
		final List<FlussoSubimpegniEsterni> righeProcessabili = new ArrayList<>();
		Reader readerSubImp;
		log.info(methodName, "pathSubImpegniCsv " + pathSubImpegniCsv);
		readerSubImp = new FileReader(pathSubImpegniCsv);

		try (BufferedReader br = new BufferedReader(readerSubImp)) {
			final int riga   = 0;
			final ValidaSubImpegniEsterniCsv validatore = new ValidaSubImpegniEsterniCsv();
			String line ="";
			//int posizioneRigaDB = 1;
			for (int posizioneRiga = 1; (line = br.readLine()) != null; posizioneRiga++) {
				log.debug(methodName, line);
				if(posizioneRiga > 1) {
					FlussoSubimpegniEsterni imp = new FlussoSubimpegniEsterni();
					line = line.replace('"', ' ');
					final String[] celledellaRiga = line.split(";");
					imp = validatore.validaCreaRigaSubImpegno(posizioneRiga,celledellaRiga);
					if (imp.getEsito().equals("KO")) {
						rigaKo = rigaKo +1;
						if(imp.getEsito().length()>501) {
							imp.setEsito("KO Riga --> "+ riga +" errori: " + imp.getEsito().substring(1,500));
						}else {
							imp.setEsito("KO Riga --> "+ riga +" errori: " + imp.getEsito());
						}
						inserisciMessaggioErroreInElaborazione(elaborazioneMessaggioDad,elab,"E",ConstantsCPassElaborazione.StatoEnum.CARICA_SUBIMPEGNO_TMP.getStatoDB(), imp.getErrore() );

					}else {
						rigaOk = rigaOk +1;
						righeProcessabili.add(imp);
					}
					imp.setEnteCodice(ente.getCodice());
					imp.setIdEnte(ente.getId());
					imp.setDataCaricamento(new Date());
					imp.setNumElaborazioneDiGiornata(numelab);
					imp.setElaborazioneId(String.valueOf(elab.getId()));
					imp.setDataElaborazione(dataElab);
					impegnoDad.saveFlussoSubImpegniEsterni(imp);
				}
			}
		} catch (final IOException e) {
			log.error("execute", "IOException ",e);
			inserisciMessaggioElaborazione(elab,ValoreEnum.AGGIORNAMENTO_ODS.getCostante(),CoreError.SYSTEM_ERROR.getCode(), e.getMessage());
			concludiElaborazione(elab, "KO", ConstantsCPassElaborazione.StatoEnum.AGGIORNAMENTO_ODS.getStatoDB() );
		}
		return righeProcessabili;
	}

}