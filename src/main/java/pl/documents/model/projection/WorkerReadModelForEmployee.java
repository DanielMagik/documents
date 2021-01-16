package pl.documents.model.projection;

import pl.documents.model.User;
import pl.documents.model.Worker;
import pl.documents.model.enums.*;

import java.time.LocalDate;
import java.util.UUID;

public class WorkerReadModelForEmployee
{
    private String email;
    private String phoneNumber;
    private String fillLocation;
    private Sex sex;
    private String firstName;
    private String secondName;
    private String surname;
    private String qualifications;
    private String citizenship;

    public WorkerReadModelForEmployee(User user, Worker source)
    {
        this.email = user.getEmail();
        this.phoneNumber = source.getPhoneNumber();
        this.fillLocation = source.getFillLocation();
        this.sex = source.getSex();
        this.firstName = source.getFirstName();
        this.secondName = source.getSecondName();
        this.surname = source.getSurname();
        this.qualifications = source.getQualifications();
        this.citizenship = source.getCitizenship();
    }

    public String getEmail()
    {
        return email;
    }

    public String getPhoneNumber()
    {
        return phoneNumber;
    }

    public String getFillLocation()
    {
        return fillLocation;
    }

    public Sex getSex()
    {
        return sex;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public String getSecondName()
    {
        return secondName;
    }

    public String getSurname()
    {
        return surname;
    }

    public String getQualifications()
    {
        return qualifications;
    }

    public String getCitizenship()
    {
        return citizenship;
    }
}
