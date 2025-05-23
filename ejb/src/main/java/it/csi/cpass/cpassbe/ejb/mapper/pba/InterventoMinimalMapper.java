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
package it.csi.cpass.cpassbe.ejb.mapper.pba;

import java.util.Collection;
import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import it.csi.cpass.cpassbe.ejb.entity.pba.CpassTPbaIntervento;
import it.csi.cpass.cpassbe.ejb.mapper.StatoMapper;
import it.csi.cpass.cpassbe.ejb.mapper.StringMapper;
import it.csi.cpass.cpassbe.lib.dto.pba.Intervento;
import it.csi.cpass.cpassbe.lib.mapper.BaseMapperInterface;
import it.csi.cpass.cpassbe.lib.mapper.annotation.Minimal;
import it.csi.cpass.cpassbe.lib.mapper.annotation.TrimmedString;

/**
 * Mapper between Intervento and CpassTPbaIntervento
 */
@Mapper(uses = {
		//CpvMapper.class, ModalitaAffidamentoMapper.class, NutsMapper.class, PrioritaMapper.class,
		//SettoreInterventiMapper.class, ProgrammaMapper.class, InterventoImportiMinimalMapper.class, UtenteMapper.class,
		StatoMapper.class,StringMapper.class
		//RicompresoTipoMapper.class, AusaMapper.class, AcquistoVariatoMapper.class,InterventoAltriDatiMapper.class, SettoreCustomMapper.class,
		//TipoProceduraPbaMapper.class,StatiInterventoMapper.class
})
public interface InterventoMinimalMapper extends BaseMapperInterface<Intervento, CpassTPbaIntervento> {

	@Override
	@Mapping(source = "interventoAnnoAvvio", target = "annoAvvio")
	@Mapping(source = "interventoCui", target = "cui")
	@Mapping(source = "interventoDurataMesi", target = "durataMesi")
	@Mapping(source = "interventoLottoFunzionale", target = "lottoFunzionale")
	@Mapping(source = "interventoNuovoAffid", target = "nuovoAffidamento")
	@Mapping(source = "interventoCup", target = "cup", qualifiedBy = TrimmedString.class)
	@Mapping(source = "interventoDescrizioneAcquisto", target = "descrizioneAcquisto", qualifiedBy = TrimmedString.class)
	@Mapping(source = "cpassDCpv", target = "cpv")
	@Mapping(source = "cpassDPbaModAffidamento", target = "modalitaAffidamento")
	@Mapping(source = "cpassDPbaNut", target = "nuts")
	@Mapping(source = "cpassDPbaPriorita", target = "priorita")
	@Mapping(source = "cpassDPbaSettoreInterventi", target = "settoreInterventi")
	@Mapping(source = "cpassDStato", target = "stato")
	@Mapping(source = "cpassDPbaRicompresoTipo", target = "ricompresoTipo")
	@Mapping(source = "cpassDPbaAusa", target = "ausa")
	@Mapping(source = "cpassDPbaAcquistoVariato", target = "acquistoVariato")
	@Mapping(source = "cpassTPbaProgramma", target = "programma")
	@Mapping(source = "interventoRicompresoCui", target = "ricompresoCui")
	@Mapping(source = "cpassTUtenteRup", target = "utenteRup")
	@Mapping(source = "interventoCopiaTipo",  target = "interventoCopiaTipo")
	@Mapping(source = "interventoImportiCopiaTipo",  target = "interventoImportiCopiaTipo")
	@Mapping(source = "flagCuiNonGenerato",       target = "flagCuiNonGenerato")
	@Mapping(source = "motivazioneNonRiproposto", target = "motivazioneNonRiproposto")
	@Mapping(source = "cpassTPbaInterventoCopia", target = "interventoCopia" )
	@Mapping(source = "cpassTSettore", target = "settore")
	@Mapping(source = "cpassTPbaInterventoAltriDatis", target = "listInterventoAltriDati")
	@Mapping(source = "cpassTPbaInterventoCapofila.id", target = "interventoCapofila.id")
	@Mapping(source = "cpassTUtenteVisto", target = "utenteVisto")
	@Mapping(source = "cpassTUtenteVistoRagioneria", target = "utenteVistoRagioneria")
	@Mapping(source = "cpassTUtenteValidazione", target = "utenteValidazione")
	@Mapping(source = "cpassTUtenteRifiuto",      target = "utenteRifiuto")
	@Mapping(source = "cpassTUtenteAvviato",      target = "utenteAvviato")
	@Mapping(source = "cpassDPbaTipoProcedura",   target = "tipoProceduraPba")
	//@Mapping(source = "cpassTPbaInterventoImportis", target = "listInterventoImporti")
	//@Mapping(source = "cpassTPbaInterventoCigs",  target = "interventoCigs")
	//@Mapping(source = "cpassRPbaStatiInterventos", target = "statiInterventos")
	//@Mapping(source = "cpassRInterventoCpvs.cpassDCpv", target = "listCpv")
	//@Mapping(source = "cpasslistCpv", target = "listCpv")
	//@Mapping(source = "esenteIva", target = "esenteIva")


	Intervento toModel(CpassTPbaIntervento entity);

	@Override
	@IterableMapping(elementTargetType = Intervento.class)
	List<Intervento> toModels(Collection<CpassTPbaIntervento> entities);

	@Override
	@InheritInverseConfiguration(name="toModel")
	@Mapping(target = "cpassTPbaInterventoImportis", ignore = true ,qualifiedBy = Minimal.class )
	@Mapping(target = "cpassTPbaInterventoAltriDatis", ignore = true)
	CpassTPbaIntervento toEntity(Intervento model);

	@Override
	@IterableMapping(elementTargetType = CpassTPbaIntervento.class)
	List<CpassTPbaIntervento> toEntities(Collection<Intervento> models);

	CpassTPbaIntervento cloneToEntity(CpassTPbaIntervento entity);

	Intervento cloneToModel(Intervento model);
	/*
	@InheritInverseConfiguration(name="toModel")
	@Named ("Complete")
	CpassTPbaIntervento toEntityComplete(Intervento model);
	 */

}
