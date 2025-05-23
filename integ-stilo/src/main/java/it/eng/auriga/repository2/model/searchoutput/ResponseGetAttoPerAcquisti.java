
package it.eng.auriga.repository2.model.searchoutput;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Classe Java per anonymous complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="Esito"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;enumeration value="atto_trovato"/&gt;
 *               &lt;enumeration value="atto_non_trovato"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="DatiAtto" minOccurs="0"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="StrutturaProponente"&gt;
 *                     &lt;complexType&gt;
 *                       &lt;simpleContent&gt;
 *                         &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema&gt;string"&gt;
 *                           &lt;attribute name="codice" use="required" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *                         &lt;/extension&gt;
 *                       &lt;/simpleContent&gt;
 *                     &lt;/complexType&gt;
 *                   &lt;/element&gt;
 *                   &lt;element name="Oggetto" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *                   &lt;element name="CdC" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *                   &lt;element name="CdR" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *                   &lt;element name="DataAdozione" type="{http://www.w3.org/2001/XMLSchema}date"/&gt;
 *                   &lt;element name="DataEsecutivita" type="{http://www.w3.org/2001/XMLSchema}date"/&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "esito",
    "datiAtto"
})
@XmlRootElement(name = "ResponseGetAttoPerAcquisti")
public class ResponseGetAttoPerAcquisti {

    @XmlElement(name = "Esito", required = true)
    protected String esito;
    @XmlElement(name = "DatiAtto")
    protected ResponseGetAttoPerAcquisti.DatiAtto datiAtto;

    /**
     * Recupera il valore della proprietà esito.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEsito() {
        return esito;
    }

    /**
     * Imposta il valore della proprietà esito.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEsito(String value) {
        this.esito = value;
    }

    /**
     * Recupera il valore della proprietà datiAtto.
     * 
     * @return
     *     possible object is
     *     {@link ResponseGetAttoPerAcquisti.DatiAtto }
     *     
     */
    public ResponseGetAttoPerAcquisti.DatiAtto getDatiAtto() {
        return datiAtto;
    }

    /**
     * Imposta il valore della proprietà datiAtto.
     * 
     * @param value
     *     allowed object is
     *     {@link ResponseGetAttoPerAcquisti.DatiAtto }
     *     
     */
    public void setDatiAtto(ResponseGetAttoPerAcquisti.DatiAtto value) {
        this.datiAtto = value;
    }


    /**
     * <p>Classe Java per anonymous complex type.
     * 
     * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
     * 
     * <pre>
     * &lt;complexType&gt;
     *   &lt;complexContent&gt;
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *       &lt;sequence&gt;
     *         &lt;element name="StrutturaProponente"&gt;
     *           &lt;complexType&gt;
     *             &lt;simpleContent&gt;
     *               &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema&gt;string"&gt;
     *                 &lt;attribute name="codice" use="required" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
     *               &lt;/extension&gt;
     *             &lt;/simpleContent&gt;
     *           &lt;/complexType&gt;
     *         &lt;/element&gt;
     *         &lt;element name="Oggetto" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
     *         &lt;element name="CdC" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
     *         &lt;element name="CdR" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
     *         &lt;element name="DataAdozione" type="{http://www.w3.org/2001/XMLSchema}date"/&gt;
     *         &lt;element name="DataEsecutivita" type="{http://www.w3.org/2001/XMLSchema}date"/&gt;
     *       &lt;/sequence&gt;
     *     &lt;/restriction&gt;
     *   &lt;/complexContent&gt;
     * &lt;/complexType&gt;
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "strutturaProponente",
        "oggetto",
        "cdC",
        "cdR",
        "dataAdozione",
        "dataEsecutivita"
    })
    public static class DatiAtto {

        @XmlElement(name = "StrutturaProponente", required = true)
        protected ResponseGetAttoPerAcquisti.DatiAtto.StrutturaProponente strutturaProponente;
        @XmlElement(name = "Oggetto", required = true)
        protected String oggetto;
        @XmlElement(name = "CdC", required = true)
        protected String cdC;
        @XmlElement(name = "CdR", required = true)
        protected String cdR;
        @XmlElement(name = "DataAdozione", required = true)
        @XmlSchemaType(name = "date")
        protected XMLGregorianCalendar dataAdozione;
        @XmlElement(name = "DataEsecutivita", required = true)
        @XmlSchemaType(name = "date")
        protected XMLGregorianCalendar dataEsecutivita;

        /**
         * Recupera il valore della proprietà strutturaProponente.
         * 
         * @return
         *     possible object is
         *     {@link ResponseGetAttoPerAcquisti.DatiAtto.StrutturaProponente }
         *     
         */
        public ResponseGetAttoPerAcquisti.DatiAtto.StrutturaProponente getStrutturaProponente() {
            return strutturaProponente;
        }

