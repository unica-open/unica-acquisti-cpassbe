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
/**
 * WSProgrammi
 * Servizio di pubblicazione programmazione lavori, beni e servizi
 *
 * OpenAPI spec version: 2.1.0
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


package it.csi.cpass.cpassbe.mit.client.model.programmi;

import java.util.Objects;
import com.google.gson.annotations.SerializedName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * Contenitore per i dati generali del soggetto
 */
@ApiModel(description = "Contenitore per i dati generali del soggetto")
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2020-03-26T12:24:42.165+01:00")
public class DatiGeneraliTecnicoEntry   {
  @SerializedName("cognome")
  private String cognome = null;

  @SerializedName("nome")
  private String nome = null;

  @SerializedName("indirizzo")
  private String indirizzo = null;

  @SerializedName("civico")
  private String civico = null;

  @SerializedName("provincia")
  private String provincia = null;

  @SerializedName("cap")
  private String cap = null;

  @SerializedName("luogoIstat")
  private String luogoIstat = null;

  @SerializedName("cfPiva")
  private String cfPiva = null;

  public DatiGeneraliTecnicoEntry cognome(String cognome) {
    this.cognome = cognome;
    return this;
  }

   /**
   * Cognome del tecnico
   * @return cognome
  **/
  @ApiModelProperty(example = "null", required = true, value = "Cognome del tecnico")
  public String getCognome() {
    return cognome;
  }

  public void setCognome(String cognome) {
    this.cognome = cognome;
  }

  public DatiGeneraliTecnicoEntry nome(String nome) {
    this.nome = nome;
    return this;
  }

   /**
   * Nome del tecnico
   * @return nome
  **/
  @ApiModelProperty(example = "null", required = true, value = "Nome del tecnico")
  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public DatiGeneraliTecnicoEntry indirizzo(String indirizzo) {
    this.indirizzo = indirizzo;
    return this;
  }

   /**
   * Indirizzo (via/piazza/corso)
   * @return indirizzo
  **/
  @ApiModelProperty(example = "null", value = "Indirizzo (via/piazza/corso)")
  public String getIndirizzo() {
    return indirizzo;
  }

  public void setIndirizzo(String indirizzo) {
    this.indirizzo = indirizzo;
  }

  public DatiGeneraliTecnicoEntry civico(String civico) {
    this.civico = civico;
    return this;
  }

   /**
   * Numero civico
   * @return civico
  **/
  @ApiModelProperty(example = "null", value = "Numero civico")
  public String getCivico() {
    return civico;
  }

  public void setCivico(String civico) {
    this.civico = civico;
  }

  public DatiGeneraliTecnicoEntry provincia(String provincia) {
    this.provincia = provincia;
    return this;
  }

   /**
   * Provincia di residenza (/WSTabelleDiContesto/rest/Tabellati/Valori?cod=Provincia)
   * @return provincia
  **/
  @ApiModelProperty(example = "null", value = "Provincia di residenza (/WSTabelleDiContesto/rest/Tabellati/Valori?cod=Provincia)")
  public String getProvincia() {
    return provincia;
  }

  public void setProvincia(String provincia) {
    this.provincia = provincia;
  }

  public DatiGeneraliTecnicoEntry cap(String cap) {
    this.cap = cap;
    return this;
  }

   /**
   * Codice di avviamento postale
   * @return cap
  **/
  @ApiModelProperty(example = "null", value = "Codice di avviamento postale")
  public String getCap() {
    return cap;
  }

  public void setCap(String cap) {
    this.cap = cap;
  }

  public DatiGeneraliTecnicoEntry luogoIstat(String luogoIstat) {
    this.luogoIstat = luogoIstat;
    return this;
  }

   /**
   * Codice ISTAT del Comune luogo di esecuzione del contratto
   * @return luogoIstat
  **/
  @ApiModelProperty(example = "null", value = "Codice ISTAT del Comune luogo di esecuzione del contratto")
  public String getLuogoIstat() {
    return luogoIstat;
  }

  public void setLuogoIstat(String luogoIstat) {
    this.luogoIstat = luogoIstat;
  }

  public DatiGeneraliTecnicoEntry cfPiva(String cfPiva) {
    this.cfPiva = cfPiva;
    return this;
  }

   /**
   * Codice fiscale
   * @return cfPiva
  **/
  @ApiModelProperty(example = "null", required = true, value = "Codice fiscale")
  public String getCfPiva() {
    return cfPiva;
  }

  public void setCfPiva(String cfPiva) {
    this.cfPiva = cfPiva;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    DatiGeneraliTecnicoEntry datiGeneraliTecnicoEntry = (DatiGeneraliTecnicoEntry) o;
    return Objects.equals(this.cognome, datiGeneraliTecnicoEntry.cognome) &&
        Objects.equals(this.nome, datiGeneraliTecnicoEntry.nome) &&
        Objects.equals(this.indirizzo, datiGeneraliTecnicoEntry.indirizzo) &&
        Objects.equals(this.civico, datiGeneraliTecnicoEntry.civico) &&
        Objects.equals(this.provincia, datiGeneraliTecnicoEntry.provincia) &&
        Objects.equals(this.cap, datiGeneraliTecnicoEntry.cap) &&
        Objects.equals(this.luogoIstat, datiGeneraliTecnicoEntry.luogoIstat) &&
        Objects.equals(this.cfPiva, datiGeneraliTecnicoEntry.cfPiva);
  }

  @Override
  public int hashCode() {
    return Objects.hash(cognome, nome, indirizzo, civico, provincia, cap, luogoIstat, cfPiva);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class DatiGeneraliTecnicoEntry {\n");
    
    sb.append("    cognome: ").append(toIndentedString(cognome)).append("\n");
    sb.append("    nome: ").append(toIndentedString(nome)).append("\n");
    sb.append("    indirizzo: ").append(toIndentedString(indirizzo)).append("\n");
    sb.append("    civico: ").append(toIndentedString(civico)).append("\n");
    sb.append("    provincia: ").append(toIndentedString(provincia)).append("\n");
    sb.append("    cap: ").append(toIndentedString(cap)).append("\n");
    sb.append("    luogoIstat: ").append(toIndentedString(luogoIstat)).append("\n");
    sb.append("    cfPiva: ").append(toIndentedString(cfPiva)).append("\n");
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

