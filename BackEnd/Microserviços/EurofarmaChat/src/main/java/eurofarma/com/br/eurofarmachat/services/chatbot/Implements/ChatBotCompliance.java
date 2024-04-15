package eurofarma.com.br.eurofarmachat.services.chatbot.Implements;
import dev.langchain4j.service.AiServices;
import eurofarma.com.br.eurofarmachat.factorys.ChatLanguageModelFactory;
import eurofarma.com.br.eurofarmachat.factorys.EmbeddingStoreContentRetrieverFactory;
import eurofarma.com.br.eurofarmachat.services.chatbot.AiChat;
import eurofarma.com.br.eurofarmachat.services.chatbot.AichatEuroFarma;
import org.springframework.stereotype.Service;

@Service
public class ChatBotCompliance implements AiChat {

    private final AiChat aiChatCompliance;
    EmbeddingStoreContentRetrieverFactory embeddingStoreFactory = new EmbeddingStoreContentRetrieverFactory();
    ChatLanguageModelFactory chatLanguageModelFactory = new ChatLanguageModelFactory();

    public ChatBotCompliance(){
        this.aiChatCompliance = AiServices.builder(AichatEuroFarma.class)
                .chatLanguageModel(chatLanguageModelFactory.chatLanguageModelOllama())
                .contentRetriever(embeddingStoreFactory.embeddingStoreContentRetriever("eurocompliance"))
                .build();
    }

    @Override
    public String chat(String pergunta) {
        if (isComplianceRelated(pergunta)) {
            return aiChatCompliance.chat(pergunta);
        } else {
            return "Não tenho essas informações.";
        }
    }

    private boolean isComplianceRelated(String pergunta) {
        String resposta = chatLanguageModelFactory.chatLanguageModelOllama().generate("Responda com sim ou não essa pergunta tem haver com compliance:" + pergunta);
        return resposta.contains("Sim") || resposta.contains("sim") ;
    }
}
