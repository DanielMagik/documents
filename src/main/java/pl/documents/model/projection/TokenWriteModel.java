package pl.documents.model.projection;

import pl.documents.model.enums.TokenType;
import pl.documents.model.enums.UserType;

public class TokenWriteModel
{
    private UserType userType;
    private TokenType tokenType;

    public void setUserType(UserType userType)
    {
        this.userType = userType;
    }

    public UserType getUserType()
    {
        return userType;
    }
}
