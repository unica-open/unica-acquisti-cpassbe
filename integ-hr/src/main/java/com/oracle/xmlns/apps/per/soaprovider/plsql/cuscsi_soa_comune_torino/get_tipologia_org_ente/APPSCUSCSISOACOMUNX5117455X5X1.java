
package com.oracle.xmlns.apps.per.soaprovider.plsql.cuscsi_soa_comune_torino.get_tipologia_org_ente;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * &lt;p&gt;Classe Java per APPS.CUSCSI_SOA_COMUNX5117455X5X1 complex type.
 * 
 * &lt;p&gt;Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * &lt;pre&gt;
 * &amp;lt;complexType name="APPS.CUSCSI_SOA_COMUNX5117455X5X1"&amp;gt;
 *   &amp;lt;complexContent&amp;gt;
 *     &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&amp;gt;
 *       &amp;lt;sequence&amp;gt;
 *         &amp;lt;element name="CUSCSI_SOA_COMUNE_TORINO-24GET__ITEM" type="{http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_comune_torino/get_tipologia_org_ente/}APPS.CUSCSI_SOA_COMUNX5117455X5X2" maxOccurs="unbounded" minOccurs="0"/&amp;gt;
 *       &amp;lt;/sequence&amp;gt;
 *     &amp;lt;/restriction&amp;gt;
 *   &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &lt;/pre&gt;
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "APPS.CUSCSI_SOA_COMUNX5117455X5X1", propOrder = {
    "cuscsisoacomunetorino24GETITEM"
})
public class APPSCUSCSISOACOMUNX5117455X5X1 {

    @XmlElement(name = "CUSCSI_SOA_COMUNE_TORINO-24GET__ITEM", nillable = true)
    protected List<APPSCUSCSISOACOMUNX5117455X5X2> cuscsisoacomunetorino24GETITEM;

    /**
     * Gets the value of the cuscsisoacomunetorino24GETITEM property.
     * 
     * &lt;p&gt;
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a &lt;CODE&gt;set&lt;/CODE&gt; method for the cuscsisoacomunetorino24GETITEM property.
     * 
     * &lt;p&gt;
     * For example, to add a new item, do as follows:
     * &lt;pre&gt;
     *    getCUSCSISOACOMUNETORINO24GETITEM().add(newItem);
     * &lt;/pre&gt;
     * 
     * 
     * &lt;p&gt;
     * Objects of the following type(s) are allowed in the list
     * {@link APPSCUSCSISOACOMUNX5117455X5X2 }
     * 
     * 
     */
    public List<APPSCUSCSISOACOMUNX5117455X5X2> getCUSCSISOACOMUNETORINO24GETITEM() {
        if (cuscsisoacomunetorino24GETITEM == null) {
            cuscsisoacomunetorino24GETITEM = new ArrayList<APPSCUSCSISOACOMUNX5117455X5X2>();
        }
        return this.cuscsisoacomunetorino24GETITEM;
    }

}
