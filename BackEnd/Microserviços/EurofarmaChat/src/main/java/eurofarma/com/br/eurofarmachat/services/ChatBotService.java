package eurofarma.com.br.eurofarmachat.services;

import eurofarma.com.br.eurofarmachat.models.chatbot.Implements.ChatBotCompliance;
import eurofarma.com.br.eurofarmachat.models.chatbot.Implements.ChatBotEuroData;
import org.springframework.stereotype.Service;

@Service
public class ChatBotService {
    public String chatWithComplianceBot(String question){
        ChatBotCompliance chatBotCompliance = new ChatBotCompliance();
        return  chatBotCompliance.chat(question);
    }

    public String chatWithEuroDataBot(String question){
        ChatBotEuroData chatBotEuroData = new ChatBotEuroData();
        return chatBotEuroData.chat(question);
    }

}
