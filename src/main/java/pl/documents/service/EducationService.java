package pl.documents.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
    public void updateEducation(UUID id, Education toUpdate)
    {
        if(!repository.existsById(id))
        {
            throw new EntityExistsException("Education with id "+id+" doesn't exists");
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
    public void checkData(Education education)
    {
        Pattern yearPattern = Pattern.compile("[12]\\d{3}");
        Matcher matcher = yearPattern.matcher(education.getGraduationYear());
        if(matcher.matches())
        {
            int year = Integer.parseInt(education.getGraduationYear());
            if(year > Year.now().getValue() || year < 1900)
            {
                throw new IllegalArgumentException("Podaj poprawny rok!");
            }
        }
        else
        {
            throw new IllegalArgumentException("Podaj poprawny rok!");
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
