package pl.documents;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "appconfiguration")
public class AppConfiguration
{
    private int accessBefore;
    private int deleteAfter;

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
}
