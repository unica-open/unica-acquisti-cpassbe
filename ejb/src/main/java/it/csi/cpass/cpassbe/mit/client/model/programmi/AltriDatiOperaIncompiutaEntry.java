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
 * Contenitore per gli altri dati di un&#39;opera incompiuta
 */
@ApiModel(description = "Contenitore per gli altri dati di un'opera incompiuta")
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2020-03-26T12:24:42.165+01:00")
public class AltriDatiOperaIncompiutaEntry   {
  @SerializedName("istat")
  private String istat = null;

  @SerializedName("nuts")
  private String nuts = null;

  @SerializedName("tipologiaIntervento")
  private String tipologiaIntervento = null;

  @SerializedName("categoriaIntervento")
  private String categoriaIntervento = null;

  @SerializedName("requisitiCapitolato")
  private String requisitiCapitolato = null;

  @SerializedName("requisitiApprovato")
  private String requisitiApprovato = null;

  @SerializedName("unitaMisura")
  private String unitaMisura = null;

  @SerializedName("dimensione")
  private Double dimensione = null;

  @SerializedName("sponsorizzazione")
  private String sponsorizzazione = null;

  @SerializedName("finanzaDiProgetto")
  private String finanzaDiProgetto = null;

  @SerializedName("costoProgetto")
  private Double costoProgetto = null;

  @SerializedName("finanziamento")
  private Double finanziamento = null;

  @SerializedName("coperturaStatale")
  private String coperturaStatale = null;

  @SerializedName("coperturaRegionale")
  private String coperturaRegionale = null;

  @SerializedName("coperturaProvinciale")
  private String coperturaProvinciale = null;

  @SerializedName("coperturaComunale")
  private String coperturaComunale = null;

  @SerializedName("coperturaAltro")
  private String coperturaAltro = null;

  @SerializedName("coperturaComunitaria")
  private String coperturaComunitaria = null;

  @SerializedName("coperturaPrivata")
  private String coperturaPrivata = null;

  public AltriDatiOperaIncompiutaEntry istat(String istat) {
    this.istat = istat;
    return this;
  }

   /**
   * Localizzazione - Codice ISTAT del Comune
   * @return istat
  **/
  @ApiModelProperty(example = "null", value = "Localizzazione - Codice ISTAT del Comune")
  public String getIstat() {
    return istat;
  }

  public void setIstat(String istat) {
    this.istat = istat;
  }

  public AltriDatiOperaIncompiutaEntry nuts(String nuts) {
    this.nuts = nuts;
    return this;
  }

   /**
   * Localizzazione - NUTS
   * @return nuts
  **/
  @ApiModelProperty(example = "null", value = "Localizzazione - NUTS")
  public String getNuts() {
    return nuts;
  }

  public void setNuts(String nuts) {
    this.nuts = nuts;
  }

  public AltriDatiOperaIncompiutaEntry tipologiaIntervento(String tipologiaIntervento) {
    this.tipologiaIntervento = tipologiaIntervento;
    return this;
  }

   /**
   * Classificazione intervento: Tipologia (/WSTabelleDiContesto/rest/Tabellati/Valori?cod=TipologiaIntervento)
   * @return tipologiaIntervento
  **/
  @ApiModelProperty(example = "null", value = "Classificazione intervento: Tipologia (/WSTabelleDiContesto/rest/Tabellati/Valori?cod=TipologiaIntervento)")
  public String getTipologiaIntervento() {
    return tipologiaIntervento;
  }

  public void setTipologiaIntervento(String tipologiaIntervento) {
    this.tipologiaIntervento = tipologiaIntervento;
  }

  public AltriDatiOperaIncompiutaEntry categoriaIntervento(String categoriaIntervento) {
    this.categoriaIntervento = categoriaIntervento;
    return this;
  }

