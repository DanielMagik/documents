package pl.documents.model;

import javax.persistence.*;
import java.time.YearMonth;

/**
 * Przebieg dotychczasowego zatrudnienia kandydata
 */
@Entity
@Table(name = "EMPLOYMENTS")
public class Employment
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    /**
     * Początek zatrudnienia(rok i miesiąc)
     */
    private YearMonth start;
    /**
     * Koniec zatrudnienia(rok i miesiąc)
     */
    private YearMonth finish;
    /**
     * Nazwa firmy
     */
    private String name;
    /**
     * Zajmowane stanowisko
     */
    private String workplace;
    /**
     * Kandydat przypisany do danego zatrudnienia
     */
    @ManyToOne
    @JoinColumn(name = "ID_WORKER",referencedColumnName = "id")
    private Worker worker;

    public Employment()
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
