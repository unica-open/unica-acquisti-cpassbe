/*-
 * ========================LICENSE_START=================================
 * CPASS BackEnd - INTEGRATION submodule - SICRAWEB
 * %%
 * Copyright (C) 2019 - 2023 CSI Piemonte
 * %%
 * SPDX-FileCopyrightText: Copyright 2019 - 2023 | CSI Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 * =========================LICENSE_END==================================
 */

package fdewsappjricercagateway;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * &lt;p&gt;Classe Java per capitoloUscitaGestione complex type.
 * 
 * &lt;p&gt;Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * &lt;pre&gt;
 * &amp;lt;complexType name="capitoloUscitaGestione"&amp;gt;
 *   &amp;lt;complexContent&amp;gt;
 *     &amp;lt;extension base="{urn:FdeWSAppjRicercaGateway}capitolo"&amp;gt;
 *       &amp;lt;sequence&amp;gt;
 *         &amp;lt;element name="importiUG" type="{urn:FdeWSAppjRicercaGateway}importoCapitoloUscitaGestione" maxOccurs="unbounded" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="macroaggregato" type="{urn:FdeWSAppjRicercaGateway}macroaggregato" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="missione" type="{urn:FdeWSAppjRicercaGateway}missione" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="programma" type="{urn:FdeWSAppjRicercaGateway}programma" minOccurs="0"/&amp;gt;
 *       &amp;lt;/sequence&amp;gt;
 *     &amp;lt;/extension&amp;gt;
 *   &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &lt;/pre&gt;
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "capitoloUscitaGestione", propOrder = {
    "importiUG",
    "macroaggregato",
    "missione",
    "programma"
})
public class CapitoloUscitaGestione
    extends Capitolo
{

    @XmlElement(nillable = true)
    protected List<ImportoCapitoloUscitaGestione> importiUG;
    protected Macroaggregato macroaggregato;
    protected Missione missione;
    protected Programma programma;

    /**
     * Gets the value of the importiUG property.
     * 
     * &lt;p&gt;
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a &lt;CODE&gt;set&lt;/CODE&gt; method for the importiUG property.
     * 
     * &lt;p&gt;
     * For example, to add a new item, do as follows:
     * &lt;pre&gt;
     *    getImportiUG().add(newItem);
     * &lt;/pre&gt;
     * 
     * 
     * &lt;p&gt;
     * Objects of the following type(s) are allowed in the list
     * {@link ImportoCapitoloUscitaGestione }
     * 
     * 
     */
    public List<ImportoCapitoloUscitaGestione> getImportiUG() {
        if (importiUG == null) {
            importiUG = new ArrayList<ImportoCapitoloUscitaGestione>();
        }
        return this.importiUG;
    }

    /**
     * Recupera il valore della proprietà macroaggregato.
     * 
     * @return
     *     possible object is
     *     {@link Macroaggregato }
     *     
     */
    public Macroaggregato getMacroaggregato() {
        return macroaggregato;
    }

    /**
     * Imposta il valore della proprietà macroaggregato.
     * 
     * @param value
     *     allowed object is
     *     {@link Macroaggregato }
     *     
     */
    public void setMacroaggregato(Macroaggregato value) {
        this.macroaggregato = value;
    }

    /**
     * Recupera il valore della proprietà missione.
     * 
     * @return
     *     possible object is
     *     {@link Missione }
     *     
     */
    public Missione getMissione() {
        return missione;
    }

    /**
     * Imposta il valore della proprietà missione.
     * 
     * @param value
     *     allowed object is
     *     {@link Missione }
     *     
     */
    public void setMissione(Missione value) {
        this.missione = value;
    }

    /**
     * Recupera il valore della proprietà programma.
     * 
     * @return
     *     possible object is
     *     {@link Programma }
     *     
     */
    public Programma getProgramma() {
        return programma;
    }

    /**
     * Imposta il valore della proprietà programma.
     * 
     * @param value
     *     allowed object is
     *     {@link Programma }
     *     
     */
    public void setProgramma(Programma value) {
        this.programma = value;
    }

}
