/*-
 * ========================LICENSE_START=================================
 * CPASS BackEnd - EJB submodule
 * %%
 * Copyright (C) 2019 - 2021 CSI Piemonte
 * %%
 * SPDX-FileCopyrightText: Copyright 2019 - 2021 | CSI Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 * =========================LICENSE_END==================================
 */
package it.csi.cpass.cpassbe.ejb.business.be.service.impl.mepa;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.xml.datatype.XMLGregorianCalendar;

import it.csi.cpass.cpassbe.ejb.business.be.dad.DecodificaDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.ScaricoMepaRigaDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.ScaricoMepaScontiDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.ScaricoMepaTestataDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.ScaricoMepaXmlDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.base.BaseService;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.mepa.PostUploadScaricoMepaXmlRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.mepa.PostUploadScaricoMepaXmlResponse;
import it.csi.cpass.cpassbe.ejb.entity.CpassTUfficio;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;
import it.csi.cpass.cpassbe.lib.dto.ApiError;
import it.csi.cpass.cpassbe.lib.dto.Stato;
import it.csi.cpass.cpassbe.lib.dto.error.MsgCpassOrd;
import it.csi.cpass.cpassbe.lib.dto.ord.mepa.ScaricoMepaRiga;
import it.csi.cpass.cpassbe.lib.dto.ord.mepa.ScaricoMepaSconti;
import it.csi.cpass.cpassbe.lib.dto.ord.mepa.ScaricoMepaTestata;
import it.csi.cpass.cpassbe.lib.dto.ord.mepa.ScaricoMepaXml;
import it.csi.cpass.cpassbe.lib.util.serialization.JAXBUtility;
import it.csi.cpass.cpassbe.lib.util.threadlocal.CpassThreadLocalContainer;
import oasis.names.specification.ubl.schema.xsd.commonaggregatecomponents_2.AllowanceChargeType;
import oasis.names.specification.ubl.schema.xsd.commonaggregatecomponents_2.DocumentReferenceType;
import oasis.names.specification.ubl.schema.xsd.commonaggregatecomponents_2.OrderLineType;
import oasis.names.specification.ubl.schema.xsd.order_2.OrderType;


public class PostUploadScaricoMepaXmlService extends BaseService<PostUploadScaricoMepaXmlRequest,PostUploadScaricoMepaXmlResponse> {

	private final DecodificaDad decodificaDad;
	private final ScaricoMepaTestataDad scaricoMepaTestataDad;
	private final ScaricoMepaRigaDad scaricoMepaRigaDad;
	private final ScaricoMepaScontiDad scaricoMepaScontiDad;
	private final ScaricoMepaXmlDad scaricoMepaXmlDad;

	public PostUploadScaricoMepaXmlService(ConfigurationHelper configurationHelper, ScaricoMepaRigaDad scaricoMepaRigaDad,ScaricoMepaTestataDad scaricoMepaTestataDad,
			ScaricoMepaScontiDad scaricoMepaScontiDad,ScaricoMepaXmlDad ScaricoMepaXmlDad, DecodificaDad decodificaDad) {
		super(configurationHelper);
		this.decodificaDad = decodificaDad;
		this.scaricoMepaTestataDad = scaricoMepaTestataDad;
		this.scaricoMepaRigaDad = scaricoMepaRigaDad;
		this.scaricoMepaScontiDad = scaricoMepaScontiDad;
		this.scaricoMepaXmlDad = ScaricoMepaXmlDad;
	}

	@Override
	protected void checkServiceParams() {
		checkNotNull(request.getAttachmentScaricoMepa(),"file mepa xml");
		checkNotNull(request.getFileXml(),"file name");
	}

