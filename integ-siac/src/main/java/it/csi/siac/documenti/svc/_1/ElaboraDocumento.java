
package it.csi.siac.documenti.svc._1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
import it.csi.siac.integ.data._1.BaseRequest;


/**
 * &lt;p&gt;Classe Java per elaboraDocumento complex type.
 * 
 * &lt;p&gt;Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * &lt;pre&gt;
 * &amp;lt;complexType name="elaboraDocumento"&amp;gt;
 *   &amp;lt;complexContent&amp;gt;
 *     &amp;lt;extension base="{http://siac.csi.it/integ/data/1.0}baseRequest"&amp;gt;
 *       &amp;lt;sequence&amp;gt;
 *         &amp;lt;element name="codiceTipoDocumento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="contenutoDocumento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *       &amp;lt;/sequence&amp;gt;
 *     &amp;lt;/extension&amp;gt;
 *   &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &lt;/pre&gt;
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "elaboraDocumento", propOrder = {
    "codiceTipoDocumento",
    "contenutoDocumento"
})
public class ElaboraDocumento
    extends BaseRequest
{

    protected String codiceTipoDocumento;
    protected String contenutoDocumento;

    /**
     * Recupera il valore della proprietà codiceTipoDocumento.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodiceTipoDocumento() {
        return codiceTipoDocumento;
    }

    /**
     * Imposta il valore della proprietà codiceTipoDocumento.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodiceTipoDocumento(String value) {
        this.codiceTipoDocumento = value;
    }

    /**
     * Recupera il valore della proprietà contenutoDocumento.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContenutoDocumento() {
        return contenutoDocumento;
    }

    /**
     * Imposta il valore della proprietà contenutoDocumento.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContenutoDocumento(String value) {
        this.contenutoDocumento = value;
    }

}
