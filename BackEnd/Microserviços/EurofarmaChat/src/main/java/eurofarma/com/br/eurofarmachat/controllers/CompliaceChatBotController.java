package eurofarma.com.br.eurofarmachat.controllers;


import eurofarma.com.br.eurofarmachat.services.CompliaceChatBotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value={"/compliaceChatBot"})
public class CompliaceChatBotController {


    CompliaceChatBotService compliaceChatBotService;

    @Autowired
    CompliaceChatBotController(CompliaceChatBotService compliaceChatBotService){
        this.compliaceChatBotService = compliaceChatBotService;
    }

    @GetMapping
    public String model(@RequestParam(value = "message", defaultValue = "Olá") String message) {
        return compliaceChatBotService.chat(message);
    }
}
