package pl.documents.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import pl.documents.model.Candidate;

import java.util.List;
import java.util.Optional;

public interface CandidateRepository
{
    List<Candidate> findAll();
    Page<Candidate> findAll(Pageable page);
    Optional<Candidate> findById(Integer id);
    boolean existsById(Integer id);
    Candidate save(Candidate entity);
    void deleteById(Integer id);

}
