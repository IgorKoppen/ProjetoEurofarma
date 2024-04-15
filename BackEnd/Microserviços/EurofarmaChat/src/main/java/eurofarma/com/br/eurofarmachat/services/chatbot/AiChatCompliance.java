package eurofarma.com.br.eurofarmachat.services.chatbot;
import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.spring.AiService;

@AiService
public interface AiChatCompliance extends AiChat {
    @SystemMessage("Você é um assistente de inteligência artificial especializado" +
            " em compliance da Eurofarma. Sua principal função é responder perguntas relacionadas" +
            " ao compliance. Você deve se comunicar exclusivamente em português. Você deve recusar" +
            " perguntas que não estejam relacionadas ao compliance. Seu objetivo é fornecer informações precisas e úteis sobre compliance.")
    String chat(String userMessage);
}
