package pl.documents.service;

import io.lsn.spring.pesel.validator.domain.PeselValidator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.documents.logic.DataChecker;
import pl.documents.model.*;
import pl.documents.model.projection.EducationReadModel;
import pl.documents.model.projection.EmploymentReadModel;
import pl.documents.model.projection.FamilyMemberReadModel;
import pl.documents.model.projection.WorkerReadModel;
import pl.documents.repository.*;

import javax.persistence.EntityExistsException;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

//TODO ZASTANOWIĆ SIĘ NAD @Scope
@Service
public class WorkerService
{

    private final WorkerRepository repository;
    private final EducationRepository educationRepository;
    private final AddressRepository addressRepository;
    private final EmploymentRepository employmentRepository;
    private final FamilyMemberRepository familyMemberRepository;

    public WorkerService(final WorkerRepository repository, final EducationRepository educationRepository,
                         final AddressRepository addressRepository, final EmploymentRepository employmentRepository, final FamilyMemberRepository familyMemberRepository)
    {
        this.repository = repository;
        this.educationRepository = educationRepository;
        this.addressRepository = addressRepository;
        this.employmentRepository = employmentRepository;
        this.familyMemberRepository = familyMemberRepository;
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
    public WorkerReadModel createWorker(Worker source)
    {
        repository.save(source);
        //todo to niepotrzebne, tylko do testów
        source.setFirstName(String.valueOf(source.getId()));
        repository.save(source);
        return new WorkerReadModel(source);
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
     * Odczyt całej edukacji pracownika o zadanym id
     * @param id id pracownika
     * @return lista edukacji
     */
    public List<EducationReadModel> readWorkerEducation(UUID id)
    {
            Worker result = repository.findById(id).orElseThrow(
                    () -> new IllegalArgumentException("Bad ID!")
            );
            return educationRepository.findAllByWorker(result).stream()
                    .map(EducationReadModel::new).collect(Collectors.toList());
    }
    /**
     * Odczyt przebiegu zatrudnienia pracownika o zadanym id
     * @param id id pracownika
     * @return lista zatrudnień
     */
    public List<EmploymentReadModel> readWorkerEmployment(UUID id)
    {
        Worker result = repository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Bad ID!")
        );
        return employmentRepository.findAllByWorker(result).stream()
                .map(EmploymentReadModel::new).collect(Collectors.toList());
    }


    /**
     * Odczyt wszystkich adresów pracownika o zadanym id
     * @param id id pracownika
     * @return lista adresów
     */
    public List<Address> readWorkerAddresses(UUID id)
    {
        Worker result = repository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Bad ID!")
        );
        return addressRepository.findAllByWorker(result);

    }
    /**
     * Odczyt wszystkich członków rodziny o zadanym id
     * @param id id pracownika
     * @return lista członków rodziny
     */
    public List<FamilyMemberReadModel> readWorkerFamily(UUID id)
    {
        Worker result = repository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Bad ID!")
        );
        return familyMemberRepository.findAllByWorker(result).stream()
                .map(FamilyMemberReadModel::new).collect(Collectors.toList());
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
        if(!DataChecker.compareStrings(oldData.getDocumentNumber(),worker.getDocumentNumber()) || !DataChecker.compareStrings(oldData.getDocumentType(),worker.getDocumentType()))
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
        if(!DataChecker.compareStrings(worker.getNIP(),oldData.getNIP()))
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

    /**
     * Dodanie szkoły dla pracownika o danym id
     * @param id id pracownika, dla którego zostanie dodana szkoła
     * @param education nowa szkoła
     */
    public void addEducation(UUID id, Education education)
    {
        if(!repository.existsById(id))
        {
            throw new EntityExistsException("Worker with id "+id+" doesn't exists!");
        }
        repository.findById(id).ifPresent(
                worker ->
                {
                    education.setWorker(worker);
                    educationRepository.save(education);
                    //TODO TO JEST TYMCZASOWE, DO TESTÓW
                    education.setSchoolName(education.getSchoolName() + " id: " + education.getId());
                    educationRepository.save(education);
                }
        );
    }
    /**
     * Dodanie zatrudnienia dla pracownika o danym id
     * @param id id pracownika, dla którego zostanie dodana szkoła
     * @param employment nowe zatrudnienie
     */
    public void addEmployment(UUID id, Employment employment)
    {
        if(!repository.existsById(id))
        {
            throw new EntityExistsException("Worker with id "+id+" doesn't exists!");
        }
        repository.findById(id).ifPresent(
                worker ->
                {
                    employment.setWorker(worker);
                    employmentRepository.save(employment);
                    //TODO TO JEST TYMCZASOWE, DO TESTÓW
                    employment.setName(employment.getName() + " id: " + employment.getId());
                    employmentRepository.save(employment);
                }
        );
    }


    /**
     * Dodanie adresu dla pracownika o danym id
     * @param id id pracownika, dla którego zostanie dodany adres
     * @param address nowy adres
     */
    public void addAddress(UUID id, Address address) throws EntityExistsException
    {
        if(!repository.existsById(id))
        {
            throw new EntityExistsException("Worker with id "+id+" doesn't exists!");
        }
        repository.findById(id).ifPresent(
                worker ->
                {
                    address.setWorker(worker);
                    addressRepository.save(address);
                }
        );
    }
    /**
     * Dodanie członka rodziny dla pracownika o danym id
     * @param id id pracownika, dla którego zostanie dodany członek rodziny
     * @param familyMember nowy członek rodziny
     */
    public void addFamilyMember(UUID id, FamilyMember familyMember) throws EntityExistsException
    {
        if(!repository.existsById(id))
        {
            throw new EntityExistsException("Worker with id "+id+" doesn't exists!");
        }
        repository.findById(id).ifPresent(
                worker ->
                {
                    familyMember.setWorker(worker);
                    familyMemberRepository.save(familyMember);
                    familyMember.setName(familyMember.getName()+" id: "+familyMember.getId());
                    familyMemberRepository.save(familyMember);
                }
        );
    }



}
