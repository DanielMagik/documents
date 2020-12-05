package pl.documents.model;

import pl.documents.model.enums.Sex;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "CANDIDATES")
public class Candidate
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;//
    @Email(message = "Bad e-mail!")
    private String email;//
    private String password;//
    @Pattern(regexp = "\\d{9,11}",message = "Bad phone number")
    private String phoneNumber;//
    @Pattern(regexp = "[A-Z][a-z ]+", message = "Bad fill location")
    private String fillLocation;//
    private Sex sex;//
    @Pattern(regexp = "[A-Z][a-z ]+", message = "Bad first name")
    private String firstName;//
    @Pattern(regexp = "[A-Z][a-z ]+", message = "Bad second name")
    private String secondName;//
    @Pattern(regexp = "[A-Z][a-z ]+", message = "Bad surname name")
    private String surname;//
    private LocalDate birthDate;//
    private String schoolName;//
    @Pattern(regexp = "\\d{4}",message = "Bad graduation year")
    private String graduationYear;//
    private String profession;//
    private String specialty;//
    private String title;//
    private String qualifications;//
    private String experience;//
    private String optionalData;//
    private LocalDateTime createDate;//
    private LocalDateTime updateDate;//

    public int getId()
    {
        return id;
    }

    public void setId(int id)
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

    String getPassword()
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

    public LocalDate getBirthDate()
    {
        return birthDate;
    }

    void setBirthDate(LocalDate birthDate)
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

    public String getQualifications()
    {
        return qualifications;
    }

    void setQualifications(String qualifications)
    {
        this.qualifications = qualifications;
    }

    public String getExperience()
    {
        return experience;
    }

    void setExperience(String experience)
    {
        this.experience = experience;
    }

    public String getOptionalData()
    {
        return optionalData;
    }

    void setOptionalData(String optionalData)
    {
        this.optionalData = optionalData;
    }

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
}
