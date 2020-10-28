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

import java.util.Collection;
import java.util.List;

import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;

import it.csi.cpass.cpassbe.ejb.entity.view.CpassVCpvOds;
import it.csi.cpass.cpassbe.lib.dto.Cpv;
import it.csi.cpass.cpassbe.lib.dto.pba.SettoreInterventi;
import it.csi.cpass.cpassbe.lib.mapper.BaseMapperInterface;


/**
 * Mapper between settore and CpassVCpvOds
 */
@Mapper
public abstract class TreeCpvOdsMapper implements BaseMapperInterface<Cpv, CpassVCpvOds> {


	@Override
	public Cpv toModel(CpassVCpvOds entity) {
		if (entity == null) {
			return null;
		}
		Cpv cpv = new Cpv();
		cpv.setId(entity.getCpvId());
		cpv.setLivello(entity.getLivello());
		cpv.setCodice(entity.getCpvCodice());
		cpv.setDescrizione(entity.getCpvDescrizione());
		cpv.setCodicePadre(entity.getCpvCodicePadre());
		cpv.setTipologia(entity.getCpvTipologia());
		cpv.setDivisione(entity.getCpvDivisione());
		cpv.setGruppo(entity.getCpvGruppo());
		cpv.setClasse(entity.getCpvClasse());
		cpv.setCategoria(entity.getCpvCategoria());		
		cpv.setIdPadre(entity.getCpvIdPadre());
		
		SettoreInterventi setInt = new SettoreInterventi();
		setInt.setId(entity.getSettoreInterventiId());
		setInt.setCodice(entity.getSettoreInterventiCodice());
		setInt.setDescrizione(entity.getSettoreInterventiDescrizione());
		cpv.setSettoreInterventi(setInt);
		return cpv;
	}

	@Override
	@IterableMapping(elementTargetType = Cpv.class)
	public abstract List<Cpv> toModels(Collection<CpassVCpvOds> entities);

	@Override
	@IterableMapping(elementTargetType = CpassVCpvOds.class)
	public abstract List<CpassVCpvOds> toEntities(Collection<Cpv> models);

	
	@Override
	public CpassVCpvOds toEntity(Cpv model) {
		if (model == null) {
			return null;
		}
		CpassVCpvOds cpassVCpv = new CpassVCpvOds();
		cpassVCpv.setId(model.getId() != null ? Long.valueOf(model.getId().longValue()) : null);
		cpassVCpv.setCpvId(model.getId());
		cpassVCpv.setCpvIdPadre(model.getIdPadre());
		cpassVCpv.setLivello(model.getLivello());
		cpassVCpv.setCpvCodice(model.getCodice());
		cpassVCpv.setCpvDescrizione(model.getDescrizione());
		cpassVCpv.setCpvCodicePadre(model.getCodicePadre());
		cpassVCpv.setCpvTipologia(model.getTipologia());
		cpassVCpv.setCpvDivisione(model.getDivisione());
		cpassVCpv.setCpvGruppo(model.getGruppo());
		cpassVCpv.setCpvClasse(model.getClasse());
		cpassVCpv.setCpvCategoria(model.getCategoria());				
		cpassVCpv.setSettoreInterventiId(model.getSettoreInterventi().getId());
		cpassVCpv.setSettoreInterventiCodice(model.getSettoreInterventi().getCodice());
		cpassVCpv.setSettoreInterventiDescrizione(model.getSettoreInterventi().getDescrizione());		
		return cpassVCpv;
	}
}
