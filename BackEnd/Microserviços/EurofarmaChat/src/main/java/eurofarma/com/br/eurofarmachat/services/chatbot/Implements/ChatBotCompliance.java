package eurofarma.com.br.eurofarmachat.services.chatbot.Implements;
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.rag.content.retriever.EmbeddingStoreContentRetriever;
import dev.langchain4j.service.AiServices;
import eurofarma.com.br.eurofarmachat.services.chatbot.AiChat;
import eurofarma.com.br.eurofarmachat.services.chatbot.AiChatCompliance;
import org.springframework.stereotype.Service;

@Service
public class ChatBotCompliance implements AiChat {

    private final AiChat aiChatCompliance;
    private final ChatLanguageModel ollama;

    public ChatBotCompliance(ChatLanguageModel chatLanguageModel, EmbeddingStoreContentRetriever contentRetriever){
        this.ollama = chatLanguageModel;
        this.aiChatCompliance = AiServices.builder(AiChatCompliance.class)
                .chatLanguageModel(chatLanguageModel)
                .contentRetriever(contentRetriever).build();
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

        String resposta = ollama.generate("Responda com sim ou não essa pergunta tem haver com compliance:" + pergunta);
        System.out.println(resposta);
        return resposta.contains("Sim") || resposta.contains("sim") ;
    }
}
