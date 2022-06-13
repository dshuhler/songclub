package com.shuhler.negadelphia.domain;

import com.shuhler.negadelphia.domain.twitter.TweetCohort;
import com.twitter.clientlib.ApiException;
import com.twitter.clientlib.api.TwitterApi;
import com.twitter.clientlib.model.TweetSearchResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Set;
import java.util.UUID;

public class TwitterSearcher {

    private static int MAX_RESULTS = 100;

    private Logger logger = LoggerFactory.getLogger(TwitterSearcher.class);

    private TwitterApi apiInstance;

    private Set<String> tweetFields = Set.of("author_id", "id", "created_at", "context_annotations", "entities");
    private Set<String> userFields = Set.of("name", "username");
    private Set<String> expansions = Set.of("author_id");

    public TwitterSearcher(TwitterApi apiInstance) {
        this.apiInstance = apiInstance;
    }

    public TweetCohort search(String query, String sinceId, int maxPages) {

        // do initial query w/o token
        TweetSearchResponse tsResponse = standardRecentSearch(query);

        TweetCohort tweetCohort = new TweetCohort(UUID.randomUUID().toString());
        tweetCohort.addAllFromSearchResponse(tsResponse);

        boolean hasMorePages = true;
        if (tsResponse.getMeta() == null || tsResponse.getMeta().getNextToken() == null) {
            hasMorePages = false;
        }

        int numPages = 1;
        while (hasMorePages) {
            logger.info("Querying for additional page number {}", numPages);
            tsResponse = searchByToken(query, tsResponse.getMeta().getNextToken());
            tweetCohort.addAllFromSearchResponse(tsResponse);
            numPages++;


            if (tsResponse.getMeta() == null || tsResponse.getMeta().getNextToken() == null) {
                hasMorePages = false;
            }

            if (numPages > maxPages) {
                break;
            }

        }

        tweetCohort.setTimeStampToNow();
        return tweetCohort;
    }





    public TweetSearchResponse searchByToken(String query, String token) {

        // Note: the API has a "nextToken" and a "paginationToken" with the same exact description
        // You want to use nextToken. see: https://twittercommunity.com/t/why-does-timeline-use-pagination-token-while-search-uses-next-token/150963

        try {
            return apiInstance.tweets().tweetsRecentSearch(query, null, null, null, null,
                    MAX_RESULTS, null, token, null, expansions, tweetFields, userFields, null, null, null);

        } catch (ApiException e) {
            logger.error("Twitter API error. Status code: {}", e.getCode());
        }
        return null;
    }

    public TweetSearchResponse standardRecentSearch(String query) {


        try {
            return apiInstance.tweets().tweetsRecentSearch(query, null, null, null, null,
                    MAX_RESULTS, null, null, null, expansions, tweetFields, userFields, null, null, null);

        } catch (ApiException e) {
            logger.error("Twitter API error. Status code: {}", e.getCode());
        }
        return null;
    }
}
