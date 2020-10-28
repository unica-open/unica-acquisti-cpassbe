
package it.csi.siac.integ.data._1;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * &lt;p&gt;Classe Java per ordinativoIncasso complex type.
 * 
 * &lt;p&gt;Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * &lt;pre&gt;
 * &amp;lt;complexType name="ordinativoIncasso"&amp;gt;
 *   &amp;lt;complexContent&amp;gt;
 *     &amp;lt;extension base="{http://siac.csi.it/integ/data/1.0}ordinativo"&amp;gt;
 *       &amp;lt;sequence&amp;gt;
 *         &amp;lt;element name="quoteIncasso" type="{http://siac.csi.it/integ/data/1.0}subOrdinativoIncasso" maxOccurs="unbounded" minOccurs="0"/&amp;gt;
 *       &amp;lt;/sequence&amp;gt;
 *     &amp;lt;/extension&amp;gt;
 *   &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &lt;/pre&gt;
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ordinativoIncasso", propOrder = {
    "quoteIncasso"
})
public class OrdinativoIncasso
    extends Ordinativo
{

    @XmlElement(nillable = true)
    protected List<SubOrdinativoIncasso> quoteIncasso;

    /**
     * Gets the value of the quoteIncasso property.
     * 
     * &lt;p&gt;
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a &lt;CODE&gt;set&lt;/CODE&gt; method for the quoteIncasso property.
     * 
     * &lt;p&gt;
     * For example, to add a new item, do as follows:
     * &lt;pre&gt;
     *    getQuoteIncasso().add(newItem);
     * &lt;/pre&gt;
     * 
     * 
     * &lt;p&gt;
     * Objects of the following type(s) are allowed in the list
     * {@link SubOrdinativoIncasso }
     * 
     * 
     */
    public List<SubOrdinativoIncasso> getQuoteIncasso() {
        if (quoteIncasso == null) {
            quoteIncasso = new ArrayList<SubOrdinativoIncasso>();
        }
        return this.quoteIncasso;
    }

}
