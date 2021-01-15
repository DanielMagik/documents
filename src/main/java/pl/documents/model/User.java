package pl.documents.model;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import pl.documents.model.enums.UserType;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import javax.persistence.*;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
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
    private boolean active;




    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "worker_id", referencedColumnName = "id")
    private Worker worker;

    public User()
    {

    }

    public User(String email, String password)
    {
        this.email=email;
        this.password=password;//encrypt(password,"qW$rtyB^OMNa2d1$");
        this.active=false;
    }

    public UUID getId()
    {
        return id;
    }

   // public void setId(UUID id)
   // {
   //     this.id = id;
  //  }

    public void setWorker(Worker worker)
    {
        this.worker = worker;
    }

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
        return this.password;//decrypt(this.password,"qW$rtyB^OMNa2d1$");
    }

    public void setPassword(String password)
    {
        this.password = password; //encrypt(password,"qW$rtyB^OMNa2d1$");
    }

    public UserType getUserType()
    {
        return userType;
    }

    public void setUserType(UserType userType)
    {
        this.userType = userType;
    }

    public Worker getWorker()
    {
        return worker;
    }

    public boolean isActive()
    {
        return active;
    }

    public void setActive(boolean active)
    {
        this.active = active;
    }

    /*
    public String encrypt(String toEncrypt, String key)
    {
        String result = null;
        try
        {
            Key aesKey = new SecretKeySpec(key.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, aesKey);
            byte[] encrypted = cipher.doFinal(toEncrypt.getBytes());
            result = new String(encrypted);
        }
        catch (NoSuchAlgorithmException | NoSuchPaddingException | BadPaddingException | InvalidKeyException | IllegalBlockSizeException e)
        {
            e.printStackTrace();
        }
        return result;
    }
    private String decrypt(String toDecrypt, String key)
    {
        String decrypted = null;
        try
        {
            Key aesKey = new SecretKeySpec(key.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, aesKey);
            decrypted = new String(cipher.doFinal(toDecrypt.getBytes()));

        }
        catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | BadPaddingException | IllegalBlockSizeException ignored)
        {

        }
        return decrypted;
    }
     */


}
