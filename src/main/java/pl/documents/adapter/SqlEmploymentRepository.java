package pl.documents.adapter;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.documents.model.Education;
import pl.documents.model.Employment;
import pl.documents.model.Worker;
import pl.documents.repository.EmploymentRepository;

import java.util.Optional;
import java.util.UUID;
import java.util.List;

@Repository
public interface SqlEmploymentRepository extends EmploymentRepository, JpaRepository<Employment, UUID>
{

}
