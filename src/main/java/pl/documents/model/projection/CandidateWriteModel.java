package pl.documents.model.projection;

import pl.documents.model.enums.Sex;

import java.time.LocalDate;
import java.util.Date;
import java.util.Set;

public class CandidateWriteModel
{
    private String firstName;
    private String secondName;
    private String surname;
    private LocalDate dateOfBirth;
    private Sex sex;
    private String phoneNumber;
    private String fillLocation;
    private String qualifications;
    private String prevEmployment;
    private String additionalPersonalData;
    private Set<EducationWriteModel> education = null;

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

    public LocalDate getDateOfBirth()
    {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth)
    {
        this.dateOfBirth = dateOfBirth;
    }

    public Sex getSex()
    {
        return sex;
    }

    public void setSex(Sex sex)
    {
        this.sex = sex;
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

    public String getQualifications()
    {
        return qualifications;
    }

    public void setQualifications(String qualifications)
    {
        this.qualifications = qualifications;
    }

    public String getPrevEmployment()
    {
        return prevEmployment;
    }

    public void setPrevEmployment(String prevEmployment)
    {
        this.prevEmployment = prevEmployment;
    }

    public String getAdditionalPersonalData()
    {
        return additionalPersonalData;
    }

    public void setAdditionalPersonalData(String additionalPersonalData)
    {
        this.additionalPersonalData = additionalPersonalData;
    }

    public Set<EducationWriteModel> getEducation()
    {
        return education;
    }

    public void setEducation(Set<EducationWriteModel> education)
    {
        this.education = education;
    }


}
