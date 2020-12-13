package pl.documents.model;

import pl.documents.model.enums.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Set;

/**
 * Dane niezbędne do wypełnienia dokumentów pracownika
 */
@Entity
@Table(name = "WORKERS")
public class Worker
{
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private int id;
    /**
     * Dane kontaktowe (e-mail)
     */
    private String email = null;
    /**
     * Dane kontaktowe (numer telefonu)
     */
    private String phoneNumber = null;
    /**
     * Hasło do logowania się
     */
    private String password = null;
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
    private LocalDate birthDate = null;
    /**
     *  Zawód
     */
    private String profession = null;
    /**
     * Specjalność
     */
    private String specialty = null;
    /**
     * Tytuł zawodowy/naukowy
     */
    private String title = null;
    /**
     * Kwalifikacje zawodowe
     */
    private String qualifications = null;
    /**
     * Dodatkowe dane osobowe
     */
    private String optionalData = null;
    /**
     * Czy pracownik jest obywatelem Polski: TRUE-TAK, FALSE-NIE
     */

    private boolean isPolishCitizen = false;
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
    private String documentType = null;//TODO Czy to nie powinien być enum?
    /**
     * Urząd skarbowy
     */
    private String taxOffice = null;
    /**
     * Imię osoby, którą należy zawiadomić w razie wypadku
     */
    private String authorizedName = null;
    /**
     * Nazwisko osoby, którą należy zawiadomić w razie wypadku
     */
    private String authorizedSurname = null;
    /**
     * Kontakt do osoby, którą należy zawiadomić w razie wypadku
     */
    private String authorizedContact = null;
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
    private String workplace = null;
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
    private boolean ZFSS1 = false;
    /**
     * Pracownik chce korzystać z:
     * pomoc rzeczowa oraz zapomogi pieniężne przyznawane w przypadku
     * indywidualnych zdarzeń losowych, klęsk żywiołowych lub długotrwałej choroby
     * TRUE-TAK, FALSE-NIE
     */
    private boolean ZFSS2 = false;
    /**
     * Pracownik chce korzystać z:
     * w zakresie działalności kulturalno-oświatowej :
     * dopłaty do imprez o charakterze kulturalno – oświatowym
     * TRUE-TAK, FALSE-NIE
     */
    private boolean ZFSS3 = false;
    /**
     * Pracownik chce korzystać z:
     * dofinansowanie do imprez o charakterze sportowym i rekreacyjnym oraz w
     * profilaktyce zdrowia dopłaty do karnetów związanych z programami zdrowia, rehabilitacji
     * i fizykoterapii zdrowotnej oraz do innych form rekreacji ruchowej
     * TRUE-TAK, FALSE-NIE
     */
    private boolean ZFSS4 = false;
    /**
     * Pracownik chce korzystać z:
     * ofinansowanie działalności sportowo-rekreacyjnej,
     * w tym zakup sprzętu rekreacyjno-sportowego , rehabilitacyjnego
     * TRUE-TAK, FALSE-NIE
     */
    private boolean ZFSS5 = false;
    /**
     * Pracownik chce korzystać z:
     * rzeczowe świadczenia okolicznościowe , takie jak: świąteczne bony towarowe,
     * paczki świąteczne, karty przedpłacone
     * TRUE-TAK, FALSE-NIE
     */
    private boolean ZFSS6 = false;
    /**
     * Pracownik chce korzystać z:
     * dofinansowanie do opieki nad dziećmi sprawowanej przez dziennego
     * opiekuna lub nianię oraz w klubach dziecięcych
     * TRUE-TAK, FALSE-NIE
     */
    private boolean ZFSS7 = false;
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
     * Data i czas utworzenia encji
     */
    private LocalDateTime createDate = null;
    /**
     * Data i czas ostatniej aktualizacji encji
     */
    private LocalDateTime updateDate = null;
    /**
     * Nazwy szkół i lata ich ukończenia
     */
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "worker")
    private Set<Education> education = null;
    /**
     * Przebieg dotychczasowego zatrudnienia
     */
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "worker")

    private Set<Employments> employments = null;
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

    public Worker()
    {

    }

    public int getId()
    {
        return id;
    }

    /*
    public void setId(int id)
    {
        this.id = id;
    }
     */

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

    public Set<Education> getEducation()
    {
        return education;
    }

    public void setEducation(Set<Education> education)
    {
        this.education = education;
    }

    public Set<Employments> getEmployments()
    {
        return employments;
    }

    public void setEmployments(Set<Employments> employments)
    {
        this.employments = employments;
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

    /**
     * Update podstawowych danych pracownika
     * @param source źródło danych, z którego pobrane zostaną nowe wartości
     */


    public void updateFrom(final Worker source)
    {
        this.email = source.email;
        this.phoneNumber = source.phoneNumber;
        this.fillLocation = source.fillLocation;
        this.sex = source.sex;
        this.firstName = source.firstName;
        this.secondName = source.secondName;
        this.surname = source.surname;
        this.birthDate = source.birthDate;
        this.profession = source.profession;
        this.specialty = source.specialty;
        this.title = source.title;
        this.qualifications = source.qualifications;
        this.optionalData = source.optionalData;
        this.isPolishCitizen = source.isPolishCitizen;
        this.citizenship = source.citizenship;
        this.documentNumber = source.documentNumber;
        this.documentType = source.documentType;
        this.taxOffice = source.taxOffice;
        this.authorizedName = source.authorizedName;
        this.authorizedSurname = source.authorizedSurname;
        this.authorizedContact = source.authorizedContact;
        this.willSpecialPowersForFamily = source.willSpecialPowersForFamily;
        this.NIP = source.NIP;
        this.willPIT2 = source.willPIT2;
        this.workplace = source.workplace;
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
        this.ZFSS1 = source.ZFSS1;
        this.ZFSS2 = source.ZFSS2;
        this.ZFSS3 = source.ZFSS3;
        this.ZFSS4 = source.ZFSS4;
        this.ZFSS5 = source.ZFSS5;
        this.ZFSS6 = source.ZFSS6;
        this.ZFSS7 = source.ZFSS7;
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
        this.createDate = source.createDate;
        this.updateDate = source.updateDate;
        this.education = source.education;
        this.employments = source.employments;
        this.addresses = source.addresses;
        this.familyMembers = source.familyMembers;
    }

    @PrePersist
    void prePersist()
    {
        this.createDate = LocalDateTime.now();
    }

    @PreUpdate
    void preUpdate()
    {
        this.updateDate=LocalDateTime.now();
    }
}
