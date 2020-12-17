package pl.documents.service;

import org.springframework.stereotype.Service;
import pl.documents.model.Education;
import pl.documents.model.Worker;
import pl.documents.repository.EducationRepository;

import javax.persistence.EntityExistsException;
import java.time.LocalDateTime;
import java.time.Year;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class EducationService
{
    private EducationRepository repository;

    public EducationService(final EducationRepository educationRepository)
    {
        this.repository = educationRepository;
    }
    public void updateEducation(int id, Education toUpdate)
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
    public boolean deleteById(int id)
    {
        if(!repository.existsById(id))
        {
            return false;
        }
        repository.deleteById(id);
        return true;
    }

}