        /**
         * Imposta il valore della proprietà strutturaProponente.
         * 
         * @param value
         *     allowed object is
         *     {@link ResponseGetAttoPerAcquisti.DatiAtto.StrutturaProponente }
         *     
         */
        public void setStrutturaProponente(ResponseGetAttoPerAcquisti.DatiAtto.StrutturaProponente value) {
            this.strutturaProponente = value;
        }

        /**
         * Recupera il valore della proprietà oggetto.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getOggetto() {
            return oggetto;
        }

        /**
         * Imposta il valore della proprietà oggetto.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setOggetto(String value) {
            this.oggetto = value;
        }

        /**
         * Recupera il valore della proprietà cdC.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getCdC() {
            return cdC;
        }

        /**
         * Imposta il valore della proprietà cdC.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setCdC(String value) {
            this.cdC = value;
        }

        /**
         * Recupera il valore della proprietà cdR.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getCdR() {
            return cdR;
        }

        /**
         * Imposta il valore della proprietà cdR.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setCdR(String value) {
            this.cdR = value;
        }

        /**
         * Recupera il valore della proprietà dataAdozione.
         * 
         * @return
         *     possible object is
         *     {@link XMLGregorianCalendar }
         *     
         */
        public XMLGregorianCalendar getDataAdozione() {
            return dataAdozione;
        }

        /**
         * Imposta il valore della proprietà dataAdozione.
         * 
         * @param value
         *     allowed object is
         *     {@link XMLGregorianCalendar }
         *     
         */
        public void setDataAdozione(XMLGregorianCalendar value) {
            this.dataAdozione = value;
        }

        /**
         * Recupera il valore della proprietà dataEsecutivita.
         * 
         * @return
         *     possible object is
         *     {@link XMLGregorianCalendar }
         *     
         */
        public XMLGregorianCalendar getDataEsecutivita() {
            return dataEsecutivita;
        }

        /**
         * Imposta il valore della proprietà dataEsecutivita.
         * 
         * @param value
         *     allowed object is
         *     {@link XMLGregorianCalendar }
         *     
         */
        public void setDataEsecutivita(XMLGregorianCalendar value) {
            this.dataEsecutivita = value;
        }


        /**
         * <p>Classe Java per anonymous complex type.
         * 
         * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
         * 
         * <pre>
         * &lt;complexType&gt;
         *   &lt;simpleContent&gt;
         *     &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema&gt;string"&gt;
         *       &lt;attribute name="codice" use="required" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
         *     &lt;/extension&gt;
         *   &lt;/simpleContent&gt;
         * &lt;/complexType&gt;
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
            "value"
        })
        public static class StrutturaProponente {

            @XmlValue
            protected String value;
            @XmlAttribute(name = "codice", required = true)
            protected String codice;

            /**
             * Recupera il valore della proprietà value.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getValue() {
                return value;
            }

            /**
             * Imposta il valore della proprietà value.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setValue(String value) {
                this.value = value;
            }

            /**
             * Recupera il valore della proprietà codice.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getCodice() {
                return codice;
            }

            /**
             * Imposta il valore della proprietà codice.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setCodice(String value) {
                this.codice = value;
            }

        }

    }

}
