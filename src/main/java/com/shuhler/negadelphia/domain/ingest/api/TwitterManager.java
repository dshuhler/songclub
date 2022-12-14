package com.shuhler.negadelphia.domain.ingest.api;

import com.shuhler.negadelphia.domain.ingest.NegaQuery;
import com.shuhler.negadelphia.domain.ingest.TweetCohort;
import com.shuhler.negadelphia.domain.ingest.TweetCohortRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;
import java.time.temporal.ChronoUnit;

@Component
public class TwitterManager {

    private Logger logger = LoggerFactory.getLogger(TwitterManager.class);


    private TweetCohortRepo tweetCohortRepo;
    private TwitterSearcher twitterSearcher;

    private boolean stopPolling = false;


    @Autowired
    public TwitterManager(TweetCohortRepo tweetCohortRepo, TwitterSearcher twitterSearcher) {
        this.tweetCohortRepo = tweetCohortRepo;
        this.twitterSearcher = twitterSearcher;
    }


    @Async
    public void pollingTwitterSearch(NegaQuery negaQuery) {

        stopPolling = false;

        while (!stopPolling) {
            OffsetDateTime startOfLastMinute = startOfMinutesAgo(1);

            logger.info("Polling at {}", startOfLastMinute);
            TweetCohort tweetCohort = twitterSearcher.search(negaQuery.getQuery(), startOfLastMinute, 3);

            tweetCohortRepo.saveToYaml(tweetCohort, negaQuery.getOutputFolderName());

            try {
                logger.info("Waiting to poll...");
                Thread.sleep(1000 * 60);
            } catch (InterruptedException e) {
                logger.info("Poll thread interupted");
                break;
            }
        }

        logger.info("Polling thread stopped");
    }



    public void stopAll() {
        stopPolling = true;
    }

    private OffsetDateTime startOfMinutesAgo(int minutes) {
        return OffsetDateTime.now().truncatedTo(ChronoUnit.MINUTES).minusMinutes(minutes);
    }

}
