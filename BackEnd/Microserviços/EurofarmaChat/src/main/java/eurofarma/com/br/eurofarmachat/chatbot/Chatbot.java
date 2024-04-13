package eurofarma.com.br.eurofarmachat.chatbot;

import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.V;
import dev.langchain4j.service.spring.AiService;

@AiService
public interface Chatbot {

    @UserMessage("Traduza para português e rescreva esse {{texto}} lembrando que você é um chatbot")
    String chat(@V("texto") String userMessage);


}
