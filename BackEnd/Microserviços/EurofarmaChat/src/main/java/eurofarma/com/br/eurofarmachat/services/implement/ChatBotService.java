package eurofarma.com.br.eurofarmachat.services.implement;

import eurofarma.com.br.eurofarmachat.dtos.AnswerDTO;
import eurofarma.com.br.eurofarmachat.dtos.QuestionDTO;
import eurofarma.com.br.eurofarmachat.models.chatbot.Implements.ChatBotCompliance;
import eurofarma.com.br.eurofarmachat.models.chatbot.Implements.ChatBotEuroData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChatBotService {

    @Autowired
    private ChatBotCompliance chatBotCompliance;

    @Autowired
    private  ChatBotEuroData chatBotEuroData;

    public AnswerDTO chatWithComplianceBot(QuestionDTO question){
        String answer = chatBotCompliance.chat(question.question());
        return new AnswerDTO(question.question(),answer);
    }

    public AnswerDTO chatWithEuroDataBot(QuestionDTO question){
        String answer = chatBotEuroData.chat(question.question());
        return new AnswerDTO(question.question(),answer);
    }

}
