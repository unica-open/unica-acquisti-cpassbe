
package it.csi.siac.integ.data._1;

import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * &lt;p&gt;Classe Java per subAccertamento complex type.
 * 
 * &lt;p&gt;Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * &lt;pre&gt;
 * &amp;lt;complexType name="subAccertamento"&amp;gt;
 *   &amp;lt;complexContent&amp;gt;
 *     &amp;lt;extension base="{http://siac.csi.it/integ/data/1.0}movimentoGestione"&amp;gt;
 *       &amp;lt;sequence&amp;gt;
 *         &amp;lt;element name="annoSubAccertamento" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="disponibilitaIncassare" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="numeroSubAccertamento" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&amp;gt;
 *       &amp;lt;/sequence&amp;gt;
 *     &amp;lt;/extension&amp;gt;
 *   &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &lt;/pre&gt;
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "subAccertamento", propOrder = {
    "annoSubAccertamento",
    "disponibilitaIncassare",
    "numeroSubAccertamento"
})
public class SubAccertamento
    extends MovimentoGestione
{

    protected Integer annoSubAccertamento;
    protected BigDecimal disponibilitaIncassare;
    protected Integer numeroSubAccertamento;

    /**
     * Recupera il valore della proprietà annoSubAccertamento.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getAnnoSubAccertamento() {
        return annoSubAccertamento;
    }

    /**
     * Imposta il valore della proprietà annoSubAccertamento.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setAnnoSubAccertamento(Integer value) {
        this.annoSubAccertamento = value;
    }

    /**
     * Recupera il valore della proprietà disponibilitaIncassare.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getDisponibilitaIncassare() {
        return disponibilitaIncassare;
    }

    /**
     * Imposta il valore della proprietà disponibilitaIncassare.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setDisponibilitaIncassare(BigDecimal value) {
        this.disponibilitaIncassare = value;
    }

    /**
     * Recupera il valore della proprietà numeroSubAccertamento.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getNumeroSubAccertamento() {
        return numeroSubAccertamento;
    }

    /**
     * Imposta il valore della proprietà numeroSubAccertamento.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setNumeroSubAccertamento(Integer value) {
        this.numeroSubAccertamento = value;
    }

}
