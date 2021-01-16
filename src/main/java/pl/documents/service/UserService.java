package pl.documents.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.documents.config.Encryption;
import pl.documents.exception.LoginException;
import pl.documents.exception.RegisterException;
import pl.documents.model.Token;
import pl.documents.model.User;
import pl.documents.model.Worker;
import pl.documents.model.enums.TokenType;
import pl.documents.model.enums.UserType;
import pl.documents.model.projection.WorkerReadModelForEmployee;
import pl.documents.repository.TokenRepository;
import pl.documents.repository.UserRepository;
import pl.documents.repository.WorkerRepository;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UserService
{
    private final UserRepository userRepository;
    private final WorkerRepository workerRepository;
    private final TokenRepository tokenRepository;
    private final Encryption encryption;

    public UserService(final UserRepository userRepository, final WorkerRepository workerRepository, final TokenRepository tokenRepository, final Encryption encryption)
    {
        this.userRepository = userRepository;
        this.workerRepository = workerRepository;
        this.tokenRepository = tokenRepository;
        this.encryption = encryption;
    }
    public UserType findTypeById(UUID id) throws RegisterException
    {
        Token t = tokenRepository.findById(id).orElseThrow(
                ()->new RegisterException("Cannot create account!")
        );
        if(!t.getTokenType().equals(TokenType.CREATE_ACCOUNT))
        {
            throw new RegisterException("Cannot create account!");
        }
        return t.getUserType();

    }
    @Transactional
    public void createUser(User user, Worker worker, UUID id)
    {
        if(worker!=null)
        {
            userRepository.save(user);
            workerRepository.save(worker);
        }
        else
        {
            user.setWorker(null);
            userRepository.save(user);
        }
        tokenRepository.deleteById(id);
    }

    public User findByEmailAndPassword(String email, String password) throws LoginException
    {
        User user = userRepository.findByEmailAndPassword(email,password).orElseThrow(
                ()->new LoginException("Bad email or password!")
        );
        if(user.isActive()==false)
            throw new LoginException("Account is not active. Please, confirm the mail.");
        return user;
    }
    public boolean existsByEmail(String email)
    {
        if(userRepository.existsByEmail(email))
            return true;
        return false;
    }

    public void checkData(String email, String password) throws RegisterException
    {
        Pattern emailPattern = Pattern.compile("^[A-Za-z0-9+_.-]+@(.+)");
        Matcher matcherEmail = emailPattern.matcher(email);
        if(!matcherEmail.matches())
        {
             throw new RegisterException("Bad e-mail!");
        }
        if(password.length()<8)
        {
            throw new RegisterException("Too short password. It must contain at least 8 characters!");
        }

        Pattern digit = Pattern.compile("^(?=.*[0-9]).{8,}$");
        Pattern smallLetter = Pattern.compile("^(?=.*[a-z]).{8,}$");
        Pattern bigLetter = Pattern.compile("^(?=.*[A-Z]).{8,}$");
        Pattern blank = Pattern.compile("^(?=\\S+$).{8,}$");
        Pattern special = Pattern.compile("^(?=.*[~`!@#$%^&*()_+=\\-|\\\\/?:.,<>;'\"{}\\[\\]]).{8,}$");
        Matcher matcher = digit.matcher(password);
        if(!matcher.matches())
        {
            throw new RegisterException("Password doesn't contains any digit!");
        }
        matcher = smallLetter.matcher(password);
        if(!matcher.matches())
        {
            throw new RegisterException("Password doesn't contains any small letter!");
        }
        matcher = bigLetter.matcher(password);
        if(!matcher.matches())
        {
            throw new RegisterException("Password doesn't contains any big letter!");
        }
        matcher = blank.matcher(password);
        if(!matcher.matches())
        {
            throw new RegisterException("Password contains whitespace characters!");
        }
        matcher = special.matcher(password);
        if(!matcher.matches())
        {
            throw new RegisterException("Password doesn't contains any special character!");
        }
    }
    public List<WorkerReadModelForEmployee> findAll()
    {
       List<User> userList = userRepository.findAll();
       List<WorkerReadModelForEmployee> result = new ArrayList<>();
       for(User user:userList)
       {
           if(user.getUserType().equals(UserType.WORKER))
               result.add(new WorkerReadModelForEmployee(user,user.getWorker()));
       }
       return result;

    }


    public void activeUser(String email)
    {

        userRepository.findByEmail(email).
                ifPresent(user ->{
                    user.setActive(true);
                    userRepository.save(user);
                });
    }

    /*
    public boolean checkAccessToWorker(UUID id, String token)
    {
        User user;
        try
        {
            user = userRepository.findById(id)
                    .orElseThrow(
                            ()->new AccessException("No access!")
                    );
        }
        catch (AccessException e)
        {
            return false;
        }
        Jws<Claims> claimsJws;
        try
        {
            claimsJws = Jwts.parser().setSigningKey(encryption.getSequence().getBytes())
                    .parseClaimsJws(token.replace("Bearer ", ""));
        }
        catch (Exception e)
        {
            return false;
        }

        String username = claimsJws.getBody().get("name").toString();
        String role = claimsJws.getBody().get("role").toString();
        String tokenId = claimsJws.getBody().get("id").toString();
        String i ="111";

        return true;

    }

     */

    public String generateToken(User user)
    {
        LocalDateTime currentTime = LocalDateTime.now();
        String sequence = encryption.getSequence();
        String encodedString = Base64.getEncoder().encodeToString(sequence.getBytes());
        String resultToken= Jwts.builder()
                .claim("name", user.getEmail())
                .claim("role","ROLE_"+user.getUserType().toString())
                .claim("id",user.getId().toString())
                .setIssuedAt(Date.from(currentTime.atZone(ZoneId.systemDefault()).toInstant()))
                 .setExpiration(Date.from(currentTime
                         .plusDays(7)
                          .atZone(ZoneId.systemDefault()).toInstant()))
                .signWith(SignatureAlgorithm.HS256,encodedString)
                .compact();

        return resultToken;

    }
}