   /**
   * Classificazione intervento: Categoria (/WSTabelleDiContesto/rest/Tabellati/Valori?cod=CategoriaIntervento)
   * @return categoriaIntervento
  **/
  @ApiModelProperty(example = "null", value = "Classificazione intervento: Categoria (/WSTabelleDiContesto/rest/Tabellati/Valori?cod=CategoriaIntervento)")
  public String getCategoriaIntervento() {
    return categoriaIntervento;
  }

  public void setCategoriaIntervento(String categoriaIntervento) {
    this.categoriaIntervento = categoriaIntervento;
  }

  public AltriDatiOperaIncompiutaEntry requisitiCapitolato(String requisitiCapitolato) {
    this.requisitiCapitolato = requisitiCapitolato;
    return this;
  }

   /**
   * Opera rispondente a tutti i requisiti di capitolato? (/WSTabelleDiContesto/rest/Tabellati/Valori?cod=SN)
   * @return requisitiCapitolato
  **/
  @ApiModelProperty(example = "null", value = "Opera rispondente a tutti i requisiti di capitolato? (/WSTabelleDiContesto/rest/Tabellati/Valori?cod=SN)")
  public String getRequisitiCapitolato() {
    return requisitiCapitolato;
  }

  public void setRequisitiCapitolato(String requisitiCapitolato) {
    this.requisitiCapitolato = requisitiCapitolato;
  }

  public AltriDatiOperaIncompiutaEntry requisitiApprovato(String requisitiApprovato) {
    this.requisitiApprovato = requisitiApprovato;
    return this;
  }

   /**
   * Opera rispondente a tutti i requisiti dell'ultimo progetto approvato? (/WSTabelleDiContesto/rest/Tabellati/Valori?cod=SN)
   * @return requisitiApprovato
  **/
  @ApiModelProperty(example = "null", value = "Opera rispondente a tutti i requisiti dell'ultimo progetto approvato? (/WSTabelleDiContesto/rest/Tabellati/Valori?cod=SN)")
  public String getRequisitiApprovato() {
    return requisitiApprovato;
  }

  public void setRequisitiApprovato(String requisitiApprovato) {
    this.requisitiApprovato = requisitiApprovato;
  }

  public AltriDatiOperaIncompiutaEntry unitaMisura(String unitaMisura) {
    this.unitaMisura = unitaMisura;
    return this;
  }

   /**
   * Dimensionamento dell'opera (unita' di misura)
   * @return unitaMisura
  **/
  @ApiModelProperty(example = "null", value = "Dimensionamento dell'opera (unita' di misura)")
  public String getUnitaMisura() {
    return unitaMisura;
  }

  public void setUnitaMisura(String unitaMisura) {
    this.unitaMisura = unitaMisura;
  }

  public AltriDatiOperaIncompiutaEntry dimensione(Double dimensione) {
    this.dimensione = dimensione;
    return this;
  }

   /**
   * Dimensionamento dell'opera (valore)
   * @return dimensione
  **/
  @ApiModelProperty(example = "null", value = "Dimensionamento dell'opera (valore)")
  public Double getDimensione() {
    return dimensione;
  }

  public void setDimensione(Double dimensione) {
    this.dimensione = dimensione;
  }

  public AltriDatiOperaIncompiutaEntry sponsorizzazione(String sponsorizzazione) {
    this.sponsorizzazione = sponsorizzazione;
    return this;
  }

   /**
   * Sponsorizzazione (/WSTabelleDiContesto/rest/Tabellati/Valori?cod=SN)
   * @return sponsorizzazione
  **/
  @ApiModelProperty(example = "null", value = "Sponsorizzazione (/WSTabelleDiContesto/rest/Tabellati/Valori?cod=SN)")
  public String getSponsorizzazione() {
    return sponsorizzazione;
  }

  public void setSponsorizzazione(String sponsorizzazione) {
    this.sponsorizzazione = sponsorizzazione;
  }

  public AltriDatiOperaIncompiutaEntry finanzaDiProgetto(String finanzaDiProgetto) {
    this.finanzaDiProgetto = finanzaDiProgetto;
    return this;
  }

