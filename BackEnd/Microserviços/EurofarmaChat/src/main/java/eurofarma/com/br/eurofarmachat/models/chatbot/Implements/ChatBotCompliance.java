package eurofarma.com.br.eurofarmachat.models.chatbot.Implements;
import dev.langchain4j.service.AiServices;
import eurofarma.com.br.eurofarmachat.models.ChatLanguageModelSingleton;
import eurofarma.com.br.eurofarmachat.models.EmbeddingStoreContentRetriever;
import eurofarma.com.br.eurofarmachat.models.RetrievalAugmentor;
import eurofarma.com.br.eurofarmachat.models.chatbot.AiChat;
import eurofarma.com.br.eurofarmachat.models.chatbot.AiChatCompliance;

public class ChatBotCompliance implements AiChat {
    private final AiChat aiChatCompliance;
    RetrievalAugmentor retrievalAugmentorFactory = new RetrievalAugmentor();
    ChatLanguageModelSingleton chatLanguageModelFactory =  ChatLanguageModelSingleton.getInstance();
    EmbeddingStoreContentRetriever embeddingStoreContentRetriever = new EmbeddingStoreContentRetriever();

    public ChatBotCompliance(){
        this.aiChatCompliance = AiServices.builder(AiChatCompliance.class)
                .chatLanguageModel(chatLanguageModelFactory.getChatLanguageModel())
                .retrievalAugmentor(retrievalAugmentorFactory.RetrievalAugmentor(embeddingStoreContentRetriever.embeddingStoreContentRetriever("eurocompliance")))
                .build();
    }

    @Override
    public String chat(String pergunta) {
            return aiChatCompliance.chat(pergunta);
    }

}
