package eurofarma.com.br.eurofarmachat.models.langchain4j;

import dev.langchain4j.rag.DefaultRetrievalAugmentor;
import dev.langchain4j.rag.RetrievalAugmentor;
import dev.langchain4j.rag.content.retriever.ContentRetriever;


public class RetrievalAugmentorCreater {

   private final RetrievalAugmentor retrievalAugmentor;

    public RetrievalAugmentorCreater(ContentRetriever contentRetriever) {
        ContentAggregatorCreater contentAggregator = new ContentAggregatorCreater();
        this.retrievalAugmentor = DefaultRetrievalAugmentor.builder()
               .contentRetriever(contentRetriever)
               .contentAggregator(contentAggregator.getAggregator())
               .build();
    }

    public RetrievalAugmentor getRetrievalAugmentor() {
        return retrievalAugmentor;
    }
}
