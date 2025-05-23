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
package it.csi.cpass.cpassbe.ejb.mapper;

import org.mapstruct.factory.Mappers;

import it.csi.cpass.cpassbe.ejb.mapper.mag.MagazzinoMapper;
import it.csi.cpass.cpassbe.ejb.mapper.ord.AooActaMapper;
import it.csi.cpass.cpassbe.ejb.mapper.ord.DestinatarioInvioNsoMapper;
import it.csi.cpass.cpassbe.ejb.mapper.ord.DestinatarioInvioNsoXmlMapper;
import it.csi.cpass.cpassbe.ejb.mapper.ord.DestinatarioOrdineMapper;
import it.csi.cpass.cpassbe.ejb.mapper.ord.DocumentiOrdineMapper;
import it.csi.cpass.cpassbe.ejb.mapper.ord.DocumentiOrdineMinimalMapper;
import it.csi.cpass.cpassbe.ejb.mapper.ord.ImpegnoAssociatoMapper;
import it.csi.cpass.cpassbe.ejb.mapper.ord.ImpegnoOrdineMapper;
import it.csi.cpass.cpassbe.ejb.mapper.ord.OggettiSpesaMapper;
import it.csi.cpass.cpassbe.ejb.mapper.ord.OggettiSpesaMinimalMapper;
import it.csi.cpass.cpassbe.ejb.mapper.ord.ProtocolloOrdineMapper;
import it.csi.cpass.cpassbe.ejb.mapper.ord.RdaOrdineMapper;
import it.csi.cpass.cpassbe.ejb.mapper.ord.RigaOrdineMapper;
import it.csi.cpass.cpassbe.ejb.mapper.ord.SettoreAooActaMapper;
import it.csi.cpass.cpassbe.ejb.mapper.ord.StatoNsoMapper;
import it.csi.cpass.cpassbe.ejb.mapper.ord.SubimpegnoAssociatoMapper;
import it.csi.cpass.cpassbe.ejb.mapper.ord.SubimpegnoOrdineMapper;
import it.csi.cpass.cpassbe.ejb.mapper.ord.TestataOrdineMapper;
import it.csi.cpass.cpassbe.ejb.mapper.ord.TipoOrdineMapper;
import it.csi.cpass.cpassbe.ejb.mapper.ord.TipoProceduraOrdMapper;
import it.csi.cpass.cpassbe.ejb.mapper.ord.UfficioSerieMapper;
import it.csi.cpass.cpassbe.ejb.mapper.ord.VOrdineMapper;
import it.csi.cpass.cpassbe.ejb.mapper.ord.evasione.CausaleSospensioneEvasioneMapper;
import it.csi.cpass.cpassbe.ejb.mapper.ord.evasione.DestinatarioEvasioneMapper;
import it.csi.cpass.cpassbe.ejb.mapper.ord.evasione.DocumentoTrasportoMapper;
import it.csi.cpass.cpassbe.ejb.mapper.ord.evasione.DocumentoTrasportoRigaMapper;
import it.csi.cpass.cpassbe.ejb.mapper.ord.evasione.DocumentoTrasportoXmlMapper;
import it.csi.cpass.cpassbe.ejb.mapper.ord.evasione.ImpegnoEvasioneMapper;
import it.csi.cpass.cpassbe.ejb.mapper.ord.evasione.RiepilogoFatturaMapper;
import it.csi.cpass.cpassbe.ejb.mapper.ord.evasione.RigaEvasioneMapper;
import it.csi.cpass.cpassbe.ejb.mapper.ord.evasione.SubimpegnoEvasioneMapper;
import it.csi.cpass.cpassbe.ejb.mapper.ord.evasione.TestataEvasioneMapper;
import it.csi.cpass.cpassbe.ejb.mapper.ord.evasione.TipoEvasioneMapper;
import it.csi.cpass.cpassbe.ejb.mapper.ord.mepa.ScaricoMepaRigaMapper;
import it.csi.cpass.cpassbe.ejb.mapper.ord.mepa.ScaricoMepaScontiMapper;
import it.csi.cpass.cpassbe.ejb.mapper.ord.mepa.ScaricoMepaTestataMapper;
import it.csi.cpass.cpassbe.ejb.mapper.ord.mepa.ScaricoMepaXmlMapper;
import it.csi.cpass.cpassbe.ejb.mapper.ord.rda.RigaRdaMapper;
import it.csi.cpass.cpassbe.ejb.mapper.ord.rda.RigaRdaReverseMapper;
import it.csi.cpass.cpassbe.ejb.mapper.ord.rda.TestataRdaMapper;
import it.csi.cpass.cpassbe.ejb.mapper.pba.AcquistiCapPrivatiDaTrasmettereMapper;
import it.csi.cpass.cpassbe.ejb.mapper.pba.AcquistiDaTrasmettereMapper;
import it.csi.cpass.cpassbe.ejb.mapper.pba.AcquistoVariatoMapper;
import it.csi.cpass.cpassbe.ejb.mapper.pba.AusaMapper;
import it.csi.cpass.cpassbe.ejb.mapper.pba.InterventoAltriDatiMapper;
import it.csi.cpass.cpassbe.ejb.mapper.pba.InterventoCigMapper;
import it.csi.cpass.cpassbe.ejb.mapper.pba.InterventoImportiMapper;
import it.csi.cpass.cpassbe.ejb.mapper.pba.InterventoImportiMinimalMapper;
import it.csi.cpass.cpassbe.ejb.mapper.pba.InterventoMapper;
import it.csi.cpass.cpassbe.ejb.mapper.pba.InterventoMinimalMapper;
import it.csi.cpass.cpassbe.ejb.mapper.pba.ModalitaAffidamentoMapper;
import it.csi.cpass.cpassbe.ejb.mapper.pba.NutsMapper;
import it.csi.cpass.cpassbe.ejb.mapper.pba.PrioritaMapper;
import it.csi.cpass.cpassbe.ejb.mapper.pba.ProgrammaMapper;
import it.csi.cpass.cpassbe.ejb.mapper.pba.RicompresoTipoMapper;
import it.csi.cpass.cpassbe.ejb.mapper.pba.RisorsaMapper;
import it.csi.cpass.cpassbe.ejb.mapper.pba.SettoreInterventiMapper;
import it.csi.cpass.cpassbe.ejb.mapper.pba.StatiInterventoMapper;
import it.csi.cpass.cpassbe.ejb.mapper.pba.StoricoInterventoRupMapper;
import it.csi.cpass.cpassbe.ejb.mapper.pba.TipoAcquistoMapper;
import it.csi.cpass.cpassbe.ejb.mapper.pba.TipoProceduraPbaMapper;
import it.csi.cpass.cpassbe.ejb.mapper.rms.RegoleSmistamentoRmsMapper;
import it.csi.cpass.cpassbe.ejb.mapper.rms.RigaRmsMapper;
import it.csi.cpass.cpassbe.ejb.mapper.rms.RigaRmsReverseMapper;
import it.csi.cpass.cpassbe.ejb.mapper.rms.RmsDaSmistareMapper;
import it.csi.cpass.cpassbe.ejb.mapper.rms.RmsRigaRdaMapper;
import it.csi.cpass.cpassbe.ejb.mapper.rms.StatiRigaRmsMapper;
import it.csi.cpass.cpassbe.ejb.mapper.rms.TestataRmsMapper;
/**
 * Mappers for Cpass
 */
