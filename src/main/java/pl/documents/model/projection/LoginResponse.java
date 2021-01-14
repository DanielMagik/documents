package pl.documents.model.projection;

public class LoginResponse
{
    private String href;
    private String token;

    public LoginResponse(String href, String token)
    {
        this.href = href;
        this.token = token;
    }

    public String getHref()
    {
        return href;
    }

    public String getToken()
    {
        return token;
    }
}
