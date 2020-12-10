package pl.documents.repository;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.documents.model.Candidate;

import java.util.List;

@Repository
interface SqlCandidateRepository extends CandidateRepository, JpaRepository<Candidate, Integer>
{
    @Override
    boolean existsByPhoneNumber(String phoneNumber);

    @Override
    boolean existsByEmail(String email);
}
