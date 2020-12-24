package pl.documents.model.projection;

import pl.documents.model.Employment;

import java.time.YearMonth;
import java.util.UUID;

public class EmploymentReadModel
{
    private UUID id;
    public YearMonth start;
    public YearMonth finish;
    public String name;
    public String workplace;

   public EmploymentReadModel(Employment source)
   {
       this.id=source.getId();
       this.start=source.getStart();
       this.finish=source.getFinish();
       this.name=source.getName();
       this.workplace=source.getWorkplace();
   }

    public UUID getId()
    {
        return id;
    }

    public YearMonth getStart()
    {
        return start;
    }

    public YearMonth getFinish()
    {
        return finish;
    }

    public String getName()
    {
        return name;
    }

    public String getWorkplace()
    {
        return workplace;
    }
}
