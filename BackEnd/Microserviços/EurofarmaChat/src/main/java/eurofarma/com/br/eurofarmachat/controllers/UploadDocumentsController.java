package eurofarma.com.br.eurofarmachat.controllers;


import eurofarma.com.br.eurofarmachat.services.UploadDocumentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(value={"/uploadDocument"})
public class UploadDocumentsController {

    private final UploadDocumentsService documentsService;

    @Autowired
    public UploadDocumentsController(UploadDocumentsService documentsService) {
        this.documentsService = documentsService;
    }

    @PostMapping("/uploadDocsToCompliance")
    public ResponseEntity<String> uploadDocsToCompliance(@RequestParam("file") MultipartFile file) {
        if(file.isEmpty()) {
            return new ResponseEntity<>("Uploaded file is empty", HttpStatus.BAD_REQUEST);
        }
        documentsService.saveToChatCompliance(file);
        return new ResponseEntity<>("Upload Completo", HttpStatus.OK);
    }
    
    @PostMapping("/uploadDocsToEuroData")
    public ResponseEntity<String> uploadDocsToEuroData(@RequestParam("file") MultipartFile file) {
        if(file.isEmpty()) {
            return new ResponseEntity<>("Uploaded file is empty", HttpStatus.BAD_REQUEST);
        }
        documentsService.saveToChatEuroData(file);
        return new ResponseEntity<>("Upload Completo", HttpStatus.OK);
    }

}
