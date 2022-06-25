package com.shuhler.negadelphia.domain.twitter;

import com.shuhler.negadelphia.domain.ingest.TweetCohort;
import com.shuhler.negadelphia.domain.ingest.TweetData;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TweetCohortTest {

    @Test
    void lastTweetId() {

        TweetData td1 = new TweetData();
        td1.setId("111");

        TweetData td2 = new TweetData();
        td2.setId("112");

        TweetData td3 = new TweetData();
        td3.setId("113");

        TweetCohort tweetCohort = new TweetCohort("test");
        tweetCohort.getTweetDataList().add(td1);
        tweetCohort.getTweetDataList().add(td2);
        tweetCohort.getTweetDataList().add(td3);

        assertEquals("113", tweetCohort.lastTweetId());


    }

}