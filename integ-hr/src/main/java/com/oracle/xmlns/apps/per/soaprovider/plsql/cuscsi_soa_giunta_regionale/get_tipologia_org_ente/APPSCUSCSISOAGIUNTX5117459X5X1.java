
package com.oracle.xmlns.apps.per.soaprovider.plsql.cuscsi_soa_giunta_regionale.get_tipologia_org_ente;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * &lt;p&gt;Classe Java per APPS.CUSCSI_SOA_GIUNTX5117459X5X1 complex type.
 * 
 * &lt;p&gt;Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * &lt;pre&gt;
 * &amp;lt;complexType name="APPS.CUSCSI_SOA_GIUNTX5117459X5X1"&amp;gt;
 *   &amp;lt;complexContent&amp;gt;
 *     &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&amp;gt;
 *       &amp;lt;sequence&amp;gt;
 *         &amp;lt;element name="CUSCSI_SOA_GIUNTA_REGIONALE-24G_ITEM" type="{http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_giunta_regionale/get_tipologia_org_ente/}APPS.CUSCSI_SOA_GIUNTX5117459X5X2" maxOccurs="unbounded" minOccurs="0"/&amp;gt;
 *       &amp;lt;/sequence&amp;gt;
 *     &amp;lt;/restriction&amp;gt;
 *   &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &lt;/pre&gt;
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "APPS.CUSCSI_SOA_GIUNTX5117459X5X1", propOrder = {
    "cuscsisoagiuntaregionale24GITEM"
})
public class APPSCUSCSISOAGIUNTX5117459X5X1 {

    @XmlElement(name = "CUSCSI_SOA_GIUNTA_REGIONALE-24G_ITEM", nillable = true)
    protected List<APPSCUSCSISOAGIUNTX5117459X5X2> cuscsisoagiuntaregionale24GITEM;

    /**
     * Gets the value of the cuscsisoagiuntaregionale24GITEM property.
     * 
     * &lt;p&gt;
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a &lt;CODE&gt;set&lt;/CODE&gt; method for the cuscsisoagiuntaregionale24GITEM property.
     * 
     * &lt;p&gt;
     * For example, to add a new item, do as follows:
     * &lt;pre&gt;
     *    getCUSCSISOAGIUNTAREGIONALE24GITEM().add(newItem);
     * &lt;/pre&gt;
     * 
     * 
     * &lt;p&gt;
     * Objects of the following type(s) are allowed in the list
     * {@link APPSCUSCSISOAGIUNTX5117459X5X2 }
     * 
     * 
     */
    public List<APPSCUSCSISOAGIUNTX5117459X5X2> getCUSCSISOAGIUNTAREGIONALE24GITEM() {
        if (cuscsisoagiuntaregionale24GITEM == null) {
            cuscsisoagiuntaregionale24GITEM = new ArrayList<APPSCUSCSISOAGIUNTX5117459X5X2>();
        }
        return this.cuscsisoagiuntaregionale24GITEM;
    }

}
