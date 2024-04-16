package eurofarma.com.br.eurofarmachat.services;

import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.ollama.OllamaChatModel;

public class ChatLanguageModelSingleton {
    private static ChatLanguageModelSingleton instance;
    private final ChatLanguageModel model;

    private ChatLanguageModelSingleton() {
        Double temperature = 0.6;
        String urlIsRunnig = "http://localhost:11434";
        String modelName = "llama2";
        model = OllamaChatModel.builder().modelName(modelName).baseUrl(urlIsRunnig).temperature(temperature).build();
    }

    public static ChatLanguageModelSingleton getInstance() {
        if (instance == null) {
            instance = new ChatLanguageModelSingleton();
        }
        return instance;
    }

    public ChatLanguageModel getChatLanguageModel() {
        return model;
    }
}
