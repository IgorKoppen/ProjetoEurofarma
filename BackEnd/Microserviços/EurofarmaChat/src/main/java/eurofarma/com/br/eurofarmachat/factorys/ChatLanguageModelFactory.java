package eurofarma.com.br.eurofarmachat.factorys;

import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.ollama.OllamaChatModel;

public class ChatLanguageModelFactory {
    public ChatLanguageModel chatLanguageModelOllama() {
        return OllamaChatModel.builder().modelName("llama2").baseUrl("http://localhost:11434").temperature(0.4).build();
    }
}
