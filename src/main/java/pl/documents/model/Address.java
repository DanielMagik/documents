package pl.documents.model;

import pl.documents.model.enums.AddressType;

import javax.persistence.*;

@Entity
@Table(name = "ADDRESSES")
public class Address
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private AddressType addressType;
    private String province;
    private String district;
    private String location;
    private String postalCode;
    private String postOffice;
    private String community;
    private String street;
    private String homeNumber;
    private String flatNumber;
    @ManyToOne
    @JoinColumn(name = "ID_WORKER",referencedColumnName = "id")
    private Worker worker;

    public Address()
    {
    }

    public AddressType getAddressType()
    {
        return addressType;
    }

    public void setAddressType(AddressType addressType)
    {
        this.addressType = addressType;
    }

    public String getProvince()
    {
        return province;
    }

    public void setProvince(String province)
    {
        this.province = province;
    }

    public String getDistrict()
    {
        return district;
    }

    public void setDistrict(String district)
    {
        this.district = district;
    }

    public String getLocation()
    {
        return location;
    }

    public void setLocation(String location)
    {
        this.location = location;
    }

    public String getPostalCode()
    {
        return postalCode;
    }

    public void setPostalCode(String postalCode)
    {
        this.postalCode = postalCode;
    }

    public String getPostOffice()
    {
        return postOffice;
    }

    public void setPostOffice(String postOffice)
    {
        this.postOffice = postOffice;
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
