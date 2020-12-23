package pl.documents.model.projection;

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

    public String getEmail()
    {
        return email;
    }

    public String getPhoneNumber()
    {
        return phoneNumber;
    }

    public String getFillLocation()
    {
        return fillLocation;
    }

    public Sex getSex()
    {
        return sex;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public String getSecondName()
    {
        return secondName;
    }

    public String getSurname()
    {
        return surname;
    }

    public LocalDate getBirthDate()
    {
        return birthDate;
    }

    public String getProfession()
    {
        return profession;
    }

    public String getSpecialty()
    {
        return specialty;
    }

    public String getTitle()
    {
        return title;
    }

    public String getQualifications()
    {
        return qualifications;
    }

    public String getOptionalData()
    {
        return optionalData;
    }

    public boolean isPolishCitizen()
    {
        return isPolishCitizen;
    }

    public String getCitizenship()
    {
        return citizenship;
    }

    public String getDocumentNumber()
    {
        return documentNumber;
    }

    public String getDocumentType()
    {
        return documentType;
    }

    public String getTaxOffice()
    {
        return taxOffice;
    }

    public String getAuthorizedName()
    {
        return authorizedName;
    }

    public String getAuthorizedSurname()
    {
        return authorizedSurname;
    }

    public String getAuthorizedContact()
    {
        return authorizedContact;
    }

    public boolean isWillSpecialPowersForFamily()
    {
        return willSpecialPowersForFamily;
    }

    public String getNIP()
    {
        return NIP;
    }

    public boolean isWillPIT2()
    {
        return willPIT2;
    }

    public String getWorkplace()
    {
        return workplace;
    }

    public String getDepartment()
    {
        return department;
    }

    public Pension getPension()
    {
        return pension;
    }

    public LocalDate getEmploymentDate()
    {
        return employmentDate;
    }

    public String getBank()
    {
        return bank;
    }

    public String getAccountNumber()
    {
        return accountNumber;
    }

    public SecurityClearance getSecurityClearance()
    {
        return securityClearance;
    }

    public String getNFZ()
    {
        return NFZ;
    }

    public String getPensionZUSNumber()
    {
        return pensionZUSNumber;
    }

    public boolean isDisabled()
    {
        return isDisabled;
    }

    public String getDisabledZUSNumber()
    {
        return disabledZUSNumber;
    }

    public LocalDate getDisabledFrom()
    {
        return disabledFrom;
    }

    public LocalDate getDisabledTo()
    {
        return disabledTo;
    }

    public Medicover getMedicover()
    {
        return medicover;
    }

    public ContractType getContractType()
    {
        return contractType;
    }

    public IncomePerPerson getIncomePerPerson()
    {
        return incomePerPerson;
    }

    public boolean isZFSS1()
    {
        return ZFSS1;
    }

    public boolean isZFSS2()
    {
        return ZFSS2;
    }

    public boolean isZFSS3()
    {
        return ZFSS3;
    }

    public boolean isZFSS4()
    {
        return ZFSS4;
    }

    public boolean isZFSS5()
    {
        return ZFSS5;
    }

    public boolean isZFSS6()
    {
        return ZFSS6;
    }

    public boolean isZFSS7()
    {
        return ZFSS7;
    }

    public boolean isHasChildren()
    {
        return hasChildren;
    }

    public boolean isWillParent()
    {
        return willParent;
    }

    public ChildUnderFourPermissions getChildUnderFourPermissions()
    {
        return childUnderFourPermissions;
    }

    public ChildUnderFourteenPermissions getChildUnderFourteenPermissions()
    {
        return childUnderFourteenPermissions;
    }

    public boolean isWillReducedTask()
    {
        return willReducedTask;
    }

    public boolean isMethodOfTaxation()
    {
        return methodOfTaxation;
    }

    public String getAnnualEarningsFamily()
    {
        return annualEarningsFamily;
    }

    public boolean isWillTaxReducingAmount()
    {
        return willTaxReducingAmount;
    }

    public boolean isWillHigherTask()
    {
        return willHigherTask;
    }

    public Month getHigherTaskMonth()
    {
        return higherTaskMonth;
    }

    public boolean isWillIncreasedCosts()
    {
        return willIncreasedCosts;
    }

    public boolean isWillZUS()
    {
        return willZUS;
    }

    public String getAnnualEarningsZUS()
    {
        return annualEarningsZUS;
    }

    public Set<AddressReadModel> getAddresses()
    {
        return addresses;
    }

    public Set<EducationReadModel> getEducation()
    {
        return education;
    }

    public Set<EmploymentReadModel> getEmployments()
    {
        return employments;
    }

    public Set<FamilyMemberReadModel> getFamilyMembers()
    {
        return familyMembers;
    }
}
