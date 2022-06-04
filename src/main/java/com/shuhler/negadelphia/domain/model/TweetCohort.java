package com.shuhler.negadelphia.domain.model;

import com.twitter.clientlib.model.Tweet;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TweetCohort {

    private String id;
    private Instant timeStamp;
    private List<TweetData> tweetDataList = new ArrayList<>();

    public TweetCohort(String id) {
        this.id = id;
    }

    public void addTweet(Tweet tweet) {
        tweetDataList.add(new TweetData(tweet));
    }

    public void setTimeStampToNow() {
        timeStamp = Instant.now();
    }


    public String getId() {
        return id;
    }

    public Instant getTimeStamp() {
        return timeStamp;
    }

    public List<TweetData> getTweetDataList() {
        return tweetDataList;
    }




}
