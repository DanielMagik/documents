package pl.documents.config;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.nio.charset.Charset;
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
        String generatedString = new String(array, Charset.forName("UTF-8"));
        this.sequence=generatedString;
    }

    public String getSequence()
    {

        return sequence;
    }
}
