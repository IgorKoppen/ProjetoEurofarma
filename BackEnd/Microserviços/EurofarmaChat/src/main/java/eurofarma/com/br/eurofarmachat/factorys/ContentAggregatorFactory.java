package eurofarma.com.br.eurofarmachat.factorys;

import dev.langchain4j.model.cohere.CohereScoringModel;
import dev.langchain4j.model.scoring.ScoringModel;
import dev.langchain4j.rag.content.aggregator.ContentAggregator;
import dev.langchain4j.rag.content.aggregator.ReRankingContentAggregator;


public class ContentAggregatorFactory {

    public ContentAggregator contentAggregator() {
        ScoringModel scoringModel = CohereScoringModel.withApiKey("J3of50dKfqI5o0LMRdPTsuqgr2LJFJ1PriyAdklJ");
        return ReRankingContentAggregator.builder()
                .scoringModel(scoringModel)
                .minScore(0.8)
                .build();
    }
}