public final class CpassMappers {
	/** Mapper for Cpv */
	public static final CpvMapper CPV = Mappers.getMapper(CpvMapper.class);
	/** Mapper for Cpv */
	public static final CpvOdsMapper CPV_ODS = Mappers.getMapper(CpvOdsMapper.class);
	/** Mapper for ModalitaAffidamento */
	public static final ModalitaAffidamentoMapper MODALITA_AFFIDAMENTO = Mappers.getMapper(ModalitaAffidamentoMapper.class);
	/** Mapper for Nuts */
	public static final NutsMapper NUTS = Mappers.getMapper(NutsMapper.class);
	/** Mapper for Priorita */
	public static final PrioritaMapper PRIORITA = Mappers.getMapper(PrioritaMapper.class);
	/** Mapper for Risorsa */
	public static final RisorsaMapper RISORSA = Mappers.getMapper(RisorsaMapper.class);
	/** Mapper for SettoreInterventi */
	public static final SettoreInterventiMapper SETTORE_INTERVENTI = Mappers.getMapper(SettoreInterventiMapper.class);
	/** Mapper for Intervento */
	public static final InterventoMapper INTERVENTO = Mappers.getMapper(InterventoMapper.class);
	/** Mapper for Intervento */
	public static final InterventoMinimalMapper INTERVENTO_MINIMAL = Mappers.getMapper(InterventoMinimalMapper.class);
	/** Mapper for Ente */
	public static final EnteMapper ENTE = Mappers.getMapper(EnteMapper.class);
	/** Mapper for Programma */
	public static final ProgrammaMapper PROGRAMMA = Mappers.getMapper(ProgrammaMapper.class);
	/** Mapper for InterventoImporti */
	public static final InterventoImportiMapper INTERVENTO_IMPORTI = Mappers.getMapper(InterventoImportiMapper.class);
	/** Mapper for InterventoImporti */
	public static final InterventoImportiMinimalMapper INTERVENTO_IMPORTI_MINIMAL = Mappers.getMapper(InterventoImportiMinimalMapper.class);
	/** Mapper for InterventoAltriDati */
	public static final InterventoAltriDatiMapper INTERVENTO_ALTRI_DATI = Mappers.getMapper(InterventoAltriDatiMapper.class);
	/** Mapper for Nuts */
	public static final StatoMapper STATO = Mappers.getMapper(StatoMapper.class);
	/** Mapper for Utente */
	public static final UtenteMapper UTENTE = Mappers.getMapper(UtenteMapper.class);
	/** Mapper for Utente */
	public static final UtenteRupDelegheMapper UTENTE_RUP_DELEGHE_MAPPER = Mappers.getMapper(UtenteRupDelegheMapper.class);

