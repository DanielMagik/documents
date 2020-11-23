package pl.documents.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import pl.documents.model.Candidate;

import java.util.List;

@RepositoryRestResource
public interface CandidateRepository extends JpaRepository<Candidate, Integer>
{
    @Override
    @RestResource(exported = false)
    void deleteById(Integer integer);

    @Override
    @RestResource(exported = false)
    void delete(Candidate candidate);

    /**
     * Finding candidates who have ever logged and completed the form.
     * @return list of candidates who completed the form
     */
    @RestResource(path = "completed", rel = "completed")
    List<Candidate> findByCompleted(@Param("completed") boolean isComplete);
}
