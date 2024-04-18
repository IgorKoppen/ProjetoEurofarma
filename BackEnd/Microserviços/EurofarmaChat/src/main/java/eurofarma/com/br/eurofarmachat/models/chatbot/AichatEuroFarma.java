package eurofarma.com.br.eurofarmachat.models.chatbot;


import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.spring.AiService;

@AiService
public interface AichatEuroFarma extends AiChat {
    @SystemMessage("Você é um chatbot especializado em busca de informação da eurofarma.Você deve se comunicar exclusivamente em português.Entregue fragmentos dos texto que sobre a pergunta")
    String chat(String userMessage);
}
