
package it.csi.siac.integ.data._1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import it.csi.siac.ricerche.svc._1.BaseRicercaDocumento;
import it.csi.siac.ricerche.svc._1.RicercaCapitolo;
import it.csi.siac.ricerche.svc._1.RicercaLiquidazione;
import it.csi.siac.ricerche.svc._1.RicercaMovimentoGestione;
import it.csi.siac.ricerche.svc._1.RicercaOrdinativoIncasso;
import it.csi.siac.ricerche.svc._1.RicercaOrdinativoSpesa;
import it.csi.siac.ricerche.svc._1.RicercaProvvisoriDiCassa;


/**
 * &lt;p&gt;Classe Java per ricercaPaginataRequest complex type.
 * 
 * &lt;p&gt;Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * &lt;pre&gt;
 * &amp;lt;complexType name="ricercaPaginataRequest"&amp;gt;
 *   &amp;lt;complexContent&amp;gt;
 *     &amp;lt;extension base="{http://siac.csi.it/integ/data/1.0}baseRicercaRequest"&amp;gt;
 *       &amp;lt;sequence&amp;gt;
 *         &amp;lt;element name="numeroElementiPerPagina" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="numeroPagina" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&amp;gt;
 *       &amp;lt;/sequence&amp;gt;
 *     &amp;lt;/extension&amp;gt;
 *   &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &lt;/pre&gt;
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ricercaPaginataRequest", propOrder = {
    "numeroElementiPerPagina",
    "numeroPagina"
})
@XmlSeeAlso({
    RicercaOrdinativoIncasso.class,
    RicercaOrdinativoSpesa.class,
    RicercaProvvisoriDiCassa.class,
    RicercaLiquidazione.class,
    RicercaCapitolo.class,
    BaseRicercaDocumento.class,
    RicercaMovimentoGestione.class
})
public abstract class RicercaPaginataRequest
    extends BaseRicercaRequest
{

    protected Integer numeroElementiPerPagina;
    protected Integer numeroPagina;

    /**
     * Recupera il valore della proprietà numeroElementiPerPagina.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getNumeroElementiPerPagina() {
        return numeroElementiPerPagina;
    }

    /**
     * Imposta il valore della proprietà numeroElementiPerPagina.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setNumeroElementiPerPagina(Integer value) {
        this.numeroElementiPerPagina = value;
    }

    /**
     * Recupera il valore della proprietà numeroPagina.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getNumeroPagina() {
        return numeroPagina;
    }

    /**
     * Imposta il valore della proprietà numeroPagina.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setNumeroPagina(Integer value) {
        this.numeroPagina = value;
    }

}
