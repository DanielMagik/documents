package pl.documents.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.documents.exception.BadIdException;
import pl.documents.exception.BadWorkerException;
import pl.documents.exception.LoginException;
import pl.documents.exception.RegisterException;
import pl.documents.model.Worker;
import pl.documents.model.projection.*;
import pl.documents.service.WorkerService;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/workers")
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
    @GetMapping("/{id}")
    ResponseEntity<WorkerReadModel> readWorker(@PathVariable UUID id)
    {
        WorkerReadModel result;
        try
        {
            result = workerService.readById(id);
            logger.info("Read worker with id "+ id+"!Read successful!");
            return ResponseEntity.ok(result);
        }
        catch (BadIdException e)
        {
            logger.info("Read worker with id "+ id+".Worker not found!");
            return ResponseEntity.notFound().build();
        }
    }


    /*
     * Stworzenie nowego pracownika o pustych polach
     * @return stworzony pracownik
     *
    @PostMapping("/workers")
    ResponseEntity<WorkerReadModel> createWorker()
    {
        logger.info("Create new empty worker");
        Worker worker = new Worker();
        WorkerReadModel result = workerService.createWorker(worker);
        return ResponseEntity.created(URI.create("")).body(result);
    }
    */

    /**
     * Odczyt pracownika o danym loginie i haśle
     * @param workerWriteModel dane logowania pracownika
     * @return szukany pracownik lub informacja o jego braku
     */
    /*
    @GetMapping("/login")
    ResponseEntity<WorkerReadModel> login(@RequestBody WorkerWriteModelChangePassword workerWriteModel)
    {
        WorkerReadModel result;
        try
        {
            Worker login = workerWriteModel.toWorker();
            result = workerService.readByEmailAndPassword(login);

            return ResponseEntity.ok(result);
        }
        catch (LoginException e)
        {
            return ResponseEntity.notFound().build();
        }
    }
     */


    /**
     * Stworzenie nowego pracownika o pustych polach w czasie rejestracji
     * @return stworzony pracownik
     */
    /*
    @PostMapping("/register")
    ResponseEntity<?> registerWorker(@RequestBody WorkerWriteModelRegister workerWriteModel)
    {
        logger.info("Register new worker!");
        Worker worker = workerWriteModel.toWorker();
        WorkerReadModel result = null;
        try
        {
            result = workerService.createWorker(worker);
            logger.info("Register success!");
        }
        catch (RegisterException e)
        {
            logger.info("Register fail!");
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getErrorMessage());
        }
        return ResponseEntity.created(URI.create("/"+result.getId())).body(result);
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
     * Akutalizacja danych pracownika o zadanym id
     * @param id id aktualizowaneg pracownika
     * @param workerWriteModel nowe dane pracownika
     * @return informacja o pomyślnej bądź nieudanej aktualizacji
     */
    @PutMapping("/update/{id}")
    ResponseEntity<?> updateWorker(@PathVariable UUID id, @RequestBody WorkerWriteModel workerWriteModel)
    {
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
