package pl.documents.model;

import pl.documents.model.enums.Sex;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Entity
@Table
public class Candidate
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Email(message = "Bad e-mail!")
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
    private boolean completed;

    public Candidate()
    {

    }

    public LocalDateTime getCreateDate()
    {
        return createDate;
    }

    void setCreateDate(LocalDateTime createDate)
    {
        this.createDate = createDate;
    }

    public LocalDateTime getUpdateDate()
    {
        return updateDate;
    }

    void setUpdateDate(LocalDateTime updateDate)
    {
        this.updateDate = updateDate;
    }

    public int getId()
    {
        return id;
    }

    void setId(int id)
    {
        this.id = id;
    }

    public String getEmail()
    {
        return email;
    }

    void setEmail(String email)
    {
        this.email = email;
    }

    public String getPassword()
    {
        return password;
    }

    void setPassword(String password)
    {
        this.password = password;
    }

    public String getPhoneNumber()
    {
        return phoneNumber;
    }

    void setPhoneNumber(String phoneNumber)
    {
        this.phoneNumber = phoneNumber;
    }

    public String getFillLocation()
    {
        return fillLocation;
    }

    void setFillLocation(String fillLocation)
    {
        this.fillLocation = fillLocation;
    }

    public Sex getSex()
    {
        return sex;
    }

    void setSex(Sex sex)
    {
        this.sex = sex;
    }

    public String getFirstName()
    {
        return firstName;
    }

    void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public String getSecondName()
    {
        return secondName;
    }

    void setSecondName(String secondName)
    {
        this.secondName = secondName;
    }

    public String getSurname()
    {
        return surname;
    }

    void setSurname(String surname)
    {
        this.surname = surname;
    }

    public LocalDateTime getBirthDate()
    {
        return birthDate;
    }

    void setBirthDate(LocalDateTime birthDate)
    {
        this.birthDate = birthDate;
    }

    public String getSchoolName()
    {
        return schoolName;
    }

    void setSchoolName(String schoolName)
    {
        this.schoolName = schoolName;
    }

    public String getGraduationYear()
    {
        return graduationYear;
    }

    void setGraduationYear(String graduationYear)
    {
        this.graduationYear = graduationYear;
    }

    public String getProfession()
    {
        return profession;
    }

    void setProfession(String profession)
    {
        this.profession = profession;
    }

    public String getSpecialty()
    {
        return specialty;
    }

    void setSpecialty(String specialty)
    {
        this.specialty = specialty;
    }

    public String getTitle()
    {
        return title;
    }

    void setTitle(String title)
    {
        this.title = title;
    }

    public boolean isCompleted()
    {
        return completed;
    }

    void setCompleted(boolean completed)
    {
        this.completed = completed;
    }
}