   /**
   * Finanza di progetto (/WSTabelleDiContesto/rest/Tabellati/Valori?cod=SN)
   * @return finanzaDiProgetto
  **/
  @ApiModelProperty(example = "null", value = "Finanza di progetto (/WSTabelleDiContesto/rest/Tabellati/Valori?cod=SN)")
  public String getFinanzaDiProgetto() {
    return finanzaDiProgetto;
  }

  public void setFinanzaDiProgetto(String finanzaDiProgetto) {
    this.finanzaDiProgetto = finanzaDiProgetto;
  }

  public AltriDatiOperaIncompiutaEntry costoProgetto(Double costoProgetto) {
    this.costoProgetto = costoProgetto;
    return this;
  }

   /**
   * Costo progetto
   * @return costoProgetto
  **/
  @ApiModelProperty(example = "null", value = "Costo progetto")
  public Double getCostoProgetto() {
    return costoProgetto;
  }

  public void setCostoProgetto(Double costoProgetto) {
    this.costoProgetto = costoProgetto;
  }

  public AltriDatiOperaIncompiutaEntry finanziamento(Double finanziamento) {
    this.finanziamento = finanziamento;
    return this;
  }

   /**
   * Finanziamento assegnato
   * @return finanziamento
  **/
  @ApiModelProperty(example = "null", value = "Finanziamento assegnato")
  public Double getFinanziamento() {
    return finanziamento;
  }

  public void setFinanziamento(Double finanziamento) {
    this.finanziamento = finanziamento;
  }

  public AltriDatiOperaIncompiutaEntry coperturaStatale(String coperturaStatale) {
    this.coperturaStatale = coperturaStatale;
    return this;
  }

   /**
   * Tipologia copertura finanziaria Statale (/WSTabelleDiContesto/rest/Tabellati/Valori?cod=SN)
   * @return coperturaStatale
  **/
  @ApiModelProperty(example = "null", value = "Tipologia copertura finanziaria Statale (/WSTabelleDiContesto/rest/Tabellati/Valori?cod=SN)")
  public String getCoperturaStatale() {
    return coperturaStatale;
  }

  public void setCoperturaStatale(String coperturaStatale) {
    this.coperturaStatale = coperturaStatale;
  }

  public AltriDatiOperaIncompiutaEntry coperturaRegionale(String coperturaRegionale) {
    this.coperturaRegionale = coperturaRegionale;
    return this;
  }

   /**
   * Tipologia copertura finanziaria Regionale (/WSTabelleDiContesto/rest/Tabellati/Valori?cod=SN)
   * @return coperturaRegionale
  **/
  @ApiModelProperty(example = "null", value = "Tipologia copertura finanziaria Regionale (/WSTabelleDiContesto/rest/Tabellati/Valori?cod=SN)")
  public String getCoperturaRegionale() {
    return coperturaRegionale;
  }

  public void setCoperturaRegionale(String coperturaRegionale) {
    this.coperturaRegionale = coperturaRegionale;
  }

  public AltriDatiOperaIncompiutaEntry coperturaProvinciale(String coperturaProvinciale) {
    this.coperturaProvinciale = coperturaProvinciale;
    return this;
  }

   /**
   * Tipologia copertura finanziaria Provinciale (/WSTabelleDiContesto/rest/Tabellati/Valori?cod=SN)
   * @return coperturaProvinciale
  **/
  @ApiModelProperty(example = "null", value = "Tipologia copertura finanziaria Provinciale (/WSTabelleDiContesto/rest/Tabellati/Valori?cod=SN)")
  public String getCoperturaProvinciale() {
    return coperturaProvinciale;
  }

  public void setCoperturaProvinciale(String coperturaProvinciale) {
    this.coperturaProvinciale = coperturaProvinciale;
  }

