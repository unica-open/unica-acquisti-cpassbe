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

import it.csi.cpass.cpassbe.ejb.entity.view.CpassVSettore;
import it.csi.cpass.cpassbe.lib.dto.TipoSettore;
import it.csi.cpass.cpassbe.lib.dto.VSettore;
import it.csi.cpass.cpassbe.lib.mapper.BaseMapperInterface;

/**
 * Mapper between settore and CpassVSettore
 */
@Mapper
public abstract class TreeSettoreMapper implements BaseMapperInterface<VSettore, CpassVSettore> {


	@Override
	public VSettore toModel(CpassVSettore entity) {
		if (entity == null) {
			return null;
		}
		final VSettore settore = new VSettore();
		settore.setId(entity.getId());
		settore.setCodice(entity.getSettoreCodice());
		settore.setDescrizione(entity.getSettoreDescrizione());
		settore.setLivello(entity.getLivello());
		settore.setIdPadre(entity.getSettoreIdPadre());
		final TipoSettore tipoSettore = new TipoSettore();
		tipoSettore.setId(entity.getTipoSettoreid());
		tipoSettore.setFlagUtilizzabile(entity.getFlagUtilizzabile());
		settore.setTipoSettore(tipoSettore);
		return settore;
	}

	@Override
	@IterableMapping(elementTargetType = VSettore.class)
	public abstract List<VSettore> toModels(Collection<CpassVSettore> entities);

	@Override
	@IterableMapping(elementTargetType = CpassVSettore.class)
	public abstract List<CpassVSettore> toEntities(Collection<VSettore> models);


	@Override
	public CpassVSettore toEntity(VSettore model) {
		if (model == null) {
			return null;
		}
		final CpassVSettore cpassVSettore = new CpassVSettore();
		//		cpassVCpv.setId(model.getId());
		//		cpassVCpv.setCpvIdPadre(model.getIdPadre());
		//		cpassVCpv.setLivello(model.getLivello());
		//		cpassVCpv.setCpvCodice(model.getCodice());
		//		cpassVCpv.setCpvDescrizione(model.getDescrizione());
		//		cpassVCpv.setCpvCodicePadre(model.getCodicePadre());
		//		cpassVCpv.setCpvTipologia(model.getTipologia());
		//		cpassVCpv.setCpvDivisione(model.getDivisione());
		//		cpassVCpv.setCpvGruppo(model.getGruppo());
		//		cpassVCpv.setCpvClasse(model.getClasse());
		//		cpassVCpv.setCpvCategoria(model.getCategoria());
		//		cpassVCpv.setSettoreInterventiId(model.getSettoreInterventi().getId());
		//		cpassVCpv.setSettoreInterventiCodice(model.getSettoreInterventi().getCodice());
		//		cpassVCpv.setSettoreInterventiDescrizione(model.getSettoreInterventi().getDescrizione());
		return cpassVSettore;
	}
}
