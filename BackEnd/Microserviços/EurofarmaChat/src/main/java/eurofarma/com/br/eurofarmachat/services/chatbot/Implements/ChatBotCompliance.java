package eurofarma.com.br.eurofarmachat.services.chatbot.Implements;
import dev.langchain4j.service.AiServices;
import eurofarma.com.br.eurofarmachat.factorys.ChatLanguageModelFactory;
import eurofarma.com.br.eurofarmachat.factorys.EmbeddingStoreContentRetrieverFactory;
import eurofarma.com.br.eurofarmachat.factorys.RetrievalAugmentorFactory;
import eurofarma.com.br.eurofarmachat.services.chatbot.AiChat;
import eurofarma.com.br.eurofarmachat.services.chatbot.AiChatCompliance;
import org.springframework.stereotype.Service;

@Service
public class ChatBotCompliance implements AiChat {

    private final AiChat aiChatCompliance;
    RetrievalAugmentorFactory retrievalAugmentorFactory = new RetrievalAugmentorFactory();
    ChatLanguageModelFactory chatLanguageModelFactory = new ChatLanguageModelFactory();
    EmbeddingStoreContentRetrieverFactory embeddingStoreContentRetriever = new EmbeddingStoreContentRetrieverFactory();

    public ChatBotCompliance(){
        this.aiChatCompliance = AiServices.builder(AiChatCompliance.class)
                .chatLanguageModel(chatLanguageModelFactory.chatLanguageModelOllama())
                .retrievalAugmentor(retrievalAugmentorFactory.RetrievalAugmentor(embeddingStoreContentRetriever.embeddingStoreContentRetriever("eurocompliance")))
                .build();
    }

    @Override
    public String chat(String pergunta) {
            return aiChatCompliance.chat(pergunta);
    }

}
