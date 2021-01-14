package pl.documents.model.projection;

import pl.documents.model.User;
import pl.documents.model.Worker;
import pl.documents.model.enums.UserType;

public class WriteModelRegister
{
    private String email;
    private String password;


    public User toUser()
    {
        User user = new User(email,password);
        return user;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }











}
