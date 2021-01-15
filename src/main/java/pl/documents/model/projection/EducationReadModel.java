package pl.documents.model.projection;

import pl.documents.model.Education;

import java.util.UUID;

public class EducationReadModel
{
    private String schoolName;
    private String graduationYear;
    private String profession;
    private String speciality;
    private String title;


    public EducationReadModel(Education source)
    {
        this.schoolName = source.getSchoolName();
        this.graduationYear=source.getGraduationYear();
        this.profession=source.getProfession();
        this.speciality=source.getSpeciality();
        this.title=source.getTitle();

    }

    public String getProfession()
    {
        return profession;
    }

    public String getSpeciality()
    {
        return speciality;
    }

    public String getTitle()
    {
        return title;
    }

    public String getSchoolName()
    {
        return schoolName;
    }

    public String getGraduationYear()
    {
        return graduationYear;
    }
}
