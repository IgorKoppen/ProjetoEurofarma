package eurofarma.com.br.eurofarmachat.models.langchain4j;

import dev.langchain4j.model.cohere.CohereScoringModel;
import dev.langchain4j.model.scoring.ScoringModel;
import dev.langchain4j.rag.content.aggregator.ContentAggregator;
import dev.langchain4j.rag.content.aggregator.ReRankingContentAggregator;


public class  ContentAggregatorFactory {

    public static ContentAggregator createAggregator() {
        ScoringModel scoringModel = CohereScoringModel.withApiKey("J3of50dKfqI5o0LMRdPTsuqgr2LJFJ1PriyAdklJ");
        return ReRankingContentAggregator.builder()
                .scoringModel(scoringModel)
                .build();
    }
}