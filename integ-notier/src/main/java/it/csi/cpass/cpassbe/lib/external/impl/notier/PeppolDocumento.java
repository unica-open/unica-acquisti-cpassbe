/*-
 * ========================LICENSE_START=================================
 * CPASS BackEnd - INTEGRATION submodule - NotiER
 * %%
 * Copyright (C) 2019 - 2025 CSI Piemonte
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

import org.apache.commons.lang3.StringUtils;

import it.csi.cpass.cpassbe.lib.dto.SettoreIndirizzo;
import it.csi.cpass.cpassbe.lib.dto.ord.Destinatario;
import it.csi.cpass.cpassbe.lib.dto.ord.RigaOrdine;
import it.csi.cpass.cpassbe.lib.dto.ord.TestataOrdine;
import it.csi.cpass.cpassbe.lib.dto.ord.nso.Documento;
import it.csi.cpass.cpassbe.lib.dto.ord.nso.TipoOrdineNSO;
import it.csi.cpass.cpassbe.lib.external.dto.RigheOrdineConTotali;
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
import oasis.names.specification.ubl.schema.xsd.commonbasiccomponents_2.AllowanceChargeReasonType;
import oasis.names.specification.ubl.schema.xsd.commonbasiccomponents_2.AmountType;
import oasis.names.specification.ubl.schema.xsd.commonbasiccomponents_2.BaseAmountType;
import oasis.names.specification.ubl.schema.xsd.commonbasiccomponents_2.ChargeIndicatorType;
import oasis.names.specification.ubl.schema.xsd.commonbasiccomponents_2.CityNameType;
import oasis.names.specification.ubl.schema.xsd.commonbasiccomponents_2.CompanyIDType;
import oasis.names.specification.ubl.schema.xsd.commonbasiccomponents_2.CountrySubentityType;
import oasis.names.specification.ubl.schema.xsd.commonbasiccomponents_2.CustomerReferenceType;
import oasis.names.specification.ubl.schema.xsd.commonbasiccomponents_2.CustomizationIDType;
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

	public static Documento getXmlDocumento(TipoOrdineNSO tipoOrdineNSO, TestataOrdine testataOrdine, Destinatario destinatarioOrdine,
			RigheOrdineConTotali righeOrdineConTotali, int maxProgressivoInvio, Map<String, String> params, SettoreIndirizzo indirizzoPrincipaleSettoreEmittente) throws DatatypeConfigurationException {
		
		Documento documento = new Documento();
		String orderDocumentReferenceId = null;
		String orderId = null;
		Date issueDate = null;
		
		OrderType orderType = new OrderType();

		String customizationID = getParameter(params, NotiERConfigurationParams.NSO_CUSTOMIZATION_ID);
		CustomizationIDType customizationIDType = new CustomizationIDType();
		customizationIDType.setValue(customizationID);
		orderType.setCustomizationID(customizationIDType);

		String profileID = getParameter(params, NotiERConfigurationParams.NSO_PROFILE_ID);
		ProfileIDType profileIDType = new ProfileIDType();
		profileIDType.setValue(profileID);
		orderType.setProfileID(profileIDType);

		boolean unicoDestinatario = isUnicoDestinatario(getParameter(params, NotiERConfigurationParams.NSO_UNICO_DESTINATARIO));
		orderId = PeppolXmlUtils.getOrderId(testataOrdine, destinatarioOrdine, maxProgressivoInvio, unicoDestinatario);
		orderType.setID(PeppolXmlUtils.getIDType(orderId));

		IssueDateType issueDateType = new IssueDateType();
		if (unicoDestinatario)
			issueDate = getDeterminazioneDataInvioNSO(destinatarioOrdine);
		else
			issueDate = getDeterminazioneDataInvioNSO(righeOrdineConTotali.getRigaOrdines().get(0).getDestinatario());
		issueDateType.setValue(PeppolXmlUtils.getXMLGregorianCalendar(issueDate));
		orderType.setIssueDate(issueDateType);

		OrderTypeCodeType orderTypeCodeType = new OrderTypeCodeType();
		orderTypeCodeType.setListID("UNCL1001"); // da esempio ricevuto in assistenza
		orderTypeCodeType.setValue("220");
		orderType.setOrderTypeCode(orderTypeCodeType);

		if(!StringUtils.isBlank(testataOrdine.getDescrizione())) {
			NoteType noteType = new NoteType();
			noteType.setValue(testataOrdine.getDescrizione());
			orderType.getNote().add(noteType);
		}

		DocumentCurrencyCodeType documentCurrencyCodeType = new DocumentCurrencyCodeType();
		documentCurrencyCodeType.setListID("ISO4217");
		documentCurrencyCodeType.setValue(EUR);
		orderType.setDocumentCurrencyCode(documentCurrencyCodeType);

		orderType.setCustomerReference(getCustomerReference(testataOrdine, destinatarioOrdine));

		if (testataOrdine.getDataScadenza() != null) {
			EndDateType endDateType = new EndDateType();
			endDateType.setValue(PeppolXmlUtils.getXMLGregorianCalendar(testataOrdine.getDataScadenza()));
			PeriodType periodType = new PeriodType();
			periodType.setEndDate(endDateType);
			orderType.getValidityPeriod().add(periodType);
		}
		
		if (!tipoOrdineNSO.equals(TipoOrdineNSO.INIZIALE)) {
			DocumentReferenceType documentReferenceType = new DocumentReferenceType();
			orderDocumentReferenceId = getOrderDocumentReferenceId(tipoOrdineNSO, orderId, issueDate, testataOrdine.getUfficio().getCodice());
			documentReferenceType.setID(PeppolXmlUtils.getIDType(orderDocumentReferenceId));
			orderType.getOrderDocumentReference().add(documentReferenceType);
		}
		
		DocumentReferenceType drt = new DocumentReferenceType();
		if (!StringUtils.isBlank(testataOrdine.getCig())) {
			drt.setID(PeppolXmlUtils.getIDType(testataOrdine.getCig()));
		} else {
			drt.setID(PeppolXmlUtils.getIDType(testataOrdine.getMotiviEsclusioneCig().getCodiceNso()));
		}
		orderType.setOriginatorDocumentReference(drt);
		
		if (testataOrdine.getProvvedimento() != null) {
			DocumentReferenceType documentReferenceType = new DocumentReferenceType();
			documentReferenceType.setID(PeppolXmlUtils.getIDType(testataOrdine.getProvvedimento().getAnno() + "/" + testataOrdine.getProvvedimento().getNumero()));
			DocumentTypeType documentTypeType = new DocumentTypeType();
//			documentTypeType.setValue("Delibera");
			documentTypeType.setValue(testataOrdine.getProvvedimento().getProvvedimentoTipo().getDescrizione());
			documentReferenceType.setDocumentType(documentTypeType);
			orderType.getAdditionalDocumentReference().add(documentReferenceType);
		}
		ContractType contractType = new ContractType();
		contractType.setID(PeppolXmlUtils.getIDType(testataOrdine.getTipoProceduraOrd().getDescrizione() + " - " + testataOrdine.getNumeroProcedura()));
		orderType.getContract().add(contractType);

//		if (unicoDestinatario)
		setBuyerCustomerParty(orderType, testataOrdine, indirizzoPrincipaleSettoreEmittente);
		setSellerSupplierParty(orderType, testataOrdine);
		setDelivery(orderType, testataOrdine, destinatarioOrdine, unicoDestinatario);
		if (!tipoOrdineNSO.equals(TipoOrdineNSO.REVOCA))
			setTotali(orderType, righeOrdineConTotali);

		
		/* TODO N.B. Nel caso di ordine iniziale o ordine sostitutivo, questa sezione si ripete per ogni riga d’ordine presente in CPASS_T_ORD_ORDINE_RIGA.
		Nel caso di ordine di revoca, invece c’è una sola sezione OrderLine, che per comodità viene riportata nel paragrafo 4.3.1.6Sezione OrderLine per un ordine di revoca*/
		
		if (tipoOrdineNSO.equals(TipoOrdineNSO.INIZIALE) || tipoOrdineNSO.equals(TipoOrdineNSO.SOSTITUTIVO)) {
			int lineItemId = 1;  
			for (RigaOrdine rigaOrdine : righeOrdineConTotali.getRigaOrdines()) {
				setOrderLine(tipoOrdineNSO, orderType, rigaOrdine, unicoDestinatario, lineItemId);
				lineItemId++;
			}
		}
		else if (tipoOrdineNSO.equals(TipoOrdineNSO.REVOCA)) {
			setOrderLineRevoca(tipoOrdineNSO,orderType,righeOrdineConTotali.getRigaOrdines().get(0));
		}

		documento.setXml(JAXBUtility.marshall(orderType));
		documento.setOrderId(orderId);
		documento.setOrderDocumentReferenceId(orderDocumentReferenceId);
		return documento;
	}

	private static String getParameter(Map<String, String> params, ConfigurationParam param) {
		return params.get(param.getParamName());
	}


	private static Date getDeterminazioneDataInvioNSO(Destinatario destinatarioOrdine) throws DatatypeConfigurationException {
		Date date = null;
		if (destinatarioOrdine.getDataInvioNso() == null) {
			date = new Date();
		} else {
			date = destinatarioOrdine.getDataInvioNso();
		}
		return date;
//		return PeppolXmlUtils.getXMLGregorianCalendar(date);
	}

	private static CustomerReferenceType getCustomerReference(TestataOrdine testataOrdine, Destinatario destinatarioOrdine) {
		String value = null;

		// Se è popolato CPASS_T_ORD_DESTINATARIO_ORDINE.contatto , utilizzare il valore di questo campo,
		if (destinatarioOrdine != null && !StringUtils.isBlank(destinatarioOrdine.getContatto())) {
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

	private static String getOrderDocumentReferenceId(TipoOrdineNSO tipoOrdineNSO, String orderId, Date issueDate, String endPointId) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String statoOrdine = null;
		if (tipoOrdineNSO.equals(TipoOrdineNSO.REVOCA)) {
			statoOrdine = "Cancelled";
		} else if (tipoOrdineNSO.equals(TipoOrdineNSO.SOSTITUTIVO)) {
			statoOrdine = "Revised";
		}
//		String value = PeppolXmlUtils.getOrderId(testataOrdine, destinatarioOrdine, maxProgressivoInvio,unicoDestinatario) + "#" + sdf.format(destinatarioOrdine.getDataInvioNso()) + "#"
//				+ testataOrdine.getUfficio().getCodice() + "#" + statoOrdine;
		String value = orderId + "#" + sdf.format(issueDate) + "#"+ endPointId + "#" + statoOrdine;
		return value;
	}

	private static void setBuyerCustomerParty(OrderType orderType, TestataOrdine testataOrdine, SettoreIndirizzo indirizzoPrincipaleSettoreEmittente) {
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
		streetNameType.setValue(indirizzoPrincipaleSettoreEmittente.getIndirizzo() + "," + indirizzoPrincipaleSettoreEmittente.getNumCivico());

		CityNameType cityNameType = new CityNameType();
		cityNameType.setValue(indirizzoPrincipaleSettoreEmittente.getLocalita());

		PostalZoneType postalZoneType = new PostalZoneType();
		postalZoneType.setValue(indirizzoPrincipaleSettoreEmittente.getCap());
		
		CountrySubentityType countrySubentityType = new CountrySubentityType();
		countrySubentityType.setValue(indirizzoPrincipaleSettoreEmittente.getProvincia());
		
		IdentificationCodeType identificationCodeType = new IdentificationCodeType();
		identificationCodeType.setValue("IT");
		identificationCodeType.setListID("ISO3166-1:Alpha2");
		CountryType countryType = new CountryType();
		countryType.setIdentificationCode(identificationCodeType);

		AddressType addressType = new AddressType();
		addressType.setStreetName(streetNameType);
		addressType.setCityName(cityNameType);
		if (!StringUtils.isBlank(postalZoneType.getValue()))
			addressType.setPostalZone(postalZoneType);
		if (!StringUtils.isBlank(countrySubentityType.getValue()))
			addressType.setCountrySubentity(countrySubentityType);
		addressType.setCountry(countryType);
		partyType.setPostalAddress(addressType);

		// PartyTaxScheme
		CompanyIDType companyIDType = new CompanyIDType();
		companyIDType.setValue("IT" + testataOrdine.getSettore().getEnte().getCodiceFiscale());
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
		if (!StringUtils.isBlank(testataOrdine.getFornitore().getPartitaIva())) {
			endpointIDType.setSchemeID("0211");
			endpointIDType.setValue("IT" + testataOrdine.getFornitore().getPartitaIva());
		} else {
			endpointIDType.setSchemeID("0210");
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

		CountrySubentityType countrySubentityType = new CountrySubentityType();
		countrySubentityType.setValue(testataOrdine.getFornitore().getProvincia());

		IdentificationCodeType identificationCodeType = new IdentificationCodeType();
		identificationCodeType.setValue("IT");
//		identificationCodeType.setListID("ISO3166-1:Alpha2");
		CountryType countryType = new CountryType();
		countryType.setIdentificationCode(identificationCodeType);

		AddressType addressType = new AddressType();
		addressType.setStreetName(streetNameType);
		addressType.setCityName(cityNameType);
		if (postalZoneType.getValue()!=null)
			addressType.setPostalZone(postalZoneType);
		addressType.setCountrySubentity(countrySubentityType);
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

	private static void setDelivery(OrderType orderType, TestataOrdine testataOrdine, Destinatario destinatarioOrdine, boolean unicoDestinatario) throws DatatypeConfigurationException {
		DeliveryType deliveryType = new DeliveryType();
		
		if (unicoDestinatario) {
			// inzio - DeliveryLocation
			StreetNameType streetNameType = new StreetNameType();
			streetNameType.setValue(destinatarioOrdine.getIndirizzo() + "," + destinatarioOrdine.getNumCivico());
	
			CityNameType cityNameType = new CityNameType();
			cityNameType.setValue(destinatarioOrdine.getLocalita());
	
			PostalZoneType postalZoneType = new PostalZoneType();
			postalZoneType.setValue(destinatarioOrdine.getCap());
	
			IdentificationCodeType identificationCodeType = new IdentificationCodeType();
			identificationCodeType.setValue("IT");
	//		identificationCodeType.setListID("ISO3166-1:Alpha2");
			CountryType countryType = new CountryType();
			countryType.setIdentificationCode(identificationCodeType);
	
			AddressType addressType = new AddressType();
			addressType.setStreetName(streetNameType);
			addressType.setCityName(cityNameType);
			if (postalZoneType.getValue()!=null)
				addressType.setPostalZone(postalZoneType);
			addressType.setCountry(countryType);
	
			LocationType locationType = new LocationType();
			locationType.setID(PeppolXmlUtils.getIDType("" + destinatarioOrdine.getProgressivo()));
			locationType.setAddress(addressType);
			// fine - DeliveryLocation
			deliveryType.setDeliveryLocation(locationType);
		}
		// inizio periodType
		StartDateType startDateType = null;
		EndDateType endDateType = null;

		if (testataOrdine.getConsegnaDataA() != null) {
			endDateType = new EndDateType();
			startDateType = new StartDateType();
			endDateType.setValue(PeppolXmlUtils.getXMLGregorianCalendar(testataOrdine.getConsegnaDataA()));
			startDateType.setValue(PeppolXmlUtils.getXMLGregorianCalendar(testataOrdine.getConsegnaDataA())); // se endDate è valorizzato startDate deve essere valorizzato, eventualmente se presente è sovrascritto con dataConsegnaDa 
		}

		if (testataOrdine.getConsegnaDataDa() != null) {
			startDateType.setValue(PeppolXmlUtils.getXMLGregorianCalendar(testataOrdine.getConsegnaDataDa()));
		}

		PeriodType periodType = new PeriodType();
		periodType.setStartDate(startDateType);
		periodType.setEndDate(endDateType);
		// fine periodType

		if (unicoDestinatario) {
			// inizio DeliveryParty
			String destinatarioSettore = destinatarioOrdine.getSettore().getCodice() + " - " + destinatarioOrdine.getSettore().getDescrizione();
			PartyNameType partyNameType = new PartyNameType();
			partyNameType.setName(PeppolXmlUtils.getNameType(destinatarioSettore));
			PartyType partyType = new PartyType();
			partyType.getPartyName().add(partyNameType);
	
			if (!StringUtils.isBlank(destinatarioOrdine.getContatto()) || !StringUtils.isBlank(destinatarioOrdine.getTelefono()) || !StringUtils.isBlank(destinatarioOrdine.getEmail())) {
				ContactType contactType = new ContactType();
				if (!StringUtils.isBlank(destinatarioOrdine.getContatto()))
					contactType.setName(PeppolXmlUtils.getNameType(destinatarioOrdine.getContatto()));
		
				if (!StringUtils.isBlank(destinatarioOrdine.getTelefono())) {
					TelephoneType telephoneType = new TelephoneType();
					telephoneType.setValue(destinatarioOrdine.getTelefono());
					contactType.setTelephone(telephoneType);
				}
				if (!StringUtils.isBlank(destinatarioOrdine.getEmail())) {
					ElectronicMailType electronicMailType = new ElectronicMailType();
					electronicMailType.setValue(destinatarioOrdine.getEmail());
					contactType.setElectronicMail(electronicMailType);
				}
				partyType.setContact(contactType);
			}
			// fine DeliveryParty
			deliveryType.setDeliveryParty(partyType);
		}

		if(periodType.getStartDate()!=null || periodType.getEndDate()!=null)
			deliveryType.setRequestedDeliveryPeriod(periodType);

		if (deliveryType.getDeliveryLocation()!=null || deliveryType.getDeliveryParty()!=null || deliveryType.getRequestedDeliveryPeriod()!=null)
			orderType.getDelivery().add(deliveryType);
	}

	private static void setTotali(OrderType orderType, RigheOrdineConTotali righeOrdineConTotali) {
		TaxAmountType taxAmountType = new TaxAmountType();
		taxAmountType.setCurrencyID(EUR);
		taxAmountType.setValue(PeppolXmlUtils.getBigDecimalRound(righeOrdineConTotali.taxAmount));

		TaxTotalType taxTotalType = new TaxTotalType();
		taxTotalType.setTaxAmount(taxAmountType);

		orderType.getTaxTotal().add(taxTotalType);

		LineExtensionAmountType lineExtensionAmountType = new LineExtensionAmountType();
		lineExtensionAmountType.setCurrencyID(EUR);
		lineExtensionAmountType.setValue(PeppolXmlUtils.getBigDecimalRound(righeOrdineConTotali.lineExtensionAmount));

//		AllowanceTotalAmountType allowanceTotalAmountType = new AllowanceTotalAmountType();
//		allowanceTotalAmountType.setCurrencyID(EUR);
//		allowanceTotalAmountType.setValue(PeppolXmlUtils.getBigDecimalRound(righeOrdineConTotali.allowanceTotalAmount));

		TaxExclusiveAmountType taxExclusiveAmountType = new TaxExclusiveAmountType();
		taxExclusiveAmountType.setCurrencyID(EUR);
		taxExclusiveAmountType.setValue(PeppolXmlUtils.getBigDecimalRound(righeOrdineConTotali.taxExclusiveAmount));

		TaxInclusiveAmountType taxInclusiveAmountType = new TaxInclusiveAmountType();
		taxInclusiveAmountType.setCurrencyID(EUR);
		taxInclusiveAmountType.setValue(PeppolXmlUtils.getBigDecimalRound(righeOrdineConTotali.taxInclusiveAmount));
		
		PayableAmountType payableAmountType = new PayableAmountType();
		payableAmountType.setCurrencyID(EUR);
		payableAmountType.setValue(PeppolXmlUtils.getBigDecimalRound(righeOrdineConTotali.payableAmount));

		MonetaryTotalType monetaryTotalType = new MonetaryTotalType();
		monetaryTotalType.setLineExtensionAmount(lineExtensionAmountType);
//		monetaryTotalType.setAllowanceTotalAmount(allowanceTotalAmountType);
		monetaryTotalType.setTaxExclusiveAmount(taxExclusiveAmountType);
		monetaryTotalType.setTaxInclusiveAmount(taxInclusiveAmountType);
		monetaryTotalType.setPayableAmount(payableAmountType);

		orderType.setAnticipatedMonetaryTotal(monetaryTotalType);
	}

	private static void setOrderLine(TipoOrdineNSO tipoOrdineNSO, OrderType orderType, RigaOrdine rigaOrdine, boolean unicoDestinatario, int lineItemId)  throws DatatypeConfigurationException{
		NoteType noteType = new NoteType();
		noteType.setValue(rigaOrdine.getNote());

		LineItemType lineItemType = new LineItemType();
//		lineItemType.setID(PeppolXmlUtils.getIDType("" + rigaOrdine.getProgressivo()));
		lineItemType.setID(PeppolXmlUtils.getIDType("" + lineItemId)); // l'id è un progressivo univo all'interno del documento, non è possibile usare il progressivo della riga che si ripete per destinatario differente

		// Vedi algoritmo 3.3.1.4 Quantità linea d’ordine
		lineItemType.setQuantity(getQuantitaLineaOrdine(tipoOrdineNSO, rigaOrdine));

		LineExtensionAmountType lineExtensionAmountType = new LineExtensionAmountType();
		lineExtensionAmountType.setValue(PeppolXmlUtils.getBigDecimalRound(rigaOrdine.getImportoNetto()));
		lineExtensionAmountType.setCurrencyID(EUR);
		lineItemType.setLineExtensionAmount(lineExtensionAmountType);

		PartialDeliveryIndicatorType partialDeliveryIndicatorType = new PartialDeliveryIndicatorType();
		partialDeliveryIndicatorType.setValue(rigaOrdine.getConsegnaParziale() == null ? false : rigaOrdine.getConsegnaParziale().booleanValue());
		lineItemType.setPartialDeliveryIndicator(partialDeliveryIndicatorType);
		
		
		if (!unicoDestinatario) {
//			DeliveryTermsType deliveryTermsType = new DeliveryTermsType();
			DeliveryType deliveryType = new DeliveryType();
			
			// ID
			String codiceFiscaleEnte = rigaOrdine.getDestinatario().getSettore().getEnte().getCodiceFiscale();
			deliveryType.setID(PeppolXmlUtils.getIDType(codiceFiscaleEnte + "-" + rigaOrdine.getDestinatario().getIndirizzoCodice()));

			// RequestedDeliveryPeriod
			StartDateType startDateType = new StartDateType();
			EndDateType endDateType = null;
			Date consegnaDataDa = rigaOrdine.getDestinatario().getTestataOrdine().getConsegnaDataDa();
			Date consegnaDataA = rigaOrdine.getDestinatario().getTestataOrdine().getConsegnaDataA();
			
			if (consegnaDataDa == null && consegnaDataA == null)
				startDateType.setValue(PeppolXmlUtils.getXMLGregorianCalendar(new Date()));
			else if (consegnaDataDa != null) {
				startDateType.setValue(PeppolXmlUtils.getXMLGregorianCalendar(consegnaDataDa));
			}
			if (consegnaDataA != null) {
				endDateType = new EndDateType();
				endDateType.setValue(PeppolXmlUtils.getXMLGregorianCalendar(consegnaDataA));
			}

			PeriodType periodType = new PeriodType();
			periodType.setStartDate(startDateType);
			if (endDateType!=null)
				periodType.setEndDate(endDateType);
			deliveryType.setRequestedDeliveryPeriod(periodType);
			
			lineItemType.getDelivery().add(deliveryType);
		}

		// La sezione è presente solo se CPASS_T_ORD_RIGA_ORDINE.percentuale_sconto è valorizzato
		if (rigaOrdine.getPercentualeSconto() != null && rigaOrdine.getPercentualeSconto().compareTo(new BigDecimal(0)) != 0) {
			ChargeIndicatorType chargeIndicatorType = new ChargeIndicatorType();
			chargeIndicatorType.setValue(false);

			AllowanceChargeReasonCodeType allowanceChargeReasonCodeType = new AllowanceChargeReasonCodeType();
			allowanceChargeReasonCodeType.setValue("104");

			AllowanceChargeReasonType allowanceChargeReasonType = new AllowanceChargeReasonType();
			allowanceChargeReasonType.setValue("Sconto 1");

			MultiplierFactorNumericType multiplierFactorNumericType = new MultiplierFactorNumericType();
//			multiplierFactorNumericType.setValue(rigaOrdine.getPercentualeSconto().divide(new BigDecimal(100)));
			multiplierFactorNumericType.setValue(rigaOrdine.getPercentualeSconto());

			AmountType amountType = new AmountType();
			amountType.setValue(PeppolXmlUtils.getBigDecimalRound(rigaOrdine.getImportoSconto()));
			amountType.setCurrencyID(EUR);
			
			BaseAmountType baseAmountType = new BaseAmountType();
			baseAmountType.setValue(PeppolXmlUtils.getBigDecimalRound(rigaOrdine.getPrezzoUnitario().multiply(rigaOrdine.getQuantita())));
			baseAmountType.setCurrencyID(EUR);

			AllowanceChargeType allowanceChargeType = new AllowanceChargeType();
			allowanceChargeType.setChargeIndicator(chargeIndicatorType);
			allowanceChargeType.setAllowanceChargeReasonCode(allowanceChargeReasonCodeType);
			allowanceChargeType.getAllowanceChargeReason().add(allowanceChargeReasonType);
			allowanceChargeType.setMultiplierFactorNumeric(multiplierFactorNumericType);
			allowanceChargeType.setAmount(amountType);
			allowanceChargeType.setBaseAmount(baseAmountType);

			lineItemType.getAllowanceCharge().add(allowanceChargeType);
		}

		// La sezione è presente solo se CPASS_T_ORD_RIGA_ORDINE.percentuale_sconto2 è valorizzato
		if (rigaOrdine.getPercentualeSconto2() != null && rigaOrdine.getPercentualeSconto2().compareTo(new BigDecimal(0)) != 0) {
			ChargeIndicatorType chargeIndicatorType = new ChargeIndicatorType();
			chargeIndicatorType.setValue(false);

			AllowanceChargeReasonCodeType allowanceChargeReasonCodeType = new AllowanceChargeReasonCodeType();
			allowanceChargeReasonCodeType.setValue("104");
			
			AllowanceChargeReasonType allowanceChargeReasonType = new AllowanceChargeReasonType();
			allowanceChargeReasonType.setValue("Sconto 2");
			
			MultiplierFactorNumericType multiplierFactorNumericType = new MultiplierFactorNumericType();
			multiplierFactorNumericType.setValue(rigaOrdine.getPercentualeSconto2());

			AmountType amountType = new AmountType();
			amountType.setValue(PeppolXmlUtils.getBigDecimalRound(rigaOrdine.getImportoSconto2()));
			amountType.setCurrencyID(EUR);

			BaseAmountType baseAmountType = new BaseAmountType();
			baseAmountType.setValue(PeppolXmlUtils.getBigDecimalRound((rigaOrdine.getPrezzoUnitario().multiply(rigaOrdine.getQuantita())).subtract(rigaOrdine.getImportoSconto())));
			baseAmountType.setCurrencyID(EUR);

			AllowanceChargeType allowanceChargeType = new AllowanceChargeType();
			allowanceChargeType.setChargeIndicator(chargeIndicatorType);
			allowanceChargeType.setAllowanceChargeReasonCode(allowanceChargeReasonCodeType);
			allowanceChargeType.getAllowanceChargeReason().add(allowanceChargeReasonType);
			allowanceChargeType.setMultiplierFactorNumeric(multiplierFactorNumericType);
			allowanceChargeType.setAmount(amountType);
			allowanceChargeType.setBaseAmount(baseAmountType);

			lineItemType.getAllowanceCharge().add(allowanceChargeType);
		}

		// price
		PriceAmountType priceAmountType = new PriceAmountType();
		priceAmountType.setValue(PeppolXmlUtils.getBigDecimalRound(rigaOrdine.getPrezzoUnitario(), 5));
		priceAmountType.setCurrencyID(EUR);

//		BaseQuantityType baseQuantityType = new BaseQuantityType();
//		baseQuantityType.setValue(rigaOrdine.getQuantita());

		PriceType priceType = new PriceType();
		priceType.setPriceAmount(priceAmountType);
//		priceType.setBaseQuantity(baseQuantityType);
		lineItemType.setPrice(priceType);

		// item
//		DescriptionType descriptionType = new DescriptionType();
//		descriptionType.setValue(rigaOrdine.getOds().getDescrizione());

		ItemIdentificationType buyersItemIdentificationType = new ItemIdentificationType();
		buyersItemIdentificationType.setID(PeppolXmlUtils.getIDType(rigaOrdine.getOds().getCodice()));

		ItemIdentificationType sellersItemIdentificationType = null;
		sellersItemIdentificationType = new ItemIdentificationType();
		sellersItemIdentificationType.setID(PeppolXmlUtils.getIDType(rigaOrdine.getListinoFornitore().getCodiceOds()));

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
		itemType.setName(PeppolXmlUtils.getNameType(rigaOrdine.getOds().getDescrizione()));
//		itemType.getDescription().add(descriptionType);
		itemType.setBuyersItemIdentification(buyersItemIdentificationType);
		itemType.setSellersItemIdentification(sellersItemIdentificationType);
		itemType.getCommodityClassification().add(commodityClassificationType);
		itemType.getClassifiedTaxCategory().add(taxCategoryType);
		lineItemType.setItem(itemType);

		OrderLineType orderLineType = new OrderLineType();
		if (!StringUtils.isBlank(noteType.getValue()))
			orderLineType.getNote().add(noteType);
		orderLineType.setLineItem(lineItemType);

		orderType.getOrderLine().add(orderLineType);
	}
	
	private static void setOrderLineRevoca (TipoOrdineNSO tipoOrdineNSO, OrderType orderType, RigaOrdine rigaOrdine) {

		LineItemType lineItemType = new LineItemType();

		ItemType itemType = new ItemType();
		itemType.setName(PeppolXmlUtils.getNameType("NA"));

		lineItemType.setItem(itemType);
		lineItemType.setID(PeppolXmlUtils.getIDType("NA"));
		// Vedi algoritmo 3.3.1.4 Quantità linea d’ordine
		lineItemType.setQuantity(getQuantitaLineaOrdine(tipoOrdineNSO, rigaOrdine));

		OrderLineType orderLineType = new OrderLineType();
		orderLineType.setLineItem(lineItemType);
		orderType.getOrderLine().add(orderLineType);
		
	}

	private static QuantityType getQuantitaLineaOrdine(TipoOrdineNSO tipoOrdineNSO, RigaOrdine rigaOrdine) {
		QuantityType quantityType = new QuantityType();
		quantityType.setUnitCode(rigaOrdine.getUnitaMisura().getCodice());

		if (tipoOrdineNSO.equals(TipoOrdineNSO.INIZIALE) || tipoOrdineNSO.equals(TipoOrdineNSO.SOSTITUTIVO)) {
			quantityType.setValue(rigaOrdine.getQuantita());
		} else if (tipoOrdineNSO.equals(TipoOrdineNSO.REVOCA)) {
			quantityType.setValue(new BigDecimal(0));
		}

		return quantityType;
	}

	private static ItemClassificationCodeType getCommodityClassificationItemClassificationCode(RigaOrdine rigaOrdine) {
		ItemClassificationCodeType itemClassificationCodeType = new ItemClassificationCodeType();
		itemClassificationCodeType.setListID("STI");
		itemClassificationCodeType.setListVersionID("19.0501");// eventualmente provare ad impostare a UNCL7143
		itemClassificationCodeType.setValue(rigaOrdine.getOds().getCpv().getCodice());
		return itemClassificationCodeType;
	}

	private static boolean isUnicoDestinatario(String unicoDestinatario) {
		return unicoDestinatario.equalsIgnoreCase("TRUE");
	}
}
