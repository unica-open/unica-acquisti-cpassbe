/*-
 * ========================LICENSE_START=================================
 * CPASS BackEnd - INTEGRATION submodule - NotiER
 * %%
 * Copyright (C) 2019 - 2020 CSI Piemonte
 * %%
 * SPDX-FileCopyrightText: Copyright 2019 - 2020 | CSI Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 * =========================LICENSE_END==================================
 */

package oasis.names.specification.ubl.schema.xsd.commonaggregatecomponents_2;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import oasis.names.specification.ubl.schema.xsd.commonbasiccomponents_2.AdditionalAccountIDType;
import oasis.names.specification.ubl.schema.xsd.commonbasiccomponents_2.CustomerAssignedAccountIDType;
import oasis.names.specification.ubl.schema.xsd.commonbasiccomponents_2.DataSendingCapabilityType;


/**
 * 
 *             
 * <pre>
 * &lt;?xml version="1.0" encoding="UTF-8"?&gt;&lt;ccts:Component xmlns:ccts="urn:un:unece:uncefact:documentation:2" xmlns="urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2" xmlns:cac="urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2" xmlns:cbc="urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2" xmlns:xsd="http://www.w3.org/2001/XMLSchema"&gt;&#13;
 *                &lt;ccts:ComponentType&gt;ABIE&lt;/ccts:ComponentType&gt;&#13;
 *                &lt;ccts:DictionaryEntryName&gt;Supplier Party. Details&lt;/ccts:DictionaryEntryName&gt;&#13;
 *                &lt;ccts:Definition&gt;A class to describe a supplier party.&lt;/ccts:Definition&gt;&#13;
 *                &lt;ccts:ObjectClass&gt;Supplier Party&lt;/ccts:ObjectClass&gt;&#13;
 *             &lt;/ccts:Component&gt;
 * </pre>
 * 
 *          
 * 
 * <p>Classe Java per SupplierPartyType complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="SupplierPartyType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element ref="{urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2}CustomerAssignedAccountID" minOccurs="0"/&gt;
 *         &lt;element ref="{urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2}AdditionalAccountID" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element ref="{urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2}DataSendingCapability" minOccurs="0"/&gt;
 *         &lt;element ref="{urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2}Party" minOccurs="0"/&gt;
 *         &lt;element ref="{urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2}DespatchContact" minOccurs="0"/&gt;
 *         &lt;element ref="{urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2}AccountingContact" minOccurs="0"/&gt;
 *         &lt;element ref="{urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2}SellerContact" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SupplierPartyType", propOrder = {
    "customerAssignedAccountID",
    "additionalAccountID",
    "dataSendingCapability",
    "party",
    "despatchContact",
    "accountingContact",
    "sellerContact"
})
public class SupplierPartyType {

    @XmlElement(name = "CustomerAssignedAccountID", namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2")
    protected CustomerAssignedAccountIDType customerAssignedAccountID;
    @XmlElement(name = "AdditionalAccountID", namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2")
    protected List<AdditionalAccountIDType> additionalAccountID;
    @XmlElement(name = "DataSendingCapability", namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2")
    protected DataSendingCapabilityType dataSendingCapability;
    @XmlElement(name = "Party")
    protected PartyType party;
    @XmlElement(name = "DespatchContact")
    protected ContactType despatchContact;
    @XmlElement(name = "AccountingContact")
    protected ContactType accountingContact;
    @XmlElement(name = "SellerContact")
    protected ContactType sellerContact;

    /**
     * 
     *                   
     * <pre>
     * &lt;?xml version="1.0" encoding="UTF-8"?&gt;&lt;ccts:Component xmlns:ccts="urn:un:unece:uncefact:documentation:2" xmlns="urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2" xmlns:cac="urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2" xmlns:cbc="urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2" xmlns:xsd="http://www.w3.org/2001/XMLSchema"&gt;&#13;
     *                      &lt;ccts:ComponentType&gt;BBIE&lt;/ccts:ComponentType&gt;&#13;
     *                      &lt;ccts:DictionaryEntryName&gt;Supplier Party. Customer Assigned_ Account Identifier. Identifier&lt;/ccts:DictionaryEntryName&gt;&#13;
     *                      &lt;ccts:Definition&gt;An identifier for this supplier party, assigned by the customer.&lt;/ccts:Definition&gt;&#13;
     *                      &lt;ccts:Cardinality&gt;0..1&lt;/ccts:Cardinality&gt;&#13;
     *                      &lt;ccts:ObjectClass&gt;Supplier Party&lt;/ccts:ObjectClass&gt;&#13;
     *                      &lt;ccts:PropertyTermQualifier&gt;Customer Assigned&lt;/ccts:PropertyTermQualifier&gt;&#13;
     *                      &lt;ccts:PropertyTerm&gt;Account Identifier&lt;/ccts:PropertyTerm&gt;&#13;
     *                      &lt;ccts:RepresentationTerm&gt;Identifier&lt;/ccts:RepresentationTerm&gt;&#13;
     *                      &lt;ccts:DataType&gt;Identifier. Type&lt;/ccts:DataType&gt;&#13;
     *                   &lt;/ccts:Component&gt;
     * </pre>
     * 
     *                
     * 
     * @return
     *     possible object is
     *     {@link CustomerAssignedAccountIDType }
     *     
     */
    public CustomerAssignedAccountIDType getCustomerAssignedAccountID() {
        return customerAssignedAccountID;
    }

    /**
     * Imposta il valore della proprietà customerAssignedAccountID.
     * 
     * @param value
     *     allowed object is
     *     {@link CustomerAssignedAccountIDType }
     *     
     */
    public void setCustomerAssignedAccountID(CustomerAssignedAccountIDType value) {
        this.customerAssignedAccountID = value;
    }

    /**
     * 
     *                   
     * <pre>
     * &lt;?xml version="1.0" encoding="UTF-8"?&gt;&lt;ccts:Component xmlns:ccts="urn:un:unece:uncefact:documentation:2" xmlns="urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2" xmlns:cac="urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2" xmlns:cbc="urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2" xmlns:xsd="http://www.w3.org/2001/XMLSchema"&gt;&#13;
     *                      &lt;ccts:ComponentType&gt;BBIE&lt;/ccts:ComponentType&gt;&#13;
     *                      &lt;ccts:DictionaryEntryName&gt;Supplier Party. Additional_ Account Identifier. Identifier&lt;/ccts:DictionaryEntryName&gt;&#13;
     *                      &lt;ccts:Definition&gt;An additional identifier for this supplier party.&lt;/ccts:Definition&gt;&#13;
     *                      &lt;ccts:Cardinality&gt;0..n&lt;/ccts:Cardinality&gt;&#13;
     *                      &lt;ccts:ObjectClass&gt;Supplier Party&lt;/ccts:ObjectClass&gt;&#13;
     *                      &lt;ccts:PropertyTermQualifier&gt;Additional&lt;/ccts:PropertyTermQualifier&gt;&#13;
     *                      &lt;ccts:PropertyTerm&gt;Account Identifier&lt;/ccts:PropertyTerm&gt;&#13;
     *                      &lt;ccts:RepresentationTerm&gt;Identifier&lt;/ccts:RepresentationTerm&gt;&#13;
     *                      &lt;ccts:DataType&gt;Identifier. Type&lt;/ccts:DataType&gt;&#13;
     *                   &lt;/ccts:Component&gt;
     * </pre>
     * 
     *                Gets the value of the additionalAccountID property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the additionalAccountID property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAdditionalAccountID().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link AdditionalAccountIDType }
     * 
     * 
     */
    public List<AdditionalAccountIDType> getAdditionalAccountID() {
        if (additionalAccountID == null) {
            additionalAccountID = new ArrayList<AdditionalAccountIDType>();
        }
        return this.additionalAccountID;
    }

    /**
     * 
     *                   
     * <pre>
     * &lt;?xml version="1.0" encoding="UTF-8"?&gt;&lt;ccts:Component xmlns:ccts="urn:un:unece:uncefact:documentation:2" xmlns="urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2" xmlns:cac="urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2" xmlns:cbc="urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2" xmlns:xsd="http://www.w3.org/2001/XMLSchema"&gt;&#13;
     *                      &lt;ccts:ComponentType&gt;BBIE&lt;/ccts:ComponentType&gt;&#13;
     *                      &lt;ccts:DictionaryEntryName&gt;Supplier Party. Data Sending Capability. Text&lt;/ccts:DictionaryEntryName&gt;&#13;
     *                      &lt;ccts:Definition&gt;Text describing the supplier's ability to send invoice data via a purchase card provider (e.g., VISA, MasterCard, American Express).&lt;/ccts:Definition&gt;&#13;
     *                      &lt;ccts:Cardinality&gt;0..1&lt;/ccts:Cardinality&gt;&#13;
     *                      &lt;ccts:ObjectClass&gt;Supplier Party&lt;/ccts:ObjectClass&gt;&#13;
     *                      &lt;ccts:PropertyTerm&gt;Data Sending Capability&lt;/ccts:PropertyTerm&gt;&#13;
     *                      &lt;ccts:RepresentationTerm&gt;Text&lt;/ccts:RepresentationTerm&gt;&#13;
     *                      &lt;ccts:DataType&gt;Text. Type&lt;/ccts:DataType&gt;&#13;
     *                   &lt;/ccts:Component&gt;
     * </pre>
     * 
     *                
     * 
     * @return
     *     possible object is
     *     {@link DataSendingCapabilityType }
     *     
     */
    public DataSendingCapabilityType getDataSendingCapability() {
        return dataSendingCapability;
    }

    /**
     * Imposta il valore della proprietà dataSendingCapability.
     * 
     * @param value
     *     allowed object is
     *     {@link DataSendingCapabilityType }
     *     
     */
    public void setDataSendingCapability(DataSendingCapabilityType value) {
        this.dataSendingCapability = value;
    }

    /**
     * 
     *                   
     * <pre>
     * &lt;?xml version="1.0" encoding="UTF-8"?&gt;&lt;ccts:Component xmlns:ccts="urn:un:unece:uncefact:documentation:2" xmlns="urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2" xmlns:cac="urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2" xmlns:cbc="urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2" xmlns:xsd="http://www.w3.org/2001/XMLSchema"&gt;&#13;
     *                      &lt;ccts:ComponentType&gt;ASBIE&lt;/ccts:ComponentType&gt;&#13;
     *                      &lt;ccts:DictionaryEntryName&gt;Supplier Party. Party&lt;/ccts:DictionaryEntryName&gt;&#13;
     *                      &lt;ccts:Definition&gt;The supplier party itself.&lt;/ccts:Definition&gt;&#13;
     *                      &lt;ccts:Cardinality&gt;0..1&lt;/ccts:Cardinality&gt;&#13;
     *                      &lt;ccts:ObjectClass&gt;Supplier Party&lt;/ccts:ObjectClass&gt;&#13;
     *                      &lt;ccts:PropertyTerm&gt;Party&lt;/ccts:PropertyTerm&gt;&#13;
     *                      &lt;ccts:AssociatedObjectClass&gt;Party&lt;/ccts:AssociatedObjectClass&gt;&#13;
     *                      &lt;ccts:RepresentationTerm&gt;Party&lt;/ccts:RepresentationTerm&gt;&#13;
     *                   &lt;/ccts:Component&gt;
     * </pre>
     * 
     *                
     * 
     * @return
     *     possible object is
     *     {@link PartyType }
     *     
     */
    public PartyType getParty() {
        return party;
    }

    /**
     * Imposta il valore della proprietà party.
     * 
     * @param value
     *     allowed object is
     *     {@link PartyType }
     *     
     */
    public void setParty(PartyType value) {
        this.party = value;
    }

    /**
     * 
     *                   
     * <pre>
     * &lt;?xml version="1.0" encoding="UTF-8"?&gt;&lt;ccts:Component xmlns:ccts="urn:un:unece:uncefact:documentation:2" xmlns="urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2" xmlns:cac="urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2" xmlns:cbc="urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2" xmlns:xsd="http://www.w3.org/2001/XMLSchema"&gt;&#13;
     *                      &lt;ccts:ComponentType&gt;ASBIE&lt;/ccts:ComponentType&gt;&#13;
     *                      &lt;ccts:DictionaryEntryName&gt;Supplier Party. Despatch_ Contact. Contact&lt;/ccts:DictionaryEntryName&gt;&#13;
     *                      &lt;ccts:Definition&gt;A contact at this supplier party for despatches (pickups).&lt;/ccts:Definition&gt;&#13;
     *                      &lt;ccts:Cardinality&gt;0..1&lt;/ccts:Cardinality&gt;&#13;
     *                      &lt;ccts:ObjectClass&gt;Supplier Party&lt;/ccts:ObjectClass&gt;&#13;
     *                      &lt;ccts:PropertyTermQualifier&gt;Despatch&lt;/ccts:PropertyTermQualifier&gt;&#13;
     *                      &lt;ccts:PropertyTerm&gt;Contact&lt;/ccts:PropertyTerm&gt;&#13;
     *                      &lt;ccts:AssociatedObjectClass&gt;Contact&lt;/ccts:AssociatedObjectClass&gt;&#13;
     *                      &lt;ccts:RepresentationTerm&gt;Contact&lt;/ccts:RepresentationTerm&gt;&#13;
     *                   &lt;/ccts:Component&gt;
     * </pre>
     * 
     *                
     * 
     * @return
     *     possible object is
     *     {@link ContactType }
     *     
     */
    public ContactType getDespatchContact() {
        return despatchContact;
    }

    /**
     * Imposta il valore della proprietà despatchContact.
     * 
     * @param value
     *     allowed object is
     *     {@link ContactType }
     *     
     */
    public void setDespatchContact(ContactType value) {
        this.despatchContact = value;
    }

    /**
     * 
     *                   
     * <pre>
     * &lt;?xml version="1.0" encoding="UTF-8"?&gt;&lt;ccts:Component xmlns:ccts="urn:un:unece:uncefact:documentation:2" xmlns="urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2" xmlns:cac="urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2" xmlns:cbc="urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2" xmlns:xsd="http://www.w3.org/2001/XMLSchema"&gt;&#13;
     *                      &lt;ccts:ComponentType&gt;ASBIE&lt;/ccts:ComponentType&gt;&#13;
     *                      &lt;ccts:DictionaryEntryName&gt;Supplier Party. Accounting_ Contact. Contact&lt;/ccts:DictionaryEntryName&gt;&#13;
     *                      &lt;ccts:Definition&gt;A contact at this supplier party for accounting.&lt;/ccts:Definition&gt;&#13;
     *                      &lt;ccts:Cardinality&gt;0..1&lt;/ccts:Cardinality&gt;&#13;
     *                      &lt;ccts:ObjectClass&gt;Supplier Party&lt;/ccts:ObjectClass&gt;&#13;
     *                      &lt;ccts:PropertyTermQualifier&gt;Accounting&lt;/ccts:PropertyTermQualifier&gt;&#13;
     *                      &lt;ccts:PropertyTerm&gt;Contact&lt;/ccts:PropertyTerm&gt;&#13;
     *                      &lt;ccts:AssociatedObjectClass&gt;Contact&lt;/ccts:AssociatedObjectClass&gt;&#13;
     *                      &lt;ccts:RepresentationTerm&gt;Contact&lt;/ccts:RepresentationTerm&gt;&#13;
     *                   &lt;/ccts:Component&gt;
     * </pre>
     * 
     *                
     * 
     * @return
     *     possible object is
     *     {@link ContactType }
     *     
     */
    public ContactType getAccountingContact() {
        return accountingContact;
    }

    /**
     * Imposta il valore della proprietà accountingContact.
     * 
     * @param value
     *     allowed object is
     *     {@link ContactType }
     *     
     */
    public void setAccountingContact(ContactType value) {
        this.accountingContact = value;
    }

    /**
     * 
     *                   
     * <pre>
     * &lt;?xml version="1.0" encoding="UTF-8"?&gt;&lt;ccts:Component xmlns:ccts="urn:un:unece:uncefact:documentation:2" xmlns="urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2" xmlns:cac="urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2" xmlns:cbc="urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2" xmlns:xsd="http://www.w3.org/2001/XMLSchema"&gt;&#13;
     *                      &lt;ccts:ComponentType&gt;ASBIE&lt;/ccts:ComponentType&gt;&#13;
     *                      &lt;ccts:DictionaryEntryName&gt;Supplier Party. Seller_ Contact. Contact&lt;/ccts:DictionaryEntryName&gt;&#13;
     *                      &lt;ccts:Definition&gt;The primary contact for this supplier party.&lt;/ccts:Definition&gt;&#13;
     *                      &lt;ccts:Cardinality&gt;0..1&lt;/ccts:Cardinality&gt;&#13;
     *                      &lt;ccts:ObjectClass&gt;Supplier Party&lt;/ccts:ObjectClass&gt;&#13;
     *                      &lt;ccts:PropertyTermQualifier&gt;Seller&lt;/ccts:PropertyTermQualifier&gt;&#13;
     *                      &lt;ccts:PropertyTerm&gt;Contact&lt;/ccts:PropertyTerm&gt;&#13;
     *                      &lt;ccts:AssociatedObjectClass&gt;Contact&lt;/ccts:AssociatedObjectClass&gt;&#13;
     *                      &lt;ccts:RepresentationTerm&gt;Contact&lt;/ccts:RepresentationTerm&gt;&#13;
     *                   &lt;/ccts:Component&gt;
     * </pre>
     * 
     *                
     * 
     * @return
     *     possible object is
     *     {@link ContactType }
     *     
     */
    public ContactType getSellerContact() {
        return sellerContact;
    }

    /**
     * Imposta il valore della proprietà sellerContact.
     * 
     * @param value
     *     allowed object is
     *     {@link ContactType }
     *     
     */
    public void setSellerContact(ContactType value) {
        this.sellerContact = value;
    }

}
