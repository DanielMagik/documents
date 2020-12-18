package pl.documents.repository;

import pl.documents.model.Education;
import pl.documents.model.Worker;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface EducationRepository
{
    List<Education> findAllByWorker(Worker worker);
    Education save(Education entity);
    boolean existsById(UUID id);
    Optional<Education> findById(UUID id);
    void deleteById(UUID id);
}