	/** Mapper for Utente */
	public static final UtenteCompletoMapper UTENTE_COMPLETO = Mappers.getMapper(UtenteCompletoMapper.class);
	/** Mapper for Ausa */
	public static final AusaMapper AUSA = Mappers.getMapper(AusaMapper.class);
	/** Mapper for RicompresoTipo */
	public static final RicompresoTipoMapper RICOMPRESO_TIPO = Mappers.getMapper(RicompresoTipoMapper.class);
	/** Mapper for AcquistoVariato */
	public static final AcquistoVariatoMapper ACQUISTO_VARIATO = Mappers.getMapper(AcquistoVariatoMapper.class);
	/** Mapper for Settore */
	public static final SettoreCustomMinimalMapper SETTORE_CUSTOM_MINIMAL = Mappers.getMapper(SettoreCustomMinimalMapper.class);
	/** Mapper for Settore */
	public static final SettoreCustomMapper SETTORE_CUSTOM = Mappers.getMapper(SettoreCustomMapper.class);

	/** Mapper for Settore Indirizzo */
	public static final SettoreIndirizzoReverseMapper SETTORE_INDIRIZZO_REVERSE = Mappers.getMapper(SettoreIndirizzoReverseMapper.class);
	public static final SettoreIndirizzoMapper SETTORE_INDIRIZZO = Mappers.getMapper(SettoreIndirizzoMapper.class);
	/** Mapper for Modulo */
	public static final ModuloMapper MODULO = Mappers.getMapper(ModuloMapper.class);
	/** Mapper for Modulo */
	public static final PermessoMapper PERMESSO = Mappers.getMapper(PermessoMapper.class);
	/** Mapper for Modulo */
	public static final ComunicazioneMapper COMUNICAZIONE = Mappers.getMapper(ComunicazioneMapper.class);
	/** Mapper for cpv */
	public static final TreeCpvMapper TREECPV = Mappers.getMapper(TreeCpvMapper.class);
	/** Mapper for cpvOds */
	public static final TreeCpvOdsMapper TREECPVODS = Mappers.getMapper(TreeCpvOdsMapper.class);
	/** ElaborazioneMapper*/
	public static final ElaborazioneMapper ELABORAZIONE =  Mappers.getMapper(ElaborazioneMapper.class);
	/** ElaborazioneMessaggioMapper*/
	public static final ElaborazioneMessaggioMapper ELABORAZIONE_MESSAGGIO =  Mappers.getMapper(ElaborazioneMessaggioMapper.class);
	/** ElaborazioneParametroMapper*/
	public static final ElaborazioneParametroMapper ELABORAZIONE_PARAMETRO =  Mappers.getMapper(ElaborazioneParametroMapper.class);
	/** Mapper for Parametro */
	public static final ParametroMapper PARAMETRO = Mappers.getMapper(ParametroMapper.class);
	/** Mapper for Parametro stampa */
	public static final ParametroStampaMapper PARAMETRO_STAMPA = Mappers.getMapper(ParametroStampaMapper.class);

