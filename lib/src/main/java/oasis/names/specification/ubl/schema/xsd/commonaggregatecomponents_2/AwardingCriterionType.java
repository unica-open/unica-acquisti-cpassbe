
package oasis.names.specification.ubl.schema.xsd.commonaggregatecomponents_2;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import oasis.names.specification.ubl.schema.xsd.commonbasiccomponents_2.AwardingCriterionTypeCodeType;
import oasis.names.specification.ubl.schema.xsd.commonbasiccomponents_2.CalculationExpressionCodeType;
import oasis.names.specification.ubl.schema.xsd.commonbasiccomponents_2.CalculationExpressionType;
import oasis.names.specification.ubl.schema.xsd.commonbasiccomponents_2.DescriptionType;
import oasis.names.specification.ubl.schema.xsd.commonbasiccomponents_2.IDType;
import oasis.names.specification.ubl.schema.xsd.commonbasiccomponents_2.MaximumAmountType;
import oasis.names.specification.ubl.schema.xsd.commonbasiccomponents_2.MaximumQuantityType;
import oasis.names.specification.ubl.schema.xsd.commonbasiccomponents_2.MinimumAmountType;
import oasis.names.specification.ubl.schema.xsd.commonbasiccomponents_2.MinimumImprovementBidType;
import oasis.names.specification.ubl.schema.xsd.commonbasiccomponents_2.MinimumQuantityType;
import oasis.names.specification.ubl.schema.xsd.commonbasiccomponents_2.WeightNumericType;
import oasis.names.specification.ubl.schema.xsd.commonbasiccomponents_2.WeightType;


