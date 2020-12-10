package pl.documents.model;

import javax.persistence.*;
import java.time.YearMonth;

/**
 * Przebieg dotychczasowego zatrudnienia kandydata
 */
@Entity
@Table(name = "EMPLOYMENTS")
public class Employments
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    /**
     * Początek zatrudnienia(rok i miesiąc)
     */
    public YearMonth start;
    /**
     * Koniec zatrudnienia(rok i miesiąc)
     */
    public YearMonth finish;
    /**
     * Nazwa firmy
     */
    public String name;
    /**
     * Zajmowane stanowisko
     */
    public String workplace;
    /**
     * Kandydat przypisany do danego zatrudnienia
     */
    @ManyToOne
    @JoinColumn(name = "ID_CANDIDATE",referencedColumnName = "id")
    private Candidate candidate;

    public Employments()
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

    public YearMonth getStart()
    {
        return start;
    }

    public void setStart(YearMonth start)
    {
        this.start = start;
    }

    public YearMonth getFinish()
    {
        return finish;
    }

    public void setFinish(YearMonth finish)
    {
        this.finish = finish;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getWorkplace()
    {
        return workplace;
    }

    public void setWorkplace(String workplace)
    {
        this.workplace = workplace;
    }
}
