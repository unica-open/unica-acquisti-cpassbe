
package it.csi.siac.ricerche.svc._1;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import it.csi.siac.integ.data._1.BaseResponse;
import it.csi.siac.integ.data._1.LiquidazioneAtti;


/**
 * &lt;p&gt;Classe Java per ricercaEstesaLiquidazioniResponse complex type.
 * 
 * &lt;p&gt;Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * &lt;pre&gt;
 * &amp;lt;complexType name="ricercaEstesaLiquidazioniResponse"&amp;gt;
 *   &amp;lt;complexContent&amp;gt;
 *     &amp;lt;extension base="{http://siac.csi.it/integ/data/1.0}baseResponse"&amp;gt;
 *       &amp;lt;sequence&amp;gt;
 *         &amp;lt;element name="liquidazioni" type="{http://siac.csi.it/integ/data/1.0}liquidazioneAtti" maxOccurs="unbounded" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="numeroTotaleLiqudazioniTrovate" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&amp;gt;
 *       &amp;lt;/sequence&amp;gt;
 *     &amp;lt;/extension&amp;gt;
 *   &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &lt;/pre&gt;
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ricercaEstesaLiquidazioniResponse", propOrder = {
    "liquidazioni",
    "numeroTotaleLiqudazioniTrovate"
})
public class RicercaEstesaLiquidazioniResponse
    extends BaseResponse
{

    @XmlElement(nillable = true)
    protected List<LiquidazioneAtti> liquidazioni;
    protected Integer numeroTotaleLiqudazioniTrovate;

    /**
     * Gets the value of the liquidazioni property.
     * 
     * &lt;p&gt;
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a &lt;CODE&gt;set&lt;/CODE&gt; method for the liquidazioni property.
     * 
     * &lt;p&gt;
     * For example, to add a new item, do as follows:
     * &lt;pre&gt;
     *    getLiquidazioni().add(newItem);
     * &lt;/pre&gt;
     * 
     * 
     * &lt;p&gt;
     * Objects of the following type(s) are allowed in the list
     * {@link LiquidazioneAtti }
     * 
     * 
     */
    public List<LiquidazioneAtti> getLiquidazioni() {
        if (liquidazioni == null) {
            liquidazioni = new ArrayList<LiquidazioneAtti>();
        }
        return this.liquidazioni;
    }

    /**
     * Recupera il valore della proprietà numeroTotaleLiqudazioniTrovate.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getNumeroTotaleLiqudazioniTrovate() {
        return numeroTotaleLiqudazioniTrovate;
    }

    /**
     * Imposta il valore della proprietà numeroTotaleLiqudazioniTrovate.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setNumeroTotaleLiqudazioniTrovate(Integer value) {
        this.numeroTotaleLiqudazioniTrovate = value;
    }

}
