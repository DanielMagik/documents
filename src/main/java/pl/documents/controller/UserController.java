package pl.documents.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.documents.config.Encryption;
import pl.documents.exception.LoginException;
import pl.documents.exception.RegisterException;
import pl.documents.exception.TokenException;
import pl.documents.model.Token;
import pl.documents.model.User;
import pl.documents.model.Worker;
import pl.documents.model.enums.UserType;
import pl.documents.model.projection.LoginResponse;
import pl.documents.model.projection.WriteModelRegister;
import pl.documents.service.MailService;
import pl.documents.service.TokenService;
import pl.documents.service.UserService;

import java.util.List;
import java.util.UUID;

@RestController
public class UserController
{
    private final UserService userService;
    private final MailService mailService;
    private final TokenService tokenService;

    public UserController(final UserService userService, final MailService mailService, final TokenService tokenService)
    {
        this.userService = userService;
        this.mailService = mailService;
        this.tokenService = tokenService;
    }


    @PostMapping("/test1")
    public String test1() {
        return "test1";
    }

    @GetMapping("/test2")
    public String test2() {
        return "test2";
    }

    @GetMapping("/test3")
    public String test3() {
        return "test3";
    }


    @GetMapping("/login")
    public ResponseEntity<?> login(@RequestBody WriteModelRegister writeModelRegister)
    {
        User user = writeModelRegister.toUser();
        User result = null;
        try
        {
            result = userService.findByEmailAndPassword(user.getEmail(),user.getPassword());
        }
        catch (LoginException e)
        {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getErrorMessage());
        }

        String href = "http://localhost:8080/";
        switch(result.getUserType())
        {
            case WORKER:
                href=href+"worker";
                break;
            case HR_EMPLOYEE:
                href=href+"hr_employee";
                break;
            case ADMIN:
                href=href+"admin";
                break;
        }
        String token = userService.generateToken(result);
        LoginResponse loginResponse = new LoginResponse(href,token);
        return ResponseEntity.ok(loginResponse);

    }

    @PostMapping("/register/{id}")
    ResponseEntity<?> registerWorker(@RequestBody WriteModelRegister writeModelRegister, @PathVariable UUID id)
    {
        UserType userType = null;
        try
        {
            userType = userService.findTypeById(id);
        }
        catch (RegisterException e)
        {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getErrorMessage());
        }


        User user = writeModelRegister.toUser();
        if(userService.existsByEmail(user.getEmail()))
        {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Already exists user with that email!");
        }
        try
        {
            userService.checkData(user.getEmail(),writeModelRegister.getPassword());
        }
        catch (RegisterException e)
        {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getErrorMessage());
        }

        user.setUserType(userType);
        Worker worker = null;
        if(user.getUserType().equals(UserType.WORKER))
        {
            worker = new Worker();
            worker.setId(user.getId());
            user.setWorker(worker);
        }
        userService.createUser(user,worker,id);
        String link;
        try
        {
            link = tokenService.generateLinkAndTokenAccountConfirm(user);
            String message = "To confirm your registration, please click the link below.\n"+
                    link;

            Thread thread = new Thread(() -> {
                mailService.sendSimpleMessage(user.getEmail(),"Confirm your registration", message);
            });
            thread.start();
        }
        catch (TokenException e)
        {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getErrorMessage());
        }

        return ResponseEntity.ok(userType.toString()+" account was created! Active it by confirming the email!");
    }

    @GetMapping("/confirm/{id}")
    ResponseEntity<?> registerWorker(@PathVariable UUID id)
    {
        Token token;
        try
        {
            token = tokenService.findTokenActiveById(id);
        }
        catch (TokenException e)
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getErrorMessage());
        }

        userService.activeUser(token.getEmail());
        return ResponseEntity.ok("Account was activated!");

    }
    @GetMapping("/all")
    ResponseEntity<List<User>> readAllWorkers()
    {
        return ResponseEntity.ok(userService.findAll());
    }

}
