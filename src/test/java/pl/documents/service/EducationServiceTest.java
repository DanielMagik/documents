package pl.documents.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.documents.exception.BadEducationException;
import pl.documents.exception.BadIdException;
import pl.documents.model.Education;
import pl.documents.model.Worker;
import pl.documents.repository.EducationRepository;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class EducationServiceTest
{
    private EducationService service;
    @BeforeEach
    void init()
    {
        service=new EducationService(null);
    }
    /*
    @Test
    void updateEducationBadIdException()
    {
        var mockEducationRepo = mock(EducationRepository.class);
        when(mockEducationRepo.existsById(any(UUID.class))).thenReturn(false);
        EducationService service = new EducationService(mockEducationRepo);
        UUID id = UUID.randomUUID();
        var exception = catchThrowable(()->service.updateEducation(id,null));
        assertThat(exception).isInstanceOf(BadIdException.class).
                hasFieldOrPropertyWithValue("errorMessage", "Education with id "+id+" doesn't exists");

    }
    @Test
    void updateEducationSuccessfulUpdate()
    {

        EducationRepository educationRepository = inMemoryRepository;
        Education e1 = new Education("POLSL","2010");
        Education e2 = new Education("POLCZ", "2012");
        e1.setId(new UUID(0,1));
        e2.setId(new UUID(0,2));
        Education update = new Education("US", "2013");
        Education update2 = new Education("PPP", "1990");
        educationRepository.save(e1);
        educationRepository.save(e2);
        var service = new EducationService(educationRepository);
        try
        {
            service.updateEducation(new UUID(0,2),update);
            service.updateEducation(new UUID(0,1), update2);

        }
        catch (BadIdException e)
        {
            throw new RuntimeException(e);
        }
        Optional<Education> optionalEducation= educationRepository.findById(new UUID(0,2));
        Education education = optionalEducation.orElse(null);
        Optional<Education> optionalEducation2= educationRepository.findById(new UUID(0,1));
        Education education2 = optionalEducation2.orElse(null);
        assertEquals(education.getSchoolName(),"US");
        assertEquals(education.getGraduationYear(),"2013");
        assertEquals(education2.getSchoolName(),"PPP");
        assertEquals(education2.getGraduationYear(),"1990");
    }
    */

    @Test
    void checkEducationBadYear()
    {

        Education education = new Education("Asdf","aaa");
        Education education1 = new Education("1000","1000");
        Education education2 = new Education("aaa", String.valueOf(LocalDateTime.now().getYear()+1));
        var exception = catchThrowable(()->service.checkData(education));
        var exception1 = catchThrowable(()->service.checkData(education1));
        var exception2 = catchThrowable(()->service.checkData(education2));

        assertAll(

                ()->assertThat(exception).isInstanceOf(BadEducationException.class).
                hasFieldOrPropertyWithValue("errorMessage", "Enter correct year!"),
                ()->assertThat(exception1).isInstanceOf(BadEducationException.class).
                hasFieldOrPropertyWithValue("errorMessage", "Enter correct year!"),
                ()->assertThat(exception2).isInstanceOf(BadEducationException.class).
                hasFieldOrPropertyWithValue("errorMessage", "Enter correct year!")
        );

    }
    @Test
    void checkEducationNoException()
    {
        Education education = new Education("Asdf","2010");
        var exception = catchThrowable(()->service.checkData(education));
        assertThat(exception).doesNotThrowAnyException();

    }

    /*
    private EducationRepository inMemoryRepository = new EducationRepository()
    {
        int counter = 0;
        UUID index = new UUID(0,counter);
        Map<UUID, Education> map  = new HashMap<>();
        @Override
        public List<Education> findAllByWorker(Worker worker)
        {
            return map.values().stream().filter(e->e.getWorker().getId().equals(worker.getId()))
                    .collect(Collectors.toList());

        }

        @Override
        public Education save(Education entity)
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
        public Optional<Education> findById(UUID id)
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