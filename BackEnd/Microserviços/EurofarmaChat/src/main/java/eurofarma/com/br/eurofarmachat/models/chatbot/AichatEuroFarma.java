package eurofarma.com.br.eurofarmachat.models.chatbot;


import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.spring.AiService;

@AiService
public interface AichatEuroFarma extends AiChat {
    @SystemMessage("Você é um funcionario da eurofarma especializado em busca de informação da eurofarma." +
            "Você deve se comunicar exclusivamente em português." +
            "Entregue os fragmentos de texto relevantes e um resumo sobre o assunto.")
    String chat(String userMessage);
}
