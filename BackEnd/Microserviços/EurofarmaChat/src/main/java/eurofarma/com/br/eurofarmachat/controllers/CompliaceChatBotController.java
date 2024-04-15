package eurofarma.com.br.eurofarmachat.controllers;


import eurofarma.com.br.eurofarmachat.services.chatbot.Implements.ChatBotCompliance;
import eurofarma.com.br.eurofarmachat.services.chatbot.Implements.ChatBotEuroData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value={"/chatbot"})
public class CompliaceChatBotController {

    ChatBotEuroData chatBotEuroData;
    ChatBotCompliance chatBotCompliance;

    @Autowired
    public CompliaceChatBotController(ChatBotEuroData chatBotEuroData, ChatBotCompliance chatBotCompliance) {
        this.chatBotEuroData = chatBotEuroData;
        this.chatBotCompliance = chatBotCompliance;
    }

    @GetMapping("/chatBotCompliance")
    public String chatWithEuroCompliance(@RequestParam(value = "message", defaultValue = "Olá") String message) {
        return chatBotCompliance.chat(message);
    }
    @GetMapping("/chatBotEuroData")
    public String chatWithEuroData(@RequestParam(value = "message", defaultValue = "Olá") String message) {
        return chatBotEuroData.chat(message);
    }
}
