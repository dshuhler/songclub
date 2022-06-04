package com.shuhler.negadelphia.domain;

import com.shuhler.negadelphia.domain.model.TweetCohort;
import com.twitter.clientlib.ApiException;
import com.twitter.clientlib.TwitterCredentialsOAuth2;
import com.twitter.clientlib.api.TwitterApi;
import com.twitter.clientlib.model.Tweet;
import com.twitter.clientlib.model.TweetSearchResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;

@Component
public class TwitterManager {

    private Logger logger = LoggerFactory.getLogger(TwitterManager.class);

    private TwitterProperties twitterProperties;
    private TweetCohortRepo tweetCohortRepo;

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
    }


    public void test() {

        Set<String> tweetFields = new HashSet<>();
        tweetFields.add("author_id");
        tweetFields.add("id");
        tweetFields.add("created_at");
        tweetFields.add("context_annotations");
        tweetFields.add("entities");

        Set<String> userFields = new HashSet<>();
        userFields.add("name");
        userFields.add("user_name");

        String eaglesContext = "context:12.689566314990436352";

        String excludeRetweets = "-is:retweet";

        TweetCohort tweetCohort = new TweetCohort("1");

        String query = eaglesContext + " " + excludeRetweets;
        try {
            TweetSearchResponse tsResponse = apiInstance.tweets().tweetsRecentSearch(query, null, null, null, null,
                    null, null, null, null, null, tweetFields, null, null, null, null);

            for (Tweet tweet : tsResponse.getData()) {
                tweetCohort.addTweet(tweet);
            }

        } catch (ApiException e) {
            logger.error("Twitter API error. Status code: {}", e.getCode());
        }

        tweetCohort.setTimeStampToNow();
        tweetCohortRepo.saveToYaml(tweetCohort);

    }

}
