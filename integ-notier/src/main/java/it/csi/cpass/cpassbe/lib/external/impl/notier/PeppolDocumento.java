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
package it.csi.cpass.cpassbe.lib.external.impl.notier;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.commons.lang3.StringUtils;

import it.csi.cpass.cpassbe.lib.dto.ord.Destinatario;
import it.csi.cpass.cpassbe.lib.dto.ord.RigaOrdine;
import it.csi.cpass.cpassbe.lib.dto.ord.TestataOrdine;
import it.csi.cpass.cpassbe.lib.external.dto.RigheOrdineConTotali;
import it.csi.cpass.cpassbe.lib.external.impl.notier.NSOHelperImpl.TIPO_ORDINE;
import it.csi.cpass.cpassbe.lib.external.itf.ConfigurationParam;
import it.csi.cpass.cpassbe.lib.util.serialization.JAXBUtility;
import it.csi.cpass.cpassbe.lib.utils.NotiERConfigurationParams;
import oasis.names.specification.ubl.schema.xsd.commonaggregatecomponents_2.AddressType;
import oasis.names.specification.ubl.schema.xsd.commonaggregatecomponents_2.AllowanceChargeType;
import oasis.names.specification.ubl.schema.xsd.commonaggregatecomponents_2.CommodityClassificationType;
import oasis.names.specification.ubl.schema.xsd.commonaggregatecomponents_2.ContactType;
import oasis.names.specification.ubl.schema.xsd.commonaggregatecomponents_2.ContractType;
import oasis.names.specification.ubl.schema.xsd.commonaggregatecomponents_2.CountryType;
import oasis.names.specification.ubl.schema.xsd.commonaggregatecomponents_2.CustomerPartyType;
import oasis.names.specification.ubl.schema.xsd.commonaggregatecomponents_2.DeliveryType;
import oasis.names.specification.ubl.schema.xsd.commonaggregatecomponents_2.DocumentReferenceType;
import oasis.names.specification.ubl.schema.xsd.commonaggregatecomponents_2.ItemIdentificationType;
import oasis.names.specification.ubl.schema.xsd.commonaggregatecomponents_2.ItemType;
import oasis.names.specification.ubl.schema.xsd.commonaggregatecomponents_2.LineItemType;
import oasis.names.specification.ubl.schema.xsd.commonaggregatecomponents_2.LocationType;
import oasis.names.specification.ubl.schema.xsd.commonaggregatecomponents_2.MonetaryTotalType;
import oasis.names.specification.ubl.schema.xsd.commonaggregatecomponents_2.OrderLineType;
import oasis.names.specification.ubl.schema.xsd.commonaggregatecomponents_2.PartyLegalEntityType;
import oasis.names.specification.ubl.schema.xsd.commonaggregatecomponents_2.PartyNameType;
import oasis.names.specification.ubl.schema.xsd.commonaggregatecomponents_2.PartyTaxSchemeType;
import oasis.names.specification.ubl.schema.xsd.commonaggregatecomponents_2.PartyType;
import oasis.names.specification.ubl.schema.xsd.commonaggregatecomponents_2.PeriodType;
import oasis.names.specification.ubl.schema.xsd.commonaggregatecomponents_2.PriceType;
import oasis.names.specification.ubl.schema.xsd.commonaggregatecomponents_2.SupplierPartyType;
import oasis.names.specification.ubl.schema.xsd.commonaggregatecomponents_2.TaxCategoryType;
import oasis.names.specification.ubl.schema.xsd.commonaggregatecomponents_2.TaxSchemeType;
import oasis.names.specification.ubl.schema.xsd.commonaggregatecomponents_2.TaxTotalType;
import oasis.names.specification.ubl.schema.xsd.commonbasiccomponents_2.AllowanceChargeReasonCodeType;
import oasis.names.specification.ubl.schema.xsd.commonbasiccomponents_2.AllowanceTotalAmountType;
import oasis.names.specification.ubl.schema.xsd.commonbasiccomponents_2.AmountType;
import oasis.names.specification.ubl.schema.xsd.commonbasiccomponents_2.BaseAmountType;
import oasis.names.specification.ubl.schema.xsd.commonbasiccomponents_2.BaseQuantityType;
import oasis.names.specification.ubl.schema.xsd.commonbasiccomponents_2.ChargeIndicatorType;
import oasis.names.specification.ubl.schema.xsd.commonbasiccomponents_2.CityNameType;
import oasis.names.specification.ubl.schema.xsd.commonbasiccomponents_2.CompanyIDType;
import oasis.names.specification.ubl.schema.xsd.commonbasiccomponents_2.CustomerReferenceType;
import oasis.names.specification.ubl.schema.xsd.commonbasiccomponents_2.CustomizationIDType;
import oasis.names.specification.ubl.schema.xsd.commonbasiccomponents_2.DescriptionType;
import oasis.names.specification.ubl.schema.xsd.commonbasiccomponents_2.DocumentCurrencyCodeType;
import oasis.names.specification.ubl.schema.xsd.commonbasiccomponents_2.DocumentTypeType;
import oasis.names.specification.ubl.schema.xsd.commonbasiccomponents_2.ElectronicMailType;
import oasis.names.specification.ubl.schema.xsd.commonbasiccomponents_2.EndDateType;
import oasis.names.specification.ubl.schema.xsd.commonbasiccomponents_2.EndpointIDType;
import oasis.names.specification.ubl.schema.xsd.commonbasiccomponents_2.IdentificationCodeType;
import oasis.names.specification.ubl.schema.xsd.commonbasiccomponents_2.IssueDateType;
import oasis.names.specification.ubl.schema.xsd.commonbasiccomponents_2.ItemClassificationCodeType;
import oasis.names.specification.ubl.schema.xsd.commonbasiccomponents_2.LineExtensionAmountType;
import oasis.names.specification.ubl.schema.xsd.commonbasiccomponents_2.MultiplierFactorNumericType;
import oasis.names.specification.ubl.schema.xsd.commonbasiccomponents_2.NoteType;
import oasis.names.specification.ubl.schema.xsd.commonbasiccomponents_2.OrderTypeCodeType;
import oasis.names.specification.ubl.schema.xsd.commonbasiccomponents_2.PartialDeliveryIndicatorType;
import oasis.names.specification.ubl.schema.xsd.commonbasiccomponents_2.PayableAmountType;
import oasis.names.specification.ubl.schema.xsd.commonbasiccomponents_2.PercentType;
import oasis.names.specification.ubl.schema.xsd.commonbasiccomponents_2.PostalZoneType;
import oasis.names.specification.ubl.schema.xsd.commonbasiccomponents_2.PriceAmountType;
import oasis.names.specification.ubl.schema.xsd.commonbasiccomponents_2.ProfileIDType;
import oasis.names.specification.ubl.schema.xsd.commonbasiccomponents_2.QuantityType;
import oasis.names.specification.ubl.schema.xsd.commonbasiccomponents_2.RegistrationNameType;
import oasis.names.specification.ubl.schema.xsd.commonbasiccomponents_2.StartDateType;
import oasis.names.specification.ubl.schema.xsd.commonbasiccomponents_2.StreetNameType;
import oasis.names.specification.ubl.schema.xsd.commonbasiccomponents_2.TaxAmountType;
import oasis.names.specification.ubl.schema.xsd.commonbasiccomponents_2.TaxExclusiveAmountType;
import oasis.names.specification.ubl.schema.xsd.commonbasiccomponents_2.TaxInclusiveAmountType;
import oasis.names.specification.ubl.schema.xsd.commonbasiccomponents_2.TelephoneType;
import oasis.names.specification.ubl.schema.xsd.order_2.OrderType;

