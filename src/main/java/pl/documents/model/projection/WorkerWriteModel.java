package pl.documents.model.projection;

import pl.documents.model.Worker;
import pl.documents.model.enums.*;

import java.time.LocalDate;
import java.time.Month;
import java.util.Set;
import java.util.stream.Collectors;

public class WorkerWriteModel
{
    private String email;
    private String phoneNumber;
    private String fillLocation;
    private Sex sex;
    private String firstName;
    private String secondName;
    private String surname;
    private LocalDate birthDate;
    private String profession;
    private String specialty;
    private String title;
    private String qualifications;
    private String additionalPersonalData;
    private boolean isPolishCitizen;
    private String citizenship;
    private String documentNumber;
    private String documentType;//TODO Czy to nie powinien być enum?
    private String taxOffice;
    private String authorizedName;
    private String authorizedSurname;
    private String authorizedContact;
    private boolean willSpecialPowersForFamily;
    private String NIP;
    private boolean willPIT2;
    private String workplace;
    private String department;
    private Pension pension;
    private LocalDate employmentDate;
    private String bank;
    private String accountNumber;
    private SecurityClearance securityClearance;
    private String NFZ;
    private String pensionZUSNumber;
    private boolean isDisabled;
    private String disabledZUSNumber;
    private LocalDate disabledFrom;
    private LocalDate disabledTo;
    private Medicover medicover;
    private ContractType contractType;
    private IncomePerPerson incomePerPerson;
    private boolean ZFSS1;
    private boolean ZFSS2;
    private boolean ZFSS3;
    private boolean ZFSS4;
    private boolean ZFSS5;
    private boolean ZFSS6;
    private boolean ZFSS7;
    private boolean hasChildren;
    private boolean willParent;
    private ChildUnderFourPermissions childUnderFourPermissions;
    private ChildUnderFourteenPermissions childUnderFourteenPermissions;
    private boolean willReducedTask;
    private boolean methodOfTaxation;
    private String annualEarningsFamily;
    private boolean willTaxReducingAmount;
    private boolean willHigherTask;
    private Month higherTaskMonth;
    private boolean willIncreasedCosts;
    private boolean willZUS;
    private String annualEarningsZUS;
    private String prevEmployment = null;
    private Set<AddressWriteModel> addresses = null;
    private Set<EducationWriteModel> education = null;
    private Set<FamilyMemberWriteModel> familyMembers = null;


