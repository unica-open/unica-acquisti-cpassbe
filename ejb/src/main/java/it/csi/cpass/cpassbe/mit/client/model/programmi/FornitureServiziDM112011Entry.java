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
import it.csi.cpass.cpassbe.mit.client.model.programmi.DatiGeneraliTecnicoEntry;


/**
 * Contenitore per i dati di un intervento per forniture e servizi per Programma DM 11/2011
 */
@ApiModel(description = "Contenitore per i dati di un intervento per forniture e servizi per Programma DM 11/2011")
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2020-03-26T12:24:42.165+01:00")
public class FornitureServiziDM112011Entry   {
  @SerializedName("codiceInterno")
  private String codiceInterno = null;

  @SerializedName("descrizione")
  private String descrizione = null;

  @SerializedName("istat")
  private String istat = null;

  @SerializedName("nuts")
  private String nuts = null;

  @SerializedName("esenteCup")
  private String esenteCup = null;

  @SerializedName("cup")
  private String cup = null;

  @SerializedName("cpv")
  private String cpv = null;

  /**
   * Settore F=Forniture, S=Servizi
   */
  public enum SettoreEnum {
    @SerializedName("F")
    F("F"),
    
    @SerializedName("S")
    S("S");

    private String value;

    SettoreEnum(String value) {
      this.value = value;
    }

    @Override
    public String toString() {
      return String.valueOf(value);
    }
  }

  @SerializedName("settore")
  private SettoreEnum settore = null;

  @SerializedName("priorita")
  private Long priorita = null;

  @SerializedName("normativaRiferimento")
  private String normativaRiferimento = null;

  @SerializedName("strumentoProgrammazione")
  private String strumentoProgrammazione = null;

  @SerializedName("previstaManodopera")
  private String previstaManodopera = null;

  @SerializedName("risorseMutuo")
  private Double risorseMutuo = null;

  @SerializedName("risorsePrivati")
  private Double risorsePrivati = null;

  @SerializedName("risorseBilancio")
  private Double risorseBilancio = null;

  @SerializedName("risorseAltro")
  private Double risorseAltro = null;

  @SerializedName("importoRisorseFinanziarie")
  private Double importoRisorseFinanziarie = null;

  @SerializedName("importoRisorseFinanziarieRegionali")
  private Double importoRisorseFinanziarieRegionali = null;

  @SerializedName("meseAvvioProcedura")
  private Long meseAvvioProcedura = null;

  @SerializedName("rup")
  private DatiGeneraliTecnicoEntry rup = null;

  public FornitureServiziDM112011Entry codiceInterno(String codiceInterno) {
    this.codiceInterno = codiceInterno;
    return this;
  }

   /**
   * Codice interno attribuito dall'amministrazione
   * @return codiceInterno
  **/
  @ApiModelProperty(example = "null", value = "Codice interno attribuito dall'amministrazione")
  public String getCodiceInterno() {
    return codiceInterno;
  }

  public void setCodiceInterno(String codiceInterno) {
    this.codiceInterno = codiceInterno;
  }

  public FornitureServiziDM112011Entry descrizione(String descrizione) {
    this.descrizione = descrizione;
    return this;
  }

   /**
   * Descrizione intervento
   * @return descrizione
  **/
  @ApiModelProperty(example = "null", required = true, value = "Descrizione intervento")
  public String getDescrizione() {
    return descrizione;
  }

  public void setDescrizione(String descrizione) {
    this.descrizione = descrizione;
  }

