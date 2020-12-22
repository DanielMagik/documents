package pl.documents.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.documents.model.Education;
import pl.documents.model.Employment;
import pl.documents.repository.EmploymentRepository;

import javax.persistence.EntityExistsException;
import java.util.UUID;

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
    public void updateEmployment(UUID id, Employment toUpdate)
    {
        if(!repository.existsById(id))
        {
            throw new EntityExistsException("Education with id "+id+" doesn't exists");
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