public class PeppolDocumento {

	private static final String EUR = "EUR";

	public static String getXmlDocumento(TIPO_ORDINE tipoOrdine, TestataOrdine testataOrdine, Destinatario destinatarioOrdine,
			RigheOrdineConTotali righeOrdineConTotali, int maxProgressivoInvio, Map<String, String> params) throws DatatypeConfigurationException {
		OrderType orderType = new OrderType();

		String customizationID = getParameter(params, NotiERConfigurationParams.NSO_CUSTOMIZATION_ID);
		CustomizationIDType customizationIDType = new CustomizationIDType();
		customizationIDType.setValue(customizationID);
		orderType.setCustomizationID(customizationIDType);

		String profileID = getParameter(params, NotiERConfigurationParams.NSO_PROFILE_ID);
		ProfileIDType profileIDType = new ProfileIDType();
		profileIDType.setValue(profileID);
		orderType.setProfileID(profileIDType);

		orderType.setID(PeppolXmlUtils.getIDType(getOrderId(testataOrdine, destinatarioOrdine, maxProgressivoInvio)));

		IssueDateType issueDateType = new IssueDateType();
		issueDateType.setValue(getDeterminazioneDataInvioNSO(destinatarioOrdine));
		orderType.setIssueDate(issueDateType);

		OrderTypeCodeType orderTypeCodeType = new OrderTypeCodeType();
		orderTypeCodeType.setValue("220");
		orderType.setOrderTypeCode(orderTypeCodeType);

		NoteType noteType = new NoteType();
		noteType.setValue(testataOrdine.getDescrizione());
		orderType.getNote().add(noteType);

		DocumentCurrencyCodeType documentCurrencyCodeType = new DocumentCurrencyCodeType();
		documentCurrencyCodeType.setValue(EUR);
		orderType.setDocumentCurrencyCode(documentCurrencyCodeType);

		orderType.setCustomerReference(getCustomerReference(testataOrdine, destinatarioOrdine));

		EndDateType endDateType = null;
		if (testataOrdine.getDataScadenza() != null) {
			endDateType = new EndDateType();
			endDateType.setValue(PeppolXmlUtils.getXMLGregorianCalendar(testataOrdine.getDataScadenza()));
		}
		PeriodType periodType = new PeriodType();
		periodType.setEndDate(endDateType);
		orderType.getValidityPeriod().add(periodType);

		orderType.getOrderDocumentReference().add(getDeterminazioneTagOrderDocumentReference(tipoOrdine, testataOrdine, destinatarioOrdine, maxProgressivoInvio));

		DocumentReferenceType documentReferenceType = new DocumentReferenceType();
		documentReferenceType.setID(PeppolXmlUtils.getIDType(testataOrdine.getProvvedimento().getAnno() + "/" + testataOrdine.getProvvedimento().getNumero()));
		DocumentTypeType documentTypeType = new DocumentTypeType();
		documentTypeType.setValue("Delibera");
		documentReferenceType.setDocumentType(documentTypeType);
		orderType.getAdditionalDocumentReference().add(documentReferenceType);

		ContractType contractType = new ContractType();
		contractType.setID(PeppolXmlUtils.getIDType("TOBD"));
		orderType.getContract().add(contractType);

		setBuyerCustomerParty(orderType, testataOrdine);
		setSellerSupplierParty(orderType, testataOrdine);
		setDelivery(orderType, testataOrdine, destinatarioOrdine);
		setTotali(orderType, righeOrdineConTotali);

		for (RigaOrdine rigaOrdine : righeOrdineConTotali.getRigaOrdines()) {
			setOrderLine(tipoOrdine, orderType, rigaOrdine);
		}

		return JAXBUtility.marshall(orderType);
	}