	@Override
	protected void execute() {
		final String methodName = "execute";

		final List<ApiError> apiErrors = new ArrayList<>();
		CpassThreadLocalContainer.SETTORE_UTENTE.get();
		String accountingContactID = null;
		String endpointID = null;
		boolean hasErrors = false;


		try {

			final String stringXmlRes = new String(request.getAttachmentScaricoMepa()); //convertFileXmlToString();
			final OrderType ot = JAXBUtility.unmarshall(stringXmlRes, OrderType.class);
			boolean ipaConditionMet = false;
			boolean isIpaInTabUffEndpoint = false;
			boolean isIpaInTabUffAcContact = false;
			boolean isIpaInTabUffAddDocRef = false;

			if (ot.getBuyerCustomerParty().getParty() != null
					&& ot.getBuyerCustomerParty().getParty().getEndpointID() != null) {
				endpointID = ot.getBuyerCustomerParty().getParty().getEndpointID().getValue();
				isIpaInTabUffEndpoint = isIPACodeInTableUfficio(endpointID);
			}
			if (ot.getBuyerCustomerParty().getAccountingContact() != null
					&& ot.getBuyerCustomerParty().getAccountingContact().getID() != null) {
				accountingContactID = ot.getBuyerCustomerParty().getAccountingContact().getID().getValue();
				isIpaInTabUffAcContact = isIPACodeInTableUfficio(accountingContactID);
			}

			if (!ot.getAdditionalDocumentReference().isEmpty()) {
				final List<DocumentReferenceType> additionalDocumentReferences = ot.getAdditionalDocumentReference();

				for (final DocumentReferenceType reference : additionalDocumentReferences) {
					if (reference.getDocumentType() == null || !"IPA".equals(reference.getDocumentType().getValue())) {
						ipaConditionMet = Boolean.TRUE;
					} else {
						ipaConditionMet = false;
					}
					isIpaInTabUffAddDocRef = isIPACodeInTableUfficio(reference.getID().getValue());
				}
			}

			if(scaricoMepaTestataDad.findScaricoMepaTestataByOrderId(ot.getID().getValue()) != null) {
				apiErrors.add(MsgCpassOrd.ORDORDE0132.getError());
				response.addApiErrors(apiErrors);
				hasErrors = Boolean.TRUE;
			}



			if(ipaConditionMet && (endpointID == null || !"0201".equals(ot.getBuyerCustomerParty().getParty().getEndpointID().getSchemeID())) && accountingContactID == null ) {
				apiErrors.add(MsgCpassOrd.ORDORDE0178.getError());
				response.addApiErrors(apiErrors);
				hasErrors = Boolean.TRUE;
			}

			if(!isIpaInTabUffAddDocRef && !isIpaInTabUffEndpoint && !isIpaInTabUffAcContact) {
				apiErrors.add(MsgCpassOrd.ORDORDE0179.getError());
				response.addApiErrors(apiErrors);
				hasErrors = Boolean.TRUE;
			}

			if(ot.getAdditionalDocumentReference().isEmpty()
					&& (endpointID == null || !"0201".equals(ot.getBuyerCustomerParty().getParty().getEndpointID().getSchemeID()))
					&& (accountingContactID == null)) {

				apiErrors.add(MsgCpassOrd.ORDORDE0178.getError());
				response.addApiErrors(apiErrors);
				hasErrors = Boolean.TRUE;
			}

			if(!ot.getSellerSupplierParty().getParty().getPartyIdentification().isEmpty()) {
				if(ot.getSellerSupplierParty().getParty().getPartyIdentification().get(0).getID().getValue() == null
						&& ot.getSellerSupplierParty().getParty().getEndpointID() == null) {
					apiErrors.add(MsgCpassOrd.ORDORDE0181.getError());
					response.addApiErrors(apiErrors);
					hasErrors = Boolean.TRUE;
				}
			}

			if(ot.getSellerSupplierParty().getParty().getPartyIdentification().isEmpty() && ot.getSellerSupplierParty().getParty().getEndpointID() == null ) {
				apiErrors.add(MsgCpassOrd.ORDORDE0181.getError());
				response.addApiErrors(apiErrors);
				hasErrors = Boolean.TRUE;
			}

			if(!hasErrors) {
				final Stato stato = decodificaDad.getStato("DA_CARICARE","ORDINE_MEPA");
				ScaricoMepaTestata scaricoMepaTestata = new ScaricoMepaTestata();
				scaricoMepaTestata.setStato(stato);
				popolaScaricoMepaTestata(scaricoMepaTestata, ot);
				scaricoMepaTestata = scaricoMepaTestataDad.saveScaricoMepaTestata(scaricoMepaTestata);

				if(scaricoMepaTestata != null) {

					for(final OrderLineType ol : ot.getOrderLine()) {

						ScaricoMepaRiga scaricoMepaRiga = new ScaricoMepaRiga();
						scaricoMepaRiga.setScaricoMepaTestata(scaricoMepaTestata);
						popolaScaricoMepaRiga(scaricoMepaRiga, ol);
						scaricoMepaRiga = scaricoMepaRigaDad.saveScaricoMepaRiga(scaricoMepaRiga);


						if(scaricoMepaRiga != null) {

							if(ol.getLineItem().getAllowanceCharge() != null && ol.getLineItem().getAllowanceCharge().size() > 0) {

								for(final AllowanceChargeType act : ol.getLineItem().getAllowanceCharge()) {

									final ScaricoMepaSconti scaricoMepaSconti = new ScaricoMepaSconti();
									scaricoMepaSconti.setScaricoMepaRiga(scaricoMepaRiga);
									popolaScaricoMepaSconti(scaricoMepaSconti, act);

									scaricoMepaScontiDad.saveScaricoMepaSconti(scaricoMepaSconti);

								}
							}
						}
					}

					//scaricoMepaXml
					final ScaricoMepaXml scaricoMepaXml = new ScaricoMepaXml();
					scaricoMepaXml.setScaricoMepaTestata(scaricoMepaTestata);
					scaricoMepaXml.setFileXml(stringXmlRes);
					scaricoMepaXmlDad.svaeScaricoMepaXml(scaricoMepaXml);

					response.setScaricoMepaTestata(scaricoMepaTestata);
				}
			}

		}catch(final Exception e) {
			log.error(methodName, e);
			//System.err.println(e.getMessage());
			if (apiErrors.contains(MsgCpassOrd.ORDORDE0132.getError())
					|| apiErrors.contains(MsgCpassOrd.ORDORDE0178.getError())
					|| apiErrors.contains(MsgCpassOrd.ORDORDE0179.getError())
					|| apiErrors.contains(MsgCpassOrd.ORDORDE0181.getError())) {
				return;
			}

			apiErrors.add(MsgCpassOrd.ORDORDE0177.getError());
			response.addApiErrors(apiErrors);
		}

	}

