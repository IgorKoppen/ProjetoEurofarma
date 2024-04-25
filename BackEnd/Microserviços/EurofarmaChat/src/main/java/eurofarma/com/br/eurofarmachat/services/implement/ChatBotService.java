package eurofarma.com.br.eurofarmachat.services.implement;

import eurofarma.com.br.eurofarmachat.dtos.AnswerDTO;
import eurofarma.com.br.eurofarmachat.dtos.QuestionDTO;
import eurofarma.com.br.eurofarmachat.models.chatbot.Implements.ChatBotCompliance;
import eurofarma.com.br.eurofarmachat.models.chatbot.Implements.ChatBotEuroData;
import org.springframework.stereotype.Service;

@Service
public class ChatBotService {
    public AnswerDTO chatWithComplianceBot(QuestionDTO question){
        ChatBotCompliance chatBotCompliance = new ChatBotCompliance();
        String answer = chatBotCompliance.chat(question.question());
        return new AnswerDTO(question.question(),answer);
    }

    public AnswerDTO chatWithEuroDataBot(QuestionDTO question){
        ChatBotEuroData chatBotEuroData = new ChatBotEuroData();
        String answer = chatBotEuroData.chat(question.question());
        return new AnswerDTO(question.question(),answer);
    }

}
