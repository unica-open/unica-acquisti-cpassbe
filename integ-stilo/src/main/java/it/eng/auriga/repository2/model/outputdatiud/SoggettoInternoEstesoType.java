//
// Questo file è stato generato dall'architettura JavaTM per XML Binding (JAXB) Reference Implementation, v2.3.2 
// Vedere <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Qualsiasi modifica a questo file andrà persa durante la ricompilazione dello schema di origine. 
// Generato il: 2020.05.26 alle 03:23:29 PM CEST 
//


package it.eng.auriga.repository2.model.outputdatiud;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per SoggettoInternoEstesoType complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="SoggettoInternoEstesoType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{}SoggettoInternoType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="NaturaRelazioneConUD" type="{}OggDiTabDiSistemaType"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SoggettoInternoEstesoType", propOrder = {
    "naturaRelazioneConUD"
})
public class SoggettoInternoEstesoType
    extends SoggettoInternoType
{

    @XmlElement(name = "NaturaRelazioneConUD", required = true)
    protected OggDiTabDiSistemaType naturaRelazioneConUD;

    /**
     * Recupera il valore della proprietà naturaRelazioneConUD.
     * 
     * @return
     *     possible object is
     *     {@link OggDiTabDiSistemaType }
     *     
     */
    public OggDiTabDiSistemaType getNaturaRelazioneConUD() {
        return naturaRelazioneConUD;
    }

    /**
     * Imposta il valore della proprietà naturaRelazioneConUD.
     * 
     * @param value
     *     allowed object is
     *     {@link OggDiTabDiSistemaType }
     *     
     */
    public void setNaturaRelazioneConUD(OggDiTabDiSistemaType value) {
        this.naturaRelazioneConUD = value;
    }

}
