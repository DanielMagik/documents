package pl.documents.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pl.documents.model.Worker;

import java.util.List;
import java.util.Optional;

public interface WorkerRepository
{
    List<Worker> findAll();
    Page<Worker> findAll(Pageable page);
    Optional<Worker> findById(Integer id);
    boolean existsById(Integer id);
    Worker save(Worker entity);
    void deleteById(Integer id);
    boolean existsByDocumentNumberAndDocumentType(String documentNumber, String documentType);
    boolean existsByNIP(String NIP);
    boolean existsByPensionZUSNumber(String pensionZUSNumber);
    boolean existsByDisabledZUSNumber(String disabledZUSNumber);
}
