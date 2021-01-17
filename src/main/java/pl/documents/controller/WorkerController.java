package pl.documents.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.documents.exception.*;
import pl.documents.model.User;
import pl.documents.model.projection.*;
import pl.documents.service.UserService;
import pl.documents.service.WorkerService;

import java.rmi.AccessException;
import java.util.UUID;

@RestController
public class WorkerController
{

    private static final Logger logger = LoggerFactory.getLogger(WorkerController.class);
    private final WorkerService workerService;
    private final UserService userService;

    public WorkerController(final WorkerService workerService, final UserService userService)
    {
        this.workerService = workerService;
        this.userService = userService;
    }

    /**
     * Odczyt listy pracowników
     * @return lista pracowników
     */

    @GetMapping("/worker")
    public ResponseEntity<?> ReadWorker(@RequestHeader("Authorization") String token)
    {
        WorkerReadModel workerReadModel;
        try
        {

            try
            {
                User user = userService.getByToken(token);
                workerReadModel = new WorkerReadModel(user.getWorker(), user);
            }
            catch (AccessException | IllegalArgumentException e)
            {
                return ResponseEntity.status(HttpStatus.CONFLICT).body("No access!");
            }

        }
        catch (NullPointerException e)
        {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Bad request!");
        }
        return ResponseEntity.ok(workerReadModel);
    }

    /**
     * Akutalizacja danych pracownika o zadanym id
     * @return informacja o pomyślnej bądź nieudanej aktualizacji
     */
    @PutMapping("/updatecandidate")
    ResponseEntity<?> updateWorkerCandidate(@RequestHeader("Authorization") String token, @RequestBody CandidateWriteModel candidateWriteModel)
    {
        User user;
        try
        {
            try
            {
                user = userService.getByToken(token);
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
        }
        catch (NullPointerException e)
        {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Incorrect data!");
        }
        return ResponseEntity.noContent().build();
    }
    @PutMapping("/updateworker")
    ResponseEntity<?> updateWorkerRest(@RequestHeader("Authorization") String token, @RequestBody WorkerWriteModelRest workerWriteModelRest)
    {
        User user;
        try
        {
            try
            {
                user = userService.getByToken(token);
            }
            catch (AccessException | IllegalArgumentException e)
            {
                return ResponseEntity.status(HttpStatus.CONFLICT).body("No access!");
            }
            try
            {
                workerService.checkWorkerRest(workerWriteModelRest, user.getWorker().getId());
            }
            catch (BadWorkerException | BadAddressException | BadFamilyMemberException e)
            {
                return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getErrorMessage());
            }
            workerService.updateWorkerRest(user.getWorker().getId(), workerWriteModelRest);
            return ResponseEntity.noContent().build();
        }
        catch (NullPointerException e)
        {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Incorrect data!");
        }
    }
}