	public static final ElaborazioneTipoMapper ELABORAZIONE_TIPO = Mappers.getMapper(ElaborazioneTipoMapper.class);

	public static final RuoloMapper RUOLO = Mappers.getMapper(RuoloMapper.class);

	public static final AliquoteIvaMapper ALIQUOTE_IVA = Mappers.getMapper(AliquoteIvaMapper.class);

	public static final UnitaMisuraMapper UNITA_MISURA = Mappers.getMapper(UnitaMisuraMapper.class);
	/** Mapper for OrdTipoOrdine */
	public static final TipoOrdineMapper TIPO_ORDINE = Mappers.getMapper(TipoOrdineMapper.class);
	/** Mapper for OrdTipoProcedura */
	public static final TipoProceduraOrdMapper TIPO_PROCEDURA_ORD = Mappers.getMapper(TipoProceduraOrdMapper.class);
	/** Mapper for OrdTipoProcedura */
	public static final TipoProceduraPbaMapper TIPO_PROCEDURA_PBA = Mappers.getMapper(TipoProceduraPbaMapper.class);
	/** Mapper for Ufficio */
	public static final UfficioMapper UFFICIO = Mappers.getMapper(UfficioMapper.class);

	public static final OggettiSpesaMapper OGGETTO_SPESA = Mappers.getMapper(OggettiSpesaMapper.class);
	public static final OggettiSpesaMinimalMapper OGGETTO_SPESA_MINIMAL = Mappers.getMapper(OggettiSpesaMinimalMapper.class);
	/** Mapper for TestataOrdine */
	public static final TestataOrdineMapper TESTATA_ORDINE = Mappers.getMapper(TestataOrdineMapper.class);
	/** Mapper for Fornitore */
	public static final FornitoreMapper FORNITORE = Mappers.getMapper(FornitoreMapper.class);
	/** Mapper for Fornitore */
	public static final FornitoreMinimalMapper FORNITORE_MINIMAL = Mappers.getMapper(FornitoreMinimalMapper.class);
	/** Mapper for Fornitore */
	public static final FornitoreEasyMapper FORNITORE_EASY = Mappers.getMapper(FornitoreEasyMapper.class);
	/** Mapper for Impegno */
	public static final ImpegnoMapper IMPEGNO = Mappers.getMapper(ImpegnoMapper.class);
	/** Mapper for Subimpegno */
	public static final SubImpegnoMapper SUBIMPEGNO = Mappers.getMapper(SubImpegnoMapper.class);
	/** Mapper for Destinatario */
	public static final DestinatarioOrdineMapper DESTINATARIO = Mappers.getMapper(DestinatarioOrdineMapper.class);
	/** Mapper for RigaOrdine */
	public static final RigaOrdineMapper RIGA_ORDINE = Mappers.getMapper(RigaOrdineMapper.class);
	/** Mapper for ImpegnoAssociato */
	public static final ImpegnoAssociatoMapper IMPEGNO_ASSOCIATO = Mappers.getMapper(ImpegnoAssociatoMapper.class);
	/** Mapper for SubImpegnoAssociato */
	public static final SubimpegnoAssociatoMapper SUBIMPEGNO_ASSOCIATO = Mappers.getMapper(SubimpegnoAssociatoMapper.class);
	/** Mapper for settore tree */
	public static final TreeSettoreMapper TREESETTORE = Mappers.getMapper(TreeSettoreMapper.class);
	/** Mapper for VOrdineMapper */
	public static final VOrdineMapper VORDINE = Mappers.getMapper(VOrdineMapper.class);
	/** Mapper for StatoNso */
	public static final StatoNsoMapper STATO_NSO = Mappers.getMapper(StatoNsoMapper.class);
	/** Mapper for Fornitore */
	public static final ListinoFornitoreMapper LISTINO_FORNITORE = Mappers.getMapper(ListinoFornitoreMapper.class);
	/** Mapper for ImpegnoOrdine */
	public static final ImpegnoOrdineMapper IMPEGNO_ORDINE = Mappers.getMapper(ImpegnoOrdineMapper.class);
	/** Mapper for SubimpegnoOrdine */
	public static final SubimpegnoOrdineMapper SUBIMPEGNO_ORDINE = Mappers.getMapper(SubimpegnoOrdineMapper.class);
	/** Mapper for TestataEvasione */
	public static final TestataEvasioneMapper TESTATA_EVASIONE = Mappers.getMapper(TestataEvasioneMapper.class);
	/** Mapper for DestinatarioEvasione */
	public static final DestinatarioEvasioneMapper DESTINATARIO_EVASIONE = Mappers.getMapper(DestinatarioEvasioneMapper.class);
	/** Mapper for TipoEvasione */
	public static final TipoEvasioneMapper TIPO_EVASIONE = Mappers.getMapper(TipoEvasioneMapper.class);
	/** Mapper for RigaEvasione */
	public static final RigaEvasioneMapper RIGA_EVASIONE = Mappers.getMapper(RigaEvasioneMapper.class);
	/** Mapper for ImpegnoEvasione */
	public static final ImpegnoEvasioneMapper IMPEGNO_EVASIONE = Mappers.getMapper(ImpegnoEvasioneMapper.class);
	/** Mapper for SubimpegnoEvasione */
	public static final SubimpegnoEvasioneMapper SUBIMPEGNO_EVASIONE = Mappers.getMapper(SubimpegnoEvasioneMapper.class);
	/** Mapper for TestataEvasione */
	public static final RiepilogoFatturaMapper RIEPILOGO_FATTURA = Mappers.getMapper(RiepilogoFatturaMapper.class);
	/** Mapper for CausaleSospensioneEvasione */
	public static final CausaleSospensioneEvasioneMapper CAUSALE_SOSPENSIONE_EVASIONE = Mappers.getMapper(CausaleSospensioneEvasioneMapper.class);

