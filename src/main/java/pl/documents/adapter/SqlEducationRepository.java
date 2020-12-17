package pl.documents.adapter;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.documents.model.Education;
import pl.documents.model.Worker;
import pl.documents.repository.EducationRepository;

import java.util.List;

@Repository
public interface SqlEducationRepository extends EducationRepository, JpaRepository<Education,Integer>
{
    /**
     * Znajduje wszyskie szkoły danego pracownika
     * @param worker pracownik
     * @return lista szkłó pracownika
     */

    @Override
    List<Education> findAllByWorker(Worker worker);

}
