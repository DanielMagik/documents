package pl.documents.service;

import io.lsn.spring.pesel.validator.domain.PeselValidator;
import nl.garvelink.iban.Modulo97;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.documents.exception.*;
import pl.documents.logic.DataChecker;
import pl.documents.model.Address;
import pl.documents.model.Education;
import pl.documents.model.FamilyMember;
import pl.documents.model.Worker;
import pl.documents.model.projection.*;
import pl.documents.repository.AddressRepository;
import pl.documents.repository.EducationRepository;
import pl.documents.repository.FamilyMemberRepository;
import pl.documents.repository.WorkerRepository;

import java.time.Year;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//TODO ZASTANOWIĆ SIĘ NAD @Scope
@Service
public class WorkerService
{
    private final WorkerRepository workerRepository;
    private final EducationRepository educationRepository;
    private final AddressRepository addressRepository;
    private final FamilyMemberRepository familyMemberRepository;

    public WorkerService(final WorkerRepository workerRepository, final EducationRepository educationRepository,
                         final AddressRepository addressRepository,
                         final FamilyMemberRepository familyMemberRepository)
    {
        this.workerRepository = workerRepository;
        this.educationRepository = educationRepository;
        this.addressRepository = addressRepository;
        this.familyMemberRepository = familyMemberRepository;
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
    private void addEducation(UUID id, Education education)
    {
        workerRepository.findById(id).ifPresent(
                worker ->
                {
                    education.setWorker(worker);
                    educationRepository.save(education);
                }
        );
    }
    /**
     * Dodanie adresu dla pracownika o danym id
     * @param id id pracownika, dla którego zostanie dodany adres
     * @param address nowy adres
     */
    public void addAddress(UUID id, Address address)
    {
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
    public void addFamilyMember(UUID id, FamilyMember familyMember)
    {
        workerRepository.findById(id).ifPresent(
                worker ->
                {
                    familyMember.setWorker(worker);
                    familyMemberRepository.save(familyMember);
                }
        );
    }



    public void checkCandidate(CandidateWriteModel candidate) throws BadWorkerException, BadEducationException
    {
        Pattern pattern;
        Matcher matcher;
        //numer telefonu
        pattern = Pattern.compile("\\d{9,11}");
        matcher = pattern.matcher(candidate.getPhoneNumber());
        if(!matcher.matches())
            throw new BadWorkerException("Bad phone number!");
        //miejscowość wypełnienia
        if(candidate.getFillLocation().isBlank())
            throw new BadWorkerException("Enter the place of filling!");
        //imię
        if(candidate.getFirstName().isBlank())
            throw new BadWorkerException("Enter name!");
        //nazwisko
        if(candidate.getSurname().isBlank())
            throw new BadWorkerException("Enter surname!");

        for (EducationWriteModel e : candidate.getEducation())
        {
            checkEducationData(e.toEducation());
        }
    }

    public void checkEducationData(Education education) throws BadEducationException
    {
        if(education!=null)
        {
            Pattern yearPattern = Pattern.compile("[12]\\d{3}");
            Matcher matcher = yearPattern.matcher(education.getGraduationYear());
            if (matcher.matches())
            {
                int year = Integer.parseInt(education.getGraduationYear());
                if (year > Year.now().getValue() || year < 1900)
                {
                    throw new BadEducationException("Enter correct year!");
                }
            }
            else
            {
                throw new BadEducationException("Enter correct year!");
            }
        }
    }
    @Transactional
    public void updateCandidate(UUID id, CandidateWriteModel candidateWriteModel)
    {
        workerRepository.findById(id).
                ifPresent(worker ->{
                    worker.updateCandidate(candidateWriteModel);
                    workerRepository.save(worker);
                });
        educationRepository.deleteAllByWorkerId(id);
        for(EducationWriteModel e : candidateWriteModel.getEducation())
        {
            addEducation(id,e.toEducation());
        }
    }
    @Transactional
    public void updateWorkerRest(UUID id, WorkerWriteModelRest workerWriteModelRest)
    {
        workerRepository.findById(id).
                ifPresent(worker ->{
                    worker.updateWorkerRest(workerWriteModelRest);
                    workerRepository.save(worker);
                });
        familyMemberRepository.deleteAllByWorkerId(id);
        addressRepository.deleteAllByWorkerId(id);
        for(AddressWriteModel a : workerWriteModelRest.getAddresses())
        {
            addAddress(id,a.toAddress());
        }
        for(FamilyMemberWriteModel f : workerWriteModelRest.getFamilyMembers())
        {
            addFamilyMember(id,f.toFamilyMember());
        }
    }

    public void checkWorkerRest(WorkerWriteModelRest newWorker, UUID id) throws BadWorkerException, BadAddressException, BadFamilyMemberException
    {
        Worker oldWorker = workerRepository.findById(id).orElseThrow(
                ()->new BadWorkerException("Cannot update worker!")
        );
        Pattern pattern;
        Matcher matcher;
        //numer PESEL/dokumentu
        //numer dokumentu będzie zmieniany
        //różne numery dokumentów lub typy dokumentów
        if(!DataChecker.compareStrings(oldWorker.getDocumentNumber(),newWorker.getDocumentNumber()) || !DataChecker.compareStrings(oldWorker.getDocumentType(),newWorker.getDocumentType()))
        {
            // w bazie istnieje pracownik, który podał ten sam numer dokumentu i typ dokumentu
            if(workerRepository.existsByDocumentNumberAndDocumentType(newWorker.getDocumentNumber(),newWorker.getDocumentType()))
            {
                if(newWorker.getDocumentType()==null)//PESEL
                    throw new BadWorkerException("There is a person in the database with the same PESEL number!");
                else
                    throw new BadWorkerException("There is a person in the database with the same document number!");
            }
            //wprowadzono numer PESEL
            if(newWorker.getDocumentType()==null || newWorker.getDocumentType().isBlank()
                    || newWorker.getDocumentType().toUpperCase().equals("PESEL"))
            {
                //PESEL jest niepoprawny
                if(!PeselValidator.isValid(newWorker.getDocumentNumber()))
                {
                    throw new BadWorkerException("Bad PESEL!");
                }
            }
        }
        //NIP
        //wprowadzono nowy nip
        if(!DataChecker.compareStrings(newWorker.getNip(), oldWorker.getNIP()) &&
                newWorker.getNip()!=null && !newWorker.getNip().isBlank())
        {
            //NIP jest niepoprawny
            if(!checkNIP(newWorker.getNip()))
            {
                throw new BadWorkerException("Bad NIP!");
            }
        }
        //numer konta
        //wprowadzono nowy numer konta
        if(!DataChecker.compareStrings(newWorker.getAccountNumber(),oldWorker.getAccountNumber()))
        {
            try
            {
                boolean accountValid = Modulo97.verifyCheckDigits(newWorker.getAccountNumber());
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
        for (AddressWriteModel a : newWorker.getAddresses())
        {
            checkAddress(a.toAddress());
        }
        for(FamilyMemberWriteModel f : newWorker.getFamilyMembers())
        {
            checkFamilyMember(f.toFamilyMember());
        }
    }

    private void checkFamilyMember(FamilyMember familyMember) throws BadFamilyMemberException
    {
        if(familyMember.getName().isBlank())
        {
            throw new BadFamilyMemberException("Bad name of family member!");
        }
        else if(familyMember.getSurname().isBlank())
        {
            throw new BadFamilyMemberException("Bad surname of family member!");
        }
        if(familyMember.getPESEL()!=null && !familyMember.getPESEL().isBlank())
        {
            if(!PeselValidator.isValid(familyMember.getPESEL()))
            {
                throw new BadFamilyMemberException("Bad PESEL for a family member "+
                        familyMember.getName()+" "+familyMember.getSurname()+"!");
            }
        }
    }

    private void checkAddress(Address address) throws BadAddressException
    {
        Pattern pattern;
        Matcher matcher;
        pattern = Pattern.compile("\\d{2}-\\d{3}");
        matcher = pattern.matcher(address.getPostalCode());
        if(!matcher.matches())
        {
            throw new BadAddressException("Enter a correct postal code!");
        }
        pattern=Pattern.compile("\\d*\\w{0,1}");
        matcher=pattern.matcher(address.getHomeNumber());
        if(!matcher.matches())
        {
            throw  new BadAddressException("Enter a correct home number!");
        }
        if(!address.getFlatNumber().isBlank())
        {
            matcher = pattern.matcher(address.getFlatNumber());
            if(!matcher.matches())
            {
                throw new BadAddressException("Enter a correct flat number!");
            }
        }
    }


}