	public static final StoricoInterventoRupMapper INTERVENTO_STORICO_RUP = Mappers.getMapper(StoricoInterventoRupMapper.class);

	public static final MetadatiFunzioneMapper METADATI_FUNZIONE = Mappers.getMapper(MetadatiFunzioneMapper.class);

	public static final TipoAcquistoMapper TIPO_ACQUISTO  = Mappers.getMapper(TipoAcquistoMapper.class);

	public static final ProvvedimentoTipoMapper PROVVEDIMENTO_TIPO = Mappers.getMapper(ProvvedimentoTipoMapper.class);

	public static final ProvvedimentoMapper PROVVEDIMENTO = Mappers.getMapper(ProvvedimentoMapper.class);

	public static final SettoreStoricoMapper SETTORE_STORICO = Mappers.getMapper(SettoreStoricoMapper.class);

	public static final FlussoImpegniEsterniMapper FlussoImpegniEsterni = Mappers.getMapper(FlussoImpegniEsterniMapper.class);

	public static final FlussoSubImpegniEsterniMapper FlussoSubImpegniEsterni = Mappers.getMapper(FlussoSubImpegniEsterniMapper.class);

	public static final FlussoAnomalieMapper FlussoAnomalie = Mappers.getMapper(FlussoAnomalieMapper.class);

