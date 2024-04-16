package eurofarma.com.br.eurofarmachat.services.chatbot.Implements;
import dev.langchain4j.service.AiServices;
import eurofarma.com.br.eurofarmachat.services.ChatLanguageModelSingleton;
import eurofarma.com.br.eurofarmachat.factorys.EmbeddingStoreContentRetrieverFactory;
import eurofarma.com.br.eurofarmachat.factorys.RetrievalAugmentorFactory;
import eurofarma.com.br.eurofarmachat.services.chatbot.AiChat;
import eurofarma.com.br.eurofarmachat.services.chatbot.AichatEuroFarma;
import org.springframework.stereotype.Service;

@Service
public class ChatBotEuroData implements AiChat {
    private final AiChat aiChatEuro;
    RetrievalAugmentorFactory retrievalAugmentorFactory = new RetrievalAugmentorFactory();
    ChatLanguageModelSingleton chatLanguageModelFactory = ChatLanguageModelSingleton.getInstance();
    EmbeddingStoreContentRetrieverFactory embeddingStoreContentRetriever = new EmbeddingStoreContentRetrieverFactory();

    public ChatBotEuroData(){
        this.aiChatEuro = AiServices.builder(AichatEuroFarma.class)
                .chatLanguageModel(chatLanguageModelFactory.getChatLanguageModel())
                .retrievalAugmentor(retrievalAugmentorFactory.RetrievalAugmentor(embeddingStoreContentRetriever.embeddingStoreContentRetriever("eurobot")))
                .build();
    }

    @Override
    public String chat(String pergunta) {
        return aiChatEuro.chat(pergunta);
    }
}
