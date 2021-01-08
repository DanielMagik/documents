package pl.documents.model.projection;

import pl.documents.model.Worker;

public class WorkerWriteModelRegister
{
    private String email;
    private String password;

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











}
