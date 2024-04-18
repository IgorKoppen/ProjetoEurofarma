package eurofarma.com.br.eurofarmachat.models;

import dev.langchain4j.model.cohere.CohereScoringModel;
import dev.langchain4j.model.scoring.ScoringModel;
import dev.langchain4j.rag.content.aggregator.ReRankingContentAggregator;


public class ContentAggregator {

    public dev.langchain4j.rag.content.aggregator.ContentAggregator contentAggregator() {
        ScoringModel scoringModel = CohereScoringModel.withApiKey("J3of50dKfqI5o0LMRdPTsuqgr2LJFJ1PriyAdklJ");
        return ReRankingContentAggregator.builder()
                .scoringModel(scoringModel)
                .minScore(0.7)
                .build();
    }
}
