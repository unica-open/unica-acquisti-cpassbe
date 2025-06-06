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
package it.csi.cpass.cpassbe.ejb.business.be.service.impl.base;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.BooleanSupplier;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

import it.csi.cpass.cpassbe.ejb.business.be.service.request.base.BaseRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.base.BaseResponse;
import it.csi.cpass.cpassbe.ejb.exception.BusinessException;
import it.csi.cpass.cpassbe.ejb.exception.ParamValidationException;
import it.csi.cpass.cpassbe.ejb.util.StringUtility;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;
import it.csi.cpass.cpassbe.ejb.util.exception.BusinessExceptionAware;
import it.csi.cpass.cpassbe.lib.dto.ApiError;
import it.csi.cpass.cpassbe.lib.dto.BaseAuditedDto;
import it.csi.cpass.cpassbe.lib.dto.BaseDto;
import it.csi.cpass.cpassbe.lib.dto.Ruolo;
import it.csi.cpass.cpassbe.lib.dto.error.CoreError;
import it.csi.cpass.cpassbe.lib.dto.error.MsgTypeEnum;
import it.csi.cpass.cpassbe.lib.dto.ord.Destinatario;
import it.csi.cpass.cpassbe.lib.external.itf.ExternalServiceResolveWrapper;
import it.csi.cpass.cpassbe.lib.external.res.ExternalServiceResponseWrapper;
import it.csi.cpass.cpassbe.lib.util.function.Thunk;
import it.csi.cpass.cpassbe.lib.util.log.LogUtil;
import it.csi.cpass.cpassbe.lib.util.reflection.GenericTypeResolver;

/**
 * Base service implementor
 * @param <Q> the request type
 * @param <R> the response type
 */
public abstract class BaseService<Q extends BaseRequest, R extends BaseResponse> implements BusinessExceptionAware {

	/** The logger */
	protected final LogUtil log = new LogUtil(getClass());
	/** The configuration helper */
	protected final ConfigurationHelper configurationHelper;

	/** The request */
	protected Q request;
	/** The response */
	protected R response;

	/**
	 * Constructor
	 * @param configurationHelper the configuration helper
	 */
	protected BaseService(ConfigurationHelper configurationHelper) {
		this.configurationHelper = configurationHelper;
	}

	/**
	 * Initialize the service
	 * @return the response
	 */
	public R initialize() {
		if(response == null) {
			response = initializeResponse();
		}
		return response;
	}

	/**
	 * Executes the service
	 * @param req the request
	 * @return the response
	 */
	public R execute(Q req) {
		this.request = req;
		this.initialize();
		baseCheckServiceParams();
		if(!response.getApiErrors().isEmpty()) {
			paramExceptionPreReturn();
			return response;
		}

		init();
		try {
			execute();
		}catch(final RuntimeException re) {
			runtimeExceptionPreReturn(re);
			throw re;
		}
		/*if(!response.getApiErrors().isEmpty()) {
			serviceExceptionPreReturn();

			throw new BusinessException("BAD REQUEST",response.getApiErrors());

		}*/

		return response;
	}

	/**
	 * Response initialization
	 * @return the response
	 */
	private R initializeResponse() {
		@SuppressWarnings("unchecked")
		final
		Class<R> responseClass = (Class<R>) GenericTypeResolver.resolveActualTypeArgs(getClass(), BaseService.class)[1];
		try {
			// Refactored for Java 11
			final Constructor<R> constructor = responseClass.getDeclaredConstructor();
			return constructor.newInstance();
		} catch (RuntimeException | InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
			throw new IllegalStateException("Cannot initialize response object", e);
		}
	}

	/**
	 * Basic service parameters check
	 * @in case of errors in parameter check
	 */
	private void baseCheckServiceParams() {
		try {
			checkCondition(request != null, CoreError.REQUIRED_PARAMETER_OMITTED.getError("parameter", "request"), Boolean.TRUE);
			checkServiceParams();
		} catch (final ParamValidationException pve) {
			log.trace("baseCheckServiceParams", "Errors in service validation: " + pve.getMessage());
		}
	}

	/**
	 * Checks own service parameters
	 * @in case of errors in param check
	 */
	protected void checkServiceParams() {
		// To be implemented as needed
	}
	protected void paramExceptionPreReturn() {
		// To be implemented as needed
	}

	protected void serviceExceptionPreReturn() {
		// To be implemented as needed
	}

	/**
	 * Initialization
	 */
	protected void init() {
		// To be implemented as needed
	}

	/**
	 * Execution
	 */
	protected abstract void execute();

