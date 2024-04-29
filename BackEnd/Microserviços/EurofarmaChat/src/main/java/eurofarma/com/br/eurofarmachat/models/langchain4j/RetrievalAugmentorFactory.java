package eurofarma.com.br.eurofarmachat.models.langchain4j;

import dev.langchain4j.rag.DefaultRetrievalAugmentor;
import dev.langchain4j.rag.RetrievalAugmentor;
import dev.langchain4j.rag.content.retriever.ContentRetriever;


public class RetrievalAugmentorFactory {

    public static RetrievalAugmentor createRetrievalAugmentor(ContentRetriever contentRetriever) {
        return DefaultRetrievalAugmentor.builder()
                .contentRetriever(contentRetriever)
                .contentAggregator(ContentAggregatorFactory.createAggregator())
                .build();
    }
}