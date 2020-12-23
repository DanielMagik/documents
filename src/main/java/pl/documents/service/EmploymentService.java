package pl.documents.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.documents.exception.BadIdException;
import pl.documents.model.Employment;
import pl.documents.repository.EmploymentRepository;

import java.util.UUID;

//TODO ZASTANOWIĆ SIĘ NAD @Scope
@Service
public class EmploymentService
{
    private final EmploymentRepository repository;

    public EmploymentService(final EmploymentRepository repository)
    {
        this.repository = repository;
    }

    /**
     * Zmiana zatrudnienia o id na to przekazane
     * @param id id zmienianego zatrudnienia
     * @param toUpdate nowe wartości zatrudnienia
     */
    public void updateEmployment(UUID id, Employment toUpdate) throws BadIdException
    {
        if(!repository.existsById(id))
        {
            throw new BadIdException("Employment with id "+id+" doesn't exists");
        }
        repository.findById(id).
                ifPresent(employment ->{
                    employment.updateForm(toUpdate);
                    repository.save(employment);
                });
    }
    /**
     * Usuwanie zatrudnienia o danym id
     * @param id id usuwanego zatrudnienia
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
