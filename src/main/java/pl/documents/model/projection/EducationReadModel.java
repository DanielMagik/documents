package pl.documents.model.projection;

import pl.documents.model.Education;

public class EducationReadModel
{
    private String schoolName;
    private String graduationYear;

    public EducationReadModel(Education source)
    {
        this.schoolName = source.getSchoolName();
        this.graduationYear=source.getGraduationYear();
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
