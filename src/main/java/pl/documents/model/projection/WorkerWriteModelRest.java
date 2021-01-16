package pl.documents.model.projection;

import pl.documents.model.enums.*;

import java.time.LocalDate;
import java.time.Month;
import java.util.Set;

public class WorkerWriteModelRest
{
    private String documentNumber;
    private String documentType;
    private String nip;
    private String taxOffice;
    private String citizenship;
    private String personToNotify;
    private String position;
    private String department;
    private String nfz;
    private String bank;
    private String accountNumber;
    private Pension pension;
    private String pensionZUSNumber;
    private LocalDate pensionFrom;
    private boolean disabled;
    private String disabledZUSNumber;
    private LocalDate disabledFrom;
    private LocalDate disabledTo;
    /////////////////////////////////////////////////////////////////
    private boolean willSpecialPowersForFamily;
    private boolean willPIT2;
    private LocalDate employmentDate;
    private SecurityClearance securityClearance;
    private Medicover medicover;
    private ContractType contractType;
    private IncomePerPerson incomePerPerson;
    private boolean zfss1;
    private boolean zfss2;
    private boolean zfss3;
    private boolean zfss4;
    private boolean zfss5;
    private boolean zfss6;
    private boolean zfss7;
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
    private Set<FamilyMemberWriteModel> familyMembers = null;


    public String getDocumentNumber()
    {
        return documentNumber;
    }

    public String getDocumentType()
    {
        return documentType;
    }

    public String getNip()
    {
        return nip;
    }

    public String getTaxOffice()
    {
        return taxOffice;
    }

    public String getCitizenship()
    {
        return citizenship;
    }

    public String getPersonToNotify()
    {
        return personToNotify;
    }

    public String getPosition()
    {
        return position;
    }

    public String getDepartment()
    {
        return department;
    }

    public String getNfz()
    {
        return nfz;
    }

    public String getBank()
    {
        return bank;
    }

    public String getAccountNumber()
    {
        return accountNumber;
    }

    public Pension getPension()
    {
        return pension;
    }

    public String getPensionZUSNumber()
    {
        return pensionZUSNumber;
    }

    public LocalDate getPensionFrom()
    {
        return pensionFrom;
    }

    public boolean isDisabled()
    {
        return disabled;
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

    public boolean isWillSpecialPowersForFamily()
    {
        return willSpecialPowersForFamily;
    }

    public boolean isWillPIT2()
    {
        return willPIT2;
    }

    public LocalDate getEmploymentDate()
    {
        return employmentDate;
    }

    public SecurityClearance getSecurityClearance()
    {
        return securityClearance;
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

    public boolean isZfss1()
    {
        return zfss1;
    }

    public boolean isZfss2()
    {
        return zfss2;
    }

    public boolean isZfss3()
    {
        return zfss3;
    }

    public boolean isZfss4()
    {
        return zfss4;
    }

    public boolean isZfss5()
    {
        return zfss5;
    }

    public boolean isZfss6()
    {
        return zfss6;
    }

    public boolean isZfss7()
    {
        return zfss7;
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

    public Set<AddressWriteModel> getAddresses()
    {
        return addresses;
    }

    public Set<FamilyMemberWriteModel> getFamilyMembers()
    {
        return familyMembers;
    }

}
