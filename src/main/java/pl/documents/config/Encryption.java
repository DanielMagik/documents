package pl.documents.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Random;

@Component
@Scope("singleton")
public class Encryption
{
    private int keyLength = 20;
    private String sequence;
    public Encryption()
    {
        byte[] array = new byte[keyLength];
        new Random().nextBytes(array);
        this.sequence = new String(array, StandardCharsets.UTF_8);
    }

    public String getSequence()
    {
        return sequence;
    }

}
