package pl.documents.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import pl.documents.model.Candidate;

@RepositoryRestResource
interface CandidateRepository extends JpaRepository<Candidate, Integer>
{

}
