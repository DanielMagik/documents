package pl.documents.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pl.documents.model.Worker;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface WorkerRepository
{
    List<Worker> findAll();
    Page<Worker> findAll(Pageable page);
    Optional<Worker> findById(UUID id);
    boolean existsById(UUID id);
    Worker save(Worker entity);
    void deleteById(UUID id);
    boolean existsByDocumentNumberAndDocumentType(String documentNumber, String documentType);
}
