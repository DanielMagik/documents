package pl.documents.repository;

import pl.documents.model.Address;

import java.util.Optional;
import java.util.UUID;

public interface AddressRepository
{
    Address save(Address entity);
    boolean existsById(UUID id);
    Optional<Address> findById(UUID id);
    void deleteById(UUID id);
    void deleteAllByWorkerId(UUID id);
}
