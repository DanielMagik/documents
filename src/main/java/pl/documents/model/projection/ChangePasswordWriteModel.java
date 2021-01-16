package pl.documents.model.projection;

public class ChangePasswordWriteModel
{
    private String password;
    private String newPassword;

    public void setPassword(String password)
    {
        this.password = password;
    }

    public void setNewPassword(String newPassword)
    {
        this.newPassword = newPassword;
    }

    public String getPassword()
    {
        return password;
    }

    public String getNewPassword()
    {
        return newPassword;
    }
}
