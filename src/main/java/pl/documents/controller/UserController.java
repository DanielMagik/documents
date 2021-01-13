package pl.documents.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.documents.exception.RegisterException;
import pl.documents.model.Worker;
import pl.documents.model.projection.WorkerReadModel;
import pl.documents.model.projection.WorkerWriteModelRegister;

import java.net.URI;

@RestController
public class UserController
{
    @PostMapping("/register")
    ResponseEntity<?> registerWorker(@RequestBody WorkerWriteModelRegister workerWriteModel)
    {
        /*
        Worker worker = workerWriteModel.toWorker();
        WorkerReadModel result = null;
        try
        {
            result = workerService.createWorker(worker);

        }
        catch (RegisterException e)
        {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getErrorMessage());
        }
        return ResponseEntity.created(URI.create("/"+result.getId())).body(result);
    }

         */
        return null;
    }
}