	public static final TipoSettoreMapper TIPO_SETTORE = Mappers.getMapper(TipoSettoreMapper.class);

	public static final NotificaMapper NOTIFICA = Mappers.getMapper(NotificaMapper.class);

	public static final FruitoreMapper FRUITORE = Mappers.getMapper(FruitoreMapper.class);

	public static final ServizioMapper SERVIZIO = Mappers.getMapper(ServizioMapper.class);

	public static final DocumentoTrasportoMapper DOCUMENTO_TRASPORTO = Mappers.getMapper(DocumentoTrasportoMapper.class);

	public static final DocumentoTrasportoRigaMapper DOCUMENTO_TRASPORTO_RIGA = Mappers.getMapper(DocumentoTrasportoRigaMapper.class);

	public static final DocumentoTrasportoXmlMapper DOCUMENTO_TRASPORTO_XML = Mappers.getMapper(DocumentoTrasportoXmlMapper.class);

	public static final DestinatarioInvioNsoMapper DESTINATARIO_INVIO_NSO = Mappers.getMapper(DestinatarioInvioNsoMapper.class);

	public static final DestinatarioInvioNsoXmlMapper DESTINATARIO_INVIO_NSO_XML = Mappers.getMapper(DestinatarioInvioNsoXmlMapper.class);

	public static final TestoNotificaMapper TESTO_NOTIFICA = Mappers.getMapper(TestoNotificaMapper.class);

	public static final ScaricoMepaTestataMapper SCARICO_MEPA_TESTATA = Mappers.getMapper(ScaricoMepaTestataMapper.class);

	public static final ScaricoMepaRigaMapper SCARICO_MEPA_RIGA = Mappers.getMapper(ScaricoMepaRigaMapper.class);

	public static final ScaricoMepaScontiMapper SCARICO_MEPA_SCONTI = Mappers.getMapper(ScaricoMepaScontiMapper.class);

	public static final ScaricoMepaXmlMapper SCARICO_MEPA_XML = Mappers.getMapper(ScaricoMepaXmlMapper.class);

	/** Mapper for TestataRmsMapper */
	public static final TestataRmsMapper TESTATA_RMS = Mappers.getMapper(TestataRmsMapper.class);

	/** Mapper for RigaRmsMapper */
	public static final RigaRmsMapper RIGA_RMS = Mappers.getMapper(RigaRmsMapper.class);

	/** Mapper for MagazzinoMapper */
	public static final MagazzinoMapper MAGAZZINO = Mappers.getMapper(MagazzinoMapper.class);

	/** Mapper for RigaRmsMapper */
	public static final RigaRmsReverseMapper RIGA_RMS_REVERSE = Mappers.getMapper(RigaRmsReverseMapper.class);

	public static final MotiviEsclusioneCigMapper MOTIVI_ESCLUSIONE_CIG = Mappers.getMapper(MotiviEsclusioneCigMapper.class);

