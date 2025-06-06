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
package it.csi.cpass.cpassbe.lib.external.itf;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.enterprise.util.AnnotationLiteral;
import javax.inject.Qualifier;

/**
 * CDI qualifier for external service
 */
@Qualifier
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER, ElementType.TYPE})
public @interface ExternalServiceImpl {

	/**
	 * The CDI qualifier name
	 * @return the qualifier name
	 */
	String value();

	/** Inline instantiation of the {@link ExternalServiceImpl} annotation. */
	public final static class Literal extends AnnotationLiteral<ExternalServiceImpl> implements ExternalServiceImpl {
		private static final long serialVersionUID = 1L;
		private final String value;
		/**
		 * Constructor
		 * @param value the value
		 */
		public Literal(String value) {
			this.value = value;
		}
		@Override
		public String value() {
			return value;
		}
	}
}