  public AltriDatiOperaIncompiutaEntry coperturaComunale(String coperturaComunale) {
    this.coperturaComunale = coperturaComunale;
    return this;
  }

   /**
   * Tipologia copertura finanziaria Comunale (/WSTabelleDiContesto/rest/Tabellati/Valori?cod=SN)
   * @return coperturaComunale
  **/
  @ApiModelProperty(example = "null", value = "Tipologia copertura finanziaria Comunale (/WSTabelleDiContesto/rest/Tabellati/Valori?cod=SN)")
  public String getCoperturaComunale() {
    return coperturaComunale;
  }

  public void setCoperturaComunale(String coperturaComunale) {
    this.coperturaComunale = coperturaComunale;
  }

  public AltriDatiOperaIncompiutaEntry coperturaAltro(String coperturaAltro) {
    this.coperturaAltro = coperturaAltro;
    return this;
  }

   /**
   * Tipologia copertura finanziaria Altra Pubblica (/WSTabelleDiContesto/rest/Tabellati/Valori?cod=SN)
   * @return coperturaAltro
  **/
  @ApiModelProperty(example = "null", value = "Tipologia copertura finanziaria Altra Pubblica (/WSTabelleDiContesto/rest/Tabellati/Valori?cod=SN)")
  public String getCoperturaAltro() {
    return coperturaAltro;
  }

  public void setCoperturaAltro(String coperturaAltro) {
    this.coperturaAltro = coperturaAltro;
  }

  public AltriDatiOperaIncompiutaEntry coperturaComunitaria(String coperturaComunitaria) {
    this.coperturaComunitaria = coperturaComunitaria;
    return this;
  }

   /**
   * Tipologia copertura finanziaria Comunitaria (/WSTabelleDiContesto/rest/Tabellati/Valori?cod=SN)
   * @return coperturaComunitaria
  **/
  @ApiModelProperty(example = "null", value = "Tipologia copertura finanziaria Comunitaria (/WSTabelleDiContesto/rest/Tabellati/Valori?cod=SN)")
  public String getCoperturaComunitaria() {
    return coperturaComunitaria;
  }

  public void setCoperturaComunitaria(String coperturaComunitaria) {
    this.coperturaComunitaria = coperturaComunitaria;
  }

  public AltriDatiOperaIncompiutaEntry coperturaPrivata(String coperturaPrivata) {
    this.coperturaPrivata = coperturaPrivata;
    return this;
  }

   /**
   * Tipologia copertura finanziaria Privata (/WSTabelleDiContesto/rest/Tabellati/Valori?cod=SN)
   * @return coperturaPrivata
  **/
  @ApiModelProperty(example = "null", value = "Tipologia copertura finanziaria Privata (/WSTabelleDiContesto/rest/Tabellati/Valori?cod=SN)")
  public String getCoperturaPrivata() {
    return coperturaPrivata;
  }

