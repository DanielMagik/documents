package pl.documents.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.documents.exception.AddressTypeExistsException;
import pl.documents.exception.BadAddressException;
import pl.documents.model.Address;
import pl.documents.model.Worker;
import pl.documents.model.enums.AddressType;
import pl.documents.model.projection.AddressReadModel;
import pl.documents.repository.AddressRepository;

import java.util.*;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

class AddressServiceTest
{
    private AddressService service;
    @BeforeEach
            void init()
    {
        service = new AddressService(inMemoryRepository);
    }

    @Test
    void checkTypeExists1()
    {

        Address a1 = new Address(AddressType.RESIDENCE, "44-444","a","b","c","d","1","2");
        Address a2 = new Address(AddressType.CHECKIN, "44-444","b","b","c","d","1","2");
        Address a3 = new Address(AddressType.CORRESPONDENCE, "44-444","c","b","c","d","1","2");
        AddressReadModel ar1 = new AddressReadModel(a1);
        AddressReadModel ar2 = new AddressReadModel(a2);
        AddressReadModel ar3 = new AddressReadModel(a3);
        List<AddressReadModel> list = new ArrayList<>();
        list.add(ar1);
        list.add(ar2);
        list.add(ar3);
        Address update = new Address(AddressType.CORRESPONDENCE, "44-444","c","b","c","d","1","2");
        var exception = catchThrowable(()->service.checkTypeExists(update,list));
        assertThat(exception).isInstanceOf(AddressTypeExistsException.class);
    }
    @Test
    void checkTypeExists2()
    {

        Address a1 = new Address(AddressType.RESIDENCE, "44-444","a","b","c","d","1","2");
        Address a2 = new Address(AddressType.CHECKIN, "44-444","b","b","c","d","1","2");
        Address a3 = new Address(AddressType.CORRESPONDENCE, "44-444","c","b","c","d","1","2");
        AddressReadModel ar1 = new AddressReadModel(a1);
        AddressReadModel ar2 = new AddressReadModel(a2);
        AddressReadModel ar3 = new AddressReadModel(a3);
        List<AddressReadModel> list = new ArrayList<>();
        list.add(ar1);
        list.add(ar2);
        list.add(ar3);
        Address update = new Address(AddressType.RESIDENCE_CORRESPONDENCE, "44-444","c","b","c","d","1","2");
        var exception = catchThrowable(()->service.checkTypeExists(update,list));
        assertThat(exception).isInstanceOf(AddressTypeExistsException.class);
    }
    @Test
    void checkTypeExists3()
    {

        Address a1 = new Address(AddressType.ALL, "44-444","a","b","c","d","1","2");
        AddressReadModel ar1 = new AddressReadModel(a1);
        List<AddressReadModel> list = new ArrayList<>();
        list.add(ar1);
        Address update = new Address(AddressType.RESIDENCE_CORRESPONDENCE, "44-444","c","b","c","d","1","2");
        var exception = catchThrowable(()->service.checkTypeExists(update,list));
        assertThat(exception).isInstanceOf(AddressTypeExistsException.class);
    }
    @Test
    void checkTypeExists4()
    {

        Address a1 = new Address(AddressType.RESIDENCE, "44-444","a","b","c","d","1","2");
        Address a2 = new Address(AddressType.CHECKIN_CORRESPONDENCE, "44-444","b","b","c","d","1","2");
        AddressReadModel ar1 = new AddressReadModel(a1);
        AddressReadModel ar2 = new AddressReadModel(a2);
        List<AddressReadModel> list = new ArrayList<>();
        list.add(ar1);
        list.add(ar2);
        Address update = new Address(AddressType.RESIDENCE_CORRESPONDENCE, "44-444","c","b","c","d","1","2");
        var exception = catchThrowable(()->service.checkTypeExists(update,list));
        assertThat(exception).isInstanceOf(AddressTypeExistsException.class);
    }
    @Test
    void checkTypeExists5()
    {

        Address a1 = new Address(AddressType.RESIDENCE_CHECKIN, "44-444","a","b","c","d","1","2");
        Address a3 = new Address(AddressType.CORRESPONDENCE, "44-444","c","b","c","d","1","2");
        AddressReadModel ar1 = new AddressReadModel(a1);
        AddressReadModel ar3 = new AddressReadModel(a3);
        List<AddressReadModel> list = new ArrayList<>();
        list.add(ar1);
        list.add(ar3);
        Address update = new Address(AddressType.RESIDENCE_CORRESPONDENCE, "44-444","c","b","c","d","1","2");
        var exception = catchThrowable(()->service.checkTypeExists(update,list));
        assertThat(exception).isInstanceOf(AddressTypeExistsException.class);
    }
    @Test
    void checkTypeExists6()
    {

        Address a1 = new Address(AddressType.RESIDENCE, "44-444","a","b","c","d","1","2");
        Address a2 = new Address(AddressType.CHECKIN, "44-444","b","b","c","d","1","2");
        Address a3 = new Address(AddressType.CORRESPONDENCE, "44-444","c","b","c","d","1","2");
        AddressReadModel ar1 = new AddressReadModel(a1);
        AddressReadModel ar2 = new AddressReadModel(a2);
        AddressReadModel ar3 = new AddressReadModel(a3);
        List<AddressReadModel> list = new ArrayList<>();
        list.add(ar1);
        list.add(ar2);
        list.add(ar3);
        Address update = new Address(AddressType.ALL, "44-444","c","b","c","d","1","2");
        var exception = catchThrowable(()->service.checkTypeExists(update,list));
        assertThat(exception).isInstanceOf(AddressTypeExistsException.class);
    }
    @Test
    void badPostalCode1()
    {

        Address update = new Address(AddressType.CORRESPONDENCE, "ass","c","b","c","d","1","2");
        var exception = catchThrowable(()->service.checkTypeExists(update,null));
        assertThat(exception).isInstanceOf(BadAddressException.class).
                hasFieldOrPropertyWithValue("errorMessage","Enter a correct postal code!");
    }
    @Test
    void badPostalCode2()
    {

        Address update = new Address(AddressType.CORRESPONDENCE, "34-7777","c","b","c","d","1","2");
        var exception = catchThrowable(()->service.checkTypeExists(update,null));
        assertThat(exception).isInstanceOf(BadAddressException.class).
                hasFieldOrPropertyWithValue("errorMessage","Enter a correct postal code!");
    }
    @Test
    void badHomeNumber1()
    {

        Address update = new Address(AddressType.CORRESPONDENCE, "34-777","c","b","c","d","aaa","2");
        var exception = catchThrowable(()->service.checkTypeExists(update,null));
        assertThat(exception).isInstanceOf(BadAddressException.class).
                hasFieldOrPropertyWithValue("errorMessage","Enter a correct home number!");
    }
    @Test
    void badHomeNumber2()
    {

        Address update = new Address(AddressType.CORRESPONDENCE, "34-777","c","b","c","d","34xx","2");
        var exception = catchThrowable(()->service.checkTypeExists(update,null));
        assertThat(exception).isInstanceOf(BadAddressException.class).
                hasFieldOrPropertyWithValue("errorMessage","Enter a correct home number!");
    }
    @Test
    void badFlatNumber1()
    {

        Address update = new Address(AddressType.CORRESPONDENCE, "34-777","c","b","c","d","34","acv");
        var exception = catchThrowable(()->service.checkTypeExists(update,null));
        assertThat(exception).isInstanceOf(BadAddressException.class).
                hasFieldOrPropertyWithValue("errorMessage","Enter a correct flat number!");
    }
    @Test
    void badFlatNumber2()
    {

        Address update = new Address(AddressType.CORRESPONDENCE, "34-777","c","b","c","d","34","3aa");
        var exception = catchThrowable(()->service.checkTypeExists(update,null));
        assertThat(exception).isInstanceOf(BadAddressException.class).
                hasFieldOrPropertyWithValue("errorMessage","Enter a correct flat number!");
    }
    @Test
    void checkTypeExistsNoException1()
    {

        Address a1 = new Address(AddressType.RESIDENCE, "44-444","a","b","c","d","1","2");
        Address a2 = new Address(AddressType.CHECKIN, "44-444","b","b","c","d","1","2");
        AddressReadModel ar1 = new AddressReadModel(a1);
        AddressReadModel ar2 = new AddressReadModel(a2);
        List<AddressReadModel> list = new ArrayList<>();
        list.add(ar1);
        list.add(ar2);
        Address update = new Address(AddressType.CORRESPONDENCE, "44-444","c","b","c","d","1","2");
        var exception = catchThrowable(()->service.checkTypeExists(update,list));
        assertThat(exception).doesNotThrowAnyException();
    }
    @Test
    void checkTypeExistsNoException2()
    {

        List<AddressReadModel> list = new ArrayList<>();
        Address update = new Address(AddressType.ALL, "44-444","c","b","c","d","1","2");
        var exception = catchThrowable(()->service.checkTypeExists(update,list));
        assertThat(exception).doesNotThrowAnyException();
    }
    @Test
    void checkTypeExistsNoException3()
    {

        Address a1 = new Address(AddressType.RESIDENCE_CORRESPONDENCE, "44-444","a","b","c","d","1","2");
        AddressReadModel ar1 = new AddressReadModel(a1);
        List<AddressReadModel> list = new ArrayList<>();
        list.add(ar1);
        Address update = new Address(AddressType.CHECKIN, "44-444","c","b","c","d","1","2");
        var exception = catchThrowable(()->service.checkTypeExists(update,list));
        assertThat(exception).doesNotThrowAnyException();
    }

