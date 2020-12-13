package pl.documents.model.projection;

import pl.documents.model.Address;
import pl.documents.model.enums.AddressType;

public class AddressWriteModel
{
    private AddressType addressType;
    private String postalCode;
    private String location;
    private String district;
    private String community;
    private String street;
    private String homeNumber;
    private String flatNumber;
    public Address toAddress()
    {
        Address address = new Address();
        address.setAddressType(addressType);
        address.setPostalCode(postalCode);
        address.setLocation(location);
        address.setDistrict(district);
        address.setCommunity(community);
        address.setStreet(street);
        address.setHomeNumber(homeNumber);
        address.setFlatNumber(flatNumber);
        return address;
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


