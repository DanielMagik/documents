package pl.documents.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import pl.documents.exception.BadIdException;
import pl.documents.exception.TemplateException;
import pl.documents.exception.TemplateExistsException;
import pl.documents.model.Template;
import pl.documents.repository.TemplateRepository;
import java.util.List;

import java.io.IOException;
import java.util.UUID;

@Service
public class TemplateService
{
    private final TemplateRepository repository;

    public TemplateService(final TemplateRepository repository)
    {
        this.repository = repository;
    }

    public Template saveFile(MultipartFile file, boolean obligatory) throws TemplateException, TemplateExistsException
    {
        String docName = file.getOriginalFilename();
        if(repository.existsByDocumentName(docName))
            throw new TemplateExistsException("Template with name " +docName+" exists in database!");
        Template template = null;
        try
        {
            template = new Template(docName,file.getContentType(), file.getBytes(),obligatory);
        }
        catch (IOException e)
        {
            throw new TemplateException("Cannot upload templates!");
        }
        return repository.save(template);
    }
    public Template readById(UUID id) throws BadIdException
    {
        Template result;
        result = repository.findById(id).orElseThrow(
                () -> new BadIdException("Template with id "+id+" doesn't exists!")
        );
        return result;
    }
    public Template readByDocumentName(String documentName) throws TemplateException
    {
        Template result;
        result = repository.findByDocumentName(documentName).orElseThrow(
                () -> new TemplateException("Template with name "+documentName+" doesn't exists!")
        );
        return result;
    }
    public List<Template> readAllFiles()
    {
        return repository.findAll();
    }
    @Transactional
    public void deleteByNameInTemplate(Template result)
    {
        repository.deleteByDocumentName(result.getDocumentName());
    }
    @Transactional
    public boolean deleteByName(String name)
    {
        if(!repository.existsByDocumentName(name))
            return false;
        repository.deleteByDocumentName(name);
        return true;
    }
}
