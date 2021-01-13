package pl.documents.model;

import org.hibernate.annotations.GenericGenerator;
import pl.documents.model.enums.UserType;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "USERS")
public class User
{


    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private UUID id;

    private String email;
    private String password;
    private UserType userType;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "worker_id", referencedColumnName = "id")
    private Worker worker;



    //public UUID getId()
  //  {
    //    return id;
   // }

   // public void setId(UUID id)
   // {
   //     this.id = id;
  //  }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public UserType getUserType()
    {
        return userType;
    }

    public void setUserType(UserType userType)
    {
        this.userType = userType;
    }
}