	protected void runtimeExceptionPreReturn(RuntimeException re)  {
		// To be implemented as needed
	}
	// CHECKS
	/**
	 * Checks that the field is not null
	 * @param field the field to check
	 * @param fieldName the field name
	 * @if the field is null
	 */
	protected void checkNotNull(Object field, String fieldName) {
		checkNotNull(field, fieldName, false);
	}

	/**
	 * Checks that the field is not null
	 * @param field the field to check
	 * @param fieldName the field name
	 * @param throwException whether to throw an exception
	 * @if the field is null and was asked to throw
	 */
	protected void checkNotNull(Object field, String fieldName, boolean throwException) {
		checkCondition(field != null, CoreError.REQUIRED_PARAMETER_OMITTED.getError("parameter", fieldName), throwException);
	}

	/**
	 * Checks that the field is not null nor empty
	 * @param field the field to check
	 * @param fieldName the field name
	 * @if the field is null
	 */
	protected void checkNotEmpty(CharSequence field, String fieldName) {
		checkNotEmpty(field, fieldName, false);
	}

	/**
	 * Checks that the field is not null nor empty
	 * @param field the field to check
	 * @param fieldName the field name
	 * @param throwException whether to throw an exception
	 * @if the field is null or empty and was asked to throw
	 */
	protected void checkNotEmpty(CharSequence field, String fieldName, boolean throwException) {
		checkCondition(StringUtils.isNotBlank(field), CoreError.REQUIRED_PARAMETER_OMITTED_EMPTY.getError("parameter", fieldName), throwException);
	}

	/**
	 * Checks that the field respects the length constraint
	 * @param field the field to check
	 * @param fieldName the field name
	 * @param minLength the minimum length (set 0 to skip)
	 * @param maxLength the maximum length (set 0 to skip)
	 * @if the field does not comply with the constraints
	 */
	protected void checkCharSequenceLength(CharSequence field, String fieldName, int minLength, int maxLength) {
		checkCharSequenceLength(field, fieldName, minLength, maxLength, false);
	}

	/**
	 * Checks that the field respects the length constraint
	 * @param field the field to check
	 * @param fieldName the field name
	 * @param minLength the minimum length (set 0 to skip)
	 * @param maxLength the maximum length (set 0 to skip)
	 * @param throwException whether to throw an exception
	 * @if the field does not comply with the constraints and was asked to throw
	 */
	protected void checkCharSequenceLength(CharSequence field, String fieldName, int minLength, int maxLength, boolean throwException) {
		checkCondition(minLength == 0 || StringUtils.length(field) >= minLength,
				CoreError.STRING_PARAMETER_LENGTH_CHECK_FAILED.getError("fieldName", fieldName, "operator", ">=", "value", Integer.valueOf(minLength)),
				throwException);
		checkCondition(maxLength == 0 || StringUtils.length(field) <= maxLength,
				CoreError.STRING_PARAMETER_LENGTH_CHECK_FAILED.getError("fieldName", fieldName, "operator", "<=", "value", Integer.valueOf(maxLength)),
				throwException);
	}

	/**
	 * Checks that a char sequence
	 * @param field the field to check
	 * @param fieldName the field name
	 * @param notNull whether the field should be not null
	 * @param notEmpty whether the field should be not empty
	 * @param minLength the minimum length (set 0 to skip)
	 * @param maxLength the maximum length (set 0 to skip)
	 * @if the field does not comply with the constraints
	 */
	protected void checkCharSequence(CharSequence field, String fieldName, boolean notNull, boolean notEmpty, int minLength, int maxLength) {
		checkCharSequence(field, fieldName, notNull, notEmpty, minLength, maxLength, false);
	}

	/**
	 * Checks that a char sequence
	 * @param field the field to check
	 * @param fieldName the field name
	 * @param notNull whether the field should be not null
	 * @param notEmpty whether the field should be not empty
	 * @param minLength the minimum length (set 0 to skip)
	 * @param maxLength the maximum length (set 0 to skip)
	 * @param throwException whether to throw an exception
	 * @if the field does not comply with the constraints and was asked to throw
	 */
	protected void checkCharSequence(CharSequence field, String fieldName, boolean notNull, boolean notEmpty, int minLength, int maxLength, boolean throwException) {
		if(!notNull && field == null) {
			return;
		}
		if(notEmpty) {
			checkNotEmpty(field, fieldName, throwException);
		}
		checkCharSequenceLength(field, fieldName, minLength, maxLength, throwException);
	}

	/**
	 * Checks whether the model given is correctly populated with its id
	 * @param model the model to check
	 * @param fieldName the name of the field
	 * @if the field does not comply with the constraints and was asked to throw
	 */
	protected <K> void checkModel(BaseDto<K> model, String fieldName) {
		checkModel(model, fieldName, false);
	}

