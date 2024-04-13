package eurofarma.com.br.eurofarmachat.controllers;


import eurofarma.com.br.eurofarmachat.services.DocumentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(value={"/documents"})
public class DocumentsController {

    @Autowired
    private DocumentsService documentsService;

    @PostMapping
    public ResponseEntity<String> uploadDocs(@RequestParam("file") MultipartFile file) {

        if(file.isEmpty()) {
            return new ResponseEntity<>("Uploaded file is empty", HttpStatus.BAD_REQUEST);
        }
        documentsService.save(file);
    return new ResponseEntity<>("Upload Completo", HttpStatus.OK);
    }
}
