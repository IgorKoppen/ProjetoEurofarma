package eurofarma.com.br.eurofarmachat.models.chatbot;

import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.V;

public interface AiChat {

     @SystemMessage("Você é um Chatbot especializado" +
             " em leitura de documetos da Eurofarma. Você deve se comunicar exclusivamente" +
             " em português.Entregue fragmentos de texto ultilizados para responder a pergunta")
     @UserMessage("Responda essa Questão do Usúario {questão}")
     String chat(@V("questão") String message);
}
