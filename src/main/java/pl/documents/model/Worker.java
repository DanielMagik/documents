package pl.documents.model;

import org.hibernate.annotations.GenericGenerator;
import pl.documents.model.enums.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
    private Set<Employment> employments = null;
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
    public Worker(String email, String password)
    {
        this.email = email;
        this.password = password;
    }

    public String getPassword()
    {
        return password;
    }

    //TODO Getter ID JEST TYMCZASOWO PUBLICZNY
    public UUID getId()
    {
        return id;
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

    //TODO SETTER JEST TYMCZASOWY
    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
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

    public Set<Education> getEducation()
    {
        return education;
    }

    public void setEducation(Set<Education> education)
    {
        this.education = education;
    }

    public Set<Employment> getEmployments()
    {
        return employments;
    }

    public void setEmployments(Set<Employment> employments)
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

    public Worker(String email, String phoneNumber, String fillLocation, Sex sex, String firstName, String secondName, String surname, LocalDate birthDate, String profession, String specialty, String title, String qualifications, String optionalData, boolean isPolishCitizen, String citizenship, String documentNumber, String documentType, String taxOffice, String authorizedName, String authorizedSurname, String authorizedContact, boolean willSpecialPowersForFamily, String NIP, boolean willPIT2, String workplace, String department, Pension pension, LocalDate employmentDate, String bank, String accountNumber, SecurityClearance securityClearance, String NFZ, String pensionZUSNumber, boolean isDisabled, String disabledZUSNumber, LocalDate disabledFrom, LocalDate disabledTo, Medicover medicover, ContractType contractType, IncomePerPerson incomePerPerson, boolean ZFSS1, boolean ZFSS2, boolean ZFSS3, boolean ZFSS4, boolean ZFSS5, boolean ZFSS6, boolean ZFSS7, boolean hasChildren, boolean willParent, ChildUnderFourPermissions childUnderFourPermissions, ChildUnderFourteenPermissions childUnderFourteenPermissions, boolean willReducedTask, boolean methodOfTaxation, String annualEarningsFamily, boolean willTaxReducingAmount, boolean willHigherTask, Month higherTaskMonth, boolean willIncreasedCosts, boolean willZUS, String annualEarningsZUS)
    {
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.fillLocation = fillLocation;
        this.sex = sex;
        this.firstName = firstName;
        this.secondName = secondName;
        this.surname = surname;
        this.birthDate = birthDate;
        this.profession = profession;
        this.specialty = specialty;
        this.title = title;
        this.qualifications = qualifications;
        this.optionalData = optionalData;
        this.isPolishCitizen = isPolishCitizen;
        this.citizenship = citizenship;
        this.documentNumber = documentNumber;
        this.documentType = documentType;
        this.taxOffice = taxOffice;
        this.authorizedName = authorizedName;
        this.authorizedSurname = authorizedSurname;
        this.authorizedContact = authorizedContact;
        this.willSpecialPowersForFamily = willSpecialPowersForFamily;
        this.NIP = NIP;
        this.willPIT2 = willPIT2;
        this.workplace = workplace;
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
        this.ZFSS1 = ZFSS1;
        this.ZFSS2 = ZFSS2;
        this.ZFSS3 = ZFSS3;
        this.ZFSS4 = ZFSS4;
        this.ZFSS5 = ZFSS5;
        this.ZFSS6 = ZFSS6;
        this.ZFSS7 = ZFSS7;
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
        this.email = source.email;//
        this.phoneNumber = source.phoneNumber;//
        this.fillLocation = source.fillLocation;//
        this.sex = source.sex;
        this.firstName = source.firstName;//
        this.secondName = source.secondName;
        this.surname = source.surname;//
        this.birthDate = source.birthDate;
        this.profession = source.profession;
        this.specialty = source.specialty;
        this.title = source.title;
        this.qualifications = source.qualifications;
        this.optionalData = source.optionalData;
        this.isPolishCitizen = source.isPolishCitizen;
        this.citizenship = source.citizenship;
        this.documentNumber = source.documentNumber;//
        this.documentType = source.documentType;//
        this.taxOffice = source.taxOffice;
        this.authorizedName = source.authorizedName;
        this.authorizedSurname = source.authorizedSurname;
        this.authorizedContact = source.authorizedContact;
        this.willSpecialPowersForFamily = source.willSpecialPowersForFamily;
        this.NIP = source.NIP;//
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
