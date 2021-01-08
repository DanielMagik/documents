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
    Optional<Template> findById(UUID id);
    void deleteById(UUID id);
    boolean existsByDocumentName(String documentName);
    void deleteByDocumentName(String documentName);
    Optional<Template> findByDocumentName(String documentName);
}
