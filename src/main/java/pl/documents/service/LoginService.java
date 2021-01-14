package pl.documents.service;

import org.springframework.stereotype.Service;
import pl.documents.exception.RegisterException;
import pl.documents.repository.TokenRepository;
import pl.documents.repository.UserRepository;

import java.util.UUID;

@Service
public class LoginService
{
    private TokenRepository tokenRepository;
    private UserRepository userRepository;

    public String createAccount(UUID uuid) throws RegisterException
    {
        if(!tokenRepository.existsById(uuid))
        {
            throw new RegisterException("Cannot create account!");
        }
        else
        {

        }
        return null;
    }
}
