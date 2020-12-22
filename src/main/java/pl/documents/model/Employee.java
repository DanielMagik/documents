package pl.documents.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

/**
 * Pracownicy kadr i administratorzy
 */
@Entity
@Table(name = "EMPLOYEES")
public class Employee
{
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private UUID id;
    /**
     * Imię
     */
    private String name;
    /**
     * Nazwisko
     */
    private String surname;
    /**
     * E-mail
     */
    private String email;
    /**
     * Hasło
     */
    private String password;

    public Employee()
    {
    }


}
