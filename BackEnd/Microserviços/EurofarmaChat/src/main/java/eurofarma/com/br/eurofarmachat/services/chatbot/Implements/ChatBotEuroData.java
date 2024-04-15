package eurofarma.com.br.eurofarmachat.services.chatbot.Implements;
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.service.AiServices;
import eurofarma.com.br.eurofarmachat.factorys.embeddingStoreContentRetrieverFactory;
import eurofarma.com.br.eurofarmachat.services.chatbot.AiChat;
import eurofarma.com.br.eurofarmachat.services.chatbot.AichatEuroFarma;
import org.springframework.stereotype.Service;

@Service
public class ChatBotEuroData implements AiChat {
    private final AiChat aiChatEuro;

    public ChatBotEuroData(ChatLanguageModel chatLanguageModel){
        embeddingStoreContentRetrieverFactory embeddingStoreFactory = new embeddingStoreContentRetrieverFactory();
        this.aiChatEuro = AiServices.builder(AichatEuroFarma.class)
                .chatLanguageModel(chatLanguageModel)
                .contentRetriever(embeddingStoreFactory.embeddingStoreContentRetriever("eurobot")).build();

    }
    @Override
    public String chat(String pergunta) {
        return aiChatEuro.chat(pergunta);
    }
}