	private static String getParameter(Map<String, String> params, ConfigurationParam param) {
		return params.get(param.getParamName());
	}

	private static String getOrderId(TestataOrdine testataOrdine, Destinatario destinatarioOrdine, int maxProgressivoInvio) {
		// CPASS_T_ORD_TESTATA_ORDINE.ordine_anno
		// ||’_’||CPASS_T_ORD_TESTATA_ORDINE.numero||’_’||CPASS_T_ORD_DESTINATARIO_ORDINE.progressivo||’_’||MAX(CPASS_R_ORD_TESTATA_INVIO_NSO.progressivo_invio)
		// + 1
		// TODO max da CPASS_R_ORD_TESTATA_INVIO_NSO
		return testataOrdine.getAnno() + "_" + testataOrdine.getNumero() + "_" + destinatarioOrdine.getProgressivo() + "_" + maxProgressivoInvio;
	}

	private static XMLGregorianCalendar getDeterminazioneDataInvioNSO(Destinatario destinatarioOrdine) throws DatatypeConfigurationException {
		Date date = null;
		if (destinatarioOrdine.getDataInvioNso() == null) {
			date = new Date();
		} else {
			date = destinatarioOrdine.getDataInvioNso();
		}
		return PeppolXmlUtils.getXMLGregorianCalendar(date);
	}

