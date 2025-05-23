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

import java.util.Collection;
import java.util.List;

import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;

import it.csi.cpass.cpassbe.ejb.entity.view.CpassVCpv;
import it.csi.cpass.cpassbe.lib.dto.Cpv;
import it.csi.cpass.cpassbe.lib.dto.pba.SettoreInterventi;
import it.csi.cpass.cpassbe.lib.mapper.BaseMapperInterface;

/**
 * Mapper between settore and CpassVCpv
 */
@Mapper
public abstract class TreeCpvMapper implements BaseMapperInterface<Cpv, CpassVCpv> {


	@Override
	public Cpv toModel(CpassVCpv entity) {
		if (entity == null) {
			return null;
		}
		final Cpv cpv = new Cpv();
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

		final SettoreInterventi setInt = new SettoreInterventi();
		setInt.setId(entity.getSettoreInterventiId());
		setInt.setCodice(entity.getSettoreInterventiCodice());
		setInt.setDescrizione(entity.getSettoreInterventiDescrizione());
		cpv.setSettoreInterventi(setInt);
		return cpv;
	}

	@Override
	@IterableMapping(elementTargetType = Cpv.class)
	public abstract List<Cpv> toModels(Collection<CpassVCpv> entities);

	@Override
	@IterableMapping(elementTargetType = CpassVCpv.class)
	public abstract List<CpassVCpv> toEntities(Collection<Cpv> models);


	@Override
	public CpassVCpv toEntity(Cpv model) {
		if (model == null) {
			return null;
		}
		final CpassVCpv cpassVCpv = new CpassVCpv();
		cpassVCpv.setId(model.getId() != null ? model.getId().longValue() : null);
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
