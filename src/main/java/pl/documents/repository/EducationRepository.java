package pl.documents.repository;

import pl.documents.model.Education;
import pl.documents.model.Worker;

import java.util.List;
import java.util.Optional;

public interface EducationRepository
{
    List<Education> findAllByWorker(Worker worker);
    Education save(Education entity);
    boolean existsById(Integer id);
    Optional<Education> findById(Integer id);
    void deleteById(Integer id);
}
