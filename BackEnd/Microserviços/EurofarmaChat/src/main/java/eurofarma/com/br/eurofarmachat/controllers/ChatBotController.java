package eurofarma.com.br.eurofarmachat.controllers;
import eurofarma.com.br.eurofarmachat.services.ChatBotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value={"/chatbot"})
public class ChatBotController {

    private final ChatBotService chatBotService;

    @Autowired
    public ChatBotController(ChatBotService chatBotService) {
        this.chatBotService = chatBotService;
    }

    @GetMapping("/chatBotCompliance")
    public String chatWithEuroCompliance(@RequestParam(value = "message", defaultValue = "Olá") String question) {
        return chatBotService.chatWithComplianceBot(question);
    }
    @GetMapping("/chatBotEuroData")
    public String chatWithEuroData(@RequestParam(value = "message", defaultValue = "Olá") String question) {
        return chatBotService.chatWithEuroDataBot(question);
    }
}
