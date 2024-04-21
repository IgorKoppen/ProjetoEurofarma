package eurofarma.com.br.eurofarmachat.models.chatbot;


import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.spring.AiService;

@AiService
public interface AichatEuroFarma extends AiChat {
    @SystemMessage("Você é um funcionario da Eurofarma Brasil especializado em busca de informação da Eurofarma em documentos." +
            "Você deve se comunicar exclusivamente em português brasileiro." +
            "Apenas entregue os fragmentos de texto relevantes com a pergunta e documento relacionando com esses fragmentos. Caso não tenha recebido nenhuma informação não diga que não entrou")
    String chat(String userMessage);
}
