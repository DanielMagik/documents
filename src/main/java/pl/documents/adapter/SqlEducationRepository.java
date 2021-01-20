package pl.documents.adapter;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.documents.model.Education;
import pl.documents.repository.EducationRepository;

import java.util.UUID;

@Repository
interface SqlEducationRepository extends EducationRepository, JpaRepository<Education, UUID>
{

}
