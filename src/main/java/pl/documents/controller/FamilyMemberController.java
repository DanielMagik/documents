package pl.documents.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.documents.exception.BadAddressException;
import pl.documents.exception.BadFamilyMemberException;
import pl.documents.exception.BadIdException;
import pl.documents.model.Education;
import pl.documents.model.FamilyMember;
import pl.documents.model.projection.EducationReadModel;
import pl.documents.model.projection.FamilyMemberReadModel;
import pl.documents.service.FamilyMemberService;
import pl.documents.service.WorkerService;

import javax.persistence.EntityExistsException;
import java.util.List;
import java.util.UUID;

@RestController
public class FamilyMemberController
{
    private final FamilyMemberService familyMemberService;
    private final WorkerService workerService;
    private static final Logger logger = LoggerFactory.getLogger(EducationController.class);

    public FamilyMemberController(final FamilyMemberService familyMemberService,final WorkerService workerService)
    {
        this.familyMemberService = familyMemberService;
        this.workerService = workerService;
    }
    /**
     * Odczyt członków rodzint pracownika o zadanym id
     * @param id id pracownika
     * @return lista członków rodziny
     */
    @GetMapping(value = "/workers/family/{id}")
    ResponseEntity<List<FamilyMemberReadModel>> readAllWorkerFamily(@PathVariable UUID id)
    {
        List<FamilyMemberReadModel> result;
        try
        {
            result = workerService.readWorkerFamily(id);
            logger.info("Read family from worker with id "+id+"!");
        }
        catch (BadIdException e)
        {
            logger.info("Read family from worker with id "+id+"!Worker not Found!");
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }

    /**
     * Dodanie członka rodziny do pracownika o zadanym id
     * @param id id pracownika
     * @param toUpdate członek rodziny
     * @return informacja o pomyślnym bądź nieudanym dodaniu
     */
    @PostMapping("/workers/family/{id}")
    ResponseEntity<?> addWorkerFamilyMember(@PathVariable UUID id, @RequestBody FamilyMember toUpdate)
    {
        try
        {
            familyMemberService.checkData(toUpdate);
            workerService.addFamilyMember(id, toUpdate);
            logger.info("Add family member to worker with id " + id+ " successful!");
        }
        catch (BadAddressException | BadFamilyMemberException e)
        {
            logger.info("Add family member to worker with id " + id+ ".Bad data");
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getErrorMessage());
        }
        catch (BadIdException e)
        {
            logger.info("Add family member to worker with id "+id+"!Worker not Found!");
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }
    /**
     * Aktualizacja encji z członkiem rodziny
     * @param id id aktualizowanego członka rodziny
     * @param toUpdate nowe dane o członku rodziny
     * @return informacja o pomyślnej bądź nieudanej aktualizacji
     */
    @PutMapping("/workers/family/{id}")
    ResponseEntity<?> updateFamily(@PathVariable UUID id, @RequestBody FamilyMember toUpdate)
    {
        try
        {
            familyMemberService.checkData(toUpdate);
            familyMemberService.updateFamilyMember(id,toUpdate);
            logger.info("Update family member with id " + id+ " successful!");
        }
        catch (BadAddressException | BadFamilyMemberException e)
        {
            logger.info("Update family member with id " + id+ ".Bad data");
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getErrorMessage());
        }
        catch (BadIdException e)
        {
            logger.info("Update family member with id "+id+"!Family member not Found!");
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }

    /**
     * Usunięcie członka rodziny o zadanym id
     * @param id id usuwanego członka rodziny
     * @return informacja o udanym bądź nieudanym usunięciu
     */
    @DeleteMapping("/workers/family/{id}")
    ResponseEntity<?> deleteFamily(@PathVariable UUID id)
    {
        if(familyMemberService.deleteById(id))
        {
            logger.info("Delete family member with id " + id+ ".Delete successful!");
            return ResponseEntity.ok().build();
        }
        else
        {
            logger.info("Delete family member with id " + id+ ".Family member not found!");
            return ResponseEntity.notFound().build();
        }
    }
}
