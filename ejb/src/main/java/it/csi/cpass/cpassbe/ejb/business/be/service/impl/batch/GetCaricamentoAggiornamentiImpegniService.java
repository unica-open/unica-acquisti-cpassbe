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
import it.csi.cpass.cpassbe.ejb.business.be.service.request.batch.GetCaricamentoAggiornamentiImpegniRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.batch.GetCaricamentoAggiornamentiImpegniResponse;
import it.csi.cpass.cpassbe.ejb.exception.NotFoundException;
import it.csi.cpass.cpassbe.ejb.util.ConstantsCPassElaborazione;
import it.csi.cpass.cpassbe.ejb.util.ConstantsCPassParametro;
import it.csi.cpass.cpassbe.ejb.util.ConstantsCPassParametro.ValoreEnum;
import it.csi.cpass.cpassbe.ejb.util.UtilityParametri;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;
import it.csi.cpass.cpassbe.ejb.validatori.ValidaImpegniEsterniCsv;
import it.csi.cpass.cpassbe.lib.dto.Elaborazione;
import it.csi.cpass.cpassbe.lib.dto.Ente;
import it.csi.cpass.cpassbe.lib.dto.FlussoImpegniEsterni;
import it.csi.cpass.cpassbe.lib.dto.Parametro;
import it.csi.cpass.cpassbe.lib.dto.error.CoreError;

/**
 * GetVerificaInvioContabilitaService
 */
public class GetCaricamentoAggiornamentiImpegniService extends BaseBatchService<GetCaricamentoAggiornamentiImpegniRequest, GetCaricamentoAggiornamentiImpegniResponse> {
	private final ImpegnoDad impegnoDad;
	private int rigaOk = 0;
	private int rigaKo = 0;

	/**
	 * Constructor
	 * @param configurationHelper the helper for the configuration
	 * @param commonDad the DAD for the common
	 */
	public GetCaricamentoAggiornamentiImpegniService(ConfigurationHelper configurationHelper,
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
	protected void execute()  {
		final Ente ente = enteDad.getEnteByCodice(request.getEnte()).orElseThrow(() -> new NotFoundException("ente"));
		final Map<String, String> params = UtilityParametri.readParams(systemDad, ConstantsCPassParametro.RiferimentoEnum.IMPEGNOEXT.getCostante(), ente.getId() );
		final String pathImpegniCsv = UtilityParametri.getParameter(params, ConstantsCPassParametro.ChiaveEnum.PATH_IMPEGNI_CSV.getCostante());
		//String pathSubImpegniCsv = UtilityParametri.getParameter(params, ConstantsCPassParametro.ChiaveEnum.PATH_SUBIMPEGNI_CSV.getCostante());
		cancellaTabelleDiAppoggio(ente.getId());
		//resetSequenceTabelleDiAppoggio();
		final Integer numelab = request.getNumelab();
		final String dataElab = request.getDataElab();

		final Elaborazione elab = inizializzaElaborazione(ente.getId(),ConstantsCPassElaborazione.StatoEnum.CARICA_IMPEGNO_TMP.getStatoDB(),ente.getCodice(),request.getNumelab(),request.getDataElab());
		try {
			caricaControllaFileCsvImpegni(ente, pathImpegniCsv, numelab,dataElab,  elab);
			if(rigaKo > 0 ) {
				response.setEsito("KO");
				concludiElaborazione(elab, "KO", ConstantsCPassElaborazione.StatoEnum.CARICA_IMPEGNO_TMP.getStatoDB() );
				log.warn("GetCaricamentoAggiornamentiImpegniService", "elaborazione conclusa CON errori " + rigaKo);

			}else {
				response.setEsito("OK");
				concludiElaborazione(elab, "OK", ConstantsCPassElaborazione.StatoEnum.CARICA_IMPEGNO_TMP.getStatoDB() );
				log.info("GetCaricamentoAggiornamentiImpegniService", "elaborazione conclusa SENZA errori " + rigaOk);
			}
		} catch (final IOException e) {
			log.error("execute", "IOException ",e);
			response.setEsito("KO");
			concludiElaborazione(elab, "KO con errore imprevisto controllare log" , ConstantsCPassElaborazione.StatoEnum.CARICA_IMPEGNO_TMP.getStatoDB() );
			//e.printStackTrace();
		}
		log.info("execute ", "dopo il caricamento Importo");
	}

	private void cancellaTabelleDiAppoggio(UUID enteId) {
		final Parametro gs = systemDad.getParametro("STORICO_GG_FLUSSI", "IMPEGNOEXT", enteId);
		if(gs!= null && gs.getValore()!= null) {
			impegnoDad.delFlussoImpegniEsterni(Integer.parseInt(gs.getValore()));
		}else {
			impegnoDad.delFlussoImpegniEsterni(5);
		}
	}

	/**
	 *
	 * @param ente
	 * @param pathImpegniCsv
	 * @return List<FlussoImpegniEsterni>
	 * @throws IOException
	 */
	private List<FlussoImpegniEsterni> caricaControllaFileCsvImpegni(Ente ente, String pathImpegniCsv,Integer numelab, String dataElab, Elaborazione elab) throws IOException {
		final String methodName = "caricaControllaFileCsvImpegni";
		final List<FlussoImpegniEsterni> righeProcessabili = new ArrayList<>();
		Reader readerImp;
		log.info(methodName, "pathImpegniCsv " + pathImpegniCsv);
		readerImp = new FileReader(pathImpegniCsv);
		try (BufferedReader br = new BufferedReader(readerImp)) {
			final int riga   = 0;
			final ValidaImpegniEsterniCsv validatore = new ValidaImpegniEsterniCsv();
			String line ="";
			//int posizioneRigaDB = 1;
			for (int posizioneRiga = 1; (line = br.readLine()) != null; posizioneRiga++) {
				log.debug(methodName, line);
				if(posizioneRiga > 1) {
					FlussoImpegniEsterni imp = new FlussoImpegniEsterni();
					line = line.replace('"', ' ');
					final String[] celledellaRiga = line.split(";");
					imp = validatore.validaCreaRigaImpegno(posizioneRiga,celledellaRiga);
					if (imp.getEsito().equals("KO")) {
						rigaKo = rigaKo +1;
						if(imp.getEsito().length()>501) {
							imp.setEsito("KO Riga --> "+ riga +" errori: " + imp.getEsito().substring(1,500));
						}else {
							imp.setEsito("KO Riga --> "+ riga +" errori: " + imp.getEsito());
						}
						inserisciMessaggioErroreInElaborazione(elaborazioneMessaggioDad,elab,"E",ConstantsCPassElaborazione.StatoEnum.CARICA_IMPEGNO_TMP.getStatoDB(), imp.getErrore() );
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
					//log.info(methodName, "anno " + imp.getAnnoImpegno() + " numero "+ imp.getNumImpegno());
					//log.info(methodName, "num righeProcessabili " + righeProcessabili.size());
					impegnoDad.saveFlussoImpegniEsterni(imp);
				}
			}
		}catch (final IOException e) {
			log.error("execute", "IOException ",e);
			inserisciMessaggioElaborazione(elab,ValoreEnum.AGGIORNAMENTO_ODS.getCostante(),CoreError.SYSTEM_ERROR.getCode(), e.getMessage());
			concludiElaborazione(elab, "KO", ConstantsCPassElaborazione.StatoEnum.AGGIORNAMENTO_ODS.getStatoDB() );
		}
		return righeProcessabili;
	}

}