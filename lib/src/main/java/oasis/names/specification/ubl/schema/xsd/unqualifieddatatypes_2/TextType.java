
package oasis.names.specification.ubl.schema.xsd.unqualifieddatatypes_2;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;

import oasis.names.specification.ubl.schema.xsd.commonbasiccomponents_2.*;
import oasis.names.specification.ubl.schema.xsd.commonbasiccomponents_2.ValueType;
import oasis.names.specification.ubl.schema.xsd.commonextensioncomponents_2.ExtensionAgencyNameType;
import oasis.names.specification.ubl.schema.xsd.commonextensioncomponents_2.ExtensionReasonType;


/**
 *
 *
 * <pre>
 * &lt;?xml version="1.0" encoding="UTF-8"?&gt;&lt;ccts:UniqueID xmlns:ccts="urn:un:unece:uncefact:documentation:2" xmlns:ccts-cct="urn:un:unece:uncefact:data:specification:CoreComponentTypeSchemaModule:2" xmlns:xsd="http://www.w3.org/2001/XMLSchema"&gt;UBLUDT0000019&lt;/ccts:UniqueID&gt;
 * </pre>
 *
 *
 * <pre>
 * &lt;?xml version="1.0" encoding="UTF-8"?&gt;&lt;ccts:CategoryCode xmlns:ccts="urn:un:unece:uncefact:documentation:2" xmlns:ccts-cct="urn:un:unece:uncefact:data:specification:CoreComponentTypeSchemaModule:2" xmlns:xsd="http://www.w3.org/2001/XMLSchema"&gt;UDT&lt;/ccts:CategoryCode&gt;
 * </pre>
 *
 *
 * <pre>
 * &lt;?xml version="1.0" encoding="UTF-8"?&gt;&lt;ccts:DictionaryEntryName xmlns:ccts="urn:un:unece:uncefact:documentation:2" xmlns:ccts-cct="urn:un:unece:uncefact:data:specification:CoreComponentTypeSchemaModule:2" xmlns:xsd="http://www.w3.org/2001/XMLSchema"&gt;Text. Type&lt;/ccts:DictionaryEntryName&gt;
 * </pre>
 *
 *
 * <pre>
 * &lt;?xml version="1.0" encoding="UTF-8"?&gt;&lt;ccts:VersionID xmlns:ccts="urn:un:unece:uncefact:documentation:2" xmlns:ccts-cct="urn:un:unece:uncefact:data:specification:CoreComponentTypeSchemaModule:2" xmlns:xsd="http://www.w3.org/2001/XMLSchema"&gt;1.0&lt;/ccts:VersionID&gt;
 * </pre>
 *
 *
 * <pre>
 * &lt;?xml version="1.0" encoding="UTF-8"?&gt;&lt;ccts:Definition xmlns:ccts="urn:un:unece:uncefact:documentation:2" xmlns:ccts-cct="urn:un:unece:uncefact:data:specification:CoreComponentTypeSchemaModule:2" xmlns:xsd="http://www.w3.org/2001/XMLSchema"&gt;A character string (i.e. a finite set of characters), generally in the form of words of a language.&lt;/ccts:Definition&gt;
 * </pre>
 *
 *
 * <pre>
 * &lt;?xml version="1.0" encoding="UTF-8"?&gt;&lt;ccts:RepresentationTermName xmlns:ccts="urn:un:unece:uncefact:documentation:2" xmlns:ccts-cct="urn:un:unece:uncefact:data:specification:CoreComponentTypeSchemaModule:2" xmlns:xsd="http://www.w3.org/2001/XMLSchema"&gt;Text&lt;/ccts:RepresentationTermName&gt;
 * </pre>
 *
 *
 * <pre>
 * &lt;?xml version="1.0" encoding="UTF-8"?&gt;&lt;ccts:PrimitiveType xmlns:ccts="urn:un:unece:uncefact:documentation:2" xmlns:ccts-cct="urn:un:unece:uncefact:data:specification:CoreComponentTypeSchemaModule:2" xmlns:xsd="http://www.w3.org/2001/XMLSchema"&gt;string&lt;/ccts:PrimitiveType&gt;
 * </pre>
 *
 *
 *
 * <p>Classe Java per TextType complex type.
 *
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 *
 * <pre>
 * &lt;complexType name="TextType"&gt;
 *   &lt;simpleContent&gt;
 *     &lt;extension base="&lt;urn:un:unece:uncefact:data:specification:CoreComponentTypeSchemaModule:2&gt;TextType"&gt;
 *     &lt;/extension&gt;
 *   &lt;/simpleContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TextType")
@XmlSeeAlso({
    AcceptedVariantsDescriptionType.class,
    AccountingCostType.class,
    ActivityTypeType.class,
    AdditionalConditionsType.class,
    AdditionalInformationType.class,
    AgencyNameType.class,
    AllowanceChargeReasonType.class,
    ApprovalStatusType.class,
    AwardingCriterionDescriptionType.class,
    BackorderReasonType.class,
    BirthplaceNameType.class,
    BuildingNumberType.class,
    BuyerReferenceType.class,
    CalculationExpressionType.class,
    CancellationNoteType.class,
    CandidateStatementType.class,
    CanonicalizationMethodType.class,
    CarrierServiceInstructionsType.class,
    CertificateTypeType.class,
    ChangeConditionsType.class,
    ChannelType.class,
    CharacteristicsType.class,
    CodeValueType.class,
    CommentType.class,
    CompanyLegalFormType.class,
    ConditionType.class,
    ConditionsType.class,
    ConditionsDescriptionType.class,
    ConsumersEnergyLevelType.class,
    ConsumptionLevelType.class,
    ConsumptionTypeType.class,
    ContentType.class,
    ContractNameType.class,
    ContractSubdivisionType.class,
    ContractTypeType.class,
    CorrectionTypeType.class,
    CountrySubentityType.class,
    CurrentChargeTypeType.class,
    CustomerReferenceType.class,
    CustomsClearanceServiceInstructionsType.class,
    DamageRemarksType.class,
    DataSendingCapabilityType.class,
    DeliveryInstructionsType.class,
    DemurrageInstructionsType.class,
    DepartmentType.class,
    DescriptionType.class,
    DistrictType.class,
    DocumentDescriptionType.class,
    DocumentHashType.class,
    DocumentStatusReasonDescriptionType.class,
    DocumentTypeType.class,
    DutyType.class,
    ElectronicDeviceDescriptionType.class,
    ElectronicMailType.class,
    ExclusionReasonType.class,
    ExemptionReasonType.class,
    ExpressionType.class,
    ExtensionType.class,
    FeeDescriptionType.class,
    FloorType.class,
    ForwarderServiceInstructionsType.class,
    FrequencyType.class,
    FundingProgramType.class,
    HandlingInstructionsType.class,
    HashAlgorithmMethodType.class,
    HaulageInstructionsType.class,
    HeatingTypeType.class,
    InformationType.class,
    InhouseMailType.class,
    InstructionNoteType.class,
    InstructionsType.class,
    InvoicingPartyReferenceType.class,
    JobTitleType.class,
    JustificationType.class,
    JustificationDescriptionType.class,
    KeywordType.class,
    LatestMeterReadingMethodType.class,
    LegalReferenceType.class,
    LimitationDescriptionType.class,
    LineType.class,
    ListValueType.class,
    LocationType.class,
    LoginType.class,
    LossRiskType.class,
    LowTendersDescriptionType.class,
    MarkAttentionType.class,
    MarkCareType.class,
    MaximumValueType.class,
    MeterConstantType.class,
    MeterNameType.class,
    MeterNumberType.class,
    MeterReadingCommentsType.class,
    MeterReadingTypeType.class,
    MinimumImprovementBidType.class,
    MinimumValueType.class,
    MonetaryScopeType.class,
    MovieTitleType.class,
    NameSuffixType.class,
    NegotiationDescriptionType.class,
    NoteType.class,
    OneTimeChargeTypeType.class,
    OptionsDescriptionType.class,
    OrderableUnitType.class,
    OrganizationDepartmentType.class,
    OtherInstructionType.class,
    OutstandingReasonType.class,
    PackingMaterialType.class,
    PartyTypeType.class,
    PasswordType.class,
    PayPerViewType.class,
    PayerReferenceType.class,
    PaymentDescriptionType.class,
    PaymentNoteType.class,
    PaymentOrderReferenceType.class,
    PersonalSituationType.class,
    PhoneNumberType.class,
    PlacardEndorsementType.class,
    PlacardNotationType.class,
    PlotIdentificationType.class,
    PostalZoneType.class,
    PostboxType.class,
    PreviousMeterReadingMethodType.class,
    PriceChangeReasonType.class,
    PriceRevisionFormulaDescriptionType.class,
    PriceTypeType.class,
    PrintQualifierType.class,
    PriorityType.class,
    PrizeDescriptionType.class,
    ProcessDescriptionType.class,
    ProcessReasonType.class,
    PurposeType.class,
    RankType.class,
    ReferenceType.class,
    RegionType.class,
    RegistrationNationalityType.class,
    RegulatoryDomainType.class,
    RejectReasonType.class,
    RejectionNoteType.class,
    RemarksType.class,
    ReplenishmentOwnerDescriptionType.class,
    ResidenceTypeType.class,
    ResolutionType.class,
    RoleDescriptionType.class,
    RoomType.class,
    SealingPartyTypeType.class,
    ServiceNumberCalledType.class,
    ServiceTypeType.class,
    ShippingMarksType.class,
    ShipsRequirementsType.class,
    SignatureMethodType.class,
    SpecialInstructionsType.class,
    SpecialServiceInstructionsType.class,
    SpecialTermsType.class,
    SpecialTransportRequirementsType.class,
    StatusReasonType.class,
    SubscriberTypeType.class,
    SummaryDescriptionType.class,
    TariffDescriptionType.class,
    TaxExemptionReasonType.class,
    TechnicalCommitteeDescriptionType.class,
    TelecommunicationsServiceCallType.class,
    TelecommunicationsServiceCategoryType.class,
    TelecommunicationsSupplyTypeType.class,
    TelefaxType.class,
    TelephoneType.class,
    TestMethodType.class,
    oasis.names.specification.ubl.schema.xsd.commonbasiccomponents_2.TextType.class,
    TierRangeType.class,
    TimeAmountType.class,
    TimezoneOffsetType.class,
    TimingComplaintType.class,
    TitleType.class,
    TradingRestrictionsType.class,
    TransportServiceProviderRemarksType.class,
    TransportServiceProviderSpecialTermsType.class,
    TransportUserRemarksType.class,
    TransportUserSpecialTermsType.class,
    TransportationServiceDescriptionType.class,
    ValidateProcessType.class,
    ValidateToolType.class,
    ValidateToolVersionType.class,
    ValueType.class,
    ValueQualifierType.class,
    WarrantyInformationType.class,
    WeightType.class,
    WorkPhaseType.class,
    XPathType.class,
    ExtensionAgencyNameType.class,
    ExtensionReasonType.class
})
public class TextType
    extends un.unece.uncefact.data.specification.corecomponenttypeschemamodule._2.TextType
{


}