  public void setCoperturaPrivata(String coperturaPrivata) {
    this.coperturaPrivata = coperturaPrivata;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AltriDatiOperaIncompiutaEntry altriDatiOperaIncompiutaEntry = (AltriDatiOperaIncompiutaEntry) o;
    return Objects.equals(this.istat, altriDatiOperaIncompiutaEntry.istat) &&
        Objects.equals(this.nuts, altriDatiOperaIncompiutaEntry.nuts) &&
        Objects.equals(this.tipologiaIntervento, altriDatiOperaIncompiutaEntry.tipologiaIntervento) &&
        Objects.equals(this.categoriaIntervento, altriDatiOperaIncompiutaEntry.categoriaIntervento) &&
        Objects.equals(this.requisitiCapitolato, altriDatiOperaIncompiutaEntry.requisitiCapitolato) &&
        Objects.equals(this.requisitiApprovato, altriDatiOperaIncompiutaEntry.requisitiApprovato) &&
        Objects.equals(this.unitaMisura, altriDatiOperaIncompiutaEntry.unitaMisura) &&
        Objects.equals(this.dimensione, altriDatiOperaIncompiutaEntry.dimensione) &&
        Objects.equals(this.sponsorizzazione, altriDatiOperaIncompiutaEntry.sponsorizzazione) &&
        Objects.equals(this.finanzaDiProgetto, altriDatiOperaIncompiutaEntry.finanzaDiProgetto) &&
        Objects.equals(this.costoProgetto, altriDatiOperaIncompiutaEntry.costoProgetto) &&
        Objects.equals(this.finanziamento, altriDatiOperaIncompiutaEntry.finanziamento) &&
        Objects.equals(this.coperturaStatale, altriDatiOperaIncompiutaEntry.coperturaStatale) &&
        Objects.equals(this.coperturaRegionale, altriDatiOperaIncompiutaEntry.coperturaRegionale) &&
        Objects.equals(this.coperturaProvinciale, altriDatiOperaIncompiutaEntry.coperturaProvinciale) &&
        Objects.equals(this.coperturaComunale, altriDatiOperaIncompiutaEntry.coperturaComunale) &&
        Objects.equals(this.coperturaAltro, altriDatiOperaIncompiutaEntry.coperturaAltro) &&
        Objects.equals(this.coperturaComunitaria, altriDatiOperaIncompiutaEntry.coperturaComunitaria) &&
        Objects.equals(this.coperturaPrivata, altriDatiOperaIncompiutaEntry.coperturaPrivata);
  }

  @Override
  public int hashCode() {
    return Objects.hash(istat, nuts, tipologiaIntervento, categoriaIntervento, requisitiCapitolato, requisitiApprovato, unitaMisura, dimensione, sponsorizzazione, finanzaDiProgetto, costoProgetto, finanziamento, coperturaStatale, coperturaRegionale, coperturaProvinciale, coperturaComunale, coperturaAltro, coperturaComunitaria, coperturaPrivata);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AltriDatiOperaIncompiutaEntry {\n");
    
    sb.append("    istat: ").append(toIndentedString(istat)).append("\n");
    sb.append("    nuts: ").append(toIndentedString(nuts)).append("\n");
    sb.append("    tipologiaIntervento: ").append(toIndentedString(tipologiaIntervento)).append("\n");
    sb.append("    categoriaIntervento: ").append(toIndentedString(categoriaIntervento)).append("\n");
    sb.append("    requisitiCapitolato: ").append(toIndentedString(requisitiCapitolato)).append("\n");
    sb.append("    requisitiApprovato: ").append(toIndentedString(requisitiApprovato)).append("\n");
    sb.append("    unitaMisura: ").append(toIndentedString(unitaMisura)).append("\n");
    sb.append("    dimensione: ").append(toIndentedString(dimensione)).append("\n");
    sb.append("    sponsorizzazione: ").append(toIndentedString(sponsorizzazione)).append("\n");
    sb.append("    finanzaDiProgetto: ").append(toIndentedString(finanzaDiProgetto)).append("\n");
    sb.append("    costoProgetto: ").append(toIndentedString(costoProgetto)).append("\n");
    sb.append("    finanziamento: ").append(toIndentedString(finanziamento)).append("\n");
    sb.append("    coperturaStatale: ").append(toIndentedString(coperturaStatale)).append("\n");
    sb.append("    coperturaRegionale: ").append(toIndentedString(coperturaRegionale)).append("\n");
    sb.append("    coperturaProvinciale: ").append(toIndentedString(coperturaProvinciale)).append("\n");
    sb.append("    coperturaComunale: ").append(toIndentedString(coperturaComunale)).append("\n");
    sb.append("    coperturaAltro: ").append(toIndentedString(coperturaAltro)).append("\n");
    sb.append("    coperturaComunitaria: ").append(toIndentedString(coperturaComunitaria)).append("\n");
    sb.append("    coperturaPrivata: ").append(toIndentedString(coperturaPrivata)).append("\n");
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

