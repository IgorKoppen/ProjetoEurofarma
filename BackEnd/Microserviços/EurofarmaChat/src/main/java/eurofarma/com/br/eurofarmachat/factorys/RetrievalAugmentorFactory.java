package eurofarma.com.br.eurofarmachat.factorys;

import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.rag.DefaultRetrievalAugmentor;
import dev.langchain4j.rag.RetrievalAugmentor;
import dev.langchain4j.rag.content.retriever.EmbeddingStoreContentRetriever;
import dev.langchain4j.rag.query.transformer.CompressingQueryTransformer;
import dev.langchain4j.rag.query.transformer.QueryTransformer;

public class RetrievalAugmentorFactory {

    private final ChatLanguageModelFactory chatLanguageModelFactory = new ChatLanguageModelFactory();

public RetrievalAugmentor RetrievalAugmentor(EmbeddingStoreContentRetriever contentRetriever) {
    ContentAggregatorFactory contentAggregatorFactory = new ContentAggregatorFactory();
    QueryTransformer queryTransformer = new CompressingQueryTransformer(chatLanguageModelFactory.chatLanguageModelOllama());
    return DefaultRetrievalAugmentor.builder()
            .queryTransformer(queryTransformer)
            .contentRetriever(contentRetriever)
            .contentAggregator(contentAggregatorFactory.contentAggregator())
            .build();
}
}
