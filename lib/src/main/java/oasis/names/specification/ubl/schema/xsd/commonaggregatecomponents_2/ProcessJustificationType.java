
package oasis.names.specification.ubl.schema.xsd.commonaggregatecomponents_2;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import oasis.names.specification.ubl.schema.xsd.commonbasiccomponents_2.DescriptionType;
import oasis.names.specification.ubl.schema.xsd.commonbasiccomponents_2.PreviousCancellationReasonCodeType;
import oasis.names.specification.ubl.schema.xsd.commonbasiccomponents_2.ProcessReasonCodeType;
import oasis.names.specification.ubl.schema.xsd.commonbasiccomponents_2.ProcessReasonType;


/**
 *
 *
 * <pre>
 * &lt;?xml version="1.0" encoding="UTF-8"?&gt;&lt;ccts:Component xmlns:ccts="urn:un:unece:uncefact:documentation:2" xmlns="urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2" xmlns:cac="urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2" xmlns:cbc="urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2" xmlns:xsd="http://www.w3.org/2001/XMLSchema"&gt;&#13;
 *                &lt;ccts:ComponentType&gt;ABIE&lt;/ccts:ComponentType&gt;&#13;
 *                &lt;ccts:DictionaryEntryName&gt;Process Justification. Details&lt;/ccts:DictionaryEntryName&gt;&#13;
 *                &lt;ccts:Definition&gt;A class to describe a justification for the choice of tendering process.&lt;/ccts:Definition&gt;&#13;
 *                &lt;ccts:ObjectClass&gt;Process Justification&lt;/ccts:ObjectClass&gt;&#13;
 *             &lt;/ccts:Component&gt;
 * </pre>
 *
 *
 *
 * <p>Classe Java per ProcessJustificationType complex type.
 *
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 *
 * <pre>
 * &lt;complexType name="ProcessJustificationType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element ref="{urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2}PreviousCancellationReasonCode" minOccurs="0"/&gt;
 *         &lt;element ref="{urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2}ProcessReasonCode" minOccurs="0"/&gt;
 *         &lt;element ref="{urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2}ProcessReason" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element ref="{urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2}Description" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ProcessJustificationType", propOrder = {
    "previousCancellationReasonCode",
    "processReasonCode",
    "processReason",
    "description"
})
public class ProcessJustificationType {

    @XmlElement(name = "PreviousCancellationReasonCode", namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2")
    protected PreviousCancellationReasonCodeType previousCancellationReasonCode;
    @XmlElement(name = "ProcessReasonCode", namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2")
    protected ProcessReasonCodeType processReasonCode;
    @XmlElement(name = "ProcessReason", namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2")
    protected List<ProcessReasonType> processReason;
    @XmlElement(name = "Description", namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2")
    protected List<DescriptionType> description;

    /**
     *
     *
     * <pre>
     * &lt;?xml version="1.0" encoding="UTF-8"?&gt;&lt;ccts:Component xmlns:ccts="urn:un:unece:uncefact:documentation:2" xmlns="urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2" xmlns:cac="urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2" xmlns:cbc="urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2" xmlns:xsd="http://www.w3.org/2001/XMLSchema"&gt;&#13;
     *                      &lt;ccts:ComponentType&gt;BBIE&lt;/ccts:ComponentType&gt;&#13;
     *                      &lt;ccts:DictionaryEntryName&gt;Process Justification. Previous_ Cancellation Reason Code. Code&lt;/ccts:DictionaryEntryName&gt;&#13;
     *                      &lt;ccts:Definition&gt;A code signifying the type of the previous tendering process (which is now being cancelled).&lt;/ccts:Definition&gt;&#13;
     *                      &lt;ccts:Cardinality&gt;0..1&lt;/ccts:Cardinality&gt;&#13;
     *                      &lt;ccts:ObjectClass&gt;Process Justification&lt;/ccts:ObjectClass&gt;&#13;
     *                      &lt;ccts:PropertyTermQualifier&gt;Previous&lt;/ccts:PropertyTermQualifier&gt;&#13;
     *                      &lt;ccts:PropertyTerm&gt;Cancellation Reason Code&lt;/ccts:PropertyTerm&gt;&#13;
     *                      &lt;ccts:RepresentationTerm&gt;Code&lt;/ccts:RepresentationTerm&gt;&#13;
     *                      &lt;ccts:DataType&gt;Code. Type&lt;/ccts:DataType&gt;&#13;
     *                   &lt;/ccts:Component&gt;
     * </pre>
     *
     *
     *
     * @return
     *     possible object is
     *     {@link PreviousCancellationReasonCodeType }
     *
     */
    public PreviousCancellationReasonCodeType getPreviousCancellationReasonCode() {
        return previousCancellationReasonCode;
    }

    /**
     * Imposta il valore della proprietà previousCancellationReasonCode.
     *
     * @param value
     *     allowed object is
     *     {@link PreviousCancellationReasonCodeType }
     *
     */
    public void setPreviousCancellationReasonCode(PreviousCancellationReasonCodeType value) {
        this.previousCancellationReasonCode = value;
    }

    /**
     *
     *
     * <pre>
     * &lt;?xml version="1.0" encoding="UTF-8"?&gt;&lt;ccts:Component xmlns:ccts="urn:un:unece:uncefact:documentation:2" xmlns="urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2" xmlns:cac="urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2" xmlns:cbc="urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2" xmlns:xsd="http://www.w3.org/2001/XMLSchema"&gt;&#13;
     *                      &lt;ccts:ComponentType&gt;BBIE&lt;/ccts:ComponentType&gt;&#13;
     *                      &lt;ccts:DictionaryEntryName&gt;Process Justification. Process_ Reason Code. Code&lt;/ccts:DictionaryEntryName&gt;&#13;
     *                      &lt;ccts:Definition&gt;The reason why the contracting authority has followed a particular tendering procedure for the awarding of a contract, expressed as a code.&lt;/ccts:Definition&gt;&#13;
     *                      &lt;ccts:Cardinality&gt;0..1&lt;/ccts:Cardinality&gt;&#13;
     *                      &lt;ccts:ObjectClass&gt;Process Justification&lt;/ccts:ObjectClass&gt;&#13;
     *                      &lt;ccts:PropertyTermQualifier&gt;Process&lt;/ccts:PropertyTermQualifier&gt;&#13;
     *                      &lt;ccts:PropertyTerm&gt;Reason Code&lt;/ccts:PropertyTerm&gt;&#13;
     *                      &lt;ccts:RepresentationTerm&gt;Code&lt;/ccts:RepresentationTerm&gt;&#13;
     *                      &lt;ccts:DataType&gt;Code. Type&lt;/ccts:DataType&gt;&#13;
     *                   &lt;/ccts:Component&gt;
     * </pre>
     *
     *
     *
     * @return
     *     possible object is
     *     {@link ProcessReasonCodeType }
     *
     */
    public ProcessReasonCodeType getProcessReasonCode() {
        return processReasonCode;
    }

    /**
     * Imposta il valore della proprietà processReasonCode.
     *
     * @param value
     *     allowed object is
     *     {@link ProcessReasonCodeType }
     *
     */
    public void setProcessReasonCode(ProcessReasonCodeType value) {
        this.processReasonCode = value;
    }

    /**
     *
     *
     * <pre>
     * &lt;?xml version="1.0" encoding="UTF-8"?&gt;&lt;ccts:Component xmlns:ccts="urn:un:unece:uncefact:documentation:2" xmlns="urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2" xmlns:cac="urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2" xmlns:cbc="urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2" xmlns:xsd="http://www.w3.org/2001/XMLSchema"&gt;&#13;
     *                      &lt;ccts:ComponentType&gt;BBIE&lt;/ccts:ComponentType&gt;&#13;
     *                      &lt;ccts:DictionaryEntryName&gt;Process Justification. Process_ Reason. Text&lt;/ccts:DictionaryEntryName&gt;&#13;
     *                      &lt;ccts:Definition&gt;The reason why the contracting authority has followed a particular tendering procedure for the awarding of a contract, expressed as text.&lt;/ccts:Definition&gt;&#13;
     *                      &lt;ccts:Cardinality&gt;0..n&lt;/ccts:Cardinality&gt;&#13;
     *                      &lt;ccts:ObjectClass&gt;Process Justification&lt;/ccts:ObjectClass&gt;&#13;
     *                      &lt;ccts:PropertyTermQualifier&gt;Process&lt;/ccts:PropertyTermQualifier&gt;&#13;
     *                      &lt;ccts:PropertyTerm&gt;Reason&lt;/ccts:PropertyTerm&gt;&#13;
     *                      &lt;ccts:RepresentationTerm&gt;Text&lt;/ccts:RepresentationTerm&gt;&#13;
     *                      &lt;ccts:DataType&gt;Text. Type&lt;/ccts:DataType&gt;&#13;
     *                   &lt;/ccts:Component&gt;
     * </pre>
     *
     *                Gets the value of the processReason property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the processReason property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getProcessReason().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ProcessReasonType }
     *
     *
     */
    public List<ProcessReasonType> getProcessReason() {
        if (processReason == null) {
            processReason = new ArrayList<>();
        }
        return this.processReason;
    }

    /**
     *
     *
     * <pre>
     * &lt;?xml version="1.0" encoding="UTF-8"?&gt;&lt;ccts:Component xmlns:ccts="urn:un:unece:uncefact:documentation:2" xmlns="urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2" xmlns:cac="urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2" xmlns:cbc="urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2" xmlns:xsd="http://www.w3.org/2001/XMLSchema"&gt;&#13;
     *                      &lt;ccts:ComponentType&gt;BBIE&lt;/ccts:ComponentType&gt;&#13;
     *                      &lt;ccts:DictionaryEntryName&gt;Process Justification. Description. Text&lt;/ccts:DictionaryEntryName&gt;&#13;
     *                      &lt;ccts:Definition&gt;Text providing justification for the selection of this process.&lt;/ccts:Definition&gt;&#13;
     *                      &lt;ccts:Cardinality&gt;0..n&lt;/ccts:Cardinality&gt;&#13;
     *                      &lt;ccts:ObjectClass&gt;Process Justification&lt;/ccts:ObjectClass&gt;&#13;
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

}
