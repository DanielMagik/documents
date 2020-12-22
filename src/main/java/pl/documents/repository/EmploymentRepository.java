package pl.documents.repository;

import pl.documents.model.Employment;
import pl.documents.model.Worker;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface EmploymentRepository
{
    List<Employment> findAllByWorker(Worker worker);
    Employment save(Employment entity);
    boolean existsById(UUID id);
    Optional<Employment> findById(UUID id);
    void deleteById(UUID id);
}
