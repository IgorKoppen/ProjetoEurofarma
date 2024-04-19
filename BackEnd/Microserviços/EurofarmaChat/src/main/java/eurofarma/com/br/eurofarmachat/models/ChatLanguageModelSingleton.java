package eurofarma.com.br.eurofarmachat.models;

import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.ollama.OllamaChatModel;

public class ChatLanguageModelSingleton {
    private static ChatLanguageModelSingleton instance;
    private final ChatLanguageModel model;

    private ChatLanguageModelSingleton() {
        Double temperature = 0.7;
        String urlIsRunning = "http://localhost:11434";
        String modelName = "llama3";
        int maxRetries = 3;
        model = OllamaChatModel.builder().modelName(modelName).baseUrl(urlIsRunning).temperature(temperature).maxRetries(maxRetries).build();
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
