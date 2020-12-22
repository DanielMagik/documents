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

}


