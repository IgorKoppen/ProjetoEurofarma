package eurofarma.com.br.eurofarmachat.models.chatbot;


import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.V;
import dev.langchain4j.service.spring.AiService;

@AiService
public interface AichatEuroFarma extends AiChat {
    @SystemMessage("Você é um funcionario da Eurofarma Brasil especializado em buscar informação da Eurofarma em documentos." +
            "Devolva as informações nesse formato:" +
            "{Resposta para pergunta}" +
            "{Documento relacionado}" +
            "{Link completo para baixar o documento}")
    @UserMessage("Responda em português essa pergunta: {{message}}")
    String chat(@V("message") String userMessage);
}
