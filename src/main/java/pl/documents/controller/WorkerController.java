package pl.documents.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.documents.config.Encryption;
import pl.documents.exception.BadEducationException;
import pl.documents.exception.BadIdException;
import pl.documents.exception.BadWorkerException;
import pl.documents.model.User;
import pl.documents.model.Worker;
import pl.documents.model.projection.*;
import pl.documents.service.WorkerService;

import java.rmi.AccessException;
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
    @GetMapping
    ResponseEntity<List<WorkerReadModelForEmployee>> readAllWorkers()
    {
        logger.info("Read all workers!");
        return ResponseEntity.ok(workerService.readAllWorkers());
    }

    @GetMapping("/worker")
    public ResponseEntity<?> ReadWorker(@RequestHeader("Authorization") String token)
    {
        WorkerReadModel workerReadModel;
        try
        {
            User user = workerService.getByToken(token);
            workerReadModel = new WorkerReadModel(user.getWorker(), user);
        }
        catch (AccessException | IllegalArgumentException e)
        {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("No access!");
        }
        return ResponseEntity.ok(workerReadModel);
    }

    /**
     * Akutalizacja danych pracownika o zadanym id
     * @return informacja o pomyślnej bądź nieudanej aktualizacji
     */
    @PutMapping("/updatecandidate")
    ResponseEntity<?> updateWorker(@RequestHeader("Authorization") String token, @RequestBody CandidateWriteModel candidateWriteModel)
    {
        User user;
        try
        {
            user = workerService.getByToken(token);
        }
        catch (AccessException | IllegalArgumentException e)
        {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("No access!");
        }
        try
        {
            workerService.checkCandidate(candidateWriteModel);
        }
        catch (BadWorkerException | BadEducationException e)
        {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getErrorMessage());
        }
        workerService.updateCandidate(user.getWorker().getId(), candidateWriteModel);
        return ResponseEntity.noContent().build();
        /*
        try
        {
            Worker toUpdate = workerWriteModel.toWorker();
            workerService.checkData(id, toUpdate);
            workerService.updateWorker(id, toUpdate);
            logger.info("Update worker with id " + id+ " successful!");
        }
        catch (BadIdException e)
        {
            logger.info("Update worker with id "+ id+".Worker not found!");
            return ResponseEntity.notFound().build();
        }
        catch (BadWorkerException e)
        {
            logger.info("Update worker with id "+ id+". Bad data!");
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getErrorMessage());
        }
        return ResponseEntity.noContent().build();

         */
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
     * Zmiana hasła lub adresu e-mail użytkownika
     * @return informacja o zmianie danych
     */
    @PutMapping("/change/{id}")
    ResponseEntity<?> changeImportantData(@PathVariable UUID id, @RequestBody WorkerWriteModelChangePassword workerWriteModel)
    {
        logger.info("Try to change important data!");
        try
        {
            workerService.changeImportantData(id,workerWriteModel);
        }
        catch (BadIdException | BadWorkerException e)
        {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getErrorMessage());
        }
        logger.info("Change success!");

        return ResponseEntity.noContent().build();
    }




    /**
     * Usunięcie pracownika o zadanym id
     * @param id id usuwanego pracownika
     * @return informacja o pomyślnym bądź nieudanym usunięciu
     */
    @DeleteMapping("/{id}")
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
