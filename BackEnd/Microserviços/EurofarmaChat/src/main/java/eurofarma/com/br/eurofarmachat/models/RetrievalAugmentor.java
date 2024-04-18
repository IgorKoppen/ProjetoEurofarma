package eurofarma.com.br.eurofarmachat.models;

import dev.langchain4j.rag.DefaultRetrievalAugmentor;
import dev.langchain4j.rag.content.retriever.EmbeddingStoreContentRetriever;


public class RetrievalAugmentor {

public dev.langchain4j.rag.RetrievalAugmentor RetrievalAugmentor(EmbeddingStoreContentRetriever contentRetriever) {
    ContentAggregator contentAggregatorFactory = new ContentAggregator();
    return DefaultRetrievalAugmentor.builder()
            .contentRetriever(contentRetriever)
            .contentAggregator(contentAggregatorFactory.contentAggregator())
            .build();
}
}