/**
 *
 *
 * <pre>
 * &lt;?xml version="1.0" encoding="UTF-8"?&gt;&lt;ccts:Component xmlns:ccts="urn:un:unece:uncefact:documentation:2" xmlns="urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2" xmlns:cac="urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2" xmlns:cbc="urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2" xmlns:xsd="http://www.w3.org/2001/XMLSchema"&gt;&#13;
 *                &lt;ccts:ComponentType&gt;ABIE&lt;/ccts:ComponentType&gt;&#13;
 *                &lt;ccts:DictionaryEntryName&gt;Awarding Criterion. Details&lt;/ccts:DictionaryEntryName&gt;&#13;
 *                &lt;ccts:Definition&gt;A class to define a criterion from the contracting party that will be taken into account when awarding a contract. An awarding criterion can be objective, when it can be evaluated following a formula, or subjective, when human analysis is required.&lt;/ccts:Definition&gt;&#13;
 *                &lt;ccts:ObjectClass&gt;Awarding Criterion&lt;/ccts:ObjectClass&gt;&#13;
 *             &lt;/ccts:Component&gt;
 * </pre>
 *
 *
 *
 * <p>Classe Java per AwardingCriterionType complex type.
 *
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 *
 * <pre>
 * &lt;complexType name="AwardingCriterionType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element ref="{urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2}ID" minOccurs="0"/&gt;
 *         &lt;element ref="{urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2}AwardingCriterionTypeCode" minOccurs="0"/&gt;
 *         &lt;element ref="{urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2}Description" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element ref="{urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2}WeightNumeric" minOccurs="0"/&gt;
 *         &lt;element ref="{urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2}Weight" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element ref="{urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2}CalculationExpression" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element ref="{urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2}CalculationExpressionCode" minOccurs="0"/&gt;
 *         &lt;element ref="{urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2}MinimumQuantity" minOccurs="0"/&gt;
 *         &lt;element ref="{urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2}MaximumQuantity" minOccurs="0"/&gt;
 *         &lt;element ref="{urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2}MinimumAmount" minOccurs="0"/&gt;
 *         &lt;element ref="{urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2}MaximumAmount" minOccurs="0"/&gt;
 *         &lt;element ref="{urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2}MinimumImprovementBid" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element ref="{urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2}SubordinateAwardingCriterion" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AwardingCriterionType", propOrder = {
    "id",
    "awardingCriterionTypeCode",
    "description",
    "weightNumeric",
    "weight",
    "calculationExpression",
    "calculationExpressionCode",
    "minimumQuantity",
    "maximumQuantity",
    "minimumAmount",
    "maximumAmount",
    "minimumImprovementBid",
    "subordinateAwardingCriterion"
})
public class AwardingCriterionType {

    @XmlElement(name = "ID", namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2")
    protected IDType id;
    @XmlElement(name = "AwardingCriterionTypeCode", namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2")
    protected AwardingCriterionTypeCodeType awardingCriterionTypeCode;
    @XmlElement(name = "Description", namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2")
    protected List<DescriptionType> description;
    @XmlElement(name = "WeightNumeric", namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2")
    protected WeightNumericType weightNumeric;
    @XmlElement(name = "Weight", namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2")
    protected List<WeightType> weight;
    @XmlElement(name = "CalculationExpression", namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2")
    protected List<CalculationExpressionType> calculationExpression;
    @XmlElement(name = "CalculationExpressionCode", namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2")
    protected CalculationExpressionCodeType calculationExpressionCode;
    @XmlElement(name = "MinimumQuantity", namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2")
    protected MinimumQuantityType minimumQuantity;
    @XmlElement(name = "MaximumQuantity", namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2")
    protected MaximumQuantityType maximumQuantity;
    @XmlElement(name = "MinimumAmount", namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2")
    protected MinimumAmountType minimumAmount;
    @XmlElement(name = "MaximumAmount", namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2")
    protected MaximumAmountType maximumAmount;
    @XmlElement(name = "MinimumImprovementBid", namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2")
    protected List<MinimumImprovementBidType> minimumImprovementBid;
    @XmlElement(name = "SubordinateAwardingCriterion")
    protected List<AwardingCriterionType> subordinateAwardingCriterion;

    /**
     *
     *
     * <pre>
     * &lt;?xml version="1.0" encoding="UTF-8"?&gt;&lt;ccts:Component xmlns:ccts="urn:un:unece:uncefact:documentation:2" xmlns="urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2" xmlns:cac="urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2" xmlns:cbc="urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2" xmlns:xsd="http://www.w3.org/2001/XMLSchema"&gt;&#13;
     *                      &lt;ccts:ComponentType&gt;BBIE&lt;/ccts:ComponentType&gt;&#13;
     *                      &lt;ccts:DictionaryEntryName&gt;Awarding Criterion. Identifier&lt;/ccts:DictionaryEntryName&gt;&#13;
     *                      &lt;ccts:Definition&gt;Identifies a specific awarding criterion.&lt;/ccts:Definition&gt;&#13;
     *                      &lt;ccts:Cardinality&gt;0..1&lt;/ccts:Cardinality&gt;&#13;
     *                      &lt;ccts:ObjectClass&gt;Awarding Criterion&lt;/ccts:ObjectClass&gt;&#13;
     *                      &lt;ccts:PropertyTerm&gt;Identifier&lt;/ccts:PropertyTerm&gt;&#13;
     *                      &lt;ccts:RepresentationTerm&gt;Identifier&lt;/ccts:RepresentationTerm&gt;&#13;
     *                      &lt;ccts:DataType&gt;Identifier. Type&lt;/ccts:DataType&gt;&#13;
     *                   &lt;/ccts:Component&gt;
     * </pre>
     *
     *
     *
     * @return
     *     possible object is
     *     {@link IDType }
     *
     */
    public IDType getID() {
        return id;
    }

    /**
     * Imposta il valore della proprietà id.
     *
     * @param value
     *     allowed object is
     *     {@link IDType }
     *
     */
    public void setID(IDType value) {
        this.id = value;
    }

    /**
     *
     *
     * <pre>
     * &lt;?xml version="1.0" encoding="UTF-8"?&gt;&lt;ccts:Component xmlns:ccts="urn:un:unece:uncefact:documentation:2" xmlns="urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2" xmlns:cac="urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2" xmlns:cbc="urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2" xmlns:xsd="http://www.w3.org/2001/XMLSchema"&gt;&#13;
     *                      &lt;ccts:ComponentType&gt;BBIE&lt;/ccts:ComponentType&gt;&#13;
     *                      &lt;ccts:DictionaryEntryName&gt;Awarding Criterion. Awarding Criterion Type Code. Code&lt;/ccts:DictionaryEntryName&gt;&#13;
     *                      &lt;ccts:Definition&gt;A code used to define this awarding criterion. &lt;/ccts:Definition&gt;&#13;
     *                      &lt;ccts:Cardinality&gt;0..1&lt;/ccts:Cardinality&gt;&#13;
     *                      &lt;ccts:ObjectClass&gt;Awarding Criterion&lt;/ccts:ObjectClass&gt;&#13;
     *                      &lt;ccts:PropertyTerm&gt;Awarding Criterion Type Code&lt;/ccts:PropertyTerm&gt;&#13;
     *                      &lt;ccts:RepresentationTerm&gt;Code&lt;/ccts:RepresentationTerm&gt;&#13;
     *                      &lt;ccts:DataType&gt;Code. Type&lt;/ccts:DataType&gt;&#13;
     *                   &lt;/ccts:Component&gt;
     * </pre>
     *
     *
     *
     * @return
     *     possible object is
     *     {@link AwardingCriterionTypeCodeType }
     *
     */
    public AwardingCriterionTypeCodeType getAwardingCriterionTypeCode() {
        return awardingCriterionTypeCode;
    }

    /**
     * Imposta il valore della proprietà awardingCriterionTypeCode.
     *
     * @param value
     *     allowed object is
     *     {@link AwardingCriterionTypeCodeType }
     *
     */
    public void setAwardingCriterionTypeCode(AwardingCriterionTypeCodeType value) {
        this.awardingCriterionTypeCode = value;
    }

    /**
     *
     *
     * <pre>
     * &lt;?xml version="1.0" encoding="UTF-8"?&gt;&lt;ccts:Component xmlns:ccts="urn:un:unece:uncefact:documentation:2" xmlns="urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2" xmlns:cac="urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2" xmlns:cbc="urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2" xmlns:xsd="http://www.w3.org/2001/XMLSchema"&gt;&#13;
     *                      &lt;ccts:ComponentType&gt;BBIE&lt;/ccts:ComponentType&gt;&#13;
     *                      &lt;ccts:DictionaryEntryName&gt;Awarding Criterion. Description. Text&lt;/ccts:DictionaryEntryName&gt;&#13;
     *                      &lt;ccts:Definition&gt;A description of the awarding criterion.&lt;/ccts:Definition&gt;&#13;
     *                      &lt;ccts:Cardinality&gt;0..n&lt;/ccts:Cardinality&gt;&#13;
     *                      &lt;ccts:ObjectClass&gt;Awarding Criterion&lt;/ccts:ObjectClass&gt;&#13;
     *                      &lt;ccts:PropertyTerm&gt;Description&lt;/ccts:PropertyTerm&gt;&#13;
     *                      &lt;ccts:RepresentationTerm&gt;Text&lt;/ccts:RepresentationTerm&gt;&#13;
     *                      &lt;ccts:DataType&gt;Text. Type&lt;/ccts:DataType&gt;&#13;
     *                   &lt;/ccts:Component&gt;
     * </pre>
     *
     *                Gets the value of the description property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the description property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDescription().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DescriptionType }
     *
     *
     */
    public List<DescriptionType> getDescription() {
        if (description == null) {
            description = new ArrayList<>();
        }
        return this.description;
    }

    /**
     *
     *
     * <pre>
     * &lt;?xml version="1.0" encoding="UTF-8"?&gt;&lt;ccts:Component xmlns:ccts="urn:un:unece:uncefact:documentation:2" xmlns="urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2" xmlns:cac="urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2" xmlns:cbc="urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2" xmlns:xsd="http://www.w3.org/2001/XMLSchema"&gt;&#13;
     *                      &lt;ccts:ComponentType&gt;BBIE&lt;/ccts:ComponentType&gt;&#13;
     *                      &lt;ccts:DictionaryEntryName&gt;Awarding Criterion. Weight Numeric. Numeric&lt;/ccts:DictionaryEntryName&gt;&#13;
     *                      &lt;ccts:Definition&gt;A number defining the comparative weighting assigned to this awarding criterion, to enable formulaic evaluation.&lt;/ccts:Definition&gt;&#13;
     *                      &lt;ccts:Cardinality&gt;0..1&lt;/ccts:Cardinality&gt;&#13;
     *                      &lt;ccts:ObjectClass&gt;Awarding Criterion&lt;/ccts:ObjectClass&gt;&#13;
     *                      &lt;ccts:PropertyTerm&gt;Weight Numeric&lt;/ccts:PropertyTerm&gt;&#13;
     *                      &lt;ccts:RepresentationTerm&gt;Numeric&lt;/ccts:RepresentationTerm&gt;&#13;
     *                      &lt;ccts:DataType&gt;Numeric. Type&lt;/ccts:DataType&gt;&#13;
     *                   &lt;/ccts:Component&gt;
     * </pre>
     *
     *
     *
     * @return
     *     possible object is
     *     {@link WeightNumericType }
     *
     */
    public WeightNumericType getWeightNumeric() {
        return weightNumeric;
    }

    /**
     * Imposta il valore della proprietà weightNumeric.
     *
     * @param value
     *     allowed object is
     *     {@link WeightNumericType }
     *
     */
    public void setWeightNumeric(WeightNumericType value) {
        this.weightNumeric = value;
    }

    /**
     *
     *
     * <pre>
     * &lt;?xml version="1.0" encoding="UTF-8"?&gt;&lt;ccts:Component xmlns:ccts="urn:un:unece:uncefact:documentation:2" xmlns="urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2" xmlns:cac="urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2" xmlns:cbc="urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2" xmlns:xsd="http://www.w3.org/2001/XMLSchema"&gt;&#13;
     *                      &lt;ccts:ComponentType&gt;BBIE&lt;/ccts:ComponentType&gt;&#13;
     *                      &lt;ccts:DictionaryEntryName&gt;Awarding Criterion. Weight. Text&lt;/ccts:DictionaryEntryName&gt;&#13;
     *                      &lt;ccts:Definition&gt;A description of the comparative weighting for this awarding criterion.&lt;/ccts:Definition&gt;&#13;
     *                      &lt;ccts:Cardinality&gt;0..n&lt;/ccts:Cardinality&gt;&#13;
     *                      &lt;ccts:ObjectClass&gt;Awarding Criterion&lt;/ccts:ObjectClass&gt;&#13;
     *                      &lt;ccts:PropertyTerm&gt;Weight&lt;/ccts:PropertyTerm&gt;&#13;
     *                      &lt;ccts:RepresentationTerm&gt;Text&lt;/ccts:RepresentationTerm&gt;&#13;
     *                      &lt;ccts:DataType&gt;Text. Type&lt;/ccts:DataType&gt;&#13;
     *                   &lt;/ccts:Component&gt;
     * </pre>
     *
     *                Gets the value of the weight property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the weight property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getWeight().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link WeightType }
     *
     *
     */
    public List<WeightType> getWeight() {
        if (weight == null) {
            weight = new ArrayList<>();
        }
        return this.weight;
    }

    /**
     *
     *
     * <pre>
     * &lt;?xml version="1.0" encoding="UTF-8"?&gt;&lt;ccts:Component xmlns:ccts="urn:un:unece:uncefact:documentation:2" xmlns="urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2" xmlns:cac="urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2" xmlns:cbc="urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2" xmlns:xsd="http://www.w3.org/2001/XMLSchema"&gt;&#13;
     *                      &lt;ccts:ComponentType&gt;BBIE&lt;/ccts:ComponentType&gt;&#13;
     *                      &lt;ccts:DictionaryEntryName&gt;Awarding Criterion. Calculation Expression. Text&lt;/ccts:DictionaryEntryName&gt;&#13;
     *                      &lt;ccts:Definition&gt;The mathematical expression that will be used to evaluate this criterion.&lt;/ccts:Definition&gt;&#13;
     *                      &lt;ccts:Cardinality&gt;0..n&lt;/ccts:Cardinality&gt;&#13;
     *                      &lt;ccts:ObjectClass&gt;Awarding Criterion&lt;/ccts:ObjectClass&gt;&#13;
     *                      &lt;ccts:PropertyTerm&gt;Calculation Expression&lt;/ccts:PropertyTerm&gt;&#13;
     *                      &lt;ccts:RepresentationTerm&gt;Text&lt;/ccts:RepresentationTerm&gt;&#13;
     *                      &lt;ccts:DataType&gt;Text. Type&lt;/ccts:DataType&gt;&#13;
     *                   &lt;/ccts:Component&gt;
     * </pre>
     *
     *                Gets the value of the calculationExpression property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the calculationExpression property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCalculationExpression().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CalculationExpressionType }
     *
     *
     */
    public List<CalculationExpressionType> getCalculationExpression() {
        if (calculationExpression == null) {
            calculationExpression = new ArrayList<>();
        }
        return this.calculationExpression;
    }

    /**
     *
     *
     * <pre>
     * &lt;?xml version="1.0" encoding="UTF-8"?&gt;&lt;ccts:Component xmlns:ccts="urn:un:unece:uncefact:documentation:2" xmlns="urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2" xmlns:cac="urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2" xmlns:cbc="urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2" xmlns:xsd="http://www.w3.org/2001/XMLSchema"&gt;&#13;
     *                      &lt;ccts:ComponentType&gt;BBIE&lt;/ccts:ComponentType&gt;&#13;
     *                      &lt;ccts:DictionaryEntryName&gt;Awarding Criterion. Calculation Expression Code. Code&lt;/ccts:DictionaryEntryName&gt;&#13;
     *                      &lt;ccts:Definition&gt;A code identifying the mathematical expression that will be used to evaluate this criterion.&lt;/ccts:Definition&gt;&#13;
     *                      &lt;ccts:Cardinality&gt;0..1&lt;/ccts:Cardinality&gt;&#13;
     *                      &lt;ccts:ObjectClass&gt;Awarding Criterion&lt;/ccts:ObjectClass&gt;&#13;
     *                      &lt;ccts:PropertyTerm&gt;Calculation Expression Code&lt;/ccts:PropertyTerm&gt;&#13;
     *                      &lt;ccts:RepresentationTerm&gt;Code&lt;/ccts:RepresentationTerm&gt;&#13;
     *                      &lt;ccts:DataType&gt;Code. Type&lt;/ccts:DataType&gt;&#13;
     *                   &lt;/ccts:Component&gt;
     * </pre>
     *
     *
     *
     * @return
     *     possible object is
     *     {@link CalculationExpressionCodeType }
     *
     */
    public CalculationExpressionCodeType getCalculationExpressionCode() {
        return calculationExpressionCode;
    }

    /**
     * Imposta il valore della proprietà calculationExpressionCode.
     *
     * @param value
     *     allowed object is
     *     {@link CalculationExpressionCodeType }
     *
     */
    public void setCalculationExpressionCode(CalculationExpressionCodeType value) {
        this.calculationExpressionCode = value;
    }

    /**
     *
     *
     * <pre>
     * &lt;?xml version="1.0" encoding="UTF-8"?&gt;&lt;ccts:Component xmlns:ccts="urn:un:unece:uncefact:documentation:2" xmlns="urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2" xmlns:cac="urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2" xmlns:cbc="urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2" xmlns:xsd="http://www.w3.org/2001/XMLSchema"&gt;&#13;
     *                      &lt;ccts:ComponentType&gt;BBIE&lt;/ccts:ComponentType&gt;&#13;
     *                      &lt;ccts:DictionaryEntryName&gt;Awarding Criterion. Minimum_ Quantity. Quantity&lt;/ccts:DictionaryEntryName&gt;&#13;
     *                      &lt;ccts:Definition&gt;The minimum quantity for an awarding criterion.&lt;/ccts:Definition&gt;&#13;
     *                      &lt;ccts:Cardinality&gt;0..1&lt;/ccts:Cardinality&gt;&#13;
     *                      &lt;ccts:ObjectClass&gt;Awarding Criterion&lt;/ccts:ObjectClass&gt;&#13;
     *                      &lt;ccts:PropertyTermQualifier&gt;Minimum&lt;/ccts:PropertyTermQualifier&gt;&#13;
     *                      &lt;ccts:PropertyTerm&gt;Quantity&lt;/ccts:PropertyTerm&gt;&#13;
     *                      &lt;ccts:RepresentationTerm&gt;Quantity&lt;/ccts:RepresentationTerm&gt;&#13;
     *                      &lt;ccts:DataType&gt;Quantity. Type&lt;/ccts:DataType&gt;&#13;
     *                   &lt;/ccts:Component&gt;
     * </pre>
     *
     *
     *
     * @return
     *     possible object is
     *     {@link MinimumQuantityType }
     *
     */
    public MinimumQuantityType getMinimumQuantity() {
        return minimumQuantity;
    }

    /**
     * Imposta il valore della proprietà minimumQuantity.
     *
     * @param value
     *     allowed object is
     *     {@link MinimumQuantityType }
     *
     */
    public void setMinimumQuantity(MinimumQuantityType value) {
        this.minimumQuantity = value;
    }

    /**
     *
     *
     * <pre>
     * &lt;?xml version="1.0" encoding="UTF-8"?&gt;&lt;ccts:Component xmlns:ccts="urn:un:unece:uncefact:documentation:2" xmlns="urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2" xmlns:cac="urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2" xmlns:cbc="urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2" xmlns:xsd="http://www.w3.org/2001/XMLSchema"&gt;&#13;
     *                      &lt;ccts:ComponentType&gt;BBIE&lt;/ccts:ComponentType&gt;&#13;
     *                      &lt;ccts:DictionaryEntryName&gt;Awarding Criterion. Maximum_ Quantity. Quantity&lt;/ccts:DictionaryEntryName&gt;&#13;
     *                      &lt;ccts:Definition&gt;The maximum quantity for an awarding criterion.&lt;/ccts:Definition&gt;&#13;
     *                      &lt;ccts:Cardinality&gt;0..1&lt;/ccts:Cardinality&gt;&#13;
     *                      &lt;ccts:ObjectClass&gt;Awarding Criterion&lt;/ccts:ObjectClass&gt;&#13;
     *                      &lt;ccts:PropertyTermQualifier&gt;Maximum&lt;/ccts:PropertyTermQualifier&gt;&#13;
     *                      &lt;ccts:PropertyTerm&gt;Quantity&lt;/ccts:PropertyTerm&gt;&#13;
     *                      &lt;ccts:RepresentationTerm&gt;Quantity&lt;/ccts:RepresentationTerm&gt;&#13;
     *                      &lt;ccts:DataType&gt;Quantity. Type&lt;/ccts:DataType&gt;&#13;
     *                   &lt;/ccts:Component&gt;
     * </pre>
     *
     *
     *
     * @return
     *     possible object is
     *     {@link MaximumQuantityType }
     *
     */
    public MaximumQuantityType getMaximumQuantity() {
        return maximumQuantity;
    }

    /**
     * Imposta il valore della proprietà maximumQuantity.
     *
     * @param value
     *     allowed object is
     *     {@link MaximumQuantityType }
     *
     */
    public void setMaximumQuantity(MaximumQuantityType value) {
        this.maximumQuantity = value;
    }

    /**
     *
     *
     * <pre>
     * &lt;?xml version="1.0" encoding="UTF-8"?&gt;&lt;ccts:Component xmlns:ccts="urn:un:unece:uncefact:documentation:2" xmlns="urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2" xmlns:cac="urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2" xmlns:cbc="urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2" xmlns:xsd="http://www.w3.org/2001/XMLSchema"&gt;&#13;
     *                      &lt;ccts:ComponentType&gt;BBIE&lt;/ccts:ComponentType&gt;&#13;
     *                      &lt;ccts:DictionaryEntryName&gt;Awarding Criterion. Minimum_ Amount. Amount&lt;/ccts:DictionaryEntryName&gt;&#13;
     *                      &lt;ccts:Definition&gt;The minimum monetary amount for an awarding criterion.&lt;/ccts:Definition&gt;&#13;
     *                      &lt;ccts:Cardinality&gt;0..1&lt;/ccts:Cardinality&gt;&#13;
     *                      &lt;ccts:ObjectClass&gt;Awarding Criterion&lt;/ccts:ObjectClass&gt;&#13;
     *                      &lt;ccts:PropertyTermQualifier&gt;Minimum&lt;/ccts:PropertyTermQualifier&gt;&#13;
     *                      &lt;ccts:PropertyTerm&gt;Amount&lt;/ccts:PropertyTerm&gt;&#13;
     *                      &lt;ccts:RepresentationTerm&gt;Amount&lt;/ccts:RepresentationTerm&gt;&#13;
     *                      &lt;ccts:DataType&gt;Amount. Type&lt;/ccts:DataType&gt;&#13;
     *                   &lt;/ccts:Component&gt;
     * </pre>
     *
     *
     *
     * @return
     *     possible object is
     *     {@link MinimumAmountType }
     *
     */
    public MinimumAmountType getMinimumAmount() {
        return minimumAmount;
    }

    /**
     * Imposta il valore della proprietà minimumAmount.
     *
     * @param value
     *     allowed object is
     *     {@link MinimumAmountType }
     *
     */
    public void setMinimumAmount(MinimumAmountType value) {
        this.minimumAmount = value;
    }

    /**
     *
     *
     * <pre>
     * &lt;?xml version="1.0" encoding="UTF-8"?&gt;&lt;ccts:Component xmlns:ccts="urn:un:unece:uncefact:documentation:2" xmlns="urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2" xmlns:cac="urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2" xmlns:cbc="urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2" xmlns:xsd="http://www.w3.org/2001/XMLSchema"&gt;&#13;
     *                      &lt;ccts:ComponentType&gt;BBIE&lt;/ccts:ComponentType&gt;&#13;
     *                      &lt;ccts:DictionaryEntryName&gt;Awarding Criterion. Maximum_ Amount. Amount&lt;/ccts:DictionaryEntryName&gt;&#13;
     *                      &lt;ccts:Definition&gt;The maximum monetary amount for an awarding criterion.&lt;/ccts:Definition&gt;&#13;
     *                      &lt;ccts:Cardinality&gt;0..1&lt;/ccts:Cardinality&gt;&#13;
     *                      &lt;ccts:ObjectClass&gt;Awarding Criterion&lt;/ccts:ObjectClass&gt;&#13;
     *                      &lt;ccts:PropertyTermQualifier&gt;Maximum&lt;/ccts:PropertyTermQualifier&gt;&#13;
     *                      &lt;ccts:PropertyTerm&gt;Amount&lt;/ccts:PropertyTerm&gt;&#13;
     *                      &lt;ccts:RepresentationTerm&gt;Amount&lt;/ccts:RepresentationTerm&gt;&#13;
     *                      &lt;ccts:DataType&gt;Amount. Type&lt;/ccts:DataType&gt;&#13;
     *                   &lt;/ccts:Component&gt;
     * </pre>
     *
     *
     *
     * @return
     *     possible object is
     *     {@link MaximumAmountType }
     *
     */
    public MaximumAmountType getMaximumAmount() {
        return maximumAmount;
    }

    /**
     * Imposta il valore della proprietà maximumAmount.
     *
     * @param value
     *     allowed object is
     *     {@link MaximumAmountType }
     *
     */
    public void setMaximumAmount(MaximumAmountType value) {
        this.maximumAmount = value;
    }

    /**
     *
     *
     * <pre>
     * &lt;?xml version="1.0" encoding="UTF-8"?&gt;&lt;ccts:Component xmlns:ccts="urn:un:unece:uncefact:documentation:2" xmlns="urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2" xmlns:cac="urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2" xmlns:cbc="urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2" xmlns:xsd="http://www.w3.org/2001/XMLSchema"&gt;&#13;
     *                      &lt;ccts:ComponentType&gt;BBIE&lt;/ccts:ComponentType&gt;&#13;
     *                      &lt;ccts:DictionaryEntryName&gt;Awarding Criterion. Minimum Improvement Bid. Text&lt;/ccts:DictionaryEntryName&gt;&#13;
     *                      &lt;ccts:Definition&gt;Describes the minimum improvement bid for this awarding criterion when used in an auction.&lt;/ccts:Definition&gt;&#13;
     *                      &lt;ccts:Cardinality&gt;0..n&lt;/ccts:Cardinality&gt;&#13;
     *                      &lt;ccts:ObjectClass&gt;Awarding Criterion&lt;/ccts:ObjectClass&gt;&#13;
     *                      &lt;ccts:PropertyTerm&gt;Minimum Improvement Bid&lt;/ccts:PropertyTerm&gt;&#13;
     *                      &lt;ccts:RepresentationTerm&gt;Text&lt;/ccts:RepresentationTerm&gt;&#13;
     *                      &lt;ccts:DataType&gt;Text. Type&lt;/ccts:DataType&gt;&#13;
     *                   &lt;/ccts:Component&gt;
     * </pre>
     *
     *                Gets the value of the minimumImprovementBid property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the minimumImprovementBid property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMinimumImprovementBid().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link MinimumImprovementBidType }
     *
     *
     */
    public List<MinimumImprovementBidType> getMinimumImprovementBid() {
        if (minimumImprovementBid == null) {
            minimumImprovementBid = new ArrayList<>();
        }
        return this.minimumImprovementBid;
    }

    /**
     *
     *
     * <pre>
     * &lt;?xml version="1.0" encoding="UTF-8"?&gt;&lt;ccts:Component xmlns:ccts="urn:un:unece:uncefact:documentation:2" xmlns="urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2" xmlns:cac="urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2" xmlns:cbc="urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2" xmlns:xsd="http://www.w3.org/2001/XMLSchema"&gt;&#13;
     *                      &lt;ccts:ComponentType&gt;ASBIE&lt;/ccts:ComponentType&gt;&#13;
     *                      &lt;ccts:DictionaryEntryName&gt;Awarding Criterion. Subordinate_ Awarding Criterion. Awarding Criterion&lt;/ccts:DictionaryEntryName&gt;&#13;
     *                      &lt;ccts:Definition&gt;Defines any subsidiary awarding criterion.&lt;/ccts:Definition&gt;&#13;
     *                      &lt;ccts:Cardinality&gt;0..n&lt;/ccts:Cardinality&gt;&#13;
     *                      &lt;ccts:ObjectClass&gt;Awarding Criterion&lt;/ccts:ObjectClass&gt;&#13;
     *                      &lt;ccts:PropertyTermQualifier&gt;Subordinate&lt;/ccts:PropertyTermQualifier&gt;&#13;
     *                      &lt;ccts:PropertyTerm&gt;Awarding Criterion&lt;/ccts:PropertyTerm&gt;&#13;
     *                      &lt;ccts:AssociatedObjectClass&gt;Awarding Criterion&lt;/ccts:AssociatedObjectClass&gt;&#13;
     *                      &lt;ccts:RepresentationTerm&gt;Awarding Criterion&lt;/ccts:RepresentationTerm&gt;&#13;
     *                   &lt;/ccts:Component&gt;
     * </pre>
     *
     *                Gets the value of the subordinateAwardingCriterion property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the subordinateAwardingCriterion property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSubordinateAwardingCriterion().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link AwardingCriterionType }
     *
     *
     */
    public List<AwardingCriterionType> getSubordinateAwardingCriterion() {
        if (subordinateAwardingCriterion == null) {
            subordinateAwardingCriterion = new ArrayList<>();
        }
        return this.subordinateAwardingCriterion;
    }

}
