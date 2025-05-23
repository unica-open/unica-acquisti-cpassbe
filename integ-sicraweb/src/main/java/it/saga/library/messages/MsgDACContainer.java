/*-
 * ========================LICENSE_START=================================
 * CPASS BackEnd - INTEGRATION submodule - SICRAWEB
 * %%
 * Copyright (C) 2019 - 2023 CSI Piemonte
 * %%
 * SPDX-FileCopyrightText: Copyright 2019 - 2023 | CSI Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 * =========================LICENSE_END==================================
 */

package it.saga.library.messages;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import fdewsappjdocumentigateway.ArrayOfXsdAnyType;


/**
 * &lt;p&gt;Classe Java per MsgDACContainer complex type.
 * 
 * &lt;p&gt;Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * &lt;pre&gt;
 * &amp;lt;complexType name="MsgDACContainer"&amp;gt;
 *   &amp;lt;complexContent&amp;gt;
 *     &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&amp;gt;
 *       &amp;lt;sequence&amp;gt;
 *         &amp;lt;element name="error" type="{http://www.w3.org/2001/XMLSchema}boolean"/&amp;gt;
 *         &amp;lt;element name="ignore" type="{http://www.w3.org/2001/XMLSchema}boolean"/&amp;gt;
 *         &amp;lt;element name="information" type="{http://www.w3.org/2001/XMLSchema}boolean"/&amp;gt;
 *         &amp;lt;element name="messageContainer" type="{http://messages.library.saga.it}MsgDACContainer"/&amp;gt;
 *         &amp;lt;element name="messages" type="{urn:FdeWSAppjDocumentiGateway}ArrayOf_xsd_anyType"/&amp;gt;
 *         &amp;lt;element name="messagesCount" type="{http://www.w3.org/2001/XMLSchema}int"/&amp;gt;
 *         &amp;lt;element name="messagesSorted" type="{urn:FdeWSAppjDocumentiGateway}ArrayOf_xsd_anyType"/&amp;gt;
 *         &amp;lt;element name="numberMessages" type="{http://www.w3.org/2001/XMLSchema}int"/&amp;gt;
 *         &amp;lt;element name="originatorException" type="{http://messages.library.saga.it}SagaException"/&amp;gt;
 *         &amp;lt;element name="sagaException" type="{http://messages.library.saga.it}SagaException"/&amp;gt;
 *         &amp;lt;element name="type" type="{http://www.w3.org/2001/XMLSchema}int"/&amp;gt;
 *         &amp;lt;element name="viewed" type="{http://www.w3.org/2001/XMLSchema}boolean"/&amp;gt;
 *         &amp;lt;element name="warning" type="{http://www.w3.org/2001/XMLSchema}boolean"/&amp;gt;
 *       &amp;lt;/sequence&amp;gt;
 *     &amp;lt;/restriction&amp;gt;
 *   &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &lt;/pre&gt;
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MsgDACContainer", propOrder = {
    "error",
    "ignore",
    "information",
    "messageContainer",
    "messages",
    "messagesCount",
    "messagesSorted",
    "numberMessages",
    "originatorException",
    "sagaException",
    "type",
    "viewed",
    "warning"
})
public class MsgDACContainer {

    protected boolean error;
    protected boolean ignore;
    protected boolean information;
    @XmlElement(required = true, nillable = true)
    protected MsgDACContainer messageContainer;
    @XmlElement(required = true, nillable = true)
    protected ArrayOfXsdAnyType messages;
    protected int messagesCount;
    @XmlElement(required = true, nillable = true)
    protected ArrayOfXsdAnyType messagesSorted;
    protected int numberMessages;
    @XmlElement(required = true, nillable = true)
    protected SagaException originatorException;
    @XmlElement(required = true, nillable = true)
    protected SagaException sagaException;
    protected int type;
    protected boolean viewed;
    protected boolean warning;

    /**
     * Recupera il valore della proprietà error.
     * 
     */
    public boolean isError() {
        return error;
    }

    /**
     * Imposta il valore della proprietà error.
     * 
     */
    public void setError(boolean value) {
        this.error = value;
    }

    /**
     * Recupera il valore della proprietà ignore.
     * 
     */
    public boolean isIgnore() {
        return ignore;
    }

    /**
     * Imposta il valore della proprietà ignore.
     * 
     */
    public void setIgnore(boolean value) {
        this.ignore = value;
    }

    /**
     * Recupera il valore della proprietà information.
     * 
     */
    public boolean isInformation() {
        return information;
    }

    /**
     * Imposta il valore della proprietà information.
     * 
     */
    public void setInformation(boolean value) {
        this.information = value;
    }

    /**
     * Recupera il valore della proprietà messageContainer.
     * 
     * @return
     *     possible object is
     *     {@link MsgDACContainer }
     *     
     */
    public MsgDACContainer getMessageContainer() {
        return messageContainer;
    }

    /**
     * Imposta il valore della proprietà messageContainer.
     * 
     * @param value
     *     allowed object is
     *     {@link MsgDACContainer }
     *     
     */
    public void setMessageContainer(MsgDACContainer value) {
        this.messageContainer = value;
    }

    /**
     * Recupera il valore della proprietà messages.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfXsdAnyType }
     *     
     */
    public ArrayOfXsdAnyType getMessages() {
        return messages;
    }

    /**
     * Imposta il valore della proprietà messages.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfXsdAnyType }
     *     
     */
    public void setMessages(ArrayOfXsdAnyType value) {
        this.messages = value;
    }

    /**
     * Recupera il valore della proprietà messagesCount.
     * 
     */
    public int getMessagesCount() {
        return messagesCount;
    }

    /**
     * Imposta il valore della proprietà messagesCount.
     * 
     */
    public void setMessagesCount(int value) {
        this.messagesCount = value;
    }

    /**
     * Recupera il valore della proprietà messagesSorted.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfXsdAnyType }
     *     
     */
    public ArrayOfXsdAnyType getMessagesSorted() {
        return messagesSorted;
    }

    /**
     * Imposta il valore della proprietà messagesSorted.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfXsdAnyType }
     *     
     */
    public void setMessagesSorted(ArrayOfXsdAnyType value) {
        this.messagesSorted = value;
    }

    /**
     * Recupera il valore della proprietà numberMessages.
     * 
     */
    public int getNumberMessages() {
        return numberMessages;
    }

    /**
     * Imposta il valore della proprietà numberMessages.
     * 
     */
    public void setNumberMessages(int value) {
        this.numberMessages = value;
    }

    /**
     * Recupera il valore della proprietà originatorException.
     * 
     * @return
     *     possible object is
     *     {@link SagaException }
     *     
     */
    public SagaException getOriginatorException() {
        return originatorException;
    }

    /**
     * Imposta il valore della proprietà originatorException.
     * 
     * @param value
     *     allowed object is
     *     {@link SagaException }
     *     
     */
    public void setOriginatorException(SagaException value) {
        this.originatorException = value;
    }

    /**
     * Recupera il valore della proprietà sagaException.
     * 
     * @return
     *     possible object is
     *     {@link SagaException }
     *     
     */
    public SagaException getSagaException() {
        return sagaException;
    }

    /**
     * Imposta il valore della proprietà sagaException.
     * 
     * @param value
     *     allowed object is
     *     {@link SagaException }
     *     
     */
    public void setSagaException(SagaException value) {
        this.sagaException = value;
    }

    /**
     * Recupera il valore della proprietà type.
     * 
     */
    public int getType() {
        return type;
    }

    /**
     * Imposta il valore della proprietà type.
     * 
     */
    public void setType(int value) {
        this.type = value;
    }

    /**
     * Recupera il valore della proprietà viewed.
     * 
     */
    public boolean isViewed() {
        return viewed;
    }

    /**
     * Imposta il valore della proprietà viewed.
     * 
     */
    public void setViewed(boolean value) {
        this.viewed = value;
    }

    /**
     * Recupera il valore della proprietà warning.
     * 
     */
    public boolean isWarning() {
        return warning;
    }

    /**
     * Imposta il valore della proprietà warning.
     * 
     */
    public void setWarning(boolean value) {
        this.warning = value;
    }

}
