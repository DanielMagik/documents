package pl.documents.controller;

import org.springframework.web.bind.annotation.*;

//@RestController
class CandidateController
{
    /*
    private static final Logger logger = LoggerFactory.getLogger(CandidateController.class);
    private final CandidateRepository repository;

    CandidateController(final CandidateRepository repository)
    {
        this.repository = repository;
    }
    @GetMapping(value = "/candidates", params = {"!page","!size","!sort"})
    ResponseEntity<List<Candidate>> readAllCandidates()
    {
        logger.info("Read all candidates.");
        return ResponseEntity.ok(repository.findAll());
    }
    @GetMapping(value = "/candidates")
    ResponseEntity<List<Candidate>> readAllCandidates(Pageable page)
    {
        logger.info("Read all candidates pageable.");
        return ResponseEntity.ok(repository.findAll(page).getContent());
    }
    @GetMapping("/candidates/{id}")
    ResponseEntity<Candidate> readCandidate(@PathVariable int id)
    {
        return repository.findById(id).
                map(candidate -> ResponseEntity.ok(candidate)).
                orElse(ResponseEntity.notFound().build());
    }
    /*
    @PostMapping("/candidates")
    ResponseEntity<Candidate> createTask(@RequestBody @Valid Candidate toCreate)
    {

        Candidate result = repository.save(toCreate);
        return ResponseEntity.created(URI.create("/" + result.getId())).body(result);
    }
    */
    /*
    @PostMapping("/candidates")
    ResponseEntity<Candidate> createCandidate()
    {
        logger.info("Create empty candidate and worker");
        Candidate c = new Candidate();
        Candidate result = repository.save(c);
        return ResponseEntity.created(URI.create("/" + result.getId())).body(result);
    }
    @PutMapping("/candidates/{id}")
    ResponseEntity<?> updateCandidate(@PathVariable int id, @RequestBody @Valid Candidate toUpdate)
    {
        if(!repository.existsById(id))
        {
            return ResponseEntity.notFound().build();
        }
        repository.findById(id).
                ifPresent(candidate ->{
                    candidate.updateFrom(toUpdate);
                    repository.save(candidate);
                });
        return ResponseEntity.noContent().build();
    }
    @DeleteMapping("/candidates/{id}")
    ResponseEntity<?> deleteCandidate(@PathVariable int id)
    {
        if(!repository.existsById(id))
        {
            return ResponseEntity.notFound().build();
        }
        repository.deleteById(id);
        return ResponseEntity.ok().build();
    }
     */
}
