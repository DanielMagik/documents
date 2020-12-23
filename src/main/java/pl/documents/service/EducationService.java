package pl.documents.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.documents.exception.BadEducationException;
import pl.documents.exception.BadIdException;
import pl.documents.model.Education;
import pl.documents.repository.EducationRepository;

import javax.persistence.EntityExistsException;
import java.time.Year;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//TODO ZASTANOWIĆ SIĘ NAD @Scope
@Service
public class EducationService
{
    private final EducationRepository repository;

    public EducationService(final EducationRepository educationRepository)
    {
        this.repository = educationRepository;
    }

    /**
     * Zmiana Edukacji o id na tą przekazaną
     * @param id id zmienianej edukacji
     * @param toUpdate nowe wartości Edukacji
     */
    public void updateEducation(UUID id, Education toUpdate) throws BadIdException
    {
        if(!repository.existsById(id))
        {
            throw new BadIdException("Education with id "+id+" doesn't exists");
        }
        repository.findById(id).
                ifPresent(education ->{
                    education.updateForm(toUpdate);
                    repository.save(education);
                });
    }

    /**
     * Sprawdzenie poprawności danych, jeśli są niepoprawne, wyrzucany jest wyjątek
     * @param education dane do sprawdzenia
     */
    public void checkData(Education education) throws BadEducationException
    {
        Pattern yearPattern = Pattern.compile("[12]\\d{3}");
        Matcher matcher = yearPattern.matcher(education.getGraduationYear());
        if(matcher.matches())
        {
            int year = Integer.parseInt(education.getGraduationYear());
            if(year > Year.now().getValue() || year < 1900)
            {
                throw new BadEducationException("Enter correct year!");
            }
        }
        else
        {
            throw new IllegalArgumentException("Enter correct year!");
        }
    }

    /**
     * Usuwanie edukacji o danym id
     * @param id id usuwanej Edukacji
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