    @Test
    void updateAddress1()
    {
        AddressRepository addressRepository = inMemoryRepository;
        Address address = new Address(AddressType.RESIDENCE, "44-444","a","b","c","d","1","2");
        Address a1 = new Address(AddressType.RESIDENCE, "44-444","b","b","c","d","1","2");
        Address a2 = new Address(AddressType.CHECKIN, "44-444","c","b","c","d","1","2");
        Address a3 = new Address(AddressType.CORRESPONDENCE, "44-444","d","b","c","d","1","2");
        List<Address> addresses = new ArrayList<>();
        addresses.add(a1);
        addresses.add(a2);
        addresses.add(a3);

        List<Address> result = service.updateAddress(address,addresses);
        List<Address> expected = new ArrayList<>();
        expected.add(address);
        expected.add(a2);
        expected.add(a3);
        for(int i=0;i<result.size();i++)
        {
            int ii = i;
            assertAll(
                    ()->assertEquals(result.get(ii),expected.get(ii)),
                    ()->assertEquals(result.get(ii).getAddressType(),expected.get(ii).getAddressType())
            );
        }
    }
    @Test
    void updateAddress2()
    {
        AddressRepository addressRepository = inMemoryRepository;
        Address address = new Address(AddressType.ALL, "44-444","a","b","c","d","1","2");
        Address a1 = new Address(AddressType.RESIDENCE, "44-444","b","b","c","d","1","2");
        Address a2 = new Address(AddressType.CHECKIN, "44-444","c","b","c","d","1","2");
        Address a3 = new Address(AddressType.CORRESPONDENCE, "44-444","d","b","c","d","1","2");
        List<Address> addresses = new ArrayList<>();
        addresses.add(a1);
        addresses.add(a2);
        addresses.add(a3);

        List<Address> result = service.updateAddress(address,addresses);
        List<Address> expected = new ArrayList<>();
        expected.add(address);
        for(int i=0;i<result.size();i++)
        {
            int ii = i;
            assertAll(
                    ()->assertEquals(result.get(ii),expected.get(ii)),
                    ()->assertEquals(result.get(ii).getAddressType(),expected.get(ii).getAddressType())
            );
        }
    }
    @Test
    void updateAddress3()
    {
        AddressRepository addressRepository = inMemoryRepository;
        Address address = new Address(AddressType.RESIDENCE, "44-444","a","b","c","d","1","2");
        Address a1 = new Address(AddressType.RESIDENCE, "44-444","a","b","c","d","1","2");
        Address a2 = new Address(AddressType.CHECKIN, "44-444","a","b","c","d","1","2");
        Address a3 = new Address(AddressType.CORRESPONDENCE, "44-444","a","b","c","d","1","2");
        List<Address> addresses = new ArrayList<>();
        addresses.add(a1);
        addresses.add(a2);
        addresses.add(a3);

        List<Address> result = service.updateAddress(address,addresses);
        List<Address> expected = new ArrayList<>();
        address.setAddressType(AddressType.ALL);
        expected.add(address);
        for(int i=0;i<result.size();i++)
        {
            int ii = i;
            assertAll(
                    ()->assertEquals(result.get(ii),expected.get(ii)),
                    ()->assertEquals(result.get(ii).getAddressType(),expected.get(ii).getAddressType())
            );
        }
    }
    @Test
    void updateAddress4()
    {
        AddressRepository addressRepository = inMemoryRepository;
        Address address = new Address(AddressType.RESIDENCE_CHECKIN, "44-444","a","b","c","d","1","2");
        Address a1 = new Address(AddressType.RESIDENCE, "44-444","b","b","c","d","1","2");
        Address a2 = new Address(AddressType.CHECKIN, "44-444","c","b","c","d","1","2");
        Address a3 = new Address(AddressType.CORRESPONDENCE, "44-444","d","b","c","d","1","2");
        List<Address> addresses = new ArrayList<>();
        addresses.add(a1);
        addresses.add(a2);
        addresses.add(a3);

        List<Address> result = service.updateAddress(address,addresses);
        List<Address> expected = new ArrayList<>();
        expected.add(address);
        expected.add(a3);
        for(int i=0;i<result.size();i++)
        {
            int ii = i;
            assertAll(
                    ()->assertEquals(result.get(ii),expected.get(ii)),
                    ()->assertEquals(result.get(ii).getAddressType(),expected.get(ii).getAddressType())
            );
        }
    }
    @Test
    void updateAddress5()
    {
        AddressRepository addressRepository = inMemoryRepository;
        Address address = new Address(AddressType.CORRESPONDENCE, "44-444","b","b","c","d","1","2");

        Address a1 = new Address(AddressType.RESIDENCE, "44-444","a","b","c","d","1","2");
        Address a2 = new Address(AddressType.CHECKIN, "44-444","b","b","c","d","1","2");
        Address a3 = new Address(AddressType.CORRESPONDENCE, "44-444","c","b","c","d","1","2");
        List<Address> addresses = new ArrayList<>();
        addresses.add(a1);
        addresses.add(a2);
        addresses.add(a3);

        List<Address> result = service.updateAddress(address,addresses);
        List<Address> expected = new ArrayList<>();
        address.setAddressType(AddressType.CHECKIN_CORRESPONDENCE);
        expected.add(address);
        expected.add(a1);
        for(int i=0;i<result.size();i++)
        {
            int ii = i;
            assertAll(
                    ()->assertEquals(result.get(ii),expected.get(ii)),
                    ()->assertEquals(result.get(ii).getAddressType(),expected.get(ii).getAddressType())
            );
        }
    }
    @Test
    void wasAddressesDeleted()
    {
        AddressRepository addressRepository = inMemoryRepository;
        Address address = new Address(AddressType.CORRESPONDENCE, "44-444","b","b","c","d","1","2");

        Address a1 = new Address(AddressType.RESIDENCE, "44-444","a","b","c","d","1","2");
        Address a2 = new Address(AddressType.CHECKIN, "44-444","b","b","c","d","1","2");
        Address a3 = new Address(AddressType.CORRESPONDENCE, "44-444","c","b","c","d","1","2");
        a1.setId(new UUID(5,6));
        a2.setId(new UUID(5,7));
        a3.setId(new UUID(5,8));
        Worker worker = new Worker();
        worker.setId(new UUID(1,1));
        a1.setWorker(worker);
        a2.setWorker(worker);
        a3.setWorker(worker);
        inMemoryRepository.save(a1);
        inMemoryRepository.save(a2);
        inMemoryRepository.save(a3);
        List<Address> addresses = new ArrayList<>();
        addresses.add(a1);
        addresses.add(a2);
        addresses.add(a3);
        List<Address> actual = inMemoryRepository.findAllByWorker(worker);
        assertEquals(3,actual.size());

        service.updateAddress(address,addresses);
        actual = inMemoryRepository.findAllByWorker(worker);
        assertEquals(0,actual.size());
    }
    private AddressRepository inMemoryRepository = new AddressRepository()
    {
        int counter = 0;
        UUID index = new UUID(0,counter);
        Map<UUID, Address> map  = new HashMap<>();
        @Override
        public List<Address> findAllByWorker(Worker worker)
        {
            return map.values().stream().filter(e->e.getWorker().getId().equals(worker.getId()))
                    .collect(Collectors.toList());
        }

        @Override
        public Address save(Address entity)
        {
            if(entity.getId().equals(new UUID(0,0)))
            {
                counter++;
                index = new UUID(0,counter);
                entity.setId(index);
            }
            map.put(entity.getId(),entity);
            return entity;
        }

        @Override
        public boolean existsById(UUID id)
        {
            return map.values().stream().anyMatch(e->e.getId().equals(id));
        }

        @Override
        public Optional<Address> findById(UUID id)
        {
            return Optional.ofNullable(map.get(id));
        }

        @Override
        public void deleteById(UUID id)
        {
            map.remove(id);
        }
    };
}