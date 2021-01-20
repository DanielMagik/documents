package pl.documents.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import pl.documents.exception.RegisterException;
import pl.documents.model.User;
import pl.documents.model.projection.EmailAndPasswordWriteModel;
import pl.documents.service.EmployeeAndAdminService;
import pl.documents.service.UserService;

import java.rmi.AccessException;

@RestController
public class EmployeeAndAdminController
{
    private final UserService userService;
    private final EmployeeAndAdminService employeeAndAdminService;
    public EmployeeAndAdminController(final UserService userService, final EmployeeAndAdminService employeeAndAdminService)
    {
        this.userService = userService;
        this.employeeAndAdminService = employeeAndAdminService;
    }

    @DeleteMapping("/deleteworker")
    public ResponseEntity<?> deleteWorker(@RequestHeader("Authorization") String token, @RequestBody EmailAndPasswordWriteModel writeModel)
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
            employeeAndAdminService.deleteWorker(user, writeModel);
        }
        catch (RegisterException e)
        {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getErrorMessage());
        }
        return ResponseEntity.ok("The user with the given e-mail has been deleted");
    }
    @DeleteMapping("/deletehr")
    public ResponseEntity<?> deleteHR(@RequestHeader("Authorization") String token, @RequestBody EmailAndPasswordWriteModel writeModel)
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
            employeeAndAdminService.deleteHR(user, writeModel);
        }
        catch (RegisterException e)
        {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getErrorMessage());
        }
        return ResponseEntity.ok("The user with the given e-mail has been deleted");
    }
}
