
package it.csi.siac.ricerche.data._1;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * &lt;p&gt;Classe Java per documentoSpesa complex type.
 * 
 * &lt;p&gt;Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * &lt;pre&gt;
 * &amp;lt;complexType name="documentoSpesa"&amp;gt;
 *   &amp;lt;complexContent&amp;gt;
 *     &amp;lt;extension base="{http://siac.csi.it/ricerche/data/1.0}documento"&amp;gt;
 *       &amp;lt;sequence&amp;gt;
 *         &amp;lt;element name="elencoOrdini" type="{http://siac.csi.it/ricerche/data/1.0}ordine" maxOccurs="unbounded" minOccurs="0"/&amp;gt;
 *       &amp;lt;/sequence&amp;gt;
 *     &amp;lt;/extension&amp;gt;
 *   &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &lt;/pre&gt;
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "documentoSpesa", propOrder = {
    "elencoOrdini"
})
public class DocumentoSpesa
    extends Documento
{

    @XmlElement(nillable = true)
    protected List<Ordine> elencoOrdini;

    /**
     * Gets the value of the elencoOrdini property.
     * 
     * &lt;p&gt;
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a &lt;CODE&gt;set&lt;/CODE&gt; method for the elencoOrdini property.
     * 
     * &lt;p&gt;
     * For example, to add a new item, do as follows:
     * &lt;pre&gt;
     *    getElencoOrdini().add(newItem);
     * &lt;/pre&gt;
     * 
     * 
     * &lt;p&gt;
     * Objects of the following type(s) are allowed in the list
     * {@link Ordine }
     * 
     * 
     */
    public List<Ordine> getElencoOrdini() {
        if (elencoOrdini == null) {
            elencoOrdini = new ArrayList<Ordine>();
        }
        return this.elencoOrdini;
    }

}
