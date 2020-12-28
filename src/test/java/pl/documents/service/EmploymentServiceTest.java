package pl.documents.service;

import org.junit.jupiter.api.Test;
import pl.documents.exception.BadIdException;
import pl.documents.model.Employment;
import pl.documents.model.Worker;
import pl.documents.repository.EmploymentRepository;

import java.time.YearMonth;
import java.util.*;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class EmploymentServiceTest
{
/*
    @Test
    void updateEmploymentBadIdException()
    {
        var mockEmploymentRepo = mock(EmploymentRepository.class);
        when(mockEmploymentRepo.existsById(any(UUID.class))).thenReturn(false);
        EmploymentService service = new EmploymentService(mockEmploymentRepo);
        UUID id = UUID.randomUUID();
        var exception = catchThrowable(()->service.updateEmployment(id,null));
        assertThat(exception).isInstanceOf(BadIdException.class).
                hasFieldOrPropertyWithValue("errorMessage", "Employment with id "+id+" doesn't exists");

    }
    @Test
    void updateEmploymentSuccessfulUpdate()
    {

        EmploymentRepository employmentRepository = inMemoryRepository;

        Employment e1 = new Employment(YearMonth.of(2012,5),YearMonth.of(2012,6),"A","b");
        Employment e2 = new Employment(YearMonth.of(2013,6),YearMonth.of(2013,7),"C","d");
        e1.setId(new UUID(0,1));
        e2.setId(new UUID(0,2));

        Employment update = new Employment(YearMonth.of(2014,7),YearMonth.of(2014,8),"AAA","b b");
        Employment update2 = new Employment(YearMonth.of(2015,8),YearMonth.of(2015,9),"BBB","a a");
        employmentRepository.save(e1);
        employmentRepository.save(e2);
        var service = new EmploymentService(employmentRepository);
        try
        {
            service.updateEmployment(new UUID(0,1),update);
            service.updateEmployment(new UUID(0,2), update2);

        }
        catch (BadIdException e)
        {
            throw new RuntimeException(e);
        }
        Optional<Employment> optionalEmployment= employmentRepository.findById(new UUID(0,1));
        Employment employment = optionalEmployment.orElse(null);
        Optional<Employment> optionalEmployment1= employmentRepository.findById(new UUID(0,2));
        Employment employment1 = optionalEmployment1.orElse(null);
        assertEquals(employment.getStart(), update.getStart());
        assertEquals(employment.getFinish(), update.getFinish());
        assertEquals(employment.getWorkplace(), update.getWorkplace());
        assertEquals(employment.getName(), update.getName());

        assertEquals(employment1.getStart(), update2.getStart());
        assertEquals(employment1.getFinish(), update2.getFinish());
        assertEquals(employment1.getWorkplace(), update2.getWorkplace());
        assertEquals(employment1.getName(), update2.getName());

    }

        private EmploymentRepository inMemoryRepository = new EmploymentRepository()
    {
        int counter = 0;
        UUID index = new UUID(0,counter);
        Map<UUID, Employment> map  = new HashMap<>();
        @Override
        public List<Employment> findAllByWorker(Worker worker)
        {
            return map.values().stream().filter(e->e.getWorker().getId().equals(worker.getId()))
                    .collect(Collectors.toList());
        }

        @Override
        public Employment save(Employment entity)
        {
            if(entity.getId().equals(new UUID(0,0)))
            {
                counter++;
                index = new UUID(0,counter);
                entity.setId(index);
            }
            map.put(entity.getId(),entity);
            return entity;
        }

        @Override
        public boolean existsById(UUID id)
        {
            return map.values().stream().anyMatch(e->e.getId().equals(id));
        }

        @Override
        public Optional<Employment> findById(UUID id)
        {
            return Optional.ofNullable(map.get(id));
        }

        @Override
        public void deleteById(UUID id)
        {
            map.remove(id);
        }
    };
 */
}