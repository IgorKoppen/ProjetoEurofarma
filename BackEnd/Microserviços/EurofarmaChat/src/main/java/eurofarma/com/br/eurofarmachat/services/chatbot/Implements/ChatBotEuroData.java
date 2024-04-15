package eurofarma.com.br.eurofarmachat.services.chatbot.Implements;
import dev.langchain4j.service.AiServices;
import eurofarma.com.br.eurofarmachat.factorys.ChatLanguageModelFactory;
import eurofarma.com.br.eurofarmachat.factorys.ContentAggregatorFactory;
import eurofarma.com.br.eurofarmachat.factorys.EmbeddingStoreContentRetrieverFactory;
import eurofarma.com.br.eurofarmachat.factorys.RetrievalAugmentorFactory;
import eurofarma.com.br.eurofarmachat.services.chatbot.AiChat;
import eurofarma.com.br.eurofarmachat.services.chatbot.AichatEuroFarma;
import org.springframework.stereotype.Service;

@Service
public class ChatBotEuroData implements AiChat {
    private final AiChat aiChatEuro;
    RetrievalAugmentorFactory retrievalAugmentorFactory = new RetrievalAugmentorFactory();
    ChatLanguageModelFactory chatLanguageModelFactory = new ChatLanguageModelFactory();
    EmbeddingStoreContentRetrieverFactory embeddingStoreContentRetriever = new EmbeddingStoreContentRetrieverFactory();

    public ChatBotEuroData(){
        this.aiChatEuro = AiServices.builder(AichatEuroFarma.class)
                .chatLanguageModel(chatLanguageModelFactory.chatLanguageModelOllama())
                .retrievalAugmentor(retrievalAugmentorFactory.RetrievalAugmentor(embeddingStoreContentRetriever.embeddingStoreContentRetriever("eurobot")))
                .build();
    }

    @Override
    public String chat(String pergunta) {
        return aiChatEuro.chat(pergunta);
    }
}
