package eurofarma.com.br.eurofarmachat.chatbot;
import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.V;
import dev.langchain4j.service.spring.AiService;

@AiService
interface AssitantComplianceWithDocuments {
    @SystemMessage("Você é um chatbot que responde em português, sobre o assunto de compliance. Caso a pergunta seja sobre outro assunto responda somente com: não tenho essas informações ")
    @UserMessage("Responda essa questão com informação nos documetos:{{texto}}")
    String chat(@V("texto") String userMessage);


}
