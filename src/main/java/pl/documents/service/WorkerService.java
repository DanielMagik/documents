package pl.documents.service;

import io.lsn.spring.pesel.validator.domain.PeselValidator;
import nl.garvelink.iban.Modulo97;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.documents.exception.BadIdException;
import pl.documents.exception.BadWorkerException;
import pl.documents.exception.LoginException;
import pl.documents.exception.RegisterException;
import pl.documents.logic.DataChecker;
import pl.documents.model.*;
import pl.documents.model.projection.EducationReadModel;
import pl.documents.model.projection.EmploymentReadModel;
import pl.documents.model.projection.FamilyMemberReadModel;
import pl.documents.model.projection.WorkerReadModel;
import pl.documents.repository.*;

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
    public WorkerReadModel readById(UUID id) throws BadIdException
    {
        Worker result;
            result = repository.findById(id).orElseThrow(
                    () -> new BadIdException("Worker with id "+id+" doesn't exists!")
            );
            return new WorkerReadModel(result);
    }
    public WorkerReadModel readByEmailAndPassword(Worker worker) throws LoginException
    {
        Worker result;
        result=repository.findByEmailAndPassword(worker.getEmail(),worker.getPassword()).orElseThrow(
                () -> new LoginException("Bad login or password!")
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
    public WorkerReadModel createWorker(Worker source) throws RegisterException
    {
        if(repository.existsByEmail(source.getEmail()))
            throw new RegisterException("In database already exists worker with e-mail: " + source.getEmail()+ " !");
        String password = source.getPassword();
        if(password.length()<8)
        {
            throw new RegisterException("Too short password. It must contain at least 8 characters!");
        }
        Pattern digit = Pattern.compile("^(?=.*[0-9]).{8,}$");
        Pattern smallLetter = Pattern.compile("^(?=.*[a-z]).{8,}$");
        Pattern bigLetter = Pattern.compile("^(?=.*[A-Z]).{8,}$");
        Pattern blank = Pattern.compile("^(?=\\S+$).{8,}$");
        Pattern special = Pattern.compile("^(?=.*[~`!@#$%^&*()_+=\\-|\\\\/?:;'\"{}\\[\\]]).{8,}$");
        Matcher matcher = digit.matcher(password);
        if(!matcher.matches())
        {
            throw new RegisterException("Password doesn't contains any digit!");
        }
        matcher = smallLetter.matcher(password);
        if(!matcher.matches())
        {
            throw new RegisterException("Password doesn't contains any small letter!");
        }
        matcher = bigLetter.matcher(password);
        if(!matcher.matches())
        {
            throw new RegisterException("Password doesn't contains any big letter!");
        }
        matcher = blank.matcher(password);
        if(!matcher.matches())
        {
            throw new RegisterException("Password contains whitespace characters!");
        }
        matcher = special.matcher(password);
        if(!matcher.matches())
        {
            throw new RegisterException("Password doesn't contains any special character!");
        }

        repository.save(source);
        return new WorkerReadModel(source);
    }

    /**
     * Zmiana danych pracownika o zadanym id
     * @param id id zmienianego pracownika
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
     * Sprawdzenie, czy pracownik o zadanym id istnieje w bazie oraz czy nowe dane pracownika są poprawne
     * @param id id pracownika, któremu zostaną zmienione dane
     * @param worker nowe dane pracownika
     */
    public void checkData(UUID id, Worker worker) throws BadWorkerException, BadIdException
    {

        Worker oldData = repository.findById(id).orElseThrow(
                ()-> new BadIdException("Worker with id "+id+" doesn't exists")
        );
        Pattern pattern;
        Matcher matcher;
        //email
        pattern = Pattern.compile("^[A-Za-z0-9+_.-]+@(.+)");
        matcher = pattern.matcher(worker.getEmail());
        if(!matcher.matches())
            throw new BadWorkerException("Bad e-mail!");
        //numer telefonu
        pattern = Pattern.compile("\\d{9,11}");
        matcher = pattern.matcher(worker.getPhoneNumber());
        if(!matcher.matches())
            throw new BadWorkerException("Bad phone number");
        //miejscowość wypełnienia
        if(isNotWord(worker.getFillLocation()))
            throw new BadWorkerException("Enter the place of filling!");
        //imię
        if(isNotWord(worker.getFirstName()))
            throw new BadWorkerException("Enter name!");
        //nazwisko
        if(isNotWord(worker.getSurname()))
            throw new BadWorkerException("Enter surname!");
        //numer PESEL/dokumentu

        //numer dokumentu będzie zmieniany
        //różne numery dokumentów lub typy dokumentów
        if(!DataChecker.compareStrings(oldData.getDocumentNumber(),worker.getDocumentNumber()) || !DataChecker.compareStrings(oldData.getDocumentType(),worker.getDocumentType()))
        {
            // w bazie istnieje pracownik, który podał ten sam numer dokumentu i typ dokumentu
            if(repository.existsByDocumentNumberAndDocumentType(worker.getDocumentNumber(),worker.getDocumentType()))
            {
                if(worker.getDocumentType()==null)//PESEL
                    throw new BadWorkerException("There is a person in the database with the same PESEL number!");
                else
                    throw new BadWorkerException("There is a person in the database with the same document number!");
            }
            //wprowadzono numer PESEL
            if(worker.getDocumentType()==null)
            {
                //PESEL jest niepoprawny
                if(!PeselValidator.isValid(worker.getDocumentNumber()))
                {
                    throw new BadWorkerException("Bad PESEL!");
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
                throw new BadWorkerException("Bad NIP!");
            }
        }
        //numer konta
        //wprowadzono nowy numer konta
        if(!DataChecker.compareStrings(worker.getAccountNumber(),oldData.getAccountNumber()))
        {
            try
            {
                boolean accountValid = Modulo97.verifyCheckDigits(worker.getAccountNumber());
                if(!accountValid)
                {
                    throw new BadWorkerException("Bad account number!");
                }
            }
            catch (IllegalArgumentException e)
            {
                throw new BadWorkerException("Bad account number format!");
            }

        }
    }

    /**
     * Odczyt całej edukacji pracownika o zadanym id
     * @param id id pracownika
     * @return lista edukacji
     */
    public List<EducationReadModel> readWorkerEducation(UUID id) throws BadIdException
    {
            Worker result = repository.findById(id).orElseThrow(
                    () -> new BadIdException("Worker with id "+id+" doesn't exists!")
            );
            return educationRepository.findAllByWorker(result).stream()
                    .map(EducationReadModel::new).collect(Collectors.toList());
    }
    /**
     * Odczyt przebiegu zatrudnienia pracownika o zadanym id
     * @param id id pracownika
     * @return lista zatrudnień
     */
    public List<EmploymentReadModel> readWorkerEmployment(UUID id) throws BadIdException
    {
        Worker result = repository.findById(id).orElseThrow(
                () -> new BadIdException("Worker with id "+id+" doesn't exists!")
        );
        return employmentRepository.findAllByWorker(result).stream()
                .map(EmploymentReadModel::new).collect(Collectors.toList());
    }


    /**
     * Odczyt wszystkich adresów pracownika o zadanym id
     * @param id id pracownika
     * @return lista adresów
     */
    public List<Address> readWorkerAddresses(UUID id) throws BadIdException
    {
        Worker result = repository.findById(id).orElseThrow(
                () -> new BadIdException("Worker with id "+id+" doesn't exists!")
        );
        return addressRepository.findAllByWorker(result);

    }
    /**
     * Odczyt wszystkich członków rodziny pracownika o zadanym id
     * @param id id pracownika
     * @return lista członków rodziny
     */
    public List<FamilyMemberReadModel> readWorkerFamily(UUID id) throws BadIdException
    {
        Worker result = repository.findById(id).orElseThrow(
                () -> new BadIdException("Worker with id "+id+" doesn't exists!")
        );
        return familyMemberRepository.findAllByWorker(result).stream()
                .map(FamilyMemberReadModel::new).collect(Collectors.toList());
    }

    /**
     * Sprawdzenie, czy przekazane słowo zawiera znaki inne niż białe
     * @param data przekazane słowo
     * @return informacja, czy data jest słowem
     */
    private boolean isNotWord(String data)
    {
        if(data==null)
            return true;
        if(data.isEmpty())
            return true;
        return data.isBlank();
    }

    /**
     * Sprawdzenie, czy przekazany string jest prawidłowym NIP-em
     * @param NIP nip
     * @return informacja zwrotna, true lub false
     */
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
    public void addEducation(UUID id, Education education) throws BadIdException
    {
        if(!repository.existsById(id))
        {
            throw new BadIdException("Worker with id "+id+" doesn't exists!");
        }
        repository.findById(id).ifPresent(
                worker ->
                {
                    education.setWorker(worker);
                    educationRepository.save(education);
                }
        );
    }
    /**
     * Dodanie zatrudnienia dla pracownika o danym id
     * @param id id pracownika, dla którego zostanie dodane zatrudnienie
     * @param employment nowe zatrudnienie
     */
    public void addEmployment(UUID id, Employment employment) throws BadIdException
    {
        if(!repository.existsById(id))
        {
            throw new BadIdException("Worker with id "+id+" doesn't exists!");
        }
        repository.findById(id).ifPresent(
                worker ->
                {
                    employment.setWorker(worker);
                    employmentRepository.save(employment);
                }
        );
    }


    /**
     * Dodanie adresu dla pracownika o danym id
     * @param id id pracownika, dla którego zostanie dodany adres
     * @param address nowy adres
     */
    public void addAddress(UUID id, Address address) throws BadIdException
    {
        if(!repository.existsById(id))
        {
            throw new BadIdException("Worker with id "+id+" doesn't exists!");
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
    public void addFamilyMember(UUID id, FamilyMember familyMember) throws BadIdException
    {
        if(!repository.existsById(id))
        {
            throw new BadIdException("Worker with id "+id+" doesn't exists!");
        }
        repository.findById(id).ifPresent(
                worker ->
                {
                    familyMember.setWorker(worker);
                    familyMemberRepository.save(familyMember);
                }
        );
    }



}
