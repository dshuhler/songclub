package com.shuhler.negadelphia.domain;

import com.shuhler.negadelphia.domain.twitter.TweetCohort;
import com.twitter.clientlib.TwitterCredentialsOAuth2;
import com.twitter.clientlib.api.TwitterApi;
import com.twitter.clientlib.model.TweetSearchResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.UUID;

@Component
public class TwitterManager {

    private Logger logger = LoggerFactory.getLogger(TwitterManager.class);

    private TwitterProperties twitterProperties;
    private TweetCohortRepo tweetCohortRepo;
    private TwitterSearcher twitterSearcher;

    private TwitterApi apiInstance = new TwitterApi();

    @Autowired
    public TwitterManager(TwitterProperties twitterProperties, TweetCohortRepo tweetCohortRepo) {
        this.twitterProperties = twitterProperties;
        this.tweetCohortRepo = tweetCohortRepo;
    }

    @PostConstruct
    private void initializeTwitterApi() {
        TwitterCredentialsOAuth2 credentials = new TwitterCredentialsOAuth2(twitterProperties.getApikey(),
                twitterProperties.getApisecret(), twitterProperties.getBearertoken(), null);
        apiInstance.setTwitterCredentials(credentials);
        twitterSearcher = new TwitterSearcher(apiInstance);
    }


    public void pollForEaglesTweets() {

        String eaglesContext = "context:12.689566314990436352";
        String excludeRetweets = "-is:retweet";


        String query = eaglesContext + " " + excludeRetweets;

        TweetCohort tweetCohort = twitterSearcher.search(query, null);

        tweetCohortRepo.saveToYaml(tweetCohort);
    }


}
