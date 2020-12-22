package pl.documents.model;

import org.hibernate.annotations.GenericGenerator;
import pl.documents.model.enums.AddressType;
import pl.documents.logic.DataChecker;

import javax.persistence.*;
import java.util.UUID;

/**
 * Adres pracownika
 */
@Entity
@Table(name = "ADDRESSES")
public class Address
{
    @Id
    @Column(columnDefinition = "binary")
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private UUID id = UUID.randomUUID();
    /**
     * Rodzaj adresu (zamieszkania, zameldowania, do korespondencji)
     */
    private AddressType addressType;
    /**
     * Kod pocztowy
     */
    private String postalCode;
    /**
     * Miejscowość
     */
    private String location;
    /**
     * Powiat
     */
    private String district;
    /**
     * Gmina
     */
    private String community;
    /**
     * Ulica
     */
    private String street;
    /**
     * Numer domu
     */
    private String homeNumber;
    /**
     * Numer lokalu
     */
    private String flatNumber;
    /**
     * Pracownik powiązany z adresem
     */
    @ManyToOne
    @JoinColumn(name = "ID_WORKER",referencedColumnName = "id")
    private Worker worker;

    public Address()
    {
    }
    public Address(AddressType type, Address address)
    {
        this.addressType=type;
        this.postalCode=address.getPostalCode();
        this.location=address.getLocation();
        this.district=address.getDistrict();
        this.community=address.getCommunity();
        this.street=address.getStreet();
        this.homeNumber=address.getHomeNumber();
        this.flatNumber=address.getFlatNumber();
    }

    public UUID getId()
    {
        return id;
    }

    public void setId(UUID id)
    {
        this.id = id;
    }

    public AddressType getAddressType()
    {
        return addressType;
    }

    public void setAddressType(AddressType addressType)
    {
        this.addressType = addressType;
    }

    public String getPostalCode()
    {
        return postalCode;
    }

    public void setPostalCode(String postalCode)
    {
        this.postalCode = postalCode;
    }

    public String getLocation()
    {
        return location;
    }

    public void setLocation(String location)
    {
        this.location = location;
    }

    public String getDistrict()
    {
        return district;
    }

    public void setDistrict(String district)
    {
        this.district = district;
    }

    public String getCommunity()
    {
        return community;
    }

    public void setCommunity(String community)
    {
        this.community = community;
    }

    public String getStreet()
    {
        return street;
    }

    public void setStreet(String street)
    {
        this.street = street;
    }

    public String getHomeNumber()
    {
        return homeNumber;
    }

    public void setHomeNumber(String homeNumber)
    {
        this.homeNumber = homeNumber;
    }

    public String getFlatNumber()
    {
        return flatNumber;
    }

    public void setFlatNumber(String flatNumber)
    {
        this.flatNumber = flatNumber;
    }

    public void setWorker(Worker worker)
    {
        this.worker = worker;
    }

    @Override
    public boolean equals(Object obj)
    {
        if(!(obj instanceof Address))
        {
            return false;
        }
        else if(!DataChecker.compareStrings(this.community, ((Address) obj).getCommunity()))
            return false;
        else if(!DataChecker.compareStrings(this.district, ((Address) obj).getDistrict()))
            return false;
        else if(!DataChecker.compareStrings(this.flatNumber, ((Address) obj).getFlatNumber()))
            return false;
        else if(!DataChecker.compareStrings(this.homeNumber, ((Address) obj).getHomeNumber()))
            return false;
        else if(!DataChecker.compareStrings(this.location, ((Address) obj).getLocation()))
            return false;
        else if(!DataChecker.compareStrings(this.postalCode, ((Address) obj).getPostalCode()))
            return false;
        else if(!DataChecker.compareStrings(this.street, ((Address) obj).getStreet()))
            return false;

        return true;
    }

}