    public Worker toWorker()
    {
        Worker worker = new Worker(email, phoneNumber,fillLocation,sex, firstName, secondName, surname, birthDate, profession, specialty, title, qualifications, additionalPersonalData, isPolishCitizen, citizenship, documentNumber, documentType, taxOffice, authorizedName, authorizedSurname, authorizedContact, willSpecialPowersForFamily, NIP, willPIT2, workplace, department,pension,  employmentDate,bank, accountNumber, securityClearance,  NFZ, pensionZUSNumber, isDisabled, disabledZUSNumber, disabledFrom, disabledTo, medicover, contractType, incomePerPerson, ZFSS1, ZFSS2, ZFSS3,ZFSS4,ZFSS5,ZFSS6, ZFSS7, hasChildren, willParent, childUnderFourPermissions, childUnderFourteenPermissions, willReducedTask, methodOfTaxation, annualEarningsFamily, willTaxReducingAmount, willHigherTask, higherTaskMonth,willIncreasedCosts,willZUS, annualEarningsZUS);
        if(addresses!=null)
        {
            worker.setAddresses(addresses.stream()
                    .map(AddressWriteModel::toAddress).collect(Collectors.toSet()));
        }
        if(education!=null)
        {
            worker.setEducation(education.stream()
                    .map(EducationWriteModel::toEducation).collect(Collectors.toSet()));
        }
        if(familyMembers!=null)
        {
            worker.setFamilyMembers(familyMembers.stream()
                    .map(FamilyMemberWriteModel::toFamilyMember).collect(Collectors.toSet()));
        }
        return worker;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public void setPhoneNumber(String phoneNumber)
    {
        this.phoneNumber = phoneNumber;
    }

    public void setFillLocation(String fillLocation)
    {
        this.fillLocation = fillLocation;
    }

    public void setSex(Sex sex)
    {
        this.sex = sex;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public void setSecondName(String secondName)
    {
        this.secondName = secondName;
    }

    public void setSurname(String surname)
    {
        this.surname = surname;
    }

    public void setBirthDate(LocalDate birthDate)
    {
        this.birthDate = birthDate;
    }

    public void setProfession(String profession)
    {
        this.profession = profession;
    }

    public void setSpecialty(String specialty)
    {
        this.specialty = specialty;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public void setQualifications(String qualifications)
    {
        this.qualifications = qualifications;
    }

    public void setAdditionalPersonalData(String additionalPersonalData)
    {
        this.additionalPersonalData = additionalPersonalData;
    }

    public void setPolishCitizen(boolean polishCitizen)
    {
        isPolishCitizen = polishCitizen;
    }

    public void setCitizenship(String citizenship)
    {
        this.citizenship = citizenship;
    }

    public void setDocumentNumber(String documentNumber)
    {
        this.documentNumber = documentNumber;
    }

    public void setDocumentType(String documentType)
    {
        this.documentType = documentType;
    }

    public void setTaxOffice(String taxOffice)
    {
        this.taxOffice = taxOffice;
    }

    public void setAuthorizedName(String authorizedName)
    {
        this.authorizedName = authorizedName;
    }

    public void setAuthorizedSurname(String authorizedSurname)
    {
        this.authorizedSurname = authorizedSurname;
    }

    public void setAuthorizedContact(String authorizedContact)
    {
        this.authorizedContact = authorizedContact;
    }

    public void setWillSpecialPowersForFamily(boolean willSpecialPowersForFamily)
    {
        this.willSpecialPowersForFamily = willSpecialPowersForFamily;
    }

    public void setNIP(String NIP)
    {
        this.NIP = NIP;
    }

    public void setWillPIT2(boolean willPIT2)
    {
        this.willPIT2 = willPIT2;
    }

    public void setWorkplace(String workplace)
    {
        this.workplace = workplace;
    }

    public void setDepartment(String department)
    {
        this.department = department;
    }

    public void setPension(Pension pension)
    {
        this.pension = pension;
    }

    public void setEmploymentDate(LocalDate employmentDate)
    {
        this.employmentDate = employmentDate;
    }

    public void setBank(String bank)
    {
        this.bank = bank;
    }

    public void setAccountNumber(String accountNumber)
    {
        this.accountNumber = accountNumber;
    }

    public void setSecurityClearance(SecurityClearance securityClearance)
    {
        this.securityClearance = securityClearance;
    }

    public void setNFZ(String NFZ)
    {
        this.NFZ = NFZ;
    }

    public void setPensionZUSNumber(String pensionZUSNumber)
    {
        this.pensionZUSNumber = pensionZUSNumber;
    }

    public void setDisabled(boolean disabled)
    {
        isDisabled = disabled;
    }

    public void setDisabledZUSNumber(String disabledZUSNumber)
    {
        this.disabledZUSNumber = disabledZUSNumber;
    }

    public void setDisabledFrom(LocalDate disabledFrom)
    {
        this.disabledFrom = disabledFrom;
    }

    public void setDisabledTo(LocalDate disabledTo)
    {
        this.disabledTo = disabledTo;
    }

    public void setMedicover(Medicover medicover)
    {
        this.medicover = medicover;
    }

    public void setContractType(ContractType contractType)
    {
        this.contractType = contractType;
    }

    public void setIncomePerPerson(IncomePerPerson incomePerPerson)
    {
        this.incomePerPerson = incomePerPerson;
    }

    public void setZFSS1(boolean ZFSS1)
    {
        this.ZFSS1 = ZFSS1;
    }

    public void setZFSS2(boolean ZFSS2)
    {
        this.ZFSS2 = ZFSS2;
    }

    public void setZFSS3(boolean ZFSS3)
    {
        this.ZFSS3 = ZFSS3;
    }

    public void setZFSS4(boolean ZFSS4)
    {
        this.ZFSS4 = ZFSS4;
    }

    public void setZFSS5(boolean ZFSS5)
    {
        this.ZFSS5 = ZFSS5;
    }

    public void setZFSS6(boolean ZFSS6)
    {
        this.ZFSS6 = ZFSS6;
    }

    public void setZFSS7(boolean ZFSS7)
    {
        this.ZFSS7 = ZFSS7;
    }

    public void setHasChildren(boolean hasChildren)
    {
        this.hasChildren = hasChildren;
    }

    public void setWillParent(boolean willParent)
    {
        this.willParent = willParent;
    }

    public void setChildUnderFourPermissions(ChildUnderFourPermissions childUnderFourPermissions)
    {
        this.childUnderFourPermissions = childUnderFourPermissions;
    }

    public void setChildUnderFourteenPermissions(ChildUnderFourteenPermissions childUnderFourteenPermissions)
    {
        this.childUnderFourteenPermissions = childUnderFourteenPermissions;
    }

    public void setWillReducedTask(boolean willReducedTask)
    {
        this.willReducedTask = willReducedTask;
    }

    public void setMethodOfTaxation(boolean methodOfTaxation)
    {
        this.methodOfTaxation = methodOfTaxation;
    }

    public void setAnnualEarningsFamily(String annualEarningsFamily)
    {
        this.annualEarningsFamily = annualEarningsFamily;
    }

    public void setWillTaxReducingAmount(boolean willTaxReducingAmount)
    {
        this.willTaxReducingAmount = willTaxReducingAmount;
    }

    public void setWillHigherTask(boolean willHigherTask)
    {
        this.willHigherTask = willHigherTask;
    }

    public void setHigherTaskMonth(Month higherTaskMonth)
    {
        this.higherTaskMonth = higherTaskMonth;
    }

    public void setWillIncreasedCosts(boolean willIncreasedCosts)
    {
        this.willIncreasedCosts = willIncreasedCosts;
    }

    public void setWillZUS(boolean willZUS)
    {
        this.willZUS = willZUS;
    }

    public void setAnnualEarningsZUS(String annualEarningsZUS)
    {
        this.annualEarningsZUS = annualEarningsZUS;
    }

    public void setAddresses(Set<AddressWriteModel> addresses)
    {
        this.addresses = addresses;
    }

    public void setEducation(Set<EducationWriteModel> education)
    {
        this.education = education;
    }

    public void setPrevEmployment(String prevEmployment)
    {
        this.prevEmployment = prevEmployment;
    }

    public void setFamilyMembers(Set<FamilyMemberWriteModel> familyMembers)
    {
        this.familyMembers = familyMembers;
    }
}
