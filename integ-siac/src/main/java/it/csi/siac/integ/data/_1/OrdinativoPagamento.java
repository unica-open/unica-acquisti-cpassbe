
package it.csi.siac.integ.data._1;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * &lt;p&gt;Classe Java per ordinativoPagamento complex type.
 * 
 * &lt;p&gt;Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * &lt;pre&gt;
 * &amp;lt;complexType name="ordinativoPagamento"&amp;gt;
 *   &amp;lt;complexContent&amp;gt;
 *     &amp;lt;extension base="{http://siac.csi.it/integ/data/1.0}ordinativo"&amp;gt;
 *       &amp;lt;sequence&amp;gt;
 *         &amp;lt;element name="quotePagamento" type="{http://siac.csi.it/integ/data/1.0}subOrdinativoPagamento" maxOccurs="unbounded" minOccurs="0"/&amp;gt;
 *       &amp;lt;/sequence&amp;gt;
 *     &amp;lt;/extension&amp;gt;
 *   &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &lt;/pre&gt;
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ordinativoPagamento", propOrder = {
    "quotePagamento"
})
public class OrdinativoPagamento
    extends Ordinativo
{

    @XmlElement(nillable = true)
    protected List<SubOrdinativoPagamento> quotePagamento;

    /**
     * Gets the value of the quotePagamento property.
     * 
     * &lt;p&gt;
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a &lt;CODE&gt;set&lt;/CODE&gt; method for the quotePagamento property.
     * 
     * &lt;p&gt;
     * For example, to add a new item, do as follows:
     * &lt;pre&gt;
     *    getQuotePagamento().add(newItem);
     * &lt;/pre&gt;
     * 
     * 
     * &lt;p&gt;
     * Objects of the following type(s) are allowed in the list
     * {@link SubOrdinativoPagamento }
     * 
     * 
     */
    public List<SubOrdinativoPagamento> getQuotePagamento() {
        if (quotePagamento == null) {
            quotePagamento = new ArrayList<SubOrdinativoPagamento>();
        }
        return this.quotePagamento;
    }

}
