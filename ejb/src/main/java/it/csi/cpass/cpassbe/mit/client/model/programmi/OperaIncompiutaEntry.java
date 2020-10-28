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
import it.csi.cpass.cpassbe.mit.client.model.programmi.AltriDatiOperaIncompiutaEntry;
import it.csi.cpass.cpassbe.mit.client.model.programmi.ImmobileEntry;
import java.util.ArrayList;
import java.util.List;


/**
 * Contenitore per i dati di un&#39;opera incompiuta
 */
@ApiModel(description = "Contenitore per i dati di un'opera incompiuta")
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2020-03-26T12:24:42.165+01:00")
public class OperaIncompiutaEntry   {
  @SerializedName("cup")
  private String cup = null;

  @SerializedName("descrizione")
  private String descrizione = null;

  @SerializedName("determinazioneAmministrazione")
  private String determinazioneAmministrazione = null;

  @SerializedName("ambito")
  private String ambito = null;

  @SerializedName("anno")
  private Long anno = null;

  @SerializedName("importoIntervento")
  private Double importoIntervento = null;

  @SerializedName("importoLavori")
  private Double importoLavori = null;

  @SerializedName("oneri")
  private Double oneri = null;

  @SerializedName("importoAvanzamento")
  private Double importoAvanzamento = null;

  @SerializedName("percentualeAvanzamento")
  private Double percentualeAvanzamento = null;

  @SerializedName("causa")
  private String causa = null;

  @SerializedName("stato")
  private String stato = null;

  @SerializedName("infrastruttura")
  private String infrastruttura = null;

  @SerializedName("fruibile")
  private String fruibile = null;

  @SerializedName("ridimensionato")
  private String ridimensionato = null;

  @SerializedName("destinazioneUso")
  private String destinazioneUso = null;

  @SerializedName("cessione")
  private String cessione = null;

  @SerializedName("previstaVendita")
  private String previstaVendita = null;

  @SerializedName("demolizione")
  private String demolizione = null;

  @SerializedName("oneriSito")
  private Double oneriSito = null;

  @SerializedName("altriDati")
  private AltriDatiOperaIncompiutaEntry altriDati = null;

  @SerializedName("immobili")
  private List<ImmobileEntry> immobili = new ArrayList<ImmobileEntry>();

  public OperaIncompiutaEntry cup(String cup) {
    this.cup = cup;
    return this;
  }

   /**
   * Codice CUP
   * @return cup
  **/
  @ApiModelProperty(example = "null", required = true, value = "Codice CUP")
  public String getCup() {
    return cup;
  }

  public void setCup(String cup) {
    this.cup = cup;
  }

  public OperaIncompiutaEntry descrizione(String descrizione) {
    this.descrizione = descrizione;
    return this;
  }

   /**
   * Descrizione opera
   * @return descrizione
  **/
  @ApiModelProperty(example = "null", required = true, value = "Descrizione opera")
  public String getDescrizione() {
    return descrizione;
  }

  public void setDescrizione(String descrizione) {
    this.descrizione = descrizione;
  }

  public OperaIncompiutaEntry determinazioneAmministrazione(String determinazioneAmministrazione) {
    this.determinazioneAmministrazione = determinazioneAmministrazione;
    return this;
  }

   /**
   * Determinazioni dell'amministrazione (/WSTabelleDiContesto/rest/Tabellati/Valori?cod=Determinazioni)
   * @return determinazioneAmministrazione
  **/
  @ApiModelProperty(example = "null", value = "Determinazioni dell'amministrazione (/WSTabelleDiContesto/rest/Tabellati/Valori?cod=Determinazioni)")
  public String getDeterminazioneAmministrazione() {
    return determinazioneAmministrazione;
  }

  public void setDeterminazioneAmministrazione(String determinazioneAmministrazione) {
    this.determinazioneAmministrazione = determinazioneAmministrazione;
  }

  public OperaIncompiutaEntry ambito(String ambito) {
    this.ambito = ambito;
    return this;
  }

   /**
   * Ambito di interesse dell'opera (/WSTabelleDiContesto/rest/Tabellati/Valori?cod=Ambito)
   * @return ambito
  **/
  @ApiModelProperty(example = "null", value = "Ambito di interesse dell'opera (/WSTabelleDiContesto/rest/Tabellati/Valori?cod=Ambito)")
  public String getAmbito() {
    return ambito;
  }

  public void setAmbito(String ambito) {
    this.ambito = ambito;
  }

  public OperaIncompiutaEntry anno(Long anno) {
    this.anno = anno;
    return this;
  }

   /**
   * Anno ultimo q.e. approvato
   * @return anno
  **/
  @ApiModelProperty(example = "null", value = "Anno ultimo q.e. approvato")
  public Long getAnno() {
    return anno;
  }

