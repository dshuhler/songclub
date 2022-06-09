package com.shuhler.negadelphia.domain;

import com.twitter.clientlib.ApiException;
import com.twitter.clientlib.api.TwitterApi;
import com.twitter.clientlib.model.TweetSearchResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Set;

public class TwitterSearcher {

    private Logger logger = LoggerFactory.getLogger(TwitterSearcher.class);

    private TwitterApi apiInstance;

    private Set<String> tweetFields = Set.of("author_id", "id", "created_at", "context_annotations", "entities");
    private Set<String> userFields = Set.of("name", "username");
    private Set<String> expansions = Set.of("author_id");

    public TwitterSearcher(TwitterApi apiInstance) {
        this.apiInstance = apiInstance;
    }

    public TweetSearchResponse searchByToken(String query, String token) {



        try {
            return apiInstance.tweets().tweetsRecentSearch(query, null, null, null, null,
                    null, null, token, null, expansions, tweetFields, userFields, null, null, null);

        } catch (ApiException e) {
            logger.error("Twitter API error. Status code: {}", e.getCode());
        }
        return null;
    }

    public TweetSearchResponse standardRecentSearch(String query) {


        try {
            return apiInstance.tweets().tweetsRecentSearch(query, null, null, null, null,
                    null, null, null, null, expansions, tweetFields, userFields, null, null, null);

        } catch (ApiException e) {
            logger.error("Twitter API error. Status code: {}", e.getCode());
        }
        return null;
    }
}