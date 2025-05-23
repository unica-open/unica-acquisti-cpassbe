
package oasis.names.specification.ubl.schema.xsd.commonaggregatecomponents_2;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import oasis.names.specification.ubl.schema.xsd.commonbasiccomponents_2.ExpectedOperatorQuantityType;
import oasis.names.specification.ubl.schema.xsd.commonbasiccomponents_2.FrequencyType;
import oasis.names.specification.ubl.schema.xsd.commonbasiccomponents_2.JustificationType;
import oasis.names.specification.ubl.schema.xsd.commonbasiccomponents_2.MaximumOperatorQuantityType;


/**
 *
 *
 * <pre>
 * &lt;?xml version="1.0" encoding="UTF-8"?&gt;&lt;ccts:Component xmlns:ccts="urn:un:unece:uncefact:documentation:2" xmlns="urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2" xmlns:cac="urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2" xmlns:cbc="urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2" xmlns:xsd="http://www.w3.org/2001/XMLSchema"&gt;&#13;
 *                &lt;ccts:ComponentType&gt;ABIE&lt;/ccts:ComponentType&gt;&#13;
 *                &lt;ccts:DictionaryEntryName&gt;Framework Agreement. Details&lt;/ccts:DictionaryEntryName&gt;&#13;
 *                &lt;ccts:Definition&gt;A class to describe a tendering framework agreement.&lt;/ccts:Definition&gt;&#13;
 *                &lt;ccts:ObjectClass&gt;Framework Agreement&lt;/ccts:ObjectClass&gt;&#13;
 *             &lt;/ccts:Component&gt;
 * </pre>
 *
 *
 *
 * <p>Classe Java per FrameworkAgreementType complex type.
 *
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 *
 * <pre>
 * &lt;complexType name="FrameworkAgreementType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element ref="{urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2}ExpectedOperatorQuantity" minOccurs="0"/&gt;
 *         &lt;element ref="{urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2}MaximumOperatorQuantity" minOccurs="0"/&gt;
 *         &lt;element ref="{urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2}Justification" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element ref="{urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2}Frequency" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element ref="{urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2}DurationPeriod" minOccurs="0"/&gt;
 *         &lt;element ref="{urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2}SubsequentProcessTenderRequirement" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FrameworkAgreementType", propOrder = {
    "expectedOperatorQuantity",
    "maximumOperatorQuantity",
    "justification",
    "frequency",
    "durationPeriod",
    "subsequentProcessTenderRequirement"
})
public class FrameworkAgreementType {

    @XmlElement(name = "ExpectedOperatorQuantity", namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2")
    protected ExpectedOperatorQuantityType expectedOperatorQuantity;
    @XmlElement(name = "MaximumOperatorQuantity", namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2")
    protected MaximumOperatorQuantityType maximumOperatorQuantity;
    @XmlElement(name = "Justification", namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2")
    protected List<JustificationType> justification;
    @XmlElement(name = "Frequency", namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2")
    protected List<FrequencyType> frequency;
    @XmlElement(name = "DurationPeriod")
    protected PeriodType durationPeriod;
    @XmlElement(name = "SubsequentProcessTenderRequirement")
    protected List<TenderRequirementType> subsequentProcessTenderRequirement;

    /**
     *
     *
     * <pre>
     * &lt;?xml version="1.0" encoding="UTF-8"?&gt;&lt;ccts:Component xmlns:ccts="urn:un:unece:uncefact:documentation:2" xmlns="urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2" xmlns:cac="urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2" xmlns:cbc="urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2" xmlns:xsd="http://www.w3.org/2001/XMLSchema"&gt;&#13;
     *                      &lt;ccts:ComponentType&gt;BBIE&lt;/ccts:ComponentType&gt;&#13;
     *                      &lt;ccts:DictionaryEntryName&gt;Framework Agreement. Expected_ Operator. Quantity&lt;/ccts:DictionaryEntryName&gt;&#13;
     *                      &lt;ccts:Definition&gt;The number of economic operators expected to participate in this framework agreement.&lt;/ccts:Definition&gt;&#13;
     *                      &lt;ccts:Cardinality&gt;0..1&lt;/ccts:Cardinality&gt;&#13;
     *                      &lt;ccts:ObjectClass&gt;Framework Agreement&lt;/ccts:ObjectClass&gt;&#13;
     *                      &lt;ccts:PropertyTermQualifier&gt;Expected&lt;/ccts:PropertyTermQualifier&gt;&#13;
     *                      &lt;ccts:PropertyTerm&gt;Operator&lt;/ccts:PropertyTerm&gt;&#13;
     *                      &lt;ccts:RepresentationTerm&gt;Quantity&lt;/ccts:RepresentationTerm&gt;&#13;
     *                      &lt;ccts:DataType&gt;Quantity. Type&lt;/ccts:DataType&gt;&#13;
     *                   &lt;/ccts:Component&gt;
     * </pre>
     *
     *
     *
     * @return
     *     possible object is
     *     {@link ExpectedOperatorQuantityType }
     *
     */
    public ExpectedOperatorQuantityType getExpectedOperatorQuantity() {
        return expectedOperatorQuantity;
    }

    /**
     * Imposta il valore della proprietà expectedOperatorQuantity.
     *
     * @param value
     *     allowed object is
     *     {@link ExpectedOperatorQuantityType }
     *
     */
    public void setExpectedOperatorQuantity(ExpectedOperatorQuantityType value) {
        this.expectedOperatorQuantity = value;
    }

    /**
     *
     *
     * <pre>
     * &lt;?xml version="1.0" encoding="UTF-8"?&gt;&lt;ccts:Component xmlns:ccts="urn:un:unece:uncefact:documentation:2" xmlns="urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2" xmlns:cac="urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2" xmlns:cbc="urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2" xmlns:xsd="http://www.w3.org/2001/XMLSchema"&gt;&#13;
     *                      &lt;ccts:ComponentType&gt;BBIE&lt;/ccts:ComponentType&gt;&#13;
     *                      &lt;ccts:DictionaryEntryName&gt;Framework Agreement. Maximum_ Operator. Quantity&lt;/ccts:DictionaryEntryName&gt;&#13;
     *                      &lt;ccts:Definition&gt;The maximum number of economic operators allowed to participate in this framework agreement.&lt;/ccts:Definition&gt;&#13;
     *                      &lt;ccts:Cardinality&gt;0..1&lt;/ccts:Cardinality&gt;&#13;
     *                      &lt;ccts:ObjectClass&gt;Framework Agreement&lt;/ccts:ObjectClass&gt;&#13;
     *                      &lt;ccts:PropertyTermQualifier&gt;Maximum&lt;/ccts:PropertyTermQualifier&gt;&#13;
     *                      &lt;ccts:PropertyTerm&gt;Operator&lt;/ccts:PropertyTerm&gt;&#13;
     *                      &lt;ccts:RepresentationTerm&gt;Quantity&lt;/ccts:RepresentationTerm&gt;&#13;
     *                      &lt;ccts:DataType&gt;Quantity. Type&lt;/ccts:DataType&gt;&#13;
     *                   &lt;/ccts:Component&gt;
     * </pre>
     *
     *
     *
     * @return
     *     possible object is
     *     {@link MaximumOperatorQuantityType }
     *
     */
    public MaximumOperatorQuantityType getMaximumOperatorQuantity() {
        return maximumOperatorQuantity;
    }

    /**
     * Imposta il valore della proprietà maximumOperatorQuantity.
     *
     * @param value
     *     allowed object is
     *     {@link MaximumOperatorQuantityType }
     *
     */
    public void setMaximumOperatorQuantity(MaximumOperatorQuantityType value) {
        this.maximumOperatorQuantity = value;
    }

    /**
     *
     *
     * <pre>
     * &lt;?xml version="1.0" encoding="UTF-8"?&gt;&lt;ccts:Component xmlns:ccts="urn:un:unece:uncefact:documentation:2" xmlns="urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2" xmlns:cac="urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2" xmlns:cbc="urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2" xmlns:xsd="http://www.w3.org/2001/XMLSchema"&gt;&#13;
     *                      &lt;ccts:ComponentType&gt;BBIE&lt;/ccts:ComponentType&gt;&#13;
     *                      &lt;ccts:DictionaryEntryName&gt;Framework Agreement. Justification. Text&lt;/ccts:DictionaryEntryName&gt;&#13;
     *                      &lt;ccts:Definition&gt;Text describing the justification for this framework agreement.&lt;/ccts:Definition&gt;&#13;
     *                      &lt;ccts:Cardinality&gt;0..n&lt;/ccts:Cardinality&gt;&#13;
     *                      &lt;ccts:ObjectClass&gt;Framework Agreement&lt;/ccts:ObjectClass&gt;&#13;
     *                      &lt;ccts:PropertyTerm&gt;Justification&lt;/ccts:PropertyTerm&gt;&#13;
     *                      &lt;ccts:RepresentationTerm&gt;Text&lt;/ccts:RepresentationTerm&gt;&#13;
     *                      &lt;ccts:DataType&gt;Text. Type&lt;/ccts:DataType&gt;&#13;
     *                   &lt;/ccts:Component&gt;
     * </pre>
     *
     *                Gets the value of the justification property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the justification property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getJustification().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link JustificationType }
     *
     *
     */
    public List<JustificationType> getJustification() {
        if (justification == null) {
            justification = new ArrayList<>();
        }
        return this.justification;
    }

    /**
     *
     *
     * <pre>
     * &lt;?xml version="1.0" encoding="UTF-8"?&gt;&lt;ccts:Component xmlns:ccts="urn:un:unece:uncefact:documentation:2" xmlns="urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2" xmlns:cac="urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2" xmlns:cbc="urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2" xmlns:xsd="http://www.w3.org/2001/XMLSchema"&gt;&#13;
     *                      &lt;ccts:ComponentType&gt;BBIE&lt;/ccts:ComponentType&gt;&#13;
     *                      &lt;ccts:DictionaryEntryName&gt;Framework Agreement. Frequency. Text&lt;/ccts:DictionaryEntryName&gt;&#13;
     *                      &lt;ccts:Definition&gt;Text describing the frequency with which subsequent contracts will be awarded.&lt;/ccts:Definition&gt;&#13;
     *                      &lt;ccts:Cardinality&gt;0..n&lt;/ccts:Cardinality&gt;&#13;
     *                      &lt;ccts:ObjectClass&gt;Framework Agreement&lt;/ccts:ObjectClass&gt;&#13;
     *                      &lt;ccts:PropertyTerm&gt;Frequency&lt;/ccts:PropertyTerm&gt;&#13;
     *                      &lt;ccts:RepresentationTerm&gt;Text&lt;/ccts:RepresentationTerm&gt;&#13;
     *                      &lt;ccts:DataType&gt;Text. Type&lt;/ccts:DataType&gt;&#13;
     *                   &lt;/ccts:Component&gt;
     * </pre>
     *
     *                Gets the value of the frequency property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the frequency property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getFrequency().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link FrequencyType }
     *
     *
     */
    public List<FrequencyType> getFrequency() {
        if (frequency == null) {
            frequency = new ArrayList<>();
        }
        return this.frequency;
    }

    /**
     *
     *
     * <pre>
     * &lt;?xml version="1.0" encoding="UTF-8"?&gt;&lt;ccts:Component xmlns:ccts="urn:un:unece:uncefact:documentation:2" xmlns="urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2" xmlns:cac="urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2" xmlns:cbc="urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2" xmlns:xsd="http://www.w3.org/2001/XMLSchema"&gt;&#13;
     *                      &lt;ccts:ComponentType&gt;ASBIE&lt;/ccts:ComponentType&gt;&#13;
     *                      &lt;ccts:DictionaryEntryName&gt;Framework Agreement. Duration_ Period. Period&lt;/ccts:DictionaryEntryName&gt;&#13;
     *                      &lt;ccts:Definition&gt;The period during which this framework agreement applies.&lt;/ccts:Definition&gt;&#13;
     *                      &lt;ccts:Cardinality&gt;0..1&lt;/ccts:Cardinality&gt;&#13;
     *                      &lt;ccts:ObjectClass&gt;Framework Agreement&lt;/ccts:ObjectClass&gt;&#13;
     *                      &lt;ccts:PropertyTermQualifier&gt;Duration&lt;/ccts:PropertyTermQualifier&gt;&#13;
     *                      &lt;ccts:PropertyTerm&gt;Period&lt;/ccts:PropertyTerm&gt;&#13;
     *                      &lt;ccts:AssociatedObjectClass&gt;Period&lt;/ccts:AssociatedObjectClass&gt;&#13;
     *                      &lt;ccts:RepresentationTerm&gt;Period&lt;/ccts:RepresentationTerm&gt;&#13;
     *                   &lt;/ccts:Component&gt;
     * </pre>
     *
     *
     *
     * @return
     *     possible object is
     *     {@link PeriodType }
     *
     */
    public PeriodType getDurationPeriod() {
        return durationPeriod;
    }

    /**
     * Imposta il valore della proprietà durationPeriod.
     *
     * @param value
     *     allowed object is
     *     {@link PeriodType }
     *
     */
    public void setDurationPeriod(PeriodType value) {
        this.durationPeriod = value;
    }

    /**
     *
     *
     * <pre>
     * &lt;?xml version="1.0" encoding="UTF-8"?&gt;&lt;ccts:Component xmlns:ccts="urn:un:unece:uncefact:documentation:2" xmlns="urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2" xmlns:cac="urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2" xmlns:cbc="urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2" xmlns:xsd="http://www.w3.org/2001/XMLSchema"&gt;&#13;
     *                      &lt;ccts:ComponentType&gt;ASBIE&lt;/ccts:ComponentType&gt;&#13;
     *                      &lt;ccts:DictionaryEntryName&gt;Framework Agreement. Subsequent Process_ Tender Requirement. Tender Requirement&lt;/ccts:DictionaryEntryName&gt;&#13;
     *                      &lt;ccts:Definition&gt;A tender requirement intended for consumption by downstream tendering processes derived from the establishment of this framework agreement.&lt;/ccts:Definition&gt;&#13;
     *                      &lt;ccts:Cardinality&gt;0..n&lt;/ccts:Cardinality&gt;&#13;
     *                      &lt;ccts:ObjectClass&gt;Framework Agreement&lt;/ccts:ObjectClass&gt;&#13;
     *                      &lt;ccts:PropertyTermQualifier&gt;Subsequent Process&lt;/ccts:PropertyTermQualifier&gt;&#13;
     *                      &lt;ccts:PropertyTerm&gt;Tender Requirement&lt;/ccts:PropertyTerm&gt;&#13;
     *                      &lt;ccts:AssociatedObjectClass&gt;Tender Requirement&lt;/ccts:AssociatedObjectClass&gt;&#13;
     *                      &lt;ccts:RepresentationTerm&gt;Tender Requirement&lt;/ccts:RepresentationTerm&gt;&#13;
     *                      &lt;ccts:Examples&gt;Curricula required&lt;/ccts:Examples&gt;&#13;
     *                   &lt;/ccts:Component&gt;
     * </pre>
     *
     *                Gets the value of the subsequentProcessTenderRequirement property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the subsequentProcessTenderRequirement property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSubsequentProcessTenderRequirement().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TenderRequirementType }
     *
     *
     */
    public List<TenderRequirementType> getSubsequentProcessTenderRequirement() {
        if (subsequentProcessTenderRequirement == null) {
            subsequentProcessTenderRequirement = new ArrayList<>();
        }
        return this.subsequentProcessTenderRequirement;
    }

}