	private static CustomerReferenceType getCustomerReference(TestataOrdine testataOrdine, Destinatario destinatarioOrdine) {
		String value = null;

		// Se è popolato CPASS_T_ORD_DESTINATARIO_ORDINE.contatto , utilizzare il valore di questo campo,
		if (!StringUtils.isBlank(destinatarioOrdine.getContatto())) {
			value = destinatarioOrdine.getContatto();
		}

		// altrimenti verificare se CPASS_T_ORD_TESTATA_ORDINE.riferimento è valorizzato ed eventualmente utilizzare questo.
		if (!StringUtils.isBlank(testataOrdine.getConsegnaRiferimento())) {
			value = testataOrdine.getConsegnaRiferimento();
		}

		// Se entrambi vuoti, non compilare il tag
		if (value == null) {
			return null;
		}

		CustomerReferenceType customerReferenceType = new CustomerReferenceType();
		customerReferenceType.setValue(value);
		return customerReferenceType;
	}

	private static DocumentReferenceType getDeterminazioneTagOrderDocumentReference(TIPO_ORDINE tipoOrdine, TestataOrdine testataOrdine,
			Destinatario destinatarioOrdine, int maxProgressivoInvio) {
		String value = null;
		if (TIPO_ORDINE.INIZIALE.equals(tipoOrdine)) {
			value = "";
		} else {
			// es. 2020_432_2_3#2018-01-0#B9GAQ#Revised
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

			String statoOrdine = null;
			if (TIPO_ORDINE.REVOCA.equals(tipoOrdine)) {
				statoOrdine = "Cancelled";
			} else if (TIPO_ORDINE.SOSTITUTIVO.equals(tipoOrdine)) {
				statoOrdine = "Revised";
			}
			value = getOrderId(testataOrdine, destinatarioOrdine, maxProgressivoInvio) + "#" + sdf.format(destinatarioOrdine.getDataInvioNso()) + "#"
					+ testataOrdine.getUfficio().getCodice() + "#" + statoOrdine;
		}

		DocumentReferenceType documentReferenceType = new DocumentReferenceType();
		documentReferenceType.setID(PeppolXmlUtils.getIDType(value));

		return documentReferenceType;
	}

	private static void setBuyerCustomerParty(OrderType orderType, TestataOrdine testataOrdine) {
		// BuyerCustomerParty
		// Party
		EndpointIDType endpointIDType = new EndpointIDType();
		endpointIDType.setSchemeID("0201");
		endpointIDType.setValue(testataOrdine.getUfficio().getCodice());
		PartyNameType partyNameType = new PartyNameType();
		partyNameType.setName(PeppolXmlUtils.getNameType(testataOrdine.getSettore().getCodice() + "-" + testataOrdine.getSettore().getDescrizione()));
		PartyType partyType = new PartyType();
		partyType.setEndpointID(endpointIDType);
		partyType.getPartyName().add(partyNameType);

		// PostalAddress
		StreetNameType streetNameType = new StreetNameType();
		streetNameType.setValue(testataOrdine.getSettore().getIndirizzo() + "," + testataOrdine.getSettore().getNumCivico());

		CityNameType cityNameType = new CityNameType();
		cityNameType.setValue(testataOrdine.getSettore().getLocalita());

		PostalZoneType postalZoneType = new PostalZoneType();
		postalZoneType.setValue(testataOrdine.getSettore().getCap());

		IdentificationCodeType identificationCodeType = new IdentificationCodeType();
		identificationCodeType.setValue("IT");
		CountryType countryType = new CountryType();
		countryType.setIdentificationCode(identificationCodeType);

		AddressType addressType = new AddressType();
		addressType.setStreetName(streetNameType);
		addressType.setCityName(cityNameType);
		addressType.setPostalZone(postalZoneType);
		addressType.setCountry(countryType);
		partyType.setPostalAddress(addressType);

		// PartyTaxScheme
		CompanyIDType companyIDType = new CompanyIDType();
		companyIDType.setValue(testataOrdine.getSettore().getEnte().getCodiceFiscale());
		PartyTaxSchemeType partyTaxSchemeType = new PartyTaxSchemeType();
		partyTaxSchemeType.setCompanyID(companyIDType);
		TaxSchemeType taxSchemeType = new TaxSchemeType();
		taxSchemeType.setID(PeppolXmlUtils.getIDType("VAT"));
		partyTaxSchemeType.setTaxScheme(taxSchemeType);
		partyType.getPartyTaxScheme().add(partyTaxSchemeType);

		// PartyLegalEntity
		RegistrationNameType registrationNameType = new RegistrationNameType();
		registrationNameType.setValue(testataOrdine.getSettore().getEnte().getDenominazione());
		PartyLegalEntityType partyLegalEntityType = new PartyLegalEntityType();
		partyLegalEntityType.setRegistrationName(registrationNameType);
		partyType.getPartyLegalEntity().add(partyLegalEntityType);

		CustomerPartyType customerPartyType = new CustomerPartyType();
		customerPartyType.setParty(partyType);
		orderType.setBuyerCustomerParty(customerPartyType);
	}

