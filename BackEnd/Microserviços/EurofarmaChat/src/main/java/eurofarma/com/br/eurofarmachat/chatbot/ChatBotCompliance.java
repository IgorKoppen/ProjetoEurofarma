package eurofarma.com.br.eurofarmachat.chatbot;
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.rag.content.retriever.EmbeddingStoreContentRetriever;
import dev.langchain4j.service.AiServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChatBotCompliance {

    private final AssitantComplianceWithDocuments assitantComplianceWithDocuments;

    @Autowired
    public ChatBotCompliance(ChatLanguageModel chatLanguageModel, EmbeddingStoreContentRetriever contentRetriever){
        this.assitantComplianceWithDocuments = AiServices.builder(AssitantComplianceWithDocuments.class)
                .chatLanguageModel(chatLanguageModel)
                .contentRetriever(contentRetriever).build();
    }

    public String chatBot(String message){
        return assitantComplianceWithDocuments.chat(message);
    }
}
