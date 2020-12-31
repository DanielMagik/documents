package pl.documents.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pl.documents.logic.DataChecker;
import pl.documents.model.Worker;

import java.util.*;

public class TestWorkerRepository implements WorkerRepository
{

    Map<UUID, Worker> map  = new HashMap<>();
    @Override
    public List<Worker> findAll()
    {
        return new ArrayList<>(map.values());
    }

    @Override
    public Page<Worker> findAll(Pageable page)
    {
        return null;
    }

    @Override
    public Optional<Worker> findById(UUID id)
    {
        return Optional.ofNullable(map.get(id));
    }

    @Override
    public boolean existsById(UUID id)
    {
        return map.values().stream().anyMatch(e->e.getId().equals(id));
    }

    @Override
    public Worker save(Worker entity)
    {
        UUID id = entity.getId();
        entity.setId(id);
        map.put(entity.getId(),entity);
        return map.get(entity.getId());
    }

    @Override
    public void deleteById(UUID id)
    {
        map.remove(id);
    }

    @Override
    public boolean existsByDocumentNumberAndDocumentType(String documentNumber, String documentType)
    {
        return map.values().stream().filter(worker -> worker.getDocumentNumber().equals(documentNumber))
                .anyMatch(worker -> DataChecker.compareStrings(worker.getDocumentType(),documentType));
    }

    @Override
    public Optional<Worker> findByEmailAndPassword(String email, String password)
    {
        return map.values().stream().filter(worker -> worker.getEmail().equals(email)).filter(
                worker -> worker.getPassword().equals(password)
        ).findAny();
    }

    @Override
    public boolean existsByEmail(String email)
    {
        return map.values().stream().anyMatch(worker -> worker.getEmail().equals(email));
    }

}