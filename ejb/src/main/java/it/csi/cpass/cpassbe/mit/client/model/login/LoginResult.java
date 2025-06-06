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
/**
 * WSLogin
 * Servizio di Login e utilizzo JWT
 *
 * OpenAPI spec version: 1.0.0
 *
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


package it.csi.cpass.cpassbe.mit.client.model.login;

import java.util.Objects;

import com.google.gson.annotations.SerializedName;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * Contenitore per il risultato dell&#39;operazione di accesso al servizio di pubblicazione
 */
@ApiModel(description = "Contenitore per il risultato dell'operazione di accesso al servizio di pubblicazione")
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2020-03-26T12:24:38.974+01:00")
public class LoginResult   {
	@SerializedName("esito")
	private Boolean esito = null;

	@SerializedName("error")
	private String error = null;

	@SerializedName("token")
	private String token = null;

	public LoginResult esito(Boolean esito) {
		this.esito = esito;
		return this;
	}

	/**
	 * Risultato operazione di accesso al servizio di pubblicazione
	 * @return esito
	 **/
	@ApiModelProperty(example = "null", value = "Risultato operazione di accesso al servizio di pubblicazione")
	public Boolean getEsito() {
		return esito;
	}

	public void setEsito(Boolean esito) {
		this.esito = esito;
	}

	public LoginResult error(String error) {
		this.error = error;
		return this;
	}

	/**
	 * Eventuale messaggio di errore in caso di operazione fallita
	 * @return error
	 **/
	@ApiModelProperty(example = "null", value = "Eventuale messaggio di errore in caso di operazione fallita")
	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public LoginResult token(String token) {
		this.token = token;
		return this;
	}

	/**
	 * Se le credenzaili sono corrette questo token è valorizzato
	 * @return token
	 **/
	@ApiModelProperty(example = "null", value = "Se le credenzaili sono corrette questo token è valorizzato")
	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}


	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		final LoginResult loginResult = (LoginResult) o;
		return Objects.equals(this.esito, loginResult.esito) &&
				Objects.equals(this.error, loginResult.error) &&
				Objects.equals(this.token, loginResult.token);
	}

	@Override
	public int hashCode() {
		return Objects.hash(esito, error, token);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("class LoginResult {\n");

		sb.append("    esito: ").append(toIndentedString(esito)).append("\n");
		sb.append("    error: ").append(toIndentedString(error)).append("\n");
		sb.append("    token: ").append(toIndentedString(token)).append("\n");
		sb.append("}");
		return sb.toString();
	}

	/**
	 * Convert the given object to string with each line indented by 4 spaces
	 * (except the first line).
	 */
	private String toIndentedString(java.lang.Object o) {
		if (o == null) {
			return "null";
		}
		return o.toString().replace("\n", "\n    ");
	}
}