  public void setAnno(Long anno) {
    this.anno = anno;
  }

  public OperaIncompiutaEntry importoIntervento(Double importoIntervento) {
    this.importoIntervento = importoIntervento;
    return this;
  }

   /**
   * Importo complessivo dell'intervento
   * @return importoIntervento
  **/
  @ApiModelProperty(example = "null", value = "Importo complessivo dell'intervento")
  public Double getImportoIntervento() {
    return importoIntervento;
  }

  public void setImportoIntervento(Double importoIntervento) {
    this.importoIntervento = importoIntervento;
  }

  public OperaIncompiutaEntry importoLavori(Double importoLavori) {
    this.importoLavori = importoLavori;
    return this;
  }

   /**
   * Importo complessivo lavori
   * @return importoLavori
  **/
  @ApiModelProperty(example = "null", value = "Importo complessivo lavori")
  public Double getImportoLavori() {
    return importoLavori;
  }

  public void setImportoLavori(Double importoLavori) {
    this.importoLavori = importoLavori;
  }

  public OperaIncompiutaEntry oneri(Double oneri) {
    this.oneri = oneri;
    return this;
  }

   /**
   * Oneri necessari per l'ultimazione dei lavori
   * @return oneri
  **/
  @ApiModelProperty(example = "null", value = "Oneri necessari per l'ultimazione dei lavori")
  public Double getOneri() {
    return oneri;
  }

  public void setOneri(Double oneri) {
    this.oneri = oneri;
  }

  public OperaIncompiutaEntry importoAvanzamento(Double importoAvanzamento) {
    this.importoAvanzamento = importoAvanzamento;
    return this;
  }

   /**
   * Importo ultimo SAL
   * @return importoAvanzamento
  **/
  @ApiModelProperty(example = "null", value = "Importo ultimo SAL")
  public Double getImportoAvanzamento() {
    return importoAvanzamento;
  }

  public void setImportoAvanzamento(Double importoAvanzamento) {
    this.importoAvanzamento = importoAvanzamento;
  }

  public OperaIncompiutaEntry percentualeAvanzamento(Double percentualeAvanzamento) {
    this.percentualeAvanzamento = percentualeAvanzamento;
    return this;
  }

   /**
   * Percentuale avanzamento lavori %
   * @return percentualeAvanzamento
  **/
  @ApiModelProperty(example = "null", value = "Percentuale avanzamento lavori %")
  public Double getPercentualeAvanzamento() {
    return percentualeAvanzamento;
  }

  public void setPercentualeAvanzamento(Double percentualeAvanzamento) {
    this.percentualeAvanzamento = percentualeAvanzamento;
  }

  public OperaIncompiutaEntry causa(String causa) {
    this.causa = causa;
    return this;
  }

   /**
   * Causa per la quale l'opera � incompiuta (/WSTabelleDiContesto/rest/Tabellati/Valori?cod=Causa)
   * @return causa
  **/
  @ApiModelProperty(example = "null", value = "Causa per la quale l'opera � incompiuta (/WSTabelleDiContesto/rest/Tabellati/Valori?cod=Causa)")
  public String getCausa() {
    return causa;
  }

  public void setCausa(String causa) {
    this.causa = causa;
  }

  public OperaIncompiutaEntry stato(String stato) {
    this.stato = stato;
    return this;
  }

   /**
   * Stato di realizzazione (/WSTabelleDiContesto/rest/Tabellati/Valori?cod=StatoRealizzazione)
   * @return stato
  **/
  @ApiModelProperty(example = "null", value = "Stato di realizzazione (/WSTabelleDiContesto/rest/Tabellati/Valori?cod=StatoRealizzazione)")
  public String getStato() {
    return stato;
  }

  public void setStato(String stato) {
    this.stato = stato;
  }

  public OperaIncompiutaEntry infrastruttura(String infrastruttura) {
    this.infrastruttura = infrastruttura;
    return this;
  }

   /**
   * Parte di infrastruttura di rete? (/WSTabelleDiContesto/rest/Tabellati/Valori?cod=SN)
   * @return infrastruttura
  **/
  @ApiModelProperty(example = "null", value = "Parte di infrastruttura di rete? (/WSTabelleDiContesto/rest/Tabellati/Valori?cod=SN)")
  public String getInfrastruttura() {
    return infrastruttura;
  }

  public void setInfrastruttura(String infrastruttura) {
    this.infrastruttura = infrastruttura;
  }

  public OperaIncompiutaEntry fruibile(String fruibile) {
    this.fruibile = fruibile;
    return this;
  }

   /**
   * Opera fruibile parzialmente? (/WSTabelleDiContesto/rest/Tabellati/Valori?cod=SN)
   * @return fruibile
  **/
  @ApiModelProperty(example = "null", value = "Opera fruibile parzialmente? (/WSTabelleDiContesto/rest/Tabellati/Valori?cod=SN)")
  public String getFruibile() {
    return fruibile;
  }

