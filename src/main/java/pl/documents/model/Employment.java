package pl.documents.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.YearMonth;
import java.util.UUID;

/**
 * Przebieg dotychczasowego zatrudnienia kandydata
 */
@Entity
@Table(name = "EMPLOYMENTS")
public class Employment
{
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private UUID id;
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
    public void updateForm(final Employment source)
    {
        this.name = source.name;
        this.workplace=source.workplace;
        this.start = source.start;
        this.finish = source.finish;
    }

    public UUID getId()
    {
        return id;
    }

    public void setId(UUID id)
    {
        this.id = id;
    }

    public void setWorker(Worker worker)
    {
        this.worker = worker;
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
