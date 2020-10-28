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
package it.csi.cpass.cpassbe.ejb.mapper;

import org.mapstruct.factory.Mappers;

import it.csi.cpass.cpassbe.ejb.mapper.ord.DestinatarioOrdineMapper;
import it.csi.cpass.cpassbe.ejb.mapper.ord.ImpegnoAssociatoMapper;
import it.csi.cpass.cpassbe.ejb.mapper.ord.ImpegnoOrdineMapper;
import it.csi.cpass.cpassbe.ejb.mapper.ord.OggettiSpesaMapper;
import it.csi.cpass.cpassbe.ejb.mapper.ord.RigaOrdineMapper;
import it.csi.cpass.cpassbe.ejb.mapper.ord.StatoElOrdineMapper;
import it.csi.cpass.cpassbe.ejb.mapper.ord.StatoNsoMapper;
import it.csi.cpass.cpassbe.ejb.mapper.ord.SubimpegnoAssociatoMapper;
import it.csi.cpass.cpassbe.ejb.mapper.ord.SubimpegnoOrdineMapper;
import it.csi.cpass.cpassbe.ejb.mapper.ord.TestataOrdineMapper;
import it.csi.cpass.cpassbe.ejb.mapper.ord.TipoOrdineMapper;
import it.csi.cpass.cpassbe.ejb.mapper.ord.TipoProceduraMapper;
import it.csi.cpass.cpassbe.ejb.mapper.ord.VOrdineMapper;
import it.csi.cpass.cpassbe.ejb.mapper.ord.evasione.CausaleSospensioneEvasioneMapper;
import it.csi.cpass.cpassbe.ejb.mapper.ord.evasione.DestinatarioEvasioneMapper;
import it.csi.cpass.cpassbe.ejb.mapper.ord.evasione.ImpegnoEvasioneMapper;
import it.csi.cpass.cpassbe.ejb.mapper.ord.evasione.RigaEvasioneMapper;
import it.csi.cpass.cpassbe.ejb.mapper.ord.evasione.SubimpegnoEvasioneMapper;
import it.csi.cpass.cpassbe.ejb.mapper.ord.evasione.TestataEvasioneMapper;
import it.csi.cpass.cpassbe.ejb.mapper.ord.evasione.TipoEvasioneMapper;
import it.csi.cpass.cpassbe.ejb.mapper.ord.evasione.RiepilogoFatturaMapper;


import it.csi.cpass.cpassbe.ejb.mapper.pba.AcquistoVariatoMapper;
import it.csi.cpass.cpassbe.ejb.mapper.pba.AusaMapper;
import it.csi.cpass.cpassbe.ejb.mapper.pba.InterventoAltriDatiMapper;
import it.csi.cpass.cpassbe.ejb.mapper.pba.InterventoImportiMapper;
import it.csi.cpass.cpassbe.ejb.mapper.pba.InterventoMapper;
import it.csi.cpass.cpassbe.ejb.mapper.pba.StoricoInterventoRupMapper;
import it.csi.cpass.cpassbe.ejb.mapper.pba.TipoAcquistoMapper;
import it.csi.cpass.cpassbe.ejb.mapper.pba.ModalitaAffidamentoMapper;
import it.csi.cpass.cpassbe.ejb.mapper.pba.NutsMapper;
import it.csi.cpass.cpassbe.ejb.mapper.pba.PrioritaMapper;
import it.csi.cpass.cpassbe.ejb.mapper.pba.ProgrammaMapper;
import it.csi.cpass.cpassbe.ejb.mapper.pba.RicompresoTipoMapper;
import it.csi.cpass.cpassbe.ejb.mapper.pba.RisorsaMapper;
import it.csi.cpass.cpassbe.ejb.mapper.pba.SettoreInterventiMapper;



/**
 * Mappers for Cpass
 */
public final class CpassMappers {
	/** Mapper for Cpv */
	public static final CpvMapper CPV = Mappers.getMapper(CpvMapper.class);
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
	/** Mapper for Ente */
	public static final EnteMapper ENTE = Mappers.getMapper(EnteMapper.class);
	/** Mapper for Programma */
	public static final ProgrammaMapper PROGRAMMA = Mappers.getMapper(ProgrammaMapper.class);
	/** Mapper for InterventoImporti */
	public static final InterventoImportiMapper INTERVENTO_IMPORTI = Mappers.getMapper(InterventoImportiMapper.class);
	/** Mapper for InterventoAltriDati */
	public static final InterventoAltriDatiMapper INTERVENTO_ALTRI_DATI = Mappers.getMapper(InterventoAltriDatiMapper.class);
	/** Mapper for Nuts */
	public static final StatoMapper STATO = Mappers.getMapper(StatoMapper.class);
	/** Mapper for Utente */
	public static final UtenteMapper UTENTE = Mappers.getMapper(UtenteMapper.class);
	/** Mapper for Ausa */
	public static final AusaMapper AUSA = Mappers.getMapper(AusaMapper.class);
	/** Mapper for RicompresoTipo */
	public static final RicompresoTipoMapper RICOMPRESO_TIPO = Mappers.getMapper(RicompresoTipoMapper.class);
	/** Mapper for AcquistoVariato */
	public static final AcquistoVariatoMapper ACQUISTO_VARIATO = Mappers.getMapper(AcquistoVariatoMapper.class);
	/** Mapper for Settore */
	public static final SettoreMapper SETTORE = Mappers.getMapper(SettoreMapper.class);
	/** Mapper for Settore Indirizzo */
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
	/** Mapper for StatoElOrdine */
	public static final StatoElOrdineMapper STATO_EL_ORDINE = Mappers.getMapper(StatoElOrdineMapper.class);

	public static final AliquoteIvaMapper ALIQUOTE_IVA = Mappers.getMapper(AliquoteIvaMapper.class);

	public static final UnitaMisuraMapper UNITA_MISURA = Mappers.getMapper(UnitaMisuraMapper.class);
	/** Mapper for OrdTipoOrdine */
	public static final TipoOrdineMapper TIPO_ORDINE = Mappers.getMapper(TipoOrdineMapper.class);
	/** Mapper for OrdTipoProcedura */
	public static final TipoProceduraMapper TIPO_PROCEDURA = Mappers.getMapper(TipoProceduraMapper.class);
	/** Mapper for Ufficio */
	public static final UfficioMapper UFFICIO = Mappers.getMapper(UfficioMapper.class);

	public static final OggettiSpesaMapper OGGETTO_SPESA = Mappers.getMapper(OggettiSpesaMapper.class);
	
	/** Mapper for TestataOrdine */
	public static final TestataOrdineMapper TESTATA_ORDINE = Mappers.getMapper(TestataOrdineMapper.class);
	/** Mapper for Fornitore */
	public static final FornitoreMapper FORNITORE = Mappers.getMapper(FornitoreMapper.class);
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
	
	
	/** Private constructor */
	private CpassMappers() {
		// Prevent instantiation
	}
}
