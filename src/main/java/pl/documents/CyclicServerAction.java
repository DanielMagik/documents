package pl.documents;


import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pl.documents.model.Token;
import pl.documents.model.User;
import pl.documents.model.enums.TokenType;
import pl.documents.model.enums.UserType;
import pl.documents.repository.TokenRepository;
import pl.documents.repository.UserRepository;

import java.time.LocalDateTime;

@Component
public class CyclicServerAction
{
    private final TokenRepository tokenRepository;
    private final UserRepository userRepository;
    private int userDays = 7;
    private int linkHours=48;
    private int noActiveUserHours=24;
    private final int repeatSeconds = 900000;

    public CyclicServerAction(final TokenRepository tokenRepository, final UserRepository userRepository)
    {
        this.tokenRepository = tokenRepository;
        this.userRepository = userRepository;
    }
    @Scheduled(fixedRate = repeatSeconds)//co 15 minut
    @Transactional
    void deleteAllWorkersOlderThan()
    {
        LocalDateTime cutOffDate = LocalDateTime.now().minusDays(userDays);
        LocalDateTime cutOffDateNoActiveUser = LocalDateTime.now().minusHours(noActiveUserHours);
        for(User u : userRepository.findAll())
        {
            if(u.getUserType().equals(UserType.WORKER) && u.isActive())
            {
                if(u.getCreateDate().isBefore(cutOffDate))
                {
                    userRepository.deleteById(u.getId());
                }
            }
            else if(!u.isActive())
            {
                if(u.getCreateDate().isBefore(cutOffDateNoActiveUser))
                {
                    userRepository.deleteById(u.getId());
                }
            }
        }
    }
    @Scheduled(fixedRate = repeatSeconds)//co 15 minut
    @Transactional
    void deleteAllTokensOlderThan()
    {
        LocalDateTime cutOffDate = LocalDateTime.now().minusHours(linkHours);
        LocalDateTime cutOffDateNoActiveUser = LocalDateTime.now().minusHours(noActiveUserHours);
        for(Token t : tokenRepository.findAll())
        {
            if(t.getTokenType().equals(TokenType.CREATE_ACCOUNT))
            {
                if(t.getCreateDate().isBefore(cutOffDate))
                {
                    tokenRepository.deleteById(t.getId());
                }
            }
            else if(t.getCreateDate().isBefore(cutOffDateNoActiveUser))
            {
                tokenRepository.deleteById(t.getId());
            }
        }
    }
}
