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
        Address address = new Address(addressType,postalCode,location,district,community,street,homeNumber,flatNumber);
        return address;
    }

    public void setAddressType(AddressType addressType)
    {
        this.addressType = addressType;
    }

    public void setPostalCode(String postalCode)
    {
        this.postalCode = postalCode;
    }

    public void setLocation(String location)
    {
        this.location = location;
    }

    public void setDistrict(String district)
    {
        this.district = district;
    }

    public void setCommunity(String community)
    {
        this.community = community;
    }

    public void setStreet(String street)
    {
        this.street = street;
    }

    public void setHomeNumber(String homeNumber)
    {
        this.homeNumber = homeNumber;
    }

    public void setFlatNumber(String flatNumber)
    {
        this.flatNumber = flatNumber;
    }
}


