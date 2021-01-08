package pl.documents.controller;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pl.documents.exception.BadIdException;
import pl.documents.exception.TemplateException;
import pl.documents.exception.TemplateExistsException;
import pl.documents.model.Template;
import pl.documents.service.TemplateService;

import java.util.List;
import java.util.UUID;

@RestController
public class TemplateController
{
    private final TemplateService service;

    public TemplateController(final TemplateService service)
    {
        this.service = service;
    }

    @GetMapping("/model")
    String get(Model model)
    {
        List<Template> templates = service.readAllFiles();
        model.addAttribute("templates",templates);
        return "templates";
    }
    @PostMapping("/uploadfiles")
    ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file,
                                           @RequestParam("obligatory") boolean obligatory)
    {
        Template template = null;
            try
            {
                template = service.saveFile(file,obligatory);
            }
            catch (TemplateException e)
            {
                service.deleteByNameInTemplate(template);
                return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getErrorMessage());
            }
            catch (TemplateExistsException e)
            {
                return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getErrorMessage());
            }

        return ResponseEntity.ok(template);
    }
    @GetMapping("/downloadfile/{id}")
    ResponseEntity<ByteArrayResource> downloadById(@PathVariable UUID id)
    {
        Template result = null;
        try
        {
            result = service.readById(id);
        }
        catch (BadIdException e)
        {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(result.getDocumentType()))
                .header(HttpHeaders.CONTENT_DISPOSITION,"attachment:filename=\""+result.getDocumentName()+"\"")
                .body(new ByteArrayResource(result.getData()));
    }
    @GetMapping("/downloadfile/{name}")
    ResponseEntity<ByteArrayResource> downloadByName(@PathVariable String name)
    {
        Template result = null;
        try
        {
            result = service.readByDocumentName(name);
        }
        catch (TemplateException e)
        {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(result.getDocumentType()))
                .header(HttpHeaders.CONTENT_DISPOSITION,"attachment:filename=\""+result.getDocumentName()+"\"")
                .body(new ByteArrayResource(result.getData()));
    }
    @DeleteMapping("/delete/{name}")
    ResponseEntity<?> deleteDocumentByName(@PathVariable String name)
    {
        if(service.deleteByName(name))
        {
            return ResponseEntity.ok().build();
        }
        else
        {
            return ResponseEntity.notFound().build();
        }
    }
}