	/**
	 * Checks whether the model given is correctly populated with its id
	 * @param model the model to check
	 * @param fieldName the name of the field
	 * @param throwException whether to throw an exception
	 * @if the field does not comply with the constraints and was asked to throw
	 */
	protected <K> void checkModel(BaseDto<K> model, String fieldName, boolean throwException) {
		checkNotNull(model, fieldName, throwException);
		checkCondition(model == null || model.getId() != null, CoreError.REQUIRED_PARAMETER_OMITTED.getError("parameter", fieldName + ".id"), throwException);
	}

	/**
	 * Checks a condition
	 * @param condition the condition
	 * @param message the message
	 * @if the condition is not satisfied
	 */
	protected void checkCondition(boolean condition, ApiError message) {
		checkCondition(condition, message, false);
	}

	/**
	 * Checks a condition
	 * @param condition the condition
	 * @param message the message
	 * @param throwException whether to throw an exception
	 * @if the condition is not satisfied and was asked to throw
	 */
	protected void checkCondition(boolean condition, ApiError message, boolean throwException) {
		if(condition) {
			return;
		}
		response.addApiError(message);
		if(throwException) {
			throw new ParamValidationException(message.getFullErrorMessage());
		}
	}

	@Override
	public void checkBusinessCondition(boolean condition, ApiError message, boolean throwException) {
		if(!condition) {
			response.addApiError(message);
			if(throwException) {
				throw new BusinessException(message);
			}
		}
	}

	public void gotoException() {
		if(response.getApiErrors().size()>0) {
			throw new BusinessException("ERROR",response.getApiErrors());
		}
	}

	@Override
	public void checkBusinessCondition(boolean condition, ApiError message) {
		checkBusinessCondition(condition, message, Boolean.TRUE);
	}

	@Override
	public void generaException(ApiError message) {
		checkBusinessCondition(Boolean.FALSE, message, Boolean.TRUE);
	}

	/**
	 *
	 * @param condition
	 * @param message
	 */
	public void warnBusinessCondition(boolean condition, ApiError message) {
		if(!condition) {
			response.addApiWarnings(message);
		}
	}

	@Override
	public void checkBusinessCondition(BooleanSupplier supplier, ApiError errorMessage) {
		this.checkBusinessCondition(supplier.getAsBoolean(), errorMessage);
	}

	@Override
	public void checkBusinessCondition(boolean condition, Supplier<ApiError> message) {
		if(!condition) {
			final ApiError em = message.get();
			response.addApiError(em);
			throw new BusinessException(em);
		}
	}

	@Override
	public void checkBusinessCondition(BooleanSupplier supplier, Supplier<ApiError> errorMessage) {
		this.checkBusinessCondition(supplier.getAsBoolean(), errorMessage);
	}

	/**
	 * Checks the validity of the Optlock
	 * @param currentOptlock the current optlock
	 * @param oldOptlock the old optlock
	 */
	protected void checkOptlock(UUID currentOptlock, UUID oldOptlock) {
		checkOptlock (currentOptlock, oldOptlock, CoreError.INVALID_OPTLOOK.getError("optlock", currentOptlock));
	}
	/**
	 * Checks the validity of the Optlock
	 * @param currentOptlock the current optlock
	 * @param oldOptlock the old optlock
	 */
	protected void checkOptlock(UUID currentOptlock, UUID oldOptlock,  ApiError message) {
		checkBusinessCondition(oldOptlock.equals(currentOptlock), message);
	}

	/**
	 * Checks whether the entity is present
	 * @param <E> the return type
	 * @param supplier the entity supplier
	 * @param modelName the model name
	 * @return the loaded instance
	 */
	public <E> E isEntityPresent(Supplier<Optional<E>> supplier, String modelName) {
		final Optional<E> instance = supplier.get();
		checkBusinessCondition(instance.isPresent(), CoreError.UNMAPPED_ENTITY.getError("entity", modelName));
		return instance.orElse(null);
	}

	/**
	 * Checks whether the entity is present
	 * @param <E> the return type
	 * @param supplier the entity supplier
	 * @param modelName the model name
	 * @return the loaded instance
	 */
	public <E> E isNotEntityPresent(Supplier<Optional<E>> supplier, String modelName) {
		final Optional<E> instance = supplier.get();
		checkBusinessCondition(!instance.isPresent(), CoreError.MAPPED_ENTITY.getError("entity", modelName));
		return instance.orElse(null);
	}

