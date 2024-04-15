package eurofarma.com.br.eurofarmachat.services.chatbot.Implements;
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.rag.content.retriever.EmbeddingStoreContentRetriever;
import dev.langchain4j.service.AiServices;
import eurofarma.com.br.eurofarmachat.services.chatbot.AiChat;
import eurofarma.com.br.eurofarmachat.services.chatbot.AichatEuroFarma;
import org.springframework.stereotype.Service;

@Service
public class ChatBotEuroData implements AiChat {
    private final AiChat aiChatEuro;
    public ChatBotEuroData(ChatLanguageModel chatLanguageModel, EmbeddingStoreContentRetriever contentRetriever){
        this.aiChatEuro = AiServices.builder(AichatEuroFarma.class)
                .chatLanguageModel(chatLanguageModel)
                .contentRetriever(contentRetriever).build();
    }
    @Override
    public String chat(String pergunta) {
        return aiChatEuro.chat(pergunta);
    }
}
