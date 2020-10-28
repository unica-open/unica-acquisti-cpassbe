
package it.csi.siac.ricerche.svc._1;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import it.csi.siac.integ.data._1.Liquidazione;
import it.csi.siac.integ.data._1.RicercaPaginataResponse;


/**
 * &lt;p&gt;Classe Java per ricercaLiquidazioneResponse complex type.
 * 
 * &lt;p&gt;Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * &lt;pre&gt;
 * &amp;lt;complexType name="ricercaLiquidazioneResponse"&amp;gt;
 *   &amp;lt;complexContent&amp;gt;
 *     &amp;lt;extension base="{http://siac.csi.it/integ/data/1.0}ricercaPaginataResponse"&amp;gt;
 *       &amp;lt;sequence&amp;gt;
 *         &amp;lt;element name="liquidazioni" type="{http://siac.csi.it/integ/data/1.0}liquidazione" maxOccurs="unbounded" minOccurs="0"/&amp;gt;
 *       &amp;lt;/sequence&amp;gt;
 *     &amp;lt;/extension&amp;gt;
 *   &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &lt;/pre&gt;
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ricercaLiquidazioneResponse", propOrder = {
    "liquidazioni"
})
public class RicercaLiquidazioneResponse
    extends RicercaPaginataResponse
{

    @XmlElement(nillable = true)
    protected List<Liquidazione> liquidazioni;

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
     * {@link Liquidazione }
     * 
     * 
     */
    public List<Liquidazione> getLiquidazioni() {
        if (liquidazioni == null) {
            liquidazioni = new ArrayList<Liquidazione>();
        }
        return this.liquidazioni;
    }

}
