package pl.documents.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.documents.model.Worker;
import pl.documents.model.projection.EducationReadModel;
import pl.documents.model.projection.WorkerReadModel;
import pl.documents.service.WorkerService;

import javax.persistence.EntityExistsException;
import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
public class WorkerController
{
    private static final Logger logger = LoggerFactory.getLogger(WorkerController.class);
    private final WorkerService workerService;
    public WorkerController(final WorkerService workerService)
    {
        this.workerService = workerService;
    }

    /**
     * Odczyt listy pracowników
     * @return lista pracowników
     */
    @GetMapping(value = "/workers")
    ResponseEntity<List<WorkerReadModel>> readAllWorkers()
    {
        logger.info("Read all workers!");
        return ResponseEntity.ok(workerService.readAllWorkers());
    }

    /*
    @GetMapping(value = "/workers")
    ResponseEntity<List<WorkerReadModel>> readAllWorkers(Pageable page)
    {
        logger.info("Read all workers pageable.");
        return ResponseEntity.ok(repository.findAll(page).getContent().
                stream().map(WorkerReadModel::new).collect(Collectors.toList()));
    }
     */

    /**
     * Odczyt pracownika o danym id
     * @param id id szukanego pracwonika
     * @return szukany pracownik lub informacja o jego braku
     */
    @GetMapping("/workers/{id}")
    ResponseEntity<WorkerReadModel> readWorker(@PathVariable UUID id)
    {
        WorkerReadModel result;
        try
        {
            result = workerService.readById(id);
            logger.info("Read worker with id "+ id+"! Read successful!");
        }
        catch (IllegalArgumentException e)
        {
            logger.info("Try to read worker with id "+ id+". Not found!");
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);

    }

    /**
     * Stworzenie nowego pracownika o pustuch polach
     * @return stworzony pracownik
     */
    @PostMapping("/workers")
    ResponseEntity<WorkerReadModel> createWorker()
    {
        logger.info("Create new empty worker");
        Worker worker = new Worker();
        WorkerReadModel result = workerService.createWorker(worker);
        return ResponseEntity.created(URI.create("")).body(result);
    }

    /**
     * Akutalizacja danych pracownika o zadanym id
     * @param id id aktualizowaneg pracownika
     * @param toUpdate nowe dane pracownika
     * @return informacja o pomyślnej bądź nieudanej aktualizacji
     */
    @PutMapping("/workers/{id}")
    ResponseEntity<?> updateWorker(@PathVariable UUID id, @RequestBody Worker toUpdate)
    {
        try
        {
            workerService.checkData(id, toUpdate);
            workerService.updateWorker(id, toUpdate);
            logger.info("Update worker with id " + id+ " successful!");
        }
        catch (EntityExistsException e)
        {
            logger.info("Update worker with id " + id+ ".Worker Not found!");
            return ResponseEntity.notFound().build();
        }
        //TODO obsługa wyjątków gdy podano błędne dane
        return ResponseEntity.noContent().build();
    }
    /**
     * Usunięcie pracownika o zadanym id
     * @param id id usuwanego pracownika
     * @return informacja o pomyślnym bądź nieudanym usunięciu
     */
    @DeleteMapping("/workers/{id}")
    ResponseEntity<?> deleteCandidate(@PathVariable UUID id)
    {
        if(workerService.deleteById(id))
        {
            logger.info("Delete worker with id " + id+ ".Delete successful!");
            return ResponseEntity.ok().build();
        }
        else
        {
            logger.info("Delete worker with id " + id+ ".Worker not found!");
            return ResponseEntity.notFound().build();
        }
    }
}
