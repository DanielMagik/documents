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
    private LocalDateTime createDate;
    private LocalDateTime updateDate;
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

    LocalDateTime getCreateDate()
    {
        return createDate;
    }

    void setCreateDate(LocalDateTime createDate)
    {
        this.createDate = createDate;
    }

    LocalDateTime getUpdateDate()
    {
        return updateDate;
    }

    void setUpdateDate(LocalDateTime updateDate)
    {
        this.updateDate = updateDate;
    }

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
