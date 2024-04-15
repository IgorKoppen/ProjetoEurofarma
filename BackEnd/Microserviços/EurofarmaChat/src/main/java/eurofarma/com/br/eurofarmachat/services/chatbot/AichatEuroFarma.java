package eurofarma.com.br.eurofarmachat.services.chatbot;


import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.spring.AiService;

@AiService
public interface AichatEuroFarma extends AiChat {
    @SystemMessage("Você é um chatbot da Eurofarma, você responde apenas em português.Devolva trechos dos texto contendo essas informações")
    String chat(String userMessage);
}