	public static final ProtocolloOrdineMapper PROTOCOLLO_ORDINE = Mappers.getMapper(ProtocolloOrdineMapper.class);

	public static final RmsDaSmistareMapper RMS_DA_SMISTARE = Mappers.getMapper(RmsDaSmistareMapper.class);

	public static final RegoleSmistamentoRmsMapper Regole_Smistamento_Rms = Mappers.getMapper(RegoleSmistamentoRmsMapper.class);

	public static final TestataRdaMapper TESTATA_RDA = Mappers.getMapper(TestataRdaMapper.class);

	public static final RigaRdaMapper RIGA_RDA = Mappers.getMapper(RigaRdaMapper.class);

	public static final SezioneMapper SEZIONE = Mappers.getMapper(SezioneMapper.class);

	public static final RdaOrdineMapper RDA_ORDINE = Mappers.getMapper(RdaOrdineMapper.class);

	public static final RigaRdaReverseMapper RIGA_RDA_REVERSE = Mappers.getMapper(RigaRdaReverseMapper.class);

	public static final StatiInterventoMapper STATI_INTERVENTO = Mappers.getMapper(StatiInterventoMapper.class);

	public static final InterventoCigMapper INTERVENTO_CIG = Mappers.getMapper(InterventoCigMapper.class);

	public static final DocumentiOrdineMapper DOCUMENTO_ORDINE = Mappers.getMapper(DocumentiOrdineMapper.class);

	public static final DocumentiOrdineMinimalMapper DOCUMENTO_ORDINE_MINIMAL = Mappers.getMapper(DocumentiOrdineMinimalMapper.class);

	public static final UfficioSerieMapper UFFICIO_SERIE = Mappers.getMapper(UfficioSerieMapper.class);

	public static final OdsDatiContabiliMapper ODS_DATI_CONTABILI = Mappers.getMapper(OdsDatiContabiliMapper.class);

	public static final GestioneCampoMapper GESTIONE_CAMPO = Mappers.getMapper(GestioneCampoMapper.class);

	public static final AooActaMapper AOO_ACTA = Mappers.getMapper(AooActaMapper.class);

	public static final SettoreAooActaMapper SETTORE_AOO_ACTA = Mappers.getMapper(SettoreAooActaMapper.class);

	public static final UtenteSettoreMapper UTENTE_SETTORE = Mappers.getMapper(UtenteSettoreMapper.class);

	public static final RuoloUtenteSettoreMapper RUOLO_UTENTE_SETTORE = Mappers.getMapper(RuoloUtenteSettoreMapper.class);

	public static final UtenteSezioneMapper ORD_UTENTE_SEZIONE = Mappers.getMapper(UtenteSezioneMapper.class);

	public static final CdcMapper CDC = Mappers.getMapper(CdcMapper.class);

	public static final AcquistiDaTrasmettereMapper Acquisti_Da_Trasmettere = Mappers.getMapper(AcquistiDaTrasmettereMapper.class);

	public static final AcquistiCapPrivatiDaTrasmettereMapper Acquisti_Cap_Privati_Da_Trasmettere = Mappers.getMapper(AcquistiCapPrivatiDaTrasmettereMapper.class);

	public static final StatiRigaRmsMapper STATI_RIGA_RMS = Mappers.getMapper(StatiRigaRmsMapper.class);

	public static final RmsRigaRdaMapper RMS_RIGA_RDA = Mappers.getMapper(RmsRigaRdaMapper.class);
	public static final RuoloPermessoMapper RUOLO_PERMESSO = Mappers.getMapper(RuoloPermessoMapper.class);

	public static final ModuloRuoloPermessoMapper MODULO_RUOLO_PERMESSO = Mappers.getMapper(ModuloRuoloPermessoMapper.class);





	/** Private constructor */
	private CpassMappers() {
		// Prevent instantiation
	}
}
