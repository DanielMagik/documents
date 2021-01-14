package pl.documents.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.documents.exception.TokenException;
import pl.documents.model.Token;
import pl.documents.model.projection.TokenWriteModel;
import pl.documents.model.projection.WriteModelRegister;
import pl.documents.service.TokenService;

import java.util.List;
import java.util.UUID;

@RestController
public class TokenController
{
    private final TokenService tokenService;

    public TokenController(final TokenService tokenService)
    {
        this.tokenService = tokenService;
    }

    @PostMapping("/generatelink")
    ResponseEntity<?> generateLinkToRegister(@RequestBody TokenWriteModel tokenWriteModel)
    {
        try
        {

            String link = tokenService.generateToken(tokenWriteModel.getUserType());
            return ResponseEntity.ok(link);
        }
        catch (TokenException e)
        {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getErrorMessage());
        }
    }
    @PostMapping("/generatelinkadmin")
    ResponseEntity<?> generateLinkToRegisterAdmin()
    {
        try
        {

            String link = tokenService.generateTokenToAdmin();
            return ResponseEntity.ok(link);
        }
        catch (TokenException e)
        {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getErrorMessage());
        }
    }

    //todo to jest do testów
    @GetMapping("/alltokens")
    ResponseEntity<List<Token>> readAllTokens()
    {
        return ResponseEntity.ok(tokenService.readAll());
    }

}
