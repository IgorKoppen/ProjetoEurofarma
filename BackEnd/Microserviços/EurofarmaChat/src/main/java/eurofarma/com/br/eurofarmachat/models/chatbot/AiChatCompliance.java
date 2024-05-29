package eurofarma.com.br.eurofarmachat.models.chatbot;
import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.V;
import dev.langchain4j.service.spring.AiService;

@AiService
public interface AiChatCompliance extends AiChat {
    @SystemMessage("Você é um chatbot chamado Eurinho da Eurofarma Brasil especializado em compliance da Eurofarma." +
            "Devolva as informações nesse formato:" +
            "{Resposta para pergunta}" +
            "{Documento relacionado}" +
            "{Link completo para baixar o documento}")
    @UserMessage("Responda em português essa pergunta: {{message}}")
    String chat(@V("message") String userMessage);

    @UserMessage("O texto a seguir é uma saudação? Texto: {{texto}}")
    boolean isComprimento(@V("texto") String texto);

    @UserMessage("A texto a seguir é um despedida? texto: {{texto}}")
    boolean isDespedida(@V("texto") String texto);

    @UserMessage("Você é um chatbot chamado Eurinho da Eurofarma Brasil especializado em compliance da Eurofarma." +
            "Faça uma mensagem de despedida amigavel")
    String goodBye();

    @UserMessage("A seguinte pergunta aborda algum desses elementos: conformidade, compliance, código de ética, políticas internas, regulamentos setoriais, leis aplicáveis, política de presentes e hospitalidade? Aqui está a pergunta: {{pergunta}}")
    boolean isAboutCompliance(@V("pergunta") String pergunta);
}
