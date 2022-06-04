package com.shuhler.negadelphia.domain.model;

import com.twitter.clientlib.model.Tweet;

import java.util.ArrayList;
import java.util.List;

public class TweetCohort {

    private String id;
    private List<TweetData> tweetDataList = new ArrayList<>();

    public TweetCohort(String id) {
        this.id = id;
    }

    public void addTweet(Tweet tweet) {
        tweetDataList.add(new TweetData(tweet));
    }

    public List<TweetData> getTweetDataList() {
        return tweetDataList;
    }
}
