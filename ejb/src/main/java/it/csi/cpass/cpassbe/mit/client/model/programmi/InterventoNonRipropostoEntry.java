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
 * Contenitore per i dati di un intervento non riproposto
 */
@ApiModel(description = "Contenitore per i dati di un intervento non riproposto")
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2020-03-26T12:24:42.165+01:00")
public class InterventoNonRipropostoEntry   {
  @SerializedName("cui")
  private String cui = null;

  @SerializedName("cup")
  private String cup = null;

  @SerializedName("descrizione")
  private String descrizione = null;

  @SerializedName("importo")
  private Double importo = null;

  @SerializedName("priorita")
  private Long priorita = null;

  @SerializedName("motivo")
  private String motivo = null;

  public InterventoNonRipropostoEntry cui(String cui) {
    this.cui = cui;
    return this;
  }

   /**
   * Codice CUI
   * @return cui
  **/
  @ApiModelProperty(example = "null", required = true, value = "Codice CUI")
  public String getCui() {
    return cui;
  }

  public void setCui(String cui) {
    this.cui = cui;
  }

  public InterventoNonRipropostoEntry cup(String cup) {
    this.cup = cup;
    return this;
  }

   /**
   * Codice CUP di progetto (assegnato da CIPE)
   * @return cup
  **/
  @ApiModelProperty(example = "null", value = "Codice CUP di progetto (assegnato da CIPE)")
  public String getCup() {
    return cup;
  }

  public void setCup(String cup) {
    this.cup = cup;
  }

  public InterventoNonRipropostoEntry descrizione(String descrizione) {
    this.descrizione = descrizione;
    return this;
  }

   /**
   * Descrizione dell'intervento
   * @return descrizione
  **/
  @ApiModelProperty(example = "null", required = true, value = "Descrizione dell'intervento")
  public String getDescrizione() {
    return descrizione;
  }

  public void setDescrizione(String descrizione) {
    this.descrizione = descrizione;
  }

  public InterventoNonRipropostoEntry importo(Double importo) {
    this.importo = importo;
    return this;
  }

   /**
   * Importo complessivo
   * @return importo
  **/
  @ApiModelProperty(example = "null", required = true, value = "Importo complessivo")
  public Double getImporto() {
    return importo;
  }

  public void setImporto(Double importo) {
    this.importo = importo;
  }

  public InterventoNonRipropostoEntry priorita(Long priorita) {
    this.priorita = priorita;
    return this;
  }

   /**
   * Livello di Priorit� (/WSTabelleDiContesto/rest/Tabellati/Valori?cod=Priorita)
   * @return priorita
  **/
  @ApiModelProperty(example = "null", required = true, value = "Livello di Priorit� (/WSTabelleDiContesto/rest/Tabellati/Valori?cod=Priorita)")
  public Long getPriorita() {
    return priorita;
  }

  public void setPriorita(Long priorita) {
    this.priorita = priorita;
  }

  public InterventoNonRipropostoEntry motivo(String motivo) {
    this.motivo = motivo;
    return this;
  }

   /**
   * Motivo per il quale l'intervento non � riproposto
   * @return motivo
  **/
  @ApiModelProperty(example = "null", required = true, value = "Motivo per il quale l'intervento non � riproposto")
  public String getMotivo() {
    return motivo;
  }

  public void setMotivo(String motivo) {
    this.motivo = motivo;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    InterventoNonRipropostoEntry interventoNonRipropostoEntry = (InterventoNonRipropostoEntry) o;
    return Objects.equals(this.cui, interventoNonRipropostoEntry.cui) &&
        Objects.equals(this.cup, interventoNonRipropostoEntry.cup) &&
        Objects.equals(this.descrizione, interventoNonRipropostoEntry.descrizione) &&
        Objects.equals(this.importo, interventoNonRipropostoEntry.importo) &&
        Objects.equals(this.priorita, interventoNonRipropostoEntry.priorita) &&
        Objects.equals(this.motivo, interventoNonRipropostoEntry.motivo);
  }

  @Override
  public int hashCode() {
    return Objects.hash(cui, cup, descrizione, importo, priorita, motivo);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class InterventoNonRipropostoEntry {\n");
    
    sb.append("    cui: ").append(toIndentedString(cui)).append("\n");
    sb.append("    cup: ").append(toIndentedString(cup)).append("\n");
    sb.append("    descrizione: ").append(toIndentedString(descrizione)).append("\n");
    sb.append("    importo: ").append(toIndentedString(importo)).append("\n");
    sb.append("    priorita: ").append(toIndentedString(priorita)).append("\n");
    sb.append("    motivo: ").append(toIndentedString(motivo)).append("\n");
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

