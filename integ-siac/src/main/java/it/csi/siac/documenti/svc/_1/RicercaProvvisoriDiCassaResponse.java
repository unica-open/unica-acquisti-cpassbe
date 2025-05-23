
package it.csi.siac.documenti.svc._1;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import it.csi.siac.integ.data._1.RicercaPaginataResponse;
import it.csi.siac.ricerche.data._1.ProvvisorioDiCassa;


/**
 * &lt;p&gt;Classe Java per ricercaProvvisoriDiCassaResponse complex type.
 * 
 * &lt;p&gt;Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * &lt;pre&gt;
 * &amp;lt;complexType name="ricercaProvvisoriDiCassaResponse"&amp;gt;
 *   &amp;lt;complexContent&amp;gt;
 *     &amp;lt;extension base="{http://siac.csi.it/integ/data/1.0}ricercaPaginataResponse"&amp;gt;
 *       &amp;lt;sequence&amp;gt;
 *         &amp;lt;element name="provvisoriDiCassa" type="{http://siac.csi.it/ricerche/data/1.0}provvisorioDiCassa" maxOccurs="unbounded" minOccurs="0"/&amp;gt;
 *       &amp;lt;/sequence&amp;gt;
 *     &amp;lt;/extension&amp;gt;
 *   &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &lt;/pre&gt;
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ricercaProvvisoriDiCassaResponse", propOrder = {
    "provvisoriDiCassa"
})
public class RicercaProvvisoriDiCassaResponse
    extends RicercaPaginataResponse
{

    @XmlElement(nillable = true)
    protected List<ProvvisorioDiCassa> provvisoriDiCassa;

    /**
     * Gets the value of the provvisoriDiCassa property.
     * 
     * &lt;p&gt;
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a &lt;CODE&gt;set&lt;/CODE&gt; method for the provvisoriDiCassa property.
     * 
     * &lt;p&gt;
     * For example, to add a new item, do as follows:
     * &lt;pre&gt;
     *    getProvvisoriDiCassa().add(newItem);
     * &lt;/pre&gt;
     * 
     * 
     * &lt;p&gt;
     * Objects of the following type(s) are allowed in the list
     * {@link ProvvisorioDiCassa }
     * 
     * 
     */
    public List<ProvvisorioDiCassa> getProvvisoriDiCassa() {
        if (provvisoriDiCassa == null) {
            provvisoriDiCassa = new ArrayList<ProvvisorioDiCassa>();
        }
        return this.provvisoriDiCassa;
    }

}
