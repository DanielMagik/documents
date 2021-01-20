package pl.documents.adapter;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.documents.model.Template;
import pl.documents.repository.TemplateRepository;

import java.util.UUID;

@Repository
interface SqlTemplateRepository extends TemplateRepository, JpaRepository<Template, UUID>
{

}
