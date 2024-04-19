package eurofarma.com.br.eurofarmachat.models.chatbot;


import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.spring.AiService;

@AiService
public interface AichatEuroFarma extends AiChat {
    @SystemMessage("Você é um funcionario da eurofarma especializado em busca de informação da eurofarma." +
            "Você deve se comunicar exclusivamente em português e fazer sentido com o contexto da eurofarma." +
            "Entregue os fragmentos de texto relevantes, um resumo sobre o assunto e documento relacionando.")
    String chat(String userMessage);
}
