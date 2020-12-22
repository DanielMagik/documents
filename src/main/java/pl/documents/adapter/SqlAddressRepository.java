package pl.documents.adapter;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.documents.model.Address;
import pl.documents.model.Education;
import pl.documents.model.Worker;
import pl.documents.repository.AddressRepository;
import pl.documents.repository.EducationRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
interface SqlAddressRepository extends AddressRepository, JpaRepository<Address,UUID>
{

}
