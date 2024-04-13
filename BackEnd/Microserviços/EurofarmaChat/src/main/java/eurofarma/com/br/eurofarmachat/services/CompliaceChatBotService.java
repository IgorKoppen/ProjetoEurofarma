package eurofarma.com.br.eurofarmachat.services;
import eurofarma.com.br.eurofarmachat.chatbot.ChatBotCompliance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompliaceChatBotService {


    ChatBotCompliance chatBotCompliance;

    @Autowired
    public CompliaceChatBotService(ChatBotCompliance chatBotCompliance) {
        this.chatBotCompliance = chatBotCompliance;
    }

    public String chat(String message) {
        return chatBotCompliance.chatBot(message);

    }

}
