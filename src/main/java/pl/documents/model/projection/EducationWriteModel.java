package pl.documents.model.projection;

import pl.documents.model.Education;

public class EducationWriteModel
{
    private String schoolName;
    private String graduationYear;

    public Education toEducation()
    {
        Education education = new Education();
        education.setSchoolName(schoolName);
        education.setGraduationYear(graduationYear);
        return education;
    }
    public String getSchoolName()
    {
        return schoolName;
    }

    public void setSchoolName(String schoolName)
    {
        this.schoolName = schoolName;
    }

    public String getGraduationYear()
    {
        return graduationYear;
    }

    public void setGraduationYear(String graduationYear)
    {
        this.graduationYear = graduationYear;
    }
}
