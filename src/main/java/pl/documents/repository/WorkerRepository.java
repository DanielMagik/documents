package pl.documents.repository;

import pl.documents.model.Worker;

import java.util.List;
import java.util.Optional;

public interface WorkerRepository
{
    List<Worker> findAll();
    Optional<Worker> findById(Integer id);
    Worker save(Worker entity);
}
