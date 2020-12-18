package pl.documents.service;

import io.lsn.spring.pesel.validator.domain.PeselValidator;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.documents.model.Education;
import pl.documents.model.Worker;
import pl.documents.model.projection.EducationReadModel;
import pl.documents.model.projection.WorkerReadModel;
import pl.documents.model.projection.WorkerWriteModel;
import pl.documents.repository.EducationRepository;
import pl.documents.repository.WorkerRepository;

import javax.persistence.EntityExistsException;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class WorkerService
{
    /**
     * Repozytorium dla tabeli WORKERS
     */
    private WorkerRepository repository;
    /**
     * Repozytorium dla tabeli EDUCATION
     */
    private EducationRepository educationRepository;

    public WorkerService(final WorkerRepository repository, final EducationRepository educationRepository)
    {
        this.repository = repository;
        this.educationRepository = educationRepository;
    }
    /**
     * Odczyt wszystkich pracowników
     * @return lista pracowników
     */
    public List<WorkerReadModel> readAllWorkers()
    {
        return repository.findAll().stream()
                .map(WorkerReadModel::new).collect(Collectors.toList());
    }

    /**
     * Odczyt pracownika o zadanym id
     * @param id zadane id
     * @return odczytany pracownik
     */
    public WorkerReadModel readById(UUID id)
    {
        Worker result;
            result = repository.findById(id).orElseThrow(
                    () -> new IllegalArgumentException("Bad ID!")
            );
            return new WorkerReadModel(result);
    }

    /**
     * Usunięcie pracownika o zadanym id
     * @param id id pracownika
     * @return informacja, czy udało się usunąć pracownika
     */
    @Transactional
    public boolean deleteById(UUID id)
    {
        if(!repository.existsById(id))
        {
            return false;
        }
        repository.deleteById(id);
        return true;
    }
    /**
     * Tworzenie nowego pracownika z pustymi polami i dodanie go do bazy danych
     * @param source pracownik do zapisu w bazie
     * @return zapisany pracownik
     */
    public WorkerReadModel createWorker(WorkerWriteModel source)
    {
        Worker result = source.toWorker();
        //todo to niepotrzebne, tylko do testów
        repository.save(result);
        result.setFirstName(String.valueOf(result.getId()));
        repository.save(result);
        return new WorkerReadModel(result);
    }

    /**
     * Odczyt wszystkich pracowników
     * @return lista pracowników
     */
    public List<WorkerReadModel> readAll()
    {
        return repository.findAll().stream()
                .map(WorkerReadModel::new)
                .collect(Collectors.toList());
    }

    /**
     * Zmiana danych pracownika o zadanym id
     * @param id id zmienianegp pracownika
     * @param toUpdate nowe dane pracownika
     */
    public void updateWorker(UUID id, Worker toUpdate)
    {
        //zmiana danych pracownika
        repository.findById(id).
                ifPresent(worker ->{
                    worker.updateFrom(toUpdate);
                    repository.save(worker);
                });
    }

    /**
     * Dodanie szkoły dla pracownika o danym id
     * @param id id pracownika, dla którego zostanie dodana szkoła
     * @param toUpdate nowa szkoła
     */
    public void addEducation(UUID id, Education toUpdate)
    {
        if(!repository.existsById(id))
        {
            throw new EntityExistsException("Worker with id "+id+" don't exists");
        }
        repository.findById(id).ifPresent(
                worker ->
                {
                    toUpdate.setWorker(worker);

                    educationRepository.save(toUpdate);
                    toUpdate.setSchoolName(toUpdate.getSchoolName() + " id: " + toUpdate.getId());
                    educationRepository.save(toUpdate);
                }
        );
    }
    public List<EducationReadModel> readWorkerEducation(UUID id)
    {
            Worker result = repository.findById(id).orElseThrow(
                    () -> new IllegalArgumentException("Bad ID!")
            );
            return educationRepository.findAllByWorker(result).stream()
                    .map(EducationReadModel::new).collect(Collectors.toList());
    }
    public void checkData(UUID id, Worker worker)
    {

        Worker oldData = repository.findById(id).orElseThrow(
                ()-> new EntityExistsException("Worker with id "+id+" doesn't exists")
        );
        Pattern pattern;
        Matcher matcher;
        //email
        pattern = Pattern.compile("^[A-Za-z0-9+_.-]+@(.+)");
        matcher = pattern.matcher(worker.getEmail());
        if(!matcher.matches())
            throw new IllegalArgumentException("Podaj poprawny e-mail!");
        //numer telefonu
        pattern = Pattern.compile("\\d{9,12}");
        matcher = pattern.matcher(worker.getPhoneNumber());
        if(!matcher.matches())
            throw new IllegalArgumentException("Podaj poprawny numer telefonu!");
        //miejscowość wypełnienia
        if(isNotWord(worker.getFillLocation()))
            throw new IllegalArgumentException("Wprowadź miejscowośc wypełnienia!");
        //imię
        if(isNotWord(worker.getFirstName()))
            throw new IllegalArgumentException("Wprowadź imię!");
        //nazwisko
        if(isNotWord(worker.getSurname()))
            throw new IllegalArgumentException("Wprowadź nazwisko!");
        //numer PESEL/dokumentu

        //numer dokumentu będzie zmieniany
        //różne numery dokumentów lub typy dokumentów
        if(!compareStrings(oldData.getDocumentNumber(),worker.getDocumentNumber()) || !compareStrings(oldData.getDocumentType(),worker.getDocumentType()))
        {
            // w bazie istnieje pracownik, który podał ten sam numer dokumentu i typ dokumentu
            if(repository.existsByDocumentNumberAndDocumentType(worker.getDocumentNumber(),worker.getDocumentType()))
            {
                if(worker.getDocumentType()==null)//PESEL
                    throw new IllegalArgumentException("W bazie istnieje osoba z tym samym numerem PESEL!");
                else
                    throw new IllegalArgumentException("W bazie istnieje osoba z tym samym numerem dokumentu!");
            }
            //wprowadzono numer PESEL
            if(worker.getDocumentType()==null)
            {
                //PESEL jest niepoprawny
                //TODO sprawdzić, czy faktycznie ta metoda sprawdza poprawność PESEL
                if(!PeselValidator.isValid(worker.getDocumentNumber()))
                {
                    throw new IllegalArgumentException("Podany PESEL jest niepoprawny!");
                }
            }
        }
        //NIP
        //wprowadzono nowy nip
        if(!compareStrings(worker.getNIP(),oldData.getNIP()))
        {
            //NIP jest niepoprawny
            if(!checkNIP(worker.getNIP()))
            {
                throw new IllegalArgumentException("Podany NIP jest niepoprawny!");
            }
        }
        //TODO napisać sprawdzanie numeru konta w banku



    }
    private boolean isNotWord(String data)
    {
        if(data==null)
            return true;
        if(data.isEmpty())
            return true;
        return data.isBlank();
    }
    private boolean checkNIP(String NIP)
    {
        Pattern pattern = Pattern.compile("\\d{10}");
        Matcher matcher = pattern.matcher(NIP);
        //NIP JEST CIĄGIEM CYFR
        if(matcher.matches())
        {
            int sum = Integer.parseInt(String.valueOf(NIP.charAt(0))) * 6+
                    Integer.parseInt(String.valueOf(NIP.charAt(1))) * 5+
            Integer.parseInt(String.valueOf(NIP.charAt(2))) * 7+
            Integer.parseInt(String.valueOf(NIP.charAt(3))) * 2+
            Integer.parseInt(String.valueOf(NIP.charAt(4))) * 3+
            Integer.parseInt(String.valueOf(NIP.charAt(5))) * 4+
            Integer.parseInt(String.valueOf(NIP.charAt(6))) * 5+
            Integer.parseInt(String.valueOf(NIP.charAt(7))) * 6+
            Integer.parseInt(String.valueOf(NIP.charAt(8))) * 7;
            sum= sum%11;
            return sum == Integer.parseInt(String.valueOf(NIP.charAt(9)));
        }
            return false;
    }
    private boolean compareStrings(String s1, String s2)
    {
        if(s1==null)
            return s2 == null;
        return s1.equals(s2);
    }

}
