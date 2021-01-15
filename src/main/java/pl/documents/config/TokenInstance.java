package pl.documents.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;

import java.rmi.AccessException;

public class TokenInstance
{
    private String role;
    private String id;
    private String token;
    private String key;
    public TokenInstance(String token, String key)
    {
        this.token=token;
        this.key = key;
    }
    public void readToken() throws AccessException
    {
        Jws<Claims> claimsJws;
        try
        {
            token = token.substring(7);
            //token.replace("Bearer ", "");
            claimsJws = Jwts.parser().setSigningKey(this.key.getBytes())
                    .parseClaimsJws(token);
        }
        catch (Exception e)
        {
            throw new AccessException("No access!");
        }

        this.role = claimsJws.getBody().get("role").toString();
        this.id = claimsJws.getBody().get("id").toString();
    }

    public String getRole()
    {
        return role;
    }

    public String getId()
    {
        return id;
    }
}
