
package it.csi.siac.documenti.svc._1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

import it.csi.siac.integ.data._1.BaseResponse;


/**
 * &lt;p&gt;Classe Java per elaboraDocumentoResponse complex type.
 * 
 * &lt;p&gt;Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * &lt;pre&gt;
 * &amp;lt;complexType name="elaboraDocumentoResponse"&amp;gt;
 *   &amp;lt;complexContent&amp;gt;
 *     &amp;lt;extension base="{http://siac.csi.it/integ/data/1.0}baseResponse"&amp;gt;
 *       &amp;lt;sequence&amp;gt;
 *         &amp;lt;element name="responseElaborazione" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *       &amp;lt;/sequence&amp;gt;
 *     &amp;lt;/extension&amp;gt;
 *   &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &lt;/pre&gt;
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "elaboraDocumentoResponse", propOrder = {
    "responseElaborazione"
})
public class ElaboraDocumentoResponse
    extends BaseResponse
{

    protected String responseElaborazione;

    /**
     * Recupera il valore della proprietà responseElaborazione.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getResponseElaborazione() {
        return responseElaborazione;
    }

    /**
     * Imposta il valore della proprietà responseElaborazione.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setResponseElaborazione(String value) {
        this.responseElaborazione = value;
    }

}
