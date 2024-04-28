package eurofarma.com.br.eurofarmachat.models.chatbot.Implements;
import dev.langchain4j.rag.RetrievalAugmentor;
import dev.langchain4j.service.AiServices;
import eurofarma.com.br.eurofarmachat.configuration.EmbeddingModelProperties;
import eurofarma.com.br.eurofarmachat.models.langchain4j.ChatLanguageModelSingleton;
import eurofarma.com.br.eurofarmachat.models.langchain4j.EmbeddingStoreContentRetrieverCreater;
import eurofarma.com.br.eurofarmachat.models.langchain4j.RetrievalAugmentorCreater;
import eurofarma.com.br.eurofarmachat.models.chatbot.AiChat;
import eurofarma.com.br.eurofarmachat.models.chatbot.AiChatCompliance;
import org.springframework.stereotype.Component;

@Component
public class ChatBotCompliance implements AiChat {
    private final AiChatCompliance aiChatCompliance;
    public ChatBotCompliance(EmbeddingModelProperties embeddingModelProperties){
        EmbeddingStoreContentRetrieverCreater embeddingStoreContentRetriever = new EmbeddingStoreContentRetrieverCreater("eurocompliance",embeddingModelProperties.getEmbeddingFolder());
        RetrievalAugmentor retrievalAugmentor = new RetrievalAugmentorCreater(embeddingStoreContentRetriever.getRetriever()).getRetrievalAugmentor();
        ChatLanguageModelSingleton chatLanguageModelFactory = ChatLanguageModelSingleton.getInstance();
        this.aiChatCompliance = AiServices.builder(AiChatCompliance.class)
                .chatLanguageModel(chatLanguageModelFactory.getChatLanguageModel())
                .retrievalAugmentor(retrievalAugmentor)
                .build();
    }

    @Override
    public String chat(String pergunta) {
        if(aiChatCompliance.isAboutCompliance(pergunta)){
            return aiChatCompliance.chat(pergunta);
        }
        return "Peço desculpas, mas como um assistente de compliance, não tenho permissão para discutir esse tópico.";
    }

}
