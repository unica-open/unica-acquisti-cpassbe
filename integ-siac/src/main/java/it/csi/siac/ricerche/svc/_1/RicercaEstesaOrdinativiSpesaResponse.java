
package it.csi.siac.ricerche.svc._1;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import it.csi.siac.integ.data._1.BaseResponse;
import it.csi.siac.integ.data._1.MandatoDiPagamento;


/**
 * &lt;p&gt;Classe Java per ricercaEstesaOrdinativiSpesaResponse complex type.
 * 
 * &lt;p&gt;Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * &lt;pre&gt;
 * &amp;lt;complexType name="ricercaEstesaOrdinativiSpesaResponse"&amp;gt;
 *   &amp;lt;complexContent&amp;gt;
 *     &amp;lt;extension base="{http://siac.csi.it/integ/data/1.0}baseResponse"&amp;gt;
 *       &amp;lt;sequence&amp;gt;
 *         &amp;lt;element name="mandatiDiPagamento" type="{http://siac.csi.it/integ/data/1.0}mandatoDiPagamento" maxOccurs="unbounded" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="numeroTotaleOrdinativiSpesaTrovati" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&amp;gt;
 *       &amp;lt;/sequence&amp;gt;
 *     &amp;lt;/extension&amp;gt;
 *   &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &lt;/pre&gt;
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ricercaEstesaOrdinativiSpesaResponse", propOrder = {
    "mandatiDiPagamento",
    "numeroTotaleOrdinativiSpesaTrovati"
})
public class RicercaEstesaOrdinativiSpesaResponse
    extends BaseResponse
{

    @XmlElement(nillable = true)
    protected List<MandatoDiPagamento> mandatiDiPagamento;
    protected Integer numeroTotaleOrdinativiSpesaTrovati;

    /**
     * Gets the value of the mandatiDiPagamento property.
     * 
     * &lt;p&gt;
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a &lt;CODE&gt;set&lt;/CODE&gt; method for the mandatiDiPagamento property.
     * 
     * &lt;p&gt;
     * For example, to add a new item, do as follows:
     * &lt;pre&gt;
     *    getMandatiDiPagamento().add(newItem);
     * &lt;/pre&gt;
     * 
     * 
     * &lt;p&gt;
     * Objects of the following type(s) are allowed in the list
     * {@link MandatoDiPagamento }
     * 
     * 
     */
    public List<MandatoDiPagamento> getMandatiDiPagamento() {
        if (mandatiDiPagamento == null) {
            mandatiDiPagamento = new ArrayList<MandatoDiPagamento>();
        }
        return this.mandatiDiPagamento;
    }

    /**
     * Recupera il valore della proprietà numeroTotaleOrdinativiSpesaTrovati.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getNumeroTotaleOrdinativiSpesaTrovati() {
        return numeroTotaleOrdinativiSpesaTrovati;
    }

    /**
     * Imposta il valore della proprietà numeroTotaleOrdinativiSpesaTrovati.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setNumeroTotaleOrdinativiSpesaTrovati(Integer value) {
        this.numeroTotaleOrdinativiSpesaTrovati = value;
    }

}
