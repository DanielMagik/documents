package pl.documents.repository;

import pl.documents.model.Template;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TemplateRepository
{
    List<Template> findAll();
    Template save(Template entity);
    boolean existsById(UUID id);
    boolean existsByDocumentName(String documentName);
    Optional<Template> findById(UUID id);
    Optional<Template> findByDocumentName(String documentName);
    void deleteById(UUID id);
    void deleteByDocumentName(String documentName);


}
