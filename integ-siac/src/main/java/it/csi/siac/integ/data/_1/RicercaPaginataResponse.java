
package it.csi.siac.integ.data._1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import it.csi.siac.documenti.svc._1.BaseRicercaDocumentoResponse;
import it.csi.siac.documenti.svc._1.RicercaProvvisoriDiCassaResponse;
import it.csi.siac.ricerche.svc._1.RicercaAccertamentoResponse;
import it.csi.siac.ricerche.svc._1.RicercaCapitoloResponse;
import it.csi.siac.ricerche.svc._1.RicercaImpegnoResponse;
import it.csi.siac.ricerche.svc._1.RicercaLiquidazioneResponse;
import it.csi.siac.ricerche.svc._1.RicercaOrdinativoIncassoResponse;
import it.csi.siac.ricerche.svc._1.RicercaOrdinativoSpesaResponse;


/**
 * &lt;p&gt;Classe Java per ricercaPaginataResponse complex type.
 * 
 * &lt;p&gt;Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * &lt;pre&gt;
 * &amp;lt;complexType name="ricercaPaginataResponse"&amp;gt;
 *   &amp;lt;complexContent&amp;gt;
 *     &amp;lt;extension base="{http://siac.csi.it/integ/data/1.0}baseRicercaResponse"&amp;gt;
 *       &amp;lt;sequence&amp;gt;
 *         &amp;lt;element name="totaleRisultati" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&amp;gt;
 *       &amp;lt;/sequence&amp;gt;
 *     &amp;lt;/extension&amp;gt;
 *   &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &lt;/pre&gt;
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ricercaPaginataResponse", propOrder = {
    "totaleRisultati"
})
@XmlSeeAlso({
    RicercaOrdinativoIncassoResponse.class,
    RicercaImpegnoResponse.class,
    RicercaOrdinativoSpesaResponse.class,
    RicercaProvvisoriDiCassaResponse.class,
    RicercaAccertamentoResponse.class,
    RicercaLiquidazioneResponse.class,
    RicercaCapitoloResponse.class,
    BaseRicercaDocumentoResponse.class
})
public abstract class RicercaPaginataResponse
    extends BaseRicercaResponse
{

    protected Integer totaleRisultati;

    /**
     * Recupera il valore della proprietà totaleRisultati.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getTotaleRisultati() {
        return totaleRisultati;
    }

    /**
     * Imposta il valore della proprietà totaleRisultati.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setTotaleRisultati(Integer value) {
        this.totaleRisultati = value;
    }

}
