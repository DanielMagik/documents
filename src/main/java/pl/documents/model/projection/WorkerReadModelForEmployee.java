package pl.documents.model.projection;

import pl.documents.model.Worker;
import pl.documents.model.enums.*;

import java.time.LocalDate;
import java.time.Month;
import java.util.Set;
import java.util.UUID;

public class WorkerReadModelForEmployee
{
    private UUID id;
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
    private boolean isPolishCitizen;
    private String citizenship;

    public WorkerReadModelForEmployee(Worker source)
    {
        this.id=source.getId();
       // this.email = source.getEmail();
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
        this.isPolishCitizen = source.isPolishCitizen();
        this.citizenship = source.getCitizenship();
    }

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

    public boolean isPolishCitizen()
    {
        return isPolishCitizen;
    }

    public String getCitizenship()
    {
        return citizenship;
    }
}
