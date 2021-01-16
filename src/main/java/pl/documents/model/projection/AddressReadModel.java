package pl.documents.model.projection;

import pl.documents.model.Address;
import pl.documents.model.enums.AddressType;

import java.util.UUID;

public class AddressReadModel
{
    private AddressType addressType;
    private String postalCode;
    private String location;
    private String district;
    private String community;
    private String street;
    private String homeNumber;
    private String flatNumber;

    public AddressReadModel(Address source)
    {
        this.addressType = source.getAddressType();
        this.postalCode = source.getPostalCode();
        this.location = source.getLocation();
        this.district = source.getDistrict();
        this.community = source.getCommunity();
        this.street = source.getStreet();
        this.homeNumber = source.getHomeNumber();
        this.flatNumber = source.getFlatNumber();
    }

    public AddressType getAddressType()
    {
        return addressType;
    }

    public String getPostalCode()
    {
        return postalCode;
    }

    public String getLocation()
    {
        return location;
    }

    public String getDistrict()
    {
        return district;
    }

    public String getCommunity()
    {
        return community;
    }

    public String getStreet()
    {
        return street;
    }

    public String getHomeNumber()
    {
        return homeNumber;
    }

    public String getFlatNumber()
    {
        return flatNumber;
    }

    public Address toAddress()
    {
        return new Address(addressType,postalCode,location,district,community,street,homeNumber,flatNumber);
    }
}
