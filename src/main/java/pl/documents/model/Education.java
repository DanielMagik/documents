package pl.documents.model;

import javax.persistence.*;

/**
 * Przebieg edukacji kandydata
 */
@Entity
@Table(name = "EDUCATION")
public class Education
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    /**
     * Nazwa szkoły
     */
    private String schoolName;
    /**
     * Rok jej ukończenia
     */
    private String graduationYear;
    /**
     * Kandydat powiązany z danym wykształceniem
     */
    @ManyToOne
    @JoinColumn(name = "ID_CANDIDATE",referencedColumnName = "id")
    private Candidate candidate;

    public Education()
    {
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
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
