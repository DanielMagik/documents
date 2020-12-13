package pl.documents.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.documents.model.Worker;
import pl.documents.repository.WorkerRepository;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
public class WorkerController
{
    private static final Logger logger = LoggerFactory.getLogger(WorkerController.class);
    private final WorkerRepository repository;

    public WorkerController( final WorkerRepository repository)
    {
        this.repository = repository;
    }
    @GetMapping(value = "/workers", params = {"!page","!size","!sort"})
    ResponseEntity<List<Worker>> readAllWorkers()
    {
        logger.info("Read all workers.");
        return ResponseEntity.ok(repository.findAll());
    }
    @GetMapping(value = "/workers")
    ResponseEntity<List<Worker>> readAllWorkers(Pageable page)
    {
        logger.info("Read all workers pageable.");
        return ResponseEntity.ok(repository.findAll(page).getContent());
    }
    @GetMapping("/workers/{id}")
    ResponseEntity<Worker> readWorker(@PathVariable int id)
    {
        return repository.findById(id).
                map(worker -> ResponseEntity.ok(worker)).
                orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/workers")
    ResponseEntity<Worker> createWorker()
    {
        logger.info("Create empty worker");
        Worker result = repository.save(new Worker());
        return ResponseEntity.created(URI.create("/" + result.getId())).body(result);
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
    @PutMapping("/workers/{id}")
    ResponseEntity<?> updateWorker(@PathVariable int id, @RequestBody @Valid Worker toUpdate)
    {
        if(!repository.existsById(id))
        {
            return ResponseEntity.notFound().build();
        }
        repository.findById(id).
                ifPresent(worker ->{
                    worker.updateFrom(toUpdate);
                    repository.save(worker);
                });
        return ResponseEntity.noContent().build();
    }
    @DeleteMapping("/workers/{id}")
    ResponseEntity<?> deleteCandidate(@PathVariable int id)
    {
        if(!repository.existsById(id))
        {
            return ResponseEntity.notFound().build();
        }
        repository.deleteById(id);
        return ResponseEntity.ok().build();
    }

}
