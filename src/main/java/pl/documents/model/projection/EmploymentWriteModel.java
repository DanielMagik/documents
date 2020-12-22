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

}