  public void setFruibile(String fruibile) {
    this.fruibile = fruibile;
  }

  public OperaIncompiutaEntry ridimensionato(String ridimensionato) {
    this.ridimensionato = ridimensionato;
    return this;
  }

   /**
   * Utilizzo ridimensionato? (/WSTabelleDiContesto/rest/Tabellati/Valori?cod=SN)
   * @return ridimensionato
  **/
  @ApiModelProperty(example = "null", value = "Utilizzo ridimensionato? (/WSTabelleDiContesto/rest/Tabellati/Valori?cod=SN)")
  public String getRidimensionato() {
    return ridimensionato;
  }

  public void setRidimensionato(String ridimensionato) {
    this.ridimensionato = ridimensionato;
  }

  public OperaIncompiutaEntry destinazioneUso(String destinazioneUso) {
    this.destinazioneUso = destinazioneUso;
    return this;
  }

   /**
   * Destinazione d'uso (/WSTabelleDiContesto/rest/Tabellati/Valori?cod=DestinazioneUso)
   * @return destinazioneUso
  **/
  @ApiModelProperty(example = "null", value = "Destinazione d'uso (/WSTabelleDiContesto/rest/Tabellati/Valori?cod=DestinazioneUso)")
  public String getDestinazioneUso() {
    return destinazioneUso;
  }

  public void setDestinazioneUso(String destinazioneUso) {
    this.destinazioneUso = destinazioneUso;
  }

  public OperaIncompiutaEntry cessione(String cessione) {
    this.cessione = cessione;
    return this;
  }

   /**
   * Cessione per realizzazione di altra opera? (/WSTabelleDiContesto/rest/Tabellati/Valori?cod=SN)
   * @return cessione
  **/
  @ApiModelProperty(example = "null", value = "Cessione per realizzazione di altra opera? (/WSTabelleDiContesto/rest/Tabellati/Valori?cod=SN)")
  public String getCessione() {
    return cessione;
  }

  public void setCessione(String cessione) {
    this.cessione = cessione;
  }

  public OperaIncompiutaEntry previstaVendita(String previstaVendita) {
    this.previstaVendita = previstaVendita;
    return this;
  }

   /**
   * Prevista la vendita? (/WSTabelleDiContesto/rest/Tabellati/Valori?cod=SN)
   * @return previstaVendita
  **/
  @ApiModelProperty(example = "null", value = "Prevista la vendita? (/WSTabelleDiContesto/rest/Tabellati/Valori?cod=SN)")
  public String getPrevistaVendita() {
    return previstaVendita;
  }

  public void setPrevistaVendita(String previstaVendita) {
    this.previstaVendita = previstaVendita;
  }

  public OperaIncompiutaEntry demolizione(String demolizione) {
    this.demolizione = demolizione;
    return this;
  }

   /**
   * Opera da demolire? (/WSTabelleDiContesto/rest/Tabellati/Valori?cod=SN)
   * @return demolizione
  **/
  @ApiModelProperty(example = "null", value = "Opera da demolire? (/WSTabelleDiContesto/rest/Tabellati/Valori?cod=SN)")
  public String getDemolizione() {
    return demolizione;
  }

  public void setDemolizione(String demolizione) {
    this.demolizione = demolizione;
  }

  public OperaIncompiutaEntry oneriSito(Double oneriSito) {
    this.oneriSito = oneriSito;
    return this;
  }

   /**
   * Oneri Sito
   * @return oneriSito
  **/
  @ApiModelProperty(example = "null", value = "Oneri Sito")
  public Double getOneriSito() {
    return oneriSito;
  }

  public void setOneriSito(Double oneriSito) {
    this.oneriSito = oneriSito;
  }

  public OperaIncompiutaEntry altriDati(AltriDatiOperaIncompiutaEntry altriDati) {
    this.altriDati = altriDati;
    return this;
  }

   /**
   * Altri dati
   * @return altriDati
  **/
  @ApiModelProperty(example = "null", value = "Altri dati")
  public AltriDatiOperaIncompiutaEntry getAltriDati() {
    return altriDati;
  }

  public void setAltriDati(AltriDatiOperaIncompiutaEntry altriDati) {
    this.altriDati = altriDati;
  }

  public OperaIncompiutaEntry immobili(List<ImmobileEntry> immobili) {
    this.immobili = immobili;
    return this;
  }

  public OperaIncompiutaEntry addImmobiliItem(ImmobileEntry immobiliItem) {
    this.immobili.add(immobiliItem);
    return this;
  }

   /**
   * Immobili da trasferire
   * @return immobili
  **/
  @ApiModelProperty(example = "null", value = "Immobili da trasferire")
  public List<ImmobileEntry> getImmobili() {
    return immobili;
  }

