package pl.documents.controller;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.documents.config.Encryption;
import pl.documents.model.User;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Base64;
import java.util.Date;

@RestController
public class LoginApi
{
    private final Encryption encryption;

    public LoginApi(Encryption encryption)
    {
        this.encryption = encryption;
    }

    @GetMapping("/test1")
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
    public String login(@RequestBody User user)
    {
        //TODO czy użytkownik istnieje w bazie? logika w dofilter powinna byc
        LocalDateTime currentTime = LocalDateTime.now();

        /*
        return Jwts.builder()
                .setSubject(user.getEmail())
                .claim("role",user.getUserType().toString())
                .setIssuedAt(Date.from(currentTime.atZone(ZoneId.systemDefault()).toInstant()))
                .setExpiration(Date.from(currentTime
                        .plusSeconds(20)
                        .atZone(ZoneId.systemDefault()).toInstant()))
                .signWith(SignatureAlgorithm.HS512,user.getPassword())
                .compact();

         */
        String encodedString = Base64.getEncoder().encodeToString(encryption.getSequence().getBytes());
        return Jwts.builder()
                .claim("name","adam")
                .claim("role","ROLE_ADMINA")
                .setIssuedAt(Date.from(currentTime.atZone(ZoneId.systemDefault()).toInstant()))
                .setExpiration(Date.from(currentTime
                        .plusSeconds(20)
                        .atZone(ZoneId.systemDefault()).toInstant()))
                .signWith(SignatureAlgorithm.HS256,encodedString)
                .compact();
    }
    @GetMapping("/getiset")
    public String get(@RequestBody User user)
    {
        return "Hello";

    }

}
