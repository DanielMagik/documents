package pl.documents.config;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Random;

@Component
@Scope("singleton")
public class Encryption
{
    private String sequence;
    public Encryption()
    {
        byte[] array = new byte[30];
        new Random().nextBytes(array);
        this.sequence = new String(array, StandardCharsets.UTF_8);
    }

    public String getSequence()
    {
        return sequence;
    }

}
