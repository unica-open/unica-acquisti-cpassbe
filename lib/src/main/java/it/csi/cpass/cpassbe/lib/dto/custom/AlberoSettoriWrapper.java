/*-
 * ========================LICENSE_START=================================
 * CPASS BackEnd - LIB submodule
 * %%
 * Copyright (C) 2019 - 2021 CSI Piemonte
 * %%
 * SPDX-FileCopyrightText: Copyright 2019 - 2021 | CSI Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 * =========================LICENSE_END==================================
 */
package it.csi.cpass.cpassbe.lib.dto.custom;

import java.util.List;

//import it.csi.cpass.cpassbe.lib.dto.Settore;
import it.csi.cpass.cpassbe.lib.dto.VSettore;

public class AlberoSettoriWrapper {
	private List<VSettore> alberoSettori;
	private int maxLevel;

	public AlberoSettoriWrapper() {}

	public AlberoSettoriWrapper(List<VSettore> alberoSettori, int maxLevel) {
		this.alberoSettori = alberoSettori;
		this.maxLevel = maxLevel;
	}

	public List<VSettore> getAlberoSettori() {
		return alberoSettori;
	}

	public void setAlberoSettori(List<VSettore> alberoSettori) {
		this.alberoSettori = alberoSettori;
	}

	public int getMaxLevel() {
		return maxLevel;
	}

	public void setMaxLevel(int maxLevel) {
		this.maxLevel = maxLevel;
	}
}
