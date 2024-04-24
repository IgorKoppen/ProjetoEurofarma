package eurofarma.com.br.eurofarmachat.controllers;


import eurofarma.com.br.eurofarmachat.services.UploadDocToComplianceService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;

@RestController
@RequestMapping(value={"/uploadDocsToCompliance"})
public class UploadDocsToComplianceController {

    private final UploadDocToComplianceService documentsService;

    @Autowired
    public UploadDocsToComplianceController(UploadDocToComplianceService documentsService) {
        this.documentsService = documentsService;
    }

    @PostMapping
    public ResponseEntity<String> uploadDocsToCompliance(@RequestParam("file") MultipartFile file) {
        return  documentsService.save(file);
    }

    @GetMapping
    public ResponseEntity<List<String>> listFilesCompliance() {
            return ResponseEntity.ok(documentsService.listAll());
    }
    @GetMapping("/download/{fileName:.+}")
    public ResponseEntity<Resource> downloadFileEuroData(@PathVariable String fileName, HttpServletRequest request) {
        return documentsService.getDownload(fileName,request);
    }
    @DeleteMapping
    public ResponseEntity<String> deleteDocAtCompliance(@RequestParam String fileName) {
        return documentsService.delete(fileName);
    }
}
