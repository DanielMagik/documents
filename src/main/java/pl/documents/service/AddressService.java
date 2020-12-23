package pl.documents.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.documents.exception.AddressTypeExistsException;
import pl.documents.exception.BadAddressException;
import pl.documents.logic.DataChecker;
import pl.documents.model.Address;
import pl.documents.model.enums.AddressType;
import pl.documents.model.projection.AddressReadModel;
import pl.documents.repository.AddressRepository;

import java.util.ArrayList;
import java.util.List;

//TODO ZASTANOWIĆ SIĘ NAD @Scope
@Service
public class AddressService
{
    private final AddressRepository repository;

    public AddressService(final AddressRepository addressRepository)
    {
        this.repository = addressRepository;
    }

    /**
     * Sprawdzenie poprawności danego adresu oraz czy można go dodać do bazy bez usuania innych. Jeśni nie, wyrzucany jest wyjątek
     * @param address
     * @param addressList
     * @throws AddressTypeExistsException
     */
    public void checkTypeExists(Address address, List<AddressReadModel> addressList) throws AddressTypeExistsException, BadAddressException
    {
        //tu może być wyrzucony wyjątek BadAddressException
        DataChecker.checkAddressCorrectness(address);
        AddressType addressType = address.getAddressType();
        List<AddressType> addressTypeList = new ArrayList<>();
        for(AddressReadModel a : addressList)
        {
            addressTypeList.add(a.getAddressType());
        }
        //zamiar stworzenia każdego adresu, gdy w liście jest jakikolwiek adres
        if(addressType == AddressType.ALL && addressTypeList.size()>0)
        {
             throw  new AddressTypeExistsException("Can,t create address of type ALL! In database exists some Address!");
        }
        //zamiar stworzenia adresu zamieszkania
        if(addressType==AddressType.RESIDENCE || addressType==AddressType.RESIDENCE_CHECKIN || addressType==AddressType.RESIDENCE_CORRESPONDENCE)
        {
            for(AddressType a : addressTypeList)
            {
                if(a== AddressType.ALL || a == AddressType.RESIDENCE || a==AddressType.RESIDENCE_CHECKIN || a==AddressType.RESIDENCE_CORRESPONDENCE)
                {
                   throw new AddressTypeExistsException("Can,t create address of type "+addressType+" In database exists Address of type "+a);

                }
            }
        }
        //zamiar stworzenia adresu zameldowania
        if(addressType==AddressType.CHECKIN || addressType==AddressType.RESIDENCE_CHECKIN || addressType==AddressType.CHECKIN_CORRESPONDENCE)
        {
            for(AddressType a : addressTypeList)
            {
                if(a== AddressType.ALL || a == AddressType.CHECKIN || a==AddressType.RESIDENCE_CHECKIN || a==AddressType.CHECKIN_CORRESPONDENCE)
                {
                    throw new AddressTypeExistsException("Can,t create address of type "+addressType+" In database exists Address of type "+a);
                }
            }
        }
        //zamiar stworzenia adresu do korespondencji
        if(addressType==AddressType.CORRESPONDENCE || addressType==AddressType.RESIDENCE_CORRESPONDENCE || addressType==AddressType.CHECKIN_CORRESPONDENCE)
        {
            for(AddressType a : addressTypeList)
            {
                if(a== AddressType.ALL || a == AddressType.CORRESPONDENCE || a==AddressType.RESIDENCE_CORRESPONDENCE || a==AddressType.CHECKIN_CORRESPONDENCE)
                {
                    throw new AddressTypeExistsException("Can,t create address of type "+addressType+" In database exists Address of type "+a);
                }
            }
        }

    }

