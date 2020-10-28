
package it.csi.siac.documenti.svc._1;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import it.csi.siac.ricerche.data._1.DocumentoSpesa;


/**
 * &lt;p&gt;Classe Java per ricercaDocumentoSpesaResponse complex type.
 * 
 * &lt;p&gt;Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * &lt;pre&gt;
 * &amp;lt;complexType name="ricercaDocumentoSpesaResponse"&amp;gt;
 *   &amp;lt;complexContent&amp;gt;
 *     &amp;lt;extension base="{http://siac.csi.it/documenti/svc/1.0}baseRicercaDocumentoResponse"&amp;gt;
 *       &amp;lt;sequence&amp;gt;
 *         &amp;lt;element name="documentiSpesa" type="{http://siac.csi.it/ricerche/data/1.0}documentoSpesa" maxOccurs="unbounded" minOccurs="0"/&amp;gt;
 *       &amp;lt;/sequence&amp;gt;
 *     &amp;lt;/extension&amp;gt;
 *   &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &lt;/pre&gt;
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ricercaDocumentoSpesaResponse", propOrder = {
    "documentiSpesa"
})
public class RicercaDocumentoSpesaResponse
    extends BaseRicercaDocumentoResponse
{

    @XmlElement(nillable = true)
    protected List<DocumentoSpesa> documentiSpesa;

    /**
     * Gets the value of the documentiSpesa property.
     * 
     * &lt;p&gt;
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a &lt;CODE&gt;set&lt;/CODE&gt; method for the documentiSpesa property.
     * 
     * &lt;p&gt;
     * For example, to add a new item, do as follows:
     * &lt;pre&gt;
     *    getDocumentiSpesa().add(newItem);
     * &lt;/pre&gt;
     * 
     * 
     * &lt;p&gt;
     * Objects of the following type(s) are allowed in the list
     * {@link DocumentoSpesa }
     * 
     * 
     */
    public List<DocumentoSpesa> getDocumentiSpesa() {
        if (documentiSpesa == null) {
            documentiSpesa = new ArrayList<DocumentoSpesa>();
        }
        return this.documentiSpesa;
    }

}
