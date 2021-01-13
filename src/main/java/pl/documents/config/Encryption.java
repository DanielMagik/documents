package pl.documents.config;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.nio.charset.Charset;
import java.util.Random;

@Component
@Scope("singleton")
public class Encryption
{
    public Encryption()
    {
        byte[] array = new byte[20];
        new Random().nextBytes(array);
        String generatedString = new String(array, Charset.forName("UTF-8"));
        this.sequence = "1234567sgadsadsaem347mwo43wbm4eot4yaeb68be";
    }

    private final String sequence;

    public String getSequence()
    {
        return sequence;
    }
}
