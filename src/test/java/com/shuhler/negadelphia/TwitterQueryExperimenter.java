package com.shuhler.negadelphia;

import com.shuhler.negadelphia.domain.ingest.TweetCohortRepo;
import com.shuhler.negadelphia.domain.ingest.api.TwitterSearcher;
import com.shuhler.negadelphia.domain.ingest.TweetCohort;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.OffsetDateTime;
import java.time.temporal.ChronoUnit;

import static com.shuhler.negadelphia.domain.ingest.api.SearchConstants.EAGLES_NO_RETWEETS;


/**
 * Intended for expirimenting without bringing up the application, not actual testing
 */
@SpringBootTest
@ActiveProfiles("local")
public class TwitterQueryExperimenter {

    @Autowired
    private TwitterSearcher twitterSearcher;

    @Autowired
    private TweetCohortRepo tweetCohortRepo;

    @Test
    void runTest() {

        OffsetDateTime now = OffsetDateTime.now();
        OffsetDateTime ago = now.truncatedTo(ChronoUnit.MINUTES).minusMinutes(10);

        TweetCohort tweetCohort = twitterSearcher.search(EAGLES_NO_RETWEETS, ago, 3);

        tweetCohortRepo.saveToYaml(tweetCohort);

    }


}
