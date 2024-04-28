package eurofarma.com.br.eurofarmachat.models.chatbot.Implements;
import dev.langchain4j.rag.RetrievalAugmentor;
import dev.langchain4j.service.AiServices;
import eurofarma.com.br.eurofarmachat.config.EmbeddingModelProperties;
import eurofarma.com.br.eurofarmachat.models.langchain4j.ChatLanguageModelSingleton;
import eurofarma.com.br.eurofarmachat.models.langchain4j.EmbeddingStoreContentRetrieverCreater;
import eurofarma.com.br.eurofarmachat.models.langchain4j.RetrievalAugmentorCreater;
import eurofarma.com.br.eurofarmachat.models.chatbot.AiChat;
import eurofarma.com.br.eurofarmachat.models.chatbot.AichatEuroFarma;
import org.springframework.stereotype.Component;


@Component
public class ChatBotEuroData implements AiChat {

    private final AichatEuroFarma aiChatEuro;

    public ChatBotEuroData(EmbeddingModelProperties embeddingModelProperties){
        EmbeddingStoreContentRetrieverCreater embeddingStoreContentRetriever = new EmbeddingStoreContentRetrieverCreater("eurodata",embeddingModelProperties.getEmbeddingFolder());
        RetrievalAugmentor retrievalAugmentor = new RetrievalAugmentorCreater(embeddingStoreContentRetriever.getRetriever()).getRetrievalAugmentor();
        ChatLanguageModelSingleton chatLanguageModelFactory = ChatLanguageModelSingleton.getInstance();

        this.aiChatEuro = AiServices.builder(AichatEuroFarma.class)
                .chatLanguageModel(chatLanguageModelFactory.getChatLanguageModel())
                .retrievalAugmentor(retrievalAugmentor)
                .build();
    }

    @Override
    public String chat(String pergunta) {
        return aiChatEuro.chat(pergunta);
    }
}
