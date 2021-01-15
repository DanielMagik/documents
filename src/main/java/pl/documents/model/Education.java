package pl.documents.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

/**
 * Przebieg edukacji kandydata
 */
@Entity
@Table(name = "EDUCATION")
public class Education
{
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private UUID id;
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
    private String profession;
    private String speciality;
    private String title;
    @ManyToOne
    @JoinColumn(name = "ID_WORKER",referencedColumnName = "id")
    private Worker worker;

    public Education()
    {
    }

    public Education(String schoolName, String graduationYear, String profession, String speciality, String title)
    {
        this.schoolName = schoolName;
        this.graduationYear = graduationYear;
        this.profession=profession;
        this.speciality=speciality;
        this.title=title;
    }
    /**
     * Update podstawowych danych o edukacji
     * @param source źródło danych, z którego pobrane zostaną nowe wartości
     */
    public void updateForm(final Education source)
    {
        this.schoolName = source.schoolName;
        this.graduationYear = source.graduationYear;
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

    public void setWorker(Worker worker)
    {
        this.worker = worker;
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

    //TYLKO DO TESTÓW
    public void setId(UUID id)
    {
        this.id = id;
    }
    public Worker getWorker()
    {
        return worker;
    }
}
