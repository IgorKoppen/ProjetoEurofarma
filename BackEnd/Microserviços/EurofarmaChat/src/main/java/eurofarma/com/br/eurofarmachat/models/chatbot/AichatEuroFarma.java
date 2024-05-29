package eurofarma.com.br.eurofarmachat.models.chatbot;


import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.V;
import dev.langchain4j.service.spring.AiService;

@AiService
public interface AichatEuroFarma extends AiChat {


    @UserMessage("A texto a seguir é um comprimento ou saudação? texto: {{texto}}")
    boolean isComprimento(@V("texto") String texto);

    @UserMessage("A texto a seguir é um despedida? texto: {{texto}}")
    boolean isDespedida(@V("texto") String texto);

    @UserMessage("Você é um funcionario da Eurofarma Brasil especializado em buscar informação da Eurofarma em documentos." +
            "Faça uma mensagem de despedida amigavel")
    String goodBye();

    @SystemMessage("Você é um funcionario da Eurofarma Brasil especializado em buscar informação da Eurofarma em documentos." +
            "Devolva as informações nesse formato:" +
            "{Resposta para pergunta}" +
            "{Documento relacionado}" +
            "{Link completo para baixar o documento}")
    @UserMessage("Responda em português essa pergunta: {{message}}")
    String chat(@V("message") String userMessage);
}
