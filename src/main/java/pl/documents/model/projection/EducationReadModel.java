package pl.documents.model.projection;

import pl.documents.model.Education;

import java.util.UUID;

public class EducationReadModel
{
    private UUID id;
    private String schoolName;
    private String graduationYear;

    public EducationReadModel(Education source)
    {
        this.id=source.getId();
        this.schoolName = source.getSchoolName();
        this.graduationYear=source.getGraduationYear();
    }

    public UUID getId()
    {
        return id;
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
