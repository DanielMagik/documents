package pl.documents.model;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table
public class Candidate
{
    @Id
    private int id;
    private String email;
    private String password;
    private String phoneNumber;
    private String fillLocation;
    private LocalDateTime fillDate;
}
