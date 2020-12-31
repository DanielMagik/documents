package pl.documents.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import pl.documents.model.Worker;
import pl.documents.model.projection.WorkerReadModel;
import pl.documents.repository.WorkerRepository;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class WorkerControllerE2ETest
{
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    WorkerRepository repository;
    @Test
    void httpGet_returnsAllWorkerEducation()
    {
        Worker worker = new Worker();
        Worker worker1 = new Worker();
        worker.setFirstName("Adam");
        repository.save(worker);
        repository.save(worker1);
        WorkerReadModel[] result = restTemplate.getForObject("http://localhost:"+port+"/workers", WorkerReadModel[].class);
        assertThat(result).hasSize(2);
        assertEquals(result[0].getFirstName(),"Adam");
    }
}