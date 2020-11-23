package pl.documents.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import pl.documents.repository.CandidateRepository;

@RepositoryRestController
class CandidateController
{
    private static final Logger logger = LoggerFactory.getLogger(CandidateController.class);
    private final CandidateRepository repository;

    CandidateController(final CandidateRepository repository)
    {
        this.repository = repository;
    }
    @GetMapping("/candidates")
    ResponseEntity<?> readAllCandidates()
    {
        logger.warn("Read all candidates!");
        return ResponseEntity.ok(repository.findAll());
    }
}