  public FornitureServiziDM112011Entry istat(String istat) {
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

  public FornitureServiziDM112011Entry nuts(String nuts) {
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

  public FornitureServiziDM112011Entry esenteCup(String esenteCup) {
    this.esenteCup = esenteCup;
    return this;
  }

   /**
   * Esente dalla richiesta CUP? (/WSTabelleDiContesto/rest/Tabellati/Valori?cod=SN)
   * @return esenteCup
  **/
  @ApiModelProperty(example = "null", value = "Esente dalla richiesta CUP? (/WSTabelleDiContesto/rest/Tabellati/Valori?cod=SN)")
  public String getEsenteCup() {
    return esenteCup;
  }

  public void setEsenteCup(String esenteCup) {
    this.esenteCup = esenteCup;
  }

  public FornitureServiziDM112011Entry cup(String cup) {
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

  public FornitureServiziDM112011Entry cpv(String cpv) {
    this.cpv = cpv;
    return this;
  }

   /**
   * Codice CPV
   * @return cpv
  **/
  @ApiModelProperty(example = "null", value = "Codice CPV")
  public String getCpv() {
    return cpv;
  }

  public void setCpv(String cpv) {
    this.cpv = cpv;
  }

  public FornitureServiziDM112011Entry settore(SettoreEnum settore) {
    this.settore = settore;
    return this;
  }

   /**
   * Settore F=Forniture, S=Servizi
   * @return settore
  **/
  @ApiModelProperty(example = "null", required = true, value = "Settore F=Forniture, S=Servizi")
  public SettoreEnum getSettore() {
    return settore;
  }

  public void setSettore(SettoreEnum settore) {
    this.settore = settore;
  }

  public FornitureServiziDM112011Entry priorita(Long priorita) {
    this.priorita = priorita;
    return this;
  }

   /**
   * Livello di Priorit� (/WSTabelleDiContesto/rest/Tabellati/Valori?cod=Priorita)
   * @return priorita
  **/
  @ApiModelProperty(example = "null", value = "Livello di Priorit� (/WSTabelleDiContesto/rest/Tabellati/Valori?cod=Priorita)")
  public Long getPriorita() {
    return priorita;
  }

  public void setPriorita(Long priorita) {
    this.priorita = priorita;
  }

  public FornitureServiziDM112011Entry normativaRiferimento(String normativaRiferimento) {
    this.normativaRiferimento = normativaRiferimento;
    return this;
  }

   /**
   * Normativa di riferimento
   * @return normativaRiferimento
  **/
  @ApiModelProperty(example = "null", value = "Normativa di riferimento")
  public String getNormativaRiferimento() {
    return normativaRiferimento;
  }

  public void setNormativaRiferimento(String normativaRiferimento) {
    this.normativaRiferimento = normativaRiferimento;
  }

  public FornitureServiziDM112011Entry strumentoProgrammazione(String strumentoProgrammazione) {
    this.strumentoProgrammazione = strumentoProgrammazione;
    return this;
  }

   /**
   * Eventuale strumento di programma
   * @return strumentoProgrammazione
  **/
  @ApiModelProperty(example = "null", value = "Eventuale strumento di programma")
  public String getStrumentoProgrammazione() {
    return strumentoProgrammazione;
  }

  public void setStrumentoProgrammazione(String strumentoProgrammazione) {
    this.strumentoProgrammazione = strumentoProgrammazione;
  }

  public FornitureServiziDM112011Entry previstaManodopera(String previstaManodopera) {
    this.previstaManodopera = previstaManodopera;
    return this;
  }

   /**
   * E' previsto impiego di manodopera/posa in opera? (/WSTabelleDiContesto/rest/Tabellati/Valori?cod=SN)
   * @return previstaManodopera
  **/
  @ApiModelProperty(example = "null", value = "E' previsto impiego di manodopera/posa in opera? (/WSTabelleDiContesto/rest/Tabellati/Valori?cod=SN)")
  public String getPrevistaManodopera() {
    return previstaManodopera;
  }

  public void setPrevistaManodopera(String previstaManodopera) {
    this.previstaManodopera = previstaManodopera;
  }

  public FornitureServiziDM112011Entry risorseMutuo(Double risorseMutuo) {
    this.risorseMutuo = risorseMutuo;
    return this;
  }

   /**
   * Risorse acquisite mediante contrazioni di mutuo
   * @return risorseMutuo
  **/
  @ApiModelProperty(example = "null", value = "Risorse acquisite mediante contrazioni di mutuo")
  public Double getRisorseMutuo() {
    return risorseMutuo;
  }

  public void setRisorseMutuo(Double risorseMutuo) {
    this.risorseMutuo = risorseMutuo;
  }

  public FornitureServiziDM112011Entry risorsePrivati(Double risorsePrivati) {
    this.risorsePrivati = risorsePrivati;
    return this;
  }

   /**
   * Risorse acquisite mediante apporti di capitali privati
   * @return risorsePrivati
  **/
  @ApiModelProperty(example = "null", value = "Risorse acquisite mediante apporti di capitali privati")
  public Double getRisorsePrivati() {
    return risorsePrivati;
  }

  public void setRisorsePrivati(Double risorsePrivati) {
    this.risorsePrivati = risorsePrivati;
  }

  public FornitureServiziDM112011Entry risorseBilancio(Double risorseBilancio) {
    this.risorseBilancio = risorseBilancio;
    return this;
  }

   /**
   * Stanziamenti di bilancio
   * @return risorseBilancio
  **/
  @ApiModelProperty(example = "null", value = "Stanziamenti di bilancio")
  public Double getRisorseBilancio() {
    return risorseBilancio;
  }

  public void setRisorseBilancio(Double risorseBilancio) {
    this.risorseBilancio = risorseBilancio;
  }

  public FornitureServiziDM112011Entry risorseAltro(Double risorseAltro) {
    this.risorseAltro = risorseAltro;
    return this;
  }

   /**
   * Altre risorse disponibili
   * @return risorseAltro
  **/
  @ApiModelProperty(example = "null", value = "Altre risorse disponibili")
  public Double getRisorseAltro() {
    return risorseAltro;
  }

  public void setRisorseAltro(Double risorseAltro) {
    this.risorseAltro = risorseAltro;
  }

  public FornitureServiziDM112011Entry importoRisorseFinanziarie(Double importoRisorseFinanziarie) {
    this.importoRisorseFinanziarie = importoRisorseFinanziarie;
    return this;
  }

   /**
   * [Attributo aggiuntivo, previsto in ambiti regionali] Importo risorse finanziarie stato/UE
   * @return importoRisorseFinanziarie
  **/
  @ApiModelProperty(example = "null", value = "[Attributo aggiuntivo, previsto in ambiti regionali] Importo risorse finanziarie stato/UE")
  public Double getImportoRisorseFinanziarie() {
    return importoRisorseFinanziarie;
  }

  public void setImportoRisorseFinanziarie(Double importoRisorseFinanziarie) {
    this.importoRisorseFinanziarie = importoRisorseFinanziarie;
  }

  public FornitureServiziDM112011Entry importoRisorseFinanziarieRegionali(Double importoRisorseFinanziarieRegionali) {
    this.importoRisorseFinanziarieRegionali = importoRisorseFinanziarieRegionali;
    return this;
  }

   /**
   * [Attributo aggiuntivo, previsto in ambiti regionali] Importo risorse finanziarie regionali
   * @return importoRisorseFinanziarieRegionali
  **/
  @ApiModelProperty(example = "null", value = "[Attributo aggiuntivo, previsto in ambiti regionali] Importo risorse finanziarie regionali")
  public Double getImportoRisorseFinanziarieRegionali() {
    return importoRisorseFinanziarieRegionali;
  }

  public void setImportoRisorseFinanziarieRegionali(Double importoRisorseFinanziarieRegionali) {
    this.importoRisorseFinanziarieRegionali = importoRisorseFinanziarieRegionali;
  }

  public FornitureServiziDM112011Entry meseAvvioProcedura(Long meseAvvioProcedura) {
    this.meseAvvioProcedura = meseAvvioProcedura;
    return this;
  }

   /**
   * [Attributo aggiuntivo, previsto in ambiti regionali] Mese previsto per avvio procedura contrattuale
   * @return meseAvvioProcedura
  **/
  @ApiModelProperty(example = "null", value = "[Attributo aggiuntivo, previsto in ambiti regionali] Mese previsto per avvio procedura contrattuale")
  public Long getMeseAvvioProcedura() {
    return meseAvvioProcedura;
  }

  public void setMeseAvvioProcedura(Long meseAvvioProcedura) {
    this.meseAvvioProcedura = meseAvvioProcedura;
  }

  public FornitureServiziDM112011Entry rup(DatiGeneraliTecnicoEntry rup) {
    this.rup = rup;
    return this;
  }

   /**
   * Responsabile unico RUP
   * @return rup
  **/
  @ApiModelProperty(example = "null", value = "Responsabile unico RUP")
  public DatiGeneraliTecnicoEntry getRup() {
    return rup;
  }

  public void setRup(DatiGeneraliTecnicoEntry rup) {
    this.rup = rup;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    FornitureServiziDM112011Entry fornitureServiziDM112011Entry = (FornitureServiziDM112011Entry) o;
    return Objects.equals(this.codiceInterno, fornitureServiziDM112011Entry.codiceInterno) &&
        Objects.equals(this.descrizione, fornitureServiziDM112011Entry.descrizione) &&
        Objects.equals(this.istat, fornitureServiziDM112011Entry.istat) &&
        Objects.equals(this.nuts, fornitureServiziDM112011Entry.nuts) &&
        Objects.equals(this.esenteCup, fornitureServiziDM112011Entry.esenteCup) &&
        Objects.equals(this.cup, fornitureServiziDM112011Entry.cup) &&
        Objects.equals(this.cpv, fornitureServiziDM112011Entry.cpv) &&
        Objects.equals(this.settore, fornitureServiziDM112011Entry.settore) &&
        Objects.equals(this.priorita, fornitureServiziDM112011Entry.priorita) &&
        Objects.equals(this.normativaRiferimento, fornitureServiziDM112011Entry.normativaRiferimento) &&
        Objects.equals(this.strumentoProgrammazione, fornitureServiziDM112011Entry.strumentoProgrammazione) &&
        Objects.equals(this.previstaManodopera, fornitureServiziDM112011Entry.previstaManodopera) &&
        Objects.equals(this.risorseMutuo, fornitureServiziDM112011Entry.risorseMutuo) &&
        Objects.equals(this.risorsePrivati, fornitureServiziDM112011Entry.risorsePrivati) &&
        Objects.equals(this.risorseBilancio, fornitureServiziDM112011Entry.risorseBilancio) &&
        Objects.equals(this.risorseAltro, fornitureServiziDM112011Entry.risorseAltro) &&
        Objects.equals(this.importoRisorseFinanziarie, fornitureServiziDM112011Entry.importoRisorseFinanziarie) &&
        Objects.equals(this.importoRisorseFinanziarieRegionali, fornitureServiziDM112011Entry.importoRisorseFinanziarieRegionali) &&
        Objects.equals(this.meseAvvioProcedura, fornitureServiziDM112011Entry.meseAvvioProcedura) &&
        Objects.equals(this.rup, fornitureServiziDM112011Entry.rup);
  }

  @Override
  public int hashCode() {
    return Objects.hash(codiceInterno, descrizione, istat, nuts, esenteCup, cup, cpv, settore, priorita, normativaRiferimento, strumentoProgrammazione, previstaManodopera, risorseMutuo, risorsePrivati, risorseBilancio, risorseAltro, importoRisorseFinanziarie, importoRisorseFinanziarieRegionali, meseAvvioProcedura, rup);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class FornitureServiziDM112011Entry {\n");
    
    sb.append("    codiceInterno: ").append(toIndentedString(codiceInterno)).append("\n");
    sb.append("    descrizione: ").append(toIndentedString(descrizione)).append("\n");
    sb.append("    istat: ").append(toIndentedString(istat)).append("\n");
    sb.append("    nuts: ").append(toIndentedString(nuts)).append("\n");
    sb.append("    esenteCup: ").append(toIndentedString(esenteCup)).append("\n");
    sb.append("    cup: ").append(toIndentedString(cup)).append("\n");
    sb.append("    cpv: ").append(toIndentedString(cpv)).append("\n");
    sb.append("    settore: ").append(toIndentedString(settore)).append("\n");
    sb.append("    priorita: ").append(toIndentedString(priorita)).append("\n");
    sb.append("    normativaRiferimento: ").append(toIndentedString(normativaRiferimento)).append("\n");
    sb.append("    strumentoProgrammazione: ").append(toIndentedString(strumentoProgrammazione)).append("\n");
    sb.append("    previstaManodopera: ").append(toIndentedString(previstaManodopera)).append("\n");
    sb.append("    risorseMutuo: ").append(toIndentedString(risorseMutuo)).append("\n");
    sb.append("    risorsePrivati: ").append(toIndentedString(risorsePrivati)).append("\n");
    sb.append("    risorseBilancio: ").append(toIndentedString(risorseBilancio)).append("\n");
    sb.append("    risorseAltro: ").append(toIndentedString(risorseAltro)).append("\n");
    sb.append("    importoRisorseFinanziarie: ").append(toIndentedString(importoRisorseFinanziarie)).append("\n");
    sb.append("    importoRisorseFinanziarieRegionali: ").append(toIndentedString(importoRisorseFinanziarieRegionali)).append("\n");
    sb.append("    meseAvvioProcedura: ").append(toIndentedString(meseAvvioProcedura)).append("\n");
    sb.append("    rup: ").append(toIndentedString(rup)).append("\n");
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

