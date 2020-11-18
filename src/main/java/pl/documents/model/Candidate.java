package pl.documents.model;


import pl.documents.model.enums.Sex;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table
public class Candidate
{
    @Id
    private int id;
    private String email;
    private String password;
    private String phoneNumber;
    private String fillLocation;
    private LocalDateTime fillDate;
    private Sex sex;
    private String firstName;
    private String secondName;
    private String surname;
    private LocalDateTime birthDate;
    private String schoolName;
    private String graduationYear;
    private String profession;
    private String specialty;
    private String title;

    int getId()
    {
        return id;
    }

    void setId(int id)
    {
        this.id = id;
    }

    String getEmail()
    {
        return email;
    }

    void setEmail(String email)
    {
        this.email = email;
    }

    String getPassword()
    {
        return password;
    }

    void setPassword(String password)
    {
        this.password = password;
    }

    String getPhoneNumber()
    {
        return phoneNumber;
    }

    void setPhoneNumber(String phoneNumber)
    {
        this.phoneNumber = phoneNumber;
    }

    String getFillLocation()
    {
        return fillLocation;
    }

    void setFillLocation(String fillLocation)
    {
        this.fillLocation = fillLocation;
    }

    LocalDateTime getFillDate()
    {
        return fillDate;
    }

    void setFillDate(LocalDateTime fillDate)
    {
        this.fillDate = fillDate;
    }

    Sex getSex()
    {
        return sex;
    }

    void setSex(Sex sex)
    {
        this.sex = sex;
    }

    String getFirstName()
    {
        return firstName;
    }

    void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    String getSecondName()
    {
        return secondName;
    }

    void setSecondName(String secondName)
    {
        this.secondName = secondName;
    }

    String getSurname()
    {
        return surname;
    }

    void setSurname(String surname)
    {
        this.surname = surname;
    }

    LocalDateTime getBirthDate()
    {
        return birthDate;
    }

    void setBirthDate(LocalDateTime birthDate)
    {
        this.birthDate = birthDate;
    }

    String getSchoolName()
    {
        return schoolName;
    }

    void setSchoolName(String schoolName)
    {
        this.schoolName = schoolName;
    }

    String getGraduationYear()
    {
        return graduationYear;
    }

    void setGraduationYear(String graduationYear)
    {
        this.graduationYear = graduationYear;
    }

    String getProfession()
    {
        return profession;
    }

    void setProfession(String profession)
    {
        this.profession = profession;
    }

    String getSpecialty()
    {
        return specialty;
    }

    void setSpecialty(String specialty)
    {
        this.specialty = specialty;
    }

    String getTitle()
    {
        return title;
    }

    void setTitle(String title)
    {
        this.title = title;
    }
}