    /**
     * Lista updatuje adresy pracownika, usuwa te stare
     * @param address nowy adres, który należy dodać
     * @param addressList aktualna lista adresów pracownika
     * @return lista adresów pracownika po transakcji
     */
    @Transactional
    public List<Address> updateAddress(Address address, List<Address> addressList)
    {
        List<Address> newAddresses = new ArrayList<>();
        Address residence = new Address(), checkin = new Address(), correspondence = new Address();
        //skopiowanie adresów z listy do 3 osobnych adresów
        for(Address a : addressList)
        {
            switch (a.getAddressType())
            {
                case ALL:
                {
                    residence = new Address(AddressType.RESIDENCE, a);
                    checkin = new Address(AddressType.CHECKIN, a);
                    correspondence = new Address(AddressType.CORRESPONDENCE, a);
                    break;
                }
                case RESIDENCE:
                {
                    residence = new Address(AddressType.RESIDENCE, a);
                    break;
                }
                case CHECKIN:
                {
                    checkin = new Address(AddressType.CHECKIN, a);
                    break;
                }
                case CORRESPONDENCE:
                {
                    correspondence = new Address(AddressType.CORRESPONDENCE, a);
                    break;
                }
                case RESIDENCE_CHECKIN:
                {
                    residence = new Address(AddressType.RESIDENCE, a);
                    checkin = new Address(AddressType.CHECKIN, a);
                    break;
                }
                case CHECKIN_CORRESPONDENCE:
                {
                    checkin = new Address(AddressType.CHECKIN, a);
                    correspondence = new Address(AddressType.CORRESPONDENCE, a);
                    break;
                }
                case RESIDENCE_CORRESPONDENCE:
                {
                    residence = new Address(AddressType.RESIDENCE, a);
                    correspondence = new Address(AddressType.CORRESPONDENCE, a);
                    break;
                }
            }
        }
        //usuwanie starych adresów
        for(Address a : addressList)
        {
            repository.deleteById(a.getId());
        }
        switch (address.getAddressType())
        {
            case ALL:
            {
                residence = new Address(AddressType.RESIDENCE, address);
                checkin = new Address(AddressType.CHECKIN, address);
                correspondence = new Address(AddressType.CORRESPONDENCE, address);
                break;
            }
            case RESIDENCE:
            {
                residence = new Address(AddressType.RESIDENCE, address);
                break;
            }
            case CHECKIN:
            {
                checkin = new Address(AddressType.CHECKIN, address);
                break;
            }
            case CORRESPONDENCE:
            {
                correspondence = new Address(AddressType.CORRESPONDENCE, address);
                break;
            }
            case RESIDENCE_CHECKIN:
            {
                residence = new Address(AddressType.RESIDENCE, address);
                checkin = new Address(AddressType.CHECKIN, address);
                break;
            }
            case RESIDENCE_CORRESPONDENCE:
            {
                residence = new Address(AddressType.RESIDENCE, address);
                correspondence = new Address(AddressType.CORRESPONDENCE, address);
                break;
            }
            case CHECKIN_CORRESPONDENCE:
            {
                checkin = new Address(AddressType.CHECKIN, address);
                correspondence = new Address(AddressType.CORRESPONDENCE, address);
                break;
            }
        }

        if(residence.equals(checkin) && checkin.equals(correspondence))
        {
            Address all = new Address(AddressType.ALL, residence);
            newAddresses.add(all);
        }
        else if(residence.equals(checkin))
        {
            Address residenceCheckin = new Address(AddressType.RESIDENCE_CHECKIN, residence);
            newAddresses.add(residenceCheckin);
            newAddresses.add(correspondence);
        }
        else if(residence.equals(correspondence))
        {
            Address residenceCorrespondence = new Address(AddressType.RESIDENCE_CORRESPONDENCE, residence);
            newAddresses.add(residenceCorrespondence);
            newAddresses.add(checkin);

        }
        else if(checkin.equals(correspondence))
        {
            Address checkinCorrespondence = new Address(AddressType.CHECKIN_CORRESPONDENCE,checkin);
            newAddresses.add(checkinCorrespondence);
            newAddresses.add(residence);
        }
        else
        {
            newAddresses.add(residence);
            newAddresses.add(checkin);
            newAddresses.add(correspondence);
        }
        return newAddresses;
    }


}
