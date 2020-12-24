package pl.documents.model.projection;

import pl.documents.model.Employment;

import java.time.YearMonth;

public class EmploymentWriteModel
{
    public YearMonth start;
    public YearMonth finish;
    public String name;
    public String workplace;

    public Employment toEmployment()
    {
        Employment employment = new Employment(start,finish,name,workplace);
        return employment;
    }

    public void setStart(YearMonth start)
    {
        this.start = start;
    }

    public void setFinish(YearMonth finish)
    {
        this.finish = finish;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void setWorkplace(String workplace)
    {
        this.workplace = workplace;
    }
}
