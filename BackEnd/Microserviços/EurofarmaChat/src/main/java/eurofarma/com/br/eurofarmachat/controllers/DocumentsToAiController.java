package eurofarma.com.br.eurofarmachat.controllers;


import eurofarma.com.br.eurofarmachat.services.DocumentsToAiService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value={"/datavector"})
public class DocumentsToAiController {
    private final DocumentsToAiService documentsToAiService;

    public DocumentsToAiController(DocumentsToAiService documentsToAiService) {
        this.documentsToAiService = documentsToAiService;
    }
    @GetMapping("/loadDocsToCompliace")
    public ResponseEntity<String> loadDocsToCompliace(@RequestParam String fileName) {
        documentsToAiService.loadToChatCompliance(fileName);
        return new ResponseEntity<>("Upload Completo", HttpStatus.OK);
    }
    @GetMapping("/loadDocsToEuroData")
    public ResponseEntity<String> loadDocsToEuroData(@RequestParam String fileName) {
        documentsToAiService.loadToChatEuroData(fileName);
        return new ResponseEntity<>("Upload Completo", HttpStatus.OK);
    }
    @DeleteMapping("/deleteFromComplianceByFileName")
    public ResponseEntity<String> deleteDocsToCompliace(@RequestParam String fileName) {
        documentsToAiService.deleteToChatCompliance(fileName);
        return new ResponseEntity<>("Upload Completo", HttpStatus.OK);
    }
    @DeleteMapping("/deleteFromEurodataByFileName")
    public ResponseEntity<String> deleteDocsToEuroData(@RequestParam String fileName) {
        documentsToAiService.deleteToChatEuroData(fileName);
        return new ResponseEntity<>("Upload Completo", HttpStatus.OK);
    }
}
