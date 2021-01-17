package pl.documents.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "TEMPLATES")
public class Template
{
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private UUID id;
    private String documentName;
    private String documentType;
    @Lob
    private byte[] data;
    private boolean obligatory;
    public Template()
    {

    }
    public Template(String documentName, String documentType, byte[] data, boolean obligatory)
    {
        this.documentName = documentName;
        this.documentType = documentType;
        this.data = data;
        this.obligatory = obligatory;
    }

    public UUID getId()
    {
        return id;
    }

    public String getDocumentName()
    {
        return documentName;
    }

    public void setDocumentName(String documentName)
    {
        this.documentName = documentName;
    }

    public String getDocumentType()
    {
        return documentType;
    }

    public void setDocumentType(String documentType)
    {
        this.documentType = documentType;
    }

    public byte[] getData()
    {
        return data;
    }

    public void setData(byte[] data)
    {
        this.data = data;
    }

    public boolean isObligatory()
    {
        return obligatory;
    }

    public void setObligatory(boolean obligatory)
    {
        this.obligatory = obligatory;
    }
}
