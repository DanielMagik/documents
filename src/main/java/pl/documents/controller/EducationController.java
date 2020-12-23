package pl.documents.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.documents.exception.BadEducationException;
import pl.documents.exception.BadIdException;
import pl.documents.model.Education;
import pl.documents.model.projection.EducationReadModel;
import pl.documents.service.EducationService;
import pl.documents.service.WorkerService;

import java.util.List;
import java.util.UUID;

@RestController
public class EducationController
{
    private final EducationService educationService;
    private final WorkerService workerService;
    private static final Logger logger = LoggerFactory.getLogger(EducationController.class);

    public EducationController(final EducationService educationService, final WorkerService workerService)
    {
        this.educationService = educationService;
        this.workerService = workerService;
    }

    /**
     * Odczyt przebiegu wykształcenia pracownika o zadanym id
     * @param id id pracownika
     * @return lista z przebiegiem wykształcenia
     */
    @GetMapping(value = "/workers/education/{id}")
    ResponseEntity<List<EducationReadModel>> readAllWorkerEducation(@PathVariable UUID id)
    {
        List<EducationReadModel> result;
        try
        {
            result = workerService.readWorkerEducation(id);
            logger.info("Read education from worker with id "+id+"!");
        }
        catch (BadIdException e)
        {
            logger.info("Read education from worker with id "+id+"! Worker not Found!");
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
    ResponseEntity<?> addWorkerEducation(@PathVariable UUID id, @RequestBody Education toUpdate)
    {
        try
        {
            educationService.checkData(toUpdate);
            workerService.addEducation(id, toUpdate);
            logger.info("Add education to worker with id " + id+ " successful!");
        }
        catch (BadEducationException e)
        {
            logger.info("Add education to worker with id " + id+ ".Bad education data!");
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getErrorMessage());
        }
        catch (BadIdException e)
        {
            logger.info("Add education to worker with id " + id+ ".Worker not found!");
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }

    /**
     * Aktualizacja encji z edukacją
     * @param id id aktualizowanej encji
     * @param toUpdate nowe dane o edukacji
     * @return informacja o pomyślnej bądź nieudanej aktualizacji
     */
    @PutMapping("/workers/education/{id}")
    ResponseEntity<?> updateEducation(@PathVariable UUID id, @RequestBody Education toUpdate)
    {
        try
        {
            educationService.checkData(toUpdate);
            educationService.updateEducation(id,toUpdate);
            logger.info("Update education with id " + id+ " successful!");
        }
        catch (BadIdException e)
        {
            logger.info("Update education with id " + id+ ".Education Not found!");
            return ResponseEntity.notFound().build();
        }
        catch (BadEducationException e)
        {
            logger.info("Update education with id " + id+ ".Bad education data!");
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getErrorMessage());
        }
        return ResponseEntity.noContent().build();
    }

    /**
     * Usunięcie edukacji o zadanym id
     * @param id id usuwanej edukacji
     * @return informacja o udanym bądź nieudanym usunięciu
     */
    @DeleteMapping("/workers/education/{id}")
    ResponseEntity<?> deleteEducation(@PathVariable UUID id)
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
