package pl.documents.repository;

import pl.documents.model.Address;
import pl.documents.model.Worker;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AddressRepository
{
    List<Address> findAllByWorker(Worker worker);
    Address save(Address entity);
    boolean existsById(UUID id);
    Optional<Address> findById(UUID id);
    void deleteById(UUID id);
}
