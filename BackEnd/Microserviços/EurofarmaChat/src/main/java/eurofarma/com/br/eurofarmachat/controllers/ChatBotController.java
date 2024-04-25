package eurofarma.com.br.eurofarmachat.controllers;
import eurofarma.com.br.eurofarmachat.dtos.AnswerDTO;
import eurofarma.com.br.eurofarmachat.dtos.QuestionDTO;
import eurofarma.com.br.eurofarmachat.services.implement.ChatBotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value={"/chatbot"})
public class ChatBotController {

    private final ChatBotService chatBotService;

    @Autowired
    public ChatBotController(ChatBotService chatBotService) {
        this.chatBotService = chatBotService;
    }
    @PostMapping("/chatBotCompliance")
    public AnswerDTO chatWithEuroCompliance(@RequestBody QuestionDTO questionDTO) {
        return chatBotService.chatWithComplianceBot(questionDTO);
    }
    @PostMapping("/chatBotEuroData")
    public AnswerDTO chatWithEuroData(@RequestBody QuestionDTO questionDTO) {
        return chatBotService.chatWithEuroDataBot(questionDTO);
    }
}
