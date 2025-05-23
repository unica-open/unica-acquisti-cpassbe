
package org.etsi.uri._01903.v1_3;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per SPUserNoticeType complex type.
 *
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 *
 * <pre>
 * &lt;complexType name="SPUserNoticeType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="NoticeRef" type="{http://uri.etsi.org/01903/v1.3.2#}NoticeReferenceType" minOccurs="0"/&gt;
 *         &lt;element name="ExplicitText" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SPUserNoticeType", propOrder = {
    "noticeRef",
    "explicitText"
})
public class SPUserNoticeType {

    @XmlElement(name = "NoticeRef")
    protected NoticeReferenceType noticeRef;
    @XmlElement(name = "ExplicitText")
    protected String explicitText;

    /**
     * Recupera il valore della proprietà noticeRef.
     *
     * @return
     *     possible object is
     *     {@link NoticeReferenceType }
     *
     */
    public NoticeReferenceType getNoticeRef() {
        return noticeRef;
    }

    /**
     * Imposta il valore della proprietà noticeRef.
     *
     * @param value
     *     allowed object is
     *     {@link NoticeReferenceType }
     *
     */
    public void setNoticeRef(NoticeReferenceType value) {
        this.noticeRef = value;
    }

    /**
     * Recupera il valore della proprietà explicitText.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getExplicitText() {
        return explicitText;
    }

    /**
     * Imposta il valore della proprietà explicitText.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setExplicitText(String value) {
        this.explicitText = value;
    }

}