  public void setImmobili(List<ImmobileEntry> immobili) {
    this.immobili = immobili;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    OperaIncompiutaEntry operaIncompiutaEntry = (OperaIncompiutaEntry) o;
    return Objects.equals(this.cup, operaIncompiutaEntry.cup) &&
        Objects.equals(this.descrizione, operaIncompiutaEntry.descrizione) &&
        Objects.equals(this.determinazioneAmministrazione, operaIncompiutaEntry.determinazioneAmministrazione) &&
        Objects.equals(this.ambito, operaIncompiutaEntry.ambito) &&
        Objects.equals(this.anno, operaIncompiutaEntry.anno) &&
        Objects.equals(this.importoIntervento, operaIncompiutaEntry.importoIntervento) &&
        Objects.equals(this.importoLavori, operaIncompiutaEntry.importoLavori) &&
        Objects.equals(this.oneri, operaIncompiutaEntry.oneri) &&
        Objects.equals(this.importoAvanzamento, operaIncompiutaEntry.importoAvanzamento) &&
        Objects.equals(this.percentualeAvanzamento, operaIncompiutaEntry.percentualeAvanzamento) &&
        Objects.equals(this.causa, operaIncompiutaEntry.causa) &&
        Objects.equals(this.stato, operaIncompiutaEntry.stato) &&
        Objects.equals(this.infrastruttura, operaIncompiutaEntry.infrastruttura) &&
        Objects.equals(this.fruibile, operaIncompiutaEntry.fruibile) &&
        Objects.equals(this.ridimensionato, operaIncompiutaEntry.ridimensionato) &&
        Objects.equals(this.destinazioneUso, operaIncompiutaEntry.destinazioneUso) &&
        Objects.equals(this.cessione, operaIncompiutaEntry.cessione) &&
        Objects.equals(this.previstaVendita, operaIncompiutaEntry.previstaVendita) &&
        Objects.equals(this.demolizione, operaIncompiutaEntry.demolizione) &&
        Objects.equals(this.oneriSito, operaIncompiutaEntry.oneriSito) &&
        Objects.equals(this.altriDati, operaIncompiutaEntry.altriDati) &&
        Objects.equals(this.immobili, operaIncompiutaEntry.immobili);
  }

  @Override
  public int hashCode() {
    return Objects.hash(cup, descrizione, determinazioneAmministrazione, ambito, anno, importoIntervento, importoLavori, oneri, importoAvanzamento, percentualeAvanzamento, causa, stato, infrastruttura, fruibile, ridimensionato, destinazioneUso, cessione, previstaVendita, demolizione, oneriSito, altriDati, immobili);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class OperaIncompiutaEntry {\n");
    
    sb.append("    cup: ").append(toIndentedString(cup)).append("\n");
    sb.append("    descrizione: ").append(toIndentedString(descrizione)).append("\n");
    sb.append("    determinazioneAmministrazione: ").append(toIndentedString(determinazioneAmministrazione)).append("\n");
    sb.append("    ambito: ").append(toIndentedString(ambito)).append("\n");
    sb.append("    anno: ").append(toIndentedString(anno)).append("\n");
    sb.append("    importoIntervento: ").append(toIndentedString(importoIntervento)).append("\n");
    sb.append("    importoLavori: ").append(toIndentedString(importoLavori)).append("\n");
    sb.append("    oneri: ").append(toIndentedString(oneri)).append("\n");
    sb.append("    importoAvanzamento: ").append(toIndentedString(importoAvanzamento)).append("\n");
    sb.append("    percentualeAvanzamento: ").append(toIndentedString(percentualeAvanzamento)).append("\n");
    sb.append("    causa: ").append(toIndentedString(causa)).append("\n");
    sb.append("    stato: ").append(toIndentedString(stato)).append("\n");
    sb.append("    infrastruttura: ").append(toIndentedString(infrastruttura)).append("\n");
    sb.append("    fruibile: ").append(toIndentedString(fruibile)).append("\n");
    sb.append("    ridimensionato: ").append(toIndentedString(ridimensionato)).append("\n");
    sb.append("    destinazioneUso: ").append(toIndentedString(destinazioneUso)).append("\n");
    sb.append("    cessione: ").append(toIndentedString(cessione)).append("\n");
    sb.append("    previstaVendita: ").append(toIndentedString(previstaVendita)).append("\n");
    sb.append("    demolizione: ").append(toIndentedString(demolizione)).append("\n");
    sb.append("    oneriSito: ").append(toIndentedString(oneriSito)).append("\n");
    sb.append("    altriDati: ").append(toIndentedString(altriDati)).append("\n");
    sb.append("    immobili: ").append(toIndentedString(immobili)).append("\n");
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

