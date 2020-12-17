package pl.documents.model.projection;

import pl.documents.model.Address;
import pl.documents.model.Worker;
import pl.documents.model.enums.*;

import java.time.LocalDate;
import java.time.Month;
import java.util.Set;
import java.util.stream.Collectors;

public class WorkerReadModel
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
    private String documentType;
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
    private Set<AddressReadModel> addresses;
    private Set<EducationReadModel> education;
    private Set<EmploymentReadModel> employments;
    private Set<FamilyMemberReadModel> familyMembers;


    public WorkerReadModel(Worker source)
    {
        this.email = source.getEmail();
        this.phoneNumber = source.getPhoneNumber();
        this.fillLocation = source.getFillLocation();
        this.sex = source.getSex();
        this.firstName = source.getFirstName();
        this.secondName = source.getSecondName();
        this.surname = source.getSurname();
        this.birthDate = source.getBirthDate();
        this.profession = source.getProfession();
        this.specialty = source.getSpecialty();
        this.title = source.getTitle();
        this.qualifications = source.getQualifications();
        this.optionalData = source.getOptionalData();
        this.isPolishCitizen = source.isPolishCitizen();
        this.citizenship = source.getCitizenship();
        this.documentNumber = source.getDocumentNumber();
        this.documentType = source.getDocumentType();
        this.taxOffice = source.getTaxOffice();
        this.authorizedName = source.getAuthorizedName();
        this.authorizedSurname = source.getAuthorizedSurname();
        this.authorizedContact = source.getAuthorizedContact();
        this.willSpecialPowersForFamily = source.isWillSpecialPowersForFamily();
        this.NIP = source.getNIP();
        this.willPIT2 = source.isWillPIT2();
        this.workplace = source.getWorkplace();
        this.department = source.getDepartment();
        this.pension = source.getPension();
        this.employmentDate = source.getEmploymentDate();
        this.bank = source.getBank();
        this.accountNumber = source.getAccountNumber();
        this.securityClearance = source.getSecurityClearance();
        this.NFZ = source.getNFZ();
        this.pensionZUSNumber = source.getPensionZUSNumber();
        this.isDisabled = source.isDisabled();
        this.disabledZUSNumber = source.getDisabledZUSNumber();
        this.disabledFrom = source.getDisabledFrom();
        this.disabledTo = source.getDisabledTo();
        this.medicover = source.getMedicover();
        this.contractType = source.getContractType();
        this.incomePerPerson = source.getIncomePerPerson();
        this.ZFSS1 = source.isZFSS1();
        this.ZFSS2 = source.isZFSS2();
        this.ZFSS3 = source.isZFSS3();
        this.ZFSS4 = source.isZFSS4();
        this.ZFSS5 = source.isZFSS5();
        this.ZFSS6 = source.isZFSS6();
        this.ZFSS7 = source.isZFSS7();
        this.hasChildren = source.isHasChildren();
        this.willParent = source.isWillParent();
        this.childUnderFourPermissions = source.getChildUnderFourPermissions();
        this.childUnderFourteenPermissions = source.getChildUnderFourteenPermissions();
        this.willReducedTask = source.isWillReducedTask();
        this.methodOfTaxation = source.isMethodOfTaxation();
        this.annualEarningsFamily = source.getAnnualEarningsFamily();
        this.willTaxReducingAmount = source.isWillTaxReducingAmount();
        this.willHigherTask = source.isWillHigherTask();
        this.higherTaskMonth = source.getHigherTaskMonth();
        this.willIncreasedCosts = source.isWillIncreasedCosts();
        this.willZUS = source.isWillZUS();
        this.annualEarningsZUS = source.getAnnualEarningsZUS();
        if(source.getAddresses()!=null)
        this.addresses = source.getAddresses().stream()
                .map(AddressReadModel::new).collect(Collectors.toSet());
        if(source.getEducation()!=null)
        this.education = source.getEducation().stream()
                .map(EducationReadModel::new).collect(Collectors.toSet());
        if(source.getEmployments()!=null)
        this.employments = source.getEmployments().stream()
                .map(EmploymentReadModel::new).collect(Collectors.toSet());
        if(source.getFamilyMembers()!=null)
        this.familyMembers = source.getFamilyMembers().stream()
                .map(FamilyMemberReadModel::new).collect(Collectors.toSet());

    }


    public Set<AddressReadModel> getAddresses()
    {
        return addresses;
    }

    public void setAddresses(Set<AddressReadModel> addresses)
    {
        this.addresses = addresses;
    }

    public Set<EducationReadModel> getEducation()
    {
        return education;
    }

    public void setEducation(Set<EducationReadModel> education)
    {
        this.education = education;
    }

    public Set<EmploymentReadModel> getEmployments()
    {
        return employments;
    }

    public void setEmployments(Set<EmploymentReadModel> employments)
    {
        this.employments = employments;
    }

    public Set<FamilyMemberReadModel> getFamilyMembers()
    {
        return familyMembers;
    }

    public void setFamilyMembers(Set<FamilyMemberReadModel> familyMembers)
    {
        this.familyMembers = familyMembers;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getPhoneNumber()
    {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber)
    {
        this.phoneNumber = phoneNumber;
    }

    public String getFillLocation()
    {
        return fillLocation;
    }

    public void setFillLocation(String fillLocation)
    {
        this.fillLocation = fillLocation;
    }

    public Sex getSex()
    {
        return sex;
    }

    public void setSex(Sex sex)
    {
        this.sex = sex;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public String getSecondName()
    {
        return secondName;
    }

    public void setSecondName(String secondName)
    {
        this.secondName = secondName;
    }

    public String getSurname()
    {
        return surname;
    }

    public void setSurname(String surname)
    {
        this.surname = surname;
    }

    public LocalDate getBirthDate()
    {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate)
    {
        this.birthDate = birthDate;
    }

    public String getProfession()
    {
        return profession;
    }

    public void setProfession(String profession)
    {
        this.profession = profession;
    }

    public String getSpecialty()
    {
        return specialty;
    }

    public void setSpecialty(String specialty)
    {
        this.specialty = specialty;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getQualifications()
    {
        return qualifications;
    }

    public void setQualifications(String qualifications)
    {
        this.qualifications = qualifications;
    }

    public String getOptionalData()
    {
        return optionalData;
    }

    public void setOptionalData(String optionalData)
    {
        this.optionalData = optionalData;
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
        this.citizenship = citizenship;
    }

    public String getDocumentNumber()
    {
        return documentNumber;
    }

    public void setDocumentNumber(String documentNumber)
    {
        this.documentNumber = documentNumber;
    }

    public String getDocumentType()
    {
        return documentType;
    }

    public void setDocumentType(String documentType)
    {
        this.documentType = documentType;
    }

    public String getTaxOffice()
    {
        return taxOffice;
    }

    public void setTaxOffice(String taxOffice)
    {
        this.taxOffice = taxOffice;
    }

    public String getAuthorizedName()
    {
        return authorizedName;
    }

    public void setAuthorizedName(String authorizedName)
    {
        this.authorizedName = authorizedName;
    }

    public String getAuthorizedSurname()
    {
        return authorizedSurname;
    }

    public void setAuthorizedSurname(String authorizedSurname)
    {
        this.authorizedSurname = authorizedSurname;
    }

    public String getAuthorizedContact()
    {
        return authorizedContact;
    }

    public void setAuthorizedContact(String authorizedContact)
    {
        this.authorizedContact = authorizedContact;
    }

    public boolean isWillSpecialPowersForFamily()
    {
        return willSpecialPowersForFamily;
    }

    public void setWillSpecialPowersForFamily(boolean willSpecialPowersForFamily)
    {
        this.willSpecialPowersForFamily = willSpecialPowersForFamily;
    }

    public String getNIP()
    {
        return NIP;
    }

    public void setNIP(String NIP)
    {
        this.NIP = NIP;
    }

    public boolean isWillPIT2()
    {
        return willPIT2;
    }

    public void setWillPIT2(boolean willPIT2)
    {
        this.willPIT2 = willPIT2;
    }

    public String getWorkplace()
    {
        return workplace;
    }

    public void setWorkplace(String workplace)
    {
        this.workplace = workplace;
    }

    public String getDepartment()
    {
        return department;
    }

    public void setDepartment(String department)
    {
        this.department = department;
    }

    public Pension getPension()
    {
        return pension;
    }

    public void setPension(Pension pension)
    {
        this.pension = pension;
    }

    public LocalDate getEmploymentDate()
    {
        return employmentDate;
    }

    public void setEmploymentDate(LocalDate employmentDate)
    {
        this.employmentDate = employmentDate;
    }

    public String getBank()
    {
        return bank;
    }

    public void setBank(String bank)
    {
        this.bank = bank;
    }

    public String getAccountNumber()
    {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber)
    {
        this.accountNumber = accountNumber;
    }

    public SecurityClearance getSecurityClearance()
    {
        return securityClearance;
    }

    public void setSecurityClearance(SecurityClearance securityClearance)
    {
        this.securityClearance = securityClearance;
    }

    public String getNFZ()
    {
        return NFZ;
    }

    public void setNFZ(String NFZ)
    {
        this.NFZ = NFZ;
    }

    public String getPensionZUSNumber()
    {
        return pensionZUSNumber;
    }

    public void setPensionZUSNumber(String pensionZUSNumber)
    {
        this.pensionZUSNumber = pensionZUSNumber;
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
        this.disabledZUSNumber = disabledZUSNumber;
    }

    public LocalDate getDisabledFrom()
    {
        return disabledFrom;
    }

    public void setDisabledFrom(LocalDate disabledFrom)
    {
        this.disabledFrom = disabledFrom;
    }

    public LocalDate getDisabledTo()
    {
        return disabledTo;
    }

    public void setDisabledTo(LocalDate disabledTo)
    {
        this.disabledTo = disabledTo;
    }

    public Medicover getMedicover()
    {
        return medicover;
    }

    public void setMedicover(Medicover medicover)
    {
        this.medicover = medicover;
    }

    public ContractType getContractType()
    {
        return contractType;
    }

    public void setContractType(ContractType contractType)
    {
        this.contractType = contractType;
    }

    public IncomePerPerson getIncomePerPerson()
    {
        return incomePerPerson;
    }

    public void setIncomePerPerson(IncomePerPerson incomePerPerson)
    {
        this.incomePerPerson = incomePerPerson;
    }

    public boolean isZFSS1()
    {
        return ZFSS1;
    }

    public void setZFSS1(boolean ZFSS1)
    {
        this.ZFSS1 = ZFSS1;
    }

    public boolean isZFSS2()
    {
        return ZFSS2;
    }

    public void setZFSS2(boolean ZFSS2)
    {
        this.ZFSS2 = ZFSS2;
    }

    public boolean isZFSS3()
    {
        return ZFSS3;
    }

    public void setZFSS3(boolean ZFSS3)
    {
        this.ZFSS3 = ZFSS3;
    }

    public boolean isZFSS4()
    {
        return ZFSS4;
    }

    public void setZFSS4(boolean ZFSS4)
    {
        this.ZFSS4 = ZFSS4;
    }

    public boolean isZFSS5()
    {
        return ZFSS5;
    }

    public void setZFSS5(boolean ZFSS5)
    {
        this.ZFSS5 = ZFSS5;
    }

    public boolean isZFSS6()
    {
        return ZFSS6;
    }

    public void setZFSS6(boolean ZFSS6)
    {
        this.ZFSS6 = ZFSS6;
    }

    public boolean isZFSS7()
    {
        return ZFSS7;
    }

    public void setZFSS7(boolean ZFSS7)
    {
        this.ZFSS7 = ZFSS7;
    }

    public boolean isHasChildren()
    {
        return hasChildren;
    }

    public void setHasChildren(boolean hasChildren)
    {
        this.hasChildren = hasChildren;
    }

    public boolean isWillParent()
    {
        return willParent;
    }

    public void setWillParent(boolean willParent)
    {
        this.willParent = willParent;
    }

    public ChildUnderFourPermissions getChildUnderFourPermissions()
    {
        return childUnderFourPermissions;
    }

    public void setChildUnderFourPermissions(ChildUnderFourPermissions childUnderFourPermissions)
    {
        this.childUnderFourPermissions = childUnderFourPermissions;
    }

    public ChildUnderFourteenPermissions getChildUnderFourteenPermissions()
    {
        return childUnderFourteenPermissions;
    }

    public void setChildUnderFourteenPermissions(ChildUnderFourteenPermissions childUnderFourteenPermissions)
    {
        this.childUnderFourteenPermissions = childUnderFourteenPermissions;
    }

    public boolean isWillReducedTask()
    {
        return willReducedTask;
    }

    public void setWillReducedTask(boolean willReducedTask)
    {
        this.willReducedTask = willReducedTask;
    }

    public boolean isMethodOfTaxation()
    {
        return methodOfTaxation;
    }

    public void setMethodOfTaxation(boolean methodOfTaxation)
    {
        this.methodOfTaxation = methodOfTaxation;
    }

    public String getAnnualEarningsFamily()
    {
        return annualEarningsFamily;
    }

    public void setAnnualEarningsFamily(String annualEarningsFamily)
    {
        this.annualEarningsFamily = annualEarningsFamily;
    }

    public boolean isWillTaxReducingAmount()
    {
        return willTaxReducingAmount;
    }

    public void setWillTaxReducingAmount(boolean willTaxReducingAmount)
    {
        this.willTaxReducingAmount = willTaxReducingAmount;
    }

    public boolean isWillHigherTask()
    {
        return willHigherTask;
    }

    public void setWillHigherTask(boolean willHigherTask)
    {
        this.willHigherTask = willHigherTask;
    }

    public Month getHigherTaskMonth()
    {
        return higherTaskMonth;
    }

    public void setHigherTaskMonth(Month higherTaskMonth)
    {
        this.higherTaskMonth = higherTaskMonth;
    }

    public boolean isWillIncreasedCosts()
    {
        return willIncreasedCosts;
    }

    public void setWillIncreasedCosts(boolean willIncreasedCosts)
    {
        this.willIncreasedCosts = willIncreasedCosts;
    }

    public boolean isWillZUS()
    {
        return willZUS;
    }

    public void setWillZUS(boolean willZUS)
    {
        this.willZUS = willZUS;
    }

    public String getAnnualEarningsZUS()
    {
        return annualEarningsZUS;
    }

    public void setAnnualEarningsZUS(String annualEarningsZUS)
    {
        this.annualEarningsZUS = annualEarningsZUS;
    }
}
