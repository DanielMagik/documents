package pl.documents.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.documents.exception.BadIdException;
import pl.documents.model.Employment;
import pl.documents.model.projection.EmploymentReadModel;
import pl.documents.service.EmploymentService;
import pl.documents.service.WorkerService;

import java.util.List;
import java.util.UUID;

@RestController
public class EmploymentController
{
    private final EmploymentService employmentService;
    private final WorkerService workerService;
    private static final Logger logger = LoggerFactory.getLogger(EmploymentController.class);

    public EmploymentController(final EmploymentService employmentService,final WorkerService workerService)
    {
        this.employmentService = employmentService;
        this.workerService = workerService;
    }

    /**
     * Odczyt przebiegu zatrudnienia pracownika o zadanym id
     * @param id id pracownika
     * @return lista z przebiegiem zatrudnienia
     */
    @GetMapping(value = "/workers/employment/{id}")
    ResponseEntity<List<EmploymentReadModel>> readAllWorkerEmployment(@PathVariable UUID id)
    {
        List<EmploymentReadModel> result;
        try
        {
            result = workerService.readWorkerEmployment(id);
            logger.info("Read employment from worker with id "+id+"!");
        }
        catch (BadIdException e)
        {
            logger.info("Read employment from worker with id "+id+".Worker not found! ");
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(result);
    }
    /**
     * Dodanie zatrudnienia do pracownika o zadanym id
     * @param id id pracownika
     * @param toUpdate zatrudnienie
     */
    @PostMapping("/workers/employment/{id}")
    ResponseEntity<?> addWorkerEmployment(@PathVariable UUID id, @RequestBody Employment toUpdate)
    {
        try
        {
            workerService.addEmployment(id, toUpdate);
            logger.info("Add employment to worker with id " + id+ " successful!");
        }
        catch (BadIdException e)
        {
            logger.info("Read employment from worker with id "+id+".Worker not found! ");
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }
    /**
     * Aktualizacja encji z zatrudnieniem
     * @param id id aktualizowanej encji
     * @param toUpdate nowe dane o zatrudnieniu
     * @return informacja o pomyślnej bądź nieudanej aktualizacji
     */
    @PutMapping("/workers/employment/{id}")
    ResponseEntity<?> updateEmployment(@PathVariable UUID id, @RequestBody Employment toUpdate)
    {
        try
        {
            employmentService.updateEmployment(id, toUpdate);
            logger.info("Update employment with id " + id+ " successful!");
        }
        catch (BadIdException e)
        {
            logger.info("Update employment with id "+id+".Employment not found!");
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }
    /**
     * Usunięcie zatrudnienia o zadanym id
     * @param id id usuwanego zatrudnienia
     * @return informacja o udanym bądź nieudanym usunięciu
     */
    @DeleteMapping("/workers/employment/{id}")
    ResponseEntity<?> deleteEmployment(@PathVariable UUID id)
    {
        if(employmentService.deleteById(id))
        {
            logger.info("Delete employment with id " + id+ ".Delete successful!");
            return ResponseEntity.ok().build();
        }
        else
        {
            logger.info("Delete employment with id " + id+ ".Employment not found!");
            return ResponseEntity.notFound().build();
        }
    }
}
