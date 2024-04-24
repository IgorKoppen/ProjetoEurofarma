package eurofarma.com.br.eurofarmachat.models.langchain4j;

import dev.langchain4j.model.cohere.CohereScoringModel;
import dev.langchain4j.model.scoring.ScoringModel;
import dev.langchain4j.rag.content.aggregator.ContentAggregator;
import dev.langchain4j.rag.content.aggregator.ReRankingContentAggregator;


public class ContentAggregatorCreater {
    private final ContentAggregator aggregator;
    public ContentAggregatorCreater() {
        ScoringModel scoringModel = CohereScoringModel.withApiKey("J3of50dKfqI5o0LMRdPTsuqgr2LJFJ1PriyAdklJ");
        this.aggregator = ReRankingContentAggregator.builder()
                .scoringModel(scoringModel)
                .build();;
    }

    public ContentAggregator getAggregator() {
        return aggregator;
    }
}