	private static void setSellerSupplierParty(OrderType orderType, TestataOrdine testataOrdine) {
		EndpointIDType endpointIDType = new EndpointIDType();
		endpointIDType.setSchemeID("9906");
		if (!StringUtils.isBlank(testataOrdine.getFornitore().getPartitaIva())) {
			endpointIDType.setValue("IT" + testataOrdine.getFornitore().getPartitaIva());
		} else {
			endpointIDType.setValue("IT" + testataOrdine.getFornitore().getCodiceFiscale());
		}

		String nomeFornitore = testataOrdine.getFornitore().getRagioneSociale();
		if (StringUtils.isBlank(nomeFornitore)) {
			nomeFornitore = testataOrdine.getFornitore().getCognome() + " " + testataOrdine.getFornitore().getNome();
		}

		PartyNameType partyNameType = new PartyNameType();
		partyNameType.setName(PeppolXmlUtils.getNameType(nomeFornitore));
		PartyType partyType = new PartyType();
		partyType.setEndpointID(endpointIDType);
		partyType.getPartyName().add(partyNameType);

		// PostalAddress
		StreetNameType streetNameType = new StreetNameType();
		streetNameType.setValue(testataOrdine.getFornitore().getIndirizzo() + "," + testataOrdine.getFornitore().getNumeroCivico());

		CityNameType cityNameType = new CityNameType();
		cityNameType.setValue(testataOrdine.getFornitore().getComune());

		PostalZoneType postalZoneType = new PostalZoneType();
		postalZoneType.setValue(testataOrdine.getFornitore().getCap());

		IdentificationCodeType identificationCodeType = new IdentificationCodeType();
		identificationCodeType.setValue("IT");
		CountryType countryType = new CountryType();
		countryType.setIdentificationCode(identificationCodeType);

		AddressType addressType = new AddressType();
		addressType.setStreetName(streetNameType);
		addressType.setCityName(cityNameType);
		addressType.setPostalZone(postalZoneType);
		addressType.setCountry(countryType);
		partyType.setPostalAddress(addressType);

		RegistrationNameType registrationNameType = new RegistrationNameType();
		registrationNameType.setValue(nomeFornitore);
		PartyLegalEntityType partyLegalEntityType = new PartyLegalEntityType();
		partyLegalEntityType.setRegistrationName(registrationNameType);
		partyType.getPartyLegalEntity().add(partyLegalEntityType);

		SupplierPartyType supplierPartyType = new SupplierPartyType();
		supplierPartyType.setParty(partyType);
		orderType.setSellerSupplierParty(supplierPartyType);
	}

