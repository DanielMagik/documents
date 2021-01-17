package pl.documents.model;

import org.hibernate.annotations.GenericGenerator;
import pl.documents.model.enums.TokenType;
import pl.documents.model.enums.UserType;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "TOKENS")
public class Token
{
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private UUID id;
    private UserType userType;
    private TokenType tokenType;
    private String email;
    private LocalDateTime createDate;

    public Token()
    {
    }


    public Token(UserType userType, TokenType tokenType)
    {
        this.userType = userType;
        this.tokenType = tokenType;
    }
    public Token(UserType userType, TokenType tokenType, String email)
    {
        this.userType = userType;
        this.tokenType = tokenType;
        this.email = email;
    }

    public UUID getId()
    {
        return id;
    }

    public UserType getUserType()
    {
        return userType;
    }

    public TokenType getTokenType()
    {
        return tokenType;
    }

    public String getEmail()
    {
        return email;
    }

    public LocalDateTime getCreateDate()
    {
        return createDate;
    }

    @PrePersist
    private void prePersist()
    {
        this.createDate=LocalDateTime.now();
    }

}
