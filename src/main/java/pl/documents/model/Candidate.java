package pl.documents.model;

import pl.documents.model.enums.Sex;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

/**
 * Dane niezbędne do wypełnienia dokumentów dla kandydata
 */
@Entity
@Table(name = "CANDIDATES")
public class Candidate
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    /**
     * Dane kontaktowe (e-mail)
     */
    private String email;
    /**
     * Dane kontaktowe (numer telefonu)
     */
    private String phoneNumber;
    /**
     * Hasło do logowania się
     */
    private String password;
    /**
     * Miejsce wypełnienia dokumentów dla kandydata
     */
    private String fillLocation;
    /**
     * Płeć
     */
    private Sex sex;
    /**
     * Pierwsze imię
     */
    private String firstName;
    /**
     * Drugie imię
     */
    private String secondName;
    /**
     * Nazwisko
     */
    private String surname;
    /**
     * Data urodzenia(YYYY-MM-DD)
     */
    private LocalDate birthDate;
    /**
     *  Zawód
     */
    private String profession;
    /**
     * Specjalność
     */
    private String specialty;
    /**
     * Tytuł zawodowy/naukowy
     */
    private String title;
    /**
     * Kwalifikacje zawodowe
     */
    private String qualifications;
    /**
     * Dodatkowe dane osobowe
     */
    private String optionalData;
    /**
     * Data i czas utworzenia encji
     */
    private LocalDateTime createDate;
    /**
     * Data i czas ostatniej aktualizacji encji
     */
    private LocalDateTime updateDate;
    /**
     * Dane niezbędne do wypełnienia dokumentów dla pracownika
     */
    @OneToOne(mappedBy = "candidate")
    private Worker worker;
    /**
     * Nazwy szkół i lata ich ukończenia
     */
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "candidate")
    private Set<Education> education;
    /**
     * Przebieg dotychczasowego zatrudnienia
     */
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "candidate")
    private Set<Employments> employments;

    public Candidate()
    {
    }

    public int getId()
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

    public LocalDateTime getCreateDate()
    {
        return createDate;
    }

    public LocalDateTime getUpdateDate()
    {
        return updateDate;
    }

    public Worker getWorker()
    {
        return worker;
    }

    public Set<Education> getEducation()
    {
        return education;
    }

    public Set<Employments> getEmployments()
    {
        return employments;
    }

    /**
     * Update podstawowych danych pracownika
     * @param source źródło danych, z którego pobrane zostaną nowe wartości
     */
    public void updateFrom(final Candidate source)
    {
        this.email=source.email;
        this.phoneNumber=source.phoneNumber;
        this.fillLocation=source.fillLocation;
        this.sex=source.sex;
        this.firstName=source.firstName;
        this.secondName=source.secondName;
        this.surname= source.surname;
        this.birthDate=source.birthDate;
        this.profession=source.profession;
        this.specialty=source.specialty;
        this.title=source.title;
        this.qualifications=source.qualifications;
        this.optionalData=source.optionalData;

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
