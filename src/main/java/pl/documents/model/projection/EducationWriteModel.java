package pl.documents.model.projection;

import pl.documents.model.Education;

public class EducationWriteModel
{
    private String schoolName;
    private String graduationYear;
    private String profession;
    private String speciality;
    private String title;

    public Education toEducation()
    {
        Education education = new Education(schoolName,graduationYear, profession, speciality, title);
        return education;
    }

    public void setSchoolName(String schoolName)
    {
        this.schoolName = schoolName;
    }

    public void setGraduationYear(String graduationYear)
    {
        this.graduationYear = graduationYear;
    }

    public void setProfession(String profession)
    {
        this.profession = profession;
    }

    public void setSpeciality(String speciality)
    {
        this.speciality = speciality;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }
}
