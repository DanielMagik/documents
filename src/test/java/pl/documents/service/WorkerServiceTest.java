package pl.documents.service;

import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pl.documents.exception.BadIdException;
import pl.documents.exception.BadWorkerException;
import pl.documents.exception.RegisterException;
import pl.documents.logic.DataChecker;
import pl.documents.model.Worker;
import pl.documents.repository.WorkerRepository;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

class WorkerServiceTest
{

    @Test
    void createWorkerBadEmail1()
    {
        Worker worker = new Worker();
        worker.setEmail("a@");
        WorkerService service = new WorkerService(null,null,null,null,null);
        var exception = catchThrowable(()->service.createWorker(worker));
        assertThat(exception).isInstanceOf(RegisterException.class).
                hasFieldOrPropertyWithValue("errorMessage", "Bad e-mail!");
    }
    @Test
    void createWorkerBadEmail2()
    {
        Worker worker = new Worker();
        worker.setEmail("@a");
        WorkerService service = new WorkerService(null,null,null,null,null);
        var exception = catchThrowable(()->service.createWorker(worker));
        assertThat(exception).isInstanceOf(RegisterException.class).
                hasFieldOrPropertyWithValue("errorMessage", "Bad e-mail!");
    }
    @Test
    void createWorkerEmailExists()
    {
        WorkerRepository repository = inMemoryRepository;
        Worker worker = new Worker();
        worker.setEmail("a@a.pl");
        worker.setId(UUID.randomUUID());
        repository.save(worker);
        Worker worker1 = new Worker();
        worker1.setEmail("a@a.pl");
        WorkerService service = new WorkerService(repository,null,null,null,null);
        var exception = catchThrowable(()->service.createWorker(worker1));
        assertThat(exception).isInstanceOf(RegisterException.class).
                hasFieldOrPropertyWithValue("errorMessage", "In database already exists worker with e-mail: "+worker1.getEmail()+" !");
    }
    @Test
    void createWorkerTooShortPassword()
    {
        WorkerRepository repository = inMemoryRepository;
        Worker worker1 = new Worker();
        worker1.setEmail("a@a.pl");
        worker1.setPassword("a");
        WorkerService service = new WorkerService(repository,null,null,null,null);
        var exception = catchThrowable(()->service.createWorker(worker1));
        assertThat(exception).isInstanceOf(RegisterException.class).
                hasFieldOrPropertyWithValue("errorMessage", "Too short password. It must contain at least 8 characters!");
    }
    @Test
    void createWorkerPasswordAnyDigit()
    {
        WorkerRepository repository = inMemoryRepository;
        Worker worker1 = new Worker();
        worker1.setEmail("a@a.pl");
        worker1.setPassword("aaaaaaaa");
        WorkerService service = new WorkerService(repository,null,null,null,null);
        var exception = catchThrowable(()->service.createWorker(worker1));
        assertThat(exception).isInstanceOf(RegisterException.class).
                hasFieldOrPropertyWithValue("errorMessage", "Password doesn't contains any digit!");
    }
    @Test
    void createWorkerPasswordAnyBigLetter()
    {
        WorkerRepository repository = inMemoryRepository;
        Worker worker1 = new Worker();
        worker1.setEmail("a@a.pl");
        worker1.setPassword("aaaaaaaa1");
        WorkerService service = new WorkerService(repository,null,null,null,null);
        var exception = catchThrowable(()->service.createWorker(worker1));
        assertThat(exception).isInstanceOf(RegisterException.class).
                hasFieldOrPropertyWithValue("errorMessage", "Password doesn't contains any big letter!");
    }
    @Test
    void createWorkerPasswordAnySmallLetter()
    {
        WorkerRepository repository = inMemoryRepository;
        Worker worker1 = new Worker();
        worker1.setEmail("a@a.pl");
        worker1.setPassword("AAAAAAAA1");
        WorkerService service = new WorkerService(repository,null,null,null,null);
        var exception = catchThrowable(()->service.createWorker(worker1));
        assertThat(exception).isInstanceOf(RegisterException.class).
                hasFieldOrPropertyWithValue("errorMessage", "Password doesn't contains any small letter!");
    }
    @Test
    void createWorkerPasswordAnySpecialCharacter()
    {
        WorkerRepository repository = inMemoryRepository;
        Worker worker1 = new Worker();
        worker1.setEmail("a@a.pl");
        worker1.setPassword("AAAAAAAAa1");
        WorkerService service = new WorkerService(repository,null,null,null,null);
        var exception = catchThrowable(()->service.createWorker(worker1));
        assertThat(exception).isInstanceOf(RegisterException.class).
                hasFieldOrPropertyWithValue("errorMessage", "Password doesn't contains any special character!");
    }
    @Test
    void createWorkerPasswordCorrect()
    {
        WorkerRepository repository = inMemoryRepository;
        Worker worker1 = new Worker();
        worker1.setEmail("a@a.pl");
        worker1.setPassword("AAAAAAAAa1\"");
        worker1.setId(UUID.randomUUID());
        WorkerService service = new WorkerService(repository,null,null,null,null);
        var exception = catchThrowable(()->service.createWorker(worker1));
        assertThat(exception).doesNotThrowAnyException();
    }
    @Test
    void checkDataBadID()
    {
        WorkerRepository repository = inMemoryRepository;
        Worker worker1 = new Worker();
        UUID id = UUID.randomUUID();
        worker1.setId(id);
        WorkerService service = new WorkerService(repository,null,null,null,null);
        var exception = catchThrowable(()->service.checkData(id, null));
        assertThat(exception).isInstanceOf(BadIdException.class).
            hasFieldOrPropertyWithValue("errorMessage", "Worker with id "+id+" doesn't exists");
    }
    @Test
    void checkDataBadPhoneNumber()
    {
        WorkerRepository repository = inMemoryRepository;
        Worker worker1 = new Worker();
        UUID id = UUID.randomUUID();
        worker1.setId(id);
        repository.save(worker1);
        WorkerService service = new WorkerService(repository,null,null,null,null);
        Worker update = new Worker();
        update.setPhoneNumber("12345");

        var exception = catchThrowable(()->service.checkData(id, update));
        assertThat(exception).isInstanceOf(BadWorkerException.class).
                hasFieldOrPropertyWithValue("errorMessage", "Bad phone number!");
    }
    @Test
    void checkDataNoPlaceOfFilling()
    {
        WorkerRepository repository = inMemoryRepository;
        Worker worker1 = new Worker();
        UUID id = UUID.randomUUID();
        worker1.setId(id);
        repository.save(worker1);
        WorkerService service = new WorkerService(repository,null,null,null,null);
        Worker update = new Worker();
        update.setPhoneNumber("123456778");
        update.setFillLocation(" ");
        var exception = catchThrowable(()->service.checkData(id, update));
        assertThat(exception).isInstanceOf(BadWorkerException.class).
                hasFieldOrPropertyWithValue("errorMessage", "Enter the place of filling!");
    }
    @Test
    void checkDataNoName()
    {
        WorkerRepository repository = inMemoryRepository;
        Worker worker1 = new Worker();
        UUID id = UUID.randomUUID();
        worker1.setId(id);
        repository.save(worker1);
        WorkerService service = new WorkerService(repository,null,null,null,null);
        Worker update = new Worker();
        update.setPhoneNumber("123456778");
        update.setFillLocation("a");
        update.setFirstName(" ");
        var exception = catchThrowable(()->service.checkData(id, update));
        assertThat(exception).isInstanceOf(BadWorkerException.class).
                hasFieldOrPropertyWithValue("errorMessage", "Enter name!");
    }
    @Test
    void checkDataNoSurname()
    {
        WorkerRepository repository = inMemoryRepository;
        Worker worker1 = new Worker();
        UUID id = UUID.randomUUID();
        worker1.setId(id);
        repository.save(worker1);
        WorkerService service = new WorkerService(repository,null,null,null,null);
        Worker update = new Worker();
        update.setPhoneNumber("123456778");
        update.setFillLocation("a");
        update.setFirstName("b");
        update.setSurname(" ");
        var exception = catchThrowable(()->service.checkData(id, update));
        assertThat(exception).isInstanceOf(BadWorkerException.class).
                hasFieldOrPropertyWithValue("errorMessage", "Enter surname!");
    }
    @Test
    void checkDataThatSamePESEL()
    {
        WorkerRepository repository = inMemoryRepository;
        Worker worker1 = new Worker();
        UUID id = new UUID(0,1);
        UUID id2 = new UUID(0,2);
        worker1.setId(id);
        worker1.setDocumentType(null);
        worker1.setDocumentNumber("96120526159");
        repository.save(worker1);
        Worker worker2 = new Worker();
        worker2.setDocumentNumber("53080343741");
        worker2.setDocumentType(null);
        worker2.setId(id2);
        repository.save(worker2);
        WorkerService service = new WorkerService(repository,null,null,null,null);
        Worker update = new Worker();
        update.setPhoneNumber("123456778");
        update.setFillLocation("a");
        update.setFirstName("b");
        update.setSurname("v");
        update.setDocumentType(null);
        update.setDocumentNumber("53080343741");
        var exception = catchThrowable(()->service.checkData(id, update));
        assertThat(exception).isInstanceOf(BadWorkerException.class).
                hasFieldOrPropertyWithValue("errorMessage", "There is a person in the database with the same PESEL number!");
    }
    @Test
    void checkDataThatSameDocumentNumber()
    {
        WorkerRepository repository = inMemoryRepository;
        Worker worker1 = new Worker();
        UUID id = new UUID(0,1);
        UUID id2 = new UUID(0,2);
        worker1.setId(id);
        worker1.setDocumentType("P");
        worker1.setDocumentNumber("96120526159");
        repository.save(worker1);
        Worker worker2 = new Worker();
        worker2.setDocumentNumber("53080343741");
        worker2.setDocumentType("P");
        worker2.setId(id2);
        repository.save(worker2);
        WorkerService service = new WorkerService(repository,null,null,null,null);
        Worker update = new Worker();
        update.setPhoneNumber("123456778");
        update.setFillLocation("a");
        update.setFirstName("b");
        update.setSurname("v");
        update.setDocumentType("P");
        update.setDocumentNumber("53080343741");
        var exception = catchThrowable(()->service.checkData(id, update));
        assertThat(exception).isInstanceOf(BadWorkerException.class).
                hasFieldOrPropertyWithValue("errorMessage", "There is a person in the database with the same document number!");
    }
    @Test
    void checkDataBadPESEL()
    {
        WorkerRepository repository = inMemoryRepository;
        Worker worker1 = new Worker();
        UUID id = UUID.randomUUID();
        worker1.setId(id);
        worker1.setDocumentType(null);
        worker1.setDocumentNumber("96120526159");
        repository.save(worker1);
        WorkerService service = new WorkerService(repository,null,null,null,null);
        Worker update = new Worker();
        update.setPhoneNumber("123456778");
        update.setFillLocation("a");
        update.setFirstName("b");
        update.setSurname("v");
        update.setDocumentType(null);
        update.setDocumentNumber("53080343841");
        var exception = catchThrowable(()->service.checkData(id, update));
        assertThat(exception).isInstanceOf(BadWorkerException.class).
                hasFieldOrPropertyWithValue("errorMessage", "Bad PESEL!");
    }
    @Test
    void checkDataBadNIP()
    {
        WorkerRepository repository = inMemoryRepository;
        Worker worker1 = new Worker();
        UUID id = UUID.randomUUID();
        worker1.setId(id);
        worker1.setDocumentType(null);
        worker1.setDocumentNumber("96120526159");
        repository.save(worker1);
        WorkerService service = new WorkerService(repository,null,null,null,null);
        Worker update = new Worker();
        update.setPhoneNumber("123456778");
        update.setFillLocation("a");
        update.setFirstName("b");
        update.setSurname("v");
        update.setDocumentType(null);
        update.setDocumentNumber("51102221691");
        update.setNIP("5281337122");
        var exception = catchThrowable(()->service.checkData(id, update));
        assertThat(exception).isInstanceOf(BadWorkerException.class).
                hasFieldOrPropertyWithValue("errorMessage", "Bad NIP!");
    }
    @Test
    void checkDataBadAccountNumberFormat()
    {
        WorkerRepository repository = inMemoryRepository;
        Worker worker1 = new Worker();
        UUID id = UUID.randomUUID();
        worker1.setId(id);
        worker1.setDocumentType(null);
        worker1.setDocumentNumber("96120526159");
        repository.save(worker1);
        WorkerService service = new WorkerService(repository,null,null,null,null);
        Worker update = new Worker();
        update.setPhoneNumber("123456778");
        update.setFillLocation("a");
        update.setFirstName("b");
        update.setSurname("v");
        update.setDocumentType(null);
        update.setDocumentNumber("51102221691");
        update.setAccountNumber("123");
        var exception = catchThrowable(()->service.checkData(id, update));
        assertThat(exception).isInstanceOf(BadWorkerException.class).
                hasFieldOrPropertyWithValue("errorMessage", "Bad account number format!");
    }
    @Test
    void checkDataBadAccountNumber()
    {
        WorkerRepository repository = inMemoryRepository;
        Worker worker1 = new Worker();
        UUID id = UUID.randomUUID();
        worker1.setId(id);
        worker1.setDocumentType(null);
        worker1.setDocumentNumber("96120526159");
        repository.save(worker1);
        WorkerService service = new WorkerService(repository,null,null,null,null);
        Worker update = new Worker();
        update.setPhoneNumber("123456778");
        update.setFillLocation("a");
        update.setFirstName("b");
        update.setSurname("v");
        update.setDocumentType(null);
        update.setDocumentNumber("51102221691");
        update.setAccountNumber("PL41249000050000460051166530");
        var exception = catchThrowable(()->service.checkData(id, update));
        assertThat(exception).isInstanceOf(BadWorkerException.class).
                hasFieldOrPropertyWithValue("errorMessage", "Bad account number!");
    }
    @Test
    void checkDataNoException()
    {
        WorkerRepository repository = inMemoryRepository;
        Worker worker1 = new Worker();
        UUID id = UUID.randomUUID();
        worker1.setId(id);
        worker1.setDocumentType(null);
        worker1.setDocumentNumber("96120526159");
        repository.save(worker1);
        WorkerService service = new WorkerService(repository,null,null,null,null);
        Worker update = new Worker();
        update.setPhoneNumber("123456778");
        update.setFillLocation("a");
        update.setFirstName("b");
        update.setSurname("v");
        update.setDocumentType(null);
        update.setDocumentNumber("51102221691");
        update.setNIP("6340194851");
        update.setAccountNumber("PL42 2490 0005 0000 4600 7549 3994");
        var exception = catchThrowable(()->service.checkData(id, update));
        assertThat(exception).doesNotThrowAnyException();
    }
    private WorkerRepository inMemoryRepository = new WorkerRepository()
    {
        int counter = 0;
        UUID index = new UUID(0,counter);
        Map<UUID, Worker> map  = new HashMap<>();
        @Override
        public List<Worker> findAll()
        {
            return new ArrayList<>(map.values());
        }

        @Override
        public Page<Worker> findAll(Pageable page)
        {
            return null;
        }

        @Override
        public Optional<Worker> findById(UUID id)
        {
            return Optional.ofNullable(map.get(id));
        }

        @Override
        public boolean existsById(UUID id)
        {
            return map.values().stream().anyMatch(e->e.getId().equals(id));
        }

        @Override
        public Worker save(Worker entity)
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
        public void deleteById(UUID id)
        {
            map.remove(id);
        }

        @Override
        public boolean existsByDocumentNumberAndDocumentType(String documentNumber, String documentType)
        {
            return map.values().stream().filter(worker -> worker.getDocumentNumber().equals(documentNumber))
                    .anyMatch(worker -> DataChecker.compareStrings(worker.getDocumentType(),documentType));
        }

        @Override
        public Optional<Worker> findByEmailAndPassword(String email, String password)
        {
            return map.values().stream().filter(worker -> worker.getEmail().equals(email)).filter(
                    worker -> worker.getPassword().equals(password)
            ).findAny();
        }

        @Override
        public boolean existsByEmail(String email)
        {
            return map.values().stream().anyMatch(worker -> worker.getEmail().equals(email));
        }
    };
}