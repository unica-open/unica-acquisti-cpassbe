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

package fdewsappjdocumentigateway;

import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.3.3
 * Generated source version: 2.2
 * 
 */
@WebServiceClient(name = "FdeWSAppjDocumentiGatewayService", targetNamespace = "urn:FdeWSAppjDocumentiGateway", wsdlLocation = "/wsdl/FdeWSAppjDocumentiGateway.wsdl")
public class FdeWSAppjDocumentiGatewayService
    extends Service
{

    private final static URL FDEWSAPPJDOCUMENTIGATEWAYSERVICE_WSDL_LOCATION;
    private final static WebServiceException FDEWSAPPJDOCUMENTIGATEWAYSERVICE_EXCEPTION;
    private final static QName FDEWSAPPJDOCUMENTIGATEWAYSERVICE_QNAME = new QName("urn:FdeWSAppjDocumentiGateway", "FdeWSAppjDocumentiGatewayService");

    static {
        FDEWSAPPJDOCUMENTIGATEWAYSERVICE_WSDL_LOCATION = fdewsappjdocumentigateway.FdeWSAppjDocumentiGatewayService.class.getResource("/wsdl/FdeWSAppjDocumentiGateway.wsdl");
        WebServiceException e = null;
        if (FDEWSAPPJDOCUMENTIGATEWAYSERVICE_WSDL_LOCATION == null) {
            e = new WebServiceException("Cannot find '/wsdl/FdeWSAppjDocumentiGateway.wsdl' wsdl. Place the resource correctly in the classpath.");
        }
        FDEWSAPPJDOCUMENTIGATEWAYSERVICE_EXCEPTION = e;
    }

    public FdeWSAppjDocumentiGatewayService() {
        super(__getWsdlLocation(), FDEWSAPPJDOCUMENTIGATEWAYSERVICE_QNAME);
    }

    public FdeWSAppjDocumentiGatewayService(WebServiceFeature... features) {
        super(__getWsdlLocation(), FDEWSAPPJDOCUMENTIGATEWAYSERVICE_QNAME, features);
    }

    public FdeWSAppjDocumentiGatewayService(URL wsdlLocation) {
        super(wsdlLocation, FDEWSAPPJDOCUMENTIGATEWAYSERVICE_QNAME);
    }

    public FdeWSAppjDocumentiGatewayService(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, FDEWSAPPJDOCUMENTIGATEWAYSERVICE_QNAME, features);
    }

    public FdeWSAppjDocumentiGatewayService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public FdeWSAppjDocumentiGatewayService(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns FdeWSAppjDocumentiGateway
     */
    @WebEndpoint(name = "FdeWSAppjDocumentiGateway")
    public FdeWSAppjDocumentiGateway getFdeWSAppjDocumentiGateway() {
        return super.getPort(new QName("urn:FdeWSAppjDocumentiGateway", "FdeWSAppjDocumentiGateway"), FdeWSAppjDocumentiGateway.class);
    }

    /**
     * 
     * @param features
     *     A list of {&#064;link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the &lt;code&gt;features&lt;/code&gt; parameter will have their default values.
     * @return
     *     returns FdeWSAppjDocumentiGateway
     */
    @WebEndpoint(name = "FdeWSAppjDocumentiGateway")
    public FdeWSAppjDocumentiGateway getFdeWSAppjDocumentiGateway(WebServiceFeature... features) {
        return super.getPort(new QName("urn:FdeWSAppjDocumentiGateway", "FdeWSAppjDocumentiGateway"), FdeWSAppjDocumentiGateway.class, features);
    }

    private static URL __getWsdlLocation() {
        if (FDEWSAPPJDOCUMENTIGATEWAYSERVICE_EXCEPTION!= null) {
            throw FDEWSAPPJDOCUMENTIGATEWAYSERVICE_EXCEPTION;
        }
        return FDEWSAPPJDOCUMENTIGATEWAYSERVICE_WSDL_LOCATION;
    }

}
