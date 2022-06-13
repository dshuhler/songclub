package com.shuhler.negadelphia.domain.twitter;

import com.twitter.clientlib.model.Tweet;
import com.twitter.clientlib.model.TweetSearchResponse;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;
import java.util.stream.Collectors;

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

    public void addAllFromSearchResponse(TweetSearchResponse tsr) {

        if (tsr.getData() == null) {
            return;
        }

        ResponseExtractor responseExtractor = new ResponseExtractor(tsr);
        tweetDataList.addAll(responseExtractor.asTweetData());
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

    public int numTweets() {
        return tweetDataList.size();
    }

    public String lastTweetId() {
        TreeSet<String> orderedIds = tweetDataList.stream()
                .map(TweetData::getId)
                .collect(Collectors.toCollection(TreeSet::new));
        return orderedIds.last();
    }

}
