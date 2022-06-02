package com.shuhler.negadelphia.domain;

import com.twitter.clientlib.ApiException;
import com.twitter.clientlib.TwitterCredentialsOAuth2;
import com.twitter.clientlib.api.TwitterApi;
import com.twitter.clientlib.model.ResourceUnauthorizedProblem;
import com.twitter.clientlib.model.SingleTweetLookupResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class TwitterManager {

    @Value("${twitter.apikey}")
    private String apikey;

    @Value("${twitter.apisecret}")
    private String apisecret;

    @Value("${twitter.bearertoken}")
    private String bearertoken;

    public void test() {

        TwitterApi apiInstance = new TwitterApi();
        TwitterCredentialsOAuth2 credentials =
                new TwitterCredentialsOAuth2(apikey, apisecret, bearertoken, null);

        apiInstance.setTwitterCredentials(credentials);

        Set<String> tweetFields = new HashSet<>();
        tweetFields.add("author_id");
        tweetFields.add("id");
        tweetFields.add("created_at");

        try {
            // findTweetById
            SingleTweetLookupResponse result = apiInstance.tweets().findTweetById("20", null, tweetFields, null , null, null, null);
            if(result.getErrors() != null && result.getErrors().size() > 0) {
                System.out.println("Error:");

                result.getErrors().forEach(e -> {
                    System.out.println(e.toString());
                    if (e instanceof ResourceUnauthorizedProblem) {
                        System.out.println(((ResourceUnauthorizedProblem) e).getTitle() + " " + ((ResourceUnauthorizedProblem) e).getDetail());
                    }
                });
            } else {
                System.out.println("findTweetById - Tweet Text: " + result.toString());
            }
        } catch (ApiException e) {
            System.err.println("Status code: " + e.getCode());
            System.err.println("Reason: " + e.getResponseBody());
            System.err.println("Response headers: " + e.getResponseHeaders());
            e.printStackTrace();
        }
    }



}
