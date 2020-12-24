package pl.documents.service;

import io.lsn.spring.pesel.validator.domain.PeselValidator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.documents.exception.BadAddressException;
import pl.documents.exception.BadFamilyMemberException;
import pl.documents.exception.BadIdException;
import pl.documents.logic.DataChecker;
import pl.documents.model.Address;
import pl.documents.model.FamilyMember;
import pl.documents.model.enums.AddressType;
import pl.documents.repository.FamilyMemberRepository;

import java.util.UUID;

//TODO ZASTANOWIĆ SIĘ NAD @Scope
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
    public void updateFamilyMember(UUID id, FamilyMember toUpdate) throws BadIdException
    {
        if(!repository.existsById(id))
        {
            throw new BadIdException("Family member with id "+id+" doesn't exists");
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
           throw new BadFamilyMemberException("Bad PESEL of family member!");
        }
        else if(familyMember.getName().isBlank())
        {
            throw new BadFamilyMemberException("Bad name of family member!");
        }
        else if(familyMember.getSurname().isBlank())
        {
            throw new BadFamilyMemberException("Bad surname of family member!");
        }
        Address address = new Address(AddressType.ALL,familyMember.getPostCode(),familyMember.getLocation(),familyMember.getDistrict(),
                familyMember.getCommunity(),familyMember.getStreet(),familyMember.getHomeNumber(),familyMember.getFlatNumber());
        //tu może być wyrzucony wyjątek BadAddressException
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
