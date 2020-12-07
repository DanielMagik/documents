package pl.documents.model;

import pl.documents.model.enums.Pension;
import pl.documents.model.enums.SecurityClearance;

import javax.persistence.*;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "WORKERS")
public class Worker
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String fillLocation;
    private boolean isPolishCitizen;
    private String citizenship;
    private String documentNumber;
    private String documentType;
    private String taxOffice;
    private String authorizedName;
    private String authorizedSurname;
    private String authorizedContact;
    private String NIP;
    private String workplace;
    private String department;
    private Pension pension;
    private LocalDate employmentDate;
    private String bank;
    private String accountNumber;
    private SecurityClearance securityClearance;
    private String NFZ;
    private String annualEarningsZUS;
    private String annualEarningsFamily;
    private String pensionZUSNumber;
    private boolean willParent;
    private boolean willReducedTask;
    private boolean willTaxReducingAmount;
    private boolean willHigherTask;
    private DayOfWeek higherTaskMonth;
    private boolean hasChildren;
    private boolean willSpecialPowersForFamily;
    private boolean willIncreasedCosts;
    private boolean willZUS;
    private boolean isDisabled;
    private String disabledZUSNumber;
    private LocalDate disabledFrom;
    private LocalDate disabledTo;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;
    @OneToOne
    @JoinColumn(name = "ID_CANDIDATE",referencedColumnName = "id")
    private Candidate candidate;
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "worker")
    private Set<Address> addresses;
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "worker")
    private Set<FamilyMember> familyMembers;



    public Worker()
    {
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getFillLocation()
    {
        return fillLocation;
    }

    public void setFillLocation(String fillLocation)
    {
        this.fillLocation = fillLocation;
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

    public String getNIP()
    {
        return NIP;
    }

    public void setNIP(String NIP)
    {
        this.NIP = NIP;
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

    public String getAnnualEarningsZUS()
    {
        return annualEarningsZUS;
    }

    public void setAnnualEarningsZUS(String annualEarningsZUS)
    {
        this.annualEarningsZUS = annualEarningsZUS;
    }

    public String getAnnualEarningsFamily()
    {
        return annualEarningsFamily;
    }

    public void setAnnualEarningsFamily(String annualEarningsFamily)
    {
        this.annualEarningsFamily = annualEarningsFamily;
    }

    public String getPensionZUSNumber()
    {
        return pensionZUSNumber;
    }

    public void setPensionZUSNumber(String pensionZUSNumber)
    {
        this.pensionZUSNumber = pensionZUSNumber;
    }



    public boolean isWillParent()
    {
        return willParent;
    }

    public void setWillParent(boolean willParent)
    {
        this.willParent = willParent;
    }

    public boolean isWillReducedTask()
    {
        return willReducedTask;
    }

    public void setWillReducedTask(boolean willReducedTask)
    {
        this.willReducedTask = willReducedTask;
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

    public DayOfWeek getHigherTaskMonth()
    {
        return higherTaskMonth;
    }

    public void setHigherTaskMonth(DayOfWeek higherTaskMonth)
    {
        this.higherTaskMonth = higherTaskMonth;
    }

    public boolean isHasChildren()
    {
        return hasChildren;
    }

    public void setHasChildren(boolean hasChildren)
    {
        this.hasChildren = hasChildren;
    }

    public boolean isWillSpecialPowersForFamily()
    {
        return willSpecialPowersForFamily;
    }

    public void setWillSpecialPowersForFamily(boolean willSpecialPowersForFamily)
    {
        this.willSpecialPowersForFamily = willSpecialPowersForFamily;
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

    public Candidate getCandidate()
    {
        return candidate;
    }

    public void setCandidate(Candidate candidate)
    {
        this.candidate = candidate;
    }

    public Set<Address> getAddresses()
    {
        return addresses;
    }

    public void setAddresses(Set<Address> addresses)
    {
        this.addresses = addresses;
    }
    public Set<FamilyMember> getFamilyMembers()
    {
        return familyMembers;
    }

    public void setFamilyMembers(Set<FamilyMember> familyMembers)
    {
        this.familyMembers = familyMembers;
    }
}
