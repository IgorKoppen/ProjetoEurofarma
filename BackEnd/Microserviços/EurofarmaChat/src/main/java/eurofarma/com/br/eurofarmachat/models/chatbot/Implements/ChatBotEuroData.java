package eurofarma.com.br.eurofarmachat.models.chatbot.Implements;
import dev.langchain4j.rag.RetrievalAugmentor;
import dev.langchain4j.rag.content.retriever.ContentRetriever;
import dev.langchain4j.service.AiServices;
import eurofarma.com.br.eurofarmachat.config.EmbeddingModelProperties;
import eurofarma.com.br.eurofarmachat.models.langchain4j.ChatLanguageModelSingleton;
import eurofarma.com.br.eurofarmachat.models.langchain4j.EmbeddingStoreContentRetrieverFactory;
import eurofarma.com.br.eurofarmachat.models.langchain4j.RetrievalAugmentorFactory;
import eurofarma.com.br.eurofarmachat.models.chatbot.AiChat;
import eurofarma.com.br.eurofarmachat.models.chatbot.AichatEuroFarma;
import org.springframework.stereotype.Component;


@Component
public class ChatBotEuroData implements AiChat {

    private final AichatEuroFarma aiChatEuro;
    private final AichatEuroFarma aiChatEuroAnalizer;

    public ChatBotEuroData(EmbeddingModelProperties embeddingModelProperties){
        ContentRetriever embeddingStoreContentRetriever = EmbeddingStoreContentRetrieverFactory.EmbeddingStoreContentRetrieverCreater("eurodata", embeddingModelProperties.getEmbeddingFolder());
        RetrievalAugmentor retrievalAugmentor = RetrievalAugmentorFactory.createRetrievalAugmentor(embeddingStoreContentRetriever);
        ChatLanguageModelSingleton chatLanguageModelFactory = ChatLanguageModelSingleton.getInstance();

        this.aiChatEuro = AiServices.builder(AichatEuroFarma.class)
                .chatLanguageModel(chatLanguageModelFactory.getChatLanguageModel())
                .retrievalAugmentor(retrievalAugmentor)
                .build();
        this.aiChatEuroAnalizer = AiServices.builder(AichatEuroFarma.class)
                .chatLanguageModel(chatLanguageModelFactory.getChatLanguageModel())
                .build();

    }

    @Override
    public String chat(String pergunta) {
        if(aiChatEuroAnalizer.isComprimento(pergunta)){
            return "Olá! Sou o assistente de busca de dados da Eurofarma. Como posso auxiliá-lo hoje";
        }
        if(aiChatEuroAnalizer.isDespedida(pergunta)){
            return aiChatEuro.goodBye();
        }
        return aiChatEuro.chat(pergunta);
    }
}
