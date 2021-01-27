package pl.documents.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.documents.exception.TokenException;
import pl.documents.model.Token;
import pl.documents.model.User;
import pl.documents.model.enums.TokenType;
import pl.documents.model.enums.UserType;
import pl.documents.repository.TokenRepository;

import java.util.List;
import java.util.UUID;

@Service
public class TokenService
{
    private final TokenRepository tokenRepository;
    @Value("${local.url}")
    private String linkBeginning;

    public TokenService(final TokenRepository tokenRepository)
    {
        this.tokenRepository = tokenRepository;
    }

    public String generateToken(UserType userType) throws TokenException
    {

            Token token = new Token(userType, TokenType.CREATE_ACCOUNT);
            if(token.getUserType().equals(UserType.ADMIN))
                throw new TokenException("Bad request!");
            tokenRepository.save(token);

            Token result = tokenRepository.findById(token.getId()).orElseThrow(
                    ()-> new TokenException("Bad request!")
            );
            String link = linkBeginning + "/register/" + result.getId();
            return link;
    }
    public List<Token> readAll()
    {
        return tokenRepository.findAll();
    }

    public String generateTokenToAdmin() throws TokenException
    {
        Token token = new Token(UserType.ADMIN, TokenType.CREATE_ACCOUNT);
        tokenRepository.save(token);

        Token result = tokenRepository.findById(token.getId()).orElseThrow(
                ()-> new TokenException("Bad request!")
        );
        String link = linkBeginning +  "/register/" + result.getId();
        return link;
    }

    public String generateLinkAndTokenAccountConfirm(User user) throws TokenException
    {
        Token token = new Token(user.getUserType(), TokenType.CONFIRM_EMAIL, user.getEmail());
        tokenRepository.save(token);
        Token result = tokenRepository.findById(token.getId()).orElseThrow(
                ()-> new TokenException("Bad request!")
        );
        String link = linkBeginning+ "/confirm/" + result.getId();
        return link;
    }
    @Transactional
    public Token findTokenActiveById(UUID id) throws TokenException
    {
        if(!tokenRepository.existsByIdAndTokenTypeIs(id,TokenType.CONFIRM_EMAIL))
            throw new TokenException("Bad request!");
        Token token = tokenRepository.findById(id).orElseThrow(
                ()->new TokenException("Bad request!")
        );
        tokenRepository.deleteById(id);
        return token;
    }
}
