package pl.documents.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.documents.model.Education;
import pl.documents.model.Worker;
import pl.documents.model.projection.EducationReadModel;
import pl.documents.model.projection.WorkerReadModel;
import pl.documents.model.projection.WorkerWriteModel;
import pl.documents.service.EducationService;
import pl.documents.service.WorkerService;

import javax.persistence.EntityExistsException;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
public class WorkerController
{
    private static final Logger logger = LoggerFactory.getLogger(WorkerController.class);
    private final WorkerService workerService;
    private final EducationService educationService;
    public WorkerController(final WorkerService workerService, final EducationService educationService)
    {
        this.workerService = workerService;
        this.educationService = educationService;
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
    ResponseEntity<WorkerReadModel> readWorker(@PathVariable int id)
    {

        /*
        Optional<Worker> result = repository.findById(id);
        if(result.isPresent())
        {
            WorkerReadModel workerReadModel = result.map(WorkerReadModel::new)
                    .orElse(new WorkerReadModel(new Worker()));
        }
         */
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

        //return repository.findById(id).
        //       map(worker -> ResponseEntity.ok(worker)).
        //        orElse(ResponseEntity.notFound().build());
    }

    /**
     * Stworzenie nowego pracownika o pustuch polach
     * @return stworzony pracownik
     */
    @PostMapping("/workers")
    ResponseEntity<WorkerReadModel> createWorker()
    {
        logger.info("Create new empty worker");
        WorkerWriteModel workerWriteModel = new WorkerWriteModel();
        workerService.createWorker(workerWriteModel);
        WorkerReadModel result = new WorkerReadModel(workerWriteModel.toWorker());
        return ResponseEntity.created(URI.create("")).body(result);
    }
    /*
    @PostMapping("/workers")
    ResponseEntity<Worker> createWorker1()
    {
        logger.info("Create empty worker");
        Worker result = repository.save(new Worker());
        return ResponseEntity.created(URI.create("/" + result.getId())).body(result);
    }
    */

    /**
     * Akutalizacja danych pracownika o zadanym id
     * @param id id aktualizowaneg pracownika
     * @param toUpdate nowe dane pracownika
     * @return informacja o pomyślnej bądź nieudanej aktualizacji
     */
    @PutMapping("/workers/{id}")
    ResponseEntity<?> updateWorker(@PathVariable int id, @RequestBody Worker toUpdate)
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
    ResponseEntity<?> deleteCandidate(@PathVariable int id)
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

    /**
     * Odczyt przebiegu wykształcenia pracownika o zadanym id
     * @param id id wykształcenia
     * @return lista z przebiegiem wykształcenia
     */
    @GetMapping(value = "/workers/education/{id}")
    ResponseEntity<List<EducationReadModel>> readAllWorkerEducation(@PathVariable int id)
    {
        List<EducationReadModel> result;
        try
        {
            result = workerService.readWorkerEducation(id);
            logger.info("Read education from worker with id "+id+"!");
        }
        catch (IllegalArgumentException e)
        {
            logger.info("Read education from worker with id "+id+"!Worker not Found!");
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }
    /**
     * Dodanie edukacji do pracownika o zadanym id
     * @param id id pracownika
     * @param toUpdate edukacja
     * @return informacja o pomyślnym bądź nieudanym dodaniu
     */
    @PostMapping("/workers/education/{id}")
    ResponseEntity<?> addWorkerEducation(@PathVariable int id, @RequestBody @Valid Education toUpdate)
    {
        try
        {
            educationService.checkData(toUpdate);
            workerService.addEducation(id, toUpdate);
            logger.info("Add education to worker with id " + id+ " successful!");
        }
        catch (EntityExistsException e)
        {
            logger.info("Add education to worker with id " + id+ ".Worker not found!");
            return ResponseEntity.notFound().build();
        }
        //TODO ZŁAP WYJĄTEK IllegalArgumentException, złe dane wejściowe
        return ResponseEntity.noContent().build();
    }

    /**
     * Aktualizacja encji z edukacją
     * @param id id aktualizowanej encji
     * @param toUpdate nowe dane o edukacji
     * @return informacja o pomyślnej bądź nieudanej aktualizacji
     */
    @PutMapping("/workers/education/{id}")
    ResponseEntity<?> updateEducation(@PathVariable int id, @RequestBody Education toUpdate)
    {
        try
        {
            educationService.checkData(toUpdate);
            educationService.updateEducation(id,toUpdate);
            logger.info("Update education with id " + id+ " successful!");
        }
        catch (EntityExistsException e)
        {
            logger.info("Update education with id " + id+ ".Worker Not found!");
            return ResponseEntity.notFound().build();
        }
        //TODO ZŁAP WYJĄTEK IllegalArgumentException, złe dane wejściowe
        return ResponseEntity.noContent().build();
    }

    /**
     * Usunięcie edukacji o zadanym id
     * @param id id usuwanej edukacji
     * @return informacja o udanym bądź nieudanym usunięciu
     */
    @DeleteMapping("/workers/education/{id}")
    ResponseEntity<?> deleteEducation(@PathVariable int id)
    {
        if(educationService.deleteById(id))
        {
            logger.info("Delete education with id " + id+ ".Delete successful!");
            return ResponseEntity.ok().build();
        }
        else
        {
            logger.info("Delete education with id " + id+ ".Education not found!");
            return ResponseEntity.notFound().build();
        }
    }


}