	private static void setDelivery(OrderType orderType, TestataOrdine testataOrdine, Destinatario destinatarioOrdine) throws DatatypeConfigurationException {
		// inzio - DeliveryLocation
		StreetNameType streetNameType = new StreetNameType();
		streetNameType.setValue(destinatarioOrdine.getIndirizzo() + "," + destinatarioOrdine.getNumCivico());

		CityNameType cityNameType = new CityNameType();
		cityNameType.setValue(destinatarioOrdine.getLocalita());

		PostalZoneType postalZoneType = new PostalZoneType();
		postalZoneType.setValue(destinatarioOrdine.getCap());

		IdentificationCodeType identificationCodeType = new IdentificationCodeType();
		identificationCodeType.setValue("IT");
		CountryType countryType = new CountryType();
		countryType.setIdentificationCode(identificationCodeType);

		AddressType addressType = new AddressType();
		addressType.setStreetName(streetNameType);
		addressType.setCityName(cityNameType);
		addressType.setPostalZone(postalZoneType);
		addressType.setCountry(countryType);

		LocationType locationType = new LocationType();
		locationType.setAddress(addressType);
		// fine - DeliveryLocation

		// inizio periodType
		StartDateType startDateType = null;
		if (testataOrdine.getConsegnaDataDa() != null) {
			startDateType = new StartDateType();
			startDateType.setValue(PeppolXmlUtils.getXMLGregorianCalendar(testataOrdine.getConsegnaDataDa()));
		}

		EndDateType endDateType = null;
		if (testataOrdine.getConsegnaDataA() != null) {
			endDateType = new EndDateType();
			endDateType.setValue(PeppolXmlUtils.getXMLGregorianCalendar(testataOrdine.getConsegnaDataA()));
		}

		PeriodType periodType = new PeriodType();
		periodType.setStartDate(startDateType);
		periodType.setEndDate(endDateType);
		// fine periodType

		// inizio DeliveryParty
		String destinatarioSettore = destinatarioOrdine.getSettore().getCodice() + "-" + destinatarioOrdine.getSettore().getDescrizione();
		PartyNameType partyNameType = new PartyNameType();
		partyNameType.setName(PeppolXmlUtils.getNameType(destinatarioSettore));
		PartyType partyType = new PartyType();
		partyType.getPartyName().add(partyNameType);

		ContactType contactType = new ContactType();
		contactType.setName(PeppolXmlUtils.getNameType(destinatarioOrdine.getContatto()));

		TelephoneType telephoneType = new TelephoneType();
		telephoneType.setValue(destinatarioOrdine.getTelefono());
		contactType.setTelephone(telephoneType);

		ElectronicMailType electronicMailType = new ElectronicMailType();
		electronicMailType.setValue(destinatarioOrdine.getEmail());
		contactType.setElectronicMail(electronicMailType);

		partyType.setContact(contactType);
		// fine DeliveryParty

		DeliveryType deliveryType = new DeliveryType();
		deliveryType.setDeliveryLocation(locationType);
		deliveryType.setRequestedDeliveryPeriod(periodType);
		deliveryType.setDeliveryParty(partyType);

		orderType.getDelivery().add(deliveryType);
	}

	private static void setTotali(OrderType orderType, RigheOrdineConTotali righeOrdineConTotali) {
		TaxAmountType taxAmountType = new TaxAmountType();
		taxAmountType.setCurrencyID(EUR);
		taxAmountType.setValue(righeOrdineConTotali.taxAmount);

		TaxTotalType taxTotalType = new TaxTotalType();
		taxTotalType.setTaxAmount(taxAmountType);

		orderType.getTaxTotal().add(taxTotalType);

		LineExtensionAmountType lineExtensionAmountType = new LineExtensionAmountType();
		lineExtensionAmountType.setCurrencyID(EUR);
		lineExtensionAmountType.setValue(righeOrdineConTotali.lineExtensionAmount);

		AllowanceTotalAmountType allowanceTotalAmountType = new AllowanceTotalAmountType();
		allowanceTotalAmountType.setCurrencyID(EUR);
		allowanceTotalAmountType.setValue(righeOrdineConTotali.allowanceTotalAmount);

		TaxExclusiveAmountType taxExclusiveAmountType = new TaxExclusiveAmountType();
		taxExclusiveAmountType.setCurrencyID(EUR);
		taxExclusiveAmountType.setValue(righeOrdineConTotali.taxExclusiveAmount);

		TaxInclusiveAmountType taxInclusiveAmountType = new TaxInclusiveAmountType();
		taxInclusiveAmountType.setCurrencyID(EUR);
		taxInclusiveAmountType.setValue(righeOrdineConTotali.taxInclusiveAmount);

		PayableAmountType payableAmountType = new PayableAmountType();
		payableAmountType.setCurrencyID(EUR);
		payableAmountType.setValue(righeOrdineConTotali.payableAmount);

		MonetaryTotalType monetaryTotalType = new MonetaryTotalType();
		monetaryTotalType.setLineExtensionAmount(lineExtensionAmountType);
		monetaryTotalType.setAllowanceTotalAmount(allowanceTotalAmountType);
		monetaryTotalType.setTaxExclusiveAmount(taxExclusiveAmountType);
		monetaryTotalType.setTaxInclusiveAmount(taxInclusiveAmountType);
		monetaryTotalType.setPayableAmount(payableAmountType);

		orderType.setAnticipatedMonetaryTotal(monetaryTotalType);
	}

