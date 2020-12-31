package pl.documents.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import pl.documents.model.Worker;
import pl.documents.repository.WorkerRepository;

import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@ActiveProfiles("integration")
@AutoConfigureMockMvc
public class WorkerControllerIntegrationTest
{
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WorkerRepository repository;

    @Test
    void  httpGet_returnsGivenWorker() throws Exception
    {
        Worker worker = new Worker();
        worker.setId(UUID.randomUUID());
        UUID id = worker.getId();
        repository.save(worker);
        mockMvc.perform(get("/workers/"+id)).
                andExpect(status().is2xxSuccessful());
    }
}
