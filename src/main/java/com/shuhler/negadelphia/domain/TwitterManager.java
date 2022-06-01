package com.shuhler.negadelphia.domain;

import com.twitter.clientlib.ApiException;
import com.twitter.clientlib.TwitterCredentialsOAuth2;
import com.twitter.clientlib.api.TwitterApi;
import com.twitter.clientlib.model.ResourceUnauthorizedProblem;
import com.twitter.clientlib.model.SingleTweetLookupResponse;

import java.util.HashSet;
import java.util.Set;

public class TwitterManager {



    public static void main (String... args) {
        TwitterManager tm = new TwitterManager();
        //tm.test();

    }
//
//    public void test() {
//
//        TwitterCredentialsOAuth2 credentials = new TwitterCredentialsOAuth2(System.getenv("TWITTER_OAUTH2_CLIENT_ID"),
//                System.getenv("TWITTER_OAUTH2_CLIENT_SECRET"),
//                System.getenv("TWITTER_OAUTH2_ACCESS_TOKEN"),
//                System.getenv("TWITTER_OAUTH2_REFRESH_TOKEN"));
//
//
//
//        TwitterApi apiInstance = new TwitterApi();
//        TwitterCredentialsOAuth2 credentials =
//                new TwitterCredentialsOAuth2(consumer_key,consumer_secret, access_token, access_secret);
//
//        apiInstance.setTwitterCredentials(credentials);
//
//        Set<String> tweetFields = new HashSet<>();
//        tweetFields.add("author_id");
//        tweetFields.add("id");
//        tweetFields.add("created_at");
//
//        try {
//            // findTweetById
//            SingleTweetLookupResponse result = apiInstance.tweets().findTweetById("20", null, tweetFields, null , null, null, null);
//            if(result.getErrors() != null && result.getErrors().size() > 0) {
//                System.out.println("Error:");
//
//                result.getErrors().forEach(e -> {
//                    System.out.println(e.toString());
//                    if (e instanceof ResourceUnauthorizedProblem) {
//                        System.out.println(((ResourceUnauthorizedProblem) e).getTitle() + " " + ((ResourceUnauthorizedProblem) e).getDetail());
//                    }
//                });
//            } else {
//                System.out.println("findTweetById - Tweet Text: " + result.toString());
//            }
//        } catch (ApiException e) {
//            System.err.println("Status code: " + e.getCode());
//            System.err.println("Reason: " + e.getResponseBody());
//            System.err.println("Response headers: " + e.getResponseHeaders());
//            e.printStackTrace();
//        }
//    }



}
