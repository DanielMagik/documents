package pl.documents.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.lsn.spring.pesel.validator.domain.PeselValidator;
import nl.garvelink.iban.Modulo97;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.documents.config.Encryption;
import pl.documents.config.TokenInstance;
import pl.documents.exception.BadIdException;
import pl.documents.exception.BadWorkerException;
import pl.documents.exception.LoginException;
import pl.documents.exception.RegisterException;
import pl.documents.logic.DataChecker;
import pl.documents.model.*;
import pl.documents.model.projection.*;
import pl.documents.repository.*;

import java.rmi.AccessException;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

//TODO ZASTANOWIĆ SIĘ NAD @Scope
@Service
public class WorkerService
{
    private final UserRepository userRepository;
    private final WorkerRepository workerRepository;
    private final EducationRepository educationRepository;
    private final AddressRepository addressRepository;
    private final EmploymentRepository employmentRepository;
    private final FamilyMemberRepository familyMemberRepository;
    private final Encryption encryption;

    public WorkerService(final UserRepository userRepository, final WorkerRepository workerRepository, final EducationRepository educationRepository,
                         final AddressRepository addressRepository, final EmploymentRepository employmentRepository,
                         final FamilyMemberRepository familyMemberRepository, Encryption encryption)
    {
        this.userRepository = userRepository;
        this.workerRepository = workerRepository;
        this.educationRepository = educationRepository;
        this.addressRepository = addressRepository;
        this.employmentRepository = employmentRepository;
        this.familyMemberRepository = familyMemberRepository;
        this.encryption = encryption;
    }

    /**
     * Odczyt wszystkich pracowników
     * @return lista pracowników
     */
    public List<WorkerReadModelForEmployee> readAllWorkers()
    {
        return workerRepository.findAll().stream()
                .map(WorkerReadModelForEmployee::new).collect(Collectors.toList());
    }

    /**
     * Usunięcie pracownika o zadanym id
     * @param id id pracownika
     * @return informacja, czy udało się usunąć pracownika
     */
    @Transactional
    public boolean deleteById(UUID id)
    {
        if(!workerRepository.existsById(id))
        {
            return false;
        }
        workerRepository.deleteById(id);
        return true;
    }

    /**
     * Tworzenie nowego pracownika z pustymi polami i dodanie go do bazy danych
     * @param source pracownik do zapisu w bazie
     * @return zapisany pracownik
     */
    public WorkerReadModel createWorker(Worker source) throws RegisterException
    {

        Pattern email = Pattern.compile("^[A-Za-z0-9+_.-]+@(.+)");
       // Matcher matcher = email.matcher(source.getEmail());
       // if(!matcher.matches())
        //{
       //     throw new RegisterException("Bad e-mail!");
       // }
        
        //if(workerRepository.existsByEmail(source.getEmail()))
        //    throw new RegisterException("In database already exists worker with e-mail: " + source.getEmail()+ " !");

        //might throw RegisterException
       // checkPassword(source.getPassword());

        workerRepository.save(source);
       // Worker result = workerRepository.findByEmailAndPassword(source.getEmail(),source.getPassword())
       // .orElseThrow(()->new RegisterException("Register fail!"));
        //return new WorkerReadModel(result);
        return null;
    }


    public void changeImportantData(UUID id, WorkerWriteModelChangePassword workerWriteModelChangePassword) throws  BadIdException, BadWorkerException
    {
        /*
        boolean willChangeEmail = workerWriteModelChangePassword.isWillChangeEmail();
        boolean willChangePassword = workerWriteModelChangePassword.isWillChangePassword();
        Worker source = workerWriteModelChangePassword.toWorker();

        Worker worker = workerRepository.findById(id).
                orElseThrow(() -> new BadIdException("Bad id!"));
        if(!willChangeEmail && !willChangePassword)
        {
            throw new BadWorkerException("Nothing to change!");
        }
        if(willChangeEmail)
        {
            if(worker.getEmail().equals(source.getEmail()))
            {
                throw new BadWorkerException("The new email is the same as the old one!");
            }
            Pattern email = Pattern.compile("^[A-Za-z0-9+_.-]+@(.+)");
            Matcher matcher = email.matcher(source.getEmail());
            if (!matcher.matches())
            {
                throw new BadWorkerException("Bad e-mail!");
            }

            if (workerRepository.existsByEmail(source.getEmail()))
                throw new BadWorkerException("In database already exists worker with e-mail: " + source.getEmail() + " !");
        }


        if(!worker.getPassword().equals(source.getPassword()))
        {
            throw new BadWorkerException("Bad old password!");
        }

        //might throw RegisterException
        if(willChangePassword)
        {
            try
            {
                checkPassword(workerWriteModelChangePassword.getNewPassword());
            }
            catch (RegisterException e)
            {
                BadWorkerException exception = new BadWorkerException(e.getErrorMessage());
                throw exception;
            }
        }
        //reset ewentualnego maila
        if(!willChangeEmail)
        {
            source.setEmail(worker.getEmail());
        }

        if(willChangePassword)
        {
            if(worker.getPassword().equals(workerWriteModelChangePassword.getNewPassword()))
            {
                throw new BadWorkerException("The new password is the same as the old one!");
            }
            source.setPassword(workerWriteModelChangePassword.getNewPassword());
        }
        workerRepository.findById(id).
                ifPresent(w ->{
                    w.updateImportantData(source);
                    workerRepository.save(w);
                });

         */
    }

