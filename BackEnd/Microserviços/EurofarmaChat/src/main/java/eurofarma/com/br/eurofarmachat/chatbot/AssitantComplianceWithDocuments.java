package eurofarma.com.br.eurofarmachat.chatbot;
import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.V;
import dev.langchain4j.service.spring.AiService;

@AiService
interface AssitantComplianceWithDocuments {
    @SystemMessage("Você é um chatbot que responde em português. Responda com trechos do documento")
    String chat(String userMessage);


}
