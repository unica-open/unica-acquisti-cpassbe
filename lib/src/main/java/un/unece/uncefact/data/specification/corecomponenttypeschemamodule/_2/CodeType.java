
package un.unece.uncefact.data.specification.corecomponenttypeschemamodule._2;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.NormalizedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 *
 *
 * <pre>
 * &lt;?xml version="1.0" encoding="UTF-8"?&gt;&lt;ccts:UniqueID xmlns:ccts="urn:un:unece:uncefact:documentation:2" xmlns:cct="urn:un:unece:uncefact:data:specification:CoreComponentTypeSchemaModule:2" xmlns:xsd="http://www.w3.org/2001/XMLSchema"&gt;UNDT000007&lt;/ccts:UniqueID&gt;
 * </pre>
 *
 *
 * <pre>
 * &lt;?xml version="1.0" encoding="UTF-8"?&gt;&lt;ccts:CategoryCode xmlns:ccts="urn:un:unece:uncefact:documentation:2" xmlns:cct="urn:un:unece:uncefact:data:specification:CoreComponentTypeSchemaModule:2" xmlns:xsd="http://www.w3.org/2001/XMLSchema"&gt;CCT&lt;/ccts:CategoryCode&gt;
 * </pre>
 *
 *
 * <pre>
 * &lt;?xml version="1.0" encoding="UTF-8"?&gt;&lt;ccts:DictionaryEntryName xmlns:ccts="urn:un:unece:uncefact:documentation:2" xmlns:cct="urn:un:unece:uncefact:data:specification:CoreComponentTypeSchemaModule:2" xmlns:xsd="http://www.w3.org/2001/XMLSchema"&gt;Code. Type&lt;/ccts:DictionaryEntryName&gt;
 * </pre>
 *
 *
 * <pre>
 * &lt;?xml version="1.0" encoding="UTF-8"?&gt;&lt;ccts:VersionID xmlns:ccts="urn:un:unece:uncefact:documentation:2" xmlns:cct="urn:un:unece:uncefact:data:specification:CoreComponentTypeSchemaModule:2" xmlns:xsd="http://www.w3.org/2001/XMLSchema"&gt;1.0&lt;/ccts:VersionID&gt;
 * </pre>
 *
 *
 * <pre>
 * &lt;?xml version="1.0" encoding="UTF-8"?&gt;&lt;ccts:Definition xmlns:ccts="urn:un:unece:uncefact:documentation:2" xmlns:cct="urn:un:unece:uncefact:data:specification:CoreComponentTypeSchemaModule:2" xmlns:xsd="http://www.w3.org/2001/XMLSchema"&gt;A character string (letters, figures, or symbols) that for brevity and/or languange independence may be used to represent or replace a definitive value or text of an attribute together with relevant supplementary information.&lt;/ccts:Definition&gt;
 * </pre>
 *
 *
 * <pre>
 * &lt;?xml version="1.0" encoding="UTF-8"?&gt;&lt;ccts:RepresentationTermName xmlns:ccts="urn:un:unece:uncefact:documentation:2" xmlns:cct="urn:un:unece:uncefact:data:specification:CoreComponentTypeSchemaModule:2" xmlns:xsd="http://www.w3.org/2001/XMLSchema"&gt;Code&lt;/ccts:RepresentationTermName&gt;
 * </pre>
 *
 *
 * <pre>
 * &lt;?xml version="1.0" encoding="UTF-8"?&gt;&lt;ccts:PrimitiveType xmlns:ccts="urn:un:unece:uncefact:documentation:2" xmlns:cct="urn:un:unece:uncefact:data:specification:CoreComponentTypeSchemaModule:2" xmlns:xsd="http://www.w3.org/2001/XMLSchema"&gt;string&lt;/ccts:PrimitiveType&gt;
 * </pre>
 *
 *
 * <pre>
 * &lt;?xml version="1.0" encoding="UTF-8"?&gt;&lt;ccts:UsageRule xmlns:ccts="urn:un:unece:uncefact:documentation:2" xmlns:cct="urn:un:unece:uncefact:data:specification:CoreComponentTypeSchemaModule:2" xmlns:xsd="http://www.w3.org/2001/XMLSchema"&gt;Should not be used if the character string identifies an instance of an object class or an object in the real world, in which case the Identifier. Type should be used.&lt;/ccts:UsageRule&gt;
 * </pre>
 *
 *
 *
 * <p>Classe Java per CodeType complex type.
 *
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 *
 * <pre>
 * &lt;complexType name="CodeType"&gt;
 *   &lt;simpleContent&gt;
 *     &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema&gt;normalizedString"&gt;
 *       &lt;attribute name="listID" type="{http://www.w3.org/2001/XMLSchema}normalizedString" /&gt;
 *       &lt;attribute name="listAgencyID" type="{http://www.w3.org/2001/XMLSchema}normalizedString" /&gt;
 *       &lt;attribute name="listAgencyName" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *       &lt;attribute name="listName" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *       &lt;attribute name="listVersionID" type="{http://www.w3.org/2001/XMLSchema}normalizedString" /&gt;
 *       &lt;attribute name="name" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *       &lt;attribute name="languageID" type="{http://www.w3.org/2001/XMLSchema}language" /&gt;
 *       &lt;attribute name="listURI" type="{http://www.w3.org/2001/XMLSchema}anyURI" /&gt;
 *       &lt;attribute name="listSchemeURI" type="{http://www.w3.org/2001/XMLSchema}anyURI" /&gt;
 *     &lt;/extension&gt;
 *   &lt;/simpleContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CodeType", propOrder = {
    "value"
})
@XmlSeeAlso({
    oasis.names.specification.ubl.schema.xsd.unqualifieddatatypes_2.CodeType.class
})
public class CodeType {

