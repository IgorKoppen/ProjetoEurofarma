package eurofarma.com.br.eurofarmachat.models.chatbot.Implements;
import dev.langchain4j.rag.RetrievalAugmentor;
import dev.langchain4j.service.AiServices;
import eurofarma.com.br.eurofarmachat.models.ChatLanguageModelSingleton;
import eurofarma.com.br.eurofarmachat.models.EmbeddingStoreContentRetrieverCreater;
import eurofarma.com.br.eurofarmachat.models.RetrievalAugmentorCreater;
import eurofarma.com.br.eurofarmachat.models.chatbot.AiChat;
import eurofarma.com.br.eurofarmachat.models.chatbot.AichatEuroFarma;



public class ChatBotEuroData implements AiChat {

    private final AiChat aiChatEuro;

    public ChatBotEuroData(){
        EmbeddingStoreContentRetrieverCreater embeddingStoreContentRetriever = new EmbeddingStoreContentRetrieverCreater("eurobot");
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
