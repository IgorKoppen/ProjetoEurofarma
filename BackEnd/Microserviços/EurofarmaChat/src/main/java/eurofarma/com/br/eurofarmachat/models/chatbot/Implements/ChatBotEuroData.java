package eurofarma.com.br.eurofarmachat.models.chatbot.Implements;
import dev.langchain4j.service.AiServices;
import eurofarma.com.br.eurofarmachat.models.ChatLanguageModelSingleton;
import eurofarma.com.br.eurofarmachat.models.EmbeddingStoreContentRetriever;
import eurofarma.com.br.eurofarmachat.models.RetrievalAugmentor;
import eurofarma.com.br.eurofarmachat.models.chatbot.AiChat;
import eurofarma.com.br.eurofarmachat.models.chatbot.AichatEuroFarma;



public class ChatBotEuroData implements AiChat {

    private final AiChat aiChatEuro;

    public ChatBotEuroData(){
        RetrievalAugmentor retrievalAugmentorFactory = new RetrievalAugmentor();
        ChatLanguageModelSingleton chatLanguageModelFactory = ChatLanguageModelSingleton.getInstance();
        EmbeddingStoreContentRetriever embeddingStoreContentRetriever = new EmbeddingStoreContentRetriever();
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
