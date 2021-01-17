package pl.documents.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.documents.exception.LoginException;
import pl.documents.exception.RegisterException;
import pl.documents.exception.TokenException;
import pl.documents.model.Token;
import pl.documents.model.User;
import pl.documents.model.Worker;
import pl.documents.model.enums.UserType;
import pl.documents.model.projection.*;
import pl.documents.service.MailService;
import pl.documents.service.TokenService;
import pl.documents.service.UserService;

import java.rmi.AccessException;
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

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody EmailAndPasswordWriteModel emailAndPasswordWriteModel)
    {
        User user;
        User result = null;
        LoginResponse loginResponse;
        try
        {
            user = emailAndPasswordWriteModel.toUser();
            try
            {
                result = userService.findByEmailAndPassword(user.getEmail(), user.getPassword());
            }
            catch (LoginException e)
            {
                return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getErrorMessage());
            }

            String href = "http://localhost:8080/";
            switch (result.getUserType())
            {
                case WORKER:
                    href = href + "worker";
                    break;
                case HR_EMPLOYEE:
                    href = href + "hr_employee";
                    break;
                case ADMIN:
                    href = href + "admin";
                    break;
            }
            String token = userService.generateToken(result);
            loginResponse = new LoginResponse(href, token);
        }
        catch (NullPointerException e)
        {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Incorrect data!");
        }

        return ResponseEntity.ok(loginResponse);

    }

    @PostMapping("/register/{id}")
    ResponseEntity<?> registerWorker(@RequestBody EmailAndPasswordWriteModel emailAndPasswordWriteModel, @PathVariable UUID id)
    {
        UserType userType = null;
        try
        {
            try
            {
                userType = userService.findTypeById(id);
            }
            catch (RegisterException e)
            {
                return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getErrorMessage());
            }


            User user = emailAndPasswordWriteModel.toUser();
            if (userService.existsByEmail(user.getEmail()))
            {
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Already exists user with that email!");
            }
            try
            {
                userService.checkData(user.getEmail(), emailAndPasswordWriteModel.getPassword());
            }
            catch (RegisterException e)
            {
                return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getErrorMessage());
            }

            user.setUserType(userType);
            Worker worker = null;
            if (user.getUserType().equals(UserType.WORKER))
            {
                worker = new Worker();
                worker.setId(user.getId());
                user.setWorker(worker);
            }
            userService.createUser(user, worker, id);
            String link;
            try
            {
                link = tokenService.generateLinkAndTokenAccountConfirm(user);
                String message = "To confirm your registration, please click the link below.\n" +
                        link;

                Thread thread = new Thread(() -> {
                    mailService.sendSimpleMessage(user.getEmail(), "Confirm your registration", message);
                });
                thread.start();
                thread.interrupt();
            }
            catch (TokenException e)
            {
                return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getErrorMessage());
            }
        }
        catch (NullPointerException e)
        {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Incorrect data!");
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
    @GetMapping("/allusers")
    ResponseEntity<List<WorkerReadModelForEmployee>> readAllWorkers(@RequestHeader("Authorization") String token)
    {
        return ResponseEntity.ok(userService.findAll());
    }
    @PutMapping("/changepassword")
    public ResponseEntity<?> changePassword(@RequestHeader("Authorization") String token, @RequestBody ChangePasswordWriteModel writeModel)
    {
        User user;
        try
        {
            user = userService.getByToken(token);
        }
        catch (AccessException | IllegalArgumentException e)
        {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("No access!");
        }
        try
        {
            userService.checkNewPassword(writeModel.getNewPassword(), writeModel.getPassword(),user.getId());
        }
        catch (RegisterException e)
        {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getErrorMessage());
        }
        userService.changePassword(user.getId(),writeModel.getNewPassword());
        return ResponseEntity.ok("Password has been changed.");
    }
    @PatchMapping("/forgetpassword")
    public ResponseEntity<?> forgetPassword(@RequestBody EmailAndPasswordWriteModel modelRegister)
    {
        try
        {
            User user=userService.findByEmail(modelRegister.getEmail());

            Thread thread = new Thread(() -> {
                mailService.sendSimpleMessage(modelRegister.getEmail(),"password reminder",
                        "Your current password is: \""+user.getPassword()+"\"");
            });
            thread.start();
            thread.interrupt();
        }
        catch (LoginException e)
        {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getErrorMessage());
        }

        return ResponseEntity.ok("Your password has been sent to the e-mail address provided.");
    }
    @DeleteMapping("/deleteaccount")
    public ResponseEntity<?> deleteMyAccount(@RequestHeader("Authorization") String token, @RequestBody EmailAndPasswordWriteModel modelRegister)
    {
        User user;
        try
        {
            user = userService.getByToken(token);
        }
        catch (AccessException | IllegalArgumentException e)
        {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("No access!");
        }

        try
        {
            userService.deleteAccount(user,modelRegister.getPassword());
        }
        catch (RegisterException e)
        {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getErrorMessage());
        }
        return ResponseEntity.ok("Your account has been deleted!");
    }

}
