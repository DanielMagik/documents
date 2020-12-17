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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    @JoinColumn(name = "ID_WORKER",referencedColumnName = "id")
    private Worker worker;

    public Education()
    {
    }
    public void updateForm(final Education source)
    {
        this.schoolName = source.schoolName;
        this.graduationYear = source.graduationYear;
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


    public void setWorker(Worker worker)
    {
        this.worker = worker;
    }
}
