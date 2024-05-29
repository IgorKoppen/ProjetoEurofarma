package eurofarma.com.br.eurofarmachat.models.chatbot.Implements;
import dev.langchain4j.rag.RetrievalAugmentor;
import dev.langchain4j.rag.content.retriever.ContentRetriever;
import dev.langchain4j.service.AiServices;
import eurofarma.com.br.eurofarmachat.config.EmbeddingModelProperties;
import eurofarma.com.br.eurofarmachat.models.langchain4j.ChatLanguageModelSingleton;
import eurofarma.com.br.eurofarmachat.models.langchain4j.EmbeddingStoreContentRetrieverFactory;
import eurofarma.com.br.eurofarmachat.models.langchain4j.RetrievalAugmentorFactory;
import eurofarma.com.br.eurofarmachat.models.chatbot.AiChat;
import eurofarma.com.br.eurofarmachat.models.chatbot.AiChatCompliance;
import org.springframework.stereotype.Component;

@Component
public class ChatBotCompliance implements AiChat {
    private final AiChatCompliance aiChatCompliance;
    private final AiChatCompliance chatComplianceAnalizer;
    public ChatBotCompliance(EmbeddingModelProperties embeddingModelProperties){
        ContentRetriever contentRetriever = EmbeddingStoreContentRetrieverFactory.EmbeddingStoreContentRetrieverCreater("eurocompliance",embeddingModelProperties.getEmbeddingFolder());
        RetrievalAugmentor retrievalAugmentor = RetrievalAugmentorFactory.createRetrievalAugmentor(contentRetriever);
        ChatLanguageModelSingleton chatLanguageModelFactory = ChatLanguageModelSingleton.getInstance();
        this.aiChatCompliance = AiServices.builder(AiChatCompliance.class)
                .chatLanguageModel(chatLanguageModelFactory.getChatLanguageModel())
                .retrievalAugmentor(retrievalAugmentor)
                .build();
        this.chatComplianceAnalizer = AiServices.builder(AiChatCompliance.class).
                chatLanguageModel(chatLanguageModelFactory.getChatLanguageModel())
                .build();
    }

    @Override
    public String chat(String pergunta) {
        if(chatComplianceAnalizer.isComprimento(pergunta)){
            return "Olá! Sou o Eurinho o assistente de conformidade da Eurofarma. Como posso auxiliá-lo hoje";
        }
        if(chatComplianceAnalizer.isDespedida(pergunta)){
            return chatComplianceAnalizer.goodBye();
        }

        if(chatComplianceAnalizer.isAboutCompliance(pergunta)){
            return aiChatCompliance.chat(pergunta);
        }
        return "Peço desculpas, mas como um assistente de compliance, não tenho permissão para discutir esse tópico.";
    }

}
