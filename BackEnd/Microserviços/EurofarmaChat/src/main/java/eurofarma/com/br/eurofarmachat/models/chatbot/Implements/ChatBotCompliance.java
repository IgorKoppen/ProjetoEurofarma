package eurofarma.com.br.eurofarmachat.models.chatbot.Implements;
import dev.langchain4j.rag.RetrievalAugmentor;
import dev.langchain4j.service.AiServices;
import eurofarma.com.br.eurofarmachat.models.ChatLanguageModelSingleton;
import eurofarma.com.br.eurofarmachat.models.EmbeddingStoreContentRetrieverCreater;
import eurofarma.com.br.eurofarmachat.models.RetrievalAugmentorCreater;
import eurofarma.com.br.eurofarmachat.models.chatbot.AiChat;
import eurofarma.com.br.eurofarmachat.models.chatbot.AiChatCompliance;

public class ChatBotCompliance implements AiChat {
    private final AiChat aiChatCompliance;


    public ChatBotCompliance(){
        EmbeddingStoreContentRetrieverCreater embeddingStoreContentRetriever = new EmbeddingStoreContentRetrieverCreater("eurocompliance");
        RetrievalAugmentor retrievalAugmentor = new RetrievalAugmentorCreater(embeddingStoreContentRetriever.getRetriever()).getRetrievalAugmentor();
        ChatLanguageModelSingleton chatLanguageModelFactory = ChatLanguageModelSingleton.getInstance();
        this.aiChatCompliance = AiServices.builder(AiChatCompliance.class)
                .chatLanguageModel(chatLanguageModelFactory.getChatLanguageModel())
                .retrievalAugmentor(retrievalAugmentor)
                .build();
    }

    @Override
    public String chat(String pergunta) {
            return aiChatCompliance.chat(pergunta);
    }

}
