package pl.documents.model.projection;

import pl.documents.model.User;
import pl.documents.model.Worker;
import pl.documents.model.enums.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public class WorkerReadModelForEmployee
{
    private UserType userType;
    private String email;
    private boolean active;
    private LocalDateTime createDate;

    public WorkerReadModelForEmployee(User user)
    {
        this.userType=user.getUserType();
        this.email = user.getEmail();
        this.active = user.isActive();
        this.createDate = user.getCreateDate();

    }

    public String getEmail()
    {
        return email;
    }

    public UserType getUserType()
    {
        return userType;
    }

    public boolean isActive()
    {
        return active;
    }

    public LocalDateTime getCreateDate()
    {
        return createDate;
    }
}
