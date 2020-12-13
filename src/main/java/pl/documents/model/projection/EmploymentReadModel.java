package pl.documents.model.projection;

import pl.documents.model.Employment;

import java.time.YearMonth;

public class EmploymentReadModel
{
    public YearMonth start;
    public YearMonth finish;
    public String name;
    public String workplace;

   public EmploymentReadModel(Employment source)
   {
       this.start=source.getStart();
       this.finish=source.getFinish();
       this.name=source.getName();
       this.workplace=source.getWorkplace();
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
