package pl.documents.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.documents.exception.TokenException;
import pl.documents.model.Token;
import pl.documents.model.projection.TokenWriteModel;
import pl.documents.service.TokenService;

import java.util.List;

@RestController
public class TokenController
{
    private final TokenService tokenService;

    public TokenController(final TokenService tokenService)
    {
        this.tokenService = tokenService;
    }

    @PostMapping("/generatelink")
    ResponseEntity<?> generateLinkToRegister(@RequestHeader("Authorization") String token, @RequestBody TokenWriteModel tokenWriteModel)
    {
        try
        {
            String link = tokenService.generateToken(tokenWriteModel.getUserType());
            return ResponseEntity.ok(link);
        }
        catch (TokenException e)
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getErrorMessage());
        }
    }

    @GetMapping("/generatelinkadmin")
    ResponseEntity<?> generateLinkToRegisterAdmin(@RequestHeader("Authorization") String token)
    {
        try
        {
            String link = tokenService.generateTokenToAdmin();
            return ResponseEntity.ok(link);
        }
        catch (TokenException e)
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getErrorMessage());
        }
    }

    @GetMapping("/alltokens")
    ResponseEntity<List<Token>> readAllTokens(@RequestHeader("Authorization") String token)
    {
        return ResponseEntity.ok(tokenService.readAll());
    }

}
