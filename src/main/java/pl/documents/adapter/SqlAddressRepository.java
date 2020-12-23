package pl.documents.adapter;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.documents.model.Address;
import pl.documents.repository.AddressRepository;

import java.util.UUID;

@Repository
interface SqlAddressRepository extends AddressRepository, JpaRepository<Address,UUID>
{

}
