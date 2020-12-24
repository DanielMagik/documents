package pl.documents.model.projection;

import pl.documents.model.Education;

public class EducationWriteModel
{
    private String schoolName;
    private String graduationYear;

    public Education toEducation()
    {
        Education education = new Education(schoolName,graduationYear);
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
}
