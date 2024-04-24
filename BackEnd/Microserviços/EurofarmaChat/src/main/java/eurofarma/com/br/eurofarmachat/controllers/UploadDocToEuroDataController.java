package eurofarma.com.br.eurofarmachat.controllers;

import eurofarma.com.br.eurofarmachat.services.UploadDocToEuroDataService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;

@RestController
@RequestMapping(value={"/uploadDocumentToAi"})
public class UploadDocToEuroDataController {

    private final UploadDocToEuroDataService documentsService;

    @Autowired
    public UploadDocToEuroDataController(UploadDocToEuroDataService uploadDocToEuroDataService) {
        this.documentsService = uploadDocToEuroDataService;
    }

    @PostMapping
    public ResponseEntity<String> uploadDocsToEuroData(@RequestParam("file") MultipartFile file) {
        return documentsService.save(file);
    }

    @GetMapping
    public ResponseEntity<List<String>> listFilesEuroData() {
      return ResponseEntity.ok(documentsService.listAll());
    }
    @GetMapping("/download/{fileName:.+}")
    public ResponseEntity<Resource> downloadFileEuroData(@PathVariable String fileName, HttpServletRequest request) {
        return documentsService.getDownload(fileName,request);
    }
    @DeleteMapping
    public ResponseEntity<String> deleteDocAtEuroData(@RequestParam String fileName) {
        return documentsService.delete(fileName);
    }


}
