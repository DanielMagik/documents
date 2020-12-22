package pl.documents.exception;

public class BasicException extends Throwable
{
    private String errorMessage;

    public String getErrorMessage()
    {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage)
    {
        this.errorMessage = errorMessage;
    }
}