    /**
     * Zmiana danych pracownika o zadanym id
     * @param id id zmienianego pracownika
     * @param toUpdate nowe dane pracownika
     */
    public void updateWorker(UUID id, Worker toUpdate) throws BadIdException
    {
        if(!workerRepository.existsById(id))
        {
            throw new BadIdException("Worker with id "+id+" doesn't exists");
        }
        workerRepository.findById(id).
                ifPresent(worker ->{
                    worker.updateFrom(toUpdate);
                    workerRepository.save(worker);
                });
    }

    /**
     * Sprawdzenie, czy pracownik o zadanym id istnieje w bazie oraz czy nowe dane pracownika są poprawne
     * @param id id pracownika, któremu zostaną zmienione dane
     * @param worker nowe dane pracownika
     */
    public void checkData(UUID id, Worker worker) throws BadWorkerException, BadIdException
    {

        Worker oldData = workerRepository.findById(id).orElseThrow(
                ()-> new BadIdException("Worker with id "+id+" doesn't exists")
        );
        Pattern pattern;
        Matcher matcher;
        //E-MAIL NIE MUSI BYĆ SPRAWDZANY, ZMIANA MAILA NASTĄPI W INNEJ FUNKCJI!
        //email
        //pattern = Pattern.compile("^[A-Za-z0-9+_.-]+@(.+)");
        //matcher = pattern.matcher(worker.getEmail());
        //if(!matcher.matches())
        //    throw new BadWorkerException("Bad e-mail!");
        //numer telefonu
        pattern = Pattern.compile("\\d{9,11}");
        matcher = pattern.matcher(worker.getPhoneNumber());
        if(!matcher.matches())
            throw new BadWorkerException("Bad phone number!");
        //miejscowość wypełnienia
        if(worker.getFillLocation().isBlank())
            throw new BadWorkerException("Enter the place of filling!");
        //imię
        if(worker.getFirstName().isBlank())
            throw new BadWorkerException("Enter name!");
        //nazwisko
        if(worker.getSurname().isBlank())
            throw new BadWorkerException("Enter surname!");
        //numer PESEL/dokumentu

        //numer dokumentu będzie zmieniany
        //różne numery dokumentów lub typy dokumentów
        if(!DataChecker.compareStrings(oldData.getDocumentNumber(),worker.getDocumentNumber()) || !DataChecker.compareStrings(oldData.getDocumentType(),worker.getDocumentType()))
        {
            // w bazie istnieje pracownik, który podał ten sam numer dokumentu i typ dokumentu
            if(workerRepository.existsByDocumentNumberAndDocumentType(worker.getDocumentNumber(),worker.getDocumentType()))
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
            Worker result = workerRepository.findById(id).orElseThrow(
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
        Worker result = workerRepository.findById(id).orElseThrow(
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
        Worker result = workerRepository.findById(id).orElseThrow(
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
        Worker result = workerRepository.findById(id).orElseThrow(
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
        if(!workerRepository.existsById(id))
        {
            throw new BadIdException("Worker with id "+id+" doesn't exists!");
        }
        workerRepository.findById(id).ifPresent(
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
        if(!workerRepository.existsById(id))
        {
            throw new BadIdException("Worker with id "+id+" doesn't exists!");
        }
        workerRepository.findById(id).ifPresent(
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
        if(!workerRepository.existsById(id))
        {
            throw new BadIdException("Worker with id "+id+" doesn't exists!");
        }
        workerRepository.findById(id).ifPresent(
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
        if(!workerRepository.existsById(id))
        {
            throw new BadIdException("Worker with id "+id+" doesn't exists!");
        }
        workerRepository.findById(id).ifPresent(
                worker ->
                {
                    familyMember.setWorker(worker);
                    familyMemberRepository.save(familyMember);
                }
        );
    }
    private void checkPassword(String password) throws RegisterException
    {
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
    }


    public WorkerReadModel getByToken(String token) throws AccessException, IllegalArgumentException
    {


        TokenInstance tokenInstance = new TokenInstance(token, encryption.getSequence());
        tokenInstance.readToken();
        String role = tokenInstance.getRole();
        String id = tokenInstance.getId();
        if(!role.equals("ROLE_WORKER"))
            throw new AccessException("No access!");

        User user = userRepository.findById(UUID.fromString(id))
                .orElseThrow(
                        ()->new AccessException("No access!")
                );

        WorkerReadModel workerReadModel = new WorkerReadModel(user.getWorker(), user);
        return workerReadModel;
    }
}
