package pl.documents.model;

import org.hibernate.annotations.GenericGenerator;
import pl.documents.model.enums.*;
import pl.documents.model.projection.CandidateWriteModel;
import pl.documents.model.projection.WorkerWriteModelRest;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.Month;
import java.util.Set;
import java.util.UUID;

/**
 * Dane niezbędne do wypełnienia dokumentów pracownika
 */
@Entity
@Table(name = "WORKERS")
public class Worker
{
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
           name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private UUID id;

    /**
     * Dane kontaktowe (numer telefonu)
     */
    private String phoneNumber = null;
    /**
     * Miejsce wypełnienia dokumentów
     */
    private String fillLocation = null;
    /**
     * Płeć
     */
    private Sex sex = null;
    /**
     * Pierwsze imię
     */
    private String firstName = null;
    /**
     * Drugie imię
     */
    private String secondName = null;
    /**
     * Nazwisko
     */
    private String surname = null;
    /**
     * Data urodzenia(YYYY-MM-DD)
     */
    private LocalDate dateOfBirth = null;
    /**
     * Kwalifikacje zawodowe
     */
    private String qualifications = null;
    /**
     * Dodatkowe dane osobowe
     */
    private String additionalPersonalData = null;

    private String prevEmployment;
    /**
     * Obywatelstwo. Pole wypełniane, gdy pracownik nie posiada Polskiego obywatelstwa
     */
    private String citizenship = null;
    /**
     * PESEL, gdy pracownik jest obywatelem Polski, numer innego dokumentu,
     * gdy jest obywatelem innego państwa
     */
    private String documentNumber = null;
    /**
     * Gdy pracownik nie jest obywatelem Polski, to typ dokumentu,
     * z którego wpisany został odpowiedni numer
     */
    private String documentType = null;
    /**
     * Urząd skarbowy
     */
    private String taxOffice = null;
    /**
     * Imię osoby, którą należy zawiadomić w razie wypadku
     */
    private String personToNotify;
    /**
     * Pracownik chce korzystać ze szczególnych uprawnień przewidzianych
     * w kodeksie pracy. Konieczność wpisania członków rodziny (dzieci albo współmałżonka)
     */
    private boolean willSpecialPowersForFamily = false;
    /**
     * Nip pracownika
     */
    private String NIP = null;
    /**
     * Czy pracownik chce złożyć PIY-2: TRUE-TAK, FALSE-NIE
     */
    private boolean willPIT2 = false;
    /**
     * Stanowisko
     */
    private String position = null;
    /**
     * Departament
     */
    private String department = null;
    /**
     * Czy pracownik nie jest/jest emerytem/rencistą
     */
    private Pension pension = null;
    /**
     * Data zatrudnienia(YYYY-MM-DD)
     */
    private LocalDate employmentDate = null;
    /**
     * Nazwa banku
     */
    private String bank = null;
    /**
     * Numer konta
     */
    private String accountNumber = null;
    /**
     * Aktualne poświadczenie bezpieczeństwa do informacji niejawnych o klauzuli tajności
     */
    private SecurityClearance securityClearance = null;
    /**
     * NFZ, do którego należy pracownik
     */
    private String NFZ = null;
    /**
     * Emerytura/renta - numer decyzji ZUS
     */
    private String pensionZUSNumber = null;
    /**
     * Czy pracownik jest niepełnosprawnu: TRUE-TAK, FALSE-NIE
     */
    private LocalDate pensionFrom;
    private boolean isDisabled = false;
    /**
     * Jeśli pracownik jest niepełnosprawny, numer decyzji ZUS
     */
    private String disabledZUSNumber = null;
    /**
     * Data, początek niepełnosprawności pracownika
     */
    private LocalDate disabledFrom = null;
    /**
     * Data, koniec niepełnosprawności pracownika
     */
    private LocalDate disabledTo = null;
    /**
     * Pakiet wybrany przez pracownika w ramach MEDICOVER
     */
    private Medicover medicover = null;
    /**
     * Typ umowy pracownika
     */
    private ContractType contractType = null;
    /**
     * Dochód brutto na osobę osób pozostających z pracownikiem w wspoólnym
     * gospodarstwie domowym
     */
    private IncomePerPerson incomePerPerson = null;
    /**
     * Pracownik chce korzystać z:
     * leczenie sanatoryjne i wczasy profilaktyczno-lecznicze zakupione
     * indywidualnie przez osobę uprawnioną do korzystania z Funduszu
     * TRUE-TAK, FALSE-NIE
     */
    private boolean zfss1 = false;
    /**
     * Pracownik chce korzystać z:
     * pomoc rzeczowa oraz zapomogi pieniężne przyznawane w przypadku
     * indywidualnych zdarzeń losowych, klęsk żywiołowych lub długotrwałej choroby
     * TRUE-TAK, FALSE-NIE
     */
    private boolean zfss2 = false;
    /**
     * Pracownik chce korzystać z:
     * w zakresie działalności kulturalno-oświatowej :
     * dopłaty do imprez o charakterze kulturalno – oświatowym
     * TRUE-TAK, FALSE-NIE
     */
    private boolean zfss3 = false;
    /**
     * Pracownik chce korzystać z:
     * dofinansowanie do imprez o charakterze sportowym i rekreacyjnym oraz w
     * profilaktyce zdrowia dopłaty do karnetów związanych z programami zdrowia, rehabilitacji
     * i fizykoterapii zdrowotnej oraz do innych form rekreacji ruchowej
     * TRUE-TAK, FALSE-NIE
     */
    private boolean zfss4 = false;
    /**
     * Pracownik chce korzystać z:
     * ofinansowanie działalności sportowo-rekreacyjnej,
     * w tym zakup sprzętu rekreacyjno-sportowego , rehabilitacyjnego
     * TRUE-TAK, FALSE-NIE
     */
    private boolean zfss5 = false;
    /**
     * Pracownik chce korzystać z:
     * rzeczowe świadczenia okolicznościowe , takie jak: świąteczne bony towarowe,
     * paczki świąteczne, karty przedpłacone
     * TRUE-TAK, FALSE-NIE
     */
    private boolean zfss6 = false;
    /**
     * Pracownik chce korzystać z:
     * dofinansowanie do opieki nad dziećmi sprawowanej przez dziennego
     * opiekuna lub nianię oraz w klubach dziecięcych
     * TRUE-TAK, FALSE-NIE
     */
    private boolean zfss7 = false;
    /**
     * Pracownik posiada dzieci:
     * TRUE-TAK, FALSE-NIE
     */
    private boolean hasChildren = false;
    /**
     * Pracownik chce wypełnić oświadczenie rodzic opiekun prawny
     */
    private boolean willParent = false;
    /**
     * Uprawnienia, z których korzysta pracownik, sprawując opiekę nad dzieckiem do lat 4
     */
    private ChildUnderFourPermissions childUnderFourPermissions = null;
    /**
     * Uprawnienia, z których korzysta pracownik, sprawując opiekę nad dzieckiem do lat 14
     */
    private ChildUnderFourteenPermissions childUnderFourteenPermissions = null;
    /**
     * Czy pracownik chce wypełnić oświadczenie do celów ulgowego obliczania zaliczek
     * na podatek dochodowy od osób fizycznych
     * TRUE-TAK, FALSE-NIE
     */
    private boolean willReducedTask = false;
    /**
     * Opcjonalny sposób opodatkowania dochodów
     * TRUE-łącznie z małżonkiem,
     * FALSE-na zasadach określonych dla osób samotnie wychowujących małoletnie dzieci
     */
    private boolean methodOfTaxation = false;
    /**
     * Opcjonalne pole, gdy pracownik chce wypełnić oświadczenie do celów ulgowego obliczania zaliczek
     * przewidywane dochody współmałżonka/dzieci w danym roku
     */
    private String annualEarningsFamily = null;
    /**
     * Czy pracownik chce wypełnić oświadczenie dotyczące kwoty zmniejszającej podatek
     * TRUE-TAK, FALSE-NIE
     */
    private boolean willTaxReducingAmount = false;
    /**
     * Czy pracownik chce wypełnić oświadczenie dotyczące wyższej stawki podatkowej
     * TRUE-TAK, FALSE-NIE
     */
    private boolean willHigherTask = false;
    /**
     * Miesiąc, od którego zostanie dla pracownika zastosowana wyższa stawka podatkowa
     */
    private Month higherTaskMonth = null;
    /**
     * Czy pracownik chce wypełnić oświadczenie
     * do celów stosowania podwyższonych kosztów uzyskania przychodów
     * TRUE-TAK, FALSE-NIE
     */
    private boolean willIncreasedCosts = false;
    /**
     * Czy pracownik chce wypełnić oświadczenie o osiągniętych przychodach
     * dla celów naliczania składek na ubezpieczenia społeczne (ZUS)
     * TRUE-TAK, FALSE-NIE
     */
    private boolean willZUS = false;
    /**
     * przychody pracownika, będące podstawą naliczania składek na ubezpieczenia społeczne
     */
    private String annualEarningsZUS = null;
    /**
     * Nazwy szkół i lata ich ukończenia
     */
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "worker")
    private Set<Education> education = null;

    /**
     * Adresy pracownika (zamieszkania, zameldowania, do korespondencji)
     */
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "worker")
    private Set<Address> addresses = null;
    /**
     * Członkowie rodziny pracownika
     */
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "worker")
    private Set<FamilyMember> familyMembers = null;

    @OneToOne(mappedBy = "worker")
    private User user;
    public Worker()
    {

    }

    public UUID getId()
    {
        return id;
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

    public LocalDate getDateOfBirth()
    {
        return dateOfBirth;
    }

    public String getQualifications()
    {
        return qualifications;
    }

    public String getAdditionalPersonalData()
    {
        return additionalPersonalData;
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

    public String getPosition()
    {
        return position;
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


    public Set<Education> getEducation()
    {
        return education;
    }

    public void setEducation(Set<Education> education)
    {
        this.education = education;
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

    public String getPersonToNotify()
    {
        return personToNotify;
    }

    public LocalDate getPensionFrom()
    {
        return pensionFrom;
    }


    public Worker(String email, String phoneNumber, String fillLocation, Sex sex, String firstName, String secondName, String surname, LocalDate dateOfBirth, String profession, String specialty, String title, String qualifications, String additionalPersonalData, boolean polishCitizen, String citizenship, String documentNumber, String documentType, String taxOffice, String authorizedName, String authorizedSurname, String authorizedContact, boolean willSpecialPowersForFamily, String NIP, boolean willPIT2, String position, String department, Pension pension, LocalDate employmentDate, String bank, String accountNumber, SecurityClearance securityClearance, String NFZ, String pensionZUSNumber, boolean isDisabled, String disabledZUSNumber, LocalDate disabledFrom, LocalDate disabledTo, Medicover medicover, ContractType contractType, IncomePerPerson incomePerPerson, boolean zfss1, boolean zfss2, boolean zfss3, boolean zfss4, boolean zfss5, boolean zfss6, boolean zfss7, boolean hasChildren, boolean willParent, ChildUnderFourPermissions childUnderFourPermissions, ChildUnderFourteenPermissions childUnderFourteenPermissions, boolean willReducedTask, boolean methodOfTaxation, String annualEarningsFamily, boolean willTaxReducingAmount, boolean willHigherTask, Month higherTaskMonth, boolean willIncreasedCosts, boolean willZUS, String annualEarningsZUS)
    {
        this.phoneNumber = phoneNumber;
        this.fillLocation = fillLocation;
        this.sex = sex;
        this.firstName = firstName;
        this.secondName = secondName;
        this.surname = surname;
        this.dateOfBirth = dateOfBirth;
        this.qualifications = qualifications;
        this.additionalPersonalData = additionalPersonalData;
        this.citizenship = citizenship;
        this.documentNumber = documentNumber;
        this.documentType = documentType;
        this.taxOffice = taxOffice;
        this.willSpecialPowersForFamily = willSpecialPowersForFamily;
        this.NIP = NIP;
        this.willPIT2 = willPIT2;
        this.position = position;
        this.department = department;
        this.pension = pension;
        this.employmentDate = employmentDate;
        this.bank = bank;
        this.accountNumber = accountNumber;
        this.securityClearance = securityClearance;
        this.NFZ = NFZ;
        this.pensionZUSNumber = pensionZUSNumber;
        this.isDisabled = isDisabled;
        this.disabledZUSNumber = disabledZUSNumber;
        this.disabledFrom = disabledFrom;
        this.disabledTo = disabledTo;
        this.medicover = medicover;
        this.contractType = contractType;
        this.incomePerPerson = incomePerPerson;
        this.zfss1 = zfss1;
        this.zfss2 = zfss2;
        this.zfss3 = zfss3;
        this.zfss4 = zfss4;
        this.zfss5 = zfss5;
        this.zfss6 = zfss6;
        this.zfss7 = zfss7;
        this.hasChildren = hasChildren;
        this.willParent = willParent;
        this.childUnderFourPermissions = childUnderFourPermissions;
        this.childUnderFourteenPermissions = childUnderFourteenPermissions;
        this.willReducedTask = willReducedTask;
        this.methodOfTaxation = methodOfTaxation;
        this.annualEarningsFamily = annualEarningsFamily;
        this.willTaxReducingAmount = willTaxReducingAmount;
        this.willHigherTask = willHigherTask;
        this.higherTaskMonth = higherTaskMonth;
        this.willIncreasedCosts = willIncreasedCosts;
        this.willZUS = willZUS;
        this.annualEarningsZUS = annualEarningsZUS;
    }

    /**
     * Update podstawowych danych pracownika
     * @param source źródło danych, z którego pobrane zostaną nowe wartości
     */
    public void updateFrom(final Worker source)
    {
        this.phoneNumber = source.phoneNumber;//
        this.fillLocation = source.fillLocation;//
        this.sex = source.sex;
        this.firstName = source.firstName;//
        this.secondName = source.secondName;
        this.surname = source.surname;//
        this.dateOfBirth = source.dateOfBirth;
        this.qualifications = source.qualifications;
        this.additionalPersonalData = source.additionalPersonalData;
        this.citizenship = source.citizenship;
        this.documentNumber = source.documentNumber;//
        this.documentType = source.documentType;//
        this.taxOffice = source.taxOffice;
        this.willSpecialPowersForFamily = source.willSpecialPowersForFamily;
        this.NIP = source.NIP;//
        this.willPIT2 = source.willPIT2;
        this.position = source.position;
        this.department = source.department;
        this.pension = source.pension;
        this.employmentDate = source.employmentDate;
        this.bank = source.bank;
        this.accountNumber = source.accountNumber;
        this.securityClearance = source.securityClearance;
        this.NFZ = source.NFZ;
        this.pensionZUSNumber = source.pensionZUSNumber;
        this.isDisabled = source.isDisabled;
        this.disabledZUSNumber = source.disabledZUSNumber;
        this.disabledFrom = source.disabledFrom;
        this.disabledTo = source.disabledTo;
        this.medicover = source.medicover;
        this.contractType = source.contractType;
        this.incomePerPerson = source.incomePerPerson;
        this.zfss1 = source.zfss1;
        this.zfss2 = source.zfss2;
        this.zfss3 = source.zfss3;
        this.zfss4 = source.zfss4;
        this.zfss5 = source.zfss5;
        this.zfss6 = source.zfss6;
        this.zfss7 = source.zfss7;
        this.hasChildren = source.hasChildren;
        this.willParent = source.willParent;
        this.childUnderFourPermissions = source.childUnderFourPermissions;
        this.childUnderFourteenPermissions = source.childUnderFourteenPermissions;
        this.willReducedTask = source.willReducedTask;
        this.methodOfTaxation = source.methodOfTaxation;
        this.annualEarningsFamily = source.annualEarningsFamily;
        this.willTaxReducingAmount = source.willTaxReducingAmount;
        this.willHigherTask = source.willHigherTask;
        this.higherTaskMonth = source.higherTaskMonth;
        this.willIncreasedCosts = source.willIncreasedCosts;
        this.willZUS = source.willZUS;
        this.annualEarningsZUS = source.annualEarningsZUS;
    }
    public void updateCandidate(CandidateWriteModel candidate)
    {
        this.firstName=candidate.getFirstName();
        this.secondName=candidate.getSecondName();
        this.surname=candidate.getSurname();
        this.dateOfBirth =candidate.getDateOfBirth();
        this.sex=candidate.getSex();
        this.phoneNumber=candidate.getPhoneNumber();
        this.fillLocation=candidate.getFillLocation();
        this.qualifications=candidate.getQualifications();
        this.prevEmployment=candidate.getPrevEmployment();
        this.additionalPersonalData=candidate.getAdditionalPersonalData();
    }
    public void updateImportantData(final Worker source)
    {
        //this.email=source.getEmail();
        //this.password=source.getPassword();
    }






    public void setId(UUID id)
    {
        this.id = id;
    }
    //TYLKO DO TESTÓW
    //public void setPassword(String password)
   // {
    //    this.password = password;
   // }

   // public void setEmail(String email)
   // {
   //    this.email = email;
    //}

    public void setPhoneNumber(String phoneNumber)
    {
        this.phoneNumber = phoneNumber;
    }

    public void setFillLocation(String fillLocation)
    {
        this.fillLocation = fillLocation;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public void setSurname(String surname)
    {
        this.surname = surname;
    }

    public void setDocumentNumber(String documentNumber)
    {
        this.documentNumber = documentNumber;
    }

    public void setDocumentType(String documentType)
    {
        this.documentType = documentType;
    }

    public void setNIP(String NIP)
    {
        this.NIP = NIP;
    }

    public void setAccountNumber(String accountNumber)
    {
        this.accountNumber = accountNumber;
    }

    public String getPrevEmployment()
    {
        return prevEmployment;
    }

    public void updateWorkerRest(WorkerWriteModelRest worker)
    {
        this.documentNumber=worker.getDocumentNumber();
        this.documentType=worker.getDocumentType();
        this.NIP=worker.getNip();
        this.taxOffice=worker.getTaxOffice();
        this.citizenship=worker.getCitizenship();
        this.personToNotify= worker.getPersonToNotify();
        this.position =worker.getPosition();
        this.department = worker.getDepartment();
        this.NFZ = worker.getNfz();
        this.bank= worker.getBank();
        this.accountNumber = worker.getAccountNumber();
        this.pension = worker.getPension();
        this.pensionZUSNumber = worker.getPensionZUSNumber();
        this.pensionFrom = worker.getPensionFrom();
        this.isDisabled = worker.isDisabled();
        this.disabledZUSNumber = worker.getDisabledZUSNumber();
        this.disabledFrom = worker.getDisabledFrom();
        this.disabledTo= worker.getDisabledTo();

        this.willSpecialPowersForFamily=worker.isWillSpecialPowersForFamily();
        this.willPIT2=worker.isWillPIT2();
        this.employmentDate=worker.getEmploymentDate();
        this.securityClearance=worker.getSecurityClearance();
        this.medicover=worker.getMedicover();
        this.contractType=worker.getContractType();
        this.incomePerPerson=worker.getIncomePerPerson();
        this.zfss1=worker.isZfss1();
        this.zfss2=worker.isZfss2();
        this.zfss3=worker.isZfss3();
        this.zfss4=worker.isZfss4();
        this.zfss5=worker.isZfss5();
        this.zfss6=worker.isZfss6();
        this.zfss7=worker.isZfss7();
        this.hasChildren=worker.isHasChildren();
        this.willParent=worker.isWillParent();
        this.childUnderFourPermissions=worker.getChildUnderFourPermissions();
        this.childUnderFourteenPermissions=worker.getChildUnderFourteenPermissions();
        this.willReducedTask = worker.isWillReducedTask();
        this.methodOfTaxation=worker.isMethodOfTaxation();
        this.annualEarningsFamily= worker.getAnnualEarningsFamily();;
        this.willTaxReducingAmount=worker.isWillTaxReducingAmount();
        this.willHigherTask=worker.isWillHigherTask();
        this.higherTaskMonth=worker.getHigherTaskMonth();
        this.willIncreasedCosts=worker.isWillIncreasedCosts();
        this.willZUS=worker.isWillZUS();
        this.annualEarningsZUS=worker.getAnnualEarningsZUS();


    }
}
