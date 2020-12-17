package pl.documents.model;

import pl.documents.model.enums.AddressType;

import javax.persistence.*;

/**
 * Adres pracownika
 */
@Entity
@Table(name = "ADDRESSES")
public class Address
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
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

    public int getId()
    {
        return id;
    }

    public void setId(int id)
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
}
