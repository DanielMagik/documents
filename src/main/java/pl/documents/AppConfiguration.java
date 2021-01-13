package pl.documents;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "appconfiguration")
public class AppConfiguration
{
    private int accessBefore;
    private int deleteAfter;
    private String encrypt;

    public int getAccessBefore()
    {
        return accessBefore;
    }

    public void setAccessBefore(int accessBefore)
    {
        this.accessBefore = accessBefore;
    }

    public int getDeleteAfter()
    {
        return deleteAfter;
    }

    public void setDeleteAfter(int deleteAfter)
    {
        this.deleteAfter = deleteAfter;
    }

    public String getEncrypt()
    {
        return encrypt;
    }

    public void setEncrypt(String encrypt)
    {
        this.encrypt = encrypt;
    }
}