	//mepa testata
	private void popolaScaricoMepaTestata(ScaricoMepaTestata scaricoMepaTestata, OrderType ot) {

		scaricoMepaTestata.setOrderId(ot.getID().getValue());
		scaricoMepaTestata.setNote("ORDINE MEPA NR. " + ot.getID().getValue());

		if(ot.getIssueDate() != null) {
			final XMLGregorianCalendar gc = ot.getIssueDate().getValue();
			scaricoMepaTestata.setIssueDate(gc.getYear()+"-"+gc.getMonth()+"-"+ gc.getDay());
		}

		if(ot.getOrderTypeCode() != null) {
			scaricoMepaTestata.setOrderTypeCode(ot.getOrderTypeCode().getValue());
		}


		if(ot.getNote() != null && ot.getNote().size() > 0 && ot.getNote().get(0) != null ){
			//scaricoMepaTestata.setNote(ot.getNote().get(0).getValue());
			scaricoMepaTestata.setNote(scaricoMepaTestata.getNote() + " " + ot.getNote().get(0).getValue());
		}


		if(ot.getCustomerReference() != null) {
			scaricoMepaTestata.setCustomerReference(ot.getCustomerReference().getValue());
		}


		if(ot.getValidityPeriod() != null && ot.getValidityPeriod().size() > 0 && ot.getValidityPeriod().get(0).getEndDate() != null) {
			final XMLGregorianCalendar gc = ot.getValidityPeriod().get(0).getEndDate().getValue();
			scaricoMepaTestata.setEndDate(gc.getYear()+"-"+gc.getMonth()+"-"+ gc.getDay());
		}

		if(ot.getOriginatorDocumentReference() != null && ot.getOriginatorDocumentReference().getID() != null) {
			scaricoMepaTestata.setOriginatorDocumentReferenceId(ot.getOriginatorDocumentReference().getID().getValue());
		}

		if(ot.getAdditionalDocumentReference() != null && ot.getAdditionalDocumentReference().size() > 0 && ot.getAdditionalDocumentReference().get(0) != null) {
			if(ot.getAdditionalDocumentReference().get(0).getID() != null) {
				scaricoMepaTestata.setAdditionalDocumentReferenceId(ot.getAdditionalDocumentReference().get(0).getID().getValue());
			}

			if(ot.getAdditionalDocumentReference().get(0).getDocumentType() != null) {
				scaricoMepaTestata.setAdditionalDocumentReferenceDocumentType(ot.getAdditionalDocumentReference().get(0).getDocumentType().getValue());
			}
		}

		if(ot.getBuyerCustomerParty() != null) {

			if(ot.getBuyerCustomerParty().getParty() != null) {

				final List<DocumentReferenceType> additionalDocumentReferences = ot.getAdditionalDocumentReference();
				boolean ipaConditionMet = false;
				for (final DocumentReferenceType reference : additionalDocumentReferences) {
					if (reference.getDocumentType() != null && "IPA".equals(reference.getDocumentType().getValue())) {
						scaricoMepaTestata.setBuyerCustomerPartyId(reference.getID().getValue());
						ipaConditionMet = Boolean.TRUE;
						break;
					}
				}

				if (!ipaConditionMet) {
					if (ot.getBuyerCustomerParty().getParty().getEndpointID() != null
							&& "0201".equals(ot.getBuyerCustomerParty().getParty().getEndpointID().getSchemeID())) {
						scaricoMepaTestata.setBuyerCustomerPartyId(transformEndpointID(ot.getBuyerCustomerParty().getParty().getEndpointID().getValue()));
					} else {
						scaricoMepaTestata.setBuyerCustomerPartyId(ot.getBuyerCustomerParty().getAccountingContact().getID().getValue());
					}
				}

				if( ot.getBuyerCustomerParty().getParty().getPartyName() != null &&
						ot.getBuyerCustomerParty().getParty().getPartyName().size() > 0 &&
						ot.getBuyerCustomerParty().getParty().getPartyName().get(0).getName() != null) {

					scaricoMepaTestata.setBuyerCustomerPartyName(ot.getBuyerCustomerParty().getParty().getPartyName().get(0).getName().getValue());
				}

				if(ot.getBuyerCustomerParty().getParty().getPostalAddress() != null) {

					if(ot.getBuyerCustomerParty().getParty().getPostalAddress().getStreetName() != null) {
						scaricoMepaTestata.setBuyerCustomerPartyStreetName(ot.getBuyerCustomerParty().getParty().getPostalAddress().getStreetName().getValue());
					}
					if(ot.getBuyerCustomerParty().getParty().getPostalAddress().getCityName() != null) {
						scaricoMepaTestata.setBuyerCustomerPartyCityName(ot.getBuyerCustomerParty().getParty().getPostalAddress().getCityName().getValue());
					}
					if(ot.getBuyerCustomerParty().getParty().getPostalAddress().getPostalZone() != null) {
						scaricoMepaTestata.setBuyerCustomerPartyPostalZone(ot.getBuyerCustomerParty().getParty().getPostalAddress().getPostalZone().getValue());
					}
					//CompanyID
					if(ot.getBuyerCustomerParty().getParty().getPartyTaxScheme() != null &&
							ot.getBuyerCustomerParty().getParty().getPartyTaxScheme().size() > 0 &&
							ot.getBuyerCustomerParty().getParty().getPartyTaxScheme().get(0) != null &&
							ot.getBuyerCustomerParty().getParty().getPartyTaxScheme().get(0).getCompanyID() != null) {
						scaricoMepaTestata.setBuyerCustomerPartyCompanyId(ot.getBuyerCustomerParty().getParty().getPartyTaxScheme().get(0).getCompanyID().getValue());
					}
				}
			}

		}

		if(ot.getSellerSupplierParty() != null) {

			if(ot.getSellerSupplierParty().getParty() != null) {

				if(ot.getSellerSupplierParty().getParty().getEndpointID() != null) {
					scaricoMepaTestata.setSellersupplierpartyEndpointId(transformEndpointID(ot.getSellerSupplierParty().getParty().getEndpointID().getValue()));

				}
				else {
					if(!ot.getSellerSupplierParty().getParty().getPartyIdentification().isEmpty()) {
						final String identification = ot.getSellerSupplierParty().getParty().getPartyIdentification().get(0).getID().getSchemeID();
						if(identification != null && "IT:VAT".equals(identification)) {
							scaricoMepaTestata.setSellersupplierpartyEndpointId(ot.getSellerSupplierParty().getParty().getPartyIdentification().get(0).getID().getValue());
						}
					}

				}
				if(ot.getSellerSupplierParty().getParty().getPartyName() != null &&  ot.getSellerSupplierParty().getParty().getPartyName().size() > 0 && ot.getSellerSupplierParty().getParty().getPartyName().get(0) != null) {
					scaricoMepaTestata.setSellersupplierpartyName( ot.getSellerSupplierParty().getParty().getPartyName().get(0).getName().getValue());
				}

				if(ot.getSellerSupplierParty().getParty().getPostalAddress() != null) {

					if(ot.getSellerSupplierParty().getParty().getPostalAddress().getStreetName() != null) {
						scaricoMepaTestata.setSellersupplierpartyStreetName(ot.getSellerSupplierParty().getParty().getPostalAddress().getStreetName().getValue());
					}
					if(ot.getSellerSupplierParty().getParty().getPostalAddress().getCityName() != null) {
						scaricoMepaTestata.setSellersupplierpartyCityName(ot.getSellerSupplierParty().getParty().getPostalAddress().getCityName().getValue());
					}
					if(ot.getSellerSupplierParty().getParty().getPostalAddress().getPostalZone() != null) {
						scaricoMepaTestata.setSellersupplierpartyPostalZone(ot.getSellerSupplierParty().getParty().getPostalAddress().getPostalZone().getValue());
					}
					if(ot.getSellerSupplierParty().getParty().getPartyLegalEntity() != null &&
							ot.getSellerSupplierParty().getParty().getPartyLegalEntity().size() > 0 &&
							ot.getSellerSupplierParty().getParty().getPartyLegalEntity().get(0) != null &&
							ot.getSellerSupplierParty().getParty().getPartyLegalEntity().get(0).getRegistrationName() != null) {
						scaricoMepaTestata.setSellersupplierpartyRegistrationName(ot.getSellerSupplierParty().getParty().getPartyLegalEntity().get(0).getRegistrationName().getValue());
					}
				}
			}
		}


		if(ot.getDelivery() != null) {

			if(ot.getDelivery().size() > 0) {

				if(ot.getDelivery().get(0).getDeliveryLocation() != null) {

					if(ot.getDelivery().get(0).getDeliveryLocation().getID() != null) {
						scaricoMepaTestata.setDeliveryLocationId(ot.getDelivery().get(0).getDeliveryLocation().getID().getValue());
					}

					if(ot.getDelivery().get(0).getDeliveryLocation().getAddress() != null) {
						if(ot.getDelivery().get(0).getDeliveryLocation().getAddress().getStreetName() != null) {
							scaricoMepaTestata.setDeliveryLocationStreetName(ot.getDelivery().get(0).getDeliveryLocation().getAddress().getStreetName().getValue());
						}
						if(ot.getDelivery().get(0).getDeliveryLocation().getAddress().getCityName() != null) {
							scaricoMepaTestata.setDeliveryLocationCityName(ot.getDelivery().get(0).getDeliveryLocation().getAddress().getCityName().getValue());
						}
						if(ot.getDelivery().get(0).getDeliveryLocation().getAddress().getPostalZone() != null) {
							scaricoMepaTestata.setDeliveryLocationPostalZone(ot.getDelivery().get(0).getDeliveryLocation().getAddress().getPostalZone().getValue());
						}
					}
				}


				if(ot.getDelivery().get(0).getRequestedDeliveryPeriod() != null) {

					if(ot.getDelivery().get(0).getRequestedDeliveryPeriod().getStartDate() != null) {
						final XMLGregorianCalendar gc = ot.getDelivery().get(0).getRequestedDeliveryPeriod().getStartDate().getValue();
						scaricoMepaTestata.setRequesteddeliveryperiodStartDate(gc.getYear()+"-"+gc.getMonth()+"-"+ gc.getDay());
					}
					if(ot.getDelivery().get(0).getRequestedDeliveryPeriod().getEndDate() != null) {
						final XMLGregorianCalendar gc = ot.getDelivery().get(0).getRequestedDeliveryPeriod().getEndDate().getValue();
						scaricoMepaTestata.setRequesteddeliveryperiodEndDate(gc.getYear()+"-"+gc.getMonth()+"-"+ gc.getDay());
					}

				}

				if(ot.getDelivery().get(0).getDeliveryParty() != null) {

					if(ot.getDelivery().get(0).getDeliveryParty().getPartyIdentification() != null && ot.getDelivery().get(0).getDeliveryParty().getPartyIdentification().size() > 0) {

						if(ot.getDelivery().get(0).getDeliveryParty().getPartyIdentification().get(0) != null && ot.getDelivery().get(0).getDeliveryParty().getPartyIdentification().get(0).getID() != null) {
							scaricoMepaTestata.setDeliverpartyId(ot.getDelivery().get(0).getDeliveryParty().getPartyIdentification().get(0).getID().getValue());
						}
					}

					if(ot.getDelivery().get(0).getDeliveryParty().getPartyName() != null &&
							ot.getDelivery().get(0).getDeliveryParty().getPartyName().size() > 0
							&& ot.getDelivery().get(0).getDeliveryParty().getPartyName().get(0) != null &&
							ot.getDelivery().get(0).getDeliveryParty().getPartyName().get(0).getName() != null) {
						scaricoMepaTestata.setDeliverpartyName(ot.getDelivery().get(0).getDeliveryParty().getPartyName().get(0).getName().getValue());
					}
					if(ot.getDelivery().get(0).getDeliveryParty().getContact() != null) {

						if(ot.getDelivery().get(0).getDeliveryParty().getContact().getName() != null) {
							scaricoMepaTestata.setDeliverpartyContactName(ot.getDelivery().get(0).getDeliveryParty().getContact().getName().getValue());
						}
						if(ot.getDelivery().get(0).getDeliveryParty().getContact().getTelephone() != null) {
							scaricoMepaTestata.setDeliverpartyContactTelephone(ot.getDelivery().get(0).getDeliveryParty().getContact().getTelephone().getValue());
						}
						if(ot.getDelivery().get(0).getDeliveryParty().getContact().getElectronicMail() != null) {
							scaricoMepaTestata.setDeliverpartyContactEmail(ot.getDelivery().get(0).getDeliveryParty().getContact().getElectronicMail().getValue());
						}
					}
				}
			}
		}


		if(ot.getTaxTotal() != null && ot.getTaxTotal().size() > 0 && ot.getTaxTotal().get(0) != null &&
				ot.getTaxTotal().get(0).getTaxAmount() != null &&
				ot.getTaxTotal().get(0).getTaxAmount().getValue() != null) {
			scaricoMepaTestata.setTaxTotal(ot.getTaxTotal().get(0).getTaxAmount().getValue());
		}

		if(ot.getAnticipatedMonetaryTotal() != null) {

			if(ot.getAnticipatedMonetaryTotal().getLineExtensionAmount() != null && ot.getAnticipatedMonetaryTotal().getLineExtensionAmount().getValue() != null) {
				scaricoMepaTestata.setAnticipatedmonetarytotalLineExtensionAmount(ot.getAnticipatedMonetaryTotal().getLineExtensionAmount().getValue());
			}
			if(ot.getAnticipatedMonetaryTotal().getAllowanceTotalAmount() != null && ot.getAnticipatedMonetaryTotal().getAllowanceTotalAmount().getValue() != null) {
				scaricoMepaTestata.setAnticipatedmonetarytotalAllowanceTotalAmount(ot.getAnticipatedMonetaryTotal().getAllowanceTotalAmount().getValue());
			}
			if(ot.getAnticipatedMonetaryTotal().getTaxExclusiveAmount() != null && ot.getAnticipatedMonetaryTotal().getTaxExclusiveAmount().getValue() != null) {
				scaricoMepaTestata.setAnticipatedmonetarytotalTaxExclusiveAmount(ot.getAnticipatedMonetaryTotal().getTaxExclusiveAmount().getValue());
			}
			if(ot.getAnticipatedMonetaryTotal().getTaxInclusiveAmount() != null && ot.getAnticipatedMonetaryTotal().getTaxInclusiveAmount().getValue() != null) {
				scaricoMepaTestata.setAnticipatedmonetarytotalTaxInclusiveAmount(ot.getAnticipatedMonetaryTotal().getTaxInclusiveAmount().getValue());
			}
			if(ot.getAnticipatedMonetaryTotal().getPayableAmount() != null && ot.getAnticipatedMonetaryTotal().getPayableAmount().getValue() != null) {
				scaricoMepaTestata.setAnticipatedmonetarytotalPayableAmount(ot.getAnticipatedMonetaryTotal().getPayableAmount().getValue());
			}
		}

	}

