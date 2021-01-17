package pl.documents.controller;

import org.apache.tomcat.util.http.fileupload.impl.FileSizeLimitExceededException;
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
    public class ObligatoryReadModel
    {
        boolean obligatory;

    }
    private final TemplateService service;

    public TemplateController(final TemplateService service)
    {
        this.service = service;
    }


    @PostMapping("/uploadfiles/{obligatory}")
    ResponseEntity<?> uploadFile(@RequestHeader("Authorization") String token, @RequestBody MultipartFile[] files, @PathVariable boolean obligatory)
    {
        Template template = null;
            try
            {
                for (MultipartFile f : files)
                {
                    template = service.saveFile(f, obligatory);
                }
            }
            catch (TemplateException e)
            {
                return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getErrorMessage());
            }

        return ResponseEntity.ok().body("Upload successful.");
    }

    @GetMapping("/downloadfile/{name}")
    ResponseEntity<ByteArrayResource> downloadByName(@RequestHeader("Authorization") String token, @PathVariable String name)
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
    @DeleteMapping("/deletefile/{name}")
    ResponseEntity<?> deleteDocumentByName(@RequestHeader("Authorization") String token, @PathVariable String name)
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
    @GetMapping("/downloadfilenames")
    public ResponseEntity<?> downloadNames(@RequestHeader("Authorization") String token)
    {
        List<String> result = service.getNames();
        return ResponseEntity.ok(result);
    }
    @GetMapping("/getobligatorynames/{obligatory}")
    public ResponseEntity<?> downloadAllFiles(@RequestHeader("Authorization") String token, @PathVariable boolean obligatory)
    {
        List<String> result = service.readAllObligatory(obligatory);
        return ResponseEntity.ok().body(result);
    }

}


