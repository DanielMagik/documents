package pl.documents.adapter;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.documents.model.Worker;
import pl.documents.repository.WorkerRepository;

import java.util.UUID;

@Repository

interface SqlWorkerRepository extends WorkerRepository, JpaRepository<Worker, UUID>
{
    @Override
    boolean existsByDocumentNumberAndDocumentType(String documentNumber, String documentType);

    @Override
    boolean existsByNIP(String NIP);

    @Override
    boolean existsByPensionZUSNumber(String pensionZUSNumber);

    @Override
    boolean existsByDisabledZUSNumber(String disabledZUSNumber);

}