	//Mepa riga
	private void popolaScaricoMepaRiga(ScaricoMepaRiga scaricoMepaRiga, OrderLineType ol) {

		if(ol.getNote() != null && ol.getNote().size() > 0 && ol.getNote().get(0) != null) {
			scaricoMepaRiga.setOrderlineNote(ol.getNote().get(0).getValue());
		}
		if(ol.getLineItem() != null) {

			if(ol.getLineItem().getID() != null) {
				scaricoMepaRiga.setOrderlineId(ol.getLineItem().getID().getValue());
			}
			if(ol.getLineItem().getQuantity() != null) {
				scaricoMepaRiga.setOrderlineQuantity(ol.getLineItem().getQuantity().getValue());
				if(ol.getLineItem().getQuantity().getUnitCode() != null) {
					scaricoMepaRiga.setOrderlineQuantityUnitCode(ol.getLineItem().getQuantity().getUnitCode());
				}
			}

			if(ol.getLineItem().getLineExtensionAmount() != null) {
				scaricoMepaRiga.setOrderlineLineExtensionAmount(ol.getLineItem().getLineExtensionAmount().getValue());
			}
			if(ol.getLineItem().getPartialDeliveryIndicator() != null) {
				scaricoMepaRiga.setOrderlineLinePartialDeliveryIndicator(ol.getLineItem().getPartialDeliveryIndicator().isValue());
			}
			if(ol.getLineItem().getDelivery() != null && ol.getLineItem().getDelivery().size() > 0 && ol.getLineItem().getDelivery().get(0) != null) {
				scaricoMepaRiga.setOrderlineLineDeliveryId(ol.getLineItem().getDelivery().get(0).getID().getValue());
			}

			if(ol.getLineItem().getPrice() != null) {

				if(ol.getLineItem().getPrice().getPriceAmount() != null) {
					scaricoMepaRiga.setPriceamount(ol.getLineItem().getPrice().getPriceAmount().getValue());
				}
				if(ol.getLineItem().getPrice().getBaseQuantity() != null) {
					scaricoMepaRiga.setBasequantity(ol.getLineItem().getPrice().getBaseQuantity().getValue());
				}
				if(ol.getLineItem().getItem() != null ) {
					if(ol.getLineItem().getItem().getName() != null) {
						scaricoMepaRiga.setItemName(ol.getLineItem().getItem().getName().getValue());
					}
					if(ol.getLineItem().getItem().getBuyersItemIdentification() != null && ol.getLineItem().getItem().getBuyersItemIdentification().getID() != null) {
						scaricoMepaRiga.setItemBuyersId(ol.getLineItem().getItem().getBuyersItemIdentification().getID().getValue());
					}
					if(ol.getLineItem().getItem().getSellersItemIdentification() != null && ol.getLineItem().getItem().getSellersItemIdentification().getID() != null) {
						scaricoMepaRiga.setItemSellersId(ol.getLineItem().getItem().getSellersItemIdentification().getID().getValue());
					}
					if(ol.getLineItem().getItem().getCommodityClassification() != null &&
							ol.getLineItem().getItem().getCommodityClassification().size() > 0 &&
							ol.getLineItem().getItem().getCommodityClassification().get(0) != null &&
							ol.getLineItem().getItem().getCommodityClassification().get(0).getCommodityCode() != null) {
						scaricoMepaRiga.setItemClassificationCode(ol.getLineItem().getItem().getCommodityClassification().get(0).getCommodityCode().getValue());
					}

					if(ol.getLineItem().getItem().getClassifiedTaxCategory() != null && ol.getLineItem().getItem().getClassifiedTaxCategory().size() > 0) {
						if(ol.getLineItem().getItem().getClassifiedTaxCategory().get(0) != null) {

							if( ol.getLineItem().getItem().getClassifiedTaxCategory().get(0).getID() != null) {
								scaricoMepaRiga.setClassifiedtaxcategoryId(ol.getLineItem().getItem().getClassifiedTaxCategory().get(0).getID().getValue());
							}
							if( ol.getLineItem().getItem().getClassifiedTaxCategory().get(0).getPercent() != null) {
								scaricoMepaRiga.setClassifiedtaxcategoryPercent(ol.getLineItem().getItem().getClassifiedTaxCategory().get(0).getPercent().getValue());
							}
							if( ol.getLineItem().getItem().getClassifiedTaxCategory().get(0).getTaxScheme() != null && ol.getLineItem().getItem().getClassifiedTaxCategory().get(0).getTaxScheme().getID() != null) {
								scaricoMepaRiga.setClassifiedtaxcategoryTaxschemeId(ol.getLineItem().getItem().getClassifiedTaxCategory().get(0).getTaxScheme().getID().getValue());
							}
						}
					}
				}
			}
		}
	}

