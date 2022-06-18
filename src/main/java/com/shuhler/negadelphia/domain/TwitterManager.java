package com.shuhler.negadelphia.domain;

import com.shuhler.negadelphia.domain.twitter.TweetCohort;
import com.twitter.clientlib.TwitterCredentialsOAuth2;
import com.twitter.clientlib.api.TwitterApi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.OffsetDateTime;
import java.time.temporal.ChronoUnit;

@Component
public class TwitterManager {

    private Logger logger = LoggerFactory.getLogger(TwitterManager.class);

    private TwitterProperties twitterProperties;
    private TweetCohortRepo tweetCohortRepo;
    private TwitterSearcher twitterSearcher;

    private boolean stopPolling = false;
    private TwitterApi apiInstance = new TwitterApi();

    @Autowired
    public TwitterManager(TwitterProperties twitterProperties, TweetCohortRepo tweetCohortRepo) {
        this.twitterProperties = twitterProperties;
        this.tweetCohortRepo = tweetCohortRepo;
    }

    @PostConstruct
    private void initialize() {
        TwitterCredentialsOAuth2 credentials = new TwitterCredentialsOAuth2(twitterProperties.getApikey(),
                twitterProperties.getApisecret(), twitterProperties.getBearertoken(), null);
        apiInstance.setTwitterCredentials(credentials);
        twitterSearcher = new TwitterSearcher(apiInstance);
    }

    @Async
    public void pollForEaglesTweets() {

        stopPolling = false;
        String eaglesContext = "context:12.689566314990436352";
        String excludeRetweets = "-is:retweet";
        String query = eaglesContext + " " + excludeRetweets;

        while (!stopPolling) {

            OffsetDateTime now = OffsetDateTime.now();
            OffsetDateTime startOfLastMinute = now.truncatedTo(ChronoUnit.MINUTES).minusMinutes(1);

            logger.info("Polling at {}", startOfLastMinute);
            TweetCohort tweetCohort = twitterSearcher.search(query, startOfLastMinute, 3);

            tweetCohortRepo.saveToYaml(tweetCohort);

            try {
                logger.info("Waiting to poll...");
                Thread.sleep(100 * 60);
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

}
