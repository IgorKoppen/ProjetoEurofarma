package eurofarma.com.br.eurofarmachat.factorys;

import dev.langchain4j.rag.DefaultRetrievalAugmentor;
import dev.langchain4j.rag.RetrievalAugmentor;
import dev.langchain4j.rag.content.retriever.EmbeddingStoreContentRetriever;


public class RetrievalAugmentorFactory {

public RetrievalAugmentor RetrievalAugmentor(EmbeddingStoreContentRetriever contentRetriever) {
    ContentAggregatorFactory contentAggregatorFactory = new ContentAggregatorFactory();
    return DefaultRetrievalAugmentor.builder()
            .contentRetriever(contentRetriever)
            .contentAggregator(contentAggregatorFactory.contentAggregator())
            .build();
}
}