	//sconti
	private void popolaScaricoMepaSconti(ScaricoMepaSconti scaricoMepaSconti, AllowanceChargeType act) {

		if(act.getChargeIndicator() != null) {
			scaricoMepaSconti.setOrderlineLineAllowancechargeIndicator(act.getChargeIndicator().isValue());
		}

		if(act.getAllowanceChargeReasonCode() != null) {
			scaricoMepaSconti.setOrderlineLineAllowancechargeReason(act.getAllowanceChargeReasonCode().getValue());
		}
		if(act.getMultiplierFactorNumeric() != null) {
			scaricoMepaSconti.setOrderlineLineAllowancechargeMultiplierFactorNumeric(act.getMultiplierFactorNumeric().getValue());
		}
		if(act.getAmount() != null) {
			scaricoMepaSconti.setOrderlineLineAllowancechargeAmount(act.getAmount().getValue());
		}
		if(act.getBaseAmount() != null) {
			scaricoMepaSconti.setOrderlineLineAllowancechargeBaseAmount(act.getBaseAmount().getValue());
		}
	}


	/**
	 *
	 * @param endpointID != null
	 * @return endpointID Senza la sottostringa iniziale = "IT",se presente
	 */
	private String transformEndpointID(String endpointID){
		final String IT ="IT";
		if(endpointID != null) {
			endpointID = endpointID.trim();
			if(endpointID.length() > 2 && (endpointID.substring(0,2)).equals(IT)) {
				endpointID = endpointID.substring(2);
			}
		}
		return endpointID;
	}

	private boolean isIPACodeInTableUfficio(String codice) {
		final Optional<CpassTUfficio> ufficioOptional = scaricoMepaTestataDad.getUfficioByCodice(codice);
		return ufficioOptional.isPresent();
	}

}
