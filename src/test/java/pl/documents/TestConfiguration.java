package pl.documents;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import pl.documents.logic.DataChecker;
import pl.documents.model.Worker;
import pl.documents.model.projection.WorkerReadModel;
import pl.documents.repository.TestWorkerRepository;
import pl.documents.repository.WorkerRepository;

import javax.sql.DataSource;
import java.util.*;



@Configuration
public class TestConfiguration
{

    @Bean
    @Primary
    @Profile("!integration")
    DataSource e2eTestDataSource()
    {
        var result = new DriverManagerDataSource("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1","sa","");
        result.setDriverClassName("org.h2.Driver");
        return result;
    }
    @Bean
    @Primary
    @Profile("integration")
    WorkerRepository testRepo()
    {
        return new TestWorkerRepository();
    }

}
