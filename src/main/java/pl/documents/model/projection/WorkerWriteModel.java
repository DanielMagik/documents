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
    private String optionalData;
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

    private Set<AddressWriteModel> addresses = null;
    private Set<EducationWriteModel> education = null;
    private Set<EmploymentWriteModel> employments = null;
    private Set<FamilyMemberWriteModel> familyMembers = null;


    public Worker toWorker()
    {
        Worker worker = new Worker(email, phoneNumber,fillLocation,sex, firstName, secondName, surname, birthDate, profession, specialty, title, qualifications,  optionalData, isPolishCitizen, citizenship, documentNumber, documentType, taxOffice, authorizedName, authorizedSurname, authorizedContact, willSpecialPowersForFamily, NIP, willPIT2, workplace, department,pension,  employmentDate,bank, accountNumber, securityClearance,  NFZ, pensionZUSNumber, isDisabled, disabledZUSNumber, disabledFrom, disabledTo, medicover, contractType, incomePerPerson, ZFSS1, ZFSS2, ZFSS3,ZFSS4,ZFSS5,ZFSS6, ZFSS7, hasChildren, willParent, childUnderFourPermissions, childUnderFourteenPermissions, willReducedTask, methodOfTaxation, annualEarningsFamily, willTaxReducingAmount, willHigherTask, higherTaskMonth,willIncreasedCosts,willZUS, annualEarningsZUS);
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
        if(employments!=null)
        {
            worker.setEmployments(employments.stream()
                    .map(EmploymentWriteModel::toEmployment).collect(Collectors.toSet()));
        }
        if(familyMembers!=null)
        {
            worker.setFamilyMembers(familyMembers.stream()
                    .map(FamilyMemberWriteModel::toFamilyMember).collect(Collectors.toSet()));
        }
        return worker;
    }


    public Set<AddressWriteModel> getAddresses()
    {
        return addresses;
    }

    public void setAddresses(Set<AddressWriteModel> addresses)
    {
        this.addresses = addresses;
    }

    public Set<EducationWriteModel> getEducation()
    {
        return education;
    }

    public void setEducation(Set<EducationWriteModel> education)
    {
        this.education = education;
    }

    public Set<EmploymentWriteModel> getEmployments()
    {
        return employments;
    }

    public void setEmployments(Set<EmploymentWriteModel> employments)
    {
        this.employments = employments;
    }

    public Set<FamilyMemberWriteModel> getFamilyMembers()
    {
        return familyMembers;
    }

    public void setFamilyMembers(Set<FamilyMemberWriteModel> familyMembers)
    {
        this.familyMembers = familyMembers;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        email = email;
    }

    public String getPhoneNumber()
    {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber)
    {
        phoneNumber = phoneNumber;
    }

    public String getFillLocation()
    {
        return fillLocation;
    }

    public void setFillLocation(String fillLocation)
    {
        fillLocation = fillLocation;
    }

    public Sex getSex()
    {
        return sex;
    }

    public void setSex(Sex sex)
    {
        sex = sex;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public void setFirstName(String firstName)
    {
        firstName = firstName;
    }

    public String getSecondName()
    {
        return secondName;
    }

    public void setSecondName(String secondName)
    {
        secondName = secondName;
    }

    public String getSurname()
    {
        return surname;
    }

    public void setSurname(String surname)
    {
        surname = surname;
    }

    public LocalDate getBirthDate()
    {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate)
    {
        birthDate = birthDate;
    }

    public String getProfession()
    {
        return profession;
    }

    public void setProfession(String profession)
    {
        profession = profession;
    }

    public String getSpecialty()
    {
        return specialty;
    }

    public void setSpecialty(String specialty)
    {
        specialty = specialty;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        title = title;
    }

    public String getQualifications()
    {
        return qualifications;
    }

    public void setQualifications(String qualifications)
    {
        qualifications = qualifications;
    }

    public String getOptionalData()
    {
        return optionalData;
    }

    public void setOptionalData(String optionalData)
    {
        optionalData = optionalData;
    }

    public boolean isPolishCitizen()
    {
        return isPolishCitizen;
    }

    public void setPolishCitizen(boolean polishCitizen)
    {
        isPolishCitizen = polishCitizen;
    }

    public String getCitizenship()
    {
        return citizenship;
    }

    public void setCitizenship(String citizenship)
    {
        citizenship = citizenship;
    }

    public String getDocumentNumber()
    {
        return documentNumber;
    }

    public void setDocumentNumber(String documentNumber)
    {
        documentNumber = documentNumber;
    }

    public String getDocumentType()
    {
        return documentType;
    }

    public void setDocumentType(String documentType)
    {
        documentType = documentType;
    }

    public String getTaxOffice()
    {
        return taxOffice;
    }

    public void setTaxOffice(String taxOffice)
    {
        taxOffice = taxOffice;
    }

    public String getAuthorizedName()
    {
        return authorizedName;
    }

    public void setAuthorizedName(String authorizedName)
    {
        authorizedName = authorizedName;
    }

    public String getAuthorizedSurname()
    {
        return authorizedSurname;
    }

    public void setAuthorizedSurname(String authorizedSurname)
    {
        authorizedSurname = authorizedSurname;
    }

    public String getAuthorizedContact()
    {
        return authorizedContact;
    }

    public void setAuthorizedContact(String authorizedContact)
    {
        authorizedContact = authorizedContact;
    }

    public boolean isWillSpecialPowersForFamily()
    {
        return willSpecialPowersForFamily;
    }

    public void setWillSpecialPowersForFamily(boolean willSpecialPowersForFamily)
    {
        willSpecialPowersForFamily = willSpecialPowersForFamily;
    }

    public String getNIP()
    {
        return NIP;
    }

    public void setNIP(String NIP)
    {
        NIP = NIP;
    }

    public boolean isWillPIT2()
    {
        return willPIT2;
    }

    public void setWillPIT2(boolean willPIT2)
    {
        willPIT2 = willPIT2;
    }

    public String getWorkplace()
    {
        return workplace;
    }

    public void setWorkplace(String workplace)
    {
        workplace = workplace;
    }

    public String getDepartment()
    {
        return department;
    }

    public void setDepartment(String department)
    {
        department = department;
    }

    public Pension getPension()
    {
        return pension;
    }

    public void setPension(Pension pension)
    {
        pension = pension;
    }

    public LocalDate getEmploymentDate()
    {
        return employmentDate;
    }

    public void setEmploymentDate(LocalDate employmentDate)
    {
        employmentDate = employmentDate;
    }

    public String getBank()
    {
        return bank;
    }

    public void setBank(String bank)
    {
        bank = bank;
    }

    public String getAccountNumber()
    {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber)
    {
        accountNumber = accountNumber;
    }

    public SecurityClearance getSecurityClearance()
    {
        return securityClearance;
    }

    public void setSecurityClearance(SecurityClearance securityClearance)
    {
        securityClearance = securityClearance;
    }

    public String getNFZ()
    {
        return NFZ;
    }

    public void setNFZ(String NFZ)
    {
        NFZ = NFZ;
    }

    public String getPensionZUSNumber()
    {
        return pensionZUSNumber;
    }

    public void setPensionZUSNumber(String pensionZUSNumber)
    {
        pensionZUSNumber = pensionZUSNumber;
    }

    public boolean isDisabled()
    {
        return isDisabled;
    }

    public void setDisabled(boolean disabled)
    {
        isDisabled = disabled;
    }

    public String getDisabledZUSNumber()
    {
        return disabledZUSNumber;
    }

    public void setDisabledZUSNumber(String disabledZUSNumber)
    {
        disabledZUSNumber = disabledZUSNumber;
    }

    public LocalDate getDisabledFrom()
    {
        return disabledFrom;
    }

    public void setDisabledFrom(LocalDate disabledFrom)
    {
        disabledFrom = disabledFrom;
    }

    public LocalDate getDisabledTo()
    {
        return disabledTo;
    }

    public void setDisabledTo(LocalDate disabledTo)
    {
        disabledTo = disabledTo;
    }

    public Medicover getMedicover()
    {
        return medicover;
    }

    public void setMedicover(Medicover medicover)
    {
        medicover = medicover;
    }

    public ContractType getContractType()
    {
        return contractType;
    }

    public void setContractType(ContractType contractType)
    {
        contractType = contractType;
    }

    public IncomePerPerson getIncomePerPerson()
    {
        return incomePerPerson;
    }

    public void setIncomePerPerson(IncomePerPerson incomePerPerson)
    {
        incomePerPerson = incomePerPerson;
    }

    public boolean isZFSS1()
    {
        return ZFSS1;
    }

    public void setZFSS1(boolean ZFSS1)
    {
        ZFSS1 = ZFSS1;
    }

    public boolean isZFSS2()
    {
        return ZFSS2;
    }

    public void setZFSS2(boolean ZFSS2)
    {
        ZFSS2 = ZFSS2;
    }

    public boolean isZFSS3()
    {
        return ZFSS3;
    }

    public void setZFSS3(boolean ZFSS3)
    {
        ZFSS3 = ZFSS3;
    }

    public boolean isZFSS4()
    {
        return ZFSS4;
    }

    public void setZFSS4(boolean ZFSS4)
    {
        ZFSS4 = ZFSS4;
    }

    public boolean isZFSS5()
    {
        return ZFSS5;
    }

    public void setZFSS5(boolean ZFSS5)
    {
        ZFSS5 = ZFSS5;
    }

    public boolean isZFSS6()
    {
        return ZFSS6;
    }

    public void setZFSS6(boolean ZFSS6)
    {
        ZFSS6 = ZFSS6;
    }

    public boolean isZFSS7()
    {
        return ZFSS7;
    }

    public void setZFSS7(boolean ZFSS7)
    {
        ZFSS7 = ZFSS7;
    }

    public boolean isHasChildren()
    {
        return hasChildren;
    }

    public void setHasChildren(boolean hasChildren)
    {
        hasChildren = hasChildren;
    }

    public boolean isWillParent()
    {
        return willParent;
    }

    public void setWillParent(boolean willParent)
    {
        willParent = willParent;
    }

    public ChildUnderFourPermissions getChildUnderFourPermissions()
    {
        return childUnderFourPermissions;
    }

    public void setChildUnderFourPermissions(ChildUnderFourPermissions childUnderFourPermissions)
    {
        childUnderFourPermissions = childUnderFourPermissions;
    }

    public ChildUnderFourteenPermissions getChildUnderFourteenPermissions()
    {
        return childUnderFourteenPermissions;
    }

    public void setChildUnderFourteenPermissions(ChildUnderFourteenPermissions childUnderFourteenPermissions)
    {
        childUnderFourteenPermissions = childUnderFourteenPermissions;
    }

    public boolean isWillReducedTask()
    {
        return willReducedTask;
    }

    public void setWillReducedTask(boolean willReducedTask)
    {
        willReducedTask = willReducedTask;
    }

    public boolean isMethodOfTaxation()
    {
        return methodOfTaxation;
    }

    public void setMethodOfTaxation(boolean methodOfTaxation)
    {
        methodOfTaxation = methodOfTaxation;
    }

    public String getAnnualEarningsFamily()
    {
        return annualEarningsFamily;
    }

    public void setAnnualEarningsFamily(String annualEarningsFamily)
    {
        annualEarningsFamily = annualEarningsFamily;
    }

    public boolean isWillTaxReducingAmount()
    {
        return willTaxReducingAmount;
    }

    public void setWillTaxReducingAmount(boolean willTaxReducingAmount)
    {
        willTaxReducingAmount = willTaxReducingAmount;
    }

    public boolean isWillHigherTask()
    {
        return willHigherTask;
    }

    public void setWillHigherTask(boolean willHigherTask)
    {
        willHigherTask = willHigherTask;
    }

    public Month getHigherTaskMonth()
    {
        return higherTaskMonth;
    }

    public void setHigherTaskMonth(Month higherTaskMonth)
    {
        higherTaskMonth = higherTaskMonth;
    }

    public boolean isWillIncreasedCosts()
    {
        return willIncreasedCosts;
    }

    public void setWillIncreasedCosts(boolean willIncreasedCosts)
    {
        willIncreasedCosts = willIncreasedCosts;
    }

    public boolean isWillZUS()
    {
        return willZUS;
    }

    public void setWillZUS(boolean willZUS)
    {
        willZUS = willZUS;
    }

    public String getAnnualEarningsZUS()
    {
        return annualEarningsZUS;
    }

    public void setAnnualEarningsZUS(String annualEarningsZUS)
    {
        annualEarningsZUS = annualEarningsZUS;
    }
}
