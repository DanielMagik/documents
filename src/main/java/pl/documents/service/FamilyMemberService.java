package pl.documents.service;

import io.lsn.spring.pesel.validator.domain.PeselValidator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.documents.exception.BadAddressException;
import pl.documents.exception.BadFamilyMemberException;
import pl.documents.exception.BasicException;
import pl.documents.logic.DataChecker;
import pl.documents.model.Address;
import pl.documents.model.Education;
import pl.documents.model.FamilyMember;
import pl.documents.repository.FamilyMemberRepository;

import javax.persistence.EntityExistsException;
import java.time.Year;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class FamilyMemberService
{
    private final FamilyMemberRepository repository;

    public FamilyMemberService(final FamilyMemberRepository repository)
    {
        this.repository = repository;
    }
    /**
     * Zmiana członka rodziny o id na tego przekazanego
     * @param id id zmienianego członka
     * @param toUpdate nowe wartości członka
     */
    public void updateFamilyMember(UUID id, FamilyMember toUpdate)
    {
        if(!repository.existsById(id))
        {
            throw new EntityExistsException("Family member with id "+id+" doesn't exists");
        }
        repository.findById(id).
                ifPresent(familyMember ->{
                    familyMember.updateForm(toUpdate);
                    repository.save(familyMember);
                });
    }
    /**
     * Sprawdzenie poprawności danych, jeśli są niepoprawne, wyrzucany jest wyjątek
     * @param familyMember dane do sprawdzenia
     */
    public void checkData(FamilyMember familyMember) throws BadFamilyMemberException, BadAddressException
    {
        if(!PeselValidator.isValid(familyMember.getPESEL()))
        {
            BadFamilyMemberException e = new BadFamilyMemberException();
            e.setErrorMessage("Podaj poprawny PESEL członka rodziny!");
            throw e;
        }
        Address address = new Address();
        address.setPostalCode(familyMember.getPostCode());
        address.setDistrict(familyMember.getDistrict());
        address.setLocation(familyMember.getLocation());
        address.setCommunity(familyMember.getCommunity());
        address.setStreet(familyMember.getStreet());
        address.setHomeNumber(familyMember.getHomeNumber());
        address.setFlatNumber(familyMember.getFlatNumber());
        DataChecker.checkAddressCorrectness(address);
    }
    /**
     * Usuwanie członka rodziny o danym id
     * @param id id usuwanego członka rodziny
     * @return informacja, czy usunięcie zakończyło się sukcesem
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

}