    @XmlValue
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    @XmlSchemaType(name = "normalizedString")
    protected String value;
    @XmlAttribute(name = "listID")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    @XmlSchemaType(name = "normalizedString")
    protected String listID;
    @XmlAttribute(name = "listAgencyID")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    @XmlSchemaType(name = "normalizedString")
    protected String listAgencyID;
    @XmlAttribute(name = "listAgencyName")
    protected String listAgencyName;
    @XmlAttribute(name = "listName")
    protected String listName;
    @XmlAttribute(name = "listVersionID")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    @XmlSchemaType(name = "normalizedString")
    protected String listVersionID;
    @XmlAttribute(name = "name")
    protected String name;
    @XmlAttribute(name = "languageID")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "language")
    protected String languageID;
    @XmlAttribute(name = "listURI")
    @XmlSchemaType(name = "anyURI")
    protected String listURI;
    @XmlAttribute(name = "listSchemeURI")
    @XmlSchemaType(name = "anyURI")
    protected String listSchemeURI;

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
     * Recupera il valore della proprietà listID.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getListID() {
        return listID;
    }

    /**
     * Imposta il valore della proprietà listID.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setListID(String value) {
        this.listID = value;
    }

    /**
     * Recupera il valore della proprietà listAgencyID.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getListAgencyID() {
        return listAgencyID;
    }

    /**
     * Imposta il valore della proprietà listAgencyID.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setListAgencyID(String value) {
        this.listAgencyID = value;
    }

    /**
     * Recupera il valore della proprietà listAgencyName.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getListAgencyName() {
        return listAgencyName;
    }

    /**
     * Imposta il valore della proprietà listAgencyName.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setListAgencyName(String value) {
        this.listAgencyName = value;
    }

    /**
     * Recupera il valore della proprietà listName.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getListName() {
        return listName;
    }

    /**
     * Imposta il valore della proprietà listName.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setListName(String value) {
        this.listName = value;
    }

    /**
     * Recupera il valore della proprietà listVersionID.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getListVersionID() {
        return listVersionID;
    }

    /**
     * Imposta il valore della proprietà listVersionID.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setListVersionID(String value) {
        this.listVersionID = value;
    }

    /**
     * Recupera il valore della proprietà name.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getName() {
        return name;
    }

    /**
     * Imposta il valore della proprietà name.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Recupera il valore della proprietà languageID.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getLanguageID() {
        return languageID;
    }

    /**
     * Imposta il valore della proprietà languageID.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setLanguageID(String value) {
        this.languageID = value;
    }

    /**
     * Recupera il valore della proprietà listURI.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getListURI() {
        return listURI;
    }

    /**
     * Imposta il valore della proprietà listURI.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setListURI(String value) {
        this.listURI = value;
    }

    /**
     * Recupera il valore della proprietà listSchemeURI.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getListSchemeURI() {
        return listSchemeURI;
    }

    /**
     * Imposta il valore della proprietà listSchemeURI.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setListSchemeURI(String value) {
        this.listSchemeURI = value;
    }

}
