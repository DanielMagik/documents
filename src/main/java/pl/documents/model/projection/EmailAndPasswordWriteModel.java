package pl.documents.model.projection;

import pl.documents.model.User;

public class EmailAndPasswordWriteModel
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

    public String getPassword()
    {
        return password;
    }

    public String getEmail()
    {
        return email;
    }
}
