/*-
 * ========================LICENSE_START=================================
 * CPASS BackEnd - INTEGRATION submodule - ACTA
 * %%
 * Copyright (C) 2019 - 2023 CSI Piemonte
 * %%
 * SPDX-FileCopyrightText: Copyright 2019 - 2023 | CSI Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 * =========================LICENSE_END==================================
 */

package it.doqui.acta.acaris.subjectregistryservice;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;

import it.doqui.acta.acaris.common.GetPropertiesMassive;
import it.doqui.acta.acaris.common.GetPropertiesMassiveResponse;
import it.doqui.acta.acaris.common.Query;
import it.doqui.acta.acaris.common.QueryResponse;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.3.3
 * Generated source version: 2.2
 * 
 */
@WebService(name = "SubjectRegistryServicePort", targetNamespace = "subjectregistryservice.acaris.acta.doqui.it")
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
@XmlSeeAlso({
    it.doqui.acta.acaris.common.ObjectFactory.class,
    it.doqui.acta.acaris.subjectregistryservice.ObjectFactory.class,
    it.doqui.acta.acaris.common.prt.ObjectFactory.class
})
public interface SubjectRegistryServicePort {


    /**
     * 
     * @param input
     * @return
     *     returns it.doqui.acta.acaris.subjectregistryservice.CreaSoggettoResponse
     * @throws AcarisException
     */
    @WebMethod
    @WebResult(name = "creaSoggettoResponse", targetNamespace = "subjectregistryservice.acaris.acta.doqui.it", partName = "output")
    public CreaSoggettoResponse creaSoggetto(
        @WebParam(name = "creaSoggetto", targetNamespace = "subjectregistryservice.acaris.acta.doqui.it", partName = "input")
        CreaSoggetto input)
        throws AcarisException
    ;

    /**
     * 
     * @param input
     * @return
     *     returns it.doqui.acta.acaris.subjectregistryservice.RicercaSoggettoResponse
     * @throws AcarisException
     */
    @WebMethod
    @WebResult(name = "ricercaSoggettoResponse", targetNamespace = "subjectregistryservice.acaris.acta.doqui.it", partName = "output")
    public RicercaSoggettoResponse ricercaSoggetto(
        @WebParam(name = "ricercaSoggetto", targetNamespace = "subjectregistryservice.acaris.acta.doqui.it", partName = "input")
        RicercaSoggetto input)
        throws AcarisException
    ;

    /**
     * 
     * @param input
     * @return
     *     returns it.doqui.acta.acaris.subjectregistryservice.RicercaSoggettoDaFonteEsternaResponse
     * @throws AcarisException
     */
    @WebMethod
    @WebResult(name = "ricercaSoggettoDaFonteEsternaResponse", targetNamespace = "subjectregistryservice.acaris.acta.doqui.it", partName = "output")
    public RicercaSoggettoDaFonteEsternaResponse ricercaSoggettoDaFonteEsterna(
        @WebParam(name = "ricercaSoggettoDaFonteEsterna", targetNamespace = "subjectregistryservice.acaris.acta.doqui.it", partName = "input")
        RicercaSoggettoDaFonteEsterna input)
        throws AcarisException
    ;

    /**
     * 
     * @param input
     * @return
     *     returns it.doqui.acta.acaris.subjectregistryservice.GetPropertiesResponse
     * @throws AcarisException
     */
    @WebMethod
    @WebResult(name = "getPropertiesResponse", targetNamespace = "subjectregistryservice.acaris.acta.doqui.it", partName = "output")
    public GetPropertiesResponse getProperties(
        @WebParam(name = "getProperties", targetNamespace = "subjectregistryservice.acaris.acta.doqui.it", partName = "input")
        GetProperties input)
        throws AcarisException
    ;

    /**
     * 
     * @param input
     * @return
     *     returns it.doqui.acta.acaris.subjectregistryservice.UpdatePropertiesResponse
     * @throws AcarisException
     */
    @WebMethod
    @WebResult(name = "updatePropertiesResponse", targetNamespace = "subjectregistryservice.acaris.acta.doqui.it", partName = "output")
    public UpdatePropertiesResponse updateProperties(
        @WebParam(name = "updateProperties", targetNamespace = "subjectregistryservice.acaris.acta.doqui.it", partName = "input")
        UpdateProperties input)
        throws AcarisException
    ;

    /**
     * 
     * @param input
     * @return
     *     returns it.doqui.acta.acaris.common.QueryResponse
     * @throws AcarisException
     */
    @WebMethod
    @WebResult(name = "queryResponse", targetNamespace = "common.acaris.acta.doqui.it", partName = "output")
    public QueryResponse query(
        @WebParam(name = "query", targetNamespace = "common.acaris.acta.doqui.it", partName = "input")
        Query input)
        throws AcarisException
    ;

    /**
     * 
     * @param input
     * @return
     *     returns it.doqui.acta.acaris.common.GetPropertiesMassiveResponse
     * @throws AcarisException
     */
    @WebMethod
    @WebResult(name = "getPropertiesMassiveResponse", targetNamespace = "common.acaris.acta.doqui.it", partName = "output")
    public GetPropertiesMassiveResponse getPropertiesMassive(
        @WebParam(name = "getPropertiesMassive", targetNamespace = "common.acaris.acta.doqui.it", partName = "input")
        GetPropertiesMassive input)
        throws AcarisException
    ;

    /**
     * 
     * @param input
     * @return
     *     returns it.doqui.acta.acaris.subjectregistryservice.CreaIndirizzoResponse
     * @throws AcarisException
     */
    @WebMethod
    @WebResult(name = "creaIndirizzoResponse", targetNamespace = "subjectregistryservice.acaris.acta.doqui.it", partName = "output")
    public CreaIndirizzoResponse creaIndirizzo(
        @WebParam(name = "creaIndirizzo", targetNamespace = "subjectregistryservice.acaris.acta.doqui.it", partName = "input")
        CreaIndirizzo input)
        throws AcarisException
    ;

    /**
     * 
     * @param input
     * @return
     *     returns it.doqui.acta.acaris.subjectregistryservice.CreaCategoriaAnagraficaResponse
     * @throws AcarisException
     */
    @WebMethod
    @WebResult(name = "creaCategoriaAnagraficaResponse", targetNamespace = "subjectregistryservice.acaris.acta.doqui.it", partName = "output")
    public CreaCategoriaAnagraficaResponse creaCategoriaAnagrafica(
        @WebParam(name = "creaCategoriaAnagrafica", targetNamespace = "subjectregistryservice.acaris.acta.doqui.it", partName = "input")
        CreaCategoriaAnagrafica input)
        throws AcarisException
    ;

}
