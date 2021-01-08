package pl.documents.model.projection;

import pl.documents.model.Worker;

public class WorkerWriteModelChangePassword
{
    private String email;
    private String password;
    private String newPassword;
    private boolean willChangeEmail;
    private boolean willChangePassword;
    public Worker toWorker()
    {
        return new Worker(email,password);
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public boolean isWillChangeEmail()
    {
        return willChangeEmail;
    }

    public void setWillChangeEmail(boolean willChangeEmail)
    {
        this.willChangeEmail = willChangeEmail;
    }

    public boolean isWillChangePassword()
    {
        return willChangePassword;
    }

    public void setWillChangePassword(boolean willChangePassword)
    {
        this.willChangePassword = willChangePassword;
    }

    public void setNewPassword(String newPassword)
    {
        this.newPassword = newPassword;
    }

    public String getNewPassword()
    {
        return newPassword;
    }
}
