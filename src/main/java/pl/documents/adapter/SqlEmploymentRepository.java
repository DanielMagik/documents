package pl.documents.adapter;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.documents.model.Employment;
import pl.documents.repository.EmploymentRepository;

import java.util.UUID;

@Repository
interface SqlEmploymentRepository extends EmploymentRepository, JpaRepository<Employment, UUID>
{

}