	private static void setOrderLine(TIPO_ORDINE tipoOrdine, OrderType orderType, RigaOrdine rigaOrdine) {
		NoteType noteType = new NoteType();
		noteType.setValue(rigaOrdine.getNote());

		LineItemType lineItemType = new LineItemType();
		lineItemType.setID(PeppolXmlUtils.getIDType("" + rigaOrdine.getProgressivo()));

		// Vedi algoritmo 3.3.1.4 Quantità linea d’ordine
		lineItemType.setQuantity(getQuantitaLineaOrdine(tipoOrdine, rigaOrdine));

		LineExtensionAmountType lineExtensionAmountType = new LineExtensionAmountType();
		lineExtensionAmountType.setValue(rigaOrdine.getPrezzoUnitario().multiply(rigaOrdine.getQuantita()));
		lineExtensionAmountType.setCurrencyID(EUR);
		lineItemType.setLineExtensionAmount(lineExtensionAmountType);

		PartialDeliveryIndicatorType partialDeliveryIndicatorType = new PartialDeliveryIndicatorType();
		partialDeliveryIndicatorType.setValue(rigaOrdine.getConsegnaParziale() == null ? false : rigaOrdine.getConsegnaParziale().booleanValue());
		lineItemType.setPartialDeliveryIndicator(partialDeliveryIndicatorType);

		// La sezione è presente solo se CPASS_T_ORD_RIGA_ORDINE.percentuale_sconto è valorizzato
		if (rigaOrdine.getPercentualeSconto() != null && rigaOrdine.getPercentualeSconto().compareTo(new BigDecimal(0)) != 0) {
			ChargeIndicatorType chargeIndicatorType = new ChargeIndicatorType();
			chargeIndicatorType.setValue(false);

			AllowanceChargeReasonCodeType allowanceChargeReasonCodeType = new AllowanceChargeReasonCodeType();
			allowanceChargeReasonCodeType.setValue("Sconto 1");

			MultiplierFactorNumericType multiplierFactorNumericType = new MultiplierFactorNumericType();
			multiplierFactorNumericType.setValue(rigaOrdine.getPercentualeSconto().divide(new BigDecimal(100)));

			AmountType amountType = new AmountType();
			amountType.setValue(rigaOrdine.getImportoSconto());

			BaseAmountType baseAmountType = new BaseAmountType();
			baseAmountType.setValue(rigaOrdine.getPrezzoUnitario().multiply(rigaOrdine.getQuantita()));

			AllowanceChargeType allowanceChargeType = new AllowanceChargeType();
			allowanceChargeType.setChargeIndicator(chargeIndicatorType);
			allowanceChargeType.setAllowanceChargeReasonCode(allowanceChargeReasonCodeType);
			allowanceChargeType.setMultiplierFactorNumeric(multiplierFactorNumericType);
			allowanceChargeType.setAmount(amountType);
			allowanceChargeType.setBaseAmount(baseAmountType);

			lineItemType.getAllowanceCharge().add(allowanceChargeType);
		}

		// La sezione è presente solo se CPASS_T_ORD_RIGA_ORDINE.percentuale_sconto2 è valorizzato
		if (rigaOrdine.getPercentualeSconto() != null && rigaOrdine.getPercentualeSconto().compareTo(new BigDecimal(0)) != 0) {
			ChargeIndicatorType chargeIndicatorType = new ChargeIndicatorType();
			chargeIndicatorType.setValue(false);

			AllowanceChargeReasonCodeType allowanceChargeReasonCodeType = new AllowanceChargeReasonCodeType();
			allowanceChargeReasonCodeType.setValue("Sconto 2");

			MultiplierFactorNumericType multiplierFactorNumericType = new MultiplierFactorNumericType();
			multiplierFactorNumericType.setValue(rigaOrdine.getPercentualeSconto2().divide(new BigDecimal(100)));

			AmountType amountType = new AmountType();
			amountType.setValue(rigaOrdine.getImportoSconto2());

			BaseAmountType baseAmountType = new BaseAmountType();
			baseAmountType.setValue(rigaOrdine.getPrezzoUnitario().multiply(rigaOrdine.getQuantita()));

			AllowanceChargeType allowanceChargeType = new AllowanceChargeType();
			allowanceChargeType.setChargeIndicator(chargeIndicatorType);
			allowanceChargeType.setAllowanceChargeReasonCode(allowanceChargeReasonCodeType);
			allowanceChargeType.setMultiplierFactorNumeric(multiplierFactorNumericType);
			allowanceChargeType.setAmount(amountType);
			allowanceChargeType.setBaseAmount(baseAmountType);

			lineItemType.getAllowanceCharge().add(allowanceChargeType);
		}

		// price
		PriceAmountType priceAmountType = new PriceAmountType();
		priceAmountType.setValue(rigaOrdine.getPrezzoUnitario());

		BaseQuantityType baseQuantityType = new BaseQuantityType();
		baseQuantityType.setValue(rigaOrdine.getQuantita());

		PriceType priceType = new PriceType();
		priceType.setPriceAmount(priceAmountType);
		priceType.setBaseQuantity(baseQuantityType);
		lineItemType.setPrice(priceType);

		// item
		DescriptionType descriptionType = new DescriptionType();
		descriptionType.setValue(rigaOrdine.getOds().getDescrizione());

		ItemIdentificationType buyersItemIdentificationType = new ItemIdentificationType();
		buyersItemIdentificationType.setID(PeppolXmlUtils.getIDType(rigaOrdine.getOds().getCodice()));

		ItemIdentificationType sellersItemIdentificationType = null;
		if (rigaOrdine.getListinoFornitore() != null) {
			sellersItemIdentificationType = new ItemIdentificationType();
			sellersItemIdentificationType.setID(PeppolXmlUtils.getIDType(rigaOrdine.getListinoFornitore().getCodiceOds()));
		}

		CommodityClassificationType commodityClassificationType = new CommodityClassificationType();
		// Vedere algoritmo 3.3.1.5 Tag CommodityClassification/ItemClassificationCode
		commodityClassificationType.setItemClassificationCode(getCommodityClassificationItemClassificationCode(rigaOrdine));

		PercentType percentType = new PercentType();
		percentType.setValue(new BigDecimal(rigaOrdine.getAliquoteIva().getPercentuale().intValue()));

		TaxSchemeType taxSchemeType = new TaxSchemeType();
		taxSchemeType.setID(PeppolXmlUtils.getIDType("VAT"));

		TaxCategoryType taxCategoryType = new TaxCategoryType();
		taxCategoryType.setID(PeppolXmlUtils.getIDType(rigaOrdine.getAliquoteIva().getCodificaPeppol()));
		taxCategoryType.setPercent(percentType);
		taxCategoryType.setTaxScheme(taxSchemeType);

		ItemType itemType = new ItemType();
		itemType.getDescription().add(descriptionType);
		itemType.setBuyersItemIdentification(buyersItemIdentificationType);
		itemType.setSellersItemIdentification(sellersItemIdentificationType);
		itemType.getCommodityClassification().add(commodityClassificationType);
		itemType.getClassifiedTaxCategory().add(taxCategoryType);
		lineItemType.setItem(itemType);

		OrderLineType orderLineType = new OrderLineType();
		orderLineType.getNote().add(noteType);
		orderLineType.setLineItem(lineItemType);

		orderType.getOrderLine().add(orderLineType);
	}

	private static QuantityType getQuantitaLineaOrdine(TIPO_ORDINE tipoOrdine, RigaOrdine rigaOrdine) {
		QuantityType quantityType = new QuantityType();
		quantityType.setUnitCode(rigaOrdine.getUnitaMisura().getCodice());

		if (tipoOrdine.equals(TIPO_ORDINE.INIZIALE) || tipoOrdine.equals(TIPO_ORDINE.SOSTITUTIVO)) {
			quantityType.setValue(rigaOrdine.getQuantita());
		} else if (tipoOrdine.equals(TIPO_ORDINE.REVOCA)) {
			quantityType.setValue(new BigDecimal(0));
		}

		return quantityType;
	}

	private static ItemClassificationCodeType getCommodityClassificationItemClassificationCode(RigaOrdine rigaOrdine) {
		ItemClassificationCodeType itemClassificationCodeType = new ItemClassificationCodeType();
		itemClassificationCodeType.setListID("STI");
		itemClassificationCodeType.setListVersionID("19.0501");
		itemClassificationCodeType.setValue(rigaOrdine.getOds().getCpv().getCodice());
		return itemClassificationCodeType;
	}

}