	/**
	 * Invokes a service
	 * @param <A> the request type
	 * @param <B> the response type
	 * @param <S> the service type
	 * @param req the request
	 * @param service the service
	 * @return the response
	 */
	protected <A extends BaseRequest, B extends BaseResponse, S extends BaseService<A, B>> B invokeService(A req, S service) {
		B res = null;
		try {
			res = service.initialize();
			return service.execute(req);
		} catch(BusinessException | ParamValidationException e) {
			if(res != null) {
				response.addApiErrors(res.getApiErrors());
			}
			throw new BusinessException("Exception in inner method invocation", e);
		} catch(final Exception e) {
			response.addApiError(CoreError.SYSTEM_ERROR.getError("error", e.getMessage()));
			throw new BusinessException("Exception in inner method invocation", e);
		}
	}
	/**
	 * Returns the DTO if present
	 * @param <T> the type
	 * @param dto the DTO
	 * @return the DTO or null
	 */
	protected <K, T extends BaseDto<K>> T returnDtoIfPopulated(T dto) {
		return returnDtoIfPopulated(dto, null);
	}
	/**
	 * Returns the DTO if present
	 * @param <T> the type
	 * @param dto the DTO
	 * @param defaultValue the default value
	 * @return the DTO or null
	 */
	protected <K, T extends BaseDto<K>> T returnDtoIfPopulated(T dto, T defaultValue) {
		return dto != null && dto.getId() != null ? dto : defaultValue;
	}

	/**
	 * @param oldField
	 * @param initializer
	 */
	protected void initField(Object oldField, Thunk initializer) {
		if(oldField == null) {
			initializer.apply();
		}
	}
	/**
	 *
	 * @param <K>
	 * @param <T>
	 * @param newDto
	 * @param oldDto
	 */
	protected <K, T extends BaseAuditedDto<K>> void setAuditData(T newDto, T oldDto) {
		newDto.setDataCreazione(oldDto.getDataCreazione());
		newDto.setDataModifica(oldDto.getDataModifica());
		newDto.setDataCancellazione(oldDto.getDataCancellazione());
		newDto.setUtenteCreazione(oldDto.getUtenteCreazione());
		newDto.setUtenteModifica(oldDto.getUtenteModifica());
		newDto.setUtenteCancellazione(oldDto.getUtenteCancellazione());
	}

	/**
	 * Invocation of external service
	 * @param <H> the handler type
	 * @param <E> the external response type
	 * @param handler the handler
	 * @param supplier the supplier for the response wrapper
	 * @return the response
	 */
	protected <H, E> E invokeExternalService(ExternalServiceResolveWrapper<H> handler, Supplier<ExternalServiceResponseWrapper<E>> supplier) {
		final ExternalServiceResponseWrapper<E> externalResponse = supplier.get();
		checkBusinessCondition(externalResponse.isSuccess(), () -> CoreError.GENERIC_ERROR.getError("error", externalResponse.getErrors().stream().collect(Collectors.joining(", "))));
		return externalResponse.getResponse();
	}

	protected void separaMessaggiErrorePerTipo(List<ApiError> apiErrors) {
		// separo i messaggi di errore dagli altri (INFO, WARNING)
		final List<ApiError> apiErrorsError = new ArrayList<>();
		final List<ApiError> apiErrorsOthers = new ArrayList<>();
		for (final ApiError apiError : apiErrors) {
			if (apiError.getType().equals(MsgTypeEnum.ERROR.getCostante())) {
				apiErrorsError.add(apiError);
			} else {
				apiErrorsOthers.add(apiError);
			}
		}
		// non può succedere di vedere a FE messaggi di tipi diversi
		if (apiErrorsOthers.size() > 0 && apiErrorsError.size() == 0) {
			response.setApiErrors(apiErrorsOthers);
		} else {
			response.setApiErrors(apiErrorsError);
		}
	}

	protected Boolean containsRuolo(List<Ruolo> ruoli,String ruoloCode) {
		Boolean ris = Boolean.FALSE;
		for(final Ruolo ruolo : ruoli) {
			if (ruolo.getCodice().equals(ruoloCode)){
				ris = Boolean.TRUE;
			}
		}
		return ris;
	}

	protected Boolean isIndirizzoEquals(Destinatario destinatario2, Destinatario dest) {
		if(    StringUtility.isEqualsString(destinatario2.getIndirizzo(),dest.getIndirizzo())
				&& StringUtility.isEqualsString(destinatario2.getCap(),dest.getCap())
				&& StringUtility.isEqualsString(destinatario2.getNumCivico(),dest.getNumCivico())
				&& StringUtility.isEqualsString(destinatario2.getLocalita(),dest.getLocalita())
				&& StringUtility.isEqualsString(destinatario2.getProvincia(),dest.getProvincia()) ) {
			return Boolean.TRUE;
		}
		return Boolean.FALSE;
	}


}
