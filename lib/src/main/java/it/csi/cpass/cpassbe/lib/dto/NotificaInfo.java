/*-
 * ========================LICENSE_START=================================
 * CPASS BackEnd - LIB submodule
 * %%
 * Copyright (C) 2019 - 2025 CSI Piemonte
 * %%
 * SPDX-FileCopyrightText: Copyright 2019 - 2020 | CSI Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 * =========================LICENSE_END==================================
 */
package it.csi.cpass.cpassbe.lib.dto;

import java.io.Serializable;
import java.math.BigDecimal;

public class NotificaInfo implements Serializable{

	private static final long serialVersionUID = 1L;

	private Integer unreadCount;
	private BigDecimal pollingTime;

	public NotificaInfo() {}

	public Integer getUnreadCount() {
		return unreadCount;
	}
	public void setUnreadCount(Integer unreadCount) {
		this.unreadCount = unreadCount;
	}
	public BigDecimal getPollingTime() {
		return pollingTime;
	}
	public void setPollingTime(BigDecimal pollingTime) {
		this.pollingTime = pollingTime;
	}

}
